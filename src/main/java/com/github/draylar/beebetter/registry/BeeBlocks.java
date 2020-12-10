package com.github.draylar.beebetter.registry;

import com.github.draylar.beebetter.BeeBetter;
import com.github.draylar.beebetter.block.ApiaryBlock;
import com.github.draylar.beebetter.block.BeeBrickBlock;
import com.github.draylar.beebetter.block.CandleBlock;
import com.github.draylar.beebetter.block.DyedCandleBlock;
import com.github.draylar.beebetter.honey.HoneyFluidBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class BeeBlocks {

    public static final ArrayList<Block> WOODEN_BEEHIVES = new ArrayList<>();

    public static final Block HONEY_FLUID = register("honey", new HoneyFluidBlock(FabricBlockSettings.of(Material.WATER).noCollision().strength(100.0F, 100f).dropsNothing().build()), (BlockItem) null);

    public static final Block BEESWAX_BLOCK = register("beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANCIENT_BEESWAX_BRICKS = register("ancient_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANCIENT_CRUMBLING_BEESWAX = register("ancient_crumbling_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANCIENT_GRAVELLED_BEESWAX = register("ancient_gravelled_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANCIENT_MOSSY_CRUMBLING_BEESWAX = register("ancient_mossy_crumbling_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANTIQUE_BEESWAX_BRICKS = register("antique_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANTIQUE_CHISELED_BEESWAX = register("antique_chiseled_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANTIQUE_FLAKY_BEESWAX_BRICKS = register("antique_flaky_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANTIQUE_MOSSY_BEESWAX_BRICKS = register("antique_mossy_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANTIQUE_POLISHED_BEESWAX = register("antique_polished_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ANTIQUE_POLISHED_BEESWAX_SLABS = register("antique_polished_beeswax_slabs", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block BEESWAX_BRICKS = register("beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block BUBBLY_BEESWAX_BRICKS = register("bubbly_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block CHISELED_BEESWAX = register("chiseled_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block CHISELED_BEESWAX_PIMPLE = register("chiseled_beeswax_pimple", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block CRUMBLING_BEESWAX = register("crumbling_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block EGREGIOUS_BEESWAX_TILES = register("egregious_beeswax_tiles", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block EGREGIOUS_POLISHED_BEESWAX = register("egregious_polished_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block EGREGIOUS_SMALL_BEESWAX_BRICKS = register("egregious_small_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block EGREGIOUS_SMALL_BEESWAX_TILES = register("egregious_small_beeswax_tiles", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block EGREGIOUS_SQUARE_BEESWAX_BRICKS = register("egregious_square_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block EGREGIOUS_WINDMILL_BEESWAX_TILES_CLOCKWISE = register("egregious_windmill_beeswax_tiles_clockwise", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block EGREGIOUS_WINDMILL_BEESWAX_TILES_COUNTERCLOCKWISE = register("egregious_windmill_beeswax_tiles_counterclockwise", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block FLAKY_BEESWAX_BRICKS = register("flaky_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block MOSSY_BEESWAX_BRICKS = register("mossy_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block POLISHED_BEESWAX = register("polished_beeswax", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block POLISHED_BEESWAX_SLABS = register("polished_beeswax_slabs", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block ROOF_BEESWAX_TILES = register("roof_beeswax_tiles", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block TINY_BEESWAX_BRICKS = register("tiny_beeswax_bricks", new Block(FabricBlockSettings.copy(Blocks.CLAY).sounds(BlockSoundGroup.SNOW).build()));

    public static final Block CANDLE = register("candle", new CandleBlock(FabricBlockSettings.copy(Blocks.TORCH).sounds(BlockSoundGroup.SNOW).build()));
    public static final Block WHITE_CANDLE = register("white_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.WHITE));
    public static final Block ORANGE_CANDLE = register("orange_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.ORANGE));
    public static final Block MAGENTA_CANDLE = register("magenta_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.MAGENTA));
    public static final Block LIGHT_BLUE_CANDLE = register("light_blue_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.LIGHT_BLUE));
    public static final Block YELLOW_CANDLE = register("yellow_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.YELLOW));
    public static final Block LIME_CANDLE = register("lime_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.LIME));
    public static final Block PINK_CANDLE = register("pink_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.PINK));
    public static final Block GRAY_CANDLE = register("gray_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.GRAY));
    public static final Block LIGHT_GRAY_CANDLE = register("light_gray_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.LIGHT_GRAY));
    public static final Block CYAN_CANDLE = register("cyan_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.CYAN));
    public static final Block PURPLE_CANDLE = register("purple_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.PURPLE));
    public static final Block BLUE_CANDLE = register("blue_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.BLUE));
    public static final Block BROWN_CANDLE = register("brown_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.BROWN));
    public static final Block GREEN_CANDLE = register("green_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.GREEN));
    public static final Block RED_CANDLE = register("red_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.RED));
    public static final Block BLACK_CANDLE = register("black_candle", new DyedCandleBlock(FabricBlockSettings.copy(CANDLE).build(), DyeColor.BLACK));

    public static final Block APIARY = register("apiary", new ApiaryBlock(FabricBlockSettings.copy(Blocks.IRON_BLOCK).build()));

    public static final Block SPRUCE_BEEHIVE = registerWoodenHive("spruce_beehive", new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.SPRUCE).build()));
    public static final Block BIRCH_BEEHIVE = registerWoodenHive("birch_beehive", new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.SAND).build()));
    public static final Block JUNGLE_BEEHIVE = registerWoodenHive("jungle_beehive", new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.DIRT).build()));
    public static final Block ACACIA_BEEHIVE = registerWoodenHive("acacia_beehive", new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.ORANGE).build()));
    public static final Block DARK_OAK_BEEHIVE = registerWoodenHive("dark_oak_beehive", new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.BROWN).build()));

    public static final Block BEE_BRICKS = register("bee_bricks", new BeeBrickBlock(FabricBlockSettings.copy(Blocks.BRICKS).build()));

    // Luminiferous Uplands
    public static Block SKYROOT_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.RED).build());

    // Tech Reborn
    public static Block RUBBER_BEEHIVE_TR = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.WOOD).build());

    // Terrestria
    public static Block CYPRESS_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.LIGHT_GRAY).build());
    public static Block HEMLOCK_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.WOOD).build());
    public static Block JAPANESE_MAPLE_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.MAGENTA_TERRACOTTA).build());
    public static Block RAINBOW_EUCALYPTUS_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.LAPIS).build());
    public static Block REDWOOD_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.WOOD).build());
    public static Block RUBBER_BEEHIVE_TERRESTRIA = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.SAND).build());
    public static Block SAKURA_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.BROWN).build());
    public static Block WILLOW_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.GRAY).build());

    // The Hallow
    public static Block DEADWOOD_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.PURPLE).build());

    // Traverse
    public static Block FIR_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.WOOD).build());

    // Blockus
    public static Block BAMBOO_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.WOOD).build());

    // Beyond Vanilla
    public static Block SHADOW_BEEHIVE = new BeehiveBlock(FabricBlockSettings.copy(Blocks.BEEHIVE).materialColor(MaterialColor.WOOD).build());

    private BeeBlocks() {
        // NO-OP
    }

    static <T extends Block> T registerWoodenHive(String name, T block) {
        T b = register(name, block);
        WOODEN_BEEHIVES.add(b);
        return b;
    }

    static <T extends Block> T register(String name, T block, Item.Settings settings) {
        return register(name, block, new BlockItem(block, settings));
    }

    static <T extends Block> T register(String name, T block) {
        return register(name, block, BeeItems.settings());
    }

    static <T extends Block> T register(String name, T block, BlockItem item) {
        T b = Registry.register(Registry.BLOCK, BeeBetter.id(name), block);
        if (item != null) {
            BeeItems.register(name, item);
            Item.BLOCK_ITEMS.put(item.getBlock(), item);
        }
        return b;
    }

    protected static void setFlammable(int burnChance, int spreadChance, Block... blocks) {
        for (Block block : blocks) {
            FlammableBlockRegistry.getDefaultInstance().add(block, burnChance, spreadChance);
        }
    }

    public static void init() {
        FabricLoader loader = FabricLoader.getInstance();

        if (loader.isModLoaded("luminiferous_uplands")) {
            BeeBetter.log("Luminiferous Uplands detected, adding Skyroot Beehive");
            SKYROOT_BEEHIVE = registerWoodenHive("skyroot_beehive", SKYROOT_BEEHIVE);
        }

        if (loader.isModLoaded("techreborn")) {
            BeeBetter.log("Tech Reborn detected, adding Rubber Beehive");
            RUBBER_BEEHIVE_TR = registerWoodenHive("rubber_beehive_tr", RUBBER_BEEHIVE_TR);
        }

        if (loader.isModLoaded("terrestria")) {
            BeeBetter.log("Terrestria detected, adding extra Beehives");
            CYPRESS_BEEHIVE = registerWoodenHive("cypress_beehive", CYPRESS_BEEHIVE);
            HEMLOCK_BEEHIVE = registerWoodenHive("hemlock_beehive", HEMLOCK_BEEHIVE);
            JAPANESE_MAPLE_BEEHIVE = registerWoodenHive("japanese_maple_beehive", JAPANESE_MAPLE_BEEHIVE);
            RAINBOW_EUCALYPTUS_BEEHIVE = registerWoodenHive("rainbow_eucalyptus_beehive", RAINBOW_EUCALYPTUS_BEEHIVE);
            REDWOOD_BEEHIVE = registerWoodenHive("redwood_beehive", REDWOOD_BEEHIVE);
            RUBBER_BEEHIVE_TERRESTRIA = registerWoodenHive("rubber_beehive_terrestria", RUBBER_BEEHIVE_TERRESTRIA);
            SAKURA_BEEHIVE = registerWoodenHive("sakura_beehive", SAKURA_BEEHIVE);
            WILLOW_BEEHIVE = registerWoodenHive("willow_beehive", WILLOW_BEEHIVE);
        }

        if (loader.isModLoaded("thehallow")) {
            BeeBetter.log("The Hallow detected, adding Deadwood Beehive");
            DEADWOOD_BEEHIVE = registerWoodenHive("deadwood_beehive", DEADWOOD_BEEHIVE);
        }

        if (loader.isModLoaded("traverse")) {
            BeeBetter.log("Traverse detected, adding Fir Beehive");
            FIR_BEEHIVE = registerWoodenHive("fir_beehive", FIR_BEEHIVE);
        }

        if (loader.isModLoaded("blockus")) {
            BeeBetter.log("Blockus detected, adding Bamboo Beehive");
            BAMBOO_BEEHIVE = registerWoodenHive("bamboo_beehive", BAMBOO_BEEHIVE);
        }

        if (loader.isModLoaded("bv")) {
            BeeBetter.log("Beyond Vanilla detected, adding Shadow Beehive");
            SHADOW_BEEHIVE = registerWoodenHive("shadow_beehive", SHADOW_BEEHIVE);
        }

        setFlammable(5, 20, WOODEN_BEEHIVES.toArray(new Block[0]));
    }
}
