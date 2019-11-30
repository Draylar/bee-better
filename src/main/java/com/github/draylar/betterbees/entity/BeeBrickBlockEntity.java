package com.github.draylar.betterbees.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;

import com.github.draylar.betterbees.block.BeeBrickBlock;
import com.github.draylar.betterbees.block.ModdedBeehiveBlock;
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
