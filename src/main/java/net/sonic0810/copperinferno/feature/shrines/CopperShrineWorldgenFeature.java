package net.sonic0810.copperinferno.feature.shrines;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.LootableInventory;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Copper Shrine: a small Overworld shrine built from existing blocks — a 5x5
 * stone-brick/copper dais, four copper-pillar columns crowned with ember lanterns and a
 * center chest rolling the {@code copper_inferno:chests/copper_shrine} loot table.
 * Registered as a real {@code Feature<DefaultFeatureConfig>} in {@code Registries.FEATURE}
 * (see {@link ShrinesFeature}) and referenced by
 * {@code data/copper_inferno/worldgen/configured_feature/copper_shrine.json}
 * ({@code "type": "copper_inferno:copper_shrine", "config": {}}); structure copied from
 * {@code feature.wildworld.RuinedForgeWorldgenFeature}. Signatures verified via javap:
 * {@code Feature(Codec)}, {@code generate(FeatureContext)},
 * {@code FeatureContext#getWorld/getRandom/getOrigin},
 * {@code LootableInventory.setLootTable(BlockView, Random, BlockPos, RegistryKey)}.
 *
 * <p>Mod blocks are resolved from {@code Registries.BLOCK} lazily inside {@link #generate}
 * (worldgen runs long after every feature's init), never at class load, so feature-init
 * order does not matter; a missing id fails fast instead of silently building with air.
 */
public class CopperShrineWorldgenFeature extends Feature<DefaultFeatureConfig> {
	private static final RegistryKey<LootTable> CHEST_LOOT =
			RegistryKey.of(RegistryKeys.LOOT_TABLE, CopperInferno.id("chests/copper_shrine"));

	public CopperShrineWorldgenFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	/** Default state of a mod block by id; throws when unregistered (never builds with air). */
	private static BlockState modState(String id) {
		Block block = Registries.BLOCK.get(CopperInferno.id(id));
		if (block == Blocks.AIR) {
			throw new IllegalStateException("copper_shrine references unregistered block copper_inferno:" + id);
		}
		return block.getDefaultState();
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		Random random = context.getRandom();

		// The heightmap placement may land on foliage or a steep face: first rise out of
		// solid blocks, then settle down onto the floor (same as RuinedForgeWorldgenFeature).
		BlockPos.Mutable cursor = context.getOrigin().mutableCopy();
		int rise = 0;
		while (!world.isAir(cursor) && rise++ < 16 && cursor.getY() < world.getTopYInclusive() - 8) {
			cursor.move(Direction.UP);
		}
		int fall = 0;
		while (world.isAir(cursor.down()) && fall++ < 32 && cursor.getY() > world.getBottomY() + 4) {
			cursor.move(Direction.DOWN);
		}
		if (!world.isAir(cursor) || !world.getBlockState(cursor.down()).isOpaqueFullCube()) {
			return false;
		}
		BlockPos base = cursor.toImmutable();

		BlockState daisMain = Blocks.STONE_BRICKS.getDefaultState();
		BlockState daisWorn = Blocks.CRACKED_STONE_BRICKS.getDefaultState();
		BlockState daisCenter = Blocks.CHISELED_STONE_BRICKS.getDefaultState();
		BlockState daisCorner = modState("copper_bricks");
		BlockState pillar = modState("copper_pillar");
		BlockState lantern = modState("ember_lantern");

		// 5x5 dais one block below the surface cursor: stone bricks (~25% cracked),
		// copper-brick corners, a chiseled center tile under the chest.
		for (int dx = -2; dx <= 2; dx++) {
			for (int dz = -2; dz <= 2; dz++) {
				boolean corner = Math.abs(dx) == 2 && Math.abs(dz) == 2;
				BlockState state = corner ? daisCorner
						: random.nextInt(4) == 0 ? daisWorn : daisMain;
				this.setBlockState(world, base.add(dx, -1, dz), state);
			}
		}
		this.setBlockState(world, base.add(0, -1, 0), daisCenter);

		// Four copper-pillar columns on the corners (2-3 tall), each crowned by a lantern.
		int height = 2 + random.nextInt(2);
		for (int dx = -2; dx <= 2; dx += 4) {
			for (int dz = -2; dz <= 2; dz += 4) {
				for (int dy = 0; dy < height; dy++) {
					this.setBlockState(world, base.add(dx, dy, dz), pillar);
				}
				this.setBlockState(world, base.add(dx, height, dz), lantern);
			}
		}

		// Center chest with the shrine loot table.
		this.setBlockState(world, base, Blocks.CHEST.getDefaultState());
		LootableInventory.setLootTable(world, random, base, CHEST_LOOT);
		return true;
	}
}
