package net.sonic0810.copperinferno.feature.bosslords;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Inferno Lord 2 "Ember Matriarch": the blaze mother of fire tornadoes (attributes
 * registered in {@link BossLordsFeature}: MAX_HEALTH 300, SCALE 2.2, FOLLOW_RANGE 64).
 * Mechanics:
 * <ol>
 * <li>Timed fire tornado volley - every 120 ticks (80 in phase 2) a whirling ring of 8
 * (12 in phase 2) small fireballs erupts outward (flame spiral + blaze-shoot sound).</li>
 * <li>Heals from fire - every second she drinks 1 health per burning living entity within
 * 8 blocks (capped at 3), with a flame flare as feedback.</li>
 * <li>Phase 2 below 50% health (one-shot, persisted): calls 2 blaze daughters and hardens
 * with Resistance; the tornado grows denser and faster.</li>
 * <li>Enrage below 25% health: one-shot bonus movement speed.</li>
 * </ol>
 * Drops Matriarch Plumes + Living Flames + the Matriarch Diadem
 * ({@code loot_table/entities/ember_matriarch.json}).
 */
public class EmberMatriarchEntity extends BlazeEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("matriarch_enrage");
	private static final int VOLLEY_INTERVAL_TICKS = 120;
	private static final int VOLLEY_INTERVAL_PHASE_TWO_TICKS = 80;
	private static final double FIRE_FEED_RANGE = 8.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.ember_matriarch"),
			BossBar.Color.RED, BossBar.Style.NOTCHED_10);

	/** One-shot phase flag, persisted so a reloaded phase-2 matriarch never re-spawns minions. */
	private boolean phaseTwo;

	public EmberMatriarchEntity(EntityType<? extends BlazeEntity> type, World world) {
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
		int interval = this.phaseTwo ? VOLLEY_INTERVAL_PHASE_TWO_TICKS : VOLLEY_INTERVAL_TICKS;
		// Idle gate: no combat target means no tornado - an unprovoked boss must not
		// hose fireballs into the terrain (block fires!) or spam the blaze-shoot scream.
		if (this.getTarget() != null && this.age % interval == 0) {
			fireTornadoVolley(world);
		}
		if (this.age % 20 == 0) {
			feedOnFire(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.25f) {
			enrage();
		}
	}

	/**
	 * Fire tornado volley: a ring of small fireballs (8, 12 in phase 2) whirls outward from
	 * the matriarch, slightly tilted downward so the ring rakes the ground around her.
	 * Vanilla constructs blaze fireballs the same way: SmallFireballEntity(world, owner,
	 * direction) with the direction normalized (ghast ShootFireballGoal bytecode).
	 */
	private void fireTornadoVolley(ServerWorld world) {
		int count = this.phaseTwo ? 12 : 8;
		for (int i = 0; i < count; i++) {
			float angle = (float) (2.0 * Math.PI * i / count) + this.age * 0.1f;
			Vec3d direction = new Vec3d(MathHelper.cos(angle), -0.1, MathHelper.sin(angle)).normalize();
			SmallFireballEntity fireball = new SmallFireballEntity(world, this, direction);
			fireball.setPosition(this.getX() + direction.x * 1.5, this.getBodyY(0.5), this.getZ() + direction.z * 1.5);
			world.spawnEntity(fireball);
		}
		// Flame spiral rising around her body.
		world.spawnParticles(ParticleTypes.FLAME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 60, 1.2, 1.4, 1.2, 0.08);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT,
				SoundCategory.HOSTILE, 2.0f, 0.7f);
	}

	/**
	 * Heals from fire: every second the matriarch drinks 1 health per burning living entity
	 * (other than herself) within 8 blocks, capped at 3 per pulse, flaring small flames as
	 * feedback. Fighting her with fire is a mistake.
	 */
	private void feedOnFire(ServerWorld world) {
		if (this.getHealth() >= this.getMaxHealth()) {
			return;
		}
		int burning = world.getEntitiesByClass(LivingEntity.class,
				this.getBoundingBox().expand(FIRE_FEED_RANGE),
				entity -> entity != this && entity.isAlive() && entity.isOnFire()).size();
		if (burning > 0) {
			this.heal(Math.min(burning, 3));
			world.spawnParticles(ParticleTypes.SMALL_FLAME,
					this.getX(), this.getBodyY(0.7), this.getZ(), 12, 0.5, 0.6, 0.5, 0.02);
		}
	}

	/** Phase 2 burst: 2 blaze daughters + 100 ticks of Resistance (denser volley via interval). */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			BlazeEntity minion = new BlazeEntity(EntityType.BLAZE, world);
			minion.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(minion);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
		world.spawnParticles(ParticleTypes.FLAME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 80, 1.5, 1.5, 1.5, 0.1);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_BLAZE_AMBIENT,
				SoundCategory.HOSTILE, 2.0f, 0.5f);
	}

	/**
	 * One-shot +0.05 flat movement speed below 25% health (hasModifier guard prevents
	 * stacking; re-applies after a reload, TheOxidizerEntity pattern).
	 */
	private void enrage() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_MODIFIER_ID, 0.05, EntityAttributeModifier.Operation.ADD_VALUE));
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
