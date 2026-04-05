package com.syndicatemc.curiosities.common.entity;

import com.syndicatemc.curiosities.common.block.CenserBlock;
import com.syndicatemc.curiosities.common.block.IncenseBlock;
import com.syndicatemc.curiosities.common.block.IncenseWallBlock;
import com.syndicatemc.curiosities.core.registry.CBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;

public class CenserBlockEntity extends BlockEntity {
    public CenserBlockEntity(BlockPos pos, BlockState state) {
        super(CBlockEntityTypes.CENSER.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CenserBlockEntity blockEntity) {
        Block block = state.getBlock();
        Holder<MobEffect> effect = ((CenserBlock) block).getEffect();
        if (state.getValue(BlockStateProperties.LIT)) {
            for (Player player : level.getEntitiesOfClass(Player.class, new AABB(pos).inflate(5.0D, 5.0D, 5.0))) {
                player.addEffect(new MobEffectInstance(effect, 110, 0, false, true));
            }
        }
    }
}
