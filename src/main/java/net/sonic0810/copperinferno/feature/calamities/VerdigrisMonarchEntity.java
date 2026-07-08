package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 4 "Verdigris Monarch": a regal, patina-crowned golem (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 550, ATTACK_DAMAGE 18, SCALE 2.2). Summoned with
 * the Verdigris Regalia. Hunts players on sight; every 100 ticks it saps every survival
 * player it can see within 6 blocks with Weakness + Mining Fatigue; below 50% health it
 * periodically grants itself Regeneration. Drops per
 * {@code loot_table/entities/verdigris_monarch.json}.
 */
public class VerdigrisMonarchEntity extends IronGolemEntity {
	/** Verdigris green, matching the oxidized copper palette. */
	private static final DustParticleEffect PATINA_BURST = new DustParticleEffect(0x57A07B, 1.0f);
	private static final int AURA_INTERVAL_TICKS = 100;
	private static final double AURA_RANGE = 6.0;
	private static final int AURA_EFFECT_TICKS = 80;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.verdigris_monarch"),
			BossBar.Color.GREEN, BossBar.Style.PROGRESS);

	public VerdigrisMonarchEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// Unlike the vanilla golem (only angered on attack), the boss hunts players on sight.
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	/**
	 * Blocks the inherited iron-golem right-click repair: without this, players could heal
	 * the boss mid-fight with iron ingots (TheOxidizerEntity pattern).
	 */
	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
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
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			ventSappingAura(world);
			if (this.getHealth() < this.getMaxHealth() * 0.5f) {
				// Royal mending: periodic self-Regeneration once wounded below half.
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1));
			}
		}
	}

	/**
	 * Weakness + Mining Fatigue to every survival/adventure player the boss has line of
	 * sight to within 6 blocks, plus a verdigris dust burst; creative and spectator players
	 * and players behind walls are unaffected.
	 */
	private void ventSappingAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, AURA_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, AURA_EFFECT_TICKS, 0));
		}
		world.spawnParticles(PATINA_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
