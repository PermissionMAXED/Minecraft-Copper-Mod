package net.sonic0810.copperinferno.feature.handbook.client;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.sonic0810.copperinferno.CopperInferno;

/**
 * Loader for the OPTIONAL handbook recipe index asset
 * {@code assets/copper_inferno/handbook/recipe_index.json} (a JSON array of objects with keys
 * {@code recipeId}, {@code grid9} (array of 9 item-id strings, "" = empty slot), {@code result},
 * {@code count}, {@code type}).
 *
 * <p>WP1 ships only this loader; the generator that emits the index (and the HandbookScreen
 * integration) land in a later work package. A missing or malformed file is NOT an error: the
 * loader returns an empty list so the handbook keeps working without the index.
 */
public final class HandbookRecipeIndex {
	private HandbookRecipeIndex() {
	}

	public static final Identifier INDEX_ID = Identifier.of(CopperInferno.MOD_ID, "handbook/recipe_index.json");

	/** One indexed recipe: a 3x3 grid ({@code grid9[row * 3 + col]}, "" = empty slot). */
	public record IndexedRecipe(String recipeId, String[] grid9, String result, int count, String type) {
	}

	/**
	 * Reads the index from {@code manager}; returns an empty list when the asset is missing or
	 * unreadable (never throws).
	 */
	public static List<IndexedRecipe> load(ResourceManager manager) {
		Optional<Resource> resource = manager.getResource(INDEX_ID);
		if (resource.isEmpty()) {
			return List.of();
		}
		try (BufferedReader reader = resource.get().getReader()) {
			JsonArray array = JsonParser.parseReader(reader).getAsJsonArray();
			List<IndexedRecipe> recipes = new ArrayList<>(array.size());
			for (JsonElement element : array) {
				JsonObject obj = element.getAsJsonObject();
				JsonArray gridArray = obj.getAsJsonArray("grid9");
				String[] grid9 = new String[gridArray.size()];
				for (int i = 0; i < grid9.length; i++) {
					grid9[i] = gridArray.get(i).getAsString();
				}
				recipes.add(new IndexedRecipe(
						obj.get("recipeId").getAsString(),
						grid9,
						obj.get("result").getAsString(),
						obj.get("count").getAsInt(),
						obj.get("type").getAsString()));
			}
			return List.copyOf(recipes);
		} catch (Exception e) {
			// Optional asset: a malformed index must never take the handbook down.
			CopperInferno.LOGGER.warn("[handbook] ignoring malformed {}", INDEX_ID, e);
			return List.of();
		}
	}
}
