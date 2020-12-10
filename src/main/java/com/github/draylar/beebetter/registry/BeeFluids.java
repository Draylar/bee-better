package com.github.draylar.beebetter.registry;

import com.github.draylar.beebetter.BeeBetter;
import com.github.draylar.beebetter.honey.HoneyFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;

public class BeeFluids {

    public static final FlowableFluid HONEY = (FlowableFluid) register("honey", new HoneyFluid.Still());
    public static final FlowableFluid FLOWING_HONEY = (FlowableFluid) register("flowing_honey", new HoneyFluid.Flowing());

    private BeeFluids() {
        // NO-OP
    }

    private static Fluid register(String name, Fluid fluid) {
        return Registry.register(Registry.FLUID, BeeBetter.id(name), fluid);
    }

    public static void init() {
        // NO-OP
    }
}
