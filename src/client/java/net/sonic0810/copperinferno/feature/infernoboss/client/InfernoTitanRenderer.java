package net.sonic0810.copperinferno.feature.infernoboss.client;

import net.minecraft.client.render.entity.BlazeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Inferno Titan" boss: the vanilla BlazeEntityRenderer (public
 * non-final, Context-only ctor, verified via javap) with only the
 * getTexture(LivingEntityRenderState) overload — the blaze renderer's own state type —
 * swapped to the boss's charred-ember recolor (emitted by
 * devtools/gen/infernoboss_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/inferno_titan.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette remap
 * of the blaze texture, so all UV mapping is preserved.
 */
public class InfernoTitanRenderer extends BlazeEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/inferno_titan.png");

	public InfernoTitanRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(LivingEntityRenderState state) {
		return TEXTURE;
	}
}
