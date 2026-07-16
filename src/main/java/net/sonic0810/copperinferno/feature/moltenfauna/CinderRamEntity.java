package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

/**
 * Cinder Ram - a headstrong ram patrolling the flock's edge with cinder-dark curls. Behavior is pure vanilla sheep (grazing, shearing, dyeing and breeding are all
 * inherited) except that breeding produces cinder ram babies: vanilla
 * {@code SheepEntity.createChild} hard-codes {@code EntityType.SHEEP} (bytecode-verified),
 * so it is overridden covariantly, mirroring the vanilla parent-color mix via the public
 * {@code DyeColor.mixColors(ServerWorld, DyeColor, DyeColor)} (verified via javap). Drops
 * Ram Rack ({@code loot_table/entities/cinder_ram.json}).
 */
public class CinderRamEntity extends SheepEntity {
	public CinderRamEntity(EntityType<? extends SheepEntity> type, World world) {
		super(type, world);
	}

	@Override
	public CinderRamEntity createChild(ServerWorld world, PassiveEntity entity) {
		CinderRamEntity child = new CinderRamEntity(MoltenFaunaFeature.CINDER_RAM, world);
		child.setColor(DyeColor.mixColors(world, this.getColor(), ((SheepEntity) entity).getColor()));
		return child;
	}
}
