package net.sonic0810.copperinferno.feature.archfiends;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Archfiend 6 "Soot Evoker Highlord": a soot-wreathed illager (MAX_HEALTH 260,
 * SCALE 2.0) keeping the full inherited evoker spell AI. Every 160 ticks its soot
 * veil blinds + slows survival players it can see within 8 blocks. Below 50% health
 * it enters a one-shot persisted phase: 200 ticks of Resistance plus a permanent
 * speed modifier (re-applied after reload with the hasModifier guard).
 * Attributes are registered in {@link ArchfiendsFeature}; drops come from
 * {@code loot_table/entities/soot_evoker_highlord.json}.
 */
public class SootEvokerHighlordEntity extends EvokerEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier PHASE_BOOST_MODIFIER_ID = CopperInferno.id("soot_evoker_highlord_phase_two");
	private static final int ABILITY_INTERVAL_TICKS = 160;
	private static final double ABILITY_RANGE = 8.0;
	private static final int ABILITY_EFFECT_TICKS = 60;
	private static final DustParticleEffect AURA_BURST = new DustParticleEffect(0x2E2431, 1.0f);

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.soot_evoker_highlord"),
			BossBar.Color.PURPLE, BossBar.Style.NOTCHED_12);

	/** One-shot phase flag, persisted so a reloaded phase-2 archfiend never
	 * re-runs its phase burst (InfernoTitan pattern). */
	private boolean phaseTwo;

	public SootEvokerHighlordEntity(EntityType<? extends EvokerEntity> type, World world) {
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
		// Temporary modifiers are not persisted; re-apply after reload
		// (hasModifier guard keeps it from stacking).
		if (this.phaseTwo) {
			applyPhaseBoost();
		}
		if (this.age % ABILITY_INTERVAL_TICKS == 0) {
			pulseAura(world);
		}
	}

	/**
	 * Effects hit every survival/adventure player the boss has line of sight to
	 * within 8 blocks (TheOxidizer ventCorrosiveCloud pattern); creative and
	 * spectator players and players behind walls are unaffected.
	 */
	private void pulseAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(ABILITY_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ABILITY_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ABILITY_EFFECT_TICKS, 0));
		}
		world.spawnParticles(AURA_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
	}

	/** Phase 2 burst: 200 ticks of Resistance. */
	private void enterPhaseTwo(ServerWorld world) {
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0));
	}

	/** One-shot +0.04 flat movement speed for phase 2
	 * (InfernoTitan applyMeleeBoost pattern; hasModifier guard). */
	private void applyPhaseBoost() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(PHASE_BOOST_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					PHASE_BOOST_MODIFIER_ID, 0.04, EntityAttributeModifier.Operation.ADD_VALUE));
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
