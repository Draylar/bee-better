package com.github.draylar.beebetter.mixin;

import com.github.draylar.beebetter.util.IBeeAccessor;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BeeEntity.class)
public abstract class BeeAccessor implements IBeeAccessor {

    @Shadow
    private BlockPos hivePos;

    @Shadow
    protected abstract boolean canEnterHive();

    @Override
    public boolean canBeeEnterHive() {
        return canEnterHive();
    }

    @Override
    public BlockPos getHivePos() {
        return hivePos;
    }

    @Override
    public void setHivePos(BlockPos pos) {
        this.hivePos = pos;
    }
}
