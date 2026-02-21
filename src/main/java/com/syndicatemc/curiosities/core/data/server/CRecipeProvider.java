package com.syndicatemc.curiosities.core.data.server;

import com.google.common.collect.ImmutableList;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.CBlockFamilies;
import com.syndicatemc.curiosities.core.other.tags.CItemTags;
import com.syndicatemc.curiosities.core.registry.CBlocks;
import com.syndicatemc.curiosities.core.registry.CItems;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

        oreRecipesAlt(output, ALUMINUM_SMELTABLES, MISC, CItems.ALUMINUM_NUGGET, 3,0.8F, 300, "aluminum_nugget");
        oreRecipesAlt(output, NICKEL_SMELTABLES, MISC, CItems.NICKEL_INGOT, 1,0.8F, 300, "nickel_ingot");

        ShapelessRecipeBuilder.shapeless(MISC, CItems.INVAR_INGOT)
                .requires(Items.NETHERITE_SCRAP, 3)
                .requires(Ingredient.of(Tags.Items.INGOTS_IRON), 3)
                .requires(Ingredient.of(CItemTags.INGOTS_NICKEL), 3)
                .group("invar_ingot")
                .unlockedBy("has_netherite_scrap", has(Items.NETHERITE_SCRAP)).save(output);

        ShapedRecipeBuilder.shaped(MISC, CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE, 2)
                .define('A', CItemTags.INGOTS_ALUMINUM)
                .define('L', Tags.Items.LEATHERS)
                .define('D', Blocks.POLISHED_DEEPSLATE)
                .define('T', CItems.ALUMINUM_UPGRADE_SMITHING_TEMPLATE)
                .pattern("ATA").pattern("LDL").pattern("ALA")
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

        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICKS, Items.SMOOTH_STONE);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_SLAB, Items.SMOOTH_STONE, 2);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_STAIRS, Items.SMOOTH_STONE);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_SLAB, CBlocks.SMOOTH_STONE_BRICKS, 2);
        stonecutterRecipe(output, BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICK_STAIRS, CBlocks.SMOOTH_STONE_BRICKS);
        ashlarRecipes(output, CBlocks.SMOOTH_STONE_ASHLAR, Items.SMOOTH_STONE, CBlocks.SMOOTH_STONE_BRICKS);
        cutBuilder(BUILDING_BLOCKS, CBlocks.SMOOTH_STONE_BRICKS, Ingredient.of(Items.SMOOTH_STONE)).unlockedBy("has_smooth_stone", has(Items.SMOOTH_STONE)).save(output);
        generateRecipes(output, CBlockFamilies.SMOOTH_STONE_BRICKS_BLOCK_FAMILY);
    }

    public void oreRecipesAlt(RecipeOutput recipeOutput, List<ItemLike> inputs, RecipeCategory category, ItemLike output, int count, float xp, int cookTime, String group) {
        for (ItemLike item : inputs) {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(item), category, new ItemStack(output, count), xp, cookTime)
                    .unlockedBy(getHasName(item), has(item))
                    .group(group)
                    .save(recipeOutput, getDefaultRecipeId(output) + "_from_smelting_" + getDefaultRecipeId(item).getPath());
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(item), category, new ItemStack(output, count), xp, cookTime / 2)
                    .unlockedBy(getHasName(item), has(item))
                    .group(group)
                    .save(recipeOutput, getDefaultRecipeId(output) + "_from_blasting_" + getDefaultRecipeId(item).getPath());
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
    protected void ashlarRecipes(RecipeOutput recipeOutput, ItemLike ashlarBlock, ItemLike requirement, ItemLike altRequirement) {
        stonecutterRecipe(recipeOutput, BUILDING_BLOCKS, ashlarBlock, requirement);
        stonecutterRecipe(recipeOutput, BUILDING_BLOCKS, ashlarBlock, altRequirement);
        ShapedRecipeBuilder.shaped(BUILDING_BLOCKS, ashlarBlock)
                .define('B', altRequirement)
                .pattern("BBB").pattern("B B").pattern("BBB")
                .unlockedBy("has_bricks", has(altRequirement)).save(recipeOutput);
    }
}
