package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.common.block.*;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.CConstants;
import com.syndicatemc.curiosities.core.other.CConstants.CCompatProperties;
import com.syndicatemc.curiosities.core.registry.helper.CBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Predicate;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@SuppressWarnings("deprecation")
public class CBlocks {
    public static final CBlockSubRegistryHelper BLOCKS = Curiosities.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> ALUMINUM_ORE = BLOCKS.createBlock("aluminum_ore", () -> new DropExperienceBlock(ConstantInt.of(0), CProperties.ALUMINUM_ORE));
    public static final DeferredBlock<Block> DEEPSLATE_ALUMINUM_ORE = BLOCKS.createBlock("deepslate_aluminum_ore", () -> new DropExperienceBlock(ConstantInt.of(0), CProperties.DEEPSLATE_ALUMINUM_ORE));
    public static final DeferredBlock<Block> NICKEL_ORE = BLOCKS.createBlock("nickel_ore", () -> new DropExperienceBlock(ConstantInt.of(0), CProperties.NICKEL_ORE));
    public static final DeferredBlock<Block> DEEPSLATE_NICKEL_ORE = BLOCKS.createBlock("deepslate_nickel_ore", () -> new DropExperienceBlock(ConstantInt.of(0), CProperties.DEEPSLATE_NICKEL_ORE));

    public static final DeferredBlock<Block> ALUMINUM_BLOCK = BLOCKS.createBlock("aluminum_block", () -> new Block(CProperties.ALUMINUM_BLOCK));
    public static final DeferredBlock<Block> RAW_ALUMINUM_BLOCK = BLOCKS.createBlock("raw_aluminum_block", () -> new Block(CProperties.RAW_ALUMINUM_BLOCK));
    public static final DeferredBlock<Block> NICKEL_BLOCK = BLOCKS.createBlock("nickel_block", () -> new Block(CProperties.NICKEL_BLOCK));
    public static final DeferredBlock<Block> RAW_NICKEL_BLOCK = BLOCKS.createBlock("raw_nickel_block", () -> new Block(CProperties.RAW_NICKEL_BLOCK));
    public static final DeferredBlock<Block> INVAR_BLOCK = BLOCKS.createBlock("invar_block", () -> new InvarWeightBlock(CProperties.INVAR_BLOCK, 10, true));

    public static final DeferredBlock<Block> ALUMINUM_GRATE = BLOCKS.createBlock("aluminum_grate", () -> new WaterloggedTransparentBlock(CProperties.ALUMINUM_GRATE), new Item.Properties().fireResistant());
    public static final DeferredBlock<Block> ALUMINUM_BARS = BLOCKS.createBlock("aluminum_bars", () -> new IronBarsBlock(CProperties.ALUMINUM_BARS));
    public static final DeferredBlock<Block> SHEET_METAL = BLOCKS.createBlock("sheet_metal", () -> new RotatedPillarBlock(CProperties.ALUMINUM_BLOCK));
    public static final DeferredBlock<Block> CUT_ALUMINUM = BLOCKS.createBlock("cut_aluminum", () -> new Block(CProperties.ALUMINUM_BLOCK));
    public static final DeferredBlock<Block> CUT_ALUMINUM_STAIRS = BLOCKS.createBlock("cut_aluminum_stairs", () -> new StairBlock(CBlocks.CUT_ALUMINUM.get().defaultBlockState(), CProperties.ALUMINUM_BLOCK));
    public static final DeferredBlock<Block> CUT_ALUMINUM_SLAB = BLOCKS.createBlock("cut_aluminum_slab", () -> new SlabBlock(CProperties.ALUMINUM_BLOCK));
    public static final DeferredBlock<Block> ALUMINUM_DOOR = BLOCKS.createBlock("aluminum_door", () -> new DoorBlock(CBlockSetTypes.ALUMINIUM, CProperties.ALUMINUM_BLOCK));
    public static final DeferredBlock<Block> ALUMINUM_TRAPDOOR = BLOCKS.createBlock("aluminum_trapdoor", () -> new TrapDoorBlock(CBlockSetTypes.ALUMINIUM, CProperties.ALUMINUM_BLOCK));

    public static final DeferredBlock<Block> NICKEL_BARS = BLOCKS.createBlock("nickel_bars", () -> new IronBarsBlock(CProperties.NICKEL_BARS));

    public static final DeferredBlock<Block> BIG_CHAIN = BLOCKS.createBlock("big_chain", () -> new BigChainBlock(CProperties.BIG_CHAIN));

    public static final DeferredBlock<Block> REDSTONE_DIODE = BLOCKS.createBlock("redstone_diode", () -> new RedstoneDiodeBlock(CProperties.REDSTONE_COMPONENTS));
    public static final DeferredBlock<Block> REDSTONE_FUSE = BLOCKS.createBlock("redstone_fuse", () -> new RedstoneFuseBlock(CProperties.REDSTONE_COMPONENTS));

    public static final DeferredBlock<Block> CAGE_LIGHT = BLOCKS.createBlock("cage_light", () -> new CageLightBlock(CProperties.CAGE_LIGHT));
    public static final DeferredBlock<Block> SOUL_CAGE_LIGHT = BLOCKS.createBlock("soul_cage_light", () -> new CageLightBlock(CProperties.SOUL_CAGE_LIGHT));
    public static final DeferredBlock<Block> TILE_LIGHT = BLOCKS.createBlock("tile_light", () -> new TileLightBlock(CProperties.TILE_LIGHT));
    public static final DeferredBlock<Block> HEAVY_LANTERN = BLOCKS.createBlock("heavy_lantern", () -> new HeavyLanternBlock(CProperties.HEAVY_LANTERN));
    public static final DeferredBlock<Block> HEAVY_SOUL_LANTERN = BLOCKS.createBlock("heavy_soul_lantern", () -> new HeavyLanternBlock(CProperties.HEAVY_SOUL_LANTERN));
    public static final DeferredBlock<Block> TIKI_TORCH = BLOCKS.createBlock("tiki_torch", () -> new TikiTorchBlock(CProperties.TIKI_TORCH, false));
    public static final DeferredBlock<Block> SOUL_TIKI_TORCH = BLOCKS.createBlock("soul_tiki_torch", () -> new TikiTorchBlock(CProperties.SOUL_TIKI_TORCH, true));

    public static final DeferredBlock<Block> ACRID_WALL_INCENSE = BLOCKS.createBlockNoItem("acrid_wall_incense", () -> new IncenseWallBlock(CProperties.INCENSE, CMobEffects.ACRID_SMOKE, 10821965));
    public static final DeferredBlock<Block> ACRID_INCENSE = BLOCKS.createStandingAndWallBlock("acrid_incense", () -> new IncenseBlock(CProperties.INCENSE, CMobEffects.ACRID_SMOKE, 10821965), ACRID_WALL_INCENSE, Direction.DOWN);
    public static final DeferredBlock<Block> BLAND_WALL_INCENSE = BLOCKS.createBlockNoItem("bland_wall_incense", () -> new IncenseWallBlock(CProperties.INCENSE, CMobEffects.BLAND_SMOKE, 12371141));
    public static final DeferredBlock<Block> BLAND_INCENSE = BLOCKS.createStandingAndWallBlock("bland_incense", () -> new IncenseBlock(CProperties.INCENSE, CMobEffects.BLAND_SMOKE, 12371141), BLAND_WALL_INCENSE, Direction.DOWN);
    public static final DeferredBlock<Block> BRIGHT_WALL_INCENSE = BLOCKS.createBlockNoItem("bright_wall_incense", () -> new IncenseWallBlock(CProperties.INCENSE, CMobEffects.BRIGHT_SMOKE, 13547846));
    public static final DeferredBlock<Block> BRIGHT_INCENSE = BLOCKS.createStandingAndWallBlock("bright_incense", () -> new IncenseBlock(CProperties.INCENSE, CMobEffects.BRIGHT_SMOKE, 13547846), BRIGHT_WALL_INCENSE, Direction.DOWN);
    public static final DeferredBlock<Block> FRESH_WALL_INCENSE = BLOCKS.createBlockNoItem("fresh_wall_incense", () -> new IncenseWallBlock(CProperties.INCENSE, CMobEffects.FRESH_SMOKE, 6462696));
    public static final DeferredBlock<Block> FRESH_INCENSE = BLOCKS.createStandingAndWallBlock("fresh_incense", () -> new IncenseBlock(CProperties.INCENSE, CMobEffects.FRESH_SMOKE, 6462696), FRESH_WALL_INCENSE, Direction.DOWN);
    public static final DeferredBlock<Block> SWEET_WALL_INCENSE = BLOCKS.createBlockNoItem("sweet_wall_incense", () -> new IncenseWallBlock(CProperties.INCENSE, CMobEffects.SWEET_SMOKE, 14126028));
    public static final DeferredBlock<Block> SWEET_INCENSE = BLOCKS.createStandingAndWallBlock("sweet_incense", () -> new IncenseBlock(CProperties.INCENSE, CMobEffects.SWEET_SMOKE, 14126028), SWEET_WALL_INCENSE, Direction.DOWN);
    public static final DeferredBlock<Block> VERDANT_WALL_INCENSE = BLOCKS.createBlockNoItem("verdant_wall_incense", () -> new IncenseWallBlock(CProperties.INCENSE, CMobEffects.VERDANT_SMOKE, 7706194));
    public static final DeferredBlock<Block> VERDANT_INCENSE = BLOCKS.createStandingAndWallBlock("verdant_incense", () -> new IncenseBlock(CProperties.INCENSE, CMobEffects.VERDANT_SMOKE, 7706194), VERDANT_WALL_INCENSE, Direction.DOWN);

    public static final DeferredBlock<Block> WEIGHT_1S = BLOCKS.createBlock("weight_1s", () -> new InvarWeightBlock(CProperties.WEIGHT, 20, false));
    public static final DeferredBlock<Block> WEIGHT_5S = BLOCKS.createBlock("weight_5s", () -> new InvarWeightBlock(CProperties.WEIGHT, 100, false));
    public static final DeferredBlock<Block> WEIGHT_20S = BLOCKS.createBlock("weight_20s", () -> new InvarWeightBlock(CProperties.WEIGHT, 400, false));

    public static final DeferredBlock<Block> CONCUSSION_BOMB = BLOCKS.createBlock("concussion_bomb", () -> new ConcussionBombBlock(CProperties.CONCUSSION_BOMB));

    public static final DeferredBlock<Block> STONE_ASHLAR = BLOCKS.createBlock("stone_ashlar", () -> new AshlarBlock(CProperties.STONE_ASHLAR));
    public static final DeferredBlock<Block> DEEPSLATE_ASHLAR = BLOCKS.createBlock("deepslate_ashlar", () -> new AshlarBlock(CProperties.DEEPSLATE_ASHLAR));
    public static final DeferredBlock<Block> TUFF_ASHLAR = BLOCKS.createBlock("tuff_ashlar", () -> new AshlarBlock(CProperties.TUFF_ASHLAR));
    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_ASHLAR = BLOCKS.createBlock("polished_blackstone_ashlar", () -> new AshlarBlock(CProperties.POLISHED_BLACKSTONE_ASHLAR));
    public static final DeferredBlock<Block> END_STONE_ASHLAR = BLOCKS.createBlock("end_stone_ashlar", () -> new AshlarBlock(CProperties.END_STONE_ASHLAR));

    public static final DeferredBlock<Block> SMOOTH_STONE_BRICKS = BLOCKS.createBlock("smooth_stone_bricks", () -> new Block(CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> SMOOTH_STONE_BRICK_STAIRS = BLOCKS.createBlock("smooth_stone_brick_stairs", () -> new StairBlock(CBlocks.SMOOTH_STONE_BRICKS.get().defaultBlockState(), CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> SMOOTH_STONE_BRICK_SLAB = BLOCKS.createBlock("smooth_stone_brick_slab", () -> new SlabBlock(CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> SMOOTH_STONE_BRICK_WALL = BLOCKS.createBlock("smooth_stone_brick_wall", () -> new WallBlock(CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> SMOOTH_STONE_ASHLAR = BLOCKS.createBlock("smooth_stone_ashlar", () -> new AshlarBlock(CProperties.SMOOTH_STONE));

    public static final DeferredBlock<Block> FANCIED_OAK_PLANKS = BLOCKS.createBlock("fancied_oak_planks", () -> new VerticalConnectingPillarBlock(CProperties.OAK));
    public static final DeferredBlock<Block> FANCIED_SPRUCE_PLANKS = BLOCKS.createBlock("fancied_spruce_planks", () -> new VerticalConnectingPillarBlock(CProperties.SPRUCE));
    public static final DeferredBlock<Block> FANCIED_BIRCH_PLANKS = BLOCKS.createBlock("fancied_birch_planks", () -> new VerticalConnectingPillarBlock(CProperties.BIRCH));
    public static final DeferredBlock<Block> FANCIED_JUNGLE_PLANKS = BLOCKS.createBlock("fancied_jungle_planks", () -> new VerticalConnectingPillarBlock(CProperties.JUNGLE));
    public static final DeferredBlock<Block> FANCIED_ACACIA_PLANKS = BLOCKS.createBlock("fancied_acacia_planks", () -> new VerticalConnectingPillarBlock(CProperties.ACACIA));
    public static final DeferredBlock<Block> FANCIED_DARK_OAK_PLANKS = BLOCKS.createBlock("fancied_dark_oak_planks", () -> new VerticalConnectingPillarBlock(CProperties.DARK_OAK));
    public static final DeferredBlock<Block> FANCIED_MANGROVE_PLANKS = BLOCKS.createBlock("fancied_mangrove_planks", () -> new VerticalConnectingPillarBlock(CProperties.MANGROVE));
    public static final DeferredBlock<Block> FANCIED_CHERRY_PLANKS = BLOCKS.createBlock("fancied_cherry_planks", () -> new VerticalConnectingPillarBlock(CProperties.CHERRY));
    public static final DeferredBlock<Block> FANCIED_BAMBOO_PLANKS = BLOCKS.createBlock("fancied_bamboo_planks", () -> new VerticalConnectingPillarBlock(CProperties.BAMBOO));
    public static final DeferredBlock<Block> FANCIED_CRIMSON_PLANKS = BLOCKS.createBlock("fancied_crimson_planks", () -> new VerticalConnectingPillarBlock(CProperties.CRIMSON));
    public static final DeferredBlock<Block> FANCIED_WARPED_PLANKS = BLOCKS.createBlock("fancied_warped_planks", () -> new VerticalConnectingPillarBlock(CProperties.WARPED));

    public static final DeferredBlock<Block> LATERITE = BLOCKS.createBlock("laterite", () -> new Block(CProperties.LATERITE));
    public static final DeferredBlock<Block> LATERITE_BRICKS = BLOCKS.createBlock("laterite_bricks", () -> new Block(CProperties.LATERITE_BRICKS));
    public static final DeferredBlock<Block> LATERITE_BRICK_STAIRS = BLOCKS.createBlock("laterite_brick_stairs", () -> new StairBlock(CBlocks.LATERITE_BRICKS.get().defaultBlockState(), CProperties.LATERITE_BRICKS));
    public static final DeferredBlock<Block> LATERITE_BRICK_SLAB = BLOCKS.createBlock("laterite_brick_slab", () -> new SlabBlock(CProperties.LATERITE_BRICKS));
    public static final DeferredBlock<Block> LATERITE_BRICK_WALL = BLOCKS.createBlock("laterite_brick_wall", () -> new WallBlock(CProperties.LATERITE_BRICKS));

    public static final DeferredBlock<Block> SCULKY_COBBLED_DEEPSLATE = BLOCKS.createBlock("sculky_cobbled_deepslate", () -> new Block(CProperties.SCULKY_DEEPSLATE));
    public static final DeferredBlock<Block> SCULKY_COBBLED_DEEPSLATE_STAIRS = BLOCKS.createBlock("sculky_cobbled_deepslate_stairs", () -> new StairBlock(CBlocks.SCULKY_COBBLED_DEEPSLATE.get().defaultBlockState(), CProperties.SCULKY_DEEPSLATE));
    public static final DeferredBlock<Block> SCULKY_COBBLED_DEEPSLATE_SLAB = BLOCKS.createBlock("sculky_cobbled_deepslate_slab", () -> new SlabBlock(CProperties.SCULKY_DEEPSLATE));
    public static final DeferredBlock<Block> SCULKY_COBBLED_DEEPSLATE_WALL = BLOCKS.createBlock("sculky_cobbled_deepslate_wall", () -> new WallBlock(CProperties.SCULKY_DEEPSLATE));

    //vanilla compat
    public static final DeferredBlock<Block> QUARTZ_BRICK_SLAB = BLOCKS.createBlock("quartz_brick_slab", () -> new SlabBlock(CProperties.SMOOTH_STONE));

    public static final DeferredBlock<Block> SMOOTH_STONE_STAIRS = BLOCKS.createBlock("smooth_stone_stairs", () -> new StairBlock(Blocks.SMOOTH_STONE.defaultBlockState(), CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> CUT_SANDSTONE_STAIRS = BLOCKS.createBlock("cut_sandstone_stairs", () -> new StairBlock(Blocks.CUT_SANDSTONE.defaultBlockState(), CProperties.SANDSTONE));
    public static final DeferredBlock<Block> CUT_RED_SANDSTONE_STAIRS = BLOCKS.createBlock("cut_red_sandstone_stairs", () -> new StairBlock(Blocks.CUT_RED_SANDSTONE.defaultBlockState(), CProperties.RED_SANDSTONE));
    public static final DeferredBlock<Block> QUARTZ_BRICK_STAIRS = BLOCKS.createBlock("quartz_brick_stairs", () -> new StairBlock(Blocks.QUARTZ_BRICKS.defaultBlockState(), CProperties.SMOOTH_STONE));

    public static final DeferredBlock<Block> STONE_WALL = BLOCKS.createBlock("stone_wall", () -> new WallBlock(CProperties.STONE));
    public static final DeferredBlock<Block> SMOOTH_STONE_WALL = BLOCKS.createBlock("smooth_stone_wall", () -> new WallBlock(CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> POLISHED_GRANITE_WALL = BLOCKS.createBlock("polished_granite_wall", () -> new WallBlock(CProperties.GRANITE));
    public static final DeferredBlock<Block> POLISHED_DIORITE_WALL = BLOCKS.createBlock("polished_diorite_wall", () -> new WallBlock(CProperties.DIORITE));
    public static final DeferredBlock<Block> POLISHED_ANDESITE_WALL = BLOCKS.createBlock("polished_andesite_wall", () -> new WallBlock(CProperties.ANDESITE));
    public static final DeferredBlock<Block> CUT_SANDSTONE_WALL = BLOCKS.createBlock("cut_sandstone_wall", () -> new WallBlock(CProperties.SANDSTONE));
    public static final DeferredBlock<Block> CUT_RED_SANDSTONE_WALL = BLOCKS.createBlock("cut_red_sandstone_wall", () -> new WallBlock(CProperties.RED_SANDSTONE));
    public static final DeferredBlock<Block> PRISMARINE_BRICK_WALL = BLOCKS.createBlock("prismarine_brick_wall", () -> new WallBlock(CProperties.PRISMARINE_BRICKS));
    public static final DeferredBlock<Block> DARK_PRISMARINE_WALL = BLOCKS.createBlock("dark_prismarine_wall", () -> new WallBlock(CProperties.DARK_PRISMARINE));
    public static final DeferredBlock<Block> PURPUR_WALL = BLOCKS.createBlock("purpur_wall", () -> new WallBlock(CProperties.PURPUR));
    public static final DeferredBlock<Block> QUARTZ_WALL = BLOCKS.createBlock("quartz_wall", () -> new WallBlock(CProperties.QUARTZ));
    public static final DeferredBlock<Block> QUARTZ_BRICK_WALL = BLOCKS.createBlock("quartz_brick_wall", () -> new WallBlock(CProperties.QUARTZ));

    //atmospheric compat
    public static final DeferredBlock<Block> FANCIED_ASPEN_PLANKS = BLOCKS.createBlock("fancied_aspen_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.ASPEN_PLANKS));
    public static final DeferredBlock<Block> FANCIED_GRIMWOOD_PLANKS = BLOCKS.createBlock("fancied_grimwood_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.GRIMWOOD_PLANKS));
    public static final DeferredBlock<Block> FANCIED_KOUSA_PLANKS = BLOCKS.createBlock("fancied_kousa_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.KOUSA_PLANKS));
    public static final DeferredBlock<Block> FANCIED_LAUREL_PLANKS = BLOCKS.createBlock("fancied_laurel_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.LAUREL_PLANKS));
    public static final DeferredBlock<Block> FANCIED_MORADO_PLANKS = BLOCKS.createBlock("fancied_morado_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.MORADO_PLANKS));
    public static final DeferredBlock<Block> FANCIED_ROSEWOOD_PLANKS = BLOCKS.createBlock("fancied_rosewood_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.ROSEWOOD_PLANKS));
    public static final DeferredBlock<Block> FANCIED_YUCCA_PLANKS = BLOCKS.createBlock("fancied_yucca_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.YUCCA_PLANKS));

    public static final DeferredBlock<Block> FANCIED_MAPLE_PLANKS = BLOCKS.createBlock("fancied_maple_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.MAPLE_PLANKS));

    public static final DeferredBlock<Block> FANCIED_PINE_PLANKS = BLOCKS.createBlock("fancied_pine_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.PINE_PLANKS));
    public static final DeferredBlock<Block> FANCIED_PLUM_PLANKS = BLOCKS.createBlock("fancied_plum_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.PLUM_PLANKS));
    public static final DeferredBlock<Block> FANCIED_WILLOW_PLANKS = BLOCKS.createBlock("fancied_willow_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.WILLOW_PLANKS));
    public static final DeferredBlock<Block> FANCIED_WISTERIA_PLANKS = BLOCKS.createBlock("fancied_wisteria_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.WISTERIA_PLANKS));

    public static final DeferredBlock<Block> FANCIED_DRIFTWOOD_PLANKS = BLOCKS.createBlock("fancied_driftwood_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.DRIFTWOOD_PLANKS));
    public static final DeferredBlock<Block> FANCIED_RIVER_PLANKS = BLOCKS.createBlock("fancied_river_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.RIVER_PLANKS));

    public static final DeferredBlock<Block> FANCIED_POWDERY_PLANKS = BLOCKS.createBlock("fancied_powdery_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.POWDERY_PLANKS));

    public static final DeferredBlock<Block> FANCIED_SOULBLIGHT_PLANKS = BLOCKS.createBlock("fancied_soulblight_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.SOULBLIGHT_PLANKS));
    public static final DeferredBlock<Block> FANCIED_WHISTLECANE_PLANKS = BLOCKS.createBlock("fancied_whistlecane_planks", () -> new VerticalConnectingPillarBlock(CCompatProperties.WHISTLECANE_PLANKS));
    public static final class CProperties {
        public static final Properties ALUMINUM_ORE = getPropFrom(Blocks.GOLD_ORE).mapColor(MapColor.PODZOL);
        public static final Properties DEEPSLATE_ALUMINUM_ORE = getPropFrom(Blocks.DEEPSLATE_GOLD_ORE).mapColor(MapColor.PODZOL);
        public static final Properties NICKEL_ORE = getPropFrom(Blocks.IRON_ORE).mapColor(MapColor.TERRACOTTA_YELLOW);
        public static final Properties DEEPSLATE_NICKEL_ORE = getPropFrom(Blocks.DEEPSLATE_IRON_ORE).mapColor(MapColor.TERRACOTTA_YELLOW);

        public static final Properties ALUMINUM_BLOCK = getPropFrom(Blocks.COPPER_BLOCK).mapColor(MapColor.SNOW);
        public static final Properties RAW_ALUMINUM_BLOCK = getPropFrom(Blocks.RAW_COPPER_BLOCK).mapColor(MapColor.PODZOL);
        public static final Properties NICKEL_BLOCK = getPropFrom(Blocks.IRON_BLOCK).mapColor(MapColor.SAND);
        public static final Properties RAW_NICKEL_BLOCK = getPropFrom(Blocks.IRON_BLOCK).mapColor(MapColor.TERRACOTTA_YELLOW);
        public static final Properties INVAR_BLOCK = getPropFrom(Blocks.NETHERITE_BLOCK).mapColor(MapColor.STONE);

        public static final Properties ALUMINUM_GRATE = getPropFrom(Blocks.COPPER_GRATE).mapColor(MapColor.SNOW);
        public static final Properties ALUMINUM_BARS = getPropFrom(Blocks.IRON_BARS).sound(SoundType.CHAIN).mapColor(MapColor.SNOW);
        public static final Properties NICKEL_BARS = getPropFrom(Blocks.IRON_BARS).mapColor(MapColor.TERRACOTTA_YELLOW);

        public static final Properties REDSTONE_COMPONENTS = getPropFrom(Blocks.REPEATER);

        public static final Properties WEIGHT = getPropFrom(Blocks.STONE).sound(SoundType.NETHERITE_BLOCK);

        public static final Properties CAGE_LIGHT = getPropFrom(Blocks.LANTERN).lightLevel(
                state -> state.getValue(BlockStateProperties.LIT) ? state.getValue(CageLightBlock.LIGHT) : 0
        );
        public static final Properties SOUL_CAGE_LIGHT = getPropFrom(Blocks.SOUL_LANTERN).lightLevel(
                state -> state.getValue(BlockStateProperties.LIT) ? state.getValue(CageLightBlock.LIGHT) - 5 : 0
        );
        public static final Properties TILE_LIGHT = getPropFrom(Blocks.COPPER_BULB).lightLevel(state -> 14).mapColor(MapColor.SNOW);
        public static final Properties BIG_CHAIN = getPropFrom(Blocks.CHAIN);
        public static final Properties HEAVY_LANTERN = getPropFrom(Blocks.LANTERN).mapColor(MapColor.STONE);
        public static final Properties HEAVY_SOUL_LANTERN = getPropFrom(Blocks.SOUL_LANTERN).mapColor(MapColor.STONE);
        public static final Properties TIKI_TORCH = getPropFrom(Blocks.BAMBOO_FENCE).lightLevel(state -> 15);
        public static final Properties SOUL_TIKI_TORCH = TIKI_TORCH.lightLevel(state -> 10);

        public static final Properties INCENSE = getPropFrom(Blocks.TORCH).lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 5 : 0);

        public static final Properties LATERITE = getPropFrom(Blocks.PACKED_MUD).sound(SoundType.ROOTED_DIRT).mapColor(MapColor.TERRACOTTA_RED);
        public static final Properties LATERITE_BRICKS = getPropFrom(Blocks.MUD_BRICKS).mapColor(MapColor.TERRACOTTA_RED);

        public static final Properties SCULKY_DEEPSLATE = getPropFrom(Blocks.DEEPSLATE).sound(CSoundEvents.CSoundTypes.SCULKY_DEEPSLATE).mapColor(MapColor.COLOR_CYAN);

        public static final Properties CONCUSSION_BOMB = getPropFrom(Blocks.TNT).mapColor(MapColor.CRIMSON_HYPHAE);

        public static final Properties STONE_ASHLAR = getPropFrom(Blocks.STONE_BRICKS);
        public static final Properties DEEPSLATE_ASHLAR = getPropFrom(Blocks.DEEPSLATE_BRICKS);
        public static final Properties TUFF_ASHLAR = getPropFrom(Blocks.TUFF_BRICKS);
        public static final Properties POLISHED_BLACKSTONE_ASHLAR = getPropFrom(Blocks.POLISHED_BLACKSTONE_BRICKS);
        public static final Properties END_STONE_ASHLAR = getPropFrom(Blocks.END_STONE_BRICKS);

        public static final Properties STONE = getPropFrom(Blocks.STONE);
        public static final Properties SMOOTH_STONE = getPropFrom(Blocks.SMOOTH_STONE);
        public static final Properties GRANITE = getPropFrom(Blocks.GRANITE);
        public static final Properties DIORITE = getPropFrom(Blocks.DIORITE);
        public static final Properties ANDESITE = getPropFrom(Blocks.ANDESITE);
        public static final Properties SANDSTONE = getPropFrom(Blocks.SANDSTONE);
        public static final Properties RED_SANDSTONE = getPropFrom(Blocks.RED_SANDSTONE);
        public static final Properties PRISMARINE_BRICKS = getPropFrom(Blocks.PRISMARINE_BRICKS);
        public static final Properties DARK_PRISMARINE = getPropFrom(Blocks.DARK_PRISMARINE);
        public static final Properties PURPUR = getPropFrom(Blocks.PURPUR_BLOCK);
        public static final Properties QUARTZ = getPropFrom(Blocks.QUARTZ_BLOCK);

        public static final Properties OAK = getPropFrom(Blocks.OAK_PLANKS);
        public static final Properties SPRUCE = getPropFrom(Blocks.SPRUCE_PLANKS);
        public static final Properties BIRCH = getPropFrom(Blocks.BIRCH_PLANKS);
        public static final Properties JUNGLE = getPropFrom(Blocks.JUNGLE_PLANKS);
        public static final Properties ACACIA = getPropFrom(Blocks.ACACIA_PLANKS);
        public static final Properties DARK_OAK = getPropFrom(Blocks.DARK_OAK_PLANKS);
        public static final Properties MANGROVE = getPropFrom(Blocks.MANGROVE_PLANKS);
        public static final Properties CHERRY = getPropFrom(Blocks.CHERRY_PLANKS);
        public static final Properties BAMBOO = getPropFrom(Blocks.BAMBOO_PLANKS);
        public static final Properties CRIMSON = getPropFrom(Blocks.CRIMSON_PLANKS);
        public static final Properties WARPED = getPropFrom(Blocks.WARPED_PLANKS);

        public static Properties getPropFrom(BlockBehaviour block) {
            return Properties.ofLegacyCopy(block);
        }
    }

    public static final class CBlockSetTypes {
        public static final BlockSetType ALUMINIUM = new BlockSetType(
                "aluminum", true, true, false,
                BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.COPPER,
                SoundEvents.COPPER_DOOR_CLOSE, SoundEvents.COPPER_DOOR_OPEN,
                SoundEvents.COPPER_TRAPDOOR_CLOSE, SoundEvents.COPPER_TRAPDOOR_OPEN,
                SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
    }

    public static void setupTabEditors() {
        CreativeModeTabContentsPopulator.mod(Curiosities.MOD_ID)
                .tab(NATURAL_BLOCKS)
                .addItemsAfter(of(Items.DEEPSLATE_GOLD_ORE), ALUMINUM_ORE, DEEPSLATE_ALUMINUM_ORE, NICKEL_ORE, DEEPSLATE_NICKEL_ORE)
                .addItemsAfter(of(Items.RAW_GOLD_BLOCK), RAW_ALUMINUM_BLOCK, RAW_NICKEL_BLOCK)
                .addItemsBefore(of(Items.SAND), LATERITE)
                .tab(BUILDING_BLOCKS)
                .addItemsAfter(of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE), ALUMINUM_BLOCK, SHEET_METAL, ALUMINUM_GRATE, CUT_ALUMINUM, CUT_ALUMINUM_STAIRS, CUT_ALUMINUM_SLAB, ALUMINUM_BARS, ALUMINUM_DOOR, ALUMINUM_TRAPDOOR, NICKEL_BLOCK, NICKEL_BARS)
                .addItemsAfter(of(Items.DIAMOND_BLOCK), INVAR_BLOCK)
                .addItemsAfter(of(Items.CHAIN), BIG_CHAIN)
                .addItemsAfter(of(Items.SMOOTH_STONE_SLAB), SMOOTH_STONE_BRICKS, SMOOTH_STONE_ASHLAR, SMOOTH_STONE_BRICK_STAIRS, SMOOTH_STONE_BRICK_SLAB, SMOOTH_STONE_BRICK_WALL)
                .addItemsBefore(of(Items.STONE_BRICK_STAIRS), STONE_ASHLAR)
                .addItemsBefore(of(Items.DEEPSLATE_BRICK_STAIRS), DEEPSLATE_ASHLAR)
                .addItemsBefore(of(Items.TUFF_BRICK_STAIRS), TUFF_ASHLAR)
                .addItemsBefore(of(Items.END_STONE_BRICK_STAIRS), END_STONE_ASHLAR)
                .addItemsBefore(of(Items.POLISHED_BLACKSTONE_BRICK_STAIRS), POLISHED_BLACKSTONE_ASHLAR)
                .addItemsAfter(of(Items.OAK_FENCE_GATE), FANCIED_OAK_PLANKS)
                .addItemsAfter(of(Items.SPRUCE_FENCE_GATE), FANCIED_SPRUCE_PLANKS)
                .addItemsAfter(of(Items.BIRCH_FENCE_GATE), FANCIED_BIRCH_PLANKS)
                .addItemsAfter(of(Items.JUNGLE_FENCE_GATE), FANCIED_JUNGLE_PLANKS)
                .addItemsAfter(of(Items.ACACIA_FENCE_GATE), FANCIED_ACACIA_PLANKS)
                .addItemsAfter(of(Items.DARK_OAK_FENCE_GATE), FANCIED_DARK_OAK_PLANKS)
                .addItemsAfter(of(Items.MANGROVE_FENCE_GATE), FANCIED_MANGROVE_PLANKS)
                .addItemsAfter(of(Items.CHERRY_FENCE_GATE), FANCIED_CHERRY_PLANKS)
                .addItemsAfter(of(Items.BAMBOO_FENCE_GATE), FANCIED_BAMBOO_PLANKS)
                .addItemsAfter(of(Items.CRIMSON_FENCE_GATE), FANCIED_CRIMSON_PLANKS)
                .addItemsAfter(of(Items.WARPED_FENCE_GATE), FANCIED_WARPED_PLANKS)
                .addItemsBefore(of(Items.PACKED_MUD), LATERITE, LATERITE_BRICKS, LATERITE_BRICK_STAIRS, LATERITE_BRICK_SLAB, LATERITE_BRICK_WALL)
                //.addItemsAfter(of(Items.COBBLED_DEEPSLATE_WALL), SCULKY_COBBLED_DEEPSLATE, SCULKY_COBBLED_DEEPSLATE_STAIRS, SCULKY_COBBLED_DEEPSLATE_SLAB, SCULKY_COBBLED_DEEPSLATE_WALL)
                /* vanilla comp */
                .addItemsAfter(of(Items.STONE_SLAB), STONE_WALL)
                .addItemsAfter(of(Items.SMOOTH_STONE), SMOOTH_STONE_STAIRS).addItemsAfter(of(Items.SMOOTH_STONE_SLAB), SMOOTH_STONE_WALL)
                .addItemsAfter(of(Items.POLISHED_GRANITE_SLAB), POLISHED_GRANITE_WALL)
                .addItemsAfter(of(Items.POLISHED_DIORITE_SLAB), POLISHED_DIORITE_WALL)
                .addItemsAfter(of(Items.POLISHED_ANDESITE_SLAB), POLISHED_ANDESITE_WALL)
                .addItemsAfter(of(Items.CUT_SANDSTONE), CUT_SANDSTONE_STAIRS).addItemsAfter(of(Items.CUT_STANDSTONE_SLAB), CUT_SANDSTONE_WALL)
                .addItemsAfter(of(Items.CUT_RED_SANDSTONE), CUT_RED_SANDSTONE_STAIRS).addItemsAfter(of(Items.CUT_RED_SANDSTONE_SLAB), CUT_RED_SANDSTONE_WALL)
                .addItemsAfter(of(Items.PRISMARINE_BRICK_SLAB), PRISMARINE_BRICK_WALL)
                .addItemsAfter(of(Items.DARK_PRISMARINE_SLAB), DARK_PRISMARINE_WALL)
                .addItemsAfter(of(Items.QUARTZ_SLAB), QUARTZ_WALL)
                .addItemsAfter(of(Items.QUARTZ_BRICKS), QUARTZ_BRICK_STAIRS, QUARTZ_BRICK_SLAB, QUARTZ_BRICK_WALL)
                .addItemsAfter(of(Items.PURPUR_SLAB), PURPUR_WALL)
                // end
                .tab(FUNCTIONAL_BLOCKS)
                .addItemsBefore(of(Items.GLOWSTONE), TILE_LIGHT)
                .addItemsAfter(of(Items.CHAIN), TIKI_TORCH, SOUL_TIKI_TORCH, CAGE_LIGHT, SOUL_CAGE_LIGHT, HEAVY_LANTERN, HEAVY_SOUL_LANTERN, BIG_CHAIN)
                .addItemsAfter(of(Items.INFESTED_DEEPSLATE), ACRID_INCENSE, BLAND_INCENSE, BRIGHT_INCENSE, FRESH_INCENSE, SWEET_INCENSE, VERDANT_INCENSE)
                .tab(REDSTONE_BLOCKS)
                .addItemsAfter(of(Items.COMPARATOR), REDSTONE_DIODE, REDSTONE_FUSE, CAGE_LIGHT)
                .addItemsAfter(of(Items.NOTE_BLOCK), INVAR_BLOCK, WEIGHT_1S, WEIGHT_5S, WEIGHT_20S)
                .tab(TOOLS_AND_UTILITIES)
                .addItemsBefore(of(Items.GOAT_HORN), ACRID_INCENSE, BLAND_INCENSE, BRIGHT_INCENSE, FRESH_INCENSE, SWEET_INCENSE, VERDANT_INCENSE);
        CreativeModeTabContentsPopulator.mod(CConstants.ATMOSPHERIC + "_" + Curiosities.MOD_ID)
                .tab(BUILDING_BLOCKS)
                .addItemsAfter(ofID(CConstants.ASPEN_FENCE_GATE), FANCIED_ASPEN_PLANKS)
                .addItemsAfter(ofID(CConstants.GRIMWOOD_FENCE_GATE), FANCIED_GRIMWOOD_PLANKS)
                .addItemsAfter(ofID(CConstants.KOUSA_FENCE_GATE), FANCIED_KOUSA_PLANKS)
                .addItemsAfter(ofID(CConstants.LAUREL_FENCE_GATE), FANCIED_LAUREL_PLANKS)
                .addItemsAfter(ofID(CConstants.MORADO_FENCE_GATE), FANCIED_MORADO_PLANKS)
                .addItemsAfter(ofID(CConstants.ROSEWOOD_FENCE_GATE), FANCIED_ROSEWOOD_PLANKS)
                .addItemsAfter(ofID(CConstants.YUCCA_FENCE_GATE), FANCIED_YUCCA_PLANKS);
        CreativeModeTabContentsPopulator.mod(CConstants.AUTUMNITY + "_" + Curiosities.MOD_ID)
                .tab(BUILDING_BLOCKS)
                .addItemsAfter(ofID(CConstants.MAPLE_FENCE_GATE), FANCIED_MAPLE_PLANKS);
        CreativeModeTabContentsPopulator.mod(CConstants.ENVIRONMENTAL + "_" + Curiosities.MOD_ID)
                .tab(BUILDING_BLOCKS)
                .addItemsAfter(ofID(CConstants.PINE_FENCE_GATE), FANCIED_PINE_PLANKS)
                .addItemsAfter(ofID(CConstants.PLUM_FENCE_GATE), FANCIED_PLUM_PLANKS)
                .addItemsAfter(ofID(CConstants.WILLOW_FENCE_GATE), FANCIED_WILLOW_PLANKS)
                .addItemsAfter(ofID(CConstants.WISTERIA_FENCE_GATE), FANCIED_WISTERIA_PLANKS);
        CreativeModeTabContentsPopulator.mod(CConstants.UPGRADE_AQUATIC + "_" + Curiosities.MOD_ID)
                .tab(BUILDING_BLOCKS)
                .addItemsAfter(ofID(CConstants.DRIFTWOOD_FENCE_GATE), FANCIED_DRIFTWOOD_PLANKS)
                .addItemsAfter(ofID(CConstants.RIVER_FENCE_GATE), FANCIED_RIVER_PLANKS);
        CreativeModeTabContentsPopulator.mod(CConstants.MY_NETHERS_DELIGHT + "_" + Curiosities.MOD_ID)
                .tab(CConstants.MY_NETHERS_DELIGHT_TAB)
                .addItemsAfter(ofID(CConstants.POWDERY_FENCE_GATE), FANCIED_POWDERY_PLANKS);
        CreativeModeTabContentsPopulator.mod(CConstants.GARDENS_OF_THE_DEAD + "_" + Curiosities.MOD_ID)
                .tab(CConstants.GARDENS_OF_THE_DEAD_TAB)
                .addItemsAfter(ofID(CConstants.SOULBLIGHT_FENCE_GATE), FANCIED_SOULBLIGHT_PLANKS)
                .addItemsAfter(ofID(CConstants.WHISTLECANE_FENCE_GATE), FANCIED_WHISTLECANE_PLANKS);
    }

    public static Predicate<ItemStack> ofID(ResourceLocation location) {
        return stack -> of(BuiltInRegistries.ITEM.get(location)).test(stack);
    }
}
