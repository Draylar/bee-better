package com.github.draylar.beebetter.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(PointOfInterestType.class)
public interface PointOfInterestTypeAccessor {

    @Invoker
    static PointOfInterestType invokeRegister(String id, Set<BlockState> set, int i, int j) {
        return null;
    }
}
