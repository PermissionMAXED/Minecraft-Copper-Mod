package net.sonic0810.copperinferno.feature.constructs.client;

import net.minecraft.client.render.entity.BatEntityRenderer;
import net.minecraft.client.render.entity.BeeEntityRenderer;
import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.client.render.entity.CaveSpiderEntityRenderer;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EndermiteEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GhastEntityRenderer;
import net.minecraft.client.render.entity.HuskEntityRenderer;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.ParrotEntityRenderer;
import net.minecraft.client.render.entity.PhantomEntityRenderer;
import net.minecraft.client.render.entity.PillagerEntityRenderer;
import net.minecraft.client.render.entity.RabbitEntityRenderer;
import net.minecraft.client.render.entity.SilverfishEntityRenderer;
import net.minecraft.client.render.entity.SnowGolemEntityRenderer;
import net.minecraft.client.render.entity.SpiderEntityRenderer;
import net.minecraft.client.render.entity.StrayEntityRenderer;
import net.minecraft.client.render.entity.VexEntityRenderer;
import net.minecraft.client.render.entity.VindicatorEntityRenderer;
import net.minecraft.client.render.entity.WitchEntityRenderer;
import net.minecraft.client.render.entity.ZoglinEntityRenderer;
import net.minecraft.client.render.entity.state.BatEntityRenderState;
import net.minecraft.client.render.entity.state.BeeEntityRenderState;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.render.entity.state.GhastEntityRenderState;
import net.minecraft.client.render.entity.state.HoglinEntityRenderState;
import net.minecraft.client.render.entity.state.IllagerEntityRenderState;
import net.minecraft.client.render.entity.state.IronGolemEntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.ParrotEntityRenderState;
import net.minecraft.client.render.entity.state.PhantomEntityRenderState;
import net.minecraft.client.render.entity.state.RabbitEntityRenderState;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.client.render.entity.state.SnowGolemEntityRenderState;
import net.minecraft.client.render.entity.state.VexEntityRenderState;
import net.minecraft.client.render.entity.state.WitchEntityRenderState;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Custom-texture subclasses of the vanilla entity renderers for the 24 constructs. Every
 * renderer keeps the vanilla model/animation and overrides ONLY the render-state
 * {@code getTexture} overload (the exact overload per renderer verified via javap; none is
 * final) to point at {@code assets/copper_inferno/textures/entity/constructs/<mob>.png} -
 * recolored copies of the vanilla base textures emitted by {@code devtools/gen/
 * constructs_gen.py}. Bases shared by several constructs (iron golem x3, pillager x2) use a
 * texture-parameterized subclass. Secondary vanilla overlays (iron golem cracks, stray
 * overlay, snow golem pumpkin) intentionally stay vanilla.
 */
public final class ConstructRenderers {
	private ConstructRenderers() {
	}

	static Identifier texture(String mob) {
		return CopperInferno.id("textures/entity/constructs/" + mob + ".png");
	}

	/** Iron-golem renderer with a swapped body texture (copper_sentinel, slag_construct,
	 * furnace_golem). */
	public static final class GolemRenderer extends IronGolemEntityRenderer {
		private final Identifier texture;

		public GolemRenderer(EntityRendererFactory.Context context, Identifier texture) {
			super(context);
			this.texture = texture;
		}

		@Override
		public Identifier getTexture(IronGolemEntityRenderState state) {
			return texture;
		}
	}

	/** Pillager renderer with a swapped texture (forge_keeper, doom_marauder). */
	public static final class MarauderRenderer extends PillagerEntityRenderer {
		private final Identifier texture;

		public MarauderRenderer(EntityRendererFactory.Context context, Identifier texture) {
			super(context);
			this.texture = texture;
		}

		@Override
		public Identifier getTexture(IllagerEntityRenderState state) {
			return texture;
		}
	}

	public static final class DoomAcolyteRenderer extends VindicatorEntityRenderer {
		private static final Identifier TEXTURE = texture("doom_acolyte");

		public DoomAcolyteRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(IllagerEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class AnvilMimicRenderer extends SilverfishEntityRenderer {
		private static final Identifier TEXTURE = texture("anvil_mimic");

		public AnvilMimicRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(LivingEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class GearSpiderRenderer extends SpiderEntityRenderer<SpiderEntity> {
		private static final Identifier TEXTURE = texture("gear_spider");

		public GearSpiderRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(LivingEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class PistonHopperRenderer extends RabbitEntityRenderer {
		private static final Identifier TEXTURE = texture("piston_hopper");

		public PistonHopperRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(RabbitEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class RedstoneShadeRenderer extends VexEntityRenderer {
		private static final Identifier TEXTURE = texture("redstone_shade");

		public RedstoneShadeRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(VexEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class WireWraithRenderer extends PhantomEntityRenderer {
		private static final Identifier TEXTURE = texture("wire_wraith");

		public WireWraithRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(PhantomEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class BoilerBlazeRenderer extends BlazeEntityRenderer {
		private static final Identifier TEXTURE = texture("boiler_blaze");

		public BoilerBlazeRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(LivingEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class ScrapVultureRenderer extends ParrotEntityRenderer {
		private static final Identifier TEXTURE = texture("scrap_vulture");

		public ScrapVultureRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(ParrotEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class ClockworkBeeRenderer extends BeeEntityRenderer {
		private static final Identifier TEXTURE = texture("clockwork_bee");

		public ClockworkBeeRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(BeeEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class SteamGhastRenderer extends GhastEntityRenderer {
		private static final Identifier TEXTURE = texture("steam_ghast");

		public SteamGhastRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(GhastEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class PipeSerpentRenderer extends CaveSpiderEntityRenderer {
		private static final Identifier TEXTURE = texture("pipe_serpent");

		public PipeSerpentRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(LivingEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class GrinderZoglinRenderer extends ZoglinEntityRenderer {
		private static final Identifier TEXTURE = texture("grinder_zoglin");

		public GrinderZoglinRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(HoglinEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class PlatedHuskRenderer extends HuskEntityRenderer {
		private static final Identifier TEXTURE = texture("plated_husk");

		public PlatedHuskRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(ZombieEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class RivetedStrayRenderer extends StrayEntityRenderer {
		private static final Identifier TEXTURE = texture("riveted_stray");

		public RivetedStrayRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(SkeletonEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class TeslaCreeperRenderer extends CreeperEntityRenderer {
		private static final Identifier TEXTURE = texture("tesla_creeper");

		public TeslaCreeperRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(CreeperEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class MagnetMiteRenderer extends EndermiteEntityRenderer {
		private static final Identifier TEXTURE = texture("magnet_mite");

		public MagnetMiteRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(LivingEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class CrucibleWitchRenderer extends WitchEntityRenderer {
		private static final Identifier TEXTURE = texture("crucible_witch");

		public CrucibleWitchRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(WitchEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class BellowsBatRenderer extends BatEntityRenderer {
		private static final Identifier TEXTURE = texture("bellows_bat");

		public BellowsBatRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(BatEntityRenderState state) {
			return TEXTURE;
		}
	}

	public static final class IngotGolemRenderer extends SnowGolemEntityRenderer {
		private static final Identifier TEXTURE = texture("ingot_golem");

		public IngotGolemRenderer(EntityRendererFactory.Context context) {
			super(context);
		}

		@Override
		public Identifier getTexture(SnowGolemEntityRenderState state) {
			return TEXTURE;
		}
	}
}
