package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.BetterBees;
import com.github.draylar.betterbees.block.ApiaryBlock;
import com.github.draylar.betterbees.block.CandleBlock;
import com.github.draylar.betterbees.block.DyedCandleBlock;
import com.github.draylar.betterbees.honey.HoneyFluidBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeeBlocks {

    public static final Block HONEY_FLUID = register("honey", new HoneyFluidBlock(FabricBlockSettings.of(Material.WATER).noCollision().strength(100.0F, 100f).dropsNothing().build()));
    public static final Block BEESWAX_BLOCK = register("beeswax", new Block(FabricBlockSettings.copy(Blocks.STONE).build()));
    public static final Block APIARY = register("apiary", new ApiaryBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build()));
    public static final Block CANDLE = register("candle", new CandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build()));
    public static final Block WHITE_CANDLE = register("white_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.WHITE));
    public static final Block ORANGE_CANDLE = register("orange_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.ORANGE));
    public static final Block MAGENTA_CANDLE = register("magenta_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.MAGENTA));
    public static final Block LIGHT_BLUE_CANDLE = register("light_blue_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.LIGHT_BLUE));
    public static final Block YELLOW_CANDLE = register("yellow_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.YELLOW));
    public static final Block LIME_CANDLE = register("lime_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.LIME));
    public static final Block PINK_CANDLE = register("pink_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.PINK));
    public static final Block GRAY_CANDLE = register("gray_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.GRAY));
    public static final Block LIGHT_GRAY_CANDLE = register("light_gray_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.LIGHT_GRAY));
    public static final Block CYAN_CANDLE = register("cyan_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.CYAN));
    public static final Block PURPLE_CANDLE = register("purple_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.PURPLE));
    public static final Block BLUE_CANDLE = register("blue_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.BLUE));
    public static final Block BROWN_CANDLE = register("brown_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.BROWN));
    public static final Block GREEN_CANDLE = register("green_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.GREEN));
    public static final Block RED_CANDLE = register("red_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.RED));
    public static final Block BLACK_CANDLE = register("black_candle", new DyedCandleBlock(FabricBlockSettings.copy(Blocks.TORCH).build(), DyeColor.BLACK));

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
