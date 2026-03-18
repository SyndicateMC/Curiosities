package com.syndicatemc.curiosities.core.registry.datapack;

import com.syndicatemc.curiosities.core.Curiosities;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CDamageTypes {
    public static final ResourceKey<DamageType> TOPAZ_TEMPO_ADD = createKey("topaz_tempo_add");

    public static void bootstrap(BootstrapContext<DamageType> context) {
        register(context, TOPAZ_TEMPO_ADD, "player", "minecraft", 0.0F);
    }

    public static DamageSource tempoDamage(Level level, LivingEntity reciever, Player player) {
        return level.damageSources().source(TOPAZ_TEMPO_ADD, reciever, player);
    }

    private static Reference<DamageType> register(BootstrapContext<DamageType> context, ResourceKey<DamageType> key, String localizationKey, String keyLocation, float exhaustion) {
        return context.register(key, new DamageType(keyLocation + "." + localizationKey, exhaustion));
    }

    public static ResourceKey<DamageType> createKey(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Curiosities.location(name));
    }
}
