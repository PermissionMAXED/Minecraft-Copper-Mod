package net.sonic0810.copperinferno.feature.calamities.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WitherSkeletonEntityRenderer;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Ashking Wither" calamity boss: the vanilla WitherSkeletonEntityRenderer
 * (public non-final, Context-only ctor, verified via javap) with only the
 * getTexture(SkeletonEntityRenderState) overload swapped to the boss's recolored
 * texture (emitted by devtools/gen/calamities_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/ashking_wither.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class AshkingWitherRenderer extends WitherSkeletonEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/ashking_wither.png");

	public AshkingWitherRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(SkeletonEntityRenderState state) {
		return TEXTURE;
	}
}
