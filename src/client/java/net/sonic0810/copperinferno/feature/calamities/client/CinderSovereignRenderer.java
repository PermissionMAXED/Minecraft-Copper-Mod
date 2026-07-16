package net.sonic0810.copperinferno.feature.calamities.client;

import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Cinder Sovereign" calamity boss: the vanilla BlazeEntityRenderer
 * (public non-final, Context-only ctor, verified via javap) with only the
 * getTexture(LivingEntityRenderState) overload swapped to the boss's recolored
 * texture (emitted by devtools/gen/calamities_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/cinder_sovereign.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class CinderSovereignRenderer extends BlazeEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/cinder_sovereign.png");

	public CinderSovereignRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(LivingEntityRenderState state) {
		return TEXTURE;
	}
}
