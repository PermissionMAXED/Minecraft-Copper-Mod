package net.sonic0810.copperinferno.feature.bossdoom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Boss 5 "Voidstone Behemoth": a gravity-cursed colossus of voidstone (attributes registered
 * in {@link BossDoomFeature}: MAX_HEALTH 600, ATTACK_DAMAGE 22, SCALE 2.6). Mechanics:
 * <ul>
 * <li>Gravity well (timed special, part 1): every 140 ticks (70 in phase 2) it collapses
 * space - every player within 12 blocks is yanked TOWARD the boss (velocity toward it,
 * stronger when closer to the edge) under a reverse-portal implosion and a warden boom.</li>
 * <li>Follow-up slam (timed special, part 2): 15 ticks after the pull it slams the ground -
 * every player still within 5 blocks is damaged and popped upward with a gust/explosion
 * shock ring.</li>
 * <li>Phase 2 "event horizon" at 50% health (persisted): permanent +8 armor plating and
 * bonus speed, and the gravity well tempo doubles.</li>
 * </ul>
 * Like The Oxidizer it hunts players on sight and blocks the inherited iron-golem
 * right-click repair. Drops via {@code loot_table/entities/voidstone_behemoth.json}.
 */
public class VoidstoneBehemothEntity extends IronGolemEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier HORIZON_ARMOR_ID = CopperInferno.id("behemoth_horizon_armor");
	private static final Identifier HORIZON_SPEED_ID = CopperInferno.id("behemoth_horizon_speed");
	private static final int WELL_INTERVAL_TICKS = 140;
	private static final int SLAM_DELAY_TICKS = 15;
	private static final double PULL_RANGE = 12.0;
	private static final double SLAM_RANGE = 5.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.voidstone_behemoth"),
			BossBar.Color.PURPLE, BossBar.Style.NOTCHED_10);

	/** One-shot phase flag, persisted so a reloaded phase-2 boss keeps its plating. */
	private boolean phaseTwo;
	/** Ticks until the follow-up slam; 0 = no slam pending. Transient by design: a reload mid-combo just skips one slam. */
	private int pendingSlamTicks;

	public VoidstoneBehemothEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// Unlike the vanilla golem (only angered on attack), the boss hunts players on sight.
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	/**
	 * Blocks the inherited iron-golem right-click repair: without this, players could heal
	 * the boss mid-fight with iron ingots (TheOxidizerEntity pattern).
	 */
	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
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
			world.spawnParticles(ParticleTypes.SQUID_INK,
					this.getX(), this.getBodyY(0.6), this.getZ(), 30, 0.9, 1.2, 0.9, 0.05);
		}
		if (this.phaseTwo) {
			applyHorizonModifiers();
		}
		int wellInterval = this.phaseTwo ? WELL_INTERVAL_TICKS / 2 : WELL_INTERVAL_TICKS;
		// Idle gate: no combat target means no collapse - an unprovoked boss must not
		// yank bystanders or boom the warden sonic dread on a timer. The follow-up slam
		// only ever arms here, so gating the well gates the whole combo.
		if (this.getTarget() != null && this.age % wellInterval == 0) {
			gravityWell(world);
			this.pendingSlamTicks = SLAM_DELAY_TICKS;
		}
		if (this.pendingSlamTicks > 0 && --this.pendingSlamTicks == 0) {
			groundSlam(world);
		}
	}

	/**
	 * Yanks every survival/adventure player within 12 blocks toward the boss; the pull is
	 * distance-scaled (stronger at the rim) so nobody gets slingshotted past it. Reverse
	 * portal + squid ink implosion with a warden boom for the audio dread.
	 */
	private void gravityWell(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(PULL_RANGE),
				player -> !player.isSpectator() && !player.isCreative())) {
			Vec3d toBoss = new Vec3d(this.getX() - player.getX(), 0.0, this.getZ() - player.getZ());
			double distance = toBoss.length();
			if (distance < 0.01) {
				continue;
			}
			double strength = Math.min(1.4, 0.35 + distance * 0.12);
			player.addVelocity(toBoss.x / distance * strength, 0.15, toBoss.z / distance * strength);
			// addVelocity only mutates the server-side velocity; flag it for sync.
			player.velocityModified = true;
		}
		world.spawnParticles(ParticleTypes.REVERSE_PORTAL,
				this.getX(), this.getBodyY(0.5), this.getZ(), 80, 5.0, 1.5, 5.0, 0.1);
		world.spawnParticles(ParticleTypes.SQUID_INK,
				this.getX(), this.getBodyY(0.5), this.getZ(), 20, 2.0, 0.8, 2.0, 0.05);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM,
				SoundCategory.HOSTILE, 1.0f, 0.6f);
	}

	/** The follow-up slam: heavy damage + knock-up to everyone dragged within 5 blocks. */
	private void groundSlam(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(SLAM_RANGE),
				player -> !player.isSpectator() && !player.isCreative())) {
			player.damage(world, this.getDamageSources().mobAttack(this), 10.0f);
			player.addVelocity(0.0, 0.6, 0.0);
			player.velocityModified = true;
		}
		world.spawnParticles(ParticleTypes.GUST,
				this.getX(), this.getBodyY(0.1), this.getZ(), 5, 1.4, 0.2, 1.4, 0.0);
		world.spawnParticles(ParticleTypes.EXPLOSION,
				this.getX(), this.getBodyY(0.2), this.getZ(), 4, 1.6, 0.3, 1.6, 0.0);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_ATTACK,
				SoundCategory.HOSTILE, 1.5f, 0.5f);
	}

	/**
	 * Phase-2 event horizon: +8 flat armor and +0.04 flat movement speed. Temporary
	 * modifiers are not persisted, so this re-applies every tick after a reload; the
	 * {@code hasModifier} guards keep it from stacking.
	 */
	private void applyHorizonModifiers() {
		EntityAttributeInstance armor = this.getAttributeInstance(EntityAttributes.ARMOR);
		if (armor != null && !armor.hasModifier(HORIZON_ARMOR_ID)) {
			armor.addTemporaryModifier(new EntityAttributeModifier(
					HORIZON_ARMOR_ID, 8.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(HORIZON_SPEED_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					HORIZON_SPEED_ID, 0.04, EntityAttributeModifier.Operation.ADD_VALUE));
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
