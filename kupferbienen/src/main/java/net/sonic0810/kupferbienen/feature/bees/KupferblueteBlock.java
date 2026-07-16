package net.sonic0810.kupferbienen.feature.bees;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.PlantBlock;

/**
 * The Kupferbluete flower block. {@link PlantBlock} (only {@code getCodec()} is abstract)
 * gives it proper plant behavior: it can only be placed on {@code #minecraft:dirt} or
 * farmland (default {@code canPlantOnTop}) and pops with drops when its support block is
 * removed — a plain {@code Block} floated in mid-air.
 */
public class KupferblueteBlock extends PlantBlock {
	public static final MapCodec<KupferblueteBlock> CODEC = createCodec(KupferblueteBlock::new);

	public KupferblueteBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected MapCodec<? extends KupferblueteBlock> getCodec() {
		return CODEC;
	}
}
