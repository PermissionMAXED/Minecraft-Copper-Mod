package net.sonic0810.copperinferno.feature.golem;

import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.CopperGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.sonic0810.copperinferno.core.ModCreativeTab;
import net.sonic0810.copperinferno.core.ModEntities;
import net.sonic0810.copperinferno.core.ModItems;
import net.sonic0810.copperinferno.core.ModSounds;
import net.sonic0810.copperinferno.feature.drpepper.DrPepperFeature;

/**
 * Dr.Pepper golems: right-clicking a vanilla Copper Golem with a Dr.Pepper converts it into a
 * {@link DrPepperGolemEntity}, plus a spawn item for direct placement. The proximity DOOM aura is
 * client-side (see {@code DrPepperGolemFeatureClient}).
 */
public final class DrPepperGolemFeature {
	private DrPepperGolemFeature() {
	}

	public static EntityType<DrPepperGolemEntity> DR_PEPPER_GOLEM;
	public static Item DR_PEPPER_GOLEM_SPAWN_ITEM;

	public static void init() {
		// Dimensions/eye height copied from the vanilla copper_golem registration
		// (EntityType bytecode: dimensions(0.49f, 0.98f).eyeHeight(0.8125f).maxTrackingRange(10)).
		DR_PEPPER_GOLEM = ModEntities.register("dr_pepper_golem",
				EntityType.Builder.create(DrPepperGolemEntity::new, SpawnGroup.MISC)
						.dimensions(0.49f, 0.98f)
						.eyeHeight(0.8125f)
						.maxTrackingRange(10));
		FabricDefaultAttributeRegistry.register(DR_PEPPER_GOLEM, CopperGolemEntity.createCopperGolemAttributes());

		// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component that SpawnEggItem reads its
		// entity type from in 1.21.9 (verified via bytecode), so the vanilla item class handles
		// placement on the clicked face + creative-mode consumption for us.
		DR_PEPPER_GOLEM_SPAWN_ITEM = ModItems.register("dr_pepper_golem", SpawnEggItem::new,
				new Item.Settings().spawnEgg(DR_PEPPER_GOLEM));

		ItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MOBS_KEY)
				.register(entries -> entries.add(DR_PEPPER_GOLEM_SPAWN_ITEM));

		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			// Exact class check: never re-transform a DrPepperGolemEntity (or other subclasses).
			if (entity.getClass() != CopperGolemEntity.class || player.isSpectator()) {
				return ActionResult.PASS;
			}
			ItemStack stack = player.getStackInHand(hand);
			if (!stack.isOf(DrPepperFeature.DR_PEPPER)) {
				return ActionResult.PASS;
			}
			if (world instanceof ServerWorld serverWorld) {
				transform(serverWorld, (CopperGolemEntity) entity, player, stack);
			}
			return ActionResult.SUCCESS;
		});
	}

	private static void transform(ServerWorld world, CopperGolemEntity golem, PlayerEntity player, ItemStack stack) {
		DrPepperGolemEntity converted = new DrPepperGolemEntity(DR_PEPPER_GOLEM, world);
		converted.refreshPositionAndAngles(golem.getX(), golem.getY(), golem.getZ(), golem.getYaw(), golem.getPitch());
		converted.setHeadYaw(golem.getHeadYaw());
		converted.setBodyYaw(golem.getBodyYaw());
		if (golem.getCustomName() != null) {
			converted.setCustomName(golem.getCustomName());
			converted.setCustomNameVisible(golem.isCustomNameVisible());
		}
		// Carry the old golem's live state over so the conversion is lossless.
		converted.setHealth(Math.min(golem.getHealth(), converted.getMaxHealth()));
		for (StatusEffectInstance effect : golem.getStatusEffects()) {
			converted.addStatusEffect(new StatusEffectInstance(effect));
		}
		// Copper golems can carry items (MAINHAND) and wear a poppy etc.; copy every slot.
		for (EquipmentSlot slot : EquipmentSlot.VALUES) {
			ItemStack equipped = golem.getEquippedStack(slot);
			if (!equipped.isEmpty()) {
				converted.equipStack(slot, equipped.copy());
			}
		}
		converted.setFireTicks(golem.getFireTicks());
		converted.setVelocity(golem.getVelocity());
		golem.discard();
		world.spawnEntity(converted);

		stack.decrementUnlessCreative(1, player);
		world.playSound(null, converted.getX(), converted.getY(), converted.getZ(),
				ModSounds.DR_PEPPER_OPEN, SoundCategory.NEUTRAL, 1.0f, 1.0f);
		world.spawnParticles(new DustParticleEffect(DrPepperGolemEntity.AURA_COLOR, 1.0f),
				converted.getX(), converted.getBodyY(0.5), converted.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
		world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
				converted.getX(), converted.getBodyY(0.5), converted.getZ(), 12, 0.45, 0.55, 0.45, 0.1);
	}
}
