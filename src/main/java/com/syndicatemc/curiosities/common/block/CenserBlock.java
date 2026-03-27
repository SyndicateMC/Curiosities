package com.syndicatemc.curiosities.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;

public class CenserBlock extends LanternBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    protected static final VoxelShape AABB = Shapes.or(
            Block.box(5.0F, 0.0F, 5.0F, 11.0F, 1.0F, 11.0F),
            Block.box(4.0F, 1.0F, 4.0F, 12.0F, 8.0F, 12.0F),
            Block.box(5.0F, 8.0F, 5.0F, 11.0F, 10.0F, 11.0F)
    );;
    protected final Holder<MobEffect> incenseEffect;
    protected final int smokeColor;

    public CenserBlock(BlockBehaviour.Properties properties, Holder<MobEffect> effect, int smokeColor) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
        this.incenseEffect = effect;
        this.smokeColor = smokeColor;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, HANGING, WATERLOGGED);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        censerFunction(state, level, pos, this.incenseEffect);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            level.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.625, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
            for (int i = 0; i < 3; i++) {
                double d0 = pos.getX() + 0.4 + random.nextFloat() * 0.2;
                double d1 = pos.getY() + 0.25;
                double d2 = pos.getZ() + 0.4 + random.nextFloat() * 0.2;
                level.addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(255, this.smokeColor)), d0, d1, d2, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT) && !state.getValue(LIT)) {
            return IncenseBlock.light(stack, state, level, pos, player, hand, hitResult);
        } else {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }
    }

    public static void censerFunction(BlockState state, ServerLevel level, BlockPos pos, Holder<MobEffect> effect) {
        for (Player player : level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(5.0D, 5.0D, 5.0D))) {
            player.addEffect(new MobEffectInstance(effect, 50, 0, false, true));
        }
        if (state.getValue(LIT)) {
            level.scheduleTick(pos, state.getBlock(), 5);
        }
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }
}
