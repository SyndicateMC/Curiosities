package com.syndicatemc.curiosities.core.other.compat;

import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class FDTags {
    public static final TagKey<Item> STRAW_HARVESTERS = TagUtil.itemTag("farmersdelight", "straw_harvesters");
    public static final TagKey<Item> ENCHANTABLE_KNIFE = TagUtil.itemTag("farmersdelight", "enchantable/knife");
    public static final TagKey<Item> KNIFE = TagUtil.itemTag("farmersdelight", "tools/knives");

    // Kitchen Projectiles compat, too
    public static final TagKey<Item> THROWABLE_KNIFE = TagUtil.itemTag("kitchenprojectiles", "throwable_knives");
    public static final TagKey<Item> LIGHT_KNIFE = TagUtil.itemTag("kitchenprojectiles", "light_knives");
}
