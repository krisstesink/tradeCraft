package idkhi.tradecraft.block.entity;

import idkhi.tradecraft.screen.CustomEnderChestScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomEnderChestBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private static final Map<UUID, DefaultedList<ItemStack>> playerInventories = new HashMap<>();
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);
    public UUID currentPlayerUuid;

    private static final int INVENTORY_SIZE = 27; // Same as a regular Ender Chest

    protected final PropertyDelegate propertyDelegate;

    public CustomEnderChestBlockEntity(BlockPos pos, BlockState state) {
        super(idkhi.tradecraft.block.entity.ModBlockEntities.CUSTOM_ENDER_CHEST_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return 0; // No properties to track for now
            }

            @Override
            public void set(int index, int value) {
            }

            @Override
            public int size() {
                return INVENTORY_SIZE;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
        buf.writeUuid(player.getUuid());
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Custom Ender Chest");
    }

    public DefaultedList<ItemStack> getOrCreateInventory(UUID playerUuid) {
        return playerInventories.computeIfAbsent(playerUuid, k -> DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY));
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        for (Map.Entry<UUID, DefaultedList<ItemStack>> entry : playerInventories.entrySet()) {
            NbtCompound inventoryNbt = new NbtCompound();
            Inventories.writeNbt(inventoryNbt, entry.getValue());
            nbt.put(entry.getKey().toString(), inventoryNbt);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        playerInventories.clear();
        for (String key : nbt.getKeys()) {
            UUID playerUuid = UUID.fromString(key);
            NbtCompound inventoryNbt = nbt.getCompound(key);
            DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
            Inventories.readNbt(inventoryNbt, inventory);
            playerInventories.put(playerUuid, inventory);
        }
    }

    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        this.currentPlayerUuid = player.getUuid();
        getOrCreateInventory(this.currentPlayerUuid);
        return new CustomEnderChestScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public static void tick(World world, BlockPos pos, BlockState state, CustomEnderChestBlockEntity be) {
        if(world.isClient()) {
            return;
        }
    }



    @Override
    public DefaultedList<ItemStack> getItems() {
        if (this.currentPlayerUuid != null) {
            return CustomEnderChestInventoryManager.getOrCreateInventory(this.currentPlayerUuid);
        }
        return DefaultedList.ofSize(27, ItemStack.EMPTY);  // Default empty inventory
    }
}