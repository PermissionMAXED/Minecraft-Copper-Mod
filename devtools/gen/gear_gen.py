#!/usr/bin/env python3
"""Asset generator for the GEAR feature (COPPER INFERNO 1 v2).

Emits ALL JSON (item definitions, item models, recipes, lang fragment) and all
16x16 PNG textures for the 12 gear items, directly into src/main/resources.
Idempotent: running it twice produces byte-identical output (all pixel art is
hard-coded character maps; no RNG).

JSON formats are copied from EXACT vanilla 1.21.9 templates extracted from
~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar
(data/minecraft/recipe/shield.json for crafting_shaped,
data/minecraft/recipe/packed_ice.json for crafting_shapeless) and from the v1
copper_horn item-definition/model indirection (assets/copper_inferno/items/ +
models/item/, parent minecraft:item/generated).

The three oxidized horn textures reuse the exact silhouette of the v1
assets/copper_inferno/textures/item/copper_horn.png (hard-coded below) with
the stage palettes used by the copper tools/armor textures.

NOTE: JSON emission is legacy scaffolding (opt-in via --write-json); the JSON in
src/main/resources is authoritative — by default this script writes ONLY PNGs.

Usage: python3 devtools/gen/gear_gen.py [--write-json]
"""

import json
import sys
from pathlib import Path

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> textures/*.png only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / "copper_inferno"
DATA = RES / "data" / "copper_inferno"

MOD = "copper_inferno"

ITEM_IDS = [
    "throwing_fizz_can",
    "exposed_copper_horn",
    "weathered_copper_horn",
    "oxidized_copper_horn",
    "copper_whistle",
    "copper_magnet",
    "copper_buckler",
    "copper_monocle",
    "copper_key",
    "copper_badge",
    "inferno_badge",
    "soda_badge",
]

LANG = {
    "item.copper_inferno.throwing_fizz_can": "Throwing Fizz Can",
    "item.copper_inferno.exposed_copper_horn": "Exposed Copper Horn",
    "item.copper_inferno.weathered_copper_horn": "Weathered Copper Horn",
    "item.copper_inferno.oxidized_copper_horn": "Oxidized Copper Horn",
    "item.copper_inferno.copper_whistle": "Copper Whistle",
    "item.copper_inferno.copper_magnet": "Copper Magnet",
    "item.copper_inferno.copper_buckler": "Copper Buckler",
    "item.copper_inferno.copper_monocle": "Copper Monocle",
    "item.copper_inferno.copper_key": "Copper Key",
    "item.copper_inferno.copper_badge": "Copper Badge",
    "item.copper_inferno.inferno_badge": "Inferno Badge",
    "item.copper_inferno.soda_badge": "Soda Badge",
}

# ---------------------------------------------------------------------------
# Palette (copper family matches the existing copper_horn / copper tool
# textures; maroon can colors match the fizz_bomb / Dr.Pepper family).
# ---------------------------------------------------------------------------
COPPER_LIGHT = (240, 144, 107)
COPPER_BASE = (224, 115, 77)
COPPER_MID = (193, 90, 59)
COPPER_DARK = (122, 58, 40)
COPPER_DEEP = (90, 42, 28)

EXPOSED_MID = (147, 65, 42)
EXPOSED_DARK = (107, 74, 43)

GREEN_LIGHT = (143, 198, 168)
GREEN_BASE = (111, 176, 142)
GREEN_MID = (87, 160, 123)
GREEN_DARK = (59, 122, 94)
GREEN_DEEP = (46, 95, 73)

MAROON = (90, 14, 20)
MAROON_LIGHT = (122, 27, 34)
MAROON_DARK = (58, 8, 12)
WHITE = (242, 239, 234)
SILVER = (154, 160, 166)
SILVER_DARK = (110, 115, 120)
SILVER_LIGHT = (198, 203, 209)
GLASS = (176, 216, 230)
IRON = (216, 216, 216)
EMBER = (214, 92, 32)
EMBER_LIGHT = (240, 140, 60)
EMBER_DARK = (74, 22, 12)
FLAME = (250, 200, 80)
RAW_COPPER = (154, 90, 62)


def paint(rows, palette):
    """Renders a 16x16 char map: '.' = transparent, everything else = palette."""
    assert len(rows) == 16, f"need 16 rows, got {len(rows)}"
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y, row in enumerate(rows):
        assert len(row) == 16, f"row {y} is {len(row)} wide: {row!r}"
        for x, ch in enumerate(row):
            if ch != ".":
                px[x, y] = palette[ch] + (255,)
    return img


# ---------------------------------------------------------------------------
# Pixel art
# ---------------------------------------------------------------------------

# Exact silhouette of the v1 copper_horn.png (bell top-right, mouthpiece
# bottom-left). Roles: L light, B base, M mid, D dark shading, G accent.
HORN_ROWS = [
    "................",
    ".........BBBBB..",
    "........BBLLDB..",
    "........BLLLDB..",
    ".......BBLLBM...",
    "......BBLLBM....",
    ".....BBLLBM.....",
    "....BBLLBM......",
    "...BBLLBM.......",
    "...BBLBM........",
    "..BBBLM.........",
    "..BBBM..........",
    "..GBBB..........",
    "..GGB...........",
    "...G............",
    "................",
]

# stage id -> (palette for HORN_ROWS roles, [(x, y, color) speckle overrides])
HORN_STAGES = {
    "exposed_copper_horn": (
        {"L": COPPER_BASE, "B": COPPER_MID, "M": EXPOSED_MID, "D": EXPOSED_DARK, "G": GREEN_BASE},
        [(10, 2, GREEN_BASE), (6, 6, GREEN_MID), (4, 9, GREEN_BASE)],
    ),
    "weathered_copper_horn": (
        {"L": GREEN_LIGHT, "B": GREEN_BASE, "M": GREEN_MID, "D": GREEN_DARK, "G": GREEN_DARK},
        [(11, 2, COPPER_MID), (5, 7, COPPER_MID)],
    ),
    "oxidized_copper_horn": (
        {"L": GREEN_BASE, "B": GREEN_MID, "M": GREEN_DARK, "D": GREEN_DEEP, "G": GREEN_LIGHT},
        [],
    ),
}

# Shaken maroon soda can with motion lines ('/').
FIZZ_CAN_ROWS = [
    "................",
    "...../......./..",
    ".....TTTT.......",
    "../.STTTTS../...",
    "....SMNMMS......",
    "./..SMNMMS.../..",
    "....SWWWWS......",
    "../.SWRRWS../...",
    "....SWWWWS......",
    "./..SMNMMS.../..",
    "....SMNMMS......",
    "../.SMNMMS../...",
    "....SDDDDS......",
    ".....SSSS.......",
    "................",
    "................",
]
FIZZ_CAN_PALETTE = {
    "T": SILVER_LIGHT, "S": SILVER_DARK, "M": MAROON, "N": MAROON_LIGHT,
    "D": MAROON_DARK, "W": WHITE, "R": MAROON, "/": SILVER_LIGHT,
}

# Pea whistle facing right: mouthpiece tube top-left, air hole, round chamber.
WHISTLE_ROWS = [
    "................",
    "................",
    "..OOOOOOOOO.....",
    "..OLLLLLCCO.....",
    "..OCCCCCCCOO....",
    "..OOOOOOCCKO....",
    ".......OCCKO....",
    "......OCCCCO....",
    ".....OCMMMMCO...",
    "....OCMMLMMMCO..",
    "....OCMMMMMMCO..",
    "....OCMMMMMMCO..",
    ".....OCMMMMCO...",
    "......OOOOOO....",
    "................",
    "................",
]
WHISTLE_PALETTE = {
    "O": COPPER_DEEP, "C": COPPER_BASE, "M": COPPER_MID, "L": COPPER_LIGHT,
    "K": (40, 24, 18),
}

# Horseshoe magnet: copper arch, silver + white pole tips at the bottom.
MAGNET_ROWS = [
    "................",
    "................",
    "....OOOOOOOO....",
    "...OCCCCCCCCO...",
    "..OCCLLCCLLCCO..",
    "..OCCOOOOOOCCO..",
    "..OCCO....OCCO..",
    "..OCCO....OCCO..",
    "..OCCO....OCCO..",
    "..OCCO....OCCO..",
    "..OSSO....OSSO..",
    "..OWWO....OWWO..",
    "..OOOO....OOOO..",
    "................",
    "................",
    "................",
]
MAGNET_PALETTE = {
    "O": COPPER_DEEP, "C": COPPER_BASE, "L": COPPER_LIGHT, "S": SILVER,
    "W": WHITE,
}

# Round buckler: copper disc, dark rivets (R), iron boss (S) in the middle.
BUCKLER_ROWS = [
    "................",
    ".....OOOOOO.....",
    "...OOCCCCCCOO...",
    "..OCCLCCCCLCCO..",
    "..OCRCCCCCCRCO..",
    ".OCCCCMMMMCCCCO.",
    ".OCCCMSSSSMCCCO.",
    ".OCCCMSSSSMCCCO.",
    ".OCCCCMMMMCCCCO.",
    "..OCRCCCCCCRCO..",
    "..OCCLCCCCLCCO..",
    "...OOCCCCCCOO...",
    ".....OOOOOO.....",
    "................",
    "................",
    "................",
]
BUCKLER_PALETTE = {
    "O": COPPER_DEEP, "C": COPPER_BASE, "L": COPPER_LIGHT, "M": COPPER_MID,
    "R": COPPER_DARK, "S": IRON,
}

# Monocle: copper rim, pale glass lens with a glint, dangling chain.
MONOCLE_ROWS = [
    "................",
    "................",
    "....OOOOOO......",
    "...OCCCCCCO.....",
    "..OCGWGGGGCO....",
    "..OCGGGGGGCO....",
    "..OCGGGGGGCO....",
    "..OCGGGGGGCO....",
    "...OCCCCCCO.....",
    "....OOOOOO......",
    ".........C......",
    "..........C.....",
    ".........C......",
    "..........C.....",
    "................",
    "................",
]
MONOCLE_PALETTE = {
    "O": COPPER_DEEP, "C": COPPER_BASE, "G": GLASS, "W": WHITE,
}

# Ornate key: ring bow at the top, shaft, two teeth at the bottom right.
KEY_ROWS = [
    "................",
    ".....OOOO.......",
    "....OCCCCO......",
    "...OCLOOCCO.....",
    "...OCO..OCO.....",
    "...OCLOOCCO.....",
    "....OCCCCO......",
    "......OCO.......",
    "......OCO.......",
    "......OCO.......",
    "......OCO.......",
    "......OCCOO.....",
    "......OCO.......",
    "......OCCO......",
    "......OOO.......",
    "................",
]
KEY_PALETTE = {
    "O": COPPER_DEEP, "C": COPPER_BASE, "L": COPPER_LIGHT,
}

# Heater-shield badge crest; F fill, L glint, E center emblem, O border.
BADGE_ROWS = [
    "................",
    "................",
    "..OOOOOOOOOOOO..",
    "..OFFFFFFFFFFO..",
    "..OFLFFFFFFFFO..",
    "..OFFFFEEFFFFO..",
    "..OFFFEEEEFFFO..",
    "..OFFFEEEEFFFO..",
    "...OFFFEEFFFO...",
    "...OFFFFFFFFO...",
    "....OFFFFFFO....",
    ".....OFFFFO.....",
    "......OFFO......",
    ".......OO.......",
    "................",
    "................",
]
BADGE_PALETTES = {
    "copper_badge": {"O": COPPER_DEEP, "F": COPPER_BASE, "L": COPPER_LIGHT, "E": RAW_COPPER},
    "inferno_badge": {"O": EMBER_DARK, "F": EMBER, "L": EMBER_LIGHT, "E": FLAME},
    "soda_badge": {"O": MAROON_DARK, "F": MAROON, "L": MAROON_LIGHT, "E": WHITE},
}


def textures():
    out = {}
    out["throwing_fizz_can"] = paint(FIZZ_CAN_ROWS, FIZZ_CAN_PALETTE)
    for horn_id, (palette, speckles) in HORN_STAGES.items():
        img = paint(HORN_ROWS, palette)
        px = img.load()
        for x, y, color in speckles:
            px[x, y] = color + (255,)
        out[horn_id] = img
    out["copper_whistle"] = paint(WHISTLE_ROWS, WHISTLE_PALETTE)
    out["copper_magnet"] = paint(MAGNET_ROWS, MAGNET_PALETTE)
    out["copper_buckler"] = paint(BUCKLER_ROWS, BUCKLER_PALETTE)
    out["copper_monocle"] = paint(MONOCLE_ROWS, MONOCLE_PALETTE)
    out["copper_key"] = paint(KEY_ROWS, KEY_PALETTE)
    for badge_id, palette in BADGE_PALETTES.items():
        out[badge_id] = paint(BADGE_ROWS, palette)
    return out


# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 template formats; only vanilla + own v1/v2 ids).
# NOTE: the three oxidized horns have NO recipes — they are obtained by
# oxidizing the v1 copper_inferno:copper_horn over time.
# ---------------------------------------------------------------------------

def shaped(key, pattern, result_id, count=1, category="equipment"):
    return {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": f"{MOD}:{result_id}"},
    }


def shapeless(ingredients, result_id, count=1, category="misc"):
    return {
        "type": "minecraft:crafting_shapeless",
        "category": category,
        "ingredients": ingredients,
        "result": {"count": count, "id": f"{MOD}:{result_id}"},
    }


RECIPES = {
    "throwing_fizz_can": shapeless(
        ["copper_inferno:fizz_bomb", "minecraft:copper_ingot"],
        "throwing_fizz_can", count=2),
    "copper_whistle": shaped(
        {"I": "minecraft:copper_ingot", "N": "minecraft:iron_nugget"},
        ["II", " N"], "copper_whistle"),
    "copper_magnet": shaped(
        {"I": "minecraft:copper_ingot", "R": "minecraft:redstone"},
        ["I I", "I I", "RIR"], "copper_magnet"),
    "copper_buckler": shaped(
        {"C": "minecraft:copper_ingot", "o": "minecraft:iron_ingot"},
        ["CoC", "CCC", " C "], "copper_buckler"),
    "copper_monocle": shaped(
        {"S": "minecraft:string", "I": "minecraft:copper_ingot", "G": "minecraft:glass"},
        [" S ", "IGI"], "copper_monocle"),
    "copper_key": shaped(
        {"N": "minecraft:iron_nugget", "I": "minecraft:copper_ingot"},
        ["N", "I", "I"], "copper_key"),
    "copper_badge": shaped(
        {"I": "minecraft:copper_ingot", "X": "minecraft:raw_copper"},
        [" I ", "IXI", " I "], "copper_badge", category="misc"),
    "inferno_badge": shaped(
        {"I": "minecraft:copper_ingot", "X": "minecraft:blaze_powder"},
        [" I ", "IXI", " I "], "inferno_badge", category="misc"),
    "soda_badge": shaped(
        {"I": "minecraft:copper_ingot", "X": "minecraft:sugar"},
        [" I ", "IXI", " I "], "soda_badge", category="misc"),
}


def write_json(path, obj):
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2) + "\n", encoding="utf-8")


def main():
    texs = textures()
    assert sorted(texs) == sorted(ITEM_IDS)

    for item_id in ITEM_IDS:
        # 1.21.9 item-definition file (required in addition to the model).
        write_json(ASSETS / "items" / f"{item_id}.json", {
            "model": {
                "type": "minecraft:model",
                "model": f"{MOD}:item/{item_id}",
            },
        })
        write_json(ASSETS / "models" / "item" / f"{item_id}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"{MOD}:item/{item_id}"},
        })
        tex_path = ASSETS / "textures" / "item" / f"{item_id}.png"
        tex_path.parent.mkdir(parents=True, exist_ok=True)
        texs[item_id].save(tex_path)

    for recipe_id, recipe in RECIPES.items():
        write_json(DATA / "recipe" / "gear" / f"{recipe_id}.json", recipe)

    write_json(ASSETS / "lang" / "fragments" / "gear.json", LANG)

    print(f"gear_gen: wrote {len(ITEM_IDS)} item defs + models + textures, "
          f"{len(RECIPES)} recipes, 1 lang fragment")


if __name__ == "__main__":
    main()
