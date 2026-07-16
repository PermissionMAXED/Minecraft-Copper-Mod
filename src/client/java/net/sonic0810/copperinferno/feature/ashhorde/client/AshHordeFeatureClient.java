package net.sonic0810.copperinferno.feature.ashhorde.client;

import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.HuskEntityRenderer;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.SpiderEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.sonic0810.copperinferno.feature.ashhorde.AshHordeFeature;

/**
 * Client-side setup for the Ash Horde: every mob reuses its vanilla base renderer (all five
 * ctors are Context-only, verified via javap), keeping the vanilla textures. The mobs extend
 * the matching vanilla entities, so the factories fit the
 * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;) bound; the vanilla
 * register method is access-widened by Fabric's transitive access wideners (same proven
 * pattern as InfernoMobsFeatureClient).
 */
public final class AshHordeFeatureClient {
	private AshHordeFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(AshHordeFeature.CINDER_SHAMBLER, ZombieEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.ASH_GHOUL, ZombieEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SLAG_ROTTER, ZombieEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.EMBER_THRALL, ZombieEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SOOT_WALKER, ZombieEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.ASH_ARCHER, SkeletonEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.CINDER_BOWMAN, SkeletonEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SLAG_MARKSMAN, SkeletonEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.EMBER_RATTLER, SkeletonEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SOOT_SKIRMISHER, SkeletonEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.EMBER_LURKER, SpiderEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.ASH_WEAVER, SpiderEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SLAG_SPINNER, SpiderEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SOOT_STALKER, SpiderEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.CINDER_BROODLING, SpiderEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SLAG_CREEPER, CreeperEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.ASH_BOMBER, CreeperEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.EMBER_BURSTER, CreeperEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SOOT_DETONATOR, CreeperEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.CINDER_CRACKER, CreeperEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.CHAR_HUSK, HuskEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.ASH_MUMMY, HuskEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SLAG_HUSK, HuskEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.EMBER_SCORCHLING, HuskEntityRenderer::new);
		EntityRendererFactories.register(AshHordeFeature.SOOT_WANDERER, HuskEntityRenderer::new);
	}
}
