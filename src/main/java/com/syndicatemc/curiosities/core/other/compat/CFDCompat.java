package com.syndicatemc.curiosities.core.other.compat;

import com.syndicatemc.curiosities.core.registry.CItems;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Function;

public class CFDCompat {
    public static final Function<Item.Properties, ? extends Item> ALUMINUM_KNIFE_FACTORY = it ->
            new KnifeItem(CItems.ALUMINUM_TIER, it);
    public static final Function<Item.Properties, ? extends Item> INVAR_KNIFE_FACTORY = it ->
            new KnifeItem(CItems.INVAR_TIER, it);
}
