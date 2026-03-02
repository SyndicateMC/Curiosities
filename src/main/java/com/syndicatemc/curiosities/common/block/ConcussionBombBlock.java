package com.syndicatemc.curiosities.common.block;

import com.syndicatemc.curiosities.common.entity.ConcussionBomb;
import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;

public class ConcussionBombBlock extends TntBlock {
    public ConcussionBombBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, false));
    }

    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        if (!level.isClientSide) {
            ConcussionBomb bomb = new ConcussionBomb(level, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, igniter);
            int i = bomb.getFuse();
            bomb.setFuse(i + level.getRandom().nextInt(i / 2));
            level.addFreshEntity(bomb);
            level.playSound(null, bomb.getX(), bomb.getY(), bomb.getZ(), CSoundEvents.CONCUSSION_BOMB_FUSE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }

    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        if (!level.isClientSide) {
            ConcussionBomb bomb = new ConcussionBomb(level, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, explosion.getIndirectSourceEntity());
            int i = bomb.getFuse();
            bomb.setFuse(i + level.getRandom().nextInt(i / 2));
            level.addFreshEntity(bomb);
        }
    }
}
