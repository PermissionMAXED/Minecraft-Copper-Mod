package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.world.World;

/**
 * An owl-faced parrot with banked-coal plumage. Tweak: a nocturnal ember-sheen — while the
 * world is at night ({@code World.isNight()} verified via javap) it refreshes GLOWING every
 * 5 seconds, so ember owls light up after dark but fade by day (unlike the always-lit Ember
 * Moth). Drops feathers ({@code loot_table/entities/ember_owl.json}).
 */
public class EmberOwlEntity extends ParrotEntity {
	public EmberOwlEntity(EntityType<? extends ParrotEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (!this.getEntityWorld().isClient() && this.getEntityWorld().isNight() && this.age % 100 == 0) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 130, 0, false, false));
		}
	}
}
