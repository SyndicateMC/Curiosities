package com.syndicatemc.curiosities.common.mob_effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SmokeEffect extends MobEffect {
    public SmokeEffect(MobEffectCategory category, int color) {
        super(category, color, ParticleTypes.ITEM_SLIME);
    }
}