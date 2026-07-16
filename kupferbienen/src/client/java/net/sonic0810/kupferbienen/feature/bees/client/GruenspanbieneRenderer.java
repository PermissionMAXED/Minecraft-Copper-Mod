package net.sonic0810.kupferbienen.feature.bees.client;

import net.minecraft.client.render.entity.BeeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.BeeEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Vanilla {@link BeeEntityRenderer} with only the {@code getTexture(BeeEntityRenderState)}
 * overload swapped to the fixed verdigris texture (see {@code KupferbieneRenderer} for the
 * pattern rationale).
 */
public class GruenspanbieneRenderer extends BeeEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("kupferbienen", "textures/entity/gruenspanbiene.png");

	public GruenspanbieneRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(BeeEntityRenderState state) {
		return TEXTURE;
	}
}
