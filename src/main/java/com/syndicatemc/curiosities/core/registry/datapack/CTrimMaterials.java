package com.syndicatemc.curiosities.core.registry.datapack;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CItems;
import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

public class CTrimMaterials {
    public static final ResourceKey<TrimMaterial> ALUMINUM = createKey("aluminum");
    public static final ResourceKey<TrimMaterial> NICKEL = createKey("nickel");
    public static final ResourceKey<TrimMaterial> INVAR = createKey("invar");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, ALUMINUM, CItems.ALUMINUM_INGOT.get(), Style.EMPTY.withColor(0xC2C7CC), Map.of());
        register(context, NICKEL, CItems.NICKEL_INGOT.get(), Style.EMPTY.withColor(0xAE9F91), Map.of());
        register(context, INVAR, CItems.INVAR_INGOT.get(), Style.EMPTY.withColor(0x818779), Map.of());
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Curiosities.location(name));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Item item, Style style, Map<Holder<ArmorMaterial>, String> overrides) {
        ResourceLocation location = key.location();
        context.register(key, TrimMaterial.create(location.getNamespace() + "_" + location.getPath(), item, -1.0F, Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style), overrides));
    }
}
