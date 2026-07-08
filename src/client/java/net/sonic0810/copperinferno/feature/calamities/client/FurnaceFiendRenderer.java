package net.sonic0810.copperinferno.feature.calamities.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.VindicatorEntityRenderer;
import net.minecraft.client.render.entity.state.IllagerEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Furnace Fiend" calamity boss: the vanilla VindicatorEntityRenderer
 * (public non-final, Context-only ctor, verified via javap) with only the
 * getTexture(IllagerEntityRenderState) overload swapped to the boss's recolored
 * texture (emitted by devtools/gen/calamities_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/furnace_fiend.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class FurnaceFiendRenderer extends VindicatorEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/furnace_fiend.png");

	public FurnaceFiendRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(IllagerEntityRenderState state) {
		return TEXTURE;
	}
}
