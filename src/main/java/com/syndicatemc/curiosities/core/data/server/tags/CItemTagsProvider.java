package com.syndicatemc.curiosities.core.data.server.tags;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.tags.CBlockTags;
import com.syndicatemc.curiosities.core.other.tags.CItemTags;
import com.syndicatemc.curiosities.core.registry.CItems;
import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.syndicatemc.curiosities.core.registry.CItems.*;
import static com.syndicatemc.curiosities.core.registry.CBlocks.*;

public class CItemTagsProvider extends BlueprintItemTagsProvider {
    public CItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
        super(Curiosities.MOD_ID, output, provider, lookup, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.copy(CBlockTags.ORES_ALUMINUM, CItemTags.ORES_ALUMINUM);
        this.copy(CBlockTags.ORES_NICKEL, CItemTags.ORES_NICKEL);
        this.copy(CBlockTags.STORAGE_BLOCKS_ALUMINUM, CItemTags.STORAGE_BLOCKS_ALUMINUM);
        this.copy(CBlockTags.STORAGE_BLOCKS_NICKEL, CItemTags.STORAGE_BLOCKS_NICKEL);
        this.copy(CBlockTags.STORAGE_BLOCKS_INVAR, CItemTags.STORAGE_BLOCKS_INVAR);

        this.tag(Tags.Items.INGOTS).add(ALUMINUM_INGOT.get(), NICKEL_INGOT.get(), INVAR_INGOT.get());
        this.tag(CItemTags.INGOTS_ALUMINUM).add(ALUMINUM_INGOT.get());
        this.tag(CItemTags.INGOTS_NICKEL).add(NICKEL_INGOT.get());
        this.tag(CItemTags.INGOTS_INVAR).add(INVAR_INGOT.get());

        this.tag(Tags.Items.NUGGETS).add(ALUMINUM_NUGGET.get(), NICKEL_NUGGET.get(), INVAR_NUGGET.get());
        this.tag(CItemTags.NUGGETS_ALUMINUM).add(ALUMINUM_NUGGET.get());
        this.tag(CItemTags.NUGGETS_NICKEL).add(NICKEL_NUGGET.get());
        this.tag(CItemTags.NUGGETS_INVAR).add(INVAR_NUGGET.get());

        this.tag(Tags.Items.RAW_MATERIALS).add(RAW_ALUMINUM.get(), RAW_NICKEL.get());
        this.tag(CItemTags.RAW_MATS_ALUMINUM).add(RAW_ALUMINUM.get());
        this.tag(CItemTags.RAW_MATS_NICKEL).add(RAW_NICKEL.get());

        this.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(NICKEL_INGOT.get(), INVAR_INGOT.get());
        this.tag(ItemTags.TRIM_MATERIALS).add(ALUMINUM_INGOT.get(), NICKEL_INGOT.get(), INVAR_INGOT.get());

        this.tag(CItemTags.ALUMINUM_TOOLS).add(ALUMINUM_SWORD.get(), ALUMINUM_SHOVEL.get(), ALUMINUM_PICKAXE.get(), ALUMINUM_AXE.get(), ALUMINUM_HOE.get(), ALUMINUM_KNIFE.get());
        this.tag(CItemTags.INVAR_TOOLS).add(INVAR_SWORD.get(), INVAR_SHOVEL.get(), INVAR_PICKAXE.get(), INVAR_AXE.get(), INVAR_HOE.get(), INVAR_KNIFE.get());
        this.tag(CItemTags.TOPAZ_TOOLS).add(TOPAZ_SWORD.get(), TOPAZ_SHOVEL.get(), TOPAZ_PICKAXE.get(), TOPAZ_AXE.get(), TOPAZ_HOE.get());

        this.tag(ItemTags.HEAD_ARMOR).add(ALUMINUM_HELMET.get(), INVAR_HELMET.get());
        this.tag(ItemTags.CHEST_ARMOR).add(ALUMINUM_CHESTPLATE.get(), INVAR_CHESTPLATE.get());
        this.tag(ItemTags.LEG_ARMOR).add(ALUMINUM_LEGGINGS.get(), INVAR_LEGGINGS.get());
        this.tag(ItemTags.FOOT_ARMOR).add(ALUMINUM_BOOTS.get(), INVAR_BOOTS.get(), HEAVY_BOOTS.get());

        this.tag(Tags.Items.MELEE_WEAPON_TOOLS).add(ALUMINUM_SWORD.get(), ALUMINUM_AXE.get(), ALUMINUM_KNIFE.get(), INVAR_SWORD.get(), INVAR_AXE.get(), INVAR_KNIFE.get(), TOPAZ_SWORD.get(), TOPAZ_AXE.get());
        this.tag(Tags.Items.MINING_TOOL_TOOLS).add(
                ALUMINUM_SHOVEL.get(), ALUMINUM_PICKAXE.get(), ALUMINUM_AXE.get(), ALUMINUM_HOE.get(), ALUMINUM_KNIFE.get(),
                INVAR_SHOVEL.get(), INVAR_PICKAXE.get(), INVAR_AXE.get(), INVAR_HOE.get(), INVAR_KNIFE.get(),
                TOPAZ_SHOVEL.get(), TOPAZ_PICKAXE.get(), TOPAZ_AXE.get(), TOPAZ_HOE.get()
        );

        this.tag(ItemTags.AXES).add(ALUMINUM_AXE.get(), INVAR_AXE.get());
        this.tag(ItemTags.PICKAXES).add(ALUMINUM_PICKAXE.get(), INVAR_PICKAXE.get());
        this.tag(ItemTags.SWORDS).add(ALUMINUM_SWORD.get(), INVAR_SWORD.get());
        this.tag(ItemTags.SHOVELS).add(ALUMINUM_SHOVEL.get(), INVAR_SHOVEL.get());
        this.tag(ItemTags.HOES).add(ALUMINUM_HOE.get(), INVAR_HOE.get());
        this.tag(CItemTags.KNIFE).add(ALUMINUM_KNIFE.get(), INVAR_KNIFE.get());

        this.tag(ItemTags.STAIRS).add(
                SMOOTH_STONE_BRICK_STAIRS.asItem(),

                SMOOTH_STONE_STAIRS.asItem(),
                CUT_SANDSTONE_STAIRS.asItem(), CUT_RED_SANDSTONE_STAIRS.asItem(),
                QUARTZ_BRICK_STAIRS.asItem()
        );

        this.tag(ItemTags.SLABS).add(
                SMOOTH_STONE_BRICK_SLAB.asItem(),

                QUARTZ_BRICK_SLAB.asItem()
        );

        this.tag(ItemTags.WALLS).add(
                SMOOTH_STONE_BRICK_WALL.asItem(),

                STONE_WALL.asItem(), SMOOTH_STONE_WALL.asItem(), POLISHED_GRANITE_WALL.asItem(), POLISHED_DIORITE_WALL.asItem(), POLISHED_ANDESITE_WALL.asItem(),
                CUT_SANDSTONE_WALL.asItem(), CUT_RED_SANDSTONE_WALL.asItem(),
                PRISMARINE_BRICK_WALL.asItem(), DARK_PRISMARINE_WALL.asItem(),
                QUARTZ_WALL.asItem(), QUARTZ_BRICK_WALL.asItem(),
                PURPUR_WALL.asItem()
        );
    }
}
