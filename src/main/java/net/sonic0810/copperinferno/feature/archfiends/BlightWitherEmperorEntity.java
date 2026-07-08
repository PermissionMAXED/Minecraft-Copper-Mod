package net.sonic0810.copperinferno.feature.archfiends;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
 * Archfiend 10 "Blight Wither Emperor": an iron-sworded wither skeleton
 * (MAX_HEALTH 300, SCALE 2.4). Below 50% health it enters a one-shot persisted
 * phase (InfernoTitan pattern): 2 vanilla wither skeleton minions (each armed with
 * a stone sword), 100 ticks of Resistance and a permanent +4 attack-damage modifier
 * (re-applied after reload with the hasModifier guard).
 * Attributes are registered in {@link ArchfiendsFeature}; drops come from
 * {@code loot_table/entities/blight_wither_emperor.json}.
 */
public class BlightWitherEmperorEntity extends WitherSkeletonEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier PHASE_BOOST_MODIFIER_ID = CopperInferno.id("blight_wither_emperor_phase_two");

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.blight_wither_emperor"),
			BossBar.Color.PURPLE, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 archfiend never
	 * re-runs its phase burst (InfernoTitan pattern). */
	private boolean phaseTwo;

	public BlightWitherEmperorEntity(EntityType<? extends WitherSkeletonEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Summon items / EntityType.create skip initialize(), so the boss arms itself here;
			// saved equipment simply re-applies over this on load. Never dropped on death.
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.0f);
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
	}

	/** Phase 2 burst: 2 vanilla wither_skeleton minions + 100 ticks of Resistance. */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			WitherSkeletonEntity minion = EntityType.WITHER_SKELETON.create(world, SpawnReason.MOB_SUMMONED);
			if (minion == null) {
				continue;
			}
			minion.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			minion.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			world.spawnEntity(minion);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
	}

	/** One-shot +4.0 flat attack damage for phase 2
	 * (InfernoTitan applyMeleeBoost pattern; hasModifier guard). */
	private void applyPhaseBoost() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(PHASE_BOOST_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					PHASE_BOOST_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE));
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
