package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.world.World;

/**
 * A bat crusted in blue-green patina. Behavioral tweak: it sheds a faint verdigris dust trail
 * while flying (same client-side particle pattern as {@code DrPepperGolemEntity}). Drops Patina
 * Membranes ({@code loot_table/entities/patina_bat.json}).
 */
public class PatinaBatEntity extends BatEntity {
	private static final DustParticleEffect PATINA_PARTICLE = new DustParticleEffect(0x43A47E, 0.8f);

	public PatinaBatEntity(EntityType<? extends BatEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tick() {
		super.tick();
		World world = this.getEntityWorld();
		if (world.isClient() && this.getRandom().nextInt(4) == 0) {
			world.addParticleClient(
					PATINA_PARTICLE,
					this.getParticleX(0.5),
					this.getRandomBodyY(),
					this.getParticleZ(0.5),
					0.0,
					-0.01,
					0.0);
		}
	}
}
