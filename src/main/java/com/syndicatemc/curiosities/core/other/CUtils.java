package com.syndicatemc.curiosities.core.other;

import com.syndicatemc.curiosities.core.other.tags.CItemTags;
import com.syndicatemc.curiosities.core.registry.CAttributes;
import com.syndicatemc.curiosities.core.registry.CMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CUtils {
    public static boolean canApplyTempoEffects(Player player) {
        AttributeMap map = player.getAttributes();
        return map.hasAttribute(CAttributes.TEMPO) && map.getValue(CAttributes.TEMPO) > 0 && player.getMainHandItem().is(CItemTags.TOPAZ_TOOLS);
    }

    public static void applyTempo(Player player) {
        player.addEffect(new MobEffectInstance(CMobEffects.TEMPO, 100,
                player.hasEffect(CMobEffects.TEMPO) ? Math.min(player.getEffect(CMobEffects.TEMPO).getAmplifier() + Math.round((float) player.getAttributes().getValue(CAttributes.TEMPO)), 9) : 0,
                false, false
        ));
    }

    public static int getCurrentTempo(Player player) {
        return player.hasEffect(CMobEffects.TEMPO) ? player.getEffect(CMobEffects.TEMPO).getAmplifier() + 1 : 0;
    }

    public static ResourceLocation getLocFromID(String id) {
        String[] splitID = id.split(":");
        return ResourceLocation.fromNamespaceAndPath(splitID[0], splitID[1]);
    }
}
