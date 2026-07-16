#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "infernocuisine" feature (100 foods & drinks).

The Inferno kitchen: 15 bottled drinks (glass bottle handed back), 10 bowl meals (bowl
handed back), 7 bottled preserves, 12 raw->cooked furnace/campfire chains (24 items),
10 pastries, 11 sweets, 15 savory snacks and 8 fire-kissed fruits.

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for). Emits by DEFAULT (no flags):
  - assets/copper_inferno/items/<id>.json           (1.21.9 item model definition)
  - assets/copper_inferno/models/item/<id>.json     (item/generated + layer0)
  - assets/copper_inferno/textures/item/<id>.png    (deterministic 16x16 Pillow art)
  - data/copper_inferno/recipe/infernocuisine/*.json (100 crafting + 12 campfire variants)
  - lang fragments: assets/.../lang/fragments/infernocuisine.json (EN)
    and assets/.../lang/fragments_de/infernocuisine.json (real German)
  - src/main/java/.../feature/infernocuisine/InfernoCuisineFeature.java
    + InfernoCuisineHandbook.java (genlib.java_feature_class / java_handbook_class;
    literal ids only)
  - devtools/hooks/infernocuisine.txt (integration hook file)

Food API verified with javap against the loom 1.21.9 minecraft-common jar
(net.minecraft.component.type.FoodComponent$Builder: nutrition(int) /
saturationModifier(float) / alwaysEdible() / build(); Item$Settings:
food(FoodComponent) + food(FoodComponent, ConsumableComponent), maxCount(int),
useRemainder(Item); ConsumableComponents.drink().build()) and mirrors the committed
feature/foods/FoodsFeature + feature/infernofoods/InfernoFoodsFeature registrations.

Recipe JSON formats are the exact vanilla 1.21.9 templates (genlib emitters for
crafting; baked_potato.json / baked_potato_from_campfire_cooking.json for cooking).
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json
from PIL import Image

RECIPES = DATA / "recipe" / "infernocuisine"
FEATURE_DIR = (ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno"
               / "feature" / "infernocuisine")

# ---------------------------------------------------------------------------
# Palette (shared with foods_gen / infernofoods_gen house style)
# ---------------------------------------------------------------------------
EMBER = (255, 148, 42)
EMBER_HOT = (255, 214, 92)
EMBER_DEEP = (198, 74, 24)
LAVA = (255, 94, 26)
MAGMA = (216, 70, 20)
CHAR = (52, 40, 38)
ASH = (128, 122, 116)
ASH_LIGHT = (176, 170, 162)
ASH_DARK = (86, 80, 76)
GLASS = (208, 224, 232)
GLASS_HI = (244, 250, 252)
BOWL_WOOD = (134, 88, 48)
BOWL_DARK = (100, 64, 34)
CREAM = (248, 240, 214)
TAN = (210, 168, 110)
TAN_DARK = (166, 124, 72)
BREAD = (196, 148, 88)
CHOC = (94, 58, 34)
GREEN = (74, 140, 60)
STEM = (94, 62, 32)
MEAT_RAW = (214, 96, 92)
MEAT_RAW_DARK = (172, 62, 60)
MEAT_COOKED = (158, 96, 52)
MEAT_COOKED_DARK = (116, 66, 34)

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


def speck(img, rng, colors, n, box=(2, 2, 13, 13)):
    """n deterministic accent pixels on already-opaque pixels inside box."""
    x0, y0, x1, y1 = box
    placed = 0
    for _ in range(n * 8):
        if placed >= n:
            break
        x, y = rng.randint(x0, x1), rng.randint(y0, y1)
        if img.getpixel((x, y))[3] != 0:
            img.putpixel((x, y), rgba(rng.choice(colors)))
            placed += 1


# ---------------------------------------------------------------------------
# Texture painters (16x16 RGBA; every rng comes from genlib.rng_for(<item id>))
# ---------------------------------------------------------------------------

def t_bottle(rng, liquid, cap):
    """Capped glass bottle (foods_gen shape) with rng bubbles in the liquid."""
    img = new_img()
    liq_dark = shade(liquid, 0.72)
    rect(img, 6, 0, 9, 1, cap)
    px(img, 6, 0, shade(cap, 0.75))
    px(img, 9, 0, shade(cap, 0.75))
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
    for y in range(6, 12):
        px(img, 9, y, shade(liquid, 1.45))
    px(img, 10, 5, GLASS_HI)
    rect(img, 4, 14, 11, 14, GLASS)
    rect(img, 5, 14, 10, 14, liq_dark)
    rect(img, 5, 15, 10, 15, GLASS)
    speck(img, rng, [shade(liquid, 1.3)], 3, (5, 6, 10, 12))
    return img


def t_bowl(rng, soup, chunk):
    """Wooden bowl of soup (infernofoods smolder_stew shape) with rng chunks."""
    img = new_img()
    px(img, 5, 1, ASH_LIGHT)
    px(img, 10, 1, ASH_LIGHT)
    px(img, 6, 2, ASH)
    px(img, 9, 3, ASH)
    rect(img, 2, 6, 13, 6, BOWL_WOOD)
    px(img, 2, 6, BOWL_DARK)
    px(img, 13, 6, BOWL_DARK)
    rect(img, 3, 5, 12, 5, soup)
    px(img, 5, 5, shade(soup, 1.25))
    px(img, 9, 5, shade(soup, 0.75))
    rect(img, 3, 7, 12, 9, BOWL_WOOD)
    rect(img, 4, 10, 11, 11, BOWL_WOOD)
    rect(img, 5, 12, 10, 12, BOWL_DARK)
    for y in range(7, 10):
        px(img, 12, y, BOWL_DARK)
    px(img, 11, 10, BOWL_DARK)
    px(img, 11, 11, BOWL_DARK)
    rect(img, 6, 13, 9, 13, BOWL_DARK)
    speck(img, rng, [chunk], 3, (4, 5, 11, 5))
    return img


def t_jar(rng, fill, lid):
    """Squat preserve jar (foods_gen shape) with rng fruit chunks."""
    img = new_img()
    dark = shade(fill, 0.7)
    rect(img, 4, 1, 11, 2, lid)
    px(img, 4, 1, shade(lid, 0.75))
    px(img, 11, 1, shade(lid, 0.75))
    rect(img, 3, 3, 12, 3, GLASS)
    for y in range(4, 13):
        px(img, 3, y, GLASS)
        px(img, 12, y, GLASS)
        rect(img, 4, y, 11, y, fill)
        px(img, 4, y, dark)
    for y in range(5, 11):
        px(img, 10, y, shade(fill, 1.4))
    rect(img, 3, 13, 12, 13, GLASS)
    rect(img, 4, 13, 11, 13, dark)
    rect(img, 4, 14, 11, 14, GLASS)
    speck(img, rng, [dark, shade(fill, 1.25)], 4, (5, 5, 10, 11))
    return img


def t_sausage(rng, base):
    """Curved sausage link with sear marks."""
    img = new_img()
    dark = shade(base, 0.7)
    rect(img, 3, 4, 12, 7, base)
    rect(img, 4, 3, 11, 3, base)
    rect(img, 4, 8, 11, 8, dark)
    px(img, 3, 4, dark)
    px(img, 12, 7, dark)
    px(img, 2, 5, dark)
    px(img, 13, 6, dark)
    # String ends.
    px(img, 1, 5, CREAM)
    px(img, 14, 6, CREAM)
    for y in range(4, 6):
        px(img, 5, y, shade(base, 1.25))
    speck(img, rng, [dark, CHAR], 4, (4, 3, 11, 7))
    return img


def t_steak(rng, base, dark):
    """Oval steak slab with grill lines."""
    img = new_img()
    rect(img, 4, 4, 11, 4, base)
    rect(img, 3, 5, 12, 10, base)
    rect(img, 4, 11, 11, 11, dark)
    for y in range(5, 11):
        px(img, 12, y, dark)
    for x in (5, 8, 11):
        for y in range(5, 11):
            px(img, x, y, dark)
    px(img, 4, 5, shade(base, 1.25))
    px(img, 6, 6, shade(base, 1.25))
    speck(img, rng, [dark], 3, (4, 5, 11, 10))
    return img


def t_strips(rng, base, stripe):
    """Three hanging strips (bacon / jerky) with a fat/stripe line each."""
    img = new_img()
    dark = shade(base, 0.7)
    for i, cx in enumerate((4, 8, 12)):
        top = 2 + (i % 2)
        rect(img, cx - 1, top, cx, top + 10, base)
        for y in range(top, top + 11):
            if y % 3 == i % 3:
                px(img, cx - 1, y, stripe)
        px(img, cx, top + 10, dark)
        px(img, cx, top, dark)
    speck(img, rng, [dark], 4, (3, 3, 13, 12))
    return img


def t_ribs(rng, base, dark):
    """Rack of ribs with pale bone lines."""
    img = new_img()
    bone = (238, 230, 214)
    rect(img, 3, 4, 12, 11, base)
    rect(img, 3, 4, 12, 4, shade(base, 1.2))
    rect(img, 3, 11, 12, 11, dark)
    for x in (4, 7, 10):
        for y in range(5, 11):
            px(img, x, y, dark)
        px(img, x, 3, bone)
        px(img, x, 12, bone)
    speck(img, rng, [dark, CHAR], 3, (3, 5, 12, 10))
    return img


def t_fillet(rng, base, stripe):
    """Tapered fish fillet with flake stripes."""
    img = new_img()
    dark = shade(base, 0.72)
    rect(img, 2, 5, 6, 10, base)
    rect(img, 7, 6, 10, 9, base)
    rect(img, 11, 7, 13, 8, base)
    for x, y in ((4, 5), (8, 6), (11, 7), (5, 10), (9, 9), (13, 8)):
        px(img, x, y, stripe)
    for y in range(5, 11):
        px(img, 2, y, dark)
    px(img, 13, 8, dark)
    speck(img, rng, [stripe, dark], 3, (3, 5, 12, 10))
    return img


def t_cube(rng, base, hi, drip=None):
    """Rounded soft cube (marshmallow / fudge / caramel / toffee / brownie)."""
    img = new_img()
    dark = shade(base, 0.75)
    rect(img, 4, 4, 11, 11, base)
    rect(img, 5, 3, 10, 3, base)
    rect(img, 5, 12, 10, 12, dark)
    for y in range(4, 12):
        px(img, 11, y, dark)
    px(img, 5, 4, hi)
    px(img, 6, 4, hi)
    px(img, 5, 5, hi)
    if drip is not None:
        rect(img, 6, 12, 6, 13, drip)
        px(img, 9, 13, drip)
        px(img, 9, 14, shade(drip, 0.8))
    speck(img, rng, [dark], 3, (5, 4, 10, 11))
    return img


def t_blob(rng, base, dot):
    """Round dough / batter / dumpling / fritter blob with accent dots."""
    img = new_img()
    dark = shade(base, 0.72)
    rect(img, 5, 3, 10, 3, base)
    rect(img, 4, 4, 11, 10, base)
    rect(img, 5, 11, 10, 11, dark)
    for y in range(4, 11):
        px(img, 11, y, dark)
    px(img, 5, 4, shade(base, 1.2))
    px(img, 6, 5, shade(base, 1.2))
    speck(img, rng, [dot], 4, (5, 4, 10, 10))
    return img


def t_round(rng, base, rim, spot, nspots):
    """Flat disc (biscuit / cracker / pizza / omelette / gingerbread) with speckles."""
    img = new_img()
    rect(img, 5, 3, 10, 3, rim)
    rect(img, 3, 4, 12, 5, base)
    rect(img, 2, 6, 13, 10, base)
    rect(img, 3, 11, 12, 12, base)
    rect(img, 5, 13, 10, 13, rim)
    for y in range(6, 11):
        px(img, 13, y, rim)
        px(img, 2, y, shade(base, 1.15))
    rect(img, 4, 12, 12, 12, rim)
    speck(img, rng, [spot], nspots, (4, 5, 11, 11))
    return img


def t_pancake(rng, base, syrup):
    """Two-layer pancake stack with syrup on top."""
    img = new_img()
    dark = shade(base, 0.72)
    rect(img, 3, 6, 12, 8, base)
    rect(img, 3, 9, 12, 9, dark)
    rect(img, 3, 10, 12, 11, base)
    rect(img, 4, 12, 11, 12, dark)
    rect(img, 4, 5, 11, 5, syrup)
    for x, y in ((5, 6), (8, 6), (10, 7)):
        px(img, x, y, syrup)
    px(img, 7, 3, EMBER_HOT)
    speck(img, rng, [dark], 3, (4, 6, 11, 11))
    return img


def t_waffle(rng, base, deep):
    """Square waffle with a 3x3 pocket grid."""
    img = new_img()
    rect(img, 3, 3, 12, 12, base)
    for i in (5, 8, 11):
        for j in range(3, 13):
            px(img, i, j, deep)
            px(img, j, i, deep)
    rect(img, 3, 12, 12, 12, shade(base, 0.7))
    speck(img, rng, [shade(base, 1.2)], 3, (4, 4, 11, 11))
    return img


def t_wedge(rng, crust, fill, top=None):
    """Triangular slice (pie / tart / quiche / melon): crust below, filling above."""
    img = new_img()
    for i, (y0, y1) in enumerate(((3, 3), (4, 5), (6, 7), (8, 9), (10, 11))):
        w = 2 + i * 2
        x0 = 8 - w // 2
        rect(img, x0, y0, x0 + w - 1, y1, fill)
    rect(img, 2, 12, 13, 13, crust)
    rect(img, 3, 14, 12, 14, shade(crust, 0.7))
    if top is not None:
        px(img, 8, 2, top)
        px(img, 7, 4, top)
        px(img, 9, 6, top)
    speck(img, rng, [shade(fill, 0.7)], 4, (5, 5, 10, 11))
    return img


def t_muffin(rng, wrap, top, dot):
    """Muffin / cupcake: pleated wrapper + domed top with a dot."""
    img = new_img()
    rect(img, 4, 4, 11, 6, top)
    rect(img, 3, 6, 12, 7, top)
    px(img, 4, 4, shade(top, 0.75))
    px(img, 11, 4, shade(top, 0.75))
    px(img, 7, 2, dot)
    rect(img, 4, 8, 11, 12, wrap)
    for x in (5, 7, 9, 11):
        for y in range(8, 13):
            px(img, x, y, shade(wrap, 0.72))
    rect(img, 5, 13, 10, 13, shade(wrap, 0.6))
    speck(img, rng, [dot], 3, (4, 4, 11, 7))
    return img


def t_donut(rng, dough, glaze):
    """Glazed ring donut with sprinkles."""
    img = new_img()
    rect(img, 4, 3, 11, 4, glaze)
    rect(img, 3, 5, 12, 8, glaze)
    rect(img, 3, 9, 12, 10, dough)
    rect(img, 4, 11, 11, 12, dough)
    rect(img, 4, 12, 11, 12, shade(dough, 0.7))
    rect(img, 7, 6, 8, 8, T)
    px(img, 7, 6, shade(dough, 0.6))
    px(img, 8, 6, shade(dough, 0.6))
    speck(img, rng, [EMBER_HOT, CREAM, MAGMA], 4, (4, 3, 11, 8))
    return img


def t_croissant(rng, base, dark):
    """Crescent croissant with segment shading."""
    img = new_img()
    rect(img, 2, 8, 4, 11, base)
    rect(img, 4, 6, 7, 11, base)
    rect(img, 7, 5, 9, 10, base)
    rect(img, 9, 6, 12, 9, base)
    rect(img, 12, 7, 13, 10, base)
    for x, y in ((4, 7), (7, 6), (10, 7), (12, 8), (3, 9)):
        px(img, x, y, dark)
    rect(img, 3, 11, 7, 11, dark)
    rect(img, 12, 10, 13, 10, dark)
    px(img, 6, 6, shade(base, 1.2))
    speck(img, rng, [dark], 3, (3, 6, 12, 10))
    return img


def t_lollipop(rng, candy, swirl):
    """Round lollipop on a stick with a swirl."""
    img = new_img()
    dark = shade(candy, 0.72)
    rect(img, 5, 2, 10, 7, candy)
    rect(img, 6, 1, 9, 1, candy)
    rect(img, 6, 8, 9, 8, dark)
    for x, y in ((6, 3), (8, 2), (9, 4), (7, 5), (6, 6)):
        px(img, x, y, swirl)
    for y in range(9, 15):
        px(img, 7, y, CREAM)
        px(img, 8, y, shade(CREAM, 0.8))
    speck(img, rng, [swirl], 2, (5, 2, 10, 7))
    return img


def t_sticks(rng, color, hi):
    """Three twisted licorice sticks."""
    img = new_img()
    dark = shade(color, 0.65)
    for i, cx in enumerate((4, 8, 12)):
        top = 2 + i
        rect(img, cx - 1, top, cx, top + 9, color)
        for y in range(top, top + 10):
            if (y + i) % 3 == 0:
                px(img, cx - 1, y, dark)
            if (y + i) % 4 == 0:
                px(img, cx, y, hi)
    speck(img, rng, [dark], 3, (3, 3, 13, 12))
    return img


def t_bar(rng, base, groove):
    """Chocolate / brittle bar with score grooves."""
    img = new_img()
    rect(img, 2, 4, 13, 11, base)
    rect(img, 2, 11, 13, 11, shade(base, 0.65))
    for y in range(4, 12):
        px(img, 13, y, shade(base, 0.65))
    for x in (5, 9):
        for y in range(4, 12):
            px(img, x, y, groove)
    for y in (7,):
        for x in range(2, 14):
            px(img, x, y, groove)
    px(img, 3, 5, shade(base, 1.3))
    speck(img, rng, [groove], 3, (3, 4, 12, 10))
    return img


def t_wrapped(rng, candy, wrapper):
    """Wrapped bonbon (infernofoods cinder_candy shape)."""
    img = new_img()
    px(img, 1, 7, shade(wrapper, 1.2))
    px(img, 2, 6, wrapper)
    px(img, 2, 8, wrapper)
    px(img, 3, 7, shade(wrapper, 1.2))
    px(img, 14, 7, shade(wrapper, 1.2))
    px(img, 13, 6, wrapper)
    px(img, 13, 8, wrapper)
    px(img, 12, 7, shade(wrapper, 1.2))
    rect(img, 5, 5, 10, 9, candy)
    for x, y in ((5, 5), (10, 5), (5, 9), (10, 9)):
        px(img, x, y, shade(candy, 0.7))
    px(img, 7, 6, shade(candy, 1.35))
    px(img, 8, 7, shade(candy, 0.75))
    speck(img, rng, [shade(candy, 1.35)], 2, (5, 5, 10, 9))
    return img


def t_stack(rng, bun, filling, extra):
    """Burger / sandwich: bun top, filling band, extra band, bun base."""
    img = new_img()
    rect(img, 4, 3, 11, 4, bun)
    rect(img, 3, 4, 12, 5, bun)
    px(img, 6, 3, shade(bun, 1.2))
    rect(img, 3, 6, 12, 6, extra)
    rect(img, 2, 7, 13, 8, filling)
    rect(img, 3, 9, 12, 9, extra)
    rect(img, 3, 10, 12, 11, bun)
    rect(img, 4, 12, 11, 12, shade(bun, 0.7))
    for y in range(7, 9):
        px(img, 13, y, shade(filling, 0.7))
    speck(img, rng, [shade(bun, 0.8)], 3, (4, 3, 11, 5))
    return img


def t_wrap(rng, tortilla, fill):
    """Folded taco / burrito with filling peeking out."""
    img = new_img()
    dark = shade(tortilla, 0.72)
    rect(img, 2, 7, 13, 12, tortilla)
    rect(img, 3, 6, 12, 6, tortilla)
    rect(img, 4, 4, 11, 5, fill)
    for x, y in ((5, 4), (7, 5), (9, 4), (10, 5)):
        px(img, x, y, shade(fill, 0.7))
    rect(img, 3, 12, 12, 12, dark)
    for y in range(7, 12):
        px(img, 13, y, dark)
        px(img, 2, y, shade(tortilla, 1.15))
    speck(img, rng, [dark], 3, (3, 7, 12, 11))
    return img


def t_noodles(rng, noodle, accent):
    """Nest of wavy noodles with accent bits."""
    img = new_img()
    dark = shade(noodle, 0.7)
    rect(img, 3, 5, 12, 11, noodle)
    rect(img, 4, 4, 11, 4, noodle)
    rect(img, 4, 12, 11, 12, dark)
    for y in range(5, 12):
        for x in range(3, 13):
            if (x + y) % 3 == 0:
                px(img, x, y, dark)
    speck(img, rng, [accent], 4, (4, 5, 11, 11))
    return img


def t_skewer(rng, meat, dark, n):
    """Diagonal skewer with n meat chunks."""
    img = new_img()
    for i in range(13):
        px(img, 2 + i, 14 - i, STEM)
    positions = [(4, 10), (7, 7), (10, 4)][:n]
    for cx, cy in positions:
        rect(img, cx - 1, cy - 1, cx + 2, cy + 2, meat)
        px(img, cx + 2, cy + 2, dark)
        px(img, cx + 2, cy - 1, dark)
        px(img, cx - 1, cy + 2, dark)
        px(img, cx, cy, shade(meat, 1.2))
    speck(img, rng, [dark, CHAR], 3, (3, 3, 12, 12))
    return img


def t_cluster(rng, base, dark, n, r):
    """Cluster of n round bits (grapes / olives / meatballs / popcorn / kernels)."""
    img = new_img()
    spots = [(5, 5), (10, 6), (6, 10), (11, 11), (8, 8), (4, 12), (12, 4)][:n]
    for cx, cy in spots:
        rect(img, cx - r, cy - r, cx + r, cy + r, base)
        px(img, cx + r, cy + r, dark)
        px(img, cx - r, cy - r, shade(base, 1.25))
    speck(img, rng, [dark], 3, (3, 3, 12, 12))
    return img


def t_spud(rng, base, spot):
    """Oval tuber / pickle / date with eye spots."""
    img = new_img()
    dark = shade(base, 0.72)
    rect(img, 3, 6, 12, 10, base)
    rect(img, 4, 5, 11, 5, base)
    rect(img, 4, 11, 11, 11, dark)
    for y in range(6, 11):
        px(img, 12, y, dark)
    px(img, 4, 6, shade(base, 1.2))
    for x, y in ((6, 7), (9, 9), (11, 7)):
        px(img, x, y, spot)
    speck(img, rng, [spot, dark], 3, (4, 6, 11, 10))
    return img


def t_fruit(rng, base, hi):
    """Round fruit (fig / plum) with a stem."""
    img = new_img()
    dark = shade(base, 0.72)
    px(img, 8, 2, STEM)
    px(img, 8, 3, STEM)
    px(img, 9, 3, GREEN)
    rect(img, 5, 4, 10, 4, base)
    rect(img, 4, 5, 11, 11, base)
    rect(img, 5, 12, 10, 12, dark)
    for y in range(5, 12):
        px(img, 11, y, dark)
    px(img, 5, 5, hi)
    px(img, 6, 5, hi)
    px(img, 5, 6, hi)
    speck(img, rng, [dark], 3, (5, 5, 10, 11))
    return img


def t_pretzel(rng, base, salt):
    """Pretzel knot: ring with crossing arms and salt grains."""
    img = new_img()
    dark = shade(base, 0.7)
    rect(img, 4, 3, 11, 4, base)
    rect(img, 3, 5, 5, 11, base)
    rect(img, 10, 5, 12, 11, base)
    rect(img, 4, 11, 11, 12, base)
    for i in range(5):
        px(img, 5 + i, 6 + i, base)
        px(img, 10 - i, 6 + i, base)
    px(img, 7, 8, dark)
    px(img, 8, 8, dark)
    rect(img, 4, 12, 11, 12, dark)
    for x, y in ((5, 3), (9, 4), (4, 7), (11, 8), (7, 11)):
        px(img, x, y, salt)
    speck(img, rng, [salt, dark], 3, (4, 4, 11, 11))
    return img


def t_omelette(rng, base, fill):
    """Folded half-moon omelette with filling dots."""
    img = new_img()
    dark = shade(base, 0.72)
    rect(img, 3, 6, 12, 7, base)
    rect(img, 2, 8, 13, 10, base)
    rect(img, 3, 11, 12, 11, base)
    rect(img, 4, 12, 11, 12, dark)
    rect(img, 4, 5, 11, 5, shade(base, 1.15))
    for y in range(8, 11):
        px(img, 13, y, dark)
    for x, y in ((5, 8), (8, 9), (10, 8), (6, 10)):
        px(img, x, y, fill)
    speck(img, rng, [fill], 2, (4, 6, 11, 11))
    return img


# ---------------------------------------------------------------------------
# Item table: (id, EN, DE, kind, nutrition, saturation-str)
# kind: drink   = maxCount 16 + GLASS_BOTTLE remainder + always-edible drink consumable
#       bowl    = maxCount 1 + BOWL remainder
#       bottle  = maxCount 16 + GLASS_BOTTLE remainder (plain food)
#       food    = plain food
#       always  = plain food, alwaysEdible
# ---------------------------------------------------------------------------
ITEMS = [
    # ---- Bottled drinks (15) ----
    ("magma_cola", "Magma Cola", "Magma-Cola", "drink", 2, "0.3"),
    ("ember_tea", "Ember Tea", "Gluttee", "drink", 2, "0.3"),
    ("lava_latte", "Lava Latte", "Lava-Milchkaffee", "drink", 3, "0.4"),
    ("cinder_cider", "Cinder Cider", "Zunder-Apfelmost", "drink", 2, "0.3"),
    ("soot_smoothie", "Soot Smoothie", "Ru\u00df-Smoothie", "drink", 2, "0.3"),
    ("blaze_brew", "Blaze Brew", "Lohentrunk", "drink", 2, "0.3"),
    ("flame_nectar", "Flame Nectar", "Flammennektar", "drink", 2, "0.3"),
    ("ashen_ale", "Ashen Ale", "Aschen-Ale", "drink", 2, "0.3"),
    ("molten_mocha", "Molten Mocha", "Geschmolzener Mokka", "drink", 3, "0.4"),
    ("ember_espresso", "Ember Espresso", "Glut-Espresso", "drink", 2, "0.3"),
    ("inferno_punch", "Inferno Punch", "Inferno-Punsch", "drink", 3, "0.4"),
    ("smoke_soda", "Smoke Soda", "Rauchbrause", "drink", 1, "0.1"),
    ("slag_shake", "Slag Shake", "Schlacken-Shake", "drink", 2, "0.3"),
    ("obsidian_oolong", "Obsidian Oolong", "Obsidian-Oolong", "drink", 2, "0.3"),
    ("charcoal_lemonade", "Charcoal Lemonade", "Holzkohlen-Limonade", "drink", 2, "0.3"),
    # ---- Bowl meals (10) ----
    ("magma_soup", "Magma Soup", "Magmasuppe", "bowl", 6, "0.6"),
    ("slag_stew", "Slag Stew", "Schlackeneintopf", "bowl", 8, "0.8"),
    ("cinder_chowder", "Cinder Chowder", "Zunder-Fischsuppe", "bowl", 7, "0.7"),
    ("blaze_broth", "Blaze Broth", "Lohenbr\u00fche", "bowl", 5, "0.6"),
    ("ember_porridge", "Ember Porridge", "Glutbrei", "bowl", 6, "0.6"),
    ("ash_gruel", "Ash Gruel", "Aschengr\u00fctze", "bowl", 4, "0.4"),
    ("lava_ramen", "Lava Ramen", "Lava-Ramen", "bowl", 7, "0.7"),
    ("coal_curry", "Coal Curry", "Kohlencurry", "bowl", 7, "0.7"),
    ("scorched_goulash", "Scorched Goulash", "Versengtes Gulasch", "bowl", 8, "0.8"),
    ("furnace_fondue", "Furnace Fondue", "Ofenfondue", "bowl", 6, "0.7"),
    # ---- Bottled preserves (7) ----
    ("magma_marmalade", "Magma Marmalade", "Magma-Marmelade", "bottle", 4, "0.5"),
    ("ember_honey", "Ember Honey", "Gluthonig", "bottle", 4, "0.5"),
    ("cinder_syrup", "Cinder Syrup", "Zundersirup", "bottle", 3, "0.4"),
    ("ash_yogurt", "Ash Yogurt", "Asche-Joghurt", "bottle", 3, "0.4"),
    ("flame_chutney", "Flame Chutney", "Flammen-Chutney", "bottle", 4, "0.5"),
    ("cinder_kimchi", "Cinder Kimchi", "Zunder-Kimchi", "bottle", 4, "0.5"),
    ("magma_salsa", "Magma Salsa", "Magma-Salsa", "bottle", 3, "0.4"),
    # ---- Raw -> cooked chains (24) ----
    ("raw_ember_sausage", "Raw Ember Sausage", "Rohe Glutwurst", "food", 2, "0.2"),
    ("ember_sausage", "Ember Sausage", "Glutwurst", "food", 6, "0.7"),
    ("raw_magma_steak", "Raw Magma Steak", "Rohes Magmasteak", "food", 3, "0.3"),
    ("magma_steak", "Magma Steak", "Magmasteak", "food", 8, "0.8"),
    ("raw_cinder_bacon", "Raw Cinder Bacon", "Roher Zunderspeck", "food", 2, "0.2"),
    ("cinder_bacon", "Cinder Bacon", "Zunderspeck", "food", 6, "0.6"),
    ("raw_slag_ribs", "Raw Slag Ribs", "Rohe Schlackenrippchen", "food", 3, "0.3"),
    ("slag_ribs", "Slag Ribs", "Schlackenrippchen", "food", 8, "0.8"),
    ("raw_ash_fillet", "Raw Ash Fillet", "Rohes Aschenfilet", "food", 2, "0.1"),
    ("ash_fillet", "Ash Fillet", "Aschenfilet", "food", 5, "0.6"),
    ("raw_lava_eel", "Raw Lava Eel", "Roher Lava-Aal", "food", 2, "0.1"),
    ("grilled_lava_eel", "Grilled Lava Eel", "Gegrillter Lava-Aal", "food", 6, "0.8"),
    ("ember_dough", "Ember Dough", "Glutteig", "food", 1, "0.1"),
    ("ash_biscuit", "Ash Biscuit", "Aschenkeks", "food", 4, "0.5"),
    ("cinder_batter", "Cinder Batter", "Zunder-Backteig", "food", 1, "0.1"),
    ("cinder_pancake", "Cinder Pancake", "Zunder-Pfannkuchen", "food", 5, "0.6"),
    ("magma_kernels", "Magma Kernels", "Magmak\u00f6rner", "food", 1, "0.1"),
    ("magma_popcorn", "Magma Popcorn", "Magma-Popcorn", "always", 3, "0.3"),
    ("molten_marshmallow", "Molten Marshmallow", "Geschmolzenes Marshmallow", "always", 2, "0.2"),
    ("toasted_marshmallow", "Toasted Marshmallow", "Ger\u00f6stetes Marshmallow", "always", 3, "0.4"),
    ("raw_blaze_chop", "Raw Blaze Chop", "Rohes Lohenkotelett", "food", 3, "0.3"),
    ("blaze_chop", "Blaze Chop", "Lohenkotelett", "food", 8, "0.8"),
    ("soot_spud", "Soot Spud", "Ru\u00dfkartoffel", "food", 1, "0.3"),
    ("baked_soot_spud", "Baked Soot Spud", "Gebackene Ru\u00dfkartoffel", "food", 5, "0.6"),
    # ---- Pastries (10) ----
    ("cinder_pie", "Cinder Pie", "Zunderpastete", "food", 5, "0.4"),
    ("magma_tart", "Magma Tart", "Magma-T\u00f6rtchen", "food", 4, "0.4"),
    ("ember_muffin", "Ember Muffin", "Glut-Muffin", "food", 4, "0.4"),
    ("ash_cupcake", "Ash Cupcake", "Aschen-Cupcake", "food", 3, "0.3"),
    ("lava_brownie", "Lava Brownie", "Lava-Brownie", "food", 4, "0.4"),
    ("soot_scone", "Soot Scone", "Ru\u00df-Scone", "food", 3, "0.3"),
    ("flame_fritter", "Flame Fritter", "Flammenkrapfen", "food", 4, "0.4"),
    ("cinder_croissant", "Cinder Croissant", "Zunder-Croissant", "food", 3, "0.4"),
    ("ember_waffle", "Ember Waffle", "Glutwaffel", "food", 4, "0.4"),
    ("magma_donut", "Magma Donut", "Magma-Donut", "food", 3, "0.3"),
    # ---- Sweets (11) ----
    ("ember_jerky", "Ember Jerky", "Glut-D\u00f6rrfleisch", "food", 4, "0.6"),
    ("cinder_toffee", "Cinder Toffee", "Zunderkaramell", "always", 2, "0.2"),
    ("magma_fudge", "Magma Fudge", "Magma-Konfekt", "always", 3, "0.3"),
    ("ash_licorice", "Ash Licorice", "Aschenlakritz", "always", 2, "0.2"),
    ("ember_brittle", "Ember Brittle", "Glutkrokant", "always", 2, "0.2"),
    ("lava_lollipop", "Lava Lollipop", "Lava-Lutscher", "always", 2, "0.2"),
    ("soot_truffle", "Soot Truffle", "Ru\u00dftr\u00fcffel", "always", 2, "0.3"),
    ("blaze_bonbon", "Blaze Bonbon", "Lohenbonbon", "always", 2, "0.2"),
    ("cinder_chocolate", "Cinder Chocolate", "Zunderschokolade", "always", 3, "0.3"),
    ("molten_caramel", "Molten Caramel", "Geschmolzenes Karamell", "always", 2, "0.3"),
    ("ember_gingerbread", "Ember Gingerbread", "Glutlebkuchen", "always", 3, "0.3"),
    # ---- Savory snacks (15) ----
    ("slag_sandwich", "Slag Sandwich", "Schlacken-Sandwich", "food", 7, "0.7"),
    ("ember_burger", "Ember Burger", "Glutburger", "food", 8, "0.8"),
    ("cinder_taco", "Cinder Taco", "Zunder-Taco", "food", 6, "0.6"),
    ("magma_pizza", "Magma Pizza", "Magma-Pizza", "food", 7, "0.7"),
    ("ash_pretzel", "Ash Pretzel", "Aschenbrezel", "food", 3, "0.3"),
    ("soot_cracker", "Soot Cracker", "Ru\u00dfcracker", "food", 2, "0.2"),
    ("ember_omelette", "Ember Omelette", "Glutomelett", "food", 5, "0.6"),
    ("lava_noodles", "Lava Noodles", "Lava-Nudeln", "food", 4, "0.4"),
    ("cinder_dumpling", "Cinder Dumpling", "Zunderkn\u00f6del", "food", 4, "0.5"),
    ("blaze_kebab", "Blaze Kebab", "Lohen-Kebab", "food", 7, "0.7"),
    ("ember_skewer", "Ember Skewer", "Glutspie\u00df", "food", 6, "0.6"),
    ("magma_meatballs", "Magma Meatballs", "Magma-Fleischb\u00e4llchen", "food", 6, "0.7"),
    ("ash_falafel", "Ash Falafel", "Aschenfalafel", "food", 4, "0.5"),
    ("cinder_quiche", "Cinder Quiche", "Zunder-Quiche", "food", 6, "0.6"),
    ("slag_burrito", "Slag Burrito", "Schlacken-Burrito", "food", 8, "0.8"),
    # ---- Fire-kissed produce (8) ----
    ("ember_fig", "Ember Fig", "Glutfeige", "food", 3, "0.4"),
    ("cinder_plum", "Cinder Plum", "Zunderpflaume", "food", 3, "0.4"),
    ("magma_melon_slice", "Magma Melon Slice", "Magmamelonenscheibe", "food", 2, "0.3"),
    ("ash_date", "Ash Date", "Aschendattel", "food", 2, "0.3"),
    ("scorched_grapes", "Scorched Grapes", "Versengte Trauben", "food", 2, "0.3"),
    ("flame_raisins", "Flame Raisins", "Flammenrosinen", "always", 2, "0.3"),
    ("soot_olives", "Soot Olives", "Ru\u00dfoliven", "always", 2, "0.2"),
    ("ember_pickle", "Ember Pickle", "Glutgurke", "food", 2, "0.3"),
]

IDS = [i[0] for i in ITEMS]
EN = {i[0]: i[1] for i in ITEMS}
DE = {i[0]: i[2] for i in ITEMS}
KIND = {i[0]: i[3] for i in ITEMS}
NUTRITION = {i[0]: (i[4], i[5]) for i in ITEMS}

# ---------------------------------------------------------------------------
# Textures: item id -> painter(rng). Each painter is seeded via rng_for(id).
# ---------------------------------------------------------------------------
TEX = {
    # Drinks: bottle(liquid, cap).
    "magma_cola": lambda r: t_bottle(r, (150, 40, 16), MAGMA),
    "ember_tea": lambda r: t_bottle(r, (196, 120, 40), EMBER),
    "lava_latte": lambda r: t_bottle(r, (222, 178, 128), LAVA),
    "cinder_cider": lambda r: t_bottle(r, (214, 150, 52), ASH),
    "soot_smoothie": lambda r: t_bottle(r, (150, 60, 80), ASH_DARK),
    "blaze_brew": lambda r: t_bottle(r, (232, 120, 24), EMBER_HOT),
    "flame_nectar": lambda r: t_bottle(r, (240, 168, 40), EMBER_DEEP),
    "ashen_ale": lambda r: t_bottle(r, (198, 150, 70), ASH_LIGHT),
    "molten_mocha": lambda r: t_bottle(r, (110, 70, 40), MAGMA),
    "ember_espresso": lambda r: t_bottle(r, (70, 44, 28), EMBER),
    "inferno_punch": lambda r: t_bottle(r, (208, 52, 36), EMBER_HOT),
    "smoke_soda": lambda r: t_bottle(r, (170, 168, 172), CHAR),
    "slag_shake": lambda r: t_bottle(r, (196, 186, 178), (120, 116, 110)),
    "obsidian_oolong": lambda r: t_bottle(r, (60, 40, 70), (30, 20, 40)),
    "charcoal_lemonade": lambda r: t_bottle(r, (230, 214, 120), CHAR),
    # Bowls: bowl(soup, chunk).
    "magma_soup": lambda r: t_bowl(r, LAVA, EMBER_HOT),
    "slag_stew": lambda r: t_bowl(r, (140, 120, 104), MAGMA),
    "cinder_chowder": lambda r: t_bowl(r, (232, 222, 200), ASH),
    "blaze_broth": lambda r: t_bowl(r, (240, 160, 60), EMBER_HOT),
    "ember_porridge": lambda r: t_bowl(r, (222, 196, 150), EMBER),
    "ash_gruel": lambda r: t_bowl(r, ASH_LIGHT, ASH),
    "lava_ramen": lambda r: t_bowl(r, (240, 140, 60), CREAM),
    "coal_curry": lambda r: t_bowl(r, (170, 120, 50), CHAR),
    "scorched_goulash": lambda r: t_bowl(r, (160, 60, 40), STEM),
    "furnace_fondue": lambda r: t_bowl(r, (246, 224, 160), EMBER),
    # Jars: jar(fill, lid).
    "magma_marmalade": lambda r: t_jar(r, (240, 140, 40), CHAR),
    "ember_honey": lambda r: t_jar(r, (240, 180, 60), BOWL_WOOD),
    "cinder_syrup": lambda r: t_jar(r, (120, 100, 90), (154, 160, 166)),
    "ash_yogurt": lambda r: t_jar(r, (238, 234, 226), ASH),
    "flame_chutney": lambda r: t_jar(r, (200, 90, 30), STEM),
    "cinder_kimchi": lambda r: t_jar(r, (210, 80, 50), ASH_DARK),
    "magma_salsa": lambda r: t_jar(r, (200, 50, 30), CHAR),
    # Meats & chains.
    "raw_ember_sausage": lambda r: t_sausage(r, MEAT_RAW),
    "ember_sausage": lambda r: t_sausage(r, MEAT_COOKED),
    "raw_magma_steak": lambda r: t_steak(r, MEAT_RAW, MEAT_RAW_DARK),
    "magma_steak": lambda r: t_steak(r, MEAT_COOKED, MEAT_COOKED_DARK),
    "raw_cinder_bacon": lambda r: t_strips(r, MEAT_RAW, CREAM),
    "cinder_bacon": lambda r: t_strips(r, MEAT_COOKED, (222, 186, 132)),
    "raw_slag_ribs": lambda r: t_ribs(r, MEAT_RAW, MEAT_RAW_DARK),
    "slag_ribs": lambda r: t_ribs(r, MEAT_COOKED, MEAT_COOKED_DARK),
    "raw_ash_fillet": lambda r: t_fillet(r, (234, 190, 180), (214, 150, 140)),
    "ash_fillet": lambda r: t_fillet(r, (216, 168, 110), (176, 128, 74)),
    "raw_lava_eel": lambda r: t_fillet(r, (226, 130, 110), (180, 90, 80)),
    "grilled_lava_eel": lambda r: t_fillet(r, (190, 120, 60), (146, 86, 40)),
    "ember_dough": lambda r: t_blob(r, (226, 202, 156), EMBER),
    "ash_biscuit": lambda r: t_round(r, TAN, TAN_DARK, ASH, 5),
    "cinder_batter": lambda r: t_blob(r, (238, 220, 176), ASH),
    "cinder_pancake": lambda r: t_pancake(r, (222, 172, 100), EMBER_DEEP),
    "magma_kernels": lambda r: t_cluster(r, (238, 196, 88), MAGMA, 7, 1),
    "magma_popcorn": lambda r: t_cluster(r, CREAM, (222, 186, 132), 5, 2),
    "molten_marshmallow": lambda r: t_cube(r, (246, 242, 234), GLASS_HI, drip=LAVA),
    "toasted_marshmallow": lambda r: t_cube(r, (238, 214, 168), (246, 242, 234), drip=None),
    "raw_blaze_chop": lambda r: t_steak(r, (222, 120, 100), (180, 84, 70)),
    "blaze_chop": lambda r: t_steak(r, (176, 108, 48), (132, 76, 32)),
    "soot_spud": lambda r: t_spud(r, (168, 158, 146), ASH_DARK),
    "baked_soot_spud": lambda r: t_spud(r, (210, 168, 96), MEAT_COOKED_DARK),
    # Pastries.
    "cinder_pie": lambda r: t_wedge(r, TAN, (200, 80, 40), top=EMBER_HOT),
    "magma_tart": lambda r: t_wedge(r, TAN_DARK, (240, 140, 40), top=None),
    "ember_muffin": lambda r: t_muffin(r, (176, 128, 74), (206, 150, 84), EMBER),
    "ash_cupcake": lambda r: t_muffin(r, ASH, CREAM, (226, 60, 70)),
    "lava_brownie": lambda r: t_cube(r, CHOC, (140, 90, 56), drip=LAVA),
    "soot_scone": lambda r: t_round(r, (222, 196, 150), TAN_DARK, ASH_DARK, 5),
    "flame_fritter": lambda r: t_blob(r, (226, 168, 74), EMBER_DEEP),
    "cinder_croissant": lambda r: t_croissant(r, (216, 160, 88), (170, 116, 56)),
    "ember_waffle": lambda r: t_waffle(r, (226, 178, 96), (176, 128, 56)),
    "magma_donut": lambda r: t_donut(r, (216, 168, 100), MAGMA),
    # Sweets.
    "ember_jerky": lambda r: t_strips(r, (140, 78, 44), (104, 54, 28)),
    "cinder_toffee": lambda r: t_cube(r, (198, 138, 64), (232, 182, 110)),
    "magma_fudge": lambda r: t_cube(r, (128, 78, 46), EMBER),
    "ash_licorice": lambda r: t_sticks(r, (58, 52, 54), ASH_LIGHT),
    "ember_brittle": lambda r: t_bar(r, (232, 168, 72), EMBER_DEEP),
    "lava_lollipop": lambda r: t_lollipop(r, LAVA, EMBER_HOT),
    "soot_truffle": lambda r: t_cluster(r, (78, 52, 36), ASH, 3, 2),
    "blaze_bonbon": lambda r: t_wrapped(r, (240, 130, 30), ASH_LIGHT),
    "cinder_chocolate": lambda r: t_bar(r, CHOC, (66, 40, 24)),
    "molten_caramel": lambda r: t_cube(r, (222, 152, 54), EMBER_HOT, drip=(240, 176, 70)),
    "ember_gingerbread": lambda r: t_round(r, (168, 108, 52), (128, 78, 36), CREAM, 4),
    # Savory.
    "slag_sandwich": lambda r: t_stack(r, BREAD, (140, 120, 104), (176, 170, 162)),
    "ember_burger": lambda r: t_stack(r, (222, 168, 92), MEAT_COOKED_DARK, (240, 196, 80)),
    "cinder_taco": lambda r: t_wrap(r, (232, 190, 100), MEAT_COOKED),
    "magma_pizza": lambda r: t_round(r, (238, 196, 110), (196, 140, 66), MAGMA, 6),
    "ash_pretzel": lambda r: t_pretzel(r, (166, 108, 52), GLASS_HI),
    "soot_cracker": lambda r: t_round(r, (226, 196, 140), TAN_DARK, CHAR, 6),
    "ember_omelette": lambda r: t_omelette(r, (240, 210, 120), EMBER),
    "lava_noodles": lambda r: t_noodles(r, (238, 204, 120), MAGMA),
    "cinder_dumpling": lambda r: t_blob(r, (236, 226, 202), ASH),
    "blaze_kebab": lambda r: t_skewer(r, (176, 108, 48), (132, 76, 32), 3),
    "ember_skewer": lambda r: t_skewer(r, MEAT_COOKED, MEAT_COOKED_DARK, 2),
    "magma_meatballs": lambda r: t_cluster(r, (150, 90, 48), (110, 62, 30), 3, 2),
    "ash_falafel": lambda r: t_cluster(r, (150, 128, 70), (108, 92, 48), 3, 2),
    "cinder_quiche": lambda r: t_wedge(r, TAN_DARK, (240, 200, 120), top=None),
    "slag_burrito": lambda r: t_wrap(r, (224, 198, 140), (140, 120, 104)),
    # Produce.
    "ember_fig": lambda r: t_fruit(r, (128, 62, 86), EMBER),
    "cinder_plum": lambda r: t_fruit(r, (110, 60, 130), (170, 120, 190)),
    "magma_melon_slice": lambda r: t_wedge(r, GREEN, (230, 90, 50), top=None),
    "ash_date": lambda r: t_spud(r, (124, 88, 52), (86, 58, 32)),
    "scorched_grapes": lambda r: t_cluster(r, (130, 60, 100), (92, 40, 70), 6, 1),
    "flame_raisins": lambda r: t_cluster(r, (94, 56, 40), (64, 36, 24), 6, 1),
    "soot_olives": lambda r: t_cluster(r, (96, 106, 54), (64, 72, 34), 4, 1),
    "ember_pickle": lambda r: t_spud(r, (110, 142, 62), (78, 104, 42)),
}

# ---------------------------------------------------------------------------
# Recipes + handbook entries. Every recipe's result is one of this feature's own
# ids; several also consume own ids (dough, batter, cooked meats, ...).
# Cross-feature ingredient ids (ember_dust, ash_pile, slag_chunk, ember_berries)
# are committed content with items/<id>.json on disk.
# ---------------------------------------------------------------------------
CI = f"{NS}:"
HANDBOOK = []
RECIPE_COUNT = 0

# German accusative for recipe texts (only masculine "-er" adjectives inflect).
DE_ACC = {"Roher": "Rohen", "Geschmolzener": "Geschmolzenen", "Gegrillter": "Gegrillten"}


def de_acc(name: str) -> str:
    first, _, rest = name.partition(" ")
    if first in DE_ACC and rest:
        return f"{DE_ACC[first]} {rest}"
    return name


def grid_shapeless(ingredients):
    grid = list(ingredients) + [""] * (9 - len(ingredients))
    return grid


def grid_shaped(key, pattern):
    grid = [""] * 9
    for r, row in enumerate(pattern):
        for c, ch in enumerate(row):
            if ch != " ":
                grid[r * 3 + c] = key[ch]
    return grid


def hb_craft(name, grid, result, count):
    HANDBOOK.append(("items", f"infernocuisine_{name}", f"{CI}{result}",
                     f"infernocuisine/{name}", grid, f"{CI}{result}", count,
                     f"Craft {count}x {EN[result]} at a crafting table.",
                     f"Stellt {count}x {de_acc(DE[result])} an der Werkbank her."))


def r_shapeless(item_id, ingredients, count=1):
    global RECIPE_COUNT
    genlib.emit_shapeless(RECIPES, item_id, list(ingredients), f"{CI}{item_id}",
                          count, category="misc")
    hb_craft(item_id, grid_shapeless(ingredients), item_id, count)
    RECIPE_COUNT += 1


def r_shaped(item_id, key, pattern, count=1):
    global RECIPE_COUNT
    genlib.emit_shaped(RECIPES, item_id, key, list(pattern), f"{CI}{item_id}",
                       count, category="misc")
    hb_craft(item_id, grid_shaped(key, pattern), item_id, count)
    RECIPE_COUNT += 1


def r_cooked(raw_id, cooked_id):
    """Furnace smelting + campfire variant (vanilla baked_potato templates)."""
    global RECIPE_COUNT
    center = ["", "", "", "", f"{CI}{raw_id}", "", "", "", ""]
    write_json(RECIPES / f"{cooked_id}.json", {
        "type": "minecraft:smelting", "category": "food", "cookingtime": 200,
        "experience": 0.35, "ingredient": f"{CI}{raw_id}",
        "result": {"id": f"{CI}{cooked_id}"},
    })
    HANDBOOK.append(("items", f"infernocuisine_{cooked_id}", f"{CI}{cooked_id}",
                     f"infernocuisine/{cooked_id}", center, f"{CI}{cooked_id}", 1,
                     f"Smelt {EN[raw_id]} in a furnace into {EN[cooked_id]}.",
                     f"{de_acc(DE[raw_id])} im Ofen garen, um {de_acc(DE[cooked_id])} zu erhalten."))
    name = f"{cooked_id}_from_campfire_cooking"
    write_json(RECIPES / f"{name}.json", {
        "type": "minecraft:campfire_cooking", "category": "food", "cookingtime": 600,
        "experience": 0.35, "ingredient": f"{CI}{raw_id}",
        "result": {"id": f"{CI}{cooked_id}"},
    })
    HANDBOOK.append(("items", f"infernocuisine_{name}", f"{CI}{cooked_id}",
                     f"infernocuisine/{name}", center, f"{CI}{cooked_id}", 1,
                     f"A campfire also cooks {EN[raw_id]} - slower, but without fuel.",
                     f"Auch das Lagerfeuer gart {de_acc(DE[raw_id])} - langsamer, aber ohne Brennstoff."))
    RECIPE_COUNT += 2


def emit_recipes():
    mc = "minecraft:"
    dust = f"{CI}ember_dust"        # committed (materials)
    ash = f"{CI}ash_pile"           # committed (infernoflora)
    slag = f"{CI}slag_chunk"        # committed (inferno)
    berries = f"{CI}ember_berries"  # committed (infernofoods)

    # ---- Drinks: bottle + flavorings ----
    r_shapeless("magma_cola", [f"{mc}glass_bottle", f"{mc}sugar", f"{mc}magma_cream"])
    r_shapeless("ember_tea", [f"{mc}glass_bottle", f"{mc}dried_kelp", dust])
    r_shapeless("lava_latte", [f"{mc}glass_bottle", f"{mc}milk_bucket", f"{mc}blaze_powder"])
    r_shapeless("cinder_cider", [f"{mc}glass_bottle", f"{mc}apple", ash])
    r_shapeless("soot_smoothie", [f"{mc}glass_bottle", f"{mc}sweet_berries", f"{mc}charcoal"])
    r_shapeless("blaze_brew", [f"{mc}glass_bottle", f"{mc}blaze_powder", f"{mc}nether_wart"])
    r_shapeless("flame_nectar", [f"{mc}glass_bottle", f"{mc}honey_bottle", f"{mc}blaze_powder"])
    r_shapeless("ashen_ale", [f"{mc}glass_bottle", f"{mc}wheat", ash])
    r_shapeless("molten_mocha", [f"{mc}glass_bottle", f"{mc}cocoa_beans", f"{mc}magma_cream"])
    r_shapeless("ember_espresso", [f"{mc}glass_bottle", f"{mc}cocoa_beans", dust])
    r_shapeless("inferno_punch", [f"{mc}glass_bottle", f"{mc}glow_berries",
                                  f"{mc}sweet_berries", f"{mc}blaze_powder"])
    r_shapeless("smoke_soda", [f"{mc}glass_bottle", f"{mc}sugar", f"{mc}charcoal"])
    r_shapeless("slag_shake", [f"{mc}glass_bottle", f"{mc}milk_bucket", slag])
    r_shapeless("obsidian_oolong", [f"{mc}glass_bottle", f"{mc}dried_kelp", f"{mc}obsidian"])
    r_shapeless("charcoal_lemonade", [f"{mc}glass_bottle", f"{mc}sugar", f"{mc}charcoal",
                                      f"{mc}glow_berries"])

    # ---- Bowl meals ----
    r_shapeless("magma_soup", [f"{mc}bowl", f"{mc}magma_cream", f"{mc}red_mushroom"])
    r_shapeless("slag_stew", [f"{mc}bowl", slag, f"{mc}potato", f"{mc}carrot"])
    r_shapeless("cinder_chowder", [f"{mc}bowl", f"{mc}cod", ash])
    r_shapeless("blaze_broth", [f"{mc}bowl", f"{mc}blaze_powder", f"{mc}chicken"])
    r_shapeless("ember_porridge", [f"{mc}bowl", f"{mc}wheat", dust])
    r_shapeless("ash_gruel", [f"{mc}bowl", f"{mc}wheat", ash])
    r_shapeless("lava_ramen", [f"{mc}bowl", f"{CI}lava_noodles", f"{mc}blaze_powder"])
    r_shapeless("coal_curry", [f"{mc}bowl", f"{mc}charcoal", f"{mc}potato", f"{mc}carrot"])
    r_shapeless("scorched_goulash", [f"{mc}bowl", f"{mc}beef", f"{mc}beetroot"])
    r_shapeless("furnace_fondue", [f"{mc}bowl", f"{mc}milk_bucket", dust])

    # ---- Bottled preserves ----
    r_shapeless("magma_marmalade", [f"{mc}glass_bottle", f"{mc}glow_berries",
                                    f"{mc}glow_berries", f"{mc}sugar"])
    r_shapeless("ember_honey", [f"{mc}glass_bottle", f"{mc}honey_bottle", dust])
    r_shapeless("cinder_syrup", [f"{mc}glass_bottle", f"{mc}sugar", f"{mc}sugar", ash])
    r_shapeless("ash_yogurt", [f"{mc}glass_bottle", f"{mc}milk_bucket", ash])
    r_shapeless("flame_chutney", [f"{mc}glass_bottle", f"{mc}apple", f"{mc}blaze_powder",
                                  f"{mc}sugar"])
    r_shapeless("cinder_kimchi", [f"{mc}glass_bottle", f"{mc}beetroot", ash, f"{mc}sugar"])
    r_shapeless("magma_salsa", [f"{mc}glass_bottle", f"{mc}beetroot", f"{mc}magma_cream",
                                f"{mc}blaze_powder"])

    # ---- Raw cuts & bases ----
    r_shapeless("raw_ember_sausage", [f"{mc}porkchop", dust, f"{mc}string"], count=2)
    r_shapeless("raw_magma_steak", [f"{mc}beef", f"{mc}magma_cream"])
    r_shapeless("raw_cinder_bacon", [f"{mc}porkchop", ash], count=2)
    r_shapeless("raw_slag_ribs", [f"{mc}mutton", slag])
    r_shapeless("raw_ash_fillet", [f"{mc}cod", ash])
    r_shapeless("raw_lava_eel", [f"{mc}salmon", f"{mc}magma_cream"])
    r_shapeless("ember_dough", [f"{mc}wheat", f"{mc}wheat", dust], count=2)
    r_shapeless("cinder_batter", [f"{mc}wheat", f"{mc}egg", f"{mc}milk_bucket", ash], count=2)
    r_shapeless("magma_kernels", [f"{mc}wheat_seeds", f"{mc}magma_cream"], count=2)
    r_shapeless("molten_marshmallow", [f"{mc}sugar", f"{mc}sugar", f"{mc}slime_ball",
                                       f"{mc}blaze_powder"], count=3)
    r_shapeless("raw_blaze_chop", [f"{mc}porkchop", f"{mc}blaze_powder"])
    r_shapeless("soot_spud", [f"{mc}potato", f"{mc}charcoal"], count=2)

    # ---- Furnace + campfire cooking chains ----
    r_cooked("raw_ember_sausage", "ember_sausage")
    r_cooked("raw_magma_steak", "magma_steak")
    r_cooked("raw_cinder_bacon", "cinder_bacon")
    r_cooked("raw_slag_ribs", "slag_ribs")
    r_cooked("raw_ash_fillet", "ash_fillet")
    r_cooked("raw_lava_eel", "grilled_lava_eel")
    r_cooked("ember_dough", "ash_biscuit")
    r_cooked("cinder_batter", "cinder_pancake")
    r_cooked("magma_kernels", "magma_popcorn")
    r_cooked("molten_marshmallow", "toasted_marshmallow")
    r_cooked("raw_blaze_chop", "blaze_chop")
    r_cooked("soot_spud", "baked_soot_spud")

    # ---- Pastries ----
    r_shapeless("cinder_pie", [f"{CI}ember_dough", f"{mc}sugar", f"{mc}egg", berries])
    r_shapeless("magma_tart", [f"{CI}ember_dough", f"{mc}glow_berries", f"{mc}sugar"])
    r_shapeless("ember_muffin", [f"{CI}ember_dough", f"{mc}sweet_berries", f"{mc}egg"])
    r_shapeless("ash_cupcake", [f"{CI}cinder_batter", f"{mc}sugar", ash])
    r_shapeless("lava_brownie", [f"{CI}cinder_batter", f"{mc}cocoa_beans", f"{mc}magma_cream"])
    r_shapeless("soot_scone", [f"{CI}ember_dough", f"{mc}charcoal", f"{mc}sugar"], count=2)
    r_shapeless("flame_fritter", [f"{CI}cinder_batter", f"{mc}apple", f"{mc}blaze_powder"],
                count=2)
    r_shapeless("cinder_croissant", [f"{CI}ember_dough", f"{CI}ember_dough",
                                     f"{mc}milk_bucket"], count=3)
    r_shapeless("ember_waffle", [f"{CI}cinder_batter", f"{mc}honey_bottle"], count=2)
    r_shaped("magma_donut", {"#": f"{CI}ember_dough"}, ["###", "# #", "###"], count=6)

    # ---- Sweets ----
    r_shapeless("ember_jerky", [f"{CI}magma_steak", dust], count=3)
    r_shapeless("cinder_toffee", [f"{mc}sugar", f"{mc}sugar", f"{mc}milk_bucket", ash],
                count=4)
    r_shapeless("magma_fudge", [f"{mc}cocoa_beans", f"{mc}sugar", f"{mc}milk_bucket",
                                f"{mc}magma_cream"], count=4)
    r_shapeless("ash_licorice", [f"{mc}sugar", ash, f"{mc}slime_ball"], count=3)
    r_shapeless("ember_brittle", [f"{mc}sugar", f"{mc}sugar", f"{mc}sugar", dust], count=3)
    r_shapeless("lava_lollipop", [f"{mc}sugar", f"{mc}magma_cream", f"{mc}stick"], count=2)
    r_shapeless("soot_truffle", [f"{mc}cocoa_beans", f"{mc}cocoa_beans", f"{mc}charcoal"],
                count=3)
    r_shapeless("blaze_bonbon", [f"{mc}sugar", f"{mc}blaze_powder", f"{mc}honey_bottle"],
                count=4)
    r_shapeless("cinder_chocolate", [f"{mc}cocoa_beans", f"{mc}cocoa_beans",
                                     f"{mc}milk_bucket", ash], count=2)
    r_shapeless("molten_caramel", [f"{mc}sugar", f"{mc}sugar", f"{mc}sugar",
                                   f"{mc}magma_cream"], count=3)
    r_shapeless("ember_gingerbread", [f"{CI}ember_dough", f"{mc}honey_bottle", dust],
                count=2)

    # ---- Savory snacks ----
    r_shaped("slag_sandwich", {"B": f"{mc}bread", "X": f"{CI}slag_ribs"}, ["B", "X", "B"])
    r_shaped("ember_burger", {"B": f"{mc}bread", "X": f"{CI}magma_steak"}, ["B", "X", "B"])
    r_shapeless("cinder_taco", [f"{mc}bread", f"{CI}cinder_bacon", ash])
    r_shapeless("magma_pizza", [f"{CI}ember_dough", f"{mc}milk_bucket", f"{mc}magma_cream",
                                f"{mc}red_mushroom"])
    r_shapeless("ash_pretzel", [f"{CI}ember_dough", ash], count=2)
    r_shapeless("soot_cracker", [f"{mc}wheat", f"{mc}charcoal"], count=4)
    r_shapeless("ember_omelette", [f"{mc}egg", f"{mc}egg", dust])
    r_shapeless("lava_noodles", [f"{mc}wheat", f"{mc}wheat", f"{mc}egg",
                                 f"{mc}magma_cream"], count=2)
    r_shapeless("cinder_dumpling", [f"{CI}ember_dough", f"{mc}porkchop"], count=2)
    r_shaped("blaze_kebab", {"M": f"{CI}blaze_chop", "S": f"{mc}stick"}, ["M", "M", "S"])
    r_shaped("ember_skewer", {"M": f"{CI}ember_sausage", "S": f"{mc}stick"}, ["M", "S"])
    r_shapeless("magma_meatballs", [f"{mc}beef", f"{mc}egg", f"{mc}magma_cream"], count=3)
    r_shapeless("ash_falafel", [f"{mc}wheat_seeds", f"{mc}wheat_seeds", ash], count=3)
    r_shapeless("cinder_quiche", [f"{CI}ember_dough", f"{mc}egg", f"{mc}milk_bucket", ash])
    r_shapeless("slag_burrito", [f"{mc}bread", f"{CI}slag_ribs", f"{CI}magma_salsa"])

    # ---- Fire-kissed produce ----
    r_shapeless("ember_fig", [f"{mc}sweet_berries", dust], count=2)
    r_shapeless("cinder_plum", [f"{mc}apple", ash], count=2)
    r_shapeless("magma_melon_slice", [f"{mc}melon_slice", f"{mc}magma_cream"], count=2)
    r_shapeless("ash_date", [f"{mc}sweet_berries", ash], count=2)
    r_shapeless("scorched_grapes", [f"{mc}sweet_berries", f"{mc}sweet_berries",
                                    f"{mc}blaze_powder"], count=2)
    r_shapeless("flame_raisins", [f"{CI}scorched_grapes"], count=2)
    r_shapeless("soot_olives", [f"{mc}glow_berries", f"{mc}charcoal"], count=2)
    r_shapeless("ember_pickle", [f"{mc}sea_pickle", dust], count=2)


# ---------------------------------------------------------------------------
# Java codegen (genlib.java_feature_class / java_handbook_class; literal ids only).
# Food builder chains verified via javap (see module docstring); every registration
# constructs a FRESH Item.Settings + FoodComponent.Builder.
# ---------------------------------------------------------------------------

def settings_expr(item_id: str) -> str:
    n, s = NUTRITION[item_id]
    kind = KIND[item_id]
    base = f"new FoodComponent.Builder().nutrition({n}).saturationModifier({s}f)"
    if kind in ("always", "drink"):
        base += ".alwaysEdible()"
    base += ".build()"
    if kind == "drink":
        return ("new Item.Settings().maxCount(16).useRemainder(Items.GLASS_BOTTLE)"
                f".food({base}, ConsumableComponents.drink().build())")
    if kind == "bowl":
        return f"new Item.Settings().maxCount(1).useRemainder(Items.BOWL).food({base})"
    if kind == "bottle":
        return f"new Item.Settings().maxCount(16).useRemainder(Items.GLASS_BOTTLE).food({base})"
    return f"new Item.Settings().food({base})"


def emit_java():
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)
    items = [(i.upper(), i, "Item::new", settings_expr(i)) for i in IDS]
    feature_doc = [
        "Inferno Cuisine: 100 food & drink items for the Inferno kitchen - 15 bottled",
        "drinks (glass bottle handed back), 10 bowl meals (bowl handed back), 7 bottled",
        "preserves, 12 raw-to-cooked furnace/campfire chains, 10 pastries, 11 sweets,",
        "15 savory snacks and 8 fire-kissed fruits.",
        "",
        "<p>Follows the verified 1.21.9 consumable pattern from",
        "{@code feature/foods/FoodsFeature} / {@code feature/infernofoods/InfernoFoodsFeature}:",
        "{@code FoodComponent.Builder} for hunger/saturation,",
        "{@code ConsumableComponents.drink()} for bottled drinks and",
        "{@code Item.Settings.useRemainder(...)} for container returns. Every registration",
        "constructs fresh settings.",
        "",
        "<p>Assets (items/*.json + models/item/*.json + 16x16 textures), recipes",
        "({@code data/copper_inferno/recipe/infernocuisine/}), the EN+DE lang fragments and",
        "this class itself are generated by {@code devtools/gen/infernocuisine_gen.py};",
        "handbook pages are registered by {@link InfernoCuisineHandbook}.",
    ]
    feature_src = genlib.java_feature_class(
        "infernocuisine", "InfernoCuisineFeature", feature_doc,
        items=items,
        tabs=[("FOODS_KEY", [i.upper() for i in IDS])],
        extra_imports=("net.minecraft.component.type.ConsumableComponents",
                       "net.minecraft.component.type.FoodComponent",
                       "net.minecraft.item.Items"),
        handbook_class="InfernoCuisineHandbook",
    )
    (FEATURE_DIR / "InfernoCuisineFeature.java").write_text(feature_src, encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the Inferno Cuisine feature: one \"items\" overview plus one",
        "recipe page for every JSON under {@code data/copper_inferno/recipe/infernocuisine/}",
        "(crafting, smelting and campfire cooking). Entry texts and grids mirror the recipe",
        "JSONs emitted by {@code devtools/gen/infernocuisine_gen.py};",
        "{@code devtools/check_handbook.py} parses the inline {@code new HandbookEntry(...)}",
        "literals positionally, so keep them inline.",
    ]
    handbook_src = genlib.java_handbook_class("infernocuisine", "InfernoCuisineHandbook",
                                              handbook_doc, HANDBOOK)
    (FEATURE_DIR / "InfernoCuisineHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/infernocuisine.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks():
    lines = [
        "# infernocuisine feature hooks (format: devtools/hooks/README.md)", "",
        "[init]",
        "# After MaterialsFeature/InfernoFeature/InfernoFloraFeature/InfernoFoodsFeature:",
        "# recipes consume ember_dust, slag_chunk, ash_pile and ember_berries.",
        "import net.sonic0810.copperinferno.feature.infernocuisine.InfernoCuisineFeature;",
        "\t\tInfernoCuisineFeature.init();", "",
        "[recipe-dir]",
        "infernocuisine", "",
        "[counts]",
        f"items: {len(IDS)}",
        f"recipes: {RECIPE_COUNT}",
        f"handbook-entries: {len(HANDBOOK)}", "",
    ]
    path = ROOT / "devtools" / "hooks" / "infernocuisine.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main():
    assert len(IDS) == 100, f"expected 100 item ids, got {len(IDS)}"
    assert len(set(IDS)) == 100, "duplicate item ids"
    assert set(IDS) == set(TEX), "TEX table out of sync with ITEMS"

    # Item defs, models, textures.
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for item_id in IDS:
        genlib.emit_item_def(ASSETS, item_id)
        genlib.emit_item_model(ASSETS, item_id)
        TEX[item_id](rng_for(item_id)).save(tex_dir / f"{item_id}.png")

    # Overview handbook page FIRST, then one page per recipe (appended by emit_recipes).
    HANDBOOK.append((
        "items", "infernocuisine_overview", f"{CI}cinder_pie", None, None, None, 0,
        "The Inferno kitchen serves 100 dishes and drinks: bottled sodas and brews in "
        "returnable bottles, steaming bowl meals (the bowl comes back), jams and syrups, "
        "raw cuts that roast in furnace or campfire, pastries, candies, hearty snacks "
        "and fire-kissed fruit.",
        "Die Inferno-K\u00fcche serviert 100 Gerichte und Getr\u00e4nke: Limonaden und "
        "Sude in Pfandflaschen, dampfende Sch\u00fcsselgerichte (die Sch\u00fcssel kommt "
        "zur\u00fcck), Marmeladen und Sirupe, rohe St\u00fccke zum R\u00f6sten in Ofen "
        "oder Lagerfeuer, Geb\u00e4ck, S\u00fc\u00dfigkeiten, deftige Snacks und "
        "feuergek\u00fcsste Fr\u00fcchte."))
    emit_recipes()

    # Lang fragments (EN + real German).
    lang_en = {f"item.{NS}.{i}": EN[i] for i in IDS}
    lang_de = {f"item.{NS}.{i}": DE[i] for i in IDS}
    genlib.lang_fragments(ASSETS, "infernocuisine", lang_en, lang_de)

    emit_java()
    emit_hooks()

    # Consistency asserts.
    assert sorted(lang_en) == sorted(lang_de) == sorted(f"item.{NS}.{i}" for i in IDS)
    assert RECIPE_COUNT == 112, f"expected 112 recipes, got {RECIPE_COUNT}"
    assert len(HANDBOOK) == 113, f"expected 113 handbook entries, got {len(HANDBOOK)}"
    for item_id in IDS:
        assert (ASSETS / "items" / f"{item_id}.json").is_file()
        assert (ASSETS / "models" / "item" / f"{item_id}.json").is_file()
        assert (tex_dir / f"{item_id}.png").is_file()
    print(f"infernocuisine_gen: {len(IDS)} items (defs, models, textures), "
          f"{RECIPE_COUNT} recipes, {len(HANDBOOK)} handbook entries, "
          "EN+DE lang fragments, Feature+Handbook java, hook file.")


if __name__ == "__main__":
    main()
