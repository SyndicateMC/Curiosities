package com.syndicatemc.curiosities.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BigChainBlock extends ChainBlock {
    protected static final VoxelShape Y_AXIS_AABB = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 16.0F, 11.0F);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(5.0F, 5.0F, 0.0F, 11.0F, 11.0F, 16.0F);
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0F, 5.0F, 5.0F, 16.0F, 11.0F, 11.0F);
    
    public BigChainBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(AXIS, Direction.Axis.Y));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(AXIS)) {
            case X:
            default:
                return X_AXIS_AABB;
            case Z:
                return Z_AXIS_AABB;
            case Y:
                return Y_AXIS_AABB;
        }
    }
}
