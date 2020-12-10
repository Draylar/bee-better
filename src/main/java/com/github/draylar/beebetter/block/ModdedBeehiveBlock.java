package com.github.draylar.beebetter.block;

import com.github.draylar.beebetter.entity.ApiaryBlockEntity;
import com.github.draylar.beebetter.entity.ModdedBeehiveBlockEntity;
import com.github.draylar.beebetter.registry.BeeItems;
import com.github.draylar.beebetter.util.BeeState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.List;
import java.util.Random;

public abstract class ModdedBeehiveBlock extends Block implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public ModdedBeehiveBlock(Settings settings) {
        super(settings);
    }

    public static void dropHoneycomb(World world, BlockPos pos) {
        for (int i = 0; i < 3; ++i) {
            dropStack(world, pos, new ItemStack(Items.HONEYCOMB, 1));
        }

        dropStack(world, pos, new ItemStack(BeeItems.BEESWAX_FLAKE, 1));
    }

    public static List<BeeEntity> getNearbyBees(World world, BlockPos pos) {
        return world.getNonSpectatingEntities(BeeEntity.class, (new Box(pos)).expand(8.0D, 6.0D, 8.0D));
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getHoneyLevel(state);
    }

    public abstract int getHoneyLevel(BlockState state);

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getStackInHand(hand);
        int honeyLevel = getHoneyLevel(state);
        boolean harvested = false;

        if (honeyLevel >= 5) {
            if (heldStack.getItem() == Items.SHEARS) {
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                dropHoneycomb(world, pos);
                heldStack.damage(1, player, ((playerx) -> playerx.sendToolBreakStatus(hand)));
                harvested = true;
            } else if (heldStack.getItem() == Items.GLASS_BOTTLE) {
                heldStack.decrement(1);
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);

                if (heldStack.isEmpty()) {
                    player.setStackInHand(hand, new ItemStack(Items.HONEY_BOTTLE));
                } else if (!player.inventory.insertStack(new ItemStack(Items.HONEY_BOTTLE))) {
                    player.dropItem(new ItemStack(Items.HONEY_BOTTLE), false);
                }

                harvested = true;
            }
        }

        if (harvested) {
            if (!CampfireBlock.isLitCampfireInRange(world, pos)) {
                if (this.hasBees(world, pos)) {
                    this.angerNearbyBees(world, pos);
                }

                this.takeHoney(world, state, pos, player, BeeState.EMERGENCY);
            } else {
                this.takeHoney(world, state, pos);
            }

            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    @Environment(EnvType.CLIENT)
    protected void addHoneyParticle(World world, BlockPos blockPos, VoxelShape voxelShape, double d) {
        this.addHoneyParticle(world, (double) blockPos.getX() + voxelShape.getMin(Direction.Axis.X), (double) blockPos.getX() + voxelShape.getMax(Direction.Axis.X), (double) blockPos.getZ() + voxelShape.getMin(Direction.Axis.Z), (double) blockPos.getZ() + voxelShape.getMax(Direction.Axis.Z), d);
    }

    @Environment(EnvType.CLIENT)
    protected void addHoneyParticle(World world, double d, double e, double f, double g, double h) {
        world.addParticle(ParticleTypes.DRIPPING_HONEY, MathHelper.lerp(world.random.nextDouble(), d, e), h, MathHelper.lerp(world.random.nextDouble(), f, g), 0.0D, 0.0D, 0.0D);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getPlayerFacing().getOpposite());
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (getHoneyLevel(state) >= 5) {
            for (int i = 0; i < random.nextInt(1) + 1; ++i) {
                this.playHoneyParticles(world, pos, state);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    protected void playHoneyParticles(World world, BlockPos blockPos, BlockState blockState) {
        if (blockState.getFluidState().isEmpty() && world.random.nextFloat() >= 0.3F) {
            VoxelShape voxelShape = blockState.getCollisionShape(world, blockPos);
            double d = voxelShape.getMax(Direction.Axis.Y);

            if (d >= 1.0D && !blockState.isIn(BlockTags.IMPERMEABLE)) {
                double e = voxelShape.getMin(Direction.Axis.Y);

                if (e > 0.0D) {
                    this.addHoneyParticle(world, blockPos, voxelShape, (double) blockPos.getY() + e - 0.05D);
                } else {
                    BlockPos blockPos2 = blockPos.down();
                    BlockState blockState2 = world.getBlockState(blockPos2);
                    VoxelShape voxelShape2 = blockState2.getCollisionShape(world, blockPos2);
                    double f = voxelShape2.getMax(Direction.Axis.Y);

                    if ((f < 1.0D || !blockState2.isFullCube(world, blockPos2)) && blockState2.getFluidState().isEmpty()) {
                        this.addHoneyParticle(world, blockPos, voxelShape, (double) blockPos.getY() - 0.05D);
                    }
                }
            }
        }
    }

    protected void angerNearbyBees(World world, BlockPos pos) {
        List<BeeEntity> nearbyBees = getNearbyBees(world, pos);

        if (!nearbyBees.isEmpty()) {
            List<PlayerEntity> nearbyPlayers = world.getNonSpectatingEntities(PlayerEntity.class, (new Box(pos)).expand(8.0D, 6.0D, 8.0D));
            int playerCount = nearbyPlayers.size();

            for (BeeEntity beeEntity : nearbyBees) {
                if (beeEntity.getTarget() == null) {
                    beeEntity.setAttacker(nearbyPlayers.get(world.random.nextInt(playerCount)));
                }
            }
        }
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient && blockEntity instanceof ModdedBeehiveBlockEntity) {
            ModdedBeehiveBlockEntity hiveBlockEntity = (ModdedBeehiveBlockEntity) blockEntity;

            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                hiveBlockEntity.angerBees(player, state, BeeState.EMERGENCY);
                world.updateComparators(pos, this);
            }

            this.angerNearbyBees(world, pos);
        }
    }

    protected boolean hasBees(World world, BlockPos blockPos) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity instanceof ModdedBeehiveBlockEntity) {
            ModdedBeehiveBlockEntity hiveBlockEntity = (ModdedBeehiveBlockEntity) blockEntity;
            return !hiveBlockEntity.hasNoBees();
        } else {
            return false;
        }
    }

    public void takeHoney(World world, BlockState blockState, BlockPos blockPos, PlayerEntity playerEntity, BeeState beeState) {
        takeHoney(world, blockState, blockPos);
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity instanceof ModdedBeehiveBlockEntity) {
            ModdedBeehiveBlockEntity hiveBlockEntity = (ModdedBeehiveBlockEntity) blockEntity;
            hiveBlockEntity.angerBees(playerEntity, blockState, beeState);
        }
    }

    public abstract void setHoneyLevel(World world, BlockState state, BlockPos pos, int level);

    public void addHoney(World world, BlockState state, BlockPos pos, int honey) {
        setHoneyLevel(world, state, pos, Math.min(getHoneyLevel(state) + honey, getMaxHoneyLevel()));
    }

    public abstract int getMaxHoneyLevel();

    public void takeHoney(World world, BlockState state, BlockPos pos, int honey) {
        setHoneyLevel(world, state, pos, Math.max(getHoneyLevel(state) - honey, 0));
    }

    protected boolean takeHoney(World world, BlockState state, BlockPos pos) {
        int honeyLevel = getHoneyLevel(state);

        if (honeyLevel >= 5) {
            takeHoney(world, state, pos, 5);
            return true;
        }
        return false;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public abstract ModdedBeehiveBlockEntity createBlockEntity(BlockView view);

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative() && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof ModdedBeehiveBlockEntity) {
                ModdedBeehiveBlockEntity beehiveBlockEntity = (ModdedBeehiveBlockEntity) blockEntity;
                ItemStack itemStack = new ItemStack(this);
                int honeyLevel = getHoneyLevel(state);
                boolean hasBees = !beehiveBlockEntity.hasNoBees();

                if (!hasBees && honeyLevel == 0) {
                    return;
                }

                CompoundTag hiveTag;

                if (hasBees) {
                    hiveTag = new CompoundTag();
                    hiveTag.put("Bees", beehiveBlockEntity.getBees());
                    itemStack.putSubTag("BlockEntityTag", hiveTag);
                }

                hiveTag = new CompoundTag();
                hiveTag.putInt("honey_level", honeyLevel);
                itemStack.putSubTag("BlockStateTag", hiveTag);

                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
        }

        super.onBreak(world, pos, state, player);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        Entity entity = builder.getNullable(LootContextParameters.THIS_ENTITY);

        if (entity instanceof TntEntity || entity instanceof CreeperEntity || entity instanceof WitherSkullEntity || entity instanceof WitherEntity || entity instanceof TntMinecartEntity) {
            BlockEntity blockEntity = builder.getNullable(LootContextParameters.BLOCK_ENTITY);

            if (blockEntity instanceof ApiaryBlockEntity) {
                ApiaryBlockEntity ApiaryBlockEntity = (ApiaryBlockEntity) blockEntity;
                ApiaryBlockEntity.angerBees(null, state, BeeState.EMERGENCY);
            }
        }

        return super.getDroppedStacks(state, builder);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (world.getBlockState(neighborPos).getBlock() instanceof FireBlock) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof ModdedBeehiveBlockEntity) {
                ModdedBeehiveBlockEntity hiveBlockEntity = (ModdedBeehiveBlockEntity) blockEntity;
                hiveBlockEntity.angerBees(null, state, BeeState.EMERGENCY);
            }
        }

        return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
    }

    public abstract IntProperty getHoneyProperty();
}
