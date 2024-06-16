package idkhi.tradecraft.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;


public class CustomEnderChestInventory extends EnderChestInventory {
    private final DefaultedList<ItemStack> additionalSlots;
    private final AdditionalSlotsInventory additionalSlotsInventory;

    public CustomEnderChestInventory() {
        super();
        this.additionalSlots = DefaultedList.ofSize(9, ItemStack.EMPTY); // 9 additional slots
        this.additionalSlotsInventory = new AdditionalSlotsInventory(additionalSlots);
    }

    public AdditionalSlotsInventory getAdditionalSlotsInventory() {
        return additionalSlotsInventory;
    }

    @Override
    public void onOpen(PlayerEntity player) {
        super.onOpen(player);
        // additional logic when opened
    }

    @Override
    public void onClose(PlayerEntity player) {
        super.onClose(player);
        // additional logic when closed
    }
}
