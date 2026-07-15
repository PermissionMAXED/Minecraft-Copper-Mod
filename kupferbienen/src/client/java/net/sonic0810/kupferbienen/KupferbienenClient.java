package net.sonic0810.kupferbienen;

import net.fabricmc.api.ClientModInitializer;
import net.sonic0810.kupferbienen.feature.bees.client.BeesFeatureClient;

public class KupferbienenClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Kupferbienen.LOGGER.info("[KUPFERBIENEN] Client initializing");
		BeesFeatureClient.initClient();
	}
}
