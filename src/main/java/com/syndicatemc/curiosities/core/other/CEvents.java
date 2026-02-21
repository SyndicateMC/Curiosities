package com.syndicatemc.curiosities.core.other;

import com.syndicatemc.curiosities.core.Curiosities;
import com.syndicatemc.curiosities.core.CuriositiesConfig;
import com.syndicatemc.curiosities.core.other.tags.CItemTags;
import com.syndicatemc.curiosities.core.registry.CAttributes;
import com.syndicatemc.curiosities.core.registry.CItems;
import com.syndicatemc.curiosities.core.registry.CSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

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
            event.addModifier(CAttributes.ARMOR_PIERCING, new AttributeModifier(Curiosities.location("armor_piercing"), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), EquipmentSlotGroup.MAINHAND);
        }
    }

    @SubscribeEvent
    public static void onEntityDamage(LivingDamageEvent.Pre event) {
        LivingEntity reciever = event.getEntity();
        DamageSource source = event.getSource();
        Entity sourceEntity = source.getEntity();
        Level level = event.getEntity().level();

        if (reciever.getItemBySlot(EquipmentSlot.FEET).is(CItems.HEAVY_BOOTS) && source.is(DamageTypeTags.IS_FALL)) {
            float originalDamage = event.getNewDamage();
            float newDamage = originalDamage / 2 - 2;

            event.setNewDamage(newDamage);
        }
        if (reciever.getAttributes().hasAttribute(CAttributes.DAMAGE_REDUCTION) && (!source.is(DamageTypeTags.BYPASSES_ARMOR) || source.is(DamageTypeTags.IS_FALL))) {
            if (reciever.getAttributes().getValue(CAttributes.DAMAGE_REDUCTION) > 0) {
                float originalDamage = event.getNewDamage();
                float damageReduction = (float) reciever.getAttributes().getValue(CAttributes.DAMAGE_REDUCTION);
                float newDamage = originalDamage - damageReduction;
                float pitchChange = 0.9F + level.random.nextFloat() * 0.2F;

                if (newDamage <= 0) {
                    event.setNewDamage(0);
                    level.playSound(null, reciever.getX(), reciever.getY(), reciever.getZ(), CSoundEvents.DAMAGE_REDUCE_ALL, SoundSource.PLAYERS, 0.2F, pitchChange);
                } else {
                    event.setNewDamage(newDamage);
                    level.playSound(null, reciever.getX(), reciever.getY(), reciever.getZ(), CSoundEvents.DAMAGE_REDUCTION, SoundSource.PLAYERS, 0.2F, pitchChange);
                }
            }
        }
        if (sourceEntity instanceof LivingEntity attacker && attacker.getAttributes().hasAttribute(CAttributes.ARMOR_PIERCING)) {
            float armorPiercing = (float)attacker.getAttributes().getValue(CAttributes.ARMOR_PIERCING);
            if (armorPiercing > 1) {
                float originalDamage = event.getNewDamage();
                float originalDamageNoRes = event.getOriginalDamage();
                float armorPiercingDamage = (originalDamageNoRes - originalDamage) * (armorPiercing - 1);
                float newDamage = originalDamage + armorPiercingDamage;
                event.setNewDamage(newDamage);
            }
        }
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeModificationEvent event) {
        for (var entityType : event.getTypes()) {
            if (event.has(entityType, Attributes.ARMOR)) {
                event.add(entityType, CAttributes.DAMAGE_REDUCTION);
            }
            event.add(entityType, CAttributes.ARMOR_PIERCING);
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

//        for (String name : new String[]{
//                "whalter3", "thewillzi11a3000"
//        }) {
//            if (player.getName().getString().equals(name)) {
//                LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
//                bolt.setPos(player.getX(), player.getY(), player.getZ());
//                level.addFreshEntity(bolt);
//                player.hurt(level.damageSources().source(DamageTypes.FELL_OUT_OF_WORLD), 1);
//            }
//        }
    }

    @SubscribeEvent
    public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock event) {
        BlockPos pos = event.getPos();
        Level level = event.getLevel();
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.REPEATER)) {
            int flag = state.getValue(RepeaterBlock.DELAY);
            if (flag == 4) {
                level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_SLIDER, SoundSource.BLOCKS, 2.0F, 0.75F, 1);
            } else {
                level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(), CSoundEvents.FUSE_SLIDER, SoundSource.BLOCKS, 2.0F, 0.75F + flag * 0.1F, 1);
            }
        }
    }
}
