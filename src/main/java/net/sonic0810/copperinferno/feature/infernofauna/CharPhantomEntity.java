package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.world.World;

/**
 * A phantom already burned black. Tweak: daylight cannot ignite it — vanilla
 * {@code PhantomEntity.tickMovement} calls {@code isAffectedByDaylight()} (protected on
 * MobEntity, call site bytecode-verified) and sets burning phantoms on fire; this override
 * returns false, so char phantoms keep circling under any sky. Swoop AI is inherited. Drops
 * phantom membranes ({@code loot_table/entities/char_phantom.json}).
 */
public class CharPhantomEntity extends PhantomEntity {
	public CharPhantomEntity(EntityType<? extends PhantomEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected boolean isAffectedByDaylight() {
		return false;
	}
}
