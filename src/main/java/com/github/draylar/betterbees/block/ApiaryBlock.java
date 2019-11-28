package com.github.draylar.betterbees.block;

import com.github.draylar.betterbees.entity.ApiaryBlockEntity;
import com.github.draylar.betterbees.entity.ModdedBeehiveBlockEntity;
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

public class ApiaryBlock extends ModdedBeehiveBlock {
    
    public static final int MAX_HONEY = 10;
    public static final IntProperty HONEY_LEVEL = IntProperty.of("honey_level", 0, MAX_HONEY);
    public static final VoxelShape SHAPE = Block.createCuboidShape(1, 0, 1, 15, 16, 15);

    public ApiaryBlock(Block.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(HONEY_LEVEL, 0).with(FACING, Direction.NORTH));
    }
    
    @Override
    public int getHoneyLevel(BlockState state) {
        return state.get(HONEY_LEVEL);
    }
    
    @Override
    public void setHoneyLevel(World world, BlockState state, BlockPos pos, int level) {
        world.setBlockState(pos, state.with(HONEY_LEVEL, level));
    }
    
    @Override
    protected int getMaxHoneyLevel() {
        return MAX_HONEY;
    }
    
    @Override
    public ModdedBeehiveBlockEntity createBlockEntity(BlockView view) {
        return new ApiaryBlockEntity();
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HONEY_LEVEL, FACING);
    }
}
