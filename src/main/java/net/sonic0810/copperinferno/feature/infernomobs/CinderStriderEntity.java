package net.sonic0810.copperinferno.feature.infernomobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.world.World;

/**
 * A cinder-crusted strider that wades the Inferno's slag seas. Behavior is pure vanilla strider
 * (lava walking, saddling, warped-fungus breeding are all inherited). Drops Strider Shells
 * ({@code loot_table/entities/cinder_strider.json}).
 */
public class CinderStriderEntity extends StriderEntity {
	public CinderStriderEntity(EntityType<? extends StriderEntity> type, World world) {
		super(type, world);
	}
}
