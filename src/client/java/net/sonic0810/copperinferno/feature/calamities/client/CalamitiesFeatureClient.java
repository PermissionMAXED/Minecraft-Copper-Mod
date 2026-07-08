package net.sonic0810.copperinferno.feature.calamities.client;

import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.RavagerEntityRenderer;
import net.minecraft.client.render.entity.VindicatorEntityRenderer;
import net.minecraft.client.render.entity.WitherSkeletonEntityRenderer;
import net.sonic0810.copperinferno.feature.calamities.CalamitiesFeature;

/**
 * Client-side setup for the calamity bosses: all 10 reuse their vanilla renderers
 * (every ctor is Context-only, verified via javap) - the SCALE attribute makes
 * them loom. The bosses extend the matching vanilla entities, so the factories fit
 * the register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;)
 * bound; the vanilla register method is access-widened by Fabric's transitive
 * access wideners (same proven pattern as the infernoboss feature).
 */
public final class CalamitiesFeatureClient {
	private CalamitiesFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(CalamitiesFeature.EMBERLORD_RAVAGER, RavagerEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.SLAG_WARLORD, RavagerEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.MOLTEN_COLOSSUS, IronGolemEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.VERDIGRIS_MONARCH, IronGolemEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.ASHKING_WITHER, WitherSkeletonEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.SOOT_REAPER, WitherSkeletonEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.CINDER_SOVEREIGN, BlazeEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.PYRE_TYRANT, BlazeEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.FURNACE_FIEND, VindicatorEntityRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.CALAMITY_HERALD, VindicatorEntityRenderer::new);
	}
}
