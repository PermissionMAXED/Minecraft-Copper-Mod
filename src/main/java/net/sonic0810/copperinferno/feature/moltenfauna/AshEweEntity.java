package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

/**
 * Ash Ewe - a gentle ewe with ash-grey fleece, grazing in small flocks. Can be sheared. Behavior is pure vanilla sheep (grazing, shearing, dyeing and breeding are all
 * inherited) except that breeding produces ash ewe babies: vanilla
 * {@code SheepEntity.createChild} hard-codes {@code EntityType.SHEEP} (bytecode-verified),
 * so it is overridden covariantly, mirroring the vanilla parent-color mix via the public
 * {@code DyeColor.mixColors(ServerWorld, DyeColor, DyeColor)} (verified via javap). Drops
 * Ewe Shank ({@code loot_table/entities/ash_ewe.json}).
 */
public class AshEweEntity extends SheepEntity {
	public AshEweEntity(EntityType<? extends SheepEntity> type, World world) {
		super(type, world);
	}

	@Override
	public AshEweEntity createChild(ServerWorld world, PassiveEntity entity) {
		AshEweEntity child = new AshEweEntity(MoltenFaunaFeature.ASH_EWE, world);
		child.setColor(DyeColor.mixColors(world, this.getColor(), ((SheepEntity) entity).getColor()));
		return child;
	}
}
