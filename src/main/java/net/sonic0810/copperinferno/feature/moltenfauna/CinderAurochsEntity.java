package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * Cinder Aurochs - a primeval wild ox with cinder-crusted horns, the largest grazer of the Inferno. Behavior is pure vanilla cow (breeding, variants and ambient behavior are
 * all inherited) except that breeding produces cinder aurochs babies: vanilla
 * {@code CowEntity.createChild} hard-codes {@code EntityType.COW}
 * (bytecode-verified), so it is overridden covariantly to return our own type. Drops
 * Aurochs Shank ({@code loot_table/entities/cinder_aurochs.json}).
 */
public class CinderAurochsEntity extends CowEntity {
	public CinderAurochsEntity(EntityType<? extends CowEntity> type, World world) {
		super(type, world);
	}

	@Override
	public CinderAurochsEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new CinderAurochsEntity(MoltenFaunaFeature.CINDER_AUROCHS, world);
	}
}
