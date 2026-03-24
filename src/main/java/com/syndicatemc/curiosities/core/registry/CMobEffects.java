package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.common.mob_effect.FreshSmokeEffect;
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

    public static final DeferredHolder<MobEffect, MobEffect> ACRID_SMOKE = EFFECTS.register("acrid_smoke", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 10821965));
    public static final DeferredHolder<MobEffect, MobEffect> BLAND_SMOKE = EFFECTS.register("bland_smoke", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 12371141));
    public static final DeferredHolder<MobEffect, MobEffect> BRIGHT_SMOKE = EFFECTS.register("bright_smoke", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 13547846));
    public static final DeferredHolder<MobEffect, MobEffect> FRESH_SMOKE = EFFECTS.register("fresh_smoke", FreshSmokeEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> SWEET_SMOKE = EFFECTS.register("sweet_smoke", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 14126028));
    public static final DeferredHolder<MobEffect, MobEffect> VERDANT_SMOKE = EFFECTS.register("verdant_smoke", () -> new BlueprintMobEffect(MobEffectCategory.BENEFICIAL, 7706194));
}
