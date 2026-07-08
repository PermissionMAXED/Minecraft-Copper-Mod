package net.sonic0810.copperinferno.feature.archfiends.client;

import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.EvokerEntityRenderer;
import net.minecraft.client.render.entity.GhastEntityRenderer;
import net.minecraft.client.render.entity.HoglinEntityRenderer;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import net.minecraft.client.render.entity.WitherSkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.sonic0810.copperinferno.feature.archfiends.ArchfiendsFeature;

/**
 * Client-side setup for the archfiend bosses: all ten reuse their vanilla
 * renderers - the SCALE attribute makes them loom. The ghast/hoglin/evoker/
 * wither-skeleton renderer ctors are Context-only (verified via javap); the
 * piglin brutes reuse PiglinEntityRenderer with the PIGLIN_BRUTE model and
 * equipment layers, exactly matching the vanilla EntityRendererFactories
 * bytecode. The bosses extend the matching vanilla entities, so the factories
 * fit the register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;)
 * bound; the vanilla register method is access-widened by Fabric's transitive
 * access wideners (same proven pattern as InfernoBossFeatureClient).
 */
public final class ArchfiendsFeatureClient {
	private ArchfiendsFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(ArchfiendsFeature.DREAD_GHAST_SOVEREIGN, GhastEntityRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.CINDER_GHAST_MATRIARCH, GhastEntityRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.MOLTEN_HOGLIN_TYRANT, HoglinEntityRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.ASHEN_HOGLIN_GOREFIEND, HoglinEntityRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.ASH_EVOKER_ARCHON, EvokerEntityRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.SOOT_EVOKER_HIGHLORD, EvokerEntityRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.CINDER_BRUTE_WARLORD,
				context -> new PiglinEntityRenderer(context, EntityModelLayers.PIGLIN_BRUTE,
						EntityModelLayers.PIGLIN_BRUTE, EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT,
						EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT));
		EntityRendererFactories.register(ArchfiendsFeature.GILDED_BRUTE_EXECUTIONER,
				context -> new PiglinEntityRenderer(context, EntityModelLayers.PIGLIN_BRUTE,
						EntityModelLayers.PIGLIN_BRUTE, EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT,
						EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT));
		EntityRendererFactories.register(ArchfiendsFeature.SLAG_WITHER_MONARCH, WitherSkeletonEntityRenderer::new);
		EntityRendererFactories.register(ArchfiendsFeature.BLIGHT_WITHER_EMPEROR, WitherSkeletonEntityRenderer::new);
	}
}
