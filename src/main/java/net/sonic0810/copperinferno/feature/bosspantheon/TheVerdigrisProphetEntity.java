package net.sonic0810.copperinferno.feature.bosspantheon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Pantheon boss 1 "The Verdigris Prophet": a corrosion-preaching evoker (attributes in
 * {@link BossPantheonFeature}: MAX_HEALTH 300, SCALE 1.8, FOLLOW_RANGE 48). Vanilla evoker
 * AI (fangs + vex summons) plus three mechanics: a timed oxidation curse (Slowness +
 * Weakness + Mining Fatigue to every visible survival player within 8 blocks, verdigris
 * dust burst + prepare-summon shriek), a timed double fang ring conjured around its target
 * (prepare-attack sound), and a phase 2 below 50% health (persisted flag): one immediate
 * retaliatory fang ring, 100 ticks of Resistance, a one-shot speed boost and both ability
 * timers running twice as fast. Drops Verdigris Tomes, Corroded Fangs and the Prophet's
 * Circlet ({@code loot_table/entities/the_verdigris_prophet.json}).
 */
public class TheVerdigrisProphetEntity extends EvokerEntity {
	/** Verdigris green, matching the oxidized copper palette. */
	private static final DustParticleEffect VERDIGRIS_BURST = new DustParticleEffect(0x57A07B, 1.0f);
	private static final Identifier PHASE_TWO_SPEED_MODIFIER_ID = CopperInferno.id("prophet_phase_two_speed");
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int CURSE_INTERVAL_TICKS = 160;
	private static final int FANG_RING_INTERVAL_TICKS = 240;
	private static final double CURSE_RANGE = 8.0;
	private static final int CURSE_EFFECT_TICKS = 80;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.the_verdigris_prophet"),
			BossBar.Color.GREEN, BossBar.Style.NOTCHED_10);

	/** One-shot phase flag, persisted so a reloaded phase-2 prophet keeps its faster cadence. */
	private boolean phaseTwo;

	public TheVerdigrisProphetEntity(EntityType<? extends EvokerEntity> type, World world) {
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
			enterPhaseTwo(world);
		}
		// Phase 2 halves both ability intervals ("the sermon quickens").
		int cadence = this.phaseTwo ? 2 : 1;
		if (this.age % (CURSE_INTERVAL_TICKS / cadence) == 0) {
			castOxidationCurse(world);
		}
		LivingEntity target = this.getTarget();
		if (target != null && this.age % (FANG_RING_INTERVAL_TICKS / cadence) == 0) {
			conjureFangRing(world, target);
		}
		applyPhaseTwoSpeed();
	}

	/**
	 * Oxidation curse: Slowness + Weakness + Mining Fatigue to every survival/adventure
	 * player the prophet can see within 8 blocks (creative/spectator and players behind
	 * walls are unaffected), plus a verdigris dust burst and the prepare-summon shriek
	 * (ventCorrosiveCloud pattern from the infernoboss Oxidizer).
	 */
	private void castOxidationCurse(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(CURSE_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, CURSE_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, CURSE_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, CURSE_EFFECT_TICKS, 0));
		}
		world.spawnParticles(VERDIGRIS_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.4, 0.5, 0.4, 0.05);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON,
				SoundCategory.HOSTILE, 1.0f, 0.8f);
	}

	/**
	 * Double ring of evoker fangs around the target: 5 fangs at radius 1.5 (no warmup) and
	 * 8 at radius 2.5 (staggered warmup), each snapped onto the ground near the target's
	 * feet (simplified version of the vanilla evoker's defensive fang circle).
	 */
	private void conjureFangRing(ServerWorld world, LivingEntity target) {
		for (int i = 0; i < 5; i++) {
			float angle = (float) i / 5.0f * MathHelper.TAU;
			spawnFang(world, target, angle, 1.5, 0);
		}
		for (int i = 0; i < 8; i++) {
			float angle = (float) i / 8.0f * MathHelper.TAU;
			spawnFang(world, target, angle, 2.5, 3);
		}
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_EVOKER_PREPARE_ATTACK,
				SoundCategory.HOSTILE, 1.0f, 1.0f);
	}

	/**
	 * One fang at the given polar offset from the target, scanning down from one block
	 * above the target's feet for the first air-above-ground spot (fangs floating in the
	 * air never bite). Off-vertical terrain simply skips the fang.
	 */
	private void spawnFang(ServerWorld world, LivingEntity target, float angle, double radius, int warmup) {
		double x = target.getX() + MathHelper.cos(angle) * radius;
		double z = target.getZ() + MathHelper.sin(angle) * radius;
		for (int dy = 1; dy >= -3; dy--) {
			BlockPos pos = BlockPos.ofFloored(x, target.getY() + dy, z);
			if (world.getBlockState(pos).isAir() && !world.getBlockState(pos.down()).isAir()) {
				world.spawnEntity(new EvokerFangsEntity(world, x, pos.getY(), z,
						angle + MathHelper.HALF_PI, warmup, this));
				return;
			}
		}
	}

	/** Phase 2 burst: one immediate retaliatory fang ring on its target + 100 ticks of Resistance. */
	private void enterPhaseTwo(ServerWorld world) {
		LivingEntity target = this.getTarget();
		if (target != null) {
			conjureFangRing(world, target);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
		world.spawnParticles(VERDIGRIS_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 50, 0.6, 0.8, 0.6, 0.1);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_EVOKER_CAST_SPELL,
				SoundCategory.HOSTILE, 1.5f, 0.6f);
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
