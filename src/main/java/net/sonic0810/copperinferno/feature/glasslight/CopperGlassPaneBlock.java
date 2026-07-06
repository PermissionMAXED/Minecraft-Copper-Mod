package net.sonic0810.copperinferno.feature.glasslight;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.PaneBlock;

/**
 * {@link PaneBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it publicly
 * constructible for the copper glass panes.
 */
public class CopperGlassPaneBlock extends PaneBlock {
	public CopperGlassPaneBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
}
