package net.sonic0810.kupferbienen;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.sonic0810.kupferbienen.core.ModCreativeTab;
import net.sonic0810.kupferbienen.feature.bees.BeesFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kupferbienen implements ModInitializer {
	public static final String MOD_ID = "kupferbienen";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("[KUPFERBIENEN] Initializing - by Sonic0810");
		// ModCreativeTab.init() MUST run before any feature init: features register their
		// ItemGroupEvents callbacks against the tab keys created there.
		ModCreativeTab.init();
		BeesFeature.init();
	}
}
