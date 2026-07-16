package net.sonic0810.copperinferno.feature.moltenfauna;

import java.util.Objects;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

/**
 * Soot Strider - a soot-black strider, quicker on its feet than its kin, skimming the Inferno's lava channels. Behavior is vanilla strider (lava walking, saddling, warped-fungus breeding are all
 * inherited) except that breeding produces soot strider babies (not vanilla striders) and natural
 * spawns never roll the vanilla zombified-piglin/baby-strider jockeys (same proven pattern as
 * the cinder strider). Drops Soot Bristles ({@code loot_table/entities/soot_strider.json}).
 */
public class SootStriderEntity extends StriderEntity {
	public SootStriderEntity(EntityType<? extends StriderEntity> type, World world) {
		super(type, world);
	}

	/**
	 * Vanilla {@code StriderEntity.createChild(ServerWorld, PassiveEntity)} hard-codes
	 * {@code EntityType.STRIDER} (bytecode-verified), so bred soot strider pairs would produce
	 * vanilla striders. Covariant override returning our own type instead.
	 */
	@Override
	public SootStriderEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SootStriderEntity(MoltenFaunaFeature.SOOT_STRIDER, world);
	}

	/**
	 * Reimplements {@code StriderEntity.initialize} minus the jockey rolls.
	 *
	 * <p>DROPPED super behavior (StriderEntity.initialize, 1.21.9 bytecode-verified): for
	 * non-baby spawns, a 1/30 roll that saddles this strider and mounts a zombified piglin
	 * holding a warped fungus on a stick, and a further 1/10 roll that mounts a <em>vanilla</em>
	 * baby strider as a jockey - both would attach vanilla mobs to a soot strider. Only the
	 * fallback branch ({@code entityData = new PassiveData(0.5f)}) is kept.
	 *
	 * <p>Because Java has no {@code super.super}, the adult path below also reimplements the
	 * grandparent chain 1:1 from bytecode: {@code PassiveEntity.initialize} (PassiveData baby
	 * roll + countSpawned) and {@code MobEntity.initialize} (random FOLLOW_RANGE spawn bonus +
	 * 5% left-handedness). {@code AnimalEntity}/{@code PathAwareEntity} do not override
	 * {@code initialize} in 1.21.9.
	 */
	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
			SpawnReason spawnReason, EntityData entityData) {
		if (this.isBaby()) {
			// Vanilla's baby path performs no jockey roll (it delegates straight up the
			// chain), so super is safe here.
			return super.initialize(world, difficulty, spawnReason, entityData);
		}
		PassiveEntity.PassiveData passiveData = new PassiveEntity.PassiveData(0.5f);
		// PassiveEntity.initialize: a fresh PassiveData has spawnedCount == 0, so (as in
		// vanilla striders) the baby roll below never triggers for the first pack member.
		if (passiveData.canSpawnBaby() && passiveData.getSpawnedCount() > 0
				&& world.getRandom().nextFloat() <= passiveData.getBabyChance()) {
			this.setBreedingAge(-24000);
		}
		passiveData.countSpawned();
		// MobEntity.initialize: RANDOM_SPAWN_BONUS_MODIFIER_ID is protected static on
		// MobEntity, so it is directly accessible here.
		Random random = world.getRandom();
		EntityAttributeInstance followRange = Objects.requireNonNull(
				this.getAttributeInstance(EntityAttributes.FOLLOW_RANGE));
		if (!followRange.hasModifier(RANDOM_SPAWN_BONUS_MODIFIER_ID)) {
			followRange.addPersistentModifier(new EntityAttributeModifier(
					RANDOM_SPAWN_BONUS_MODIFIER_ID, random.nextTriangular(0.0, 0.11485),
					EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
		}
		this.setLeftHanded(random.nextFloat() < 0.05f);
		return passiveData;
	}
}
