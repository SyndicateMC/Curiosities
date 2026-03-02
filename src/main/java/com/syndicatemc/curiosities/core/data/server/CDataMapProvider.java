package com.syndicatemc.curiosities.core.data.server;

import com.syndicatemc.curiosities.core.registry.CBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class CDataMapProvider extends DataMapProvider {
    public CDataMapProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void gather(Provider provider) {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(CBlocks.FANCIED_OAK_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_SPRUCE_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_BIRCH_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_JUNGLE_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_ACACIA_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_DARK_OAK_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_MANGROVE_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_CHERRY_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_BAMBOO_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_CRIMSON_PLANKS.getId(), new FurnaceFuel(300), false)
                .add(CBlocks.FANCIED_WARPED_PLANKS.getId(), new FurnaceFuel(300), false);
    }
}
