package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.world.World;

/**
 * A pale, bubbling cube of pure carbonated water built on the magma cube (fire-immune, splits
 * on death). Behavioral tweak: it is just fizzy water — its contact damage is halved compared
 * to the magma cube it is built on ({@link #getDamageAmount()}). Drops Float Foam
 * ({@code loot_table/entities/carbonated_cube.json}).
 */
public class CarbonatedCubeEntity extends MagmaCubeEntity {
	public CarbonatedCubeEntity(EntityType<? extends MagmaCubeEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected float getDamageAmount() {
		return Math.max(1.0f, super.getDamageAmount() / 2.0f);
	}
}
