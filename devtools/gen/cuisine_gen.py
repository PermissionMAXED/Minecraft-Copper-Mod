#!/usr/bin/env python3
"""Asset generator for the v4 "Inferno Cuisine & Brews" feature (WP7).

70 standalone consumables registered by feature/cuisine/CuisineFeature.java:
10 kitchen ingredients + 40 foods + 20 bottled fizz brews.

Emits, directly into src/main/resources (all deterministic, idempotent — run any
number of times, same bytes):
  - assets/copper_inferno/items/<id>.json           (1.21.9 item model definition)
  - assets/copper_inferno/models/item/<id>.json     (item/generated + layer0)
  - assets/copper_inferno/textures/item/<id>.png    (deterministic 16x16 Pillow art)
  - data/copper_inferno/recipe/cuisine/<name>.json  (crafting + smelting/smoking)
  - assets/copper_inferno/lang/fragments/cuisine.json    (EN, incl. effect keys)
  - assets/copper_inferno/lang/fragments_de/cuisine.json (DE, real German)

JSON formats are verbatim copies of the shipped generators (devtools/gen/
foods_gen.py, infernofoods_gen.py, gemalloy_gen.py), which extracted them from
the vanilla 1.21.9 jars. Textures follow the infernofoods_gen painter style
(pure pixel ops, no RNG).

UNIQUE-INPUT RULE: every crafting recipe's ingredient set contains cuisine_spice
or another item registered by this feature (ember_flour, soda_sugar, cinder_salt,
magma_butter, ash_yeast, fizz_crystal, ember_oil, smoke_extract, cinder_cocoa or
a crafted cuisine food), so no collision with vanilla or other features is
possible. The cuisine_spice bootstrap itself uses copper_inferno:ember_dust (a
mod-only item), keeping it off any vanilla input set. Cooking recipes only ever
take cuisine items as input, never vanilla ones.

Usage: python3 devtools/gen/cuisine_gen.py
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import NS, item_def, write_json  # noqa: E402

from PIL import Image  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
MOD = NS

# ---------------------------------------------------------------------------
# Palette (cuisine tones: embers, ash, soda fizz, baked goods)
# ---------------------------------------------------------------------------
EMBER = (255, 148, 42)
EMBER_HOT = (255, 214, 92)
EMBER_DEEP = (198, 74, 24)
CHAR = (52, 40, 38)
ASH = (128, 122, 116)
ASH_LIGHT = (176, 170, 162)
ASH_DARK = (86, 80, 76)
GLASS = (208, 224, 232)
GLASS_HI = (244, 250, 252)
CORK = (166, 124, 72)
BOWL = (134, 88, 48)
BOWL_DARK = (100, 64, 34)
CRUST = (188, 140, 82)
CRUST_DARK = (140, 96, 52)
CRUMB = (226, 190, 130)
MEAT = (196, 92, 74)
MEAT_DARK = (142, 58, 46)
MEAT_COOKED = (158, 96, 58)
BERRY = (214, 58, 36)
GREEN = (74, 140, 60)
PINK = (240, 110, 170)
GOLD = (224, 166, 46)
COCOA = (94, 62, 40)
COCOA_DARK = (64, 40, 26)
SALT = (232, 232, 226)
CREAM = (244, 232, 202)
CHEESE = (240, 196, 84)
CHEESE_DARK = (198, 152, 48)
STICK = (110, 78, 40)

T = (0, 0, 0, 0)


def rgba(c):
    return (c[0], c[1], c[2], 255) if len(c) == 3 else c


def new_img():
    return Image.new("RGBA", (16, 16), T)


def px(img, x, y, c):
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), rgba(c))


def rect(img, x0, y0, x1, y1, c):
    for y in range(y0, y1 + 1):
        for x in range(x0, x1 + 1):
            px(img, x, y, c)


def shade(c, f):
    return tuple(max(0, min(255, int(round(v * f)))) for v in c[:3])


# ---------------------------------------------------------------------------
# Parameterized shape painters (16x16 RGBA, purely arithmetic — no RNG)
# ---------------------------------------------------------------------------


def tex_powder(base, fleck):
    """Heaped pile of ground ingredient with deterministic flecks."""
    img = new_img()
    dark = shade(base, 0.72)
    light = shade(base, 1.22)
    rect(img, 2, 11, 13, 13, base)
    rect(img, 3, 9, 12, 10, base)
    rect(img, 5, 7, 10, 8, base)
    rect(img, 7, 6, 8, 6, base)
    rect(img, 2, 13, 13, 13, dark)
    for y in range(6, 13):
        px(img, 12 - (12 - y) // 3, y, dark)
    for x in range(2, 14):
        for y in range(6, 14):
            if img.getpixel((x, y))[3] and (x * 7 + y * 13) % 11 == 0:
                px(img, x, y, fleck)
            elif img.getpixel((x, y))[3] and (x * 5 + y * 3) % 13 == 0:
                px(img, x, y, light)
    return img


def tex_bottle(liquid, bubble):
    """Corked fizz bottle: glass walls, colored brew, rising bubble streaks."""
    img = new_img()
    liq_dark = shade(liquid, 0.72)
    rect(img, 6, 0, 9, 1, CORK)
    px(img, 6, 0, shade(CORK, 0.75))
    px(img, 9, 0, shade(CORK, 0.75))
    for y in (2, 3):
        px(img, 6, y, GLASS)
        px(img, 9, y, GLASS)
        rect(img, 7, y, 8, y, liq_dark)
    px(img, 5, 4, GLASS)
    px(img, 10, 4, GLASS)
    rect(img, 6, 4, 9, 4, liquid)
    for y in range(5, 14):
        px(img, 4, y, GLASS)
        px(img, 11, y, GLASS)
        rect(img, 5, y, 10, y, liquid)
        px(img, 5, y, liq_dark)
    for x, y in ((7, 6), (9, 8), (6, 9), (8, 11), (7, 12)):
        px(img, x, y, bubble)
    px(img, 10, 5, GLASS_HI)
    px(img, 10, 6, GLASS_HI)
    rect(img, 4, 14, 11, 14, GLASS)
    rect(img, 5, 14, 10, 14, liq_dark)
    rect(img, 5, 15, 10, 15, GLASS)
    return img


def tex_bowl(soup, chunk_a, chunk_b):
    """Wooden bowl of soup with two chunk colors and heat wisps."""
    img = new_img()
    px(img, 5, 1, ASH_LIGHT)
    px(img, 10, 1, ASH_LIGHT)
    px(img, 6, 2, ASH)
    px(img, 9, 3, ASH)
    rect(img, 2, 6, 13, 6, BOWL)
    px(img, 2, 6, BOWL_DARK)
    px(img, 13, 6, BOWL_DARK)
    rect(img, 3, 5, 12, 5, soup)
    px(img, 5, 5, chunk_a)
    px(img, 9, 5, chunk_b)
    px(img, 7, 5, chunk_a)
    px(img, 11, 5, shade(soup, 1.25))
    rect(img, 3, 7, 12, 9, BOWL)
    rect(img, 4, 10, 11, 11, BOWL)
    rect(img, 5, 12, 10, 12, BOWL_DARK)
    for y in range(7, 10):
        px(img, 12, y, BOWL_DARK)
    px(img, 11, 10, BOWL_DARK)
    px(img, 11, 11, BOWL_DARK)
    rect(img, 6, 13, 9, 13, BOWL_DARK)
    return img


def tex_loaf(crust, dust, glint):
    """Rounded loaf with top dusting and score lines."""
    img = new_img()
    dark = shade(crust, 0.7)
    rect(img, 3, 5, 12, 5, crust)
    rect(img, 2, 6, 13, 10, crust)
    rect(img, 3, 11, 12, 11, dark)
    for y in range(6, 11):
        px(img, 13, y, dark)
    rect(img, 3, 5, 12, 5, dust)
    rect(img, 2, 6, 13, 6, shade(dust, 0.85))
    px(img, 5, 8, dark)
    px(img, 6, 9, dark)
    px(img, 8, 8, dark)
    px(img, 9, 9, dark)
    px(img, 11, 8, dark)
    px(img, 6, 6, glint)
    px(img, 10, 10, glint)
    return img


def tex_toast(crust, crumb, mark):
    """Square toast slice with crust border and diagonal sear marks."""
    img = new_img()
    rect(img, 3, 3, 12, 12, crumb)
    for i in range(3, 13):
        px(img, i, 3, crust)
        px(img, i, 12, crust)
        px(img, 3, i, crust)
        px(img, 12, i, crust)
    px(img, 3, 3, shade(crust, 0.8))
    px(img, 12, 3, shade(crust, 0.8))
    for d in range(6):
        px(img, 5 + d, 5 + d, mark)
        px(img, 8 + d // 2, 4 + d, shade(mark, 1.2))
    px(img, 10, 6, mark)
    px(img, 6, 10, mark)
    return img


def tex_jerky(base, dark, glint):
    """Twisted dried-meat strip."""
    img = new_img()
    strip = [(3, 3), (4, 3), (5, 4), (6, 4), (7, 5), (8, 5), (8, 6), (9, 6),
             (9, 7), (10, 7), (10, 8), (11, 8), (11, 9), (12, 9), (12, 10),
             (12, 11), (11, 11), (11, 12), (10, 12)]
    for x, y in strip:
        px(img, x, y, base)
        px(img, x + 1, y, base)
        px(img, x, y + 1, dark)
        px(img, x + 1, y + 1, dark)
    px(img, 5, 3, dark)
    px(img, 8, 4, glint)
    px(img, 10, 6, glint)
    px(img, 12, 8, dark)
    px(img, 11, 10, glint)
    return img


def tex_gem_candy(gem, sugar):
    """Candied gem: faceted diamond rolled in sugar sparkles."""
    img = new_img()
    dark = shade(gem, 0.7)
    hi = shade(gem, 1.35)
    for i, w in enumerate((1, 3, 5, 7)):
        y = 3 + i
        rect(img, 8 - w // 2 - 1, y, 8 + w // 2, y, gem)
    for i, w in enumerate((7, 5, 3, 1)):
        y = 7 + i
        rect(img, 8 - w // 2 - 1, y, 8 + w // 2, y, gem)
    for y in range(3, 11):
        for x in range(3, 13):
            if img.getpixel((x, y))[3] and x > 8:
                if (x + y) % 2 == 0:
                    px(img, x, y, dark)
    px(img, 6, 4, hi)
    px(img, 5, 5, hi)
    px(img, 6, 5, hi)
    for x, y in ((4, 3), (11, 4), (3, 8), (12, 9), (7, 11), (9, 2)):
        px(img, x, y, sugar)
    return img


def tex_ham(meat, glaze, bone):
    """Glazed ham with a bone end and shine streaks."""
    img = new_img()
    dark = shade(meat, 0.72)
    rect(img, 4, 4, 12, 4, meat)
    rect(img, 3, 5, 13, 10, meat)
    rect(img, 4, 11, 12, 11, dark)
    for y in range(5, 11):
        px(img, 13, y, dark)
    rect(img, 1, 6, 3, 8, bone)
    px(img, 1, 6, shade(bone, 0.8))
    px(img, 1, 8, shade(bone, 0.8))
    rect(img, 4, 4, 12, 4, glaze)
    rect(img, 3, 5, 13, 5, shade(glaze, 0.85))
    px(img, 6, 6, glaze)
    px(img, 9, 7, glaze)
    px(img, 5, 7, shade(meat, 1.2))
    return img


def tex_wedge(fill, crust, accent):
    """Wedge slice (pie/pizza/cake/cheese) pointing down."""
    img = new_img()
    dark = shade(crust, 0.72)
    for i, (x0, x1) in enumerate(((2, 13), (3, 12), (4, 11), (5, 11), (6, 10),
                                  (7, 9), (7, 9), (8, 8))):
        y = 4 + i
        rect(img, x0, y, x1, y, fill)
    rect(img, 2, 3, 13, 3, crust)
    rect(img, 2, 4, 13, 4, shade(crust, 0.85))
    for i in range(8):
        px(img, 2 + i, 4 + i, dark)
        px(img, 13 - i, 4 + i, dark)
    for x, y in ((6, 6), (9, 6), (7, 8), (8, 10)):
        px(img, x, y, accent)
    return img


def tex_round(base, accent, hole=False, square=False):
    """Cookie / donut / brownie / cracker."""
    img = new_img()
    dark = shade(base, 0.72)
    if square:
        rect(img, 3, 4, 12, 11, base)
        rect(img, 3, 11, 12, 11, dark)
        for y in range(4, 12):
            px(img, 12, y, dark)
    else:
        rect(img, 5, 3, 10, 3, base)
        rect(img, 4, 4, 11, 4, base)
        rect(img, 3, 5, 12, 10, base)
        rect(img, 4, 11, 11, 11, base)
        rect(img, 5, 12, 10, 12, dark)
        rect(img, 4, 11, 11, 11, dark)
        for y in range(5, 11):
            px(img, 12, y, dark)
    if hole:
        rect(img, 7, 7, 8, 8, T)
        px(img, 7, 6, dark)
        px(img, 8, 9, dark)
    for x, y in ((5, 5), (9, 5), (6, 8), (10, 8), (8, 6), (5, 9)):
        if not (hole and 6 <= x <= 9 and 6 <= y <= 9):
            px(img, x, y, accent)
    return img


def tex_pretzel(base, salt):
    """Twisted pretzel with salt grains."""
    img = new_img()
    dark = shade(base, 0.72)
    loop = [(5, 3), (6, 3), (7, 3), (8, 3), (9, 3), (10, 3),
            (4, 4), (11, 4), (3, 5), (12, 5), (3, 6), (12, 6),
            (4, 7), (11, 7), (5, 8), (10, 8), (6, 9), (9, 9),
            (7, 10), (8, 10), (5, 10), (10, 10),
            (4, 11), (11, 11), (3, 12), (12, 12), (4, 12), (11, 12),
            (5, 12), (10, 12), (6, 12), (9, 12), (7, 12), (8, 12)]
    for x, y in loop:
        px(img, x, y, base)
    for x, y in ((6, 3), (11, 4), (12, 6), (10, 8), (8, 10), (11, 12), (4, 12)):
        px(img, x, y, dark)
    for x, y in ((5, 4), (9, 4), (4, 6), (11, 6), (7, 9), (6, 11), (10, 11)):
        px(img, x, y, salt)
    return img


def tex_sausage(base, glint):
    """Smoked sausage link with twine ends."""
    img = new_img()
    dark = shade(base, 0.7)
    for i in range(9):
        x = 3 + i
        y = 10 - i // 2
        rect(img, x, y, x, y + 2, base)
        px(img, x, y + 2, dark)
    px(img, 2, 11, STICK)
    px(img, 2, 12, STICK)
    px(img, 12, 5, STICK)
    px(img, 12, 4, STICK)
    px(img, 5, 9, glint)
    px(img, 8, 8, glint)
    px(img, 10, 7, dark)
    return img


def tex_skewer(meat_a, meat_b):
    """Diagonal skewer with alternating seared chunks."""
    img = new_img()
    for i in range(13):
        px(img, 1 + i, 14 - i, STICK)
    for i, base in ((2, meat_a), (5, meat_b), (8, meat_a)):
        cx, cy = 2 + i, 13 - i
        rect(img, cx, cy - 2, cx + 2, cy, base)
        px(img, cx + 2, cy, shade(base, 0.7))
        px(img, cx, cy - 2, shade(base, 1.2))
        px(img, cx + 1, cy - 1, CHAR)
    return img


def tex_dumpling(base, accent):
    """Pleated steamed dumpling."""
    img = new_img()
    dark = shade(base, 0.75)
    rect(img, 5, 5, 10, 5, base)
    rect(img, 4, 6, 11, 6, base)
    rect(img, 3, 7, 12, 10, base)
    rect(img, 4, 11, 11, 11, dark)
    for y in range(7, 11):
        px(img, 12, y, dark)
    for x in (5, 7, 9):
        px(img, x, 5, dark)
        px(img, x + 1, 6, dark)
    px(img, 5, 8, accent)
    px(img, 8, 9, accent)
    px(img, 5, 1, ASH_LIGHT)
    px(img, 10, 2, ASH_LIGHT)
    return img


def tex_omelette(base, filling):
    """Folded half-moon omelette with filling peeking out."""
    img = new_img()
    dark = shade(base, 0.75)
    rect(img, 3, 7, 12, 7, base)
    rect(img, 2, 8, 13, 10, base)
    rect(img, 3, 11, 12, 11, dark)
    rect(img, 4, 6, 11, 6, base)
    rect(img, 5, 5, 10, 5, shade(base, 1.12))
    for x in range(4, 12, 2):
        px(img, x, 8, filling)
    px(img, 6, 9, filling)
    px(img, 9, 9, filling)
    for y in range(8, 11):
        px(img, 13, y, dark)
    return img


def tex_corn(kernel, husk, char):
    """Charred corn cob with pulled-back husk."""
    img = new_img()
    dark = shade(kernel, 0.72)
    for y in range(3, 12):
        rect(img, 6, y, 9, y, kernel)
    px(img, 7, 2, kernel)
    px(img, 8, 2, kernel)
    rect(img, 7, 12, 8, 12, dark)
    for y in range(3, 12):
        for x in range(6, 10):
            if (x + y) % 2 == 0:
                px(img, x, y, dark)
    for x, y in ((6, 4), (8, 6), (7, 9), (9, 10)):
        px(img, x, y, char)
    for i in range(4):
        px(img, 5 - i // 2, 11 + i, husk)
        px(img, 10 + i // 2, 11 + i, husk)
    px(img, 6, 13, shade(husk, 0.8))
    px(img, 9, 13, shade(husk, 0.8))
    return img


def tex_candy_cane(base, stripe):
    """Hooked candy cane with spiral stripes."""
    img = new_img()
    for y in range(5, 14):
        rect(img, 8, y, 9, y, base)
    for x in range(5, 10):
        rect(img, x, 2, x, 3, base)
    px(img, 4, 3, base)
    px(img, 4, 4, base)
    px(img, 4, 5, base)
    px(img, 5, 4, shade(base, 0.85))
    for y in range(5, 14, 2):
        px(img, 8, y, stripe)
        px(img, 9, y + 1, stripe) if y + 1 < 14 else None
    px(img, 6, 2, stripe)
    px(img, 8, 3, stripe)
    px(img, 4, 4, stripe)
    return img


def tex_taco(shell, fill_a, fill_b):
    """Folded taco with two fillings."""
    img = new_img()
    dark = shade(shell, 0.75)
    for i, (x0, x1) in enumerate(((3, 12), (2, 13), (2, 13), (3, 13), (4, 13))):
        rect(img, x0, 7 + i, x1, 7 + i, shell)
    rect(img, 4, 12, 13, 12, dark)
    for x in range(3, 13):
        y = 6 if x % 2 else 7
        px(img, x, y, fill_a if x % 3 else fill_b)
    px(img, 4, 5, fill_b)
    px(img, 7, 5, GREEN)
    px(img, 10, 5, fill_a)
    for y in range(8, 12):
        px(img, 2 if y < 10 else 3, y, dark)
    return img


def tex_pancakes(base, syrup):
    """Stack of three pancakes with syrup drip."""
    img = new_img()
    dark = shade(base, 0.75)
    for i in range(3):
        y = 6 + i * 3
        rect(img, 3, y, 12, y + 1, base)
        rect(img, 3, y + 1, 12, y + 1, dark)
    rect(img, 4, 5, 11, 5, syrup)
    px(img, 5, 6, syrup)
    px(img, 9, 6, syrup)
    px(img, 7, 7, syrup)
    px(img, 12, 6, syrup)
    px(img, 3, 4, shade(base, 1.15))
    return img


def tex_meatballs(base, glow):
    """Three seared meatballs."""
    img = new_img()
    dark = shade(base, 0.7)
    for cx, cy in ((4, 5), (10, 5), (7, 10)):
        rect(img, cx - 1, cy - 1, cx + 2, cy + 2, base)
        px(img, cx + 2, cy + 2, dark)
        px(img, cx - 1, cy + 2, dark)
        px(img, cx + 2, cy - 1, dark)
        px(img, cx, cy, glow)
        px(img, cx + 1, cy + 1, CHAR)
    return img


def tex_croissant(base, glint):
    """Curved crescent croissant with fold lines."""
    img = new_img()
    dark = shade(base, 0.72)
    arc = [(3, 9), (3, 8), (4, 7), (4, 6), (5, 5), (6, 4), (7, 4), (8, 4),
           (9, 4), (10, 5), (11, 6), (11, 7), (12, 8), (12, 9)]
    for x, y in arc:
        rect(img, x, y, x + 1, y + 2, base)
    for x, y in ((4, 10), (7, 6), (10, 7), (12, 10)):
        px(img, x, y, dark)
    px(img, 5, 6, dark)
    px(img, 6, 5, dark)
    px(img, 9, 5, dark)
    px(img, 10, 6, dark)
    px(img, 7, 5, glint)
    px(img, 3, 10, dark)
    px(img, 13, 10, dark)
    return img


def tex_gelato(cream, accent):
    """Scoop of gelato on a waffle cone."""
    img = new_img()
    dark = shade(cream, 0.8)
    rect(img, 5, 3, 10, 3, cream)
    rect(img, 4, 4, 11, 6, cream)
    rect(img, 5, 7, 10, 7, dark)
    px(img, 6, 4, accent)
    px(img, 9, 5, accent)
    px(img, 7, 6, accent)
    px(img, 5, 5, shade(cream, 1.1))
    for i in range(6):
        x0 = 5 + i // 2
        x1 = 10 - i // 2
        rect(img, x0, 8 + i, x1, 8 + i, CRUST)
        if i % 2 == 0:
            px(img, x0 + 1, 8 + i, CRUST_DARK)
            px(img, x1 - 1, 8 + i, CRUST_DARK)
    px(img, 8, 14, CRUST_DARK)
    return img


def tex_potato(base, char, glint):
    """Charred baked potato with a steam split."""
    img = new_img()
    dark = shade(base, 0.72)
    rect(img, 4, 5, 11, 5, base)
    rect(img, 3, 6, 12, 10, base)
    rect(img, 4, 11, 11, 11, dark)
    for y in range(6, 11):
        px(img, 12, y, dark)
    rect(img, 6, 6, 9, 6, glint)
    px(img, 7, 7, glint)
    for x, y in ((4, 7), (10, 9), (6, 10), (11, 6)):
        px(img, x, y, char)
    px(img, 7, 2, ASH_LIGHT)
    px(img, 9, 1, ASH_LIGHT)
    return img


def tex_muffin(top, wrapper):
    """Domed muffin in a pleated wrapper."""
    img = new_img()
    dark = shade(top, 0.75)
    rect(img, 5, 3, 10, 3, top)
    rect(img, 4, 4, 11, 5, top)
    rect(img, 3, 6, 12, 7, top)
    px(img, 12, 7, dark)
    px(img, 3, 7, dark)
    for x, y in ((6, 4), (9, 5), (7, 6), (10, 6)):
        px(img, x, y, dark)
    rect(img, 4, 8, 11, 12, wrapper)
    for x in range(4, 12, 2):
        for y in range(8, 13):
            px(img, x, y, shade(wrapper, 0.8))
    rect(img, 5, 13, 10, 13, shade(wrapper, 0.7))
    return img


def tex_burger(bun, patty, cheese):
    """Stacked burger: bun, cheese, patty, bun."""
    img = new_img()
    bun_dark = shade(bun, 0.75)
    rect(img, 4, 3, 11, 3, bun)
    rect(img, 3, 4, 12, 5, bun)
    px(img, 5, 4, shade(bun, 1.15))
    px(img, 8, 3, shade(bun, 1.15))
    rect(img, 3, 6, 12, 6, cheese)
    px(img, 4, 7, cheese)
    px(img, 10, 7, cheese)
    rect(img, 3, 7, 12, 8, patty)
    rect(img, 3, 8, 12, 8, shade(patty, 0.7))
    rect(img, 4, 9, 11, 9, GREEN)
    rect(img, 3, 10, 12, 11, bun)
    rect(img, 4, 12, 11, 12, bun_dark)
    return img


def tex_ribs(meat, bone):
    """Rack of smoked ribs."""
    img = new_img()
    dark = shade(meat, 0.7)
    rect(img, 3, 5, 12, 11, meat)
    rect(img, 3, 11, 12, 11, dark)
    for x in (4, 7, 10):
        for y in range(5, 11):
            px(img, x, y, dark)
        px(img, x, 4, bone)
        px(img, x, 12, bone)
    px(img, 5, 6, EMBER)
    px(img, 9, 8, EMBER_DEEP)
    px(img, 11, 6, shade(meat, 1.2))
    px(img, 5, 1, ASH_LIGHT)
    px(img, 10, 2, ASH_LIGHT)
    return img


def tex_marshmallow(base, accent):
    """Squishy toasted marshmallow cube."""
    img = new_img()
    dark = shade(base, 0.85)
    rect(img, 4, 4, 11, 11, base)
    rect(img, 4, 11, 11, 11, dark)
    for y in range(4, 12):
        px(img, 11, y, dark)
    rect(img, 4, 4, 11, 4, shade(base, 1.05))
    px(img, 5, 5, shade(base, 1.08))
    for x, y in ((6, 6), (9, 7), (7, 9)):
        px(img, x, y, accent)
    px(img, 10, 10, shade(accent, 0.8))
    return img


def tex_waffle(base, syrup):
    """Square waffle with deep grid pockets."""
    img = new_img()
    dark = shade(base, 0.7)
    rect(img, 3, 3, 12, 12, base)
    for i in range(3, 13):
        px(img, i, 3, shade(base, 1.1))
    for x in (5, 8, 11):
        for y in range(3, 13):
            px(img, x, y, dark)
    for y in (5, 8, 11):
        for x in range(3, 13):
            px(img, x, y, dark)
    px(img, 4, 4, syrup)
    px(img, 7, 7, syrup)
    px(img, 10, 4, syrup)
    px(img, 6, 10, syrup)
    return img


def tex_pudding(base, glass):
    """Fizz pudding in a glass cup with a wobbly top."""
    img = new_img()
    dark = shade(base, 0.75)
    rect(img, 4, 4, 11, 4, base)
    px(img, 5, 3, base)
    px(img, 8, 3, base)
    px(img, 10, 3, base)
    for y in range(5, 12):
        px(img, 3, y, glass)
        px(img, 12, y, glass)
        rect(img, 4, y, 11, y, base if y < 9 else dark)
    px(img, 11, 5, GLASS_HI)
    px(img, 11, 6, GLASS_HI)
    for x, y in ((6, 6), (9, 7), (7, 9)):
        px(img, x, y, shade(base, 1.2))
    rect(img, 3, 12, 12, 12, glass)
    rect(img, 5, 13, 10, 13, glass)
    return img


def tex_butter(base, glint):
    """Slab of butter on a small dish."""
    img = new_img()
    dark = shade(base, 0.78)
    rect(img, 4, 5, 11, 9, base)
    rect(img, 4, 9, 11, 9, dark)
    for y in range(5, 10):
        px(img, 11, y, dark)
    rect(img, 4, 5, 11, 5, shade(base, 1.12))
    px(img, 5, 6, glint)
    px(img, 6, 6, glint)
    rect(img, 2, 10, 13, 11, GLASS)
    rect(img, 3, 12, 12, 12, shade(GLASS, 0.8))
    return img


def tex_cheese(base, hole):
    """Smoked cheese wedge with holes."""
    img = new_img()
    dark = shade(base, 0.72)
    for i in range(8):
        y = 4 + i
        x0 = 10 - i
        rect(img, x0, y, 13, y, base)
    rect(img, 3, 11, 13, 11, dark)
    for y in range(4, 12):
        px(img, 13, y, dark)
    for x, y in ((11, 6), (9, 8), (12, 9), (7, 10)):
        px(img, x, y, hole)
    px(img, 10, 4, shade(base, 1.15))
    for i in range(0, 8, 2):
        px(img, 10 - i, 4 + i, ASH)
    return img


def tex_crystal(base):
    """Faceted fizz crystal shard."""
    img = new_img()
    dark = shade(base, 0.7)
    hi = shade(base, 1.35)
    diamond = [(8, 2), (7, 3), (8, 3), (9, 3), (6, 4), (7, 4), (8, 4), (9, 4),
               (10, 4)]
    for x, y in diamond:
        px(img, x, y, base)
    for y in range(5, 12):
        w = 3 if y < 9 else 2
        rect(img, 8 - w, y, 8 + w - 1, y, base)
    px(img, 7, 12, base)
    px(img, 8, 12, base)
    px(img, 8, 13, dark)
    for y in range(5, 12):
        px(img, 9, y, dark)
        px(img, 10, y, dark) if y < 9 else None
    px(img, 6, 5, hi)
    px(img, 6, 6, hi)
    px(img, 7, 7, hi)
    px(img, 5, 8, hi)
    px(img, 8, 2, hi)
    return img


def tex_tart(crust, fill, accent):
    """Open-topped round tart."""
    img = new_img()
    dark = shade(crust, 0.72)
    rect(img, 4, 5, 11, 5, crust)
    rect(img, 3, 6, 12, 10, crust)
    rect(img, 4, 11, 11, 11, dark)
    for y in range(6, 11):
        px(img, 12, y, dark)
    rect(img, 5, 6, 10, 8, fill)
    px(img, 5, 6, shade(fill, 0.8))
    px(img, 10, 8, shade(fill, 0.8))
    px(img, 7, 6, accent)
    px(img, 9, 7, accent)
    px(img, 6, 8, accent)
    for x in range(3, 13, 2):
        px(img, x, 9, dark)
    return img


def tex_wrap(tortilla, fill_a, fill_b):
    """Rolled wrap with filling showing at the open end."""
    img = new_img()
    dark = shade(tortilla, 0.75)
    for i in range(9):
        x = 3 + i
        y0 = 9 - i // 2
        rect(img, x, y0, x, y0 + 4, tortilla)
        px(img, x, y0 + 4, dark)
    rect(img, 12, 4, 13, 8, fill_a)
    px(img, 13, 5, fill_b)
    px(img, 12, 6, GREEN)
    px(img, 13, 7, fill_b)
    px(img, 5, 8, dark)
    px(img, 7, 7, dark)
    px(img, 9, 6, dark)
    px(img, 4, 11, shade(tortilla, 1.1))
    return img


def tex_cake_slice(sponge, frosting, accent):
    """Layered cake slice."""
    img = new_img()
    dark = shade(sponge, 0.75)
    rect(img, 4, 4, 11, 5, frosting)
    px(img, 5, 3, frosting)
    px(img, 8, 3, frosting)
    rect(img, 4, 6, 11, 8, sponge)
    rect(img, 4, 9, 11, 9, shade(frosting, 0.85))
    rect(img, 4, 10, 11, 12, sponge)
    rect(img, 4, 12, 11, 12, dark)
    for y in range(6, 13):
        px(img, 11, y, dark)
    px(img, 6, 7, accent)
    px(img, 9, 11, accent)
    px(img, 5, 4, shade(frosting, 1.1))
    return img


def tex_pastry(crust, dust, glint):
    """Folded triangular pastry."""
    img = new_img()
    dark = shade(crust, 0.72)
    for i in range(8):
        y = 4 + i
        rect(img, 3 + i // 2, y, 12, y, crust)
    rect(img, 7, 11, 12, 11, dark)
    for y in range(4, 12):
        px(img, 12, y, dark)
    for i in range(0, 8, 2):
        px(img, 4 + i // 2 + 1, 4 + i, dark)
    px(img, 8, 4, dust)
    px(img, 10, 5, dust)
    px(img, 7, 6, dust)
    px(img, 9, 8, glint)
    return img


def tex_noodle_bowl(noodle, chunk):
    """Bowl of noodles with chopstick."""
    img = tex_bowl(noodle, chunk, shade(noodle, 1.25))
    for i in range(5):
        px(img, 10 + i // 2, 4 - i if 4 - i >= 0 else 0, STICK)
    px(img, 4, 5, shade(noodle, 1.3))
    px(img, 6, 5, shade(noodle, 0.8))
    return img


def tex_biscuit(base, accent):
    """Domed drop biscuit."""
    img = new_img()
    dark = shade(base, 0.75)
    rect(img, 5, 5, 10, 5, base)
    rect(img, 4, 6, 11, 6, base)
    rect(img, 3, 7, 12, 10, base)
    rect(img, 4, 11, 11, 11, dark)
    for y in range(7, 11):
        px(img, 12, y, dark)
    px(img, 5, 6, shade(base, 1.12))
    px(img, 6, 5, shade(base, 1.12))
    for x, y in ((6, 8), (9, 7), (8, 9), (5, 9)):
        px(img, x, y, accent)
    return img


# ---------------------------------------------------------------------------
# The 70 item ids (matching CuisineFeature registration order) + painters
# ---------------------------------------------------------------------------

FIZZ_LIQUIDS = {
    "sugar_rush_fizz": PINK,
    "ember_belly_fizz": (226, 88, 30),
    "cinder_skin_fizz": (138, 90, 68),
    "ember_fizz": EMBER,
    "cinder_fizz": (150, 110, 90),
    "ash_fizz": ASH_LIGHT,
    "magma_fizz": (232, 74, 26),
    "soda_fizz": (168, 96, 40),
    "smoke_fizz": (150, 146, 150),
    "spice_fizz": GOLD,
    "leaping_fizz": (94, 182, 86),
    "mending_fizz": (222, 92, 120),
    "hearty_fizz": (238, 178, 66),
    "lucky_fizz": (60, 180, 110),
    "tidal_fizz": (70, 160, 210),
    "feather_fizz": (190, 214, 235),
    "dolphin_fizz": (60, 190, 190),
    "iced_ember_tea": (196, 120, 40),
    "molten_mocha": (110, 74, 48),
    "cindercream_shake": (238, 222, 196),
}

TEXTURES = {
    # ---- ingredients (10) ----
    "cuisine_spice": lambda: tex_powder(EMBER_DEEP, EMBER_HOT),
    "ember_flour": lambda: tex_powder((222, 202, 162), EMBER),
    "soda_sugar": lambda: tex_powder((238, 216, 224), PINK),
    "cinder_salt": lambda: tex_powder(SALT, ASH),
    "magma_butter": lambda: tex_butter((244, 186, 66), EMBER_HOT),
    "ash_yeast": lambda: tex_powder((198, 186, 158), ASH_DARK),
    "fizz_crystal": lambda: tex_crystal((240, 194, 92)),
    "ember_oil": lambda: tex_bottle((236, 130, 32), EMBER_HOT),
    "smoke_extract": lambda: tex_bottle((150, 146, 150), ASH_LIGHT),
    "cinder_cocoa": lambda: tex_powder(COCOA, EMBER_DEEP),
    # ---- foods (40) ----
    "ash_pastry": lambda: tex_pastry(CRUST, ASH_LIGHT, EMBER),
    "ember_jerky": lambda: tex_jerky(MEAT_DARK, shade(MEAT_DARK, 0.7), EMBER),
    "candied_pyrium": lambda: tex_gem_candy((224, 166, 46), SALT),
    "magma_stew": lambda: tex_bowl(EMBER, EMBER_HOT, EMBER_DEEP),
    "soda_glazed_ham": lambda: tex_ham(MEAT_COOKED, (214, 118, 48), CREAM),
    "cinder_toast": lambda: tex_toast(CRUST_DARK, CRUMB, CHAR),
    "fizz_tart": lambda: tex_tart(CRUST, PINK, SALT),
    "ember_dumpling": lambda: tex_dumpling(CREAM, EMBER_DEEP),
    "charred_skewer": lambda: tex_skewer(MEAT_COOKED, MEAT_DARK),
    "soda_bread": lambda: tex_loaf(CRUST, (222, 178, 120), EMBER),
    "magma_cake_slice": lambda: tex_cake_slice(CRUMB, EMBER_DEEP, EMBER_HOT),
    "pepper_pretzel": lambda: tex_pretzel(CRUST_DARK, SALT),
    "smoke_sausage": lambda: tex_sausage(MEAT_DARK, ASH_LIGHT),
    "ember_noodles": lambda: tex_noodle_bowl((238, 198, 110), EMBER_DEEP),
    "cinder_cookie": lambda: tex_round(CRUST, COCOA_DARK),
    "fizz_pudding": lambda: tex_pudding(PINK, GLASS),
    "soda_waffle": lambda: tex_waffle(CRUMB, (168, 96, 40)),
    "ember_pie_slice": lambda: tex_wedge(BERRY, CRUST, EMBER_HOT),
    "ash_biscuit": lambda: tex_biscuit((216, 198, 168), ASH),
    "magma_chili": lambda: tex_bowl(EMBER_DEEP, MEAT_DARK, EMBER_HOT),
    "cinder_wrap": lambda: tex_wrap(CRUMB, MEAT_COOKED, EMBER_DEEP),
    "soda_donut": lambda: tex_round((214, 130, 62), PINK, hole=True),
    "ember_omelette": lambda: tex_omelette((242, 200, 96), EMBER_DEEP),
    "charred_corn": lambda: tex_corn((238, 198, 82), GREEN, CHAR),
    "fizz_candy_cane": lambda: tex_candy_cane(SALT, PINK),
    "smoke_cheese": lambda: tex_cheese(CHEESE, CHEESE_DARK),
    "ember_taco": lambda: tex_taco((230, 178, 92), MEAT_COOKED, EMBER_DEEP),
    "cinder_brownie": lambda: tex_round(COCOA, EMBER, square=True),
    "soda_pancakes": lambda: tex_pancakes(CRUMB, (168, 96, 40)),
    "magma_meatballs": lambda: tex_meatballs(MEAT_DARK, EMBER),
    "ash_cracker": lambda: tex_round((216, 202, 172), ASH_DARK, square=True),
    "ember_croissant": lambda: tex_croissant(CRUST, EMBER_HOT),
    "fizz_gelato": lambda: tex_gelato((238, 222, 230), PINK),
    "charred_potato": lambda: tex_potato((206, 168, 108), CHAR, CREAM),
    "soda_muffin": lambda: tex_muffin((196, 126, 66), (222, 202, 162)),
    "ember_burger": lambda: tex_burger(CRUST, MEAT_DARK, CHEESE),
    "cinder_pizza_slice": lambda: tex_wedge(CHEESE, CRUST_DARK, BERRY),
    "smoke_ribs": lambda: tex_ribs(MEAT_DARK, SALT),
    "fizz_marshmallow": lambda: tex_marshmallow(CREAM, PINK),
    "ember_chowder": lambda: tex_bowl(CREAM, EMBER, MEAT_COOKED),
    # ---- fizz brews (20) ----
    **{fid: (lambda liq=liq: tex_bottle(liq, shade(liq, 1.35)))
       for fid, liq in FIZZ_LIQUIDS.items()},
}

# ---------------------------------------------------------------------------
# Lang (EN + real German; item names + the 3 new effect keys)
# ---------------------------------------------------------------------------

LANG_EN = {
    "cuisine_spice": "Cuisine Spice",
    "ember_flour": "Ember Flour",
    "soda_sugar": "Soda Sugar",
    "cinder_salt": "Cinder Salt",
    "magma_butter": "Magma Butter",
    "ash_yeast": "Ash Yeast",
    "fizz_crystal": "Fizz Crystal",
    "ember_oil": "Ember Oil",
    "smoke_extract": "Smoke Extract",
    "cinder_cocoa": "Cinder Cocoa",
    "ash_pastry": "Ash Pastry",
    "ember_jerky": "Ember Jerky",
    "candied_pyrium": "Candied Pyrium",
    "magma_stew": "Magma Stew",
    "soda_glazed_ham": "Soda-Glazed Ham",
    "cinder_toast": "Cinder Toast",
    "fizz_tart": "Fizz Tart",
    "ember_dumpling": "Ember Dumpling",
    "charred_skewer": "Charred Skewer",
    "soda_bread": "Soda Bread",
    "magma_cake_slice": "Magma Cake Slice",
    "pepper_pretzel": "Pepper Pretzel",
    "smoke_sausage": "Smoke Sausage",
    "ember_noodles": "Ember Noodles",
    "cinder_cookie": "Cinder Cookie",
    "fizz_pudding": "Fizz Pudding",
    "soda_waffle": "Soda Waffle",
    "ember_pie_slice": "Ember Pie Slice",
    "ash_biscuit": "Ash Biscuit",
    "magma_chili": "Magma Chili",
    "cinder_wrap": "Cinder Wrap",
    "soda_donut": "Soda Donut",
    "ember_omelette": "Ember Omelette",
    "charred_corn": "Charred Corn",
    "fizz_candy_cane": "Fizz Candy Cane",
    "smoke_cheese": "Smoke Cheese",
    "ember_taco": "Ember Taco",
    "cinder_brownie": "Cinder Brownie",
    "soda_pancakes": "Soda Pancakes",
    "magma_meatballs": "Magma Meatballs",
    "ash_cracker": "Ash Cracker",
    "ember_croissant": "Ember Croissant",
    "fizz_gelato": "Fizz Gelato",
    "charred_potato": "Charred Potato",
    "soda_muffin": "Soda Muffin",
    "ember_burger": "Ember Burger",
    "cinder_pizza_slice": "Cinder Pizza Slice",
    "smoke_ribs": "Smoke Ribs",
    "fizz_marshmallow": "Fizz Marshmallow",
    "ember_chowder": "Ember Chowder",
    "sugar_rush_fizz": "Sugar Rush Fizz",
    "ember_belly_fizz": "Ember Belly Fizz",
    "cinder_skin_fizz": "Cinder Skin Fizz",
    "ember_fizz": "Ember Fizz",
    "cinder_fizz": "Cinder Fizz",
    "ash_fizz": "Ash Fizz",
    "magma_fizz": "Magma Fizz",
    "soda_fizz": "Soda Fizz",
    "smoke_fizz": "Smoke Fizz",
    "spice_fizz": "Spice Fizz",
    "leaping_fizz": "Leaping Fizz",
    "mending_fizz": "Mending Fizz",
    "hearty_fizz": "Hearty Fizz",
    "lucky_fizz": "Lucky Fizz",
    "tidal_fizz": "Tidal Fizz",
    "feather_fizz": "Feather Fizz",
    "dolphin_fizz": "Dolphin Fizz",
    "iced_ember_tea": "Iced Ember Tea",
    "molten_mocha": "Molten Mocha",
    "cindercream_shake": "Cindercream Shake",
}

LANG_DE = {
    "cuisine_spice": "Gew\u00fcrzmischung",
    "ember_flour": "Glutmehl",
    "soda_sugar": "Brausezucker",
    "cinder_salt": "Zindersalz",
    "magma_butter": "Magmabutter",
    "ash_yeast": "Aschehefe",
    "fizz_crystal": "Brausekristall",
    "ember_oil": "Glut\u00f6l",
    "smoke_extract": "Rauchextrakt",
    "cinder_cocoa": "Zinderkakao",
    "ash_pastry": "Aschegeb\u00e4ck",
    "ember_jerky": "Glut-D\u00f6rrfleisch",
    "candied_pyrium": "Kandiertes Pyrium",
    "magma_stew": "Magmaeintopf",
    "soda_glazed_ham": "Brause-Glasurschinken",
    "cinder_toast": "Zindertoast",
    "fizz_tart": "Brauset\u00f6rtchen",
    "ember_dumpling": "Glutklo\u00df",
    "charred_skewer": "Verkohlter Spie\u00df",
    "soda_bread": "Brausebrot",
    "magma_cake_slice": "Magmakuchenst\u00fcck",
    "pepper_pretzel": "Pfefferbrezel",
    "smoke_sausage": "Rauchwurst",
    "ember_noodles": "Glutnudeln",
    "cinder_cookie": "Zinderkeks",
    "fizz_pudding": "Brausepudding",
    "soda_waffle": "Brausewaffel",
    "ember_pie_slice": "Glutpastetenst\u00fcck",
    "ash_biscuit": "Aschebr\u00f6tchen",
    "magma_chili": "Magma-Chili",
    "cinder_wrap": "Zinder-Wrap",
    "soda_donut": "Brausekrapfen",
    "ember_omelette": "Glutomelett",
    "charred_corn": "Verkohlter Maiskolben",
    "fizz_candy_cane": "Brause-Zuckerstange",
    "smoke_cheese": "Rauchk\u00e4se",
    "ember_taco": "Glut-Taco",
    "cinder_brownie": "Zinder-Brownie",
    "soda_pancakes": "Brausepfannkuchen",
    "magma_meatballs": "Magma-Fleischb\u00e4llchen",
    "ash_cracker": "Aschecracker",
    "ember_croissant": "Glutcroissant",
    "fizz_gelato": "Brause-Eiscreme",
    "charred_potato": "Verkohlte Kartoffel",
    "soda_muffin": "Brausemuffin",
    "ember_burger": "Glutburger",
    "cinder_pizza_slice": "Zinderpizzast\u00fcck",
    "smoke_ribs": "Rauchrippchen",
    "fizz_marshmallow": "Brause-Marshmallow",
    "ember_chowder": "Glutsuppe",
    "sugar_rush_fizz": "Zuckerrausch-Brause",
    "ember_belly_fizz": "Glutbauch-Brause",
    "cinder_skin_fizz": "Zinderhaut-Brause",
    "ember_fizz": "Glutbrause",
    "cinder_fizz": "Zinderbrause",
    "ash_fizz": "Aschebrause",
    "magma_fizz": "Magmabrause",
    "soda_fizz": "Sodabrause",
    "smoke_fizz": "Rauchbrause",
    "spice_fizz": "Gew\u00fcrzbrause",
    "leaping_fizz": "Sprungbrause",
    "mending_fizz": "Heilbrause",
    "hearty_fizz": "Herzbrause",
    "lucky_fizz": "Gl\u00fccksbrause",
    "tidal_fizz": "Gezeitenbrause",
    "feather_fizz": "Federbrause",
    "dolphin_fizz": "Delfinbrause",
    "iced_ember_tea": "Glut-Eistee",
    "molten_mocha": "Geschmolzener Mokka",
    "cindercream_shake": "Zindercreme-Shake",
}

EFFECT_LANG_EN = {
    f"effect.{MOD}.sugar_rush": "Sugar Rush",
    f"effect.{MOD}.ember_belly": "Ember Belly",
    f"effect.{MOD}.cinder_skin": "Cinder Skin",
}

EFFECT_LANG_DE = {
    f"effect.{MOD}.sugar_rush": "Zuckerrausch",
    f"effect.{MOD}.ember_belly": "Glutbauch",
    f"effect.{MOD}.cinder_skin": "Zinderhaut",
}

# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 formats copied from infernofoods_gen.py). Shapeless
# throughout; smelting/smoking pairs for the two "cooked" foods. Every crafting
# input set contains a cuisine item (see UNIQUE-INPUT RULE in the docstring).
# ---------------------------------------------------------------------------


def shapeless(ingredients, result_id, count=1):
    return {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "ingredients": ingredients,
        "result": {"count": count, "id": f"{MOD}:{result_id}"},
    }


def cooking(rtype, ingredient, result_id, time, xp=0.35):
    """baked_potato.json / baked_potato_from_smoking.json template."""
    return {
        "type": rtype,
        "category": "food",
        "cookingtime": time,
        "experience": xp,
        "ingredient": ingredient,
        "result": {"id": f"{MOD}:{result_id}"},
    }


def ci(item_id):
    return f"{MOD}:{item_id}"


SPICE = ci("cuisine_spice")
FLOUR = ci("ember_flour")
SSUGAR = ci("soda_sugar")
SALT_I = ci("cinder_salt")
BUTTER = ci("magma_butter")
YEAST = ci("ash_yeast")
FIZZ = ci("fizz_crystal")
OIL = ci("ember_oil")
SMOKE_X = ci("smoke_extract")
COCOA_I = ci("cinder_cocoa")

# Drink flavor item per fizz brew ({glass_bottle, fizz_crystal, flavor...}).
FIZZ_FLAVORS = {
    "sugar_rush_fizz": [SSUGAR],
    "ember_belly_fizz": [OIL],
    "cinder_skin_fizz": [SALT_I],
    "ember_fizz": ["minecraft:blaze_powder"],
    "cinder_fizz": ["minecraft:charcoal"],
    "ash_fizz": [YEAST],
    "magma_fizz": ["minecraft:magma_cream"],
    "soda_fizz": ["minecraft:sugar"],
    "smoke_fizz": [SMOKE_X],
    "spice_fizz": [SPICE],
    "leaping_fizz": ["minecraft:rabbit_foot"],
    "mending_fizz": ["minecraft:glistering_melon_slice"],
    "hearty_fizz": ["minecraft:golden_carrot"],
    "lucky_fizz": ["minecraft:emerald"],
    "tidal_fizz": ["minecraft:pufferfish"],
    "feather_fizz": ["minecraft:feather"],
    "dolphin_fizz": ["minecraft:cod"],
    "iced_ember_tea": ["minecraft:ice", OIL],
    "molten_mocha": [COCOA_I, "minecraft:magma_cream"],
    "cindercream_shake": ["minecraft:milk_bucket", COCOA_I],
}

RECIPES = {
    # ---- ingredients ----
    # Bootstrap: ember_dust is mod-only, keeping the spice off vanilla inputs.
    "cuisine_spice": shapeless(
        ["minecraft:blaze_powder", "minecraft:sugar", ci("ember_dust")],
        "cuisine_spice", count=4),
    "ember_flour": shapeless(
        ["minecraft:wheat", "minecraft:wheat", SPICE], "ember_flour", count=3),
    "soda_sugar": shapeless(
        ["minecraft:sugar", "minecraft:sugar", SPICE], "soda_sugar", count=3),
    "cinder_salt": shapeless(
        ["minecraft:bone_meal", "minecraft:bone_meal", SPICE], "cinder_salt", count=3),
    "magma_butter": shapeless(
        ["minecraft:milk_bucket", SPICE], "magma_butter", count=2),
    "ash_yeast": shapeless(
        ["minecraft:brown_mushroom", "minecraft:sugar", SPICE], "ash_yeast", count=2),
    "fizz_crystal": shapeless(
        [SSUGAR, SSUGAR, "minecraft:gunpowder"], "fizz_crystal", count=2),
    "ember_oil": shapeless(
        ["minecraft:blaze_powder", "minecraft:honey_bottle", SPICE], "ember_oil", count=2),
    "smoke_extract": shapeless(
        ["minecraft:charcoal", "minecraft:glass_bottle", SPICE], "smoke_extract", count=2),
    "cinder_cocoa": shapeless(
        ["minecraft:cocoa_beans", "minecraft:cocoa_beans", SPICE], "cinder_cocoa", count=3),
    # ---- foods ----
    "ash_pastry": shapeless([FLOUR, BUTTER, SSUGAR], "ash_pastry", count=2),
    "ember_jerky": shapeless(["minecraft:beef", SALT_I, SPICE], "ember_jerky", count=2),
    "candied_pyrium": shapeless(
        [ci("pyrium_nugget"), SSUGAR, SSUGAR], "candied_pyrium"),
    "magma_stew": shapeless(
        ["minecraft:bowl", OIL, "minecraft:baked_potato", SPICE], "magma_stew"),
    "soda_glazed_ham": shapeless(
        ["minecraft:cooked_porkchop", SSUGAR, OIL], "soda_glazed_ham"),
    "cinder_toast": cooking("minecraft:smelting", ci("soda_bread"), "cinder_toast", 200),
    "cinder_toast_from_smoking": cooking(
        "minecraft:smoking", ci("soda_bread"), "cinder_toast", 100),
    "fizz_tart": shapeless([FLOUR, FIZZ, "minecraft:sweet_berries"], "fizz_tart", count=2),
    "ember_dumpling": shapeless(
        [FLOUR, "minecraft:cooked_chicken", SPICE], "ember_dumpling", count=3),
    "charred_skewer": shapeless(
        ["minecraft:stick", ci("smoke_sausage"), SPICE], "charred_skewer", count=2),
    "soda_bread": shapeless([FLOUR, YEAST], "soda_bread", count=2),
    "magma_cake_slice": shapeless(
        [FLOUR, BUTTER, "minecraft:egg", SSUGAR], "magma_cake_slice", count=3),
    "pepper_pretzel": shapeless([FLOUR, SALT_I], "pepper_pretzel", count=2),
    "smoke_sausage": shapeless(
        ["minecraft:porkchop", SMOKE_X, SALT_I], "smoke_sausage", count=2),
    "ember_noodles": shapeless(["minecraft:bowl", FLOUR, OIL], "ember_noodles"),
    "cinder_cookie": shapeless([FLOUR, COCOA_I], "cinder_cookie", count=4),
    "fizz_pudding": shapeless(
        ["minecraft:milk_bucket", FIZZ, SSUGAR], "fizz_pudding", count=2),
    "soda_waffle": shapeless([FLOUR, BUTTER, "minecraft:honey_bottle"], "soda_waffle", count=2),
    "ember_pie_slice": shapeless(
        [FLOUR, "minecraft:sweet_berries", OIL], "ember_pie_slice", count=3),
    "ash_biscuit": shapeless([FLOUR, YEAST, BUTTER], "ash_biscuit", count=4),
    "magma_chili": shapeless(
        ["minecraft:bowl", SPICE, "minecraft:cooked_beef", OIL], "magma_chili"),
    "cinder_wrap": shapeless(
        [FLOUR, "minecraft:cooked_mutton", SPICE], "cinder_wrap", count=2),
    "soda_donut": shapeless([FLOUR, SSUGAR, OIL], "soda_donut", count=3),
    "ember_omelette": shapeless(
        ["minecraft:egg", "minecraft:egg", OIL, SALT_I], "ember_omelette", count=2),
    "charred_corn": shapeless(["minecraft:wheat", OIL, SPICE], "charred_corn", count=2),
    "fizz_candy_cane": shapeless([FIZZ, SSUGAR], "fizz_candy_cane", count=2),
    "smoke_cheese": cooking("minecraft:smelting", BUTTER, "smoke_cheese", 200),
    "smoke_cheese_from_smoking": cooking("minecraft:smoking", BUTTER, "smoke_cheese", 100),
    "ember_taco": shapeless([FLOUR, "minecraft:cooked_beef", SPICE], "ember_taco", count=2),
    "cinder_brownie": shapeless([COCOA_I, BUTTER, SSUGAR], "cinder_brownie", count=3),
    "soda_pancakes": shapeless([FLOUR, "minecraft:egg", SSUGAR], "soda_pancakes", count=2),
    "magma_meatballs": shapeless(
        ["minecraft:beef", OIL, SPICE], "magma_meatballs", count=2),
    "ash_cracker": shapeless([FLOUR, SALT_I, YEAST], "ash_cracker", count=4),
    "ember_croissant": shapeless([FLOUR, BUTTER, BUTTER], "ember_croissant", count=2),
    "fizz_gelato": shapeless(
        ["minecraft:snowball", FIZZ, "minecraft:milk_bucket"], "fizz_gelato", count=2),
    "charred_potato": shapeless(
        ["minecraft:baked_potato", OIL, SPICE], "charred_potato", count=2),
    "soda_muffin": shapeless(
        [FLOUR, SSUGAR, "minecraft:sweet_berries"], "soda_muffin", count=3),
    "ember_burger": shapeless(
        [ci("soda_bread"), "minecraft:cooked_beef", SPICE], "ember_burger"),
    "cinder_pizza_slice": shapeless(
        [FLOUR, ci("smoke_cheese"), SPICE], "cinder_pizza_slice", count=3),
    "smoke_ribs": shapeless(
        ["minecraft:cooked_porkchop", SMOKE_X, OIL], "smoke_ribs"),
    "fizz_marshmallow": shapeless(
        [SSUGAR, SSUGAR, "minecraft:egg"], "fizz_marshmallow", count=4),
    "ember_chowder": shapeless(
        ["minecraft:bowl", "minecraft:cooked_cod", OIL, SALT_I], "ember_chowder"),
    # ---- fizz brews ----
    **{fid: shapeless(["minecraft:glass_bottle", FIZZ] + flavors, fid)
       for fid, flavors in FIZZ_FLAVORS.items()},
}

# ---------------------------------------------------------------------------
# Emission
# ---------------------------------------------------------------------------


def main():
    ids = list(TEXTURES)
    assert set(ids) == set(LANG_EN) == set(LANG_DE), "item id tables out of sync"
    assert len(ids) == 70, f"expected 70 items, got {len(ids)}"

    for item_id in ids:
        write_json(ASSETS / "items" / f"{item_id}.json",
                   item_def(f"{MOD}:item/{item_id}"))
        write_json(ASSETS / "models" / "item" / f"{item_id}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"{MOD}:item/{item_id}"},
        })
        tex_path = ASSETS / "textures" / "item" / f"{item_id}.png"
        tex_path.parent.mkdir(parents=True, exist_ok=True)
        TEXTURES[item_id]().save(tex_path)

    for name, recipe in RECIPES.items():
        write_json(DATA / "recipe" / "cuisine" / f"{name}.json", recipe)

    lang_en = {f"item.{MOD}.{item_id}": LANG_EN[item_id] for item_id in ids}
    lang_en.update(EFFECT_LANG_EN)
    lang_de = {f"item.{MOD}.{item_id}": LANG_DE[item_id] for item_id in ids}
    lang_de.update(EFFECT_LANG_DE)
    write_json(ASSETS / "lang" / "fragments" / "cuisine.json", lang_en)
    write_json(ASSETS / "lang" / "fragments_de" / "cuisine.json", lang_de)

    print(f"cuisine_gen: wrote {len(ids)} items (item defs, models, textures) + "
          f"{len(RECIPES)} recipes + EN/DE lang fragments ({len(lang_en)} keys each)")


if __name__ == "__main__":
    main()
