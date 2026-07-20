package net.sonic0810.copperinferno.feature.bosslords;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
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
 * Inferno Lord 4 "Magma Leviathan": a colossal armored ghast (attributes registered in
 * {@link BossLordsFeature}: MAX_HEALTH 400, SCALE 1.8 on the already-4x4 ghast frame,
 * FOLLOW_RANGE 64). Mechanics:
 * <ol>
 * <li>Timed lava-bomb mortar - every 100 ticks (70 in phase 2) it hurls a spread of 3
 * (5 in phase 2) explosive fireballs at its target (lava spray + ghast-shoot sound); the
 * vanilla single-fireball attack keeps running between mortars.</li>
 * <li>Armor phase below 50% health (announced once, persisted): its hide hardens - it
 * re-applies Resistance continuously (totem flash + white smoke shell as feedback).</li>
 * <li>Enrage below 25% health: mortar bombs detonate with doubled explosion power.</li>
 * </ol>
 * Drops Leviathan Scales + Magma Bomb Shells + the Leviathan Maw
 * ({@code loot_table/entities/magma_leviathan.json}).
 */
public class MagmaLeviathanEntity extends GhastEntity {
	private static final String ARMOR_PHASE_KEY = "ArmorPhase";
	private static final int MORTAR_INTERVAL_TICKS = 100;
	private static final int MORTAR_INTERVAL_PHASE_TWO_TICKS = 70;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.magma_leviathan"),
			BossBar.Color.PINK, BossBar.Style.NOTCHED_12);

	/** One-shot announcement flag for the armor phase, persisted across reloads. */
	private boolean armorPhase;

	public MagmaLeviathanEntity(EntityType<? extends GhastEntity> type, World world) {
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
		if (!this.armorPhase && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.armorPhase = true;
			announceArmorPhase(world);
		}
		if (this.armorPhase) {
			maintainArmor(world);
		}
		int interval = this.armorPhase ? MORTAR_INTERVAL_PHASE_TWO_TICKS : MORTAR_INTERVAL_TICKS;
		// Idle gate: no combat target means no mortar (the volley also re-checks target
		// liveness internally before hurling explosive fireballs into the landscape).
		if (this.getTarget() != null && this.age % interval == 0) {
			lavaBombMortar(world);
		}
	}

	/**
	 * Lava-bomb mortar: hurls a spread of explosive ghast fireballs at the current target
	 * (3, 5 in the armor phase; explosion power 1, 2 when enraged below 25% health).
	 * Vanilla constructs ghast fireballs the same way: FireballEntity(world, owner,
	 * direction, power) with a normalized direction (ghast ShootFireballGoal bytecode).
	 * The vanilla ghast target selector supplies the player target.
	 */
	private void lavaBombMortar(ServerWorld world) {
		LivingEntity target = this.getTarget();
		if (target == null || !target.isAlive()) {
			return;
		}
		int count = this.armorPhase ? 5 : 3;
		int power = this.getHealth() < this.getMaxHealth() * 0.25f ? 2 : 1;
		Vec3d origin = new Vec3d(this.getX(), this.getBodyY(0.5), this.getZ());
		for (int i = 0; i < count; i++) {
			Vec3d spread = new Vec3d(
					(this.random.nextDouble() - 0.5) * 0.3,
					(this.random.nextDouble() - 0.5) * 0.15 + 0.1,
					(this.random.nextDouble() - 0.5) * 0.3);
			Vec3d direction = target.getEntityPos()
					.add(0.0, target.getHeight() * 0.5, 0.0)
					.subtract(origin)
					.normalize()
					.add(spread)
					.normalize();
			FireballEntity bomb = new FireballEntity(world, this, direction, power);
			bomb.setPosition(origin.x + direction.x * 3.0, origin.y, origin.z + direction.z * 3.0);
			world.spawnEntity(bomb);
		}
		world.spawnParticles(ParticleTypes.LAVA,
				this.getX(), this.getBodyY(0.3), this.getZ(), 30, 2.0, 1.5, 2.0, 0.05);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_GHAST_SHOOT,
				SoundCategory.HOSTILE, 2.0f, 0.6f);
	}

	/** One-shot armor-phase announcement: totem flash + deep warcry. */
	private void announceArmorPhase(ServerWorld world) {
		world.spawnParticles(ParticleTypes.WHITE_SMOKE,
				this.getX(), this.getBodyY(0.5), this.getZ(), 100, 3.0, 3.0, 3.0, 0.05);
		world.playSound(null, this.getBlockPos(), SoundEvents.ITEM_TOTEM_USE,
				SoundCategory.HOSTILE, 2.0f, 0.6f);
	}

	/**
	 * Armor stance: Resistance is re-applied continuously (the effect never runs out
	 * mid-phase) with a faint white smoke shell so players can read the stance.
	 */
	private void maintainArmor(ServerWorld world) {
		if (this.age % 60 == 0) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
			world.spawnParticles(ParticleTypes.WHITE_SMOKE,
					this.getX(), this.getBodyY(0.5), this.getZ(), 15, 2.5, 2.5, 2.5, 0.01);
		}
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putBoolean(ARMOR_PHASE_KEY, this.armorPhase);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.armorPhase = view.getBoolean(ARMOR_PHASE_KEY, false);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
