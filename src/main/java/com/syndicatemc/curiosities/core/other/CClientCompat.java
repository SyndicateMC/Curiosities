package com.syndicatemc.curiosities.core.other;

import com.syndicatemc.curiosities.client.render.entity.ConcussionBombRenderer;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.registry.CBlocks;
import com.syndicatemc.curiosities.core.registry.CEntityTypes;
import com.syndicatemc.curiosities.core.registry.CItems;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.syndicatemc.curiosities.core.registry.CBlocks.*;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = Curiosities.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CClientCompat {
    public static void registerCompat() {
        CItems.setupTabEditors();
        CBlocks.setupTabEditors();
        registerRenderLayers();
    }

    public static void registerRenderLayers() {
        for (DeferredBlock<?> block : new DeferredBlock[]{
                ALUMINUM_GRATE,
                ALUMINUM_BARS, NICKEL_BARS,
                REDSTONE_FUSE,
                CAGE_LIGHT, SOUL_CAGE_LIGHT,
                BIG_CHAIN, HEAVY_LANTERN, HEAVY_SOUL_LANTERN
        }) {
            ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
        }
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
//        event.registerItem(new IClientItemExtensions() {
//            @Override
//            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
//                return new InvarArmorModel<>(createLayerDefinition().bakeRoot(), slot);
//            }
//        }, CItems.INVAR_HELMET, CItems.INVAR_CHESTPLATE, CItems.INVAR_LEGGINGS, CItems.INVAR_BOOTS);
    }

    @SubscribeEvent
    public static void addItemTooltips(ItemTooltipEvent event) {
//        ItemStack item = event.getItemStack();
//
//        if (item.is(CItems.HEAVY_BOOTS)) {
//            List<Component> tooltip = event.getToolTip();
//            MutableComponent heavyBootsFallSpeed = Component.translatable("tooltip.curiosities.heavy_boots.exponential_fall_speed").withStyle(ChatFormatting.BLUE);
//            MutableComponent heavyBootsFallDamage = Component.translatable("tooltip.curiosities.heavy_boots.fall_damage").withStyle(ChatFormatting.BLUE);
//
//            tooltip.add(4, heavyBootsFallSpeed);
//            tooltip.add(5, heavyBootsFallDamage);
//        }
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CEntityTypes.CONCUSSION_BOMB.value(), ConcussionBombRenderer::new);
    }
}
