package com.github.draylar.beebetter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import net.minecraft.block.DispenserBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import com.github.draylar.beebetter.dispenser.GlassBottleDispenserBehavior;
import com.github.draylar.beebetter.dispenser.ShearsDispenserBehavior;
import com.github.draylar.beebetter.registry.BeeBlocks;
import com.github.draylar.beebetter.registry.BeeEntities;
import com.github.draylar.beebetter.registry.BeeFluids;
import com.github.draylar.beebetter.registry.BeeItems;
import com.github.draylar.beebetter.registry.BeePOI;
import com.github.draylar.beebetter.registry.BeeTags;
import com.github.draylar.beebetter.registry.BeeWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeeBetter implements ModInitializer {
	
	public static final String MODID = "beebetter";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(BeeItems.HONEY_BUCKET));
	
	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
	
	@Override
	public void onInitialize() {
		BeeBlocks.init();
		BeeItems.init();
		BeeFluids.init();
		BeeTags.init();
		BeeEntities.init();
		BeePOI.init();
		BeeWorld.init();
		
		// shitty hack but it's infinitely easier than mixin-ing into this
		DispenserBlock.registerBehavior(Items.GLASS_BOTTLE.asItem(), new GlassBottleDispenserBehavior());
		DispenserBlock.registerBehavior(Items.SHEARS.asItem(), new ShearsDispenserBehavior());
	}
	
	public static void log(String log) {
		LOGGER.info("["+MODID+"] "+log);
	}
}
