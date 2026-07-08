package net.sonic0810.copperinferno;

import net.fabricmc.api.ClientModInitializer;
import net.sonic0810.copperinferno.core.client.CoreClient;
import net.sonic0810.copperinferno.feature.cinderstone.client.CinderStoneFeatureClient;
import net.sonic0810.copperinferno.feature.drpepper.client.DrPepperFeatureClient;
import net.sonic0810.copperinferno.feature.extras.client.ExtrasFeatureClient;
import net.sonic0810.copperinferno.feature.glasslight.client.GlassLightFeatureClient;
import net.sonic0810.copperinferno.feature.golem.client.DrPepperGolemFeatureClient;
import net.sonic0810.copperinferno.feature.handbook.client.HandbookFeatureClient;
import net.sonic0810.copperinferno.feature.infernoboss.client.InfernoBossFeatureClient;
import net.sonic0810.copperinferno.feature.infernodim.client.InfernoDimensionFeatureClient;
import net.sonic0810.copperinferno.feature.infernoflora.client.InfernoFloraFeatureClient;
import net.sonic0810.copperinferno.feature.infernofx.client.InfernoFxFeatureClient;
import net.sonic0810.copperinferno.feature.infernomobs.client.InfernoMobsFeatureClient;
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
		GlassLightFeatureClient.initClient();
		// v3 "The Inferno Dimension" client features (same relative order as the common side).
		InfernoDimensionFeatureClient.initClient();
		CinderStoneFeatureClient.initClient();
		InfernoFloraFeatureClient.initClient();
		InfernoMobsFeatureClient.initClient();
		InfernoBossFeatureClient.initClient();
		InfernoFxFeatureClient.initClient();
		HandbookFeatureClient.initClient();
	}
}
