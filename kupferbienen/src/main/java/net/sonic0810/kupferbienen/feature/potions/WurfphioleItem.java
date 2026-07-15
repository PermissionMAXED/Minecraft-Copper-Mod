package net.sonic0810.kupferbienen.feature.potions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * Splash vial: a {@link BrewPotionItem} (so the brewing-stand builder accepts it) that is
 * THROWN instead of drunk. {@code use()} mirrors the vanilla
 * {@code SplashPotionItem}/{@code ThrowablePotionItem} bytecode exactly (verified via javap -c):
 * splash-potion throw sound with the same randomized pitch, then on the server
 * {@code ProjectileEntity.spawnWithVelocity(creator, world, stack, user, -20.0F, 0.5F, 1.0F)}
 * (pitch offset -20, ThrowablePotionItem.POWER = 0.5), used-stat increment and
 * {@code decrementUnlessCreative}.
 */
public class WurfphioleItem extends BrewPotionItem {
	public WurfphioleItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		world.playSound(null, user.getX(), user.getY(), user.getZ(),
				SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS,
				0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

		if (world instanceof ServerWorld serverWorld) {
			ProjectileEntity.spawnWithVelocity(
					(spawnWorld, thrower, thrownStack) -> new GeworfenePhioleEntity(spawnWorld, thrower, thrownStack),
					serverWorld, stack, user, -20.0F, 0.5F, 1.0F);
		}

		user.incrementStat(Stats.USED.getOrCreateStat(this));
		stack.decrementUnlessCreative(1, user);
		return ActionResult.SUCCESS;
	}
}
