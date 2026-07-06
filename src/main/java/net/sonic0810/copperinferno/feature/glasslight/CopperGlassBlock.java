package net.sonic0810.copperinferno.feature.glasslight;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.TransparentBlock;

/**
 * {@link TransparentBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it
 * publicly constructible for the copper glass blocks.
 */
public class CopperGlassBlock extends TransparentBlock {
	public CopperGlassBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
}
