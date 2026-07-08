package net.sonic0810.copperinferno.feature.wildworld;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.sonic0810.copperinferno.feature.inferno.InfernoFeature;
import net.sonic0810.copperinferno.feature.infernodim.InfernoDimensionFeature;

/**
 * Ruined Forge: a small abandoned smithy built from the existing cinderstone-family blocks —
 * a 5x5 cobbled-cinderstone floor, broken inferno-brick walls, a corner chimney and a glowing
 * molten-slag hearth (sometimes crowned by an inferno core). Registered as a real
 * {@code Feature<DefaultFeatureConfig>} in {@code Registries.FEATURE} (see
 * {@link RuinedForgesFeature}) and referenced by
 * {@code data/copper_inferno/worldgen/configured_feature/ruined_forge.json}
 * ({@code "type": "copper_inferno:ruined_forge", "config": {}}). Signatures verified via
 * javap: {@code Feature(Codec)}, {@code generate(FeatureContext)},
 * {@code FeatureContext#getWorld/getRandom/getOrigin}.
 *
 * <p>Block fields of other features are read lazily inside {@link #generate}, never at class
 * load, so feature-init order does not matter.
 */
public class RuinedForgeWorldgenFeature extends Feature<DefaultFeatureConfig> {
	public RuinedForgeWorldgenFeature(Codec<DefaultFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
		StructureWorldAccess world = context.getWorld();
		Random random = context.getRandom();

		// The Inferno is a nether-like capped dimension, so the placement height may land
		// inside terrain: first rise out of solid blocks, then settle down onto the floor.
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

		BlockState floorMain = InfernoDimensionFeature.COBBLED_CINDERSTONE.getDefaultState();
		BlockState floorWorn = InfernoFeature.CRACKED_INFERNO_BRICKS.getDefaultState();
		BlockState wall = InfernoFeature.INFERNO_BRICKS.block().getDefaultState();
		BlockState wallWorn = InfernoFeature.CRACKED_INFERNO_BRICKS.getDefaultState();
		BlockState hearth = InfernoDimensionFeature.MOLTEN_SLAG.getDefaultState();
		BlockState core = InfernoFeature.INFERNO_CORE.getDefaultState();

		// 5x5 floor (one block below the surface cursor), 20% worn tiles.
		for (int dx = -2; dx <= 2; dx++) {
			for (int dz = -2; dz <= 2; dz++) {
				BlockPos floorPos = base.add(dx, -1, dz);
				this.setBlockState(world, floorPos, random.nextInt(5) == 0 ? floorWorn : floorMain);
			}
		}

		// Broken perimeter walls: ring of inferno bricks, 1-3 high, ~35% of columns collapsed.
		for (int dx = -2; dx <= 2; dx++) {
			for (int dz = -2; dz <= 2; dz++) {
				boolean edge = Math.abs(dx) == 2 || Math.abs(dz) == 2;
				if (!edge || random.nextInt(100) < 35) {
					continue;
				}
				int height = 1 + random.nextInt(3);
				for (int dy = 0; dy < height; dy++) {
					BlockPos wallPos = base.add(dx, dy, dz);
					this.setBlockState(world, wallPos, random.nextInt(4) == 0 ? wallWorn : wall);
				}
			}
		}

		// Chimney stack on one corner (3-5 bricks tall).
		int chimneyHeight = 3 + random.nextInt(3);
		for (int dy = 0; dy < chimneyHeight; dy++) {
			this.setBlockState(world, base.add(2, dy, 2), wall);
		}

		// Central hearth: glowing molten slag, sometimes crowned with an inferno core.
		this.setBlockState(world, base.add(0, -1, 0), floorMain);
		this.setBlockState(world, base, hearth);
		if (random.nextInt(3) == 0) {
			this.setBlockState(world, base.up(), core);
		}
		return true;
	}
}
