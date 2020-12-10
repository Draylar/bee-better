package com.github.draylar.beebetter.registry;

import com.github.draylar.beebetter.BeeBetter;
import com.github.draylar.beebetter.world.feature.HoneyPoolFeature;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

import java.util.Set;

public class BeeWorld {

    private static final Feature<SingleStateFeatureConfig> HONEY_POOL = registerFeature("honey_pool", new HoneyPoolFeature(SingleStateFeatureConfig.CODEC));
    private static final ConfiguredFeature<?, ?> HONEY_POOL_CONF = HONEY_POOL.configure(new SingleStateFeatureConfig(BeeBlocks.HONEY_FLUID.getDefaultState())).decorate(Decorator.WATER_LAKE.configure(new ChanceDecoratorConfig(35)));
    private static final Set<Biome.Category> CAT_BLACKLIST = ImmutableSet.of(
            Biome.Category.NETHER,
            Biome.Category.THEEND
    );

    private BeeWorld() {
        // NO-OP
    }

    private static <F extends FeatureConfig> Feature<F> registerFeature(String name, Feature<F> feature) {
        return Registry.register(Registry.FEATURE, BeeBetter.id(name), feature);
    }

    public static void init() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, BeeBetter.id("honey_pool"), HONEY_POOL_CONF);
        BiomeModifications.addFeature(ctx -> {
            Biome.Category cat = ctx.getBiome().getCategory();
            return !CAT_BLACKLIST.contains(cat);
        }, GenerationStep.Feature.LOCAL_MODIFICATIONS, BuiltinRegistries.CONFIGURED_FEATURE.getKey(HONEY_POOL_CONF).get());
    }
}