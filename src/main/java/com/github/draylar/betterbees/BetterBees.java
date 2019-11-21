package com.github.draylar.betterbees;

import com.github.draylar.betterbees.registry.Blocks;
import com.github.draylar.betterbees.registry.Fluids;
import com.github.draylar.betterbees.registry.Items;
import com.github.draylar.betterbees.registry.Tags;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterBees implements ModInitializer
{
	public static final String MODID = "betterbees";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@Override
	public void onInitialize()
	{
		Blocks.init();
		Items.init();
		Fluids.init();
		Tags.init();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
