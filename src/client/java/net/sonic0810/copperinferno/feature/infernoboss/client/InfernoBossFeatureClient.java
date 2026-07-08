package net.sonic0810.copperinferno.feature.infernoboss.client;

import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.sonic0810.copperinferno.feature.infernoboss.InfernoBossFeature;

/**
 * Client-side setup for the Inferno bosses: both reuse their vanilla renderers (both ctors
 * are Context-only, verified via javap) - the SCALE attribute makes them loom. The bosses
 * extend the matching vanilla entities, so the factories fit the
 * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;) bound; the vanilla
 * register method is access-widened by Fabric's transitive access wideners (same proven
 * pattern as the Dr.Pepper golem and the Inferno mobs).
 */
public final class InfernoBossFeatureClient {
	private InfernoBossFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(InfernoBossFeature.THE_OXIDIZER, IronGolemEntityRenderer::new);
		EntityRendererFactories.register(InfernoBossFeature.INFERNO_TITAN, BlazeEntityRenderer::new);
	}
}
