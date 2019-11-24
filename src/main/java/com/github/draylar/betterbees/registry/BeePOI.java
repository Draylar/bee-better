package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.mixin.PointOfInterestTypeAccessor;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BedPart;
import net.minecraft.village.PointOfInterestType;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

public class BeePOI {

    public static final Set HIVE_STATES = ImmutableList.of(BeeBlocks.SPRUCE_BEEHIVE, BeeBlocks.BIRCH_BEEHIVE, BeeBlocks.JUNGLE_BEEHIVE, BeeBlocks.ACACIA_BEEHIVE, BeeBlocks.DARK_OAK_BEEHIVE, BeeBlocks.BEE_BRICKS).stream().flatMap((block) -> block.getStateManager().getStates().stream()).collect(ImmutableSet.toImmutableSet());
    public static final PointOfInterestType APIARY =
            PointOfInterestTypeAccessor.invokeRegister(
                    "apiary",
                    PointOfInterestTypeAccessor.getGetAllStatesOf(BeeBlocks.APIARY),
                    0,
                    1
            );
    public static final PointOfInterestType MODDED_BEEHIVES =
            PointOfInterestTypeAccessor.invokeRegister(
                    "modded_beehive",
                    HIVE_STATES,
                    0,
                    1
            );


    public static void init() {
        // NO-OP
    }

    private BeePOI() {
        // NO-OP
    }
}