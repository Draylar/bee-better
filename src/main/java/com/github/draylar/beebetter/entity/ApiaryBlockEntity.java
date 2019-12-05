package com.github.draylar.beebetter.entity;

import net.minecraft.block.BlockState;

import com.github.draylar.beebetter.block.ApiaryBlock;
import com.github.draylar.beebetter.registry.BeeEntities;

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
