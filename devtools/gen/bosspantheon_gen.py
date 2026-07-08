#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4 "Copper Pantheon" feature (WP13, 6 bosses).

Emits, directly into src/main/resources:
  - item model-definitions  assets/copper_inferno/items/<id>.json         (lib_gen.item_def)
  - item models             assets/copper_inferno/models/item/<id>.json   (item/generated)
  - item textures           assets/copper_inferno/textures/item/<id>.png
      * 6 spawn eggs: the matching vanilla <base>_spawn_egg.png extracted from the client
        jar (lib_gen.extract_vanilla) and remapped onto the boss palette (lib_gen.recolor)
      * 6 summon items, 12 drops, 6 trophies + the Pantheon Relic catalyst: thematic
        vanilla item textures extracted + recolored the same way
  - entity textures         assets/copper_inferno/textures/entity/bosspantheon/<boss>.png
      * the vanilla entity texture of each boss's base, extracted + recolored; consumed by
        the getTexture overrides in BossPantheonFeatureClient
  - entity loot tables      data/copper_inferno/loot_table/entities/<boss>.json
                            (infernoboss schema incl. "random_sequence"; 3 pools per boss:
                            2 drops + 1 EPIC trophy)
  - recipes                 data/copper_inferno/recipe/bosspantheon/*.json — the Pantheon
                            Relic is crafted from vanilla items, and every summon-item
                            recipe rings THAT relic (an item no other recipe anywhere
                            consumes), so no canonical input set can collide with vanilla
                            or other feature recipes (check_recipe_collisions.py-safe by
                            construction)
  - lang fragments          assets/copper_inferno/lang/fragments/bosspantheon.json (EN)
                            assets/copper_inferno/lang/fragments_de/bosspantheon.json (DE)

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
# Palettes (dark -> light ramps for lib_gen.recolor), one per boss + the relic
# ---------------------------------------------------------------------------

VERDIGRIS = [(0x1E, 0x3B, 0x2E), (0x37, 0x6E, 0x55), (0x57, 0xA0, 0x7B), (0xA0, 0xD8, 0xB8)]
PATINA = [(0x0E, 0x3A, 0x3E), (0x1F, 0x6B, 0x6E), (0x3A, 0xA7, 0x9E), (0x8F, 0xD9, 0xCE)]
COIL = [(0x4A, 0x22, 0x12), (0x8F, 0x40, 0x2A), (0xE0, 0x73, 0x4D), (0xFF, 0xB8, 0x8A)]
STORM = [(0x3A, 0x32, 0x10), (0x8A, 0x76, 0x1A), (0xE8, 0xC8, 0x38), (0xFF, 0xF0, 0x9E)]
MINT = [(0x14, 0x46, 0x38), (0x2E, 0x8A, 0x66), (0x5F, 0xD4, 0x9E), (0xC2, 0xFF, 0xE0)]
GOLD = [(0x4A, 0x30, 0x08), (0x9C, 0x6A, 0x10), (0xF6, 0xC1, 0x2B), (0xFF, 0xEC, 0x9E)]
RELIC = [(0x2A, 0x12, 0x3A), (0x5A, 0x2E, 0x7A), (0x9C, 0x5A, 0xC8), (0xE0, 0xB8, 0xFF)]

# ---------------------------------------------------------------------------
# Roster: boss id -> (vanilla spawn-egg texture base, vanilla entity texture
# path, palette, [(drop id, min, max), ...] — last drop is the EPIC trophy)
# ---------------------------------------------------------------------------

E = "assets/minecraft/textures/entity/"

BOSSES = {
    "the_verdigris_prophet": ("evoker", E + "illager/evoker.png", VERDIGRIS, [
        (f"{NS}:verdigris_tome", 1.0, 2.0),
        (f"{NS}:corroded_fang", 2.0, 4.0),
        (f"{NS}:prophets_circlet", 1.0, 1.0),
    ]),
    "patina_warden": ("iron_golem", E + "iron_golem/iron_golem.png", PATINA, [
        (f"{NS}:patina_plate", 2.0, 4.0),
        (f"{NS}:warden_rivet", 3.0, 6.0),
        (f"{NS}:patina_aegis", 1.0, 1.0),
    ]),
    "coil_hydra": ("ghast", E + "ghast/ghast.png", COIL, [
        (f"{NS}:coil_scale", 2.0, 4.0),
        (f"{NS}:hydra_tear", 1.0, 3.0),
        (f"{NS}:hydra_diadem", 1.0, 1.0),
    ]),
    "thunder_conductor": ("blaze", E + "blaze.png", STORM, [
        (f"{NS}:storm_rod", 1.0, 3.0),
        (f"{NS}:charged_filament", 2.0, 4.0),
        (f"{NS}:thunder_scepter", 1.0, 1.0),
    ]),
    "the_mint_king": ("slime", E + "slime/slime.png", MINT, [
        (f"{NS}:royal_jelly", 3.0, 6.0),
        (f"{NS}:mint_crystal", 2.0, 4.0),
        (f"{NS}:mint_crown", 1.0, 1.0),
    ]),
    "gilded_executioner": ("vindicator", E + "illager/vindicator.png", GOLD, [
        (f"{NS}:gilded_scrap", 3.0, 6.0),
        (f"{NS}:executioner_pendant", 1.0, 2.0),
        (f"{NS}:gilded_greataxe", 1.0, 1.0),
    ]),
}

# non-egg item id -> (vanilla item texture base, palette)
ITEMS = {
    "pantheon_relic": ("nether_star", RELIC),
    # the_verdigris_prophet
    "prophet_sigil": ("echo_shard", VERDIGRIS),
    "verdigris_tome": ("book", VERDIGRIS),
    "corroded_fang": ("amethyst_shard", VERDIGRIS),
    "prophets_circlet": ("golden_helmet", VERDIGRIS),
    # patina_warden
    "patina_core": ("heart_of_the_sea", PATINA),
    "patina_plate": ("iron_ingot", PATINA),
    "warden_rivet": ("iron_nugget", PATINA),
    "patina_aegis": ("totem_of_undying", PATINA),
    # coil_hydra
    "hydra_sigil": ("fire_charge", COIL),
    "coil_scale": ("turtle_scute", COIL),
    "hydra_tear": ("ghast_tear", COIL),
    "hydra_diadem": ("nether_star", COIL),
    # thunder_conductor
    "conductor_core": ("prismarine_crystals", STORM),
    "storm_rod": ("blaze_rod", STORM),
    "charged_filament": ("glow_ink_sac", STORM),
    "thunder_scepter": ("breeze_rod", STORM),
    # the_mint_king
    "mint_king_core": ("ender_eye", MINT),
    "royal_jelly": ("slime_ball", MINT),
    "mint_crystal": ("quartz", MINT),
    "mint_crown": ("turtle_helmet", MINT),
    # gilded_executioner
    "executioner_core": ("raw_gold", GOLD),
    "gilded_scrap": ("gold_nugget", GOLD),
    "executioner_pendant": ("heart_of_the_sea", GOLD),
    "gilded_greataxe": ("golden_axe", GOLD),
}

ITEM_IDS = list(ITEMS) + [f"{boss}_spawn_egg" for boss in BOSSES]

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

BOSS_NAMES = {
    "the_verdigris_prophet": ("The Verdigris Prophet", "Der Grünspan-Prophet"),
    "patina_warden": ("Patina Warden", "Patina-Wächter"),
    "coil_hydra": ("Coil Hydra", "Spulen-Hydra"),
    "thunder_conductor": ("Thunder Conductor", "Donnerleiter"),
    "the_mint_king": ("The Mint King", "Der Minzkönig"),
    "gilded_executioner": ("Gilded Executioner", "Vergoldeter Scharfrichter"),
}

ITEM_NAMES = {
    "pantheon_relic": ("Pantheon Relic", "Pantheon-Reliquie"),
    "prophet_sigil": ("Prophet Sigil", "Prophetensiegel"),
    "verdigris_tome": ("Verdigris Tome", "Grünspanfoliant"),
    "corroded_fang": ("Corroded Fang", "Korrodierter Fangzahn"),
    "prophets_circlet": ("Prophet's Circlet", "Diadem des Propheten"),
    "patina_core": ("Patina Core", "Patina-Kern"),
    "patina_plate": ("Patina Plate", "Patinaplatte"),
    "warden_rivet": ("Warden Rivet", "Wächterniete"),
    "patina_aegis": ("Patina Aegis", "Patina-Ägide"),
    "hydra_sigil": ("Hydra Sigil", "Hydrasiegel"),
    "coil_scale": ("Coil Scale", "Spulenschuppe"),
    "hydra_tear": ("Hydra Tear", "Hydraträne"),
    "hydra_diadem": ("Hydra Diadem", "Hydra-Diadem"),
    "conductor_core": ("Conductor Core", "Leiterkern"),
    "storm_rod": ("Storm Rod", "Sturmrute"),
    "charged_filament": ("Charged Filament", "Geladenes Filament"),
    "thunder_scepter": ("Thunder Scepter", "Donnerzepter"),
    "mint_king_core": ("Mint King Core", "Minzkönig-Kern"),
    "royal_jelly": ("Royal Jelly", "Königsgelee"),
    "mint_crystal": ("Mint Crystal", "Minzkristall"),
    "mint_crown": ("Mint Crown", "Minzkrone"),
    "executioner_core": ("Executioner Core", "Scharfrichter-Kern"),
    "gilded_scrap": ("Gilded Scrap", "Vergoldeter Schrott"),
    "executioner_pendant": ("Executioner Pendant", "Scharfrichter-Anhänger"),
    "gilded_greataxe": ("Gilded Greataxe", "Vergoldete Großaxt"),
}

MESSAGES = {
    "message.copper_inferno.pantheon_summon.peaceful": (
        "The Copper Pantheon does not answer on Peaceful difficulty.",
        "Das Kupfer-Pantheon antwortet nicht auf der Schwierigkeit Friedlich."),
    "message.copper_inferno.pantheon_summon.blocked": (
        "There is no room here for the boss to rise.",
        "Hier ist kein Platz, damit sich der Boss erheben kann."),
}


def build_lang() -> tuple[dict, dict]:
    en, de = {}, {}
    for boss, (name_en, name_de) in BOSS_NAMES.items():
        en[f"entity.{NS}.{boss}"] = name_en
        de[f"entity.{NS}.{boss}"] = name_de
        en[f"item.{NS}.{boss}_spawn_egg"] = f"{name_en} Spawn Egg"
        de[f"item.{NS}.{boss}_spawn_egg"] = f"{name_de}-Spawn-Ei"
    for item, (name_en, name_de) in ITEM_NAMES.items():
        en[f"item.{NS}.{item}"] = name_en
        de[f"item.{NS}.{item}"] = name_de
    for key, (text_en, text_de) in MESSAGES.items():
        en[key] = text_en
        de[key] = text_de
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
    entity_dir = ASSETS / "textures" / "entity" / "bosspantheon"
    for boss, (egg_base, entity_tex, palette, _drops) in BOSSES.items():
        egg = recolor(extract_vanilla(f"assets/minecraft/textures/item/{egg_base}_spawn_egg.png"), palette)
        save_png(item_dir / f"{boss}_spawn_egg.png", egg)
        body = recolor(extract_vanilla(entity_tex), palette)
        save_png(entity_dir / f"{boss}.png", body)
        count += 2
    for item, (tex_base, palette) in ITEMS.items():
        img = recolor(extract_vanilla(f"assets/minecraft/textures/item/{tex_base}.png"), palette)
        save_png(item_dir / f"{item}.png", img)
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
# Entity loot tables (infernoboss schema incl. "random_sequence"; one pool per
# drop, the last pool being the guaranteed EPIC trophy)
# ---------------------------------------------------------------------------


def emit_loot_tables() -> None:
    for boss, (_egg, _tex, _pal, drops) in BOSSES.items():
        write_json(DATA / "loot_table" / "entities" / f"{boss}.json", {
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
            "random_sequence": f"{NS}:entities/{boss}",
        })


# ---------------------------------------------------------------------------
# Recipes (vanilla 1.21.9 crafting_shaped format). Unique-input rule: the
# Pantheon Relic itself rings a diamond with copper + amethyst (no vanilla or
# mod recipe uses that input set - collision-checked), and every summon-item
# recipe rings the relic, an item no other recipe anywhere consumes.
# ---------------------------------------------------------------------------

# summon item -> (corner ingredient, edge ingredient)
SUMMON_RECIPES = {
    "prophet_sigil": ("minecraft:oxidized_copper", "minecraft:emerald"),
    "patina_core": ("minecraft:cut_copper", "minecraft:iron_ingot"),
    "hydra_sigil": ("minecraft:fire_charge", "minecraft:ghast_tear"),
    "conductor_core": ("minecraft:lightning_rod", "minecraft:blaze_rod"),
    "mint_king_core": ("minecraft:slime_ball", "minecraft:sugar"),
    "executioner_core": ("minecraft:gold_ingot", "minecraft:gold_nugget"),
}


def emit_recipes() -> None:
    rdir = DATA / "recipe" / "bosspantheon"
    write_json(rdir / "pantheon_relic.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {
            "C": "minecraft:copper_ingot",
            "A": "minecraft:amethyst_shard",
            "D": "minecraft:diamond",
        },
        "pattern": ["CAC", "ADA", "CAC"],
        "result": {"count": 1, "id": f"{NS}:pantheon_relic"},
    })
    for summon, (corner, edge) in SUMMON_RECIPES.items():
        write_json(rdir / f"{summon}.json", {
            "type": "minecraft:crafting_shaped",
            "category": "misc",
            "key": {
                "C": corner,
                "E": edge,
                "R": f"{NS}:pantheon_relic",
            },
            "pattern": ["CEC", "ERE", "CEC"],
            "result": {"count": 1, "id": f"{NS}:{summon}"},
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
    write_json(ASSETS / "lang" / "fragments" / "bosspantheon.json", en)
    write_json(ASSETS / "lang" / "fragments_de" / "bosspantheon.json", de)
    print(f"bosspantheon_gen: {len(BOSSES)} bosses / {len(ITEM_IDS)} items — "
          f"{tex_count} textures, {len(ITEM_IDS) * 2} item JSONs, {len(BOSSES)} loot tables, "
          f"{1 + len(SUMMON_RECIPES)} recipes, {len(en)} EN / {len(de)} DE lang keys generated.")


if __name__ == "__main__":
    main()
