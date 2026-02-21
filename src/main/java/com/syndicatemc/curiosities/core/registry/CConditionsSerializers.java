package com.syndicatemc.curiosities.core.registry;

import com.mojang.serialization.MapCodec;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.CuriositiesConfig;
import com.teamabnormals.blueprint.core.api.conditions.ConfigValueCondition;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class CConditionsSerializers {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, Curiosities.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends ICondition>, ConfigValueCondition.Serializer> CONFIG = CONDITION_SERIALIZERS.register("config", () -> new ConfigValueCondition.Serializer(DataUtil.getConfigValues(CuriositiesConfig.COMMON)));
}