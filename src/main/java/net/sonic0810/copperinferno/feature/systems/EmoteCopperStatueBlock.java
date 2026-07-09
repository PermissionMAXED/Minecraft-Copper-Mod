package net.sonic0810.copperinferno.feature.systems;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * Emote Copper Statue — the statue-emotes system. A NEW statue block (the original
 * {@code feature/statue} copper player statue is untouched) whose little copper figure is a
 * plain baked JSON model in one of FOUR poses, driven by a blockstate {@link EnumProperty}
 * ({@code emote=salute|wave|cheer|facepalm}) that right-clicking cycles through.
 */
public class EmoteCopperStatueBlock extends Block {
	public static final MapCodec<EmoteCopperStatueBlock> CODEC = createCodec(EmoteCopperStatueBlock::new);

	/** The four statue poses (blockstate property values, in cycle order). */
	public enum Emote implements StringIdentifiable {
		SALUTE("salute"),
		WAVE("wave"),
		CHEER("cheer"),
		FACEPALM("facepalm");

		private final String name;

		Emote(String name) {
			this.name = name;
		}

		@Override
		public String asString() {
			return this.name;
		}
	}

	public static final EnumProperty<Emote> EMOTE = EnumProperty.of("emote", Emote.class);

	/** Same statue-ish outline as the copper player statue's pedestal figure. */
	private static final VoxelShape SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);

	public EmoteCopperStatueBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(EMOTE, Emote.SALUTE));
	}

	@Override
	protected MapCodec<? extends EmoteCopperStatueBlock> getCodec() {
		return CODEC;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(EMOTE);
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (world.isClient()) {
			return ActionResult.SUCCESS;
		}
		BlockState cycled = state.cycle(EMOTE);
		world.setBlockState(pos, cycled, Block.NOTIFY_LISTENERS);
		world.playSound(null, pos, SoundEvents.BLOCK_COPPER_BREAK, SoundCategory.BLOCKS, 0.5f, 1.6f);
		player.sendMessage(Text.translatable("message.copper_inferno.emote_statue.pose",
				Text.translatable("emote.copper_inferno." + cycled.get(EMOTE).asString())), true);
		return ActionResult.SUCCESS;
	}
}
