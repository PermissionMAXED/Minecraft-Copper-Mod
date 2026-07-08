package net.sonic0810.copperinferno.feature.handbook;

import java.util.function.Consumer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * The COPPER INFERNO Handbook. Right-click opens the client-side handbook screen via the
 * {@link HandbookFeature#handbookScreenOpener} hook (mirrors the statue feature's
 * {@code statueScreenOpener} pattern so no client class leaks into common code).
 */
public class HandbookItem extends Item {
	public HandbookItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		if (world.isClient()) {
			// The client feature installs this hook; it opens the handbook screen.
			Consumer<PlayerEntity> opener = HandbookFeature.handbookScreenOpener;
			if (opener != null) {
				opener.accept(user);
			}
		}
		return ActionResult.SUCCESS;
	}
}
