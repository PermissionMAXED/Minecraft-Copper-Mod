package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.world.World;

/**
 * A root-beer-brown hoglin that wandered out of the Nether and stayed for the taste. Behavioral
 * tweak: {@link #canConvert()} returns false, so unlike a vanilla hoglin it never zombifies in
 * the Overworld (mandatory for an Overworld-native hoglin — the vanilla one converts to a zoglin
 * after 300 ticks outside the Nether). Drops Cola Chunks
 * ({@code loot_table/entities/root_beer_boar.json}).
 */
public class RootBeerBoarEntity extends HoglinEntity {
	public RootBeerBoarEntity(EntityType<? extends HoglinEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean canConvert() {
		return false;
	}
}
