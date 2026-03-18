package com.syndicatemc.curiosities.core.data.server;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CFeatures;
import com.syndicatemc.curiosities.core.registry.datapack.CBiomeModifiers;
import com.syndicatemc.curiosities.core.registry.datapack.CDamageTypes;
import com.syndicatemc.curiosities.core.registry.datapack.CStructureRepaletters;
import com.syndicatemc.curiosities.core.registry.datapack.CTrimMaterials;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.core.HolderLookup.Provider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CDataPackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, CFeatures.CConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, CFeatures.CPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CBiomeModifiers::bootstrap)
            .add(Registries.TRIM_MATERIAL, CTrimMaterials::bootstrap)
            .add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, CStructureRepaletters::bootstrap)
            .add(Registries.DAMAGE_TYPE, CDamageTypes::bootstrap);

    public CDataPackProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(output, provider, BUILDER, CStructureRepaletters::applyConditions, Set.of(Curiosities.MOD_ID));
    }
}
