package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.world.World;

/**
 * A moth-like bat with wings of living ember. Tweak: it permanently glows — the GLOWING effect
 * is refreshed every 100 ticks (no particles, no icon) so it shimmers through the Inferno's
 * gloom. Drops Ember Wings ({@code loot_table/entities/ember_moth.json}).
 */
public class EmberMothEntity extends BatEntity {
	public EmberMothEntity(EntityType<? extends BatEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (!this.getEntityWorld().isClient() && this.age % 100 == 0) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 130, 0, false, false));
		}
	}
}
