#!/usr/bin/env python3
"""Asset generator for the v4 "Doom Ascendancy" feature (WP14): 6 bosses, 30 items.

Idempotent: running it any number of times produces byte-identical files. Emits, directly
into src/main/resources:
  - 6 entity textures       assets/copper_inferno/textures/entity/<boss>.png
                            (lib_gen.extract_vanilla of the base mob's vanilla texture,
                            remapped onto the boss's palette via lib_gen.recolor)
  - 30 item textures        assets/copper_inferno/textures/item/<id>.png (16x16, Pillow,
                            seeded: 6 summon items + 12 drops + 6 trophies + 6 spawn eggs)
  - item model-definitions  assets/copper_inferno/items/<id>.json (vanilla 1.21.9 format)
  - item models             assets/copper_inferno/models/item/<id>.json (item/generated)
  - entity loot tables      data/copper_inferno/loot_table/entities/<boss>.json
                            (infernoboss schema incl. "random_sequence"; 3 pools per boss:
                            2 drops + 1 EPIC trophy)
  - recipes                 data/copper_inferno/recipe/bossdoom/*.json — one shaped recipe
                            per summon item; every input set contains at least one
                            copper_inferno item (dr_pepper / inferno_powder / voidstone /
                            infernium_ingot), so the canonical input set is unique
                            (collision-checked via devtools/check_recipe_collisions.py)
  - lang fragments          assets/copper_inferno/lang/fragments/bossdoom.json (EN)
                            assets/copper_inferno/lang/fragments_de/bossdoom.json (DE)

Boss roster (base mob -> summon item -> drops + trophy):
  dr_doompepper       ravager    dr_doompepper_sigil       doom_syrup, doompepper_heart + doompepper_crown
  the_carbonated_one  slime      the_carbonated_one_core   carbonation_crystal, pressurized_gel + eternal_bottlecap
  soda_seraph         phantom    soda_seraph_sigil         seraph_plume, sugar_essence + seraph_halo
  kiln_archon         blaze      kiln_archon_core          archon_cinder, molten_flux + kiln_scepter
  voidstone_behemoth  iron_golem voidstone_behemoth_core   behemoth_plate, singularity_pearl + voidstone_idol
  the_last_smith      evoker     the_last_smith_sigil      forged_scrap, master_blueprint + smiths_masterwork
"""

import math
import sys
from pathlib import Path
from random import Random

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import (  # noqa: E402
    NS, extract_vanilla, item_def, recolor, write_json,
)
from PIL import Image  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# ---------------------------------------------------------------------------
# Palettes (dark -> light luminance ramps for lib_gen.recolor)
# ---------------------------------------------------------------------------

DOOMPEPPER = [(0x2A, 0x07, 0x0A), (0x5A, 0x0E, 0x14), (0x8C, 0x1A, 0x20), (0xC0, 0x3A, 0x33), (0xF0, 0x8A, 0x5A)]
FIZZ = [(0x0E, 0x33, 0x14), (0x1F, 0x66, 0x26), (0x3C, 0xA8, 0x3F), (0x7E, 0xD4, 0x6B), (0xD2, 0xF7, 0xB8)]
CREAM = [(0x4A, 0x14, 0x2E), (0x8C, 0x2E, 0x55), (0xC9, 0x54, 0x84), (0xEE, 0x8F, 0xB4), (0xFF, 0xD9, 0xE8)]
KILN = [(0x3A, 0x1A, 0x08), (0x8C, 0x3A, 0x0E), (0xD2, 0x66, 0x1A), (0xF7, 0xA3, 0x3C), (0xFF, 0xE9, 0xA8)]
VOID = [(0x0A, 0x08, 0x12), (0x1E, 0x18, 0x33), (0x3C, 0x2E, 0x5E), (0x6A, 0x54, 0x99), (0xA8, 0x8F, 0xD4)]
SMITH = [(0x14, 0x12, 0x14), (0x33, 0x30, 0x36), (0x5C, 0x58, 0x60), (0x8F, 0x8A, 0x94), (0xC9, 0xC4, 0xCE)]
GOLD = [(0x4A, 0x33, 0x0E), (0x8F, 0x66, 0x18), (0xD2, 0x9E, 0x2C), (0xF0, 0xC6, 0x4A), (0xFF, 0xEA, 0x9E)]

# ---------------------------------------------------------------------------
# Roster: boss id -> (vanilla entity texture in the client jar, recolor palette,
#                     [(drop item id, loot min, loot max), ...] — last entry = trophy)
# ---------------------------------------------------------------------------

E = "assets/minecraft/textures/entity"

BOSSES = {
    "dr_doompepper": (f"{E}/illager/ravager.png", DOOMPEPPER, [
        (f"{NS}:doom_syrup", 2.0, 4.0),
        (f"{NS}:doompepper_heart", 1.0, 1.0),
        (f"{NS}:doompepper_crown", 1.0, 1.0),
    ]),
    "the_carbonated_one": (f"{E}/slime/slime.png", FIZZ, [
        (f"{NS}:carbonation_crystal", 2.0, 4.0),
        (f"{NS}:pressurized_gel", 1.0, 2.0),
        (f"{NS}:eternal_bottlecap", 1.0, 1.0),
    ]),
    "soda_seraph": (f"{E}/phantom.png", CREAM, [
        (f"{NS}:seraph_plume", 2.0, 4.0),
        (f"{NS}:sugar_essence", 1.0, 2.0),
        (f"{NS}:seraph_halo", 1.0, 1.0),
    ]),
    "kiln_archon": (f"{E}/blaze.png", KILN, [
        (f"{NS}:archon_cinder", 2.0, 3.0),
        (f"{NS}:molten_flux", 1.0, 2.0),
        (f"{NS}:kiln_scepter", 1.0, 1.0),
    ]),
    "voidstone_behemoth": (f"{E}/iron_golem/iron_golem.png", VOID, [
        (f"{NS}:behemoth_plate", 2.0, 4.0),
        (f"{NS}:singularity_pearl", 1.0, 1.0),
        (f"{NS}:voidstone_idol", 1.0, 1.0),
    ]),
    "the_last_smith": (f"{E}/illager/evoker.png", SMITH, [
        (f"{NS}:forged_scrap", 2.0, 4.0),
        (f"{NS}:master_blueprint", 1.0, 1.0),
        (f"{NS}:smiths_masterwork", 1.0, 1.0),
    ]),
}

SUMMON_ITEMS = {
    "dr_doompepper": "dr_doompepper_sigil",
    "the_carbonated_one": "the_carbonated_one_core",
    "soda_seraph": "soda_seraph_sigil",
    "kiln_archon": "kiln_archon_core",
    "voidstone_behemoth": "voidstone_behemoth_core",
    "the_last_smith": "the_last_smith_sigil",
}

DROPS = [
    "doom_syrup", "doompepper_heart",
    "carbonation_crystal", "pressurized_gel",
    "seraph_plume", "sugar_essence",
    "archon_cinder", "molten_flux",
    "behemoth_plate", "singularity_pearl",
    "forged_scrap", "master_blueprint",
]

TROPHIES = [
    "doompepper_crown", "eternal_bottlecap", "seraph_halo",
    "kiln_scepter", "voidstone_idol", "smiths_masterwork",
]

ITEM_IDS = (list(SUMMON_ITEMS.values()) + DROPS + TROPHIES
            + [f"{boss}_spawn_egg" for boss in BOSSES])

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

BOSS_NAMES = {
    "dr_doompepper": ("Dr. Doompepper", "Dr. Doompepper"),
    "the_carbonated_one": ("The Carbonated One", "Der Karbonisierte"),
    "soda_seraph": ("Soda Seraph", "Limonaden-Seraph"),
    "kiln_archon": ("Kiln Archon", "Brennofen-Archon"),
    "voidstone_behemoth": ("Voidstone Behemoth", "Leerenstein-Behemoth"),
    "the_last_smith": ("The Last Smith", "Der Letzte Schmied"),
}

ITEM_NAMES = {
    "dr_doompepper_sigil": ("Doompepper Sigil", "Doompepper-Siegel"),
    "the_carbonated_one_core": ("Carbonated Core", "Karbonisierter Kern"),
    "soda_seraph_sigil": ("Seraph Sigil", "Seraph-Siegel"),
    "kiln_archon_core": ("Kiln Core", "Brennofen-Kern"),
    "voidstone_behemoth_core": ("Behemoth Core", "Behemoth-Kern"),
    "the_last_smith_sigil": ("Smith's Sigil", "Schmiedesiegel"),
    "doom_syrup": ("Doom Syrup", "Doom-Sirup"),
    "doompepper_heart": ("Doompepper Heart", "Doompepper-Herz"),
    "doompepper_crown": ("Doompepper Crown", "Doompepper-Krone"),
    "carbonation_crystal": ("Carbonation Crystal", "Kohlensäurekristall"),
    "pressurized_gel": ("Pressurized Gel", "Druckgel"),
    "eternal_bottlecap": ("Eternal Bottlecap", "Ewiger Kronkorken"),
    "seraph_plume": ("Seraph Plume", "Seraphenfeder"),
    "sugar_essence": ("Sugar Essence", "Zuckeressenz"),
    "seraph_halo": ("Seraph Halo", "Seraphen-Heiligenschein"),
    "archon_cinder": ("Archon Cinder", "Archon-Asche"),
    "molten_flux": ("Molten Flux", "Schmelzfluss"),
    "kiln_scepter": ("Kiln Scepter", "Brennofen-Zepter"),
    "behemoth_plate": ("Behemoth Plate", "Behemoth-Platte"),
    "singularity_pearl": ("Singularity Pearl", "Singularitätsperle"),
    "voidstone_idol": ("Voidstone Idol", "Leerenstein-Götze"),
    "forged_scrap": ("Forged Scrap", "Geschmiedeter Schrott"),
    "master_blueprint": ("Master Blueprint", "Meisterbauplan"),
    "smiths_masterwork": ("Smith's Masterwork", "Meisterwerk des Schmieds"),
}


def emit_lang() -> None:
    en, de = {}, {}
    for boss, (name_en, name_de) in BOSS_NAMES.items():
        en[f"entity.{NS}.{boss}"] = name_en
        de[f"entity.{NS}.{boss}"] = name_de
        en[f"item.{NS}.{boss}_spawn_egg"] = f"{name_en} Spawn Egg"
        de[f"item.{NS}.{boss}_spawn_egg"] = f"{name_de}-Spawn-Ei"
    for item, (name_en, name_de) in ITEM_NAMES.items():
        en[f"item.{NS}.{item}"] = name_en
        de[f"item.{NS}.{item}"] = name_de
    en[f"message.{NS}.doom_sigil.peaceful"] = "The doom refuses to rise on peaceful difficulty."
    de[f"message.{NS}.doom_sigil.peaceful"] = "Das Verhängnis erhebt sich nicht auf friedlichem Schwierigkeitsgrad."
    en[f"message.{NS}.doom_sigil.blocked"] = "There is no room for the doom to rise here."
    de[f"message.{NS}.doom_sigil.blocked"] = "Hier ist kein Platz, damit sich das Verhängnis erheben kann."
    write_json(ASSETS / "lang" / "fragments" / "bossdoom.json", en)
    write_json(ASSETS / "lang" / "fragments_de" / "bossdoom.json", de)


# ---------------------------------------------------------------------------
# Texture helpers (16x16 item sprites; infernoboss_gen painter style)
# ---------------------------------------------------------------------------


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def spawn_egg(rng: Random, base, base_dark, base_light, spots, outline) -> Image.Image:
    """Classic spawn-egg silhouette with seeded speckles (copperfauna_gen painter)."""
    img = blank()
    half = {2: 1.6, 3: 2.4, 4: 3.0, 5: 3.5, 6: 4.0, 7: 4.4, 8: 4.7, 9: 4.9,
            10: 5.0, 11: 5.0, 12: 4.7, 13: 4.0, 14: 2.8}
    cells = []
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            cells.append((x, y, x == x0 or x == x1 or y in (2, 14)))
    for x, y, edge in cells:
        if edge:
            color = outline
        elif x <= 5 and y <= 9:
            color = base_light
        elif x >= 10 or y >= 12:
            color = base_dark
        else:
            color = base
        px(img, x, y, color)
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, spots)
    return img


def egg_for(boss: str) -> Image.Image:
    """Egg colors derived from the boss's entity palette: body = mid tones, spots = the
    palette's brightest stop, outline = its darkest stop (copperfauna_gen.egg_for)."""
    pal = BOSSES[boss][1]
    rng = Random(f"{NS}:bossdoom:{boss}_spawn_egg")
    return spawn_egg(rng, pal[2], pal[1], pal[3], pal[4], pal[0])


# ------------------------- parameterized painters ---------------------------


def tex_sigil(pal, rune_pal) -> Image.Image:
    """Carved summoning tablet: framed stone slab with a glowing two-armed rune
    (titan_sigil geometry, parameterized palettes)."""
    img = blank()
    for y in range(2, 14):
        for x in range(3, 13):
            if x in (3, 12) or y in (2, 13):
                color = pal[0]
            elif (x * 7 + y * 3) % 11 == 0:
                color = pal[2]
            else:
                color = pal[1]
            px(img, x, y, color)
    for y in range(4, 12):
        px(img, 7, y, rune_pal[3] if y % 2 else rune_pal[2])
    for x, y in [(5, 5), (6, 4), (9, 5), (8, 4), (5, 9), (6, 10), (9, 9), (8, 10)]:
        px(img, x, y, rune_pal[2])
    px(img, 7, 3, rune_pal[4])
    px(img, 7, 12, rune_pal[3])
    return img


def tex_core_orb(pal, ring_pal) -> Image.Image:
    """Caged summoning orb: bright core ringed by a contrasting metal band
    (oxidizer_core geometry, parameterized palettes)."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 7.5)
            if d <= 6.2:
                if d > 5.2:
                    color = ring_pal[0]
                elif d > 4.3:
                    color = ring_pal[2] if (x + y) % 2 else ring_pal[1]
                elif d > 2.4:
                    color = pal[2] if (x * 3 + y * 5) % 7 else pal[1]
                else:
                    color = pal[3]
                px(img, x, y, color)
    for x, y in [(3, 4), (12, 4), (2, 9), (13, 9), (5, 13), (10, 13)]:
        px(img, x, y, pal[1])
    px(img, 7, 7, pal[4])
    px(img, 8, 6, pal[3])
    return img


def tex_bottle(pal) -> Image.Image:
    """Corked syrup bottle with a viscous fill and a glint."""
    img = blank()
    for x in range(6, 10):  # cork + neck
        px(img, x, 2, pal[1])
        px(img, x, 3, pal[0])
    for y in range(4, 14):
        for x in range(4, 12):
            if x in (4, 11) or y in (4, 13):
                color = pal[0]
            elif y >= 7:
                color = pal[2] if (x * 3 + y * 5) % 6 else pal[1]  # syrup
            else:
                color = pal[3]  # headspace shine
            px(img, x, y, color)
    px(img, 5, 8, pal[4])
    px(img, 5, 9, pal[3])
    return img


def tex_heart(pal) -> Image.Image:
    """Still-beating boss heart (oxidizer_heart geometry, parameterized palette)."""
    img = blank()
    rows = {3: [(4, 6), (9, 11)], 4: [(3, 12)], 5: [(3, 12)], 6: [(3, 12)],
            7: [(4, 11)], 8: [(5, 10)], 9: [(6, 9)], 10: [(6, 9)], 11: [(7, 8)], 12: [(7, 8)]}
    for y, spans in rows.items():
        for x0, x1 in spans:
            for x in range(x0, x1 + 1):
                edge = x in (x0, x1) or y in (3, 12)
                if edge:
                    color = pal[0]
                elif x <= 6 and y <= 7:
                    color = pal[3]
                elif y >= 9:
                    color = pal[1]
                else:
                    color = pal[2]
                px(img, x, y, color)
    px(img, 7, 2, pal[1])
    px(img, 8, 2, pal[0])
    px(img, 5, 5, pal[4])
    return img


def tex_crystal(pal) -> Image.Image:
    """Twin-spired crystal cluster on a rock base."""
    img = blank()
    for i, (cx, top, w) in enumerate([(5, 3, 1), (10, 5, 1)]):
        for y in range(top, 12):
            for x in range(cx - w, cx + w + 1):
                if x == cx - w:
                    color = pal[3]
                elif x == cx + w:
                    color = pal[1]
                else:
                    color = pal[2]
                px(img, x, y, color)
        px(img, cx, top - 1, pal[4])
    for y in (12, 13):
        for x in range(3, 13):
            px(img, x, y, pal[0] if y == 13 or x in (3, 12) else pal[1])
    return img


def tex_gel(pal) -> Image.Image:
    """Wobbly pressurized gel blob with a bright core (verdigris_gel geometry)."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 8.0)
            if d <= 5.4:
                if d > 4.4:
                    px(img, x, y, pal[1], 220)
                elif d > 2.4:
                    px(img, x, y, pal[2], 235)
                else:
                    px(img, x, y, pal[3])
    px(img, 6, 6, pal[4])
    px(img, 7, 6, pal[4])
    px(img, 6, 7, pal[4])
    for x, y in [(4, 11), (11, 10), (9, 12)]:
        px(img, x, y, pal[1])
    return img


def tex_plume(pal) -> Image.Image:
    """Curved feather with a dark quill (gilded_feather geometry, parameterized)."""
    img = blank()
    body = {2: (10, 12), 3: (9, 12), 4: (8, 12), 5: (7, 11), 6: (6, 10), 7: (5, 9),
            8: (4, 8), 9: (3, 7), 10: (3, 6), 11: (2, 5), 12: (2, 4)}
    for y, (x0, x1) in body.items():
        for x in range(x0, x1 + 1):
            if x == x0 or y == 2:
                c = pal[4]
            elif x == x1:
                c = pal[1]
            else:
                c = pal[3] if (x + y) % 2 else pal[2]
            px(img, x, y, c)
    for i in range(4):
        px(img, 2 + i // 2, 13 - i // 4, pal[0])
    px(img, 1, 14, pal[0])
    return img


def tex_essence(pal) -> Image.Image:
    """Swirling essence phial: heaped mote pile under drifting sparkles."""
    img = blank()
    half = {8: 2.0, 9: 3.2, 10: 4.2, 11: 5.0, 12: 5.6, 13: 6.0}
    for y, h in half.items():
        x0, x1 = int(math.ceil(7.5 - h)), int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y == 13:
                c = pal[0]
            else:
                c = pal[2] if (x * 5 + y * 3) % 4 else pal[1]
            px(img, x, y, c)
    for x, y in [(5, 9), (9, 10), (7, 11)]:
        px(img, x, y, pal[3])
    for x, y in [(4, 5), (10, 4), (7, 6), (12, 6), (6, 3)]:
        px(img, x, y, pal[4], 150)
    return img


def tex_cinder(pal) -> Image.Image:
    """Angular slag shard over a hot core (titan_ember geometry, parameterized)."""
    img = blank()
    rows = {2: (6, 9), 3: (5, 11), 4: (4, 12), 5: (3, 12), 6: (3, 13), 7: (2, 13),
            8: (2, 13), 9: (3, 13), 10: (3, 12), 11: (4, 11), 12: (5, 10), 13: (6, 9)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            d = math.hypot(x - 7.5, y - 7.5)
            if x in (x0, x1) or y in (2, 13):
                color = pal[0]
            elif d < 2.0:
                color = pal[4]
            elif d < 3.4:
                color = pal[3]
            elif (x * 5 + y * 7) % 9 == 0:
                color = pal[1]
            else:
                color = pal[2]
            px(img, x, y, color)
    px(img, 7, 7, pal[4])
    px(img, 8, 8, pal[4])
    return img


def tex_flux(pal) -> Image.Image:
    """Molten flux puddle: glowing liquid disc with a crusted rim."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 9.0)
            if d <= 5.6 and y >= 5:
                if d > 4.6:
                    color = pal[0]
                elif (x * 3 + y * 7) % 5 == 0:
                    color = pal[4]
                else:
                    color = pal[3] if (x + y) % 2 else pal[2]
                px(img, x, y, color)
    for x, y in [(6, 3), (9, 4), (7, 2)]:  # rising heat motes
        px(img, x, y, pal[4], 150)
    return img


def tex_plate(pal) -> Image.Image:
    """Riveted armor plate (lode_hide geometry, plated look)."""
    img = blank()
    for y in range(3, 14):
        for x in range(2, 14):
            if x in (2, 13) or y in (3, 13):
                c = pal[0]
            elif (x + y) % 5 == 0:
                c = pal[1]
            else:
                c = pal[2] if (x * 7 + y * 3) % 3 else pal[3]
            px(img, x, y, c)
    for i in range(4):  # rivets
        px(img, 4 + 3 * i, 3, pal[4])
        px(img, 4 + 3 * i, 13, pal[4])
    return img


def tex_pearl(pal) -> Image.Image:
    """Singularity pearl: dark orb whose rim bends the light inward."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 7.5)
            if d <= 5.4:
                if d > 4.5:
                    c = pal[2]
                elif d > 3.4:
                    c = pal[1]
                elif d > 1.6:
                    c = pal[0]
                else:
                    c = pal[4]  # the pinprick of collapsed light
                px(img, x, y, c)
    px(img, 4, 4, pal[3])
    px(img, 11, 11, pal[1])
    return img


def tex_scrap(pal) -> Image.Image:
    """Bundle of forged scrap bars, crossed."""
    img = blank()
    for i in range(10):  # bar 1: diagonal
        px(img, 3 + i, 4 + i // 2, pal[2])
        px(img, 3 + i, 5 + i // 2, pal[1])
    for i in range(10):  # bar 2: opposite diagonal
        px(img, 12 - i, 6 + i // 2, pal[3])
        px(img, 12 - i, 7 + i // 2, pal[2])
    for x, y in [(4, 4), (12, 6), (7, 9)]:
        px(img, x, y, pal[4])
    for x in range(5, 11):  # binding strap
        px(img, x, 12, pal[0])
    return img


def tex_blueprint(pal) -> Image.Image:
    """Master blueprint: rolled plan sheet with schematic lines."""
    img = blank()
    for y in range(3, 13):
        for x in range(2, 14):
            if x in (2, 13) or y in (3, 12):
                c = pal[0]
            else:
                c = pal[1]
            px(img, x, y, c)
    for x in range(4, 12):  # schematic sword outline
        px(img, x, 7, pal[4])
    for y in range(5, 10):
        px(img, 5, y, pal[3])
    px(img, 10, 5, pal[3])
    px(img, 10, 9, pal[3])
    for x, y in [(4, 10), (7, 10), (11, 10)]:  # dimension ticks
        px(img, x, y, pal[2])
    return img


def tex_crown(pal, jewel_pal) -> Image.Image:
    """Trophy crown: gold band, flame-tipped points, jewels (inferno_crown geometry)."""
    img = blank()
    for y in (10, 11, 12):
        for x in range(2, 14):
            if y == 12 or x in (2, 13):
                color = GOLD[1]
            elif y == 10:
                color = GOLD[4]
            else:
                color = GOLD[2]
            px(img, x, y, color)
    for cx in (3, 7, 12):
        px(img, cx, 9, GOLD[2])
        px(img, cx, 8, GOLD[2])
        px(img, cx, 7, GOLD[4])
        px(img, cx, 6, jewel_pal[3])
        px(img, cx, 5, jewel_pal[4])
    for cx in (5, 9):
        px(img, cx, 9, GOLD[1])
        px(img, cx, 8, GOLD[2])
    px(img, 5, 11, jewel_pal[2])
    px(img, 8, 11, jewel_pal[3])
    px(img, 11, 11, jewel_pal[2])
    return img


def tex_bottlecap(pal) -> Image.Image:
    """Eternal bottlecap: crimped disc with a star emboss."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 7.5)
            if d <= 5.8:
                if d > 4.8:
                    c = pal[1] if (x * 3 + y * 5) % 2 else pal[0]  # crimped rim
                elif d > 3.8:
                    c = pal[2]
                else:
                    c = pal[3]
                px(img, x, y, c)
    for x, y in [(7, 5), (8, 5), (6, 7), (9, 7), (7, 9), (8, 9), (7, 7), (8, 7)]:
        px(img, x, y, pal[4])  # star emboss
    px(img, 5, 4, pal[4])
    return img


def tex_halo(pal) -> Image.Image:
    """Seraph halo: floating luminous ring, tilted."""
    img = blank()
    for t in range(120):
        a = t * math.pi / 60.0
        x = int(round(7.5 + 5.2 * math.cos(a)))
        y = int(round(6.5 + 2.6 * math.sin(a)))
        px(img, x, y, pal[3])
        px(img, x, y + 1, pal[2])
    for x, y in [(3, 5), (12, 5), (7, 3), (8, 9)]:
        px(img, x, y, pal[4])
    for x, y in [(5, 12), (8, 13), (11, 12)]:  # falling light motes
        px(img, x, y, pal[4], 140)
    return img


def tex_scepter(pal) -> Image.Image:
    """Kiln scepter: diagonal rod crowned with a molten head."""
    img = blank()
    for i in range(9):  # rod
        px(img, 3 + i, 12 - i, pal[1])
        px(img, 4 + i, 12 - i, pal[0])
    for y in range(2, 6):  # molten head
        for x in range(10, 14):
            d = math.hypot(x - 11.5, y - 3.5)
            if d <= 2.0:
                px(img, x, y, pal[4] if d < 1.0 else pal[3])
    px(img, 10, 5, pal[2])
    px(img, 13, 2, pal[4])
    px(img, 2, 13, pal[0])
    return img


def tex_idol(pal) -> Image.Image:
    """Voidstone idol: squat carved figure with a glowing gaze."""
    img = blank()
    for y in range(2, 6):  # head
        for x in range(6, 10):
            px(img, x, y, pal[1] if x in (6, 9) or y in (2, 5) else pal[2])
    for y in range(6, 13):  # body
        for x in range(4, 12):
            if x in (4, 11) or y == 12:
                c = pal[0]
            elif (x * 3 + y * 5) % 7 == 0:
                c = pal[3]
            else:
                c = pal[1]
            px(img, x, y, c)
    px(img, 7, 3, pal[4])  # the gaze
    px(img, 8, 3, pal[4])
    for x in (3, 12):  # stub arms
        px(img, x, 7, pal[1])
        px(img, x, 8, pal[0])
    return img


def tex_masterwork(pal) -> Image.Image:
    """Smith's masterwork: an ornate hammer, head aglow with finishing heat."""
    img = blank()
    for y in range(3, 7):  # hammer head
        for x in range(4, 12):
            if y in (3, 6) or x in (4, 11):
                c = pal[1]
            else:
                c = pal[3]
            px(img, x, y, c)
    px(img, 5, 4, pal[4])
    px(img, 10, 5, KILN[3])  # finishing heat
    for y in range(7, 14):  # haft
        px(img, 7, y, pal[2])
        px(img, 8, y, pal[1])
    px(img, 7, 13, pal[0])
    px(img, 8, 13, pal[0])
    return img


TEXTURES = {
    "dr_doompepper_sigil": lambda: tex_sigil(SMITH, DOOMPEPPER),
    "the_carbonated_one_core": lambda: tex_core_orb(FIZZ, SMITH),
    "soda_seraph_sigil": lambda: tex_sigil(CREAM, GOLD),
    "kiln_archon_core": lambda: tex_core_orb(KILN, SMITH),
    "voidstone_behemoth_core": lambda: tex_core_orb(VOID, SMITH),
    "the_last_smith_sigil": lambda: tex_sigil(SMITH, KILN),
    "doom_syrup": lambda: tex_bottle(DOOMPEPPER),
    "doompepper_heart": lambda: tex_heart(DOOMPEPPER),
    "carbonation_crystal": lambda: tex_crystal(FIZZ),
    "pressurized_gel": lambda: tex_gel(FIZZ),
    "seraph_plume": lambda: tex_plume(CREAM),
    "sugar_essence": lambda: tex_essence(CREAM),
    "archon_cinder": lambda: tex_cinder(KILN),
    "molten_flux": lambda: tex_flux(KILN),
    "behemoth_plate": lambda: tex_plate(VOID),
    "singularity_pearl": lambda: tex_pearl(VOID),
    "forged_scrap": lambda: tex_scrap(SMITH),
    "master_blueprint": lambda: tex_blueprint(VOID),
    "doompepper_crown": lambda: tex_crown(GOLD, DOOMPEPPER),
    "eternal_bottlecap": lambda: tex_bottlecap(FIZZ),
    "seraph_halo": lambda: tex_halo(CREAM),
    "kiln_scepter": lambda: tex_scepter(KILN),
    "voidstone_idol": lambda: tex_idol(VOID),
    "smiths_masterwork": lambda: tex_masterwork(SMITH),
}


def emit_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in TEXTURES.items():
        fn().save(item_dir / f"{name}.png")
    for boss in BOSSES:
        egg_for(boss).save(item_dir / f"{boss}_spawn_egg.png")

    entity_dir = ASSETS / "textures" / "entity"
    entity_dir.mkdir(parents=True, exist_ok=True)
    for boss, (vanilla_path, palette, _drops) in BOSSES.items():
        recolor(extract_vanilla(vanilla_path), palette).save(entity_dir / f"{boss}.png")


# ---------------------------------------------------------------------------
# Item model-definitions + models
# ---------------------------------------------------------------------------


def emit_item_assets() -> None:
    for item_id in ITEM_IDS:
        write_json(ASSETS / "items" / f"{item_id}.json", item_def(f"{NS}:item/{item_id}"))
        write_json(ASSETS / "models" / "item" / f"{item_id}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"{NS}:item/{item_id}"},
        })


# ---------------------------------------------------------------------------
# Entity loot tables (infernoboss schema incl. "random_sequence"; one pool per
# drop, 3 pools per boss: 2 drops + 1 EPIC trophy)
# ---------------------------------------------------------------------------


def emit_loot_tables() -> None:
    for boss_id, (_tex, _pal, drops) in BOSSES.items():
        write_json(DATA / "loot_table" / "entities" / f"{boss_id}.json", {
            "type": "minecraft:entity",
            "pools": [
                {
                    "bonus_rolls": 0.0,
                    "entries": [
                        {
                            "type": "minecraft:item",
                            "functions": [
                                {
                                    "add": False,
                                    "count": {
                                        "type": "minecraft:uniform",
                                        "max": cmax,
                                        "min": cmin,
                                    },
                                    "function": "minecraft:set_count",
                                }
                            ],
                            "name": drop_id,
                        }
                    ],
                    "rolls": 1.0,
                }
                for drop_id, cmin, cmax in drops
            ],
            "random_sequence": f"{NS}:entities/{boss_id}",
        })


# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 crafting_shaped format; every input set contains at
# least one copper_inferno item so the canonical input set cannot collide with
# vanilla; mod-vs-mod uniqueness verified via check_recipe_collisions.py).
# These grids are mirrored 1:1 by the handbook entries in BossDoomFeature.
# ---------------------------------------------------------------------------


def emit_recipes() -> None:
    m = lambda p: f"{NS}:{p}"
    rdir = DATA / "recipe" / "bossdoom"

    def shaped(name: str, key: dict, pattern: list, result_id: str) -> None:
        write_json(rdir / f"{name}.json", {
            "type": "minecraft:crafting_shaped",
            "category": "misc",
            "key": key,
            "pattern": pattern,
            "result": {"count": 1, "id": result_id},
        })

    # 4 Dr.Pepper + 4 iron ingots ringing a wither skeleton skull.
    shaped("dr_doompepper_sigil",
           {"P": m("dr_pepper"), "I": "minecraft:iron_ingot", "S": "minecraft:wither_skeleton_skull"},
           ["PIP", "ISI", "PIP"], m("dr_doompepper_sigil"))
    # 4 sugar + 4 slime balls ringing a Dr.Pepper.
    shaped("the_carbonated_one_core",
           {"S": "minecraft:sugar", "G": "minecraft:slime_ball", "D": m("dr_pepper")},
           ["SGS", "GDG", "SGS"], m("the_carbonated_one_core"))
    # 4 phantom membranes + 4 sugar ringing a Dr.Pepper.
    shaped("soda_seraph_sigil",
           {"M": "minecraft:phantom_membrane", "S": "minecraft:sugar", "D": m("dr_pepper")},
           ["MSM", "SDS", "MSM"], m("soda_seraph_sigil"))
    # 4 blaze rods + 4 copper ingots ringing an inferno powder (materials feature, v2).
    shaped("kiln_archon_core",
           {"B": "minecraft:blaze_rod", "C": "minecraft:copper_ingot", "P": m("inferno_powder")},
           ["BCB", "CPC", "BCB"], m("kiln_archon_core"))
    # 4 voidstone (depthstone feature, v4) + 4 obsidian ringing an ender pearl.
    shaped("voidstone_behemoth_core",
           {"V": m("voidstone"), "O": "minecraft:obsidian", "E": "minecraft:ender_pearl"},
           ["VOV", "OEO", "VOV"], m("voidstone_behemoth_core"))
    # 4 infernium ingots (infernium feature, v3) + 4 iron ingots ringing an anvil.
    shaped("the_last_smith_sigil",
           {"I": m("infernium_ingot"), "H": "minecraft:iron_ingot", "A": "minecraft:anvil"},
           ["IHI", "HAH", "IHI"], m("the_last_smith_sigil"))


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    emit_textures()
    emit_item_assets()
    emit_loot_tables()
    emit_recipes()
    emit_lang()
    print(f"bossdoom_gen: assets for {len(BOSSES)} bosses / {len(ITEM_IDS)} items generated "
          f"({len(BOSSES)} entity textures, {len(ITEM_IDS)} item textures, "
          f"{len(BOSSES)} loot tables, {len(SUMMON_ITEMS)} recipes, lang EN+DE).")


if __name__ == "__main__":
    main()
