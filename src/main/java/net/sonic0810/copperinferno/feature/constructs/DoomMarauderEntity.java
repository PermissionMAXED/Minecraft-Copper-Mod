package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PillagerEntity;
import net.minecraft.world.World;

/**
 * A pillager driven to a permanent DOOM-fueled frenzy, stalking the Inferno. Behavior is
 * vanilla pillager plus ONE tweak: an ever-refreshing Speed boost keeps it charging - see
 * {@link #tick()}. Drops Doom Emblems
 * ({@code loot_table/entities/doom_marauder.json}).
 */
public class DoomMarauderEntity extends PillagerEntity {
	public DoomMarauderEntity(EntityType<? extends PillagerEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tick() {
		super.tick();
		// TWEAK: DOOM frenzy. Refreshed every 5s server-side so the Speed I boost never
		// lapses. addStatusEffect(StatusEffectInstance) verified via javap.
		if (!this.getEntityWorld().isClient() && this.age % 100 == 0) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 140, 0));
		}
	}
}
