package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;

/**
 * A soda-yellow chicken with a carbonated metabolism. Behavioral tweak: it lays eggs at least
 * twice as fast — the public vanilla {@code eggLayTime} countdown (rolled as 6000..12000 ticks)
 * is capped at 3000 every tick ({@link #tickMovement()}). Drops Fizz Globules
 * ({@code loot_table/entities/fizzy_chicken.json}).
 */
public class FizzyChickenEntity extends ChickenEntity {
	public FizzyChickenEntity(EntityType<? extends ChickenEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (!this.getEntityWorld().isClient() && this.isAlive() && this.eggLayTime > 3000) {
			this.eggLayTime = 3000;
		}
	}
}
