#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "infernodim" feature (the Inferno dimension).

Idempotent: running it any number of times produces byte-identical files. Emits into
src/main/resources:
  - 16x16 block textures + the infernium_igniter item texture (Pillow, seeded noise) - DEFAULT
  - behind --write-json (legacy scaffolding; the JSON in src/main/resources is authoritative):
      blockstates, block/item models, item model-definitions (assets/copper_inferno/...)
      loot tables (data/copper_inferno/loot_table/blocks/...)
      recipes (data/copper_inferno/recipe/infernodim/...)
      lang fragments (assets/copper_inferno/lang/fragments{,_de}/infernodim.json)
      the data-driven dimension: dimension/, dimension_type/, worldgen/{noise_settings,
      biome,configured_feature,placed_feature} (data/copper_inferno/...)

The dimension JSON is NOT invented: every file is derived from the real vanilla 1.21.9
schemas extracted straight out of fabric-loom's minecraft-client.jar with zipfile
(the_nether dimension_type, nether noise_settings, nether_wastes/crimson_forest/
basalt_deltas biomes, ore_nether_gold/ore_magma configured+placed features), with only
the documented substitutions applied (block names, colors, spawners, appended ores).
Blockstate/model/recipe JSON structures are exact copies of the vanilla formats
(nether_portal blockstate + models, stone-family recipes).
"""

import json
import os
import sys
import zipfile
from pathlib import Path
from random import Random

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> textures/*.png only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
CLIENT_JAR = Path(os.path.expanduser("~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"))

# ---------------------------------------------------------------------------
# Palette
# ---------------------------------------------------------------------------
EMBER = (0xE2, 0x58, 0x22)           # #E25822
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)    # #FF7A2F
EMBER_HOT = (0xFF, 0xC2, 0x6B)
VERDIGRIS = (0x6F, 0xB0, 0x8E)
VERDIGRIS_DARK = (0x4E, 0x9E, 0x7A)
COPPER = (0xE0, 0x73, 0x4D)
COPPER_DARK = (0xC1, 0x5A, 0x3B)

CINDER_DARK = (0x2E, 0x24, 0x22)
CINDER = (0x3B, 0x2E, 0x2B)
CINDER_LIGHT = (0x4A, 0x39, 0x34)
CINDER_MORTAR = (0x1E, 0x15, 0x13)

ASH_LIGHT = (0xC9, 0xC4, 0xBE)
ASH = (0xB1, 0xAB, 0xA4)
ASH_DARK = (0x96, 0x8F, 0x88)

SIMPLE_CUBES = [
    "cinderstone", "cobbled_cinderstone", "slagstone", "ash_block", "ember_soil",
    "scorched_sand", "cinder_gravel", "infernium_ore", "smolder_crystal_ore",
    "molten_slag", "infernium_portal_frame",
]

LANG_EN = {
    "block.copper_inferno.cinderstone": "Cinderstone",
    "block.copper_inferno.cobbled_cinderstone": "Cobbled Cinderstone",
    "block.copper_inferno.slagstone": "Slagstone",
    "block.copper_inferno.ash_block": "Ash Block",
    "block.copper_inferno.ember_soil": "Ember Soil",
    "block.copper_inferno.scorched_sand": "Scorched Sand",
    "block.copper_inferno.cinder_gravel": "Cinder Gravel",
    "block.copper_inferno.infernium_ore": "Infernium Ore",
    "block.copper_inferno.smolder_crystal_ore": "Smolder Crystal Ore",
    "block.copper_inferno.molten_slag": "Molten Slag",
    "block.copper_inferno.infernium_portal_frame": "Infernium Portal Frame",
    "block.copper_inferno.inferno_portal": "Inferno Portal",
    "item.copper_inferno.infernium_igniter": "Infernium Igniter",
    "biome.copper_inferno.cinder_wastes": "Cinder Wastes",
    "biome.copper_inferno.ember_grove": "Ember Grove",
    "biome.copper_inferno.slag_sea": "Slag Sea",
}

LANG_DE = {
    "block.copper_inferno.cinderstone": "Aschenstein",
    "block.copper_inferno.cobbled_cinderstone": "Gepflasterter Aschenstein",
    "block.copper_inferno.slagstone": "Schlackenstein",
    "block.copper_inferno.ash_block": "Ascheblock",
    "block.copper_inferno.ember_soil": "Glutboden",
    "block.copper_inferno.scorched_sand": "Verbrannter Sand",
    "block.copper_inferno.cinder_gravel": "Aschenkies",
    "block.copper_inferno.infernium_ore": "Infernium-Erz",
    "block.copper_inferno.smolder_crystal_ore": "Schwelkristall-Erz",
    "block.copper_inferno.molten_slag": "Geschmolzene Schlacke",
    "block.copper_inferno.infernium_portal_frame": "Infernium-Portalrahmen",
    "block.copper_inferno.inferno_portal": "Inferno-Portal",
    "item.copper_inferno.infernium_igniter": "Infernium-Anzünder",
    "biome.copper_inferno.cinder_wastes": "Aschenöde",
    "biome.copper_inferno.ember_grove": "Gluthain",
    "biome.copper_inferno.slag_sea": "Schlackenmeer",
}


def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False) + "\n",
                    encoding="utf-8")


def block_ref(name: str) -> str:
    return f"{NS}:block/{name}"


def jar_json(entry: str):
    """Load a vanilla JSON file straight out of the 1.21.9 minecraft-client.jar."""
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        return json.loads(jar.read(entry).decode("utf-8"))


# ---------------------------------------------------------------------------
# Blockstates / models / item definitions / loot tables
# ---------------------------------------------------------------------------

def emit_cube(name: str) -> None:
    write_json(ASSETS / "blockstates" / f"{name}.json",
               {"variants": {"": {"model": block_ref(name)}}})
    write_json(ASSETS / "models" / "block" / f"{name}.json",
               {"parent": "minecraft:block/cube_all", "textures": {"all": block_ref(name)}})
    write_json(ASSETS / "items" / f"{name}.json",
               {"model": {"type": "minecraft:model", "model": block_ref(name)}})


def emit_portal() -> None:
    """inferno_portal: vanilla nether_portal blockstate/model structure (axis=x|z panes).
    The registered BlockItem gets an item/generated model so it renders in inventories."""
    name = "inferno_portal"
    write_json(ASSETS / "blockstates" / f"{name}.json", {"variants": {
        "axis=x": {"model": block_ref(f"{name}_ns")},
        "axis=z": {"model": block_ref(f"{name}_ew")},
    }})
    tex = {"particle": block_ref(name), "portal": block_ref(name)}
    write_json(ASSETS / "models" / "block" / f"{name}_ns.json", {
        "textures": tex,
        "elements": [{
            "from": [0, 0, 6], "to": [16, 16, 10],
            "faces": {
                "north": {"uv": [0, 0, 16, 16], "texture": "#portal"},
                "south": {"uv": [0, 0, 16, 16], "texture": "#portal"},
            },
        }],
    })
    write_json(ASSETS / "models" / "block" / f"{name}_ew.json", {
        "textures": tex,
        "elements": [{
            "from": [6, 0, 0], "to": [10, 16, 16],
            "faces": {
                "east": {"uv": [0, 0, 16, 16], "texture": "#portal"},
                "west": {"uv": [0, 0, 16, 16], "texture": "#portal"},
            },
        }],
    })
    write_json(ASSETS / "models" / "item" / f"{name}.json",
               {"parent": "minecraft:item/generated", "textures": {"layer0": block_ref(name)}})
    write_json(ASSETS / "items" / f"{name}.json",
               {"model": {"type": "minecraft:model", "model": f"{NS}:item/{name}"}})


def emit_igniter() -> None:
    name = "infernium_igniter"
    write_json(ASSETS / "models" / "item" / f"{name}.json",
               {"parent": "minecraft:item/generated", "textures": {"layer0": f"{NS}:item/{name}"}})
    write_json(ASSETS / "items" / f"{name}.json",
               {"model": {"type": "minecraft:model", "model": f"{NS}:item/{name}"}})


def emit_drop_self_loot(name: str) -> None:
    write_json(DATA / "loot_table" / "blocks" / f"{name}.json", {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "conditions": [{"condition": "minecraft:survives_explosion"}],
            "entries": [{"type": "minecraft:item", "name": f"{NS}:{name}"}],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{name}",
    })


def emit_portal_loot() -> None:
    # Unbreakable pane: empty pools (vanilla nether_portal has no pools at all).
    write_json(DATA / "loot_table" / "blocks" / "inferno_portal.json", {
        "type": "minecraft:block",
        "pools": [],
        "random_sequence": f"{NS}:blocks/inferno_portal",
    })


# ---------------------------------------------------------------------------
# Recipes (vanilla formats: crafting_shaped / smelting / stonecutting)
# ---------------------------------------------------------------------------

def recipe_path(name: str) -> Path:
    return DATA / "recipe" / "infernodim" / f"{name}.json"


def emit_recipes() -> None:
    write_json(recipe_path("infernium_igniter"), {
        "type": "minecraft:crafting_shaped",
        "category": "equipment",
        "key": {"F": "minecraft:flint", "C": "minecraft:copper_ingot", "B": "minecraft:blaze_rod"},
        "pattern": ["F", "C", "B"],
        "result": {"count": 1, "id": f"{NS}:infernium_igniter"},
    })
    write_json(recipe_path("infernium_portal_frame"), {
        "type": "minecraft:crafting_shaped",
        "category": "building",
        "key": {"C": f"{NS}:cinderstone", "I": "minecraft:copper_ingot", "M": "minecraft:magma_block"},
        "pattern": ["CIC", "IMI", "CIC"],
        "result": {"count": 4, "id": f"{NS}:infernium_portal_frame"},
    })
    write_json(recipe_path("cobbled_cinderstone"), {
        "type": "minecraft:crafting_shaped",
        "category": "building",
        "key": {"#": f"{NS}:cinderstone"},
        "pattern": ["##", "##"],
        "result": {"count": 4, "id": f"{NS}:cobbled_cinderstone"},
    })
    # Smelting format copied from vanilla cracked_stone_bricks.
    write_json(recipe_path("cinderstone_from_smelting"), {
        "type": "minecraft:smelting",
        "category": "blocks",
        "cookingtime": 200,
        "experience": 0.1,
        "ingredient": f"{NS}:cobbled_cinderstone",
        "result": {"id": f"{NS}:cinderstone"},
    })
    # Stonecutting format copied from vanilla stone_brick_stairs_from_stone_bricks_stonecutting.
    write_json(recipe_path("cobbled_cinderstone_from_cinderstone_stonecutting"), {
        "type": "minecraft:stonecutting",
        "ingredient": f"{NS}:cinderstone",
        "result": {"count": 1, "id": f"{NS}:cobbled_cinderstone"},
    })


# ---------------------------------------------------------------------------
# Dimension JSON (derived from extracted vanilla schemas -- NOT invented)
# ---------------------------------------------------------------------------

def rename_block_states(node, mapping: dict):
    """Replace block ids inside {"Name": ...} state dicts only (leaves noise ids etc. alone)."""
    if isinstance(node, dict):
        for key, value in node.items():
            if key == "Name" and isinstance(value, str) and value in mapping:
                node[key] = mapping[value]
            else:
                rename_block_states(value, mapping)
    elif isinstance(node, list):
        for value in node:
            rename_block_states(value, mapping)


def emit_dimension_type() -> None:
    # Copy of vanilla the_nether with a slightly brighter ambient light.
    dim_type = jar_json("data/minecraft/dimension_type/the_nether.json")
    dim_type["ambient_light"] = 0.12
    dim_type["effects"] = "minecraft:the_nether"
    write_json(DATA / "dimension_type" / "inferno.json", dim_type)


def emit_noise_settings() -> None:
    # Copy of vanilla nether noise settings; ONLY block substitutions, nothing else.
    noise = jar_json("data/minecraft/worldgen/noise_settings/nether.json")
    rename_block_states(noise, {
        "minecraft:netherrack": f"{NS}:cinderstone",
        "minecraft:soul_sand": f"{NS}:ash_block",
        "minecraft:soul_soil": f"{NS}:ember_soil",
        "minecraft:gravel": f"{NS}:cinder_gravel",
    })
    write_json(DATA / "worldgen" / "noise_settings" / "inferno.json", noise)


# (biome id, vanilla source, effect color overrides)
BIOMES = [
    ("cinder_wastes", "nether_wastes", {
        "fog_color": 0x4A1E0A, "sky_color": 0xFF7A2F,
        "water_color": 0x57A07B, "water_fog_color": 0x0E2418,
    }),
    ("ember_grove", "crimson_forest", {
        "fog_color": 0x3A1206, "sky_color": 0xE25822,
        "water_color": 0x4E9E7A, "water_fog_color": 0x123322,
    }),
    ("slag_sea", "basalt_deltas", {
        "fog_color": 0x8A8380, "sky_color": 0x6E6864,
        "water_color": 0x6FB08E, "water_fog_color": 0x2E2B29,
    }),
]

# Appended to every biome's UNDERGROUND_ORES step (index 7); identical order in every
# biome so the placed-feature ordering stays cycle-free across the dimension.
ORE_PLACED_FEATURES = [
    f"{NS}:infernium_ore",
    f"{NS}:smolder_crystal_ore",
    f"{NS}:molten_slag",
]


def emit_biomes() -> None:
    for biome_id, source, colors in BIOMES:
        biome = jar_json(f"data/minecraft/worldgen/biome/{source}.json")
        biome["effects"].update(colors)
        # Mobs are added in code by the mob worker: every spawner list is emptied.
        biome["spawners"] = {key: [] for key in biome["spawners"]}
        biome["features"][7] = biome["features"][7] + ORE_PLACED_FEATURES
        write_json(DATA / "worldgen" / "biome" / f"{biome_id}.json", biome)


# (id, ore block, size, count, height_range "height" spec) -- schema from vanilla
# ore_nether_gold (configured) + ore_gold_nether / ore_magma (placed).
ORES = [
    ("infernium_ore", 10, 10, {
        "type": "minecraft:uniform",
        "min_inclusive": {"above_bottom": 10},
        "max_inclusive": {"below_top": 10},
    }),
    ("smolder_crystal_ore", 8, 6, {
        "type": "minecraft:uniform",
        "min_inclusive": {"absolute": 10},
        "max_inclusive": {"absolute": 60},
    }),
    ("molten_slag", 33, 4, {
        "type": "minecraft:uniform",
        "min_inclusive": {"absolute": 27},
        "max_inclusive": {"absolute": 36},
    }),
]


def emit_ores() -> None:
    for ore_id, size, count, height in ORES:
        write_json(DATA / "worldgen" / "configured_feature" / f"{ore_id}.json", {
            "type": "minecraft:ore",
            "config": {
                "discard_chance_on_air_exposure": 0.0,
                "size": size,
                "targets": [{
                    "state": {"Name": f"{NS}:{ore_id}"},
                    "target": {"block": f"{NS}:cinderstone",
                               "predicate_type": "minecraft:block_match"},
                }],
            },
        })
        write_json(DATA / "worldgen" / "placed_feature" / f"{ore_id}.json", {
            "feature": f"{NS}:{ore_id}",
            "placement": [
                {"type": "minecraft:count", "count": count},
                {"type": "minecraft:in_square"},
                {"type": "minecraft:height_range", "height": height},
                {"type": "minecraft:biome"},
            ],
        })


def emit_dimension() -> None:
    # Stem schema validated against the extracted vanilla world_preset/normal.json
    # ("type" + "generator"{"type","biome_source","settings"}).
    write_json(DATA / "dimension" / "inferno.json", {
        "type": f"{NS}:inferno",
        "generator": {
            "type": "minecraft:noise",
            "settings": f"{NS}:inferno",
            "biome_source": {
                "type": "minecraft:checkerboard",
                "biomes": [f"{NS}:cinder_wastes", f"{NS}:ember_grove", f"{NS}:slag_sea"],
                "scale": 3,
            },
        },
    })


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic)
# ---------------------------------------------------------------------------

def new_canvas(rng: Random, shades) -> Image.Image:
    """Clustered 2x2 mottling plus sparse single-pixel accents."""
    img = Image.new("RGB", (16, 16))
    cells = {(cx, cy): rng.choice(shades) for cy in range(8) for cx in range(8)}
    for y in range(16):
        for x in range(16):
            img.putpixel((x, y), cells[(x // 2, y // 2)])
    for y in range(16):
        for x in range(16):
            if rng.random() < 0.06:
                img.putpixel((x, y), rng.choice(shades))
    return img


def sprinkle(img: Image.Image, rng: Random, pixels, colors, prob: float) -> None:
    for x, y in pixels:
        if rng.random() < prob:
            img.putpixel((x, y), rng.choice(colors))


def tex_cinderstone(rng: Random) -> Image.Image:
    """Warm charcoal terrain stone with sparse ember flecks (netherrack analog)."""
    img = new_canvas(rng, [CINDER_DARK, CINDER, CINDER, CINDER_LIGHT])
    all_px = [(x, y) for y in range(16) for x in range(16)]
    sprinkle(img, rng, all_px, [EMBER], 0.02)
    return img


def tex_cobbled_cinderstone(rng: Random) -> Image.Image:
    """Cinderstone broken into 4x4 cobbles with dark mortar joints."""
    img = new_canvas(rng, [CINDER_DARK, CINDER, CINDER_LIGHT])
    for y in range(16):
        row = y // 4
        offset = (row % 2) * 2
        for x in range(16):
            if y % 4 == 3 or (x - offset) % 4 == 3:
                img.putpixel((x, y), CINDER_MORTAR)
    return img


def tex_slagstone(rng: Random) -> Image.Image:
    """Gray banded stone (basalt analog): 2-row horizontal strata."""
    img = Image.new("RGB", (16, 16))
    bands = [(0x4C, 0x48, 0x46), (0x3E, 0x3A, 0x38), (0x57, 0x52, 0x4F), (0x45, 0x40, 0x3E)]
    for y in range(16):
        base = bands[(y // 2) % len(bands)]
        for x in range(16):
            shade = base
            if rng.random() < 0.15:
                shade = rng.choice(bands)
            img.putpixel((x, y), shade)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    sprinkle(img, rng, all_px, [EMBER], 0.012)
    return img


def tex_ash_block(rng: Random) -> Image.Image:
    """Soft pale ash drifts."""
    return new_canvas(rng, [ASH_LIGHT, ASH, ASH, ASH_DARK])


def tex_ember_soil(rng: Random) -> Image.Image:
    """Dark soil laced with glowing ember cracks (block emits light 3)."""
    img = new_canvas(rng, [(0x33, 0x22, 0x1C), (0x40, 0x2B, 0x22), (0x4A, 0x33, 0x28)])
    x = rng.randrange(3, 13)
    for y in range(16):
        x = max(1, min(14, x + rng.choice([-1, 0, 0, 1])))
        img.putpixel((x, y), EMBER)
        if rng.random() < 0.35:
            img.putpixel((max(0, min(15, x + rng.choice([-1, 1]))), y), EMBER_BRIGHT)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    sprinkle(img, rng, all_px, [EMBER, EMBER_BRIGHT], 0.03)
    return img


def tex_scorched_sand(rng: Random) -> Image.Image:
    """Heat-dulled tan-gray sand speckle."""
    return new_canvas(rng, [(0xA8, 0x94, 0x78), (0x9A, 0x86, 0x6C), (0x8C, 0x78, 0x60), (0xB4, 0xA0, 0x84)])


def tex_cinder_gravel(rng: Random) -> Image.Image:
    """Gray pebble clusters with dark outlines (gravel analog)."""
    img = new_canvas(rng, [(0x54, 0x4E, 0x4A), (0x47, 0x41, 0x3E), (0x5F, 0x58, 0x53)])
    for _ in range(9):
        px, py = rng.randrange(0, 14), rng.randrange(0, 14)
        pebble = rng.choice([(0x6A, 0x62, 0x5C), (0x3A, 0x34, 0x31), (0x57, 0x50, 0x4B)])
        for dx in range(2):
            for dy in range(2):
                img.putpixel((px + dx, py + dy), pebble)
        img.putpixel((px, py), tuple(max(0, c - 24) for c in pebble))
    return img


def _ore_base_with_nuggets(rng: Random, nugget_colors, highlight) -> Image.Image:
    img = tex_cinderstone(Random(f"{NS}:cinderstone"))  # same base as the host stone
    for _ in range(5):
        px, py = rng.randrange(1, 13), rng.randrange(1, 13)
        color = rng.choice(nugget_colors)
        for dx, dy in [(0, 0), (1, 0), (0, 1), (1, 1)]:
            img.putpixel((px + dx, py + dy), color)
        img.putpixel((px + 1, py), highlight)
    return img


def tex_infernium_ore(rng: Random) -> Image.Image:
    """Copper-orange infernium nuggets in cinderstone."""
    return _ore_base_with_nuggets(rng, [COPPER, COPPER_DARK, EMBER_BRIGHT], EMBER_HOT)


def tex_smolder_crystal_ore(rng: Random) -> Image.Image:
    """Verdigris-green smolder crystal shards in cinderstone."""
    return _ore_base_with_nuggets(rng, [VERDIGRIS, VERDIGRIS_DARK], (0xA5, 0xD8, 0xC0))


def tex_molten_slag(rng: Random) -> Image.Image:
    """Magma-style: dark crust cells over glowing seams (block emits light 10)."""
    img = Image.new("RGB", (16, 16))
    for y in range(16):
        for x in range(16):
            if x % 4 == 3 or y % 4 == 3:
                img.putpixel((x, y), rng.choice([EMBER, EMBER_BRIGHT, EMBER_HOT]))
            else:
                img.putpixel((x, y), rng.choice([CINDER_DARK, (0x26, 0x1B, 0x18), CINDER]))
    for _ in range(6):
        px, py = rng.randrange(0, 15), rng.randrange(0, 15)
        img.putpixel((px, py), EMBER_BRIGHT)
    return img


def tex_infernium_portal_frame(rng: Random) -> Image.Image:
    """Chiseled dark frame, copper inlay ring, glowing corner studs (light 5)."""
    img = new_canvas(rng, [CINDER_DARK, CINDER])
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), CINDER_MORTAR)
    for i in range(3, 13):
        for x, y in ((i, 3), (i, 12), (3, i), (12, i)):
            img.putpixel((x, y), rng.choice([COPPER, COPPER_DARK]))
    for x, y in [(3, 3), (12, 3), (3, 12), (12, 12)]:
        img.putpixel((x, y), EMBER_BRIGHT)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), EMBER)
    img.putpixel((8, 8), EMBER_HOT)
    return img


def tex_inferno_portal(rng: Random) -> Image.Image:
    """Translucent ember/verdigris swirl (RGBA; rendered on the TRANSLUCENT layer)."""
    img = Image.new("RGBA", (16, 16))
    for y in range(16):
        for x in range(16):
            band = (x + y * 2 + rng.randrange(0, 2)) % 8
            if band < 3:
                color = EMBER
            elif band < 5:
                color = EMBER_BRIGHT
            elif band < 7:
                color = VERDIGRIS_DARK
            else:
                color = (0x8A, 0x2E, 0x12)
            alpha = 176 + rng.randrange(0, 48)
            img.putpixel((x, y), (*color, alpha))
    for _ in range(10):
        px, py = rng.randrange(0, 16), rng.randrange(0, 16)
        img.putpixel((px, py), (*EMBER_HOT, 230))
    return img


IGNITER_GRID = [
    "................",
    ".......gg.......",
    "......gffg......",
    ".....gf..g......",
    ".....gf.........",
    ".....gf.........",
    ".....gf..g......",
    "......gffg..cc..",
    ".......gg..cxc..",
    "..........cxc...",
    ".........cxc....",
    "........cxc.....",
    ".......bbc......",
    "......bob.......",
    ".....bob........",
    "......b.........",
]
IGNITER_COLORS = {
    "g": (0x5A, 0x5A, 0x60),   # flint arc (steel gray)
    "f": (0x3A, 0x3A, 0x40),   # flint core
    "c": COPPER,               # copper striker edge
    "x": COPPER_DARK,          # copper striker core
    "b": (0xE2, 0xA0, 0x33),   # blaze rod
    "o": EMBER_BRIGHT,         # blaze glow
}


def tex_infernium_igniter(_rng: Random) -> Image.Image:
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    for y, row in enumerate(IGNITER_GRID):
        for x, ch in enumerate(row):
            if ch != ".":
                img.putpixel((x, y), (*IGNITER_COLORS[ch], 255))
    return img


BLOCK_TEXTURES = {
    "cinderstone": tex_cinderstone,
    "cobbled_cinderstone": tex_cobbled_cinderstone,
    "slagstone": tex_slagstone,
    "ash_block": tex_ash_block,
    "ember_soil": tex_ember_soil,
    "scorched_sand": tex_scorched_sand,
    "cinder_gravel": tex_cinder_gravel,
    "infernium_ore": tex_infernium_ore,
    "smolder_crystal_ore": tex_smolder_crystal_ore,
    "molten_slag": tex_molten_slag,
    "infernium_portal_frame": tex_infernium_portal_frame,
    "inferno_portal": tex_inferno_portal,
}


def emit_textures() -> None:
    block_dir = ASSETS / "textures" / "block"
    block_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in BLOCK_TEXTURES.items():
        # Per-texture fixed seed keeps output byte-identical across runs.
        fn(Random(f"{NS}:{name}")).save(block_dir / f"{name}.png")
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    tex_infernium_igniter(Random(f"{NS}:infernium_igniter")).save(item_dir / "infernium_igniter.png")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    for name in SIMPLE_CUBES:
        emit_cube(name)
        emit_drop_self_loot(name)
    emit_portal()
    emit_portal_loot()
    emit_igniter()
    emit_recipes()
    emit_dimension_type()
    emit_noise_settings()
    emit_biomes()
    emit_ores()
    emit_dimension()
    write_json(ASSETS / "lang" / "fragments" / "infernodim.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "infernodim.json", LANG_DE)
    emit_textures()
    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for legacy JSON)"
    print(f"infernodim_gen: assets generated ({mode}).")


if __name__ == "__main__":
    main()
