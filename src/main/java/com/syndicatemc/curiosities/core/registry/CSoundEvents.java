package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.core.Curiosities;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CSoundEvents {
    public static final SoundSubRegistryHelper SOUND_EVENTS = Curiosities.REGISTRY_HELPER.getSoundSubHelper();

    public static final DeferredHolder<SoundEvent, SoundEvent> FUSE_TRIP = SOUND_EVENTS.createSoundEvent("block.redstone_fuse.trip");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUSE_FIX = SOUND_EVENTS.createSoundEvent("block.redstone_fuse.fix");
    public static final DeferredHolder<SoundEvent, SoundEvent> FUSE_SLIDER = SOUND_EVENTS.createSoundEvent("block.redstone_fuse.slider");
    public static final DeferredHolder<SoundEvent, SoundEvent> CAGE_LIGHT_ON = SOUND_EVENTS.createSoundEvent("block.cage_light.on");
    public static final DeferredHolder<SoundEvent, SoundEvent> CAGE_LIGHT_OFF = SOUND_EVENTS.createSoundEvent("block.cage_light.off");
    public static final DeferredHolder<SoundEvent, SoundEvent> CONCUSSION_BOMB_FUSE = SOUND_EVENTS.createSoundEvent("block.concussion_fuse");
    public static final DeferredHolder<SoundEvent, SoundEvent> ASHLAR_BRICK_SHUFFLE = SOUND_EVENTS.createSoundEvent("block.ashlar_brick_shuffle");

    public static final DeferredHolder<SoundEvent, SoundEvent> DAMAGE_REDUCTION = SOUND_EVENTS.createSoundEvent("damage.damage_reduce");
    public static final DeferredHolder<SoundEvent, SoundEvent> DAMAGE_REDUCE_ALL = SOUND_EVENTS.createSoundEvent("damage.damage_reduce_full");

    public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_ALUMINUM = SOUND_EVENTS.createSoundEvent("item.armor.equip_aluminum");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_INVAR = SOUND_EVENTS.createSoundEvent("item.armor.equip_invar");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARMOR_EQUIP_HEAVY = SOUND_EVENTS.createSoundEvent("item.armor.equip_heavy");

    public static final DeferredHolder<SoundEvent, SoundEvent> TRUMPET = SOUND_EVENTS.createSoundEvent("block.note_block.trumpet");
    public static final DeferredHolder<SoundEvent, SoundEvent> TRUMPET_EXPOSED = SOUND_EVENTS.createSoundEvent("block.note_block.trumpet_exposed");
    public static final DeferredHolder<SoundEvent, SoundEvent> TRUMPET_WEATHERED = SOUND_EVENTS.createSoundEvent("block.note_block.trumpet_weathered");
    public static final DeferredHolder<SoundEvent, SoundEvent> TRUMPET_OXIDIZED = SOUND_EVENTS.createSoundEvent("block.note_block.trumpet_oxidized");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_DRUM = SOUND_EVENTS.createSoundEvent("block.note_block.steel_drum");
    public static final DeferredHolder<SoundEvent, SoundEvent> SLAP_BASS = SOUND_EVENTS.createSoundEvent("block.note_block.slap_bass");

    public static final DeferredHolder<SoundEvent, SoundEvent> SCULKY_DEEPSLATE_BREAK = SOUND_EVENTS.createSoundEvent("block.sculky_deepslate.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> SCULKY_DEEPSLATE_FALL = SOUND_EVENTS.createSoundEvent("block.sculky_deepslate.fall");
    public static final DeferredHolder<SoundEvent, SoundEvent> SCULKY_DEEPSLATE_HIT = SOUND_EVENTS.createSoundEvent("block.sculky_deepslate.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> SCULKY_DEEPSLATE_PLACE = SOUND_EVENTS.createSoundEvent("block.sculky_deepslate.place");
    public static final DeferredHolder<SoundEvent, SoundEvent> SCULKY_DEEPSLATE_STEP = SOUND_EVENTS.createSoundEvent("block.sculky_deepslate.step");

    public static class CSoundTypes {
        public static final DeferredSoundType SCULKY_DEEPSLATE = new DeferredSoundType(1.0F, 1.0F, SCULKY_DEEPSLATE_BREAK, SCULKY_DEEPSLATE_STEP, SCULKY_DEEPSLATE_PLACE, SCULKY_DEEPSLATE_HIT, SCULKY_DEEPSLATE_FALL);
    }
}
