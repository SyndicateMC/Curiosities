package com.syndicatemc.curiosities.core.other;

import com.syndicatemc.curiosities.core.registry.CBlocks;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.syndicatemc.curiosities.core.registry.CBlocks.*;

public class CBlockFamilies {
    public static final BlockFamily CUT_ALUMINUM_BLOCK_FAMILY = new BlockFamily.Builder(CUT_ALUMINUM.get())
            .chiseled(SHEET_METAL.get()).slab(CUT_ALUMINUM_SLAB.get()).stairs(CUT_ALUMINUM_STAIRS.get())
            .recipeGroupPrefix("metal").recipeUnlockedBy("has_aluminum").getFamily();
    public static final BlockFamily SMOOTH_STONE_BRICKS_BLOCK_FAMILY = new BlockFamily.Builder(SMOOTH_STONE_BRICKS.get())
            .slab(SMOOTH_STONE_BRICK_SLAB.get()).stairs(SMOOTH_STONE_BRICK_STAIRS.get())
            .recipeGroupPrefix("stone").recipeUnlockedBy("has_smooth_stone").getFamily();
}
