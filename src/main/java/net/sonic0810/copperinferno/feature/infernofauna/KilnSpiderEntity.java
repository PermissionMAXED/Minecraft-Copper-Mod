package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.world.World;

/**
 * A brick-red spider that nests inside abandoned kilns. Tweak: kiln-hardened chitin —
 * {@code isFireImmune()} returns true, so fire, lava splashes and magma blocks cannot touch it
 * (unlike the Ash Stalker, which avoids the flames instead). Drops string
 * ({@code loot_table/entities/kiln_spider.json}).
 */
public class KilnSpiderEntity extends SpiderEntity {
	public KilnSpiderEntity(EntityType<? extends SpiderEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean isFireImmune() {
		return true;
	}
}
