package com.github.draylar.beebetter.registry;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.Set;

public class BeeWorld {

    public static final RegistryEntry<ConfiguredFeature<LakeFeature.Config, ?>> HONEY_POOL = ConfiguredFeatures.register("beebetter:honey_pool", Feature.LAKE, new LakeFeature.Config(BlockStateProvider.of(BeeBlocks.HONEY_FLUID.getDefaultState()), BlockStateProvider.of(Blocks.STONE.getDefaultState())));
    public static final RegistryEntry<PlacedFeature> HONEY_POOL_SURFACE = PlacedFeatures.register("beebetter:honey_pool_surface", HONEY_POOL, RarityFilterPlacementModifier.of(100), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    private static final Set<Biome.Category> CAT_BLACKLIST = ImmutableSet.of(
            Biome.Category.NETHER,
            Biome.Category.THEEND
    );

    private BeeWorld() {
        // NO-OP
    }

    public static void init() {
        BiomeModifications.addFeature(ctx -> !CAT_BLACKLIST.contains(Biome.getCategory(ctx.getBiomeRegistryEntry())), GenerationStep.Feature.LOCAL_MODIFICATIONS, BuiltinRegistries.PLACED_FEATURE.getKey(HONEY_POOL_SURFACE.comp_349()).get());
    }
}