#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4 "Inferno Wildlife" feature (WP9, 24 mobs).

Emits, directly into src/main/resources:
  - item model-definitions  assets/copper_inferno/items/<id>.json         (lib_gen.item_def)
  - item models             assets/copper_inferno/models/item/<id>.json   (item/generated)
  - item textures           assets/copper_inferno/textures/item/<id>.png
      * 24 spawn eggs: the matching vanilla <base>_spawn_egg.png extracted from the client
        jar (lib_gen.extract_vanilla) and remapped onto a per-mob fiery palette
        (lib_gen.recolor)
      * 12 mob drops: thematic vanilla item textures extracted + recolored the same way
  - entity textures         assets/copper_inferno/textures/entity/infernofauna/<mob>.png
      * the vanilla entity texture of each mob's base, extracted + recolored; consumed by
        the getTexture overrides in InfernoFaunaFeatureClient
  - entity loot tables      data/copper_inferno/loot_table/entities/<mob>.json
                            (infernomobs schema incl. "random_sequence")
  - recipes                 data/copper_inferno/recipe/infernofauna/*.json — every recipe
                            is shapeless and its ingredient list contains one of THIS
                            feature's unique drop items, so the canonical input set can
                            never collide with vanilla or other feature recipes
                            (check_recipe_collisions.py-safe by construction)
  - lang fragments          assets/copper_inferno/lang/fragments/infernofauna.json (EN)
                            assets/copper_inferno/lang/fragments_de/infernofauna.json (DE)

Idempotent: extraction + recolor are deterministic and PNGs are only rewritten when their
bytes change, so running the script any number of times produces byte-identical output.
"""

import io
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from lib_gen import NS, extract_vanilla, item_def, recolor, write_json  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# ---------------------------------------------------------------------------
# Palettes (dark -> light ramps for lib_gen.recolor)
# ---------------------------------------------------------------------------

CHAR = [(0x14, 0x0E, 0x10), (0x2B, 0x22, 0x26), (0x4A, 0x36, 0x3A), (0x6E, 0x52, 0x50)]
SOOT = [(0x11, 0x0F, 0x10), (0x24, 0x20, 0x21), (0x3B, 0x35, 0x35), (0x59, 0x50, 0x4D)]
ASH = [(0x2E, 0x2B, 0x29), (0x5F, 0x5B, 0x57), (0x8F, 0x8A, 0x84), (0xB9, 0xB4, 0xAE)]
EMBER = [(0x3A, 0x14, 0x0C), (0x8A, 0x2E, 0x12), (0xE2, 0x58, 0x22), (0xFF, 0xB1, 0x6B)]
FLAME = [(0x5A, 0x1E, 0x08), (0xC2, 0x4A, 0x0E), (0xFF, 0x7A, 0x2F), (0xFF, 0xD8, 0x66)]
MOLTEN = [(0x2B, 0x10, 0x0C), (0x6E, 0x22, 0x10), (0xD8, 0x4A, 0x1A), (0xFF, 0xC8, 0x50)]
BRIMSTONE = [(0x3A, 0x2C, 0x10), (0x6E, 0x58, 0x1C), (0xA8, 0x8A, 0x2A), (0xD8, 0xC0, 0x58)]
SLAG = [(0x1C, 0x15, 0x18), (0x3D, 0x2C, 0x2E), (0x5C, 0x44, 0x42), (0x8A, 0x64, 0x54)]
GLOW = [(0x4A, 0x30, 0x10), (0x9C, 0x66, 0x1E), (0xE8, 0xA8, 0x38), (0xFF, 0xE8, 0x90)]
PYRE = [(0x30, 0x0E, 0x0E), (0x66, 0x1A, 0x14), (0xB0, 0x38, 0x1E), (0xFF, 0x8A, 0x3C)]
KILN = [(0x38, 0x16, 0x10), (0x74, 0x30, 0x1E), (0xB0, 0x52, 0x30), (0xE0, 0x8A, 0x54)]
BONE = [(0x4A, 0x40, 0x32), (0x8E, 0x86, 0x74), (0xC9, 0xC2, 0xB2), (0xF2, 0xEE, 0xE4)]

# ---------------------------------------------------------------------------
# Roster: mob id -> (vanilla spawn-egg texture base, egg palette,
#                    vanilla entity texture path, entity palette,
#                    loot drop id, loot min, loot max)
# ---------------------------------------------------------------------------

E = "assets/minecraft/textures/entity/"

MOBS = {
    "magma_hopper": ("magma_cube", MOLTEN, E + "slime/magmacube.png", MOLTEN,
                     f"{NS}:hopper_slag", 0.0, 2.0),
    "cinder_hound": ("wolf", CHAR, E + "wolf/wolf.png", CHAR,
                     f"{NS}:singed_fang", 0.0, 2.0),
    "ember_moth": ("bat", EMBER, E + "bat.png", EMBER,
                   f"{NS}:ember_wing", 0.0, 2.0),
    "slag_snail": ("silverfish", SLAG, E + "silverfish.png", SLAG,
                   f"{NS}:slag_shell", 0.0, 2.0),
    "pyre_raven": ("parrot", PYRE, E + "parrot/parrot_red_blue.png", PYRE,
                   f"{NS}:pyre_feather", 0.0, 2.0),
    "ash_stalker": ("spider", ASH, E + "spider/spider.png", ASH,
                    "minecraft:string", 0.0, 2.0),
    "fumarole_frog": ("frog", SLAG, E + "frog/warm_frog.png", SLAG,
                      f"{NS}:fumarole_gland", 0.0, 1.0),
    "soot_piglet": ("pig", SOOT, E + "pig/temperate_pig.png", SOOT,
                    "minecraft:cooked_porkchop", 1.0, 3.0),
    "scorch_goat": ("goat", CHAR, E + "goat/goat.png", CHAR,
                    f"{NS}:scorched_horn", 0.0, 1.0),
    "lava_eel": ("guardian", MOLTEN, E + "guardian.png", MOLTEN,
                 f"{NS}:lava_eel_fillet", 1.0, 2.0),
    "ember_owl": ("parrot", GLOW, E + "parrot/parrot_grey.png", GLOW,
                  "minecraft:feather", 0.0, 2.0),
    "cinder_toad": ("frog", EMBER, E + "frog/temperate_frog.png", EMBER,
                    "minecraft:slime_ball", 0.0, 1.0),
    "flare_wisp": ("vex", FLAME, E + "illager/vex.png", FLAME,
                   f"{NS}:flare_essence", 0.0, 1.0),
    "smolder_tortoise": ("turtle", KILN, E + "turtle/big_sea_turtle.png", KILN,
                         f"{NS}:smolder_scute", 0.0, 1.0),
    "brimstone_bull": ("hoglin", BRIMSTONE, E + "hoglin/hoglin.png", BRIMSTONE,
                       f"{NS}:brimstone_hide", 0.0, 2.0),
    "ash_wanderer": ("husk", ASH, E + "zombie/husk.png", ASH,
                     "minecraft:rotten_flesh", 0.0, 2.0),
    "pyroclast_golem": ("iron_golem", SLAG, E + "iron_golem/iron_golem.png", SLAG,
                        f"{NS}:pyroclast_core", 1.0, 2.0),
    "ember_serpent": ("cave_spider", EMBER, E + "spider/cave_spider.png", EMBER,
                      "minecraft:spider_eye", 0.0, 1.0),
    "soot_sheep": ("sheep", SOOT, E + "sheep/sheep.png", SOOT,
                   "minecraft:black_wool", 1.0, 1.0),
    "glow_strider": ("strider", GLOW, E + "strider/strider.png", GLOW,
                     "minecraft:glowstone_dust", 0.0, 2.0),
    "kiln_spider": ("spider", KILN, E + "spider/spider.png", KILN,
                    "minecraft:string", 0.0, 2.0),
    "char_phantom": ("phantom", SOOT, E + "phantom.png", SOOT,
                     "minecraft:phantom_membrane", 0.0, 1.0),
    "molten_mite": ("endermite", MOLTEN, E + "endermite.png", MOLTEN,
                    "minecraft:blaze_powder", 0.0, 1.0),
    "inferno_ghastling": ("ghast", FLAME, E + "ghast/ghast.png", FLAME,
                          "minecraft:ghast_tear", 0.0, 1.0),
}

# drop item id -> (vanilla item texture base, palette)
DROPS = {
    "hopper_slag": ("magma_cream", MOLTEN),
    "singed_fang": ("echo_shard", BONE),
    "ember_wing": ("phantom_membrane", EMBER),
    "slag_shell": ("nautilus_shell", SLAG),
    "pyre_feather": ("feather", PYRE),
    "fumarole_gland": ("slime_ball", SLAG),
    "scorched_horn": ("goat_horn", CHAR),
    "lava_eel_fillet": ("cooked_cod", MOLTEN),
    "flare_essence": ("blaze_powder", FLAME),
    "smolder_scute": ("turtle_scute", KILN),
    "brimstone_hide": ("leather", BRIMSTONE),
    "pyroclast_core": ("ender_pearl", MOLTEN),
}

ITEM_IDS = [f"{mob}_spawn_egg" for mob in MOBS] + list(DROPS)

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

NAMES = {
    "magma_hopper": ("Magma Hopper", "Magmahüpfer"),
    "cinder_hound": ("Cinder Hound", "Zunderhund"),
    "ember_moth": ("Ember Moth", "Glutfalter"),
    "slag_snail": ("Slag Snail", "Schlackenschnecke"),
    "pyre_raven": ("Pyre Raven", "Feuerrabe"),
    "ash_stalker": ("Ash Stalker", "Aschenschleicher"),
    "fumarole_frog": ("Fumarole Frog", "Fumarolenfrosch"),
    "soot_piglet": ("Soot Piglet", "Rußferkel"),
    "scorch_goat": ("Scorch Goat", "Sengziege"),
    "lava_eel": ("Lava Eel", "Lava-Aal"),
    "ember_owl": ("Ember Owl", "Gluteule"),
    "cinder_toad": ("Cinder Toad", "Zunderkröte"),
    "flare_wisp": ("Flare Wisp", "Flammenirrlicht"),
    "smolder_tortoise": ("Smolder Tortoise", "Schwelschildkröte"),
    "brimstone_bull": ("Brimstone Bull", "Schwefelbulle"),
    "ash_wanderer": ("Ash Wanderer", "Aschenwanderer"),
    "pyroclast_golem": ("Pyroclast Golem", "Pyroklast-Golem"),
    "ember_serpent": ("Ember Serpent", "Glutnatter"),
    "soot_sheep": ("Soot Sheep", "Rußschaf"),
    "glow_strider": ("Glow Strider", "Glimmschreiter"),
    "kiln_spider": ("Kiln Spider", "Brennofenspinne"),
    "char_phantom": ("Char Phantom", "Kohlenphantom"),
    "molten_mite": ("Molten Mite", "Schmelzmilbe"),
    "inferno_ghastling": ("Inferno Ghastling", "Inferno-Ghastling"),
}

DROP_NAMES = {
    "hopper_slag": ("Hopper Slag", "Hüpferschlacke"),
    "singed_fang": ("Singed Fang", "Versengter Fangzahn"),
    "ember_wing": ("Ember Wing", "Glutflügel"),
    "slag_shell": ("Slag Shell", "Schlackengehäuse"),
    "pyre_feather": ("Pyre Feather", "Feuerfeder"),
    "fumarole_gland": ("Fumarole Gland", "Fumarolendrüse"),
    "scorched_horn": ("Scorched Horn", "Versengtes Horn"),
    "lava_eel_fillet": ("Lava Eel Fillet", "Lava-Aal-Filet"),
    "flare_essence": ("Flare Essence", "Flackeressenz"),
    "smolder_scute": ("Smolder Scute", "Schwelhornschild"),
    "brimstone_hide": ("Brimstone Hide", "Schwefelhaut"),
    "pyroclast_core": ("Pyroclast Core", "Pyroklast-Kern"),
}


def build_lang() -> tuple[dict, dict]:
    en, de = {}, {}
    for mob, (name_en, name_de) in NAMES.items():
        en[f"entity.{NS}.{mob}"] = name_en
        de[f"entity.{NS}.{mob}"] = name_de
        en[f"item.{NS}.{mob}_spawn_egg"] = f"{name_en} Spawn Egg"
        de[f"item.{NS}.{mob}_spawn_egg"] = f"{name_de}-Spawn-Ei"
    for drop, (name_en, name_de) in DROP_NAMES.items():
        en[f"item.{NS}.{drop}"] = name_en
        de[f"item.{NS}.{drop}"] = name_de
    return en, de


# ---------------------------------------------------------------------------
# Textures (extract vanilla + recolor; write only when bytes change)
# ---------------------------------------------------------------------------


def save_png(path: Path, img) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    buf = io.BytesIO()
    img.save(buf, format="PNG")
    data = buf.getvalue()
    if not path.exists() or path.read_bytes() != data:
        path.write_bytes(data)


def emit_textures() -> int:
    count = 0
    item_dir = ASSETS / "textures" / "item"
    entity_dir = ASSETS / "textures" / "entity" / "infernofauna"
    for mob, (egg_base, egg_pal, entity_tex, entity_pal, _drop, _lo, _hi) in MOBS.items():
        egg = recolor(extract_vanilla(f"assets/minecraft/textures/item/{egg_base}_spawn_egg.png"), egg_pal)
        save_png(item_dir / f"{mob}_spawn_egg.png", egg)
        body = recolor(extract_vanilla(entity_tex), entity_pal)
        save_png(entity_dir / f"{mob}.png", body)
        count += 2
    for drop, (tex_base, pal) in DROPS.items():
        img = recolor(extract_vanilla(f"assets/minecraft/textures/item/{tex_base}.png"), pal)
        save_png(item_dir / f"{drop}.png", img)
        count += 1
    return count


# ---------------------------------------------------------------------------
# Item model-definitions + models (audit rule: BOTH files per registered id)
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
    for mob, (_eb, _ep, _et, _epal, drop_id, cmin, cmax) in MOBS.items():
        write_json(DATA / "loot_table" / "entities" / f"{mob}.json", {
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
            "random_sequence": f"{NS}:entities/{mob}",
        })


# ---------------------------------------------------------------------------
# Recipes. Unique-input rule: every ingredient list contains at least one of
# this feature's own drop items (each used by no other recipe anywhere), so no
# canonical input set can collide with vanilla or other mod recipes.
# ---------------------------------------------------------------------------

RECIPES = {
    "magma_cream_from_hopper_slag": (["hopper_slag"], "minecraft:magma_cream", 2),
    "bone_meal_from_singed_fang": (["singed_fang"], "minecraft:bone_meal", 3),
    "phantom_membrane_from_ember_wing": (["ember_wing", "ember_wing"], "minecraft:phantom_membrane", 1),
    "magma_block_from_slag_shell": (["slag_shell", "slag_shell"], "minecraft:magma_block", 1),
    "feather_from_pyre_feather": (["pyre_feather"], "minecraft:feather", 2),
    "slime_ball_from_fumarole_gland": (["fumarole_gland"], "minecraft:slime_ball", 2),
    "bone_from_scorched_horn": (["scorched_horn"], "minecraft:bone", 4),
    "cooked_cod_from_lava_eel_fillet": (["lava_eel_fillet"], "minecraft:cooked_cod", 1),
    "blaze_powder_from_flare_essence": (["flare_essence"], "minecraft:blaze_powder", 2),
    "turtle_scute_from_smolder_scute": (["smolder_scute"], "minecraft:turtle_scute", 1),
    "leather_from_brimstone_hide": (["brimstone_hide"], "minecraft:leather", 2),
    "iron_ingot_from_pyroclast_core": (["pyroclast_core"], "minecraft:iron_ingot", 2),
}


def emit_recipes() -> None:
    rdir = DATA / "recipe" / "infernofauna"
    for name, (ingredients, result_id, count) in RECIPES.items():
        write_json(rdir / f"{name}.json", {
            "type": "minecraft:crafting_shapeless",
            "category": "misc",
            "ingredients": [f"{NS}:{i}" if ":" not in i else i for i in ingredients],
            "result": {"count": count, "id": result_id},
        })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    tex_count = emit_textures()
    emit_item_assets()
    emit_loot_tables()
    emit_recipes()
    en, de = build_lang()
    write_json(ASSETS / "lang" / "fragments" / "infernofauna.json", en)
    write_json(ASSETS / "lang" / "fragments_de" / "infernofauna.json", de)
    print(f"infernofauna_gen: {len(MOBS)} mobs / {len(ITEM_IDS)} items — "
          f"{tex_count} textures, {len(ITEM_IDS) * 2} item JSONs, {len(MOBS)} loot tables, "
          f"{len(RECIPES)} recipes, {len(en)} EN / {len(de)} DE lang keys generated.")


if __name__ == "__main__":
    main()
