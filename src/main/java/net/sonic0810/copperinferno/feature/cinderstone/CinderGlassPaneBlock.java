package net.sonic0810.copperinferno.feature.cinderstone;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.PaneBlock;

/**
 * {@link PaneBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it publicly
 * constructible for the cinder/smolder glass panes.
 */
public class CinderGlassPaneBlock extends PaneBlock {
	public CinderGlassPaneBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
}
