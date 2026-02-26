package com.syndicatemc.curiosities.core.data.server;

import com.google.common.collect.ImmutableList;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.syndicatemc.curiosities.core.registry.CBlocks.*;

public class CLootTableProvider extends LootTableProvider {
    public CLootTableProvider(PackOutput output, CompletableFuture<Provider> provider) {
        super(output, BuiltInLootTables.all(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(CBlockLoot::new, LootContextParamSets.BLOCK)
        ), provider);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {
    }

    private static class CBlockLoot extends BlockLootSubProvider {
        private static final Set<Item> EXPLOSION_RESISTANT = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.PIGLIN_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemLike::asItem).collect(Collectors.toSet());

        protected CBlockLoot(Provider provider) {
            super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        public void generate() {
            this.add(ALUMINUM_ORE.get(), block -> createOreDrop(block, CItems.RAW_ALUMINUM.get()));
            this.add(DEEPSLATE_ALUMINUM_ORE.get(), block -> createOreDrop(block, CItems.RAW_ALUMINUM.get()));
            this.add(NICKEL_ORE.get(), block -> createOreDrop(block, CItems.RAW_NICKEL.get()));
            this.add(DEEPSLATE_NICKEL_ORE.get(), block -> createOreDrop(block, CItems.RAW_NICKEL.get()));

            for (DeferredBlock<?> block : new DeferredBlock[]{
                    RAW_ALUMINUM_BLOCK, ALUMINUM_BLOCK,
                    RAW_NICKEL_BLOCK, NICKEL_BLOCK,
                    INVAR_BLOCK,

                    ALUMINUM_GRATE, ALUMINUM_BARS, SHEET_METAL, ALUMINUM_TRAPDOOR, CUT_ALUMINUM, CUT_ALUMINUM_STAIRS,
                    NICKEL_BARS,

                    REDSTONE_DIODE, REDSTONE_FUSE,
                    WEIGHT_1S, WEIGHT_5S, WEIGHT_20S,
                    CAGE_LIGHT, SOUL_CAGE_LIGHT, TILE_LIGHT,
                    BIG_CHAIN, HEAVY_LANTERN, HEAVY_SOUL_LANTERN,
                    CONCUSSION_BOMB,

                    STONE_ASHLAR, DEEPSLATE_ASHLAR, TUFF_ASHLAR, POLISHED_BLACKSTONE_ASHLAR,
                    SMOOTH_STONE_BRICKS, SMOOTH_STONE_BRICK_STAIRS, SMOOTH_STONE_BRICK_WALL, SMOOTH_STONE_ASHLAR,

                    SMOOTH_STONE_STAIRS, CUT_SANDSTONE_STAIRS, CUT_RED_SANDSTONE_STAIRS, QUARTZ_BRICK_STAIRS,
                    STONE_WALL, SMOOTH_STONE_WALL, POLISHED_GRANITE_WALL, POLISHED_DIORITE_WALL, POLISHED_ANDESITE_WALL, CUT_SANDSTONE_WALL, CUT_RED_SANDSTONE_WALL, PRISMARINE_BRICK_WALL, DARK_PRISMARINE_WALL, QUARTZ_WALL, QUARTZ_BRICK_WALL, PURPUR_WALL
            }) {
                this.dropSelf(block.get());
            }

            for (DeferredBlock<?> block : new DeferredBlock[]{
                    CUT_ALUMINUM_SLAB, SMOOTH_STONE_BRICK_SLAB, QUARTZ_BRICK_SLAB
            }) {
                this.add(block.get(), this::createSlabItemTable);
            }

            this.add(ALUMINUM_DOOR.get(), this::createDoorTable);
        }

        @Override
        public Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(Curiosities.MOD_ID)).collect(Collectors.toSet());
        }
    }
}
