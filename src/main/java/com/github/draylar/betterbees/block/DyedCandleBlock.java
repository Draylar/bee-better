package com.github.draylar.betterbees.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.client.block.ColoredBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class DyedCandleBlock extends CandleBlock implements ColoredBlock {
    public final DyeColor color;
    
    public DyedCandleBlock(Settings settings, DyeColor color) {
        super(settings);
        this.color = color;
    }
    
    @Override
    public DyeColor getColor() {
        return color;
    }
}
