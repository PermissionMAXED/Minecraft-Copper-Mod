package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.world.World;

/**
 * A beetle wearing a bottlecap for a shell. Behavioral tweak: ARMOR 6 (a vanilla silverfish has
 * none — the cap is solid crimped steel) in the default attributes registered by
 * {@link SodaFaunaFeature}. Drops Bottlecaps ({@code loot_table/entities/bottlecap_beetle.json}).
 */
public class BottlecapBeetleEntity extends SilverfishEntity {
	public BottlecapBeetleEntity(EntityType<? extends SilverfishEntity> type, World world) {
		super(type, world);
	}
}
