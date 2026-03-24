package com.syndicatemc.curiosities.common.block;

import com.mojang.serialization.MapCodec;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CMobEffects;
import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import com.teamabnormals.blueprint.common.network.particle.SpawnParticlesPayload;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.ItemAbilities;

import java.util.List;

public class IncenseBlock extends BaseTorchBlock {
    public static final MapCodec<IncenseBlock> CODEC = simpleCodec(IncenseBlock::incenseBlockProvider);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final IntegerProperty LIFETIME = IntegerProperty.create("lifetime", 0, 300);
    protected final Holder<MobEffect> incenseEffect;
    protected final int smokeColor;

    public IncenseBlock(BlockBehaviour.Properties properties, Holder<MobEffect> effect, int smokeColor) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false).setValue(LIFETIME, 300));
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
        builder.add(LIT, LIFETIME);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            double d0 = (double) pos.getX() + 0.5;
            double d1 = (double) pos.getY() + 0.7;
            double d2 = (double) pos.getZ() + 0.5;
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
            level.addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(255, this.smokeColor)), d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        incenseFunction(state, level, pos, this.incenseEffect);
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
        level.setBlock(pos, state.setValue(LIT, true).setValue(LIFETIME, 300), 11);
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

    public static void incenseFunction(BlockState state, ServerLevel level, BlockPos pos, Holder<MobEffect> effect) {
        for (Player player : level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(2.5D, 2.5D, 2.5D))) {
            player.addEffect(new MobEffectInstance(effect, 50, 0, false, true));
        }
        if (state.getValue(LIT)) if (state.getValue(LIFETIME) > 1) {
            level.setBlock(pos, state.setValue(LIFETIME, state.getValue(LIFETIME) - 1), 11);
            level.scheduleTick(pos, state.getBlock(), 5);
        } else level.destroyBlock(pos, false);
    }
}
