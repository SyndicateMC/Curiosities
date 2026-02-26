package com.syndicatemc.curiosities.core.other.tags;

import com.syndicatemc.curiosities.core.Curiosities;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CBlockTags {
    public static final TagKey<Block> ORES_ALUMINUM = TagUtil.blockTag("forge", "ores/aluminum");
    public static final TagKey<Block> ORES_NICKEL = TagUtil.blockTag("forge", "ores/nickel");

    public static final TagKey<Block> STORAGE_BLOCKS_ALUMINUM = TagUtil.blockTag("forge", "storage_blocks/aluminum");
    public static final TagKey<Block> STORAGE_BLOCKS_NICKEL = TagUtil.blockTag("forge", "storage_blocks/nickel");
    public static final TagKey<Block> STORAGE_BLOCKS_INVAR = TagUtil.blockTag("forge", "storage_blocks/invar");

    public static final TagKey<Block> TRUMPET_NOTE_BLOCKS = TagUtil.blockTag(Curiosities.MOD_ID, "trumpet_note_blocks");
    public static final TagKey<Block> TRUMPET_EXPOSED_NOTE_BLOCKS = TagUtil.blockTag(Curiosities.MOD_ID, "trumpet_exposed_note_blocks");
    public static final TagKey<Block> TRUMPET_WEATHERED_NOTE_BLOCKS = TagUtil.blockTag(Curiosities.MOD_ID, "trumpet_weathered_note_blocks");
    public static final TagKey<Block> TRUMPET_OXIDIZED_NOTE_BLOCKS = TagUtil.blockTag(Curiosities.MOD_ID, "trumpet_oxidized_note_blocks");
    public static final TagKey<Block> STEEL_DRUM_NOTE_BLOCKS = TagUtil.blockTag(Curiosities.MOD_ID, "steel_drum_note_blocks");
    public static final TagKey<Block> SLAP_BASE_NOTE_BLOCKS = TagUtil.blockTag(Curiosities.MOD_ID, "slap_bass_note_blocks");
}
