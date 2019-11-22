package com.github.draylar.betterbees.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.village.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Set;

@Mixin(PointOfInterestType.class)
public interface PointOfInterestTypeAccessor {

    @Invoker
    public static PointOfInterestType invokeRegister(String id, Set<BlockState> set, int i, int j) {
        return null;
    }

    @Invoker
    public static Set<BlockState> getGetAllStatesOf(Block block) {
        return null;
    }
}
