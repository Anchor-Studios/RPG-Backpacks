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
        public final ForgeConfigSpec.IntValue leatherBackpackSlots;
        public final ForgeConfigSpec.IntValue ironBackpackSlots;
        public final ForgeConfigSpec.IntValue goldenBackpackSlots;
        public final ForgeConfigSpec.IntValue diamondBackpackSlots;
        public final ForgeConfigSpec.IntValue netheriteBackpackSlots;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("Backpack Inventory Sizes");

            leatherBackpackSlots = builder
                    .comment("Number of inventory slots in the Leather Backpack")
                    .defineInRange("leatherBackpackSlots", 27, 1, 999);

            ironBackpackSlots = builder
                    .comment("Number of inventory slots in the Iron Backpack")
                    .defineInRange("ironBackpackSlots", 36, 1, 999);

            goldenBackpackSlots = builder
                    .comment("Number of inventory slots in the Golden Backpack")
                    .defineInRange("goldenBackpackSlots", 45, 1, 999);

            diamondBackpackSlots = builder
                    .comment("Number of inventory slots in the Diamond Backpack")
                    .defineInRange("diamondBackpackSlots", 60, 1, 999);

            netheriteBackpackSlots = builder
                    .comment("Number of inventory slots in the Netherite Backpack")
                    .defineInRange("netheriteBackpackSlots", 77, 1, 999);

            builder.pop();
        }
    }
}