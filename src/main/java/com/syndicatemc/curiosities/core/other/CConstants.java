package com.syndicatemc.curiosities.core.other;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class CConstants {
    public static final String FARMERS_DELIGHT = "farmersdelight";
    public static final ResourceKey<CreativeModeTab> FARMERS_DELIGHT_TAB = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(FARMERS_DELIGHT, FARMERS_DELIGHT));
    public static final ResourceLocation DIAMOND_KNIFE = getItem(FARMERS_DELIGHT, "diamond_knife");
    public static final ResourceLocation NETHERITE_KNIFE = getItem(FARMERS_DELIGHT, "netherite_knife");

    public static final String WOODWORKS = "woodworks";

    public static final String ATMOSPHERIC = "atmospheric";
    public static final ResourceLocation ASPEN_FENCE_GATE = getItem(ATMOSPHERIC, "aspen_fence_gate");
    public static final ResourceLocation GRIMWOOD_FENCE_GATE = getItem(ATMOSPHERIC, "grimwood_fence_gate");
    public static final ResourceLocation KOUSA_FENCE_GATE = getItem(ATMOSPHERIC, "kousa_fence_gate");
    public static final ResourceLocation LAUREL_FENCE_GATE = getItem(ATMOSPHERIC, "laurel_fence_gate");
    public static final ResourceLocation MORADO_FENCE_GATE = getItem(ATMOSPHERIC, "morado_fence_gate");
    public static final ResourceLocation ROSEWOOD_FENCE_GATE = getItem(ATMOSPHERIC, "rosewood_fence_gate");
    public static final ResourceLocation YUCCA_FENCE_GATE = getItem(ATMOSPHERIC, "yucca_fence_gate");

    public static final String ENVIRONMENTAL = "environmental";
    public static final ResourceLocation PINE_FENCE_GATE = getItem(ENVIRONMENTAL, "pine_fence_gate");
    public static final ResourceLocation PLUM_FENCE_GATE = getItem(ENVIRONMENTAL, "plum_fence_gate");
    public static final ResourceLocation WILLOW_FENCE_GATE = getItem(ENVIRONMENTAL, "willow_fence_gate");
    public static final ResourceLocation WISTERIA_FENCE_GATE = getItem(ENVIRONMENTAL, "wisteria_fence_gate");

    public static final String UPGRADE_AQUATIC = "upgrade_aquatic";
    public static final ResourceLocation DRIFTWOOD_FENCE_GATE = getItem(UPGRADE_AQUATIC, "driftwood_fence_gate");
    public static final ResourceLocation RIVER_FENCE_GATE = getItem(UPGRADE_AQUATIC, "river_fence_gate");

    public static final class CCompatProperties {
        public static Properties ASPEN_PLANKS = moddedPlanks(MapColor.GOLD);
        public static Properties GRIMWOOD_PLANKS = moddedPlanks(MapColor.TERRACOTTA_BLACK);
        public static Properties KOUSA_PLANKS = moddedPlanks(MapColor.TERRACOTTA_CYAN);
        public static Properties LAUREL_PLANKS = moddedPlanks(MapColor.TERRACOTTA_YELLOW);
        public static Properties MORADO_PLANKS = moddedPlanks(MapColor.COLOR_RED);
        public static Properties ROSEWOOD_PLANKS = moddedPlanks(MapColor.TERRACOTTA_MAGENTA);
        public static Properties YUCCA_PLANKS = moddedPlanks(MapColor.COLOR_ORANGE);

        public static Properties PINE_PLANKS = moddedPlanks(MapColor.TERRACOTTA_LIGHT_GRAY);
        public static Properties PLUM_PLANKS = moddedPlanks(MapColor.TERRACOTTA_RED);
        public static Properties WILLOW_PLANKS = moddedPlanks(MapColor.TERRACOTTA_GREEN);
        public static Properties WISTERIA_PLANKS = moddedPlanks(MapColor.TERRACOTTA_WHITE);

        public static Properties DRIFTWOOD_PLANKS = moddedPlanks(MapColor.STONE);
        public static Properties RIVER_PLANKS = moddedPlanks(MapColor.COLOR_BROWN);

        private static Properties moddedPlanks(MapColor color) {
            return Properties.ofLegacyCopy(Blocks.OAK_PLANKS).mapColor(color);
        }
    }

    private static ResourceLocation getItem(String modid, String item) {
        return ResourceLocation.fromNamespaceAndPath(modid, item);
    }
}
