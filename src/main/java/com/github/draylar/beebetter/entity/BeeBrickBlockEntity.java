package com.github.draylar.beebetter.entity;

import com.github.draylar.beebetter.block.BeeBrickBlock;
import com.github.draylar.beebetter.block.ModdedBeehiveBlock;
import com.github.draylar.beebetter.registry.BeeEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;

public class BeeBrickBlockEntity extends ModdedBeehiveBlockEntity {

    public BeeBrickBlockEntity() {
        super(BeeEntities.BEE_BRICKS);
    }

    @Override
    public int getMaxBees() {
        return 1;
    }

    @Override
    public int getHoneyLevel(BlockState state) {
        return state.get(BeeBrickBlock.HONEY_LEVEL);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient && this.world.getTimeOfDay() % 24000L == 0 && hasNoBees() && ModdedBeehiveBlock.getNearbyBees(this.getWorld(), this.getPos()).isEmpty()) {
            this.tryEnterHive(new BeeEntity(EntityType.BEE, world), false);
        }
    }
}
