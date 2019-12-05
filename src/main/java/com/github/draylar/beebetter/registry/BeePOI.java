package com.github.draylar.beebetter.registry;

import net.minecraft.village.PointOfInterestType;

import com.github.draylar.beebetter.mixin.PointOfInterestTypeAccessor;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

public class BeePOI {
	
	public static final Set HIVE_STATES = ImmutableList.of(BeeBlocks.APIARY, BeeBlocks.SPRUCE_BEEHIVE, BeeBlocks.BIRCH_BEEHIVE, BeeBlocks.JUNGLE_BEEHIVE, BeeBlocks.ACACIA_BEEHIVE, BeeBlocks.DARK_OAK_BEEHIVE, BeeBlocks.BEE_BRICKS).stream().flatMap((block) -> block.getStateManager().getStates().stream()).collect(ImmutableSet.toImmutableSet());
	public static final PointOfInterestType MODDED_BEEHIVES =
			PointOfInterestTypeAccessor.invokeRegister(
					"modded_beehive",
					HIVE_STATES,
					0,
					1
			);
	
	
	private BeePOI() {
		// NO-OP
	}
	
	public static void init() {
		// NO-OP
	}
}