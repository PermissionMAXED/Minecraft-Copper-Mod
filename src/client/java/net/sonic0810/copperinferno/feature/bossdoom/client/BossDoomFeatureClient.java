package net.sonic0810.copperinferno.feature.bossdoom.client;

import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.EvokerEntityRenderer;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.PhantomEntityRenderer;
import net.minecraft.client.render.entity.RavagerEntityRenderer;
import net.minecraft.client.render.entity.SlimeEntityRenderer;
import net.minecraft.client.render.entity.state.EvokerEntityRenderState;
import net.minecraft.client.render.entity.state.IronGolemEntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PhantomEntityRenderState;
import net.minecraft.client.render.entity.state.RavagerEntityRenderState;
import net.minecraft.client.render.entity.state.SlimeEntityRenderState;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.feature.bossdoom.BossDoomFeature;

/**
 * Client-side setup for the six Doom Ascendancy bosses. Each boss reuses its vanilla
 * renderer (all six ctors are Context-only, verified via javap) SUBCLASSED anonymously to
 * override the renderer's public {@code getTexture(<state>)} method (the 1.21.9
 * render-state contract: {@code LivingEntityRenderer.getTexture(S)} feeds
 * {@code getRenderLayer}, so overriding it swaps the whole body texture) so every boss uses
 * its own {@code copper_inferno:textures/entity/<id>.png} - the same proven pattern as
 * {@code CopperFaunaFeatureClient}. The explicit type witness on each
 * {@code EntityRendererFactories.<VanillaBase>register} call pins the factory's type
 * parameter to the vanilla base entity, exactly the bound the vanilla renderers are typed
 * to; {@code EvokerEntityRenderer} is itself generic in the entity
 * ({@code <T extends SpellcastingIllagerEntity>}, verified via javap), so it is
 * instantiated at {@code EvokerEntity}. getTexture state-parameter types verified via
 * javap: Ravager/IronGolem/Evoker/Slime/Phantom use their specific render states, Blaze
 * uses the plain {@code LivingEntityRenderState}.
 *
 * <p>Feature-renderer layers keep their vanilla textures (phantom eyes, slime overlay):
 * only the base skin is remapped, which is the intended look. The SCALE attribute makes
 * the bosses loom; no custom models are needed.
 */
public final class BossDoomFeatureClient {
	private BossDoomFeatureClient() {
	}

	private static Identifier tex(String id) {
		return CopperInferno.id("textures/entity/" + id + ".png");
	}

	public static void initClient() {
		Identifier drDoompepper = tex("dr_doompepper");
		EntityRendererFactories.<RavagerEntity>register(BossDoomFeature.DR_DOOMPEPPER,
				ctx -> new RavagerEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(RavagerEntityRenderState state) {
						return drDoompepper;
					}
				});
		Identifier theCarbonatedOne = tex("the_carbonated_one");
		EntityRendererFactories.<SlimeEntity>register(BossDoomFeature.THE_CARBONATED_ONE,
				ctx -> new SlimeEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(SlimeEntityRenderState state) {
						return theCarbonatedOne;
					}
				});
		Identifier sodaSeraph = tex("soda_seraph");
		EntityRendererFactories.<PhantomEntity>register(BossDoomFeature.SODA_SERAPH,
				ctx -> new PhantomEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(PhantomEntityRenderState state) {
						return sodaSeraph;
					}
				});
		Identifier kilnArchon = tex("kiln_archon");
		EntityRendererFactories.<BlazeEntity>register(BossDoomFeature.KILN_ARCHON,
				ctx -> new BlazeEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(LivingEntityRenderState state) {
						return kilnArchon;
					}
				});
		Identifier voidstoneBehemoth = tex("voidstone_behemoth");
		EntityRendererFactories.<IronGolemEntity>register(BossDoomFeature.VOIDSTONE_BEHEMOTH,
				ctx -> new IronGolemEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(IronGolemEntityRenderState state) {
						return voidstoneBehemoth;
					}
				});
		Identifier theLastSmith = tex("the_last_smith");
		EntityRendererFactories.<EvokerEntity>register(BossDoomFeature.THE_LAST_SMITH,
				ctx -> new EvokerEntityRenderer<EvokerEntity>(ctx) {
					@Override
					public Identifier getTexture(EvokerEntityRenderState state) {
						return theLastSmith;
					}
				});
	}
}
