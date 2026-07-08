package net.sonic0810.copperinferno.feature.arsenal;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

/**
 * Generic arsenal charm/talisman: use (right-click) to apply one or more status effects to
 * yourself. Follows the HeatWardCharmItem / AshTalismanItem patterns from feature/infernofx:
 * a per-item cooldown via {@code ItemCooldownManager.set(ItemStack, int)}, and talismans
 * ({@code consumed = true}) crumble away on use ({@code decrementUnlessCreative}) while
 * charms are reusable.
 */
public class ArsenalCharmItem extends Item {
	/** One status effect application; fresh {@link StatusEffectInstance}s are built per use. */
	public record EffectSpec(RegistryEntry<StatusEffect> effect, int durationTicks, int amplifier) {
	}

	private final int cooldownTicks;
	private final boolean consumed;
	private final EffectSpec[] effects;

	public ArsenalCharmItem(Settings settings, int cooldownTicks, boolean consumed, EffectSpec... effects) {
		super(settings);
		this.cooldownTicks = cooldownTicks;
		this.consumed = consumed;
		this.effects = effects;
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (world instanceof ServerWorld serverWorld) {
			for (EffectSpec spec : effects) {
				user.addStatusEffect(new StatusEffectInstance(spec.effect(), spec.durationTicks(), spec.amplifier()));
			}
			world.playSound(null, user.getX(), user.getY(), user.getZ(),
					SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 0.8F, 1.2F);
			serverWorld.spawnParticles(ParticleTypes.ENCHANT,
					user.getX(), user.getBodyY(0.8), user.getZ(), 16, 0.4, 0.5, 0.4, 0.1);
		}

		user.getItemCooldownManager().set(stack, cooldownTicks);
		if (consumed) {
			stack.decrementUnlessCreative(1, user);
		}
		return ActionResult.SUCCESS;
	}
}
