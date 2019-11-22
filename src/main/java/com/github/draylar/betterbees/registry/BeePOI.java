package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.mixin.PointOfInterestTypeAccessor;
import net.minecraft.village.PointOfInterestType;

public class BeePOI {

    public static final PointOfInterestType APIARY =
            PointOfInterestTypeAccessor.invokeRegister(
                    "apiary",
                    PointOfInterestTypeAccessor.getGetAllStatesOf(BeeBlocks.APIARY),
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