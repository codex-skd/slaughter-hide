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

    public static final TagKey<Item> CLEAVER = create("cleaver");
    public static final TagKey<Item> SKINNING_KNIVES = create("skinning_knives");

    private static TagKey<Item> create(String path) {
        return TagKey.create(Registries.ITEM, Identifier.parse(SlaughterHide.MOD_ID + ":" + path));
    }
}