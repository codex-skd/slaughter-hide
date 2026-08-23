package com.skd.slaughterhide.tag;

import com.skd.slaughterhide.SlaughterHide;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/** Item tags the generic carcass handlers check. */
public final class ModItemTags {
    private ModItemTags() {
    }

    // Original handler tags
    public static final TagKey<Item> CLEAVER = create("cleaver");
    public static final TagKey<Item> SKINNING_KNIVES = create("skinning_knives");

    // Tier-specific repair tags
    public static final TagKey<Item> BONE_REPAIR_ITEMS = create("butcher_tool_repair_tier_0");
    public static final TagKey<Item> COPPER_REPAIR_ITEMS = create("butcher_tool_repair_tier_1");
    public static final TagKey<Item> IRON_REPAIR_ITEMS = create("butcher_tool_repair_tier_2");
    public static final TagKey<Item> GOLD_REPAIR_ITEMS = create("butcher_tool_repair_tier_3");
    public static final TagKey<Item> DIAMOND_REPAIR_ITEMS = create("butcher_tool_repair_tier_4");
    public static final TagKey<Item> NETHERITE_REPAIR_ITEMS = create("butcher_tool_repair_tier_5");

    private static TagKey<Item> create(String path) {
        return TagKey.create(Registries.ITEM, Identifier.parse(SlaughterHide.MOD_ID + ":" + path));
    }
}