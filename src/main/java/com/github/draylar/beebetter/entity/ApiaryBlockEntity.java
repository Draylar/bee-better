package com.github.draylar.beebetter.entity;

import com.github.draylar.beebetter.block.ApiaryBlock;
import com.github.draylar.beebetter.registry.BeeEntities;
import net.minecraft.block.BlockState;

public class ApiaryBlockEntity extends ModdedBeehiveBlockEntity {

    public ApiaryBlockEntity() {
        super(BeeEntities.APIARY);
    }

    @Override
    public int getHoneyLevel(BlockState state) {
        return state.get(ApiaryBlock.HONEY_LEVEL);
    }

    @Override
    public int getMaxBees() {
        return 6;
    }
}
