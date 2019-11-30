package com.github.draylar.betterbees.block;

import net.minecraft.client.block.ColoredBlock;
import net.minecraft.util.DyeColor;

public class DyedCandleBlock extends CandleBlock implements ColoredBlock {
	public final DyeColor color;
	
	public DyedCandleBlock(Settings settings, DyeColor color) {
		super(settings);
		this.color = color;
	}
	
	@Override
	public DyeColor getColor() {
		return color;
	}
}
