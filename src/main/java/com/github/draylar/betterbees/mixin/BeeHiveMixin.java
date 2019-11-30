package com.github.draylar.betterbees.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BeeHiveBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.github.draylar.betterbees.registry.BeeItems;

@Mixin(BeeHiveBlock.class)
public abstract class BeeHiveMixin extends Block {
	
	public BeeHiveMixin(Settings settings) {
		super(settings);
	}
	
	@Inject(at = @At("HEAD"), method = "dropHoneycomb")
	private static void dropHoneycomb(World world, BlockPos blockPos, CallbackInfo ci) {
		dropStack(world, blockPos, new ItemStack(BeeItems.BEESWAX_FLAKE, 1));
	}
}
