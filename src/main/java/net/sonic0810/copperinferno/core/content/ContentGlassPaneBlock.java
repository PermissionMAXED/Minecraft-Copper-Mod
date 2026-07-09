package net.sonic0810.copperinferno.core.content;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.PaneBlock;

/**
 * {@link PaneBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it publicly
 * constructible for the v4 palette/alloy glass panes (same pattern as the cinderstone feature's
 * CinderGlassPaneBlock).
 */
public class ContentGlassPaneBlock extends PaneBlock {
	public ContentGlassPaneBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
}
