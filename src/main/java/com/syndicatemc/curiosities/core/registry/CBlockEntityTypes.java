package com.syndicatemc.curiosities.core.registry;

import com.syndicatemc.curiosities.common.entity.CenserBlockEntity;
import com.syndicatemc.curiosities.common.entity.IncenseBlockEntity;
import com.syndicatemc.curiosities.core.Curiosities;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public class CBlockEntityTypes {
    public static final BlockEntitySubRegistryHelper BLOCK_ENTITY_TYPES  = Curiosities.REGISTRY_HELPER.getBlockEntitySubHelper();

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<IncenseBlockEntity>> INCENSE = BLOCK_ENTITY_TYPES.createBlockEntity("incense", IncenseBlockEntity::new, () -> Set.of(
            CBlocks.ACRID_INCENSE.get(), CBlocks.BLAND_INCENSE.get(), CBlocks.BRIGHT_INCENSE.get(), CBlocks.FRESH_INCENSE.get(), CBlocks.SWEET_INCENSE.get(), CBlocks.VERDANT_INCENSE.get(),
            CBlocks.ACRID_WALL_INCENSE.get(), CBlocks.BLAND_WALL_INCENSE.get(), CBlocks.BRIGHT_WALL_INCENSE.get(), CBlocks.FRESH_WALL_INCENSE.get(), CBlocks.SWEET_WALL_INCENSE.get(), CBlocks.VERDANT_WALL_INCENSE.get()
    ));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CenserBlockEntity>> CENSER = BLOCK_ENTITY_TYPES.createBlockEntity("censer", CenserBlockEntity::new, () -> Set.of(
            CBlocks.ACRID_CENSER.get(), CBlocks.BLAND_CENSER.get(), CBlocks.BRIGHT_CENSER.get(), CBlocks.FRESH_CENSER.get(), CBlocks.SWEET_CENSER.get(), CBlocks.VERDANT_CENSER.get()
    ));
}
