package com.syndicatemc.curiosities.core.data.client;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.datapack.CTrimMaterials;
import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.concurrent.CompletableFuture;

public class CSpriteSourceProvider extends SpriteSourceProvider {
    public CSpriteSourceProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, ExistingFileHelper helper) {
        super(output, lookup, Curiosities.MOD_ID, helper);
    }

    @Override
    protected void gather() {
        this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS).addSource(BlueprintTrims.materialPatternPermutations(CTrimMaterials.ALUMINUM, CTrimMaterials.NICKEL, CTrimMaterials.INVAR));
        this.atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(BlueprintTrims.materialPermutationsForItemLayers(CTrimMaterials.ALUMINUM, CTrimMaterials.NICKEL, CTrimMaterials.INVAR));
    }
}
