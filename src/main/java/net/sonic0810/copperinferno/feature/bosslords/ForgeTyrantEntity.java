package net.sonic0810.copperinferno.feature.bosslords;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
 * Inferno Lord 6 "Forge Tyrant": a ravager cast from forge steel (attributes registered
 * in {@link BossLordsFeature}: MAX_HEALTH 600, ATTACK_DAMAGE 18, SCALE 2.0). The vanilla
 * ravager AI already hunts players. Mechanics:
 * <ol>
 * <li>Timed anvil quake - every 150 ticks (100 in phase 2) grounded players within 8
 * blocks are crushed for 10 damage, slowed and hurled away (gust + crit particles,
 * anvil-land sound).</li>
 * <li>Phase 2 below 50% health (one-shot, persisted): bellows for 2 axe-wielding
 * forge-hand adds (vindicators) and quakes faster.</li>
 * <li>Enrage below 25% health: one-shot bonus speed AND attack damage, with a roar.</li>
 * </ol>
 * Drops Forge Scrap + Tyrant Hide + the Tyrant's Anvil
 * ({@code loot_table/entities/forge_tyrant.json}).
 */
public class ForgeTyrantEntity extends RavagerEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier ENRAGE_SPEED_MODIFIER_ID = CopperInferno.id("tyrant_enrage_speed");
	private static final Identifier ENRAGE_DAMAGE_MODIFIER_ID = CopperInferno.id("tyrant_enrage_damage");
	private static final int QUAKE_INTERVAL_TICKS = 150;
	private static final int QUAKE_INTERVAL_PHASE_TWO_TICKS = 100;
	private static final double QUAKE_RANGE = 8.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.forge_tyrant"),
			BossBar.Color.YELLOW, BossBar.Style.NOTCHED_6);

	/** One-shot phase flag, persisted so a reloaded phase-2 tyrant never re-spawns adds. */
	private boolean phaseTwo;
	/** Roar-once latch for the enrage; not persisted (a reload just roars again once). */
	private boolean enraged;

	public ForgeTyrantEntity(EntityType<? extends RavagerEntity> type, World world) {
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
			summonForgeHands(world);
		}
		int interval = this.phaseTwo ? QUAKE_INTERVAL_PHASE_TWO_TICKS : QUAKE_INTERVAL_TICKS;
		// Idle gate: no combat target means no quake - an unprovoked boss must not clang
		// the anvil-land peal (or crush bystanders) on a timer.
		if (this.getTarget() != null && this.age % interval == 0) {
			anvilQuake(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.25f) {
			enrage(world);
		}
	}

	/**
	 * Anvil quake: every grounded survival/adventure player within 8 blocks is crushed for
	 * 10 damage, slowed for 5 seconds and hurled away from the tyrant. Airborne players
	 * ride out the shockwave (dodge mechanic); creative/spectator players are unaffected.
	 */
	private void anvilQuake(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(QUAKE_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && player.isOnGround())) {
			player.damage(world, this.getDamageSources().mobAttack(this), 10.0f);
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1));
			Vec3d away = player.getEntityPos().subtract(this.getEntityPos());
			Vec3d push = away.lengthSquared() > 1.0E-4
					? away.normalize().multiply(1.4)
					: new Vec3d(0.0, 0.0, 1.4);
			player.addVelocity(push.x, 0.6, push.z);
			player.velocityModified = true;
		}
		world.spawnParticles(ParticleTypes.GUST,
				this.getX(), this.getY() + 0.3, this.getZ(), 6, 2.5, 0.3, 2.5, 0.0);
		world.spawnParticles(ParticleTypes.CRIT,
				this.getX(), this.getY() + 0.5, this.getZ(), 50, 3.5, 0.5, 3.5, 0.2);
		world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ANVIL_LAND,
				SoundCategory.HOSTILE, 2.0f, 0.5f);
	}

	/**
	 * Phase 2 burst: 2 forge-hand adds - vindicators hand-equipped with iron axes
	 * (constructor-spawned illagers get no initialize() equipment), never dropped as loot.
	 */
	private void summonForgeHands(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			VindicatorEntity add = new VindicatorEntity(EntityType.VINDICATOR, world);
			add.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
			add.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.0f);
			add.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 4.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 4.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(add);
		}
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_RAVAGER_ROAR,
				SoundCategory.HOSTILE, 2.0f, 0.8f);
		world.spawnParticles(ParticleTypes.LARGE_SMOKE,
				this.getX(), this.getBodyY(0.5), this.getZ(), 40, 2.0, 1.0, 2.0, 0.05);
	}

	/**
	 * One-shot enrage below 25% health: +0.06 movement speed and +6 attack damage with a
	 * roar (hasModifier guards prevent stacking; re-applies after a reload).
	 */
	private void enrage(ServerWorld world) {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_SPEED_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_SPEED_MODIFIER_ID, 0.06, EntityAttributeModifier.Operation.ADD_VALUE));
		}
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(ENRAGE_DAMAGE_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_DAMAGE_MODIFIER_ID, 6.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
		if (!this.enraged) {
			this.enraged = true;
			world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_RAVAGER_ROAR,
					SoundCategory.HOSTILE, 2.0f, 0.6f);
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
