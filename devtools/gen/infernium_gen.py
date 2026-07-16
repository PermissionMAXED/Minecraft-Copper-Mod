#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "infernium" feature (dimension material economy
plus a full new tool & armor tier: 21 items + 2 storage blocks).

Idempotent: running it any number of times produces the same files. By default this script
writes ONLY PNGs (item/block textures + the worn-armor equipment layer textures); pass
--write-json to also (re)emit the JSON scaffolding:
  - item model-definitions + item models (assets/copper_inferno/items + models/item)
  - blockstates, block models, drop-self loot tables for the 2 storage blocks
  - the equipment asset JSON (assets/copper_inferno/equipment/infernium.json) whose schema
    is an exact copy of the vanilla 1.21.9 assets/minecraft/equipment/iron.json (extracted
    from the client jar), minus the horse_body layer we don't use
  - recipes (data/copper_inferno/recipe/infernium/*.json) mirroring vanilla 1.21.9 formats
    (iron tool/armor shapes, raw-ore smelting/blasting, storage-block cycles) and the
    shipped copper_upgrade_smithing_template craft/duplication/smithing_transform JSONs
  - the repair item tag (data/copper_inferno/tags/item/infernium_repair.json)
  - lang fragments EN + DE (assets/copper_inferno/lang/fragments{,_de}/infernium.json)
  - the vanilla-tag fragment (devtools/tagfrag/infernium.json) for merge_tags.py

Worn-armor layer textures (textures/entity/equipment/humanoid{,_leggings}/infernium.png)
are produced by recoloring the vanilla iron layer PNGs (same 64x32 dimensions) from the
client jar into the ember-orange/charcoal infernium palette — deterministic, always written.
"""

import json
import sys
import zipfile
from pathlib import Path
from random import Random

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> PNGs only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
TAGFRAG = ROOT / "devtools" / "tagfrag"
CLIENT_JAR = Path.home() / ".gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"

# Infernium palette (ember-orange / charcoal, matching the mod's inferno look).
K = (0x1C, 0x12, 0x16)        # outline / near-black
D = (0x2B, 0x22, 0x26)        # very dark charcoal
C = (0x3D, 0x2C, 0x2E)        # charcoal
c = (0x54, 0x3E, 0x42)        # charcoal light
E = (0xE2, 0x58, 0x22)        # ember
e = (0xFF, 0x7A, 0x2F)        # ember bright
H = (0xFF, 0xB1, 0x6B)        # ember hot
W = (0xFF, 0xE0, 0xC0)        # near-white hot
G = (0x9A, 0x8F, 0x8A)        # ash light
g = (0x6E, 0x65, 0x60)        # ash dark
L = (0xC9, 0xE4, 0xEE)        # glass light
l = (0x8F, 0xB8, 0xCC)        # glass dark

ITEMS = [
    "raw_infernium", "infernium_ingot", "infernium_nugget", "smolder_crystal",
    "ember_dust", "slag_chunk", "ash_pile", "cinder_rod",
    "infernium_plate", "infernium_gear", "smolder_lens",
    "infernium_upgrade_smithing_template",
    "infernium_sword", "infernium_pickaxe", "infernium_axe", "infernium_shovel",
    "infernium_hoe",
    "infernium_helmet", "infernium_chestplate", "infernium_leggings", "infernium_boots",
]
HANDHELD = {"infernium_sword", "infernium_pickaxe", "infernium_axe", "infernium_shovel",
            "infernium_hoe"}
BLOCKS = ["infernium_block", "smolder_crystal_block"]

LANG_EN = {
    "item.copper_inferno.raw_infernium": "Raw Infernium",
    "item.copper_inferno.infernium_ingot": "Infernium Ingot",
    "item.copper_inferno.infernium_nugget": "Infernium Nugget",
    "item.copper_inferno.smolder_crystal": "Smolder Crystal",
    "item.copper_inferno.ember_dust": "Ember Dust",
    "item.copper_inferno.slag_chunk": "Slag Chunk",
    "item.copper_inferno.ash_pile": "Ash Pile",
    "item.copper_inferno.cinder_rod": "Cinder Rod",
    "item.copper_inferno.infernium_plate": "Infernium Plate",
    "item.copper_inferno.infernium_gear": "Infernium Gear",
    "item.copper_inferno.smolder_lens": "Smolder Lens",
    "item.copper_inferno.infernium_upgrade_smithing_template": "Infernium Upgrade Smithing Template",
    "item.copper_inferno.infernium_sword": "Infernium Sword",
    "item.copper_inferno.infernium_pickaxe": "Infernium Pickaxe",
    "item.copper_inferno.infernium_axe": "Infernium Axe",
    "item.copper_inferno.infernium_shovel": "Infernium Shovel",
    "item.copper_inferno.infernium_hoe": "Infernium Hoe",
    "item.copper_inferno.infernium_helmet": "Infernium Helmet",
    "item.copper_inferno.infernium_chestplate": "Infernium Chestplate",
    "item.copper_inferno.infernium_leggings": "Infernium Leggings",
    "item.copper_inferno.infernium_boots": "Infernium Boots",
    "block.copper_inferno.infernium_block": "Block of Infernium",
    "block.copper_inferno.smolder_crystal_block": "Smolder Crystal Block",
    # Tag translation (tag.item.<ns>.<path>, fabric-tag-conventions-v2 format) for the
    # repair tag emitted by emit_repair_tag().
    "tag.item.copper_inferno.infernium_repair": "Infernium Repair Items",
}
LANG_DE = {
    "item.copper_inferno.raw_infernium": "Rohinfernium",
    "item.copper_inferno.infernium_ingot": "Infernium-Barren",
    "item.copper_inferno.infernium_nugget": "Infernium-Klumpen",
    "item.copper_inferno.smolder_crystal": "Schwelkristall",
    "item.copper_inferno.ember_dust": "Glutstaub",
    "item.copper_inferno.slag_chunk": "Schlackenbrocken",
    "item.copper_inferno.ash_pile": "Aschehaufen",
    "item.copper_inferno.cinder_rod": "Zunderstab",
    "item.copper_inferno.infernium_plate": "Infernium-Platte",
    "item.copper_inferno.infernium_gear": "Infernium-Zahnrad",
    "item.copper_inferno.smolder_lens": "Schwellinse",
    "item.copper_inferno.infernium_upgrade_smithing_template": "Infernium-Aufwertungs-Schmiedevorlage",
    "item.copper_inferno.infernium_sword": "Infernium-Schwert",
    "item.copper_inferno.infernium_pickaxe": "Infernium-Spitzhacke",
    "item.copper_inferno.infernium_axe": "Infernium-Axt",
    "item.copper_inferno.infernium_shovel": "Infernium-Schaufel",
    "item.copper_inferno.infernium_hoe": "Infernium-Hacke",
    "item.copper_inferno.infernium_helmet": "Infernium-Helm",
    "item.copper_inferno.infernium_chestplate": "Infernium-Harnisch",
    "item.copper_inferno.infernium_leggings": "Infernium-Beinschutz",
    "item.copper_inferno.infernium_boots": "Infernium-Stiefel",
    "block.copper_inferno.infernium_block": "Infernium-Block",
    "block.copper_inferno.smolder_crystal_block": "Schwelkristallblock",
    "tag.item.copper_inferno.infernium_repair": "Infernium-Reparaturgegenst\u00e4nde",
}

TAGFRAG_CONTENT = {
    "item/swords": [f"{NS}:infernium_sword"],
    "item/pickaxes": [f"{NS}:infernium_pickaxe"],
    "item/axes": [f"{NS}:infernium_axe"],
    "item/shovels": [f"{NS}:infernium_shovel"],
    "item/hoes": [f"{NS}:infernium_hoe"],
    "item/head_armor": [f"{NS}:infernium_helmet"],
    "item/chest_armor": [f"{NS}:infernium_chestplate"],
    "item/leg_armor": [f"{NS}:infernium_leggings"],
    "item/foot_armor": [f"{NS}:infernium_boots"],
    "block/mineable/pickaxe": [f"{NS}:infernium_block", f"{NS}:smolder_crystal_block"],
}


def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True) + "\n", encoding="utf-8")


# ---------------------------------------------------------------------------
# JSON emitters
# ---------------------------------------------------------------------------

def emit_item_assets() -> None:
    for name in ITEMS:
        parent = "minecraft:item/handheld" if name in HANDHELD else "minecraft:item/generated"
        write_json(ASSETS / "models" / "item" / f"{name}.json",
                   {"parent": parent, "textures": {"layer0": f"{NS}:item/{name}"}})
        write_json(ASSETS / "items" / f"{name}.json",
                   {"model": {"type": "minecraft:model", "model": f"{NS}:item/{name}"}})


def emit_block_assets() -> None:
    for name in BLOCKS:
        write_json(ASSETS / "blockstates" / f"{name}.json",
                   {"variants": {"": {"model": f"{NS}:block/{name}"}}})
        write_json(ASSETS / "models" / "block" / f"{name}.json",
                   {"parent": "minecraft:block/cube_all", "textures": {"all": f"{NS}:block/{name}"}})
        write_json(ASSETS / "items" / f"{name}.json",
                   {"model": {"type": "minecraft:model", "model": f"{NS}:block/{name}"}})
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


def emit_equipment_asset() -> None:
    # Schema copied from vanilla assets/minecraft/equipment/iron.json (client jar),
    # without the horse_body layer (no infernium horse armor).
    write_json(ASSETS / "equipment" / "infernium.json", {
        "layers": {
            "humanoid": [{"texture": f"{NS}:infernium"}],
            "humanoid_leggings": [{"texture": f"{NS}:infernium"}],
        }
    })


def rp(name: str) -> Path:
    return DATA / "recipe" / "infernium" / f"{name}.json"


def emit_recipes() -> None:
    ing = f"{NS}:infernium_ingot"
    nug = f"{NS}:infernium_nugget"
    raw = f"{NS}:raw_infernium"
    cry = f"{NS}:smolder_crystal"
    tpl = f"{NS}:infernium_upgrade_smithing_template"
    rod = f"{NS}:cinder_rod"

    # --- smelting / blasting (vanilla iron_ingot_from_*_raw_iron format)
    write_json(rp("infernium_ingot_from_smelting_raw_infernium"), {
        "type": "minecraft:smelting", "category": "misc", "cookingtime": 200,
        "experience": 1.0, "group": "infernium_ingot", "ingredient": raw,
        "result": {"id": ing},
    })
    write_json(rp("infernium_ingot_from_blasting_raw_infernium"), {
        "type": "minecraft:blasting", "category": "misc", "cookingtime": 100,
        "experience": 1.0, "group": "infernium_ingot", "ingredient": raw,
        "result": {"id": ing},
    })
    # Infernium Ore (registered by feature/infernodim) also smelts straight to ingots,
    # mirroring vanilla iron_ingot_from_smelting_iron_ore.
    write_json(rp("infernium_ingot_from_smelting_infernium_ore"), {
        "type": "minecraft:smelting", "category": "misc", "cookingtime": 200,
        "experience": 1.0, "group": "infernium_ingot", "ingredient": f"{NS}:infernium_ore",
        "result": {"id": ing},
    })
    write_json(rp("infernium_ingot_from_blasting_infernium_ore"), {
        "type": "minecraft:blasting", "category": "misc", "cookingtime": 100,
        "experience": 1.0, "group": "infernium_ingot", "ingredient": f"{NS}:infernium_ore",
        "result": {"id": ing},
    })

    # --- nugget <-> ingot <-> block storage cycles (vanilla iron formats)
    write_json(rp("infernium_ingot_from_nuggets"), {
        "type": "minecraft:crafting_shaped", "category": "misc", "group": "infernium_ingot",
        "key": {"#": nug}, "pattern": ["###", "###", "###"],
        "result": {"count": 1, "id": ing},
    })
    write_json(rp("infernium_nugget"), {
        "type": "minecraft:crafting_shapeless", "category": "misc",
        "ingredients": [ing], "result": {"count": 9, "id": nug},
    })
    write_json(rp("infernium_block"), {
        "type": "minecraft:crafting_shaped", "category": "building",
        "key": {"#": ing}, "pattern": ["###", "###", "###"],
        "result": {"count": 1, "id": f"{NS}:infernium_block"},
    })
    write_json(rp("infernium_ingot_from_infernium_block"), {
        "type": "minecraft:crafting_shapeless", "category": "misc", "group": "infernium_ingot",
        "ingredients": [f"{NS}:infernium_block"], "result": {"count": 9, "id": ing},
    })
    write_json(rp("smolder_crystal_block"), {
        "type": "minecraft:crafting_shaped", "category": "building",
        "key": {"#": cry}, "pattern": ["###", "###", "###"],
        "result": {"count": 1, "id": f"{NS}:smolder_crystal_block"},
    })
    write_json(rp("smolder_crystal_from_smolder_crystal_block"), {
        "type": "minecraft:crafting_shapeless", "category": "misc",
        "ingredients": [f"{NS}:smolder_crystal_block"], "result": {"count": 9, "id": cry},
    })

    # --- ember/ash/slag/rod chain
    write_json(rp("ember_dust"), {
        "type": "minecraft:crafting_shapeless", "category": "misc",
        "ingredients": [cry], "result": {"count": 4, "id": f"{NS}:ember_dust"},
    })
    write_json(rp("ash_pile"), {
        "type": "minecraft:smelting", "category": "misc", "cookingtime": 100,
        "experience": 0.1, "ingredient": f"{NS}:ember_dust",
        "result": {"id": f"{NS}:ash_pile"},
    })
    write_json(rp("slag_chunk"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"#": f"{NS}:ash_pile"}, "pattern": ["##", "##"],
        "result": {"count": 1, "id": f"{NS}:slag_chunk"},
    })
    write_json(rp("cinder_rod"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"#": f"{NS}:slag_chunk"}, "pattern": ["#", "#"],
        "result": {"count": 1, "id": rod},
    })

    # --- machining parts
    write_json(rp("infernium_plate"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"#": ing}, "pattern": ["##"],
        "result": {"count": 1, "id": f"{NS}:infernium_plate"},
    })
    write_json(rp("infernium_gear"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"#": nug, "G": ing}, "pattern": [" # ", "#G#", " # "],
        "result": {"count": 1, "id": f"{NS}:infernium_gear"},
    })
    write_json(rp("smolder_lens"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"#": "minecraft:glass", "C": cry}, "pattern": [" # ", "#C#", " # "],
        "result": {"count": 1, "id": f"{NS}:smolder_lens"},
    })

    # --- tools (vanilla iron tool shapes: ingots + cinder_rod as the stick)
    tool_patterns = {
        "infernium_sword": ["X", "X", "#"],
        "infernium_pickaxe": ["XXX", " # ", " # "],
        "infernium_axe": ["XX", "X#", " #"],
        "infernium_shovel": ["X", "#", "#"],
        "infernium_hoe": ["XX", " #", " #"],
    }
    for name, pattern in tool_patterns.items():
        write_json(rp(name), {
            "type": "minecraft:crafting_shaped", "category": "equipment",
            "key": {"#": rod, "X": ing}, "pattern": pattern,
            "result": {"count": 1, "id": f"{NS}:{name}"},
        })

    # --- armor (vanilla iron armor shapes)
    armor_patterns = {
        "infernium_helmet": ["XXX", "X X"],
        "infernium_chestplate": ["X X", "XXX", "XXX"],
        "infernium_leggings": ["XXX", "X X", "X X"],
        "infernium_boots": ["X X", "X X"],
    }
    for name, pattern in armor_patterns.items():
        write_json(rp(name), {
            "type": "minecraft:crafting_shaped", "category": "equipment",
            "key": {"X": ing}, "pattern": pattern,
            "result": {"count": 1, "id": f"{NS}:{name}"},
        })

    # --- smithing template craft + duplication (mirrors the shipped copper template JSONs)
    write_json(rp("infernium_upgrade_smithing_template"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"#": ing, "D": cry, "B": "minecraft:blackstone"},
        "pattern": ["#D#", "#B#", "###"],
        "result": {"count": 1, "id": tpl},
    })
    write_json(rp("infernium_upgrade_smithing_template_duplication"), {
        "type": "minecraft:crafting_shaped", "category": "misc",
        "key": {"#": ing, "C": "minecraft:blackstone", "S": tpl},
        "pattern": ["#S#", "#C#", "###"],
        "result": {"count": 2, "id": tpl},
    })

    # --- smithing upgrades: vanilla copper gear -> infernium (shipped *_smithing format)
    for piece in ("sword", "pickaxe", "axe", "shovel", "hoe",
                  "helmet", "chestplate", "leggings", "boots"):
        write_json(rp(f"infernium_{piece}_smithing"), {
            "type": "minecraft:smithing_transform",
            "addition": ing,
            "base": f"minecraft:copper_{piece}",
            "result": {"id": f"{NS}:infernium_{piece}"},
            "template": tpl,
        })


def emit_repair_tag() -> None:
    write_json(DATA / "tags" / "item" / "infernium_repair.json",
               {"values": [f"{NS}:infernium_ingot"]})


def emit_lang() -> None:
    write_json(ASSETS / "lang" / "fragments" / "infernium.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "infernium.json", LANG_DE)


def emit_tagfrag() -> None:
    write_json(TAGFRAG / "infernium.json", TAGFRAG_CONTENT)


# ---------------------------------------------------------------------------
# Item textures (16x16, deterministic pixel art)
# ---------------------------------------------------------------------------

def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def put(img, x, y, color):
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (*color, 255))


def fill_rect(img, x0, y0, x1, y1, color):
    for y in range(y0, y1 + 1):
        for x in range(x0, x1 + 1):
            put(img, x, y, color)


def outline(img, color=K):
    """MC-style sprite outline drawn OUTSIDE the shape: transparent pixels bordering a
    colored pixel become `color` (keeps thin 2px-wide blades/rods readable)."""
    src = img.copy()
    for y in range(16):
        for x in range(16):
            if src.getpixel((x, y))[3] != 0:
                continue
            for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)):
                nx, ny = x + dx, y + dy
                if 0 <= nx < 16 and 0 <= ny < 16 and src.getpixel((nx, ny))[3] != 0:
                    put(img, x, y, color)
                    break
    return img


def diag_handle(img, x0, y0, steps):
    """2-wide charcoal cinder-rod handle running up-right from (x0, y0)."""
    for i in range(steps):
        x, y = x0 + i, y0 - i
        put(img, x, y, D)
        put(img, x + 1, y, C)
    # ember tip speck at the base
    put(img, x0, y0, E)


def tex_raw_infernium(rng: Random) -> Image.Image:
    img = blank()
    # irregular chunk silhouette
    rows = {3: (5, 10), 4: (4, 11), 5: (3, 12), 6: (3, 12), 7: (2, 12), 8: (3, 13),
            9: (3, 12), 10: (4, 12), 11: (4, 11), 12: (5, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            img_color = C if rng.random() < 0.75 else c
            put(img, x, y, img_color)
    # ember veins
    for x, y in [(6, 5), (7, 5), (8, 6), (5, 8), (6, 9), (9, 9), (10, 8), (10, 4), (4, 6), (8, 11)]:
        put(img, x, y, E)
    for x, y in [(7, 6), (6, 8), (9, 8)]:
        put(img, x, y, e)
    return outline(img)


def tex_infernium_ingot(rng: Random) -> Image.Image:
    img = blank()
    # 3D ingot: top face (parallelogram), front face, right side.
    for i in range(3):  # top face rows y=4..6, shifting left
        y = 4 + i
        for x in range(6 - i, 12 - i + 1):
            put(img, x, y, H if i == 0 else e)
    fill_rect(img, 3, 7, 10, 10, E)         # front face
    for i in range(3):                      # right side face, sloped
        y = 7 + i
        put(img, 11 + (2 - i) // 2, y, C)
    fill_rect(img, 11, 7, 11, 10, C)
    # glow highlights
    put(img, 4, 8, e)
    put(img, 5, 8, e)
    put(img, 7, 5, W)
    return outline(img)


def tex_infernium_nugget(rng: Random) -> Image.Image:
    img = blank()
    rows = {6: (6, 9), 7: (5, 10), 8: (5, 10), 9: (6, 10), 10: (7, 9)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            put(img, x, y, E)
    put(img, 7, 7, e)
    put(img, 8, 7, H)
    put(img, 6, 8, e)
    return outline(img)


def tex_smolder_crystal(rng: Random) -> Image.Image:
    img = blank()
    # tall rhombus shard
    for y in range(2, 14):
        half = (5 - abs(y - 8)) // 1
        half = max(0, 4 - abs(y - 8) * 2 // 3)
        for x in range(7 - half, 8 + half + 1):
            put(img, x, y, E)
    # facet: bright left edge, hot core
    for y in range(4, 12):
        put(img, 7 - max(0, 3 - abs(y - 8) * 2 // 3), y, e)
    for y in range(6, 10):
        put(img, 7, y, H)
    put(img, 7, 7, W)
    put(img, 8, 8, e)
    return outline(img)


def tex_ember_dust(rng: Random) -> Image.Image:
    img = blank()
    # glowing pile
    rows = {8: (6, 9), 9: (5, 10), 10: (4, 11), 11: (3, 12), 12: (3, 12)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            put(img, x, y, E if rng.random() < 0.7 else e)
    for x, y in [(7, 8), (6, 10), (9, 11), (5, 12)]:
        put(img, x, y, H)
    # sparks drifting above
    for x, y in [(5, 6), (9, 5), (7, 4), (11, 7)]:
        put(img, x, y, e)
    return outline(img)


def tex_slag_chunk(rng: Random) -> Image.Image:
    img = blank()
    rows = {4: (5, 10), 5: (4, 11), 6: (3, 12), 7: (3, 12), 8: (3, 12), 9: (4, 12),
            10: (4, 11), 11: (5, 10)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            put(img, x, y, D if rng.random() < 0.7 else C)
    # porous holes + ember cracks
    for x, y in [(6, 6), (9, 7), (5, 9), (8, 10)]:
        put(img, x, y, K)
    for x, y in [(7, 7), (8, 7), (6, 8), (10, 9), (5, 5)]:
        put(img, x, y, E)
    put(img, 7, 8, e)
    return outline(img)


def tex_ash_pile(rng: Random) -> Image.Image:
    img = blank()
    rows = {7: (7, 8), 8: (6, 9), 9: (5, 10), 10: (4, 11), 11: (3, 12), 12: (3, 13)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            put(img, x, y, G if rng.random() < 0.65 else g)
    # a couple of dying embers in the ash
    put(img, 7, 10, E)
    put(img, 10, 12, E)
    return outline(img)


def tex_cinder_rod(rng: Random) -> Image.Image:
    img = blank()
    # 2-wide diagonal rod, ember-hot at both ends
    for i in range(10):
        x, y = 3 + i, 12 - i
        put(img, x, y, C if i % 3 else D)
        put(img, x + 1, y, D)
    for x, y in [(3, 12), (4, 12), (3, 13)]:
        put(img, x, y, E)
    for x, y in [(12, 3), (13, 3), (13, 2)]:
        put(img, x, y, e)
    put(img, 13, 2, H)
    return outline(img)


def tex_infernium_plate(rng: Random) -> Image.Image:
    img = blank()
    fill_rect(img, 3, 5, 12, 11, E)
    fill_rect(img, 4, 6, 11, 7, e)  # sheen band
    # rivets
    for x, y in [(4, 6), (11, 6), (4, 10), (11, 10)]:
        put(img, x, y, C)
    put(img, 5, 6, H)
    return outline(img)


def tex_infernium_gear(rng: Random) -> Image.Image:
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d2 = (x - cx) ** 2 + (y - cy) ** 2
            if 8 <= d2 <= 22:
                put(img, x, y, E)
    # teeth (8 directions)
    for dx, dy in [(0, -6), (0, 6), (-6, 0), (6, 0), (-4, -4), (4, -4), (-4, 4), (4, 4)]:
        x, y = int(cx + dx * 0.99), int(cy + dy * 0.99)
        fill_rect(img, x, y, x + 1, y + 1, e)
    # hub hole
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), (0, 0, 0, 0))
    put(img, 6, 6, H)
    return outline(img)


def tex_smolder_lens(rng: Random) -> Image.Image:
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d2 = (x - cx) ** 2 + (y - cy) ** 2
            if d2 <= 30:
                if d2 >= 20:
                    put(img, x, y, l)       # glass rim
                elif d2 >= 9:
                    put(img, x, y, E)
                else:
                    put(img, x, y, e)
    put(img, 7, 7, H)
    put(img, 8, 8, H)
    put(img, 5, 5, L)  # glass glint
    put(img, 6, 4, L)
    return outline(img)


def tex_smithing_template(rng: Random) -> Image.Image:
    img = blank()
    # card shape like vanilla smithing templates: dark slate with a glowing glyph
    fill_rect(img, 3, 2, 12, 13, C)
    fill_rect(img, 4, 3, 11, 12, D)
    # corner accents
    for x, y in [(4, 3), (11, 3), (4, 12), (11, 12)]:
        put(img, x, y, c)
    # ingot glyph
    fill_rect(img, 6, 6, 9, 8, E)
    put(img, 6, 6, e)
    put(img, 7, 6, H)
    # crystal spark below
    put(img, 7, 10, e)
    put(img, 8, 10, E)
    return outline(img)


def tex_sword(rng: Random) -> Image.Image:
    img = blank()
    # blade: 2-wide diagonal from tip (13,1) down-left to (5,9)
    for i in range(9):
        x, y = 13 - i, 1 + i
        put(img, x, y, H if i < 2 else e)
        put(img, x - 1, y + 1, E)
        put(img, x, y + 1, e)
    # guard
    for x, y in [(3, 10), (4, 11), (4, 9), (5, 10), (2, 11), (5, 12)]:
        put(img, x, y, E)
    # handle
    put(img, 3, 12, D)
    put(img, 2, 13, D)
    put(img, 1, 14, C)
    return outline(img)


def tex_pickaxe(rng: Random) -> Image.Image:
    img = blank()
    diag_handle(img, 2, 13, 9)
    # curved head band across the top-right
    head = [(4, 3), (5, 2), (6, 1), (7, 1), (8, 1), (9, 1), (10, 2), (11, 3),
            (12, 4), (13, 5), (13, 6), (14, 7), (14, 8)]
    for x, y in head:
        put(img, x, y, E)
        put(img, x, y + 1, e)
    for x, y in [(7, 1), (8, 1), (7, 2), (8, 2)]:
        put(img, x, y, H)
    return outline(img)


def tex_axe(rng: Random) -> Image.Image:
    img = blank()
    diag_handle(img, 2, 13, 9)
    # solid wedge head across the top, cutting edge sweeping down-left
    fill_rect(img, 9, 2, 12, 2, E)
    fill_rect(img, 8, 3, 13, 3, E)
    fill_rect(img, 7, 4, 13, 4, E)
    fill_rect(img, 7, 5, 9, 5, E)
    fill_rect(img, 12, 5, 13, 5, E)
    fill_rect(img, 7, 6, 8, 6, E)
    put(img, 7, 7, E)
    for x, y in [(9, 2), (10, 2), (8, 3)]:
        put(img, x, y, H)
    for x, y in [(7, 5), (7, 6), (7, 7), (8, 6)]:
        put(img, x, y, e)  # bright cutting edge
    return outline(img)


def tex_shovel(rng: Random) -> Image.Image:
    img = blank()
    diag_handle(img, 2, 13, 8)
    # spade head: diamond-ish scoop pointing up-right
    fill_rect(img, 11, 1, 12, 1, E)
    fill_rect(img, 10, 2, 13, 2, E)
    fill_rect(img, 9, 3, 14, 3, E)
    fill_rect(img, 9, 4, 14, 4, E)
    fill_rect(img, 10, 5, 13, 5, E)
    fill_rect(img, 11, 6, 12, 6, E)
    for x, y in [(11, 1), (12, 1), (10, 2), (11, 2)]:
        put(img, x, y, H)
    for x, y in [(9, 4), (10, 5), (11, 6)]:
        put(img, x, y, e)
    return outline(img)


def tex_hoe(rng: Random) -> Image.Image:
    img = blank()
    diag_handle(img, 2, 13, 9)
    # blade: bent flat head at the top
    for x, y in [(8, 1), (9, 1), (10, 1), (11, 1), (12, 2), (13, 3),
                 (8, 2), (9, 2), (10, 2), (11, 2), (12, 3)]:
        put(img, x, y, E)
    for x, y in [(8, 1), (9, 1)]:
        put(img, x, y, H)
    put(img, 8, 2, e)
    return outline(img)


def tex_helmet(rng: Random) -> Image.Image:
    img = blank()
    # dome
    fill_rect(img, 4, 4, 11, 5, E)
    fill_rect(img, 3, 6, 12, 8, E)
    put(img, 5, 3, E)
    fill_rect(img, 6, 3, 9, 3, E)
    put(img, 10, 3, E)
    # cheek guards with eye gap
    fill_rect(img, 3, 9, 5, 11, E)
    fill_rect(img, 10, 9, 12, 11, E)
    fill_rect(img, 6, 9, 9, 9, e)  # brow edge
    # highlights
    fill_rect(img, 5, 4, 6, 5, H)
    put(img, 7, 3, H)
    put(img, 4, 7, e)
    return outline(img)


def tex_chestplate(rng: Random) -> Image.Image:
    img = blank()
    # shoulders
    fill_rect(img, 2, 3, 5, 5, E)
    fill_rect(img, 10, 3, 13, 5, E)
    # torso (neck gap on top)
    fill_rect(img, 3, 6, 12, 12, E)
    fill_rect(img, 6, 4, 9, 5, e)  # collar rim
    for x in range(6, 10):
        img.putpixel((x, 3), (0, 0, 0, 0))
    # abdomen shading + highlight
    fill_rect(img, 4, 7, 5, 9, H)
    fill_rect(img, 7, 8, 8, 12, e)
    put(img, 3, 3, H)
    return outline(img)


def tex_leggings(rng: Random) -> Image.Image:
    img = blank()
    # waistband
    fill_rect(img, 3, 3, 12, 5, E)
    fill_rect(img, 3, 3, 12, 3, e)
    # legs
    fill_rect(img, 3, 6, 6, 13, E)
    fill_rect(img, 9, 6, 12, 13, E)
    # highlights
    fill_rect(img, 4, 6, 4, 12, H)
    put(img, 10, 6, e)
    put(img, 10, 7, e)
    return outline(img)


def tex_boots(rng: Random) -> Image.Image:
    img = blank()
    for x0 in (2, 9):
        # shaft
        fill_rect(img, x0, 5, x0 + 3, 8, E)
        # foot extending right
        fill_rect(img, x0, 9, x0 + 4, 11, E)
        put(img, x0 + 1, 5, H)
        fill_rect(img, x0, 11, x0 + 4, 11, e)
    return outline(img)


ITEM_TEXTURES = {
    "raw_infernium": tex_raw_infernium,
    "infernium_ingot": tex_infernium_ingot,
    "infernium_nugget": tex_infernium_nugget,
    "smolder_crystal": tex_smolder_crystal,
    "ember_dust": tex_ember_dust,
    "slag_chunk": tex_slag_chunk,
    "ash_pile": tex_ash_pile,
    "cinder_rod": tex_cinder_rod,
    "infernium_plate": tex_infernium_plate,
    "infernium_gear": tex_infernium_gear,
    "smolder_lens": tex_smolder_lens,
    "infernium_upgrade_smithing_template": tex_smithing_template,
    "infernium_sword": tex_sword,
    "infernium_pickaxe": tex_pickaxe,
    "infernium_axe": tex_axe,
    "infernium_shovel": tex_shovel,
    "infernium_hoe": tex_hoe,
    "infernium_helmet": tex_helmet,
    "infernium_chestplate": tex_chestplate,
    "infernium_leggings": tex_leggings,
    "infernium_boots": tex_boots,
}


# ---------------------------------------------------------------------------
# Block textures (16x16 RGB)
# ---------------------------------------------------------------------------

def tex_infernium_block(rng: Random) -> Image.Image:
    img = Image.new("RGB", (16, 16))
    for y in range(16):
        for x in range(16):
            img.putpixel((x, y), E if rng.random() < 0.85 else e)
    # frame + cross seams
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), C)
        img.putpixel((i, 7), D)
        img.putpixel((7, i), D)
    # rivets
    for x, y in [(2, 2), (13, 2), (2, 13), (13, 13), (2, 9), (13, 9), (9, 2), (9, 13)]:
        img.putpixel((x, y), H)
    # sheen
    for x, y in [(3, 3), (4, 3), (3, 4), (10, 10), (11, 10)]:
        img.putpixel((x, y), H)
    return img


def tex_smolder_crystal_block(rng: Random) -> Image.Image:
    img = Image.new("RGB", (16, 16))
    for y in range(16):
        for x in range(16):
            img.putpixel((x, y), D if rng.random() < 0.8 else C)
    # clustered crystal facets: (cx, cy, size)
    for cx, cy, s in [(3, 4, 3), (10, 2, 4), (5, 11, 4), (12, 10, 3), (8, 7, 3)]:
        for dy in range(s):
            for dx in range(s - dy):
                x, y = cx + dx, cy + dy
                if 0 <= x < 16 and 0 <= y < 16:
                    img.putpixel((x, y), e if dx == 0 else E)
        if 0 <= cx < 16 and 0 <= cy < 16:
            img.putpixel((cx, cy), H)
    # stray glow specks
    for x, y in [(1, 9), (14, 6), (7, 14), (14, 14), (2, 1)]:
        img.putpixel((x, y), E)
    return img


BLOCK_TEXTURES = {
    "infernium_block": tex_infernium_block,
    "smolder_crystal_block": tex_smolder_crystal_block,
}


# ---------------------------------------------------------------------------
# Worn-armor layer textures: vanilla iron layers recolored to ember/charcoal
# ---------------------------------------------------------------------------

def lerp(a, b, t):
    return tuple(int(round(a[i] + (b[i] - a[i]) * t)) for i in range(3))


def recolor_iron_layer(png_bytes: bytes) -> Image.Image:
    src = Image.open(__import__("io").BytesIO(png_bytes)).convert("RGBA")
    out = Image.new("RGBA", src.size, (0, 0, 0, 0))
    for y in range(src.size[1]):
        for x in range(src.size[0]):
            r, gg, b, a = src.getpixel((x, y))
            if a == 0:
                continue
            # The vanilla iron layer's opaque luminance only spans ~0.70..1.0, so stretch
            # that band across the full charcoal -> ember -> hot ramp for readable shading.
            lum = (r + gg + b) / (3 * 255)
            t = min(1.0, max(0.0, (lum - 0.70) / 0.30))
            if t < 0.4:
                color = lerp(D, E, t / 0.4)          # shadows/seams -> charcoal
            else:
                color = lerp(E, H, (t - 0.4) / 0.6)  # plates -> ember/hot orange
            out.putpixel((x, y), (*color, a))
    return out


def emit_armor_layers() -> None:
    if not CLIENT_JAR.is_file():
        print(f"infernium_gen: WARNING client jar not found at {CLIENT_JAR}; "
              "skipping worn-armor layer textures", file=sys.stderr)
        return
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        for layer in ("humanoid", "humanoid_leggings"):
            data = jar.read(f"assets/minecraft/textures/entity/equipment/{layer}/iron.png")
            img = recolor_iron_layer(data)
            dest = ASSETS / "textures" / "entity" / "equipment" / layer / "infernium.png"
            dest.parent.mkdir(parents=True, exist_ok=True)
            img.save(dest)


def emit_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in ITEM_TEXTURES.items():
        fn(Random(f"{NS}:{name}")).save(item_dir / f"{name}.png")
    block_dir = ASSETS / "textures" / "block"
    block_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in BLOCK_TEXTURES.items():
        fn(Random(f"{NS}:{name}")).save(block_dir / f"{name}.png")
    emit_armor_layers()


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_block_assets()
    emit_equipment_asset()
    emit_recipes()
    emit_repair_tag()
    emit_lang()
    emit_tagfrag()
    emit_textures()
    mode = "textures + JSON" if WRITE_JSON else "textures only (pass --write-json for JSON)"
    print(f"infernium_gen: assets generated ({mode}).")


if __name__ == "__main__":
    main()
