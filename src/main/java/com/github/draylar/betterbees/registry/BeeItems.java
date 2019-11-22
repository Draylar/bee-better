package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.BetterBees;
import com.github.draylar.betterbees.honey.HoneyBucketItem;
import com.github.draylar.betterbees.item.BeeStingerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BeeItems {

    public static final Item HONEY_BUCKET = register("honey_bucket", new HoneyBucketItem(new Item.Settings().maxCount(1).group(ItemGroup.MISC).group(BetterBees.GROUP).recipeRemainder(Items.BUCKET)));
    public static final Item BEESWAX = register("beeswax", new BlockItem(BeeBlocks.BEESWAX, new Item.Settings().group(BetterBees.GROUP)));
    public static final Item BEESWAX_FLAKE = register("beeswax_flake", new Item(new Item.Settings().group(BetterBees.GROUP)));
    public static final Item COMPRESSED_BEESWAX = register("compressed_beeswax", new Item(new Item.Settings().group(BetterBees.GROUP)));
    public static final Item BEE_STINGER = register("bee_stinger", new BeeStingerItem(new Item.Settings().group(BetterBees.GROUP)));
    public static final Item APIARY = register("apiary", new BlockItem(BeeBlocks.APIARY, new Item.Settings().group(BetterBees.GROUP)));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(BetterBees.MODID, name), item);
    }

    public static void init() {
        // NO-OP
    }

    private BeeItems() {
        // NO-OP
    }
}
