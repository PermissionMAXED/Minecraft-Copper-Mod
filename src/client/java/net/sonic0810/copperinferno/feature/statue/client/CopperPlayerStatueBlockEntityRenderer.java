package net.sonic0810.copperinferno.feature.statue.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.PlayerSkinCache;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.sonic0810.copperinferno.feature.statue.CopperPlayerStatueBlock;
import net.sonic0810.copperinferno.feature.statue.CopperPlayerStatueBlockEntity;

/**
 * Renders a copper-tinted, statically posed player model with the skin of the stored owner
 * profile. Uses the 1.21.9 render-state contract (createRenderState / updateRenderState /
 * render(state, matrices, queue, camera)), mirroring
 * {@code CopperGolemStatueBlockEntityRenderer}. The model is submitted through
 * {@code OrderedRenderCommandQueue.submitModelPart} with an ARGB tint for the copper look.
 */
public class CopperPlayerStatueBlockEntityRenderer
		implements BlockEntityRenderer<CopperPlayerStatueBlockEntity, CopperPlayerStatueRenderState> {
	/** ARGB copper tint applied to the whole skin. */
	private static final int COPPER_TINT = 0xFFB87350;

	private final PlayerEntityModel model;
	private final PlayerSkinCache skinCache;

	public CopperPlayerStatueBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
		this.model = new PlayerEntityModel(context.getLayerModelPart(EntityModelLayers.PLAYER), false);
		this.skinCache = context.playerSkinRenderCache();
	}

	@Override
	public CopperPlayerStatueRenderState createRenderState() {
		return new CopperPlayerStatueRenderState();
	}

	@Override
	public void updateRenderState(CopperPlayerStatueBlockEntity blockEntity, CopperPlayerStatueRenderState state,
			float tickProgress, Vec3d cameraPos, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
		BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
		state.facing = blockEntity.getCachedState().contains(CopperPlayerStatueBlock.FACING)
				? blockEntity.getCachedState().get(CopperPlayerStatueBlock.FACING)
				: net.minecraft.util.math.Direction.NORTH;
		// No name set -> copper silhouette using the default skin render layer.
		RenderLayer layer = PlayerSkinCache.DEFAULT_RENDER_LAYER;
		if (blockEntity.getOwner().isPresent()) {
			PlayerSkinCache.Entry entry = this.skinCache.get(blockEntity.getOwner().get());
			if (entry != null) {
				layer = entry.getRenderLayer();
			}
		}
		state.skinRenderLayer = layer;
	}

	@Override
	public void render(CopperPlayerStatueRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue,
			CameraRenderState cameraState) {
		if (state.skinRenderLayer == null) {
			return;
		}
		matrices.push();
		matrices.translate(0.5f, 0.0f, 0.5f);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-state.facing.getPositiveHorizontalDegrees()));
		// Entity-model convention: flip, then drop by 1.501 so the feet rest on the block bottom.
		matrices.scale(-1.0f, -1.0f, 1.0f);
		matrices.translate(0.0f, -1.501f, 0.0f);
		// Static statue: base pose of the baked player model, no setAngles/animation. Submitting
		// the root part renders the whole hierarchy (incl. hat/jacket/sleeves overlays).
		queue.submitModelPart(this.model.getRootPart(), matrices, state.skinRenderLayer,
				state.lightmapCoordinates, OverlayTexture.DEFAULT_UV, null, COPPER_TINT, state.crumblingOverlay);
		matrices.pop();
	}
}
