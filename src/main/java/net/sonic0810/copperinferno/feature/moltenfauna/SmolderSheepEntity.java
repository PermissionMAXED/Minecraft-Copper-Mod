package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

/**
 * Smolder Sheep - a sheep whose fleece smolders without ever burning away. Can be sheared. Behavior is pure vanilla sheep (grazing, shearing, dyeing and breeding are all
 * inherited) except that breeding produces smolder sheep babies: vanilla
 * {@code SheepEntity.createChild} hard-codes {@code EntityType.SHEEP} (bytecode-verified),
 * so it is overridden covariantly, mirroring the vanilla parent-color mix via the public
 * {@code DyeColor.mixColors(ServerWorld, DyeColor, DyeColor)} (verified via javap). Drops
 * Smolder Fleece ({@code loot_table/entities/smolder_sheep.json}).
 */
public class SmolderSheepEntity extends SheepEntity {
	public SmolderSheepEntity(EntityType<? extends SheepEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SmolderSheepEntity createChild(ServerWorld world, PassiveEntity entity) {
		SmolderSheepEntity child = new SmolderSheepEntity(MoltenFaunaFeature.SMOLDER_SHEEP, world);
		child.setColor(DyeColor.mixColors(world, this.getColor(), ((SheepEntity) entity).getColor()));
		return child;
	}
}
