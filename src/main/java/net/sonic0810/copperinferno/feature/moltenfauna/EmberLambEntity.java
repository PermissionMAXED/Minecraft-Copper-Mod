package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

/**
 * Ember Lamb - a small, skittish lamb whose fleece sparks faintly when it bolts. Behavior is pure vanilla sheep (grazing, shearing, dyeing and breeding are all
 * inherited) except that breeding produces ember lamb babies: vanilla
 * {@code SheepEntity.createChild} hard-codes {@code EntityType.SHEEP} (bytecode-verified),
 * so it is overridden covariantly, mirroring the vanilla parent-color mix via the public
 * {@code DyeColor.mixColors(ServerWorld, DyeColor, DyeColor)} (verified via javap). Drops
 * Lamb Cutlet ({@code loot_table/entities/ember_lamb.json}).
 */
public class EmberLambEntity extends SheepEntity {
	public EmberLambEntity(EntityType<? extends SheepEntity> type, World world) {
		super(type, world);
	}

	@Override
	public EmberLambEntity createChild(ServerWorld world, PassiveEntity entity) {
		EmberLambEntity child = new EmberLambEntity(MoltenFaunaFeature.EMBER_LAMB, world);
		child.setColor(DyeColor.mixColors(world, this.getColor(), ((SheepEntity) entity).getColor()));
		return child;
	}
}
