package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 1 "Emberlord Ravager": a colossal war beast wreathed in embers (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 400, ATTACK_DAMAGE 16, SCALE 2.0). Summoned with an
 * Emberlord Warhorn. Every 80 ticks it ignites every survival player it can see within 5
 * blocks; below 50% health it enrages with bonus movement speed (TheOxidizerEntity
 * pattern). Drops per {@code loot_table/entities/emberlord_ravager.json}.
 */
public class EmberlordRavagerEntity extends RavagerEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("emberlord_enrage");
	private static final int AURA_INTERVAL_TICKS = 80;
	private static final double AURA_RANGE = 5.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.emberlord_ravager"),
			BossBar.Color.RED, BossBar.Style.NOTCHED_10);

	public EmberlordRavagerEntity(EntityType<? extends RavagerEntity> type, World world) {
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
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			ventEmberAura(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/**
	 * Ignites every survival/adventure player the boss has line of sight to within 5 blocks
	 * (TheOxidizerEntity.ventCorrosiveCloud pattern); creative and spectator players and
	 * players behind walls are unaffected.
	 */
	private void ventEmberAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.setOnFireFor(4.0f);
		}
		world.spawnParticles(ParticleTypes.FLAME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.4, 0.5, 0.4, 0.02);
	}

	/**
	 * One-shot +0.05 flat movement speed below 50% health. Temporary modifiers are not
	 * persisted, so after a reload this simply re-applies on the next tick; the
	 * {@code hasModifier} guard keeps it from stacking.
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
