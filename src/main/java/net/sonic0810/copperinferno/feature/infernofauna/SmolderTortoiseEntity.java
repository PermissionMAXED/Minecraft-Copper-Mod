package net.sonic0810.copperinferno.feature.infernofauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.world.World;

/**
 * A tortoise whose shell smolders like a banked hearth. Tweak: it is immovable — the default
 * attributes registered in {@link InfernoFaunaFeature} pin KNOCKBACK_RESISTANCE to 1.0 (the
 * attribute-tweak pattern proven by the infernomobs Ember Wraith), so nothing shoves it off its
 * warming stone. Turtle AI (egg laying, seeking water) is inherited; eggs hatch vanilla turtles
 * (the egg block hard-codes the vanilla type), accepted and documented here. Drops Smolder
 * Scutes ({@code loot_table/entities/smolder_tortoise.json}).
 */
public class SmolderTortoiseEntity extends TurtleEntity {
	public SmolderTortoiseEntity(EntityType<? extends TurtleEntity> type, World world) {
		super(type, world);
	}
}
