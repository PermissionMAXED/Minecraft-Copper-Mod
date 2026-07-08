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
import net.minecraft.entity.mob.EvokerEntity;
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
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Boss 6 "The Last Smith": the final master of the doom-forge, an evoker whose spells are
 * hammer blows (attributes registered in {@link BossDoomFeature}: MAX_HEALTH 260,
 * SCALE 1.8). Mechanics:
 * <ul>
 * <li>Forge a copy (timed special): every 160 ticks it hammers out a perfect copy of its
 * target player's main-hand item and wields it (an iron sword when the hand is empty),
 * with anvil clang + crit sparks. The copy never drops (drop chance 0).</li>
 * <li>Gear tempering (multi-phase): at 66% and 33% health (persisted phase counter) each
 * tempering pass hammers +4 attack damage and +4 armor into its own gear, with a one-shot
 * Resistance quench and enchanted-hit sparks.</li>
 * <li>Doom-forge arsenal (inherited ranged specials): the vanilla evoker fang lines,
 * fang circles and vex summons keep pressure between hammer mechanics.</li>
 * </ul>
 * Drops via {@code loot_table/entities/the_last_smith.json}.
 */
public class TheLastSmithEntity extends EvokerEntity {
	private static final String PHASE_KEY = "SmithPhase";
	private static final Identifier TEMPER_DAMAGE_ID = CopperInferno.id("smith_temper_damage");
	private static final Identifier TEMPER_ARMOR_ID = CopperInferno.id("smith_temper_armor");
	private static final int FORGE_INTERVAL_TICKS = 160;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.the_last_smith"),
			BossBar.Color.WHITE, BossBar.Style.PROGRESS);

	/** 0, 1 or 2 completed tempering passes, persisted so a reload never re-triggers them. */
	private int phase;

	public TheLastSmithEntity(EntityType<? extends EvokerEntity> type, World world) {
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
		float healthFraction = this.getHealth() / this.getMaxHealth();
		if (this.phase == 0 && healthFraction < 2.0f / 3.0f) {
			this.phase = 1;
			temperGear(world);
		}
		if (this.phase == 1 && healthFraction < 1.0f / 3.0f) {
			this.phase = 2;
			temperGear(world);
		}
		if (this.phase > 0) {
			applyTemperModifiers();
		}
		if (this.age % FORGE_INTERVAL_TICKS == 0) {
			forgeWeaponCopy(world);
		}
	}

	/**
	 * Hammers a copy of the target player's main-hand item into its own hand (iron sword
	 * for empty hands); the forged copy is cosmetic-and-menacing gear that never drops.
	 */
	private void forgeWeaponCopy(ServerWorld world) {
		LivingEntity target = this.getTarget();
		if (!(target instanceof PlayerEntity player)) {
			return;
		}
		ItemStack original = player.getMainHandStack();
		ItemStack copy = original.isEmpty() ? new ItemStack(Items.IRON_SWORD) : original.copyWithCount(1);
		this.equipStack(EquipmentSlot.MAINHAND, copy);
		this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.0f);
		world.spawnParticles(ParticleTypes.CRIT,
				this.getX(), this.getBodyY(0.6), this.getZ(), 20, 0.4, 0.5, 0.4, 0.1);
		world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ANVIL_USE,
				SoundCategory.HOSTILE, 1.2f, 1.1f);
	}

	/** One tempering pass: quench burst + sparks (the stat gain lives in the modifiers). */
	private void temperGear(ServerWorld world) {
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1));
		world.spawnParticles(ParticleTypes.ENCHANTED_HIT,
				this.getX(), this.getBodyY(0.6), this.getZ(), 30, 0.5, 0.7, 0.5, 0.15);
		world.playSound(null, this.getBlockPos(), SoundEvents.BLOCK_ANVIL_USE,
				SoundCategory.HOSTILE, 1.4f, 0.7f);
	}

	/**
	 * Per-phase tempering: +4 attack damage and +4 armor PER completed pass. Temporary
	 * modifiers are not persisted, so this re-applies every tick after a reload; the value
	 * scales with the persisted phase counter and the modifier is re-added (remove + add)
	 * only when the phase advances.
	 */
	private void applyTemperModifiers() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null) {
			EntityAttributeModifier wanted = new EntityAttributeModifier(
					TEMPER_DAMAGE_ID, 4.0 * this.phase, EntityAttributeModifier.Operation.ADD_VALUE);
			EntityAttributeModifier present = damage.getModifier(TEMPER_DAMAGE_ID);
			if (present == null || present.value() != wanted.value()) {
				damage.removeModifier(TEMPER_DAMAGE_ID);
				damage.addTemporaryModifier(wanted);
			}
		}
		EntityAttributeInstance armor = this.getAttributeInstance(EntityAttributes.ARMOR);
		if (armor != null) {
			EntityAttributeModifier wanted = new EntityAttributeModifier(
					TEMPER_ARMOR_ID, 4.0 * this.phase, EntityAttributeModifier.Operation.ADD_VALUE);
			EntityAttributeModifier present = armor.getModifier(TEMPER_ARMOR_ID);
			if (present == null || present.value() != wanted.value()) {
				armor.removeModifier(TEMPER_ARMOR_ID);
				armor.addTemporaryModifier(wanted);
			}
		}
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putInt(PHASE_KEY, this.phase);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.phase = view.getInt(PHASE_KEY, 0);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
