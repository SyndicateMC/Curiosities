package com.syndicatemc.curiosities.core.data.client;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CBlocks;
import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.syndicatemc.curiosities.core.registry.CItems.*;

public class CItemModelProvider extends BlueprintItemModelProvider {
    public CItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Curiosities.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        this.generatedItem(
                RAW_ALUMINUM, ALUMINUM_INGOT, ALUMINUM_NUGGET, RAW_NICKEL, NICKEL_INGOT, NICKEL_NUGGET, INVAR_INGOT, INVAR_NUGGET, TOPAZ,
                ALUMINUM_UPGRADE_SMITHING_TEMPLATE,

                CBlocks.ALUMINUM_DOOR, CBlocks.CAGE_LIGHT, CBlocks.SOUL_CAGE_LIGHT, CBlocks.BIG_CHAIN, CBlocks.HEAVY_LANTERN, CBlocks.HEAVY_SOUL_LANTERN
        );
        this.handheldItem(
                ALUMINUM_SWORD, ALUMINUM_SHOVEL, ALUMINUM_PICKAXE, ALUMINUM_AXE, ALUMINUM_HOE, ALUMINUM_KNIFE,
                INVAR_SWORD, INVAR_SHOVEL, INVAR_PICKAXE, INVAR_AXE, INVAR_HOE, INVAR_KNIFE,
                TOPAZ_SWORD, TOPAZ_SHOVEL, TOPAZ_PICKAXE, TOPAZ_AXE, TOPAZ_HOE
        );
        this.trimmableArmorItem(
                ALUMINUM_HELMET, ALUMINUM_CHESTPLATE, ALUMINUM_LEGGINGS, ALUMINUM_BOOTS,
                INVAR_HELMET, INVAR_CHESTPLATE, INVAR_LEGGINGS, INVAR_BOOTS,
                HEAVY_BOOTS
        );
    }
}
