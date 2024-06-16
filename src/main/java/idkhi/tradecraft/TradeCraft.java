package idkhi.tradecraft;

import idkhi.tradecraft.block.CustomEnderChestBlock;
import idkhi.tradecraft.blockentity.CustomEnderChestBlockEntity;
import idkhi.tradecraft.container.CustomEnderChestContainer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class TradeCraft implements ModInitializer {
	public static final ScreenHandlerType<CustomEnderChestContainer> CUSTOM_ENDER_CHEST_SCREEN_ID;
	public static final Block CUSTOM_ENDER_CHEST_BLOCK;
	public static final BlockEntityType<CustomEnderChestBlockEntity> CUSTOM_ENDER_CHEST_BLOCK_ENTITY;
	public static final Item CUSTOM_ENDER_CHEST_BLOCK_ITEM;

	static {
		// Register the custom screen handler
		CUSTOM_ENDER_CHEST_SCREEN_ID = ScreenHandlerRegistry.registerSimple(
				new Identifier("tradecraft", "custom_ender_chest"),
				CustomEnderChestContainer::new
		);

		// Register the custom block
		CUSTOM_ENDER_CHEST_BLOCK = new CustomEnderChestBlock(FabricBlockSettings.copyOf(Blocks.ENDER_CHEST).strength(4.0f));
		CUSTOM_ENDER_CHEST_BLOCK_ITEM = Registry.register(Registries.ITEM, new Identifier("tradecraft", "custom_ender_chest"), new BlockItem(CUSTOM_ENDER_CHEST_BLOCK, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier("tradecraft", "custom_ender_chest"), CUSTOM_ENDER_CHEST_BLOCK);

		// Register the custom block entity
		CUSTOM_ENDER_CHEST_BLOCK_ENTITY = Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				new Identifier("tradecraft", "custom_ender_chest_block_entity"),
				BlockEntityType.Builder.create(CustomEnderChestBlockEntity::new, CUSTOM_ENDER_CHEST_BLOCK).build(null)
		);
	}

	@Override
	public void onInitialize() {

	}


	}


