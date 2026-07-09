package net.sonic0810.copperinferno.core.content;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.TransparentBlock;

/**
 * {@link TransparentBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it
 * publicly constructible for the v4 palette/alloy glass blocks (same pattern as the cinderstone
 * feature's CinderGlassBlock).
 */
public class ContentGlassBlock extends TransparentBlock {
	public ContentGlassBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
}
