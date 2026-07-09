package net.sonic0810.copperinferno.feature.bosspantheon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Pantheon boss 6 "Gilded Executioner": a gold-plated headsman vindicator (attributes in
 * {@link BossPantheonFeature}: MAX_HEALTH 300, ATTACK_DAMAGE 14, SCALE 2.0). Always wields
 * a golden axe (equipped in the constructor with zero drop chance, so summon method never
 * matters). Three mechanics on top of vanilla vindicator AI: a timed gold-rush dash
 * (launches itself at any target further than 4 blocks with a gold-dust trail + sweep
 * sound, plus 3 seconds of Speed), a disarm strike (every axe hit has a 25% chance to
 * knock the victim's main-hand item to the ground - creative players exempt - with an
 * item-break crack), and a phase 2 below 50% health (persisted flag: one-shot +4 attack
 * damage / +0.04 speed enrage, dash cadence tightens from 140 to 90 ticks, gold-dust
 * eruption on entry). Drops Gilded Scraps, Executioner Pendants and the Gilded Greataxe
 * ({@code loot_table/entities/gilded_executioner.json}).
 */
public class GildedExecutionerEntity extends VindicatorEntity {
	/** Gold dust, matching the gilded palette. */
	private static final DustParticleEffect GOLD_DUST = new DustParticleEffect(0xF6C12B, 1.2f);
	private static final Identifier ENRAGE_DAMAGE_MODIFIER_ID = CopperInferno.id("executioner_enrage_damage");
	private static final Identifier ENRAGE_SPEED_MODIFIER_ID = CopperInferno.id("executioner_enrage_speed");
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int DASH_INTERVAL_TICKS = 140;
	private static final int DASH_INTERVAL_PHASE_TWO_TICKS = 90;
	private static final double DASH_MIN_RANGE = 4.0;
	private static final float DISARM_CHANCE = 0.25f;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.gilded_executioner"),
			BossBar.Color.PURPLE, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 executioner keeps the dash cadence. */
	private boolean phaseTwo;

	public GildedExecutionerEntity(EntityType<? extends VindicatorEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon). The
		// golden axe is equipped here (not in initialize()) so EVERY summon path arms it;
		// drop chance zero - the trophy axe comes from the loot table instead.
		if (!world.isClient()) {
			this.setPersistent();
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
			this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.0f);
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
			world.spawnParticles(GOLD_DUST,
					this.getX(), this.getBodyY(0.5), this.getZ(), 50, 0.6, 0.9, 0.6, 0.1);
			world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_VINDICATOR_CELEBRATE,
					SoundCategory.HOSTILE, 1.5f, 0.7f);
		}
		if (this.phaseTwo) {
			enrage();
		}
		LivingEntity target = this.getTarget();
		int interval = this.phaseTwo ? DASH_INTERVAL_PHASE_TWO_TICKS : DASH_INTERVAL_TICKS;
		if (target != null && this.age % interval == 0
				&& this.squaredDistanceTo(target) > DASH_MIN_RANGE * DASH_MIN_RANGE) {
			goldRushDash(world, target);
		}
	}

	/**
	 * Gold-rush dash: hurls itself at the target (LeapAtTargetGoal-style setVelocity,
	 * server-authoritative for mobs) with 3 seconds of Speed, a gold-dust trail and a
	 * sweeping-attack whoosh.
	 */
	private void goldRushDash(ServerWorld world, LivingEntity target) {
		Vec3d dash = new Vec3d(target.getX() - this.getX(), 0.0, target.getZ() - this.getZ())
				.normalize();
		this.setVelocity(dash.getX() * 1.2, 0.35, dash.getZ() * 1.2);
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 1));
		world.spawnParticles(GOLD_DUST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 25, 0.4, 0.6, 0.4, 0.1);
		world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP,
				SoundCategory.HOSTILE, 1.5f, 0.7f);
	}

	/**
	 * Disarm strike: 25% of successful axe hits knock the victim's main-hand item to the
	 * ground (setStackInHand syncs for ServerPlayerEntity too; Entity.dropStack verified
	 * via javap). Creative players are never disarmed. ENTITY_ITEM_BREAK is a
	 * RegistryEntry in 1.21.9, so the coordinate playSound overload is used.
	 */
	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean hit = super.tryAttack(world, target);
		if (hit && target instanceof LivingEntity living
				&& !(living instanceof PlayerEntity player && player.isCreative())
				&& this.random.nextFloat() < DISARM_CHANCE) {
			ItemStack held = living.getMainHandStack();
			if (!held.isEmpty()) {
				living.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
				living.dropStack(world, held);
				world.spawnParticles(GOLD_DUST,
						living.getX(), living.getBodyY(0.5), living.getZ(), 10, 0.3, 0.4, 0.3, 0.1);
				world.playSound(null, living.getX(), living.getY(), living.getZ(),
						SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.HOSTILE, 1.0f, 0.8f);
			}
		}
		return hit;
	}

	/**
	 * One-shot +4 attack damage and +0.04 movement speed below 50% health. Temporary
	 * modifiers are not persisted, so after a reload this re-applies on the next tick; the
	 * {@code hasModifier} guards keep it from stacking (enrage() pattern from the
	 * infernoboss Oxidizer).
	 */
	private void enrage() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(ENRAGE_DAMAGE_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_DAMAGE_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_SPEED_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_SPEED_MODIFIER_ID, 0.04, EntityAttributeModifier.Operation.ADD_VALUE));
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
