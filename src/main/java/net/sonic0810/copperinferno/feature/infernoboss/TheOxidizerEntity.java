package net.sonic0810.copperinferno.feature.infernoboss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Boss 1 "The Oxidizer": a giant corroded iron golem (attributes registered in
 * {@link InfernoBossFeature}: MAX_HEALTH 300, ATTACK_DAMAGE 18, SCALE 2.0). Summoned by
 * clicking an {@link OxidizerCoreItem} on any copper block. Every 100 ticks it vents a
 * corrosive verdigris cloud (Slowness + Weakness to players within 6 blocks); below 50%
 * health it enrages with bonus movement speed. Drops an Oxidizer Heart + Verdigris Scales
 * ({@code loot_table/entities/the_oxidizer.json}).
 */
public class TheOxidizerEntity extends IronGolemEntity {
	/** Verdigris green, matching the oxidized copper palette. */
	public static final int OXIDE_COLOR = 0x57A07B;
	private static final DustParticleEffect OXIDE_BURST = new DustParticleEffect(OXIDE_COLOR, 1.0f);
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("oxidizer_enrage");
	private static final int ABILITY_INTERVAL_TICKS = 100;
	private static final double ABILITY_RANGE = 6.0;
	private static final int ABILITY_EFFECT_TICKS = 60;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.the_oxidizer"),
			BossBar.Color.GREEN, BossBar.Style.NOTCHED_10);

	public TheOxidizerEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// Unlike the vanilla golem (only angered on attack), the boss hunts players on sight.
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
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
			ventCorrosiveCloud(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/** Slowness + Weakness to every non-spectator player within 6 blocks + a verdigris burst. */
	private void ventCorrosiveCloud(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(ABILITY_RANGE), player -> !player.isSpectator())) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ABILITY_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, ABILITY_EFFECT_TICKS, 0));
		}
		// Green dust burst (same spawnParticles shape as DrPepperGolemFeature.transform).
		world.spawnParticles(OXIDE_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
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
}
