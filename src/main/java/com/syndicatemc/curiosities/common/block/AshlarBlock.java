package com.syndicatemc.curiosities.common.block;

import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import com.teamabnormals.blueprint.common.network.particle.SpawnParticlesPayload;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;

import java.util.List;

public class AshlarBlock extends Block {
    public static final IntegerProperty LAYER = IntegerProperty.create("layer", 1, 3);

    public AshlarBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LAYER, 1));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LAYER);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos targetPos = context.getClickedPos();
        int yPos = targetPos.getY();
        int yDiv = 1;
        if (yPos > 0) {
            if (yPos % 3 != 0) yDiv = yPos % 3; else yDiv = 3;
        } else if (yPos == 0) {
            yDiv = 3;
        } else if (yPos < 0) {
            if (-yPos % 3 == 2) yDiv = 1;
            if (-yPos % 3 == 1) yDiv = 2;
            if (-yPos % 3 == 0) yDiv = 3;
        }
        return this.defaultBlockState().setValue(LAYER, yDiv);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.canPerformAction(ItemAbilities.PICKAXE_DIG)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        } else {
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.ASHLAR_BRICK_SHUFFLE, SoundSource.BLOCKS, 0.5F, 0.9F + level.getRandom().nextFloat() * 0.2F);
            level.setBlock(pos, state.cycle(LAYER), 3);
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
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
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
    }
}
