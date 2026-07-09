package net.sonic0810.copperinferno.core.content;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.GrateBlock;

/**
 * {@link GrateBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it publicly
 * constructible for the v4 alloy grates (vanilla copper-grate behavior: transparent +
 * waterloggable).
 */
public class ContentGrateBlock extends GrateBlock {
	public ContentGrateBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
}
