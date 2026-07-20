package net.sonic0810.copperinferno.feature.bosslords;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MagmaCubeEntity;
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
 * Inferno Lord 1 "The Slag King": a molten iron-golem monarch (attributes registered in
 * {@link BossLordsFeature}: MAX_HEALTH 500, ATTACK_DAMAGE 20, SCALE 3.0). Mechanics:
 * <ol>
 * <li>Timed molten fissure slam - every 140 ticks (90 in phase 2) grounded players in
 * sight within 7 blocks take damage, catch fire and are hurled away (lava + gust particles,
 * golem-attack + anvil sounds).</li>
 * <li>Phase 2 below 50% health (one-shot, persisted): erupts 3 slag minions (size-2 magma
 * cubes), hardens with Resistance and slams faster.</li>
 * <li>Enrage below 25% health: one-shot bonus movement speed.</li>
 * </ol>
 * Drops Royal Slag + Fissure Shards + the Slag Crown
 * ({@code loot_table/entities/the_slag_king.json}).
 */
public class TheSlagKingEntity extends IronGolemEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("slag_king_enrage");
	private static final int SLAM_INTERVAL_TICKS = 140;
	private static final int SLAM_INTERVAL_PHASE_TWO_TICKS = 90;
	private static final double SLAM_RANGE = 7.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.the_slag_king"),
			BossBar.Color.YELLOW, BossBar.Style.NOTCHED_6);

	/** One-shot phase flag, persisted so a reloaded phase-2 king never re-spawns minions. */
	private boolean phaseTwo;

	public TheSlagKingEntity(EntityType<? extends IronGolemEntity> type, World world) {
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

	/** Blocks the inherited iron-golem right-click repair (players could heal the boss). */
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
			enterPhaseTwo(world);
		}
		int interval = this.phaseTwo ? SLAM_INTERVAL_PHASE_TWO_TICKS : SLAM_INTERVAL_TICKS;
		// Idle gate: no combat target means no slam - an unprovoked boss must not spray
		// lava particles or clang golem-attack/anvil sounds across the land on a timer.
		if (this.getTarget() != null && this.age % interval == 0) {
			fissureSlam(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.25f) {
			enrage();
		}
	}

	/**
	 * Molten fissure slam: every grounded survival/adventure player the king has line of
	 * sight to within 7 blocks takes 8 slam damage, burns for 3 seconds and is hurled away
	 * from the king. Creative/spectator players and players behind walls are unaffected.
	 */
	private void fissureSlam(ServerWorld world) {
		boolean hitAnyone = false;
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(SLAM_RANGE),
				player -> !player.isSpectator() && !player.isCreative()
						&& player.isOnGround() && this.canSee(player))) {
			player.damage(world, this.getDamageSources().mobAttack(this), 8.0f);
			player.setOnFireFor(3.0f);
			Vec3d away = player.getEntityPos().subtract(this.getEntityPos());
			Vec3d push = away.lengthSquared() > 1.0E-4
					? away.normalize().multiply(1.2)
					: new Vec3d(0.0, 0.0, 1.2);
			player.addVelocity(push.x, 0.5, push.z);
			player.velocityModified = true;
			hitAnyone = true;
		}
		// The fissure tears open around the king: lava spray + a gust shockwave.
		world.spawnParticles(ParticleTypes.LAVA,
				this.getX(), this.getY() + 0.2, this.getZ(), 40, 3.0, 0.3, 3.0, 0.02);
		world.spawnParticles(ParticleTypes.GUST,
				this.getX(), this.getY() + 0.3, this.getZ(), 4, 1.5, 0.2, 1.5, 0.0);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_ATTACK,
				SoundCategory.HOSTILE, 2.0f, 0.6f);
		if (hitAnyone) {
			world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ANVIL_LAND,
					SoundCategory.HOSTILE, 1.0f, 0.8f);
		}
	}

	/** Phase 2 burst: 3 slag minions (size-2 magma cubes) + 200 ticks of Resistance. */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 3; i++) {
			MagmaCubeEntity minion = new MagmaCubeEntity(EntityType.MAGMA_CUBE, world);
			minion.setSize(2, true);
			minion.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 4.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 4.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(minion);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0));
		world.spawnParticles(ParticleTypes.LAVA,
				this.getX(), this.getBodyY(0.5), this.getZ(), 60, 1.5, 1.0, 1.5, 0.05);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_RAVAGER_ROAR,
				SoundCategory.HOSTILE, 2.0f, 0.7f);
	}

	/**
	 * One-shot +0.06 flat movement speed below 25% health. Temporary modifiers are not
	 * persisted, so after a reload this simply re-applies on the next tick; the
	 * {@code hasModifier} guard keeps it from stacking (TheOxidizerEntity pattern).
	 */
	private void enrage() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_MODIFIER_ID, 0.06, EntityAttributeModifier.Operation.ADD_VALUE));
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
