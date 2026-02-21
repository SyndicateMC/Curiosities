package com.syndicatemc.curiosities.core.data.server.tags;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.datapack.CTrimMaterials;
import com.teamabnormals.blueprint.core.other.tags.BlueprintTrimMaterialTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class CTrimMaterialTagsProvider extends TagsProvider<TrimMaterial> {
    public CTrimMaterialTagsProvider(PackOutput output, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
        super(output, Registries.TRIM_MATERIAL, provider, Curiosities.MOD_ID, helper);
    }

    @Override
    public void addTags(Provider provider) {
        //this.tag(BlueprintTrimMaterialTags.GENERATES_OVERRIDES).add(CTrimMaterials.ALUMINUM, CTrimMaterials.NICKEL, CTrimMaterials.INVAR);
    }
}
