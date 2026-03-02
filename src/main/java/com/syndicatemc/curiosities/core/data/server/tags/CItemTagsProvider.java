package com.syndicatemc.curiosities.core.data.server.tags;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.tags.CBlockTags;
import com.syndicatemc.curiosities.core.other.tags.CItemTags;
import com.syndicatemc.curiosities.core.registry.CItems;
import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

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

        this.tag(CItemTags.INGOTS_ALUMINUM).add(CItems.ALUMINUM_INGOT.get());
        this.tag(CItemTags.INGOTS_NICKEL).add(CItems.NICKEL_INGOT.get());
        this.tag(CItemTags.INGOTS_INVAR).add(CItems.INVAR_INGOT.get());

        this.tag(CItemTags.NUGGETS_ALUMINUM).add(CItems.ALUMINUM_NUGGET.get());
        this.tag(CItemTags.NUGGETS_NICKEL).add(CItems.NICKEL_NUGGET.get());
        this.tag(CItemTags.NUGGETS_INVAR).add(CItems.INVAR_NUGGET.get());

        this.tag(ItemTags.TRIM_MATERIALS).add(CItems.ALUMINUM_INGOT.get(), CItems.NICKEL_INGOT.get(), CItems.INVAR_INGOT.get());

        this.tag(CItemTags.ALUMINUM_TOOLS).add(CItems.ALUMINUM_SWORD.get(), CItems.ALUMINUM_SHOVEL.get(), CItems.ALUMINUM_PICKAXE.get(), CItems.ALUMINUM_AXE.get(), CItems.ALUMINUM_HOE.get(), CItems.ALUMINUM_KNIFE.get());
        this.tag(CItemTags.INVAR_TOOLS).add(CItems.INVAR_SWORD.get(), CItems.INVAR_SHOVEL.get(), CItems.INVAR_PICKAXE.get(), CItems.INVAR_AXE.get(), CItems.INVAR_HOE.get(), CItems.INVAR_KNIFE.get());

        this.tag(ItemTags.HEAD_ARMOR).add(CItems.ALUMINUM_HELMET.get(), CItems.INVAR_HELMET.get());
        this.tag(ItemTags.CHEST_ARMOR).add(CItems.ALUMINUM_CHESTPLATE.get(), CItems.INVAR_CHESTPLATE.get());
        this.tag(ItemTags.LEG_ARMOR).add(CItems.ALUMINUM_LEGGINGS.get(), CItems.INVAR_LEGGINGS.get());
        this.tag(ItemTags.FOOT_ARMOR).add(CItems.ALUMINUM_BOOTS.get(), CItems.INVAR_BOOTS.get(), CItems.HEAVY_BOOTS.get());

        this.tag(Tags.Items.MELEE_WEAPON_TOOLS).add(CItems.ALUMINUM_SWORD.get(), CItems.ALUMINUM_AXE.get(), CItems.ALUMINUM_KNIFE.get(), CItems.INVAR_SWORD.get(), CItems.INVAR_AXE.get(), CItems.INVAR_KNIFE.get());

        this.tag(ItemTags.AXES).add(CItems.ALUMINUM_AXE.get(), CItems.INVAR_AXE.get());
        this.tag(ItemTags.PICKAXES).add(CItems.ALUMINUM_PICKAXE.get(), CItems.INVAR_PICKAXE.get());
        this.tag(ItemTags.SWORDS).add(CItems.ALUMINUM_SWORD.get(), CItems.INVAR_SWORD.get());
        this.tag(ItemTags.SHOVELS).add(CItems.ALUMINUM_SHOVEL.get(), CItems.INVAR_SHOVEL.get());
        this.tag(ItemTags.HOES).add(CItems.ALUMINUM_HOE.get(), CItems.INVAR_HOE.get());
        this.tag(CItemTags.KNIFE).add(CItems.ALUMINUM_KNIFE.get(), CItems.INVAR_KNIFE.get());
    }
}
