#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4 "Systems" feature (WP15).

Emits, directly into src/main/resources (plus the tag fragment under devtools/tagfrag):
  - blockstates, block models, items/<id>.json model-definitions and loot tables for the
    6 systems blocks (magnet_bench, waxing_station, copper_bounty_board, tempering_forge,
    soda_fountain, emote_copper_statue)
  - the 4 emote_copper_statue POSE models (element-based little copper figure; the
    blockstate maps emote=salute|wave|cheer|facepalm onto them)
  - the reinforced_copper_magnet item (item def + generated model + 16x16 sprite)
  - 16x16 deterministic textures (lib_gen seeded primitives + hard-coded motif overlays)
  - recipes (data/copper_inferno/recipe/systems/*.json), each with a feature-unique
    signature ingredient (copper_magnet / copper_block+honeycomb / copper_coin /
    ember_dust / soda_syrup / copper_player_statue) so no input set collides
  - lang fragments: assets/copper_inferno/lang/fragments/systems.json (EN) and
    fragments_de/systems.json (real German), including the system messages
  - tag fragment: devtools/tagfrag/systems.json (mineable tags)

NOTE: soda_syrup_block is NOT emitted here — it already ships with the sodablocks
feature (WP10) on this branch; the soda_fountain simply checks for it below itself.

All JSON structures come from devtools/gen/lib_gen.py (exact vanilla 1.21.9 formats).
Idempotent: all pixels are seeded/deterministic, so re-running writes identical bytes.
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import lib_gen  # noqa: E402
from lib_gen import (  # noqa: E402
    NS, item_def, loot_drop_self, merge, noise_cube, plank_grain, seeded, write_json,
)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
TAGFRAG = ROOT / "devtools" / "tagfrag" / "systems.json"

# ---------------------------------------------------------------------------
# Palettes (copper family matches the gear/masonry textures)
# ---------------------------------------------------------------------------
COPPER_LIGHT = (240, 144, 107)
COPPER_BASE = (224, 115, 77)
COPPER_MID = (193, 90, 59)
COPPER_DARK = (122, 58, 40)
COPPER_DEEP = (90, 42, 28)
COPPER_SHADES = [COPPER_DARK, COPPER_MID, COPPER_BASE, COPPER_BASE, COPPER_LIGHT]

IRON = (216, 216, 216)
WHITE = (242, 239, 234)
HONEY = (250, 188, 62)
HONEY_DARK = (204, 138, 30)
PAPER = (236, 230, 214)
PAPER_LINE = (120, 110, 92)
WOOD = (162, 116, 70)
WOOD_DARK = (110, 78, 46)
EMBER = (226, 88, 34)
FLAME = (250, 200, 80)
EMBER_DARK = (74, 22, 12)
MAROON = (90, 14, 20)
MAROON_LIGHT = (122, 27, 34)
GLASS = (176, 216, 230)


def _paint_over(img, rows, palette):
    """Paints a 16x16 char map over an existing image ('.' = keep base pixel)."""
    px = img.load()
    for y, row in enumerate(rows):
        assert len(row) == 16, f"row {y} is {len(row)} wide"
        for x, ch in enumerate(row):
            if ch != ".":
                px[x, y] = palette[ch]
    assert len(rows) == 16
    return img


# Horseshoe magnet motif (magnet_bench face).
MAGNET_MOTIF = [
    "................",
    "................",
    "....DDDDDDDD....",
    "...DCCCCCCCCD...",
    "...DCCDDDDCCD...",
    "...DCCD..DCCD...",
    "...DCCD..DCCD...",
    "...DCCD..DCCD...",
    "...DSSD..DSSD...",
    "...DWWD..DWWD...",
    "...DDDD..DDDD...",
    "................",
    "................",
    "................",
    "................",
    "................",
]

# Honey drips (waxing_station face).
HONEY_MOTIF = [
    "................",
    ".HHHHHHHHHHHHHH.",
    ".HHHHHHHHHHHHHH.",
    "..hHH..hHH..hH..",
    "..hH....hH..hH..",
    "..hH....hH......",
    "...H............",
    "................",
    "................",
    "................",
    "................",
    "................",
    "................",
    "................",
    "................",
    "................",
]

# Pinned bounty notes (copper_bounty_board face).
NOTES_MOTIF = [
    "................",
    "................",
    "..PPPP...PPPP...",
    "..PlPP...PPlP...",
    "..PPlP...PlPP...",
    "..PPPP...PPPP...",
    "..PlPP...PlPP...",
    "..PPPP...PPPP...",
    "................",
    "....PPPP........",
    "....PlPP........",
    "....PPlP........",
    "....PPPP........",
    "................",
    "................",
    "................",
]

# Glowing fire mouth (tempering_forge face).
FORGE_MOTIF = [
    "................",
    "................",
    "................",
    "................",
    "....KKKKKKKK....",
    "...KEEFEEFEEK...",
    "...KEFFEFFEEK...",
    "...KFEEFEEFFK...",
    "...KEEFEEFEEK...",
    "....KKKKKKKK....",
    "................",
    "................",
    "................",
    "................",
    "................",
    "................",
]

# Soda tap + fizzing glass (soda_fountain face).
FOUNTAIN_MOTIF = [
    "................",
    "....DDDDDDD.....",
    "....DMMMMMD.....",
    "....DMmMMMD.....",
    "....DDDDMDD.....",
    ".......DMD......",
    ".......GmG......",
    "......GmmmG.....",
    "......GmMmG.....",
    "......GMMMG.....",
    "......GGGGG.....",
    "................",
    "................",
    "................",
    "................",
    "................",
]

# Reinforced copper magnet item sprite (gear magnet silhouette + ember pole tips
# + iron reinforcement band across the arch).
REINFORCED_MAGNET_ROWS = [
    "................",
    "................",
    "....OOOOOOOO....",
    "...OCCCCCCCCO...",
    "..OCCLLCCLLCCO..",
    "..OSSOOOOOOSSO..",
    "..OCCO....OCCO..",
    "..OCCO....OCCO..",
    "..OCCO....OCCO..",
    "..OCCO....OCCO..",
    "..OEEO....OEEO..",
    "..OFFO....OFFO..",
    "..OOOO....OOOO..",
    "................",
    "................",
    "................",
]
REINFORCED_MAGNET_PALETTE = {
    "O": COPPER_DEEP, "C": COPPER_BASE, "L": COPPER_LIGHT, "S": IRON,
    "E": EMBER, "F": FLAME,
}


def paint_sprite(rows, palette):
    """16x16 RGBA char-map sprite ('.' = transparent)."""
    from PIL import Image
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y, row in enumerate(rows):
        assert len(row) == 16
        for x, ch in enumerate(row):
            if ch != ".":
                px[x, y] = palette[ch] + (255,)
    assert len(rows) == 16
    return img


def textures():
    out = {}
    out["block/magnet_bench"] = _paint_over(
        noise_cube(seeded("magnet_bench"), COPPER_SHADES), MAGNET_MOTIF,
        {"D": COPPER_DEEP, "C": COPPER_BASE, "S": IRON, "W": WHITE})
    out["block/waxing_station"] = _paint_over(
        noise_cube(seeded("waxing_station"), COPPER_SHADES), HONEY_MOTIF,
        {"H": HONEY, "h": HONEY_DARK})
    out["block/copper_bounty_board"] = _paint_over(
        plank_grain(seeded("copper_bounty_board"), WOOD, (182, 134, 84), WOOD_DARK),
        NOTES_MOTIF, {"P": PAPER, "l": PAPER_LINE})
    out["block/tempering_forge"] = _paint_over(
        noise_cube(seeded("tempering_forge"), [COPPER_DEEP, COPPER_DARK, COPPER_DARK, COPPER_MID]),
        FORGE_MOTIF, {"K": EMBER_DARK, "E": EMBER, "F": FLAME})
    out["block/soda_fountain"] = _paint_over(
        noise_cube(seeded("soda_fountain"), COPPER_SHADES), FOUNTAIN_MOTIF,
        {"D": COPPER_DEEP, "M": MAROON, "m": MAROON_LIGHT, "G": GLASS})
    out["block/emote_copper_statue"] = noise_cube(seeded("emote_copper_statue"), COPPER_SHADES)
    out["item/reinforced_copper_magnet"] = paint_sprite(REINFORCED_MAGNET_ROWS, REINFORCED_MAGNET_PALETTE)
    return out


# ---------------------------------------------------------------------------
# Emote statue models: pedestal + chunky copper figure, arms per pose.
# ---------------------------------------------------------------------------


def _element(from_xyz, to_xyz):
    faces = {face: {"texture": "#statue"} for face in
             ("down", "up", "north", "south", "west", "east")}
    return {"from": list(from_xyz), "to": list(to_xyz), "faces": faces}


# Shared body parts: pedestal, legs, torso, head.
_STATUE_BODY = [
    _element((3, 0, 3), (13, 2, 13)),
    _element((6, 2, 6), (10, 6, 10)),
    _element((5, 6, 6), (11, 11, 10)),
    _element((6, 11, 6), (10, 15, 10)),
]

_ARM_LEFT_DOWN = _element((3, 6, 7), (5, 11, 9))
_ARM_RIGHT_DOWN = _element((11, 6, 7), (13, 11, 9))
_ARM_LEFT_UP = _element((3, 11, 7), (5, 16, 9))
_ARM_RIGHT_UP = _element((11, 11, 7), (13, 16, 9))
_ARM_RIGHT_SALUTE = _element((10, 12, 4), (12, 14, 7))  # hand angled to the brow
_ARM_RIGHT_SALUTE_UPPER = _element((11, 8, 7), (13, 13, 9))
_ARM_FACEPALM_HAND = _element((6, 12, 4), (10, 14, 6))  # palm over the face
_ARM_FACEPALM_UPPER = _element((11, 9, 4), (13, 13, 7))

STATUE_POSES = {
    "salute": [_ARM_LEFT_DOWN, _ARM_RIGHT_SALUTE_UPPER, _ARM_RIGHT_SALUTE],
    "wave": [_ARM_LEFT_DOWN, _ARM_RIGHT_UP],
    "cheer": [_ARM_LEFT_UP, _ARM_RIGHT_UP],
    "facepalm": [_ARM_LEFT_DOWN, _ARM_FACEPALM_UPPER, _ARM_FACEPALM_HAND],
}


def statue_pose_model(pose: str) -> dict:
    tex = f"{NS}:block/emote_copper_statue"
    return {
        "parent": "minecraft:block/block",
        "textures": {"particle": tex, "statue": tex},
        "elements": _STATUE_BODY + STATUE_POSES[pose],
    }


def emit_statue() -> dict:
    name = "emote_copper_statue"
    files = {
        f"assets/{NS}/blockstates/{name}.json": {"variants": {
            f"emote={pose}": {"model": f"{NS}:block/{name}_{pose}"}
            for pose in STATUE_POSES
        }},
        f"assets/{NS}/items/{name}.json": item_def(f"{NS}:block/{name}_salute"),
    }
    for pose in STATUE_POSES:
        files[f"assets/{NS}/models/block/{name}_{pose}.json"] = statue_pose_model(pose)
    return merge(files, loot_drop_self(name))


def emit_simple_cube(name: str) -> dict:
    """Full cube over its own block texture (exact lib_gen.emit_cube output)."""
    return lib_gen.emit_cube(name)


def emit_reinforced_magnet() -> dict:
    name = "reinforced_copper_magnet"
    return {
        f"assets/{NS}/items/{name}.json": item_def(f"{NS}:item/{name}"),
        f"assets/{NS}/models/item/{name}.json": {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"{NS}:item/{name}"},
        },
    }


# ---------------------------------------------------------------------------
# Recipes (vanilla crafting_shaped format; every recipe carries a signature
# ingredient unique to this feature's recipe set, so canonical inputs are unique).
# ---------------------------------------------------------------------------


def shaped(key, pattern, result_id, count=1, category="misc"):
    return {
        "type": "minecraft:crafting_shaped",
        "category": category,
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": f"{NS}:{result_id}"},
    }


RECIPES = {
    "magnet_bench": shaped(
        {"M": f"{NS}:copper_magnet", "I": "minecraft:copper_ingot", "R": "minecraft:redstone_block"},
        [" M ", "III", "IRI"], "magnet_bench", category="building"),
    "waxing_station": shaped(
        {"H": "minecraft:honeycomb", "I": "minecraft:copper_ingot", "C": "minecraft:copper_block"},
        ["HHH", "ICI", "III"], "waxing_station", category="building"),
    "copper_bounty_board": shaped(
        {"P": "minecraft:paper", "K": f"{NS}:copper_coin", "I": "minecraft:copper_ingot"},
        ["PPP", "PKP", "III"], "copper_bounty_board", category="building"),
    "tempering_forge": shaped(
        {"D": f"{NS}:ember_dust", "F": "minecraft:blast_furnace", "I": "minecraft:copper_ingot"},
        ["DDD", "IFI", "III"], "tempering_forge", category="building"),
    "soda_fountain": shaped(
        {"G": "minecraft:glass", "S": f"{NS}:soda_syrup", "I": "minecraft:copper_ingot"},
        ["G G", "ISI", "III"], "soda_fountain", category="building"),
    "emote_copper_statue": shaped(
        {"I": "minecraft:copper_ingot", "S": f"{NS}:copper_player_statue"},
        [" I ", "ISI", " I "], "emote_copper_statue", category="building"),
}


# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

LANG_EN = {
    "block.copper_inferno.magnet_bench": "Magnet Bench",
    "block.copper_inferno.waxing_station": "Waxing Station",
    "block.copper_inferno.copper_bounty_board": "Copper Bounty Board",
    "block.copper_inferno.tempering_forge": "Tempering Forge",
    "block.copper_inferno.soda_fountain": "Soda Fountain",
    "block.copper_inferno.emote_copper_statue": "Emote Copper Statue",
    "item.copper_inferno.reinforced_copper_magnet": "Reinforced Copper Magnet",
    "message.copper_inferno.magnet_bench.upgraded": "The bench hums: your magnet is reinforced!",
    "message.copper_inferno.magnet_bench.need_ingots": "The upgrade trade costs %s copper ingots.",
    "message.copper_inferno.magnet_bench.already_upgraded": "This magnet is already reinforced.",
    "message.copper_inferno.waxing_station.waxed": "Waxed %s copper item(s).",
    "message.copper_inferno.waxing_station.nothing": "No unwaxed copper items to wax.",
    "message.copper_inferno.bounty_board.progress": "Bounty: %s - %s/%s slain.",
    "message.copper_inferno.bounty_board.cycled": "New bounty posted: %s x%s (reward: %s coins).",
    "message.copper_inferno.bounty_board.complete": "Bounty complete! %s copper coins paid out.",
    "message.copper_inferno.tempering_forge.repaired": "The forge tempers your gear: +%s durability.",
    "message.copper_inferno.tempering_forge.no_ember": "You need Ember Dust to fuel the forge.",
    "message.copper_inferno.tempering_forge.undamaged": "That item needs no tempering.",
    "message.copper_inferno.soda_fountain.no_syrup": "The fountain must sit on a Soda Syrup Block.",
    "message.copper_inferno.emote_statue.pose": "The statue strikes a pose: %s",
    "emote.copper_inferno.salute": "Salute",
    "emote.copper_inferno.wave": "Wave",
    "emote.copper_inferno.cheer": "Cheer",
    "emote.copper_inferno.facepalm": "Facepalm",
}

LANG_DE = {
    "block.copper_inferno.magnet_bench": "Magnetwerkbank",
    "block.copper_inferno.waxing_station": "Wachsstation",
    "block.copper_inferno.copper_bounty_board": "Kupfer-Kopfgeldtafel",
    "block.copper_inferno.tempering_forge": "H\u00e4rteschmiede",
    "block.copper_inferno.soda_fountain": "Brausebrunnen",
    "block.copper_inferno.emote_copper_statue": "Kupfer-Emote-Statue",
    "item.copper_inferno.reinforced_copper_magnet": "Verst\u00e4rkter Kupfermagnet",
    "message.copper_inferno.magnet_bench.upgraded": "Die Werkbank summt: dein Magnet ist verst\u00e4rkt!",
    "message.copper_inferno.magnet_bench.need_ingots": "Der Ausbau kostet %s Kupferbarren.",
    "message.copper_inferno.magnet_bench.already_upgraded": "Dieser Magnet ist bereits verst\u00e4rkt.",
    "message.copper_inferno.waxing_station.waxed": "%s Kupfergegenst\u00e4nde gewachst.",
    "message.copper_inferno.waxing_station.nothing": "Keine ungewachsten Kupfergegenst\u00e4nde dabei.",
    "message.copper_inferno.bounty_board.progress": "Kopfgeld: %s - %s/%s erlegt.",
    "message.copper_inferno.bounty_board.cycled": "Neues Kopfgeld: %s x%s (Belohnung: %s M\u00fcnzen).",
    "message.copper_inferno.bounty_board.complete": "Kopfgeld erf\u00fcllt! %s Kupferm\u00fcnzen ausgezahlt.",
    "message.copper_inferno.tempering_forge.repaired": "Die Schmiede h\u00e4rtet deine Ausr\u00fcstung: +%s Haltbarkeit.",
    "message.copper_inferno.tempering_forge.no_ember": "Du brauchst Glutstaub als Brennstoff f\u00fcr die Schmiede.",
    "message.copper_inferno.tempering_forge.undamaged": "Dieser Gegenstand braucht keine H\u00e4rtung.",
    "message.copper_inferno.soda_fountain.no_syrup": "Der Brunnen muss auf einem Sodasirupblock stehen.",
    "message.copper_inferno.emote_statue.pose": "Die Statue posiert: %s",
    "emote.copper_inferno.salute": "Salutieren",
    "emote.copper_inferno.wave": "Winken",
    "emote.copper_inferno.cheer": "Jubeln",
    "emote.copper_inferno.facepalm": "Facepalm",
}

# ---------------------------------------------------------------------------
# Tag fragment (merged into data/minecraft/tags by devtools/merge_tags.py)
# ---------------------------------------------------------------------------

TAGS = {
    "block/mineable/pickaxe": [
        f"{NS}:emote_copper_statue",
        f"{NS}:magnet_bench",
        f"{NS}:soda_fountain",
        f"{NS}:tempering_forge",
        f"{NS}:waxing_station",
    ],
    "block/mineable/axe": [
        f"{NS}:copper_bounty_board",
    ],
}


def main():
    files = merge(
        emit_simple_cube("magnet_bench"),
        emit_simple_cube("waxing_station"),
        emit_simple_cube("copper_bounty_board"),
        emit_simple_cube("tempering_forge"),
        emit_simple_cube("soda_fountain"),
        emit_statue(),
        emit_reinforced_magnet(),
    )
    for rel, obj in files.items():
        write_json(RES / rel, obj)

    texs = textures()
    for rel, img in texs.items():
        path = ASSETS / "textures" / f"{rel}.png"
        path.parent.mkdir(parents=True, exist_ok=True)
        import io
        buf = io.BytesIO()
        img.save(buf, format="PNG")
        data = buf.getvalue()
        if not path.is_file() or path.read_bytes() != data:
            path.write_bytes(data)

    for recipe_id, recipe in RECIPES.items():
        write_json(DATA / "recipe" / "systems" / f"{recipe_id}.json", recipe)

    write_json(ASSETS / "lang" / "fragments" / "systems.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "systems.json", LANG_DE)
    write_json(TAGFRAG, TAGS)

    print(f"systems_gen: wrote {len(files)} JSON files, {len(texs)} textures, "
          f"{len(RECIPES)} recipes, 2 lang fragments, 1 tag fragment")


if __name__ == "__main__":
    main()
