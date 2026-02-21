package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.common.block.*;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.helper.CBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

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

    public static final DeferredBlock<Block> WEIGHT_1S = BLOCKS.createBlock("weight_1s", () -> new InvarWeightBlock(CProperties.WEIGHT, 20, false));
    public static final DeferredBlock<Block> WEIGHT_5S = BLOCKS.createBlock("weight_5s", () -> new InvarWeightBlock(CProperties.WEIGHT, 100, false));
    public static final DeferredBlock<Block> WEIGHT_20S = BLOCKS.createBlock("weight_20s", () -> new InvarWeightBlock(CProperties.WEIGHT, 400, false));

    public static final DeferredBlock<Block> CONCUSSION_BOMB = BLOCKS.createBlock("concussion_bomb", () -> new ConcussionBombBlock(CProperties.CONCUSSION_BOMB));

    public static final DeferredBlock<Block> STONE_ASHLAR = BLOCKS.createBlock("stone_ashlar", () -> new AshlarBlock(CProperties.STONE_ASHLAR));
    public static final DeferredBlock<Block> DEEPSLATE_ASHLAR = BLOCKS.createBlock("deepslate_ashlar", () -> new AshlarBlock(CProperties.DEEPSLATE_ASHLAR));
    public static final DeferredBlock<Block> TUFF_ASHLAR = BLOCKS.createBlock("tuff_ashlar", () -> new AshlarBlock(CProperties.TUFF_ASHLAR));
    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_ASHLAR = BLOCKS.createBlock("polished_blackstone_ashlar", () -> new AshlarBlock(CProperties.POLISHED_BLACKSTONE_ASHLAR));

    public static final DeferredBlock<Block> SMOOTH_STONE_BRICKS = BLOCKS.createBlock("smooth_stone_bricks", () -> new Block(CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> SMOOTH_STONE_BRICK_STAIRS = BLOCKS.createBlock("smooth_stone_brick_stairs", () -> new StairBlock(CBlocks.SMOOTH_STONE_BRICKS.get().defaultBlockState(), CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> SMOOTH_STONE_BRICK_SLAB = BLOCKS.createBlock("smooth_stone_brick_slab", () -> new SlabBlock(CProperties.SMOOTH_STONE));
    public static final DeferredBlock<Block> SMOOTH_STONE_ASHLAR = BLOCKS.createBlock("smooth_stone_ashlar", () -> new AshlarBlock(CProperties.SMOOTH_STONE));

    public static final class CProperties {
        public static final BlockBehaviour.Properties ALUMINUM_ORE = BlockBehaviour.Properties.ofLegacyCopy(Blocks.GOLD_ORE).mapColor(MapColor.PODZOL);
        public static final BlockBehaviour.Properties DEEPSLATE_ALUMINUM_ORE = BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_GOLD_ORE).mapColor(MapColor.PODZOL);
        public static final BlockBehaviour.Properties NICKEL_ORE = BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_ORE).mapColor(MapColor.TERRACOTTA_YELLOW);
        public static final BlockBehaviour.Properties DEEPSLATE_NICKEL_ORE = BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_IRON_ORE).mapColor(MapColor.TERRACOTTA_YELLOW);

        public static final BlockBehaviour.Properties ALUMINUM_BLOCK = BlockBehaviour.Properties.ofLegacyCopy(Blocks.COPPER_BLOCK).mapColor(MapColor.SNOW);
        public static final BlockBehaviour.Properties RAW_ALUMINUM_BLOCK = BlockBehaviour.Properties.ofLegacyCopy(Blocks.RAW_COPPER_BLOCK).mapColor(MapColor.PODZOL);
        public static final BlockBehaviour.Properties NICKEL_BLOCK = BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK).mapColor(MapColor.SAND);
        public static final BlockBehaviour.Properties RAW_NICKEL_BLOCK = BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BLOCK).mapColor(MapColor.TERRACOTTA_YELLOW);
        public static final BlockBehaviour.Properties INVAR_BLOCK = BlockBehaviour.Properties.ofLegacyCopy(Blocks.NETHERITE_BLOCK).mapColor(MapColor.STONE);

        public static final BlockBehaviour.Properties ALUMINUM_GRATE = BlockBehaviour.Properties.ofLegacyCopy(Blocks.COPPER_GRATE).mapColor(MapColor.SNOW);
        public static final BlockBehaviour.Properties ALUMINUM_BARS = BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BARS).sound(SoundType.CHAIN).mapColor(MapColor.SNOW);
        public static final BlockBehaviour.Properties NICKEL_BARS = BlockBehaviour.Properties.ofLegacyCopy(Blocks.IRON_BARS).mapColor(MapColor.TERRACOTTA_YELLOW);

        public static final BlockBehaviour.Properties REDSTONE_COMPONENTS = BlockBehaviour.Properties.ofLegacyCopy(Blocks.REPEATER);

        public static final BlockBehaviour.Properties WEIGHT = BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE).sound(SoundType.NETHERITE_BLOCK);

        public static final BlockBehaviour.Properties CAGE_LIGHT = BlockBehaviour.Properties.ofLegacyCopy(Blocks.LANTERN).lightLevel(
                state -> state.getValue(BlockStateProperties.LIT) ? state.getValue(CageLightBlock.LIGHT) : 0
        );
        public static final BlockBehaviour.Properties SOUL_CAGE_LIGHT = BlockBehaviour.Properties.ofLegacyCopy(Blocks.SOUL_LANTERN).lightLevel(
                state -> state.getValue(BlockStateProperties.LIT) ? state.getValue(CageLightBlock.LIGHT) - 5 : 0
        );
        public static final BlockBehaviour.Properties TILE_LIGHT = BlockBehaviour.Properties.ofLegacyCopy(Blocks.COPPER_BULB).lightLevel(state -> 14).mapColor(MapColor.SNOW);
        public static final BlockBehaviour.Properties BIG_CHAIN = BlockBehaviour.Properties.ofLegacyCopy(Blocks.CHAIN);
        public static final BlockBehaviour.Properties HEAVY_LANTERN = BlockBehaviour.Properties.ofLegacyCopy(Blocks.LANTERN).mapColor(MapColor.STONE);
        public static final BlockBehaviour.Properties HEAVY_SOUL_LANTERN = BlockBehaviour.Properties.ofLegacyCopy(Blocks.SOUL_LANTERN).mapColor(MapColor.STONE);

        public static final BlockBehaviour.Properties CONCUSSION_BOMB = BlockBehaviour.Properties.ofLegacyCopy(Blocks.TNT).mapColor(MapColor.CRIMSON_HYPHAE);

        public static final BlockBehaviour.Properties STONE_ASHLAR = BlockBehaviour.Properties.ofLegacyCopy(Blocks.STONE_BRICKS);
        public static final BlockBehaviour.Properties DEEPSLATE_ASHLAR = BlockBehaviour.Properties.ofLegacyCopy(Blocks.DEEPSLATE_BRICKS);
        public static final BlockBehaviour.Properties TUFF_ASHLAR = BlockBehaviour.Properties.ofLegacyCopy(Blocks.TUFF_BRICKS);
        public static final BlockBehaviour.Properties POLISHED_BLACKSTONE_ASHLAR = BlockBehaviour.Properties.ofLegacyCopy(Blocks.POLISHED_BLACKSTONE_BRICKS);

        public static final BlockBehaviour.Properties SMOOTH_STONE = BlockBehaviour.Properties.ofLegacyCopy(Blocks.SMOOTH_STONE);
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
                .tab(BUILDING_BLOCKS)
                .addItemsAfter(of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE), ALUMINUM_BLOCK, SHEET_METAL, ALUMINUM_GRATE, CUT_ALUMINUM, CUT_ALUMINUM_STAIRS, CUT_ALUMINUM_SLAB, ALUMINUM_BARS, ALUMINUM_DOOR, ALUMINUM_TRAPDOOR, NICKEL_BLOCK, NICKEL_BARS)
                .addItemsAfter(of(Items.DIAMOND_BLOCK), INVAR_BLOCK)
                .addItemsAfter(of(Items.CHAIN), BIG_CHAIN)
                .addItemsAfter(of(Items.SMOOTH_STONE_SLAB), SMOOTH_STONE_BRICKS, SMOOTH_STONE_ASHLAR, SMOOTH_STONE_BRICK_STAIRS, SMOOTH_STONE_BRICK_SLAB)
                .addItemsBefore(of(Items.STONE_BRICK_STAIRS), STONE_ASHLAR)
                .addItemsBefore(of(Items.DEEPSLATE_BRICK_STAIRS), DEEPSLATE_ASHLAR)
                .addItemsBefore(of(Items.TUFF_BRICK_STAIRS), TUFF_ASHLAR)
                .addItemsBefore(of(Items.POLISHED_BLACKSTONE_BRICK_STAIRS), POLISHED_BLACKSTONE_ASHLAR)
                .tab(FUNCTIONAL_BLOCKS)
                .addItemsBefore(of(Items.GLOWSTONE), TILE_LIGHT)
                .addItemsAfter(of(Items.CHAIN), CAGE_LIGHT, SOUL_CAGE_LIGHT, HEAVY_LANTERN, HEAVY_SOUL_LANTERN, BIG_CHAIN)
                .tab(REDSTONE_BLOCKS)
                .addItemsAfter(of(Items.COMPARATOR), REDSTONE_DIODE, REDSTONE_FUSE, CAGE_LIGHT)
                .addItemsAfter(of(Items.NOTE_BLOCK), INVAR_BLOCK, WEIGHT_1S, WEIGHT_5S, WEIGHT_20S);
    }
}
