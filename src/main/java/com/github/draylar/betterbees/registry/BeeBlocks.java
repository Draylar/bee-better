package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.BetterBees;
import com.github.draylar.betterbees.block.ApiaryBlock;
import com.github.draylar.betterbees.honey.HoneyFluidBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeeBlocks {

    public static final Block HONEY_FLUID = register("honey", new HoneyFluidBlock(FabricBlockSettings.of(Material.WATER).noCollision().strength(100.0F, 100f).dropsNothing().build()));
    public static final Block BEESWAX = register("beeswax", new Block(FabricBlockSettings.of(Material.STONE).build()));
    public static final Block APIARY = register("apiary", new ApiaryBlock(FabricBlockSettings.of(Material.STONE).build()));

    private static Block register(String name, Block item) {
        return Registry.register(Registry.BLOCK, new Identifier(BetterBees.MODID, name), item);
    }

    public static void init() {
        // NO-OP
    }

    private BeeBlocks() {
        // NO-OP
    }
}
