package net.sonic0810.copperinferno.feature.artifacts.client;

import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;

/**
 * Render state for the trophy pedestal (1.21.9 render-state contract). Mirrors vanilla
 * {@code BrushableBlockEntityRenderState}: the displayed stack is baked into a reusable
 * {@link ItemRenderState} during {@code updateRenderState}.
 */
public class TrophyPedestalRenderState extends BlockEntityRenderState {
	public final ItemRenderState itemRenderState = new ItemRenderState();
}
