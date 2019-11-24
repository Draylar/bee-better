package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.BetterBees;
import net.fabricmc.fabric.api.tag.TagRegistry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public class BeeTags {

    public static final Tag<Fluid> HONEY = registerFluid("liquid_honey");
    public static final Tag<Block> CANDLES = registerBlock("candles");
    public static final Tag<Item> CANDLES_ITEM = registerItem("candles");
    public static final Tag<Block> WOODEN_BEEHIVES = registerBlock("wooden_beehives");
    public static final Tag<Item> WOODEN_BEEHIVES_ITEM = registerItem("wooden_beehives");

    private static Tag<Fluid> registerFluid(String name) {
        return TagRegistry.fluid(BetterBees.id(name));
    }
    
    private static Tag<Item> registerItem(String name) {
        return TagRegistry.item(BetterBees.id(name));
    }
    
    private static Tag<Block> registerBlock(String name) {
        return TagRegistry.block(BetterBees.id(name));
    }

    public static void init() {
        // NO-OP
    }

    private BeeTags() {
        // NO-OP
    }
}
