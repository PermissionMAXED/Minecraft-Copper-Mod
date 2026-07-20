package net.sonic0810.copperinferno.feature.artifacts;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Stores the single displayed item for the Trophy Pedestal. Same storage + client-sync
 * pattern as {@code CopperPlayerStatueBlockEntity}: ReadView/WriteView persistence (the stack
 * is written via {@code ItemStack.CODEC}, omitted entirely when empty because the codec
 * rejects empty stacks) and {@code toUpdatePacket}/{@code toInitialChunkDataNbt} +
 * {@code updateListeners} so the client block-entity renderer always sees the current trophy.
 */
public class TrophyPedestalBlockEntity extends BlockEntity {
	private static final String ITEM_KEY = "item";

	private ItemStack stack = ItemStack.EMPTY;

	public TrophyPedestalBlockEntity(BlockPos pos, BlockState state) {
		super(ArtifactsFeature.TROPHY_PEDESTAL_BLOCK_ENTITY, pos, state);
	}

	public ItemStack getStack() {
		return this.stack;
	}

	/** Server-side: sets the displayed trophy and resyncs to watching clients. */
	public void setStack(ItemStack stack) {
		this.stack = stack;
		this.markDirty();
		World world = this.getWorld();
		if (world != null) {
			BlockState state = this.getCachedState();
			world.updateListeners(this.getPos(), state, state, Block.NOTIFY_LISTENERS);
		}
	}

	@Override
	protected void writeData(WriteView view) {
		super.writeData(view);
		view.putNullable(ITEM_KEY, ItemStack.CODEC, this.stack.isEmpty() ? null : this.stack);
	}

	@Override
	protected void readData(ReadView view) {
		super.readData(view);
		this.stack = view.read(ITEM_KEY, ItemStack.CODEC).orElse(ItemStack.EMPTY);
	}

	@Override
	public BlockEntityUpdateS2CPacket toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
		return this.createNbt(registries);
	}
}
