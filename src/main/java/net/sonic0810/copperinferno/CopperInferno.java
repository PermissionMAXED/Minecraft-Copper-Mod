package net.sonic0810.copperinferno;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopperInferno implements ModInitializer {
	public static final String MOD_ID = "copper_inferno";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[COPPER INFERNO 1] Initializing - by Sonic0810");
	}
}
