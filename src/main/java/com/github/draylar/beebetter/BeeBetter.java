package com.github.draylar.beebetter;

import com.github.draylar.beebetter.dispenser.GlassBottleDispenserBehavior;
import com.github.draylar.beebetter.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeeBetter implements ModInitializer {

    public static final String MODID = "beebetter";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("group"), () -> new ItemStack(BeeItems.HONEY_BUCKET));

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }

    @Override
    public void onInitialize() {
        BeeBlocks.init();
        BeeItems.init();
        BeeFluids.init();
        BeeTags.init();
        BeeEntities.init();
        BeePOI.init();
        BeeWorld.init();

        System.out.println(BeeBlocks.APIARY);

        // hack but it's infinitely easier than mixin-ing into this
        DispenserBlock.registerBehavior(Items.GLASS_BOTTLE.asItem(), new GlassBottleDispenserBehavior());
    }

    public static void log(String log) {
        LOGGER.info("[" + MODID + "] " + log);
    }
}
