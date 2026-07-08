package net.sonic0810.copperinferno.feature.cinderstone;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.TransparentBlock;

/**
 * {@link TransparentBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it
 * publicly constructible for the cinder/smolder glass blocks.
 */
public class CinderGlassBlock extends TransparentBlock {
	public CinderGlassBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
}
