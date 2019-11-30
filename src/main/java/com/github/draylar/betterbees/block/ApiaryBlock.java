package com.github.draylar.betterbees.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import com.github.draylar.betterbees.entity.ApiaryBlockEntity;
import com.github.draylar.betterbees.entity.ModdedBeehiveBlockEntity;

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
