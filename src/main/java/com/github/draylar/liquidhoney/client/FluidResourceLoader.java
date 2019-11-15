package com.github.draylar.liquidhoney.client;

import com.github.draylar.liquidhoney.LiquidHoney;
import com.github.draylar.liquidhoney.registry.Fluids;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Collection;

public class FluidResourceLoader implements SimpleSynchronousResourceReloadListener {

    @Override
    public Identifier getFabricId() {
        return LiquidHoney.id("fluid_resource_loader");
    }

    @Override
    public Collection<Identifier> getFabricDependencies() {
        return Arrays.asList(ResourceReloadListenerKeys.MODELS, ResourceReloadListenerKeys.TEXTURES);
    }

    @Override
    public void apply(ResourceManager resourceManager) {
        FluidRenderHandler honeyRenderHandler = (blockRenderView, blockPos, fluidState) ->
                new Sprite[] {
                        MinecraftClient.getInstance().getSpriteAtlas().getSprite(LiquidHoney.id("block/honey_still")),
                        MinecraftClient.getInstance().getSpriteAtlas().getSprite(LiquidHoney.id("block/honey_flow"))
                };

        FluidRenderHandlerRegistry.INSTANCE.register(Fluids.HONEY, honeyRenderHandler);
        FluidRenderHandlerRegistry.INSTANCE.register(Fluids.FLOWING_HONEY, honeyRenderHandler);
    }
}
