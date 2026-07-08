package net.sonic0810.copperinferno.feature.bossdoom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Boss 4 "Kiln Archon": a towering master-smelter blaze (attributes registered in
 * {@link BossDoomFeature}: MAX_HEALTH 350, ATTACK_DAMAGE 10, SCALE 2.8). Mechanics:
 * <ul>
 * <li>Furnace beam (timed special): every 100 ticks (50 white-hot) it fires a flame/lava
 * particle beam at its visible target within 24 blocks - the beam MELTS ARMOR, damaging the
 * durability of every worn piece, plus fire damage and ignition.</li>
 * <li>Kiln shield (defensive stance): every 220 ticks it vitrifies for 70 ticks of
 * Resistance II under a smoke + flame shell.</li>
 * <li>Phase 2 "white-hot" at 50% health (persisted): permanent bonus speed, a one-shot
 * Resistance burst, a lava-splash nova and the furnace beam tempo doubles.</li>
 * </ul>
 * Vanilla blaze AI (hovering, triple fireball volleys) is inherited. Drops via
 * {@code loot_table/entities/kiln_archon.json}.
 */
public class KilnArchonEntity extends BlazeEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier WHITE_HOT_SPEED_ID = CopperInferno.id("archon_white_hot_speed");
	private static final int BEAM_INTERVAL_TICKS = 100;
	private static final int SHIELD_INTERVAL_TICKS = 220;
	private static final int SHIELD_DURATION_TICKS = 70;
	private static final double BEAM_RANGE = 24.0;
	private static final EquipmentSlot[] ARMOR_SLOTS = {
			EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.kiln_archon"),
			BossBar.Color.YELLOW, BossBar.Style.NOTCHED_12);

	/** One-shot phase flag, persisted so a reloaded white-hot boss stays white-hot. */
	private boolean phaseTwo;

	public KilnArchonEntity(EntityType<? extends BlazeEntity> type, World world) {
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
			// One-shot white-hot nova; the speed modifier re-applies below after reloads.
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
			world.spawnParticles(ParticleTypes.LAVA,
					this.getX(), this.getBodyY(0.5), this.getZ(), 25, 1.0, 1.0, 1.0, 0.0);
		}
		if (this.phaseTwo) {
			applyWhiteHotSpeed();
		}
		int beamInterval = this.phaseTwo ? BEAM_INTERVAL_TICKS / 2 : BEAM_INTERVAL_TICKS;
		if (this.age % beamInterval == 0) {
			furnaceBeam(world);
		}
		if (this.age % SHIELD_INTERVAL_TICKS == 0) {
			kilnShield(world);
		}
	}

	/**
	 * The armor-melting beam: a flame/lava particle line from the boss's chest to its
	 * visible target within 24 blocks. Every worn armor piece of a player target loses 8
	 * durability ({@code ItemStack.damage(int, LivingEntity, EquipmentSlot)}, which handles
	 * breaking, Unbreaking and creative), plus 5 fire damage and 3 seconds of ignition.
	 */
	private void furnaceBeam(ServerWorld world) {
		LivingEntity target = this.getTarget();
		if (target == null || !this.canSee(target)
				|| this.squaredDistanceTo(target) > BEAM_RANGE * BEAM_RANGE) {
			return;
		}
		Vec3d from = new Vec3d(this.getX(), this.getBodyY(0.7), this.getZ());
		Vec3d to = new Vec3d(target.getX(), target.getBodyY(0.5), target.getZ());
		Vec3d step = to.subtract(from).normalize().multiply(0.7);
		Vec3d cursor = from;
		for (int i = 0; i < from.distanceTo(to) / 0.7; i++) {
			world.spawnParticles(ParticleTypes.FLAME, cursor.x, cursor.y, cursor.z, 2, 0.05, 0.05, 0.05, 0.0);
			if (i % 3 == 0) {
				world.spawnParticles(ParticleTypes.LAVA, cursor.x, cursor.y, cursor.z, 1, 0.05, 0.05, 0.05, 0.0);
			}
			cursor = cursor.add(step);
		}
		if (target instanceof PlayerEntity player) {
			for (EquipmentSlot slot : ARMOR_SLOTS) {
				ItemStack armor = player.getEquippedStack(slot);
				if (!armor.isEmpty()) {
					armor.damage(8, player, slot);
				}
			}
		}
		target.damage(world, this.getDamageSources().mobAttack(this), 5.0f);
		target.setOnFireFor(3.0f);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_BLAZE_SHOOT,
				SoundCategory.HOSTILE, 1.5f, 0.6f);
	}

	/** Defensive stance: 70 ticks of Resistance II under a smoke + flame shell. */
	private void kilnShield(ServerWorld world) {
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, SHIELD_DURATION_TICKS, 1));
		world.spawnParticles(ParticleTypes.LARGE_SMOKE,
				this.getX(), this.getBodyY(0.5), this.getZ(), 20, 0.9, 1.1, 0.9, 0.02);
		world.spawnParticles(ParticleTypes.FLAME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 25, 0.9, 1.1, 0.9, 0.03);
	}

	/**
	 * Phase-2 +0.04 flat movement speed. Temporary modifiers are not persisted, so this
	 * re-applies every tick after a reload; the {@code hasModifier} guard keeps it from
	 * stacking.
	 */
	private void applyWhiteHotSpeed() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(WHITE_HOT_SPEED_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					WHITE_HOT_SPEED_ID, 0.04, EntityAttributeModifier.Operation.ADD_VALUE));
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
