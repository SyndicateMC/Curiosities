package com.syndicatemc.curiosities.core;

import com.syndicatemc.curiosities.core.data.client.CBlockStateProvider;
import com.syndicatemc.curiosities.core.data.client.CItemModelProvider;
import com.syndicatemc.curiosities.core.data.client.CSpriteSourceProvider;
import com.syndicatemc.curiosities.core.data.server.*;
import com.syndicatemc.curiosities.core.data.server.tags.CBlockTagsProvider;
import com.syndicatemc.curiosities.core.data.server.tags.CItemTagsProvider;
import com.syndicatemc.curiosities.core.data.server.tags.CTrimMaterialTagsProvider;
import com.syndicatemc.curiosities.core.other.CClientCompat;
import com.syndicatemc.curiosities.core.other.CCompat;
import com.syndicatemc.curiosities.core.registry.*;
import com.syndicatemc.curiosities.core.registry.helper.CBlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

@Mod(Curiosities.MOD_ID)
public class Curiosities {
    public static final String MOD_ID = "curiosities";
    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MOD_ID, helper -> helper.putSubHelper(Registries.BLOCK, new CBlockSubRegistryHelper(helper)));
    public static final Logger LOGGER = LogManager.getLogger();

    public Curiosities(IEventBus bus, ModContainer container) {
        CBlocks.BLOCKS.register(bus);
        CItems.ITEMS.register(bus);
        CEntityTypes.ENTITY_TYPES.register(bus);
        CSoundEvents.SOUND_EVENTS.register(bus);
        CArmorMaterials.ARMOR_MATERIALS.register(bus);
        CFeatures.FEATURES.register(bus);
        CAttributes.ATTRIBUTES.register(bus);

        CConditionsSerializers.CONDITION_SERIALIZERS.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);

        container.registerConfig(ModConfig.Type.COMMON, CuriositiesConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(CCompat::registerCompat);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(CClientCompat::registerCompat);
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CDataPackProvider datapack = new CDataPackProvider(output, provider);

        boolean server = event.includeServer();
        CBlockTagsProvider blockTags = new CBlockTagsProvider(output, provider, helper);
        generator.addProvider(server, datapack);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new CItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
        generator.addProvider(server, new CTrimMaterialTagsProvider(output, provider, helper));
        generator.addProvider(server, new CLootTableProvider(output, provider));
        generator.addProvider(server, new CRecipeProvider(output, provider));
        generator.addProvider(server, CAdvancementProvider.create(output, provider, helper));
        generator.addProvider(server, new CDataRemolderProvider(output, provider));

        boolean client = event.includeClient();
        generator.addProvider(client, new CBlockStateProvider(output, helper));
        generator.addProvider(client, new CSpriteSourceProvider(output, provider, helper));
        generator.addProvider(client, new CItemModelProvider(output, helper));
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
