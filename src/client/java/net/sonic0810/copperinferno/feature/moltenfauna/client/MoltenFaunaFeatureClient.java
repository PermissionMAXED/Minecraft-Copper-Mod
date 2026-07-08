package net.sonic0810.copperinferno.feature.moltenfauna.client;

import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.client.render.entity.StriderEntityRenderer;
import net.sonic0810.copperinferno.feature.moltenfauna.MoltenFaunaFeature;

/**
 * Client-side setup for the Molten Fauna mobs: each type reuses its vanilla renderer
 * (all five ctors are Context-only, verified via javap against the 1.21.9 client jar).
 * The mobs extend the matching vanilla entities, so the factories fit the
 * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;) bound; the
 * vanilla register method is access-widened by Fabric's transitive access wideners
 * (same proven pattern as the infernomobs renderers).
 */
public final class MoltenFaunaFeatureClient {
	private MoltenFaunaFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(MoltenFaunaFeature.MAGMA_STRIDER, StriderEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.SOOT_STRIDER, StriderEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.BASALT_STRIDER, StriderEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.SLAG_STRIDER, StriderEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.OBSIDIAN_STRIDER, StriderEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.EMBER_GRAZER, CowEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.ASH_YAK, CowEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.MAGMA_OX, CowEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.SLAG_BUFFALO, CowEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.CINDER_AUROCHS, CowEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.SOOT_HEN, ChickenEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.CINDER_ROOSTER, ChickenEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.EMBER_PULLET, ChickenEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.ASH_FOWL, ChickenEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.MAGMA_BANTAM, ChickenEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.MAGMA_HOG, PigEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.SOOT_SWINE, PigEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.CINDER_BOAR, PigEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.SLAG_SOW, PigEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.EMBER_PORKER, PigEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.ASH_EWE, SheepEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.CINDER_RAM, SheepEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.EMBER_LAMB, SheepEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.SOOT_WETHER, SheepEntityRenderer::new);
		EntityRendererFactories.register(MoltenFaunaFeature.SMOLDER_SHEEP, SheepEntityRenderer::new);
	}
}
