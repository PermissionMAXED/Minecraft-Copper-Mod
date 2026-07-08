package net.sonic0810.copperinferno.feature.bossdoom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.RavagerEntity;
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
import net.sonic0810.copperinferno.core.ModSounds;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Boss 1 "Dr. Doompepper": a colossal soda-corrupted ravager (attributes registered in
 * {@link BossDoomFeature}: MAX_HEALTH 500, ATTACK_DAMAGE 16, SCALE 2.2). Mechanics:
 * <ul>
 * <li>DOOM kick shockwave (timed special): every 140 ticks (70 enraged) every player within
 * 7 blocks is damaged and hurled away to the existing {@code ModSounds.DOOM_KICK} beat with
 * a gust/explosion ring.</li>
 * <li>Soda geyser (timed special): every 110 ticks a carbonated geyser erupts under its
 * current target, knocking it skyward with splash/bubble/firework spray.</li>
 * <li>Phase 2 at 50% health (persisted): permanently enrages - bonus attack damage and
 * speed, a one-shot Resistance burst, and the DOOM kick beat doubles in tempo.</li>
 * </ul>
 * Vanilla ravager AI (melee charge, roar, stun) is inherited; players are hunted on sight
 * by the vanilla ravager target selector. Drops via
 * {@code loot_table/entities/dr_doompepper.json}.
 */
public class DrDoompepperEntity extends RavagerEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier ENRAGE_DAMAGE_ID = CopperInferno.id("doompepper_enrage_damage");
	private static final Identifier ENRAGE_SPEED_ID = CopperInferno.id("doompepper_enrage_speed");
	private static final int KICK_INTERVAL_TICKS = 140;
	private static final int GEYSER_INTERVAL_TICKS = 110;
	private static final double KICK_RANGE = 7.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.dr_doompepper"),
			BossBar.Color.RED, BossBar.Style.NOTCHED_6);

	/** One-shot phase flag, persisted so a reloaded enraged boss stays enraged. */
	private boolean phaseTwo;

	public DrDoompepperEntity(EntityType<? extends RavagerEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			// One-shot phase burst; the attribute modifiers re-apply below after reloads.
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
			world.spawnParticles(ParticleTypes.ANGRY_VILLAGER,
					this.getX(), this.getBodyY(0.7), this.getZ(), 20, 0.6, 0.6, 0.6, 0.02);
		}
		if (this.phaseTwo) {
			applyEnrageModifiers();
		}
		int kickInterval = this.phaseTwo ? KICK_INTERVAL_TICKS / 2 : KICK_INTERVAL_TICKS;
		// Idle gate: no combat target means no kick - an unprovoked boss must not blast
		// the streamed ModSounds.DOOM_KICK beat across the landscape every 7 seconds.
		if (this.getTarget() != null && this.age % kickInterval == 0) {
			doomKickShockwave(world);
		}
		if (this.getTarget() != null && this.age % GEYSER_INTERVAL_TICKS == 0) {
			sodaGeyser(world);
		}
	}

	/**
	 * The signature DOOM kick: every survival/adventure player within 7 blocks is damaged
	 * and hurled away from the boss on the {@code ModSounds.DOOM_KICK} beat, with a
	 * gust + explosion ring (spawnParticles shape as in TheOxidizerEntity).
	 */
	private void doomKickShockwave(ServerWorld world) {
		boolean hitAnyone = false;
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(KICK_RANGE),
				player -> !player.isSpectator() && !player.isCreative())) {
			player.damage(world, this.getDamageSources().mobAttack(this), 8.0f);
			player.takeKnockback(1.5,
					this.getX() - player.getX(), this.getZ() - player.getZ());
			// takeKnockback only mutates the server-side velocity; flag it for sync.
			player.velocityModified = true;
			hitAnyone = true;
		}
		world.spawnParticles(ParticleTypes.GUST,
				this.getX(), this.getBodyY(0.2), this.getZ(), 6, 1.6, 0.2, 1.6, 0.0);
		if (hitAnyone) {
			world.spawnParticles(ParticleTypes.EXPLOSION,
					this.getX(), this.getBodyY(0.5), this.getZ(), 4, 1.2, 0.4, 1.2, 0.0);
		}
		world.playSound(null, this.getBlockPos(), ModSounds.DOOM_KICK,
				SoundCategory.HOSTILE, 1.2f, 1.0f);
	}

	/** Carbonated geyser under the current target: minor damage plus a violent knock-up. */
	private void sodaGeyser(ServerWorld world) {
		LivingEntity target = this.getTarget();
		if (target == null || this.squaredDistanceTo(target) > 16.0 * 16.0) {
			return;
		}
		target.damage(world, this.getDamageSources().mobAttack(this), 4.0f);
		target.addVelocity(0.0, 1.0, 0.0);
		target.velocityModified = true;
		world.spawnParticles(ParticleTypes.SPLASH,
				target.getX(), target.getY(), target.getZ(), 40, 0.4, 0.8, 0.4, 0.1);
		world.spawnParticles(ParticleTypes.BUBBLE_POP,
				target.getX(), target.getY() + 0.5, target.getZ(), 25, 0.3, 0.7, 0.3, 0.05);
		world.spawnParticles(ParticleTypes.FIREWORK,
				target.getX(), target.getY(), target.getZ(), 12, 0.2, 0.5, 0.2, 0.08);
		world.playSound(null, target.getBlockPos(), SoundEvents.ENTITY_GENERIC_SPLASH,
				SoundCategory.HOSTILE, 1.0f, 0.8f);
	}

	/**
	 * Phase-2 enrage: +6 flat attack damage and +0.05 flat movement speed. Temporary
	 * modifiers are not persisted, so this re-applies every tick after a reload; the
	 * {@code hasModifier} guards keep it from stacking (enrage() pattern from
	 * TheOxidizerEntity).
	 */
	private void applyEnrageModifiers() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(ENRAGE_DAMAGE_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_DAMAGE_ID, 6.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_SPEED_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_SPEED_ID, 0.05, EntityAttributeModifier.Operation.ADD_VALUE));
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
