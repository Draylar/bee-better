package com.github.draylar.beebetter.ai;

import com.github.draylar.beebetter.util.IBeeAccessor;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;

public class MoveToModdedHiveGoal extends BeeMoveToTargetGoal {

    public MoveToModdedHiveGoal(BeeEntity bee) {
        super(bee, 2, 48);
    }

    @Override
    protected BlockPos getTargetPos() {
        return ((IBeeAccessor) this.getBee()).getHivePos();
    }

    @Override
    public boolean canBeeStart() {
        if (this.method_23741()) {
            return ((IBeeAccessor) this.getBee()).canBeeEnterHive();
        } else {
            ((IBeeAccessor) this.getBee()).setHivePos(null);
            return false;
        }
    }

    @Override
    public boolean canBeeContinue() {
        return this.canBeeStart();
    }

    @Override
    protected void method_23885() {
        ((IBeeAccessor) this.getBee()).setHivePos(null);
    }
}
