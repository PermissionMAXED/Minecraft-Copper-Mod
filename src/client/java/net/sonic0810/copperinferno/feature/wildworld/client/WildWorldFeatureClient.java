package net.sonic0810.copperinferno.feature.wildworld.client;

/**
 * v4 "Wild World" client hooks (WP16): only the Ashfall ambience needs client wiring — the
 * geyser/fumarole particle effects run through {@code Block#randomDisplayTick} in common code,
 * and all three WP16 blocks are plain opaque cubes (default render layer, no factories).
 */
public final class WildWorldFeatureClient {
	private WildWorldFeatureClient() {
	}

	public static void initClient() {
		AshfallAmbienceClient.initClient();
	}
}
