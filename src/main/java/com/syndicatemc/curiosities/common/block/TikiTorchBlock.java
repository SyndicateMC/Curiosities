package com.syndicatemc.curiosities.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TikiTorchBlock extends Block {
    protected static final VoxelShape AABB = Shapes.or(
            Block.box(6.0F, 0.0F, 6.0F, 10.0F, 2.0F, 10.0F),
            Block.box(5.0F, 2.0F, 5.0F, 11.0F, 10.0F, 11.0F),
            Block.box(6.0F, 10.0F, 6.0F, 10.0F, 11.0F, 10.0F)
    );
    protected final boolean isSoul;

    public TikiTorchBlock(BlockBehaviour.Properties properties, boolean isSoul) {
        super(properties);
        this.isSoul = isSoul;
        this.registerDefaultState(this.stateDefinition.any());
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return Block.canSupportCenter(level, pos.below(), Direction.UP);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double d0 = pos.getX() + 0.45F + random.nextFloat() * 0.1;
        double d1 = pos.getY() + 0.65F + random.nextFloat() * 0.2;
        double d2 = pos.getZ() + 0.45F + random.nextFloat() * 0.1;
        level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0F, 0.0F, 0.0F);
        level.addParticle(this.isSoul ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME, d0, d1, d2, 0.0F, 0.0F, 0.0F);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }
}
