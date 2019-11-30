package com.github.draylar.betterbees.util;

import net.minecraft.util.math.BlockPos;

public interface IBeeAccessor {
	
	boolean canBeeEnterHive();
	
	BlockPos getHivePos();
	
	void setHivePos(BlockPos pos);
}
