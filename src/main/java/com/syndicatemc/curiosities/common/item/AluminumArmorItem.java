package com.syndicatemc.curiosities.common.item;

import com.google.common.base.Suppliers;
import com.syndicatemc.curiosities.core.registry.CArmorMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.function.Supplier;

public class AluminumArmorItem extends ArmorItem {
    private final Supplier<ItemAttributeModifiers> defaultModifiers;

    public AluminumArmorItem(ArmorItem.Type slot) {
        super(CArmorMaterials.ALUMINUM, slot, new Properties().durability(slot.getDurability(10)));
        this.defaultModifiers = Suppliers.memoize(() -> {
            ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
            EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
            ResourceLocation location = ResourceLocation.withDefaultNamespace("armor." + type.getName());
            builder.add(Attributes.ARMOR, new AttributeModifier(location, CArmorMaterials.ALUMINUM.value().getDefense(slot), AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            builder.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(location, 0.5, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            builder.add(Attributes.BLOCK_BREAK_SPEED, new AttributeModifier(location, 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), equipmentSlotGroup);

            return builder.build();
        });
    }

    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.defaultModifiers.get();
    }
}
