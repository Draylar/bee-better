package com.github.draylar.liquidhoney;

import com.github.draylar.liquidhoney.registry.Blocks;
import com.github.draylar.liquidhoney.registry.Fluids;
import com.github.draylar.liquidhoney.registry.Items;
import com.github.draylar.liquidhoney.registry.Tags;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LiquidHoney implements ModInitializer
{
	public static final String MODID = "liquidhoney";
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
