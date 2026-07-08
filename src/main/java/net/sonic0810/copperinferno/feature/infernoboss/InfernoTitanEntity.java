package net.sonic0810.copperinferno.feature.infernoboss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;
import net.sonic0810.copperinferno.feature.infernomobs.EmberWraithEntity;
import net.sonic0810.copperinferno.feature.infernomobs.InfernoMobsFeature;

/**
 * Boss 2 "Inferno Titan": a colossal blaze (attributes registered in
 * {@link InfernoBossFeature}: MAX_HEALTH 260, SCALE 2.5). Summoned by a
 * {@link TitanSigilItem}, only inside the Inferno dimension. Phase 1 is pure vanilla blaze
 * AI (ranged fireballs); once below 50% health it enters phase 2 exactly once (flag
 * persisted via write/readCustomData): 2 Ember Wraith minions, 100 ticks of Resistance,
 * a melee charge goal and +4 attack damage. Drops Titan Embers + the Inferno Crown
 * ({@code loot_table/entities/inferno_titan.json}).
 */
public class InfernoTitanEntity extends BlazeEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier MELEE_BOOST_MODIFIER_ID = CopperInferno.id("titan_phase_two_melee");

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.inferno_titan"),
			BossBar.Color.RED, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 titan never re-spawns minions. */
	private boolean phaseTwo;
	/** Goals are not persisted; tracked separately so a reloaded titan re-adds the charge. */
	private boolean meleeGoalAdded;

	public InfernoTitanEntity(EntityType<? extends BlazeEntity> type, World world) {
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
		if (this.phaseTwo && !this.meleeGoalAdded) {
			this.meleeGoalAdded = true;
			this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, true));
			applyMeleeBoost();
		}
	}

	/**
	 * One-shot +4 flat attack damage for the phase-2 melee charge (enrage() pattern from
	 * {@link TheOxidizerEntity}). Temporary modifiers are not persisted, so after a reload
	 * this re-applies alongside the melee goal; the {@code hasModifier} guard keeps it from
	 * stacking.
	 */
	private void applyMeleeBoost() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(MELEE_BOOST_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					MELEE_BOOST_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE));
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

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
