package net.sonic0810.copperinferno.feature.archfiends;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Archfiend 1 "Dread Ghast Sovereign": a colossal spectral ghast (MAX_HEALTH 320,
 * SCALE 1.6). Keeps the inherited vanilla ghast fireball AI; every 120 ticks its dread
 * veil applies Darkness + Slowness to survival players it can see within 10 blocks.
 * Below 50% health it enters a one-shot persisted frenzy phase: 200 ticks of
 * Resistance and the veil pulses twice as often.
 * Attributes are registered in {@link ArchfiendsFeature}; drops come from
 * {@code loot_table/entities/dread_ghast_sovereign.json}.
 */
public class DreadGhastSovereignEntity extends GhastEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int ABILITY_INTERVAL_TICKS = 120;
	private static final double ABILITY_RANGE = 10.0;
	private static final int ABILITY_EFFECT_TICKS = 100;
	private static final DustParticleEffect AURA_BURST = new DustParticleEffect(0x7B5CC7, 1.0f);

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.dread_ghast_sovereign"),
			BossBar.Color.PURPLE, BossBar.Style.NOTCHED_10);

	/** One-shot phase flag, persisted so a reloaded phase-2 archfiend never
	 * re-runs its phase burst (InfernoTitan pattern). */
	private boolean phaseTwo;

	public DreadGhastSovereignEntity(EntityType<? extends GhastEntity> type, World world) {
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
		// The frenzy phase vents the veil twice as often.
		int interval = this.phaseTwo ? ABILITY_INTERVAL_TICKS / 2 : ABILITY_INTERVAL_TICKS;
		if (this.age % interval == 0) {
			pulseAura(world);
		}
	}

	/**
	 * Effects hit every survival/adventure player the boss has line of sight to
	 * within 10 blocks (TheOxidizer ventCorrosiveCloud pattern); creative and
	 * spectator players and players behind walls are unaffected.
	 */
	private void pulseAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(ABILITY_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, ABILITY_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ABILITY_EFFECT_TICKS, 0));
		}
		world.spawnParticles(AURA_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
	}

	/** Phase 2 burst: 200 ticks of Resistance + a doubled aura rate (see mobTick). */
	private void enterPhaseTwo(ServerWorld world) {
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0));
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
