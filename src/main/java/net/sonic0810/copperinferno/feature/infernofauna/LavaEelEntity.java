package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.world.World;

/**
 * An eel-like guardian that swims the Slag Sea's lava. Tweak: submerged in lava it knits itself
 * back together — REGENERATION I is refreshed every 4 seconds while {@code isInLava()} (public
 * on Entity, verified via javap). The EntityType is registered fire-immune so the lava it lives
 * in cannot kill it; beam attack and flopping-on-land behavior are inherited. Drops Lava Eel
 * Fillets ({@code loot_table/entities/lava_eel.json}).
 */
public class LavaEelEntity extends GuardianEntity {
	public LavaEelEntity(EntityType<? extends GuardianEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (!this.getEntityWorld().isClient() && this.isInLava() && this.age % 80 == 0) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0, false, false));
		}
	}
}
