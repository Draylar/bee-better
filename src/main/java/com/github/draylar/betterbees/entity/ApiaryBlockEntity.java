package com.github.draylar.betterbees.entity;

import com.github.draylar.betterbees.block.ApiaryBlock;
import com.github.draylar.betterbees.block.ModdedBeehiveBlock;
import com.github.draylar.betterbees.registry.BeeEntities;
import com.github.draylar.betterbees.util.BeeState;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Iterator;
import java.util.List;

public class ApiaryBlockEntity extends ModdedBeehiveBlockEntity {

    public ApiaryBlockEntity() {
        super(BeeEntities.APIARY);
    }
    
    @Override
    public int getMaxBees() {
        return 6;
    }
}
