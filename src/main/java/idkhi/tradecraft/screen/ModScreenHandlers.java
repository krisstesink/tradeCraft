package idkhi.tradecraft.screen;

import idkhi.tradecraft.TradeCraft;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<GemPolishingScreenHandler> GEM_POLISHING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TradeCraft.MOD_ID, "gem_polishing"),
                    new ExtendedScreenHandlerType<>(GemPolishingScreenHandler::new));

    public static final ScreenHandlerType<CustomEnderChestScreenHandler> CUSTOM_ENDER_CHEST_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(TradeCraft.MOD_ID, "custom_ender_chest"),
                    new ExtendedScreenHandlerType<>(CustomEnderChestScreenHandler::new));

    public static void registerScreenHandlers() {
        TradeCraft.LOGGER.info("Registering Screen Handlers for " + TradeCraft.MOD_ID);
    }
}
