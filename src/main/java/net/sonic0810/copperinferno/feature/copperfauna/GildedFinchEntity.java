package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.world.World;

/**
 * A finch-sized parrot in gilded plumage. Behavioral tweak: MAX_HEALTH 10 (vanilla parrot: 6)
 * in the default attributes registered by {@link CopperFaunaFeature}. Drops Gilded Feathers
 * ({@code loot_table/entities/gilded_finch.json}).
 */
public class GildedFinchEntity extends ParrotEntity {
	public GildedFinchEntity(EntityType<? extends ParrotEntity> type, World world) {
		super(type, world);
	}
}
