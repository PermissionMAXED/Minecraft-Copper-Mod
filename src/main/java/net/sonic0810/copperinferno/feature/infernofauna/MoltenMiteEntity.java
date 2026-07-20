package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A mite of half-molten rock. Tweak: squashing it splashes liquid stone — whoever damages it is
 * set on fire for 2 seconds (shorter than the Flare Wisp's burn; same
 * {@code damage(ServerWorld, DamageSource, float)} hook, verified via javap). The vanilla
 * endermite despawn timer is inherited, so molten mites cool off and crumble after a couple of
 * minutes like their end-born cousins. Drops blaze powder
 * ({@code loot_table/entities/molten_mite.json}).
 */
public class MoltenMiteEntity extends EndermiteEntity {
	public MoltenMiteEntity(EntityType<? extends EndermiteEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean damage(ServerWorld world, DamageSource source, float amount) {
		boolean damaged = super.damage(world, source, amount);
		if (damaged && source.getAttacker() instanceof LivingEntity attacker && !attacker.isFireImmune()) {
			attacker.setOnFireFor(2.0f);
		}
		return damaged;
	}
}
