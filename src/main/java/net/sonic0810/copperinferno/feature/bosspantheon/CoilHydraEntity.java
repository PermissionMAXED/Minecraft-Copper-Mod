package net.sonic0810.copperinferno.feature.bosspantheon;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Pantheon boss 3 "Coil Hydra": a many-throated copper-coil ghast (attributes in
 * {@link BossPantheonFeature}: MAX_HEALTH 320, SCALE 1.8, FOLLOW_RANGE 64). Vanilla ghast
 * AI (single fireballs) plus three mechanics: a timed hydra volley (a horizontal fan of
 * small fireballs around one large "mother" fireball, flame burst + shoot scream), the
 * mother fireball SPLITTING mid-flight into four more small fireballs fanned off its
 * current velocity, and a phase 2 below 50% health (persisted flag: volley grows from 3 to
 * 5 heads, cadence tightens from 100 to 70 ticks, entry grants 100 ticks of Resistance).
 * Drops Coil Scales, Hydra Tears and the Hydra Diadem
 * ({@code loot_table/entities/coil_hydra.json}).
 */
public class CoilHydraEntity extends GhastEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int VOLLEY_INTERVAL_TICKS = 100;
	private static final int VOLLEY_INTERVAL_PHASE_TWO_TICKS = 70;
	private static final int SPLIT_DELAY_TICKS = 12;
	/** Fan half-angle step between volley fireballs, in degrees. */
	private static final float VOLLEY_SPREAD_DEGREES = 12.0f;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.coil_hydra"),
			BossBar.Color.RED, BossBar.Style.NOTCHED_12);

	/** One-shot phase flag, persisted so a reloaded phase-2 hydra keeps its 5-head volley. */
	private boolean phaseTwo;
	/** The airborne mother fireball awaiting its split (transient: a chunk reload just skips one split). */
	private FireballEntity pendingSplit;
	private int splitCountdown;

	public CoilHydraEntity(EntityType<? extends GhastEntity> type, World world) {
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
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
			world.spawnParticles(ParticleTypes.FLAME,
					this.getX(), this.getBodyY(0.5), this.getZ(), 60, 2.0, 2.0, 2.0, 0.05);
			world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_GHAST_SHOOT,
					SoundCategory.HOSTILE, 2.0f, 0.5f);
		}
		LivingEntity target = this.getTarget();
		int interval = this.phaseTwo ? VOLLEY_INTERVAL_PHASE_TWO_TICKS : VOLLEY_INTERVAL_TICKS;
		if (target != null && this.canSee(target) && this.age % interval == 0) {
			fireHydraVolley(world, target);
		}
		tickPendingSplit(world);
	}

	/**
	 * Hydra volley: one large explosive "mother" fireball straight at the target flanked by
	 * a horizontal fan of small fireballs (3 heads in phase 1, 5 in phase 2). Constructor
	 * shapes verified via javap: FireballEntity(World, LivingEntity, Vec3d, int) and
	 * SmallFireballEntity(World, LivingEntity, Vec3d) take a velocity vector like the
	 * vanilla ghast/blaze shots.
	 */
	private void fireHydraVolley(ServerWorld world, LivingEntity target) {
		Vec3d origin = new Vec3d(this.getX(), this.getBodyY(0.5), this.getZ());
		Vec3d direction = new Vec3d(
				target.getX() - origin.getX(),
				target.getBodyY(0.5) - origin.getY(),
				target.getZ() - origin.getZ()).normalize();

		FireballEntity mother = new FireballEntity(world, this, direction, 1);
		mother.setPosition(origin);
		world.spawnEntity(mother);
		this.pendingSplit = mother;
		this.splitCountdown = SPLIT_DELAY_TICKS;

		int heads = this.phaseTwo ? 5 : 3;
		for (int i = 0; i < heads; i++) {
			// Symmetric fan around the mother: +/-12, +/-24, ... degrees, skipping 0.
			float degrees = (i / 2 + 1) * VOLLEY_SPREAD_DEGREES * (i % 2 == 0 ? 1 : -1);
			SmallFireballEntity head = new SmallFireballEntity(world, this,
					direction.rotateY((float) Math.toRadians(degrees)));
			head.setPosition(origin);
			world.spawnEntity(head);
		}
		world.spawnParticles(ParticleTypes.FLAME,
				origin.getX(), origin.getY(), origin.getZ(), 20, 0.8, 0.8, 0.8, 0.05);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_GHAST_SHOOT,
				SoundCategory.HOSTILE, 2.0f, 0.8f);
	}

	/**
	 * The split: while the mother fireball is still flying after 12 ticks, it bursts into
	 * four small fireballs fanned off its current velocity (flame pop + blaze-shot hiss at
	 * the split point). If it already detonated, the split simply fizzles.
	 */
	private void tickPendingSplit(ServerWorld world) {
		if (this.pendingSplit == null || this.splitCountdown-- > 0) {
			return;
		}
		FireballEntity mother = this.pendingSplit;
		this.pendingSplit = null;
		if (!mother.isAlive()) {
			return;
		}
		Vec3d velocity = mother.getVelocity();
		if (velocity.lengthSquared() < 1.0e-4) {
			return;
		}
		Vec3d direction = velocity.normalize();
		for (int i = 0; i < 4; i++) {
			float degrees = (i / 2 + 1) * 18.0f * (i % 2 == 0 ? 1 : -1);
			SmallFireballEntity shard = new SmallFireballEntity(world, this,
					direction.rotateY((float) Math.toRadians(degrees)));
			shard.setPosition(mother.getX(), mother.getY(), mother.getZ());
			world.spawnEntity(shard);
		}
		world.spawnParticles(ParticleTypes.FLAME,
				mother.getX(), mother.getY(), mother.getZ(), 16, 0.4, 0.4, 0.4, 0.08);
		world.playSound(null, mother.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT,
				SoundCategory.HOSTILE, 1.5f, 1.2f);
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
