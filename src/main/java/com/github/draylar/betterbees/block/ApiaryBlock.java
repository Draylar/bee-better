package com.github.draylar.betterbees.block;

import com.github.draylar.betterbees.entity.ApiaryBlockEntity;
import com.github.draylar.betterbees.registry.BeeItems;
import com.github.draylar.betterbees.util.BeeState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityContext;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ApiaryBlock extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final IntProperty HONEY_LEVEL = IntProperty.of("honey_level", 0, 10);
    public static final VoxelShape SHAPE = Block.createCuboidShape(1, 0, 1, 15, 16, 15);

    public ApiaryBlock(Block.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(HONEY_LEVEL, 0).with(FACING, Direction.NORTH));
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(HONEY_LEVEL);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
        return SHAPE;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient && blockEntity instanceof ApiaryBlockEntity) {
            ApiaryBlockEntity ApiaryBlockEntity = (ApiaryBlockEntity)blockEntity;

            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
                ApiaryBlockEntity.angerBees(player, state, BeeState.EMERGENCY);
                world.updateHorizontalAdjacent(pos, this);
            }

            this.angerNearbyBees(world, pos);
        }
    }

    private void angerNearbyBees(World world, BlockPos blockPos) {
        List<BeeEntity> nearbyBees = world.getNonSpectatingEntities(BeeEntity.class, (new Box(blockPos)).expand(8.0D, 6.0D, 8.0D));

        if (!nearbyBees.isEmpty()) {
            List<PlayerEntity> nearbyPlayers = world.getNonSpectatingEntities(PlayerEntity.class, (new Box(blockPos)).expand(8.0D, 6.0D, 8.0D));
            int playerCount = nearbyPlayers.size();

            for (BeeEntity beeEntity : nearbyBees) {
                if (beeEntity.getTarget() == null) {
                    beeEntity.setBeeAttacker(nearbyPlayers.get(world.random.nextInt(playerCount)));
                }
            }
        }
    }

    private static void dropHoneycomb(World world, BlockPos pos) {
        for(int i = 0; i < 3; ++i) {
            dropStack(world, pos, new ItemStack(Items.HONEYCOMB, 1));
        }

        dropStack(world, pos, new ItemStack(BeeItems.BEESWAX_FLAKE, 1));

        //  10 levels = 6 combs
        if(world.getBlockState(pos).get(HONEY_LEVEL) == 10) {
            for(int i = 0; i < 3; ++i) {
                dropStack(world, pos, new ItemStack(Items.HONEYCOMB, 1));
            }

            dropStack(world, pos, new ItemStack(BeeItems.BEESWAX_FLAKE, 1));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldStack = player.getStackInHand(hand);
        int honeyLevel = state.get(HONEY_LEVEL);
        boolean shearedHive = false;

        if (honeyLevel >= 5) {
            if (heldStack.getItem() == Items.SHEARS) {
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                dropHoneycomb(world, pos);
                heldStack.damage(1, player, ((playerx) -> playerx.sendToolBreakStatus(hand)));
                shearedHive = true;
            }

            else if (heldStack.getItem() == Items.GLASS_BOTTLE) {
                heldStack.decrement(1);
                world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);

                if (heldStack.isEmpty()) {
                    player.setStackInHand(hand, new ItemStack(Items.HONEY_BOTTLE));
                }

                else if (!player.inventory.insertStack(new ItemStack(Items.HONEY_BOTTLE))) {
                    player.dropItem(new ItemStack(Items.HONEY_BOTTLE), false);
                }

                shearedHive = true;
            }
        }

        if (shearedHive) {
            if (!CampfireBlock.method_23895(world, pos, 5)) {
                if (this.hasBees(world, pos)) {
                    this.angerNearbyBees(world, pos);
                }

                this.emptyHoney(world, state, pos, player, BeeState.EMERGENCY);
            } else {
                this.resetHoneyLevel(world, state, pos);

                if (player instanceof ServerPlayerEntity) {
                    Criterions.SAFELY_HARVEST_HONEY.test((ServerPlayerEntity) player, pos, heldStack);
                }
            }

            return ActionResult.SUCCESS;
        } else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    private boolean hasBees(World world, BlockPos blockPos) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity instanceof ApiaryBlockEntity) {
            ApiaryBlockEntity ApiaryBlockEntity = (ApiaryBlockEntity) blockEntity;
            return !ApiaryBlockEntity.hasNoBees();
        } else {
            return false;
        }
    }

    private void emptyHoney(World world, BlockState blockState, BlockPos blockPos, PlayerEntity playerEntity, BeeState beeState) {
        this.resetHoneyLevel(world, blockState, blockPos);
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity instanceof ApiaryBlockEntity) {
            ApiaryBlockEntity ApiaryBlockEntity = (ApiaryBlockEntity) blockEntity;
            ApiaryBlockEntity.angerBees(playerEntity, blockState, beeState);
        }
    }

    private void resetHoneyLevel(World world, BlockState blockState, BlockPos blockPos) {
        int honeyLevel = blockState.get(HONEY_LEVEL);

        // between 5 and under 10, decrement once
        if(honeyLevel >= 5 && honeyLevel != 10) {
            world.setBlockState(blockPos, blockState.with(HONEY_LEVEL, honeyLevel - 5), 3);
        }

        // level is 10, set to 0
        else if (honeyLevel == 10) {
            world.setBlockState(blockPos, blockState.with(HONEY_LEVEL, 0), 3);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(HONEY_LEVEL) >= 5) {
            for(int i = 0; i < random.nextInt(1) + 1; ++i) {
                this.playHoneyParticles(world, pos, state);
            }
        }
    }

    @Environment(EnvType.CLIENT)
    private void playHoneyParticles(World world, BlockPos blockPos, BlockState blockState) {
        if (blockState.getFluidState().isEmpty() && world.random.nextFloat() >= 0.3F) {
            VoxelShape voxelShape = blockState.getCollisionShape(world, blockPos);
            double d = voxelShape.getMaximum(Direction.Axis.Y);

            if (d >= 1.0D && !blockState.matches(BlockTags.IMPERMEABLE)) {
                double e = voxelShape.getMinimum(Direction.Axis.Y);

                if (e > 0.0D) {
                    this.addHoneyParticle(world, blockPos, voxelShape, (double)blockPos.getY() + e - 0.05D);
                } else {
                    BlockPos blockPos2 = blockPos.method_10074();
                    BlockState blockState2 = world.getBlockState(blockPos2);
                    VoxelShape voxelShape2 = blockState2.getCollisionShape(world, blockPos2);
                    double f = voxelShape2.getMaximum(Direction.Axis.Y);

                    if ((f < 1.0D || !blockState2.isFullCube(world, blockPos2)) && blockState2.getFluidState().isEmpty()) {
                        this.addHoneyParticle(world, blockPos, voxelShape, (double)blockPos.getY() - 0.05D);
                    }
                }
            }
        }
    }

    @Environment(EnvType.CLIENT)
    private void addHoneyParticle(World world, BlockPos blockPos, VoxelShape voxelShape, double d) {
        this.addHoneyParticle(world, (double)blockPos.getX() + voxelShape.getMinimum(Direction.Axis.X), (double)blockPos.getX() + voxelShape.getMaximum(Direction.Axis.X), (double)blockPos.getZ() + voxelShape.getMinimum(Direction.Axis.Z), (double)blockPos.getZ() + voxelShape.getMaximum(Direction.Axis.Z), d);
    }

    @Environment(EnvType.CLIENT)
    private void addHoneyParticle(World world, double d, double e, double f, double g, double h) {
        world.addParticle(ParticleTypes.DRIPPING_HONEY, MathHelper.lerp(world.random.nextDouble(), d, e), h, MathHelper.lerp(world.random.nextDouble(), f, g), 0.0D, 0.0D, 0.0D);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HONEY_LEVEL, FACING);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new ApiaryBlockEntity();
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative() && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof ApiaryBlockEntity) {
                ApiaryBlockEntity ApiaryBlockEntity = (ApiaryBlockEntity)blockEntity;
                ItemStack itemStack = new ItemStack(this);
                int honeyLevel = state.get(HONEY_LEVEL);
                boolean hasBees = !ApiaryBlockEntity.hasNoBees();

                if (!hasBees && honeyLevel == 0) {
                    return;
                }

                CompoundTag apiaryTag;

                if (hasBees) {
                    apiaryTag = new CompoundTag();
                    apiaryTag.put("Bees", ApiaryBlockEntity.getBees());
                    itemStack.putSubTag("BlockEntityTag", apiaryTag);
                }

                apiaryTag = new CompoundTag();
                apiaryTag.putInt("honey_level", honeyLevel);
                itemStack.putSubTag("BlockStateTag", apiaryTag);

                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), itemStack);
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
                ApiaryBlockEntity ApiaryBlockEntity = (ApiaryBlockEntity)blockEntity;
                ApiaryBlockEntity.angerBees(null, state, BeeState.EMERGENCY);
            }
        }

        return super.getDroppedStacks(state, builder);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
        if (world.getBlockState(neighborPos).getBlock() instanceof FireBlock) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof ApiaryBlockEntity) {
                ApiaryBlockEntity ApiaryBlockEntity = (ApiaryBlockEntity) blockEntity;
                ApiaryBlockEntity.angerBees(null, state, BeeState.EMERGENCY);
            }
        }

        return super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
    }
}
