package com.github.draylar.beebetter.mixin.hive;

import com.github.draylar.beebetter.block.ApiaryBlock;
import com.github.draylar.beebetter.block.BeeBrickBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeehiveBlockEntity.class)
public class HoneyLevelMixin {

    @Inject(method = "getHoneyLevel", at = @At("HEAD"), cancellable = true)
    private static void adjustHoneyLevel(BlockState state, CallbackInfoReturnable<Integer> cir) {
        if(state.getBlock() instanceof ApiaryBlock apiary) {
            cir.setReturnValue(apiary.getHoneyLevel(state));
        } else if(state.getBlock() instanceof BeeBrickBlock brick) {
            cir.setReturnValue(brick.getHoneyLevel(state));
        }
    }
}
