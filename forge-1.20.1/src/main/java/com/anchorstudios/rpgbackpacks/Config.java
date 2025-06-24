package com.anchorstudios.rpgbackpacks;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {
        public final ForgeConfigSpec.IntValue manualUtcOffset;
        public final ForgeConfigSpec.IntValue smoothTransitionDuration;
        public final ForgeConfigSpec.BooleanValue useServerTime;
        public final ForgeConfigSpec.BooleanValue smoothTimeTransition;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("Day Length Settings");

            useServerTime = builder
                    .comment("Use server time instead of system clock.")
                    .define("useServerTime", true);

            manualUtcOffset = builder
                    .comment("Sets your custom time zone offset (in hours) relative to UTC (Coordinated Universal Time). This is only used if useServerTime is set to false. It lets you simulate real-time sync based on any time zone, regardless of your server or system clock.")
                    .defineInRange("manualUtcOffset", 0, -12, 14);

            smoothTimeTransition = builder
                    .comment("Controls whether time changes — such as switching time zones, enabling real-time sync or updating custom day length — happen instantly or gradually.")
                    .define("smoothTimeTransition", true);

            smoothTransitionDuration = builder
                    .comment("How many real-world seconds the transition to a new time should take when smoothTimeTransition is true.")
                    .defineInRange("smoothTransitionDuration", 10, 1, Integer.MAX_VALUE);

            builder.pop();
        }
    }
}