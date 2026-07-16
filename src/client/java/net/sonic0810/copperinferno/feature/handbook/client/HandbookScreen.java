package net.sonic0810.copperinferno.feature.handbook.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
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
 * crafting grid) with per-category entry counts, a search box filtering the active category by
 * the entries' localized display names, a paginated page area on the right (prev/next buttons,
 * arrow/PageUp/PageDown/Home/End keys and the mouse wheel all flip pages) and a DE/EN language
 * toggle for the entry body text.
 *
 * <p>Built to stay responsive with thousands of entries: the per-category entry lists, the
 * localized search names, the per-entry rendered heights and the recipe-kind lookups are all
 * cached lazily; a category is only paginated when it is shown and repagination reuses every
 * cache. Everything is drawn with {@link DrawContext} primitives - no GUI atlas textures.
 *
 * <p>Entry BODY text follows the toggle ({@code textDe}/{@code textEn}); static UI labels are
 * {@code Text.translatable} keys (present in both lang fragments) and item names come from
 * {@link ItemStack#getName()}, both auto-translated by the client language. The toggle's
 * default follows the client language code ({@code LanguageManager.getLanguage()}, verified via
 * javap; {@link Locale} fallback).
 *
 * <p>Recipe widgets adapt their layout to the recipe type, resolved by reading the mod's own
 * recipe JSON from the classpath (cached): shaped recipes render the classic 3x3 grid,
 * shapeless recipes a single ingredient row tagged "Shapeless", furnace-family recipes
 * (smelting/blasting/smoking/campfire) a single input slot with a small flame accent, and
 * stonecutting/smithing single-row layouts tagged with their station name.
 */
public class HandbookScreen extends Screen {
	private static final Text TITLE = Text.translatable("screen.copper_inferno.handbook.title");
	/** The six HandbookEntry categories plus the synthetic all-recipes tab. */
	private static final String[] CATEGORIES = {"blocks", "items", "gear", "dimension", "mobs", "bosses", "recipes"};

	private static final int CATEGORY_X = 8;
	private static final int CATEGORY_WIDTH = 88;
	/** Room for the per-category count text right of the buttons. */
	private static final int COUNT_COLUMN = 34;
	private static final int PAGE_TOP = 28;
	/** 18px cells with a 1px gap between them. */
	private static final int CELL_SIZE = 18;
	private static final int CELL_STRIDE = 19;
	private static final int GRID_HEIGHT = 3 * CELL_STRIDE - 1;
	/** Horizontal indent of text/grids relative to the entry icon. */
	private static final int TEXT_INDENT = 22;
	private static final int ENTRY_SPACING = 8;

	/** How a recipe widget is laid out; resolved from the recipe JSON's "type". */
	private enum RecipeKind {
		SHAPED(null), SHAPELESS("shapeless"), FURNACE("furnace"), BLASTING("blasting"),
		SMOKING("smoking"), CAMPFIRE("campfire"), STONECUTTER("stonecutter"), SMITHING("smithing");

		/** Suffix of the screen.copper_inferno.handbook.kind.* label key, null = unlabeled. */
		final String labelKey;

		RecipeKind(String labelKey) {
			this.labelKey = labelKey;
		}

		boolean singleRow() {
			return this != SHAPED;
		}

		boolean furnaceFamily() {
			return this == FURNACE || this == BLASTING || this == SMOKING || this == CAMPFIRE;
		}
	}

	private static final Pattern RECIPE_TYPE_RE = Pattern.compile("\"type\"\\s*:\\s*\"([a-z_:]+)\"");

	/** ItemStacks rendered in icons/grids, cached per item id (missing/typo ids -> air). */
	private final Map<String, ItemStack> stackCache = new HashMap<>();
	private final Map<String, ButtonWidget> categoryButtons = new HashMap<>();
	/** entry id -> lowercased localized search haystack (icon + result names, entry id). */
	private final Map<String, String> searchNameCache = new HashMap<>();
	/** entry id + language -> rendered entry height in px (cleared on init, i.e. resize). */
	private final Map<String, Integer> heightCache = new HashMap<>();
	/** recipe id -> layout kind (classpath JSON lookup done once per recipe). */
	private final Map<String, RecipeKind> kindCache = new HashMap<>();

	/** category -> its entries, built lazily on first access ("recipes" = all grid entries). */
	private Map<String, List<HandbookEntry>> categoryIndex;

	private String category = "blocks";
	private boolean german;
	private String query = "";
	private int page;
	/** Entries of the active category matching the search, in registration order. */
	private List<HandbookEntry> visible = List.of();
	/** pageStarts.get(p) = index into {@link #visible} of page p's first entry. */
	private List<Integer> pageStarts = List.of();

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
		this.heightCache.clear(); // heights depend on the page width, which follows the window
		this.categoryButtons.clear();
		int y = PAGE_TOP;
		for (String cat : CATEGORIES) {
			Text label = Text.translatable("screen.copper_inferno.handbook.category." + cat);
			ButtonWidget button = ButtonWidget.builder(label, b -> this.selectCategory(cat))
					.dimensions(CATEGORY_X, y, CATEGORY_WIDTH, 20)
					.tooltip(Tooltip.of(Text.literal("")
							.append(label).append(" (" + this.categoryCount(cat) + ")")))
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
		int searchWidth = Math.max(60, Math.min(140, this.pageRight() - this.pageLeft() - 60));
		this.searchField = new TextFieldWidget(this.textRenderer,
				this.pageLeft(), 5, searchWidth, 16,
				Text.translatable("screen.copper_inferno.handbook.search"));
		this.searchField.setMaxLength(64);
		this.searchField.setPlaceholder(Text.translatable("screen.copper_inferno.handbook.search"));
		this.searchField.setText(this.query);
		this.searchField.setChangedListener(this::onSearchChanged);
		this.addDrawableChild(this.searchField);
		this.rebuildVisible();
		this.repaginate();
		this.updateButtons();
	}

	private int pageLeft() {
		return CATEGORY_X + CATEGORY_WIDTH + COUNT_COLUMN + 6;
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

	// ------------------------------------------------------------------ lazy category index

	/** Buckets all registered entries by category (plus the synthetic "recipes" tab) once. */
	private Map<String, List<HandbookEntry>> categoryIndex() {
		if (this.categoryIndex == null) {
			Map<String, List<HandbookEntry>> index = new HashMap<>();
			for (String cat : CATEGORIES) {
				index.put(cat, new ArrayList<>());
			}
			for (HandbookEntry entry : HandbookEntries.all()) {
				List<HandbookEntry> bucket = index.get(entry.category());
				if (bucket != null) {
					bucket.add(entry);
				}
				if (entry.grid() != null) {
					index.get("recipes").add(entry);
				}
			}
			this.categoryIndex = index;
		}
		return this.categoryIndex;
	}

	private int categoryCount(String cat) {
		List<HandbookEntry> bucket = this.categoryIndex().get(cat);
		return bucket == null ? 0 : bucket.size();
	}

	// ------------------------------------------------------------------ search

	/** Lowercased localized names (icon + result) plus the entry id, cached per entry. */
	private String searchHaystack(HandbookEntry entry) {
		return this.searchNameCache.computeIfAbsent(entry.id(), key -> {
			StringBuilder sb = new StringBuilder();
			ItemStack icon = this.stackFor(entry.iconItemId());
			if (!icon.isEmpty()) {
				sb.append(icon.getName().getString()).append(' ');
			}
			if (entry.resultItemId() != null) {
				ItemStack result = this.stackFor(entry.resultItemId());
				if (!result.isEmpty()) {
					sb.append(result.getName().getString()).append(' ');
				}
			}
			sb.append(entry.id());
			return sb.toString().toLowerCase(Locale.ROOT);
		});
	}

	private void onSearchChanged(String text) {
		String normalized = text.trim().toLowerCase(Locale.ROOT);
		if (normalized.equals(this.query)) {
			return;
		}
		this.query = normalized;
		this.page = 0;
		this.rebuildVisible();
		this.repaginate();
		this.updateButtons();
	}

	/** Recomputes {@link #visible} = active category filtered by the search query. */
	private void rebuildVisible() {
		List<HandbookEntry> base = this.categoryIndex().getOrDefault(this.category, List.of());
		if (this.query.isEmpty()) {
			this.visible = base;
			return;
		}
		List<HandbookEntry> filtered = new ArrayList<>();
		for (HandbookEntry entry : base) {
			if (this.searchHaystack(entry).contains(this.query)) {
				filtered.add(entry);
			}
		}
		this.visible = filtered;
	}

	// ------------------------------------------------------------------ navigation

	private void selectCategory(String cat) {
		this.category = cat;
		this.page = 0;
		this.rebuildVisible();
		this.repaginate();
		this.updateButtons();
	}

	private void toggleLanguage() {
		this.german = !this.german;
		// Search names follow the client language, not the toggle - only heights change.
		this.repaginate();
		this.updateButtons();
	}

	private void turnPage(int direction) {
		this.page = Math.max(0, Math.min(this.pageStarts.size() - 1, this.page + direction));
		this.updateButtons();
	}

	private void jumpToPage(int target) {
		this.page = Math.max(0, Math.min(this.pageStarts.size() - 1, target));
		this.updateButtons();
	}

	private void updateButtons() {
		for (Map.Entry<String, ButtonWidget> entry : this.categoryButtons.entrySet()) {
			// The selected tab renders disabled, the usual vanilla "you are here" pattern.
			entry.getValue().active = !entry.getKey().equals(this.category);
		}
		this.prevButton.active = this.page > 0;
		this.nextButton.active = this.page < this.pageStarts.size() - 1;
		this.langButton.setMessage(this.langLabel());
	}

	// ------------------------------------------------------------------ pagination

	/** Greedily packs {@link #visible} into pages by cached rendered height. */
	private void repaginate() {
		int budget = this.pageBottom() - PAGE_TOP;
		List<Integer> starts = new ArrayList<>();
		int used = 0;
		for (int i = 0; i < this.visible.size(); i++) {
			int entryHeight = this.entryHeight(this.visible.get(i));
			if (starts.isEmpty() || used + entryHeight > budget) {
				starts.add(i);
				used = 0;
			}
			used += entryHeight;
		}
		this.pageStarts = starts;
		this.page = Math.max(0, Math.min(starts.size() - 1, this.page));
	}

	private int entryHeight(HandbookEntry entry) {
		String key = entry.id() + (this.german ? "|de" : "|en");
		Integer cached = this.heightCache.get(key);
		if (cached != null) {
			return cached;
		}
		int textWidth = this.pageRight() - this.pageLeft() - TEXT_INDENT;
		int lines = this.textRenderer.wrapLines(Text.literal(this.body(entry)), textWidth).size();
		int height = Math.max(CELL_SIZE, lines * (this.textRenderer.fontHeight + 1));
		if (entry.grid() != null) {
			height += 2 + (this.recipeKind(entry).singleRow() ? CELL_SIZE : GRID_HEIGHT);
		}
		height += ENTRY_SPACING;
		this.heightCache.put(key, height);
		return height;
	}

	// ------------------------------------------------------------------ recipe kinds

	/**
	 * Resolves the recipe widget layout by reading the mod's own recipe JSON from the
	 * classpath (works in dev and in the built jar); falls back to id heuristics, then to
	 * the shaped 3x3 grid. Cached per recipe id.
	 */
	private RecipeKind recipeKind(HandbookEntry entry) {
		String rid = entry.recipeId();
		if (rid == null) {
			return RecipeKind.SHAPED;
		}
		return this.kindCache.computeIfAbsent(rid, key -> {
			String path = "/data/copper_inferno/recipe/" + key.replace("copper_inferno:", "") + ".json";
			try (InputStream in = HandbookScreen.class.getResourceAsStream(path)) {
				if (in != null) {
					String json = new String(in.readAllBytes(), StandardCharsets.UTF_8);
					Matcher m = RECIPE_TYPE_RE.matcher(json);
					if (m.find()) {
						return switch (m.group(1)) {
							case "minecraft:crafting_shapeless" -> RecipeKind.SHAPELESS;
							case "minecraft:smelting" -> RecipeKind.FURNACE;
							case "minecraft:blasting" -> RecipeKind.BLASTING;
							case "minecraft:smoking" -> RecipeKind.SMOKING;
							case "minecraft:campfire_cooking" -> RecipeKind.CAMPFIRE;
							case "minecraft:stonecutting" -> RecipeKind.STONECUTTER;
							case "minecraft:smithing_transform", "minecraft:smithing_trim" -> RecipeKind.SMITHING;
							default -> RecipeKind.SHAPED;
						};
					}
				}
			} catch (IOException ignored) {
				// fall through to heuristics
			}
			String lower = key.toLowerCase(Locale.ROOT);
			if (lower.contains("stonecutting")) {
				return RecipeKind.STONECUTTER;
			}
			if (lower.contains("smithing")) {
				return RecipeKind.SMITHING;
			}
			if (lower.contains("smelting") || lower.contains("blasting") || lower.contains("smoking")
					|| lower.contains("campfire")) {
				return RecipeKind.FURNACE;
			}
			return RecipeKind.SHAPED;
		});
	}

	// ------------------------------------------------------------------ rendering

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
		super.render(context, mouseX, mouseY, deltaTicks);
		context.drawTextWithShadow(this.textRenderer, this.title, CATEGORY_X, 10, 0xFFFFFFFF);
		// Per-category entry counts right of the category buttons.
		for (String cat : CATEGORIES) {
			ButtonWidget button = this.categoryButtons.get(cat);
			if (button != null) {
				context.drawTextWithShadow(this.textRenderer, "(" + this.categoryCount(cat) + ")",
						CATEGORY_X + CATEGORY_WIDTH + 3, button.getY() + 6, 0xFFA0A0A0);
			}
		}
		if (this.visible.isEmpty()) {
			context.drawCenteredTextWithShadow(this.textRenderer,
					Text.translatable(this.query.isEmpty()
							? "screen.copper_inferno.handbook.empty"
							: "screen.copper_inferno.handbook.no_results"),
					(this.pageLeft() + this.pageRight()) / 2, (PAGE_TOP + this.pageBottom()) / 2, 0xFFA0A0A0);
		} else {
			// Clip to the page backdrop so an oversized entry (tiny GUI sizes) cannot
			// overflow into the footer / neighbouring widgets.
			context.enableScissor(this.pageLeft() - 4, PAGE_TOP - 4, this.pageRight() + 4, this.pageBottom() + 4);
			int start = this.pageStarts.get(this.page);
			int end = this.page + 1 < this.pageStarts.size() ? this.pageStarts.get(this.page + 1) : this.visible.size();
			int y = PAGE_TOP;
			for (int i = start; i < end; i++) {
				if (y >= this.pageBottom()) {
					break; // never start an entry past the footer
				}
				y += this.renderEntry(context, this.visible.get(i), this.pageLeft(), y);
			}
			context.disableScissor();
		}
		Text pageText = Text.translatable("screen.copper_inferno.handbook.page",
				this.page + 1, Math.max(1, this.pageStarts.size()));
		context.drawCenteredTextWithShadow(this.textRenderer, pageText,
				(this.pageLeft() + this.pageRight()) / 2, this.height - 18, 0xFFFFFFFF);
		// Filtered entry count for the active view, bottom-left under the categories.
		context.drawTextWithShadow(this.textRenderer,
				Text.translatable("screen.copper_inferno.handbook.results", this.visible.size()),
				CATEGORY_X, this.height - 18, 0xFFA0A0A0);
	}

	@Override
	public void renderBackground(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
		super.renderBackground(context, mouseX, mouseY, deltaTicks);
		// Page-area backdrop, drawn before the widgets/content so everything sits on top.
		context.fill(this.pageLeft() - 4, PAGE_TOP - 4, this.pageRight() + 4, this.pageBottom() + 4, 0x66000000);
	}

	/** Renders one entry (icon + body text + its kind-specific recipe widget); returns its height. */
	private int renderEntry(DrawContext context, HandbookEntry entry, int x, int y) {
		ItemStack icon = this.stackFor(entry.iconItemId());
		if (!icon.isEmpty()) {
			context.drawItem(icon, x, y);
		}
		int textWidth = this.pageRight() - x - TEXT_INDENT;
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
		RecipeKind kind = this.recipeKind(entry);
		int widgetY = y + textBlock + 2;
		int widgetHeight = kind.singleRow()
				? this.renderSingleRowWidget(context, entry, kind, x + TEXT_INDENT, widgetY)
				: this.renderShapedWidget(context, entry, x + TEXT_INDENT, widgetY);
		return textBlock + 2 + widgetHeight + ENTRY_SPACING;
	}

	/** Classic 3x3 crafting grid -> result xN. Returns the widget height. */
	private int renderShapedWidget(DrawContext context, HandbookEntry entry, int gridX, int gridY) {
		String[] grid = entry.grid();
		for (int i = 0; i < 9; i++) {
			int cellX = gridX + (i % 3) * CELL_STRIDE;
			int cellY = gridY + (i / 3) * CELL_STRIDE;
			this.renderSlot(context, cellX, cellY, i < grid.length ? grid[i] : "");
		}
		int middleY = gridY + GRID_HEIGHT / 2;
		this.renderArrowAndResult(context, entry, gridX + 3 * CELL_STRIDE + 4, middleY, null);
		return GRID_HEIGHT;
	}

	/**
	 * Single-row widget for shapeless/furnace/stonecutter/smithing recipes: the grid's
	 * non-empty ingredients in one row, a kind accent, the arrow, the result and a localized
	 * station label. Returns the widget height.
	 */
	private int renderSingleRowWidget(DrawContext context, HandbookEntry entry, RecipeKind kind, int rowX, int rowY) {
		List<String> inputs = new ArrayList<>();
		for (String id : entry.grid()) {
			if (id != null && !id.isEmpty()) {
				inputs.add(id);
			}
		}
		if (inputs.isEmpty()) {
			inputs.add(""); // always show at least one (empty) input slot
		}
		int x = rowX;
		for (String id : inputs) {
			this.renderSlot(context, x, rowY, id);
			x += CELL_STRIDE;
		}
		int middleY = rowY + CELL_SIZE / 2;
		if (kind.furnaceFamily()) {
			// Tiny flame accent: an orange base with a bright core, right of the inputs.
			context.fill(x + 1, middleY - 3, x + 7, middleY + 4, 0xFFE25822);
			context.fill(x + 3, middleY - 5, x + 5, middleY + 2, 0xFFFF7A2F);
			context.fill(x + 3, middleY, x + 5, middleY + 3, 0xFFFFB16B);
			x += 9;
		} else if (kind == RecipeKind.STONECUTTER) {
			// Saw-blade accent: a short bright line through a dark disc.
			context.fill(x + 1, middleY - 3, x + 8, middleY + 4, 0xFF5A5A5A);
			context.drawHorizontalLine(x, x + 8, middleY, 0xFFC0C0C0);
			x += 10;
		}
		this.renderArrowAndResult(context, entry, x + 3, middleY, kind.labelKey);
		return CELL_SIZE;
	}

	/** One 18x18 slot; empty ids render the empty slot background only. */
	private void renderSlot(DrawContext context, int cellX, int cellY, String id) {
		context.fill(cellX, cellY, cellX + CELL_SIZE, cellY + CELL_SIZE, 0xFF3B3B3B);
		if (id != null && !id.isEmpty()) {
			ItemStack stack = this.stackFor(id);
			if (!stack.isEmpty()) {
				context.drawItem(stack, cellX + 1, cellY + 1);
			}
		}
	}

	/** Arrow -> result slot xN, plus an optional gray localized kind label. */
	private void renderArrowAndResult(DrawContext context, HandbookEntry entry, int arrowX, int middleY,
			String labelKey) {
		context.drawTextWithShadow(this.textRenderer, "->", arrowX, middleY - this.textRenderer.fontHeight / 2,
				0xFFFFFFFF);
		int x = arrowX + this.textRenderer.getWidth("->") + 6;
		if (entry.resultItemId() != null) {
			context.fill(x, middleY - 9, x + CELL_SIZE, middleY - 9 + CELL_SIZE, 0xFF3B3B3B);
			ItemStack result = this.stackFor(entry.resultItemId());
			if (!result.isEmpty()) {
				context.drawItem(result, x + 1, middleY - 8);
			}
			x += CELL_SIZE + 4;
			if (entry.resultCount() > 1) {
				String count = "x" + entry.resultCount();
				context.drawTextWithShadow(this.textRenderer, count, x,
						middleY - this.textRenderer.fontHeight / 2, 0xFFFFFFFF);
				x += this.textRenderer.getWidth(count) + 6;
			}
		}
		if (labelKey != null) {
			context.drawTextWithShadow(this.textRenderer,
					Text.translatable("screen.copper_inferno.handbook.kind." + labelKey),
					x, middleY - this.textRenderer.fontHeight / 2, 0xFF9E9E9E);
		}
	}

	// ------------------------------------------------------------------ input

	@Override
	public boolean keyPressed(KeyInput input) {
		// While typing in the search box, arrows must move the caret, not flip pages.
		if (this.searchField != null && this.searchField.isFocused()) {
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
		if (input.key() == GLFW.GLFW_KEY_HOME) {
			this.jumpToPage(0);
			return true;
		}
		if (input.key() == GLFW.GLFW_KEY_END) {
			this.jumpToPage(this.pageStarts.size() - 1);
			return true;
		}
		return super.keyPressed(input);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
		if (verticalAmount != 0) {
			this.turnPage(verticalAmount < 0 ? 1 : -1);
			return true;
		}
		return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
	}

	@Override
	public boolean shouldPause() {
		return false;
	}
}
