package net.sonic0810.copperinferno.feature.handbook.client;

import net.minecraft.client.MinecraftClient;
import net.sonic0810.copperinferno.feature.handbook.HandbookFeature;

/**
 * Client-side setup for the handbook (the handbook screen rendering HandbookEntries).
 */
public final class HandbookFeatureClient {
	private HandbookFeatureClient() {
	}

	public static void initClient() {
		HandbookFeature.handbookScreenOpener =
				player -> MinecraftClient.getInstance().setScreen(new HandbookScreen());
	}
}
