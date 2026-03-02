package com.syndicatemc.curiosities.core.data.server;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class CAdvancementProvider implements AdvancementProvider.AdvancementGenerator {
    public static AdvancementProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        return new AdvancementProvider(output, provider, helper, List.of(new CAdvancementProvider()));
    }

    @Override
    public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {
        createAdvancement("invar_armor", "nether", ResourceLocation.withDefaultNamespace("nether/obtain_ancient_debris"), CItems.INVAR_CHESTPLATE.get(), AdvancementType.CHALLENGE, true, true, false)
                .rewards(AdvancementRewards.Builder.experience(75))
                .addCriterion("invar_armor", InventoryChangeTrigger.TriggerInstance.hasItems(CItems.INVAR_HELMET.get(), CItems.INVAR_CHESTPLATE.get(), CItems.INVAR_LEGGINGS.get(), CItems.INVAR_BOOTS.get()))
                .save(consumer, Curiosities.MOD_ID + ":nether/invar_armor");

        createAdvancement("aluminum_armor", "story", ResourceLocation.withDefaultNamespace("story/obtain_armor"), CItems.ALUMINUM_HELMET.get(), AdvancementType.TASK, true, true, false)
                .rewards(AdvancementRewards.Builder.experience(75))
                .addCriterion("aluminum_armor", InventoryChangeTrigger.TriggerInstance.hasItems(CItems.ALUMINUM_HELMET.get(), CItems.ALUMINUM_CHESTPLATE.get(), CItems.ALUMINUM_LEGGINGS.get(), CItems.ALUMINUM_BOOTS.get()))
                .save(consumer, Curiosities.MOD_ID + ":story/aluminum_armor");
    }

    private static Advancement.Builder createAdvancement(String name, String category, AdvancementHolder parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().parent(parent).display(icon,
                Component.translatable("advancements." + Curiosities.MOD_ID + "." + category + "." + name + ".title"),
                Component.translatable("advancements." + Curiosities.MOD_ID + "." + category + "." + name + ".description"),
                null, frame, showToast, announceToChat, hidden);
    }

    private static Advancement.Builder createAdvancement(String name, String category, ResourceLocation parent, ItemLike icon, AdvancementType frame, boolean showToast, boolean announceToChat, boolean hidden) {
        return createAdvancement(name, category, Advancement.Builder.advancement().build(parent), icon, frame, showToast, announceToChat, hidden);
    }
}
