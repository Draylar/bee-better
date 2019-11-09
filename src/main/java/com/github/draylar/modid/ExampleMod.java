package com.github.draylar.modid;

import com.github.draylar.modid.registry.Blocks;
import com.github.draylar.modid.registry.Items;
import com.github.draylar.modid.registry.Entities;
import com.github.draylar.modid.config.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleMod implements ModInitializer
{
	public static final String MODID = "modid";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final ModConfig CONFIG = AutoConfig.register(ModConfig.class, GsonConfigSerializer::new).getConfig();

	@Override
	public void onInitialize()
	{
		Blocks.init();
		Items.init();
		Entities.init();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
