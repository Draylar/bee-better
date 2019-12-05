package com.github.draylar.beebetter.registry;

import net.minecraft.fluid.BaseFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.registry.Registry;

import com.github.draylar.beebetter.BeeBetter;
import com.github.draylar.beebetter.honey.HoneyFluid;

public class BeeFluids {
	
	public static final BaseFluid HONEY = (BaseFluid) register("honey", new HoneyFluid.Still());
	public static final BaseFluid FLOWING_HONEY = (BaseFluid) register("flowing_honey", new HoneyFluid.Flowing());
	
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
