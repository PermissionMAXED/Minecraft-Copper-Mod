package net.sonic0810.copperinferno.feature.handbook.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * The COPPER INFERNO Handbook screen: a left column of category buttons (the six
 * {@link HandbookEntry} categories plus a synthetic "recipes" tab collecting every entry with a
 * crafting grid), a paginated page area on the right, prev/next page buttons, a header search
 * field that live-filters the current tab, and a DE/EN language toggle.
 *
 * <p>The "recipes" tab additionally lists EVERY recipe of the mod: after the curated grid
 * entries it appends one auto-generated entry per {@link HandbookRecipeIndex} recipe that no
 * curated entry documents (recipeId matching accepts both "dir/name" and bare-name forms,
 * mirroring devtools/check_handbook.py). The auto list is built lazily on the first visit to
 * the tab so the other categories never pay for the 2000+ index entries.
 *
 * <p>Entry BODY text follows the toggle ({@code textDe}/{@code textEn}); static UI labels are
 * {@code Text.translatable} keys (present in both lang fragments) and item names come from
 * {@link ItemStack#getName()}, both auto-translated by the client language. The toggle's
 * default follows the client language code ({@code LanguageManager.getLanguage()}, verified via
 * javap; {@link Locale} fallback).
 */
public class HandbookScreen extends Screen {
	private static final Text TITLE = Text.translatable("screen.copper_inferno.handbook.title");
	/**
	 * Search hint, drawn (gray) in {@link #render} ONLY while the field is empty. The vanilla
	 * {@code TextFieldWidget#setPlaceholder} is not used because it hides the hint while the
	 * empty field is merely focused (verified via javap on the 1.21.9 renderWidget bytecode);
	 * this manual draw keeps the hint up until the user actually types, and its isEmpty()
	 * guard guarantees the hint can never render underneath typed text.
	 */
	private static final Text SEARCH_PLACEHOLDER =
			Text.translatable("screen.copper_inferno.handbook.search");
	/** The six HandbookEntry categories plus the synthetic all-recipes tab. */
	private static final String[] CATEGORIES = {"blocks", "items", "gear", "dimension", "mobs", "bosses", "recipes"};

	private static final int CATEGORY_X = 8;
	private static final int CATEGORY_WIDTH = 88;
	private static final int PAGE_TOP = 28;
	/** 18px cells with a 1px gap between them. */
	private static final int CELL_SIZE = 18;
	private static final int CELL_STRIDE = 19;
	private static final int GRID_HEIGHT = 3 * CELL_STRIDE - 1;
	/** Horizontal indent of text/grids relative to the entry icon. */
	private static final int TEXT_INDENT = 22;
	private static final int ENTRY_SPACING = 8;

	/** ItemStacks rendered in icons/grids, cached per item id (missing/typo ids -> air). */
	private final Map<String, ItemStack> stackCache = new HashMap<>();
	private final Map<String, ButtonWidget> categoryButtons = new HashMap<>();
	/** Auto entries from the recipe index, built lazily for the recipes tab; null = not built. */
	private List<HandbookEntry> autoEntries;
	/** Index recipe type (shaped/shapeless/smelting/...) per auto entry id, for the type badge. */
	private final Map<String, String> autoEntryTypes = new HashMap<>();

	private String category = "blocks";
	private boolean german;
	private int page;
	/** Raw search-field text; survives init() re-layouts (resize). Empty = no filter. */
	private String searchQuery = "";
	private List<List<HandbookEntry>> pages = List.of();

	private ButtonWidget prevButton;
	private ButtonWidget nextButton;
	private ButtonWidget langButton;
	private TextFieldWidget searchField;

	public HandbookScreen() {
		super(TITLE);
		// LanguageManager.getLanguage() returns the selected code, e.g. "de_de" (verified via
		// javap); fall back to the JVM locale if the manager is somehow not ready yet.
		LanguageManager languageManager = MinecraftClient.getInstance().getLanguageManager();
		String code = languageManager != null ? languageManager.getLanguage() : Locale.getDefault().getLanguage();
		this.german = code != null && code.toLowerCase(Locale.ROOT).startsWith("de");
	}

	@Override
	protected void init() {
		this.categoryButtons.clear();
		int y = PAGE_TOP;
		for (String cat : CATEGORIES) {
			ButtonWidget button = ButtonWidget.builder(
							Text.translatable("screen.copper_inferno.handbook.category." + cat),
							b -> this.selectCategory(cat))
					.dimensions(CATEGORY_X, y, CATEGORY_WIDTH, 20)
					.build();
			this.categoryButtons.put(cat, button);
			this.addDrawableChild(button);
			y += 22;
		}
		this.prevButton = this.addDrawableChild(ButtonWidget.builder(
						Text.translatable("screen.copper_inferno.handbook.prev"), b -> this.turnPage(-1))
				.dimensions(this.pageLeft(), this.height - 24, 20, 20)
				.build());
		this.nextButton = this.addDrawableChild(ButtonWidget.builder(
						Text.translatable("screen.copper_inferno.handbook.next"), b -> this.turnPage(1))
				.dimensions(this.pageRight() - 20, this.height - 24, 20, 20)
				.build());
		this.langButton = this.addDrawableChild(ButtonWidget.builder(this.langLabel(), b -> this.toggleLanguage())
				.dimensions(this.width - 48, 4, 40, 18)
				.build());
		// Header search field (left of the language toggle), shared by every tab. Ctor
		// (TextRenderer, x, y, w, h, Text) verified via javap.
		int searchWidth = Math.min(140, this.width / 3);
		this.searchField = new TextFieldWidget(this.textRenderer,
				this.width - 48 - 8 - searchWidth, 4, searchWidth, 18,
				Text.translatable("screen.copper_inferno.handbook.search"));
		this.searchField.setMaxLength(64);
		this.searchField.setText(this.searchQuery); // restore across resize re-inits
		this.searchField.setChangedListener(this::onSearchChanged);
		this.addDrawableChild(this.searchField);
		this.repaginate();
		this.updateButtons();
	}

	/** Live filter: any edit re-filters the current tab and jumps back to page 0. */
	private void onSearchChanged(String query) {
		if (query.equals(this.searchQuery)) {
			return; // e.g. the setText() during init(); nothing changed
		}
		this.searchQuery = query;
		this.page = 0;
		this.repaginate();
		this.updateButtons();
	}

	/**
	 * Case-insensitive substring match against the entry's display name (the icon stack's
	 * {@link ItemStack#getName()}, i.e. the same name the page renderer shows) and the raw
	 * result item id (e.g. "copper_inferno:copper_gear").
	 */
	private boolean matchesSearch(HandbookEntry entry, String query) {
		String name = this.stackFor(entry.iconItemId()).getName().getString();
		if (name.toLowerCase(Locale.ROOT).contains(query)) {
			return true;
		}
		return entry.resultItemId() != null
				&& entry.resultItemId().toLowerCase(Locale.ROOT).contains(query);
	}

	private int pageLeft() {
		return CATEGORY_X + CATEGORY_WIDTH + 12;
	}

	private int pageRight() {
		return this.width - 8;
	}

	private int pageBottom() {
		return this.height - 32;
	}

	private String body(HandbookEntry entry) {
		return this.german ? entry.textDe() : entry.textEn();
	}

	private Text langLabel() {
		return Text.translatable(this.german
				? "screen.copper_inferno.handbook.lang_de"
				: "screen.copper_inferno.handbook.lang_en");
	}

	private ItemStack stackFor(String id) {
		return this.stackCache.computeIfAbsent(id, key -> {
			Identifier identifier = Identifier.tryParse(key);
			if (identifier == null) {
				return ItemStack.EMPTY;
			}
			// Registries.ITEM is a DefaultedRegistry: unknown ids resolve to minecraft:air.
			return new ItemStack(Registries.ITEM.get(identifier));
		});
	}

	private void selectCategory(String cat) {
		this.category = cat;
		this.page = 0;
		this.repaginate();
		this.updateButtons();
	}

	private void toggleLanguage() {
		this.german = !this.german;
		this.repaginate();
		this.updateButtons();
	}

	private void turnPage(int direction) {
		this.page = Math.max(0, Math.min(this.pages.size() - 1, this.page + direction));
		this.updateButtons();
	}

	private void updateButtons() {
		for (Map.Entry<String, ButtonWidget> entry : this.categoryButtons.entrySet()) {
			// The selected tab renders disabled, the usual vanilla "you are here" pattern.
			entry.getValue().active = !entry.getKey().equals(this.category);
		}
		this.prevButton.active = this.page > 0;
		this.nextButton.active = this.page < this.pages.size() - 1;
		this.langButton.setMessage(this.langLabel());
	}

	/** Greedily packs the current category's entries into pages by rendered height. */
	private void repaginate() {
		List<HandbookEntry> filtered = new ArrayList<>();
		for (HandbookEntry entry : HandbookEntries.all()) {
			boolean match = "recipes".equals(this.category)
					? entry.grid() != null
					: this.category.equals(entry.category());
			if (match) {
				filtered.add(entry);
			}
		}
		if ("recipes".equals(this.category)) {
			// Curated grid entries first, then every not-yet-documented indexed recipe.
			filtered.addAll(this.autoRecipeEntries());
		}
		String query = this.searchQuery.trim().toLowerCase(Locale.ROOT);
		if (!query.isEmpty()) {
			filtered.removeIf(entry -> !this.matchesSearch(entry, query));
		}
		int budget = this.pageBottom() - PAGE_TOP;
		List<List<HandbookEntry>> newPages = new ArrayList<>();
		List<HandbookEntry> current = new ArrayList<>();
		int used = 0;
		for (HandbookEntry entry : filtered) {
			int entryHeight = this.entryHeight(entry);
			if (!current.isEmpty() && used + entryHeight > budget) {
				newPages.add(current);
				current = new ArrayList<>();
				used = 0;
			}
			current.add(entry);
			used += entryHeight;
		}
		if (!current.isEmpty()) {
			newPages.add(current);
		}
		this.pages = newPages;
		this.page = Math.max(0, Math.min(newPages.size() - 1, this.page));
	}

	/**
	 * One synthetic entry per indexed recipe that no curated entry documents, built lazily on
	 * the first visit to the recipes tab and cached for this screen's lifetime. Dedupe accepts
	 * a curated {@link HandbookEntry#recipeId()} in both "dir/name" and bare-name form, with or
	 * without the {@code copper_inferno:} prefix (mirrors devtools/check_handbook.py).
	 */
	private List<HandbookEntry> autoRecipeEntries() {
		if (this.autoEntries != null) {
			return this.autoEntries;
		}
		Set<String> documented = new HashSet<>();
		for (HandbookEntry entry : HandbookEntries.all()) {
			if (entry.recipeId() != null) {
				String id = entry.recipeId();
				if (id.startsWith("copper_inferno:")) {
					id = id.substring("copper_inferno:".length());
				}
				documented.add(id);
			}
		}
		List<HandbookEntry> built = new ArrayList<>();
		for (HandbookRecipeIndex.IndexedRecipe recipe
				: HandbookRecipeIndex.load(MinecraftClient.getInstance().getResourceManager())) {
			String recipeId = recipe.recipeId();
			String bareName = recipeId.substring(recipeId.lastIndexOf('/') + 1);
			if (documented.contains(recipeId) || documented.contains(bareName)) {
				continue;
			}
			// ItemStack#getName follows the client language automatically; the
			// Crafted/Herstellung template prefix follows the DE/EN toggle like all body text.
			String resultName = this.stackFor(recipe.result()).getName().getString();
			HandbookEntry entry = new HandbookEntry("recipes", "auto/" + recipeId, recipe.result(),
					recipeId, recipe.grid9(), recipe.result(), recipe.count(),
					"Crafted: " + resultName + " x" + recipe.count() + ".",
					"Herstellung: " + resultName + " x" + recipe.count() + ".");
			this.autoEntryTypes.put(entry.id(), recipe.type());
			built.add(entry);
		}
		this.autoEntries = built;
		return built;
	}

	/** The badge label for an auto (recipe-index) entry, or null for curated entries. */
	private Text badgeFor(HandbookEntry entry) {
		String type = this.autoEntryTypes.get(entry.id());
		return type == null ? null : Text.translatable("screen.copper_inferno.handbook.type." + type);
	}

	/** Body wrap width; entries with a type badge reserve the badge's width on the right. */
	private int bodyTextWidth(HandbookEntry entry) {
		int width = this.pageRight() - this.pageLeft() - TEXT_INDENT;
		Text badge = this.badgeFor(entry);
		if (badge != null) {
			width -= this.textRenderer.getWidth(badge) + 8;
		}
		return width;
	}

	private int entryHeight(HandbookEntry entry) {
		int textWidth = this.bodyTextWidth(entry);
		int lines = this.textRenderer.wrapLines(Text.literal(this.body(entry)), textWidth).size();
		int height = Math.max(CELL_SIZE, lines * (this.textRenderer.fontHeight + 1));
		if (entry.grid() != null) {
			height += 2 + GRID_HEIGHT;
		}
		return height + ENTRY_SPACING;
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
		super.render(context, mouseX, mouseY, deltaTicks);
		// Title sits left in the header so it cannot collide with the search field.
		context.drawTextWithShadow(this.textRenderer, this.title, CATEGORY_X, 10, 0xFFFFFFFF);
		// Search hint: ONLY while the field is empty, so typed text never overlaps it. Drawn
		// at the widget's own text anchor (x+4, vertically centered), matching vanilla
		// TextFieldWidget.updateTextPosition() for a background-drawing field.
		if (this.searchField != null && this.searchField.getText().isEmpty()) {
			context.drawTextWithShadow(this.textRenderer, SEARCH_PLACEHOLDER,
					this.searchField.getX() + 4,
					this.searchField.getY() + (this.searchField.getHeight() - 8) / 2,
					0xFFA0A0A0);
		}
		if (this.pages.isEmpty()) {
			context.drawCenteredTextWithShadow(this.textRenderer,
					Text.translatable("screen.copper_inferno.handbook.empty"),
					(this.pageLeft() + this.pageRight()) / 2, (PAGE_TOP + this.pageBottom()) / 2, 0xFFA0A0A0);
		} else {
			// Clip to the page backdrop so an oversized entry (tiny GUI sizes) cannot
			// overflow into the footer / neighbouring widgets.
			context.enableScissor(this.pageLeft() - 4, PAGE_TOP - 4, this.pageRight() + 4, this.pageBottom() + 4);
			int y = PAGE_TOP;
			for (HandbookEntry entry : this.pages.get(this.page)) {
				if (y >= this.pageBottom()) {
					break; // never start an entry past the footer
				}
				y += this.renderEntry(context, entry, this.pageLeft(), y);
			}
			context.disableScissor();
		}
		Text pageText = Text.translatable("screen.copper_inferno.handbook.page",
				this.page + 1, Math.max(1, this.pages.size()));
		context.drawCenteredTextWithShadow(this.textRenderer, pageText,
				(this.pageLeft() + this.pageRight()) / 2, this.height - 18, 0xFFFFFFFF);
	}

	@Override
	public void renderBackground(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
		super.renderBackground(context, mouseX, mouseY, deltaTicks);
		// Page-area backdrop, drawn before the widgets/content so everything sits on top.
		context.fill(this.pageLeft() - 4, PAGE_TOP - 4, this.pageRight() + 4, this.pageBottom() + 4, 0x66000000);
	}

	/** Renders one entry (icon + body text, plus the 3x3 recipe widget) and returns its height. */
	private int renderEntry(DrawContext context, HandbookEntry entry, int x, int y) {
		ItemStack icon = this.stackFor(entry.iconItemId());
		if (!icon.isEmpty()) {
			context.drawItem(icon, x, y);
		}
		// Recipe-type badge (shaped/shapeless/smelting/...), right-aligned on auto entries;
		// bodyTextWidth() reserved this horizontal strip so the body text cannot collide.
		Text badge = this.badgeFor(entry);
		if (badge != null) {
			context.drawTextWithShadow(this.textRenderer, badge,
					this.pageRight() - this.textRenderer.getWidth(badge) - 2, y, 0xFFA0A0A0);
		}
		int textWidth = this.bodyTextWidth(entry);
		List<OrderedText> lines = this.textRenderer.wrapLines(Text.literal(this.body(entry)), textWidth);
		int lineY = y;
		for (OrderedText line : lines) {
			context.drawTextWithShadow(this.textRenderer, line, x + TEXT_INDENT, lineY, 0xFFE0E0E0);
			lineY += this.textRenderer.fontHeight + 1;
		}
		int textBlock = Math.max(CELL_SIZE, lines.size() * (this.textRenderer.fontHeight + 1));
		if (entry.grid() == null) {
			return textBlock + ENTRY_SPACING;
		}

		// ----- 3x3 recipe widget: ingredient grid -> result xN
		int gridX = x + TEXT_INDENT;
		int gridY = y + textBlock + 2;
		String[] grid = entry.grid();
		for (int i = 0; i < 9; i++) {
			int cellX = gridX + (i % 3) * CELL_STRIDE;
			int cellY = gridY + (i / 3) * CELL_STRIDE;
			context.fill(cellX, cellY, cellX + CELL_SIZE, cellY + CELL_SIZE, 0xFF3B3B3B);
			String id = i < grid.length ? grid[i] : "";
			if (id != null && !id.isEmpty()) {
				ItemStack stack = this.stackFor(id);
				if (!stack.isEmpty()) {
					context.drawItem(stack, cellX + 1, cellY + 1);
				}
			}
		}
		int middleY = gridY + GRID_HEIGHT / 2;
		int arrowX = gridX + 3 * CELL_STRIDE + 4;
		context.drawTextWithShadow(this.textRenderer, "->", arrowX, middleY - this.textRenderer.fontHeight / 2, 0xFFFFFFFF);
		if (entry.resultItemId() != null) {
			int resultX = arrowX + this.textRenderer.getWidth("->") + 6;
			context.fill(resultX, middleY - 9, resultX + CELL_SIZE, middleY - 9 + CELL_SIZE, 0xFF3B3B3B);
			ItemStack result = this.stackFor(entry.resultItemId());
			if (!result.isEmpty()) {
				context.drawItem(result, resultX + 1, middleY - 8);
			}
			if (entry.resultCount() > 1) {
				String count = "x" + entry.resultCount();
				context.drawTextWithShadow(this.textRenderer, count,
						resultX + CELL_SIZE + 4, middleY - this.textRenderer.fontHeight / 2, 0xFFFFFFFF);
			}
		}
		return textBlock + 2 + GRID_HEIGHT + ENTRY_SPACING;
	}

	@Override
	public boolean keyPressed(KeyInput input) {
		if (this.searchField != null && this.searchField.isFocused()) {
			// While typing a query, arrows/page keys belong to the text field (cursor
			// movement), not the page-turn shortcuts; super routes to the focused child.
			return super.keyPressed(input);
		}
		if (input.key() == GLFW.GLFW_KEY_LEFT || input.key() == GLFW.GLFW_KEY_PAGE_UP) {
			this.turnPage(-1);
			return true;
		}
		if (input.key() == GLFW.GLFW_KEY_RIGHT || input.key() == GLFW.GLFW_KEY_PAGE_DOWN) {
			this.turnPage(1);
			return true;
		}
		return super.keyPressed(input);
	}

	@Override
	public boolean shouldPause() {
		return false;
	}
}
