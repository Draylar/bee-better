package com.github.draylar.beebetter.mixin;

import com.github.draylar.beebetter.entity.ApiaryBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BeeEntity.class)
public abstract class BeeMixin extends AnimalEntity implements Flutterer {

    private BeeMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Inject(at = @At(value = "RETURN", ordinal = 1), cancellable = true, method = "isHiveNearFire", locals = LocalCapture.CAPTURE_FAILHARD)
    private void isHiveNearFire(CallbackInfoReturnable<Boolean> info, BlockEntity be) {
        if(!info.getReturnValueZ() && be instanceof ApiaryBlockEntity && ((ApiaryBlockEntity) be).isNearFire()) {
            info.setReturnValue(true);
        }
    }

    @Inject(at = @At(value = "RETURN", ordinal = 1), cancellable = true, method = "isHiveValid", locals = LocalCapture.CAPTURE_FAILHARD)
    private void isHiveValid(CallbackInfoReturnable<Boolean> info, BlockEntity be) {
        if(!info.getReturnValueZ() && be instanceof ApiaryBlockEntity) {
            info.setReturnValue(true);
        }
    }
}
