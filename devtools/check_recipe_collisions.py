#!/usr/bin/env python3
"""Recipe collision audit for COPPER INFERNO.

Detects crafting-input collisions: two crafting recipes (vanilla vs mod, or
mod vs mod) whose canonical input sets are identical but whose results differ.
Such pairs make one of the recipes uncraftable in game.

Canonicalization:
  - crafting_shaped:   pattern grid with each key char substituted by its
    canonical ingredient, trimmed of empty border rows/columns; the grid and
    its horizontal mirror are both computed and the lexicographically smaller
    one is used (vanilla shaped matching accepts horizontally mirrored
    placements).
  - crafting_shapeless: sorted multiset of canonical ingredients.

Vanilla recipes are read straight out of the Minecraft client jar with
zipfile. Exits non-zero listing every collision found.

Usage: python3 devtools/check_recipe_collisions.py [path-to-minecraft-client.jar]
"""

import glob
import json
import os
import sys
import zipfile

DEFAULT_JAR = os.path.expanduser(
    "~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"
)
MOD_RECIPE_ROOT = os.path.join(
    os.path.dirname(os.path.dirname(os.path.abspath(__file__))),
    "src/main/resources/data/copper_inferno/recipe",
)


def canon_ingredient(ing):
    """An ingredient is a string ('ns:item' or '#ns:tag') or a list of
    alternatives. Canonical form: sorted tuple of alternative strings."""
    if isinstance(ing, str):
        return (ing,)
    if isinstance(ing, list):
        alts = []
        for a in ing:
            alts.extend(canon_ingredient(a))
        return tuple(sorted(set(alts)))
    raise ValueError(f"unsupported ingredient format: {ing!r}")


def trim_grid(grid):
    """Drop fully-empty border rows/columns (shaped matching is
    translation-invariant within the crafting grid)."""
    while grid and all(c is None for c in grid[0]):
        grid = grid[1:]
    while grid and all(c is None for c in grid[-1]):
        grid = grid[:-1]
    while grid and all(row[0] is None for row in grid):
        grid = [row[1:] for row in grid]
    while grid and all(row[-1] is None for row in grid):
        grid = [row[:-1] for row in grid]
    return tuple(tuple(row) for row in grid)


def canon_recipe(recipe):
    """Return a hashable canonical input key for crafting recipes,
    or None for non-crafting recipe types."""
    rtype = recipe.get("type")
    if rtype == "minecraft:crafting_shaped":
        key = {k: canon_ingredient(v) for k, v in recipe["key"].items()}
        pattern = recipe["pattern"]
        width = max(len(row) for row in pattern)
        grid = []
        for row in pattern:
            row = row.ljust(width)
            grid.append([key[c] if c != " " else None for c in row])
        grid = trim_grid(grid)
        mirror = tuple(tuple(reversed(row)) for row in grid)
        return ("shaped", min(grid, mirror, key=repr))
    if rtype == "minecraft:crafting_shapeless":
        ings = tuple(sorted(canon_ingredient(i) for i in recipe["ingredients"]))
        return ("shapeless", ings)
    return None


def result_of(recipe):
    res = recipe.get("result", {})
    if isinstance(res, str):
        return (res, 1)
    return (res.get("id"), res.get("count", 1))


def load_vanilla(jar_path):
    recipes = {}
    with zipfile.ZipFile(jar_path) as jar:
        for name in jar.namelist():
            if name.startswith("data/minecraft/recipe/") and name.endswith(".json"):
                with jar.open(name) as fh:
                    recipes["minecraft:" + os.path.basename(name)[:-5]] = json.load(fh)
    return recipes


def load_mod(root):
    recipes = {}
    for path in sorted(glob.glob(os.path.join(root, "**", "*.json"), recursive=True)):
        rel = os.path.relpath(path, root).replace(os.sep, "/")[:-5]
        with open(path) as fh:
            recipes["copper_inferno:" + rel] = json.load(fh)
    return recipes


def main():
    jar_path = sys.argv[1] if len(sys.argv) > 1 else DEFAULT_JAR
    if not os.path.isfile(jar_path):
        print(f"ERROR: minecraft client jar not found: {jar_path}", file=sys.stderr)
        return 2

    vanilla = load_vanilla(jar_path)
    mod = load_mod(MOD_RECIPE_ROOT)

    index = {}  # canonical input key -> list of (recipe id, result)
    for source in (vanilla, mod):
        for rid, recipe in source.items():
            key = canon_recipe(recipe)
            if key is not None:
                index.setdefault(key, []).append((rid, result_of(recipe)))

    collisions = []
    for key, entries in index.items():
        mods = [e for e in entries if e[0].startswith("copper_inferno:")]
        if not mods:
            continue  # vanilla-vs-vanilla overlaps are not ours to police
        if len({res for _, res in entries}) > 1:
            collisions.append((key, entries))

    n_vanilla = sum(1 for r in vanilla.values() if canon_recipe(r) is not None)
    n_mod = sum(1 for r in mod.values() if canon_recipe(r) is not None)
    print(f"Indexed {n_vanilla} vanilla and {n_mod} mod crafting recipes.")

    if collisions:
        print(f"\nFOUND {len(collisions)} INPUT COLLISION(S):\n")
        for key, entries in collisions:
            print(f"  canonical input: {key}")
            for rid, (res_id, count) in entries:
                print(f"    - {rid}  ->  {count}x {res_id}")
            print()
        return 1

    print("No collisions: every mod crafting recipe has a unique input set.")
    return 0


if __name__ == "__main__":
    sys.exit(main())
