package com.syndicatemc.curiosities.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.ticks.TickPriority;

public class VerticalConnectingPillarBlock extends RotatedPillarBlock {
    public static final BooleanProperty TOP_CONNECTED = BooleanProperty.create("top_connected");
    public static final BooleanProperty BOTTOM_CONNECTED = BooleanProperty.create("bottom_connected");

    public VerticalConnectingPillarBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(TOP_CONNECTED, false).setValue(BOTTOM_CONNECTED, false).setValue(AXIS, Direction.Axis.Y));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TOP_CONNECTED, BOTTOM_CONNECTED, AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos targetPos = context.getClickedPos();
        Level level = context.getLevel();
        Direction.Axis axis = context.getClickedFace().getAxis();
        return getConnections(this.defaultBlockState(), level, targetPos).setValue(AXIS, axis);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        BlockState newState = getConnections(state, level, pos);
        level.setBlock(pos, newState, 3);
        super.onPlace(state, level, pos, oldState, isMoving);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState newState = getConnections(state, (Level)level, currentPos);
        level.setBlock(currentPos, newState, 3);
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    protected BlockState getConnections(BlockState state, Level level, BlockPos pos) {
        boolean topConnected = false;
        boolean bottomConnected = false;
        Direction.Axis axis = state.getValue(AXIS);
//        switch (axis) {
//            case X:
//                BlockState eastState = level.getBlockState(pos.east());
//                BlockState westState = level.getBlockState(pos.west());
//                if (eastState.is(this) && eastState.getValue(AXIS) == Direction.Axis.X) topConnected = true;
//                if (westState.is(this) && westState.getValue(AXIS) == Direction.Axis.X) bottomConnected = true;
//                break;
//            case Y:
//                BlockState aboveState = level.getBlockState(pos.above());
//                BlockState belowState = level.getBlockState(pos.below());
//                if (aboveState.is(this) && aboveState.getValue(AXIS) == Direction.Axis.Y) topConnected = true;
//                if (belowState.is(this) && belowState.getValue(AXIS) == Direction.Axis.Y) bottomConnected = true;
//                break;
//            case Z:
//                BlockState northState = level.getBlockState(pos.north());
//                BlockState southState = level.getBlockState(pos.south());
//                if (northState.is(this) && northState.getValue(AXIS) == Direction.Axis.Z) topConnected = true;
//                if (southState.is(this) && southState.getValue(AXIS) == Direction.Axis.Z) bottomConnected = true;
//                break;
//        }
        if (axis == Direction.Axis.X) {
            BlockState eastState = level.getBlockState(pos.east());
            BlockState westState = level.getBlockState(pos.west());
            if (eastState.is(this) && eastState.getValue(AXIS) == Direction.Axis.X) topConnected = true;
            if (westState.is(this) && westState.getValue(AXIS) == Direction.Axis.X) bottomConnected = true;
        }
        if (axis == Direction.Axis.Y) {
            BlockState aboveState = level.getBlockState(pos.above());
            BlockState belowState = level.getBlockState(pos.below());
            if (aboveState.is(this) && aboveState.getValue(AXIS) == Direction.Axis.Y) topConnected = true;
            if (belowState.is(this) && belowState.getValue(AXIS) == Direction.Axis.Y) bottomConnected = true;
        }
        if (axis == Direction.Axis.Z) {
            BlockState northState = level.getBlockState(pos.north());
            BlockState southState = level.getBlockState(pos.south());
            if (northState.is(this) && northState.getValue(AXIS) == Direction.Axis.Z) topConnected = true;
            if (southState.is(this) && southState.getValue(AXIS) == Direction.Axis.Z) bottomConnected = true;
        }
        return state.setValue(TOP_CONNECTED, topConnected).setValue(BOTTOM_CONNECTED, bottomConnected);
    }
}
