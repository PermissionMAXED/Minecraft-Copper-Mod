package net.sonic0810.copperinferno.feature.archfiends;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Archfiend 3 "Molten Hoglin Tyrant": a magma-clad hoglin (MAX_HEALTH 320,
 * ATTACK_DAMAGE 16, SCALE 2.2), zombification-proof via the public
 * HoglinEntity.setImmuneToZombification (verified via javap). Brain AI is inherited
 * untouched; every 100 ticks its molten stomp ignites (60 fire ticks) and slows
 * survival players it can see within 5 blocks; below 50% health it enrages with the
 * TheOxidizer speed-modifier pattern.
 * Attributes are registered in {@link ArchfiendsFeature}; drops come from
 * {@code loot_table/entities/molten_hoglin_tyrant.json}.
 */
public class MoltenHoglinTyrantEntity extends HoglinEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("molten_hoglin_tyrant_enrage");
	private static final int ABILITY_INTERVAL_TICKS = 100;
	private static final double ABILITY_RANGE = 5.0;
	private static final int ABILITY_EFFECT_TICKS = 60;
	private static final int ABILITY_FIRE_TICKS = 60;
	private static final DustParticleEffect AURA_BURST = new DustParticleEffect(0xE25822, 1.0f);

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.molten_hoglin_tyrant"),
			BossBar.Color.RED, BossBar.Style.NOTCHED_6);

	public MoltenHoglinTyrantEntity(EntityType<? extends HoglinEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Public in 1.21.9 (verified via javap); an archfiend never zombifies in the Overworld.
			this.setImmuneToZombification(true);
		}
	}

	/**
	 * Blocks the inherited hoglin feeding/breeding interaction: without this,
	 * players could pacify or breed the boss mid-fight (TheOxidizer interactMob
	 * pattern; HoglinEntity.interactMob is public, verified via javap).
	 */
	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		return ActionResult.PASS;
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
	 * within 5 blocks (TheOxidizer ventCorrosiveCloud pattern); creative and
	 * spectator players and players behind walls are unaffected.
	 */
	private void pulseAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(ABILITY_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ABILITY_EFFECT_TICKS, 0));
			player.setOnFireForTicks(ABILITY_FIRE_TICKS);
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
