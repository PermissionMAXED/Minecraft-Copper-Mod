package net.sonic0810.copperinferno.feature.golem;

import net.minecraft.block.Oxidizable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CopperGolemEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

/**
 * A Copper Golem that drank a Dr.Pepper. Behaves like the vanilla golem but never oxidizes (and
 * therefore never turns into a statue) and is surrounded by a maroon dust aura. While a player is
 * within 10 blocks, the client plays the DOOM track (see {@code DrPepperGolemFeatureClient}).
 *
 * <p>Statue note: {@code CopperGolemEntity#canTurnIntoStatue(World)} is PRIVATE in 1.21.9
 * (verified via javap -p), so it cannot be overridden. However, {@code serverTick} only calls it
 * when the tracked oxidation level is {@code OXIDIZED}, and every level change funnels through the
 * public {@link #setOxidationLevel} — pinning the level to {@code UNAFFECTED} here therefore
 * blocks both oxidation and the statue conversion.
 */
public class DrPepperGolemEntity extends CopperGolemEntity {
	/** Maroon aura color, also used by the transformation burst. */
	public static final int AURA_COLOR = 0x6E0F1A;
	private static final DustParticleEffect AURA_PARTICLE = new DustParticleEffect(AURA_COLOR, 1.0f);

	public DrPepperGolemEntity(EntityType<? extends GolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void setOxidationLevel(Oxidizable.OxidationLevel level) {
		super.setOxidationLevel(Oxidizable.OxidationLevel.UNAFFECTED);
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.getEntityWorld();
		if (world.isClient()) {
			Random random = this.getRandom();
			// Occasional maroon dust to distinguish the Dr.Pepper golem from the vanilla one.
			if (random.nextInt(3) == 0) {
				world.addParticleClient(
						AURA_PARTICLE,
						this.getParticleX(0.6),
						this.getRandomBodyY() + 0.15,
						this.getParticleZ(0.6),
						0.0,
						0.02,
						0.0);
			}
		}
	}
}
