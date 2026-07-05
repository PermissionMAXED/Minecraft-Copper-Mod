package net.sonic0810.copperinferno;

import net.fabricmc.api.ClientModInitializer;

public class CopperInfernoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		CopperInferno.LOGGER.info("[COPPER INFERNO 1] Client initializing");
	}
}
