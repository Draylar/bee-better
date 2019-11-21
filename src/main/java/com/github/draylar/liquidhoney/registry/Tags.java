package com.github.draylar.liquidhoney.registry;

import com.github.draylar.liquidhoney.LiquidHoney;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;

public class Tags {

    public static final Tag<Fluid> HONEY = register("liquid_honey");

    private static Tag<Fluid> register(String name) {
        return TagRegistry.fluid(LiquidHoney.id(name));
    }

    public static void init() {
        // NO-OP
    }

    private Tags() {
        // NO-OP
    }
}
