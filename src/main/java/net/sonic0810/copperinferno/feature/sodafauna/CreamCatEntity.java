package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.world.World;

/**
 * A plump cream-white cat raised on soda-fountain cream. Behavioral tweak: MAX_HEALTH 14 (a
 * vanilla cat has 10 — all that cream) in the default attributes registered by
 * {@link SodaFaunaFeature}. Drops Cream Swirls ({@code loot_table/entities/cream_cat.json}).
 */
public class CreamCatEntity extends CatEntity {
	public CreamCatEntity(EntityType<? extends CatEntity> type, World world) {
		super(type, world);
	}
}
