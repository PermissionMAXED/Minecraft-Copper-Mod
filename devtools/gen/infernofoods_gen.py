#!/usr/bin/env python3
"""Asset generator for the INFERNOFOODS feature (COPPER INFERNO 1 v3).

Emits, for each of the 9 Inferno-dimension foods, directly into src/main/resources:
  - assets/copper_inferno/items/<id>.json                (1.21.9 item model definition)
  - assets/copper_inferno/models/item/<id>.json          (item/generated + layer0)
  - assets/copper_inferno/textures/item/<id>.png         (deterministic 16x16 Pillow art)
  - data/copper_inferno/recipe/infernofoods/<name>.json  (cooking/crafting recipes)
  - assets/copper_inferno/lang/fragments/infernofoods.json    (EN lang fragment)
  - assets/copper_inferno/lang/fragments_de/infernofoods.json (DE lang fragment)

JSON formats copied from EXACT vanilla 1.21.9 templates extracted from
~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar:
  - smelting/smoking:   baked_potato.json / baked_potato_from_smoking.json
  - crafting_shapeless: mushroom_stew.json
  - crafting_shaped:    golden_apple.json (8-ingot ring) / cookie.json ("#X#" row)
Item definition / model JSON mirror devtools/gen/foods_gen.py (feature/foods).

Idempotent: pure functions of the tables below, no RNG; re-running produces
byte-identical output.

NOTE: JSON emission is scaffolding (opt-in via --write-json); the JSON in
src/main/resources is authoritative — by default this script writes ONLY PNGs.

Usage: python3 devtools/gen/infernofoods_gen.py [--write-json]
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

# ---------------------------------------------------------------------------
# Palette (inferno tones: embers, ash, char)
# ---------------------------------------------------------------------------
EMBER = (255, 148, 42)           # glowing ember orange
EMBER_HOT = (255, 214, 92)       # white-hot ember highlight
EMBER_DEEP = (198, 74, 24)       # deep ember red-orange
CHAR = (52, 40, 38)              # charred crust
ASH = (128, 122, 116)            # ash grey
ASH_LIGHT = (176, 170, 162)      # pale ash
ASH_DARK = (86, 80, 76)          # sooty grey
BERRY = (214, 58, 36)            # ember berry red
BERRY_DARK = (150, 34, 22)
FUNGUS_CAP = (172, 62, 30)       # roasted cap brown-red
FUNGUS_STEM = (216, 178, 128)    # toasted stem
GLASS = (208, 224, 232)          # bottle glass outline (matches foods_gen)
GLASS_HI = (244, 250, 252)       # glass highlight
GREEN = (74, 140, 60)            # stems / leaves
BOWL = (134, 88, 48)             # wooden bowl
BOWL_DARK = (100, 64, 34)
STRIDER = (178, 62, 66)          # strider red
STRIDER_DARK = (128, 40, 46)
SMOKE = (150, 146, 150)          # bottled smoke swirl
SMOKE_LIGHT = (198, 194, 198)

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

def tex_ember_berries():
    """Cluster of three glowing red berries on a charred sprig."""
    img = new_img()
    # Charred sprig.
    px(img, 8, 2, CHAR)
    px(img, 7, 3, CHAR)
    px(img, 8, 3, CHAR)
    px(img, 6, 4, CHAR)
    px(img, 9, 4, CHAR)
    px(img, 11, 4, GREEN)
    px(img, 10, 3, GREEN)
    # Berry 1 (left).
    rect(img, 3, 6, 6, 9, BERRY)
    px(img, 6, 9, BERRY_DARK)
    px(img, 6, 6, BERRY_DARK)
    px(img, 4, 7, EMBER_HOT)  # ember glow
    # Berry 2 (right).
    rect(img, 9, 6, 12, 9, BERRY)
    px(img, 12, 9, BERRY_DARK)
    px(img, 9, 9, BERRY_DARK)
    px(img, 10, 7, EMBER)
    # Berry 3 (bottom center).
    rect(img, 6, 10, 9, 13, BERRY)
    px(img, 9, 13, BERRY_DARK)
    px(img, 6, 13, BERRY_DARK)
    px(img, 7, 11, EMBER_HOT)
    return img


def tex_roasted_ember_fungus():
    """Roasted mushroom: seared cap with ember cracks on a toasted stem."""
    img = new_img()
    cap_dark = shade(FUNGUS_CAP, 0.7)
    # Cap.
    rect(img, 5, 2, 10, 2, FUNGUS_CAP)
    rect(img, 3, 3, 12, 5, FUNGUS_CAP)
    rect(img, 2, 6, 13, 7, FUNGUS_CAP)
    # Cap shading + char rim.
    for x in range(2, 14):
        px(img, x, 7, cap_dark)
    px(img, 12, 4, cap_dark)
    px(img, 13, 6, CHAR)
    px(img, 2, 6, CHAR)
    # Ember cracks glowing in the cap.
    px(img, 5, 4, EMBER)
    px(img, 6, 5, EMBER_HOT)
    px(img, 9, 3, EMBER)
    px(img, 10, 5, EMBER_DEEP)
    # Stem.
    rect(img, 6, 8, 9, 13, FUNGUS_STEM)
    for y in range(8, 14):
        px(img, 9, y, shade(FUNGUS_STEM, 0.75))
    # Toast marks.
    px(img, 7, 10, shade(FUNGUS_STEM, 0.6))
    px(img, 8, 12, shade(FUNGUS_STEM, 0.6))
    # Base char.
    rect(img, 6, 14, 9, 14, CHAR)
    return img


def tex_ash_bread():
    """Grey-crusted loaf dusted with ash, ember glints in the crust."""
    img = new_img()
    crust = (150, 128, 104)      # baked crust under the ash
    crust_dark = shade(crust, 0.7)
    # Loaf body (rounded).
    rect(img, 3, 5, 12, 5, crust)
    rect(img, 2, 6, 13, 10, crust)
    rect(img, 3, 11, 12, 11, crust)
    # Bottom + right shading.
    rect(img, 3, 11, 12, 11, crust_dark)
    for y in range(6, 11):
        px(img, 13, y, crust_dark)
    # Ash dusting on top.
    rect(img, 3, 5, 12, 5, ASH_LIGHT)
    rect(img, 2, 6, 13, 6, ASH)
    px(img, 4, 7, ASH_LIGHT)
    px(img, 8, 7, ASH)
    px(img, 11, 7, ASH_LIGHT)
    # Score lines.
    px(img, 5, 8, crust_dark)
    px(img, 6, 9, crust_dark)
    px(img, 8, 8, crust_dark)
    px(img, 9, 9, crust_dark)
    px(img, 11, 8, crust_dark)
    # Ember glints baked into the crust.
    px(img, 6, 6, EMBER)
    px(img, 10, 10, EMBER_DEEP)
    return img


def tex_smolder_stew():
    """Wooden bowl of glowing orange stew with rising heat wisps."""
    img = new_img()
    # Heat wisps.
    px(img, 5, 1, SMOKE_LIGHT)
    px(img, 10, 1, SMOKE_LIGHT)
    px(img, 6, 2, SMOKE)
    px(img, 9, 3, SMOKE)
    # Bowl rim.
    rect(img, 2, 6, 13, 6, BOWL)
    px(img, 2, 6, BOWL_DARK)
    px(img, 13, 6, BOWL_DARK)
    # Stew surface (glowing).
    rect(img, 3, 5, 12, 5, EMBER)
    px(img, 5, 5, EMBER_HOT)
    px(img, 9, 5, EMBER_DEEP)
    px(img, 7, 5, EMBER_HOT)
    # Bowl body tapering down.
    rect(img, 3, 7, 12, 9, BOWL)
    rect(img, 4, 10, 11, 11, BOWL)
    rect(img, 5, 12, 10, 12, BOWL_DARK)
    # Bowl shading.
    for y in range(7, 10):
        px(img, 12, y, BOWL_DARK)
    px(img, 11, 10, BOWL_DARK)
    px(img, 11, 11, BOWL_DARK)
    # Foot.
    rect(img, 6, 13, 9, 13, BOWL_DARK)
    return img


def tex_cinder_candy():
    """Wrapped hard candy: ember-orange drop with twisted ash-grey wrapper ends."""
    img = new_img()
    # Wrapper twists.
    px(img, 1, 7, ASH_LIGHT)
    px(img, 2, 6, ASH)
    px(img, 2, 8, ASH)
    px(img, 3, 7, ASH_LIGHT)
    px(img, 14, 7, ASH_LIGHT)
    px(img, 13, 6, ASH)
    px(img, 13, 8, ASH)
    px(img, 12, 7, ASH_LIGHT)
    # Candy drop.
    rect(img, 5, 5, 10, 9, EMBER)
    px(img, 5, 5, EMBER_DEEP)
    px(img, 10, 5, EMBER_DEEP)
    px(img, 5, 9, EMBER_DEEP)
    px(img, 10, 9, EMBER_DEEP)
    # Swirl.
    px(img, 7, 6, EMBER_HOT)
    px(img, 8, 7, BERRY)
    px(img, 7, 8, EMBER_DEEP)
    px(img, 9, 6, EMBER_HOT)
    return img


def tex_strider_escargot():
    """Strider-red snail shell spiral on a skewer, ember-seared."""
    img = new_img()
    # Skewer.
    px(img, 12, 12, (94, 62, 32))
    px(img, 13, 13, (94, 62, 32))
    px(img, 14, 14, (94, 62, 32))
    # Shell (round spiral).
    rect(img, 4, 3, 10, 3, STRIDER)
    rect(img, 3, 4, 11, 9, STRIDER)
    rect(img, 4, 10, 10, 10, STRIDER)
    # Outline shading.
    for y in range(4, 10):
        px(img, 11, y, STRIDER_DARK)
    rect(img, 4, 10, 10, 10, STRIDER_DARK)
    # Spiral groove.
    px(img, 7, 4, STRIDER_DARK)
    px(img, 8, 4, STRIDER_DARK)
    px(img, 9, 5, STRIDER_DARK)
    px(img, 9, 6, STRIDER_DARK)
    px(img, 8, 7, STRIDER_DARK)
    px(img, 7, 7, STRIDER_DARK)
    px(img, 6, 6, STRIDER_DARK)
    px(img, 6, 5, STRIDER_DARK)
    px(img, 7, 5, EMBER)  # seared center glint
    # Snail "foot" peeking out toward the skewer.
    rect(img, 9, 11, 11, 11, FUNGUS_STEM)
    px(img, 11, 11, shade(FUNGUS_STEM, 0.75))
    # Ember sear marks.
    px(img, 4, 6, EMBER_DEEP)
    px(img, 5, 9, EMBER)
    return img


def tex_infernium_apple():
    """Golden-apple silhouette in glowing infernium orange with hot sheen."""
    img = new_img()
    base = EMBER_DEEP
    dark = shade(base, 0.72)
    # Stem + leaf.
    px(img, 8, 1, (94, 62, 32))
    px(img, 8, 2, (94, 62, 32))
    px(img, 9, 2, GREEN)
    px(img, 10, 2, GREEN)
    # Body (same silhouette as foods_gen tex_apple).
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
    # White-hot sheen.
    px(img, 5, 5, EMBER_HOT)
    px(img, 4, 6, EMBER_HOT)
    px(img, 5, 6, EMBER_HOT)
    px(img, 4, 7, EMBER_HOT)
    # Ember sparkles (enchanted-ish glow).
    for x, y in ((7, 6), (9, 8), (6, 10), (10, 5)):
        px(img, x, y, EMBER)
    return img


def tex_ember_jam():
    """Squat jam jar glowing with ember-orange jam (foods_gen jar shape)."""
    img = new_img()
    jam = EMBER
    dark = shade(jam, 0.7)
    lid = CHAR
    rect(img, 4, 1, 11, 2, lid)
    px(img, 4, 1, shade(lid, 0.75))
    px(img, 11, 1, shade(lid, 0.75))
    rect(img, 3, 3, 12, 3, GLASS)
    for y in range(4, 13):
        px(img, 3, y, GLASS)
        px(img, 12, y, GLASS)
        rect(img, 4, y, 11, y, jam)
        px(img, 4, y, dark)
    # Berry chunks in the jam.
    px(img, 7, 6, BERRY)
    px(img, 9, 9, BERRY_DARK)
    px(img, 6, 10, BERRY)
    for y in range(5, 11):
        px(img, 10, y, EMBER_HOT)
    rect(img, 3, 13, 12, 13, GLASS)
    rect(img, 4, 13, 11, 13, dark)
    rect(img, 4, 14, 11, 14, GLASS)
    return img


def tex_bottled_smoke():
    """Corked glass bottle with a curling grey smoke wisp inside."""
    img = new_img()
    cork = (166, 124, 72)
    # Cork.
    rect(img, 6, 0, 9, 1, cork)
    px(img, 6, 0, shade(cork, 0.75))
    px(img, 9, 0, shade(cork, 0.75))
    # Neck (glass walls, faint haze inside).
    for y in (2, 3):
        px(img, 6, y, GLASS)
        px(img, 9, y, GLASS)
        rect(img, 7, y, 8, y, SMOKE_LIGHT)
    # Shoulders.
    px(img, 5, 4, GLASS)
    px(img, 10, 4, GLASS)
    rect(img, 6, 4, 9, 4, SMOKE_LIGHT)
    # Body rows 5-13: glass walls, hazy interior.
    for y in range(5, 14):
        px(img, 4, y, GLASS)
        px(img, 11, y, GLASS)
        rect(img, 5, y, 10, y, SMOKE_LIGHT)
    # Curling smoke wisp.
    px(img, 7, 5, SMOKE)
    px(img, 8, 6, SMOKE)
    px(img, 9, 7, ASH_DARK)
    px(img, 8, 8, SMOKE)
    px(img, 7, 9, ASH_DARK)
    px(img, 6, 10, SMOKE)
    px(img, 7, 11, ASH_DARK)
    px(img, 8, 12, SMOKE)
    # Glass highlight streak.
    px(img, 10, 5, GLASS_HI)
    px(img, 10, 6, GLASS_HI)
    # Bottom rim.
    rect(img, 4, 14, 11, 14, GLASS)
    rect(img, 5, 14, 10, 14, shade(SMOKE, 0.8))
    rect(img, 5, 15, 10, 15, GLASS)
    return img


TEXTURES = {
    "ember_berries": tex_ember_berries,
    "roasted_ember_fungus": tex_roasted_ember_fungus,
    "ash_bread": tex_ash_bread,
    "smolder_stew": tex_smolder_stew,
    "cinder_candy": tex_cinder_candy,
    "strider_escargot": tex_strider_escargot,
    "infernium_apple": tex_infernium_apple,
    "ember_jam": tex_ember_jam,
    "bottled_smoke": tex_bottled_smoke,
}

# ---------------------------------------------------------------------------
# Lang (EN + DE fragments; merged by devtools/merge_lang.py at integration)
# ---------------------------------------------------------------------------
LANG_EN = {
    "ember_berries": "Ember Berries",
    "roasted_ember_fungus": "Roasted Ember Fungus",
    "ash_bread": "Ash Bread",
    "smolder_stew": "Smolder Stew",
    "cinder_candy": "Cinder Candy",
    "strider_escargot": "Strider Escargot",
    "infernium_apple": "Infernium Apple",
    "ember_jam": "Ember Jam",
    "bottled_smoke": "Bottled Smoke",
}

LANG_DE = {
    "ember_berries": "Glutbeeren",
    "roasted_ember_fungus": "Ger\u00f6steter Glutpilz",
    "ash_bread": "Aschebrot",
    "smolder_stew": "Schweleintopf",
    "cinder_candy": "Zunderbonbon",
    "strider_escargot": "Schreiter-Escargot",
    "infernium_apple": "Infernium-Apfel",
    "ember_jam": "Glutmarmelade",
    "bottled_smoke": "Rauchflasche",
}

# ---------------------------------------------------------------------------
# Recipes (formats copied from vanilla 1.21.9 templates; see module docstring).
# References ember_fungus (infernoflora), ash_pile/ember_dust/infernium_ingot
# (infernium) — cross-feature ids are allowed; audits run at integration.
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


RAW_ROASTABLES = [f"{MOD}:ember_berries", f"{MOD}:ember_fungus"]

RECIPES = {
    # Cooking: raw Inferno produce roasts into Roasted Ember Fungus.
    "roasted_ember_fungus": cooking(
        "minecraft:smelting", RAW_ROASTABLES, "roasted_ember_fungus", 200),
    "roasted_ember_fungus_from_smoking": cooking(
        "minecraft:smoking", RAW_ROASTABLES, "roasted_ember_fungus", 100),
    # Crafting.
    "ash_bread": shaped(  # cookie.json "#X#" row template
        {"#": "minecraft:wheat", "X": f"{MOD}:ash_pile"},
        ["#X#"],
        "ash_bread"),
    "smolder_stew": shapeless(  # mushroom_stew.json template
        [f"{MOD}:roasted_ember_fungus", f"{MOD}:ember_berries", "minecraft:bowl"],
        "smolder_stew"),
    "cinder_candy": shapeless(
        ["minecraft:sugar", "minecraft:sugar", f"{MOD}:ember_dust"],
        "cinder_candy", count=3),
    "strider_escargot": shapeless(
        ["minecraft:string", "minecraft:string", f"{MOD}:roasted_ember_fungus"],
        "strider_escargot"),
    "infernium_apple": shaped(  # golden_apple.json template with infernium ingots
        {"#": f"{MOD}:infernium_ingot", "X": "minecraft:apple"},
        ["###", "#X#", "###"],
        "infernium_apple"),
    "ember_jam": shapeless(
        [f"{MOD}:ember_berries", f"{MOD}:ember_berries", "minecraft:sugar",
         "minecraft:glass_bottle"],
        "ember_jam"),
    "bottled_smoke": shapeless(
        ["minecraft:glass_bottle", f"{MOD}:ash_pile", "minecraft:charcoal"],
        "bottled_smoke"),
}

# ---------------------------------------------------------------------------
# Emission
# ---------------------------------------------------------------------------


def write_json(path, obj):
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2) + "\n", encoding="utf-8")


def main():
    ids = list(TEXTURES)
    assert set(ids) == set(LANG_EN) == set(LANG_DE), "item id tables out of sync"
    assert len(ids) == 9

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

    # Recipes (names include cooking variants, so keyed independently of ids).
    for name, recipe in RECIPES.items():
        write_json(DATA / "recipe" / "infernofoods" / f"{name}.json", recipe)

    # Lang fragments (this feature's files only; merged by integration).
    write_json(ASSETS / "lang" / "fragments" / "infernofoods.json",
               {f"item.{MOD}.{item_id}": LANG_EN[item_id] for item_id in ids})
    write_json(ASSETS / "lang" / "fragments_de" / "infernofoods.json",
               {f"item.{MOD}.{item_id}": LANG_DE[item_id] for item_id in ids})

    print(f"infernofoods_gen: wrote {len(ids)} items "
          f"(item defs, models, textures) + {len(RECIPES)} recipes + EN/DE lang fragments")


if __name__ == "__main__":
    main()
