package com.syndicatemc.curiosities.core.other;

import com.syndicatemc.curiosities.common.block.IncenseBlock;
import com.syndicatemc.curiosities.common.block.IncenseWallBlock;
import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.CuriositiesConfig;
import com.syndicatemc.curiosities.core.other.tags.CItemTags;
import com.syndicatemc.curiosities.core.registry.CAttributes;
import com.syndicatemc.curiosities.core.registry.CItems;
import com.syndicatemc.curiosities.core.registry.CMobEffects;
import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import com.syndicatemc.curiosities.core.registry.datapack.CDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import javax.swing.plaf.PanelUI;

import java.util.Collection;

import static net.neoforged.neoforge.event.entity.living.MobEffectEvent.Applicable.Result.DO_NOT_APPLY;

@EventBusSubscriber(modid = Curiosities.MOD_ID)
public class CEvents {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void addItemAttributes(ItemAttributeModifierEvent event) {
        ItemStack item = event.getItemStack();

        if (item.is(CItemTags.ALUMINUM_TOOLS)) {
            event.addModifier(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(Curiosities.location("block_interaction_range"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
            event.addModifier(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Curiosities.location("entity_interaction_range"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        }
        if (item.is(CItemTags.INVAR_TOOLS)) {
            event.addModifier(CAttributes.ARMOR_PIERCING, new AttributeModifier(Curiosities.location("armor_piercing"), 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND);
        }
        if (item.is(CItemTags.TOPAZ_TOOLS)) {
            event.addModifier(CAttributes.TEMPO, new AttributeModifier(Curiosities.location("tempo"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
        }
    }

    @SubscribeEvent
    public static void onEntityDamagePre(LivingDamageEvent.Pre event) {
        LivingEntity reciever = event.getEntity();
        DamageSource source = event.getSource();
        Entity sourceEntity = source.getEntity();
        Level level = reciever.level();
        float originalDamage = event.getOriginalDamage();
        float modifiedDamage = event.getNewDamage();
        float reductionPercent = modifiedDamage / originalDamage;

        applyTempoDamage(event, sourceEntity, source, modifiedDamage, reductionPercent);
        applyAcridDamage(event, sourceEntity, source, modifiedDamage);
        calcFreshSmokeDamageRes(event, reciever, source, modifiedDamage);
        calcHeavyBootsFallDamage(event, reciever, source, modifiedDamage);
        calcDamageReduction(event, reciever, source, modifiedDamage);
        calcArmorPiercing(event, sourceEntity, originalDamage, modifiedDamage);
    }

    private static void applyTempoDamage(LivingDamageEvent.Pre event, Entity sourceEntity, DamageSource source, float modifiedDamage, float reductionPercent) {
        if (sourceEntity instanceof Player player && CUtils.canApplyTempoEffects(player) && source.is(DamageTypes.PLAYER_ATTACK)) {
            float newDamage = modifiedDamage + (CUtils.getCurrentTempo(player) / 2.0F) * reductionPercent;
            CUtils.applyTempo(player);
            event.setNewDamage(newDamage);
        }
    }

    private static void applyAcridDamage(LivingDamageEvent.Pre event, Entity sourceEntity, DamageSource source, float modifiedDamage) {
        if (sourceEntity instanceof Player player && player.hasEffect(CMobEffects.ACRID_SMOKE) && source.is(DamageTypes.PLAYER_ATTACK)) {
            float newDamage = modifiedDamage + modifiedDamage * 0.1F * (player.getEffect(CMobEffects.ACRID_SMOKE).getAmplifier() + 1.0F);
            player.heal(modifiedDamage * 0.1F);
            event.setNewDamage(newDamage);
        }
    }

    private static void calcFreshSmokeDamageRes(LivingDamageEvent.Pre event, LivingEntity reciever, DamageSource source, float modifiedDamage) {
        if (reciever instanceof Player player && player.hasEffect(CMobEffects.FRESH_SMOKE) && (!source.is(DamageTypeTags.BYPASSES_ARMOR) || source.is(DamageTypeTags.IS_FALL)) && player.isInWaterOrRain()) {
            float newDamage = modifiedDamage * 0.80F;
            event.setNewDamage(newDamage);
        }
    }

    private static void calcHeavyBootsFallDamage(LivingDamageEvent.Pre event, LivingEntity reciever, DamageSource source, float modifiedDamage) {
        if (reciever.getItemBySlot(EquipmentSlot.FEET).is(CItems.HEAVY_BOOTS) && source.is(DamageTypeTags.IS_FALL)) {
            float newDamage = modifiedDamage / 2 - 2;
            event.setNewDamage(newDamage);
        }
    }

    private static void calcArmorPiercing(LivingDamageEvent.Pre event, Entity sourceEntity, float originalDamage, float modifiedDamage) {
        if (sourceEntity instanceof LivingEntity attacker && attacker.getAttributes().hasAttribute(CAttributes.ARMOR_PIERCING)) {
            float armorPiercing = (float)attacker.getAttributes().getValue(CAttributes.ARMOR_PIERCING);
            if (armorPiercing > 1) {
                float armorPiercingDamage = (originalDamage - modifiedDamage) * (armorPiercing - 1);
                float newDamage = modifiedDamage + armorPiercingDamage;
                event.setNewDamage(newDamage);
            }
        }
    }

    private static void calcDamageReduction(LivingDamageEvent.Pre event, LivingEntity reciever, DamageSource source, float modifiedDamage) {
        if (reciever.getAttributes().hasAttribute(CAttributes.DAMAGE_REDUCTION) && (!source.is(DamageTypeTags.BYPASSES_ARMOR) || source.is(DamageTypeTags.IS_FALL))) {
            if (reciever.getAttributes().getValue(CAttributes.DAMAGE_REDUCTION) > 0) {
                float damageReduction = (float) reciever.getAttributes().getValue(CAttributes.DAMAGE_REDUCTION);
                float newDamage = modifiedDamage - damageReduction;
                if (newDamage <= 0) newDamage = 0;
                event.setNewDamage(newDamage);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityHeal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();
        float amount = event.getAmount();
        if (entity instanceof Player player && player.hasEffect(CMobEffects.VERDANT_SMOKE))
            event.setAmount(amount * (1.0F + 0.25F * (player.getEffect(CMobEffects.VERDANT_SMOKE).getAmplifier() + 1)));
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeModificationEvent event) {
        for (var entityType : event.getTypes()) {
            if (event.has(entityType, Attributes.ARMOR)) {
                event.add(entityType, CAttributes.DAMAGE_REDUCTION);
                event.add(entityType, CAttributes.IMMUTABILITY);
            }
            if (event.has(entityType, Attributes.ATTACK_DAMAGE)) event.add(entityType, CAttributes.ARMOR_PIERCING);
            if (event.has(entityType, Attributes.BLOCK_BREAK_SPEED)) event.add(entityType, CAttributes.TEMPO);
        }
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (player.getItemBySlot(EquipmentSlot.FEET).is(CItems.HEAVY_BOOTS) && !player.isFallFlying()) {
            Vec3 speed = player.getDeltaMovement();
            double speedMod = player.isCrouching() ? CuriositiesConfig.COMMON.heavyBootsFallSpeedCrouching.get() : CuriositiesConfig.COMMON.heavyBootsFallSpeed.get();
            Vec3 addedSpeed = new Vec3(0, speed.y * speedMod, 0.0D);
            if (speed.y < -0.1D && speed.y > -CuriositiesConfig.COMMON.heavyBootsFallSpeedMaximum.get()) player.addDeltaMovement(addedSpeed);
        }
        if (player.hasEffect(CMobEffects.BLAND_SMOKE)) {
            Collection<MobEffectInstance> effects = player.getActiveEffects();
            for (MobEffectInstance effectInstance : effects) {
                Holder<MobEffect> effect = effectInstance.getEffect();
                for (String effectId : CuriositiesConfig.COMMON.blandIncenseAffectedEffects.get()) {
                    ResourceLocation affectedEffect = CUtils.getLocFromID(effectId);
                    if (effect.is(affectedEffect)) {
                        player.heal(1.0F * (player.getEffect(CMobEffects.BLAND_SMOKE).getAmplifier() + 1));
                        player.removeEffect(effect);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.REPEATER)) {
            int flag = state.getValue(RepeaterBlock.DELAY);
            if (flag == 4) {
                level.playSeededSound(null, pos.getCenter().x, pos.getCenter().y, pos.getCenter().z, CSoundEvents.FUSE_SLIDER, SoundSource.BLOCKS, 0.5F, 0.75F, 1);
            } else {
                level.playSeededSound(null, pos.getCenter().x, pos.getCenter().y, pos.getCenter().z, CSoundEvents.FUSE_SLIDER, SoundSource.BLOCKS, 0.5F, 0.75F + flag * 0.1F, 1);
            }
        }
    }

    @SubscribeEvent
    public static void playerBreakBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack stack = player.getMainHandItem();
        BlockState state = event.getState();
        if (toolDropCorrectTopaz(stack, state)) CUtils.applyTempo(player);
    }

    @SubscribeEvent
    public static void playerBreakSpeed(PlayerEvent.BreakSpeed event) {
        float breakSpeed = event.getNewSpeed();
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        BlockState state = event.getState();
        if (toolDropCorrectTopaz(stack, state)) {
            float modifier = stack.getDestroySpeed(state) * CUtils.getCurrentTempo(player) * (stack.canPerformAction(ItemAbilities.SHOVEL_DIG) ? 0.5F : 1.0F);
            event.setNewSpeed(breakSpeed + modifier);
        }
    }

    @SubscribeEvent
    public static void onExperienceCollect(PlayerXpEvent.PickupXp event) {
        Player player = event.getEntity();
        ExperienceOrb orb = event.getOrb();
        int exp = orb.getValue();
        if (player.hasEffect(CMobEffects.BRIGHT_SMOKE)) {
            player.heal(exp * 0.1F * (player.getEffect(CMobEffects.BRIGHT_SMOKE).getAmplifier() + 1));
        }
    }

    @SubscribeEvent
    public static void experienceChange(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity();
        int exp = event.getAmount();
        if (player.hasEffect(CMobEffects.BRIGHT_SMOKE)) {
            event.setAmount(Math.round(exp + exp * 0.1F * (player.getEffect(CMobEffects.BRIGHT_SMOKE).getAmplifier() + 1)));
        }
    }

    @SubscribeEvent
    public static void onEffectGain(MobEffectEvent.Applicable event) {
        LivingEntity entity = event.getEntity();
        Holder<MobEffect> effect = event.getEffectInstance().getEffect();
        if (entity instanceof Player player && player.hasEffect(CMobEffects.BLAND_SMOKE)) {
            for (String effectId : CuriositiesConfig.COMMON.blandIncenseAffectedEffects.get()) {
                ResourceLocation affectedEffect = CUtils.getLocFromID(effectId);
                if (effect.is(affectedEffect)) {
                    player.heal(1.0F * (player.getEffect(CMobEffects.BLAND_SMOKE).getAmplifier() + 1));
                    event.setResult(DO_NOT_APPLY);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        BlockState state = event.getPlacedBlock();
        BlockPos pos = event.getPos();
        Level level = (Level) event.getLevel();
        if (entity instanceof Player player) {
            ItemStack stack = player.getOffhandItem();
            Block block = state.getBlock();
            if (stack.getItem() instanceof FlintAndSteelItem && (block instanceof IncenseBlock || block instanceof IncenseWallBlock)) {
                stack.hurtAndBreak(1, player, EquipmentSlot.OFFHAND);
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                level.setBlock(pos, state.setValue(IncenseBlock.LIT, true), 11);
                level.scheduleTick(pos, block, 0);
            }
        }
    }

    private static boolean toolDropCorrectTopaz(ItemStack stack, BlockState state) {
        return stack.is(CItemTags.TOPAZ_TOOLS) && stack.isCorrectToolForDrops(state);
    }
}
