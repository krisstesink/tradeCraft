package idkhi.tradecraft;

import idkhi.tradecraft.block.ModBlocks;
import idkhi.tradecraft.screen.CustomEnderChestScreen;
import idkhi.tradecraft.screen.GemPolishingScreen;
import idkhi.tradecraft.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class TradeCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RUBY_TRAPDOOR, RenderLayer.getCutout());

		HandledScreens.register(ModScreenHandlers.GEM_POLISHING_SCREEN_HANDLER, GemPolishingScreen::new);
		HandledScreens.register(ModScreenHandlers.CUSTOM_ENDER_CHEST_SCREEN_HANDLER, CustomEnderChestScreen::new);
	}
}