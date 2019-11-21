package com.github.draylar.betterbees;

import com.github.draylar.betterbees.client.FluidResourceLoader;
import com.github.draylar.betterbees.registry.Blocks;
import com.github.draylar.betterbees.registry.Fluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceType;

public class BetterBeesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.HONEY_FLUID, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluid(Fluids.HONEY, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluid(Fluids.FLOWING_HONEY, RenderLayer.getTranslucent());

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEX).register((texture, registry) -> {
            registry.register(BetterBees.id("block/honey_still"));
        });
    }
}
