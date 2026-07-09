#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4.1 "War Horns" feature.

Three battle horns registered by feature/warhorns/WarHornsFeature.java:
copper_war_horn (knockback blast), verdant_calm_horn (ally regen + extinguish),
doom_war_horn (hostile Slowness II + Darkness to the DOOM_KICK sample; the plain
id `doom_horn` is already taken by feature/artifacts).

Emits (all deterministic, idempotent — run any number of times, same bytes):
  - assets/copper_inferno/textures/item/<id>.png   16x16 sprites: the vanilla
    goat_horn sprite recolored onto three themed ramps via
    lib_gen.recolor/extract_vanilla (exactly like devtools/gen/fishing_gen.py)
  - assets/copper_inferno/models/item/<id>.json    (item/generated + layer0)
  - assets/copper_inferno/items/<id>.json          (1.21.9 item model definition)
  - data/copper_inferno/recipe/warhorns/<id>.json  3 shaped recipes; every input
    set contains a mod-unique id (horn_valve / verdigris_pearl / doom_alloy_shard)
    so no vanilla/mod collisions are possible (devtools/check_recipe_collisions.py)
  - assets/copper_inferno/lang/fragments/warhorns.json + fragments_de/warhorns.json
    (EN + real German)
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import (  # noqa: E402
    NS, _im, _it, _shade, extract_vanilla, item_def, merge, recolor,
    write_files, write_json,
)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

ITEMS = ["copper_war_horn", "verdant_calm_horn", "doom_war_horn"]

# ---------------------------------------------------------------------------
# Palettes (copper matches the copper gear family, verdant the verdigris teal
# used by fishing_gen, doom the bossdoom maroon family)
# ---------------------------------------------------------------------------

COPPER = (0xC1, 0x6C, 0x44)     # copper metal (fishing_gen / gear family)
VERDANT = (0x52, 0xA2, 0x84)    # vanilla oxidized-copper teal
DOOM = (0x7A, 0x1B, 0x22)       # bossdoom maroon


def ramp(base):
    """Dark -> light 5-step recolor ramp (same construction as fishing_gen)."""
    return [_shade(base, -72), _shade(base, -32), base, _shade(base, 40), _shade(base, 84)]


TINTS = {
    "copper_war_horn": COPPER,
    "verdant_calm_horn": VERDANT,
    "doom_war_horn": DOOM,
}

# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 crafting_shaped format, artifacts_gen style). Every
# input set contains a mod-unique item, so the canonical input set can never
# collide with a vanilla recipe (repo collision rule).
# ---------------------------------------------------------------------------


def shaped(key, pattern, result_id, count=1, category="equipment"):
    return {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": f"{NS}:{result_id}"},
    }


RECIPES = {
    "copper_war_horn": shaped(
        {"C": "minecraft:copper_ingot", "H": "minecraft:goat_horn",
         "V": f"{NS}:horn_valve"},
        [" C ", "CHC", " V "], "copper_war_horn"),
    "verdant_calm_horn": shaped(
        {"C": "minecraft:copper_ingot", "H": "minecraft:goat_horn",
         "P": f"{NS}:verdigris_pearl"},
        [" P ", "CHC", " C "], "verdant_calm_horn"),
    "doom_war_horn": shaped(
        {"C": "minecraft:copper_ingot", "H": "minecraft:goat_horn",
         "D": f"{NS}:doom_alloy_shard"},
        [" D ", "CHC", " C "], "doom_war_horn"),
}

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

LANG_EN = {
    "copper_war_horn": "Copper War Horn",
    "verdant_calm_horn": "Verdant Calm Horn",
    "doom_war_horn": "Doom War Horn",
}

LANG_DE = {
    "copper_war_horn": "Kupfer-Kriegshorn",
    "verdant_calm_horn": "Horn der Ruhe",
    "doom_war_horn": "Doom-Kriegshorn",
}

# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    assert set(ITEMS) == set(TINTS) == set(RECIPES) == set(LANG_EN) == set(LANG_DE), \
        "item tables out of sync"

    files = {}
    for item_id in ITEMS:
        files = merge(files, {
            _im(item_id): {"parent": "minecraft:item/generated",
                           "textures": {"layer0": f"{NS}:item/{item_id}"}},
            _it(item_id): item_def(f"{NS}:item/{item_id}"),
        })
    for recipe_id, recipe in RECIPES.items():
        files[f"data/{NS}/recipe/warhorns/{recipe_id}.json"] = recipe
    json_count = write_files(files, RES)

    goat_horn = extract_vanilla("assets/minecraft/textures/item/goat_horn.png")
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for item_id in ITEMS:
        recolor(goat_horn, ramp(TINTS[item_id])).save(tex_dir / f"{item_id}.png")

    write_json(ASSETS / "lang" / "fragments" / "warhorns.json",
               {f"item.{NS}.{i}": LANG_EN[i] for i in ITEMS})
    write_json(ASSETS / "lang" / "fragments_de" / "warhorns.json",
               {f"item.{NS}.{i}": LANG_DE[i] for i in ITEMS})

    print(f"warhorns_gen: {len(ITEMS)} items -> {json_count} JSON files "
          f"(defs/models/recipes), {len(ITEMS)} textures, 2 lang fragments.")


if __name__ == "__main__":
    main()
