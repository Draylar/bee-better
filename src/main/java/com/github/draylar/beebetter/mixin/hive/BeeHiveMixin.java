package com.github.draylar.beebetter.mixin.hive;

import com.github.draylar.beebetter.registry.BeePOI;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.entity.passive.BeeEntity$FindHiveGoal")
public class BeeHiveMixin {

    @Inject(method = "method_23743", at = @At("HEAD"), cancellable = true)
    private static void adjustValidBeePOI(PointOfInterestType pointOfInterestType, CallbackInfoReturnable<Boolean> cir) {
        if(pointOfInterestType == BeePOI.MODDED_BEEHIVES) {
            cir.setReturnValue(true);
        }
    }
}
