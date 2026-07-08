package net.sonic0810.copperinferno.feature.infernoboss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;
import net.sonic0810.copperinferno.feature.infernomobs.EmberWraithEntity;
import net.sonic0810.copperinferno.feature.infernomobs.InfernoMobsFeature;

/**
 * Boss 2 "Inferno Titan": a colossal blaze (attributes registered in
 * {@link InfernoBossFeature}: MAX_HEALTH 260, SCALE 2.5). Summoned by a
 * {@link TitanSigilItem}, only inside the Inferno dimension. Phase 1 is pure vanilla blaze
 * AI (ranged fireballs); once below 50% health it enters phase 2 exactly once (flag
 * persisted via write/readCustomData): 2 Ember Wraith minions, 100 ticks of Resistance,
 * and a melee charge goal. Drops Titan Embers + the Inferno Crown
 * ({@code loot_table/entities/inferno_titan.json}).
 */
public class InfernoTitanEntity extends BlazeEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.inferno_titan"),
			BossBar.Color.RED, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 titan never re-spawns minions. */
	private boolean phaseTwo;
	/** Goals are not persisted; tracked separately so a reloaded titan re-adds the charge. */
	private boolean meleeGoalAdded;

	public InfernoTitanEntity(EntityType<? extends BlazeEntity> type, World world) {
		super(type, world);
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
		if (this.phaseTwo && !this.meleeGoalAdded) {
			this.meleeGoalAdded = true;
			this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, true));
		}
	}

	/** Phase 2 burst: 2 Ember Wraith minions + 100 ticks of Resistance (melee goal added in mobTick). */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			EmberWraithEntity minion = new EmberWraithEntity(InfernoMobsFeature.EMBER_WRAITH, world);
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
}
