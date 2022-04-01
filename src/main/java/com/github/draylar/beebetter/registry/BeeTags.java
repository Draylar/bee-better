package com.github.draylar.beebetter.registry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeeTags {

    public static final TagKey<Fluid> HONEY = registerFluid("liquid_honey");
    public static final TagKey<Block> CANDLES = registerBlock("candles");
    public static final TagKey<Item> CANDLES_ITEM = registerItem("candles");
    public static final TagKey<Block> WOODEN_BEEHIVES = registerBlock("wooden_beehives");
    public static final TagKey<Item> WOODEN_BEEHIVES_ITEM = registerItem("wooden_beehives");

    private BeeTags() {
        // NO-OP
    }

    private static TagKey<Fluid> registerFluid(String name) {
        return TagKey.of(Registry.FLUID_KEY, new Identifier(name));
    }

    private static TagKey<Item> registerItem(String name) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(name));
    }

    private static TagKey<Block> registerBlock(String name) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(name));
    }

    public static void init() {
        // NO-OP
    }
}
