package net.sonic0810.copperinferno.feature.archfiends.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.PiglinEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Cinder Brute Warlord" archfiend boss: the vanilla PiglinEntityRenderer
 * (public non-final, verified via javap) with only the getTexture(PiglinEntityRenderState)
 * overload swapped to the boss's recolored texture (emitted by
 * devtools/gen/archfiends_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/cinder_brute_warlord.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class CinderBruteWarlordRenderer extends PiglinEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/cinder_brute_warlord.png");

	public CinderBruteWarlordRenderer(EntityRendererFactory.Context context) {
		// PIGLIN_BRUTE model + equipment layers, exactly as the vanilla
		// EntityRendererFactories bytecode wires the piglin_brute renderer.
		super(context, EntityModelLayers.PIGLIN_BRUTE, EntityModelLayers.PIGLIN_BRUTE,
				EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT,
				EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT);
	}

	@Override
	public Identifier getTexture(PiglinEntityRenderState state) {
		return TEXTURE;
	}
}
