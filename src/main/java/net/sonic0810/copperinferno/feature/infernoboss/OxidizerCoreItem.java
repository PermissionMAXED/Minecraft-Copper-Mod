package net.sonic0810.copperinferno.feature.infernoboss;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Summons {@link TheOxidizerEntity}. Only works when clicked on a block whose id path
 * contains {@code "copper"} (any vanilla or modded copper block); the boss erupts on the
 * clicked face with a thunder clap and the core is consumed.
 */
public class OxidizerCoreItem extends Item {
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
			TheOxidizerEntity boss = new TheOxidizerEntity(InfernoBossFeature.THE_OXIDIZER, serverWorld);
			boss.refreshPositionAndAngles(
					spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5,
					context.getPlayerYaw() + 180.0f, 0.0f);
			boss.setPersistent();
			serverWorld.spawnEntity(boss);
			serverWorld.playSound(null, spawnPos, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
					SoundCategory.HOSTILE, 1.0f, 1.0f);
			PlayerEntity player = context.getPlayer();
			if (player != null) {
				context.getStack().decrementUnlessCreative(1, player);
			} else {
				context.getStack().decrement(1);
			}
		}
		return ActionResult.SUCCESS;
	}
}
