package com.github.draylar.beebetter;

import com.github.draylar.beebetter.client.FluidResourceLoader;
import com.github.draylar.beebetter.registry.BeeBlocks;
import com.github.draylar.beebetter.registry.BeeFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.resource.ResourceType;

@Environment(EnvType.CLIENT)
public class BeeBetterClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new FluidResourceLoader());

        BlockRenderLayerMap.INSTANCE.putBlock(BeeBlocks.HONEY_FLUID, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluid(BeeFluids.HONEY, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluid(BeeFluids.FLOWING_HONEY, RenderLayer.getTranslucent());

        ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((texture, registry) -> {
            registry.register(BeeBetter.id("block/honey_still"));
        });
    }
}
