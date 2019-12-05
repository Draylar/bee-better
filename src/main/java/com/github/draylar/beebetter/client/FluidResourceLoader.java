package com.github.draylar.beebetter.client;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import com.github.draylar.beebetter.BeeBetter;
import com.github.draylar.beebetter.registry.BeeFluids;

import java.util.Arrays;
import java.util.Collection;

public class FluidResourceLoader implements SimpleSynchronousResourceReloadListener {
	
	@Override
	public Identifier getFabricId() {
		return BeeBetter.id("fluid_resource_loader");
	}
	
	@Override
	public Collection<Identifier> getFabricDependencies() {
		return Arrays.asList(ResourceReloadListenerKeys.MODELS, ResourceReloadListenerKeys.TEXTURES);
	}
	
	@Override
	public void apply(ResourceManager resourceManager) {
		FluidRenderHandler honeyRenderHandler = (blockRenderView, blockPos, fluidState) ->
				new Sprite[]{
						MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX).apply(BeeBetter.id("block/honey_still")),
						MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEX).apply(BeeBetter.id("block/honey_flow"))
				};
		
		FluidRenderHandlerRegistry.INSTANCE.register(BeeFluids.HONEY, honeyRenderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(BeeFluids.FLOWING_HONEY, honeyRenderHandler);
	}
}
