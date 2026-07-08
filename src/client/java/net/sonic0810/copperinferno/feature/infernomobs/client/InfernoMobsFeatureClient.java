package net.sonic0810.copperinferno.feature.infernomobs.client;

import net.minecraft.client.render.entity.BatEntityRenderer;
import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.MagmaCubeEntityRenderer;
import net.minecraft.client.render.entity.SilverfishEntityRenderer;
import net.minecraft.client.render.entity.StriderEntityRenderer;
import net.sonic0810.copperinferno.feature.infernomobs.InfernoMobsFeature;

/**
 * Client-side setup for Inferno mobs: each type reuses its vanilla renderer (all five ctors are
 * Context-only, verified via javap). The mobs extend the matching vanilla entities, so the
 * factories fit the register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;)
 * bound; the vanilla register method is access-widened by Fabric's transitive access wideners
 * (same proven pattern as the Dr.Pepper golem).
 */
public final class InfernoMobsFeatureClient {
	private InfernoMobsFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(InfernoMobsFeature.EMBER_WRAITH, BlazeEntityRenderer::new);
		EntityRendererFactories.register(InfernoMobsFeature.SLAG_CRAWLER, SilverfishEntityRenderer::new);
		EntityRendererFactories.register(InfernoMobsFeature.MOLTEN_SLAGLING, MagmaCubeEntityRenderer::new);
		EntityRendererFactories.register(InfernoMobsFeature.CINDER_STRIDER, StriderEntityRenderer::new);
		EntityRendererFactories.register(InfernoMobsFeature.ASH_BAT, BatEntityRenderer::new);
	}
}
