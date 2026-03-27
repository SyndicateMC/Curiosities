package com.syndicatemc.curiosities.core.data.server;

import com.google.common.collect.ImmutableList;
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.tag.MNDTags;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.CBlockFamilies;
import com.syndicatemc.curiosities.core.other.CConstants;
import com.syndicatemc.curiosities.core.other.tags.CItemTags;
import com.syndicatemc.curiosities.core.registry.CBlocks;
import com.syndicatemc.curiosities.core.registry.CItems;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericItemTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.environmental.core.other.tags.EnvironmentalItemTags;
import com.teamabnormals.environmental.core.registry.EnvironmentalBlocks;
import com.teamabnormals.upgrade_aquatic.core.other.tags.UAItemTags;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;
import com.teamabnormals.woodworks.core.data.server.WoodworksRecipeProvider;
import gardensofthedead.registry.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.woodworks.core.other.WoodworksConditions.SAWMILL_ENABLED;
import static net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId;
import static net.minecraft.data.recipes.RecipeCategory.*;

public class CRecipeProvider extends BlueprintRecipeProvider {
    private static final List<ItemLike> ALUMINUM_SMELTABLES = ImmutableList.of(CBlocks.ALUMINUM_ORE, CBlocks.DEEPSLATE_ALUMINUM_ORE, CItems.RAW_ALUMINUM);
    private static final List<ItemLike> NICKEL_SMELTABLES = ImmutableList.of(CBlocks.NICKEL_ORE, CBlocks.DEEPSLATE_NICKEL_ORE, CItems.RAW_NICKEL);

    public CRecipeProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(Curiosities.MOD_ID, output, provider);
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        storageRecipesWithCustomUnpacking(output, MISC, CItems.ALUMINUM_INGOT, BUILDING_BLOCKS, CBlocks.ALUMINUM_BLOCK, "aluminum_ingot_from_block", "aluminum_ingot");
        storageRecipesWithCustomPacking(output, MISC, CItems.ALUMINUM_NUGGET, MISC, CItems.ALUMINUM_INGOT, "aluminum_ingot_from_nuggets", "aluminum_ingot");
        storageRecipesWithCustomUnpacking(output, MISC, CItems.NICKEL_INGOT, BUILDING_BLOCKS, CBlocks.NICKEL_BLOCK, "nickel_ingot_from_block", "nickel_ingot");
        storageRecipesWithCustomPacking(output, MISC, CItems.NICKEL_NUGGET, MISC, CItems.NICKEL_INGOT, "nickel_ingot_from_nuggets", "nickel_ingot");
        storageRecipesWithCustomUnpacking(output, MISC, CItems.INVAR_INGOT, BUILDING_BLOCKS, CBlocks.INVAR_BLOCK, "invar_ingot_from_block", "invar_ingot");
        storageRecipesWithCustomPacking(output, MISC, CItems.INVAR_NUGGET, MISC, CItems.INVAR_INGOT, "invar_ingot_from_nuggets", "invar_ingot");

        storageRecipes(output, MISC, CItems.RAW_ALUMINUM, BUILDING_BLOCKS, CBlocks.RAW_ALUMINUM_BLOCK);
        storageRecipes(output, MISC, CItems.RAW_NICKEL, BUILDING_BLOCKS, CBlocks.RAW_NICKEL_BLOCK);

        oreRecipesAlt(output, ALUMINUM_SMELTABLES, MISC, CItems.ALUMINUM_NUGGET, CItems.ALUMINUM_INGOT, 3, 2,0.8F, 300, "aluminum");
        oreRecipesAlt(output, NICKEL_SMELTABLES, MISC, CItems.NICKEL_INGOT, 1, 0.8F, 300, "nickel_ingot");

        ShapelessRecipeBuilder.shapeless(MISC, CItems.INVAR_INGOT)
                .requires(Items.NETHERITE_SCRAP, 3)
                .requires(Ingredient.of(Tags.Items.INGOTS_IRON), 3)
                .requires(Ingredient.of(CItemTags.INGOTS_NICKEL), 3)
                .group("invar_ingot")
                .unlockedBy("has_netherite_scrap", has(Items.NETHERITE_SCRAP)).save(output);

        ShapedRecipeBuilder.shaped(MISC, CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE, 2)
                .define('A', CItemTags.NUGGETS_ALUMINUM)
                .define('L', Tags.Items.LEATHERS)
                .define('D', Blocks.POLISHED_DEEPSLATE)
                .define('T', CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE)
                .pattern("ATA").pattern("ADA").pattern("ALA")
                .unlockedBy("has_aluminum_upgrade", has(CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE)).save(output);

        ShapedRecipeBuilder.shaped(REDSTONE, CBlocks.REDSTONE_DIODE, 4)
                .define('N', CItemTags.INGOTS_NICKEL)
                .define('B', Items.STONE_BUTTON)
                .define('R', Items.REDSTONE)
                .pattern("RRB").pattern("NNN")
                .unlockedBy("has_redstone", has(Items.REDSTONE)).unlockedBy("has_nickel", has(CItemTags.INGOTS_NICKEL))
                .save(output);
        ShapedRecipeBuilder.shaped(REDSTONE, CBlocks.REDSTONE_FUSE)
                .define('N', CItemTags.INGOTS_NICKEL)
                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                .define('R', Items.REDSTONE)
                .define('A', CItemTags.NUGGETS_ALUMINUM)
                .pattern(" R ").pattern("AGA").pattern("NNN")
                .unlockedBy("has_redstone", has(Items.REDSTONE)).unlockedBy("has_nickel", has(CItemTags.INGOTS_NICKEL)).unlockedBy("has_aluminum", has(CItemTags.INGOTS_ALUMINUM))
                .save(output);

        cageLightRecipe(CBlocks.CAGE_LIGHT, Items.TORCH).save(output);
        cageLightRecipe(CBlocks.SOUL_CAGE_LIGHT, Items.SOUL_TORCH).save(output);
        heavyLanternRecipe(CBlocks.HEAVY_LANTERN, Items.TORCH).save(output);
        heavyLanternRecipe(CBlocks.HEAVY_SOUL_LANTERN, Items.SOUL_TORCH).save(output);
        tikiTorchRecipe(CBlocks.TIKI_TORCH, Items.TORCH).save(output);
        tikiTorchRecipe(CBlocks.SOUL_TIKI_TORCH, Items.SOUL_TORCH).save(output);
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, CBlocks.TILE_LIGHT, 4)
                .define('G', Tags.Items.GLASS_BLOCKS_COLORLESS)
                .define('I', CItemTags.INGOTS_ALUMINUM)
                .define('S', Items.GLOWSTONE)
                .pattern("GIG").pattern("GSG").pattern("GIG").unlockedBy("has_aluminum", has(CItemTags.INGOTS_ALUMINUM)).unlockedBy("has_glass", has(Tags.Items.GLASS_BLOCKS_COLORLESS)).unlockedBy("has_glowstone", has(Items.GLOWSTONE_DUST))
                .save(output);
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, CBlocks.BIG_CHAIN, 4)
                .define('N', CItemTags.NUGGETS_INVAR)
                .define('I', CItemTags.INGOTS_INVAR)
                .pattern("N").pattern("I").pattern("N").unlockedBy("has_invar", has(CItemTags.INGOTS_INVAR))
                .save(output);

        ShapedRecipeBuilder.shaped(REDSTONE, CBlocks.WEIGHT_1S, 4)
                .define('N', CItemTags.NUGGETS_INVAR)
                .define('R', Items.IRON_INGOT)
                .pattern("R R").pattern(" N ").pattern("R R").unlockedBy("has_invar", has(CItemTags.INGOTS_INVAR))
                .save(output);
        ShapedRecipeBuilder.shaped(REDSTONE, CBlocks.WEIGHT_5S, 4)
                .define('N', CItemTags.NUGGETS_INVAR)
                .define('R', Items.IRON_INGOT)
                .pattern("RNR").pattern("NNN").pattern("RNR").unlockedBy("has_invar", has(CItemTags.INGOTS_INVAR))
                .save(output);
        ShapedRecipeBuilder.shaped(REDSTONE, CBlocks.WEIGHT_20S, 4)
                .define('N', CItemTags.NUGGETS_INVAR)
                .define('I', CItemTags.INGOTS_INVAR)
                .define('R', Items.IRON_INGOT)
                .pattern("RNR").pattern("I I").pattern("RNR").unlockedBy("has_invar", has(CItemTags.INGOTS_INVAR))
                .save(output);

        aluminumSmithingRecipe(output, Items.LEATHER_HELMET, CItems.ALUMINUM_HELMET.get());
        aluminumSmithingRecipe(output, Items.LEATHER_CHESTPLATE, CItems.ALUMINUM_CHESTPLATE.get());
        aluminumSmithingRecipe(output, Items.LEATHER_LEGGINGS, CItems.ALUMINUM_LEGGINGS.get());
        aluminumSmithingRecipe(output, Items.LEATHER_BOOTS, CItems.ALUMINUM_BOOTS.get());
        aluminumSmithingRecipe(output, Items.IRON_SWORD, CItems.ALUMINUM_SWORD.get());
        aluminumSmithingRecipe(output, Items.IRON_SHOVEL, CItems.ALUMINUM_SHOVEL.get());
        aluminumSmithingRecipe(output, Items.IRON_PICKAXE, CItems.ALUMINUM_PICKAXE.get());
        aluminumSmithingRecipe(output, Items.IRON_AXE, CItems.ALUMINUM_AXE.get());
        aluminumSmithingRecipe(output, Items.IRON_HOE, CItems.ALUMINUM_HOE.get());

        invarSmithingRecipe(output, Items.DIAMOND_HELMET, CItems.INVAR_HELMET.get());
        invarSmithingRecipe(output, Items.DIAMOND_CHESTPLATE, CItems.INVAR_CHESTPLATE.get());
        invarSmithingRecipe(output, Items.DIAMOND_LEGGINGS, CItems.INVAR_LEGGINGS.get());
        invarSmithingRecipe(output, Items.DIAMOND_BOOTS, CItems.INVAR_BOOTS.get());
        invarSmithingRecipe(output, Items.DIAMOND_SWORD, CItems.INVAR_SWORD.get());
        invarSmithingRecipe(output, Items.DIAMOND_SHOVEL, CItems.INVAR_SHOVEL.get());
        invarSmithingRecipe(output, Items.DIAMOND_PICKAXE, CItems.INVAR_PICKAXE.get());
        invarSmithingRecipe(output, Items.DIAMOND_AXE, CItems.INVAR_AXE.get());
        invarSmithingRecipe(output, Items.DIAMOND_HOE, CItems.INVAR_HOE.get());

        ShapedRecipeBuilder.shaped(COMBAT, CItems.HEAVY_BOOTS)
                .define('C', Items.HEAVY_CORE)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.BREEZE_ROD)
                .pattern(" C ").pattern("R R").pattern("I I").unlockedBy("has_heavy_core", has(Items.HEAVY_CORE))
                .save(output);

        doorBuilder(CBlocks.ALUMINUM_DOOR, Ingredient.of(CItemTags.INGOTS_ALUMINUM)).unlockedBy("has_aluminum_ingot", has(CItemTags.INGOTS_ALUMINUM)).save(output);
        trapdoorBuilder(CBlocks.ALUMINUM_TRAPDOOR, Ingredient.of(CItemTags.INGOTS_ALUMINUM)).unlockedBy("has_aluminum_ingot", has(CItemTags.INGOTS_ALUMINUM)).save(output);
        cutBuilder(BUILDING_BLOCKS, CBlocks.CUT_ALUMINUM, Ingredient.of(CBlocks.ALUMINUM_BLOCK)).unlockedBy("has_aluminum_block", has(CBlocks.ALUMINUM_BLOCK)).save(output);
        //chiseled(output, BUILDING_BLOCKS, CBlocks.SHEET_METAL, CBlocks.CUT_ALUMINUM_SLAB);
        grate(output, CBlocks.ALUMINUM_GRATE.get(), CBlocks.ALUMINUM_BLOCK.get());
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SHEET_METAL, CBlocks.ALUMINUM_BLOCK, 4);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.CUT_ALUMINUM, CBlocks.ALUMINUM_BLOCK, 4);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.CUT_ALUMINUM_STAIRS, CBlocks.CUT_ALUMINUM);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.CUT_ALUMINUM_SLAB, CBlocks.CUT_ALUMINUM, 2);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SHEET_METAL, CBlocks.CUT_ALUMINUM);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.ALUMINUM_GRATE, CBlocks.ALUMINUM_BLOCK, 4);
        barsRecipe(output, CItemTags.INGOTS_ALUMINUM, CBlocks.ALUMINUM_BARS, "aluminum_ingot");
        generateRecipes(output, CBlockFamilies.CUT_ALUMINUM_BLOCK_FAMILY);

        barsRecipe(output, CItemTags.INGOTS_NICKEL, CBlocks.NICKEL_BARS, "nickel_ingot");

        ashlarRecipes(output, CBlocks.STONE_ASHLAR, Items.STONE, Items.STONE_BRICKS);
        ashlarRecipes(output, CBlocks.DEEPSLATE_ASHLAR, Items.COBBLED_DEEPSLATE, Items.DEEPSLATE_BRICKS);
        ashlarRecipes(output, CBlocks.TUFF_ASHLAR, Items.TUFF, Items.TUFF_BRICKS);
        ashlarRecipes(output, CBlocks.POLISHED_BLACKSTONE_ASHLAR, Items.POLISHED_BLACKSTONE, Items.POLISHED_BLACKSTONE_BRICKS);
        ashlarRecipes(output, CBlocks.END_STONE_ASHLAR, Items.END_STONE, Items.END_STONE_BRICKS);

        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICKS, Items.SMOOTH_STONE);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_SLAB, Items.SMOOTH_STONE, 2);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_STAIRS, Items.SMOOTH_STONE);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_SLAB, CBlocks.SMOOTH_STONE_BRICKS, 2);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_STAIRS, CBlocks.SMOOTH_STONE_BRICKS);
        ashlarRecipes(output, CBlocks.SMOOTH_STONE_ASHLAR, Items.SMOOTH_STONE, CBlocks.SMOOTH_STONE_BRICKS);
        wallStoneRecipes(output, CBlocks.SMOOTH_STONE_BRICK_WALL, CBlocks.SMOOTH_STONE_BRICKS);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_WALL, Items.SMOOTH_STONE);
        cutBuilder(BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICKS, Ingredient.of(Items.SMOOTH_STONE)).unlockedBy("has_smooth_stone", has(Items.SMOOTH_STONE)).save(output);
        generateRecipes(output, CBlockFamilies.SMOOTH_STONE_BRICKS_BLOCK_FAMILY);

        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, CBlocks.LATERITE, 2)
                .requires(Items.DIRT)
                .requires(Items.CLAY)
                .requires(Items.IRON_NUGGET)
                .requires(CItems.ALUMINUM_NUGGET)
                .unlockedBy("has_aluminum", has(CItems.ALUMINUM_INGOT)).save(output);
        cutBuilder(BUILDING_BLOCKS, CBlocks.LATERITE_BRICKS, Ingredient.of(CBlocks.LATERITE)).unlockedBy("has_laterite", has(CBlocks.LATERITE)).save(output);
        generateRecipes(output, CBlockFamilies.LATERITE_BRICKS_BLOCK_FAMILY);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_BRICK_SLAB, CBlocks.LATERITE_BRICKS, 2);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_BRICK_STAIRS, CBlocks.LATERITE_BRICKS);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_BRICK_WALL, CBlocks.LATERITE_BRICKS);
        generateRecipes(output, CBlockFamilies.LATERITE_TILES_BLOCK_FAMILY);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_TILE_SLAB, CBlocks.LATERITE_BRICKS, 2);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_TILE_STAIRS, CBlocks.LATERITE_BRICKS);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_TILE_WALL, CBlocks.LATERITE_BRICKS);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_TILE_SLAB, CBlocks.LATERITE_TILES, 2);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_TILE_STAIRS, CBlocks.LATERITE_TILES);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.LATERITE_TILE_WALL, CBlocks.LATERITE_TILES);

//        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, CBlocks.SCULKY_COBBLED_DEEPSLATE)
//                .requires(Items.COBBLED_DEEPSLATE)
//                .requires(Items.SCULK)
//                .unlockedBy("has_sculk", has(Items.SCULK)).save(output);
//        ShapelessRecipeBuilder.shapeless(BUILDING_BLOCKS, CBlocks.SCULKY_COBBLED_DEEPSLATE)
//                .requires(Items.COBBLED_DEEPSLATE)
//                .requires(Items.SCULK)
//                .unlockedBy("has_sculk", has(Items.SCULK)).save(output, getDefaultRecipeId(CBlocks.SCULKY_COBBLED_DEEPSLATE) + "_alt");
//        generateRecipes(output, CBlockFamilies.SCULKY_COBBLED_DEEPSLATE_BLOCK_FAMILY);
//        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SCULKY_COBBLED_DEEPSLATE_SLAB, CBlocks.SCULKY_COBBLED_DEEPSLATE, 2);
//        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SCULKY_COBBLED_DEEPSLATE_STAIRS, CBlocks.SCULKY_COBBLED_DEEPSLATE);
//        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SCULKY_COBBLED_DEEPSLATE_WALL, CBlocks.SCULKY_COBBLED_DEEPSLATE);

        fanciedPlanksRecipe(output, CBlocks.FANCIED_OAK_PLANKS, Items.OAK_SLAB, Items.OAK_PLANKS, ItemTags.OAK_LOGS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_SPRUCE_PLANKS, Items.SPRUCE_SLAB, Items.SPRUCE_PLANKS, ItemTags.SPRUCE_LOGS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_BIRCH_PLANKS, Items.BIRCH_SLAB, Items.BIRCH_PLANKS, ItemTags.BIRCH_LOGS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_JUNGLE_PLANKS, Items.JUNGLE_SLAB, Items.JUNGLE_PLANKS, ItemTags.JUNGLE_LOGS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_ACACIA_PLANKS, Items.ACACIA_SLAB, Items.ACACIA_PLANKS, ItemTags.ACACIA_LOGS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_DARK_OAK_PLANKS, Items.DARK_OAK_SLAB, Items.DARK_OAK_PLANKS, ItemTags.DARK_OAK_LOGS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_MANGROVE_PLANKS, Items.MANGROVE_SLAB, Items.MANGROVE_PLANKS, ItemTags.MANGROVE_LOGS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_CHERRY_PLANKS, Items.CHERRY_SLAB, Items.CHERRY_PLANKS, ItemTags.CHERRY_LOGS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_BAMBOO_PLANKS, Items.BAMBOO_SLAB, Items.BAMBOO_PLANKS, ItemTags.BAMBOO_BLOCKS, 2);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_CRIMSON_PLANKS, Items.CRIMSON_SLAB, Items.CRIMSON_PLANKS, ItemTags.CRIMSON_STEMS);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_WARPED_PLANKS, Items.WARPED_SLAB, Items.WARPED_PLANKS, ItemTags.WARPED_STEMS);

        //vanilla compat
        stairsStoneRecipes(output, CBlocks.SMOOTH_STONE_STAIRS, Items.SMOOTH_STONE);
        stairsStoneRecipes(output, CBlocks.CUT_SANDSTONE_STAIRS, Items.CUT_SANDSTONE);
        stairsStoneRecipes(output, CBlocks.CUT_RED_SANDSTONE_STAIRS, Items.CUT_RED_SANDSTONE);
        stairsStoneRecipes(output, CBlocks.QUARTZ_BRICK_STAIRS, Items.QUARTZ_BRICKS);

        slabStoneRecipes(output, CBlocks.QUARTZ_BRICK_SLAB, Items.QUARTZ_BRICKS);

        wallStoneRecipes(output, CBlocks.STONE_WALL, Items.STONE);
        wallStoneRecipes(output, CBlocks.SMOOTH_STONE_WALL, Items.SMOOTH_STONE);
        wallStoneRecipes(output, CBlocks.POLISHED_GRANITE_WALL, Items.POLISHED_GRANITE);
        wallStoneRecipes(output, CBlocks.POLISHED_DIORITE_WALL, Items.POLISHED_DIORITE);
        wallStoneRecipes(output, CBlocks.POLISHED_ANDESITE_WALL, Items.POLISHED_ANDESITE);
        wallStoneRecipes(output, CBlocks.CUT_SANDSTONE_WALL, Items.CUT_SANDSTONE);
        wallStoneRecipes(output, CBlocks.CUT_RED_SANDSTONE_WALL, Items.CUT_RED_SANDSTONE);
        wallStoneRecipes(output, CBlocks.PRISMARINE_BRICK_WALL, Items.PRISMARINE_BRICKS);
        wallStoneRecipes(output, CBlocks.DARK_PRISMARINE_WALL, Items.DARK_PRISMARINE);
        wallStoneRecipes(output, CBlocks.QUARTZ_WALL, Items.QUARTZ_BLOCK);
        wallStoneRecipes(output, CBlocks.QUARTZ_BRICK_WALL, Items.QUARTZ_BRICKS);
        wallStoneRecipes(output, CBlocks.PURPUR_WALL, Items.PURPUR_BLOCK);

        incenseRecipe(output, CBlocks.ACRID_INCENSE, Items.ROTTEN_FLESH, Items.SPIDER_EYE, Items.CRIMSON_ROOTS);
        incenseRecipe(output, CBlocks.BLAND_INCENSE, Items.OXEYE_DAISY, Items.BONE_MEAL, Items.WHITE_TULIP);
        incenseRecipe(output, CBlocks.BRIGHT_INCENSE, Items.SUNFLOWER, Items.HONEYCOMB, Items.DANDELION);
        incenseRecipe(output, CBlocks.FRESH_INCENSE, Items.CORNFLOWER, Items.LAPIS_LAZULI, Items.TUBE_CORAL);
        incenseRecipe(output, CBlocks.SWEET_INCENSE, Items.LILAC, Items.AMETHYST_SHARD, Items.PEONY);
        incenseRecipe(output, CBlocks.VERDANT_INCENSE, Items.FERN, Items.SLIME_BALL, Items.SPRUCE_SAPLING);

        censerRecipe(output, CBlocks.ACRID_CENSER, CBlocks.ACRID_INCENSE);
        censerRecipe(output, CBlocks.BLAND_CENSER, CBlocks.BLAND_INCENSE);
        censerRecipe(output, CBlocks.BRIGHT_CENSER, CBlocks.BRIGHT_INCENSE);
        censerRecipe(output, CBlocks.FRESH_CENSER, CBlocks.FRESH_INCENSE);
        censerRecipe(output, CBlocks.SWEET_CENSER, CBlocks.SWEET_INCENSE);
        censerRecipe(output, CBlocks.VERDANT_CENSER, CBlocks.VERDANT_INCENSE);

        //fd compat
        aluminumSmithingRecipe(output.withConditions(new ModLoadedCondition(CConstants.FARMERS_DELIGHT)), ModItems.IRON_KNIFE.get(), CItems.ALUMINUM_KNIFE.get());
        invarSmithingRecipe(output.withConditions(new ModLoadedCondition(CConstants.FARMERS_DELIGHT)), ModItems.DIAMOND_KNIFE.get(), CItems.INVAR_KNIFE.get());

        //misc bormals compat
        fanciedPlanksRecipe(output, CBlocks.FANCIED_ASPEN_PLANKS, AtmosphericBlocks.ASPEN_SLAB, AtmosphericBlocks.ASPEN_PLANKS, AtmosphericItemTags.ASPEN_LOGS, CConstants.ATMOSPHERIC);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_GRIMWOOD_PLANKS, AtmosphericBlocks.GRIMWOOD_SLAB, AtmosphericBlocks.GRIMWOOD_PLANKS, AtmosphericItemTags.GRIMWOOD_LOGS, CConstants.ATMOSPHERIC);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_KOUSA_PLANKS, AtmosphericBlocks.KOUSA_SLAB, AtmosphericBlocks.KOUSA_PLANKS, AtmosphericItemTags.KOUSA_LOGS, CConstants.ATMOSPHERIC);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_LAUREL_PLANKS, AtmosphericBlocks.LAUREL_SLAB, AtmosphericBlocks.LAUREL_PLANKS, AtmosphericItemTags.LAUREL_LOGS, CConstants.ATMOSPHERIC);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_MORADO_PLANKS, AtmosphericBlocks.MORADO_SLAB, AtmosphericBlocks.MORADO_PLANKS, AtmosphericItemTags.MORADO_LOGS, CConstants.ATMOSPHERIC);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_ROSEWOOD_PLANKS, AtmosphericBlocks.ROSEWOOD_SLAB, AtmosphericBlocks.ROSEWOOD_PLANKS, AtmosphericItemTags.ROSEWOOD_LOGS, CConstants.ATMOSPHERIC);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_YUCCA_PLANKS, AtmosphericBlocks.YUCCA_SLAB, AtmosphericBlocks.YUCCA_PLANKS, AtmosphericItemTags.YUCCA_LOGS, CConstants.ATMOSPHERIC);

        fanciedPlanksRecipe(output, CBlocks.FANCIED_PINE_PLANKS, EnvironmentalBlocks.PINE_SLAB, EnvironmentalBlocks.PINE_PLANKS, EnvironmentalItemTags.PINE_LOGS, CConstants.ENVIRONMENTAL);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_PLUM_PLANKS, EnvironmentalBlocks.PLUM_SLAB, EnvironmentalBlocks.PLUM_PLANKS, EnvironmentalItemTags.PLUM_LOGS, CConstants.ENVIRONMENTAL);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_WILLOW_PLANKS, EnvironmentalBlocks.WILLOW_SLAB, EnvironmentalBlocks.WILLOW_PLANKS, EnvironmentalItemTags.WILLOW_LOGS, CConstants.ENVIRONMENTAL);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_WISTERIA_PLANKS, EnvironmentalBlocks.WISTERIA_SLAB, EnvironmentalBlocks.WISTERIA_PLANKS, EnvironmentalItemTags.WISTERIA_LOGS, CConstants.ENVIRONMENTAL);

        fanciedPlanksRecipe(output, CBlocks.FANCIED_DRIFTWOOD_PLANKS, UABlocks.DRIFTWOOD_SLAB, UABlocks.DRIFTWOOD_PLANKS, UAItemTags.DRIFTWOOD_LOGS, CConstants.UPGRADE_AQUATIC);
        fanciedPlanksRecipe(output, CBlocks.FANCIED_RIVER_PLANKS, UABlocks.RIVER_SLAB, UABlocks.RIVER_PLANKS, UAItemTags.RIVER_LOGS, CConstants.UPGRADE_AQUATIC);

        //mnd compat
        fanciedPlanksRecipe(output, CBlocks.FANCIED_POWDERY_PLANKS, MNDItems.POWDERY_PLANKS_SLAB.get(), MNDItems.POWDERY_PLANKS.get(), MNDTags.BLOCK_OF_POWDERY, CConstants.MY_NETHERS_DELIGHT);

        //gotd compat
        fanciedPlanksRecipe(output, CBlocks.FANCIED_SOULBLIGHT_PLANKS, gardensofthedead.registry.ModItems.SOULBLIGHT_SLAB.get(), gardensofthedead.registry.ModItems.SOULBLIGHT_PLANKS.get(), ModTags.Items.SOULBLIGHT_STEMS, CConstants.GARDENS_OF_THE_DEAD);
        fanciedPlanksRecipe(output.withConditions(new ModLoadedCondition(CConstants.GARDENS_OF_THE_DEAD)), CBlocks.FANCIED_WHISTLECANE_PLANKS, gardensofthedead.registry.ModItems.WHISTLECANE_SLAB.get(), gardensofthedead.registry.ModItems.WHISTLECANE_PLANKS.get(), ModTags.Items.WHISTLECANE_BLOCKS, 2);
    }

    public void oreRecipesAlt(RecipeOutput recipeOutput, List<ItemLike> inputs, RecipeCategory category, ItemLike output, int count, float xp, int cookTime, String group) {
        oreRecipesAlt(recipeOutput, inputs, category, output, output, count, count, xp, cookTime, group);
    }

    public void oreRecipesAlt(RecipeOutput recipeOutput, List<ItemLike> inputs, RecipeCategory category, ItemLike smeltOutput, ItemLike blastOutput, int smeltCount, int blastCount, float xp, int cookTime, String group) {
        for (ItemLike item : inputs) {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(item), category, new ItemStack(smeltOutput, smeltCount), xp, cookTime)
                    .unlockedBy(getHasName(item), has(item))
                    .group(group)
                    .save(recipeOutput, getDefaultRecipeId(smeltOutput) + "_from_smelting_" + getDefaultRecipeId(item).getPath());
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(item), category, new ItemStack(blastOutput, blastCount), xp, cookTime / 2)
                    .unlockedBy(getHasName(item), has(item))
                    .group(group)
                    .save(recipeOutput, getDefaultRecipeId(blastOutput) + "_from_blasting_" + getDefaultRecipeId(item).getPath());
        }
    }

    protected void aluminumSmithingRecipe(RecipeOutput recipeOutput, Item input, Item output) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(input), Ingredient.of(CItemTags.INGOTS_ALUMINUM), COMBAT, output).unlocks("has_aluminum_ingot", has(CItems.ALUMINUM_INGOT)).save(recipeOutput, Curiosities.location(getItemName(output) + "_smithing"));
    }

    protected void invarSmithingRecipe(RecipeOutput recipeOutput, Item input, Item output) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(input), Ingredient.of(CItemTags.INGOTS_INVAR), COMBAT, output).unlocks("has_invar_ingot", has(CItems.INVAR_INGOT)).save(recipeOutput, Curiosities.location(getItemName(output) + "_smithing"));
    }

    protected void barsRecipe(RecipeOutput recipeOutput, TagKey<Item> input, ItemLike output, String id) {
        ShapedRecipeBuilder.shaped(DECORATIONS, output.asItem(), 16).define('#', input).pattern("###").pattern("###").unlockedBy("has_" + id, has(input)).save(recipeOutput);
    }

    protected static RecipeBuilder cageLightRecipe(ItemLike cageLight, ItemLike requirement) {
        return ShapedRecipeBuilder.shaped(REDSTONE, cageLight)
                .define('N', CItemTags.NUGGETS_NICKEL)
                .define('R', Items.REDSTONE)
                .define('L', requirement)
                .pattern("NNN").pattern("NLN").pattern("NRN")
                .unlockedBy("has_redstone", has(Items.REDSTONE)).unlockedBy("has_nickel", has(CItemTags.INGOTS_NICKEL)).unlockedBy("has_lantern", has(requirement));
    }
    protected static RecipeBuilder heavyLanternRecipe(ItemLike lantern, ItemLike requirement) {
        return ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, lantern)
                .define('N', CItemTags.NUGGETS_INVAR)
                .define('I', Items.IRON_INGOT)
                .define('L', requirement)
                .pattern("NIN").pattern("NLN").pattern("NIN")
                .unlockedBy("has_nickel", has(CItemTags.INGOTS_INVAR)).unlockedBy("has_lantern", has(requirement));
    }
    protected static RecipeBuilder tikiTorchRecipe(ItemLike cageLight, ItemLike requirement) {
        return ShapedRecipeBuilder.shaped(REDSTONE, cageLight)
                .define('B', Items.BAMBOO)
                .define('T', requirement)
                .pattern("BTB").pattern(" B ")
                .unlockedBy("has_bamboo", has(Items.BAMBOO)).unlockedBy("has_torch", has(requirement));
    }
    public void ashlarRecipes(RecipeOutput recipeOutput, ItemLike ashlarBlock, ItemLike requirement, ItemLike altRequirement) {
        stonecutterRecipe(recipeOutput, BUILDING_BLOCKS, ashlarBlock, requirement);
        stonecutterRecipe(recipeOutput, BUILDING_BLOCKS, ashlarBlock, altRequirement);
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ashlarBlock, 8)
                .define('B', altRequirement)
                .pattern("BBB").pattern("B B").pattern("BBB")
                .unlockedBy("has_bricks", has(altRequirement)).save(recipeOutput);
    }

    public void fanciedPlanksRecipe(RecipeOutput recipeOutput, ItemLike fanciedPlanks, ItemLike slabReq, ItemLike plankReq, TagKey<Item> logReq) {
        fanciedPlanksRecipe(recipeOutput, fanciedPlanks, slabReq, plankReq, logReq, 4);
    }

    public void fanciedPlanksRecipe(RecipeOutput recipeOutput, ItemLike fanciedPlanks, ItemLike slabReq, ItemLike plankReq, TagKey<Item> logReq, String modid) {
        fanciedPlanksRecipe(recipeOutput.withConditions(new ModLoadedCondition(modid)), fanciedPlanks, slabReq, plankReq, logReq, 4);
    }

    public void fanciedPlanksRecipe(RecipeOutput recipeOutput, ItemLike fanciedPlanks, ItemLike slabReq, ItemLike plankReq, TagKey<Item> logReq, int logOutput) {
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, fanciedPlanks, 2)
                .define('B', slabReq)
                .pattern("BB").pattern("BB")
                .unlockedBy("has_planks", has(plankReq)).save(recipeOutput);
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput.withConditions(new ModLoadedCondition(CConstants.WOODWORKS)), SAWMILL_ENABLED, BUILDING_BLOCKS, plankReq, fanciedPlanks, 1, "", Curiosities.MOD_ID);
        WoodworksRecipeProvider.sawmillRecipe(recipeOutput.withConditions(new ModLoadedCondition(CConstants.WOODWORKS)), SAWMILL_ENABLED, BUILDING_BLOCKS, logReq, fanciedPlanks, logOutput, "", Curiosities.MOD_ID);
    }

    public void stairsStoneRecipes(RecipeOutput output, ItemLike stairs, ItemLike requirement) {
        stairBuilder(stairs, Ingredient.of(requirement)).unlockedBy("has_req", has(requirement)).save(output);
        stonecutterRecipe(output, BUILDING_BLOCKS, stairs, requirement);
    }

    public void slabStoneRecipes(RecipeOutput output, ItemLike slab, ItemLike requirement) {
        slabBuilder(BUILDING_BLOCKS, slab, Ingredient.of(requirement)).unlockedBy("has_req", has(requirement)).save(output);
        stonecutterRecipe(output, BUILDING_BLOCKS, slab, requirement, 2);
    }

    public void wallStoneRecipes(RecipeOutput output, ItemLike wall, ItemLike requirement) {
        wall(output, BUILDING_BLOCKS, wall, requirement);
        stonecutterRecipe(output, BUILDING_BLOCKS, wall, requirement);
    }

    public void incenseRecipe(RecipeOutput output, ItemLike incense, ItemLike first, ItemLike second, ItemLike third) {
        ShapedRecipeBuilder.shaped(TOOLS, incense, 2)
                .define('S', Tags.Items.RODS_WOODEN)
                .define('1', first)
                .define('2', second)
                .define('3', third)
                .pattern("12").pattern("S3")
                .unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
                .save(output);
    }

    public void censerRecipe(RecipeOutput output, ItemLike censer, ItemLike incense) {
        ShapedRecipeBuilder.shaped(TOOLS, censer)
                .define('G', Items.GOLD_INGOT)
                .define('I', incense)
                .pattern("GIG").pattern("I I").pattern("GIG")
                .unlockedBy("has_incense", has(incense))
                .save(output);
    }
}
