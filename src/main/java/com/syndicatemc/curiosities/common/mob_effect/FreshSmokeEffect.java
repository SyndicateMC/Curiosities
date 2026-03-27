package com.syndicatemc.curiosities.common.mob_effect;

import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class FreshSmokeEffect extends BlueprintMobEffect {
    public FreshSmokeEffect() {
        super(MobEffectCategory.BENEFICIAL, 6462696);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player && player.isInWaterOrRain()) player.heal(0.0625F * (amplifier + 1));
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 5 == 0;
    }
}
