package com.skd.slaughterhide.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.ItemLike;

/**
 * Simple butchery tool shared by the cleaver and the skinning knife.
 * Behaves like a sword that never combines damaged stacks, matching the
 * original Butchery tools (which are only ever used to cut carcasses).
 */
public class ButcherToolItem extends Item {

    public static final TagKey<Item> REPAIR_ITEMS = TagKey.create(
            Registries.ITEM, Identifier.parse("slaughter_hide:butcher_tool_repair_items"));

    private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL, 250, 6.0f, 0.0f, 14, REPAIR_ITEMS);

    public ButcherToolItem(Item.Properties properties) {
        super(properties.sword(TOOL_MATERIAL, 4.0f, -2.2f).setNoCombineRepair());
    }

    @Override
    public ItemStackTemplate getCraftingRemainder(ItemInstance itemInstance) {
        ItemStack retval = new ItemStack((ItemLike) this);
        retval.setDamageValue(itemInstance.getOrDefault(DataComponents.DAMAGE, 0) + 1);
        if (retval.getDamageValue() >= retval.getMaxDamage()) {
            return null;
        }
        return ItemStackTemplate.fromNonEmptyStack(retval);
    }
}