package com.github.draylar.beebetter.mixin.state;

import com.github.draylar.beebetter.block.ModdedBeehiveBlock;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(State.class)
public abstract class BlockStateMixin<O, S> {

    @Shadow public abstract <T extends Comparable<T>> T get(Property<T> property);

    @Shadow public abstract <T extends Comparable<T>, V extends T> S with(Property<T> property, V comparable);

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private <T extends Comparable<T>> void adjustHoneyProperty(Property<T> property, CallbackInfoReturnable<T> cir) {
        if(((State) (Object) this) instanceof BlockState state && property == BeehiveBlock.HONEY_LEVEL) {
            if(state.getBlock() instanceof ModdedBeehiveBlock moddedBeehiveBlock) {
                cir.setReturnValue((T) get(moddedBeehiveBlock.getHoneyProperty()));
            }
        }
    }

    @Inject(method = "with", at = @At("HEAD"), cancellable = true)
    private <T extends Comparable<T>, V extends T> void adjustHoneyWith(Property<T> property, V comparable, CallbackInfoReturnable<S> cir) {
        if(((State) (Object) this) instanceof BlockState state && property == BeehiveBlock.HONEY_LEVEL) {
            if(state.getBlock() instanceof ModdedBeehiveBlock moddedBeehiveBlock) {
                cir.setReturnValue(with((Property<T>) moddedBeehiveBlock.getHoneyProperty(), comparable));
            }
        }
    }

    @Inject(method = "contains", at = @At("HEAD"), cancellable = true)
    private <T extends Comparable<T>> void adjustHoneyContains(Property<T> property, CallbackInfoReturnable<Boolean> cir) {
        if(((State) (Object) this) instanceof BlockState state && property == BeehiveBlock.HONEY_LEVEL) {
            if(state.getBlock() instanceof ModdedBeehiveBlock) {
                cir.setReturnValue(true);
            }
        }
    }
}
