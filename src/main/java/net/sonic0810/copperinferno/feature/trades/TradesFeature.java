package net.sonic0810.copperinferno.feature.trades;

import java.util.Optional;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * v4.1 "Trades" feature: villager + wandering trader offers for mod content, wired through
 * Fabric's {@link TradeOfferHelper}.
 *
 * <ul>
 *   <li>TOOLSMITH levels 1-3: buys raw gemalloy chunks (pyrium/emberite/kilnite) for
 *       emeralds, sells Pyrium/Emberite Ingots and a Pyrium Lamp.</li>
 *   <li>LIBRARIAN level 2: sells one Ember Edge enchanted book — only when the
 *       {@code copper_inferno:ember_edge} DATA enchantment actually exists in the trading
 *       world's dynamic registries (defensive lookup, offer silently skipped otherwise).</li>
 *   <li>Wandering trader: a dedicated 4-offer pool — a random scorchwood sapling, a
 *       Dr.Pepper, a Pyrium Nugget and Ember Jerky.</li>
 * </ul>
 *
 * <p>All item ids are resolved via {@link Registries#ITEM} lookups with
 * {@link Identifier} literals: the ids are registered by the gemalloy / scorchwood /
 * drpepper / infernofoods features (all init before this one, see CopperInferno), and the
 * registry lookup keeps this module free of compile-time deps on those feature classes.
 * All prices are modest (3-12 emeralds).
 */
public final class TradesFeature {
	private TradesFeature() {
	}

	/** The 8 scorchwood saplings (registered by scorchwood); one is picked at random per offer. */
	private static final String[] SCORCHWOOD_SAPLINGS = {
			"emberwood_sapling", "ashwillow_sapling", "cinderpine_sapling", "charoak_sapling",
			"glowbirch_sapling", "sootmaple_sapling", "duskthorn_sapling", "pyrewood_sapling"};

	public static void init() {
		registerToolsmithOffers();
		registerLibrarianOffers();
		registerWanderingTraderOffers();
		registerHandbookEntry();
	}

	/** Resolves a copper_inferno item registered by another feature (id registered by gemalloy etc.). */
	private static Item item(String path) {
		return Registries.ITEM.get(Identifier.of(CopperInferno.MOD_ID, path));
	}

	/** Villager BUYS {@code count} of the item, paying {@code emeralds} emeralds. */
	private static TradeOffers.Factory buying(String path, int count, int emeralds, int maxUses, int merchantXp) {
		Item item = item(path);
		return (entity, random) -> new TradeOffer(new TradedItem(item, count),
				new ItemStack(Items.EMERALD, emeralds), maxUses, merchantXp, 0.05F);
	}

	/** Villager SELLS {@code count} of the item for {@code emeralds} emeralds. */
	private static TradeOffers.Factory selling(String path, int count, int emeralds, int maxUses, int merchantXp) {
		Item item = item(path);
		return (entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, emeralds),
				new ItemStack(item, count), maxUses, merchantXp, 0.05F);
	}

	/**
	 * Toolsmith progression: novice buys raw pyrium/emberite chunks, apprentice buys raw
	 * kilnite and sells the first ingot, journeyman sells the better ingot plus a lamp.
	 * (Ids registered by gemalloy.)
	 */
	private static void registerToolsmithOffers() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 1, factories -> {
			factories.add(buying("raw_pyrium", 8, 3, 16, 2));
			factories.add(buying("raw_emberite", 8, 4, 16, 2));
		});
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 2, factories -> {
			factories.add(buying("raw_kilnite", 6, 4, 12, 10));
			factories.add(selling("pyrium_ingot", 1, 5, 12, 5));
		});
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 3, factories -> {
			factories.add(selling("emberite_ingot", 1, 7, 12, 10));
			factories.add(selling("pyrium_lamp", 1, 12, 8, 10));
		});
	}

	/**
	 * Librarian level 2: one Ember Edge enchanted book. {@code copper_inferno:ember_edge}
	 * is a DATA enchantment (datapack json, world-scoped), so it is looked up defensively
	 * from the trading entity's dynamic registry manager inside the factory; when absent
	 * the factory returns null, which vanilla's fillRecipesFromPool skips — no crash, the
	 * offer simply never materializes.
	 */
	private static void registerLibrarianOffers() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 2, factories ->
				factories.add((entity, random) -> entity.getRegistryManager()
						.getOptional(RegistryKeys.ENCHANTMENT)
						.flatMap(registry -> registry.getEntry(
								Identifier.of(CopperInferno.MOD_ID, "ember_edge")))
						.map(enchantment -> new TradeOffer(
								new TradedItem(Items.EMERALD, 9),
								Optional.of(new TradedItem(Items.BOOK, 1)),
								EnchantmentHelper.getEnchantedBookWith(
										new EnchantmentLevelEntry(enchantment, 1)),
								6, 15, 0.2F))
						.orElse(null)));
	}

	/**
	 * Wandering trader: a dedicated 4-offer pool (count == factory count, so every offer
	 * rolls): random scorchwood sapling, Dr.Pepper, Pyrium Nugget, Ember Jerky.
	 */
	private static void registerWanderingTraderOffers() {
		TradeOfferHelper.registerWanderingTraderOffers(builder -> builder.pool(
				CopperInferno.id("trades_wares"), 4,
				(entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 5),
						new ItemStack(item(SCORCHWOOD_SAPLINGS[random.nextInt(SCORCHWOOD_SAPLINGS.length)])),
						8, 1, 0.05F),
				selling("dr_pepper", 1, 3, 8, 1),
				selling("pyrium_nugget", 3, 4, 8, 1),
				selling("ember_jerky", 2, 3, 8, 1)));
	}

	/** One "items" handbook page documenting the new trades (bilingual inline). */
	private static void registerHandbookEntry() {
		HandbookEntries.add(new HandbookEntry("items", "trades_overview",
				"minecraft:emerald", null, null, null, 0,
				"New trades, all 3-12 emeralds: Toolsmiths buy Raw Pyrium, Raw Emberite and Raw Kilnite"
						+ " and sell Pyrium Ingots, Emberite Ingots and a Pyrium Lamp (levels 1-3);"
						+ " Librarians (level 2) sell an Ember Edge book once that enchantment exists;"
						+ " the Wandering Trader hawks a random scorchwood sapling, Dr.Pepper,"
						+ " Pyrium Nuggets and Ember Jerky.",
				"Neue Angebote, alle 3-12 Smaragde: Werkzeugschmiede kaufen Roh-Pyrium, Roh-Emberite"
						+ " und Roh-Kilnite und verkaufen Pyrium-Barren, Emberite-Barren und eine"
						+ " Pyrium-Lampe (Stufen 1-3); Bibliothekare (Stufe 2) verkaufen ein"
						+ " Glutschneide-Buch, sobald diese Verzauberung existiert; der fahrende"
						+ " H\u00e4ndler bietet einen zuf\u00e4lligen Brandholz-Setzling, Dr.Pepper,"
						+ " Pyrium-Klumpen und Glut-D\u00f6rrfleisch."));
	}
}
