package net.sonic0810.copperinferno.feature.bosspantheon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CreeperEntity;
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
 * Pantheon boss 4 "Thunder Conductor": a storm-wired blaze lord (attributes in
 * {@link BossPantheonFeature}: MAX_HEALTH 280, SCALE 2.4, FOLLOW_RANGE 64). Vanilla blaze
 * AI (small fireballs) plus three mechanics: a timed lightning-rod strike (a REAL lightning
 * bolt dropped on its target, electric-spark burst at the conductor), a charged-creeper
 * aura (every 100 ticks each creeper within 8 blocks is struck by a cosmetic bolt and
 * super-charged - vanilla CreeperEntity.onStruckByLightning - while visible survival
 * players within 4 blocks take a 3-point static shock), and a phase 2 below 50% health
 * (persisted flag: strike cadence halves from 160 to 80 ticks, entry grants 100 ticks of
 * Resistance plus a one-shot speed surge). Drops Storm Rods, Charged Filaments and the
 * Thunder Scepter ({@code loot_table/entities/thunder_conductor.json}).
 */
public class ThunderConductorEntity extends BlazeEntity {
	private static final Identifier PHASE_TWO_SPEED_MODIFIER_ID = CopperInferno.id("conductor_phase_two_speed");
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int STRIKE_INTERVAL_TICKS = 160;
	private static final int STRIKE_INTERVAL_PHASE_TWO_TICKS = 80;
	private static final int AURA_INTERVAL_TICKS = 100;
	private static final double AURA_CREEPER_RANGE = 8.0;
	private static final double AURA_SHOCK_RANGE = 4.0;
	private static final float AURA_SHOCK_DAMAGE = 3.0f;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.thunder_conductor"),
			BossBar.Color.YELLOW, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 conductor keeps its faster strikes. */
	private boolean phaseTwo;

	public ThunderConductorEntity(EntityType<? extends BlazeEntity> type, World world) {
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
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
			world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER,
					SoundCategory.HOSTILE, 2.0f, 1.4f);
		}
		applyPhaseTwoSpeed();
		LivingEntity target = this.getTarget();
		int interval = this.phaseTwo ? STRIKE_INTERVAL_PHASE_TWO_TICKS : STRIKE_INTERVAL_TICKS;
		if (target != null && this.canSee(target) && this.age % interval == 0) {
			callLightningOnTarget(world, target);
		}
		// Idle gate: no combat target means no aura - an unprovoked boss must not shock
		// bystanders or drop (even cosmetic) lightning bolts on creepers on a timer.
		if (target != null && this.age % AURA_INTERVAL_TICKS == 0) {
			pulseChargedAura(world);
		}
	}

	/**
	 * Drops a REAL lightning bolt on the target's position (vanilla fire/damage rules
	 * apply). EntityType.LIGHTNING_BOLT.create(World, SpawnReason) and
	 * refreshPositionAfterTeleport(Vec3d) verified via javap.
	 */
	private void callLightningOnTarget(ServerWorld world, LivingEntity target) {
		LightningEntity bolt = EntityType.LIGHTNING_BOLT.create(world, SpawnReason.TRIGGERED);
		if (bolt == null) {
			return;
		}
		bolt.refreshPositionAfterTeleport(target.getEntityPos());
		world.spawnEntity(bolt);
		world.spawnParticles(ParticleTypes.ELECTRIC_SPARK,
				this.getX(), this.getBodyY(0.5), this.getZ(), 24, 0.6, 0.8, 0.6, 0.15);
	}

	/**
	 * Charged-creeper aura: each creeper within 8 blocks is hit by a COSMETIC bolt (visual
	 * only, no block fire) and then explicitly super-charged through the vanilla
	 * CreeperEntity.onStruckByLightning hook; visible survival players within 4 blocks take
	 * a static shock (lightningBolt damage source) with an electric-spark crackle.
	 */
	private void pulseChargedAura(ServerWorld world) {
		for (CreeperEntity creeper : world.getEntitiesByClass(CreeperEntity.class,
				this.getBoundingBox().expand(AURA_CREEPER_RANGE),
				creeper -> creeper.isAlive() && !creeper.isCharged())) {
			LightningEntity bolt = EntityType.LIGHTNING_BOLT.create(world, SpawnReason.TRIGGERED);
			if (bolt == null) {
				continue;
			}
			bolt.refreshPositionAfterTeleport(creeper.getEntityPos());
			bolt.setCosmetic(true);
			world.spawnEntity(bolt);
			creeper.onStruckByLightning(world, bolt);
		}
		boolean shocked = false;
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_SHOCK_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.damage(world, world.getDamageSources().lightningBolt(), AURA_SHOCK_DAMAGE);
			world.spawnParticles(ParticleTypes.ELECTRIC_SPARK,
					player.getX(), player.getBodyY(0.5), player.getZ(), 12, 0.3, 0.5, 0.3, 0.1);
			shocked = true;
		}
		if (shocked) {
			world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
					SoundCategory.HOSTILE, 1.0f, 1.6f);
		}
	}

	/**
	 * One-shot +0.05 flat movement speed once phase 2 is reached. Temporary modifiers are
	 * not persisted, so after a reload this re-applies on the next tick; the
	 * {@code hasModifier} guard keeps it from stacking (enrage() pattern from the
	 * infernoboss Oxidizer).
	 */
	private void applyPhaseTwoSpeed() {
		if (!this.phaseTwo) {
			return;
		}
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(PHASE_TWO_SPEED_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					PHASE_TWO_SPEED_MODIFIER_ID, 0.05, EntityAttributeModifier.Operation.ADD_VALUE));
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
