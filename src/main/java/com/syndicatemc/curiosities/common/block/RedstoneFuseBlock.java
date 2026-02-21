package com.syndicatemc.curiosities.common.block;

import com.mojang.serialization.MapCodec;
import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;
import org.jetbrains.annotations.Nullable;

public class RedstoneFuseBlock extends DiodeBlock {
    public static final BooleanProperty BROKEN = BooleanProperty.create("broken");
    public static final IntegerProperty THRESHOLD = IntegerProperty.create("threshold", 1, 14);

    public static final MapCodec<RedstoneFuseBlock> CODEC = simpleCodec(RedstoneFuseBlock::new);

    public RedstoneFuseBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false).setValue(BROKEN, false).setValue(THRESHOLD, 1));
    }

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int flag = state.getValue(THRESHOLD);
        int flag1 = this.getInputSignal(level, pos, state);
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        } else if (player.isCrouching() && state.getValue(BROKEN) && !(flag1 >= flag)) {
            level.setBlock(pos, state.setValue(BROKEN, false), 3);
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_FIX, SoundSource.BLOCKS, 2.0F, 1.0F + level.random.nextFloat() * 0.1F, 1);
            level.scheduleTick(pos, this, this.getDelay(state), TickPriority.VERY_HIGH);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            level.setBlock(pos, state.cycle(THRESHOLD), 3);
            if (flag == 14) {
                level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_SLIDER, SoundSource.BLOCKS, 2.0F, 1.0F, 1);
            } else {
                level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_SLIDER, SoundSource.BLOCKS, 2.0F, 1.0F + flag * 0.05F, 1);
            }
            level.scheduleTick(pos, this, this.getDelay(state), TickPriority.VERY_HIGH);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, BROKEN, THRESHOLD);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        boolean flag = state.getValue(POWERED);
        boolean flag1 = this.shouldTurnOn(level, pos, state);
        int flag2 = state.getValue(THRESHOLD);
        int flag3 = this.getInputSignal(level, pos, state);
        boolean isBroken = state.getValue(BROKEN);
        if (flag3 >= flag2 && !isBroken) {
            level.setBlock(pos, state.setValue(BROKEN, true), 2);
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_TRIP, SoundSource.BLOCKS, 2.0F, 1.2F + random.nextFloat() * 0.1F, 1);
        }
        if (flag3 < flag2 && isBroken && this.getAlternateSignal(level, pos, state) > 0) {
            level.setBlock(pos, state.setValue(BROKEN, false), 2);
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_FIX, SoundSource.BLOCKS, 2.0F, 1.0F + level.random.nextFloat() * 0.1F, 1);
        }
        if (flag && !flag1) {
            level.setBlock(pos, state.setValue(POWERED, false), 2);
        } else if (!flag && flag1 && !isBroken) {
            level.setBlock(pos, state.setValue(POWERED, true), 2);
        }
        level.scheduleTick(pos, this, this.getDelay(state), TickPriority.VERY_HIGH);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        int flag = state.getValue(THRESHOLD);
        int flag1 = this.getInputSignal(level, pos, state);
        if (state.canSurvive(level, pos)) {
            if (flag1 >= flag && !state.getValue(BROKEN)) {
                level.setBlock(pos, state.setValue(POWERED, false).setValue(BROKEN, true), 2);
                level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_TRIP, SoundSource.BLOCKS, 2.0F, 1.2F + level.random.nextFloat() * 0.1F, 1);
            }
            if (flag1 < flag && state.getValue(BROKEN) && this.getAlternateSignal(level, pos, state) > 0) {
                level.setBlock(pos, state.setValue(BROKEN, false), 2);
                level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_FIX, SoundSource.BLOCKS, 2.0F, 1.0F + level.random.nextFloat() * 0.1F, 1);
            }
            this.checkTickOnNeighbor(level, pos, state);
        } else {
            BlockEntity blockentity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
            dropResources(state, level, pos, blockentity);
            level.removeBlock(pos, false);

            for(Direction direction : Direction.values()) {
                level.updateNeighborsAt(pos.relative(direction), this);
            }
        }

    }

    @Override
    protected int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return super.getLightBlock(state, level, pos);
    }

    public MapCodec<RedstoneFuseBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getDelay(BlockState state) {
        return 2;
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        Direction facing = state.getValue(FACING);
        return facing == direction || facing.getOpposite() == direction;
    }

    @Override
    protected boolean sideInputDiodesOnly() {
        return true;
    }

    @Override
    protected boolean shouldTurnOn(Level level, BlockPos pos, BlockState state) {
        return this.getInputSignal(level, pos, state) > 0 && !state.getValue(BROKEN);
    }

    @Override
    protected int getOutputSignal(BlockGetter level, BlockPos pos, BlockState state) {
        return 15;
    }
}
