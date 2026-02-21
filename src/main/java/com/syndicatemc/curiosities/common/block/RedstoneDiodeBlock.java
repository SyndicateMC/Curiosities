package com.syndicatemc.curiosities.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

public class RedstoneDiodeBlock extends DiodeBlock {
    public static final MapCodec<RedstoneDiodeBlock> CODEC = simpleCodec(RedstoneDiodeBlock::new);

    public RedstoneDiodeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    public MapCodec<RedstoneDiodeBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getDelay(BlockState state) {
        return 0;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        Direction facing = state.getValue(FACING);
        return facing == direction || facing.getOpposite() == direction;
    }

    @Override
    protected int getOutputSignal(BlockGetter level, BlockPos pos, BlockState state) {
        return this.getInputSignal((Level)level, pos, state);
    }
}
