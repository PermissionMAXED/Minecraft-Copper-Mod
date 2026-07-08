#!/usr/bin/env python3
"""Handbook recipe-index generator (v4 WP18).

Walks src/main/resources/data/copper_inferno/recipe/**/*.json and emits
src/main/resources/assets/copper_inferno/handbook/recipe_index.json: a JSON array (sorted by
recipeId, one object per line) of

  {"recipeId": "<dir>/<name>", "type": "crafting|smelting|blasting|smoking|stonecutting|smithing",
   "grid9": [9 item-id strings, "" = empty slot], "result": "ns:id", "count": N}

matching the WP1 client loader contract (HandbookRecipeIndex reads key "grid9").

Grid layout per recipe type:
  - crafting_shaped:     pattern+key expanded row-major into the 9 slots
  - crafting_shapeless:  ingredients fill slots 0..n-1
  - smelting/blasting/smoking/stonecutting: the single ingredient in center slot 4
  - smithing_transform:  slots 3/4/5 = template/base/addition

Ingredient alternatives (JSON arrays) take the FIRST alternative; "#tag" ingredients are
replaced by a representative item via TAG_REPRESENTATIVES (hardcoded from the tags actually
used in our recipes -- an unmapped tag is a fatal error so new tags fail loudly here).

Idempotent: running it any number of times produces byte-identical output. Exits non-zero on
any recipe it cannot index (unknown type, unmapped tag, malformed ingredient).
"""
import json
import os
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
RECIPE_ROOT = os.path.join(ROOT, "src/main/resources/data/copper_inferno/recipe")
OUT_PATH = os.path.join(ROOT, "src/main/resources/assets/copper_inferno/handbook/recipe_index.json")

TYPE_MAP = {
    "minecraft:crafting_shaped": "crafting",
    "minecraft:crafting_shapeless": "crafting",
    "minecraft:smelting": "smelting",
    "minecraft:blasting": "blasting",
    "minecraft:smoking": "smoking",
    "minecraft:stonecutting": "stonecutting",
    "minecraft:smithing_transform": "smithing",
}

# Representative item for every "#tag" ingredient that appears in our recipes (the first
# entry of the tag on disk; vanilla tags mapped by hand). Scan for new tags with:
#   rg -o '"#[a-z0-9_:./]+"' src/main/resources/data/copper_inferno/recipe
TAG_REPRESENTATIVES = {
    "minecraft:planks": "minecraft:oak_planks",
    "minecraft:eggs": "minecraft:egg",
    "copper_inferno:ashwillow_logs": "copper_inferno:ashwillow_log",
    "copper_inferno:charoak_logs": "copper_inferno:charoak_log",
    "copper_inferno:cinderpine_logs": "copper_inferno:cinderpine_log",
    "copper_inferno:duskthorn_logs": "copper_inferno:duskthorn_log",
    "copper_inferno:emberwood_logs": "copper_inferno:emberwood_log",
    "copper_inferno:glowbirch_logs": "copper_inferno:glowbirch_log",
    "copper_inferno:pyrewood_logs": "copper_inferno:pyrewood_log",
    "copper_inferno:scorched_logs": "copper_inferno:scorched_stem",
    "copper_inferno:sootmaple_logs": "copper_inferno:sootmaple_log",
}

errors: list[str] = []


def item_of(ingredient, where: str) -> str:
    """Resolve one ingredient JSON value to a single display item id."""
    if isinstance(ingredient, list):
        if not ingredient:
            errors.append(f"{where}: empty ingredient alternatives list")
            return ""
        ingredient = ingredient[0]  # first alternative
    if not isinstance(ingredient, str) or not ingredient:
        errors.append(f"{where}: unsupported ingredient form {ingredient!r}")
        return ""
    if ingredient.startswith("#"):
        tag = ingredient[1:]
        rep = TAG_REPRESENTATIVES.get(tag)
        if rep is None:
            errors.append(f"{where}: tag #{tag} missing from TAG_REPRESENTATIVES")
            return ""
        return rep
    return ingredient


def grid_of(recipe: dict, rtype: str, where: str) -> list[str]:
    grid = [""] * 9
    if rtype == "minecraft:crafting_shaped":
        key = recipe["key"]
        pattern = recipe["pattern"]
        if len(pattern) > 3 or any(len(row) > 3 for row in pattern):
            errors.append(f"{where}: pattern larger than 3x3")
            return grid
        for r, row in enumerate(pattern):
            for c, ch in enumerate(row):
                if ch == " ":
                    continue
                if ch not in key:
                    errors.append(f"{where}: pattern char {ch!r} missing from key")
                    continue
                grid[r * 3 + c] = item_of(key[ch], f"{where} key {ch!r}")
    elif rtype == "minecraft:crafting_shapeless":
        ingredients = recipe["ingredients"]
        if len(ingredients) > 9:
            errors.append(f"{where}: {len(ingredients)} shapeless ingredients (max 9)")
        for i, ing in enumerate(ingredients[:9]):
            grid[i] = item_of(ing, f"{where} ingredient {i}")
    elif rtype in ("minecraft:smelting", "minecraft:blasting", "minecraft:smoking",
                   "minecraft:stonecutting"):
        grid[4] = item_of(recipe["ingredient"], f"{where} ingredient")
    elif rtype == "minecraft:smithing_transform":
        grid[3] = item_of(recipe["template"], f"{where} template")
        grid[4] = item_of(recipe["base"], f"{where} base")
        grid[5] = item_of(recipe["addition"], f"{where} addition")
    return grid


def main() -> int:
    entries = []
    for dirpath, _dirs, files in os.walk(RECIPE_ROOT):
        for fn in files:
            if not fn.endswith(".json"):
                continue
            path = os.path.join(dirpath, fn)
            recipe_id = os.path.relpath(path, RECIPE_ROOT)[:-5].replace(os.sep, "/")
            with open(path, encoding="utf-8") as f:
                recipe = json.load(f)
            rtype = recipe.get("type")
            if rtype not in TYPE_MAP:
                errors.append(f"{recipe_id}: unknown recipe type {rtype!r}")
                continue
            result = recipe["result"]
            entries.append({
                "recipeId": recipe_id,
                "type": TYPE_MAP[rtype],
                "grid9": grid_of(recipe, rtype, recipe_id),
                "result": result["id"],
                "count": int(result.get("count", 1)),
            })

    if errors:
        print(f"[handbook_index_gen] {len(errors)} ERRORS:", file=sys.stderr)
        for e in errors:
            print("  - " + e, file=sys.stderr)
        return 1

    entries.sort(key=lambda e: e["recipeId"])
    lines = [json.dumps(e, ensure_ascii=False, separators=(",", ":")) for e in entries]
    os.makedirs(os.path.dirname(OUT_PATH), exist_ok=True)
    with open(OUT_PATH, "w", encoding="utf-8", newline="\n") as f:
        f.write("[\n" + ",\n".join(lines) + "\n]\n")
    print(f"[handbook_index_gen] wrote {os.path.relpath(OUT_PATH, ROOT)}: {len(entries)} recipes")
    return 0


if __name__ == "__main__":
    sys.exit(main())
