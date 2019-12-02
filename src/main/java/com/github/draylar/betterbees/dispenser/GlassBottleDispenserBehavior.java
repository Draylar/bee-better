package com.github.draylar.betterbees.dispenser;

import net.minecraft.block.BeeHiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.BeeHiveBlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import com.github.draylar.betterbees.block.ModdedBeehiveBlock;
import com.github.draylar.betterbees.util.BeeState;

public class GlassBottleDispenserBehavior extends FallibleItemDispenserBehavior {
	private final ItemDispenserBehavior field_20533 = new ItemDispenserBehavior();
	
	private ItemStack method_22141(BlockPointer blockPointer, ItemStack emptyBottleStack, ItemStack filledBottleStack) {
		emptyBottleStack.decrement(1);
		if (emptyBottleStack.isEmpty()) {
			return filledBottleStack.copy();
		} else {
			if (((DispenserBlockEntity)blockPointer.getBlockEntity()).addToFirstFreeSlot(filledBottleStack.copy()) < 0) {
				this.field_20533.dispense(blockPointer, filledBottleStack.copy());
			}
			
			return emptyBottleStack;
		}
	}
	
	public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		this.success = false;
		IWorld iWorld = pointer.getWorld();
		BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
		BlockState blockState = iWorld.getBlockState(blockPos);
		Block block = blockState.getBlock();
		if (block.matches(BlockTags.BEEHIVES)) {
			if(block instanceof BeeHiveBlock && blockState.get(BeeHiveBlock.HONEY_LEVEL) >= 5) {
				((BeeHiveBlock)blockState.getBlock()).emptyHoney(iWorld.getWorld(), blockState, blockPos, (PlayerEntity)null, BeeHiveBlockEntity.BeeState.BEE_RELEASED);
				this.success = true;
				return this.method_22141(pointer, stack, new ItemStack(Items.HONEY_BOTTLE));
			} else if(block instanceof ModdedBeehiveBlock && blockState.get(((ModdedBeehiveBlock) block).getHoneyProperty()) >= 5) {
				((ModdedBeehiveBlock)blockState.getBlock()).takeHoneyAndAnger(iWorld.getWorld(), blockState, blockPos, (PlayerEntity)null, BeeState.BEE_RELEASED);
				this.success = true;
				return this.method_22141(pointer, stack, new ItemStack(Items.HONEY_BOTTLE));
			} else {
				return super.dispenseSilently(pointer, stack);
			}
		} else if (iWorld.getFluidState(blockPos).matches(FluidTags.WATER)) {
			this.success = true;
			return this.method_22141(pointer, stack, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
		} else {
			return super.dispenseSilently(pointer, stack);
		}
	}
}
