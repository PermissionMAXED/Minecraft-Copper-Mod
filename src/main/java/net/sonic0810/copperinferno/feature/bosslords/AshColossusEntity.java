package net.sonic0810.copperinferno.feature.bosslords;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Inferno Lord 3 "Ash Colossus": a towering golem wrapped in ash storms (attributes
 * registered in {@link BossLordsFeature}: MAX_HEALTH 550, ATTACK_DAMAGE 22, SCALE 3.5).
 * Mechanics:
 * <ol>
 * <li>Timed blinding ash storm - every 160 ticks (110 in phase 2) every player it can see
 * within 10 blocks is blinded and slowed (white-ash + smoke shroud, elder-guardian-curse
 * sound).</li>
 * <li>Cinder-shard volley (same cadence): 4-6 still-glowing cinders (small fireballs) are
 * flung out of the storm straight at its target.</li>
 * <li>Phase 2 below 50% health: the storm additionally saps strength (Weakness) and the
 * colossus takes a defensive stance, re-hardening itself with Resistance on every
 * storm.</li>
 * <li>Enrage below 25% health: one-shot bonus movement speed.</li>
 * </ol>
 * Drops Colossal Ash + Blinding Grit + the Ash Colossus Idol
 * ({@code loot_table/entities/ash_colossus.json}).
 */
public class AshColossusEntity extends IronGolemEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("colossus_enrage");
	private static final int STORM_INTERVAL_TICKS = 160;
	private static final int STORM_INTERVAL_PHASE_TWO_TICKS = 110;
	private static final double STORM_RANGE = 10.0;
	private static final int STORM_EFFECT_TICKS = 100;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.ash_colossus"),
			BossBar.Color.WHITE, BossBar.Style.PROGRESS);

	public AshColossusEntity(EntityType<? extends IronGolemEntity> type, World world) {
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
		// The phase shift is derived from health each tick (no one-shot burst to guard, so
		// no persisted flag is needed - the colossus's phase survives reloads for free).
		boolean phaseTwo = this.getHealth() < this.getMaxHealth() * 0.5f;
		int interval = phaseTwo ? STORM_INTERVAL_PHASE_TWO_TICKS : STORM_INTERVAL_TICKS;
		// Idle gate: no combat target means no storm and no cinders - an unprovoked boss
		// must not spam the elder-guardian curse peal or fling fireballs on a timer.
		LivingEntity target = this.getTarget();
		if (target != null && this.age % interval == 0) {
			ashStorm(world, phaseTwo);
			cinderShardVolley(world, target);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.25f) {
			enrage();
		}
	}

	/**
	 * Blinding ash storm: Blindness + Slowness (plus Weakness in phase 2) to every
	 * survival/adventure player the colossus has line of sight to within 10 blocks. In
	 * phase 2 the colossus also re-hardens itself with Resistance (defensive stance).
	 * Creative/spectator players and players behind walls are unaffected.
	 */
	private void ashStorm(ServerWorld world, boolean phaseTwo) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(STORM_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, STORM_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, STORM_EFFECT_TICKS, 0));
			if (phaseTwo) {
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, STORM_EFFECT_TICKS, 0));
			}
		}
		if (phaseTwo) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, STORM_EFFECT_TICKS, 0));
		}
		// A billowing shroud of white ash and smoke swallows the arena.
		world.spawnParticles(ParticleTypes.WHITE_ASH,
				this.getX(), this.getBodyY(0.5), this.getZ(), 150, 5.0, 2.5, 5.0, 0.1);
		world.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
				this.getX(), this.getBodyY(0.3), this.getZ(), 20, 3.0, 1.0, 3.0, 0.02);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE,
				SoundCategory.HOSTILE, 1.5f, 1.2f);
	}

	/**
	 * Cinder-shard volley, the colossus's offensive special (same cadence as the storm,
	 * target-gated): 4-6 still-glowing cinders are flung out of the ash cloud straight at
	 * the target, each with a slight scatter. Projectile construction copied from
	 * EmberMatriarchEntity.fireTornadoVolley: SmallFireballEntity(World, LivingEntity,
	 * Vec3d) takes a normalized direction, like the vanilla blaze/ghast shots.
	 */
	private void cinderShardVolley(ServerWorld world, LivingEntity target) {
		Vec3d origin = new Vec3d(this.getX(), this.getBodyY(0.6), this.getZ());
		int count = 4 + this.random.nextInt(3);
		for (int i = 0; i < count; i++) {
			Vec3d scatter = new Vec3d(
					(this.random.nextDouble() - 0.5) * 0.25,
					(this.random.nextDouble() - 0.5) * 0.12,
					(this.random.nextDouble() - 0.5) * 0.25);
			Vec3d direction = new Vec3d(
					target.getX() - origin.getX(),
					target.getBodyY(0.5) - origin.getY(),
					target.getZ() - origin.getZ())
					.normalize()
					.add(scatter)
					.normalize();
			SmallFireballEntity shard = new SmallFireballEntity(world, this, direction);
			shard.setPosition(origin.getX() + direction.getX() * 1.5, origin.getY(),
					origin.getZ() + direction.getZ() * 1.5);
			world.spawnEntity(shard);
		}
		// Embers glint inside the colossus's own white-ash shroud as the shards leave.
		world.spawnParticles(ParticleTypes.WHITE_ASH,
				origin.getX(), origin.getY(), origin.getZ(), 30, 1.2, 1.0, 1.2, 0.05);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT,
				SoundCategory.HOSTILE, 1.5f, 0.6f);
	}

	/**
	 * One-shot +0.06 flat movement speed below 25% health (hasModifier guard prevents
	 * stacking; re-applies after a reload, TheOxidizerEntity pattern).
	 */
	private void enrage() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_MODIFIER_ID, 0.06, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
