package net.sonic0810.copperinferno;

import net.fabricmc.api.ClientModInitializer;
import net.sonic0810.copperinferno.core.client.CoreClient;
import net.sonic0810.copperinferno.feature.artifacts.client.ArtifactsFeatureClient;
import net.sonic0810.copperinferno.feature.bossdoom.client.BossDoomFeatureClient;
import net.sonic0810.copperinferno.feature.bosslords.client.BossLordsFeatureClient;
import net.sonic0810.copperinferno.feature.bosspantheon.client.BossPantheonFeatureClient;
import net.sonic0810.copperinferno.feature.chromacopper.client.ChromaCopperFeatureClient;
import net.sonic0810.copperinferno.feature.cinderstone.client.CinderStoneFeatureClient;
import net.sonic0810.copperinferno.feature.constructs.client.ConstructsFeatureClient;
import net.sonic0810.copperinferno.feature.copperfauna.client.CopperFaunaFeatureClient;
import net.sonic0810.copperinferno.feature.depthstone.client.DepthStoneFeatureClient;
import net.sonic0810.copperinferno.feature.drpepper.client.DrPepperFeatureClient;
import net.sonic0810.copperinferno.feature.extras.client.ExtrasFeatureClient;
import net.sonic0810.copperinferno.feature.gemalloy.client.GemAlloyFeatureClient;
import net.sonic0810.copperinferno.feature.glasslight.client.GlassLightFeatureClient;
import net.sonic0810.copperinferno.feature.golem.client.DrPepperGolemFeatureClient;
import net.sonic0810.copperinferno.feature.handbook.client.HandbookFeatureClient;
import net.sonic0810.copperinferno.feature.infernoboss.client.InfernoBossFeatureClient;
import net.sonic0810.copperinferno.feature.infernodim.client.InfernoDimensionFeatureClient;
import net.sonic0810.copperinferno.feature.infernofauna.client.InfernoFaunaFeatureClient;
import net.sonic0810.copperinferno.feature.infernoflora.client.InfernoFloraFeatureClient;
import net.sonic0810.copperinferno.feature.infernofx.client.InfernoFxFeatureClient;
import net.sonic0810.copperinferno.feature.infernomobs.client.InfernoMobsFeatureClient;
import net.sonic0810.copperinferno.feature.sodafauna.client.SodaFaunaFeatureClient;
import net.sonic0810.copperinferno.feature.statue.client.PlayerStatueFeatureClient;
import net.sonic0810.copperinferno.feature.systems.client.SystemsFeatureClient;
import net.sonic0810.copperinferno.feature.wildworld.client.WildWorldFeatureClient;

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
		// v4 mega-content-update client wave (same relative order as the common side;
		// empty skeletons until their work package lands). HandbookFeatureClient LAST.
		ChromaCopperFeatureClient.initClient();
		DepthStoneFeatureClient.initClient();
		GemAlloyFeatureClient.initClient();
		CopperFaunaFeatureClient.initClient();
		InfernoFaunaFeatureClient.initClient();
		SodaFaunaFeatureClient.initClient();
		ConstructsFeatureClient.initClient();
		BossLordsFeatureClient.initClient();
		BossPantheonFeatureClient.initClient();
		BossDoomFeatureClient.initClient();
		SystemsFeatureClient.initClient();
		WildWorldFeatureClient.initClient();
		ArtifactsFeatureClient.initClient();
		HandbookFeatureClient.initClient();
	}
}
