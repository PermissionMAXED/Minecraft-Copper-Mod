package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 9 "Furnace Fiend": an axe-swinging fiend from the furnace depths (attributes
 * in {@link CalamitiesFeature}: MAX_HEALTH 320, SCALE 1.8; vanilla vindicator attack
 * damage). Summoned with a Fiend Ember Key. Every landed axe blow sets the victim on fire
 * (tryAttack hook); below 50% health it enrages with bonus movement speed. Drops per
 * {@code loot_table/entities/furnace_fiend.json}.
 */
public class FurnaceFiendEntity extends VindicatorEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("fiend_enrage");

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.furnace_fiend"),
			BossBar.Color.YELLOW, BossBar.Style.NOTCHED_6);

	public FurnaceFiendEntity(EntityType<? extends VindicatorEntity> type, World world) {
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

	/** Furnace-hot axe: every landed hit sets the victim on fire for 4 seconds. */
	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		if (!super.tryAttack(world, target)) {
			return false;
		}
		target.setOnFireFor(4.0f);
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
