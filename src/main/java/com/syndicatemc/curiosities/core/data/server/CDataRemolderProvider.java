package com.syndicatemc.curiosities.core.data.server;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CItems;
import com.teamabnormals.blueprint.common.remolder.data.RemolderProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.blueprint.common.remolder.RemolderTypes.*;
import static com.teamabnormals.blueprint.common.remolder.util.LootRemolders.addEntry;

public class CDataRemolderProvider extends RemolderProvider {
    public CDataRemolderProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(Curiosities.MOD_ID, PackOutput.Target.DATA_PACK, output, provider);
    }

    @Override
    protected void registerEntries(Provider provider) {
        this.entry("village_toolsmith").path("loot_table/chests/village/village_toolsmith").remolder(sequence(
                addEntry(0, simpleLootEntry(CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE.get(), 3))
        ));
        this.entry("village_weaponsmith").path("loot_table/chests/village/village_weaponsmith").remolder(sequence(
                addEntry(0, simpleLootEntry(CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE.get(), 4))
        ));

        this.entry("village_armorer").path("loot_table/chests/village/village_armorer").remolder(sequence(
                addEntry(0, simpleLootEntry(CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE.get(), 2))
        ));
    }

    private static LootPoolEntryContainer simpleLootEntry(ItemLike item, int weight) {
        return LootItem.lootTableItem(item).setWeight(weight).build();
    }

    private static LootPoolEntryContainer simpleLootEntry(ItemLike item, int weight, int min, int max) {
        return LootItem.lootTableItem(item).setWeight(weight).apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max))).build();
    }
}
