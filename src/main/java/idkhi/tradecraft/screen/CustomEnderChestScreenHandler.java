package idkhi.tradecraft.screen;

import idkhi.tradecraft.block.entity.CustomEnderChestBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CustomEnderChestScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final CustomEnderChestBlockEntity blockEntity;

    // Constructor to handle syncId, PlayerInventory, and PacketByteBuf
    public CustomEnderChestScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(buf.readBlockPos()),
                new ArrayPropertyDelegate(27));
    }
    public CustomEnderChestScreenHandler(int syncId, PlayerInventory playerInventory,
                                         BlockEntity blockEntity, PropertyDelegate arrayPropertyDelegate) {

        super(ModScreenHandlers.CUSTOM_ENDER_CHEST_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 27);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;
        this.blockEntity = ((CustomEnderChestBlockEntity) blockEntity);

        // Custom Ender Chest Slots
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }

        // Player Inventory Slots
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Player Hotbar Slots
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        addProperties(arrayPropertyDelegate);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        this.sendContentUpdates();
    }
}