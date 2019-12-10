package com.github.draylar.beebetter.registry;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

import com.github.draylar.beebetter.BeeBetter;
import com.github.draylar.beebetter.world.feature.HoneyPoolFeature;

public class BeeWorld {
	
	private static final Feature<SingleStateFeatureConfig> HONEY_POOL = registerFeature("honey_pool", new HoneyPoolFeature(SingleStateFeatureConfig::deserialize));
	
	private BeeWorld() {
		// NO-OP
	}
	
	private static <F extends FeatureConfig> Feature<F> registerFeature(String name, Feature<F> feature) {
		return Registry.register(Registry.FEATURE, BeeBetter.id(name), feature);
	}
	
	public static void init() {
		// add honey pool to valid overworld biomes
		Registry.BIOME.stream().filter(biome -> {
			Biome.Category category = biome.getCategory();
			return category != Biome.Category.NETHER && category != Biome.Category.THEEND && category != Biome.Category.OCEAN;
		}).forEach(biome -> {
			biome.addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, HONEY_POOL.configure(new SingleStateFeatureConfig(BeeBlocks.HONEY_FLUID.getDefaultState())).createDecoratedFeature(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(35))));
		});
	}
}