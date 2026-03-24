package com.syndicatemc.curiosities.core.data.server.tags;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.tags.CBlockTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.syndicatemc.curiosities.core.registry.CBlocks.*;

public class CBlockTagsProvider extends BlockTagsProvider {
    public CBlockTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
        super(output, provider, Curiosities.MOD_ID, helper);
    }

    @Override
    public void addTags(Provider provider) {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ALUMINUM_ORE.get(), DEEPSLATE_ALUMINUM_ORE.get(),
                RAW_ALUMINUM_BLOCK.get(), ALUMINUM_BLOCK.get(),
                NICKEL_ORE.get(), DEEPSLATE_NICKEL_ORE.get(),
                RAW_NICKEL_BLOCK.get(), NICKEL_BLOCK.get(),
                INVAR_BLOCK.get(),
                ALUMINUM_GRATE.get(), SHEET_METAL.get(), ALUMINUM_BARS.get(), ALUMINUM_DOOR.get(), ALUMINUM_TRAPDOOR.get(), CUT_ALUMINUM.get(), CUT_ALUMINUM_STAIRS.get(), CUT_ALUMINUM_SLAB.get(),
                NICKEL_BARS.get(),
                WEIGHT_1S.get(), WEIGHT_5S.get(), WEIGHT_20S.get(),
                CAGE_LIGHT.get(), SOUL_CAGE_LIGHT.get(), TILE_LIGHT.get(),
                BIG_CHAIN.get(), HEAVY_LANTERN.get(), HEAVY_SOUL_LANTERN.get(),
                STONE_ASHLAR.get(), DEEPSLATE_ASHLAR.get(), TUFF_ASHLAR.get(), POLISHED_BLACKSTONE_ASHLAR.get(), END_STONE_ASHLAR.get(),
                SMOOTH_STONE_BRICKS.get(), SMOOTH_STONE_ASHLAR.get(), SMOOTH_STONE_BRICK_STAIRS.get(), SMOOTH_STONE_BRICK_SLAB.get(), SMOOTH_STONE_BRICK_WALL.get(),
                LATERITE_BRICKS.get(), LATERITE_BRICK_STAIRS.get(), LATERITE_BRICK_SLAB.get(), LATERITE_BRICK_WALL.get(),

                SMOOTH_STONE_STAIRS.get(), CUT_SANDSTONE_STAIRS.get(), CUT_RED_SANDSTONE_STAIRS.get(), QUARTZ_BRICK_STAIRS.get(),
                QUARTZ_BRICK_SLAB.get(),
                STONE_WALL.get(), SMOOTH_STONE_WALL.get(), POLISHED_GRANITE_WALL.get(), POLISHED_DIORITE_WALL.get(), POLISHED_ANDESITE_WALL.get(), CUT_SANDSTONE_WALL.get(), CUT_RED_SANDSTONE_WALL.get(), PRISMARINE_BRICK_WALL.get(), DARK_PRISMARINE_WALL.get(), QUARTZ_WALL.get(), QUARTZ_BRICK_WALL.get(), PURPUR_WALL.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                FANCIED_OAK_PLANKS.get(), FANCIED_SPRUCE_PLANKS.get(), FANCIED_BIRCH_PLANKS.get(), FANCIED_JUNGLE_PLANKS.get(), FANCIED_ACACIA_PLANKS.get(), FANCIED_DARK_OAK_PLANKS.get(), FANCIED_MANGROVE_PLANKS.get(), FANCIED_CHERRY_PLANKS.get(), FANCIED_BAMBOO_PLANKS.get(), FANCIED_CRIMSON_PLANKS.get(), FANCIED_WARPED_PLANKS.get(),

                FANCIED_ASPEN_PLANKS.get(), FANCIED_GRIMWOOD_PLANKS.get(), FANCIED_KOUSA_PLANKS.get(), FANCIED_LAUREL_PLANKS.get(), FANCIED_MORADO_PLANKS.get(), FANCIED_ROSEWOOD_PLANKS.get(), FANCIED_YUCCA_PLANKS.get(),
                FANCIED_MAPLE_PLANKS.get(),
                FANCIED_PINE_PLANKS.get(), FANCIED_PLUM_PLANKS.get(), FANCIED_WILLOW_PLANKS.get(), FANCIED_WISTERIA_PLANKS.get(),
                FANCIED_DRIFTWOOD_PLANKS.get(), FANCIED_RIVER_PLANKS.get(),
                FANCIED_POWDERY_PLANKS.get(),
                FANCIED_SOULBLIGHT_PLANKS.get(), FANCIED_WHISTLECANE_PLANKS.get(),

                TIKI_TORCH.get(), SOUL_TIKI_TORCH.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(LATERITE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                NICKEL_ORE.get(), DEEPSLATE_NICKEL_ORE.get(),
                RAW_ALUMINUM_BLOCK.get(), ALUMINUM_BLOCK.get(),
                RAW_NICKEL_BLOCK.get(), NICKEL_BLOCK.get(),
                ALUMINUM_GRATE.get(), SHEET_METAL.get(), ALUMINUM_BARS.get(), ALUMINUM_DOOR.get(), ALUMINUM_TRAPDOOR.get(), CUT_ALUMINUM.get(), CUT_ALUMINUM_STAIRS.get(), CUT_ALUMINUM_SLAB.get(),
                NICKEL_BARS.get()
        );
        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                ALUMINUM_ORE.get(), DEEPSLATE_ALUMINUM_ORE.get()
        );
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                INVAR_BLOCK.get()
        );

        this.tag(BlockTags.STAIRS).add(
                SMOOTH_STONE_BRICK_STAIRS.get(),

                SMOOTH_STONE_STAIRS.get(),
                CUT_SANDSTONE_STAIRS.get(), CUT_RED_SANDSTONE_STAIRS.get(),
                QUARTZ_BRICK_STAIRS.get()
        );

        this.tag(BlockTags.SLABS).add(
                SMOOTH_STONE_BRICK_SLAB.get(),

                QUARTZ_BRICK_SLAB.get()
        );

        this.tag(BlockTags.WALLS).add(
                SMOOTH_STONE_BRICK_WALL.get(),

                STONE_WALL.get(), SMOOTH_STONE_WALL.get(), POLISHED_GRANITE_WALL.get(), POLISHED_DIORITE_WALL.get(), POLISHED_ANDESITE_WALL.get(),
                CUT_SANDSTONE_WALL.get(), CUT_RED_SANDSTONE_WALL.get(),
                PRISMARINE_BRICK_WALL.get(), DARK_PRISMARINE_WALL.get(),
                QUARTZ_WALL.get(), QUARTZ_BRICK_WALL.get(),
                PURPUR_WALL.get()
        );

        this.tag(CBlockTags.ORES_ALUMINUM).add(ALUMINUM_ORE.get(), DEEPSLATE_ALUMINUM_ORE.get());
        this.tag(CBlockTags.ORES_NICKEL).add(NICKEL_ORE.get(), DEEPSLATE_NICKEL_ORE.get());
        this.tag(CBlockTags.STORAGE_BLOCKS_ALUMINUM).add(ALUMINUM_BLOCK.get());
        this.tag(CBlockTags.STORAGE_BLOCKS_NICKEL).add(NICKEL_BLOCK.get());
        this.tag(CBlockTags.STORAGE_BLOCKS_INVAR).add(INVAR_BLOCK.get());

        this.tag(BlockTags.DOORS).add(ALUMINUM_DOOR.get());
        this.tag(BlockTags.TRAPDOORS).add(ALUMINUM_TRAPDOOR.get());

        this.tag(BlockTags.DIRT).add(LATERITE.get());

        this.tag(BlockTags.PIGLIN_REPELLENTS).add(SOUL_CAGE_LIGHT.get(), HEAVY_SOUL_LANTERN.get());

        this.tag(CBlockTags.TRUMPET_NOTE_BLOCKS).add(Blocks.COPPER_BLOCK, Blocks.CUT_COPPER, Blocks.CUT_COPPER_STAIRS, Blocks.CUT_COPPER_SLAB, Blocks.CHISELED_COPPER, Blocks.COPPER_GRATE, Blocks.COPPER_BULB,
                Blocks.WAXED_COPPER_BLOCK, Blocks.WAXED_CUT_COPPER, Blocks.WAXED_CUT_COPPER_STAIRS, Blocks.WAXED_CUT_COPPER_SLAB, Blocks.WAXED_CHISELED_COPPER, Blocks.WAXED_COPPER_GRATE, Blocks.WAXED_COPPER_BULB);
        this.tag(CBlockTags.TRUMPET_EXPOSED_NOTE_BLOCKS).add(Blocks.EXPOSED_COPPER, Blocks.EXPOSED_CUT_COPPER, Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.EXPOSED_CHISELED_COPPER, Blocks.EXPOSED_COPPER_GRATE, Blocks.EXPOSED_COPPER_BULB,
                Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, Blocks.WAXED_EXPOSED_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER, Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS, Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, Blocks.WAXED_EXPOSED_CHISELED_COPPER, Blocks.WAXED_EXPOSED_COPPER_GRATE, Blocks.WAXED_EXPOSED_COPPER_BULB);
        this.tag(CBlockTags.TRUMPET_WEATHERED_NOTE_BLOCKS).add(Blocks.WEATHERED_COPPER, Blocks.WEATHERED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.WEATHERED_CHISELED_COPPER, Blocks.WEATHERED_COPPER_GRATE, Blocks.WEATHERED_COPPER_BULB,
                Blocks.WAXED_WEATHERED_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER, Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS, Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, Blocks.WAXED_WEATHERED_CHISELED_COPPER, Blocks.WAXED_WEATHERED_COPPER_GRATE, Blocks.WAXED_WEATHERED_COPPER_BULB);
        this.tag(CBlockTags.TRUMPET_OXIDIZED_NOTE_BLOCKS).add(Blocks.OXIDIZED_COPPER, Blocks.OXIDIZED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CHISELED_COPPER, Blocks.OXIDIZED_COPPER_GRATE, Blocks.OXIDIZED_COPPER_BULB,
                Blocks.WAXED_OXIDIZED_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER, Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS, Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, Blocks.WAXED_OXIDIZED_CHISELED_COPPER, Blocks.WAXED_OXIDIZED_COPPER_GRATE, Blocks.WAXED_OXIDIZED_COPPER_BULB);
        this.tag(CBlockTags.STEEL_DRUM_NOTE_BLOCKS).add(ALUMINUM_BLOCK.get(), CUT_ALUMINUM.get(), CUT_ALUMINUM_STAIRS.get(), CUT_ALUMINUM_SLAB.get(), SHEET_METAL.get(), ALUMINUM_GRATE.get());
        this.tag(CBlockTags.SLAP_BASE_NOTE_BLOCKS).add(NICKEL_BLOCK.get());
    }
}
