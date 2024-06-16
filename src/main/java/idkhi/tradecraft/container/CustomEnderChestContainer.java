package idkhi.tradecraft.container;

import idkhi.tradecraft.TradeCraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CustomEnderChestContainer extends ScreenHandler {
    private final Inventory inventory;

    public CustomEnderChestContainer(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(36 + 9)); // 36 normal + 9 additional slots
    }

    public CustomEnderChestContainer(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(TradeCraft.CUSTOM_ENDER_CHEST_SCREEN_ID, syncId);
        this.inventory = inventory;
        checkSize(inventory, 45); // Ensure inventory is the right size
        inventory.onOpen(playerInventory.player);

        // Ender Chest slots (3 rows)
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }

        // Additional slots (4th row)
        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventory, 36 + j, 8 + j * 18, 18 + 3 * 18));
        }

        // Player inventory slots
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Player hotbar slots
        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}