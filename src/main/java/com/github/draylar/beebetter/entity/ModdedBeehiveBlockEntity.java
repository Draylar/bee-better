package com.github.draylar.beebetter.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public abstract class ModdedBeehiveBlockEntity extends BeehiveBlockEntity {

    public ModdedBeehiveBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    public abstract int getMaxBees();

    @Override
    public boolean isFullOfBees() {
        return getBeeCount() == getMaxBees();
    }
}
