package com.syndicatemc.curiosities.core.other;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class CConstants {
    public static final String FARMERS_DELIGHT = "farmersdelight";
    public static final ResourceKey<CreativeModeTab> FARMERS_DELIGHT_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(FARMERS_DELIGHT, FARMERS_DELIGHT));
    public static final ResourceLocation DIAMOND_KNIFE = getResource(FARMERS_DELIGHT, "diamond_knife");
    public static final ResourceLocation NETHERITE_KNIFE = getResource(FARMERS_DELIGHT, "netherite_knife");

    public static final String WOODWORKS = "woodworks";

    public static final String ATMOSPHERIC = "atmospheric";
    public static final ResourceLocation ASPEN_FENCE_GATE = getResource(ATMOSPHERIC, "aspen_fence_gate");
    public static final ResourceLocation GRIMWOOD_FENCE_GATE = getResource(ATMOSPHERIC, "grimwood_fence_gate");
    public static final ResourceLocation KOUSA_FENCE_GATE = getResource(ATMOSPHERIC, "kousa_fence_gate");
    public static final ResourceLocation LAUREL_FENCE_GATE = getResource(ATMOSPHERIC, "laurel_fence_gate");
    public static final ResourceLocation MORADO_FENCE_GATE = getResource(ATMOSPHERIC, "morado_fence_gate");
    public static final ResourceLocation ROSEWOOD_FENCE_GATE = getResource(ATMOSPHERIC, "rosewood_fence_gate");
    public static final ResourceLocation YUCCA_FENCE_GATE = getResource(ATMOSPHERIC, "yucca_fence_gate");

    public static final String AUTUMNITY = "autumnity";
    public static final ResourceLocation MAPLE_FENCE_GATE = getResource(AUTUMNITY, "maple_fence_gate");

    public static final String ENVIRONMENTAL = "environmental";
    public static final ResourceLocation PINE_FENCE_GATE = getResource(ENVIRONMENTAL, "pine_fence_gate");
    public static final ResourceLocation PLUM_FENCE_GATE = getResource(ENVIRONMENTAL, "plum_fence_gate");
    public static final ResourceLocation WILLOW_FENCE_GATE = getResource(ENVIRONMENTAL, "willow_fence_gate");
    public static final ResourceLocation WISTERIA_FENCE_GATE = getResource(ENVIRONMENTAL, "wisteria_fence_gate");

    public static final String UPGRADE_AQUATIC = "upgrade_aquatic";
    public static final ResourceLocation DRIFTWOOD_FENCE_GATE = getResource(UPGRADE_AQUATIC, "driftwood_fence_gate");
    public static final ResourceLocation RIVER_FENCE_GATE = getResource(UPGRADE_AQUATIC, "river_fence_gate");

    public static final String MY_NETHERS_DELIGHT = "mynethersdelight";
    public static final ResourceKey<CreativeModeTab> MY_NETHERS_DELIGHT_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, getResource(MY_NETHERS_DELIGHT, "main"));
    public static final ResourceLocation POWDERY_FENCE_GATE = getResource(MY_NETHERS_DELIGHT, "powdery_fence_gate");

    public static final String GARDENS_OF_THE_DEAD = "gardens_of_the_dead";
    public static final ResourceKey<CreativeModeTab> GARDENS_OF_THE_DEAD_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, getResource(GARDENS_OF_THE_DEAD, "main"));
    public static final ResourceLocation SOULBLIGHT_FENCE_GATE = getResource(GARDENS_OF_THE_DEAD, "soulblight_fence_gate");
    public static final ResourceLocation WHISTLECANE_FENCE_GATE = getResource(GARDENS_OF_THE_DEAD, "whistlecane_fence_gate");

    public static final class CCompatProperties {
        public static Properties ASPEN_PLANKS = moddedPlanks(MapColor.GOLD);
        public static Properties GRIMWOOD_PLANKS = moddedPlanks(MapColor.TERRACOTTA_BLACK);
        public static Properties KOUSA_PLANKS = moddedPlanks(MapColor.TERRACOTTA_CYAN);
        public static Properties LAUREL_PLANKS = moddedPlanks(MapColor.TERRACOTTA_YELLOW);
        public static Properties MORADO_PLANKS = moddedPlanks(MapColor.COLOR_RED);
        public static Properties ROSEWOOD_PLANKS = moddedPlanks(MapColor.TERRACOTTA_MAGENTA);
        public static Properties YUCCA_PLANKS = moddedPlanks(MapColor.COLOR_ORANGE);

        public static Properties MAPLE_PLANKS = moddedPlanks(MapColor.TERRACOTTA_ORANGE);

        public static Properties PINE_PLANKS = moddedPlanks(MapColor.TERRACOTTA_LIGHT_GRAY);
        public static Properties PLUM_PLANKS = moddedPlanks(MapColor.TERRACOTTA_RED);
        public static Properties WILLOW_PLANKS = moddedPlanks(MapColor.TERRACOTTA_GREEN);
        public static Properties WISTERIA_PLANKS = moddedPlanks(MapColor.TERRACOTTA_WHITE);

        public static Properties DRIFTWOOD_PLANKS = moddedPlanks(MapColor.STONE);
        public static Properties RIVER_PLANKS = moddedPlanks(MapColor.COLOR_BROWN);

        public static Properties POWDERY_PLANKS = Properties.ofLegacyCopy(Blocks.CRIMSON_PLANKS).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.TERRACOTTA_GRAY);

        public static Properties SOULBLIGHT_PLANKS = Properties.of().strength(2, 3).sound(SoundType.WOOD).mapColor(MapColor.COLOR_BROWN).instrument(NoteBlockInstrument.BASS);
        public static Properties WHISTLECANE_PLANKS = Properties.of().strength(1, 2).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.CRIMSON_NYLIUM).instrument(NoteBlockInstrument.BASS);

        private static Properties moddedPlanks(MapColor color) {
            return Properties.ofLegacyCopy(Blocks.OAK_PLANKS).mapColor(color);
        }
    }

    private static ResourceLocation getResource(String modid, String item) {
        return ResourceLocation.fromNamespaceAndPath(modid, item);
    }
}
