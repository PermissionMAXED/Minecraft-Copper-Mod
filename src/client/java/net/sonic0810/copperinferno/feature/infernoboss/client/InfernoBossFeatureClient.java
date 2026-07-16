package net.sonic0810.copperinferno.feature.infernoboss.client;

import net.minecraft.client.render.entity.EntityRendererFactories;
import net.sonic0810.copperinferno.feature.infernoboss.InfernoBossFeature;

/**
 * Client-side setup for the Inferno bosses: each registers its own tiny renderer
 * subclass ({@link TheOxidizerRenderer} / {@link InfernoTitanRenderer} — the vanilla
 * iron-golem/blaze renderer with the boss's recolored entity texture; both ctors are
 * Context-only, verified via javap) - the SCALE attribute makes them loom. The bosses
 * extend the matching vanilla entities, so the factories fit the
 * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;) bound; the vanilla
 * register method is access-widened by Fabric's transitive access wideners (same proven
 * pattern as the Dr.Pepper golem and the Inferno mobs).
 */
public final class InfernoBossFeatureClient {
	private InfernoBossFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(InfernoBossFeature.THE_OXIDIZER, TheOxidizerRenderer::new);
		EntityRendererFactories.register(InfernoBossFeature.INFERNO_TITAN, InfernoTitanRenderer::new);
	}
}
