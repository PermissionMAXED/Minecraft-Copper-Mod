package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VindicatorEntity;
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
 * Calamity 10 "Calamity Herald": the doom-crier of the calamities (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 380, ATTACK_DAMAGE 14, SCALE 2.0). Summoned with a
 * Herald Omen Sigil. Every 120 ticks it marks every survival player it can see within 8
 * blocks with Glowing + Slowness; below 50% health it enters phase two exactly once
 * (persisted flag, InfernoTitanEntity pattern), calling 2 vanilla vindicator adds. Drops
 * per {@code loot_table/entities/calamity_herald.json}.
 */
public class CalamityHeraldEntity extends VindicatorEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int AURA_INTERVAL_TICKS = 120;
	private static final double AURA_RANGE = 8.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.calamity_herald"),
			BossBar.Color.PURPLE, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 herald never re-spawns adds. */
	private boolean phaseTwo;

	public CalamityHeraldEntity(EntityType<? extends VindicatorEntity> type, World world) {
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
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			ventOmenAura(world);
		}
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			enterPhaseTwo(world);
		}
	}

	/**
	 * Glowing + Slowness to every survival/adventure player the boss has line of sight to
	 * within 8 blocks, plus an omen burst; creative and spectator players and players
	 * behind walls are unaffected.
	 */
	private void ventOmenAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0));
		}
		world.spawnParticles(ParticleTypes.WITCH,
				this.getX(), this.getBodyY(0.5), this.getZ(), 24, 0.5, 0.6, 0.5, 0.0);
	}

	/** Phase 2 burst (InfernoTitanEntity pattern): 2 vanilla vindicator adds. */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			VindicatorEntity add = new VindicatorEntity(EntityType.VINDICATOR, world);
			add.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(add);
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
