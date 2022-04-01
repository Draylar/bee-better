package com.github.draylar.beebetter.dispenser;

import com.github.draylar.beebetter.block.ModdedBeehiveBlock;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlassBottleDispenserBehavior extends FallibleItemDispenserBehavior {

    private final ItemDispenserBehavior dispenserBehavior = new ItemDispenserBehavior();

    private ItemStack fillHoney(BlockPointer blockPointer, ItemStack emptyBottleStack, ItemStack filledBottleStack) {
        emptyBottleStack.decrement(1);
        if (emptyBottleStack.isEmpty()) {
            return filledBottleStack.copy();
        } else {
            if (((DispenserBlockEntity) blockPointer.getBlockEntity()).addToFirstFreeSlot(filledBottleStack.copy()) < 0) {
                this.dispenserBehavior.dispense(blockPointer, filledBottleStack.copy());
            }

            return emptyBottleStack;
        }
    }

    @Override
    public ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        setSuccess(false);
        World world = pointer.getWorld();
        BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if (blockState.isIn(BlockTags.BEEHIVES)) {
            if (block instanceof BeehiveBlock && blockState.get(BeehiveBlock.HONEY_LEVEL) >= 5) {
                ((BeehiveBlock) blockState.getBlock()).takeHoney(world, blockState, blockPos, null, BeehiveBlockEntity.BeeState.BEE_RELEASED);
                setSuccess(true);
                return this.fillHoney(pointer, stack, new ItemStack(Items.HONEY_BOTTLE));
            } else if (block instanceof ModdedBeehiveBlock && blockState.get(((ModdedBeehiveBlock) block).getHoneyProperty()) >= 5) {
                ((ModdedBeehiveBlock) blockState.getBlock()).takeHoney(world, blockState, blockPos, null, BeehiveBlockEntity.BeeState.BEE_RELEASED);
                setSuccess(true);
                return this.fillHoney(pointer, stack, new ItemStack(Items.HONEY_BOTTLE));
            } else {
                return super.dispenseSilently(pointer, stack);
            }
        } else if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
            setSuccess(true);
            return this.fillHoney(pointer, stack, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
        } else {
            return super.dispenseSilently(pointer, stack);
        }
    }
}
