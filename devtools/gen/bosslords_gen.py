#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4 "Inferno Lords" feature (WP12, 6 bosses).

Emits, directly into src/main/resources:
  - item model-definitions  assets/copper_inferno/items/<id>.json         (lib_gen.item_def)
  - item models             assets/copper_inferno/models/item/<id>.json   (item/generated)
  - item textures           assets/copper_inferno/textures/item/<id>.png
      * 6 spawn eggs: the matching vanilla <base>_spawn_egg.png extracted from the client
        jar (lib_gen.extract_vanilla) and remapped onto a per-boss palette (lib_gen.recolor)
      * 6 summon items + 12 drops + 6 trophies: thematic vanilla item textures extracted +
        recolored the same way
  - entity textures         assets/copper_inferno/textures/entity/bosslords/<boss>.png
      * the vanilla entity texture of each boss's base, extracted + recolored; consumed by
        the getTexture overrides in BossLordsFeatureClient
  - entity loot tables      data/copper_inferno/loot_table/entities/<boss>.json
                            (infernoboss schema incl. "random_sequence"; 3 pools:
                            2 drops + 1 trophy)
  - recipes                 data/copper_inferno/recipe/bosslords/*.json — every summon-item
                            recipe is shaped with one of THIS feature's unique drop items at
                            its center, so the canonical input set can never collide with
                            vanilla or other feature recipes
                            (check_recipe_collisions.py-safe by construction)
  - lang fragments          assets/copper_inferno/lang/fragments/bosslords.json (EN)
                            assets/copper_inferno/lang/fragments_de/bosslords.json (DE)

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

MOLTEN = [(0x2B, 0x10, 0x0C), (0x6E, 0x22, 0x10), (0xD8, 0x4A, 0x1A), (0xFF, 0xC8, 0x50)]
FLAME = [(0x5A, 0x1E, 0x08), (0xC2, 0x4A, 0x0E), (0xFF, 0x7A, 0x2F), (0xFF, 0xD8, 0x66)]
ASH = [(0x2E, 0x2B, 0x29), (0x5F, 0x5B, 0x57), (0x8F, 0x8A, 0x84), (0xB9, 0xB4, 0xAE)]
OBSIDIAN = [(0x14, 0x0B, 0x1E), (0x2E, 0x18, 0x40), (0x55, 0x2E, 0x66), (0x8A, 0x54, 0x96)]
SOUL = [(0x0E, 0x1A, 0x1C), (0x1E, 0x40, 0x44), (0x38, 0x74, 0x78), (0x6C, 0xC8, 0xC4)]
FORGE = [(0x1A, 0x16, 0x18), (0x3C, 0x34, 0x36), (0x6E, 0x60, 0x5C), (0xA8, 0x94, 0x84)]
GOLD = [(0x4A, 0x30, 0x10), (0x9C, 0x66, 0x1E), (0xE8, 0xA8, 0x38), (0xFF, 0xE8, 0x90)]
EMBER = [(0x3A, 0x14, 0x0C), (0x8A, 0x2E, 0x12), (0xE2, 0x58, 0x22), (0xFF, 0xB1, 0x6B)]

# ---------------------------------------------------------------------------
# Roster: boss id -> (vanilla spawn-egg texture base, egg palette,
#                     vanilla entity texture path, entity palette)
# ---------------------------------------------------------------------------

E = "assets/minecraft/textures/entity/"

BOSSES = {
    "the_slag_king": ("iron_golem", MOLTEN, E + "iron_golem/iron_golem.png", MOLTEN),
    "ember_matriarch": ("blaze", FLAME, E + "blaze.png", FLAME),
    "ash_colossus": ("iron_golem", ASH, E + "iron_golem/iron_golem.png", ASH),
    "magma_leviathan": ("ghast", OBSIDIAN, E + "ghast/ghast.png", OBSIDIAN),
    "cinder_reaper": ("skeleton", SOUL, E + "skeleton/skeleton.png", SOUL),
    "forge_tyrant": ("ravager", FORGE, E + "illager/ravager.png", FORGE),
}

# boss id -> list of (drop item id, min count, max count); one loot pool per entry,
# trophies last (always exactly 1).
LOOT = {
    "the_slag_king": [(f"{NS}:royal_slag", 1.0, 2.0), (f"{NS}:fissure_shard", 2.0, 4.0),
                      (f"{NS}:slag_crown", 1.0, 1.0)],
    "ember_matriarch": [(f"{NS}:matriarch_plume", 2.0, 3.0), (f"{NS}:living_flame", 1.0, 2.0),
                        (f"{NS}:matriarch_diadem", 1.0, 1.0)],
    "ash_colossus": [(f"{NS}:colossal_ash", 2.0, 4.0), (f"{NS}:blinding_grit", 1.0, 2.0),
                     (f"{NS}:ash_colossus_idol", 1.0, 1.0)],
    "magma_leviathan": [(f"{NS}:leviathan_scale", 2.0, 3.0), (f"{NS}:magma_bomb_shell", 1.0, 2.0),
                        (f"{NS}:leviathan_maw", 1.0, 1.0)],
    "cinder_reaper": [(f"{NS}:reaper_cinder", 2.0, 3.0), (f"{NS}:withered_cloth", 1.0, 2.0),
                      (f"{NS}:cinder_scythe", 1.0, 1.0)],
    "forge_tyrant": [(f"{NS}:forge_scrap", 2.0, 4.0), (f"{NS}:tyrant_hide", 1.0, 2.0),
                     (f"{NS}:tyrants_anvil", 1.0, 1.0)],
}

# item id -> (vanilla item texture base, palette): summon items, drops, trophies.
ITEMS = {
    "slag_king_core": ("heart_of_the_sea", MOLTEN),
    "royal_slag": ("magma_cream", MOLTEN),
    "fissure_shard": ("echo_shard", EMBER),
    "slag_crown": ("golden_helmet", GOLD),
    "ember_matriarch_sigil": ("fire_charge", FLAME),
    "matriarch_plume": ("feather", FLAME),
    "living_flame": ("blaze_powder", FLAME),
    "matriarch_diadem": ("golden_helmet", FLAME),
    "ash_colossus_core": ("heart_of_the_sea", ASH),
    "colossal_ash": ("gunpowder", ASH),
    "blinding_grit": ("glowstone_dust", ASH),
    "ash_colossus_idol": ("totem_of_undying", ASH),
    "magma_leviathan_sigil": ("fire_charge", OBSIDIAN),
    "leviathan_scale": ("prismarine_shard", MOLTEN),
    "magma_bomb_shell": ("fire_charge", MOLTEN),
    "leviathan_maw": ("ghast_tear", MOLTEN),
    "cinder_reaper_sigil": ("echo_shard", SOUL),
    "reaper_cinder": ("charcoal", SOUL),
    "withered_cloth": ("leather", SOUL),
    "cinder_scythe": ("netherite_hoe", EMBER),
    "forge_tyrant_core": ("totem_of_undying", FORGE),
    "forge_scrap": ("netherite_scrap", FORGE),
    "tyrant_hide": ("leather", FORGE),
    "tyrants_anvil": ("netherite_ingot", FORGE),
}

ITEM_IDS = list(ITEMS) + [f"{boss}_spawn_egg" for boss in BOSSES]

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

BOSS_NAMES = {
    "the_slag_king": ("The Slag King", "Der Schlackenkönig"),
    "ember_matriarch": ("Ember Matriarch", "Glutmatriarchin"),
    "ash_colossus": ("Ash Colossus", "Aschenkoloss"),
    "magma_leviathan": ("Magma Leviathan", "Magma-Leviathan"),
    "cinder_reaper": ("Cinder Reaper", "Zunderschnitter"),
    "forge_tyrant": ("Forge Tyrant", "Schmiedetyrann"),
}

ITEM_NAMES = {
    "slag_king_core": ("Slag King Core", "Schlackenkönig-Kern"),
    "royal_slag": ("Royal Slag", "Königsschlacke"),
    "fissure_shard": ("Fissure Shard", "Spaltsplitter"),
    "slag_crown": ("Slag Crown", "Schlackenkrone"),
    "ember_matriarch_sigil": ("Ember Matriarch Sigil", "Glutmatriarchin-Siegel"),
    "matriarch_plume": ("Matriarch Plume", "Matriarchinnen-Feder"),
    "living_flame": ("Living Flame", "Lebende Flamme"),
    "matriarch_diadem": ("Matriarch Diadem", "Matriarchinnen-Diadem"),
    "ash_colossus_core": ("Ash Colossus Core", "Aschenkoloss-Kern"),
    "colossal_ash": ("Colossal Ash", "Kolossale Asche"),
    "blinding_grit": ("Blinding Grit", "Blendgrus"),
    "ash_colossus_idol": ("Ash Colossus Idol", "Aschenkoloss-Götzenbild"),
    "magma_leviathan_sigil": ("Magma Leviathan Sigil", "Magma-Leviathan-Siegel"),
    "leviathan_scale": ("Leviathan Scale", "Leviathan-Schuppe"),
    "magma_bomb_shell": ("Magma Bomb Shell", "Magmabomben-Hülle"),
    "leviathan_maw": ("Leviathan Maw", "Leviathan-Schlund"),
    "cinder_reaper_sigil": ("Cinder Reaper Sigil", "Zunderschnitter-Siegel"),
    "reaper_cinder": ("Reaper Cinder", "Schnitterzunder"),
    "withered_cloth": ("Withered Cloth", "Verdorrtes Tuch"),
    "cinder_scythe": ("Cinder Scythe", "Zundersense"),
    "forge_tyrant_core": ("Forge Tyrant Core", "Schmiedetyrann-Kern"),
    "forge_scrap": ("Forge Scrap", "Schmiedeschrott"),
    "tyrant_hide": ("Tyrant Hide", "Tyrannenhaut"),
    "tyrants_anvil": ("Tyrant's Anvil", "Tyrannenamboss"),
}

MESSAGES_EN = {
    f"message.{NS}.bosslords.wrong_dimension": "This relic only answers within the Inferno dimension.",
    f"message.{NS}.bosslords.peaceful": "The Inferno Lords do not answer on peaceful difficulty.",
    f"message.{NS}.bosslords.blocked": "There is no room for the boss to rise here.",
}

MESSAGES_DE = {
    f"message.{NS}.bosslords.wrong_dimension": "Dieses Relikt wirkt nur in der Inferno-Dimension.",
    f"message.{NS}.bosslords.peaceful": "Die Inferno-Fürsten antworten nicht auf der Schwierigkeit Friedlich.",
    f"message.{NS}.bosslords.blocked": "Hier ist kein Platz, damit der Boss sich erheben kann.",
}


def build_lang() -> tuple[dict, dict]:
    en, de = dict(MESSAGES_EN), dict(MESSAGES_DE)
    for boss, (name_en, name_de) in BOSS_NAMES.items():
        en[f"entity.{NS}.{boss}"] = name_en
        de[f"entity.{NS}.{boss}"] = name_de
        en[f"item.{NS}.{boss}_spawn_egg"] = f"{name_en} Spawn Egg"
        de[f"item.{NS}.{boss}_spawn_egg"] = f"{name_de}-Spawn-Ei"
    for item, (name_en, name_de) in ITEM_NAMES.items():
        en[f"item.{NS}.{item}"] = name_en
        de[f"item.{NS}.{item}"] = name_de
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
    entity_dir = ASSETS / "textures" / "entity" / "bosslords"
    for boss, (egg_base, egg_pal, entity_tex, entity_pal) in BOSSES.items():
        egg = recolor(extract_vanilla(f"assets/minecraft/textures/item/{egg_base}_spawn_egg.png"), egg_pal)
        save_png(item_dir / f"{boss}_spawn_egg.png", egg)
        body = recolor(extract_vanilla(entity_tex), entity_pal)
        save_png(entity_dir / f"{boss}.png", body)
        count += 2
    for item, (tex_base, pal) in ITEMS.items():
        img = recolor(extract_vanilla(f"assets/minecraft/textures/item/{tex_base}.png"), pal)
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
# Entity loot tables (infernoboss schema incl. "random_sequence")
# ---------------------------------------------------------------------------


def emit_loot_tables() -> None:
    for boss, drops in LOOT.items():
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
# Recipes. Unique-input rule: every summon-item recipe is shaped with one of
# this feature's own drop items at its center (each used by no other recipe
# anywhere), so no canonical input set can collide with vanilla or other mod
# recipes. Grids match the handbook entries in BossLordsFeature 1:1.
# ---------------------------------------------------------------------------

RECIPES = {
    "slag_king_core": (
        {"M": "minecraft:magma_block", "I": "minecraft:iron_ingot", "R": f"{NS}:royal_slag"},
        ["MIM", "IRI", "MIM"]),
    "ember_matriarch_sigil": (
        {"B": "minecraft:blaze_powder", "F": "minecraft:fire_charge", "P": f"{NS}:matriarch_plume"},
        ["BFB", "FPF", "BFB"]),
    "ash_colossus_core": (
        {"T": "minecraft:tuff", "B": "minecraft:bone_meal", "A": f"{NS}:colossal_ash"},
        ["TBT", "BAB", "TBT"]),
    "magma_leviathan_sigil": (
        {"O": "minecraft:obsidian", "C": "minecraft:magma_cream", "S": f"{NS}:leviathan_scale"},
        ["OCO", "CSC", "OCO"]),
    "cinder_reaper_sigil": (
        {"B": "minecraft:bone", "S": "minecraft:soul_sand", "C": f"{NS}:reaper_cinder"},
        ["BSB", "SCS", "BSB"]),
    "forge_tyrant_core": (
        {"I": "minecraft:iron_block", "C": "minecraft:coal", "F": f"{NS}:forge_scrap"},
        ["ICI", "CFC", "ICI"]),
}


def emit_recipes() -> None:
    rdir = DATA / "recipe" / "bosslords"
    for name, (key, pattern) in RECIPES.items():
        write_json(rdir / f"{name}.json", {
            "type": "minecraft:crafting_shaped",
            "category": "misc",
            "key": key,
            "pattern": pattern,
            "result": {"count": 1, "id": f"{NS}:{name}"},
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
    write_json(ASSETS / "lang" / "fragments" / "bosslords.json", en)
    write_json(ASSETS / "lang" / "fragments_de" / "bosslords.json", de)
    print(f"bosslords_gen: {len(BOSSES)} bosses / {len(ITEM_IDS)} items — "
          f"{tex_count} textures, {len(ITEM_IDS) * 2} item JSONs, {len(LOOT)} loot tables, "
          f"{len(RECIPES)} recipes, {len(en)} EN / {len(de)} DE lang keys generated.")


if __name__ == "__main__":
    main()
