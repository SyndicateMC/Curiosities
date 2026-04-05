package com.syndicatemc.curiosities.common.block;

import com.syndicatemc.curiosities.common.entity.CenserBlockEntity;
import com.syndicatemc.curiosities.core.other.CUtils;
import com.syndicatemc.curiosities.core.registry.CBlockEntityTypes;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CenserBlock extends LanternBlock implements EntityBlock {
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

    public Holder<MobEffect> getEffect() {
        return incenseEffect;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.canPerformAction(ItemAbilities.FIRESTARTER_LIGHT) && !state.getValue(LIT)) {
            return IncenseBlock.light(stack, state, level, pos, player, hand, hitResult);
        } else {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        return new CenserBlockEntity(blockPos, blockState);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntity) {
        return CUtils.createTickerHelper(blockEntity, CBlockEntityTypes.CENSER.get(), CenserBlockEntity::tick);
    }
}
