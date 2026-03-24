package com.syndicatemc.curiosities.core.mixin;

import com.syndicatemc.curiosities.core.registry.CMobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "eat", at = @At("HEAD"))
    private void curiosities$eaten(Level level, ItemStack food, FoodProperties foodProperties, CallbackInfoReturnable<ItemStack> cir) {
        Player player = (Player) (Object) this;
        if (player.hasEffect(CMobEffects.SWEET_SMOKE)) {
            int nutrition = foodProperties.nutrition();
            float saturation = foodProperties.saturation();
            float bonus = nutrition * 0.25F;
            player.heal(bonus);
            player.getFoodData().eat(Math.round(bonus), saturation * 0.1F);
        }
    }
}
