package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.common.item.AluminumArmorItem;
import com.syndicatemc.curiosities.common.item.CSmithingTemplateItem;
import com.syndicatemc.curiosities.common.item.InvarArmorItem;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.CConstants;
import com.syndicatemc.curiosities.core.other.compat.CFDCompat;
import com.teamabnormals.blueprint.core.api.BlueprintItemTier;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class CItems {
    public static final ItemSubRegistryHelper ITEMS = Curiosities.REGISTRY_HELPER.getItemSubHelper();

    public static Supplier<? extends Item> compat(String modid, Function<Item.Properties, ? extends Item> supplier, Item.Properties properties) {
        if (ModList.get().isLoaded(modid)) return () -> supplier.apply(properties);
        return () -> new Item(properties);
    }

    public static final DeferredItem<Item> RAW_ALUMINUM = ITEMS.createItem("raw_aluminum", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALUMINUM_INGOT = ITEMS.createItem("aluminum_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALUMINUM_NUGGET = ITEMS.createItem("aluminum_nugget", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_NICKEL = ITEMS.createItem("raw_nickel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NICKEL_INGOT = ITEMS.createItem("nickel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> NICKEL_NUGGET = ITEMS.createItem("nickel_nugget", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> INVAR_INGOT = ITEMS.createItem("invar_ingot", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> INVAR_NUGGET = ITEMS.createItem("invar_nugget", () -> new Item(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> TOPAZ = ITEMS.createItem("topaz", () -> new Item(new Item.Properties()));

    public static final Tier ALUMINUM_TIER = new BlueprintItemTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 8.0F, 2.0F, 14, () -> Ingredient.of(ALUMINUM_INGOT));
    public static final DeferredItem<Item> ALUMINUM_SWORD = ITEMS.createItem("aluminum_sword", () -> new SwordItem(ALUMINUM_TIER, new Item.Properties().attributes(SwordItem.createAttributes(ALUMINUM_TIER, 3, -2.4F))));
    public static final DeferredItem<Item> ALUMINUM_SHOVEL = ITEMS.createItem("aluminum_shovel", () -> new ShovelItem(ALUMINUM_TIER, new Item.Properties().attributes(ShovelItem.createAttributes(ALUMINUM_TIER, 1.5F, -3.0F))));
    public static final DeferredItem<Item> ALUMINUM_PICKAXE = ITEMS.createItem("aluminum_pickaxe", () -> new PickaxeItem(ALUMINUM_TIER, new Item.Properties().attributes(PickaxeItem.createAttributes(ALUMINUM_TIER, 1.0F, -2.8F))));
    public static final DeferredItem<Item> ALUMINUM_AXE = ITEMS.createItem("aluminum_axe", () -> new AxeItem(ALUMINUM_TIER, new Item.Properties().attributes(AxeItem.createAttributes(ALUMINUM_TIER, 6.0F, -3.1F))));
    public static final DeferredItem<Item> ALUMINUM_HOE = ITEMS.createItem("aluminum_hoe", () -> new HoeItem(ALUMINUM_TIER, new Item.Properties().attributes(HoeItem.createAttributes(ALUMINUM_TIER, -2.0F, -1.0F))));

    public static final Tier INVAR_TIER = new BlueprintItemTier(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 9.0F, 3.0F, 15, () -> Ingredient.of(INVAR_INGOT));
    public static final DeferredItem<Item> INVAR_SWORD = ITEMS.createItem("invar_sword", () -> new SwordItem(INVAR_TIER, new Item.Properties().attributes(SwordItem.createAttributes(INVAR_TIER, 3, -2.4F)).fireResistant()));
    public static final DeferredItem<Item> INVAR_SHOVEL = ITEMS.createItem("invar_shovel", () -> new ShovelItem(INVAR_TIER, new Item.Properties().attributes(ShovelItem.createAttributes(INVAR_TIER, 1.5F, -3.0F)).fireResistant()));
    public static final DeferredItem<Item> INVAR_PICKAXE = ITEMS.createItem("invar_pickaxe", () -> new PickaxeItem(INVAR_TIER, new Item.Properties().attributes(PickaxeItem.createAttributes(INVAR_TIER, 1.0F, -2.8F)).fireResistant()));
    public static final DeferredItem<Item> INVAR_AXE = ITEMS.createItem("invar_axe", () -> new AxeItem(INVAR_TIER, new Item.Properties().attributes(AxeItem.createAttributes(INVAR_TIER, 5.0F, -3.0F)).fireResistant()));
    public static final DeferredItem<Item> INVAR_HOE = ITEMS.createItem("invar_hoe", () -> new HoeItem(INVAR_TIER, new Item.Properties().attributes(HoeItem.createAttributes(INVAR_TIER, -3.0F, 0.0F)).fireResistant()));

    public static final Tier TOPAZ_TIER = new BlueprintItemTier(BlockTags.INCORRECT_FOR_IRON_TOOL, 1000, 2.5F, 0.5F, 15, () -> Ingredient.of(TOPAZ));
    public static final DeferredItem<Item> TOPAZ_SWORD = ITEMS.createItem("topaz_sword", () -> new SwordItem(TOPAZ_TIER, new Item.Properties().attributes(SwordItem.createAttributes(TOPAZ_TIER, 3, -2.6F))));
    public static final DeferredItem<Item> TOPAZ_SHOVEL = ITEMS.createItem("topaz_shovel", () -> new ShovelItem(TOPAZ_TIER, new Item.Properties().attributes(ShovelItem.createAttributes(TOPAZ_TIER, 1.5F, -3.0F))));
    public static final DeferredItem<Item> TOPAZ_PICKAXE = ITEMS.createItem("topaz_pickaxe", () -> new PickaxeItem(TOPAZ_TIER, new Item.Properties().attributes(PickaxeItem.createAttributes(TOPAZ_TIER, 1.0F, -2.8F))));
    public static final DeferredItem<Item> TOPAZ_AXE = ITEMS.createItem("topaz_axe", () -> new AxeItem(TOPAZ_TIER, new Item.Properties().attributes(AxeItem.createAttributes(TOPAZ_TIER, 6.0F, -3.3F))));
    public static final DeferredItem<Item> TOPAZ_HOE = ITEMS.createItem("topaz_hoe", () -> new HoeItem(TOPAZ_TIER, new Item.Properties().attributes(HoeItem.createAttributes(TOPAZ_TIER, -1.0F, -1.0F))));

    public static final DeferredItem<ArmorItem> ALUMINUM_HELMET = ITEMS.createItem("aluminum_helmet", () -> new AluminumArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<ArmorItem> ALUMINUM_CHESTPLATE = ITEMS.createItem("aluminum_chestplate", () -> new AluminumArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<ArmorItem> ALUMINUM_LEGGINGS = ITEMS.createItem("aluminum_leggings", () -> new AluminumArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<ArmorItem> ALUMINUM_BOOTS = ITEMS.createItem("aluminum_boots", () -> new AluminumArmorItem(ArmorItem.Type.BOOTS));

    public static final DeferredItem<ArmorItem> INVAR_HELMET = ITEMS.createItem("invar_helmet", () -> new InvarArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<ArmorItem> INVAR_CHESTPLATE = ITEMS.createItem("invar_chestplate", () -> new InvarArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<ArmorItem> INVAR_LEGGINGS = ITEMS.createItem("invar_leggings", () -> new InvarArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<ArmorItem> INVAR_BOOTS = ITEMS.createItem("invar_boots", () -> new InvarArmorItem(ArmorItem.Type.BOOTS));

    public static final DeferredItem<ArmorItem> HEAVY_BOOTS = ITEMS.createItem("heavy_boots", () -> new ArmorItem(CArmorMaterials.HEAVY, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(10)).rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> ALUMINUM_UPGRADE_SMITHING_TEMPLATE = ITEMS.createItem("aluminum_upgrade_smithing_template", CSmithingTemplateItem::createAluminumUpgradeTemplate);

    //fd compat
    public static final DeferredHolder<Item, Item> ALUMINUM_KNIFE = ITEMS.createItem("aluminum_knife", compat(CConstants.FARMERS_DELIGHT, it -> CFDCompat.ALUMINUM_KNIFE_FACTORY.apply(it), new Item.Properties().attributes(DiggerItem.createAttributes(ALUMINUM_TIER, 0.5F, -2.0F))));
    public static final DeferredHolder<Item, Item> INVAR_KNIFE = ITEMS.createItem("invar_knife", compat(CConstants.FARMERS_DELIGHT, it -> CFDCompat.INVAR_KNIFE_FACTORY.apply(it), new Item.Properties().attributes(DiggerItem.createAttributes(INVAR_TIER, 0.5F, -2.0F)).fireResistant()));

    public static void setupTabEditors() {
        CreativeModeTabContentsPopulator.mod(Curiosities.MOD_ID)
                .tab(INGREDIENTS)
                .addItemsAfter(of(Items.RAW_GOLD), RAW_ALUMINUM, RAW_NICKEL)
                .addItemsAfter(of(Items.GOLD_INGOT), ALUMINUM_INGOT, NICKEL_INGOT)
                .addItemsAfter(of(Items.NETHERITE_SCRAP), INVAR_INGOT)
                .addItemsAfter(of(Items.GOLD_NUGGET), ALUMINUM_NUGGET, NICKEL_NUGGET, INVAR_NUGGET)
                .addItemsAfter(of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), ALUMINUM_UPGRADE_SMITHING_TEMPLATE)
                .tab(TOOLS_AND_UTILITIES)
                .addItemsAfter(of(Items.GOLDEN_HOE), ALUMINUM_SHOVEL, ALUMINUM_PICKAXE, ALUMINUM_AXE, ALUMINUM_HOE)
                .addItemsBefore(of(Items.NETHERITE_SHOVEL), INVAR_SHOVEL, INVAR_PICKAXE, INVAR_AXE, INVAR_HOE)
                .tab(COMBAT)
                .addItemsAfter(of(Items.GOLDEN_SWORD), ALUMINUM_SWORD)
                .addItemsAfter(of(Items.GOLDEN_AXE), ALUMINUM_AXE)
                .addItemsBefore(of(Items.NETHERITE_SWORD), INVAR_SWORD)
                .addItemsBefore(of(Items.NETHERITE_AXE), INVAR_AXE)
                .addItemsAfter(of(Items.GOLDEN_BOOTS), ALUMINUM_HELMET, ALUMINUM_CHESTPLATE, ALUMINUM_LEGGINGS, ALUMINUM_BOOTS)
                .addItemsBefore(of(Items.NETHERITE_HELMET), INVAR_HELMET, INVAR_CHESTPLATE, INVAR_LEGGINGS, INVAR_BOOTS)
                .addItemsAfter(of(Items.TURTLE_HELMET), HEAVY_BOOTS);
        if (ModList.get().isLoaded(CConstants.FARMERS_DELIGHT)) CreativeModeTabContentsPopulator.mod(CConstants.FARMERS_DELIGHT + "_" + Curiosities.MOD_ID)
                .tab(CConstants.FARMERS_DELIGHT_TAB)
                .addItemsBefore(ofID(CConstants.DIAMOND_KNIFE), ALUMINUM_KNIFE)
                .addItemsBefore(ofID(CConstants.NETHERITE_KNIFE), INVAR_KNIFE);
    }

    public static Predicate<ItemStack> modLoaded(ItemLike item, String... modids) {
        return stack -> of(item).test(stack) && BlockSubRegistryHelper.areModsLoaded(modids);
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location) {
        return stack -> of(BuiltInRegistries.ITEM.get(location)).test(stack);
    }
}

