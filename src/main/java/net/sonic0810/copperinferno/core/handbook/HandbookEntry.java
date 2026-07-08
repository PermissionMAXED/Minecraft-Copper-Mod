package net.sonic0810.copperinferno.core.handbook;

import org.jetbrains.annotations.Nullable;

/**
 * One page of the in-game handbook. Server-safe (main source set, no client imports); the
 * client-side handbook screen renders these.
 *
 * @param category     one of {@code "blocks"}, {@code "items"}, {@code "gear"},
 *                     {@code "dimension"}, {@code "mobs"}, {@code "bosses"}
 * @param id           unique entry id within the handbook
 * @param iconItemId   item id (e.g. {@code "copper_inferno:infernium_ingot"}) rendered as the
 *                     entry's icon
 * @param recipeId     recipe id this entry documents, or null for non-recipe entries
 * @param grid         9 item-id strings row-major for a 3x3 crafting preview ({@code ""} = empty
 *                     slot), or null when there is no grid to show
 * @param resultItemId item id of the recipe result, or null
 * @param resultCount  result stack size (ignored when {@code resultItemId} is null)
 * @param textEn       English body text
 * @param textDe       German body text
 */
public record HandbookEntry(String category, String id, String iconItemId, @Nullable String recipeId,
		@Nullable String[] grid, @Nullable String resultItemId, int resultCount, String textEn, String textDe) {
}
