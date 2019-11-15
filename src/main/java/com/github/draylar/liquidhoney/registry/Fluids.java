package com.github.draylar.liquidhoney.registry;

import com.github.draylar.liquidhoney.LiquidHoney;
import com.github.draylar.liquidhoney.honey.HoneyFluid;
import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;

public class Fluids {

    public static final BaseFluid HONEY = (BaseFluid) register("honey", new HoneyFluid.Still());
    public static final BaseFluid FLOWING_HONEY = (BaseFluid) register("flowing_honey", new HoneyFluid.Flowing());

    private static Fluid register(String name, Fluid fluid) {
        return Registry.register(Registry.FLUID, LiquidHoney.id(name), fluid);
    }

    public static void init() {
        // NO-OP
    }

    private Fluids() {
        // NO-OP
    }
}
