#!/usr/bin/env python3
"""Asset generator for the FOODS feature (COPPER INFERNO 1 v2).

Emits, for each of the 18 food/drink items, directly into src/main/resources:
  - assets/copper_inferno/items/<id>.json           (1.21.9 item model definition)
  - assets/copper_inferno/models/item/<id>.json     (item/generated + layer0)
  - assets/copper_inferno/textures/item/<id>.png    (deterministic 16x16 Pillow art)
  - data/copper_inferno/recipe/foods/<id>.json      (crafting recipe)
  - assets/copper_inferno/lang/fragments/foods.json (lang fragment, this feature only)

JSON formats copied from EXACT vanilla 1.21.9 templates extracted from
~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar:
  - crafting_shapeless: fermented_spider_eye.json / pumpkin_pie.json ("#minecraft:eggs" tag form)
  - crafting_shaped:    golden_apple.json (8-ingot ring) / cookie.json ("#X#" row)
Item definition / model JSON mirror the existing copper_inferno:dr_pepper item (v1).

Idempotent: pure functions of the tables below, no RNG; re-running produces
byte-identical output.

Usage: python3 devtools/gen/foods_gen.py
"""

import json
from pathlib import Path

from PIL import Image

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / "copper_inferno"
DATA = RES / "data" / "copper_inferno"
MOD = "copper_inferno"

# ---------------------------------------------------------------------------
# Palette
# ---------------------------------------------------------------------------
GLASS = (208, 224, 232)          # bottle glass outline
GLASS_HI = (244, 250, 252)       # glass highlight
MAROON = (90, 14, 20)            # Dr.Pepper maroon (v1 DR_PEPPER_COLOR 0x5A0E14)
MAROON_LIGHT = (122, 27, 34)
COPPER = (224, 115, 77)          # copper base #E0734D
COPPER_DARK = (193, 90, 59)      # copper shade #C15A3B
COPPER_HI = (247, 168, 130)      # copper sheen
GREEN = (74, 140, 60)            # stems / leaves
CREAM = (248, 240, 214)
TAN = (210, 168, 110)
TAN_DARK = (166, 124, 72)
WHITE = (242, 239, 234)
FOAM = (250, 248, 240)

T = (0, 0, 0, 0)  # transparent


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
# Texture painters (each returns a 16x16 RGBA image)
# ---------------------------------------------------------------------------

def tex_bottle(liquid, cap):
    """Glass bottle with flavor-colored liquid and a colored cap."""
    img = new_img()
    liq_dark = shade(liquid, 0.72)
    # Cap.
    rect(img, 6, 0, 9, 1, cap)
    px(img, 6, 0, shade(cap, 0.75))
    px(img, 9, 0, shade(cap, 0.75))
    # Neck (glass walls).
    for y in (2, 3):
        px(img, 6, y, GLASS)
        px(img, 9, y, GLASS)
        rect(img, 7, y, 8, y, liq_dark)
    # Shoulders.
    px(img, 5, 4, GLASS)
    px(img, 10, 4, GLASS)
    rect(img, 6, 4, 9, 4, liquid)
    # Body rows 5-14: glass walls x4/x11, liquid inside.
    for y in range(5, 14):
        px(img, 4, y, GLASS)
        px(img, 11, y, GLASS)
        rect(img, 5, y, 10, y, liquid)
        px(img, 5, y, liq_dark)
    # Glass highlight streak.
    for y in range(6, 12):
        px(img, 9, y, shade(liquid, 1.45))
    px(img, 10, 5, GLASS_HI)
    # Bottom rim.
    rect(img, 4, 14, 11, 14, GLASS)
    rect(img, 5, 14, 10, 14, liq_dark)
    rect(img, 5, 15, 10, 15, GLASS)
    return img


def tex_jar(syrup):
    """Squat syrup jar with a metal lid."""
    img = new_img()
    dark = shade(syrup, 0.7)
    lid = (154, 160, 166)
    rect(img, 4, 1, 11, 2, lid)
    px(img, 4, 1, shade(lid, 0.75))
    px(img, 11, 1, shade(lid, 0.75))
    rect(img, 3, 3, 12, 3, GLASS)
    for y in range(4, 13):
        px(img, 3, y, GLASS)
        px(img, 12, y, GLASS)
        rect(img, 4, y, 11, y, syrup)
        px(img, 4, y, dark)
    for y in range(5, 11):
        px(img, 10, y, shade(syrup, 1.4))
    rect(img, 3, 13, 12, 13, GLASS)
    rect(img, 4, 13, 11, 13, dark)
    rect(img, 4, 14, 11, 14, GLASS)
    return img


def tex_apple(base, hi, glazed=False):
    """Apple silhouette; copper sheen highlight, optional candy glaze sparkle."""
    img = new_img()
    dark = shade(base, 0.72)
    # Stem + leaf.
    px(img, 8, 1, (94, 62, 32))
    px(img, 8, 2, (94, 62, 32))
    px(img, 9, 2, GREEN)
    px(img, 10, 2, GREEN)
    # Body.
    rect(img, 5, 4, 10, 4, base)
    for y in range(5, 12):
        rect(img, 3, y, 12, y, base)
    rect(img, 4, 12, 11, 12, base)
    rect(img, 5, 13, 7, 13, base)
    rect(img, 8, 13, 10, 13, base)
    # Shading right/bottom.
    for y in range(5, 12):
        px(img, 12, y, dark)
        px(img, 11, y, dark)
    rect(img, 4, 12, 11, 12, dark)
    rect(img, 5, 13, 10, 13, shade(base, 0.6))
    # Top dip.
    px(img, 8, 4, dark)
    # Sheen.
    px(img, 5, 5, hi)
    px(img, 4, 6, hi)
    px(img, 5, 6, hi)
    px(img, 4, 7, hi)
    if glazed:
        # Candy sparkles.
        for x, y in ((7, 6), (9, 8), (6, 10), (10, 5)):
            px(img, x, y, GLASS_HI)
    return img


def tex_ice_cube():
    """Pale blue ice cube with a maroon Dr.Pepper core."""
    img = new_img()
    ice = (168, 214, 240)
    ice_dark = (120, 172, 206)
    rect(img, 3, 3, 12, 12, ice)
    # Outline.
    for x in range(3, 13):
        px(img, x, 3, ice_dark)
        px(img, x, 12, ice_dark)
    for y in range(3, 13):
        px(img, 3, y, ice_dark)
        px(img, 12, y, ice_dark)
    # Maroon core.
    rect(img, 6, 6, 9, 9, MAROON)
    rect(img, 7, 7, 8, 8, MAROON_LIGHT)
    # Highlights.
    px(img, 4, 4, GLASS_HI)
    px(img, 5, 4, GLASS_HI)
    px(img, 4, 5, GLASS_HI)
    px(img, 11, 11, GLASS_HI)
    return img


def tex_crystals():
    """Cluster of fizzy pink-white sugar crystal shards."""
    img = new_img()
    pink = (240, 170, 182)
    pink_dark = (206, 120, 136)
    # Three shards.
    for cx, cy, h in ((5, 12, 6), (9, 13, 8), (12, 12, 5)):
        for i in range(h):
            y = cy - i
            w = 1 if i >= h - 2 else 2
            rect(img, cx - w // 2, y, cx + (w - 1) // 2, y, pink)
            px(img, cx + (w - 1) // 2, y, pink_dark)
    # White glints + fizz dots.
    for x, y in ((5, 8), (9, 7), (12, 9)):
        px(img, x, y, WHITE)
    for x, y in ((3, 4), (8, 2), (13, 5)):
        px(img, x, y, GLASS_HI)
    # Base.
    rect(img, 3, 13, 13, 13, pink_dark)
    return img


def tex_gummy():
    """Maroon gummy-bear blob with jelly shine."""
    img = new_img()
    body = (152, 32, 44)
    dark = shade(body, 0.7)
    hi = (214, 92, 104)
    # Ears/head.
    px(img, 5, 2, body)
    px(img, 10, 2, body)
    rect(img, 5, 3, 10, 5, body)
    # Body.
    rect(img, 4, 6, 11, 11, body)
    # Legs.
    rect(img, 4, 12, 6, 13, body)
    rect(img, 9, 12, 11, 13, body)
    # Arms.
    px(img, 3, 7, body)
    px(img, 12, 7, body)
    # Shading.
    for y in range(6, 12):
        px(img, 11, y, dark)
    rect(img, 9, 12, 11, 13, dark)
    # Jelly shine.
    px(img, 6, 4, hi)
    px(img, 5, 7, hi)
    px(img, 6, 7, hi)
    return img


def tex_float():
    """Tall glass of maroon soda with foam + ice-cream scoop and a straw."""
    img = new_img()
    # Straw.
    px(img, 11, 0, (226, 60, 70))
    px(img, 11, 1, (226, 60, 70))
    px(img, 10, 2, (226, 60, 70))
    # Ice cream scoop poking above the rim.
    rect(img, 5, 2, 8, 3, FOAM)
    px(img, 4, 3, FOAM)
    # Foam band at the top of the glass.
    rect(img, 4, 4, 11, 5, FOAM)
    px(img, 4, 5, shade(FOAM, 0.85))
    # Glass walls + soda.
    for y in range(6, 14):
        px(img, 3, y, GLASS)
        px(img, 12, y, GLASS)
        rect(img, 4, y, 11, y, MAROON)
        px(img, 4, y, shade(MAROON, 0.75))
    # Bubbles.
    px(img, 7, 8, MAROON_LIGHT)
    px(img, 9, 10, MAROON_LIGHT)
    px(img, 6, 11, MAROON_LIGHT)
    # Highlight.
    for y in range(7, 12):
        px(img, 10, y, shade(MAROON, 1.5))
    # Base.
    rect(img, 3, 14, 12, 14, GLASS)
    rect(img, 4, 15, 11, 15, GLASS)
    return img


def tex_cookie():
    """Round tan cookie with maroon Dr.Pepper chips."""
    img = new_img()
    dark = TAN_DARK
    rect(img, 5, 2, 10, 2, TAN)
    rect(img, 3, 3, 12, 4, TAN)
    rect(img, 2, 5, 13, 10, TAN)
    rect(img, 3, 11, 12, 12, TAN)
    rect(img, 5, 13, 10, 13, TAN)
    # Rim shading.
    rect(img, 5, 13, 10, 13, dark)
    for y in range(5, 11):
        px(img, 13, y, dark)
    rect(img, 11, 11, 12, 12, dark)
    # Maroon chips.
    for x, y in ((5, 4), (9, 5), (4, 8), (8, 9), (11, 7), (7, 12)):
        px(img, x, y, MAROON)
        px(img, x + 1, y, MAROON_LIGHT)
    return img


def tex_pepper():
    """Curved red chili pepper with a green stem."""
    img = new_img()
    red = (198, 34, 24)
    red_dark = shade(red, 0.7)
    red_hi = (240, 96, 70)
    # Stem.
    px(img, 10, 1, GREEN)
    px(img, 9, 2, GREEN)
    px(img, 10, 2, shade(GREEN, 0.75))
    # Body curving down-left.
    rect(img, 8, 3, 10, 4, red)
    rect(img, 7, 5, 10, 7, red)
    rect(img, 6, 8, 9, 10, red)
    rect(img, 5, 11, 8, 12, red)
    rect(img, 4, 13, 6, 14, red)
    # Shading along the right edge.
    px(img, 10, 4, red_dark)
    px(img, 10, 6, red_dark)
    px(img, 9, 9, red_dark)
    px(img, 8, 12, red_dark)
    px(img, 6, 14, red_dark)
    # Shine.
    px(img, 8, 4, red_hi)
    px(img, 7, 6, red_hi)
    px(img, 6, 9, red_hi)
    return img


def tex_carrot():
    """Carrot with a copper tint and green top."""
    img = new_img()
    body = (222, 122, 58)  # carrot orange pulled toward copper
    dark = COPPER_DARK
    hi = COPPER_HI
    # Green top.
    px(img, 11, 1, GREEN)
    px(img, 12, 1, GREEN)
    px(img, 12, 2, GREEN)
    px(img, 13, 2, GREEN)
    px(img, 11, 3, GREEN)
    px(img, 12, 3, shade(GREEN, 0.75))
    # Tapered body from top-right to bottom-left.
    rect(img, 9, 4, 12, 5, body)
    rect(img, 7, 6, 11, 7, body)
    rect(img, 5, 8, 9, 9, body)
    rect(img, 3, 10, 7, 11, body)
    rect(img, 2, 12, 4, 13, body)
    # Copper sheen + shading.
    px(img, 10, 4, hi)
    px(img, 8, 6, hi)
    px(img, 6, 8, hi)
    px(img, 4, 10, hi)
    px(img, 12, 5, dark)
    px(img, 11, 7, dark)
    px(img, 9, 9, dark)
    px(img, 7, 11, dark)
    px(img, 4, 13, dark)
    return img


def tex_cake_slice():
    """Triangular cake slice: maroon sponge layers + white frosting."""
    img = new_img()
    sponge = MAROON_LIGHT
    sponge_dark = MAROON
    # Frosting top edge (slice narrows to the left).
    rect(img, 9, 3, 13, 3, FOAM)
    rect(img, 7, 4, 13, 4, FOAM)
    # Sponge widening downward.
    rect(img, 6, 5, 13, 6, sponge)
    rect(img, 5, 7, 13, 7, CREAM)   # cream filling line
    rect(img, 4, 8, 13, 10, sponge)
    rect(img, 3, 11, 13, 11, CREAM)
    rect(img, 2, 12, 13, 13, sponge_dark)
    # Right cut face shading.
    for y in range(5, 14):
        px(img, 13, y, sponge_dark)
    # Cherry on top.
    px(img, 10, 2, (226, 60, 70))
    # Plate.
    rect(img, 1, 14, 14, 14, (200, 204, 210))
    return img


TEXTURES = {
    # Drinks: bottle(liquid color, cap color).
    "cherry_dr_pepper": lambda: tex_bottle((150, 20, 40), (226, 60, 70)),
    "vanilla_dr_pepper": lambda: tex_bottle((124, 62, 32), CREAM),
    "cream_soda": lambda: tex_bottle((226, 164, 54), (250, 232, 196)),
    "dr_pepper_zero": lambda: tex_bottle((28, 16, 16), (154, 160, 166)),
    "grape_soda": lambda: tex_bottle((122, 48, 160), (228, 204, 244)),
    "orange_soda": lambda: tex_bottle((235, 128, 24), (252, 220, 180)),
    # Foods.
    "soda_syrup": lambda: tex_jar(MAROON),
    "caramel_syrup": lambda: tex_jar((196, 120, 34)),
    "copper_apple": lambda: tex_apple(COPPER, COPPER_HI),
    "candied_copper_apple": lambda: tex_apple((238, 148, 96), (255, 208, 168), glazed=True),
    "dr_pepper_ice_cube": tex_ice_cube,
    "fizzy_sugar_crystals": tex_crystals,
    "soda_gummy": tex_gummy,
    "soda_float": tex_float,
    "dr_pepper_cookie": tex_cookie,
    "inferno_pepper": tex_pepper,
    "copper_carrot": tex_carrot,
    "dr_pepper_cake_slice": tex_cake_slice,
}

# ---------------------------------------------------------------------------
# Lang
# ---------------------------------------------------------------------------
LANG = {
    "cherry_dr_pepper": "Cherry Dr.Pepper",
    "vanilla_dr_pepper": "Vanilla Dr.Pepper",
    "cream_soda": "Cream Soda",
    "dr_pepper_zero": "Dr.Pepper Zero",
    "grape_soda": "Grape Soda",
    "orange_soda": "Orange Soda",
    "soda_syrup": "Soda Syrup",
    "caramel_syrup": "Caramel Syrup",
    "copper_apple": "Copper Apple",
    "candied_copper_apple": "Candied Copper Apple",
    "dr_pepper_ice_cube": "Dr.Pepper Ice Cube",
    "fizzy_sugar_crystals": "Fizzy Sugar Crystals",
    "soda_gummy": "Soda Gummy",
    "soda_float": "Soda Float",
    "dr_pepper_cookie": "Dr.Pepper Cookie",
    "inferno_pepper": "Inferno Pepper",
    "copper_carrot": "Copper Carrot",
    "dr_pepper_cake_slice": "Dr.Pepper Cake Slice",
}

# ---------------------------------------------------------------------------
# Recipes (formats copied from vanilla 1.21.9 templates; see module docstring).
# Only vanilla + this feature's own ids are referenced.
# ---------------------------------------------------------------------------


def shapeless(ingredients, result_id, count=1):
    return {
        "type": "minecraft:crafting_shapeless",
        "category": "misc",
        "ingredients": ingredients,
        "result": {"count": count, "id": f"{MOD}:{result_id}"},
    }


def shaped(key, pattern, result_id, count=1):
    return {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": key,
        "pattern": pattern,
        "result": {"count": count, "id": f"{MOD}:{result_id}"},
    }


def drink(flavoring, result_id):
    """Uniform drink recipe: glass bottle + sugar + one vanilla flavoring."""
    return shapeless(["minecraft:glass_bottle", "minecraft:sugar", flavoring], result_id)


RECIPES = {
    # Drinks: bottle + sugar + flavoring (fermented_spider_eye.json shapeless template).
    "cherry_dr_pepper": drink("minecraft:sweet_berries", "cherry_dr_pepper"),        # red cherry-like berries
    "vanilla_dr_pepper": drink("minecraft:milk_bucket", "vanilla_dr_pepper"),        # vanilla cream (bucket returned by vanilla crafting remainder)
    "cream_soda": drink("minecraft:honey_bottle", "cream_soda"),                     # sweet cream (empty bottle returned)
    "dr_pepper_zero": drink("minecraft:cocoa_beans", "dr_pepper_zero"),              # dark cola flavor
    "grape_soda": drink("minecraft:chorus_fruit", "grape_soda"),                     # purple fruit
    "orange_soda": drink("minecraft:glow_berries", "orange_soda"),                   # orange berries
    # Syrups.
    "soda_syrup": shapeless(
        ["minecraft:sugar", "minecraft:sugar", "minecraft:sugar", "minecraft:glass_bottle"],
        "soda_syrup"),
    "caramel_syrup": shapeless(
        [f"{MOD}:soda_syrup", "minecraft:sugar", "minecraft:sugar"],
        "caramel_syrup"),
    # Apples.
    "copper_apple": shaped(  # golden_apple.json template with copper ingots
        {"#": "minecraft:copper_ingot", "X": "minecraft:apple"},
        ["###", "#X#", "###"],
        "copper_apple"),
    "candied_copper_apple": shapeless(
        [f"{MOD}:copper_apple", "minecraft:honey_bottle", "minecraft:sugar"],
        "candied_copper_apple"),
    # Snacks.
    "dr_pepper_ice_cube": shapeless(
        ["minecraft:ice", f"{MOD}:soda_syrup"],
        "dr_pepper_ice_cube", count=4),
    "fizzy_sugar_crystals": shapeless(
        ["minecraft:sugar", "minecraft:sugar", "minecraft:gunpowder"],
        "fizzy_sugar_crystals", count=2),
    "soda_gummy": shapeless(
        [f"{MOD}:soda_syrup", "minecraft:slime_ball"],
        "soda_gummy", count=2),
    "soda_float": shapeless(
        [f"{MOD}:cream_soda", "minecraft:snowball", "minecraft:snowball"],
        "soda_float"),
    "dr_pepper_cookie": shaped(  # cookie.json template with syrup instead of cocoa
        {"#": "minecraft:wheat", "X": f"{MOD}:soda_syrup"},
        ["#X#"],
        "dr_pepper_cookie", count=8),
    "inferno_pepper": shapeless(
        ["minecraft:carrot", "minecraft:blaze_powder"],
        "inferno_pepper"),
    "copper_carrot": shaped(  # golden_apple.json template with a carrot core
        {"#": "minecraft:copper_ingot", "X": "minecraft:carrot"},
        ["###", "#X#", "###"],
        "copper_carrot"),
    "dr_pepper_cake_slice": shapeless(  # pumpkin_pie.json template ("#minecraft:eggs" tag form)
        ["minecraft:wheat", "minecraft:sugar", "#minecraft:eggs", f"{MOD}:soda_syrup"],
        "dr_pepper_cake_slice", count=3),
}

# ---------------------------------------------------------------------------
# Emission
# ---------------------------------------------------------------------------


def write_json(path, obj):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2) + "\n", encoding="utf-8")


def main():
    ids = list(TEXTURES)
    assert set(ids) == set(LANG) == set(RECIPES), "item id tables out of sync"
    assert len(ids) == 18

    for item_id in ids:
        # Item model definition (1.21.9 requires this alongside the model).
        write_json(ASSETS / "items" / f"{item_id}.json", {
            "model": {
                "type": "minecraft:model",
                "model": f"{MOD}:item/{item_id}",
            }
        })
        # Item model.
        write_json(ASSETS / "models" / "item" / f"{item_id}.json", {
            "parent": "minecraft:item/generated",
            "textures": {"layer0": f"{MOD}:item/{item_id}"},
        })
        # Texture.
        tex_path = ASSETS / "textures" / "item" / f"{item_id}.png"
        tex_path.parent.mkdir(parents=True, exist_ok=True)
        TEXTURES[item_id]().save(tex_path)
        # Recipe.
        write_json(DATA / "recipe" / "foods" / f"{item_id}.json", RECIPES[item_id])

    # Lang fragment (this feature's file only; merged into en_us.json by integration).
    write_json(ASSETS / "lang" / "fragments" / "foods.json",
               {f"item.{MOD}.{item_id}": LANG[item_id] for item_id in ids})

    print(f"foods_gen: wrote {len(ids)} items "
          f"({len(ids)} item defs, models, textures, recipes) + lang fragment")


if __name__ == "__main__":
    main()
