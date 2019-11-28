package com.github.draylar.betterbees.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;

import com.github.draylar.betterbees.registry.BeeEntities;

public class BeeBrickBlockEntity extends ModdedBeehiveBlockEntity {

    public BeeBrickBlockEntity() {
        super(BeeEntities.BEE_BRICKS);
    }
    
    @Override
    public int getMaxBees() {
        return 1;
    }
    
    @Override
    public void tick() {
        super.tick();
        if (hasNoBees() && !this.world.isClient && this.world.getTimeOfDay() % 24000L == 0) {
            this.tryEnterHive(new BeeEntity(EntityType.BEE, world), false);
        }
    }
}
