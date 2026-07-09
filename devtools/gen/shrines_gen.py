#!/usr/bin/env python3
"""Asset generator for the v4.1 "Shrines" feature.

One named worldgen feature (feature.shrines): copper_shrine, a small Overworld shrine —
a 5x5 stone-brick/copper dais, four copper-pillar columns crowned with ember lanterns and
a center chest rolling the copper_inferno:chests/copper_shrine loot table. The shrine
itself is built in code by CopperShrineWorldgenFeature, a custom
Feature<DefaultFeatureConfig> registered as copper_inferno:copper_shrine (same pattern as
devtools/gen/wildworld_gen.py's ruined_forge).

Emits (all deterministic, idempotent — run any number of times, same bytes):
  - worldgen: configured_feature/copper_shrine.json ({"type": "copper_inferno:copper_shrine",
    "config": {}}) + placed_feature/copper_shrine.json (rarity_filter chance 40, in_square,
    heightmap, biome — the ruined_forge placement stack with the height_range swapped for
    the vanilla desert_well-style surface heightmap)
  - loot: data/copper_inferno/loot_table/chests/copper_shrine.json (schema copied from the
    vanilla 1.21.9 chests/igloo_chest.json: copper ingots, pyrium nuggets from the gemalloy
    family, a Dr.Pepper)
  - lang fragments EN+DE (assets/copper_inferno/lang/fragments{,_de}/shrines.json) with the
    handbook keys for the shrine entry

Self-checks: every copper_inferno block the Java feature builds with must have a blockstate
on disk, and every copper_inferno loot item must have an items/<id>.json on disk.
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import NS, write_json  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# Blocks CopperShrineWorldgenFeature builds with (besides vanilla stone bricks + chest);
# each id MUST have assets/copper_inferno/blockstates/<id>.json on disk.
SHRINE_BLOCKS = ["copper_bricks", "copper_pillar", "ember_lantern"]

# copper_inferno items rolled by the chest loot table; each id MUST have
# assets/copper_inferno/items/<id>.json on disk (copper ingots are vanilla).
LOOT_ITEMS = ["pyrium_nugget", "dr_pepper"]


# ---------------------------------------------------------------------------
# Worldgen: configured + placed feature (ruined_forge pair with a heightmap placement)
# ---------------------------------------------------------------------------


def emit_worldgen() -> int:
    write_json(DATA / "worldgen" / "configured_feature" / "copper_shrine.json", {
        "type": f"{NS}:copper_shrine",
        "config": {},
    })
    write_json(DATA / "worldgen" / "placed_feature" / "copper_shrine.json", {
        "feature": f"{NS}:copper_shrine",
        "placement": [
            {"type": "minecraft:rarity_filter", "chance": 40},
            {"type": "minecraft:in_square"},
            {"type": "minecraft:heightmap", "heightmap": "MOTION_BLOCKING"},
            {"type": "minecraft:biome"},
        ],
    })
    return 2


# ---------------------------------------------------------------------------
# Chest loot (schema copied from the vanilla 1.21.9 chests/igloo_chest.json)
# ---------------------------------------------------------------------------


def count_uniform(lo: float, hi: float) -> dict:
    return {
        "add": False,
        "count": {"type": "minecraft:uniform", "max": hi, "min": lo},
        "function": "minecraft:set_count",
    }


def chest_loot() -> dict:
    return {
        "type": "minecraft:chest",
        "pools": [{
            "bonus_rolls": 0.0,
            "entries": [
                {
                    "type": "minecraft:item",
                    "functions": [count_uniform(2.0, 5.0)],
                    "name": "minecraft:copper_ingot",
                    "weight": 10,
                },
                {
                    "type": "minecraft:item",
                    "functions": [count_uniform(1.0, 3.0)],
                    "name": f"{NS}:pyrium_nugget",
                    "weight": 5,
                },
                {
                    "type": "minecraft:item",
                    "name": f"{NS}:dr_pepper",
                    "weight": 4,
                },
            ],
            "rolls": {"type": "minecraft:uniform", "max": 5.0, "min": 3.0},
        }],
        "random_sequence": f"{NS}:chests/copper_shrine",
    }


# ---------------------------------------------------------------------------
# Lang fragments (EN + real German): handbook keys for the shrine entry
# ---------------------------------------------------------------------------

LANG_EN = {
    f"handbook.{NS}.shrines_copper_shrine.title": "Copper Shrines",
}

LANG_DE = {
    f"handbook.{NS}.shrines_copper_shrine.title": "Kupferschreine",
}


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    for block in SHRINE_BLOCKS:
        path = ASSETS / "blockstates" / f"{block}.json"
        assert path.is_file(), f"shrine block {NS}:{block} has no blockstate at {path}"
    for item in LOOT_ITEMS:
        path = ASSETS / "items" / f"{item}.json"
        assert path.is_file(), f"loot item {NS}:{item} has no item definition at {path}"
    assert LANG_EN.keys() == LANG_DE.keys(), "EN/DE handbook key sets differ"

    feature_count = emit_worldgen()
    write_json(DATA / "loot_table" / "chests" / "copper_shrine.json", chest_loot())
    write_json(ASSETS / "lang" / "fragments" / "shrines.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "shrines.json", LANG_DE)

    print(f"shrines_gen: 1 worldgen feature pair ({feature_count} files), 1 chest loot "
          f"table, 2 lang fragments ({len(LANG_EN)} keys per locale); verified "
          f"{len(SHRINE_BLOCKS)} shrine blockstates + {len(LOOT_ITEMS)} loot items on disk.")


if __name__ == "__main__":
    main()
