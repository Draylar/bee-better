package com.github.draylar.beebetter.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.village.PointOfInterestType;

import java.util.Set;

@Mixin(PointOfInterestType.class)
public interface PointOfInterestTypeAccessor {
	
	@Invoker
	static PointOfInterestType invokeRegister(String id, Set<BlockState> set, int i, int j) {
		return null;
	}
	
	@Invoker
	static Set<BlockState> getGetAllStatesOf(Block block) {
		return null;
	}
}
