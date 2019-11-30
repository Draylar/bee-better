package com.github.draylar.betterbees.entity;

import net.minecraft.block.BlockState;

import com.github.draylar.betterbees.block.ApiaryBlock;
import com.github.draylar.betterbees.registry.BeeEntities;

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
