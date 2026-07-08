package net.sonic0810.copperinferno.feature.statue;

import java.util.function.Consumer;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.StringHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModBlockEntities;
import net.sonic0810.copperinferno.core.ModBlocks;
import net.sonic0810.copperinferno.core.ModCreativeTab;

/**
 * Copper player statues. Content is registered by the statue feature worker.
 */
public final class PlayerStatueFeature {
	private PlayerStatueFeature() {
	}

	public static final int MAX_NAME_LENGTH = 16;
	/** Players must be within this distance (squared) of the statue to rename it. */
	private static final double MAX_EDIT_DISTANCE_SQ = 64.0;

	/** Assigned in init(). */
	public static Block COPPER_PLAYER_STATUE;
	/** Assigned in init(). */
	public static BlockEntityType<CopperPlayerStatueBlockEntity> COPPER_PLAYER_STATUE_BLOCK_ENTITY;

	/**
	 * Installed by {@code PlayerStatueFeatureClient}; opens the client-side name-entry screen.
	 * Null on a dedicated server.
	 */
	public static Consumer<BlockPos> statueScreenOpener;

	public static void init() {
		COPPER_PLAYER_STATUE = ModBlocks.register(
				"copper_player_statue",
				CopperPlayerStatueBlock::new,
				AbstractBlock.Settings.create()
						.strength(3.0f, 6.0f)
						.sounds(BlockSoundGroup.COPPER)
						.nonOpaque(),
				true);

		COPPER_PLAYER_STATUE_BLOCK_ENTITY = ModBlockEntities.register(
				"copper_player_statue",
				CopperPlayerStatueBlockEntity::new,
				COPPER_PLAYER_STATUE);

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY)
				.register(entries -> entries.add(COPPER_PLAYER_STATUE));

		PayloadTypeRegistry.playC2S().register(SetStatueNamePayload.ID, SetStatueNamePayload.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(SetStatueNamePayload.ID, PlayerStatueFeature::handleSetStatueName);
	}

	private static void handleSetStatueName(SetStatueNamePayload payload, ServerPlayNetworking.Context context) {
		ServerPlayerEntity player = context.player();
		World world = player.getEntityWorld();
		BlockPos pos = payload.pos();
		String name = payload.name().trim();
		if (name.isEmpty() || name.length() > MAX_NAME_LENGTH || !StringHelper.isValidPlayerName(name)) {
			return;
		}
		if (player.isSpectator()) {
			return;
		}
		// World.canEntityModifyAt is the 1.21.9 replacement for canPlayerModifyAt (verified via
		// javap); it covers spawn protection and other per-position modification rules.
		if (!world.canEntityModifyAt(player, pos)) {
			return;
		}
		if (player.squaredDistanceTo(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) > MAX_EDIT_DISTANCE_SQ) {
			return;
		}
		if (world.getBlockEntity(pos) instanceof CopperPlayerStatueBlockEntity statue) {
			statue.setOwnerName(name);
		}
	}
}
