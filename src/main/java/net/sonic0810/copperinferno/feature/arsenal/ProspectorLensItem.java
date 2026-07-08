package net.sonic0810.copperinferno.feature.arsenal;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * "Prospector Lens" — peer through it (use) to count every ore block within 8 blocks (cube
 * scan; a block counts as ore when its registry id path ends in {@code _ore}, which covers
 * vanilla, infernium and all 12 WP5 gem/alloy ores). The tally is shown as an action-bar
 * overlay ({@code PlayerEntity.sendMessage(Text, true)}). 5s cooldown, never consumed.
 */
public class ProspectorLensItem extends Item {
	private static final int COOLDOWN_TICKS = 5 * 20;
	private static final int SCAN_RADIUS = 8;

	public ProspectorLensItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			BlockPos center = user.getBlockPos();
			int count = 0;
			for (BlockPos pos : BlockPos.iterate(center.add(-SCAN_RADIUS, -SCAN_RADIUS, -SCAN_RADIUS),
					center.add(SCAN_RADIUS, SCAN_RADIUS, SCAN_RADIUS))) {
				if (Registries.BLOCK.getId(serverWorld.getBlockState(pos).getBlock()).getPath().endsWith("_ore")) {
					count++;
				}
			}

			user.sendMessage(Text.translatable("item.copper_inferno.prospector_lens.result", count), true);
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 1.0F, 0.8F);
			user.getItemCooldownManager().set(stack, COOLDOWN_TICKS);
		}

		return ActionResult.SUCCESS;
	}
}
