package com.syndicatemc.curiosities.core.other;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class CConstants {
    public static final String FARMERS_DELIGHT = "farmersdelight";
    public static final ResourceKey<CreativeModeTab> FARMERS_DELIGHT_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(FARMERS_DELIGHT, FARMERS_DELIGHT));
    public static final ResourceLocation DIAMOND_KNIFE = ResourceLocation.fromNamespaceAndPath(FARMERS_DELIGHT, "diamond_knife");
    public static final ResourceLocation NETHERITE_KNIFE = ResourceLocation.fromNamespaceAndPath(FARMERS_DELIGHT, "netherite_knife");

    public static final String WOODWORKS = "woodworks";
}
