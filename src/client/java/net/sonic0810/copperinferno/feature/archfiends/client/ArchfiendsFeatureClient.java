package net.sonic0810.copperinferno.feature.archfiends.client;

import net.minecraft.client.render.entity.EntityRendererFactories;
import net.sonic0810.copperinferno.feature.archfiends.ArchfiendsFeature;

/**
 * Client-side setup for the archfiend bosses: each registers its own tiny
 * renderer subclass (vanilla renderer + the boss's recolored entity texture;
 * see the per-boss <Boss>Renderer classes in this package) - the SCALE
 * attribute makes them loom. The bosses extend the matching vanilla entities,
 * so the factories fit the
 * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;)
 * bound; the vanilla register method is access-widened by Fabric's transitive
 * access wideners (same proven pattern as InfernoBossFeatureClient).
 */
public final class ArchfiendsFeatureClient {
	private ArchfiendsFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(ArchfiendsFeature.DREAD_GHAST_SOVEREIGN, DreadGhastSovereignRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.CINDER_GHAST_MATRIARCH, CinderGhastMatriarchRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.MOLTEN_HOGLIN_TYRANT, MoltenHoglinTyrantRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.ASHEN_HOGLIN_GOREFIEND, AshenHoglinGorefiendRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.ASH_EVOKER_ARCHON, AshEvokerArchonRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.SOOT_EVOKER_HIGHLORD, SootEvokerHighlordRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.CINDER_BRUTE_WARLORD, CinderBruteWarlordRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.GILDED_BRUTE_EXECUTIONER, GildedBruteExecutionerRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.SLAG_WITHER_MONARCH, SlagWitherMonarchRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.BLIGHT_WITHER_EMPEROR, BlightWitherEmperorRenderer::new);
	}
}
