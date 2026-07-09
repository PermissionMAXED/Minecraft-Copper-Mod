package net.sonic0810.copperinferno.feature.constructs.client;

import net.minecraft.client.render.entity.EntityRendererFactories;
import net.sonic0810.copperinferno.feature.constructs.ConstructsFeature;

/**
 * Client-side setup for the 24 constructs: every type gets a custom-texture subclass of its
 * vanilla renderer (see {@link ConstructRenderers}; all ctors are Context-only, verified via
 * javap). The constructs extend the matching vanilla entities, so the factories fit the
 * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;) bound; the vanilla
 * register method is access-widened by Fabric's transitive access wideners (proven pattern:
 * Dr.Pepper golem, infernomobs).
 */
public final class ConstructsFeatureClient {
	private ConstructsFeatureClient() {
	}

	public static void initClient() {
		EntityRendererFactories.register(ConstructsFeature.COPPER_SENTINEL,
				ctx -> new ConstructRenderers.GolemRenderer(ctx, ConstructRenderers.texture("copper_sentinel")));
		EntityRendererFactories.register(ConstructsFeature.SLAG_CONSTRUCT,
				ctx -> new ConstructRenderers.GolemRenderer(ctx, ConstructRenderers.texture("slag_construct")));
		EntityRendererFactories.register(ConstructsFeature.FURNACE_GOLEM,
				ctx -> new ConstructRenderers.GolemRenderer(ctx, ConstructRenderers.texture("furnace_golem")));
		EntityRendererFactories.register(ConstructsFeature.FORGE_KEEPER,
				ctx -> new ConstructRenderers.MarauderRenderer(ctx, ConstructRenderers.texture("forge_keeper")));
		EntityRendererFactories.register(ConstructsFeature.DOOM_MARAUDER,
				ctx -> new ConstructRenderers.MarauderRenderer(ctx, ConstructRenderers.texture("doom_marauder")));
		EntityRendererFactories.register(ConstructsFeature.DOOM_ACOLYTE,
				ConstructRenderers.DoomAcolyteRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.ANVIL_MIMIC,
				ConstructRenderers.AnvilMimicRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.GEAR_SPIDER,
				ConstructRenderers.GearSpiderRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.PISTON_HOPPER,
				ConstructRenderers.PistonHopperRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.REDSTONE_SHADE,
				ConstructRenderers.RedstoneShadeRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.WIRE_WRAITH,
				ConstructRenderers.WireWraithRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.BOILER_BLAZE,
				ConstructRenderers.BoilerBlazeRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.SCRAP_VULTURE,
				ConstructRenderers.ScrapVultureRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.CLOCKWORK_BEE,
				ConstructRenderers.ClockworkBeeRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.STEAM_GHAST,
				ConstructRenderers.SteamGhastRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.PIPE_SERPENT,
				ConstructRenderers.PipeSerpentRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.GRINDER_ZOGLIN,
				ConstructRenderers.GrinderZoglinRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.PLATED_HUSK,
				ConstructRenderers.PlatedHuskRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.RIVETED_STRAY,
				ConstructRenderers.RivetedStrayRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.TESLA_CREEPER,
				ConstructRenderers.TeslaCreeperRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.MAGNET_MITE,
				ConstructRenderers.MagnetMiteRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.CRUCIBLE_WITCH,
				ConstructRenderers.CrucibleWitchRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.BELLOWS_BAT,
				ConstructRenderers.BellowsBatRenderer::new);
		EntityRendererFactories.register(ConstructsFeature.INGOT_GOLEM,
				ConstructRenderers.IngotGolemRenderer::new);
	}
}
