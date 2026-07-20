package net.sonic0810.kupferbienen.feature.flora.client;

/**
 * Client-side setup for the flora feature: cross/crop models need the CUTOUT render layer
 * (BeesFeatureClient pattern: {@code BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, ...)}).
 *
 * <p>Skeleton: render-layer registrations are added by later expansion workers.
 */
public final class FloraFeatureClient {
	private FloraFeatureClient() {
	}

	public static void initClient() {
		// Skeleton: flora render layers register here.
	}
}
