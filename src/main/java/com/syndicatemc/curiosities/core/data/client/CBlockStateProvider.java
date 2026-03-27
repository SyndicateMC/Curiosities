package com.syndicatemc.curiosities.core.data.client;

import com.syndicatemc.curiosities.common.block.AshlarBlock;
import com.syndicatemc.curiosities.common.block.RedstoneDiodeBlock;
import com.syndicatemc.curiosities.common.block.RedstoneFuseBlock;
import com.syndicatemc.curiosities.common.block.VerticalConnectingPillarBlock;
import com.syndicatemc.curiosities.core.Curiosities;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.syndicatemc.curiosities.core.registry.CBlocks.*;

public class CBlockStateProvider extends BlueprintBlockStateProvider {
    public CBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, Curiosities.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (DeferredBlock<?> block : new DeferredBlock[]{
                ALUMINUM_ORE, DEEPSLATE_ALUMINUM_ORE, NICKEL_ORE, DEEPSLATE_NICKEL_ORE,
                ALUMINUM_BLOCK, RAW_ALUMINUM_BLOCK, NICKEL_BLOCK, RAW_NICKEL_BLOCK, INVAR_BLOCK,
                ALUMINUM_GRATE, CUT_ALUMINUM,
                WEIGHT_1S, WEIGHT_5S, WEIGHT_20S,
                SMOOTH_STONE_BRICKS,

                LATERITE, LATERITE_BRICKS, LATERITE_TILES,
                SCULKY_COBBLED_DEEPSLATE
        }) {
            this.block(block);
        }
        logBlock(SHEET_METAL);

        ironBarsBlock(ALUMINUM_BARS);
        ironBarsBlock(NICKEL_BARS);

        slabBlock(CUT_ALUMINUM.get(), CUT_ALUMINUM_SLAB.get());
        stairsBlock(CUT_ALUMINUM.get(), CUT_ALUMINUM_STAIRS.get());
        slabBlock(SMOOTH_STONE_BRICKS.get(), SMOOTH_STONE_BRICK_SLAB.get());
        stairsBlock(SMOOTH_STONE_BRICKS.get(), SMOOTH_STONE_BRICK_STAIRS.get());
        wallBlock(SMOOTH_STONE_BRICKS.get(), SMOOTH_STONE_BRICK_WALL.get());
        slabBlock(LATERITE_BRICKS.get(), LATERITE_BRICK_SLAB.get());
        stairsBlock(LATERITE_BRICKS.get(), LATERITE_BRICK_STAIRS.get());
        wallBlock(LATERITE_BRICKS.get(), LATERITE_BRICK_WALL.get());
        slabBlock(LATERITE_TILES.get(), LATERITE_TILE_SLAB.get());
        stairsBlock(LATERITE_TILES.get(), LATERITE_TILE_STAIRS.get());
        wallBlock(LATERITE_TILES.get(), LATERITE_TILE_WALL.get());
        slabBlock(SCULKY_COBBLED_DEEPSLATE.get(), SCULKY_COBBLED_DEEPSLATE_SLAB.get());
        stairsBlock(SCULKY_COBBLED_DEEPSLATE.get(), SCULKY_COBBLED_DEEPSLATE_STAIRS.get());
        wallBlock(SCULKY_COBBLED_DEEPSLATE.get(), SCULKY_COBBLED_DEEPSLATE_WALL.get());

        //vanilla compat
        wallBlock(Blocks.STONE, STONE_WALL.get());

        stairsBlock(Blocks.SMOOTH_STONE, SMOOTH_STONE_STAIRS.get());
        wallBlock(Blocks.SMOOTH_STONE, SMOOTH_STONE_WALL.get());

        wallBlock(Blocks.POLISHED_GRANITE, POLISHED_GRANITE_WALL.get());
        wallBlock(Blocks.POLISHED_DIORITE, POLISHED_DIORITE_WALL.get());
        wallBlock(Blocks.POLISHED_ANDESITE, POLISHED_ANDESITE_WALL.get());

        stairsBlock((StairBlock)CUT_SANDSTONE_STAIRS.get(), Curiosities.location("block/cut_sandstone"));
        blockItem(CUT_SANDSTONE_STAIRS.get());
        wallBlock((WallBlock)CUT_SANDSTONE_WALL.get(), Curiosities.location("block/cut_sandstone"));
        itemModels().getBuilder(name(CUT_SANDSTONE_WALL.get())).parent(this.models().wallInventory(name(CUT_SANDSTONE_WALL.get()) + "_inventory", Curiosities.location("block/cut_sandstone")));
        stairsBlock((StairBlock)CUT_RED_SANDSTONE_STAIRS.get(), Curiosities.location("block/cut_red_sandstone"));
        blockItem(CUT_RED_SANDSTONE_STAIRS.get());
        wallBlock((WallBlock)CUT_RED_SANDSTONE_WALL.get(), Curiosities.location("block/cut_red_sandstone"));
        itemModels().getBuilder(name(CUT_RED_SANDSTONE_WALL.get())).parent(this.models().wallInventory(name(CUT_RED_SANDSTONE_WALL.get()) + "_inventory", Curiosities.location("block/cut_red_sandstone")));

        wallBlock(Blocks.PRISMARINE_BRICKS, PRISMARINE_BRICK_WALL.get());
        wallBlock(Blocks.DARK_PRISMARINE, DARK_PRISMARINE_WALL.get());

        wallBlock((WallBlock)QUARTZ_WALL.get(), ResourceLocation.withDefaultNamespace("block/quartz_block_top"));
        itemModels().getBuilder(name(QUARTZ_WALL.get())).parent(this.models().wallInventory(name(QUARTZ_WALL.get()) + "_inventory", ResourceLocation.withDefaultNamespace("block/quartz_block_top")));
        slabBlock(Blocks.QUARTZ_BRICKS, QUARTZ_BRICK_SLAB.get());
        stairsBlock(Blocks.QUARTZ_BRICKS, QUARTZ_BRICK_STAIRS.get());
        wallBlock(Blocks.QUARTZ_BRICKS, QUARTZ_BRICK_WALL.get());

        wallBlock(Blocks.PURPUR_BLOCK, PURPUR_WALL.get());
        //end

        redstoneDiodeBlock(REDSTONE_DIODE);
        redstoneFuseBlock(REDSTONE_FUSE);
        tikiTorchBlock(TIKI_TORCH, false);
        tikiTorchBlock(SOUL_TIKI_TORCH, true);

        cubeBottomTopBlock(CONCUSSION_BOMB);

        for (DeferredBlock<Block> block : new DeferredBlock[]{
                FANCIED_OAK_PLANKS, FANCIED_SPRUCE_PLANKS, FANCIED_BIRCH_PLANKS, FANCIED_JUNGLE_PLANKS, FANCIED_ACACIA_PLANKS, FANCIED_DARK_OAK_PLANKS, FANCIED_MANGROVE_PLANKS, FANCIED_CHERRY_PLANKS, FANCIED_BAMBOO_PLANKS, FANCIED_CRIMSON_PLANKS, FANCIED_WARPED_PLANKS
        }) {
            verticalConnectingPillarBlock(block);
        }

        for (DeferredBlock<Block> block : new DeferredBlock[]{
                STONE_ASHLAR, DEEPSLATE_ASHLAR, TUFF_ASHLAR, POLISHED_BLACKSTONE_ASHLAR, SMOOTH_STONE_ASHLAR, END_STONE_ASHLAR
        }) {
            ashlarBlock(block);
        }

        for (DeferredBlock<Block> block : new DeferredBlock[]{
                FANCIED_ASPEN_PLANKS, FANCIED_GRIMWOOD_PLANKS, FANCIED_KOUSA_PLANKS, FANCIED_LAUREL_PLANKS, FANCIED_MORADO_PLANKS, FANCIED_ROSEWOOD_PLANKS, FANCIED_YUCCA_PLANKS,
                FANCIED_MAPLE_PLANKS,
                FANCIED_PINE_PLANKS, FANCIED_PLUM_PLANKS, FANCIED_WILLOW_PLANKS, FANCIED_WISTERIA_PLANKS,
                FANCIED_DRIFTWOOD_PLANKS, FANCIED_RIVER_PLANKS,
                FANCIED_POWDERY_PLANKS,
                FANCIED_SOULBLIGHT_PLANKS, FANCIED_WHISTLECANE_PLANKS
        }) {
            fanciedPlanksCompat(block);
        }

        incenseBlock(ACRID_INCENSE, ACRID_WALL_INCENSE);
        incenseBlock(BLAND_INCENSE, BLAND_WALL_INCENSE);
        incenseBlock(BRIGHT_INCENSE, BRIGHT_WALL_INCENSE);
        incenseBlock(FRESH_INCENSE, FRESH_WALL_INCENSE);
        incenseBlock(SWEET_INCENSE, SWEET_WALL_INCENSE);
        incenseBlock(VERDANT_INCENSE, VERDANT_WALL_INCENSE);

        for (DeferredBlock<Block> block : new DeferredBlock[]{
                ACRID_CENSER, BLAND_CENSER, BRIGHT_CENSER, FRESH_CENSER, SWEET_CENSER, VERDANT_CENSER
        }) {
            censerBlock(block);
        }
    }

    private void redstoneDiodeBlock(DeferredBlock<?> block) {
        ModelFile.ExistingModelFile model = this.models().getExistingFile(Curiosities.location("block/redstone_diode"));
        ModelFile.ExistingModelFile modelOn = this.models().getExistingFile(Curiosities.location("block/redstone_diode_on"));

        MultiPartBlockStateBuilder builder = this.getMultipartBuilder(block.get());
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            int rotation = (int) (direction.toYRot() + 180) % 360;
            builder.part().modelFile(model).rotationY(rotation).addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(RedstoneDiodeBlock.POWERED, false);
            builder.part().modelFile(modelOn).rotationY(rotation).addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(RedstoneDiodeBlock.POWERED, true);
        }
        this.generatedItem(block.get(), "item");
    }

    private void redstoneFuseBlock(DeferredBlock<?> block) {
        ModelFile.ExistingModelFile model = this.models().getExistingFile(Curiosities.location("block/redstone_fuse"));
        ModelFile.ExistingModelFile modelOn = this.models().getExistingFile(Curiosities.location("block/redstone_fuse_on"));
        ModelFile.ExistingModelFile fuse = this.models().getExistingFile(Curiosities.location("block/redstone_fuse/fuse"));
        ModelFile.ExistingModelFile fuseBroken = this.models().getExistingFile(Curiosities.location("block/redstone_fuse/fuse_broken"));

        MultiPartBlockStateBuilder builder = this.getMultipartBuilder(block.get());

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            int rotation = (int) (direction.toYRot() + 180) % 360;
            builder.part().modelFile(model).rotationY(rotation).addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(RedstoneFuseBlock.POWERED, false);
            builder.part().modelFile(modelOn).rotationY(rotation).addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(RedstoneFuseBlock.POWERED, true);

            builder.part().modelFile(fuse).rotationY(rotation).addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(RedstoneFuseBlock.BROKEN, false);
            builder.part().modelFile(fuseBroken).rotationY(rotation).addModel().condition(HorizontalDirectionalBlock.FACING, direction).condition(RedstoneFuseBlock.BROKEN, true);

            for (int i = 1; i < 15; i++) {
                builder.part().modelFile(this.models().getExistingFile(Curiosities.location("block/redstone_fuse/slider_" + (i - 1)))).rotationY(rotation).addModel()
                        .condition(HorizontalDirectionalBlock.FACING, direction).condition(RedstoneFuseBlock.THRESHOLD, i);
            }
        }
        this.generatedItem(block.get(), "item");
    }

    public void ashlarBlock(DeferredBlock<?> object) {
        Block block = object.get();
        ModelFile lower = models().cubeBottomTop(name(block) + "_lower", suffix(blockTexture(block), "_lower"), suffix(blockTexture(block), "_bottom"), suffix(blockTexture(block), "_top"));
        ModelFile mid = models().cubeBottomTop(name(block) + "_mid", suffix(blockTexture(block), "_mid"), suffix(blockTexture(block), "_top"), suffix(blockTexture(block), "_bottom"));
        ModelFile upper = models().cubeBottomTop(name(block) + "_upper", suffix(blockTexture(block), "_upper"), suffix(blockTexture(block), "_bottom"), suffix(blockTexture(block), "_top"));

        this.getVariantBuilder(block).forAllStates((state) -> {
            int layer = state.getValue(AshlarBlock.LAYER);

            ModelFile model = null;
            if (layer == 1) model = lower;
            if (layer == 2) model = mid;
            if (layer == 3) model = upper;

            return ConfiguredModel.builder().modelFile(model).build();
        });
        this.simpleBlockItem(block, upper);
    }
    public void verticalConnectingPillarBlock(DeferredBlock<Block> object) {
        Block block = object.get();
        ModelFile normal = models().cubeColumn(name(block) + "_normal", suffix(blockTexture(block), "_normal"), suffix(blockTexture(block), "_top"));
        ModelFile bothConnected = models().cubeColumn(name(block) + "_both_connected", suffix(blockTexture(block), "_both_connected"), suffix(blockTexture(block), "_top"));
        ModelFile topConnected = models().cubeColumn(name(block) + "_top_connected", suffix(blockTexture(block), "_top_connected"), suffix(blockTexture(block), "_top"));
        ModelFile bottomConnected = models().cubeColumn(name(block) + "_bottom_connected", suffix(blockTexture(block), "_bottom_connected"), suffix(blockTexture(block), "_top"));
        ModelFile hNormal = models().cubeColumnHorizontal(name(block) + "_normal_horizontal", suffix(blockTexture(block), "_normal"), suffix(blockTexture(block), "_top"));
        ModelFile hBothConnected = models().cubeColumnHorizontal(name(block) + "_both_connected_horizontal", suffix(blockTexture(block), "_both_connected"), suffix(blockTexture(block), "_top"));
        ModelFile hTopConnected = models().cubeColumnHorizontal(name(block) + "_top_connected_horizontal", suffix(blockTexture(block), "_top_connected"), suffix(blockTexture(block), "_top"));
        ModelFile hBottomConnected = models().cubeColumnHorizontal(name(block) + "_bottom_connected_horizontal", suffix(blockTexture(block), "_bottom_connected"), suffix(blockTexture(block), "_top"));

        verticalConnectingPillarVariantBuilder(block, normal, hNormal, bothConnected, hBothConnected, topConnected, hTopConnected, bottomConnected, hBottomConnected);
        this.simpleBlockItem(block, normal);
    }
    private void fanciedPlanksCompat(DeferredBlock<Block> object) {
        Block block = object.get();
        ModelFile normal = models().cubeColumn("block/compat/" + name(block) + "_normal", suffix(blockRlWithCustomDir(block, "compat"), "_normal"), suffix(blockRlWithCustomDir(block, "compat"), "_top"));
        ModelFile bothConnected = models().cubeColumn("block/compat/" + name(block) + "_both_connected", suffix(blockRlWithCustomDir(block, "compat"), "_both_connected"), suffix(blockRlWithCustomDir(block, "compat"), "_top"));
        ModelFile topConnected = models().cubeColumn("block/compat/" + name(block) + "_top_connected", suffix(blockRlWithCustomDir(block, "compat"), "_top_connected"), suffix(blockRlWithCustomDir(block, "compat"), "_top"));
        ModelFile bottomConnected = models().cubeColumn("block/compat/" + name(block) + "_bottom_connected", suffix(blockRlWithCustomDir(block, "compat"), "_bottom_connected"), suffix(blockRlWithCustomDir(block, "compat"), "_top"));
        ModelFile hNormal = models().cubeColumnHorizontal("block/compat/" + name(block) + "_normal_horizontal", suffix(blockRlWithCustomDir(block, "compat"), "_normal"), suffix(blockRlWithCustomDir(block, "compat"), "_top"));
        ModelFile hBothConnected = models().cubeColumnHorizontal("block/compat/" + name(block) + "_both_connected_horizontal", suffix(blockRlWithCustomDir(block, "compat"), "_both_connected"), suffix(blockRlWithCustomDir(block, "compat"), "_top"));
        ModelFile hTopConnected = models().cubeColumnHorizontal("block/compat/" + name(block) + "_top_connected_horizontal", suffix(blockRlWithCustomDir(block, "compat"), "_top_connected"), suffix(blockRlWithCustomDir(block, "compat"), "_top"));
        ModelFile hBottomConnected = models().cubeColumnHorizontal("block/compat/" + name(block) + "_bottom_connected_horizontal", suffix(blockRlWithCustomDir(block, "compat"), "_bottom_connected"), suffix(blockRlWithCustomDir(block, "compat"), "_top"));

        verticalConnectingPillarVariantBuilder(block, normal, hNormal, bothConnected, hBothConnected, topConnected, hTopConnected, bottomConnected, hBottomConnected);
        this.simpleBlockItem(block, normal);
    }
    private ResourceLocation blockRlWithCustomDir(Block block, String dir) {
        ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "block/" + dir + "/" + name.getPath());
    }
    private void verticalConnectingPillarVariantBuilder(Block block, ModelFile normal, ModelFile hNormal, ModelFile bothConnected, ModelFile hBothConnected, ModelFile topConnected, ModelFile hTopConnected, ModelFile bottomConnected, ModelFile hBottomConnected) {
        this.getVariantBuilder(block).forAllStates(state -> {
            boolean top = state.getValue(VerticalConnectingPillarBlock.TOP_CONNECTED);
            boolean bottom = state.getValue(VerticalConnectingPillarBlock.BOTTOM_CONNECTED);
            Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);
            boolean yAxis = axis == Direction.Axis.Y;

            ModelFile model = null;
            if (!top && !bottom) model = yAxis ? normal : hNormal;
            if (top && !bottom) model = yAxis ? topConnected : hTopConnected;
            if (!top && bottom) model = yAxis ? bottomConnected : hBottomConnected;
            if (top && bottom) model = yAxis ? bothConnected : hBothConnected;

            if (axis == Direction.Axis.X) return ConfiguredModel.builder().modelFile(model).rotationX(90).rotationY(90).build();
            else if (axis == Direction.Axis.Y) return ConfiguredModel.builder().modelFile(model).build();
            else return ConfiguredModel.builder().modelFile(model).rotationX(90).build();
        });
    }

    private void tikiTorchBlock(DeferredBlock<?> object, boolean isSoul) {
        Block block = object.get();
        ModelFile.ExistingModelFile model = this.models().getExistingFile(Curiosities.location(isSoul ? "block/soul_tiki_torch" : "block/tiki_torch"));
        this.getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
        this.generatedItem(block, "item");
    }

    private void incenseBlock(DeferredBlock<?> incenseObject, DeferredBlock<?> wallIncenseObject) {
        Block incense = incenseObject.get();
        ModelFile incenseModel = this.models().withExistingParent(name(incense), Curiosities.location("block/templates/incense")).texture("incense", Curiosities.location("block/" + name(incense)));
        this.getVariantBuilder(incense).forAllStates(state -> ConfiguredModel.builder().modelFile(incenseModel).build());

        Block wallIncense = wallIncenseObject.get();
        ModelFile wallIncenseModel = this.models().withExistingParent(name(wallIncense), Curiosities.location("block/templates/incense_wall")).texture("incense", Curiosities.location("block/" + name(incense)));
        this.getVariantBuilder(wallIncense).forAllStates(state -> {
            Direction direction = state.getValue(HorizontalDirectionalBlock.FACING);
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder().modelFile(wallIncenseModel);
            switch (direction) {
                case NORTH:
                    builder.rotationY(270);
                    break;
                case SOUTH:
                    builder.rotationY(90);
                    break;
                case WEST:
                    builder.rotationY(180);
                    break;
            }
            return builder.build();
        });

        this.generatedItem(incense, "item");
    }
    private void censerBlock(DeferredBlock<?> censerObject) {
        Block censer = censerObject.get();
        ModelFile censerModel = this.models().withExistingParent(name(censer), Curiosities.location("block/templates/censer"))
                .texture("main", Curiosities.location("block/" + name(censer)))
                .texture("top", Curiosities.location("block/" + name(censer) + "_top"));
        ModelFile hangingCenserModel = this.models().withExistingParent(name(censer) + "_hanging", Curiosities.location("block/templates/hanging_censer"))
                .texture("main", Curiosities.location("block/" + name(censer)))
                .texture("top", Curiosities.location("block/" + name(censer) + "_top"))
                .texture("chain", Curiosities.location("block/censer_chain"));
        this.getVariantBuilder(censer).forAllStatesExcept(state -> {
            Boolean hanging = state.getValue(LanternBlock.HANGING);
            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder().modelFile(hanging ? hangingCenserModel : censerModel);
            return builder.build();
        }, BlockStateProperties.WATERLOGGED, BlockStateProperties.LIT);
        this.generatedItem(censer, "item");
    }
}
