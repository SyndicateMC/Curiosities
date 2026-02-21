package com.syndicatemc.curiosities.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HeavyLanternBlock extends LanternBlock {
    protected static final VoxelShape AABB = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 15.0F);
    protected static final VoxelShape HANGING_AABB = Shapes.or(Block.box(1.0F, 4.0F, 1.0F, 15.0F, 14.0F, 15.0F), Block.box(5.0F, 14.0F, 5.0F, 11.0F, 16.0F, 11.0F));;

    public HeavyLanternBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, false).setValue(WATERLOGGED, false));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(HANGING) ? HANGING_AABB : AABB;
    }
}
