package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.BetterBees;
import com.github.draylar.betterbees.block.ApiaryBlock;
import com.github.draylar.betterbees.block.CandleBlock;
import com.github.draylar.betterbees.block.DyedCandleBlock;
import com.github.draylar.betterbees.honey.HoneyFluidBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;

import net.minecraft.block.BeeHiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeeBlocks {

    public static final Block HONEY_FLUID = register("honey", new HoneyFluidBlock(FabricBlockSettings.of(Material.WATER).noCollision().strength(100.0F, 100f).dropsNothing().build()));
    public static final Block BEESWAX_BLOCK = register("beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block APIARY = register("apiary", new ApiaryBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build()));
    public static final Block CANDLE = register("candle", new CandleBlock(FabricBlockSettings.copy(Blocks.TORCH).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block WHITE_CANDLE = register("white_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.WHITE));
    public static final Block ORANGE_CANDLE = register("orange_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.ORANGE));
    public static final Block MAGENTA_CANDLE = register("magenta_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.MAGENTA));
    public static final Block LIGHT_BLUE_CANDLE = register("light_blue_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.LIGHT_BLUE));
    public static final Block YELLOW_CANDLE = register("yellow_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.YELLOW));
    public static final Block LIME_CANDLE = register("lime_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.LIME));
    public static final Block PINK_CANDLE = register("pink_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.PINK));
    public static final Block GRAY_CANDLE = register("gray_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.GRAY));
    public static final Block LIGHT_GRAY_CANDLE = register("light_gray_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.LIGHT_GRAY));
    public static final Block CYAN_CANDLE = register("cyan_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.CYAN));
    public static final Block PURPLE_CANDLE = register("purple_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.PURPLE));
    public static final Block BLUE_CANDLE = register("blue_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.BLUE));
    public static final Block BROWN_CANDLE = register("brown_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.BROWN));
    public static final Block GREEN_CANDLE = register("green_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.GREEN));
    public static final Block RED_CANDLE = register("red_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.RED));
    public static final Block BLACK_CANDLE = register("black_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.BLACK));
    public static final Block SPRUCE_BEEHIVE = register("spruce_beehive", new BeeHiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.SPRUCE).build()));
    public static final Block BIRCH_BEEHIVE = register("birch_beehive", new BeeHiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.SAND).build()));
    public static final Block JUNGLE_BEEHIVE = register("jungle_beehive", new BeeHiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.DIRT).build()));
    public static final Block ACACIA_BEEHIVE = register("acacia_beehive", new BeeHiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.ORANGE).build()));
    public static final Block DARK_OAK_BEEHIVE = register("dark_oak_beehive", new BeeHiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.BROWN).build()));
    public static final Block BEE_BRICKS = register("bee_bricks", new BeeHiveBlock(FabricBlockSettings.copy(Blocks.BRICKS).build()));
    
    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(BetterBees.MODID, name), block);
    }

    public static void init() {
        // NO-OP
    }

    private BeeBlocks() {
        // NO-OP
    }
}
