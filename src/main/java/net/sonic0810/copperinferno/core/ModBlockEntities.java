package net.sonic0810.copperinferno.core;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Block entity type registration helper. BlockEntityType fields live in the individual feature classes.
 */
public final class ModBlockEntities {
	private ModBlockEntities() {
	}

	/**
	 * Registers a block entity type under {@code copper_inferno:<path>} for the given blocks.
	 */
	public static <T extends BlockEntity> BlockEntityType<T> register(String path, FabricBlockEntityTypeBuilder.Factory<? extends T> factory, Block... blocks) {
		BlockEntityType<T> type = FabricBlockEntityTypeBuilder.<T>create(factory, blocks).build();
		return Registry.register(Registries.BLOCK_ENTITY_TYPE, CopperInferno.id(path), type);
	}
}
