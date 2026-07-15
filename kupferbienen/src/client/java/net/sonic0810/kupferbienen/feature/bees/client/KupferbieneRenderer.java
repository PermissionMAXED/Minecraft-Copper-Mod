package net.sonic0810.kupferbienen.feature.bees.client;

import net.minecraft.client.render.entity.BeeEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.BeeEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * Vanilla {@link BeeEntityRenderer} (public non-final, Context-only ctor, verified via javap)
 * with only the {@code getTexture(BeeEntityRenderState)} overload swapped to the fixed copper
 * texture (a pure luminance remap of the vanilla bee texture emitted by
 * devtools/gen/kupferbienen_gen.py, so all UV mapping is preserved). Model and animations stay
 * vanilla; the angry/nectar texture variants are intentionally collapsed into the one look.
 */
public class KupferbieneRenderer extends BeeEntityRenderer {
	private static final Identifier TEXTURE =
			Identifier.of("kupferbienen", "textures/entity/kupferbiene.png");

	public KupferbieneRenderer(EntityRendererFactory.Context context) {
		super(context);
	}

	@Override
	public Identifier getTexture(BeeEntityRenderState state) {
		return TEXTURE;
	}
}
