package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.common.entity.ConcussionBomb;
import com.syndicatemc.curiosities.core.Curiosities;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CEntityTypes {
    public static final EntitySubRegistryHelper ENTITY_TYPES = Curiosities.REGISTRY_HELPER.getEntitySubHelper();

    public static final DeferredHolder<EntityType<?>, EntityType<ConcussionBomb>> CONCUSSION_BOMB = ENTITY_TYPES.createEntity("concussion_bomb", ConcussionBomb::new, MobCategory.MISC, builder ->
            builder.fireImmune().sized(0.98F, 0.98F).eyeHeight(0.15F).clientTrackingRange(10).updateInterval(10));
}
