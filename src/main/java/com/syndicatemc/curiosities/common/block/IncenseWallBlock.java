package com.syndicatemc.curiosities.common.block;

import com.syndicatemc.curiosities.common.entity.IncenseBlockEntity;
import com.syndicatemc.curiosities.core.other.CUtils;
import com.syndicatemc.curiosities.core.registry.CBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
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

public class IncenseWallBlock extends WallTorchBlock implements EntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    protected final Holder<MobEffect> incenseEffect;
    protected final int smokeColor;

    public IncenseWallBlock(BlockBehaviour.Properties properties, Holder<MobEffect> effect, int smokeColor) {
        super(null, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
        this.incenseEffect = effect;
        this.smokeColor = smokeColor;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT)) {
            Direction direction = state.getValue(FACING);
            double d0 = pos.getX() + 0.5F;
            double d1 = pos.getY() + 0.7;
            double d2 = pos.getZ() + 0.5F;
            Direction direction1 = direction.getOpposite();
            level.addParticle(ParticleTypes.SMOKE, d0 + 0.27 * direction1.getStepX(), d1 + 0.22, d2 + 0.27 * direction1.getStepZ(), 0.0F, 0.0F, 0.0F);
            level.addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(255, this.smokeColor)), d0 + 0.27 * direction1.getStepX(), d1 + 0.22, d2 + 0.27 * direction1.getStepZ(), 0.0F, 0.0F, 0.0F);
        }
    }

    public Holder<MobEffect> getEffect() {
        return incenseEffect;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT) && !state.getValue(LIT)) {
            return IncenseBlock.light(stack, state, level, pos, player, hand, hitResult);
        } else {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }
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
