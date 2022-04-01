package com.github.draylar.beebetter.entity;

import com.github.draylar.beebetter.mixin.BlockEntityAccessor;
import com.github.draylar.beebetter.registry.BeeEntities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class BeeBrickBlockEntity extends ModdedBeehiveBlockEntity {

    public BeeBrickBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
        ((BlockEntityAccessor) this).setType(BeeEntities.BEE_BRICKS);
    }

    @Override
    public int getMaxBees() {
        return 1;
    }
}
