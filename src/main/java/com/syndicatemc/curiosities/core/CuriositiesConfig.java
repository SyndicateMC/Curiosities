package com.syndicatemc.curiosities.core;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class CuriositiesConfig {
    public static class Common {
        @ConfigKey("invar_decor_bastions")
        public final ConfigValue<Boolean> invarDecorBastions;

        @ConfigKey("heavy_boots_fall_speed")
        public final ConfigValue<Double> heavyBootsFallSpeed;
        @ConfigKey("heavy_boots_fall_speed_crouching")
        public final ConfigValue<Double> heavyBootsFallSpeedCrouching;
        @ConfigKey("heavy_boots_fall_speed_maximum")
        public final ConfigValue<Double> heavyBootsFallSpeedMaximum;

        public Common(ModConfigSpec.Builder builder) {
            builder.push("generation");
            builder.push("structures");
            this.invarDecorBastions = builder.comment("If Chains and Lanterns in Bastion Remnants are replaced by Big Chains and Heavy Lanterns").define("Invar Decor in Bastions", false);
            builder.pop();
            builder.pop();
            builder.push("items");
            builder.push("armor");
            this.heavyBootsFallSpeed = builder.comment("The exponential fall speed increase the Heavy Boots normally apply").define("Heavy Boots normal fall speed", 0.05D);
            this.heavyBootsFallSpeedCrouching = builder.comment("The exponential fall speed increase the Heavy Boots apply while crouching").define("Heavy Boots crouching fall speed", 0.1D);
            this.heavyBootsFallSpeedMaximum = builder.comment("The maximum downwards velocity before the Heavy Boots stop increasing fall speed").define("Heavy Boots maximum fall speed", 5.0D);
            builder.pop();
            builder.pop();
        }
    }

    public static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
