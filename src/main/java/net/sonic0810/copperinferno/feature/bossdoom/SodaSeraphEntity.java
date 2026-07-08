package net.sonic0810.copperinferno.feature.bossdoom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Boss 3 "Soda Seraph": a haloed sugar-winged phantom (attributes registered in
 * {@link BossDoomFeature}: MAX_HEALTH 300, ATTACK_DAMAGE 8, SCALE 2.6). Mechanics:
 * <ul>
 * <li>Sugar-rush dive (on-hit debuff): every successful swoop attack overdoses the victim
 * with Hunger, Weakness and Nausea under a firework burst and phantom shriek - the vanilla
 * phantom circling/swoop AI supplies the dives.</li>
 * <li>Sugar storm (timed special): every 120 ticks (60 when crashing) every player within
 * 10 blocks it can see is sprayed with Hunger + Slowness under end-rod glitter.</li>
 * <li>Phase 2 "sugar crash" at 50% health (persisted): permanent bonus speed, a one-shot
 * Regeneration gulp and the sugar storm tempo doubles.</li>
 * </ul>
 * Unlike a vanilla phantom it does NOT burn in daylight ({@link #isAffectedByDaylight}
 * returns false) - a boss that self-immolates at dawn would be a joke fight. Drops via
 * {@code loot_table/entities/soda_seraph.json}.
 */
public class SodaSeraphEntity extends PhantomEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier CRASH_SPEED_ID = CopperInferno.id("seraph_crash_speed");
	private static final int STORM_INTERVAL_TICKS = 120;
	private static final double STORM_RANGE = 10.0;
	private static final int DEBUFF_TICKS = 100;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.soda_seraph"),
			BossBar.Color.PINK, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded crashing boss stays crashed. */
	private boolean phaseTwo;

	public SodaSeraphEntity(EntityType<? extends PhantomEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	/** The vanilla phantom sets itself on fire at dawn; the boss shrugs the sun off. */
	@Override
	protected boolean isAffectedByDaylight() {
		return false;
	}

	/** Sugar-rush payload on every successful swoop hit (vanilla swoop AI does the diving). */
	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, DEBUFF_TICKS, 1));
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, DEBUFF_TICKS, 0));
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, DEBUFF_TICKS, 0));
			world.spawnParticles(ParticleTypes.FIREWORK,
					living.getX(), living.getBodyY(0.8), living.getZ(), 15, 0.3, 0.4, 0.3, 0.08);
			world.playSound(null, living.getBlockPos(), SoundEvents.ENTITY_PHANTOM_SWOOP,
					SoundCategory.HOSTILE, 1.2f, 1.4f);
		}
		return hit;
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			// One-shot sugar-crash gulp; the speed modifier re-applies below after reloads.
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0));
			world.spawnParticles(ParticleTypes.ANGRY_VILLAGER,
					this.getX(), this.getBodyY(0.7), this.getZ(), 20, 0.7, 0.5, 0.7, 0.02);
		}
		if (this.phaseTwo) {
			applyCrashSpeed();
		}
		int stormInterval = this.phaseTwo ? STORM_INTERVAL_TICKS / 2 : STORM_INTERVAL_TICKS;
		if (this.age % stormInterval == 0) {
			sugarStorm(world);
		}
	}

	/**
	 * Hunger + Slowness to every survival/adventure player the boss has line of sight to
	 * within 10 blocks, plus end-rod glitter (ventCorrosiveCloud pattern from
	 * TheOxidizerEntity).
	 */
	private void sugarStorm(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(STORM_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, DEBUFF_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, DEBUFF_TICKS, 0));
		}
		world.spawnParticles(ParticleTypes.END_ROD,
				this.getX(), this.getBodyY(0.5), this.getZ(), 40, 2.0, 1.0, 2.0, 0.05);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE,
				SoundCategory.HOSTILE, 0.8f, 1.6f);
	}

	/**
	 * Phase-2 +0.02 flat (flying) movement speed. Temporary modifiers are not persisted, so
	 * this re-applies every tick after a reload; the {@code hasModifier} guard keeps it from
	 * stacking.
	 */
	private void applyCrashSpeed() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(CRASH_SPEED_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					CRASH_SPEED_ID, 0.02, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putBoolean(PHASE_TWO_KEY, this.phaseTwo);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.phaseTwo = view.getBoolean(PHASE_TWO_KEY, false);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
