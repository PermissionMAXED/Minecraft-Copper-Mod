package net.sonic0810.copperinferno.feature.artifacts.client;

import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.sonic0810.copperinferno.feature.artifacts.TrophyPedestalBlockEntity;

/**
 * Draws the enshrined trophy item floating above the pedestal column. Uses the 1.21.9
 * render-state contract (createRenderState / updateRenderState / render(state, matrices,
 * queue, camera)) exactly like {@code CopperPlayerStatueBlockEntityRenderer}; the item is
 * baked with {@code ItemModelManager.clearAndUpdate} and submitted through
 * {@code ItemRenderState.render}, the same pipeline vanilla's
 * {@code BrushableBlockEntityRenderer} uses (verified via javap/bytecode).
 */
public class TrophyPedestalBlockEntityRenderer
		implements BlockEntityRenderer<TrophyPedestalBlockEntity, TrophyPedestalRenderState> {
	private final ItemModelManager itemModelManager;

	public TrophyPedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
		this.itemModelManager = context.itemModelManager();
	}

	@Override
	public TrophyPedestalRenderState createRenderState() {
		return new TrophyPedestalRenderState();
	}

	@Override
	public void updateRenderState(TrophyPedestalBlockEntity blockEntity, TrophyPedestalRenderState state,
			float tickProgress, Vec3d cameraPos, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
		BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
		this.itemModelManager.clearAndUpdate(state.itemRenderState, blockEntity.getStack(),
				ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
	}

	@Override
	public void render(TrophyPedestalRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue,
			CameraRenderState cameraState) {
		if (state.itemRenderState.isEmpty()) {
			return;
		}
		matrices.push();
		matrices.translate(0.5f, 1.05f, 0.5f);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(45.0f));
		matrices.scale(0.6f, 0.6f, 0.6f);
		state.itemRenderState.render(matrices, queue, state.lightmapCoordinates, OverlayTexture.DEFAULT_UV, 0);
		matrices.pop();
	}
}
