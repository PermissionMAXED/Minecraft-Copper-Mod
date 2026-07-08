package net.sonic0810.copperinferno.feature.infernoboss.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.state.IronGolemEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for "The Oxidizer" boss: the vanilla IronGolemEntityRenderer (public
 * non-final, Context-only ctor, verified via javap) with only the
 * getTexture(IronGolemEntityRenderState) overload swapped to the boss's verdigris
 * recolor (emitted by devtools/gen/infernoboss_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/the_oxidizer.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette remap
 * of the iron golem texture, so all UV mapping is preserved.
 */
public class TheOxidizerRenderer extends IronGolemEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/the_oxidizer.png");

	public TheOxidizerRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(IronGolemEntityRenderState state) {
		return TEXTURE;
	}
}
