#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "cinderstone" feature (56 building blocks).

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name). Emits, directly into src/main/resources:
  - blockstates, block models, item models, items/<id>.json model-definitions
  - 16x16 block textures (Pillow, deterministic seeded noise)
  - loot tables (drop-self; slabs use the vanilla double-drops-2 format)
  - recipes (data/copper_inferno/recipe/cinderstone/*.json)
  - lang fragments: assets/copper_inferno/lang/fragments/cinderstone.json (EN)
    and assets/copper_inferno/lang/fragments_de/cinderstone.json (German)

Blockstate/model/item/loot/recipe JSON structures are exact copies of the vanilla 1.21.9
formats extracted from ~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar
(stone_bricks family, basalt pillar, glass, glass_pane, lantern, cracked_stone_bricks
smelting, stone_brick_wall stonecutting). Do NOT "improve" them.

NOTE: JSON emission is legacy scaffolding (opt-in via --write-json); the JSON in
src/main/resources is authoritative — by default this script writes ONLY PNGs.
"""

import json
import sys
from pathlib import Path
from random import Random

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> textures/*.png only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# ---------------------------------------------------------------------------
# Palette (task spec): charcoal #2B2226/#3D2C2E, ember #E25822/#FF7A2F,
# slag teal-gray, ash #8A8580.
# ---------------------------------------------------------------------------
CHARCOAL_DARK = (0x2B, 0x22, 0x26)
CHARCOAL = (0x3D, 0x2C, 0x2E)
CHARCOAL_LIGHT = (0x4C, 0x38, 0x3B)
CHARCOAL_MORTAR = (0x1C, 0x15, 0x18)
FISSURE = (0x14, 0x0B, 0x0E)

EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_HOT = (0xFF, 0xB1, 0x6B)

SLAG_DARK = (0x2E, 0x3B, 0x3A)
SLAG = (0x3E, 0x50, 0x4E)
SLAG_LIGHT = (0x51, 0x66, 0x62)
SLAG_TEAL = (0x5E, 0x8C, 0x82)
SLAG_MORTAR = (0x1D, 0x27, 0x26)

ASH_DARK = (0x6F, 0x6B, 0x66)
ASH = (0x8A, 0x85, 0x80)
ASH_LIGHT = (0xA3, 0x9E, 0x98)
ASH_MORTAR = (0x53, 0x4F, 0x4B)

FORGE_DARK = (0x39, 0x29, 0x24)
FORGE = (0x4C, 0x36, 0x2B)
FORGE_LIGHT = (0x5E, 0x44, 0x33)
FORGE_MORTAR = (0x22, 0x16, 0x12)
COPPER_GLINT = (0xB8, 0x6A, 0x3E)

# (shades list, mortar) per stone family look.
LOOKS = {
    "charcoal": ([CHARCOAL_DARK, CHARCOAL, CHARCOAL, CHARCOAL_LIGHT], CHARCOAL_MORTAR),
    "slag": ([SLAG_DARK, SLAG, SLAG, SLAG_LIGHT], SLAG_MORTAR),
    "ash": ([ASH_DARK, ASH, ASH, ASH_LIGHT], ASH_MORTAR),
    "forge": ([FORGE_DARK, FORGE, FORGE, FORGE_LIGHT], FORGE_MORTAR),
}

FAMILIES = [
    # (base block name, stem)
    ("cinderstone_bricks", "cinderstone_brick"),
    ("cinderstone_tiles", "cinderstone_tile"),
    ("polished_cinderstone", "polished_cinderstone"),
    ("slagstone_bricks", "slagstone_brick"),
    ("ashen_bricks", "ashen_brick"),
    ("smoldering_bricks", "smoldering_brick"),
    ("forge_bricks", "forge_brick"),
    ("quenched_slag", "quenched_slag"),
]

SINGLE_CUBES = [
    "cracked_cinderstone_bricks",
    "chiseled_cinderstone_bricks",
    "carved_cinderstone",
    "cracked_slagstone_bricks",
    "chiseled_slagstone_bricks",
    "cracked_forge_bricks",
    "chiseled_forge_bricks",
    "chiseled_ashen_bricks",
    "ashen_mosaic",
    "smolder_lamp",
    "ashen_lamp",
    "ember_coal_block",
    "forge_heart",
]

PILLARS = [
    "cinderstone_pillar",
    "slagstone_pillar",
    "forge_pillar",
    "ashen_pillar",
    "quenched_slag_pillar",
]

# ---------------------------------------------------------------------------
# Lang (EN + real German). Keys use the block-prefixed translation key that
# ModBlocks.register applies to every BlockItem.
# ---------------------------------------------------------------------------
LANG_EN = {
    "block.copper_inferno.cinderstone_bricks": "Cinderstone Bricks",
    "block.copper_inferno.cinderstone_brick_slab": "Cinderstone Brick Slab",
    "block.copper_inferno.cinderstone_brick_stairs": "Cinderstone Brick Stairs",
    "block.copper_inferno.cinderstone_brick_wall": "Cinderstone Brick Wall",
    "block.copper_inferno.cinderstone_tiles": "Cinderstone Tiles",
    "block.copper_inferno.cinderstone_tile_slab": "Cinderstone Tile Slab",
    "block.copper_inferno.cinderstone_tile_stairs": "Cinderstone Tile Stairs",
    "block.copper_inferno.cinderstone_tile_wall": "Cinderstone Tile Wall",
    "block.copper_inferno.polished_cinderstone": "Polished Cinderstone",
    "block.copper_inferno.polished_cinderstone_slab": "Polished Cinderstone Slab",
    "block.copper_inferno.polished_cinderstone_stairs": "Polished Cinderstone Stairs",
    "block.copper_inferno.polished_cinderstone_wall": "Polished Cinderstone Wall",
    "block.copper_inferno.slagstone_bricks": "Slagstone Bricks",
    "block.copper_inferno.slagstone_brick_slab": "Slagstone Brick Slab",
    "block.copper_inferno.slagstone_brick_stairs": "Slagstone Brick Stairs",
    "block.copper_inferno.slagstone_brick_wall": "Slagstone Brick Wall",
    "block.copper_inferno.ashen_bricks": "Ashen Bricks",
    "block.copper_inferno.ashen_brick_slab": "Ashen Brick Slab",
    "block.copper_inferno.ashen_brick_stairs": "Ashen Brick Stairs",
    "block.copper_inferno.ashen_brick_wall": "Ashen Brick Wall",
    "block.copper_inferno.smoldering_bricks": "Smoldering Bricks",
    "block.copper_inferno.smoldering_brick_slab": "Smoldering Brick Slab",
    "block.copper_inferno.smoldering_brick_stairs": "Smoldering Brick Stairs",
    "block.copper_inferno.smoldering_brick_wall": "Smoldering Brick Wall",
    "block.copper_inferno.forge_bricks": "Forge Bricks",
    "block.copper_inferno.forge_brick_slab": "Forge Brick Slab",
    "block.copper_inferno.forge_brick_stairs": "Forge Brick Stairs",
    "block.copper_inferno.forge_brick_wall": "Forge Brick Wall",
    "block.copper_inferno.quenched_slag": "Quenched Slag",
    "block.copper_inferno.quenched_slag_slab": "Quenched Slag Slab",
    "block.copper_inferno.quenched_slag_stairs": "Quenched Slag Stairs",
    "block.copper_inferno.quenched_slag_wall": "Quenched Slag Wall",
    "block.copper_inferno.cracked_cinderstone_bricks": "Cracked Cinderstone Bricks",
    "block.copper_inferno.chiseled_cinderstone_bricks": "Chiseled Cinderstone Bricks",
    "block.copper_inferno.cinderstone_pillar": "Cinderstone Pillar",
    "block.copper_inferno.carved_cinderstone": "Carved Cinderstone",
    "block.copper_inferno.cracked_slagstone_bricks": "Cracked Slagstone Bricks",
    "block.copper_inferno.chiseled_slagstone_bricks": "Chiseled Slagstone Bricks",
    "block.copper_inferno.slagstone_pillar": "Slagstone Pillar",
    "block.copper_inferno.cracked_forge_bricks": "Cracked Forge Bricks",
    "block.copper_inferno.chiseled_forge_bricks": "Chiseled Forge Bricks",
    "block.copper_inferno.forge_pillar": "Forge Pillar",
    "block.copper_inferno.ashen_pillar": "Ashen Pillar",
    "block.copper_inferno.chiseled_ashen_bricks": "Chiseled Ashen Bricks",
    "block.copper_inferno.ashen_mosaic": "Ashen Mosaic",
    "block.copper_inferno.quenched_slag_pillar": "Quenched Slag Pillar",
    "block.copper_inferno.ember_lantern": "Ember Lantern",
    "block.copper_inferno.ashen_lantern": "Ashen Lantern",
    "block.copper_inferno.smolder_lamp": "Smolder Lamp",
    "block.copper_inferno.ashen_lamp": "Ashen Lamp",
    "block.copper_inferno.cinder_glass": "Cinder Glass",
    "block.copper_inferno.cinder_glass_pane": "Cinder Glass Pane",
    "block.copper_inferno.smolder_glass": "Smolder Glass",
    "block.copper_inferno.smolder_glass_pane": "Smolder Glass Pane",
    "block.copper_inferno.ember_coal_block": "Ember Coal Block",
    "block.copper_inferno.forge_heart": "Forge Heart",
}

LANG_DE = {
    "block.copper_inferno.cinderstone_bricks": "Zundersteinziegel",
    "block.copper_inferno.cinderstone_brick_slab": "Zundersteinziegelstufe",
    "block.copper_inferno.cinderstone_brick_stairs": "Zundersteinziegeltreppe",
    "block.copper_inferno.cinderstone_brick_wall": "Zundersteinziegelmauer",
    "block.copper_inferno.cinderstone_tiles": "Zundersteinfliesen",
    "block.copper_inferno.cinderstone_tile_slab": "Zundersteinfliesenstufe",
    "block.copper_inferno.cinderstone_tile_stairs": "Zundersteinfliesentreppe",
    "block.copper_inferno.cinderstone_tile_wall": "Zundersteinfliesenmauer",
    "block.copper_inferno.polished_cinderstone": "Polierter Zunderstein",
    "block.copper_inferno.polished_cinderstone_slab": "Polierte Zundersteinstufe",
    "block.copper_inferno.polished_cinderstone_stairs": "Polierte Zundersteintreppe",
    "block.copper_inferno.polished_cinderstone_wall": "Polierte Zundersteinmauer",
    "block.copper_inferno.slagstone_bricks": "Schlackensteinziegel",
    "block.copper_inferno.slagstone_brick_slab": "Schlackensteinziegelstufe",
    "block.copper_inferno.slagstone_brick_stairs": "Schlackensteinziegeltreppe",
    "block.copper_inferno.slagstone_brick_wall": "Schlackensteinziegelmauer",
    "block.copper_inferno.ashen_bricks": "Aschenziegel",
    "block.copper_inferno.ashen_brick_slab": "Aschenziegelstufe",
    "block.copper_inferno.ashen_brick_stairs": "Aschenziegeltreppe",
    "block.copper_inferno.ashen_brick_wall": "Aschenziegelmauer",
    "block.copper_inferno.smoldering_bricks": "Schwelziegel",
    "block.copper_inferno.smoldering_brick_slab": "Schwelziegelstufe",
    "block.copper_inferno.smoldering_brick_stairs": "Schwelziegeltreppe",
    "block.copper_inferno.smoldering_brick_wall": "Schwelziegelmauer",
    "block.copper_inferno.forge_bricks": "Schmiedeziegel",
    "block.copper_inferno.forge_brick_slab": "Schmiedeziegelstufe",
    "block.copper_inferno.forge_brick_stairs": "Schmiedeziegeltreppe",
    "block.copper_inferno.forge_brick_wall": "Schmiedeziegelmauer",
    "block.copper_inferno.quenched_slag": "Abgeschreckte Schlacke",
    "block.copper_inferno.quenched_slag_slab": "Abgeschreckte Schlackenstufe",
    "block.copper_inferno.quenched_slag_stairs": "Abgeschreckte Schlackentreppe",
    "block.copper_inferno.quenched_slag_wall": "Abgeschreckte Schlackenmauer",
    "block.copper_inferno.cracked_cinderstone_bricks": "Rissige Zundersteinziegel",
    "block.copper_inferno.chiseled_cinderstone_bricks": "Gemei\u00dfelte Zundersteinziegel",
    "block.copper_inferno.cinderstone_pillar": "Zundersteins\u00e4ule",
    "block.copper_inferno.carved_cinderstone": "Verzierter Zunderstein",
    "block.copper_inferno.cracked_slagstone_bricks": "Rissige Schlackensteinziegel",
    "block.copper_inferno.chiseled_slagstone_bricks": "Gemei\u00dfelte Schlackensteinziegel",
    "block.copper_inferno.slagstone_pillar": "Schlackensteins\u00e4ule",
    "block.copper_inferno.cracked_forge_bricks": "Rissige Schmiedeziegel",
    "block.copper_inferno.chiseled_forge_bricks": "Gemei\u00dfelte Schmiedeziegel",
    "block.copper_inferno.forge_pillar": "Schmiedes\u00e4ule",
    "block.copper_inferno.ashen_pillar": "Aschens\u00e4ule",
    "block.copper_inferno.chiseled_ashen_bricks": "Gemei\u00dfelte Aschenziegel",
    "block.copper_inferno.ashen_mosaic": "Aschenmosaik",
    "block.copper_inferno.quenched_slag_pillar": "Abgeschreckte Schlackens\u00e4ule",
    "block.copper_inferno.ember_lantern": "Glutlaterne",
    "block.copper_inferno.ashen_lantern": "Aschenlaterne",
    "block.copper_inferno.smolder_lamp": "Schwellampe",
    "block.copper_inferno.ashen_lamp": "Aschenlampe",
    "block.copper_inferno.cinder_glass": "Zunderglas",
    "block.copper_inferno.cinder_glass_pane": "Zunderglasscheibe",
    "block.copper_inferno.smolder_glass": "Schwelglas",
    "block.copper_inferno.smolder_glass_pane": "Schwelglasscheibe",
    "block.copper_inferno.ember_coal_block": "Glutkohleblock",
    "block.copper_inferno.forge_heart": "Schmiedeherz",
}

# ---------------------------------------------------------------------------
# Vanilla templates (exact structure from minecraft-client.jar, 1.21.9):
# stone_brick_stairs blockstate, brick_wall multipart, basalt pillar,
# glass_pane multipart, lantern hanging variants.
# ---------------------------------------------------------------------------

STAIRS_BLOCKSTATE_TEMPLATE = """
{
  "variants": {
    "facing=east,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 270},
    "facing=east,half=bottom,shape=inner_right": {"model": "@INNER@"},
    "facing=east,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 270},
    "facing=east,half=bottom,shape=outer_right": {"model": "@OUTER@"},
    "facing=east,half=bottom,shape=straight": {"model": "@STAIRS@"},
    "facing=east,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180},
    "facing=east,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 90},
    "facing=east,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180},
    "facing=east,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 90},
    "facing=east,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180},
    "facing=north,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 180},
    "facing=north,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 270},
    "facing=north,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 180},
    "facing=north,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 270},
    "facing=north,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 270},
    "facing=north,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 270},
    "facing=north,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180},
    "facing=north,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 270},
    "facing=north,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180},
    "facing=north,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 270},
    "facing=south,half=bottom,shape=inner_left": {"model": "@INNER@"},
    "facing=south,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 90},
    "facing=south,half=bottom,shape=outer_left": {"model": "@OUTER@"},
    "facing=south,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 90},
    "facing=south,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 90},
    "facing=south,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 90},
    "facing=south,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 180},
    "facing=south,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 90},
    "facing=south,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 180},
    "facing=south,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 90},
    "facing=west,half=bottom,shape=inner_left": {"model": "@INNER@", "uvlock": true, "y": 90},
    "facing=west,half=bottom,shape=inner_right": {"model": "@INNER@", "uvlock": true, "y": 180},
    "facing=west,half=bottom,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "y": 90},
    "facing=west,half=bottom,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "y": 180},
    "facing=west,half=bottom,shape=straight": {"model": "@STAIRS@", "uvlock": true, "y": 180},
    "facing=west,half=top,shape=inner_left": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 180},
    "facing=west,half=top,shape=inner_right": {"model": "@INNER@", "uvlock": true, "x": 180, "y": 270},
    "facing=west,half=top,shape=outer_left": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 180},
    "facing=west,half=top,shape=outer_right": {"model": "@OUTER@", "uvlock": true, "x": 180, "y": 270},
    "facing=west,half=top,shape=straight": {"model": "@STAIRS@", "uvlock": true, "x": 180, "y": 180}
  }
}
"""

WALL_BLOCKSTATE_TEMPLATE = """
{
  "multipart": [
    {"apply": {"model": "@WALL_POST@"}, "when": {"up": "true"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true}, "when": {"north": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 90}, "when": {"east": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 180}, "when": {"south": "low"}},
    {"apply": {"model": "@WALL_SIDE@", "uvlock": true, "y": 270}, "when": {"west": "low"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true}, "when": {"north": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 90}, "when": {"east": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 180}, "when": {"south": "tall"}},
    {"apply": {"model": "@WALL_SIDE_TALL@", "uvlock": true, "y": 270}, "when": {"west": "tall"}}
  ]
}
"""


def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False) + "\n",
                    encoding="utf-8")


def block_ref(name: str) -> str:
    return f"{NS}:block/{name}"


def item_def(model_ref: str) -> dict:
    return {"model": {"type": "minecraft:model", "model": model_ref}}


# ---------------------------------------------------------------------------
# JSON emitters (vanilla formats)
# ---------------------------------------------------------------------------

def emit_cube(name: str) -> None:
    """Blockstate + cube_all model + item definition for a simple full cube."""
    write_json(ASSETS / "blockstates" / f"{name}.json",
               {"variants": {"": {"model": block_ref(name)}}})
    write_json(ASSETS / "models" / "block" / f"{name}.json",
               {"parent": "minecraft:block/cube_all", "textures": {"all": block_ref(name)}})
    write_json(ASSETS / "items" / f"{name}.json", item_def(block_ref(name)))


def emit_family(base: str, stem: str) -> None:
    emit_cube(base)
    tex = block_ref(base)
    tex3 = {"bottom": tex, "side": tex, "top": tex}

    # Slab (vanilla stone_brick_slab format; double state reuses the base cube model).
    slab = f"{stem}_slab"
    write_json(ASSETS / "models" / "block" / f"{slab}.json",
               {"parent": "minecraft:block/slab", "textures": tex3})
    write_json(ASSETS / "models" / "block" / f"{slab}_top.json",
               {"parent": "minecraft:block/slab_top", "textures": tex3})
    write_json(ASSETS / "blockstates" / f"{slab}.json", {"variants": {
        "type=bottom": {"model": block_ref(slab)},
        "type=double": {"model": block_ref(base)},
        "type=top": {"model": block_ref(f"{slab}_top")},
    }})
    write_json(ASSETS / "items" / f"{slab}.json", item_def(block_ref(slab)))

    # Stairs (vanilla stone_brick_stairs format).
    stairs = f"{stem}_stairs"
    write_json(ASSETS / "models" / "block" / f"{stairs}.json",
               {"parent": "minecraft:block/stairs", "textures": tex3})
    write_json(ASSETS / "models" / "block" / f"{stairs}_inner.json",
               {"parent": "minecraft:block/inner_stairs", "textures": tex3})
    write_json(ASSETS / "models" / "block" / f"{stairs}_outer.json",
               {"parent": "minecraft:block/outer_stairs", "textures": tex3})
    stairs_state = (STAIRS_BLOCKSTATE_TEMPLATE
                    .replace("@STAIRS@", block_ref(stairs))
                    .replace("@INNER@", block_ref(f"{stairs}_inner"))
                    .replace("@OUTER@", block_ref(f"{stairs}_outer")))
    write_json(ASSETS / "blockstates" / f"{stairs}.json", json.loads(stairs_state))
    write_json(ASSETS / "items" / f"{stairs}.json", item_def(block_ref(stairs)))

    # Wall (vanilla stone_brick_wall format; item uses the wall_inventory model).
    wall = f"{stem}_wall"
    wall_tex = {"wall": tex}
    for suffix, parent in (("post", "template_wall_post"), ("side", "template_wall_side"),
                           ("side_tall", "template_wall_side_tall"), ("inventory", "wall_inventory")):
        write_json(ASSETS / "models" / "block" / f"{wall}_{suffix}.json",
                   {"parent": f"minecraft:block/{parent}", "textures": wall_tex})
    wall_state = (WALL_BLOCKSTATE_TEMPLATE
                  .replace("@WALL_POST@", block_ref(f"{wall}_post"))
                  .replace("@WALL_SIDE_TALL@", block_ref(f"{wall}_side_tall"))
                  .replace("@WALL_SIDE@", block_ref(f"{wall}_side")))
    write_json(ASSETS / "blockstates" / f"{wall}.json", json.loads(wall_state))
    write_json(ASSETS / "items" / f"{wall}.json", item_def(block_ref(f"{wall}_inventory")))


def emit_pillar(name: str) -> None:
    """Axis blockstate + cube_column model, exactly like vanilla basalt."""
    write_json(ASSETS / "blockstates" / f"{name}.json", {"variants": {
        "axis=x": {"model": block_ref(name), "x": 90, "y": 90},
        "axis=y": {"model": block_ref(name)},
        "axis=z": {"model": block_ref(name), "x": 90},
    }})
    write_json(ASSETS / "models" / "block" / f"{name}.json", {
        "parent": "minecraft:block/cube_column",
        "textures": {"end": block_ref(f"{name}_top"), "side": block_ref(f"{name}_side")},
    })
    write_json(ASSETS / "items" / f"{name}.json", item_def(block_ref(name)))


def emit_glass(name: str) -> None:
    """Vanilla glass: single-variant blockstate, cube_all, item def -> block model."""
    emit_cube(name)


def emit_pane(name: str, glass: str) -> None:
    """Vanilla glass_pane: multipart blockstate + 5 pane models + flat item sprite."""
    m = block_ref(name)
    write_json(ASSETS / "blockstates" / f"{name}.json", {"multipart": [
        {"apply": {"model": f"{m}_post"}},
        {"apply": {"model": f"{m}_side"}, "when": {"north": "true"}},
        {"apply": {"model": f"{m}_side", "y": 90}, "when": {"east": "true"}},
        {"apply": {"model": f"{m}_side_alt"}, "when": {"south": "true"}},
        {"apply": {"model": f"{m}_side_alt", "y": 90}, "when": {"west": "true"}},
        {"apply": {"model": f"{m}_noside"}, "when": {"north": "false"}},
        {"apply": {"model": f"{m}_noside_alt"}, "when": {"east": "false"}},
        {"apply": {"model": f"{m}_noside_alt", "y": 90}, "when": {"south": "false"}},
        {"apply": {"model": f"{m}_noside", "y": 270}, "when": {"west": "false"}},
    ]})
    pane_tex = block_ref(glass)
    edge_tex = block_ref(f"{name}_top")
    write_json(ASSETS / "models" / "block" / f"{name}_post.json",
               {"parent": "minecraft:block/template_glass_pane_post",
                "textures": {"edge": edge_tex, "pane": pane_tex}})
    write_json(ASSETS / "models" / "block" / f"{name}_side.json",
               {"parent": "minecraft:block/template_glass_pane_side",
                "textures": {"edge": edge_tex, "pane": pane_tex}})
    write_json(ASSETS / "models" / "block" / f"{name}_side_alt.json",
               {"parent": "minecraft:block/template_glass_pane_side_alt",
                "textures": {"edge": edge_tex, "pane": pane_tex}})
    write_json(ASSETS / "models" / "block" / f"{name}_noside.json",
               {"parent": "minecraft:block/template_glass_pane_noside",
                "textures": {"pane": pane_tex}})
    write_json(ASSETS / "models" / "block" / f"{name}_noside_alt.json",
               {"parent": "minecraft:block/template_glass_pane_noside_alt",
                "textures": {"pane": pane_tex}})
    # Vanilla glass_pane item: item/generated over the glass cube texture.
    write_json(ASSETS / "models" / "item" / f"{name}.json",
               {"parent": "minecraft:item/generated", "textures": {"layer0": pane_tex}})
    write_json(ASSETS / "items" / f"{name}.json", item_def(f"{NS}:item/{name}"))


def emit_lantern(name: str) -> None:
    """Vanilla lantern: hanging=true/false blockstate + template models + flat item."""
    write_json(ASSETS / "blockstates" / f"{name}.json", {"variants": {
        "hanging=false": {"model": block_ref(name)},
        "hanging=true": {"model": block_ref(f"{name}_hanging")},
    }})
    write_json(ASSETS / "models" / "block" / f"{name}.json",
               {"parent": "minecraft:block/template_lantern",
                "textures": {"lantern": block_ref(name)}})
    write_json(ASSETS / "models" / "block" / f"{name}_hanging.json",
               {"parent": "minecraft:block/template_hanging_lantern",
                "textures": {"lantern": block_ref(name)}})
    write_json(ASSETS / "models" / "item" / f"{name}.json",
               {"parent": "minecraft:item/generated",
                "textures": {"layer0": f"{NS}:item/{name}"}})
    write_json(ASSETS / "items" / f"{name}.json", item_def(f"{NS}:item/{name}"))


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


def emit_slab_loot(name: str) -> None:
    """Vanilla slab loot (drop self, x2 when type=double)."""
    write_json(DATA / "loot_table" / "blocks" / f"{name}.json", {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "entries": [{
                "type": "minecraft:item",
                "functions": [
                    {
                        "add": False,
                        "conditions": [{
                            "block": f"{NS}:{name}",
                            "condition": "minecraft:block_state_property",
                            "properties": {"type": "double"},
                        }],
                        "count": 2.0,
                        "function": "minecraft:set_count",
                    },
                    {"function": "minecraft:explosion_decay"},
                ],
                "name": f"{NS}:{name}",
            }],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{name}",
    })


# ---------------------------------------------------------------------------
# Recipes (vanilla formats: crafting_shaped/shapeless, smelting, stonecutting)
# ---------------------------------------------------------------------------

def recipe_path(name: str) -> Path:
    return DATA / "recipe" / "cinderstone" / f"{name}.json"


def emit_shaped(name: str, key: dict, pattern: list, result_id: str, count: int,
                category: str = "building") -> None:
    write_json(recipe_path(name), {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": result_id},
    })


def emit_shapeless(name: str, ingredients: list, result_id: str, count: int) -> None:
    write_json(recipe_path(name), {
        "type": "minecraft:crafting_shapeless",
        "category": "building",
        "ingredients": ingredients,
        "result": {"count": count, "id": result_id},
    })


def emit_smelting(name: str, ingredient: str, result_id: str) -> None:
    # Format copied from vanilla cracked_stone_bricks.json.
    write_json(recipe_path(name), {
        "type": "minecraft:smelting",
        "category": "blocks",
        "cookingtime": 200,
        "experience": 0.1,
        "ingredient": ingredient,
        "result": {"id": result_id},
    })


def emit_stonecutting(name: str, ingredient: str, result_id: str, count: int) -> None:
    # Format copied from masonry's copper_bricks_from_copper_block_stonecutting.json
    # (== vanilla stone_brick_wall_from_stone_bricks_stonecutting.json).
    write_json(recipe_path(name), {
        "type": "minecraft:stonecutting",
        "ingredient": ingredient,
        "result": {"count": count, "id": result_id},
    })


def emit_recipes() -> None:
    ci = f"{NS}:"

    # Base family cubes.
    emit_shaped("cinderstone_bricks", {"D": "minecraft:deepslate", "C": "minecraft:charcoal"},
                ["D D", " C ", "D D"], f"{ci}cinderstone_bricks", 4)
    emit_shaped("cinderstone_tiles", {"#": f"{ci}cinderstone_bricks"},
                ["##", "##"], f"{ci}cinderstone_tiles", 4)
    emit_shaped("polished_cinderstone", {"#": f"{ci}cinderstone_tiles"},
                ["##", "##"], f"{ci}polished_cinderstone", 4)
    emit_shaped("slagstone_bricks", {"B": "minecraft:blackstone", "P": "minecraft:prismarine_shard"},
                ["B B", " P ", "B B"], f"{ci}slagstone_bricks", 4)
    emit_shaped("ashen_bricks", {"C": "minecraft:cobbled_deepslate", "B": "minecraft:bone_meal"},
                ["C C", " B ", "C C"], f"{ci}ashen_bricks", 4)
    emit_shaped("smoldering_bricks", {"#": f"{ci}cinderstone_bricks", "M": "minecraft:magma_block"},
                ["# #", " M ", "# #"], f"{ci}smoldering_bricks", 4)
    emit_shaped("forge_bricks", {"#": f"{ci}cinderstone_bricks", "C": "minecraft:copper_ingot"},
                ["# #", " C ", "# #"], f"{ci}forge_bricks", 4)
    emit_shaped("quenched_slag", {"#": f"{ci}slagstone_bricks"},
                ["##", "##"], f"{ci}quenched_slag", 4)

    # Family slab/stairs/wall recipes from their base cubes (vanilla formats).
    for base, stem in FAMILIES:
        cube = f"{ci}{base}"
        emit_shaped(f"{stem}_slab", {"#": cube}, ["###"], f"{ci}{stem}_slab", 6)
        emit_shaped(f"{stem}_stairs", {"#": cube}, ["#  ", "## ", "###"], f"{ci}{stem}_stairs", 4)
        emit_shaped(f"{stem}_wall", {"#": cube}, ["###", "###"], f"{ci}{stem}_wall", 6,
                    category="misc")

    # Smelting: cracked variants (vanilla cracked_stone_bricks format).
    emit_smelting("cracked_cinderstone_bricks", f"{ci}cinderstone_bricks",
                  f"{ci}cracked_cinderstone_bricks")
    emit_smelting("cracked_slagstone_bricks", f"{ci}slagstone_bricks",
                  f"{ci}cracked_slagstone_bricks")
    emit_smelting("cracked_forge_bricks", f"{ci}forge_bricks", f"{ci}cracked_forge_bricks")

    # Chiseled/carved: two vertical slabs (vanilla chiseled_stone_bricks layout).
    emit_shaped("chiseled_cinderstone_bricks", {"#": f"{ci}cinderstone_brick_slab"},
                ["#", "#"], f"{ci}chiseled_cinderstone_bricks", 1)
    emit_shaped("chiseled_slagstone_bricks", {"#": f"{ci}slagstone_brick_slab"},
                ["#", "#"], f"{ci}chiseled_slagstone_bricks", 1)
    emit_shaped("chiseled_forge_bricks", {"#": f"{ci}forge_brick_slab"},
                ["#", "#"], f"{ci}chiseled_forge_bricks", 1)
    emit_shaped("chiseled_ashen_bricks", {"#": f"{ci}ashen_brick_slab"},
                ["#", "#"], f"{ci}chiseled_ashen_bricks", 1)
    emit_shaped("carved_cinderstone", {"#": f"{ci}polished_cinderstone_slab"},
                ["#", "#"], f"{ci}carved_cinderstone", 1)

    # Pillars: two vertical cubes -> 2 (vanilla quartz_pillar layout).
    emit_shaped("cinderstone_pillar", {"#": f"{ci}cinderstone_bricks"},
                ["#", "#"], f"{ci}cinderstone_pillar", 2)
    emit_shaped("slagstone_pillar", {"#": f"{ci}slagstone_bricks"},
                ["#", "#"], f"{ci}slagstone_pillar", 2)
    emit_shaped("forge_pillar", {"#": f"{ci}forge_bricks"},
                ["#", "#"], f"{ci}forge_pillar", 2)
    emit_shaped("ashen_pillar", {"#": f"{ci}ashen_bricks"},
                ["#", "#"], f"{ci}ashen_pillar", 2)
    emit_shaped("quenched_slag_pillar", {"#": f"{ci}quenched_slag"},
                ["#", "#"], f"{ci}quenched_slag_pillar", 2)

    # Remaining singles.
    emit_shaped("ashen_mosaic", {"#": f"{ci}ashen_bricks"},
                ["##", "##"], f"{ci}ashen_mosaic", 4)
    emit_shapeless("ember_coal_block", ["minecraft:coal_block", "minecraft:blaze_powder"],
                   f"{ci}ember_coal_block", 1)
    emit_shaped("ember_lantern", {"N": "minecraft:iron_nugget", "P": "minecraft:blaze_powder"},
                ["NNN", "NPN", "NNN"], f"{ci}ember_lantern", 1)
    emit_shaped("ashen_lantern", {"N": "minecraft:iron_nugget", "G": "minecraft:glow_ink_sac"},
                ["NNN", "NGN", "NNN"], f"{ci}ashen_lantern", 1)
    emit_shapeless("smolder_lamp", ["minecraft:glowstone", "minecraft:magma_block"],
                   f"{ci}smolder_lamp", 1)
    emit_shapeless("ashen_lamp", ["minecraft:glowstone", f"{ci}ashen_bricks"],
                   f"{ci}ashen_lamp", 1)
    emit_shaped("cinder_glass", {"G": "minecraft:glass", "C": "minecraft:charcoal"},
                ["GGG", "GCG", "GGG"], f"{ci}cinder_glass", 8)
    emit_shaped("smolder_glass", {"G": "minecraft:glass", "M": "minecraft:magma_cream"},
                ["GGG", "GMG", "GGG"], f"{ci}smolder_glass", 8)
    emit_shaped("cinder_glass_pane", {"G": f"{ci}cinder_glass"},
                ["GGG", "GGG"], f"{ci}cinder_glass_pane", 16)
    emit_shaped("smolder_glass_pane", {"G": f"{ci}smolder_glass"},
                ["GGG", "GGG"], f"{ci}smolder_glass_pane", 16)
    emit_shaped("forge_heart", {"#": f"{ci}forge_bricks", "C": "minecraft:copper_block"},
                ["###", "#C#", "###"], f"{ci}forge_heart", 1)

    # Stonecutting from the cinderstone/slagstone bases (masonry stonecutting format).
    for result, count in (("cinderstone_brick_slab", 2), ("cinderstone_brick_stairs", 1),
                          ("cinderstone_brick_wall", 1), ("chiseled_cinderstone_bricks", 1),
                          ("cinderstone_pillar", 1), ("cinderstone_tiles", 1),
                          ("polished_cinderstone", 1)):
        emit_stonecutting(f"{result}_from_cinderstone_bricks_stonecutting",
                          f"{ci}cinderstone_bricks", f"{ci}{result}", count)
    for result, count in (("slagstone_brick_slab", 2), ("slagstone_brick_stairs", 1),
                          ("slagstone_brick_wall", 1), ("chiseled_slagstone_bricks", 1),
                          ("slagstone_pillar", 1), ("quenched_slag", 1)):
        emit_stonecutting(f"{result}_from_slagstone_bricks_stonecutting",
                          f"{ci}slagstone_bricks", f"{ci}{result}", count)


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic — seeded per texture name)
# ---------------------------------------------------------------------------

def new_canvas(rng: Random, shades) -> Image.Image:
    """Base fill with CLUSTERED 2x2 mottling plus sparse single-pixel accents
    (structured shading rather than raw per-pixel static)."""
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


def brick_texture(rng: Random, look: str, ember_prob: float,
                  accents=None) -> Image.Image:
    """Running-bond bricks: 4px courses, 8px bricks, 1px mortar; optional glowing
    accent pixels along the mortar seams."""
    shades, mortar = LOOKS[look]
    img = new_canvas(rng, shades)
    seams = []
    for y in range(16):
        row = y // 4
        offset = (row % 2) * 4
        for x in range(16):
            if y % 4 == 3 or (x - offset) % 8 == 7:
                img.putpixel((x, y), mortar)
                seams.append((x, y))
    if ember_prob > 0:
        sprinkle(img, rng, seams, accents or [EMBER, EMBER_BRIGHT], ember_prob)
    return img


def tex_cinderstone_bricks(rng: Random) -> Image.Image:
    return brick_texture(rng, "charcoal", 0.08)


def tex_cinderstone_tiles(rng: Random) -> Image.Image:
    """2x2 grid of 8x8 charcoal tiles: dark grout, faint ember dots at crossings."""
    img = new_canvas(rng, LOOKS["charcoal"][0])
    grout = []
    for y in range(16):
        for x in range(16):
            if x % 8 == 7 or y % 8 == 7:
                img.putpixel((x, y), CHARCOAL_MORTAR)
                grout.append((x, y))
    sprinkle(img, rng, grout, [EMBER], 0.05)
    for x, y in [(7, 7), (15, 7), (7, 15), (15, 15)]:
        img.putpixel((x, y), EMBER)
    return img


def tex_polished_cinderstone(rng: Random) -> Image.Image:
    """Smooth charcoal slab with a 1px bevel frame (polished look, no embers)."""
    img = new_canvas(rng, [CHARCOAL, CHARCOAL, CHARCOAL_LIGHT])
    for i in range(16):
        img.putpixel((i, 0), CHARCOAL_LIGHT)
        img.putpixel((0, i), CHARCOAL_LIGHT)
        img.putpixel((i, 15), CHARCOAL_MORTAR)
        img.putpixel((15, i), CHARCOAL_MORTAR)
    return img


def tex_slagstone_bricks(rng: Random) -> Image.Image:
    return brick_texture(rng, "slag", 0.10, accents=[SLAG_TEAL, EMBER])


def tex_ashen_bricks(rng: Random) -> Image.Image:
    return brick_texture(rng, "ash", 0.0)


def tex_smoldering_bricks(rng: Random) -> Image.Image:
    """Charcoal bricks with heavily glowing seams and embers inside the bricks."""
    img = brick_texture(rng, "charcoal", 0.45)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    sprinkle(img, rng, all_px, [EMBER, EMBER_BRIGHT], 0.05)
    return img


def tex_forge_bricks(rng: Random) -> Image.Image:
    """Dark copper-tinted bricks with ember seams and sparse copper glints."""
    img = brick_texture(rng, "forge", 0.18)
    all_px = [(x, y) for y in range(16) for x in range(16)]
    sprinkle(img, rng, all_px, [COPPER_GLINT], 0.04)
    return img


def tex_quenched_slag(rng: Random) -> Image.Image:
    """Mottled cooled slag: teal-gray blobs with dark shrink cracks."""
    img = new_canvas(rng, [SLAG_DARK, SLAG, SLAG, SLAG_LIGHT, SLAG_TEAL])
    x = 4
    for y in range(16):  # one wandering dark crack
        x = max(1, min(14, x + rng.choice([-1, 0, 1])))
        img.putpixel((x, y), SLAG_MORTAR)
    y = 11
    for x2 in range(16):  # and one crossing horizontally
        y = max(1, min(14, y + rng.choice([-1, 0, 1])))
        img.putpixel((x2, y), SLAG_MORTAR)
    return img


def cracked(base_fn, rng: Random) -> Image.Image:
    """Base texture plus a jagged glowing fissure running top to bottom."""
    img = base_fn(rng)
    x = 6
    for y in range(16):
        x = max(1, min(14, x + rng.choice([-1, 0, 0, 1])))
        img.putpixel((x, y), FISSURE)
        if rng.random() < 0.4:
            img.putpixel((x + rng.choice([-1, 1]), y), EMBER)
        if rng.random() < 0.2:
            img.putpixel((x, y), EMBER_BRIGHT)
    return img


def framed(rng: Random, look: str) -> Image.Image:
    """1px mortar frame with a lighter inner bevel row — chiseled base plate."""
    shades, mortar = LOOKS[look]
    img = new_canvas(rng, shades)
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), mortar)
        if 0 < i < 15:
            img.putpixel((i, 1), shades[-1])
    return img


DIAMOND_GLYPH = [(8, 4), (7, 5), (8, 5), (9, 5), (6, 6), (7, 6), (9, 6), (10, 6),
                 (5, 7), (6, 7), (10, 7), (11, 7), (5, 8), (6, 8), (10, 8), (11, 8),
                 (6, 9), (7, 9), (9, 9), (10, 9), (7, 10), (8, 10), (9, 10), (8, 11)]


def tex_chiseled_cinderstone_bricks(rng: Random) -> Image.Image:
    img = framed(rng, "charcoal")
    for x, y in DIAMOND_GLYPH:
        img.putpixel((x, y), EMBER)
    for x, y in [(8, 7), (8, 8), (7, 8), (7, 7)]:
        img.putpixel((x, y), EMBER_BRIGHT)
    img.putpixel((8, 8), EMBER_HOT)
    return img


RING_GLYPH = [(7, 4), (8, 4), (6, 5), (9, 5), (5, 6), (10, 6), (5, 7), (10, 7),
              (5, 8), (10, 8), (5, 9), (10, 9), (6, 10), (9, 10), (7, 11), (8, 11)]


def tex_chiseled_slagstone_bricks(rng: Random) -> Image.Image:
    img = framed(rng, "slag")
    for x, y in RING_GLYPH:
        img.putpixel((x, y), SLAG_TEAL)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), EMBER)
    return img


ANVIL_GLYPH = [(5, 5), (6, 5), (7, 5), (8, 5), (9, 5), (10, 5), (7, 6), (8, 6),
               (7, 7), (8, 7), (6, 8), (7, 8), (8, 8), (9, 8), (5, 9), (6, 9),
               (7, 9), (8, 9), (9, 9), (10, 9)]


def tex_chiseled_forge_bricks(rng: Random) -> Image.Image:
    img = framed(rng, "forge")
    for x, y in ANVIL_GLYPH:
        img.putpixel((x, y), COPPER_GLINT)
    for x, y in [(7, 6), (8, 6)]:
        img.putpixel((x, y), EMBER_BRIGHT)
    return img


SWIRL_GLYPH = [(6, 5), (7, 5), (8, 5), (9, 5), (5, 6), (10, 6), (10, 7), (7, 7),
               (8, 7), (9, 8), (7, 8), (6, 9), (7, 9), (8, 9), (9, 10), (5, 8),
               (5, 9), (6, 10), (7, 10), (8, 10)]


def tex_chiseled_ashen_bricks(rng: Random) -> Image.Image:
    img = framed(rng, "ash")
    for x, y in SWIRL_GLYPH:
        img.putpixel((x, y), ASH_MORTAR)
    for x, y in [(7, 7), (8, 7)]:
        img.putpixel((x, y), ASH_LIGHT)
    return img


def tex_carved_cinderstone(rng: Random) -> Image.Image:
    """Concentric carved squares in polished charcoal with ember corner dots."""
    img = new_canvas(rng, [CHARCOAL, CHARCOAL, CHARCOAL_LIGHT])
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), CHARCOAL_MORTAR)
    for i in range(4, 12):
        for x, y in ((i, 4), (i, 11), (4, i), (11, i)):
            img.putpixel((x, y), CHARCOAL_DARK)
    for i in range(7, 9):
        for x, y in ((i, 7), (i, 8), (7, i), (8, i)):
            img.putpixel((x, y), CHARCOAL_LIGHT)
    for x, y in [(4, 4), (11, 4), (4, 11), (11, 11)]:
        img.putpixel((x, y), EMBER)
    return img


def tex_ashen_mosaic(rng: Random) -> Image.Image:
    """4x4 checker of diagonal-striped ash cells (mosaic parquet look)."""
    img = Image.new("RGB", (16, 16))
    for y in range(16):
        for x in range(16):
            cell = (x // 4 + y // 4) % 2
            d = (x + y) % 4 if cell == 0 else (x - y) % 4
            if x % 4 == 0 or y % 4 == 0:
                img.putpixel((x, y), ASH_MORTAR)
            elif d == 0:
                img.putpixel((x, y), ASH_LIGHT)
            elif d == 2:
                img.putpixel((x, y), ASH_DARK)
            else:
                img.putpixel((x, y), ASH)
    return img


def lamp_texture(rng: Random, frame_shades, frame_line, cells) -> Image.Image:
    """Warm 3x3-cell grid lamp: stone frame lines, glowing cells."""
    img = new_canvas(rng, frame_shades)
    for y in range(16):
        for x in range(16):
            if x in (0, 15) or y in (0, 15) or x in (5, 10) or y in (5, 10):
                if not (x in (0, 15) or y in (0, 15)):
                    img.putpixel((x, y), frame_line)
            else:
                img.putpixel((x, y), cells[(x * 31 + y * 17) % len(cells)])
    return img


def tex_smolder_lamp(rng: Random) -> Image.Image:
    return lamp_texture(rng, [CHARCOAL_DARK, CHARCOAL], CHARCOAL_MORTAR,
                        [EMBER_HOT, EMBER_BRIGHT, EMBER_BRIGHT, EMBER])


def tex_ashen_lamp(rng: Random) -> Image.Image:
    return lamp_texture(rng, [ASH_DARK, ASH], ASH_MORTAR,
                        [(0xFF, 0xE8, 0xC2), (0xF2, 0xD4, 0xA6), (0xE0, 0xC0, 0x90),
                         (0xFF, 0xE8, 0xC2)])


def tex_ember_coal_block(rng: Random) -> Image.Image:
    """Near-black coal chunks with glowing ember cracks between them."""
    img = new_canvas(rng, [(0x17, 0x14, 0x15), (0x22, 0x1D, 0x1F), (0x2B, 0x22, 0x26)])
    cracks = []
    for y in range(16):
        for x in range(16):
            if (x * 7 + y * 5) % 13 == 0:
                cracks.append((x, y))
    for x, y in cracks:
        img.putpixel((x, y), FISSURE)
    sprinkle(img, rng, cracks, [EMBER, EMBER_BRIGHT], 0.5)
    return img


def tex_forge_heart(rng: Random) -> Image.Image:
    """Forge-brick frame around a radiant molten copper core (block emits light 13)."""
    img = framed(rng, "forge")
    ring = []
    for i in range(4, 12):
        ring += [(i, 4), (i, 11), (4, i), (11, i)]
    for x, y in ring:
        img.putpixel((x, y), FORGE_MORTAR)
    sprinkle(img, rng, ring, [EMBER], 0.35)
    for y in range(5, 11):
        for x in range(5, 11):
            img.putpixel((x, y), EMBER if (x + y) % 2 else EMBER_BRIGHT)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), EMBER_HOT)
    return img


def pillar_side(rng: Random, look: str, accents, seam_prob: float) -> Image.Image:
    """Vertical column striping with two accent seams (basalt-side style)."""
    shades, mortar = LOOKS[look]
    dark, mid, _, light = shades[0], shades[1], shades[2], shades[-1]
    img = Image.new("RGB", (16, 16))
    for x in range(16):
        if x in (0, 15):
            column = [mortar]
        elif x in (1, 7, 8, 14):
            column = [dark, dark, mid]
        else:
            column = [mid, mid, light, dark]
        shade = rng.choice(column)
        for y in range(16):
            if y % 2 == 0:
                shade = rng.choice(column)
            img.putpixel((x, y), shade)
    seam_px = [(x, y) for x in (4, 11) for y in range(16)]
    sprinkle(img, rng, seam_px, accents, seam_prob)
    return img


def pillar_top(rng: Random, look: str, accents) -> Image.Image:
    """Framed top face with an accent inner ring and bright centre (basalt-top style)."""
    shades, mortar = LOOKS[look]
    img = new_canvas(rng, shades)
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), mortar)
    ring = []
    for i in range(3, 13):
        ring += [(i, 3), (i, 12), (3, i), (12, i)]
    for x, y in ring:
        img.putpixel((x, y), shades[0])
    sprinkle(img, rng, ring, accents, 0.3)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), accents[-1])
    return img


PILLAR_LOOKS = {
    "cinderstone_pillar": ("charcoal", [EMBER, EMBER_BRIGHT], 0.3),
    "slagstone_pillar": ("slag", [SLAG_TEAL, EMBER], 0.3),
    "forge_pillar": ("forge", [EMBER, COPPER_GLINT], 0.35),
    "ashen_pillar": ("ash", [ASH_LIGHT, ASH_MORTAR], 0.25),
    "quenched_slag_pillar": ("slag", [SLAG_TEAL, SLAG_LIGHT], 0.3),
}


def glass_texture(rng: Random, frame_dark, frame_mid, tint, glow=None) -> Image.Image:
    """2px stone frame, semi-transparent tinted center (real RGBA alpha)."""
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in range(16):
            edge = min(x, y, 15 - x, 15 - y)
            if edge == 0:
                px[x, y] = (*frame_dark, 255)
            elif edge == 1:
                px[x, y] = (*frame_mid, 255) if (x * 31 + y * 17) % 5 else (*frame_dark, 255)
            else:
                if glow is not None and (x * 7 + y * 11) % 9 == 0:
                    px[x, y] = (*glow, 150)
                elif (x + y) % 7 == 0:
                    px[x, y] = (*tint, 110)
                else:
                    px[x, y] = (*tint, 70)
    return img


def tex_cinder_glass(rng: Random) -> Image.Image:
    return glass_texture(rng, CHARCOAL_MORTAR, CHARCOAL, (0x6B, 0x5A, 0x5E))


def tex_smolder_glass(rng: Random) -> Image.Image:
    return glass_texture(rng, CHARCOAL_MORTAR, CHARCOAL, (0xE2, 0x8A, 0x50),
                         glow=EMBER_BRIGHT)


def pane_top(rng: Random, mid, dark, darker) -> Image.Image:
    """Edge texture for pane post/side models: 2px opaque strip at columns 7-8."""
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(16):
        for x in (7, 8):
            if y in (0, 15):
                px[x, y] = (*darker, 255)
            else:
                px[x, y] = (*mid, 255) if (x * 31 + y * 17) % 5 < 3 else (*dark, 255)
    return img


def tex_cinder_glass_pane_top(rng: Random) -> Image.Image:
    return pane_top(rng, CHARCOAL, CHARCOAL_DARK, CHARCOAL_MORTAR)


def tex_smolder_glass_pane_top(rng: Random) -> Image.Image:
    return pane_top(rng, EMBER, CHARCOAL, CHARCOAL_MORTAR)


def lantern_texture(rng: Random, metal, flame) -> Image.Image:
    """Lantern texture in the vanilla template_lantern UV layout (transparent RGBA).

    Regions sampled by the vanilla templates: body sides (0,2)-(5,8), body
    top/bottom (0,9)-(5,14), cap sides (1,0)-(4,1), handle column (11..13, 0..12).
    """
    m_light, m_base, m_dark, m_darker = metal
    f_light, f_base, f_dark = flame
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y in range(2, 9):  # body sides: caged frame around a glowing window
        for x in range(0, 6):
            if x in (0, 5) or y in (2, 8):
                px[x, y] = (*m_darker, 255)
            elif x in (1, 4) or y == 3:
                px[x, y] = (*m_dark, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_base, 255)
            else:
                px[x, y] = (*f_light, 255) if (x * 31 + y * 17 + 7) % 5 == 0 else (*f_base, 255)
    px[2, 7] = (*f_dark, 255)
    px[3, 5] = (*f_light, 255)
    for y in range(9, 15):  # body top/bottom
        for x in range(0, 6):
            if x in (0, 5) or y in (9, 14):
                px[x, y] = (*m_darker, 255)
            else:
                px[x, y] = (*m_base, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_dark, 255)
    for y in range(0, 2):  # cap sides
        for x in range(1, 5):
            px[x, y] = (*m_dark, 255) if y == 0 else (*m_base, 255)
    for y in range(0, 13):  # handle/chain strip
        px[12, y] = (*m_darker, 255)
        if y % 2 == 0:
            px[11, y] = (*m_dark, 255)
        else:
            px[13, y] = (*m_dark, 255)
    return img


def lantern_item_texture(rng: Random, metal, flame) -> Image.Image:
    """Flat 16x16 item sprite: small caged lantern with glowing window."""
    m_light, m_base, m_dark, m_darker = metal
    f_light, f_base, f_dark = flame
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    px[7, 1] = (*m_dark, 255)
    px[8, 1] = (*m_dark, 255)
    px[7, 2] = (*m_darker, 255)
    px[8, 2] = (*m_darker, 255)
    for x in range(5, 11):
        px[x, 3] = (*m_dark, 255)
        px[x, 4] = (*m_base, 255)
    for y in range(5, 13):
        for x in range(4, 12):
            if x in (4, 11) or y in (5, 12):
                px[x, y] = (*m_darker, 255)
            elif x in (5, 10):
                px[x, y] = (*m_dark, 255) if (x * 31 + y * 17) % 5 < 3 else (*m_base, 255)
            else:
                px[x, y] = (*f_light, 255) if (x * 31 + y * 17 + 3) % 5 == 0 else (*f_base, 255)
    px[7, 11] = (*f_dark, 255)
    px[8, 7] = (*f_light, 255)
    for x in range(5, 11):
        px[x, 13] = (*m_dark, 255)
    return img


LANTERN_METAL = (CHARCOAL_LIGHT, CHARCOAL, CHARCOAL_DARK, CHARCOAL_MORTAR)
EMBER_FLAME = (EMBER_HOT, EMBER_BRIGHT, EMBER)
ASH_FLAME = ((0xFF, 0xF2, 0xD8), (0xE8, 0xD8, 0xB4), (0xB8, 0xA8, 0x88))


def tex_ember_lantern(rng: Random) -> Image.Image:
    return lantern_texture(rng, LANTERN_METAL, EMBER_FLAME)


def tex_ashen_lantern(rng: Random) -> Image.Image:
    return lantern_texture(rng, (ASH_LIGHT, ASH, ASH_DARK, ASH_MORTAR), ASH_FLAME)


def tex_ember_lantern_item(rng: Random) -> Image.Image:
    return lantern_item_texture(rng, LANTERN_METAL, EMBER_FLAME)


def tex_ashen_lantern_item(rng: Random) -> Image.Image:
    return lantern_item_texture(rng, (ASH_LIGHT, ASH, ASH_DARK, ASH_MORTAR), ASH_FLAME)


BLOCK_TEXTURES = {
    "cinderstone_bricks": tex_cinderstone_bricks,
    "cinderstone_tiles": tex_cinderstone_tiles,
    "polished_cinderstone": tex_polished_cinderstone,
    "slagstone_bricks": tex_slagstone_bricks,
    "ashen_bricks": tex_ashen_bricks,
    "smoldering_bricks": tex_smoldering_bricks,
    "forge_bricks": tex_forge_bricks,
    "quenched_slag": tex_quenched_slag,
    "cracked_cinderstone_bricks": lambda rng: cracked(tex_cinderstone_bricks, rng),
    "chiseled_cinderstone_bricks": tex_chiseled_cinderstone_bricks,
    "carved_cinderstone": tex_carved_cinderstone,
    "cracked_slagstone_bricks": lambda rng: cracked(tex_slagstone_bricks, rng),
    "chiseled_slagstone_bricks": tex_chiseled_slagstone_bricks,
    "cracked_forge_bricks": lambda rng: cracked(tex_forge_bricks, rng),
    "chiseled_forge_bricks": tex_chiseled_forge_bricks,
    "chiseled_ashen_bricks": tex_chiseled_ashen_bricks,
    "ashen_mosaic": tex_ashen_mosaic,
    "smolder_lamp": tex_smolder_lamp,
    "ashen_lamp": tex_ashen_lamp,
    "ember_coal_block": tex_ember_coal_block,
    "forge_heart": tex_forge_heart,
    "cinder_glass": tex_cinder_glass,
    "smolder_glass": tex_smolder_glass,
    "cinder_glass_pane_top": tex_cinder_glass_pane_top,
    "smolder_glass_pane_top": tex_smolder_glass_pane_top,
    "ember_lantern": tex_ember_lantern,
    "ashen_lantern": tex_ashen_lantern,
}

ITEM_TEXTURES = {
    "ember_lantern": tex_ember_lantern_item,
    "ashen_lantern": tex_ashen_lantern_item,
}


def emit_textures() -> None:
    block_dir = ASSETS / "textures" / "block"
    block_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in BLOCK_TEXTURES.items():
        fn(Random(f"copper_inferno:{name}")).save(block_dir / f"{name}.png")
    for pillar, (look, accents, seam_prob) in PILLAR_LOOKS.items():
        pillar_side(Random(f"copper_inferno:{pillar}_side"), look, accents, seam_prob) \
            .save(block_dir / f"{pillar}_side.png")
        pillar_top(Random(f"copper_inferno:{pillar}_top"), look, accents) \
            .save(block_dir / f"{pillar}_top.png")
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in ITEM_TEXTURES.items():
        fn(Random(f"copper_inferno:item/{name}")).save(item_dir / f"{name}.png")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    ids = []
    for base, stem in FAMILIES:
        emit_family(base, stem)
        emit_drop_self_loot(base)
        emit_slab_loot(f"{stem}_slab")
        emit_drop_self_loot(f"{stem}_stairs")
        emit_drop_self_loot(f"{stem}_wall")
        ids += [base, f"{stem}_slab", f"{stem}_stairs", f"{stem}_wall"]
    for name in SINGLE_CUBES:
        emit_cube(name)
        emit_drop_self_loot(name)
        ids.append(name)
    for name in PILLARS:
        emit_pillar(name)
        emit_drop_self_loot(name)
        ids.append(name)
    for name in ("cinder_glass", "smolder_glass"):
        emit_glass(name)
        emit_drop_self_loot(name)
        ids.append(name)
    for name, glass in (("cinder_glass_pane", "cinder_glass"),
                        ("smolder_glass_pane", "smolder_glass")):
        emit_pane(name, glass)
        emit_drop_self_loot(name)
        ids.append(name)
    for name in ("ember_lantern", "ashen_lantern"):
        emit_lantern(name)
        emit_drop_self_loot(name)
        ids.append(name)

    emit_recipes()
    emit_textures()
    write_json(ASSETS / "lang" / "fragments" / "cinderstone.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "cinderstone.json", LANG_DE)

    assert len(ids) == 56, f"expected 56 block ids, got {len(ids)}"
    assert sorted(f"block.{NS}.{i}" for i in ids) == sorted(LANG_EN) == sorted(LANG_DE)
    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for legacy JSON)"
    print(f"cinderstone_gen: assets generated for {len(ids)} blocks ({mode}).")


if __name__ == "__main__":
    main()
