package net.sonic0810.copperinferno.feature.archfiends;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinBruteEntity;
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
 * Archfiend 7 "Cinder Brute Warlord": a piglin brute archfiend (MAX_HEALTH 300,
 * ATTACK_DAMAGE 18, SCALE 2.0), zombification-proof via the public
 * AbstractPiglinEntity.setImmuneToZombification and armed with a golden axe (both
 * verified via javap). Brain AI is inherited untouched; every 100 ticks its war cry
 * grants itself Speed and applies Weakness to survival players it can see within 6
 * blocks; below 50% health its axe arm gains a one-shot +4 attack-damage modifier.
 * Attributes are registered in {@link ArchfiendsFeature}; drops come from
 * {@code loot_table/entities/cinder_brute_warlord.json}.
 */
public class CinderBruteWarlordEntity extends PiglinBruteEntity {
	private static final Identifier MELEE_BOOST_MODIFIER_ID = CopperInferno.id("cinder_brute_warlord_melee");
	private static final int ABILITY_INTERVAL_TICKS = 100;
	private static final double ABILITY_RANGE = 6.0;
	private static final int ABILITY_EFFECT_TICKS = 80;
	private static final DustParticleEffect AURA_BURST = new DustParticleEffect(0xF6C12B, 1.0f);

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.cinder_brute_warlord"),
			BossBar.Color.YELLOW, BossBar.Style.NOTCHED_6);

	public CinderBruteWarlordEntity(EntityType<? extends PiglinBruteEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Public in 1.21.9 (verified via javap); an archfiend never zombifies in the Overworld.
			this.setImmuneToZombification(true);
			// Summon items / EntityType.create skip initialize(), so the boss arms itself here;
			// saved equipment simply re-applies over this on load. Never dropped on death.
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
		if (this.age % ABILITY_INTERVAL_TICKS == 0) {
			pulseAura(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			applyMeleeBoost();
		}
	}

	/**
	 * Effects hit every survival/adventure player the boss has line of sight to
	 * within 6 blocks (TheOxidizer ventCorrosiveCloud pattern); creative and
	 * spectator players and players behind walls are unaffected.
	 */
	private void pulseAura(ServerWorld world) {
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, ABILITY_EFFECT_TICKS, 0));
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(ABILITY_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, ABILITY_EFFECT_TICKS, 0));
		}
		world.spawnParticles(AURA_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
	}

	/**
	 * One-shot +4 flat attack damage below 50% health (InfernoTitan
	 * applyMeleeBoost pattern); the {@code hasModifier} guard keeps it from
	 * stacking across reloads.
	 */
	private void applyMeleeBoost() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(MELEE_BOOST_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					MELEE_BOOST_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
