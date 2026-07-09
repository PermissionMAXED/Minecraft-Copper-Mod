package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A zoglin fitted with grinding drums for tusks, rampaging through the Inferno. Behavior is
 * vanilla zoglin plus ONE tweak: grinding it down pays out triple experience - see
 * {@link #getExperienceToDrop(ServerWorld)}. Drops leather
 * ({@code loot_table/entities/grinder_zoglin.json}).
 */
public class GrinderZoglinEntity extends ZoglinEntity {
	public GrinderZoglinEntity(EntityType<? extends ZoglinEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected int getExperienceToDrop(ServerWorld world) {
		// TWEAK: XP grinder. protected int getExperienceToDrop(ServerWorld) verified via
		// javap on LivingEntity.
		return super.getExperienceToDrop(world) * 3;
	}
}
