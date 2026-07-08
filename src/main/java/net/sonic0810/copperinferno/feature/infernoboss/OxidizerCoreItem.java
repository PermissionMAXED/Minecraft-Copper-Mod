package net.sonic0810.copperinferno.feature.infernoboss;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Summons {@link TheOxidizerEntity}. Only works when clicked on a block whose id path
 * contains {@code "copper"} (any vanilla or modded copper block); the boss erupts on the
 * clicked face with a thunder clap and the core is consumed. If the boss's collision box
 * is obstructed it is nudged up to 8 blocks upward; with no clear spot the summon aborts
 * (actionbar message) and the core is NOT consumed.
 */
public class OxidizerCoreItem extends Item {
	private static final int MAX_UPWARD_NUDGE = 8;

	public OxidizerCoreItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		Identifier blockId = Registries.BLOCK.getId(world.getBlockState(pos).getBlock());
		if (!blockId.getPath().contains("copper")) {
			return ActionResult.PASS;
		}
		if (world instanceof ServerWorld serverWorld) {
			BlockPos spawnPos = pos.offset(context.getSide());
			PlayerEntity player = context.getPlayer();
			TheOxidizerEntity boss = new TheOxidizerEntity(InfernoBossFeature.THE_OXIDIZER, serverWorld);
			if (!nudgeToEmptySpace(serverWorld, boss,
					spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5,
					context.getPlayerYaw() + 180.0f)) {
				if (player != null) {
					player.sendMessage(Text.translatable("message.copper_inferno.oxidizer_core.blocked"), true);
				}
				return ActionResult.FAIL;
			}
			serverWorld.spawnEntity(boss);
			serverWorld.playSound(null, spawnPos, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
					SoundCategory.HOSTILE, 1.0f, 1.0f);
			if (player != null) {
				context.getStack().decrementUnlessCreative(1, player);
			} else {
				context.getStack().decrement(1);
			}
		}
		return ActionResult.SUCCESS;
	}

	/**
	 * Positions the boss at (x, y, z), nudging upward one block at a time (max 8) until its
	 * collision box is unobstructed ({@code CollisionView.isSpaceEmpty(Entity)}, so the boss
	 * never suffocates inside blocks). Returns false when no clear spot exists.
	 */
	private static boolean nudgeToEmptySpace(ServerWorld world, TheOxidizerEntity boss,
			double x, double y, double z, float yaw) {
		for (int dy = 0; dy <= MAX_UPWARD_NUDGE; dy++) {
			boss.refreshPositionAndAngles(x, y + dy, z, yaw, 0.0f);
			if (world.isSpaceEmpty(boss)) {
				return true;
			}
		}
		return false;
	}
}
