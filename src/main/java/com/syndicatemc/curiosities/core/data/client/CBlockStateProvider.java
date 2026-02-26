package com.syndicatemc.curiosities.core.data.client;

import com.syndicatemc.curiosities.common.block.RedstoneDiodeBlock;
import com.syndicatemc.curiosities.common.block.RedstoneFuseBlock;
import com.syndicatemc.curiosities.common.block.TileLightBlock;
import com.syndicatemc.curiosities.core.Curiosities;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

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
                SMOOTH_STONE_BRICKS
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

        cubeBottomTopBlock(CONCUSSION_BOMB);

        for (DeferredBlock<?> block : new DeferredBlock[]{
                STONE_ASHLAR, DEEPSLATE_ASHLAR, TUFF_ASHLAR, POLISHED_BLACKSTONE_ASHLAR, SMOOTH_STONE_ASHLAR
        }) {
            this.simpleBlockItem(block.get(), new ModelFile.ExistingModelFile(Curiosities.location("block/ashlar/" + name(block.get()) + "_upper"), this.models().existingFileHelper));
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

    private void ashlarBlock(DeferredBlock<?> block) {

    }
}
