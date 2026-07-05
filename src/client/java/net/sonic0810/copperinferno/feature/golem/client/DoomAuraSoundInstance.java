package net.sonic0810.copperinferno.feature.golem.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.sonic0810.copperinferno.core.ModSounds;
import net.sonic0810.copperinferno.feature.golem.DrPepperGolemEntity;

/**
 * Looping positional DOOM track that follows a Dr.Pepper golem. Ultra loud up close, fading
 * linearly with distance, and done once the golem is removed or the player leaves the 10-block
 * aura.
 */
class DoomAuraSoundInstance extends MovingSoundInstance {
	/** Aura radius in blocks; beyond this the track stops. */
	static final double RANGE = 10.0;
	private static final float MAX_VOLUME = 4.0f;

	private final DrPepperGolemEntity golem;

	DoomAuraSoundInstance(DrPepperGolemEntity golem) {
		super(ModSounds.DOOM_KICK, SoundCategory.RECORDS, SoundInstance.createRandom());
		this.golem = golem;
		this.repeat = true;
		this.repeatDelay = 0;
		this.volume = MAX_VOLUME;
		this.x = golem.getX();
		this.y = golem.getY();
		this.z = golem.getZ();
	}

	@Override
	public void tick() {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (this.golem.isRemoved() || player == null) {
			this.setDone();
			return;
		}
		this.x = this.golem.getX();
		this.y = this.golem.getY();
		this.z = this.golem.getZ();
		double distance = Math.sqrt(player.squaredDistanceTo(this.golem));
		if (distance > RANGE) {
			this.setDone();
			return;
		}
		this.volume = MAX_VOLUME * (float) (1.0 - distance / RANGE);
	}

	void forceStop() {
		this.setDone();
	}
}
