package net.sonic0810.copperinferno.feature.sodafauna;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

/**
 * A vanilla-cream sheep with a soft-serve swirl of a fleece. Behavioral tweak: it can also be
 * bred (and lured) with sugar on top of the vanilla sheep food ({@link #isBreedingItem}).
 * {@code createChild} is overridden only for type consistency (vanilla hard-codes
 * {@code EntityType.SHEEP}; same fix as the infernomobs Cinder Strider).
 * Drops Cream Swirls ({@code loot_table/entities/vanilla_sheep.json}).
 */
public class VanillaSheepEntity extends SheepEntity {
	public VanillaSheepEntity(EntityType<? extends SheepEntity> type, World world) {
		super(type, world);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.isOf(Items.SUGAR) || super.isBreedingItem(stack);
	}

	@Override
	public VanillaSheepEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new VanillaSheepEntity(SodaFaunaFeature.VANILLA_SHEEP, world);
	}
}
