package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * A mischievous lemon-lime soda spirit built on the vex (flies through walls, melee charges).
 * Behavioral tweak: it trails lime fizz while flying (client-side {@link DustParticleEffect}
 * in {@link #tick()}, the same proven pattern as the Dr.Pepper golem aura). Drops Sprite
 * Essence ({@code loot_table/entities/soda_sprite.json}).
 */
public class SodaSpriteEntity extends VexEntity {
	/** Lemon-lime fizz color. */
	public static final int FIZZ_COLOR = 0xB4F04A;
	private static final DustParticleEffect FIZZ_PARTICLE = new DustParticleEffect(FIZZ_COLOR, 0.8f);

	public SodaSpriteEntity(EntityType<? extends VexEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.getEntityWorld();
		if (world.isClient()) {
			Random random = this.getRandom();
			if (random.nextInt(4) == 0) {
				world.addParticleClient(
						FIZZ_PARTICLE,
						this.getParticleX(0.5),
						this.getRandomBodyY(),
						this.getParticleZ(0.5),
						0.0,
						0.03,
						0.0);
			}
		}
	}
}
