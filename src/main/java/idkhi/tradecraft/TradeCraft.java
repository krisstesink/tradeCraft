package idkhi.tradecraft;

import idkhi.tradecraft.block.ModBlocks;
import idkhi.tradecraft.block.entity.ModBlockEntities;
import idkhi.tradecraft.commands.CommandRegistrar;
import idkhi.tradecraft.item.ModItemGroups;
import idkhi.tradecraft.item.ModItems;
import idkhi.tradecraft.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeCraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("tradecraft");
	public static final String MOD_ID = "tradecraft";


	@Override
	public void onInitialize() {
		CommandRegistrar.registerCommands();
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();



		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		LOGGER.info("Hello Fabric world!");
	}
}