package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.BetterBees;
import com.github.draylar.betterbees.honey.HoneyBucketItem;
import com.github.draylar.betterbees.item.BeeStingerItem;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeeItems {

    public static final Item HONEY_BUCKET = register("honey_bucket", new HoneyBucketItem(settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item BEESWAX = register("beeswax", new BlockItem(BeeBlocks.BEESWAX, settings()));
    public static final Item BEESWAX_FLAKE = register("beeswax_flake", new Item(settings()));
    public static final Item COMPRESSED_BEESWAX = register("compressed_beeswax", new Item(settings()));
    public static final Item BEE_STINGER = register("bee_stinger", new BeeStingerItem(settings()));
    public static final Item APIARY = register("apiary", new BlockItem(BeeBlocks.APIARY, settings()));
    public static final Item CANDLE = register("candle", new BlockItem(BeeBlocks.CANDLE, settings()));
    public static final Item WHITE_CANDLE = register("white_candle", new BlockItem(BeeBlocks.WHITE_CANDLE, settings()));
    public static final Item ORANGE_CANDLE = register("orange_candle", new BlockItem(BeeBlocks.ORANGE_CANDLE, settings()));
    public static final Item MAGENTA_CANDLE = register("magenta_candle", new BlockItem(BeeBlocks.MAGENTA_CANDLE, settings()));
    public static final Item LIGHT_BLUE_CANDLE = register("light_blue_candle", new BlockItem(BeeBlocks.LIGHT_BLUE_CANDLE, settings()));
    public static final Item YELLOW_CANDLE = register("yellow_candle", new BlockItem(BeeBlocks.YELLOW_CANDLE, settings()));
    public static final Item LIME_CANDLE = register("lime_candle", new BlockItem(BeeBlocks.LIME_CANDLE, settings()));
    public static final Item PINK_CANDLE = register("pink_candle", new BlockItem(BeeBlocks.PINK_CANDLE, settings()));
    public static final Item GRAY_CANDLE = register("gray_candle", new BlockItem(BeeBlocks.GRAY_CANDLE, settings()));
    public static final Item LIGHT_GRAY_CANDLE = register("light_gray_candle", new BlockItem(BeeBlocks.LIGHT_GRAY_CANDLE, settings()));
    public static final Item CYAN_CANDLE = register("cyan_candle", new BlockItem(BeeBlocks.CYAN_CANDLE, settings()));
    public static final Item PURPLE_CANDLE = register("purple_candle", new BlockItem(BeeBlocks.PURPLE_CANDLE, settings()));
    public static final Item BLUE_CANDLE = register("blue_candle", new BlockItem(BeeBlocks.BLUE_CANDLE, settings()));
    public static final Item BROWN_CANDLE = register("brown_candle", new BlockItem(BeeBlocks.BROWN_CANDLE, settings()));
    public static final Item GREEN_CANDLE = register("green_candle", new BlockItem(BeeBlocks.GREEN_CANDLE, settings()));
    public static final Item RED_CANDLE = register("red_candle", new BlockItem(BeeBlocks.RED_CANDLE, settings()));
    public static final Item BLACK_CANDLE = register("black_candle", new BlockItem(BeeBlocks.BLACK_CANDLE, settings()));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(BetterBees.MODID, name), item);
    }
    
    private static Item.Settings settings() {
        return new Item.Settings().group(BetterBees.GROUP);
    }

    public static void init() {
        // NO-OP
    }

    private BeeItems() {
        // NO-OP
    }
}
