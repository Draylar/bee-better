package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.BetterBees;
import com.github.draylar.betterbees.honey.HoneyFluid;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;

public class Fluids {

    public static final BaseFluid HONEY = (BaseFluid) register("honey", new HoneyFluid.Still());
    public static final BaseFluid FLOWING_HONEY = (BaseFluid) register("flowing_honey", new HoneyFluid.Flowing());

    private static Fluid register(String name, Fluid fluid) {
        return Registry.register(Registry.FLUID, BetterBees.id(name), fluid);
    }

    public static void init() {
        // NO-OP
    }

    private Fluids() {
        // NO-OP
    }
}
