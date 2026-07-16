package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
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
 * Calamity 5 "Ashking Wither": the skeletal king of the ash (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 350, ATTACK_DAMAGE 12, SCALE 2.0). Summoned with an
 * Ashking Skull Idol. Every 100 ticks it withers every survival player it can see within 6
 * blocks; below 50% health it enters phase two exactly once (persisted flag,
 * InfernoTitanEntity pattern) hardening with Resistance, and keeps a guarded speed boost
 * while in phase two. Drops per {@code loot_table/entities/ashking_wither.json}.
 */
public class AshkingWitherEntity extends WitherSkeletonEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier ASH_HASTE_MODIFIER_ID = CopperInferno.id("ashking_phase_two_haste");
	private static final int AURA_INTERVAL_TICKS = 100;
	private static final double AURA_RANGE = 6.0;
	private static final int AURA_EFFECT_TICKS = 60;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.ashking_wither"),
			BossBar.Color.PURPLE, BossBar.Style.NOTCHED_10);

	/** One-shot phase flag, persisted so a reloaded phase-2 king never re-buffs Resistance. */
	private boolean phaseTwo;

	public AshkingWitherEntity(EntityType<? extends WitherSkeletonEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Summoned bosses skip initialize(); hand over the vanilla stone sword so the
			// skeleton's melee goal engages (updateAttackType reads the held stack).
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			this.updateAttackType();
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
			ventWitherAura(world);
		}
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
		}
		if (this.phaseTwo) {
			// Temporary modifiers are not persisted; re-applies after a reload (guarded).
			applyAshHaste();
		}
	}

	/**
	 * Wither I to every survival/adventure player the boss has line of sight to within 6
	 * blocks, plus an ash smoke burst; creative and spectator players and players behind
	 * walls are unaffected.
	 */
	private void ventWitherAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, AURA_EFFECT_TICKS, 0));
		}
		world.spawnParticles(ParticleTypes.LARGE_SMOKE,
				this.getX(), this.getBodyY(0.5), this.getZ(), 24, 0.4, 0.6, 0.4, 0.01);
	}

	/** One-shot +0.06 flat movement speed in phase two; hasModifier guard prevents stacking. */
	private void applyAshHaste() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ASH_HASTE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ASH_HASTE_MODIFIER_ID, 0.06, EntityAttributeModifier.Operation.ADD_VALUE));
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
