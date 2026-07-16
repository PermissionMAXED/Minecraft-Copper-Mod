package net.sonic0810.copperinferno.feature.calamities.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.state.IronGolemEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Verdigris Monarch" calamity boss: the vanilla IronGolemEntityRenderer
 * (public non-final, Context-only ctor, verified via javap) with only the
 * getTexture(IronGolemEntityRenderState) overload swapped to the boss's recolored
 * texture (emitted by devtools/gen/calamities_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/verdigris_monarch.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class VerdigrisMonarchRenderer extends IronGolemEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/verdigris_monarch.png");

	public VerdigrisMonarchRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(IronGolemEntityRenderState state) {
		return TEXTURE;
	}
}
