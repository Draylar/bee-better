package com.github.draylar.beebetter.mixin;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BeehiveBlock.class)
public interface BeehiveBlockAccessor {

    @Invoker
    boolean callHasBees(World world, BlockPos blockPos);

    @Invoker
    void callAngerNearbyBees(World world, BlockPos blockPos);

    @Invoker
    void callSpawnHoneyParticles(World world, BlockPos blockPos, BlockState blockState);
}
