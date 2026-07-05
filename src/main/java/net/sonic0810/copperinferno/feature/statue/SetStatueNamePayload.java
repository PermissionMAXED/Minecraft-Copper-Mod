package net.sonic0810.copperinferno.feature.statue;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * C2S payload sent by the statue name-entry screen: sets the displayed player of the statue at
 * {@code pos} to {@code name} (16 chars max, validated server-side).
 */
public record SetStatueNamePayload(BlockPos pos, String name) implements CustomPayload {
	public static final CustomPayload.Id<SetStatueNamePayload> ID =
			new CustomPayload.Id<>(CopperInferno.id("set_statue_name"));
	public static final PacketCodec<RegistryByteBuf, SetStatueNamePayload> CODEC = PacketCodec.tuple(
			BlockPos.PACKET_CODEC, SetStatueNamePayload::pos,
			PacketCodecs.string(PlayerStatueFeature.MAX_NAME_LENGTH), SetStatueNamePayload::name,
			SetStatueNamePayload::new);

	@Override
	public CustomPayload.Id<? extends CustomPayload> getId() {
		return ID;
	}
}
