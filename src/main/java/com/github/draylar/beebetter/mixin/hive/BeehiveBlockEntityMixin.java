package com.github.draylar.beebetter.mixin.hive;

import com.github.draylar.beebetter.block.ModdedBeehiveBlock;
import com.github.draylar.beebetter.entity.ModdedBeehiveBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BeehiveBlockEntity.class)
public class BeehiveBlockEntityMixin {

    @ModifyConstant(method = "releaseBee", constant = @Constant(intValue = 5))
    private static int adjustMaxHoneyLimit(int constant, World world, BlockPos blockPos, BlockState state, @Coerce Object bee, @Nullable List<Entity> list, BeehiveBlockEntity.BeeState beeState, @Nullable BlockPos blockPos2) {
        if(state.getBlock() instanceof ModdedBeehiveBlock modded) {
            return modded.getMaxHoneyLevel();
        }

        return constant;
    }
}
