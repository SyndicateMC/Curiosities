package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.core.Curiosities;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Curiosities.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> DAMAGE_REDUCTION = ATTRIBUTES.register("damage_reduction", () ->
            new RangedAttribute("attribute.%s.%s".formatted(Curiosities.MOD_ID, "damage_reduction"), 0, 0, 10)
    );
    public static final DeferredHolder<Attribute, Attribute> ARMOR_PIERCING = ATTRIBUTES.register("armor_piercing", () ->
            new RangedAttribute("attribute.%s.%s".formatted(Curiosities.MOD_ID, "armor_piercing"), 1, 1, 2)
    );
    public static final DeferredHolder<Attribute, Attribute> IMMUTABILITY = ATTRIBUTES.register("immutability", () ->
            new RangedAttribute("attribute.%s.%s".formatted(Curiosities.MOD_ID, "immutability"), 0, 0, 10)
    );
}
