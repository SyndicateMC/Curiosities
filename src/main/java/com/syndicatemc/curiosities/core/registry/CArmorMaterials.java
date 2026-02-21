package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.core.Curiosities;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.checkerframework.checker.units.qual.A;

import java.util.EnumMap;
import java.util.List;

public class CArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Curiosities.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ALUMINUM = ARMOR_MATERIALS.register("aluminum", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 5);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            15,
            CSoundEvents.ARMOR_EQUIP_ALUMINUM,
            () -> Ingredient.of(CItems.ALUMINUM_INGOT),
            List.of(new ArmorMaterial.Layer(Curiosities.location("aluminum"))),
            0.0F,
            0.0F
    ));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> INVAR = ARMOR_MATERIALS.register("invar", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            15,
            CSoundEvents.ARMOR_EQUIP_INVAR,
            () -> Ingredient.of(CItems.INVAR_INGOT),
            List.of(new ArmorMaterial.Layer(Curiosities.location("invar"))),
            1.0F,
            0.0F
    ));
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> HEAVY = ARMOR_MATERIALS.register("heavy", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
            }),
            15,
            CSoundEvents.ARMOR_EQUIP_HEAVY,
            () -> Ingredient.of(Items.BREEZE_ROD),
            List.of(new ArmorMaterial.Layer(Curiosities.location("heavy"))),
            0.0F,
            0.0F
    ));
}
