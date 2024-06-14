package idkhi.tradecraft.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class AdditionalSlotsInventory implements Inventory {
    private final DefaultedList<ItemStack> additionalSlots;

    public AdditionalSlotsInventory(DefaultedList<ItemStack> additionalSlots) {
        this.additionalSlots = additionalSlots;
    }

    @Override
    public int size() {
        return additionalSlots.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : additionalSlots) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return additionalSlots.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = additionalSlots.get(slot);
        if (!stack.isEmpty()) {
            if (stack.getCount() <= amount) {
                additionalSlots.set(slot, ItemStack.EMPTY);
                return stack;
            } else {
                ItemStack result = stack.split(amount);
                if (stack.getCount() == 0) {
                    additionalSlots.set(slot, ItemStack.EMPTY);
                }
                return result;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack stack = additionalSlots.get(slot);
        additionalSlots.set(slot, ItemStack.EMPTY);
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        additionalSlots.set(slot, stack);
    }

    @Override
    public void markDirty() {
        // Do nothing for now
    }


    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        additionalSlots.clear();
    }
}
