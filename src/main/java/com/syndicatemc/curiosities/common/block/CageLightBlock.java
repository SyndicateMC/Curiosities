package com.syndicatemc.curiosities.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.math.Axis;
import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import com.teamabnormals.blueprint.common.network.particle.SpawnParticlesPayload;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class CageLightBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final IntegerProperty LIGHT = IntegerProperty.create("light", 10, 15);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public static final BooleanProperty WAXED = BooleanProperty.create("waxed");
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
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.DOWN).setValue(WATERLOGGED, false).setValue(LIT, true).setValue(LIGHT, 10).setValue(WAXED, false));
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        int tick = state.getValue(LIT) ? 120 + level.getRandom().nextInt(120) : 5 + level.getRandom().nextInt(3);
        level.scheduleTick(pos, this, tick);
        super.onPlace(state, level, pos, oldState, isMoving);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean lit = state.getValue(LIT);
        boolean waxed = state.getValue(WAXED);
        int tick = state.getValue(LIT) ? 120 + level.getRandom().nextInt(120) : 5 + level.getRandom().nextInt(3);

        if (lit && !waxed) {
            level.setBlock(pos, state.setValue(LIT, false), 3);
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.CAGE_LIGHT_OFF, SoundSource.BLOCKS, 0.05F, 0.95F + random.nextFloat() * 0.1F, 1);
        } else if (!waxed) {
            level.setBlock(pos, state.setValue(LIT, true).setValue(LIGHT, 10 + random.nextInt(5)), 3);
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.CAGE_LIGHT_ON, SoundSource.BLOCKS, 0.05F, 0.95F + random.nextFloat() * 0.1F, 1);
        }
        level.scheduleTick(pos, this, tick);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        boolean isAxe = stack.canPerformAction(ItemAbilities.AXE_WAX_OFF);
        boolean isComb = stack.is(Items.HONEYCOMB);
        boolean waxed = state.getValue(WAXED);

        if (!isAxe && !isComb) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else if (isAxe && waxed) {
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 0.5F, 0.9F + level.getRandom().nextFloat() * 0.2F);
            level.setBlock(pos, state.setValue(WAXED, false), 3);
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            if (!level.isClientSide) {
                for (int i = 0; i < 11; i++) {
                    NetworkUtil.spawnParticle((ServerLevel)level, ParticleTypes.WAX_OFF, List.of(
                            new SpawnParticlesPayload.ParticleInstance(
                                    pos.getX() + Math.random(), pos.getY() + Math.random(), pos.getZ() + Math.random(),
                                    level.random.nextGaussian() * 0.3D, level.random.nextGaussian() * 0.3D, level.random.nextGaussian() * 0.3D
                            ))
                    );
                }
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (isComb && !waxed){
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 0.5F, 0.9F + level.getRandom().nextFloat() * 0.2F);
            level.setBlock(pos, state.setValue(WAXED, true), 3);
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            if (!level.isClientSide) {
                for (int i = 0; i < 11; i++) {
                    NetworkUtil.spawnParticle((ServerLevel)level, ParticleTypes.WAX_ON, List.of(
                            new SpawnParticlesPayload.ParticleInstance(
                                    pos.getX() + Math.random(), pos.getY() + Math.random(), pos.getZ() + Math.random(),
                                    level.random.nextGaussian() * 0.3D, level.random.nextGaussian() * 0.3D, level.random.nextGaussian() * 0.3D
                            ))
                    );
                }
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, LIT, LIGHT, WAXED);
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
