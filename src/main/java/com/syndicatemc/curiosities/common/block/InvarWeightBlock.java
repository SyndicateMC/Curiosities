package com.syndicatemc.curiosities.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.common.network.particle.SpawnParticlesPayload;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.TickPriority;

import java.util.List;

public class InvarWeightBlock extends FallingBlock {
    public static final MapCodec<InvarWeightBlock> CODEC = simpleCodec(InvarWeightBlock::weightBlockProvider);
    public final int delay;
    public final boolean canHurt;

    public InvarWeightBlock(Properties properties, int delay, boolean canHurt) {
        super(properties);
        this.delay = delay;
        this.canHurt = canHurt;
    }

    public static InvarWeightBlock weightBlockProvider(Properties properties) {
        return new InvarWeightBlock(properties, 10, false);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (isFree(level.getBlockState(pos.below())) && pos.getY() >= level.getMinBuildHeight()) {
            level.scheduleTick(pos, this, this.getDelayAfterPlace(), TickPriority.HIGH);
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (isFree(level.getBlockState(currentPos.below())) && currentPos.getY() >= level.getMinBuildHeight()) {
            level.scheduleTick(currentPos, this, this.getDelayAfterPlace(), TickPriority.VERY_HIGH);
        }
        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public MapCodec<InvarWeightBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getDelayAfterPlace() {
        return this.delay;
    }

    @Override
    protected void falling(FallingBlockEntity entity) {
        if (this.canHurt) entity.setHurtsEntities(5.0F, 100);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {}

    @Override
    public void onLand(Level level, BlockPos pos, BlockState state, BlockState replaceableState, FallingBlockEntity fallingBlock) {
        level.playSound(null, pos, SoundEvents.NETHERITE_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 0.5F + level.random.nextFloat() * 0.25F);
        if (!level.isClientSide) {
            for (int i = 0; i < 11; i++) {
                NetworkUtil.spawnParticle((ServerLevel)level, new BlockParticleOption(ParticleTypes.BLOCK, state), List.of(
                        new SpawnParticlesPayload.ParticleInstance(
                                pos.getX() + Math.random(), pos.getY() + Math.random(), pos.getZ() + Math.random(),
                                level.random.nextGaussian() * 0.02D, level.random.nextGaussian() * 0.02D, level.random.nextGaussian() * 0.02D
                        ))
                );
            }
        }
    }
}
