package net.sonic0810.copperinferno.core.content;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.PressurePlateBlock;

/**
 * {@link PressurePlateBlock}'s constructor is protected in 1.21.9; this trivial subclass makes it
 * publicly constructible for the v4 wood-set pressure plates (same pattern as the utilityblocks
 * feature's PublicPressurePlateBlock).
 */
public class ContentPressurePlateBlock extends PressurePlateBlock {
	public ContentPressurePlateBlock(BlockSetType blockSetType, AbstractBlock.Settings settings) {
		super(blockSetType, settings);
	}
}
