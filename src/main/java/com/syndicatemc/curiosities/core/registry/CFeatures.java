package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.core.Curiosities;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class CFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Curiosities.MOD_ID);

    public static final class CConfiguredFeatures {
        public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM = createKey("ore_aluminum");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ALUMINUM_BURIED = createKey("ore_aluminum_buried");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NICKEL = createKey("ore_nickel");
        public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_NICKEL_BURIED = createKey("ore_nickel_buried");

        public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
            RuleTest baseStone = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
            RuleTest stoneOre = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
            RuleTest deepslateOre = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
            List<OreConfiguration.TargetBlockState> aluminumTargets = List.of(OreConfiguration.target(stoneOre, CBlocks.ALUMINUM_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateOre, CBlocks.DEEPSLATE_ALUMINUM_ORE.get().defaultBlockState()));
            List<OreConfiguration.TargetBlockState> nickelTargets = List.of(OreConfiguration.target(stoneOre, CBlocks.NICKEL_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateOre, CBlocks.DEEPSLATE_NICKEL_ORE.get().defaultBlockState()));

            register(context, ORE_ALUMINUM, Feature.ORE, new OreConfiguration(aluminumTargets, 9));
            register(context, ORE_ALUMINUM_BURIED, Feature.ORE, new OreConfiguration(aluminumTargets, 9, 0.5F));
            register(context, ORE_NICKEL, Feature.ORE, new OreConfiguration(nickelTargets, 9));
            register(context, ORE_NICKEL_BURIED, Feature.ORE, new OreConfiguration(nickelTargets, 8, 0.75F));
        }

        public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, Curiosities.location(name));
        }

        public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
            context.register(key, new ConfiguredFeature<>(feature, config));
        }
    }

    public static final class CPlacedFeatures {
        public static final ResourceKey<PlacedFeature> ORE_ALUMINUM = createKey("ore_aluminum");
        public static final ResourceKey<PlacedFeature> ORE_ALUMINUM_BURIED = createKey("ore_aluminum_buried");
        public static final ResourceKey<PlacedFeature> ORE_NICKEL = createKey("ore_nickel");
        public static final ResourceKey<PlacedFeature> ORE_NICKEL_BURIED = createKey("ore_nickel_buried");

        public static void bootstrap(BootstrapContext<PlacedFeature> context) {
            register(context, ORE_ALUMINUM, CConfiguredFeatures.ORE_ALUMINUM, commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(48))));
            register(context, ORE_ALUMINUM_BURIED, CConfiguredFeatures.ORE_ALUMINUM_BURIED, commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))));
            register(context, ORE_NICKEL, CConfiguredFeatures.ORE_NICKEL, commonOrePlacement(5, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64))));
            register(context, ORE_NICKEL_BURIED, CConfiguredFeatures.ORE_NICKEL_BURIED, commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(48))));
        }

        public static ResourceKey<PlacedFeature> createKey(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, Curiosities.location(name));
        }

        private static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifierOther) {
            return List.of(modifier, InSquarePlacement.spread(), modifierOther, BiomeFilter.biome());
        }

        private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
            return orePlacement(CountPlacement.of(count), modifier);
        }

        public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
            context.register(key, new PlacedFeature(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(feature), modifiers));
        }

        public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, ResourceKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
            register(context, key, feature, List.of(modifiers));
        }
    }
}
