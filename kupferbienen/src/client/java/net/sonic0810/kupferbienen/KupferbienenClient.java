package net.sonic0810.kupferbienen;

import net.fabricmc.api.ClientModInitializer;

public class KupferbienenClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Kupferbienen.LOGGER.info("[KUPFERBIENEN] Client initializing");
	}
}
