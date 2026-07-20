package net.sonic0810.copperinferno.feature.bossdoom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.ModSounds;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Boss 2 "The Carbonated One": a pressurized soda slime god (attributes registered in
 * {@link BossDoomFeature}: MAX_HEALTH 400, ATTACK_DAMAGE 10, SCALE 3.5). Mechanics:
 * <ul>
 * <li>Fizz detonation (timed special): every 110 ticks (55 when shaken) every player within
 * 8 blocks is damaged and blasted away in a bubble/explosion knockback storm popping to the
 * existing {@code ModSounds.FIZZ_BOMB_POP}.</li>
 * <li>Carbonation shield (defensive stance): every 240 ticks it seals its surface for 80
 * ticks of Resistance II under an end-rod sparkle shell.</li>
 * <li>Phase 2 "shaken up" at 50% health (persisted): permanent bonus speed, a one-shot
 * Regeneration surge and the fizz detonation tempo doubles.</li>
 * </ul>
 * The slime size is pinned to 1 (see {@link #setSize}): the vanilla split-on-death in
 * {@code SlimeEntity.remove} only fires for size &gt; 1, so the boss never splits into
 * boss-bar-carrying children, and size 1 is the loot-dropping size - its bulk comes from
 * the SCALE attribute instead. Drops via {@code loot_table/entities/the_carbonated_one.json}.
 */
public class TheCarbonatedOneEntity extends SlimeEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier SHAKEN_SPEED_ID = CopperInferno.id("carbonated_shaken_speed");
	private static final int FIZZ_INTERVAL_TICKS = 110;
	private static final int SHIELD_INTERVAL_TICKS = 240;
	private static final int SHIELD_DURATION_TICKS = 80;
	private static final double FIZZ_RANGE = 8.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.the_carbonated_one"),
			BossBar.Color.GREEN, BossBar.Style.NOTCHED_10);

	/** One-shot phase flag, persisted so a reloaded shaken boss stays shaken. */
	private boolean phaseTwo;

	public TheCarbonatedOneEntity(EntityType<? extends SlimeEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	/**
	 * Pins the slime size to 1 and restores the boss attribute bases afterwards.
	 * {@code SlimeEntity.setSize} (called by initialize() on spawn and by readCustomData on
	 * load) force-sets MAX_HEALTH to size&sup2;, MOVEMENT_SPEED to 0.2+0.1*size and
	 * ATTACK_DAMAGE to size (verified via bytecode), which would shrink the boss to 1 HP;
	 * re-setting the bases here keeps the boss stats authoritative. Size 1 also means no
	 * split-on-death and vanilla's smallest-size-drops-loot rule applies.
	 */
	@Override
	public void setSize(int size, boolean heal) {
		super.setSize(1, heal);
		EntityAttributeInstance maxHealth = this.getAttributeInstance(EntityAttributes.MAX_HEALTH);
		if (maxHealth != null) {
			maxHealth.setBaseValue(400.0);
		}
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null) {
			speed.setBaseValue(0.4);
		}
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null) {
			damage.setBaseValue(10.0);
		}
		if (heal) {
			this.setHealth(this.getMaxHealth());
		}
	}

	/**
	 * BUG FIX: restores contact melee at the pinned size 1. Vanilla
	 * {@code SlimeEntity.canAttack()} is {@code !this.isSmall() && this.canActVoluntarily()}
	 * (bytecode-verified) and {@code isSmall()} is {@code getSize() <= 1}; it gates
	 * {@code onPlayerCollision}, {@code pushAwayFrom} and the FaceTowardTargetGoal ram
	 * flag, so with the size pinned to 1 (no-split + smallest-size loot) the boss's
	 * ATTACK_DAMAGE of 10 was dead code. Dropping the size half of the gate keeps the
	 * AI-active check while letting the pinned-size god actually bite.
	 */
	@Override
	protected boolean canAttack() {
		return this.canActVoluntarily();
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
			// One-shot "shaken up" surge; the speed modifier re-applies below after reloads.
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1));
			world.spawnParticles(ParticleTypes.ANGRY_VILLAGER,
					this.getX(), this.getBodyY(0.7), this.getZ(), 20, 0.8, 0.8, 0.8, 0.02);
		}
		if (this.phaseTwo) {
			applyShakenSpeed();
		}
		int fizzInterval = this.phaseTwo ? FIZZ_INTERVAL_TICKS / 2 : FIZZ_INTERVAL_TICKS;
		// Idle gate: no combat target means no detonation - an unprovoked boss must not
		// blast terrain-side knockback or spam ModSounds.FIZZ_BOMB_POP on a timer.
		if (this.getTarget() != null && this.age % fizzInterval == 0) {
			fizzDetonation(world);
		}
		if (this.age % SHIELD_INTERVAL_TICKS == 0) {
			carbonationShield(world);
		}
	}

	/**
	 * The knockback storm: every survival/adventure player within 8 blocks is damaged,
	 * left reeling with 3 seconds of Nausea (the carbonation goes to the head) and
	 * blasted away from the boss, under a bubble/firework/explosion spray popping to the
	 * existing {@code ModSounds.FIZZ_BOMB_POP}.
	 */
	private void fizzDetonation(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(FIZZ_RANGE),
				player -> !player.isSpectator() && !player.isCreative())) {
			player.damage(world, this.getDamageSources().mobAttack(this), 6.0f);
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 60, 0));
			player.takeKnockback(1.8,
					this.getX() - player.getX(), this.getZ() - player.getZ());
			player.addVelocity(0.0, 0.4, 0.0);
			// takeKnockback/addVelocity only mutate the server-side velocity; flag for sync.
			player.velocityModified = true;
		}
		world.spawnParticles(ParticleTypes.BUBBLE_POP,
				this.getX(), this.getBodyY(0.5), this.getZ(), 60, 1.6, 1.0, 1.6, 0.15);
		world.spawnParticles(ParticleTypes.FIREWORK,
				this.getX(), this.getBodyY(0.5), this.getZ(), 20, 1.0, 0.8, 1.0, 0.1);
		world.spawnParticles(ParticleTypes.EXPLOSION,
				this.getX(), this.getBodyY(0.5), this.getZ(), 3, 0.8, 0.5, 0.8, 0.0);
		world.playSound(null, this.getBlockPos(), ModSounds.FIZZ_BOMB_POP,
				SoundCategory.HOSTILE, 1.4f, 0.9f);
	}

	/** Defensive stance: 80 ticks of Resistance II under an end-rod sparkle shell. */
	private void carbonationShield(ServerWorld world) {
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, SHIELD_DURATION_TICKS, 1));
		world.spawnParticles(ParticleTypes.END_ROD,
				this.getX(), this.getBodyY(0.6), this.getZ(), 30, 1.2, 1.2, 1.2, 0.02);
	}

	/**
	 * Phase-2 +0.06 flat movement speed. Temporary modifiers are not persisted, so this
	 * re-applies every tick after a reload; the {@code hasModifier} guard keeps it from
	 * stacking. NOTE: setSize resets the MOVEMENT_SPEED base but never touches modifiers,
	 * so the two never fight.
	 */
	private void applyShakenSpeed() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(SHAKEN_SPEED_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					SHAKEN_SPEED_ID, 0.06, EntityAttributeModifier.Operation.ADD_VALUE));
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
