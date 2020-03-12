package com.github.draylar.beebetter.block;

import net.minecraft.block.Stainable;
import net.minecraft.util.DyeColor;

public class DyedCandleBlock extends CandleBlock implements Stainable {
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
