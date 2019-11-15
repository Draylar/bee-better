package com.github.draylar.liquidhoney.registry;

import com.github.draylar.liquidhoney.LiquidHoney;
import com.github.draylar.liquidhoney.honey.HoneyBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {

    public static final Item HONEY_BUCKET = register("honey_bucket", new HoneyBucketItem(new Item.Settings().maxCount(1).group(ItemGroup.MISC)));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(LiquidHoney.MODID, name), item);
    }

    public static void init() {
        // NO-OP
    }

    private Items() {
        // NO-OP
    }
}
