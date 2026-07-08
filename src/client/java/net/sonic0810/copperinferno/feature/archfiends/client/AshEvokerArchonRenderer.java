package net.sonic0810.copperinferno.feature.archfiends.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EvokerEntityRenderer;
import net.minecraft.client.render.entity.state.EvokerEntityRenderState;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.util.Identifier;

/**
 * Renderer for the "Ash Evoker Archon" archfiend boss: the vanilla EvokerEntityRenderer
 * (public non-final, verified via javap) with only the getTexture(EvokerEntityRenderState)
 * overload swapped to the boss's recolored texture (emitted by
 * devtools/gen/archfiends_gen.py into
 * src/client/resources/assets/copper_inferno/textures/entity/ash_evoker_archon.png).
 * Model and animations stay vanilla; the texture is a pure luminance->palette
 * remap of the base mob's texture, so all UV mapping is preserved.
 */
public class AshEvokerArchonRenderer extends EvokerEntityRenderer<EvokerEntity> {
	private static final Identifier TEXTURE =
			Identifier.of("copper_inferno", "textures/entity/ash_evoker_archon.png");

	public AshEvokerArchonRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(EvokerEntityRenderState state) {
		return TEXTURE;
	}
}
