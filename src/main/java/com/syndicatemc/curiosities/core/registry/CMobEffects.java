package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.core.Curiosities;
import com.teamabnormals.blueprint.common.effect.BlueprintMobEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Curiosities.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> TEMPO = EFFECTS.register("tempo", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 16295993));
}
