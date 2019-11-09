package com.github.draylar.modid.registry;

import com.github.draylar.modid.ExampleMod;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Items {



    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ExampleMod.MODID, name), item);
    }

    public static void init() {
        // NO-OP
    }

    private Items() {
        // NO-OP
    }
}
