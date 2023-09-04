package com.kryeit;

import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class Utils {
    public static SimpleContainer parseItemStackToContainer(ItemStack itemStack) {
        if (itemStack != null) {
            CompoundTag itemNBT = itemStack.getTag();
            if (itemNBT == null) return null;
            // Check if the item has a 'BlockEntityTag' compound tag
            CompoundTag blockEntityTag = itemNBT.getCompound("BlockEntityTag");
            if (!blockEntityTag.isEmpty()) {
                ListTag itemsTag = blockEntityTag.getList("Items", 10);

                if (!itemsTag.isEmpty()) {
                    int size = 27;
                    NonNullList<ItemStack> inventoryList = NonNullList.withSize(size, ItemStack.EMPTY);

                    for (int i = 0; i < size; i++) {
                        CompoundTag itemTag = itemsTag.getCompound(i);
                        byte slot = itemTag.getByte("Slot");
                        String itemId = itemTag.getString("id");
                        Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(itemId));

                        ItemStack stack = new ItemStack(item);
                        stack.setCount(itemTag.getByte("Count"));
                        stack.setTag(itemTag.getCompound("tag")); // Copy NBT data
                        inventoryList.set(slot, stack);
                    }

                    return new SimpleContainer(inventoryList.toArray(new ItemStack[size]));
                }
            }
        }

        return null;
    }
}
