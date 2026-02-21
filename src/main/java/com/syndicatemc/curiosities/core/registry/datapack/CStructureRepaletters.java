package com.syndicatemc.curiosities.core.registry.datapack;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.other.CConditions;
import com.syndicatemc.curiosities.core.registry.CBlocks;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.function.BiConsumer;

import static com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry.*;

public class CStructureRepaletters {
    public static final ResourceKey<StructureRepaletterEntry> INVAR_DECOR_BASTIONS = create("invar_bastion_decor");

    public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        HolderSet<Structure> bastionRemnant = holder(structures, BuiltinStructures.BASTION_REMNANT);
        context.register(INVAR_DECOR_BASTIONS, repalette().priority(50).repaletters(
                simple(Blocks.CHAIN, CBlocks.BIG_CHAIN.get()),
                simple(Blocks.LANTERN, CBlocks.HEAVY_LANTERN.get())
        ).select(bastionRemnant));
    }

    public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> builder) {
        builder.accept(INVAR_DECOR_BASTIONS, CConditions.INVAR_DECOR_BASTIONS);
    }

    private static ResourceKey<StructureRepaletterEntry> create(String name) {
        return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, Curiosities.location(name));
    }
}
