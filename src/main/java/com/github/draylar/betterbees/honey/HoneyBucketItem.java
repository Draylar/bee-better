package com.github.draylar.betterbees.honey;

import com.github.draylar.betterbees.registry.BeeFluids;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class HoneyBucketItem extends BucketItem {

    public HoneyBucketItem(Settings settings) {
        super(BeeFluids.HONEY, settings);
    }

    @Override
    protected void playEmptyingSound(PlayerEntity player, IWorld world, BlockPos pos) {
        SoundEvent soundEvent = SoundEvents.BLOCK_HONEY_BLOCK_PLACE;
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
