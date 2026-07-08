package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

/**
 * A cube of dark, sticky cola built on the magma cube (fire-immune, splits on death).
 * Behavioral tweak: it splashes {@link ParticleTypes#SPLASH} instead of flames when it lands
 * ({@link #getParticles()}). Drops Cola Chunks ({@code loot_table/entities/cola_cube.json}).
 */
public class ColaCubeEntity extends MagmaCubeEntity {
	public ColaCubeEntity(EntityType<? extends MagmaCubeEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected ParticleEffect getParticles() {
		return ParticleTypes.SPLASH;
	}
}
