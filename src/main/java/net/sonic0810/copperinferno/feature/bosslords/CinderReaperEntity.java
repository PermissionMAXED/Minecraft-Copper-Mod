package net.sonic0810.copperinferno.feature.bosslords;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Inferno Lord 5 "Cinder Reaper": a scythe-wielding skeleton lord that steps through
 * shadow (attributes registered in {@link BossLordsFeature}: MAX_HEALTH 240,
 * ATTACK_DAMAGE 8, SCALE 1.8). Wields a netherite-hoe scythe (never dropped; holding a
 * non-bow weapon makes the vanilla skeleton AI pick its melee goal via updateAttackType)
 * and never burns in daylight. Mechanics:
 * <ol>
 * <li>Timed teleporting scythe strike - every 100 ticks (60 in phase 2) it blinks right
 * next to its target and reaps it: damage + Wither (portal particles + enderman-teleport
 * sound at both ends).</li>
 * <li>Wither aura - every 60 ticks every player within 4 blocks withers (soul
 * particles).</li>
 * <li>Phase 2 below 50% health: one-shot ghostly speed stance + faster blinks.</li>
 * <li>Enrage below 25% health: one-shot bonus scythe damage.</li>
 * </ol>
 * Drops Reaper Cinders + Withered Cloth + the Cinder Scythe
 * ({@code loot_table/entities/cinder_reaper.json}).
 */
public class CinderReaperEntity extends SkeletonEntity {
	private static final Identifier PHASE_TWO_SPEED_MODIFIER_ID = CopperInferno.id("reaper_phase_two_speed");
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("reaper_enrage");
	private static final int STRIKE_INTERVAL_TICKS = 100;
	private static final int STRIKE_INTERVAL_PHASE_TWO_TICKS = 60;
	private static final int AURA_INTERVAL_TICKS = 60;
	private static final double AURA_RANGE = 4.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.cinder_reaper"),
			BossBar.Color.PURPLE, BossBar.Style.NOTCHED_20);

	public CinderReaperEntity(EntityType<? extends SkeletonEntity> type, World world) {
		super(type, world);
		if (!world.isClient()) {
			// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
			this.setPersistent();
			// The scythe: constructor-spawned skeletons (summon item path) get no
			// initialize() equipment, so the hoe is equipped here; updateAttackType() then
			// makes the vanilla skeleton AI pick its melee goal (no bow in hand).
			equipScythe();
		}
	}

	/**
	 * The /summon and spawn-egg paths DO call initialize(), whose vanilla implementation
	 * hands every skeleton a bow (and then updateAttackType() would pick the ranged goal);
	 * overriding it keeps the scythe in hand on every spawn path. Never dropped as loot.
	 */
	@Override
	protected void initEquipment(net.minecraft.util.math.random.Random random,
			net.minecraft.world.LocalDifficulty localDifficulty) {
		equipScythe();
	}

	private void equipScythe() {
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.NETHERITE_HOE));
		this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.0f);
		this.updateAttackType();
	}

	/** The reaper walks in daylight unburnt (sunlight immunity befits a boss). */
	@Override
	protected boolean isAffectedByDaylight() {
		return false;
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
		boolean phaseTwo = this.getHealth() < this.getMaxHealth() * 0.5f;
		if (phaseTwo) {
			applyPhaseTwoSpeed();
		}
		int interval = phaseTwo ? STRIKE_INTERVAL_PHASE_TWO_TICKS : STRIKE_INTERVAL_TICKS;
		if (this.age % interval == 0) {
			teleportScytheStrike(world, phaseTwo);
		}
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			witherAura(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.25f) {
			enrage();
		}
	}

	/**
	 * Teleporting scythe strike: blinks right next to its living target (random offset
	 * within 2 blocks; LivingEntity.teleport finds safe footing and returns false when
	 * there is none, in which case the reaper stays put) and reaps it for 7 damage plus
	 * Wither (Wither II in phase 2). Portal particles mark both ends of the blink.
	 */
	private void teleportScytheStrike(ServerWorld world, boolean phaseTwo) {
		LivingEntity target = this.getTarget();
		if (target == null || !target.isAlive()) {
			return;
		}
		world.spawnParticles(ParticleTypes.PORTAL,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.4, 0.8, 0.4, 0.1);
		double destX = target.getX() + (this.random.nextDouble() - 0.5) * 4.0;
		double destZ = target.getZ() + (this.random.nextDouble() - 0.5) * 4.0;
		if (this.teleport(destX, target.getY(), destZ, true)) {
			world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT,
					SoundCategory.HOSTILE, 1.5f, 0.7f);
			world.spawnParticles(ParticleTypes.PORTAL,
					this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.4, 0.8, 0.4, 0.1);
		}
		if (this.squaredDistanceTo(target) < 25.0) {
			target.damage(world, this.getDamageSources().mobAttack(this), 7.0f);
			target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, phaseTwo ? 1 : 0));
			world.spawnParticles(ParticleTypes.SWEEP_ATTACK,
					target.getX(), target.getBodyY(0.5), target.getZ(), 2, 0.3, 0.3, 0.3, 0.0);
		}
	}

	/**
	 * Wither aura: every survival/adventure player within 4 blocks withers for 3 seconds;
	 * soul wisps bleed off the reaper as feedback.
	 */
	private void witherAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative())) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 0));
		}
		world.spawnParticles(ParticleTypes.SOUL,
				this.getX(), this.getBodyY(0.5), this.getZ(), 12, 1.0, 1.0, 1.0, 0.02);
	}

	/**
	 * One-shot +0.05 flat movement speed once phase 2 begins (hasModifier guard prevents
	 * stacking; re-applies after a reload, InfernoTitanEntity melee-boost pattern).
	 */
	private void applyPhaseTwoSpeed() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(PHASE_TWO_SPEED_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					PHASE_TWO_SPEED_MODIFIER_ID, 0.05, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** One-shot +4 flat scythe damage below 25% health (hasModifier guard, same pattern). */
	private void enrage() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(ENRAGE_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
