package net.sonic0810.copperinferno.feature.archfiends.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.GhastEntityRenderer;
import net.minecraft.client.render.entity.state.GhastEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Dread Ghast Sovereign" archfiend boss: the vanilla GhastEntityRenderer
 * (public non-final, verified via javap) with only the getTexture(GhastEntityRenderState)
 * overload swapped to the boss's recolored texture (emitted by
 * devtools/gen/archfiends_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/dread_ghast_sovereign.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class DreadGhastSovereignRenderer extends GhastEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/dread_ghast_sovereign.png");

	public DreadGhastSovereignRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(GhastEntityRenderState state) {
		return TEXTURE;
	}
}
