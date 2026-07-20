package net.sonic0810.copperinferno.feature.constructs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * A scruffy scavenger bird riveted together from scrapyard offcuts. Behavior is vanilla parrot
 * plus ONE tweak: it is completely untameable (no seed taming, no cookie, no shoulder rides) -
 * see {@link #interactMob(PlayerEntity, Hand)}. Drops raw copper it has hoarded
 * ({@code loot_table/entities/scrap_vulture.json}).
 */
public class ScrapVultureEntity extends ParrotEntity {
	public ScrapVultureEntity(EntityType<? extends ParrotEntity> type, World world) {
		super(type, world);
	}

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		// TWEAK: untameable scrapper. interactMob(PlayerEntity, Hand) -> ActionResult
		// verified via javap; PASS skips the whole vanilla taming/cookie/sit interaction.
		return ActionResult.PASS;
	}
}
