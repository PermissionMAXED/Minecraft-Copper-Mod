package net.sonic0810.copperinferno.feature.statue.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.util.math.Direction;

/**
 * Render state for the copper player statue (1.21.9 render-state contract).
 */
public class CopperPlayerStatueRenderState extends BlockEntityRenderState {
	public Direction facing = Direction.NORTH;
	/** Render layer for the resolved player skin; null renders nothing. */
	public RenderLayer skinRenderLayer;
}
