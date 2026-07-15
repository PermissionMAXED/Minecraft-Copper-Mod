package net.sonic0810.kupferbienen.feature.potions.client;

import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.sonic0810.kupferbienen.feature.potions.PotionsFeature;

/**
 * Client-side setup for the potions feature: the thrown vial reuses the vanilla
 * {@link FlyingItemEntityRenderer} (Context-only ctor, verified via javap), which renders the
 * projectile's item stack — no entity texture needed. {@code EntityRendererFactories.register}
 * is access-widened by Fabric's transitive access wideners (BeesFeatureClient pattern).
 */
public final class PotionsFeatureClient {
	private PotionsFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(PotionsFeature.GEWORFENE_PHIOLE, FlyingItemEntityRenderer::new);
	}
}
