package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.world.World;

/**
 * A leather-winged bellows that flaps ceaselessly to feed the Inferno's forges. Behavior is
 * vanilla bat plus ONE tweak: a bellows must keep pumping, so it never roosts on ceilings - see
 * {@link #setRoosting(boolean)}. Drops Forge Bellows
 * ({@code loot_table/entities/bellows_bat.json}).
 */
public class BellowsBatEntity extends BatEntity {
	public BellowsBatEntity(EntityType<? extends BatEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void setRoosting(boolean roosting) {
		// TWEAK: never idle. BatEntity.setRoosting(boolean) is public (verified via javap);
		// every vanilla attempt to hang from a ceiling is forced back to flight.
		super.setRoosting(false);
	}
}
