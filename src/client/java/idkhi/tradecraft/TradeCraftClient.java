package idkhi.tradecraft;

import idkhi.tradecraft.screen.CustomEnderChestScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.util.Identifier;

public class TradeCraftClient implements ClientModInitializer {
	private static final Identifier CUSTOM_ENDER_CHEST_SCREEN_ID = new Identifier("tradecraft", "custom_ender_chest");

	@Override
	public void onInitializeClient() {
		// Register your custom ender chest screen
		ScreenRegistry.register(TradeCraft.CUSTOM_ENDER_CHEST_SCREEN_ID, CustomEnderChestScreen::new);
	}
}