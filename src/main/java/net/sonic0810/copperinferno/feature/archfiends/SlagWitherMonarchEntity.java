package net.sonic0810.copperinferno.feature.archfiends;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Archfiend 9 "Slag Wither Monarch": a slag-armored wither skeleton (MAX_HEALTH
 * 280, SCALE 2.2) armed with a stone sword (equipStack + zero drop chance, both
 * verified via javap; AbstractSkeletonEntity.onEquipStack picks the melee goal).
 * Every 120 ticks its withering aura applies Wither + Slowness to survival players
 * it can see within 6 blocks; below 50% health it enrages with the TheOxidizer
 * speed-modifier pattern.
 * Attributes are registered in {@link ArchfiendsFeature}; drops come from
 * {@code loot_table/entities/slag_wither_monarch.json}.
 */
public class SlagWitherMonarchEntity extends WitherSkeletonEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("slag_wither_monarch_enrage");
	private static final int ABILITY_INTERVAL_TICKS = 120;
	private static final double ABILITY_RANGE = 6.0;
	private static final int ABILITY_EFFECT_TICKS = 60;
	private static final DustParticleEffect AURA_BURST = new DustParticleEffect(0x39443A, 1.0f);

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.slag_wither_monarch"),
			BossBar.Color.GREEN, BossBar.Style.NOTCHED_10);

	public SlagWitherMonarchEntity(EntityType<? extends WitherSkeletonEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Summon items / EntityType.create skip initialize(), so the boss arms itself here;
			// saved equipment simply re-applies over this on load. Never dropped on death.
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
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
		if (this.age % ABILITY_INTERVAL_TICKS == 0) {
			pulseAura(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/**
	 * Effects hit every survival/adventure player the boss has line of sight to
	 * within 6 blocks (TheOxidizer ventCorrosiveCloud pattern); creative and
	 * spectator players and players behind walls are unaffected.
	 */
	private void pulseAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(ABILITY_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, ABILITY_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ABILITY_EFFECT_TICKS, 0));
		}
		world.spawnParticles(AURA_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
	}

	/**
	 * One-shot +0.05 flat movement speed below 50% health (TheOxidizer
	 * enrage pattern). Temporary modifiers are not persisted, so after a reload
	 * this simply re-applies on the next tick; the {@code hasModifier} guard
	 * keeps it from stacking.
	 */
	private void enrage() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_MODIFIER_ID, 0.05, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
