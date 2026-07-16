package net.sonic0810.copperinferno.feature.calamities;

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
import net.minecraft.particle.ParticleTypes;
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
 * Calamity 3 "Molten Colossus": a magma-veined iron colossus (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 600, ATTACK_DAMAGE 22, SCALE 2.5). Summoned with a
 * Colossus Effigy. Hunts players on sight (unlike the vanilla golem); every 100 ticks it
 * vents a molten wave that ignites and slows every survival player it can see within 6
 * blocks; below 50% health its fists gain +8 damage (TheOxidizerEntity pattern). Drops per
 * {@code loot_table/entities/molten_colossus.json}.
 */
public class MoltenColossusEntity extends IronGolemEntity {
	private static final Identifier MOLTEN_FURY_MODIFIER_ID = CopperInferno.id("colossus_molten_fury");
	private static final int AURA_INTERVAL_TICKS = 100;
	private static final double AURA_RANGE = 6.0;
	private static final int AURA_EFFECT_TICKS = 60;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.molten_colossus"),
			BossBar.Color.RED, BossBar.Style.NOTCHED_10);

	public MoltenColossusEntity(EntityType<? extends IronGolemEntity> type, World world) {
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
			ventMoltenWave(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/**
	 * Ignites + slows every survival/adventure player the boss has line of sight to within
	 * 6 blocks; creative and spectator players and players behind walls are unaffected.
	 */
	private void ventMoltenWave(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.setOnFireFor(3.0f);
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, AURA_EFFECT_TICKS, 0));
		}
		world.spawnParticles(ParticleTypes.LAVA,
				this.getX(), this.getBodyY(0.5), this.getZ(), 12, 0.5, 0.6, 0.5, 0.0);
	}

	/** One-shot +8 flat attack damage below 50% health; hasModifier guard prevents stacking. */
	private void enrage() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(MOLTEN_FURY_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					MOLTEN_FURY_MODIFIER_ID, 8.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
