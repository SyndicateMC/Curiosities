package com.syndicatemc.curiosities.common.block;

import com.mojang.serialization.MapCodec;
import com.syndicatemc.curiosities.common.entity.IncenseBlockEntity;
import com.syndicatemc.curiosities.core.other.CUtils;
import com.syndicatemc.curiosities.core.registry.CBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class IncenseBlock extends BaseTorchBlock implements EntityBlock {
    public static final MapCodec<IncenseBlock> CODEC = simpleCodec(IncenseBlock::incenseBlockProvider);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    protected final Holder<MobEffect> incenseEffect;
    protected final int smokeColor;

    public IncenseBlock(BlockBehaviour.Properties properties, Holder<MobEffect> effect, int smokeColor) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
        this.incenseEffect = effect;
        this.smokeColor = smokeColor;
    }

    public static IncenseBlock incenseBlockProvider(Properties properties) {
        return new IncenseBlock(properties, null, 0);
    }

    @Override
    public MapCodec<IncenseBlock> codec() {
        return CODEC;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double d0 = pos.getX() + 0.5;
            double d1 = pos.getY() + 0.7;
            double d2 = pos.getZ() + 0.5;
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
            level.addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(255, this.smokeColor)), d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    public Holder<MobEffect> getEffect() {
        return incenseEffect;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT) && !state.getValue(LIT)) {
            return light(stack, state, level, pos, player, hand, hitResult);
        } else {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }
    }
    public static ItemInteractionResult light(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        RandomSource random = level.getRandom();
        level.setBlock(pos, state.setValue(LIT, true), 11);
        if (stack.getItem() instanceof FlintAndSteelItem) {
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            level.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
        } else {
            if (!player.isCreative()) stack.shrink(1);
            level.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
        level.scheduleTick(pos, state.getBlock(), 5);
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new IncenseBlockEntity(blockPos, blockState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntity) {
        return CUtils.createTickerHelper(blockEntity, CBlockEntityTypes.INCENSE.get(), IncenseBlockEntity::tick);
    }
}
