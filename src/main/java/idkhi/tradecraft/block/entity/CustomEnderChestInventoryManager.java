package idkhi.tradecraft.block.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CustomEnderChestInventoryManager {
    private static final ConcurrentHashMap<UUID, DefaultedList<ItemStack>> playerInventories = new ConcurrentHashMap<>();
    private static final int INVENTORY_SIZE = 27;

    public static DefaultedList<ItemStack> getOrCreateInventory(UUID playerUuid) {
        return playerInventories.computeIfAbsent(playerUuid, k -> DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY));
    }

    public static void setInventory(UUID playerUuid, DefaultedList<ItemStack> inventory) {
        playerInventories.put(playerUuid, inventory);
    }

    public static DefaultedList<ItemStack> getInventory(UUID playerUuid) {
        return playerInventories.get(playerUuid);
    }
}
