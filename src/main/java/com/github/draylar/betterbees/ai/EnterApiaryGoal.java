package com.github.draylar.betterbees.ai;

import com.github.draylar.betterbees.entity.ApiaryBlockEntity;
import com.github.draylar.betterbees.util.IBeeAccessor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.passive.BeeEntity;

public class EnterApiaryGoal extends NotAngryGoal {

    public EnterApiaryGoal(BeeEntity bee) {
        super(bee);
    }

    @Override
    public boolean canBeeStart() {
        IBeeAccessor beeAccessor = (IBeeAccessor) this.getBee();

        if (beeAccessor.canBeeEnterHive() && beeAccessor.getHivePos().isWithinDistance(this.getBee().getPos(), 2.0D)) {
            BlockEntity blockEntity = this.getBee().world.getBlockEntity(beeAccessor.getHivePos());

            if (blockEntity instanceof ApiaryBlockEntity) {
                ApiaryBlockEntity beeHiveBlockEntity = (ApiaryBlockEntity) blockEntity;

                if (!beeHiveBlockEntity.isFullOfBees()) {
                    return true;
                }

                beeAccessor.setHivePos(null);
            }
        }

        return false;
    }

    @Override
    public boolean canBeeContinue() {
        return false;
    }

    @Override
    public void start() {
        IBeeAccessor beeAccessor = (IBeeAccessor) this.getBee();

        BlockEntity blockEntity = this.getBee().world.getBlockEntity(beeAccessor.getHivePos());

        if (blockEntity instanceof ApiaryBlockEntity) {
            ApiaryBlockEntity beeHiveBlockEntity = (ApiaryBlockEntity) blockEntity;
            beeHiveBlockEntity.tryEnterHive(this.getBee(), this.getBee().hasNectar());
        }
    }
}
