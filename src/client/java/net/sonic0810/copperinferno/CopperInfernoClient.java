package net.sonic0810.copperinferno;

import net.fabricmc.api.ClientModInitializer;
import net.sonic0810.copperinferno.core.client.CoreClient;
import net.sonic0810.copperinferno.feature.drpepper.client.DrPepperFeatureClient;
import net.sonic0810.copperinferno.feature.extras.client.ExtrasFeatureClient;
import net.sonic0810.copperinferno.feature.golem.client.DrPepperGolemFeatureClient;
import net.sonic0810.copperinferno.feature.statue.client.PlayerStatueFeatureClient;

public class CopperInfernoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		CopperInferno.LOGGER.info("[COPPER INFERNO 1] Client initializing");
		CoreClient.initClient();
		PlayerStatueFeatureClient.initClient();
		DrPepperFeatureClient.initClient();
		DrPepperGolemFeatureClient.initClient();
		ExtrasFeatureClient.initClient();
	}
}
