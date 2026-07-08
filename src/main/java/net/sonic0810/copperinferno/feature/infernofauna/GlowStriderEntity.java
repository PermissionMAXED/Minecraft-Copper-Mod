package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A strider with a phosphorescent hide, easy to spot far across the Slag Sea. Tweak: it
 * permanently glows — GLOWING is refreshed every 100 ticks (no particles, no icon). Lava
 * walking, saddling and warped-fungus breeding are inherited; {@code createChild} is overridden
 * only for type consistency (vanilla hard-codes {@code EntityType.STRIDER}; same fix as the
 * infernomobs Cinder Strider). Drops glowstone dust
 * ({@code loot_table/entities/glow_strider.json}).
 */
public class GlowStriderEntity extends StriderEntity {
	public GlowStriderEntity(EntityType<? extends StriderEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (!this.getEntityWorld().isClient() && this.age % 100 == 0) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 130, 0, false, false));
		}
	}

	@Override
	public GlowStriderEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new GlowStriderEntity(InfernoFaunaFeature.GLOW_STRIDER, world);
	}
}
