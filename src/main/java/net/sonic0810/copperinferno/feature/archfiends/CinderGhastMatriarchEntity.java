package net.sonic0810.copperinferno.feature.archfiends;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Archfiend 2 "Cinder Ghast Matriarch": a burning ghast (MAX_HEALTH 300, SCALE 1.8).
 * Keeps the inherited vanilla ghast fireball AI; every 140 ticks her cinder rain
 * ignites survival players she can see within 8 blocks (60 fire ticks). Below 50%
 * health she enters a one-shot persisted phase: 2 vanilla blaze minions
 * (EntityType.BLAZE.create + MOB_SUMMONED, verified) and 100 ticks of Resistance.
 * Attributes are registered in {@link ArchfiendsFeature}; drops come from
 * {@code loot_table/entities/cinder_ghast_matriarch.json}.
 */
public class CinderGhastMatriarchEntity extends GhastEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int ABILITY_INTERVAL_TICKS = 140;
	private static final double ABILITY_RANGE = 8.0;
	private static final int ABILITY_FIRE_TICKS = 60;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.cinder_ghast_matriarch"),
			BossBar.Color.RED, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 archfiend never
	 * re-runs its phase burst (InfernoTitan pattern). */
	private boolean phaseTwo;

	public CinderGhastMatriarchEntity(EntityType<? extends GhastEntity> type, World world) {
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
			player.setOnFireForTicks(ABILITY_FIRE_TICKS);
		}
		world.spawnParticles(ParticleTypes.FLAME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
	}

	/** Phase 2 burst: 2 vanilla blaze minions + 100 ticks of Resistance. */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			BlazeEntity minion = EntityType.BLAZE.create(world, SpawnReason.MOB_SUMMONED);
			if (minion == null) {
				continue;
			}
			minion.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(minion);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
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
