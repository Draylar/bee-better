package com.github.draylar.betterbees.mixin;

import com.github.draylar.betterbees.ai.EnterApiaryGoal;
import com.github.draylar.betterbees.ai.FindApiaryGoal;
import com.github.draylar.betterbees.ai.MoveToApiaryGoal;
import com.github.draylar.betterbees.entity.ApiaryBlockEntity;
import net.minecraft.block.entity.BeeHiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeeEntity.class)
public abstract class BeeMixin extends AnimalEntity implements Flutterer {

    @Shadow private int cannotEnterHiveTicks;

    @Shadow public abstract boolean hasHive();

    @Shadow public abstract boolean hasStung();

    @Shadow private int ticksSincePollination;

    @Shadow public abstract boolean hasNectar();

    @Shadow private BlockPos hivePos;

    protected BeeMixin(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 0, shift = At.Shift.AFTER), method = "initGoals")
    private void addFindApiaryGoal(CallbackInfo ci) {
        this.goalSelector.add(0, new FindApiaryGoal(((BeeEntity) (Object) this)));
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 2, shift = At.Shift.AFTER), method = "initGoals")
    private void addEnterApiaryGoal(CallbackInfo ci) {
        this.goalSelector.add(1, new EnterApiaryGoal(((BeeEntity) (Object) this)));
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 6, shift = At.Shift.AFTER), method = "initGoals")
    private void moveToApiaryGoal(CallbackInfo ci) {
        this.goalSelector.add(5, new MoveToApiaryGoal((BeeEntity) (Object) this));
    }

    // TODO: CHANGE TO INJECT
    @Overwrite
    private boolean canEnterHive() {
        if (this.cannotEnterHiveTicks > 0) {
            return false;
        } else if (!this.hasHive()) {
            return false;
        } else if (this.hasStung()) {
            return false;
        } else {
            boolean bl = this.ticksSincePollination > 3600;
            if (!bl && !this.hasNectar() && !this.world.method_23886() && !this.world.isRaining()) {
                return false;
            } else {
                BlockEntity blockEntity = this.world.getBlockEntity(this.hivePos);
                boolean isBeeHive = blockEntity instanceof BeeHiveBlockEntity && !((BeeHiveBlockEntity)blockEntity).method_23280();
                boolean isApiary = blockEntity instanceof ApiaryBlockEntity && !((ApiaryBlockEntity) blockEntity).method_23280();
                return isBeeHive || isApiary;
            }
        }
    }
}
