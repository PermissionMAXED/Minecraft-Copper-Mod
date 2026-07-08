package net.sonic0810.copperinferno.feature.calamities.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.RavagerEntityRenderer;
import net.minecraft.client.render.entity.state.RavagerEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Slag Warlord" calamity boss: the vanilla RavagerEntityRenderer
 * (public non-final, Context-only ctor, verified via javap) with only the
 * getTexture(RavagerEntityRenderState) overload swapped to the boss's recolored
 * texture (emitted by devtools/gen/calamities_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/slag_warlord.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class SlagWarlordRenderer extends RavagerEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/slag_warlord.png");

	public SlagWarlordRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(RavagerEntityRenderState state) {
		return TEXTURE;
	}
}
