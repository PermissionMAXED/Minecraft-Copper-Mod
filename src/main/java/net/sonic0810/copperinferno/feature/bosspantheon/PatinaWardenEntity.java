package net.sonic0810.copperinferno.feature.bosspantheon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Pantheon boss 2 "Patina Warden": a colossal oxidized-copper golem (attributes in
 * {@link BossPantheonFeature}: MAX_HEALTH 450, ATTACK_DAMAGE 17, SCALE 2.2). Hunts players
 * on sight and carries three mechanics: a timed "patina shield" stance (100 ticks every
 * 300 ticks, persisted; while up, incoming damage is reduced to 25% and half the original
 * amount is reflected back at the attacker as thorns, with copper-clank + wax-on feedback),
 * a phase 2 below 50% health (persisted; shield cadence tightens to every 200 ticks) and a
 * one-shot enrage (movement + attack-damage boost) in phase 2. The inherited iron-ingot
 * right-click repair is blocked so the boss cannot be healed mid-fight. Drops Patina
 * Plates, Warden Rivets and the Patina Aegis
 * ({@code loot_table/entities/patina_warden.json}).
 */
public class PatinaWardenEntity extends IronGolemEntity {
	private static final Identifier ENRAGE_SPEED_MODIFIER_ID = CopperInferno.id("warden_enrage_speed");
	private static final Identifier ENRAGE_DAMAGE_MODIFIER_ID = CopperInferno.id("warden_enrage_damage");
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final String SHIELD_TICKS_KEY = "PatinaShieldTicks";
	private static final int SHIELD_INTERVAL_TICKS = 300;
	private static final int SHIELD_INTERVAL_PHASE_TWO_TICKS = 200;
	private static final int SHIELD_DURATION_TICKS = 100;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.patina_warden"),
			BossBar.Color.BLUE, BossBar.Style.NOTCHED_6);

	/** Remaining patina-shield ticks; persisted so a reloaded warden keeps its stance. */
	private int shieldTicks;
	/** One-shot phase flag, persisted so a reloaded phase-2 warden keeps the tighter cadence. */
	private boolean phaseTwo;

	public PatinaWardenEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// Unlike the vanilla golem (only angered on attack), the boss hunts players on sight.
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	/**
	 * Blocks the inherited iron-golem right-click repair: without this, players could heal
	 * the boss mid-fight with iron ingots (same guard as the infernoboss Oxidizer).
	 */
	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		return ActionResult.PASS;
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
			world.spawnParticles(ParticleTypes.ANGRY_VILLAGER,
					this.getX(), this.getBodyY(0.75), this.getZ(), 20, 0.6, 0.6, 0.6, 0.0);
			world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_ATTACK,
					SoundCategory.HOSTILE, 1.5f, 0.6f);
		}
		if (this.phaseTwo) {
			enrage();
		}
		int interval = this.phaseTwo ? SHIELD_INTERVAL_PHASE_TWO_TICKS : SHIELD_INTERVAL_TICKS;
		// Idle gate: no combat target means no stance - an unprovoked boss must not clang
		// the golem-repair peal on a timer (the reflection only matters mid-fight anyway).
		if (this.getTarget() != null && this.shieldTicks <= 0 && this.age % interval == 0) {
			raiseShield(world);
		}
		if (this.shieldTicks > 0) {
			this.shieldTicks--;
			if (this.age % 10 == 0) {
				// Steady wax-on shimmer telegraphs that the shield (and reflection) is up.
				world.spawnParticles(ParticleTypes.WAX_ON,
						this.getX(), this.getBodyY(0.5), this.getZ(), 8, 0.7, 0.9, 0.7, 0.0);
			}
		}
	}

	private void raiseShield(ServerWorld world) {
		this.shieldTicks = SHIELD_DURATION_TICKS;
		world.spawnParticles(ParticleTypes.WAX_ON,
				this.getX(), this.getBodyY(0.5), this.getZ(), 40, 0.8, 1.0, 0.8, 0.0);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_REPAIR,
				SoundCategory.HOSTILE, 1.5f, 0.7f);
	}

	/**
	 * Patina shield: while up, incoming damage is cut to 25% and half the ORIGINAL amount
	 * is reflected at the attacker as thorns damage. Thorns hits are never reflected again
	 * (no mirror-vs-mirror loops), and the copper-clank feedback plays on every absorbed
	 * blow. LivingEntity.damage(ServerWorld, DamageSource, float) verified via javap.
	 */
	@Override
	public boolean damage(ServerWorld world, DamageSource source, float amount) {
		if (this.shieldTicks > 0 && !source.isOf(DamageTypes.THORNS)) {
			if (source.getAttacker() instanceof LivingEntity attacker && attacker != this) {
				attacker.damage(world, world.getDamageSources().thorns(this), amount * 0.5f);
			}
			world.spawnParticles(ParticleTypes.CRIT,
					this.getX(), this.getBodyY(0.5), this.getZ(), 12, 0.5, 0.7, 0.5, 0.1);
			world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_COPPER_HIT,
					SoundCategory.HOSTILE, 1.2f, 0.8f);
			return super.damage(world, source, amount * 0.25f);
		}
		return super.damage(world, source, amount);
	}

	/**
	 * One-shot +0.04 movement speed and +4 attack damage below 50% health. Temporary
	 * modifiers are not persisted, so after a reload this re-applies on the next tick; the
	 * {@code hasModifier} guards keep it from stacking (enrage() pattern from the
	 * infernoboss Oxidizer).
	 */
	private void enrage() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_SPEED_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_SPEED_MODIFIER_ID, 0.04, EntityAttributeModifier.Operation.ADD_VALUE));
		}
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(ENRAGE_DAMAGE_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_DAMAGE_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putBoolean(PHASE_TWO_KEY, this.phaseTwo);
		view.putInt(SHIELD_TICKS_KEY, this.shieldTicks);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.phaseTwo = view.getBoolean(PHASE_TWO_KEY, false);
		this.shieldTicks = view.getInt(SHIELD_TICKS_KEY, 0);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
