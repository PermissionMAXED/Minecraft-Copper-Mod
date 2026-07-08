#!/usr/bin/env python3
"""Asset generator for the v4 "Wild World" feature (WP16).

8 named worldgen features (feature.wildworld): the verdigris_jungle + doom_basin Inferno
biomes, the ember_geyser and fumarole_vent blocks with their Doom Basin patches, the
copper_meteorite Overworld debris field with the new meteoric_iron block (+ the
meteoric_iron_chunk item), the smolder_crystal_cave ore cluster, the client-side ashfall
(no data of its own) and the ruined_forge custom Feature<DefaultFeatureConfig>.

Emits (all deterministic, idempotent — run any number of times, same bytes):
  - textures: 3 block PNGs (ember_geyser, fumarole_vent, meteoric_iron; lib_gen noise
    painters) + 1 item PNG (meteoric_iron_chunk: vanilla raw_iron recolored onto the
    meteoric ramp via lib_gen.recolor)
  - blockstates/models/items/loot for the 3 blocks (lib_gen emitters = verbatim vanilla
    1.21.9 formats); meteoric_iron gets the silk-touch/2-4-chunk loot table (structure
    copied from the shipped cinder_nest.json), the other two drop themselves
  - models/item + items/ for meteoric_iron_chunk
  - recipes under data/copper_inferno/recipe/wildworld/ — every crafting input set
    contains meteoric_iron_chunk or meteoric_iron (ids unique to WP16), so no vanilla or
    cross-work-package collisions are possible
  - worldgen: biome/verdigris_jungle.json + biome/doom_basin.json (cloned from the
    SHIPPED ember_grove.json structure with color/particle/feature overrides),
    configured+placed features patch_ember_geysers, patch_fumarole_vents,
    patch_verdigris_flora, copper_meteorite, smolder_crystal_cave, ruined_forge
    (schemas copied from the shipped infernodim/gemalloy worldgen JSON; the overworld
    placement uses the vanilla desert_well/forest_rock rarity+heightmap stack extracted
    from the client jar)
  - patches data/copper_inferno/dimension/inferno.json: appends the two new biome ids to
    the checkerboard biome list IF the generator's biome source is the extensible
    minecraft:checkerboard list (it is); no-op when already present
  - lang fragments EN+DE (assets/copper_inferno/lang/fragments{,_de}/wildworld.json)
  - the vanilla-tag fragment devtools/tagfrag/wildworld.json for merge_tags.py

FeatureSorter safety: per generation step, every biome's placed-feature list stays a
subsequence of one master order — the new biomes copy ember_grove's step-7/step-9 lists
verbatim and only APPEND new WP16-unique features at the end, so the ordering across the
five Inferno biomes is cycle-free.
"""

import json
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import (  # noqa: E402
    NS, _im, _it, _lt, _shade, block_ref, emit_cube, extract_vanilla, item_def, merge,
    noise_cube, recolor, seeded, sprinkle, write_files, write_json,
)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
TAGFRAG = ROOT / "devtools" / "tagfrag"

# ---------------------------------------------------------------------------
# Palette
# ---------------------------------------------------------------------------

EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_HOT = (0xFF, 0xC2, 0x6B)
COPPER = (0xE0, 0x73, 0x4D)

CINDER_DARK = (0x2E, 0x24, 0x22)
CINDER = (0x3B, 0x2E, 0x2B)
CINDER_LIGHT = (0x4A, 0x39, 0x34)
CINDER_MORTAR = (0x1E, 0x15, 0x13)

SLAG_DARK = (0x3E, 0x3A, 0x38)
SLAG = (0x4C, 0x48, 0x46)
SLAG_LIGHT = (0x57, 0x52, 0x4F)

METEOR_BASE = (0x3C, 0x3A, 0x42)  # dark space-iron violet-gray


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic via lib_gen.seeded)
# ---------------------------------------------------------------------------


def tex_ember_geyser():
    """Cinderstone crater with a glowing vent throat in the middle."""
    rng = seeded("ember_geyser")
    img = noise_cube(rng, [CINDER_DARK, CINDER, CINDER, CINDER_LIGHT])
    # crater rim ring (square, radius 4)
    for i in range(4, 12):
        for x, y in ((i, 4), (i, 11), (4, i), (11, i)):
            img.putpixel((x, y), CINDER_MORTAR)
    # glowing throat
    for y in range(6, 10):
        for x in range(6, 10):
            img.putpixel((x, y), EMBER if (x + y) % 2 == 0 else EMBER_BRIGHT)
    for x, y in ((7, 7), (8, 8)):
        img.putpixel((x, y), EMBER_HOT)
    # spatter around the rim
    ring_px = [(x, y) for y in range(3, 13) for x in range(3, 13)
               if min(x, y) == 3 or max(x, y) == 12]
    sprinkle(img, rng, ring_px, [EMBER, EMBER_BRIGHT], 0.18)
    return img


def tex_fumarole_vent():
    """Banded slag chimney face with a dark smoke hole and pale ash dusting."""
    rng = seeded("fumarole_vent")
    img = noise_cube(rng, [SLAG_DARK, SLAG, SLAG, SLAG_LIGHT])
    # dark central flue (6x6) with a sooty 1px rim
    for i in range(4, 12):
        for x, y in ((i, 4), (i, 11), (4, i), (11, i)):
            img.putpixel((x, y), (0x2A, 0x27, 0x25))
    for y in range(5, 11):
        for x in range(5, 11):
            img.putpixel((x, y), (0x1A, 0x18, 0x17) if (x + y) % 3 else (0x22, 0x1F, 0x1E))
    # faint inner glow at the throat bottom
    for x in (7, 8):
        img.putpixel((x, 10), (0x6E, 0x3A, 0x1E))
    # ash dusting on the shoulders
    edge_px = [(x, y) for y in range(16) for x in range(16) if min(x, y, 15 - x, 15 - y) <= 1]
    sprinkle(img, rng, edge_px, [(0xB1, 0xAB, 0xA4), (0x96, 0x8F, 0x88)], 0.12)
    return img


def tex_meteoric_iron():
    """Pitted space-iron: dark violet-gray mottle, deep pits, copper impact flecks."""
    rng = seeded("meteoric_iron")
    shades = [_shade(METEOR_BASE, -18), METEOR_BASE, METEOR_BASE, _shade(METEOR_BASE, 20)]
    img = noise_cube(rng, shades)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    sprinkle(img, rng, all_px, [_shade(METEOR_BASE, -34)], 0.10)  # pits
    sprinkle(img, rng, all_px, [COPPER, _shade(COPPER, -28)], 0.05)  # copper flecks
    sprinkle(img, rng, all_px, [_shade(METEOR_BASE, 52)], 0.03)  # sheen sparks
    return img


def emit_textures() -> int:
    block_dir = ASSETS / "textures" / "block"
    item_dir = ASSETS / "textures" / "item"
    block_dir.mkdir(parents=True, exist_ok=True)
    item_dir.mkdir(parents=True, exist_ok=True)
    tex_ember_geyser().save(block_dir / "ember_geyser.png")
    tex_fumarole_vent().save(block_dir / "fumarole_vent.png")
    tex_meteoric_iron().save(block_dir / "meteoric_iron.png")
    chunk_ramp = [_shade(METEOR_BASE, -52), _shade(METEOR_BASE, -20), METEOR_BASE,
                  _shade(METEOR_BASE, 32), _shade(METEOR_BASE, 76)]
    recolor(extract_vanilla("assets/minecraft/textures/item/raw_iron.png"),
            chunk_ramp).save(item_dir / "meteoric_iron_chunk.png")
    return 4


# ---------------------------------------------------------------------------
# Blocks / item assets + loot
# ---------------------------------------------------------------------------


def meteoric_iron_loot() -> dict:
    """Silk touch -> the block itself, otherwise 2-4 meteoric_iron_chunk. Structure copied
    verbatim from the shipped data/copper_inferno/loot_table/blocks/cinder_nest.json."""
    return {
        "pools": [{
            "bonus_rolls": 0.0,
            "entries": [{
                "type": "minecraft:alternatives",
                "children": [
                    {
                        "type": "minecraft:item",
                        "conditions": [{
                            "condition": "minecraft:match_tool",
                            "predicate": {
                                "predicates": {
                                    "minecraft:enchantments": [{
                                        "enchantments": "minecraft:silk_touch",
                                        "levels": {"min": 1},
                                    }],
                                },
                            },
                        }],
                        "name": f"{NS}:meteoric_iron",
                    },
                    {
                        "type": "minecraft:item",
                        "functions": [
                            {
                                "add": False,
                                "count": {"type": "minecraft:uniform", "max": 4.0, "min": 2.0},
                                "function": "minecraft:set_count",
                            },
                            {"function": "minecraft:explosion_decay"},
                        ],
                        "name": f"{NS}:meteoric_iron_chunk",
                    },
                ],
            }],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/meteoric_iron",
        "type": "minecraft:block",
    }


def emit_block_assets() -> dict:
    files = merge(
        emit_cube("ember_geyser"),
        emit_cube("fumarole_vent"),
        emit_cube("meteoric_iron"),
    )
    files[_lt("meteoric_iron")] = meteoric_iron_loot()  # replace the drop-self table
    files = merge(files, {
        _im("meteoric_iron_chunk"): {"parent": "minecraft:item/generated",
                                     "textures": {"layer0": f"{NS}:item/meteoric_iron_chunk"}},
        _it("meteoric_iron_chunk"): item_def(f"{NS}:item/meteoric_iron_chunk"),
    })
    return files


# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 formats, same shapes as devtools/gen/gemalloy_gen.py).
# Unique-input rule: every crafting input set contains meteoric_iron_chunk or
# meteoric_iron, both unique to WP16.
# ---------------------------------------------------------------------------


def emit_recipes() -> int:
    rdir = DATA / "recipe" / "wildworld"
    recipes = {
        "meteoric_iron": {
            "type": "minecraft:crafting_shaped", "category": "building",
            "key": {"#": f"{NS}:meteoric_iron_chunk"},
            "pattern": ["###", "###", "###"],
            "result": {"count": 1, "id": f"{NS}:meteoric_iron"},
        },
        "meteoric_iron_chunk_from_block": {
            "type": "minecraft:crafting_shapeless", "category": "misc",
            "ingredients": [f"{NS}:meteoric_iron"],
            "result": {"count": 9, "id": f"{NS}:meteoric_iron_chunk"},
        },
        "iron_ingot_from_smelting_meteoric_iron_chunk": {
            "type": "minecraft:smelting", "category": "misc", "cookingtime": 200,
            "experience": 0.7, "ingredient": f"{NS}:meteoric_iron_chunk",
            "result": {"id": "minecraft:iron_ingot"},
        },
        "ember_geyser": {
            "type": "minecraft:crafting_shaped", "category": "building",
            "key": {"C": f"{NS}:cobbled_cinderstone", "M": f"{NS}:meteoric_iron_chunk",
                    "L": "minecraft:magma_block"},
            "pattern": ["CMC", "MLM", "CMC"],
            "result": {"count": 1, "id": f"{NS}:ember_geyser"},
        },
        "fumarole_vent": {
            "type": "minecraft:crafting_shaped", "category": "building",
            "key": {"C": f"{NS}:cobbled_cinderstone", "M": f"{NS}:meteoric_iron_chunk"},
            "pattern": ["CMC", "C C", "CCC"],
            "result": {"count": 1, "id": f"{NS}:fumarole_vent"},
        },
    }
    for name, obj in recipes.items():
        write_json(rdir / f"{name}.json", obj)
    return len(recipes)


# ---------------------------------------------------------------------------
# Worldgen: configured + placed features (schemas copied from the shipped
# infernodim/gemalloy worldgen JSON and the vanilla desert_well placement stack)
# ---------------------------------------------------------------------------

PLACE_IN_SQUARE = {"type": "minecraft:in_square"}
PLACE_BIOME = {"type": "minecraft:biome"}


def height_uniform(min_spec, max_spec):
    return {"type": "minecraft:height_range",
            "height": {"type": "minecraft:uniform",
                       "min_inclusive": min_spec, "max_inclusive": max_spec}}


HEIGHT_4_4 = height_uniform({"above_bottom": 4}, {"below_top": 4})


def simple_state(name: str):
    return {"type": "minecraft:simple_state_provider", "state": {"Name": name}}


def weighted_states(entries):
    return {"type": "minecraft:weighted_state_provider",
            "entries": [{"data": {"Name": name}, "weight": weight} for name, weight in entries]}


def random_patch(to_place, predicate, tries=96, xz_spread=7, y_spread=3):
    return {
        "type": "minecraft:random_patch",
        "config": {
            "feature": {
                "feature": {"type": "minecraft:simple_block", "config": {"to_place": to_place}},
                "placement": [{"type": "minecraft:block_predicate_filter",
                               "predicate": predicate}],
            },
            "tries": tries,
            "xz_spread": xz_spread,
            "y_spread": y_spread,
        },
    }


def on_ground(ground_blocks):
    return {"type": "minecraft:all_of", "predicates": [
        {"type": "minecraft:matching_blocks", "blocks": "minecraft:air"},
        {"type": "minecraft:matching_blocks", "blocks": ground_blocks, "offset": [0, -1, 0]},
    ]}


def emit_feature(name: str, configured, placement) -> None:
    write_json(DATA / "worldgen" / "configured_feature" / f"{name}.json", configured)
    write_json(DATA / "worldgen" / "placed_feature" / f"{name}.json",
               {"feature": f"{NS}:{name}", "placement": placement})


# Doom Basin floors after the ember_grove-derived surface rules: cinderstone, ember soil,
# ash, moss/wart tops (same GROUND_GROVE set as devtools/gen/infernodim_gen.py).
GROUND_BASIN = [f"{NS}:cinderstone", f"{NS}:ember_soil", f"{NS}:ash_block",
                f"{NS}:ember_moss_block", f"{NS}:ember_wart_block"]

# Common Overworld surface blocks the meteorite debris may rest on.
GROUND_OVERWORLD = ["minecraft:grass_block", "minecraft:dirt", "minecraft:coarse_dirt",
                    "minecraft:podzol", "minecraft:mycelium", "minecraft:sand",
                    "minecraft:red_sand", "minecraft:gravel", "minecraft:stone",
                    "minecraft:moss_block", "minecraft:mud", "minecraft:snow_block"]


def count_uniform(lo: int, hi: int):
    return {"type": "minecraft:count",
            "count": {"type": "minecraft:uniform", "min_inclusive": lo, "max_inclusive": hi}}


def emit_worldgen_features() -> int:
    # 3) ember geyser fields on Doom Basin floors.
    emit_feature("patch_ember_geysers",
                 random_patch(simple_state(f"{NS}:ember_geyser"), on_ground(GROUND_BASIN),
                              tries=6, xz_spread=4, y_spread=3),
                 [count_uniform(0, 3), PLACE_IN_SQUARE, HEIGHT_4_4, PLACE_BIOME])
    # 5) fumarole vents on Doom Basin floors.
    emit_feature("patch_fumarole_vents",
                 random_patch(simple_state(f"{NS}:fumarole_vent"), on_ground(GROUND_BASIN),
                              tries=5, xz_spread=4, y_spread=3),
                 [count_uniform(0, 2), PLACE_IN_SQUARE, HEIGHT_4_4, PLACE_BIOME])
    # 1) dense Verdigris Jungle undergrowth (existing infernoflora blocks, jungle density).
    emit_feature("patch_verdigris_flora",
                 random_patch(weighted_states([
                     (f"{NS}:ember_fungus", 4), (f"{NS}:spore_cluster", 4),
                     (f"{NS}:smolder_bloom", 3), (f"{NS}:cinder_roots", 3),
                     (f"{NS}:ashen_grass", 2)]),
                     on_ground(GROUND_BASIN)),
                 [{"type": "minecraft:count_on_every_layer", "count": 10}, PLACE_BIOME])
    # 4) rare Overworld copper meteorite debris field (rarity+heightmap stack from the
    # vanilla desert_well/forest_rock placed features).
    emit_feature("copper_meteorite",
                 random_patch(weighted_states([("minecraft:raw_copper_block", 3),
                                               (f"{NS}:meteoric_iron", 2)]),
                              on_ground(GROUND_OVERWORLD),
                              tries=24, xz_spread=3, y_spread=2),
                 [{"type": "minecraft:rarity_filter", "chance": 32}, PLACE_IN_SQUARE,
                  {"type": "minecraft:heightmap", "heightmap": "MOTION_BLOCKING"},
                  PLACE_BIOME])
    # 6) smolder crystal caves: huge clusters of the EXISTING smolder_crystal_ore (ore
    # schema from the shipped smolder_crystal_ore.json, size 8 -> 24, rarity-gated).
    write_json(DATA / "worldgen" / "configured_feature" / "smolder_crystal_cave.json", {
        "type": "minecraft:ore",
        "config": {
            "discard_chance_on_air_exposure": 0.0,
            "size": 24,
            "targets": [{
                "state": {"Name": f"{NS}:smolder_crystal_ore"},
                "target": {"block": f"{NS}:cinderstone",
                           "predicate_type": "minecraft:block_match"},
            }],
        },
    })
    write_json(DATA / "worldgen" / "placed_feature" / "smolder_crystal_cave.json", {
        "feature": f"{NS}:smolder_crystal_cave",
        "placement": [
            {"type": "minecraft:rarity_filter", "chance": 3},
            PLACE_IN_SQUARE,
            height_uniform({"absolute": 10}, {"absolute": 70}),
            PLACE_BIOME,
        ],
    })
    # 8) ruined forges: the custom copper_inferno:ruined_forge Feature<DefaultFeatureConfig>
    # (config {} like the vanilla DefaultFeatureConfig features, e.g. freeze_top_layer).
    write_json(DATA / "worldgen" / "configured_feature" / "ruined_forge.json", {
        "type": f"{NS}:ruined_forge",
        "config": {},
    })
    write_json(DATA / "worldgen" / "placed_feature" / "ruined_forge.json", {
        "feature": f"{NS}:ruined_forge",
        "placement": [
            {"type": "minecraft:rarity_filter", "chance": 5},
            PLACE_IN_SQUARE,
            height_uniform({"absolute": 34}, {"absolute": 80}),
            PLACE_BIOME,
        ],
    })
    return 6


# ---------------------------------------------------------------------------
# Biomes: clone the SHIPPED ember_grove.json structure, override colors/particles
# and feature lists. Step lists copy ember_grove verbatim and only APPEND
# WP16-unique placed features at the end (FeatureSorter-safe subsequences).
# ---------------------------------------------------------------------------

BIOME_OVERRIDES = {
    "verdigris_jungle": {
        "effects": {
            "fog_color": 0x1C3A2C, "sky_color": 0x6FB08E,
            "water_color": 0x4E9E7A, "water_fog_color": 0x123322,
            "particle": {"options": {"type": "minecraft:warped_spore"}, "probability": 0.045},
        },
        "append_step_7": [],
        "step_9": ["copper_inferno:spring_lava", "copper_inferno:patch_ember_flora",
                   "copper_inferno:patch_ember_moss", "copper_inferno:patch_cinder_nest",
                   "copper_inferno:patch_verdigris_flora"],
    },
    "doom_basin": {
        "effects": {
            "fog_color": 0x3A0D08, "sky_color": 0x9A2228,
            "water_color": 0x6F3030, "water_fog_color": 0x2A0A08,
            "particle": {"options": {"type": "minecraft:ash"}, "probability": 0.06},
            "ambient_sound": "minecraft:ambient.basalt_deltas.loop",
            "additions_sound": {"sound": "minecraft:ambient.basalt_deltas.additions",
                                "tick_chance": 0.0111},
            "mood_sound": {"block_search_extent": 8, "offset": 2.0,
                           "sound": "minecraft:ambient.basalt_deltas.mood",
                           "tick_delay": 6000},
        },
        "append_step_7": ["copper_inferno:patch_ember_geysers",
                          "copper_inferno:patch_fumarole_vents"],
        "step_9": ["copper_inferno:spring_lava"],
    },
}


def emit_biomes() -> int:
    template_path = DATA / "worldgen" / "biome" / "ember_grove.json"
    template = json.loads(template_path.read_text(encoding="utf-8"))
    for biome_id, spec in BIOME_OVERRIDES.items():
        biome = json.loads(json.dumps(template))  # deep copy
        biome["effects"].update(spec["effects"])
        features = [list(step) for step in biome["features"]]
        features[7] = features[7] + spec["append_step_7"]
        features[9] = spec["step_9"]
        biome["features"] = features
        write_json(DATA / "worldgen" / "biome" / f"{biome_id}.json", biome)
    return len(BIOME_OVERRIDES)


def patch_dimension() -> str:
    """Append the two new biomes to dimension/inferno.json IF its biome source is the
    extensible minecraft:checkerboard list (per WP16 charter). Idempotent."""
    dim_path = DATA / "dimension" / "inferno.json"
    dim = json.loads(dim_path.read_text(encoding="utf-8"))
    source = dim.get("generator", {}).get("biome_source", {})
    if source.get("type") != "minecraft:checkerboard" or not isinstance(source.get("biomes"), list):
        return "dimension NOT patched (biome source is not an extensible checkerboard list)"
    added = []
    for biome_id in (f"{NS}:verdigris_jungle", f"{NS}:doom_basin"):
        if biome_id not in source["biomes"]:
            source["biomes"].append(biome_id)
            added.append(biome_id)
    write_json(dim_path, dim)
    return f"dimension patched (+{len(added)} biomes)" if added else "dimension already patched"


# ---------------------------------------------------------------------------
# Lang fragments (EN + real German)
# ---------------------------------------------------------------------------

LANG_EN = {
    f"block.{NS}.ember_geyser": "Ember Geyser",
    f"block.{NS}.fumarole_vent": "Fumarole Vent",
    f"block.{NS}.meteoric_iron": "Meteoric Iron",
    f"item.{NS}.meteoric_iron_chunk": "Meteoric Iron Chunk",
    f"biome.{NS}.verdigris_jungle": "Verdigris Jungle",
    f"biome.{NS}.doom_basin": "Doom Basin",
}

LANG_DE = {
    f"block.{NS}.ember_geyser": "Glutgeysir",
    f"block.{NS}.fumarole_vent": "Fumarolenschlot",
    f"block.{NS}.meteoric_iron": "Meteoreisen",
    f"item.{NS}.meteoric_iron_chunk": "Meteoreisen-Brocken",
    f"biome.{NS}.verdigris_jungle": "Grünspandschungel",
    f"biome.{NS}.doom_basin": "Doom-Becken",
}


# ---------------------------------------------------------------------------
# Tag fragment (merged into data/minecraft/tags by devtools/merge_tags.py)
# ---------------------------------------------------------------------------


def emit_tagfrag() -> None:
    write_json(TAGFRAG / "wildworld.json", {
        "block/mineable/pickaxe": [
            f"{NS}:ember_geyser",
            f"{NS}:fumarole_vent",
            f"{NS}:meteoric_iron",
        ],
        "block/needs_stone_tool": [
            f"{NS}:meteoric_iron",
        ],
    })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    files = emit_block_assets()
    json_count = write_files(files, RES)
    png_count = emit_textures()
    recipe_count = emit_recipes()
    feature_count = emit_worldgen_features()
    biome_count = emit_biomes()
    dim_note = patch_dimension()
    write_json(ASSETS / "lang" / "fragments" / "wildworld.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "wildworld.json", LANG_DE)
    emit_tagfrag()

    blockstates = [p for p in files if "/blockstates/" in p]
    items = [p for p in files if f"assets/{NS}/items/" in p]
    loot = [p for p in files if "/loot_table/" in p]
    assert len(blockstates) == 3, f"expected 3 blockstates, got {len(blockstates)}"
    assert len(loot) == 3, f"expected 3 loot tables, got {len(loot)}"
    assert len(items) == 4, f"expected 4 item definitions, got {len(items)}"
    assert len(LANG_EN) == len(LANG_DE) == 6, "expected 6 lang keys per locale"
    print(f"wildworld_gen: {len(blockstates)} blocks + 1 item -> {json_count} asset/loot "
          f"JSON files, {png_count} textures, {recipe_count} recipes, {feature_count} "
          f"worldgen feature pairs, {biome_count} biomes, {dim_note}, 2 lang fragments, "
          "1 tag fragment.")


if __name__ == "__main__":
    main()
