package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
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
 * Calamity 2 "Slag Warlord": a slag-armored ravager warchief (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 500, ATTACK_DAMAGE 18, SCALE 2.2). Summoned with a
 * Slag War Banner. Below 50% health it enters phase two exactly once (flag persisted via
 * write/readCustomData, InfernoTitanEntity pattern): 2 vanilla vindicator lieutenants,
 * 100 ticks of Resistance and +6 attack damage. Drops per
 * {@code loot_table/entities/slag_warlord.json}.
 */
public class SlagWarlordEntity extends RavagerEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier WAR_FURY_MODIFIER_ID = CopperInferno.id("warlord_phase_two_fury");

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.slag_warlord"),
			BossBar.Color.YELLOW, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 warlord never re-spawns adds. */
	private boolean phaseTwo;

	public SlagWarlordEntity(EntityType<? extends RavagerEntity> type, World world) {
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
		if (this.phaseTwo) {
			// Temporary modifiers are not persisted; re-applies after a reload (guarded).
			applyWarFury();
		}
	}

	/** Phase 2 burst (InfernoTitanEntity pattern): 2 vindicator lieutenants + Resistance. */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			VindicatorEntity lieutenant = new VindicatorEntity(EntityType.VINDICATOR, world);
			lieutenant.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(lieutenant);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
	}

	/** One-shot +6 flat attack damage in phase two; the hasModifier guard prevents stacking. */
	private void applyWarFury() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(WAR_FURY_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					WAR_FURY_MODIFIER_ID, 6.0, EntityAttributeModifier.Operation.ADD_VALUE));
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
