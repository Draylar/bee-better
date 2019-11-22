package com.github.draylar.betterbees.honey;

import com.github.draylar.betterbees.registry.BeeFluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HoneyFluidBlock extends FluidBlock {

    public HoneyFluidBlock(Settings settings) {
        super(BeeFluids.HONEY, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(entity instanceof LivingEntity) {
            ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 1, 2, true, false, false));
        }

        super.onEntityCollision(state, world, pos, entity);
    }
}
