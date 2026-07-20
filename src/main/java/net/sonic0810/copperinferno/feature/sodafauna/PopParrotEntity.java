package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * A soda-orange parrot that pops and crackles like candy in cola. Behavioral tweak: it pops
 * musical {@link ParticleTypes#NOTE} particles while ticking (client-side, same proven pattern
 * as the Dr.Pepper golem aura; the note color rides on the x-velocity like the vanilla note
 * block). Drops Cherry Syrup ({@code loot_table/entities/pop_parrot.json}).
 */
public class PopParrotEntity extends ParrotEntity {
	public PopParrotEntity(EntityType<? extends ParrotEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.getEntityWorld();
		if (world.isClient()) {
			Random random = this.getRandom();
			if (random.nextInt(10) == 0) {
				world.addParticleClient(
						ParticleTypes.NOTE,
						this.getParticleX(0.6),
						this.getRandomBodyY() + 0.4,
						this.getParticleZ(0.6),
						random.nextInt(25) / 24.0,
						0.0,
						0.0);
			}
		}
	}
}
