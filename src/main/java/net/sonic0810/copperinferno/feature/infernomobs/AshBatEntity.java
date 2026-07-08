package net.sonic0810.copperinferno.feature.infernomobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.world.World;

/**
 * An ash-grey bat that roosts in the Inferno (and, rarely, the Overworld). Behavior is pure
 * vanilla bat. Drops leather ({@code loot_table/entities/ash_bat.json}).
 */
public class AshBatEntity extends BatEntity {
	public AshBatEntity(EntityType<? extends BatEntity> type, World world) {
		super(type, world);
	}
}
