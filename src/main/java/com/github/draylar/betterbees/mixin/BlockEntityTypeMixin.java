package com.github.draylar.betterbees.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BeeHiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
	@Inject(method = "supports(Lnet/minecraft/block/Block;)Z", at = @At("HEAD"), cancellable = true)
	private void supports(Block block, CallbackInfoReturnable<Boolean> info) {
		if (BlockEntityType.BEEHIVE.equals((Object) this) && block instanceof BeeHiveBlock) {
			info.setReturnValue(true);
		}
	}
}
