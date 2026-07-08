package net.sonic0810.copperinferno.feature.archfiends;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Archfiend 4 "Ashen Hoglin Gorefiend": an ash-crusted hoglin (MAX_HEALTH 340,
 * SCALE 2.4), zombification-proof. Brain AI is inherited untouched; every 160 ticks
 * it knits wounds shut (heal 8) in an ash burst. Below 50% health it enters a
 * one-shot persisted phase: 2 vanilla hoglin minions (each made
 * zombification-immune) and 100 ticks of Resistance.
 * Attributes are registered in {@link ArchfiendsFeature}; drops come from
 * {@code loot_table/entities/ashen_hoglin_gorefiend.json}.
 */
public class AshenHoglinGorefiendEntity extends HoglinEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int HEAL_INTERVAL_TICKS = 160;
	private static final DustParticleEffect HEAL_BURST = new DustParticleEffect(0x9A9A94, 1.0f);

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.ashen_hoglin_gorefiend"),
			BossBar.Color.WHITE, BossBar.Style.NOTCHED_10);

	/** One-shot phase flag, persisted so a reloaded phase-2 archfiend never
	 * re-runs its phase burst (InfernoTitan pattern). */
	private boolean phaseTwo;

	public AshenHoglinGorefiendEntity(EntityType<? extends HoglinEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Public in 1.21.9 (verified via javap); an archfiend never zombifies in the Overworld.
			this.setImmuneToZombification(true);
		}
	}

	/**
	 * Blocks the inherited hoglin feeding/breeding interaction: without this,
	 * players could pacify or breed the boss mid-fight (TheOxidizer interactMob
	 * pattern; HoglinEntity.interactMob is public, verified via javap).
	 */
	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
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
			enterPhaseTwo(world);
		}
		if (this.age % HEAL_INTERVAL_TICKS == 0 && this.getHealth() < this.getMaxHealth()) {
			regenerate(world);
		}
	}

	/** Knits wounds shut in a burst of ash (same spawnParticles shape as the
	 * TheOxidizer verdigris burst). */
	private void regenerate(ServerWorld world) {
		this.heal(8.0f);
		world.spawnParticles(HEAL_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
	}

	/** Phase 2 burst: 2 vanilla hoglin minions + 100 ticks of Resistance. */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			HoglinEntity minion = EntityType.HOGLIN.create(world, SpawnReason.MOB_SUMMONED);
			if (minion == null) {
				continue;
			}
			minion.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			minion.setImmuneToZombification(true);
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
