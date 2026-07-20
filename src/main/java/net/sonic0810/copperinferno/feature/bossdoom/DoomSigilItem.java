package net.sonic0810.copperinferno.feature.bossdoom;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.ModSounds;

/**
 * Shared summon item for the six Doom Ascendancy bosses (TitanSigilItem pattern,
 * parameterized on a {@link BossFactory}). Works both on a block (boss rises on the clicked
 * face) and in the air (boss appears a few blocks ahead of the player). Never works on
 * peaceful difficulty (the hostile boss would instantly despawn, wasting the item) - shown
 * as a translatable actionbar message without consuming the item. If the boss's collision
 * box is obstructed it is nudged up to 8 blocks upward; with no clear spot the summon
 * aborts (actionbar message) and the item is NOT consumed. A successful summon claps
 * thunder AND drops the existing {@code ModSounds.DOOM_KICK} beat - the doom has begun.
 */
public class DoomSigilItem extends Item {
	/** Creates the (not yet spawned) boss entity; bound to the boss's EntityType at registration. */
	@FunctionalInterface
	public interface BossFactory {
		MobEntity create(ServerWorld world);
	}

	private static final int MAX_UPWARD_NUDGE = 8;

	private final BossFactory factory;

	public DoomSigilItem(Settings settings, BossFactory factory) {
		super(settings);
		this.factory = factory;
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		Vec3d spawnPos = Vec3d.ofBottomCenter(context.getBlockPos().offset(context.getSide()));
		return trySummon(context.getWorld(), context.getPlayer(), spawnPos, context.getStack());
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		Vec3d look = user.getRotationVector();
		Vec3d spawnPos = user.getEntityPos().add(look.x * 4.0, 0.0, look.z * 4.0);
		return trySummon(world, user, spawnPos, user.getStackInHand(hand));
	}

	private ActionResult trySummon(World world, PlayerEntity player, Vec3d spawnPos, ItemStack stack) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			if (!world.isClient() && player != null) {
				player.sendMessage(Text.translatable("message.copper_inferno.doom_sigil.peaceful"), true);
			}
			return ActionResult.FAIL;
		}
		if (world instanceof ServerWorld serverWorld) {
			MobEntity boss = this.factory.create(serverWorld);
			float yaw = player != null ? player.getYaw() + 180.0f : 0.0f;
			if (!nudgeToEmptySpace(serverWorld, boss, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), yaw)) {
				if (player != null) {
					player.sendMessage(Text.translatable("message.copper_inferno.doom_sigil.blocked"), true);
				}
				return ActionResult.FAIL;
			}
			serverWorld.spawnEntity(boss);
			serverWorld.playSound(null, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(),
					SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.HOSTILE, 1.0f, 0.8f);
			serverWorld.playSound(null, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(),
					ModSounds.DOOM_KICK, SoundCategory.HOSTILE, 1.5f, 1.0f);
			if (player != null) {
				stack.decrementUnlessCreative(1, player);
			} else {
				stack.decrement(1);
			}
		}
		return ActionResult.SUCCESS;
	}

	/**
	 * Positions the boss at (x, y, z), nudging upward one block at a time (max 8) until its
	 * collision box is unobstructed ({@code CollisionView.isSpaceEmpty(Entity)}, so the boss
	 * never suffocates inside blocks). Returns false when no clear spot exists
	 * (TitanSigilItem pattern).
	 */
	private static boolean nudgeToEmptySpace(ServerWorld world, MobEntity boss,
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
