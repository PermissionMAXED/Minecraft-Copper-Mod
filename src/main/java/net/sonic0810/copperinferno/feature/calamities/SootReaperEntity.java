package net.sonic0810.copperinferno.feature.calamities;

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
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 6 "Soot Reaper": a spectral wither harvester (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 300, ATTACK_DAMAGE 10, SCALE 1.8). Summoned with a
 * Reaper Knell. Its scythe strikes blind and wither the victim (tryAttack hook on top of
 * the vanilla wither-skeleton wither); below 50% health it enrages with bonus movement
 * speed. Drops per {@code loot_table/entities/soot_reaper.json}.
 */
public class SootReaperEntity extends WitherSkeletonEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("reaper_enrage");
	private static final int ON_HIT_EFFECT_TICKS = 60;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.soot_reaper"),
			BossBar.Color.WHITE, BossBar.Style.PROGRESS);

	public SootReaperEntity(EntityType<? extends WitherSkeletonEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Summoned bosses skip initialize(); hand over the vanilla stone sword so the
			// skeleton's melee goal engages (updateAttackType reads the held stack).
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			this.updateAttackType();
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

	/** Scythe strike: Blindness + Wither on every landed hit. */
	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		if (!super.tryAttack(world, target)) {
			return false;
		}
		if (target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ON_HIT_EFFECT_TICKS, 0));
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, ON_HIT_EFFECT_TICKS, 0));
		}
		return true;
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/**
	 * One-shot +0.06 flat movement speed below 50% health. Temporary modifiers are not
	 * persisted, so after a reload this simply re-applies on the next tick; the
	 * {@code hasModifier} guard keeps it from stacking.
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
