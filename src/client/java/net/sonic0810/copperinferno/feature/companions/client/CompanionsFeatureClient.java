package net.sonic0810.copperinferno.feature.companions.client;

import net.minecraft.client.render.entity.CatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.WolfEntityRenderer;
import net.minecraft.client.render.entity.state.CatEntityRenderState;
import net.minecraft.client.render.entity.state.WolfEntityRenderState;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.feature.companions.CompanionsFeature;

/**
 * Client-side setup for the two Companions pets. Each reuses its vanilla renderer (Context-only
 * ctor) SUBCLASSED anonymously to override the renderer's public {@code getTexture(<state>)}
 * overload (the 1.21.9 render-state contract: {@code LivingEntityRenderer.getTexture(S)} feeds
 * {@code getRenderLayer}, so overriding it swaps the whole body texture) — the exact pattern of
 * {@code CopperFaunaFeatureClient} (Gutter Cat / Rust Wolf); overloads verified via javap:
 * {@code CatEntityRenderer.getTexture(CatEntityRenderState)},
 * {@code WolfEntityRenderer.getTexture(WolfEntityRenderState)}. Feature-renderer layers (cat /
 * wolf collar) keep their vanilla textures: only the base skin is remapped, which is the
 * intended look.
 */
public final class CompanionsFeatureClient {
	private CompanionsFeatureClient() {
	}

	private static Identifier tex(String id) {
		return CopperInferno.id("textures/entity/" + id + ".png");
	}

	public static void initClient() {
		Identifier copperKit = tex("copper_kit");
		EntityRendererFactories.<CatEntity>register(CompanionsFeature.COPPER_KIT,
				ctx -> new CatEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(CatEntityRenderState state) {
						return copperKit;
					}
				});
		Identifier emberHound = tex("ember_hound");
		EntityRendererFactories.<WolfEntity>register(CompanionsFeature.EMBER_HOUND,
				ctx -> new WolfEntityRenderer(ctx) {
					@Override
					public Identifier getTexture(WolfEntityRenderState state) {
						return emberHound;
					}
				});
	}
}
