package net.sonic0810.copperinferno.feature.archfiends.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HoglinEntityRenderer;
import net.minecraft.client.render.entity.state.HoglinEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Molten Hoglin Tyrant" archfiend boss: the vanilla HoglinEntityRenderer
 * (public non-final, verified via javap) with only the getTexture(HoglinEntityRenderState)
 * overload swapped to the boss's recolored texture (emitted by
 * devtools/gen/archfiends_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/molten_hoglin_tyrant.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class MoltenHoglinTyrantRenderer extends HoglinEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/molten_hoglin_tyrant.png");

	public MoltenHoglinTyrantRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(HoglinEntityRenderState state) {
		return TEXTURE;
	}
}
