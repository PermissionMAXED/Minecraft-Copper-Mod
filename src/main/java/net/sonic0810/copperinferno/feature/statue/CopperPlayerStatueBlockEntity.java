package net.sonic0810.copperinferno.feature.statue;

import java.util.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Stores the (optional) owner profile whose skin the statue displays. Mirrors the owner-storage +
 * client-sync pattern of {@code SkullBlockEntity}.
 */
public class CopperPlayerStatueBlockEntity extends BlockEntity {
	private static final String OWNER_KEY = "owner";

	private Optional<ProfileComponent> owner = Optional.empty();

	public CopperPlayerStatueBlockEntity(BlockPos pos, BlockState state) {
		super(PlayerStatueFeature.COPPER_PLAYER_STATUE_BLOCK_ENTITY, pos, state);
	}

	public Optional<ProfileComponent> getOwner() {
		return this.owner;
	}

	/** Server-side: sets the displayed player by name and resyncs to watching clients. */
	public void setOwnerName(String name) {
		this.owner = Optional.of(ProfileComponent.ofDynamic(name));
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
		view.putNullable(OWNER_KEY, ProfileComponent.CODEC, this.owner.orElse(null));
	}

	@Override
	protected void readData(ReadView view) {
		super.readData(view);
		this.owner = view.read(OWNER_KEY, ProfileComponent.CODEC);
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
