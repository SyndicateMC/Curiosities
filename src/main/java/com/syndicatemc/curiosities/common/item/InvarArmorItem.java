package com.syndicatemc.curiosities.common.item;

import com.google.common.base.Suppliers;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CArmorMaterials;
import com.syndicatemc.curiosities.core.registry.CAttributes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.function.Supplier;

public class InvarArmorItem extends ArmorItem {
    //private static final ResourceLocation TEXTURE = Curiosities.location("textures/models/armor/invar_armor.png");
    private final Supplier<ItemAttributeModifiers> defaultModifiers;

    public InvarArmorItem(Type slot) {
        super(CArmorMaterials.INVAR, slot, new Properties().durability(slot.getDurability(37)).fireResistant());
        this.defaultModifiers = Suppliers.memoize(() -> {
            ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
            EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
            ResourceLocation location = ResourceLocation.withDefaultNamespace("armor." + type.getName());
            builder.add(Attributes.ARMOR, new AttributeModifier(location, CArmorMaterials.INVAR.value().getDefense(slot), AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(location, CArmorMaterials.INVAR.value().toughness(), AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            builder.add(CAttributes.DAMAGE_REDUCTION, new AttributeModifier(location, 0.25F, AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            return builder.build();
        });
    }

    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.defaultModifiers.get();
    }

//    @Nullable
//    @Override
//    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
//        return TEXTURE;
//    }
}
