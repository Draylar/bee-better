package com.github.draylar.beebetter.registry;

import com.github.draylar.beebetter.BeeBetter;
import com.github.draylar.beebetter.entity.ApiaryBlockEntity;
import com.github.draylar.beebetter.entity.BeeBrickBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.object.builder.FabricEntityType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BeeEntities {

    public static final BlockEntityType<ApiaryBlockEntity> APIARY = register("apiary", FabricBlockEntityTypeBuilder.create(ApiaryBlockEntity::new, BeeBlocks.APIARY).build(null));
    public static final BlockEntityType<BeeBrickBlockEntity> BEE_BRICKS = register("bee_bricks", FabricBlockEntityTypeBuilder.create(BeeBrickBlockEntity::new, BeeBlocks.BEE_BRICKS).build(null));

    private BeeEntities() {
        // NO-OP
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> build) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, BeeBetter.id(name), build);
    }

    public static void init() {
        // NO-OP
    }
}
