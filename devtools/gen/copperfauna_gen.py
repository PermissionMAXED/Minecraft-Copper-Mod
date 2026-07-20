#!/usr/bin/env python3
"""Asset generator for the v4 "Copper Fauna" feature (WP8): 24 Overworld mobs, 36 items.

Idempotent: running it any number of times produces byte-identical files. Emits, directly
into src/main/resources:
  - 24 entity textures      assets/copper_inferno/textures/entity/<mob>.png
                            (lib_gen.extract_vanilla of the base mob's vanilla texture,
                            remapped onto the mob's palette via lib_gen.recolor)
  - 36 item textures        assets/copper_inferno/textures/item/<id>.png (16x16, Pillow,
                            seeded: 24 spawn eggs + 12 mob drops)
  - item model-definitions  assets/copper_inferno/items/<id>.json (vanilla 1.21.9 format)
  - item models             assets/copper_inferno/models/item/<id>.json (item/generated)
  - entity loot tables      data/copper_inferno/loot_table/entities/<mob>.json
                            (infernomobs schema incl. "random_sequence")
  - recipes                 data/copper_inferno/recipe/copperfauna/*.json — one per drop
                            item, drop -> useful goods; every input set contains one of THIS
                            feature's drop items, so the canonical input set is unique
                            (collision-checked via devtools/check_recipe_collisions.py)
  - lang fragments          assets/copper_inferno/lang/fragments/copperfauna.json (EN)
                            assets/copper_inferno/lang/fragments_de/copperfauna.json (DE)

Mob roster + drop pairing (one drop item per TWO mobs, 12 drops):
  copper_chitin    copper_beetle,    statue_mite
  verdigris_gel    verdigris_slime,  verdigris_frog
  patina_membrane  patina_bat,       scrap_phantom
  spark_tuft       spark_hare,       spark_fox
  lode_hide        lode_boar,        lode_cow
  gilded_feather   gilded_finch,     coil_chicken
  live_wire        coil_spider,      conductor_creeper
  rust_fang        rust_wolf,        gutter_cat
  tarnish_dust     tarnish_witch,    oxidized_zombie
  storm_cell       ampere_bee,       thunder_goat
  patina_bone      patina_skeleton,  copper_golemite
  burnished_coin   patina_sheep,     coin_ocelot
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

COPPER = [(0x3A, 0x22, 0x18), (0x7A, 0x43, 0x2B), (0xB4, 0x68, 0x4D), (0xE7, 0x9C, 0x6F), (0xFF, 0xD0, 0xA8)]
VERDIGRIS = [(0x14, 0x33, 0x2B), (0x2B, 0x63, 0x50), (0x43, 0xA4, 0x7E), (0x76, 0xC6, 0xA3), (0xC1, 0xEC, 0xDA)]
PATINA = [(0x14, 0x3C, 0x40), (0x2C, 0x6E, 0x6E), (0x4F, 0xAB, 0x9C), (0x86, 0xD1, 0xBF), (0xC8, 0xF0, 0xE4)]
GOLD = [(0x4A, 0x33, 0x0E), (0x8F, 0x66, 0x18), (0xD2, 0x9E, 0x2C), (0xF0, 0xC6, 0x4A), (0xFF, 0xEA, 0x9E)]
RUST = [(0x33, 0x18, 0x10), (0x6B, 0x32, 0x1E), (0x9C, 0x4A, 0x28), (0xC9, 0x6E, 0x3F), (0xE8, 0xA8, 0x74)]
LODE = [(0x1C, 0x16, 0x14), (0x3C, 0x30, 0x2A), (0x5E, 0x4C, 0x40), (0x84, 0x6C, 0x58), (0xB0, 0x96, 0x7C)]
SPARK = [(0x4A, 0x1E, 0x0C), (0x8F, 0x3E, 0x16), (0xD2, 0x66, 0x22), (0xF2, 0x94, 0x3C), (0xFF, 0xCC, 0x70)]
STORM = [(0x16, 0x1A, 0x24), (0x30, 0x38, 0x4C), (0x50, 0x5C, 0x74), (0x7C, 0x88, 0xA0), (0xB4, 0xBE, 0xD2)]
TARNISH = [(0x1A, 0x20, 0x18), (0x38, 0x42, 0x34), (0x5A, 0x66, 0x52), (0x84, 0x8E, 0x76), (0xB6, 0xBC, 0xA2)]
GUTTER = [(0x14, 0x14, 0x16), (0x30, 0x30, 0x34), (0x50, 0x50, 0x56), (0x78, 0x78, 0x80), (0xA8, 0xA8, 0xB2)]

# ---------------------------------------------------------------------------
# Roster: mob id -> (vanilla entity texture in the client jar, recolor palette,
#                    (drop item id, loot min, loot max))
# ---------------------------------------------------------------------------

E = "assets/minecraft/textures/entity"

MOBS = {
    "copper_beetle": (f"{E}/silverfish.png", COPPER, (f"{NS}:copper_chitin", 0.0, 2.0)),
    "verdigris_slime": (f"{E}/slime/slime.png", VERDIGRIS, (f"{NS}:verdigris_gel", 0.0, 2.0)),
    "patina_bat": (f"{E}/bat.png", PATINA, (f"{NS}:patina_membrane", 0.0, 1.0)),
    "spark_hare": (f"{E}/rabbit/brown.png", SPARK, (f"{NS}:spark_tuft", 0.0, 2.0)),
    "lode_boar": (f"{E}/pig/temperate_pig.png", LODE, (f"{NS}:lode_hide", 1.0, 2.0)),
    "gilded_finch": (f"{E}/parrot/parrot_red_blue.png", GOLD, (f"{NS}:gilded_feather", 0.0, 2.0)),
    "coil_spider": (f"{E}/spider/spider.png", COPPER, (f"{NS}:live_wire", 0.0, 2.0)),
    "rust_wolf": (f"{E}/wolf/wolf.png", RUST, (f"{NS}:rust_fang", 0.0, 2.0)),
    "statue_mite": (f"{E}/endermite.png", PATINA, (f"{NS}:copper_chitin", 0.0, 2.0)),
    "tarnish_witch": (f"{E}/witch.png", TARNISH, (f"{NS}:tarnish_dust", 1.0, 3.0)),
    "conductor_creeper": (f"{E}/creeper/creeper.png", COPPER, (f"{NS}:live_wire", 0.0, 2.0)),
    "ampere_bee": (f"{E}/bee/bee.png", SPARK, (f"{NS}:storm_cell", 0.0, 1.0)),
    "oxidized_zombie": (f"{E}/zombie/zombie.png", VERDIGRIS, (f"{NS}:tarnish_dust", 0.0, 2.0)),
    "patina_skeleton": (f"{E}/skeleton/skeleton.png", PATINA, (f"{NS}:patina_bone", 0.0, 2.0)),
    "copper_golemite": (f"{E}/snow_golem.png", COPPER, (f"{NS}:patina_bone", 0.0, 1.0)),
    "spark_fox": (f"{E}/fox/fox.png", SPARK, (f"{NS}:spark_tuft", 0.0, 2.0)),
    "verdigris_frog": (f"{E}/frog/temperate_frog.png", VERDIGRIS, (f"{NS}:verdigris_gel", 0.0, 1.0)),
    "coil_chicken": (f"{E}/chicken/temperate_chicken.png", COPPER, (f"{NS}:gilded_feather", 0.0, 2.0)),
    "lode_cow": (f"{E}/cow/temperate_cow.png", LODE, (f"{NS}:lode_hide", 1.0, 2.0)),
    "patina_sheep": (f"{E}/sheep/sheep.png", PATINA, (f"{NS}:burnished_coin", 0.0, 1.0)),
    "gutter_cat": (f"{E}/cat/tabby.png", GUTTER, (f"{NS}:rust_fang", 0.0, 1.0)),
    "thunder_goat": (f"{E}/goat/goat.png", STORM, (f"{NS}:storm_cell", 0.0, 2.0)),
    "scrap_phantom": (f"{E}/phantom.png", RUST, (f"{NS}:patina_membrane", 0.0, 2.0)),
    "coin_ocelot": (f"{E}/cat/ocelot.png", GOLD, (f"{NS}:burnished_coin", 0.0, 2.0)),
}

DROPS = [
    "copper_chitin", "verdigris_gel", "patina_membrane", "spark_tuft",
    "lode_hide", "gilded_feather", "live_wire", "rust_fang",
    "tarnish_dust", "storm_cell", "patina_bone", "burnished_coin",
]

ITEM_IDS = [f"{mob}_spawn_egg" for mob in MOBS] + DROPS

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

MOB_NAMES = {
    "copper_beetle": ("Copper Beetle", "Kupferkäfer"),
    "verdigris_slime": ("Verdigris Slime", "Grünspanschleim"),
    "patina_bat": ("Patina Bat", "Patina-Fledermaus"),
    "spark_hare": ("Spark Hare", "Funkenhase"),
    "lode_boar": ("Lode Boar", "Erzkeiler"),
    "gilded_finch": ("Gilded Finch", "Vergoldeter Fink"),
    "coil_spider": ("Coil Spider", "Spulenspinne"),
    "rust_wolf": ("Rust Wolf", "Rostwolf"),
    "statue_mite": ("Statue Mite", "Statuenmilbe"),
    "tarnish_witch": ("Tarnish Witch", "Anlaufhexe"),
    "conductor_creeper": ("Conductor Creeper", "Leiter-Creeper"),
    "ampere_bee": ("Ampere Bee", "Ampere-Biene"),
    "oxidized_zombie": ("Oxidized Zombie", "Oxidierter Zombie"),
    "patina_skeleton": ("Patina Skeleton", "Patina-Skelett"),
    "copper_golemite": ("Copper Golemite", "Kupfergolemit"),
    "spark_fox": ("Spark Fox", "Funkenfuchs"),
    "verdigris_frog": ("Verdigris Frog", "Grünspanfrosch"),
    "coil_chicken": ("Coil Chicken", "Spulenhuhn"),
    "lode_cow": ("Lode Cow", "Erzkuh"),
    "patina_sheep": ("Patina Sheep", "Patina-Schaf"),
    "gutter_cat": ("Gutter Cat", "Gossenkatze"),
    "thunder_goat": ("Thunder Goat", "Donnerziege"),
    "scrap_phantom": ("Scrap Phantom", "Schrottphantom"),
    "coin_ocelot": ("Coin Ocelot", "Münzozelot"),
}

DROP_NAMES = {
    "copper_chitin": ("Copper Chitin", "Kupferchitin"),
    "verdigris_gel": ("Verdigris Gel", "Grünspangel"),
    "patina_membrane": ("Patina Membrane", "Patinamembran"),
    "spark_tuft": ("Spark Tuft", "Funkenbüschel"),
    "lode_hide": ("Lode Hide", "Erzhaut"),
    "gilded_feather": ("Gilded Feather", "Vergoldete Feder"),
    "live_wire": ("Live Wire", "Stromdraht"),
    "rust_fang": ("Rust Fang", "Rostzahn"),
    "tarnish_dust": ("Tarnish Dust", "Anlaufstaub"),
    "storm_cell": ("Storm Cell", "Sturmzelle"),
    "patina_bone": ("Patina Bone", "Patinaknochen"),
    "burnished_coin": ("Burnished Coin", "Polierte Münze"),
}


def emit_lang() -> None:
    en, de = {}, {}
    for mob, (name_en, name_de) in MOB_NAMES.items():
        en[f"entity.{NS}.{mob}"] = name_en
        de[f"entity.{NS}.{mob}"] = name_de
        en[f"item.{NS}.{mob}_spawn_egg"] = f"{name_en} Spawn Egg"
        de[f"item.{NS}.{mob}_spawn_egg"] = f"{name_de}-Spawn-Ei"
    for drop, (name_en, name_de) in DROP_NAMES.items():
        en[f"item.{NS}.{drop}"] = name_en
        de[f"item.{NS}.{drop}"] = name_de
    write_json(ASSETS / "lang" / "fragments" / "copperfauna.json", en)
    write_json(ASSETS / "lang" / "fragments_de" / "copperfauna.json", de)


# ---------------------------------------------------------------------------
# Texture helpers (16x16 item sprites)
# ---------------------------------------------------------------------------


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def spawn_egg(rng: Random, base, base_dark, base_light, spots, outline) -> Image.Image:
    """Classic spawn-egg silhouette with seeded speckles (infernomobs_gen painter)."""
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


def egg_for(mob: str) -> Image.Image:
    """Egg colors derived from the mob's entity palette: body = mid tones, spots = the
    palette's brightest stop, outline = its darkest stop."""
    pal = MOBS[mob][1]
    rng = Random(f"{NS}:copperfauna:{mob}_spawn_egg")
    return spawn_egg(rng, pal[2], pal[1], pal[3], pal[4], pal[0])


# --------------------------- drop item painters ----------------------------


def tex_copper_chitin(rng: Random) -> Image.Image:
    """Hexagonal chitin plate with a lit facet and rivet dents."""
    img = blank()
    half = {3: 2.0, 4: 3.5, 5: 4.5, 6: 5.0, 7: 5.5, 8: 5.5, 9: 5.0, 10: 4.5, 11: 3.5, 12: 2.0}
    for y, h in half.items():
        x0, x1 = int(math.ceil(7.5 - h)), int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (3, 12):
                c = COPPER[0]
            elif x <= 6 and y <= 7:
                c = COPPER[3]
            else:
                c = COPPER[2]
            px(img, x, y, c)
    for x, y in [(6, 6), (9, 8), (7, 10)]:
        px(img, x, y, COPPER[1])
    px(img, 5, 5, COPPER[4])
    return img


def tex_verdigris_gel(rng: Random) -> Image.Image:
    """Wobbly translucent gel blob with a bright core."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 8.0)
            if d <= 5.4:
                if d > 4.4:
                    px(img, x, y, VERDIGRIS[1], 220)
                elif d > 2.4:
                    px(img, x, y, VERDIGRIS[2], 235)
                else:
                    px(img, x, y, VERDIGRIS[3])
    px(img, 6, 6, VERDIGRIS[4])
    px(img, 7, 6, VERDIGRIS[4])
    px(img, 6, 7, VERDIGRIS[4])
    for x, y in [(4, 11), (11, 10), (9, 12)]:
        px(img, x, y, VERDIGRIS[1])
    return img


def tex_patina_membrane(rng: Random) -> Image.Image:
    """Stretched wing membrane between two bone spars."""
    img = blank()
    for i in range(11):
        px(img, 2 + i, 3 + i // 3, PATINA[0])     # upper spar
        px(img, 2, 4 + i, PATINA[0] if i < 9 else PATINA[1])  # left spar
    for y in range(4, 14):
        for x in range(3, 3 + (14 - y)):
            if x < 14:
                c = PATINA[2] if (x + y) % 3 else PATINA[3]
                px(img, x, y, c, 235)
    for i in range(4):
        px(img, 4 + 2 * i, 5 + 2 * i, PATINA[1])  # fold crease
    return img


def tex_spark_tuft(rng: Random) -> Image.Image:
    """Fluffy static-charged fur tuft with spark pixels."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 8.5)
            if d <= 4.6:
                c = SPARK[3] if d < 2.4 else (SPARK[2] if (x * 3 + y * 5) % 4 else SPARK[1])
                px(img, x, y, c)
    for ang in range(8):  # wispy fur strands
        a = ang * math.pi / 4 + 0.3
        x = int(7.5 + math.cos(a) * 5.6)
        y = int(8.5 + math.sin(a) * 5.6)
        px(img, x, y, SPARK[2])
    for x, y in [(3, 3), (12, 4), (11, 12), (7, 2)]:
        px(img, x, y, SPARK[4])  # static sparks
    return img


def tex_lode_hide(rng: Random) -> Image.Image:
    """Heavy square pelt with stitched edges."""
    img = blank()
    for y in range(3, 14):
        for x in range(2, 14):
            if x in (2, 13) or y in (3, 13):
                c = LODE[0]
            elif (x + y) % 5 == 0:
                c = LODE[1]
            else:
                c = LODE[2] if (x * 7 + y * 3) % 3 else LODE[3]
            px(img, x, y, c)
    for i in range(4):  # stitches
        px(img, 4 + 3 * i, 3, LODE[4])
        px(img, 4 + 3 * i, 13, LODE[4])
    return img


def tex_gilded_feather(rng: Random) -> Image.Image:
    """Curved gilded feather with a dark quill."""
    img = blank()
    body = {2: (10, 12), 3: (9, 12), 4: (8, 12), 5: (7, 11), 6: (6, 10), 7: (5, 9),
            8: (4, 8), 9: (3, 7), 10: (3, 6), 11: (2, 5), 12: (2, 4)}
    for y, (x0, x1) in body.items():
        for x in range(x0, x1 + 1):
            if x == x0 or y == 2:
                c = GOLD[4]
            elif x == x1:
                c = GOLD[1]
            else:
                c = GOLD[3] if (x + y) % 2 else GOLD[2]
            px(img, x, y, c)
    for i in range(4):  # quill
        px(img, 2 + i // 2, 13 - i // 4, GOLD[0])
    px(img, 1, 14, GOLD[0])
    return img


def tex_live_wire(rng: Random) -> Image.Image:
    """Coiled copper wire, ends sparking."""
    img = blank()
    for t in range(40):
        a = t * 0.55
        x = int(7.5 + (2.0 + t * 0.07) * math.cos(a))
        y = int(7.5 + (2.0 + t * 0.07) * math.sin(a))
        px(img, x, y, COPPER[2] if t % 3 else COPPER[3])
    px(img, 13, 3, GOLD[4])
    px(img, 14, 2, GOLD[4])
    px(img, 2, 12, GOLD[4])
    px(img, 7, 7, COPPER[1])
    px(img, 8, 8, COPPER[1])
    return img


def tex_rust_fang(rng: Random) -> Image.Image:
    """Curved fang, rust-stained at the root (crawler_fang geometry, rust palette)."""
    img = blank()
    for y in (2, 3, 4):
        for x in range(3, 9):
            px(img, x, y, RUST[1] if y == 2 or x == 3 else RUST[2])
    body = {5: (4, 9), 6: (5, 9), 7: (6, 10), 8: (7, 10), 9: (8, 11), 10: (9, 11), 11: (10, 12), 12: (11, 12)}
    for y, (x0, x1) in body.items():
        for x in range(x0, x1 + 1):
            if x == x0:
                c = RUST[4]
            elif x == x1:
                c = RUST[1]
            else:
                c = RUST[3]
            px(img, x, y, c)
    px(img, 12, 13, RUST[0])
    px(img, 5, 3, RUST[3])
    return img


def tex_tarnish_dust(rng: Random) -> Image.Image:
    """Heaped pile of murky tarnish dust with drifting motes."""
    img = blank()
    half = {8: 2.0, 9: 3.2, 10: 4.2, 11: 5.0, 12: 5.6, 13: 6.0}
    for y, h in half.items():
        x0, x1 = int(math.ceil(7.5 - h)), int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y == 13:
                c = TARNISH[0]
            else:
                c = TARNISH[2] if (x * 5 + y * 3) % 4 else TARNISH[1]
            px(img, x, y, c)
    for x, y in [(5, 9), (9, 10), (7, 11)]:
        px(img, x, y, TARNISH[3])
    for x, y in [(4, 5), (10, 4), (7, 6), (12, 6)]:
        px(img, x, y, TARNISH[3], 140)  # motes
    return img


def tex_storm_cell(rng: Random) -> Image.Image:
    """Charged canister: slate body, copper caps, lightning glyph."""
    img = blank()
    for y in range(3, 14):
        for x in range(5, 11):
            if y in (3, 13):
                c = COPPER[2]
            elif y in (4, 12):
                c = COPPER[1]
            elif x in (5, 10):
                c = STORM[0]
            else:
                c = STORM[1] if (x + y) % 2 else STORM[2]
            px(img, x, y, c)
    for x, y in [(8, 5), (7, 6), (8, 7), (7, 8), (8, 9), (7, 10)]:  # bolt
        px(img, x, y, GOLD[4])
    px(img, 6, 5, STORM[3])
    return img


def tex_patina_bone(rng: Random) -> Image.Image:
    """Diagonal bone with knobbed ends, patina-stained."""
    img = blank()
    for i in range(8):  # shaft from (4,11) up to (11,4)
        x, y = 4 + i, 11 - i
        px(img, x, y, PATINA[3])
        px(img, x + 1, y, PATINA[2])
        px(img, x, y + 1, PATINA[1])
    for dx, dy in [(0, 0), (1, 0), (0, 1), (1, 1), (-1, 1), (1, -1)]:
        px(img, 3 + dx, 12 + dy, PATINA[3])   # lower knob
        px(img, 12 + dx, 3 + dy, PATINA[3])   # upper knob
    px(img, 2, 13, PATINA[1])
    px(img, 13, 2, PATINA[4])
    for x, y in [(6, 9), (9, 6)]:
        px(img, x, y, PATINA[0])  # patina stains
    return img


def tex_burnished_coin(rng: Random) -> Image.Image:
    """Burnished round coin with an embossed rim and mint lustre."""
    img = blank()
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - 7.5, y - 7.5)
            if d <= 5.4:
                if d > 4.5:
                    c = GOLD[1]
                elif d > 3.6:
                    c = GOLD[2]
                elif abs(x - 7.5) < 1.5 and 4 <= y <= 11:
                    c = GOLD[1]  # embossed bar
                else:
                    c = GOLD[3]
                px(img, x, y, c)
    px(img, 5, 4, GOLD[4])
    px(img, 4, 5, GOLD[4])
    px(img, 10, 11, GOLD[0])
    return img


DROP_TEXTURES = {
    "copper_chitin": tex_copper_chitin,
    "verdigris_gel": tex_verdigris_gel,
    "patina_membrane": tex_patina_membrane,
    "spark_tuft": tex_spark_tuft,
    "lode_hide": tex_lode_hide,
    "gilded_feather": tex_gilded_feather,
    "live_wire": tex_live_wire,
    "rust_fang": tex_rust_fang,
    "tarnish_dust": tex_tarnish_dust,
    "storm_cell": tex_storm_cell,
    "patina_bone": tex_patina_bone,
    "burnished_coin": tex_burnished_coin,
}


def emit_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    for mob in MOBS:
        egg_for(mob).save(item_dir / f"{mob}_spawn_egg.png")
    for name, fn in DROP_TEXTURES.items():
        fn(Random(f"{NS}:copperfauna:{name}")).save(item_dir / f"{name}.png")

    entity_dir = ASSETS / "textures" / "entity"
    entity_dir.mkdir(parents=True, exist_ok=True)
    for mob, (vanilla_path, palette, _drop) in MOBS.items():
        recolor(extract_vanilla(vanilla_path), palette).save(entity_dir / f"{mob}.png")


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
# Entity loot tables (infernomobs schema incl. "random_sequence")
# ---------------------------------------------------------------------------


def emit_loot_tables() -> None:
    for mob_id, (_tex, _pal, (drop_id, cmin, cmax)) in MOBS.items():
        write_json(DATA / "loot_table" / "entities" / f"{mob_id}.json", {
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
            ],
            "random_sequence": f"{NS}:entities/{mob_id}",
        })


# ---------------------------------------------------------------------------
# Recipes: one per drop item, drop -> useful goods. Every input set contains one
# of THIS feature's drop items (unique-input rule), so no canonical input set can
# collide with vanilla or with other features' crafting recipes
# (verified via devtools/check_recipe_collisions.py).
# ---------------------------------------------------------------------------


def emit_recipes() -> None:
    rdir = DATA / "recipe" / "copperfauna"

    def shapeless(name: str, ingredients: list, result_id: str, count: int) -> None:
        write_json(rdir / f"{name}.json", {
            "type": "minecraft:crafting_shapeless",
            "category": "misc",
            "ingredients": ingredients,
            "result": {"count": count, "id": result_id},
        })

    m = lambda p: f"{NS}:{p}"
    # Two chitin plates hammer into a copper ingot.
    shapeless("copper_ingot_from_copper_chitin", [m("copper_chitin"), m("copper_chitin")],
              "minecraft:copper_ingot", 1)
    # Verdigris gel wobbles apart into two slime balls.
    shapeless("slime_ball_from_verdigris_gel", [m("verdigris_gel")], "minecraft:slime_ball", 2)
    # Two patina membranes cure into a phantom membrane.
    shapeless("phantom_membrane_from_patina_membrane", [m("patina_membrane"), m("patina_membrane")],
              "minecraft:phantom_membrane", 1)
    # Two spark tufts spin into three string.
    shapeless("string_from_spark_tuft", [m("spark_tuft"), m("spark_tuft")], "minecraft:string", 3)
    # Two heavy lode hides tan into three leather.
    shapeless("leather_from_lode_hide", [m("lode_hide"), m("lode_hide")], "minecraft:leather", 3)
    # A gilded feather is plucked apart into two plain feathers.
    shapeless("feather_from_gilded_feather", [m("gilded_feather")], "minecraft:feather", 2)
    # Two live wires ground down into four redstone.
    shapeless("redstone_from_live_wire", [m("live_wire"), m("live_wire")], "minecraft:redstone", 4)
    # A rust fang grinds into three bone meal.
    shapeless("bone_meal_from_rust_fang", [m("rust_fang")], "minecraft:bone_meal", 3)
    # Two tarnish dusts refine into two gunpowder.
    shapeless("gunpowder_from_tarnish_dust", [m("tarnish_dust"), m("tarnish_dust")],
              "minecraft:gunpowder", 2)
    # A storm cell discharges into two glowstone dust.
    shapeless("glowstone_dust_from_storm_cell", [m("storm_cell")], "minecraft:glowstone_dust", 2)
    # A patina bone scrubs up into two bones.
    shapeless("bone_from_patina_bone", [m("patina_bone")], "minecraft:bone", 2)
    # Four burnished coins melt back into two copper ingots.
    shapeless("copper_ingot_from_burnished_coin",
              [m("burnished_coin"), m("burnished_coin"), m("burnished_coin"), m("burnished_coin")],
              "minecraft:copper_ingot", 2)


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    emit_textures()
    emit_item_assets()
    emit_loot_tables()
    emit_recipes()
    emit_lang()
    print(f"copperfauna_gen: assets for {len(MOBS)} mobs / {len(ITEM_IDS)} items generated "
          f"({len(MOBS)} entity textures, {len(ITEM_IDS)} item textures, "
          f"{len(MOBS)} loot tables, 12 recipes, lang EN+DE).")


if __name__ == "__main__":
    main()
