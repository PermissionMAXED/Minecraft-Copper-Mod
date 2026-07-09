#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4 "sodafauna" feature (24 mobs, 36 items).

Idempotent: running it any number of times produces byte-identical files (all texture
work is deterministic: vanilla extraction + luminance recoloring, no RNG). Emits directly
into src/main/resources:
  - spawn-egg item textures    assets/copper_inferno/textures/item/<id>_spawn_egg.png
    (the matching vanilla <base>_spawn_egg.png extracted from the client jar and
    recolored onto a bright soda/candy palette)
  - drop item textures         assets/copper_inferno/textures/item/<drop>.png
    (a fitting vanilla item texture recolored the same way)
  - entity skins               assets/copper_inferno/textures/entity/sodafauna/<id>.png
    (the vanilla base mob's skin recolored; consumed by the getTexture overrides in
    SodaFaunaFeatureClient)
  - item model-definitions     assets/copper_inferno/items/<id>.json
  - item models                assets/copper_inferno/models/item/<id>.json
  - entity loot tables         data/copper_inferno/loot_table/entities/<mob>.json
  - recipes                    data/copper_inferno/recipe/sodafauna/*.json
  - lang fragments             assets/copper_inferno/lang/fragments/sodafauna.json (EN)
                               assets/copper_inferno/lang/fragments_de/sodafauna.json (DE)

Every recipe's input multiset contains one of this feature's own drop items, and each
drop appears in exactly one recipe, so recipe inputs can never collide with vanilla or
with other features (enforced globally by devtools/check_recipe_collisions.py).
"""

from pathlib import Path

from lib_gen import NS, extract_vanilla, item_def, recolor, write_json

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# ---------------------------------------------------------------------------
# Palettes (darkest -> lightest, bright soda/candy ramps)
# ---------------------------------------------------------------------------

PALETTES = {
    "fizz_slime": [(0x2E, 0x7D, 0x1E), (0x59, 0xC5, 0x31), (0x8F, 0xE8, 0x58), (0xD6, 0xFF, 0x9E)],
    "cola_cube": [(0x1E, 0x0F, 0x08), (0x3B, 0x1D, 0x0E), (0x6B, 0x3A, 0x1E), (0xA9, 0x6B, 0x3A)],
    "soda_sprite": [(0x3E, 0x6B, 0x1E), (0x77, 0xB9, 0x3B), (0xB4, 0xF0, 0x4A), (0xEF, 0xFF, 0xD0)],
    "gummy_hopper": [(0x8E, 0x12, 0x30), (0xD9, 0x1C, 0x4D), (0xF7, 0x5E, 0x86), (0xFF, 0xC2, 0xD2)],
    "caramel_golem": [(0x5A, 0x2E, 0x0E), (0x96, 0x55, 0x1C), (0xC9, 0x80, 0x35), (0xF0, 0xBC, 0x72)],
    "syrup_spider": [(0x2E, 0x16, 0x08), (0x5C, 0x30, 0x0F), (0x9C, 0x5A, 0x1D), (0xD9, 0x9A, 0x4E)],
    "bottlecap_beetle": [(0x3A, 0x3F, 0x46), (0x6E, 0x76, 0x7F), (0xA6, 0xAD, 0xB5), (0xE3, 0xE7, 0xEA)],
    "sugar_rush_fox": [(0x9E, 0x25, 0x60), (0xE0, 0x49, 0x8F), (0xF7, 0x84, 0xBC), (0xFF, 0xD3, 0xE8)],
    "root_beer_boar": [(0x24, 0x12, 0x0A), (0x4A, 0x26, 0x14), (0x7A, 0x45, 0x2A), (0xB5, 0x7C, 0x4E)],
    "cream_cat": [(0x8A, 0x73, 0x50), (0xC4, 0xA9, 0x7A), (0xEB, 0xD9, 0xB0), (0xFF, 0xF6, 0xE0)],
    "pepper_pup": [(0x3E, 0x10, 0x13), (0x71, 0x21, 0x2A), (0xA4, 0x3A, 0x44), (0xD9, 0x88, 0x90)],
    "cherry_bee": [(0x40, 0x08, 0x0E), (0x8E, 0x12, 0x20), (0xD6, 0x28, 0x39), (0xFF, 0x9F, 0xA8)],
    "grape_bat": [(0x2E, 0x12, 0x45), (0x5B, 0x2D, 0x86), (0x8F, 0x52, 0xC4), (0xC9, 0xA0, 0xE8)],
    "lime_frog": [(0x2F, 0x5C, 0x14), (0x5E, 0x9C, 0x28), (0x92, 0xCF, 0x4C), (0xD8, 0xF5, 0xA2)],
    "vanilla_sheep": [(0x9C, 0x8A, 0x62), (0xD1, 0xBE, 0x92), (0xF2, 0xE6, 0xC4), (0xFF, 0xFB, 0xEF)],
    "canned_creeper": [(0x4E, 0x52, 0x57), (0x84, 0x89, 0x8F), (0xB8, 0xBD, 0xC2), (0xF2, 0xF4, 0xF6)],
    "soda_witch": [(0x2A, 0x10, 0x38), (0x55, 0x20, 0x70), (0x8A, 0x3F, 0xAE), (0xCB, 0x92, 0xE3)],
    "float_phantom": [(0x8C, 0x82, 0x70), (0xC2, 0xB8, 0xA4), (0xE8, 0xE0, 0xCE), (0xFF, 0xFD, 0xF4)],
    "fizzy_chicken": [(0x8A, 0x6A, 0x12), (0xC9, 0xA1, 0x1F), (0xEF, 0xD2, 0x3F), (0xFF, 0xF6, 0xA8)],
    "diet_zombie": [(0x3C, 0x4A, 0x3C), (0x66, 0x79, 0x5F), (0x93, 0xA8, 0x89), (0xC9, 0xD8, 0xBE)],
    "sugar_skeleton": [(0x9A, 0x92, 0x84), (0xC8, 0xC2, 0xB4), (0xED, 0xEA, 0xDF), (0xFF, 0xFF, 0xFA)],
    "carbonated_cube": [(0x5F, 0x7C, 0x86), (0x93, 0xB7, 0xC1), (0xC4, 0xE2, 0xE8), (0xF0, 0xFC, 0xFF)],
    "straw_stray": [(0x8E, 0x24, 0x30), (0xC9, 0x48, 0x56), (0xE9, 0x90, 0x9A), (0xFF, 0xF4, 0xF0)],
    "pop_parrot": [(0x8E, 0x3A, 0x0E), (0xD9, 0x63, 0x1C), (0xF9, 0x8E, 0x3C), (0xFF, 0xCF, 0x9A)],
}

# mob id -> (vanilla base id for the spawn-egg texture, vanilla entity skin in the jar)
MOB_SOURCES = {
    "fizz_slime": ("slime", "entity/slime/slime.png"),
    "cola_cube": ("magma_cube", "entity/slime/magmacube.png"),
    "soda_sprite": ("vex", "entity/illager/vex.png"),
    "gummy_hopper": ("rabbit", "entity/rabbit/white.png"),
    "caramel_golem": ("snow_golem", "entity/snow_golem.png"),
    "syrup_spider": ("spider", "entity/spider/spider.png"),
    "bottlecap_beetle": ("silverfish", "entity/silverfish.png"),
    "sugar_rush_fox": ("fox", "entity/fox/fox.png"),
    "root_beer_boar": ("hoglin", "entity/hoglin/hoglin.png"),
    "cream_cat": ("cat", "entity/cat/white.png"),
    "pepper_pup": ("wolf", "entity/wolf/wolf.png"),
    "cherry_bee": ("bee", "entity/bee/bee.png"),
    "grape_bat": ("bat", "entity/bat.png"),
    "lime_frog": ("frog", "entity/frog/temperate_frog.png"),
    "vanilla_sheep": ("sheep", "entity/sheep/sheep.png"),
    "canned_creeper": ("creeper", "entity/creeper/creeper.png"),
    "soda_witch": ("witch", "entity/witch.png"),
    "float_phantom": ("phantom", "entity/phantom.png"),
    "fizzy_chicken": ("chicken", "entity/chicken/temperate_chicken.png"),
    "diet_zombie": ("zombie", "entity/zombie/zombie.png"),
    "sugar_skeleton": ("skeleton", "entity/skeleton/skeleton.png"),
    "carbonated_cube": ("magma_cube", "entity/slime/magmacube.png"),
    "straw_stray": ("stray", "entity/skeleton/stray.png"),
    "pop_parrot": ("parrot", "entity/parrot/parrot_red_blue.png"),
}

# drop id -> (vanilla item texture to recolor, palette key)
DROP_SOURCES = {
    "fizz_globule": ("item/slime_ball.png", "fizz_slime"),
    "cola_chunk": ("item/magma_cream.png", "cola_cube"),
    "sprite_essence": ("item/ghast_tear.png", "soda_sprite"),
    "gummy_drop": ("item/sweet_berries.png", "gummy_hopper"),
    "caramel_glob": ("item/slime_ball.png", "caramel_golem"),
    "bottlecap": ("item/iron_nugget.png", "bottlecap_beetle"),
    "sugar_crystal": ("item/quartz.png", "sugar_skeleton"),
    "cream_swirl": ("item/snowball.png", "cream_cat"),
    "pepper_spice": ("item/blaze_powder.png", "pepper_pup"),
    "cherry_syrup": ("item/honey_bottle.png", "cherry_bee"),
    "float_foam": ("item/glowstone_dust.png", "float_phantom"),
    "zero_syrup": ("item/glass_bottle.png", "diet_zombie"),
}

# mob id -> (drop item id, min count, max count); every drop item appears EXACTLY twice.
LOOT = {
    "fizz_slime": (f"{NS}:fizz_globule", 0.0, 2.0),
    "cola_cube": (f"{NS}:cola_chunk", 0.0, 1.0),
    "soda_sprite": (f"{NS}:sprite_essence", 0.0, 1.0),
    "gummy_hopper": (f"{NS}:gummy_drop", 0.0, 2.0),
    "caramel_golem": (f"{NS}:caramel_glob", 0.0, 2.0),
    "syrup_spider": (f"{NS}:caramel_glob", 0.0, 1.0),
    "bottlecap_beetle": (f"{NS}:bottlecap", 0.0, 1.0),
    "sugar_rush_fox": (f"{NS}:sugar_crystal", 0.0, 2.0),
    "root_beer_boar": (f"{NS}:cola_chunk", 1.0, 3.0),
    "cream_cat": (f"{NS}:cream_swirl", 0.0, 2.0),
    "pepper_pup": (f"{NS}:pepper_spice", 0.0, 2.0),
    "cherry_bee": (f"{NS}:cherry_syrup", 0.0, 1.0),
    "grape_bat": (f"{NS}:gummy_drop", 0.0, 1.0),
    "lime_frog": (f"{NS}:sprite_essence", 0.0, 1.0),
    "vanilla_sheep": (f"{NS}:cream_swirl", 1.0, 2.0),
    "canned_creeper": (f"{NS}:bottlecap", 0.0, 2.0),
    "soda_witch": (f"{NS}:pepper_spice", 0.0, 2.0),
    "float_phantom": (f"{NS}:float_foam", 0.0, 1.0),
    "fizzy_chicken": (f"{NS}:fizz_globule", 0.0, 1.0),
    "diet_zombie": (f"{NS}:zero_syrup", 0.0, 2.0),
    "sugar_skeleton": (f"{NS}:sugar_crystal", 0.0, 2.0),
    "carbonated_cube": (f"{NS}:float_foam", 0.0, 1.0),
    "straw_stray": (f"{NS}:zero_syrup", 0.0, 1.0),
    "pop_parrot": (f"{NS}:cherry_syrup", 0.0, 1.0),
}

# recipe name -> (drop input id, result id, result count). One recipe per drop item; the
# single-item input is always one of OUR drops, so the input set is globally unique.
RECIPES = {
    "slime_ball_from_fizz_globule": ("fizz_globule", "minecraft:slime_ball", 2),
    "brown_dye_from_cola_chunk": ("cola_chunk", "minecraft:brown_dye", 2),
    "lime_dye_from_sprite_essence": ("sprite_essence", "minecraft:lime_dye", 2),
    "magenta_dye_from_gummy_drop": ("gummy_drop", "minecraft:magenta_dye", 2),
    "orange_dye_from_caramel_glob": ("caramel_glob", "minecraft:orange_dye", 2),
    "iron_nugget_from_bottlecap": ("bottlecap", "minecraft:iron_nugget", 3),
    "sugar_from_sugar_crystal": ("sugar_crystal", "minecraft:sugar", 2),
    "white_dye_from_cream_swirl": ("cream_swirl", "minecraft:white_dye", 2),
    "red_dye_from_pepper_spice": ("pepper_spice", "minecraft:red_dye", 2),
    "pink_dye_from_cherry_syrup": ("cherry_syrup", "minecraft:pink_dye", 2),
    "snowball_from_float_foam": ("float_foam", "minecraft:snowball", 2),
    "gray_dye_from_zero_syrup": ("zero_syrup", "minecraft:gray_dye", 2),
}

MOB_NAMES_EN = {
    "fizz_slime": "Fizz Slime",
    "cola_cube": "Cola Cube",
    "soda_sprite": "Soda Sprite",
    "gummy_hopper": "Gummy Hopper",
    "caramel_golem": "Caramel Golem",
    "syrup_spider": "Syrup Spider",
    "bottlecap_beetle": "Bottlecap Beetle",
    "sugar_rush_fox": "Sugar Rush Fox",
    "root_beer_boar": "Root Beer Boar",
    "cream_cat": "Cream Cat",
    "pepper_pup": "Pepper Pup",
    "cherry_bee": "Cherry Bee",
    "grape_bat": "Grape Bat",
    "lime_frog": "Lime Frog",
    "vanilla_sheep": "Vanilla Sheep",
    "canned_creeper": "Canned Creeper",
    "soda_witch": "Soda Witch",
    "float_phantom": "Float Phantom",
    "fizzy_chicken": "Fizzy Chicken",
    "diet_zombie": "Diet Zombie",
    "sugar_skeleton": "Sugar Skeleton",
    "carbonated_cube": "Carbonated Cube",
    "straw_stray": "Straw Stray",
    "pop_parrot": "Pop Parrot",
}

MOB_NAMES_DE = {
    "fizz_slime": "Brauseschleim",
    "cola_cube": "Colawürfel",
    "soda_sprite": "Limogeist",
    "gummy_hopper": "Gummihüpfer",
    "caramel_golem": "Karamellgolem",
    "syrup_spider": "Sirupspinne",
    "bottlecap_beetle": "Kronkorkenkäfer",
    "sugar_rush_fox": "Zuckerrausch-Fuchs",
    "root_beer_boar": "Wurzelbier-Keiler",
    "cream_cat": "Sahnekatze",
    "pepper_pup": "Pfefferwelpe",
    "cherry_bee": "Kirschbiene",
    "grape_bat": "Traubenfledermaus",
    "lime_frog": "Limettenfrosch",
    "vanilla_sheep": "Vanilleschaf",
    "canned_creeper": "Dosen-Creeper",
    "soda_witch": "Limohexe",
    "float_phantom": "Schwebschaum-Phantom",
    "fizzy_chicken": "Sprudelhuhn",
    "diet_zombie": "Diät-Zombie",
    "sugar_skeleton": "Zuckerskelett",
    "carbonated_cube": "Sprudelwürfel",
    "straw_stray": "Strohhalm-Eiswanderer",
    "pop_parrot": "Knisterpapagei",
}

DROP_NAMES_EN = {
    "fizz_globule": "Fizz Globule",
    "cola_chunk": "Cola Chunk",
    "sprite_essence": "Sprite Essence",
    "gummy_drop": "Gummy Drop",
    "caramel_glob": "Caramel Glob",
    "bottlecap": "Bottlecap",
    "sugar_crystal": "Sugar Crystal",
    "cream_swirl": "Cream Swirl",
    "pepper_spice": "Pepper Spice",
    "cherry_syrup": "Cherry Syrup",
    "float_foam": "Float Foam",
    "zero_syrup": "Zero Syrup",
}

DROP_NAMES_DE = {
    "fizz_globule": "Brausekügelchen",
    "cola_chunk": "Colabrocken",
    "sprite_essence": "Limoessenz",
    "gummy_drop": "Gummitropfen",
    "caramel_glob": "Karamellklumpen",
    "bottlecap": "Flaschenkapsel",
    "sugar_crystal": "Zuckerkristall",
    "cream_swirl": "Sahnewirbel",
    "pepper_spice": "Pfeffergewürz",
    "cherry_syrup": "Kirschsirup",
    "float_foam": "Schwebschaum",
    "zero_syrup": "Zero-Sirup",
}

ITEM_IDS = [f"{mob}_spawn_egg" for mob in MOB_SOURCES] + list(DROP_SOURCES)


# ---------------------------------------------------------------------------
# Textures (all deterministic: vanilla extraction + luminance recoloring)
# ---------------------------------------------------------------------------

def save_png(img, path: Path) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    img.save(path)


def emit_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    entity_dir = ASSETS / "textures" / "entity" / "sodafauna"
    for mob, (egg_base, skin) in MOB_SOURCES.items():
        palette = PALETTES[mob]
        egg = extract_vanilla(f"assets/minecraft/textures/item/{egg_base}_spawn_egg.png")
        save_png(recolor(egg, palette), item_dir / f"{mob}_spawn_egg.png")
        save_png(recolor(extract_vanilla(f"assets/minecraft/textures/{skin}"), palette),
                 entity_dir / f"{mob}.png")
    for drop, (src, palette_key) in DROP_SOURCES.items():
        img = extract_vanilla(f"assets/minecraft/textures/{src}")
        save_png(recolor(img, PALETTES[palette_key]), item_dir / f"{drop}.png")


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
# Entity loot tables (same schema as infernomobs / dr_pepper_golem)
# ---------------------------------------------------------------------------

def emit_loot_tables() -> None:
    for mob_id, (drop_id, cmin, cmax) in LOOT.items():
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
# Recipes (vanilla 1.21.9 crafting_shapeless format)
# ---------------------------------------------------------------------------

def emit_recipes() -> None:
    rdir = DATA / "recipe" / "sodafauna"
    for name, (drop, result, count) in RECIPES.items():
        write_json(rdir / f"{name}.json", {
            "type": "minecraft:crafting_shapeless",
            "category": "misc",
            "ingredients": [f"{NS}:{drop}"],
            "result": {"count": count, "id": result},
        })


# ---------------------------------------------------------------------------
# Lang fragments
# ---------------------------------------------------------------------------

def emit_lang() -> None:
    en, de = {}, {}
    for mob in MOB_SOURCES:
        en[f"entity.{NS}.{mob}"] = MOB_NAMES_EN[mob]
        de[f"entity.{NS}.{mob}"] = MOB_NAMES_DE[mob]
        en[f"item.{NS}.{mob}_spawn_egg"] = f"{MOB_NAMES_EN[mob]} Spawn Egg"
        de[f"item.{NS}.{mob}_spawn_egg"] = f"{MOB_NAMES_DE[mob]}-Spawn-Ei"
    for drop in DROP_SOURCES:
        en[f"item.{NS}.{drop}"] = DROP_NAMES_EN[drop]
        de[f"item.{NS}.{drop}"] = DROP_NAMES_DE[drop]
    write_json(ASSETS / "lang" / "fragments" / "sodafauna.json", en)
    write_json(ASSETS / "lang" / "fragments_de" / "sodafauna.json", de)


def main() -> None:
    assert len(DROP_SOURCES) == 12 and len(MOB_SOURCES) == 24 and len(LOOT) == 24
    drop_uses = {}
    for drop_id, _cmin, _cmax in LOOT.values():
        drop_uses[drop_id] = drop_uses.get(drop_id, 0) + 1
    assert all(n == 2 for n in drop_uses.values()) and len(drop_uses) == 12, drop_uses
    emit_textures()
    emit_item_assets()
    emit_loot_tables()
    emit_recipes()
    emit_lang()
    print(f"sodafauna_gen: assets for {len(MOB_SOURCES)} mobs / {len(ITEM_IDS)} items generated "
          f"({len(LOOT)} loot tables, {len(RECIPES)} recipes, lang EN+DE).")


if __name__ == "__main__":
    main()
