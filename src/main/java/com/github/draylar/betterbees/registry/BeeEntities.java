package com.github.draylar.betterbees.registry;

import com.github.draylar.betterbees.BetterBees;
import com.github.draylar.betterbees.entity.ApiaryBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BeeEntities {

    public static final BlockEntityType<ApiaryBlockEntity> APIARY = register("apiary", BlockEntityType.Builder.create(ApiaryBlockEntity::new, BeeBlocks.APIARY).build(null));

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> build) {
        return Registry.register(Registry.BLOCK_ENTITY, BetterBees.id(name), build);
    }

    public static void init() {
        // NO-OP
    }

    private BeeEntities() {
        // NO-OP
    }
}
