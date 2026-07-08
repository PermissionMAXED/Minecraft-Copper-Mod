#!/usr/bin/env python3
"""Asset generator for the v4 "Arsenal & Trinkets" feature (WP6).

~90 standalone items registered by feature/arsenal/ArsenalFeature.java:
  - 4 gear tiers x 9 pieces (pyrium, emberite, ashsteel, voidsteel: sword/pickaxe/axe/
    shovel/hoe/helmet/chestplate/leggings/boots) built on the WP5 gem-alloy ingots
  - 54 singles: throwing weapons, charms & talismans, utility gadgets, crafting catalysts

Emits (all deterministic, idempotent — run any number of times, same bytes):
  - 90 16x16 item texture PNGs (tier tools/armor are ports of the infernium_gen painters
    parameterized on the WP5 material palettes; singles use small shape painters below)
  - items/<id>.json + models/item/<id>.json for all 90 ids (handheld parent for
    tools/weapons, item/generated layer0 otherwise) — the vanilla 1.21.9 formats via
    devtools/gen/lib_gen.py
  - the 4 worn-armor equipment assets (assets/copper_inferno/equipment/<tier>.json) plus
    humanoid/humanoid_leggings layer textures recolored from the vanilla iron layers
    (same approach as infernium_gen.emit_armor_layers)
  - 90 recipes under data/copper_inferno/recipe/arsenal/. UNIQUE-INPUT RULE: every
    crafting recipe includes copper_inferno:arsenal_catalyst or another arsenal-unique
    item (tool/armor recipes use minecraft:stick handles + the catalyst + the matching
    WP5 ingot), so no vanilla/mod input collisions are possible
  - the 4 repair item tags (data/copper_inferno/tags/item/<tier>_repair.json -> WP5 ingot)
  - lang fragments EN + DE (assets/copper_inferno/lang/fragments{,_de}/arsenal.json),
    real German: 90 names + 44 material tooltips + 1 prospector-lens overlay message
  - the vanilla-tag fragment devtools/tagfrag/arsenal.json for merge_tags.py
    (item/swords, pickaxes, axes, shovels, hoes, head/chest/leg/foot_armor)
"""

import sys
import zipfile
from pathlib import Path

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import (  # noqa: E402
    NS, _im, _it, _shade, item_def, merge, write_files, write_json,
)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
TAGFRAG = ROOT / "devtools" / "tagfrag"
CLIENT_JAR = Path.home() / ".gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"

# ---------------------------------------------------------------------------
# Content lists (must match ArsenalFeature.java registrations 1:1)
# ---------------------------------------------------------------------------

TIERS = ["pyrium", "emberite", "ashsteel", "voidsteel"]
PIECES = ["sword", "pickaxe", "axe", "shovel", "hoe",
          "helmet", "chestplate", "leggings", "boots"]

# Mid-tone tier colors, identical to the WP5 gemalloy_gen BASE_RGB entries.
TIER_RGB = {
    "pyrium": (0xE0, 0xA6, 0x2E),
    "emberite": (0xD9, 0x5B, 0x2A),
    "ashsteel": (0x9C, 0xA0, 0xA4),
    "voidsteel": (0x5A, 0x4A, 0x78),
}

THROWING = ["ember_javelin", "slag_hammer", "ash_grenade", "cinder_dart",
            "copper_bolas", "voidsteel_chakram"]
CHARMS = ["magnet_charm", "ember_ward_talisman", "haste_charm", "leaping_charm",
          "void_ward_talisman", "tide_talisman", "storm_talisman", "miner_talisman",
          "hearth_talisman", "shadow_charm", "gilded_charm", "pepper_charm"]
GADGETS = ["copper_grapnel", "prospector_lens", "signal_horn", "portable_anvil",
           "pocket_furnace", "copper_trowel", "slag_scraper", "ember_lighter",
           "tuning_hammer", "arsenal_whetstone", "field_kit", "copper_plumb_line"]
CATALYSTS = ["arsenal_catalyst", "catalyst_dust", "tempered_plate", "doom_alloy_shard",
             "pyrium_core", "emberite_core", "ashsteel_core", "voidsteel_core",
             "gilded_rivet", "forge_binding", "weapon_grip", "blast_powder",
             "molten_solder", "arc_filament", "hardened_strap", "lens_blank",
             "alloy_mesh", "tool_matrix", "javelin_shaft", "charm_locket",
             "talisman_cord", "grapnel_hook", "whetstone_grit", "horn_valve"]

GEAR = [f"{t}_{p}" for t in TIERS for p in PIECES]
SINGLES = THROWING + CHARMS + GADGETS + CATALYSTS
ALL_ITEMS = GEAR + SINGLES

# Items with real use-behavior in Java (everything else is an ArsenalMaterialItem and
# needs an item.copper_inferno.<id>.tooltip lang line).
BEHAVIOR = {"ember_javelin", "slag_hammer", "ash_grenade", "magnet_charm",
            "ember_ward_talisman", "haste_charm", "leaping_charm", "copper_grapnel",
            "prospector_lens", "signal_horn"}
MATERIALS = [i for i in SINGLES if i not in BEHAVIOR]

HANDHELD = {f"{t}_{p}" for t in TIERS for p in ("sword", "pickaxe", "axe", "shovel", "hoe")}
HANDHELD |= {"ember_javelin", "slag_hammer", "copper_trowel", "slag_scraper", "tuning_hammer"}

# ---------------------------------------------------------------------------
# Palette helpers
# ---------------------------------------------------------------------------

K = (0x1C, 0x12, 0x16)   # outline / near-black
D = (0x2B, 0x22, 0x26)   # very dark charcoal (handles)
C = (0x3D, 0x2C, 0x2E)   # charcoal
c_ = (0x54, 0x3E, 0x42)  # charcoal light
COPPER = (0xC1, 0x6A, 0x3F)
COPPER_L = (0xE0, 0x8C, 0x5C)
IRON = (0xB8, 0xB8, 0xC0)
IRON_L = (0xE4, 0xE4, 0xEA)
GOLD = (0xF3, 0xC5, 0x4A)
GOLD_L = (0xFF, 0xE2, 0x8F)
ASH = (0x9A, 0x8F, 0x8A)
ASH_D = (0x6E, 0x65, 0x60)
LEATHER = (0x8A, 0x5A, 0x33)
CORD = (0x7A, 0x5C, 0x38)
GLASS = (0xC9, 0xE4, 0xEE)
GLASS_D = (0x8F, 0xB8, 0xCC)
RED = (0x9A, 0x22, 0x28)
EMBER = (0xE2, 0x58, 0x22)
EMBER_L = (0xFF, 0x7A, 0x2F)


def tier_palette(t: str) -> dict:
    base = TIER_RGB[t]
    return {
        "E": base,                    # main metal
        "e": _shade(base, 36),        # bright
        "H": _shade(base, 80),        # highlight
        "W": _shade(base, 124),       # near-white glint
        "D": D, "C": C, "c": c_,      # charcoal handle shades
    }


# ---------------------------------------------------------------------------
# Sprite primitives (all deterministic, no RNG needed)
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
    """MC-style sprite outline drawn OUTSIDE the shape (port of infernium_gen.outline)."""
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


def ring(img, cx, cy, r2_min, r2_max, color):
    for y in range(16):
        for x in range(16):
            d2 = (x - cx) ** 2 + (y - cy) ** 2
            if r2_min <= d2 <= r2_max:
                put(img, x, y, color)


def diag_handle(img, x0, y0, steps):
    """2-wide charcoal handle running up-right from (x0, y0), infernium_gen style."""
    for i in range(steps):
        x, y = x0 + i, y0 - i
        put(img, x, y, D)
        put(img, x + 1, y, C)


# ---------------------------------------------------------------------------
# Tier tool/armor painters (ports of the infernium_gen painters, palette-driven)
# ---------------------------------------------------------------------------


def tex_sword(p):
    img = blank()
    for i in range(9):
        x, y = 13 - i, 1 + i
        put(img, x, y, p["H"] if i < 2 else p["e"])
        put(img, x - 1, y + 1, p["E"])
        put(img, x, y + 1, p["e"])
    for x, y in [(3, 10), (4, 11), (4, 9), (5, 10), (2, 11), (5, 12)]:
        put(img, x, y, p["E"])
    put(img, 3, 12, p["D"])
    put(img, 2, 13, p["D"])
    put(img, 1, 14, p["C"])
    return outline(img)


def tex_pickaxe(p):
    img = blank()
    diag_handle(img, 2, 13, 9)
    head = [(4, 3), (5, 2), (6, 1), (7, 1), (8, 1), (9, 1), (10, 2), (11, 3),
            (12, 4), (13, 5), (13, 6), (14, 7), (14, 8)]
    for x, y in head:
        put(img, x, y, p["E"])
        put(img, x, y + 1, p["e"])
    for x, y in [(7, 1), (8, 1), (7, 2), (8, 2)]:
        put(img, x, y, p["H"])
    return outline(img)


def tex_axe(p):
    img = blank()
    diag_handle(img, 2, 13, 9)
    fill_rect(img, 9, 2, 12, 2, p["E"])
    fill_rect(img, 8, 3, 13, 3, p["E"])
    fill_rect(img, 7, 4, 13, 4, p["E"])
    fill_rect(img, 7, 5, 9, 5, p["E"])
    fill_rect(img, 12, 5, 13, 5, p["E"])
    fill_rect(img, 7, 6, 8, 6, p["E"])
    put(img, 7, 7, p["E"])
    for x, y in [(9, 2), (10, 2), (8, 3)]:
        put(img, x, y, p["H"])
    for x, y in [(7, 5), (7, 6), (7, 7), (8, 6)]:
        put(img, x, y, p["e"])
    return outline(img)


def tex_shovel(p):
    img = blank()
    diag_handle(img, 2, 13, 8)
    fill_rect(img, 11, 1, 12, 1, p["E"])
    fill_rect(img, 10, 2, 13, 2, p["E"])
    fill_rect(img, 9, 3, 14, 3, p["E"])
    fill_rect(img, 9, 4, 14, 4, p["E"])
    fill_rect(img, 10, 5, 13, 5, p["E"])
    fill_rect(img, 11, 6, 12, 6, p["E"])
    for x, y in [(11, 1), (12, 1), (10, 2), (11, 2)]:
        put(img, x, y, p["H"])
    for x, y in [(9, 4), (10, 5), (11, 6)]:
        put(img, x, y, p["e"])
    return outline(img)


def tex_hoe(p):
    img = blank()
    diag_handle(img, 2, 13, 9)
    for x, y in [(8, 1), (9, 1), (10, 1), (11, 1), (12, 2), (13, 3),
                 (8, 2), (9, 2), (10, 2), (11, 2), (12, 3)]:
        put(img, x, y, p["E"])
    for x, y in [(8, 1), (9, 1)]:
        put(img, x, y, p["H"])
    put(img, 8, 2, p["e"])
    return outline(img)


def tex_helmet(p):
    img = blank()
    fill_rect(img, 4, 4, 11, 5, p["E"])
    fill_rect(img, 3, 6, 12, 8, p["E"])
    put(img, 5, 3, p["E"])
    fill_rect(img, 6, 3, 9, 3, p["E"])
    put(img, 10, 3, p["E"])
    fill_rect(img, 3, 9, 5, 11, p["E"])
    fill_rect(img, 10, 9, 12, 11, p["E"])
    fill_rect(img, 6, 9, 9, 9, p["e"])
    fill_rect(img, 5, 4, 6, 5, p["H"])
    put(img, 7, 3, p["H"])
    put(img, 4, 7, p["e"])
    return outline(img)


def tex_chestplate(p):
    img = blank()
    fill_rect(img, 2, 3, 5, 5, p["E"])
    fill_rect(img, 10, 3, 13, 5, p["E"])
    fill_rect(img, 3, 6, 12, 12, p["E"])
    fill_rect(img, 6, 4, 9, 5, p["e"])
    for x in range(6, 10):
        img.putpixel((x, 3), (0, 0, 0, 0))
    fill_rect(img, 4, 7, 5, 9, p["H"])
    fill_rect(img, 7, 8, 8, 12, p["e"])
    put(img, 3, 3, p["H"])
    return outline(img)


def tex_leggings(p):
    img = blank()
    fill_rect(img, 3, 3, 12, 5, p["E"])
    fill_rect(img, 3, 3, 12, 3, p["e"])
    fill_rect(img, 3, 6, 6, 13, p["E"])
    fill_rect(img, 9, 6, 12, 13, p["E"])
    fill_rect(img, 4, 6, 4, 12, p["H"])
    put(img, 10, 6, p["e"])
    put(img, 10, 7, p["e"])
    return outline(img)


def tex_boots(p):
    img = blank()
    for x0 in (2, 9):
        fill_rect(img, x0, 5, x0 + 3, 8, p["E"])
        fill_rect(img, x0, 9, x0 + 4, 11, p["E"])
        put(img, x0 + 1, 5, p["H"])
        fill_rect(img, x0, 11, x0 + 4, 11, p["e"])
    return outline(img)


PIECE_PAINTERS = {
    "sword": tex_sword, "pickaxe": tex_pickaxe, "axe": tex_axe,
    "shovel": tex_shovel, "hoe": tex_hoe, "helmet": tex_helmet,
    "chestplate": tex_chestplate, "leggings": tex_leggings, "boots": tex_boots,
}

# ---------------------------------------------------------------------------
# Single-item painters
# ---------------------------------------------------------------------------


def tex_javelin(head, shaft):
    img = blank()
    for i in range(10):
        x, y = 2 + i, 13 - i
        put(img, x, y, shaft)
        put(img, x + 1, y, _shade(shaft, -20))
    for x, y in [(12, 3), (13, 2), (14, 1), (13, 3), (12, 2)]:
        put(img, x, y, head)
    put(img, 14, 1, _shade(head, 60))
    return outline(img)


def tex_dart(head, shaft):
    img = blank()
    for i in range(6):
        x, y = 4 + i, 11 - i
        put(img, x, y, shaft)
    for x, y in [(10, 4), (11, 3), (10, 5)]:
        put(img, x, y, head)
    put(img, 11, 3, _shade(head, 60))
    for x, y in [(3, 12), (4, 12), (3, 13)]:
        put(img, x, y, RED)
    return outline(img)


def tex_hammer(head, grip):
    img = blank()
    diag_handle(img, 2, 13, 8)
    fill_rect(img, 8, 1, 13, 5, head)
    fill_rect(img, 8, 1, 13, 1, _shade(head, 40))
    fill_rect(img, 8, 5, 13, 5, _shade(head, -30))
    put(img, 9, 2, _shade(head, 70))
    put(img, 3, 12, grip)
    return outline(img)


def tex_grenade(shell, band):
    img = blank()
    ring(img, 7.5, 8.5, 0, 20, shell)
    fill_rect(img, 5, 8, 10, 8, band)
    fill_rect(img, 7, 3, 8, 4, C)
    put(img, 8, 2, EMBER_L)
    put(img, 5, 6, _shade(shell, 50))
    put(img, 6, 6, _shade(shell, 50))
    return outline(img)


def tex_bolas(ball, cord):
    img = blank()
    for x, y in [(4, 5), (5, 6), (6, 7), (7, 8), (8, 9), (9, 10), (10, 9), (11, 8)]:
        put(img, x, y, cord)
    ring(img, 3.5, 3.5, 0, 4, ball)
    ring(img, 12.5, 11.5, 0, 4, ball)
    put(img, 3, 3, _shade(ball, 60))
    put(img, 12, 11, _shade(ball, 60))
    return outline(img)


def tex_chakram(metal):
    img = blank()
    ring(img, 7.5, 7.5, 16, 32, metal)
    ring(img, 7.5, 7.5, 30, 32, _shade(metal, 50))
    put(img, 4, 4, _shade(metal, 80))
    return outline(img)


def tex_pendant(gem, plate):
    """Round charm on a short chain."""
    img = blank()
    for x, y in [(7, 1), (8, 2), (7, 3), (8, 4)]:
        put(img, x, y, CORD)
    ring(img, 7.5, 9.5, 0, 13, plate)
    ring(img, 7.5, 9.5, 0, 4, gem)
    put(img, 6, 8, _shade(plate, 60))
    put(img, 7, 9, _shade(gem, 60))
    return outline(img)


def tex_talisman(gem):
    """Diamond-shaped talisman tile on a cord loop."""
    img = blank()
    for x, y in [(5, 2), (6, 1), (7, 1), (8, 1), (9, 1), (10, 2), (5, 3), (10, 3)]:
        put(img, x, y, CORD)
    for dy in range(-3, 4):
        half = 3 - abs(dy)
        for dx in range(-half, half + 1):
            put(img, 7 + dx, 9 + dy, gem)
    put(img, 7, 9, _shade(gem, 80))
    put(img, 6, 8, _shade(gem, 40))
    for dy in range(-3, 4):
        half = 3 - abs(dy)
        put(img, 7 - half, 9 + dy, _shade(gem, -40))
    return outline(img)


def tex_orb(color):
    img = blank()
    ring(img, 7.5, 7.5, 0, 20, color)
    ring(img, 7.5, 7.5, 14, 20, _shade(color, -40))
    put(img, 6, 6, _shade(color, 70))
    put(img, 5, 7, _shade(color, 40))
    return outline(img)


def tex_catalyst():
    img = blank()
    ring(img, 7.5, 7.5, 0, 10, GOLD)
    for x, y in [(7, 2), (8, 2), (7, 13), (8, 13), (2, 7), (2, 8), (13, 7), (13, 8)]:
        put(img, x, y, GOLD_L)
    for x, y in [(7, 3), (8, 12), (3, 7), (12, 8), (4, 4), (11, 11), (11, 4), (4, 11)]:
        put(img, x, y, GOLD)
    put(img, 7, 7, (0xFF, 0xF6, 0xD0))
    put(img, 8, 8, GOLD_L)
    return outline(img)


def tex_pile(color):
    img = blank()
    rows = {8: (6, 9), 9: (5, 10), 10: (4, 11), 11: (3, 12), 12: (3, 12)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            put(img, x, y, color if (x * 7 + y * 5) % 3 else _shade(color, 30))
    for x, y in [(5, 6), (9, 5), (7, 4)]:
        put(img, x, y, _shade(color, 50))
    return outline(img)


def tex_shard(color):
    img = blank()
    for y in range(2, 14):
        half = max(0, 3 - abs(y - 8) * 2 // 3)
        for x in range(7 - half, 8 + half + 1):
            put(img, x, y, color)
    for y in range(4, 12):
        put(img, 7 - max(0, 2 - abs(y - 8) // 2), y, _shade(color, 40))
    put(img, 7, 7, _shade(color, 90))
    return outline(img)


def tex_plate(color):
    img = blank()
    fill_rect(img, 3, 5, 12, 11, color)
    fill_rect(img, 4, 6, 11, 7, _shade(color, 30))
    for x, y in [(4, 6), (11, 6), (4, 10), (11, 10)]:
        put(img, x, y, C)
    put(img, 5, 6, _shade(color, 70))
    return outline(img)


def tex_hook(metal):
    img = blank()
    for y in range(2, 9):
        put(img, 7, y, metal)
        put(img, 8, y, _shade(metal, -20))
    for x, y in [(6, 9), (5, 10), (5, 11), (6, 12), (7, 13), (8, 13), (9, 12), (10, 11),
                 (4, 10), (10, 10)]:
        put(img, x, y, metal)
    put(img, 10, 10, _shade(metal, 40))
    fill_rect(img, 6, 1, 9, 1, _shade(metal, -30))
    return outline(img)


def tex_lens(rim, glass):
    img = blank()
    ring(img, 6.5, 6.5, 9, 20, rim)
    ring(img, 6.5, 6.5, 0, 8, glass)
    put(img, 5, 5, (0xF2, 0xFB, 0xFF))
    for i in range(4):
        put(img, 10 + i, 11 + i, rim)
    put(img, 13, 14, _shade(rim, -30))
    return outline(img)


def tex_horn(metal):
    img = blank()
    for i, w in [(0, 1), (1, 1), (2, 2), (3, 2), (4, 3), (5, 3), (6, 4)]:
        x = 3 + i
        for dy in range(w):
            put(img, x, 10 - i + dy, metal)
    fill_rect(img, 10, 3, 12, 7, metal)
    fill_rect(img, 10, 3, 12, 3, _shade(metal, 50))
    put(img, 3, 10, _shade(metal, -40))
    put(img, 11, 4, _shade(metal, 80))
    return outline(img)


def tex_anvil(metal):
    img = blank()
    fill_rect(img, 3, 4, 12, 6, metal)
    fill_rect(img, 6, 7, 9, 9, _shade(metal, -30))
    fill_rect(img, 4, 10, 11, 12, metal)
    fill_rect(img, 3, 4, 12, 4, _shade(metal, 40))
    put(img, 4, 5, _shade(metal, 60))
    return outline(img)


def tex_furnace(stone, fire):
    img = blank()
    fill_rect(img, 3, 3, 12, 12, stone)
    fill_rect(img, 3, 3, 12, 3, _shade(stone, 30))
    fill_rect(img, 5, 7, 10, 10, K)
    for x, y in [(6, 9), (7, 8), (8, 9), (9, 8), (7, 10), (8, 10)]:
        put(img, x, y, fire)
    put(img, 7, 9, EMBER_L)
    return outline(img)


def tex_trowel(metal, grip):
    img = blank()
    diag_handle(img, 2, 13, 5)
    for i, half in [(0, 0), (1, 1), (2, 2), (3, 2), (4, 1), (5, 0)]:
        x = 8 + i - 3
        y = 8 - i
        for d in range(-half, half + 1):
            put(img, 9 + d, 5 + i - 3, metal)
    fill_rect(img, 8, 3, 11, 6, metal)
    put(img, 9, 4, _shade(metal, 50))
    return outline(img)


def tex_scraper(metal, grip):
    img = blank()
    diag_handle(img, 3, 12, 6)
    fill_rect(img, 9, 2, 13, 4, metal)
    fill_rect(img, 9, 2, 13, 2, _shade(metal, 40))
    put(img, 10, 3, _shade(metal, 60))
    return outline(img)


def tex_lighter(shell, flame):
    img = blank()
    fill_rect(img, 5, 6, 10, 13, shell)
    fill_rect(img, 5, 6, 10, 6, _shade(shell, 40))
    put(img, 6, 8, _shade(shell, 60))
    for x, y in [(7, 4), (8, 3), (7, 2), (8, 4)]:
        put(img, x, y, flame)
    put(img, 7, 3, EMBER_L)
    return outline(img)


def tex_whetstone(stone):
    img = blank()
    fill_rect(img, 2, 8, 13, 11, stone)
    fill_rect(img, 2, 8, 13, 8, _shade(stone, 30))
    fill_rect(img, 2, 11, 13, 11, _shade(stone, -30))
    for x in (4, 7, 10):
        put(img, x, 9, _shade(stone, -20))
    return outline(img)


def tex_kit(bag, clasp):
    img = blank()
    fill_rect(img, 3, 6, 12, 12, bag)
    fill_rect(img, 3, 5, 12, 6, _shade(bag, -30))
    for x, y in [(5, 3), (6, 2), (9, 2), (10, 3), (7, 2), (8, 2)]:
        put(img, x, y, _shade(bag, -30))
    fill_rect(img, 7, 8, 8, 9, clasp)
    return outline(img)


def tex_plumb(metal, cord):
    img = blank()
    for y in range(1, 8):
        put(img, 7, y, cord)
    fill_rect(img, 6, 8, 9, 10, metal)
    for x, y in [(7, 11), (8, 11), (7, 12)]:
        put(img, x, y, metal)
    put(img, 7, 13, _shade(metal, -30))
    put(img, 6, 8, _shade(metal, 50))
    return outline(img)


def tex_rivet(metal):
    img = blank()
    for cx, cy in [(4, 4), (11, 4), (4, 11), (11, 11)]:
        ring(img, cx, cy, 0, 3, metal)
        put(img, cx - 1, cy - 1, _shade(metal, 60))
    return outline(img)


def tex_binding(strap, buckle):
    img = blank()
    for i in range(10):
        put(img, 3 + i, 3 + i, strap)
        put(img, 4 + i, 3 + i, _shade(strap, -20))
        put(img, 12 - i, 3 + i, strap)
        put(img, 11 - i, 3 + i, _shade(strap, -20))
    fill_rect(img, 7, 7, 8, 8, buckle)
    return outline(img)


def tex_grip(wrap):
    img = blank()
    fill_rect(img, 6, 2, 9, 13, wrap)
    for y in range(3, 13, 2):
        fill_rect(img, 6, y, 9, y, _shade(wrap, -30))
    fill_rect(img, 6, 2, 9, 2, _shade(wrap, 30))
    return outline(img)


def tex_solder(metal):
    img = blank()
    fill_rect(img, 3, 9, 12, 11, metal)
    fill_rect(img, 3, 9, 12, 9, _shade(metal, 40))
    for x, y in [(6, 6), (7, 5), (7, 7), (8, 6)]:
        put(img, x, y, EMBER)
    put(img, 7, 6, EMBER_L)
    return outline(img)


def tex_filament(wire):
    img = blank()
    pts = [(2, 11), (3, 10), (4, 9), (5, 10), (6, 11), (7, 10), (8, 9), (9, 8),
           (10, 7), (11, 6), (12, 5), (13, 4)]
    for x, y in pts:
        put(img, x, y, wire)
        put(img, x, y + 1, _shade(wire, -30))
    put(img, 13, 4, _shade(wire, 60))
    put(img, 2, 11, _shade(wire, 60))
    return outline(img)


def tex_strap(leather, stud):
    img = blank()
    fill_rect(img, 2, 6, 13, 9, leather)
    fill_rect(img, 2, 6, 13, 6, _shade(leather, 25))
    fill_rect(img, 2, 9, 13, 9, _shade(leather, -30))
    for x in (4, 8, 12):
        put(img, x, 7, stud)
    return outline(img)


def tex_lens_blank(glass):
    img = blank()
    ring(img, 7.5, 7.5, 0, 18, glass)
    ring(img, 7.5, 7.5, 13, 18, _shade(glass, -30))
    put(img, 6, 6, (0xF2, 0xFB, 0xFF))
    return outline(img)


def tex_mesh(metal):
    img = blank()
    for y in range(3, 13):
        for x in range(3, 13):
            if x % 3 == 0 or y % 3 == 0:
                put(img, x, y, metal if (x + y) % 2 else _shade(metal, -25))
    return outline(img)


def tex_matrix(metal, node):
    img = blank()
    for y in range(3, 13):
        for x in range(3, 13):
            if x % 4 == 3 or y % 4 == 3:
                put(img, x, y, metal)
    for x, y in [(5, 5), (9, 5), (5, 9), (9, 9)]:
        put(img, x, y, node)
    return outline(img)


def tex_shaft(wood):
    img = blank()
    for i in range(11):
        x, y = 2 + i, 13 - i
        put(img, x, y, wood)
        put(img, x + 1, y, _shade(wood, -25))
    put(img, 12, 3, _shade(wood, 30))
    fill_rect(img, 2, 13, 3, 13, _shade(wood, -35))
    return outline(img)


def tex_locket(metal):
    img = blank()
    for x, y in [(7, 2), (8, 3), (7, 4)]:
        put(img, x, y, CORD)
    ring(img, 7.5, 9.5, 0, 12, metal)
    ring(img, 7.5, 9.5, 8, 12, _shade(metal, -35))
    put(img, 6, 8, _shade(metal, 60))
    put(img, 7, 9, _shade(metal, 30))
    return outline(img)


def tex_cord(cord):
    img = blank()
    ring(img, 7.5, 7.5, 14, 26, cord)
    for x, y in [(7, 12), (8, 12), (7, 13), (8, 14)]:
        put(img, x, y, cord)
    put(img, 8, 14, _shade(cord, -30))
    put(img, 4, 4, _shade(cord, 30))
    return outline(img)


def tex_valve(metal):
    img = blank()
    fill_rect(img, 6, 4, 9, 12, metal)
    fill_rect(img, 4, 6, 11, 8, metal)
    fill_rect(img, 6, 4, 9, 4, _shade(metal, 45))
    fill_rect(img, 4, 6, 11, 6, _shade(metal, 25))
    put(img, 7, 5, _shade(metal, 70))
    fill_rect(img, 6, 12, 9, 12, _shade(metal, -30))
    return outline(img)


PYR = TIER_RGB["pyrium"]
EMB = TIER_RGB["emberite"]
ASHS = TIER_RGB["ashsteel"]
VOID = TIER_RGB["voidsteel"]

SINGLE_PAINTERS = {
    # throwing weapons
    "ember_javelin": lambda: tex_javelin(EMBER, C),
    "slag_hammer": lambda: tex_hammer(C, D),
    "ash_grenade": lambda: tex_grenade(ASH_D, ASH),
    "cinder_dart": lambda: tex_dart(EMBER, C),
    "copper_bolas": lambda: tex_bolas(COPPER, CORD),
    "voidsteel_chakram": lambda: tex_chakram(VOID),
    # charms & talismans
    "magnet_charm": lambda: tex_pendant(RED, IRON),
    "ember_ward_talisman": lambda: tex_talisman(EMBER),
    "haste_charm": lambda: tex_pendant((0xE8, 0xD2, 0x4A), GOLD),
    "leaping_charm": lambda: tex_pendant((0x6F, 0xD8, 0x6F), GOLD),
    "void_ward_talisman": lambda: tex_talisman(VOID),
    "tide_talisman": lambda: tex_talisman((0x4E, 0xA8, 0x96)),
    "storm_talisman": lambda: tex_talisman((0x6F, 0x8F, 0xC8)),
    "miner_talisman": lambda: tex_talisman(IRON),
    "hearth_talisman": lambda: tex_talisman((0xB4, 0x5C, 0x30)),
    "shadow_charm": lambda: tex_pendant((0x30, 0x28, 0x3C), IRON),
    "gilded_charm": lambda: tex_pendant(GOLD_L, GOLD),
    "pepper_charm": lambda: tex_pendant((0x7E, 0x2E, 0x38), COPPER),
    # utility gadgets
    "copper_grapnel": lambda: tex_hook(COPPER),
    "prospector_lens": lambda: tex_lens(COPPER, GLASS),
    "signal_horn": lambda: tex_horn(COPPER),
    "portable_anvil": lambda: tex_anvil(IRON),
    "pocket_furnace": lambda: tex_furnace((0x80, 0x80, 0x80), EMBER),
    "copper_trowel": lambda: tex_trowel(COPPER, D),
    "slag_scraper": lambda: tex_scraper(C, D),
    "ember_lighter": lambda: tex_lighter(IRON, EMBER),
    "tuning_hammer": lambda: tex_hammer(IRON, D),
    "arsenal_whetstone": lambda: tex_whetstone((0x8A, 0x8A, 0x8A)),
    "field_kit": lambda: tex_kit(LEATHER, GOLD),
    "copper_plumb_line": lambda: tex_plumb(COPPER, CORD),
    # crafting catalysts & parts
    "arsenal_catalyst": tex_catalyst,
    "catalyst_dust": lambda: tex_pile(GOLD),
    "tempered_plate": lambda: tex_plate(IRON),
    "doom_alloy_shard": lambda: tex_shard(RED),
    "pyrium_core": lambda: tex_orb(PYR),
    "emberite_core": lambda: tex_orb(EMB),
    "ashsteel_core": lambda: tex_orb(ASHS),
    "voidsteel_core": lambda: tex_orb(VOID),
    "gilded_rivet": lambda: tex_rivet(GOLD),
    "forge_binding": lambda: tex_binding(LEATHER, IRON),
    "weapon_grip": lambda: tex_grip(LEATHER),
    "blast_powder": lambda: tex_pile((0x5A, 0x5A, 0x5A)),
    "molten_solder": lambda: tex_solder(COPPER_L),
    "arc_filament": lambda: tex_filament(COPPER_L),
    "hardened_strap": lambda: tex_strap(LEATHER, IRON),
    "lens_blank": lambda: tex_lens_blank(GLASS_D),
    "alloy_mesh": lambda: tex_mesh(IRON),
    "tool_matrix": lambda: tex_matrix(IRON, RED),
    "javelin_shaft": lambda: tex_shaft(CORD),
    "charm_locket": lambda: tex_locket(GOLD),
    "talisman_cord": lambda: tex_cord(CORD),
    "grapnel_hook": lambda: tex_hook(IRON),
    "whetstone_grit": lambda: tex_pile((0x8A, 0x8A, 0x8A)),
    "horn_valve": lambda: tex_valve(GOLD),
}

# ---------------------------------------------------------------------------
# Worn-armor equipment layers (vanilla iron layers recolored per tier palette,
# same approach as infernium_gen.emit_armor_layers)
# ---------------------------------------------------------------------------


def lerp(a, b, t):
    return tuple(int(round(a[i] + (b[i] - a[i]) * t)) for i in range(3))


def recolor_iron_layer(png_bytes: bytes, base) -> Image.Image:
    import io
    dark = _shade(base, -64)
    light = _shade(base, 70)
    src = Image.open(io.BytesIO(png_bytes)).convert("RGBA")
    out = Image.new("RGBA", src.size, (0, 0, 0, 0))
    for y in range(src.size[1]):
        for x in range(src.size[0]):
            r, g, b, a = src.getpixel((x, y))
            if a == 0:
                continue
            # Vanilla iron layer's opaque luminance spans ~0.70..1.0: stretch it over the
            # dark -> base -> light tier ramp (same trick as infernium_gen).
            lum = (r + g + b) / (3 * 255)
            t = min(1.0, max(0.0, (lum - 0.70) / 0.30))
            if t < 0.4:
                color = lerp(dark, base, t / 0.4)
            else:
                color = lerp(base, light, (t - 0.4) / 0.6)
            out.putpixel((x, y), (*color, a))
    return out


def emit_armor_layers() -> int:
    if not CLIENT_JAR.is_file():
        print(f"arsenal_gen: WARNING client jar not found at {CLIENT_JAR}; "
              "skipping worn-armor layer textures", file=sys.stderr)
        return 0
    n = 0
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        for layer in ("humanoid", "humanoid_leggings"):
            data = jar.read(f"assets/minecraft/textures/entity/equipment/{layer}/iron.png")
            for t in TIERS:
                img = recolor_iron_layer(data, TIER_RGB[t])
                dest = ASSETS / "textures" / "entity" / "equipment" / layer / f"{t}.png"
                dest.parent.mkdir(parents=True, exist_ok=True)
                img.save(dest)
                n += 1
    return n


def emit_equipment_assets() -> None:
    # Schema copied from vanilla assets/minecraft/equipment/iron.json (see infernium_gen),
    # without the horse_body layer.
    for t in TIERS:
        write_json(ASSETS / "equipment" / f"{t}.json", {
            "layers": {
                "humanoid": [{"texture": f"{NS}:{t}"}],
                "humanoid_leggings": [{"texture": f"{NS}:{t}"}],
            }
        })


# ---------------------------------------------------------------------------
# Item JSON (items/<id>.json + models/item/<id>.json, vanilla 1.21.9 formats)
# ---------------------------------------------------------------------------


def emit_item_assets() -> dict:
    files = {}
    for name in ALL_ITEMS:
        parent = "minecraft:item/handheld" if name in HANDHELD else "minecraft:item/generated"
        files = merge(files, {
            _im(name): {"parent": parent, "textures": {"layer0": f"{NS}:item/{name}"}},
            _it(name): item_def(f"{NS}:item/{name}"),
        })
    return files


def emit_textures() -> int:
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    n = 0
    for t in TIERS:
        p = tier_palette(t)
        for piece, painter in PIECE_PAINTERS.items():
            painter(p).save(item_dir / f"{t}_{piece}.png")
            n += 1
    for name, painter in SINGLE_PAINTERS.items():
        painter().save(item_dir / f"{name}.png")
        n += 1
    return n


# ---------------------------------------------------------------------------
# Recipes (data/copper_inferno/recipe/arsenal/, vanilla 1.21.9 formats mirroring
# infernium_gen). UNIQUE-INPUT RULE: every recipe includes arsenal_catalyst or
# another arsenal-unique item.
# ---------------------------------------------------------------------------


def rp(name: str) -> Path:
    return DATA / "recipe" / "arsenal" / f"{name}.json"


CAT = f"{NS}:arsenal_catalyst"
DUST = f"{NS}:catalyst_dust"

GEAR_PATTERNS = {
    "sword": ["X ", "XC", "# "],
    "pickaxe": ["XXX", " C ", " # "],
    "axe": ["XX", "XC", " #"],
    "shovel": ["X", "C", "#"],
    "hoe": ["XX", " C", " #"],
    "helmet": ["XXX", "XCX"],
    "chestplate": ["XCX", "XXX", "XXX"],
    "leggings": ["XXX", "XCX", "X X"],
    "boots": ["X X", "XCX"],
}


def emit_recipes() -> int:
    n = 0

    def shaped(name: str, key: dict, pattern: list, count: int = 1,
               category: str = "equipment") -> None:
        nonlocal n
        write_json(rp(name), {
            "type": "minecraft:crafting_shaped", "category": category,
            "key": key, "pattern": pattern,
            "result": {"count": count, "id": f"{NS}:{name}"},
        })
        n += 1

    def shapeless(name: str, ingredients: list, count: int = 1,
                  category: str = "misc") -> None:
        nonlocal n
        write_json(rp(name), {
            "type": "minecraft:crafting_shapeless", "category": category,
            "ingredients": ingredients,
            "result": {"count": count, "id": f"{NS}:{name}"},
        })
        n += 1

    # --- gear: stick handles + arsenal_catalyst + the matching WP5 ingot
    for t in TIERS:
        ing = f"{NS}:{t}_ingot"
        for piece, pattern in GEAR_PATTERNS.items():
            key = {"X": ing, "C": CAT}
            if any("#" in row for row in pattern):
                key["#"] = "minecraft:stick"
            shaped(f"{t}_{piece}", key, pattern)

    # --- the catalyst root: all four WP5 tier ingots around blaze powder
    shaped("arsenal_catalyst", {
        "P": f"{NS}:pyrium_ingot", "E": f"{NS}:emberite_ingot",
        "V": f"{NS}:voidsteel_ingot", "A": f"{NS}:ashsteel_ingot",
        "B": "minecraft:blaze_powder",
    }, [" P ", "EBV", " A "], count=2, category="misc")
    shapeless("catalyst_dust", [CAT], count=4)

    # --- parts (each includes catalyst dust or another arsenal-unique item)
    shapeless("tempered_plate", [DUST, "minecraft:iron_ingot", "minecraft:iron_ingot"])
    shapeless("doom_alloy_shard", [DUST, f"{NS}:doomium_ingot"], count=2)
    for t in TIERS:
        shaped(f"{t}_core", {"X": f"{NS}:{t}_ingot", "D": DUST},
               [" X ", "XDX", " X "], category="misc")
    shapeless("gilded_rivet", [DUST, "minecraft:gold_ingot"], count=4)
    shapeless("forge_binding", [DUST, f"{NS}:slag_chunk"], count=2)
    shapeless("weapon_grip", [DUST, "minecraft:stick", "minecraft:leather"], count=2)
    shapeless("blast_powder", [DUST, "minecraft:gunpowder", "minecraft:gunpowder"], count=3)
    shapeless("molten_solder", [DUST, "minecraft:copper_ingot", "minecraft:blaze_powder"], count=2)
    shapeless("arc_filament", [DUST, "minecraft:copper_ingot", "minecraft:redstone"], count=2)
    shapeless("hardened_strap", [DUST, "minecraft:leather", "minecraft:iron_nugget"], count=2)
    shapeless("lens_blank", [DUST, "minecraft:glass", "minecraft:glass"], count=2)
    shapeless("alloy_mesh", [DUST, "minecraft:iron_nugget", "minecraft:copper_ingot"], count=2)
    shapeless("tool_matrix", [DUST, f"{NS}:tempered_plate", "minecraft:redstone"])
    shaped("javelin_shaft", {"D": DUST, "S": "minecraft:stick"}, ["D", "S", "S"],
           count=2, category="misc")
    shapeless("charm_locket", [DUST, "minecraft:gold_nugget", "minecraft:gold_nugget"])
    shapeless("talisman_cord", [DUST, "minecraft:string", "minecraft:string"], count=2)
    shapeless("grapnel_hook", [DUST, "minecraft:iron_ingot", "minecraft:iron_nugget"])
    shapeless("whetstone_grit", [DUST, "minecraft:flint"], count=2)
    shapeless("horn_valve", [DUST, "minecraft:copper_ingot", "minecraft:gold_nugget"])

    # --- throwing weapons
    shaped("ember_javelin", {"E": f"{NS}:emberite_ingot", "J": f"{NS}:javelin_shaft"},
           ["E", "J", "J"], count=2)
    shaped("slag_hammer", {"T": f"{NS}:tempered_plate", "G": f"{NS}:weapon_grip"},
           ["TTT", "TGT", " G "])
    shapeless("ash_grenade", [f"{NS}:blast_powder", f"{NS}:ash_pile"], count=2,
              category="equipment")
    shapeless("cinder_dart", [f"{NS}:javelin_shaft", "minecraft:flint"], count=4,
              category="equipment")
    shapeless("copper_bolas", [f"{NS}:talisman_cord", "minecraft:copper_ingot",
                               "minecraft:copper_ingot"], category="equipment")
    shaped("voidsteel_chakram", {"V": f"{NS}:voidsteel_ingot", "T": f"{NS}:tempered_plate"},
           [" V ", "VTV", " V "])

    # --- charms & talismans
    shaped("magnet_charm", {"I": "minecraft:iron_ingot", "R": "minecraft:redstone",
                            "L": f"{NS}:charm_locket"}, [" I ", "IRI", " L "])
    shapeless("ember_ward_talisman", [f"{NS}:talisman_cord", "minecraft:magma_cream"])
    shapeless("haste_charm", [f"{NS}:charm_locket", "minecraft:redstone", "minecraft:sugar"])
    shapeless("leaping_charm", [f"{NS}:charm_locket", "minecraft:rabbit_foot"])
    shapeless("void_ward_talisman", [f"{NS}:talisman_cord", "minecraft:ender_pearl"])
    shapeless("tide_talisman", [f"{NS}:talisman_cord", "minecraft:prismarine_shard"])
    shapeless("storm_talisman", [f"{NS}:talisman_cord", "minecraft:copper_ingot",
                                 "minecraft:redstone"])
    shapeless("miner_talisman", [f"{NS}:talisman_cord", "minecraft:raw_iron"])
    shapeless("hearth_talisman", [f"{NS}:talisman_cord", "minecraft:coal"])
    shapeless("shadow_charm", [f"{NS}:charm_locket", "minecraft:black_dye"])
    shapeless("gilded_charm", [f"{NS}:charm_locket", "minecraft:gold_ingot"])
    shapeless("pepper_charm", [f"{NS}:charm_locket", f"{NS}:dr_pepper"])

    # --- utility gadgets
    shaped("copper_grapnel", {"H": f"{NS}:grapnel_hook", "S": "minecraft:string",
                              "C": "minecraft:copper_ingot"}, ["H", "S", "C"])
    shaped("prospector_lens", {"C": "minecraft:copper_ingot", "L": f"{NS}:lens_blank",
                               "D": DUST}, [" C ", "CLC", " D "])
    shaped("signal_horn", {"C": "minecraft:copper_ingot", "V": f"{NS}:horn_valve"},
           ["CC", "CV"])
    shaped("portable_anvil", {"T": f"{NS}:tempered_plate", "I": "minecraft:iron_block"},
           ["TTT", " I ", "TTT"])
    shapeless("pocket_furnace", [f"{NS}:tempered_plate", "minecraft:furnace",
                                 "minecraft:blaze_powder"], category="equipment")
    shaped("copper_trowel", {"C": "minecraft:copper_ingot", "G": f"{NS}:weapon_grip"},
           ["C", "G"])
    shaped("slag_scraper", {"S": f"{NS}:slag_chunk", "G": f"{NS}:weapon_grip"},
           ["S", "G"])
    shapeless("ember_lighter", [f"{NS}:molten_solder", "minecraft:flint",
                                "minecraft:iron_ingot"], category="equipment")
    shaped("tuning_hammer", {"I": "minecraft:iron_nugget", "G": f"{NS}:weapon_grip"},
           ["I", "G"])
    shapeless("arsenal_whetstone", [f"{NS}:whetstone_grit", "minecraft:stone"],
              category="equipment")
    shapeless("field_kit", [f"{NS}:hardened_strap", "minecraft:paper",
                            "minecraft:iron_nugget"], category="equipment")
    shapeless("copper_plumb_line", [f"{NS}:talisman_cord", "minecraft:copper_ingot",
                                    "minecraft:iron_nugget"], category="equipment")
    return n


# ---------------------------------------------------------------------------
# Repair tags
# ---------------------------------------------------------------------------


def emit_repair_tags() -> None:
    for t in TIERS:
        write_json(DATA / "tags" / "item" / f"{t}_repair.json",
                   {"values": [f"{NS}:{t}_ingot"]})


# ---------------------------------------------------------------------------
# Lang fragments (EN + real German)
# ---------------------------------------------------------------------------

TIER_EN = {"pyrium": "Pyrium", "emberite": "Emberite",
           "ashsteel": "Ashsteel", "voidsteel": "Voidsteel"}
PIECE_EN = {"sword": "Sword", "pickaxe": "Pickaxe", "axe": "Axe", "shovel": "Shovel",
            "hoe": "Hoe", "helmet": "Helmet", "chestplate": "Chestplate",
            "leggings": "Leggings", "boots": "Boots"}
PIECE_DE = {"sword": "Schwert", "pickaxe": "Spitzhacke", "axe": "Axt",
            "shovel": "Schaufel", "hoe": "Hacke", "helmet": "Helm",
            "chestplate": "Harnisch", "leggings": "Beinschutz", "boots": "Stiefel"}

SINGLE_EN = {
    "ember_javelin": "Ember Javelin",
    "slag_hammer": "Slag Hammer",
    "ash_grenade": "Ash Grenade",
    "cinder_dart": "Cinder Dart",
    "copper_bolas": "Copper Bolas",
    "voidsteel_chakram": "Voidsteel Chakram",
    "magnet_charm": "Magnet Charm",
    "ember_ward_talisman": "Ember Ward Talisman",
    "haste_charm": "Haste Charm",
    "leaping_charm": "Leaping Charm",
    "void_ward_talisman": "Void Ward Talisman",
    "tide_talisman": "Tide Talisman",
    "storm_talisman": "Storm Talisman",
    "miner_talisman": "Miner's Talisman",
    "hearth_talisman": "Hearth Talisman",
    "shadow_charm": "Shadow Charm",
    "gilded_charm": "Gilded Charm",
    "pepper_charm": "Pepper Charm",
    "copper_grapnel": "Copper Grapnel",
    "prospector_lens": "Prospector Lens",
    "signal_horn": "Signal Horn",
    "portable_anvil": "Portable Anvil",
    "pocket_furnace": "Pocket Furnace",
    "copper_trowel": "Copper Trowel",
    "slag_scraper": "Slag Scraper",
    "ember_lighter": "Ember Lighter",
    "tuning_hammer": "Tuning Hammer",
    "arsenal_whetstone": "Arsenal Whetstone",
    "field_kit": "Field Kit",
    "copper_plumb_line": "Copper Plumb Line",
    "arsenal_catalyst": "Arsenal Catalyst",
    "catalyst_dust": "Catalyst Dust",
    "tempered_plate": "Tempered Plate",
    "doom_alloy_shard": "Doom Alloy Shard",
    "pyrium_core": "Pyrium Core",
    "emberite_core": "Emberite Core",
    "ashsteel_core": "Ashsteel Core",
    "voidsteel_core": "Voidsteel Core",
    "gilded_rivet": "Gilded Rivet",
    "forge_binding": "Forge Binding",
    "weapon_grip": "Weapon Grip",
    "blast_powder": "Blast Powder",
    "molten_solder": "Molten Solder",
    "arc_filament": "Arc Filament",
    "hardened_strap": "Hardened Strap",
    "lens_blank": "Lens Blank",
    "alloy_mesh": "Alloy Mesh",
    "tool_matrix": "Tool Matrix",
    "javelin_shaft": "Javelin Shaft",
    "charm_locket": "Charm Locket",
    "talisman_cord": "Talisman Cord",
    "grapnel_hook": "Grapnel Hook",
    "whetstone_grit": "Whetstone Grit",
    "horn_valve": "Horn Valve",
}

SINGLE_DE = {
    "ember_javelin": "Glutspeer",
    "slag_hammer": "Schlackehammer",
    "ash_grenade": "Aschegranate",
    "cinder_dart": "Zinder-Wurfpfeil",
    "copper_bolas": "Kupfer-Bola",
    "voidsteel_chakram": "Voidsteel-Chakram",
    "magnet_charm": "Magnetamulett",
    "ember_ward_talisman": "Glutschutz-Talisman",
    "haste_charm": "Eile-Amulett",
    "leaping_charm": "Sprungamulett",
    "void_ward_talisman": "Leerenschutz-Talisman",
    "tide_talisman": "Gezeiten-Talisman",
    "storm_talisman": "Sturm-Talisman",
    "miner_talisman": "Bergmanns-Talisman",
    "hearth_talisman": "Herdfeuer-Talisman",
    "shadow_charm": "Schattenamulett",
    "gilded_charm": "Vergoldetes Amulett",
    "pepper_charm": "Pfeffer-Amulett",
    "copper_grapnel": "Kupfer-Enterhaken",
    "prospector_lens": "Sch\u00fcrferlinse",
    "signal_horn": "Signalhorn",
    "portable_anvil": "Tragbarer Amboss",
    "pocket_furnace": "Taschenofen",
    "copper_trowel": "Kupferkelle",
    "slag_scraper": "Schlackekratzer",
    "ember_lighter": "Glutfeuerzeug",
    "tuning_hammer": "Stimmhammer",
    "arsenal_whetstone": "Arsenal-Wetzstein",
    "field_kit": "Feldausr\u00fcstung",
    "copper_plumb_line": "Kupfer-Lot",
    "arsenal_catalyst": "Arsenal-Katalysator",
    "catalyst_dust": "Katalysatorstaub",
    "tempered_plate": "Geh\u00e4rtete Platte",
    "doom_alloy_shard": "Doom-Legierungssplitter",
    "pyrium_core": "Pyrium-Kern",
    "emberite_core": "Emberite-Kern",
    "ashsteel_core": "Ashsteel-Kern",
    "voidsteel_core": "Voidsteel-Kern",
    "gilded_rivet": "Vergoldete Niete",
    "forge_binding": "Schmiedebund",
    "weapon_grip": "Waffengriff",
    "blast_powder": "Sprengpulver",
    "molten_solder": "Geschmolzenes L\u00f6tmetall",
    "arc_filament": "Lichtbogenfaden",
    "hardened_strap": "Geh\u00e4rteter Riemen",
    "lens_blank": "Linsenrohling",
    "alloy_mesh": "Legierungsgeflecht",
    "tool_matrix": "Werkzeugmatrix",
    "javelin_shaft": "Speerschaft",
    "charm_locket": "Amulett-Medaillon",
    "talisman_cord": "Talisman-Kordel",
    "grapnel_hook": "Enterhakenspitze",
    "whetstone_grit": "Wetzstein-K\u00f6rnung",
    "horn_valve": "Hornventil",
}

TOOLTIP_EN = {
    "cinder_dart": "Fletched from a javelin shaft - ammunition for tinkerers.",
    "copper_bolas": "Two copper weights on a cord. Best thrown in stories.",
    "voidsteel_chakram": "A razor ring of voidsteel. Display piece of the arsenal.",
    "void_ward_talisman": "Woven against the whispers of the void.",
    "tide_talisman": "Smells faintly of the sea.",
    "storm_talisman": "Hums when thunderheads gather.",
    "miner_talisman": "A lucky nugget wound in cord.",
    "hearth_talisman": "Warm to the touch, like a banked fire.",
    "shadow_charm": "It drinks the light around it.",
    "gilded_charm": "Pure ostentation, beautifully made.",
    "pepper_charm": "Fizzes softly. Smells of 23 flavors.",
    "portable_anvil": "Every smith's dream, every back's nightmare.",
    "pocket_furnace": "A furnace folded small enough for a satchel.",
    "copper_trowel": "Lays mortar; points at blocks judgmentally.",
    "slag_scraper": "For chipping cooled slag off the forge floor.",
    "ember_lighter": "Clicks out a stubborn little flame.",
    "tuning_hammer": "Taps horns and valves back into pitch.",
    "arsenal_whetstone": "Puts the edge back on a hard day's blade.",
    "field_kit": "Straps, patches and spare rivets for the road.",
    "copper_plumb_line": "Finds true vertical, even in the Inferno.",
    "arsenal_catalyst": "All four gem alloys fused - the key to every arsenal recipe.",
    "catalyst_dust": "Ground catalyst - binds arsenal parts together.",
    "tempered_plate": "Twice-hardened plate, the arsenal's backbone.",
    "doom_alloy_shard": "A sliver of doomium alloy. It hums a low E.",
    "pyrium_core": "A compressed heart of pyrium.",
    "emberite_core": "A compressed heart of emberite.",
    "ashsteel_core": "A compressed heart of ashsteel.",
    "voidsteel_core": "A compressed heart of voidsteel.",
    "gilded_rivet": "Gold-capped rivets for fancy fittings.",
    "forge_binding": "Slag-cured straps that hold a forge together.",
    "weapon_grip": "Leather-wrapped grip, worn smooth.",
    "blast_powder": "Handle far away from the forge, please.",
    "molten_solder": "Joins copper to anything, briefly to fingers.",
    "arc_filament": "Carries a spark along a copper thread.",
    "hardened_strap": "Boiled leather with an iron stud.",
    "lens_blank": "Unground glass, waiting for a purpose.",
    "alloy_mesh": "Woven metal cloth, surprisingly flexible.",
    "tool_matrix": "A lattice that remembers the shape of tools.",
    "javelin_shaft": "Straight-grained and balanced for throwing.",
    "charm_locket": "An empty locket, eager for a charm.",
    "talisman_cord": "Braided cord for hanging talismans.",
    "grapnel_hook": "The business end of a grapnel.",
    "whetstone_grit": "Coarse grit for dressing whetstones.",
    "horn_valve": "Lets a horn hit the low notes.",
}

TOOLTIP_DE = {
    "cinder_dart": "Aus einem Speerschaft gefiedert - Munition f\u00fcr Bastler.",
    "copper_bolas": "Zwei Kupfergewichte an einer Schnur. Am besten in Geschichten geworfen.",
    "voidsteel_chakram": "Ein Klingenring aus Voidsteel. Prunkst\u00fcck des Arsenals.",
    "void_ward_talisman": "Geflochten gegen das Fl\u00fcstern der Leere.",
    "tide_talisman": "Riecht schwach nach Meer.",
    "storm_talisman": "Summt, wenn Gewitterwolken aufziehen.",
    "miner_talisman": "Ein Gl\u00fccksklumpen, in Kordel gewickelt.",
    "hearth_talisman": "Warm wie ein gedecktes Herdfeuer.",
    "shadow_charm": "Es trinkt das Licht um sich herum.",
    "gilded_charm": "Reine Angeberei, wundersch\u00f6n gemacht.",
    "pepper_charm": "Zischt leise. Riecht nach 23 Geschmacksrichtungen.",
    "portable_anvil": "Der Traum jedes Schmieds, der Albtraum jedes R\u00fcckens.",
    "pocket_furnace": "Ein Ofen, klein genug f\u00fcr die Satteltasche gefaltet.",
    "copper_trowel": "Verstreicht M\u00f6rtel; zeigt vorwurfsvoll auf Bl\u00f6cke.",
    "slag_scraper": "Zum Abklopfen erkalteter Schlacke vom Schmiedeboden.",
    "ember_lighter": "Klickt eine sture kleine Flamme hervor.",
    "tuning_hammer": "Klopft H\u00f6rner und Ventile zur\u00fcck in die Stimmung.",
    "arsenal_whetstone": "Bringt die Schneide nach einem harten Tag zur\u00fcck.",
    "field_kit": "Riemen, Flicken und Ersatznieten f\u00fcr unterwegs.",
    "copper_plumb_line": "Findet die wahre Senkrechte, sogar im Inferno.",
    "arsenal_catalyst": "Alle vier Edellegierungen verschmolzen - der Schl\u00fcssel zu jedem Arsenal-Rezept.",
    "catalyst_dust": "Gemahlener Katalysator - bindet Arsenal-Bauteile zusammen.",
    "tempered_plate": "Zweifach geh\u00e4rtete Platte, das R\u00fcckgrat des Arsenals.",
    "doom_alloy_shard": "Ein Splitter Doomium-Legierung. Er summt ein tiefes E.",
    "pyrium_core": "Ein verdichtetes Herz aus Pyrium.",
    "emberite_core": "Ein verdichtetes Herz aus Emberite.",
    "ashsteel_core": "Ein verdichtetes Herz aus Ashsteel.",
    "voidsteel_core": "Ein verdichtetes Herz aus Voidsteel.",
    "gilded_rivet": "Goldgekr\u00f6nte Nieten f\u00fcr edle Beschl\u00e4ge.",
    "forge_binding": "In Schlacke geh\u00e4rtete Riemen, die eine Schmiede zusammenhalten.",
    "weapon_grip": "Lederumwickelter Griff, glatt gewetzt.",
    "blast_powder": "Bitte weit weg von der Esse lagern.",
    "molten_solder": "Verbindet Kupfer mit allem, kurzzeitig auch mit Fingern.",
    "arc_filament": "Tr\u00e4gt einen Funken \u00fcber einen Kupferfaden.",
    "hardened_strap": "Gekochtes Leder mit Eisenniete.",
    "lens_blank": "Ungeschliffenes Glas, das auf einen Zweck wartet.",
    "alloy_mesh": "Gewebtes Metalltuch, erstaunlich biegsam.",
    "tool_matrix": "Ein Gitter, das sich die Form von Werkzeugen merkt.",
    "javelin_shaft": "Gerade gemasert und zum Werfen ausbalanciert.",
    "charm_locket": "Ein leeres Medaillon, das sich nach einem Zauber sehnt.",
    "talisman_cord": "Geflochtene Kordel zum Aufh\u00e4ngen von Talismanen.",
    "grapnel_hook": "Das Gesch\u00e4ftsende eines Enterhakens.",
    "whetstone_grit": "Grobe K\u00f6rnung zum Abrichten von Wetzsteinen.",
    "horn_valve": "L\u00e4sst ein Horn die tiefen T\u00f6ne treffen.",
}


def emit_lang() -> tuple[int, int]:
    en = {}
    de = {}
    i = f"item.{NS}."
    for t in TIERS:
        for p in PIECES:
            en[f"{i}{t}_{p}"] = f"{TIER_EN[t]} {PIECE_EN[p]}"
            de[f"{i}{t}_{p}"] = f"{TIER_EN[t]}-{PIECE_DE[p]}"
    for s in SINGLES:
        en[f"{i}{s}"] = SINGLE_EN[s]
        de[f"{i}{s}"] = SINGLE_DE[s]
    for m in MATERIALS:
        en[f"{i}{m}.tooltip"] = TOOLTIP_EN[m]
        de[f"{i}{m}.tooltip"] = TOOLTIP_DE[m]
    en[f"{i}prospector_lens.result"] = "Ores within 8 blocks: %s"
    de[f"{i}prospector_lens.result"] = "Erze im Umkreis von 8 Bl\u00f6cken: %s"
    write_json(ASSETS / "lang" / "fragments" / "arsenal.json", en)
    write_json(ASSETS / "lang" / "fragments_de" / "arsenal.json", de)
    return len(en), len(de)


# ---------------------------------------------------------------------------
# Vanilla-tag fragment
# ---------------------------------------------------------------------------


def emit_tagfrag() -> None:
    write_json(TAGFRAG / "arsenal.json", {
        "item/swords": sorted(f"{NS}:{t}_sword" for t in TIERS),
        "item/pickaxes": sorted(f"{NS}:{t}_pickaxe" for t in TIERS),
        "item/axes": sorted(f"{NS}:{t}_axe" for t in TIERS),
        "item/shovels": sorted(f"{NS}:{t}_shovel" for t in TIERS),
        "item/hoes": sorted(f"{NS}:{t}_hoe" for t in TIERS),
        "item/head_armor": sorted(f"{NS}:{t}_helmet" for t in TIERS),
        "item/chest_armor": sorted(f"{NS}:{t}_chestplate" for t in TIERS),
        "item/leg_armor": sorted(f"{NS}:{t}_leggings" for t in TIERS),
        "item/foot_armor": sorted(f"{NS}:{t}_boots" for t in TIERS),
    })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    assert len(ALL_ITEMS) == 90, f"expected 90 items, got {len(ALL_ITEMS)}"
    assert len(set(ALL_ITEMS)) == 90, "duplicate item id"
    assert set(SINGLE_PAINTERS) == set(SINGLES), "painter/id mismatch"

    files = emit_item_assets()
    json_count = write_files(files, RES)
    tex_count = emit_textures()
    layer_count = emit_armor_layers()
    emit_equipment_assets()
    recipe_count = emit_recipes()
    emit_repair_tags()
    en_count, de_count = emit_lang()
    emit_tagfrag()

    item_defs = [p for p in files if f"assets/{NS}/items/" in p]
    models = [p for p in files if f"assets/{NS}/models/item/" in p]
    assert len(item_defs) == 90, f"expected 90 item defs, got {len(item_defs)}"
    assert len(models) == 90, f"expected 90 item models, got {len(models)}"
    assert recipe_count == 90, f"expected 90 recipes, got {recipe_count}"
    assert tex_count == 90, f"expected 90 textures, got {tex_count}"
    assert en_count == de_count == 90 + len(MATERIALS) + 1, \
        f"lang key count mismatch: {en_count}/{de_count}"

    print(f"arsenal_gen: 90 items -> {json_count} item JSON files, {tex_count} textures, "
          f"{recipe_count} recipes, 4 repair tags, {layer_count} armor layer textures, "
          f"4 equipment assets, {en_count} EN + {de_count} DE lang keys, 1 tag fragment.")


if __name__ == "__main__":
    main()
