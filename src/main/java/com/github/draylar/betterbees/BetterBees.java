package com.github.draylar.betterbees;

import com.github.draylar.betterbees.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterBees implements ModInitializer {

	public static final String MODID = "betterbees";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(BeeItems.APIARY));

	@Override
	public void onInitialize() {
		BeeBlocks.init();
		BeeItems.init();
		BeeFluids.init();
		BeeTags.init();
		BeeEntities.init();
		BeePOI.init();
		BeeWorld.init();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
