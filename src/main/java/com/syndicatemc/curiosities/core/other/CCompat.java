package com.syndicatemc.curiosities.core.other;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.tags.CBlockTags;
import com.syndicatemc.curiosities.core.registry.CBlocks;
import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.DataUtil.CustomNoteBlockInstrument;

public class CCompat {
    public static void registerCompat() {
        registerNoteBlocks();
    }

    private static void registerNoteBlocks() {
        DataUtil.registerNoteBlockInstrument(new CustomNoteBlockInstrument(Curiosities.MOD_ID, source -> source.state().is(CBlockTags.TRUMPET_NOTE_BLOCKS), CSoundEvents.TRUMPET.get()));
        DataUtil.registerNoteBlockInstrument(new CustomNoteBlockInstrument(Curiosities.MOD_ID, source -> source.state().is(CBlockTags.TRUMPET_EXPOSED_NOTE_BLOCKS), CSoundEvents.TRUMPET_EXPOSED.get()));
        DataUtil.registerNoteBlockInstrument(new CustomNoteBlockInstrument(Curiosities.MOD_ID, source -> source.state().is(CBlockTags.TRUMPET_WEATHERED_NOTE_BLOCKS), CSoundEvents.TRUMPET_WEATHERED.get()));
        DataUtil.registerNoteBlockInstrument(new CustomNoteBlockInstrument(Curiosities.MOD_ID, source -> source.state().is(CBlockTags.TRUMPET_OXIDIZED_NOTE_BLOCKS), CSoundEvents.TRUMPET_OXIDIZED.get()));
        DataUtil.registerNoteBlockInstrument(new CustomNoteBlockInstrument(Curiosities.MOD_ID, source -> source.state().is(CBlockTags.STEEL_DRUM_NOTE_BLOCKS), CSoundEvents.STEEL_DRUM.get()));
        DataUtil.registerNoteBlockInstrument(new CustomNoteBlockInstrument(Curiosities.MOD_ID, source -> source.state().is(CBlockTags.SLAP_BASE_NOTE_BLOCKS), CSoundEvents.SLAP_BASS.get()));
    }
}
