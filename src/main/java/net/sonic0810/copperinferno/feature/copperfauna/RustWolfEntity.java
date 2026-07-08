package net.sonic0810.copperinferno.feature.copperfauna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A wolf with a rust-matted coat. Behavioral tweak: its rusty bite saps the target with
 * Weakness (tetanus, basically). Tame/breed behavior is inherited from {@link WolfEntity};
 * {@code createChild} is overridden only for type consistency (vanilla hard-codes
 * {@code EntityType.WOLF}; same fix as the infernomobs Cinder Strider).
 * Drops Rust Fangs ({@code loot_table/entities/rust_wolf.json}).
 */
public class RustWolfEntity extends WolfEntity {
	public RustWolfEntity(EntityType<? extends WolfEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0), this);
		}
		return hit;
	}

	@Override
	public RustWolfEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new RustWolfEntity(CopperFaunaFeature.RUST_WOLF, world);
	}
}
