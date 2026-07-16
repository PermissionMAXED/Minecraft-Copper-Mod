package net.sonic0810.copperinferno.feature.moltenfauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;

/**
 * Soot Wether - a heavy, even-tempered wether carrying a thick soot-dark fleece. Behavior is pure vanilla sheep (grazing, shearing, dyeing and breeding are all
 * inherited) except that breeding produces soot wether babies: vanilla
 * {@code SheepEntity.createChild} hard-codes {@code EntityType.SHEEP} (bytecode-verified),
 * so it is overridden covariantly, mirroring the vanilla parent-color mix via the public
 * {@code DyeColor.mixColors(ServerWorld, DyeColor, DyeColor)} (verified via javap). Drops
 * Wether Ribs ({@code loot_table/entities/soot_wether.json}).
 */
public class SootWetherEntity extends SheepEntity {
	public SootWetherEntity(EntityType<? extends SheepEntity> type, World world) {
		super(type, world);
	}

	@Override
	public SootWetherEntity createChild(ServerWorld world, PassiveEntity entity) {
		SootWetherEntity child = new SootWetherEntity(MoltenFaunaFeature.SOOT_WETHER, world);
		child.setColor(DyeColor.mixColors(world, this.getColor(), ((SheepEntity) entity).getColor()));
		return child;
	}
}
