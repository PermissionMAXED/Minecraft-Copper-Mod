package net.sonic0810.copperinferno.feature.warhorns;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * One war horn, parameterized by blast sound (+ pitch) / cooldown / area effect. On use
 * (server side, like {@code feature/artifacts/DoomHornItem}): play the sound, run the
 * {@link HornEffect} around the hornblower, start the per-item cooldown. The effect cores
 * themselves are the static methods in {@link WarHornsFeature}.
 */
public class HornItem extends Item {
	/** The area effect a horn blast applies around the hornblower (server side only). */
	@FunctionalInterface
	public interface HornEffect {
		void apply(ServerWorld world, PlayerEntity user);
	}

	private final SoundEvent sound;
	private final float volume;
	private final float pitch;
	private final int cooldownTicks;
	private final HornEffect effect;

	public HornItem(SoundEvent sound, float volume, float pitch, int cooldownTicks,
			HornEffect effect, Settings settings) {
		super(settings);
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.cooldownTicks = cooldownTicks;
		this.effect = effect;
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		if (world instanceof ServerWorld serverWorld) {
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					this.sound, SoundCategory.PLAYERS, this.volume, this.pitch);
			this.effect.apply(serverWorld, user);
			user.getItemCooldownManager().set(user.getStackInHand(hand), this.cooldownTicks);
		}
		return ActionResult.SUCCESS;
	}
}
