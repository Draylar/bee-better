package com.github.draylar.beebetter.mixin;

import com.github.draylar.beebetter.registry.BeeItems;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeehiveBlock.class)
public abstract class BeehiveMixin extends Block {

    public BeehiveMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "dropHoneycomb")
    private static void dropHoneycomb(World world, BlockPos blockPos, CallbackInfo ci) {
        dropStack(world, blockPos, new ItemStack(BeeItems.BEESWAX_FLAKE, 1));
    }
}
