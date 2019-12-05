package com.github.draylar.beebetter.registry;

import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

import com.github.draylar.beebetter.BeeBetter;

public class BeeTags {
	
	public static final Tag<Fluid> HONEY = registerFluid("liquid_honey");
	public static final Tag<Block> CANDLES = registerBlock("candles");
	public static final Tag<Item> CANDLES_ITEM = registerItem("candles");
	public static final Tag<Block> WOODEN_BEEHIVES = registerBlock("wooden_beehives");
	public static final Tag<Item> WOODEN_BEEHIVES_ITEM = registerItem("wooden_beehives");
	
	private BeeTags() {
		// NO-OP
	}
	
	private static Tag<Fluid> registerFluid(String name) {
		return TagRegistry.fluid(BeeBetter.id(name));
	}
	
	private static Tag<Item> registerItem(String name) {
		return TagRegistry.item(BeeBetter.id(name));
	}
	
	private static Tag<Block> registerBlock(String name) {
		return TagRegistry.block(BeeBetter.id(name));
	}
	
	public static void init() {
		// NO-OP
	}
}
