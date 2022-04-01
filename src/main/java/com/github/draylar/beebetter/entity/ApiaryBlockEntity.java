package com.github.draylar.beebetter.entity;

import com.github.draylar.beebetter.mixin.BlockEntityAccessor;
import com.github.draylar.beebetter.registry.BeeEntities;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class ApiaryBlockEntity extends ModdedBeehiveBlockEntity {

    public ApiaryBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
        ((BlockEntityAccessor) this).setType(BeeEntities.APIARY);
    }

    @Override
    public int getMaxBees() {
        return 6;
    }
}
