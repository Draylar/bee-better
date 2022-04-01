package com.github.draylar.beebetter.block;

import com.github.draylar.beebetter.mixin.BeehiveBlockAccessor;
import com.github.draylar.beebetter.registry.BeeItems;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public abstract class ModdedBeehiveBlock extends BeehiveBlock {

    public ModdedBeehiveBlock(Settings settings) {
        super(settings);
    }

    public static void dropHoneycomb(World world, BlockPos pos) {
        for (int i = 0; i < 3; ++i) {
            dropStack(world, pos, new ItemStack(Items.HONEYCOMB, 1));
        }

        dropStack(world, pos, new ItemStack(BeeItems.BEESWAX_FLAKE, 1));
    }

    public void takeHoney(World world, BlockState blockState, BlockPos blockPos) {
        world.setBlockState(blockPos, blockState.with(getHoneyProperty(), 0), 3);
    }

    public abstract IntProperty getHoneyProperty();

    public abstract int getMaxHoneyLevel();

    public abstract int getHoneyLevel(BlockState state);
}
