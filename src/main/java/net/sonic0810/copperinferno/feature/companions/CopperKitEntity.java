package net.sonic0810.copperinferno.feature.companions;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

/**
 * A copper-furred kitten, tameable like any cat. Companion perk: while it sits and purrs, its
 * owner within {@value #PURR_RANGE} blocks is granted 10 seconds of Haste once every 60 seconds
 * (checked in {@code mobTick(ServerWorld)} — signature verified via javap on CatEntity, which
 * widens {@code MobEntity.mobTick} to public). {@code createChild} is overridden for type
 * consistency because vanilla {@code CatEntity.createChild} hard-codes {@code EntityType.CAT}
 * (same fix as the copperfauna Gutter Cat). Drops String
 * ({@code loot_table/entities/copper_kit.json}).
 */
public class CopperKitEntity extends CatEntity {
	/** How far (blocks) the sitting purr reaches the owner. */
	private static final double PURR_RANGE = 16.0;
	/** One purr buff per 60 seconds. */
	private static final int PURR_INTERVAL_TICKS = 20 * 60;
	/** 10 seconds of Haste. */
	private static final int HASTE_DURATION_TICKS = 20 * 10;

	private int purrCooldown = PURR_INTERVAL_TICKS;

	public CopperKitEntity(EntityType<? extends CatEntity> type, World world) {
		super(type, world);
	}

	@Override
	public void mobTick(ServerWorld world) {
		super.mobTick(world);
		if (!this.isTamed() || !this.isInSittingPose()) {
			return;
		}
		if (--this.purrCooldown > 0) {
			return;
		}
		this.purrCooldown = PURR_INTERVAL_TICKS;
		LivingEntity owner = this.getOwner();
		if (owner != null && owner.squaredDistanceTo(this) <= PURR_RANGE * PURR_RANGE) {
			owner.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, HASTE_DURATION_TICKS, 0), this);
			this.playSound(SoundEvents.ENTITY_CAT_PURR, 1.0f, 1.0f);
		}
	}

	@Override
	public CopperKitEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new CopperKitEntity(CompanionsFeature.COPPER_KIT, world);
	}
}
