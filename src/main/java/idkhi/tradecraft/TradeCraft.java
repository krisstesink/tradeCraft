package idkhi.tradecraft;

import idkhi.tradecraft.block.BankBlock;
import idkhi.tradecraft.block.BankBlockEntity;
import idkhi.tradecraft.commands.CommandRegistrar;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeCraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("tradecraft");
	public static BlockEntityType<BankBlockEntity> BANK_BLOCK_ENTITY;
	public static final Block BANK_BLOCK = new BankBlock(FabricBlockSettings.copyOf(Blocks.BLUE_ICE));


	@Override
	public void onInitialize() {
		CommandRegistrar.registerCommands();
		Registry.register(Registries.ITEM,
				new Identifier("tradecraft", "bank"), new BlockItem(BANK_BLOCK, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier("tradecraft", "bank"), BANK_BLOCK);
		BANK_BLOCK_ENTITY = Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				new Identifier("tradecraft", "custom_block_entity"),
				FabricBlockEntityTypeBuilder.create(BankBlockEntity::new, BANK_BLOCK).build());
		LOGGER.info("Hello Fabric world!");
	}
}