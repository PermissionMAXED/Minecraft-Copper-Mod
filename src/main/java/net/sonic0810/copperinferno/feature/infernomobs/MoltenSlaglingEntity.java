package net.sonic0810.copperinferno.feature.infernomobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.world.World;

/**
 * A molten slag cube. Behavior is pure vanilla magma cube, including the vanilla size handling:
 * {@code SlimeEntity.initialize(...)} rolls a random size (1/2/4) on natural spawn and
 * {@code setSize(int, boolean)} (public in 1.21.9, verified via javap) rescales health/damage, and
 * cubes split into smaller ones on death. Drops Slagling Cores
 * ({@code loot_table/entities/molten_slagling.json}).
 */
public class MoltenSlaglingEntity extends MagmaCubeEntity {
	public MoltenSlaglingEntity(EntityType<? extends MagmaCubeEntity> type, World world) {
		super(type, world);
	}
}
