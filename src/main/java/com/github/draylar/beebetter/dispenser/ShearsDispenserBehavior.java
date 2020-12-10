package com.github.draylar.beebetter.dispenser;

import com.github.draylar.beebetter.block.ModdedBeehiveBlock;
import com.github.draylar.beebetter.util.BeeState;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class ShearsDispenserBehavior extends FallibleItemDispenserBehavior {
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        World world = pointer.getWorld();
        if (!world.isClient()) {
            this.setSuccess(false);
            BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
            List<SheepEntity> list = world.getNonSpectatingEntities(SheepEntity.class, new Box(blockPos));

            for (SheepEntity sheepEntity : list) {
                if (sheepEntity.isAlive() && !sheepEntity.isSheared() && !sheepEntity.isBaby()) {
                    sheepEntity.setSheared(true);
                    if (stack.damage(1, world.random, null)) {
                        stack.setCount(0);
                    }

                    this.setSuccess(true);
                    break;
                }
            }

            if (!this.isSuccess()) {
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.isIn(BlockTags.BEEHIVES)) {
                    Block block = blockState.getBlock();
                    if (block instanceof BeehiveBlock) {
                        int i = blockState.get(BeehiveBlock.HONEY_LEVEL);
                        if (i >= 5) {
                            if (stack.damage(1, world.random, null)) {
                                stack.setCount(0);
                            }

                            BeehiveBlock.dropHoneycomb(world, blockPos);
                            ((BeehiveBlock) block).takeHoney(world, blockState, blockPos, null, BeehiveBlockEntity.BeeState.BEE_RELEASED);
                            this.setSuccess(true);
                        }
                    } else if (block instanceof ModdedBeehiveBlock) {
                        int i = blockState.get(((ModdedBeehiveBlock) block).getHoneyProperty());
                        if (i >= 5) {
                            if (stack.damage(1, world.random, null)) {
                                stack.setCount(0);
                            }

                            ModdedBeehiveBlock.dropHoneycomb(world, blockPos);
                            ((ModdedBeehiveBlock) block).takeHoney(world, blockState, blockPos, null, BeeState.BEE_RELEASED);
                            this.setSuccess(true);
                        }
                    }
                }
            }
        }

        return stack;
    }
}
