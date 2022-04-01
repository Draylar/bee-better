package com.github.draylar.beebetter.block;

import com.github.draylar.beebetter.entity.BeeBrickBlockEntity;
import com.github.draylar.beebetter.registry.BeeEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BeeBrickBlock extends ModdedBeehiveBlock {

    public static final int MAX_HONEY = 5;
    public static final IntProperty HONEY_LEVEL = IntProperty.of("honey_level", 0, MAX_HONEY);

    public BeeBrickBlock(Settings settings) {
        super(settings);
        setDefaultState(this.getStateManager().getDefaultState().with(HONEY_LEVEL, 0).with(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BeeBrickBlockEntity(pos, state);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HONEY_LEVEL, FACING);
    }

    @Override
    public IntProperty getHoneyProperty() {
        return HONEY_LEVEL;
    }

    @Override
    public int getMaxHoneyLevel() {
        return 5;
    }

    @Override
    public int getHoneyLevel(BlockState state) {
        return state.get(HONEY_LEVEL);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return world.isClient ? null : checkType(blockEntityType, BeeEntities.BEE_BRICKS, BeehiveBlockEntity::serverTick);
    }
}
