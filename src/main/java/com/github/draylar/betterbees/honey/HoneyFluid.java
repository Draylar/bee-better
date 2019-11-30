package com.github.draylar.betterbees.honey;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldView;

import com.github.draylar.betterbees.registry.BeeBlocks;
import com.github.draylar.betterbees.registry.BeeFluids;
import com.github.draylar.betterbees.registry.BeeItems;

public abstract class HoneyFluid extends BaseFluid {
	
	@Override
	public Fluid getFlowing() {
		return BeeFluids.FLOWING_HONEY;
	}
	
	@Override
	public Fluid getStill() {
		return BeeFluids.HONEY;
	}
	
	@Override
	public Item getBucketItem() {
		return BeeItems.HONEY_BUCKET;
	}
	
	@Override
	protected boolean isInfinite() {
		return false;
	}
	
	@Override
	protected void beforeBreakingBlock(IWorld world, BlockPos pos, BlockState state) {
		BlockEntity blockEntity = state.getBlock().hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world.getWorld(), pos, blockEntity);
	}
	
	// flow speed; 4 is water, 2 is lava
	@Override
	protected int method_15733(WorldView worldView) {
		return 1;
	}
	
	@Override
	protected int getLevelDecreasePerBlock(WorldView worldView) {
		return 1;
	}
	
	@Override
	protected boolean method_15777(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
		return direction == Direction.DOWN && !fluid.matches(FluidTags.WATER);
	}
	
	@Override
	public int getTickRate(WorldView worldView) {
		return 40;
	}
	
	@Override
	protected float getBlastResistance() {
		return 50;
	}
	
	@Override
	protected BlockState toBlockState(FluidState fluidState) {
		return BeeBlocks.HONEY_FLUID.getDefaultState().with(FluidBlock.LEVEL, method_15741(fluidState));
	}
	
	@Override
	protected ParticleEffect getParticle() {
		return ParticleTypes.DRIPPING_HONEY;
	}
	
	
	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid == BeeFluids.FLOWING_HONEY || fluid == BeeFluids.HONEY;
	}
	
	public static class Flowing extends HoneyFluid {
		@Override
		public boolean isStill(FluidState fluidState) {
			return false;
		}
		
		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}
		
		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}
	}
	
	public static class Still extends HoneyFluid {
		@Override
		public boolean isStill(FluidState fluidState) {
			return true;
		}
		
		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}
	}
}
