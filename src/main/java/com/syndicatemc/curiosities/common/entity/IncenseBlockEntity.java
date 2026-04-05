package com.syndicatemc.curiosities.common.entity;

import com.syndicatemc.curiosities.common.block.IncenseBlock;
import com.syndicatemc.curiosities.common.block.IncenseWallBlock;
import com.syndicatemc.curiosities.core.registry.CBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;

public class IncenseBlockEntity extends BlockEntity {
    private int lifetime = 1200;

    public IncenseBlockEntity(BlockPos pos, BlockState state) {
        super(CBlockEntityTypes.INCENSE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, IncenseBlockEntity blockEntity) {
        Block block = state.getBlock();
        Holder<MobEffect> effect = null;
        if (block instanceof IncenseBlock) effect = ((IncenseBlock) block).getEffect();
        if (block instanceof IncenseWallBlock) effect = ((IncenseWallBlock) block).getEffect();
        if (state.getValue(BlockStateProperties.LIT)) {
            for (Player player : level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(2.5D, 2.5D, 2.5D))) {
                player.addEffect(new MobEffectInstance(effect, 110, 1, false, true));
            }
            blockEntity.lifetime--;
            if (blockEntity.lifetime <= 0) level.destroyBlock(pos, false);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("Lifetime", 11)) {
            this.lifetime = tag.getInt("Lifetime");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("Lifetime", this.lifetime);
    }
}
