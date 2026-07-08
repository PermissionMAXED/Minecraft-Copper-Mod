package net.sonic0810.copperinferno.feature.calamities.client;

import net.minecraft.client.render.entity.EntityRendererFactories;
import net.sonic0810.copperinferno.feature.calamities.CalamitiesFeature;

/**
 * Client-side setup for the calamity bosses: each registers its own tiny renderer
 * subclass (vanilla renderer + the boss's recolored entity texture; every ctor is
 * Context-only, verified via javap) - the SCALE attribute makes them loom. The
 * bosses extend the matching vanilla entities, so the factories fit the
 * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;)
 * bound; the vanilla register method is access-widened by Fabric's transitive
 * access wideners (same proven pattern as the infernoboss feature).
 */
public final class CalamitiesFeatureClient {
	private CalamitiesFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(CalamitiesFeature.EMBERLORD_RAVAGER, EmberlordRavagerRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.SLAG_WARLORD, SlagWarlordRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.MOLTEN_COLOSSUS, MoltenColossusRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.VERDIGRIS_MONARCH, VerdigrisMonarchRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.ASHKING_WITHER, AshkingWitherRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.SOOT_REAPER, SootReaperRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.CINDER_SOVEREIGN, CinderSovereignRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.PYRE_TYRANT, PyreTyrantRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.FURNACE_FIEND, FurnaceFiendRenderer::new);
		EntityRendererFactories.register(CalamitiesFeature.CALAMITY_HERALD, CalamityHeraldRenderer::new);
	}
}
