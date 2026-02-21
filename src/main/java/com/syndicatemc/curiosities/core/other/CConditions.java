package com.syndicatemc.curiosities.core.other;

import com.google.common.collect.Maps;
import com.syndicatemc.curiosities.core.registry.CConditionsSerializers;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import net.neoforged.neoforge.common.ModConfigSpec;

import static com.syndicatemc.curiosities.core.CuriositiesConfig.COMMON;

public class CConditions {
    public static final ConfigValueCondition INVAR_DECOR_BASTIONS = config(COMMON.invarDecorBastions, "invar_decor_bastions");

    public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key, boolean inverted) {
        return new ConfigValueCondition(CConditionsSerializers.CONFIG.get(), value, key, Maps.newHashMap(), inverted);
    }

    public static ConfigValueCondition config(ModConfigSpec.ConfigValue<?> value, String key) {
        return config(value, key, false);
    }
}
