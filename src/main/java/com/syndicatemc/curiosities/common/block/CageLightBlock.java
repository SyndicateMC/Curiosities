package com.syndicatemc.curiosities.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.math.Axis;
import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;

public class CageLightBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final IntegerProperty LIGHT = IntegerProperty.create("light", 10, 15);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    protected static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, Block.box(4.0F, 4.0F, 12.0F, 12.0F, 12.0F, 16.0F),
            Direction.SOUTH, Block.box(4.0F, 4.0F, 0.0F, 12.0F, 12.0F, 4.0F),
            Direction.WEST, Block.box(12.0F, 4.0F, 4.0F, 16.0F, 12.0F, 12.0F),
            Direction.EAST, Block.box(0.0F, 4.0F, 4.0F, 4.0F, 12.0F, 12.0F),
            Direction.UP, Block.box(4.0F, 0.0F, 4.0F, 12.0F, 4.0F, 12.0F),
            Direction.DOWN, Block.box(4.0F, 12.0F, 4.0F, 12.0F, 16.0F, 12.0F)
    ));


    public CageLightBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.DOWN).setValue(WATERLOGGED, false).setValue(LIT, true).setValue(LIGHT, 10));
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        int tick = state.getValue(LIT) ? 120 + level.getRandom().nextInt(120) : 5 + level.getRandom().nextInt(3);
        level.scheduleTick(pos, this, tick);
        super.onPlace(state, level, pos, oldState, isMoving);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            level.setBlock(pos, state.setValue(LIT, false), 3);
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.CAGE_LIGHT_OFF, SoundSource.BLOCKS, 0.05F, 0.95F + random.nextFloat() * 0.1F, 1);
        } else if (!state.getValue(LIT)) {
            level.setBlock(pos, state.setValue(LIT, true).setValue(LIGHT, 10 + random.nextInt(5)), 3);
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.CAGE_LIGHT_ON, SoundSource.BLOCKS, 0.05F, 0.95F + random.nextFloat() * 0.1F, 1);
        }
    }
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, LIT, LIGHT);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState placedOnState = level.getBlockState(blockpos);
        if (placedOnState.is(BlockTags.WALLS) && (direction == Direction.DOWN || direction == Direction.UP)) {
            return true;
        } else {
            return placedOnState.isFaceSturdy(level, blockpos, direction);
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState blockstate = super.getStateForPlacement(context);
        Direction[] adirection = context.getNearestLookingDirections();

        for(Direction direction : adirection) {
            blockstate = blockstate.setValue(FACING, direction.getOpposite());
            if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
            }
        }
        return null;
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(LIT) ? state.getValue(LIGHT) : 0;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
}
