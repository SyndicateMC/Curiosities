package com.syndicatemc.curiosities.core.other.tags;

import com.syndicatemc.curiosities.core.Curiosities;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CItemTags {
    public static final TagKey<Item> INGOTS_ALUMINUM = TagUtil.itemTag("c", "ingots/aluminum");
    public static final TagKey<Item> INGOTS_NICKEL = TagUtil.itemTag("c", "ingots/nickel");
    public static final TagKey<Item> INGOTS_INVAR = TagUtil.itemTag("c", "ingots/invar");

    public static final TagKey<Item> NUGGETS_ALUMINUM = TagUtil.itemTag("c", "nuggets/aluminum");
    public static final TagKey<Item> NUGGETS_NICKEL = TagUtil.itemTag("c", "nuggets/nickel");
    public static final TagKey<Item> NUGGETS_INVAR = TagUtil.itemTag("c", "nuggets/invar");

    public static final TagKey<Item> ORES_ALUMINUM = TagUtil.itemTag("c", "ores/aluminum");
    public static final TagKey<Item> ORES_NICKEL = TagUtil.itemTag("c", "ores/nickel");

    public static final TagKey<Item> STORAGE_BLOCKS_ALUMINUM = TagUtil.itemTag("c", "storage_blocks/aluminum");
    public static final TagKey<Item> STORAGE_BLOCKS_NICKEL = TagUtil.itemTag("c", "storage_blocks/nickel");
    public static final TagKey<Item> STORAGE_BLOCKS_INVAR = TagUtil.itemTag("c", "storage_blocks/invar");

    public static final TagKey<Item> ALUMINUM_TOOLS = TagUtil.itemTag(Curiosities.MOD_ID, "aluminum_tools");
    public static final TagKey<Item> INVAR_TOOLS = TagUtil.itemTag(Curiosities.MOD_ID, "invar_tools");
}
