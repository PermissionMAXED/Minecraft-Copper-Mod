#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4 "constructs" feature (WP11: 24 mobs, 36 items).

Idempotent: running it any number of times produces byte-identical files (all textures are
recolors of vanilla textures - fully deterministic, no RNG). Emits, directly into
src/main/resources:
  - spawn-egg item textures   assets/copper_inferno/textures/item/<mob>_spawn_egg.png
                              (extract_vanilla of the matching vanilla 1.21.5+ per-mob egg
                              sprite, recolored to the construct's metallic palette)
  - drop item textures        assets/copper_inferno/textures/item/<drop>.png
                              (extract_vanilla of a shape-matched vanilla item, recolored)
  - entity textures           assets/copper_inferno/textures/entity/constructs/<mob>.png
                              (extract_vanilla of the base mob's texture, recolored; consumed
                              by the ConstructRenderers getTexture overrides)
  - item model-definitions    assets/copper_inferno/items/<id>.json
  - item models               assets/copper_inferno/models/item/<id>.json (item/generated)
  - entity loot tables        data/copper_inferno/loot_table/entities/<mob>.json
                              (infernomobs schema incl. "random_sequence")
  - recipes                   data/copper_inferno/recipe/constructs/*.json (12 shapeless
                              conversions; every input set contains a construct-only drop
                              item, so inputs are globally unique - verified by
                              devtools/check_recipe_collisions.py)
  - lang fragments            assets/copper_inferno/lang/fragments/constructs.json (EN)
                              assets/copper_inferno/lang/fragments_de/constructs.json (DE)

Recipes reference ONLY vanilla ids and this feature's own ids.
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import NS, extract_vanilla, item_def, recolor, write_json  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS

# ---------------------------------------------------------------------------
# Metallic / industrial palettes (dark -> light ramps for lib_gen.recolor)
# ---------------------------------------------------------------------------

COPPER = [(0x2E, 0x16, 0x0C), (0x6B, 0x36, 0x1D), (0xB4, 0x62, 0x38), (0xE7, 0x9C, 0x63), (0xFF, 0xD3, 0xA6)]
OXIDIZED = [(0x0E, 0x2E, 0x28), (0x1E, 0x59, 0x4C), (0x3B, 0x8F, 0x78), (0x6C, 0xC0, 0xA4), (0xAD, 0xE6, 0xD0)]
IRON = [(0x1B, 0x1D, 0x21), (0x45, 0x49, 0x50), (0x7C, 0x82, 0x8A), (0xB4, 0xB9, 0xBF), (0xE8, 0xEB, 0xEE)]
GUNMETAL = [(0x11, 0x13, 0x17), (0x2B, 0x2F, 0x36), (0x4C, 0x52, 0x5C), (0x74, 0x7B, 0x86), (0xA6, 0xAD, 0xB6)]
BRASS = [(0x3A, 0x28, 0x0C), (0x7A, 0x58, 0x1C), (0xB8, 0x8A, 0x2E), (0xE3, 0xB8, 0x4F), (0xFF, 0xE8, 0x9C)]
SLAG = [(0x14, 0x0C, 0x0C), (0x33, 0x22, 0x1E), (0x5C, 0x39, 0x28), (0xB4, 0x4A, 0x1E), (0xFF, 0x9A, 0x3C)]
REDSTONE = [(0x2A, 0x05, 0x05), (0x6E, 0x0C, 0x0A), (0xB4, 0x18, 0x10), (0xE8, 0x39, 0x25), (0xFF, 0x8A, 0x66)]
MAGNETITE = [(0x0C, 0x0E, 0x1C), (0x22, 0x28, 0x44), (0x3E, 0x4A, 0x70), (0x6A, 0x7B, 0xA4), (0xA8, 0xB8, 0xD8)]
BRONZE = [(0x2C, 0x18, 0x08), (0x5E, 0x38, 0x14), (0x96, 0x60, 0x28), (0xC8, 0x92, 0x48), (0xF2, 0xC8, 0x86)]
SOOT = [(0x0A, 0x08, 0x08), (0x22, 0x1E, 0x1E), (0x40, 0x3A, 0x38), (0x66, 0x5E, 0x5A), (0x94, 0x8A, 0x84)]
DOOM = [(0x1C, 0x04, 0x08), (0x4C, 0x0A, 0x12), (0x8A, 0x14, 0x1E), (0xC4, 0x2E, 0x2E), (0xF2, 0x6A, 0x4A)]
STEAM = [(0x2E, 0x34, 0x3A), (0x5C, 0x66, 0x70), (0x8E, 0x9A, 0xA4), (0xC2, 0xCC, 0xD4), (0xF2, 0xF6, 0xF8)]

# mob id -> (vanilla base name for the egg sprite, entity texture path in the client jar,
#            palette)
MOBS = {
    "copper_sentinel": ("iron_golem", "entity/iron_golem/iron_golem.png", COPPER),
    "slag_construct": ("iron_golem", "entity/iron_golem/iron_golem.png", SLAG),
    "doom_acolyte": ("vindicator", "entity/illager/vindicator.png", DOOM),
    "forge_keeper": ("pillager", "entity/illager/pillager.png", BRONZE),
    "anvil_mimic": ("silverfish", "entity/silverfish.png", GUNMETAL),
    "gear_spider": ("spider", "entity/spider/spider.png", COPPER),
    "piston_hopper": ("rabbit", "entity/rabbit/white.png", IRON),
    "redstone_shade": ("vex", "entity/illager/vex.png", REDSTONE),
    "wire_wraith": ("phantom", "entity/phantom.png", REDSTONE),
    "boiler_blaze": ("blaze", "entity/blaze.png", BRONZE),
    "furnace_golem": ("iron_golem", "entity/iron_golem/iron_golem.png", BRASS),
    "scrap_vulture": ("parrot", "entity/parrot/parrot_grey.png", GUNMETAL),
    "clockwork_bee": ("bee", "entity/bee/bee.png", BRASS),
    "steam_ghast": ("ghast", "entity/ghast/ghast.png", STEAM),
    "pipe_serpent": ("cave_spider", "entity/spider/cave_spider.png", OXIDIZED),
    "grinder_zoglin": ("zoglin", "entity/hoglin/zoglin.png", GUNMETAL),
    "plated_husk": ("husk", "entity/zombie/husk.png", BRONZE),
    "riveted_stray": ("stray", "entity/skeleton/stray.png", IRON),
    "tesla_creeper": ("creeper", "entity/creeper/creeper.png", REDSTONE),
    "magnet_mite": ("endermite", "entity/endermite.png", MAGNETITE),
    "crucible_witch": ("witch", "entity/witch.png", SLAG),
    "bellows_bat": ("bat", "entity/bat.png", COPPER),
    "ingot_golem": ("snow_golem", "entity/snow_golem.png", IRON),
    "doom_marauder": ("pillager", "entity/illager/pillager.png", DOOM),
}

# drop item id -> (vanilla item texture to recolor, palette)
DROPS = {
    "sentinel_plating": ("netherite_scrap", COPPER),
    "slag_grit": ("gunpowder", SLAG),
    "doom_emblem": ("echo_shard", DOOM),
    "forge_bellows": ("leather", BRONZE),
    "anvil_shard": ("flint", GUNMETAL),
    "copper_gearwheel": ("heart_of_the_sea", COPPER),
    "piston_spring": ("blaze_rod", IRON),
    "redstone_filament": ("string", REDSTONE),
    "boiler_plate": ("iron_ingot", BRONZE),
    "rivet_bolt": ("iron_nugget", IRON),
    "magnetite_shard": ("amethyst_shard", MAGNETITE),
    "crucible_dross": ("glowstone_dust", SLAG),
}

# mob id -> (loot drop item id, min count, max count)
LOOT = {
    "copper_sentinel": (f"{NS}:sentinel_plating", 0.0, 2.0),
    "slag_construct": (f"{NS}:slag_grit", 1.0, 3.0),
    "doom_acolyte": (f"{NS}:doom_emblem", 0.0, 1.0),
    "forge_keeper": (f"{NS}:forge_bellows", 0.0, 1.0),
    "anvil_mimic": (f"{NS}:anvil_shard", 0.0, 2.0),
    "gear_spider": (f"{NS}:copper_gearwheel", 0.0, 1.0),
    "piston_hopper": (f"{NS}:piston_spring", 0.0, 1.0),
    "redstone_shade": (f"{NS}:redstone_filament", 0.0, 2.0),
    "wire_wraith": (f"{NS}:redstone_filament", 1.0, 2.0),
    "boiler_blaze": (f"{NS}:boiler_plate", 0.0, 1.0),
    "furnace_golem": (f"{NS}:sentinel_plating", 1.0, 2.0),
    "scrap_vulture": ("minecraft:raw_copper", 0.0, 2.0),
    "clockwork_bee": (f"{NS}:copper_gearwheel", 0.0, 1.0),
    "steam_ghast": (f"{NS}:boiler_plate", 1.0, 2.0),
    "pipe_serpent": ("minecraft:string", 0.0, 2.0),
    "grinder_zoglin": ("minecraft:leather", 0.0, 2.0),
    "plated_husk": (f"{NS}:rivet_bolt", 0.0, 2.0),
    "riveted_stray": (f"{NS}:rivet_bolt", 0.0, 2.0),
    "tesla_creeper": (f"{NS}:redstone_filament", 0.0, 2.0),
    "magnet_mite": (f"{NS}:magnetite_shard", 0.0, 1.0),
    "crucible_witch": (f"{NS}:crucible_dross", 1.0, 2.0),
    "bellows_bat": (f"{NS}:forge_bellows", 0.0, 1.0),
    "ingot_golem": ("minecraft:iron_nugget", 1.0, 3.0),
    "doom_marauder": (f"{NS}:doom_emblem", 0.0, 2.0),
}

ITEM_IDS = [f"{mob}_spawn_egg" for mob in MOBS] + list(DROPS)

# ---------------------------------------------------------------------------
# Lang (EN + DE)
# ---------------------------------------------------------------------------

NAMES = {
    "copper_sentinel": ("Copper Sentinel", "Kupferwächter"),
    "slag_construct": ("Slag Construct", "Schlackenkonstrukt"),
    "doom_acolyte": ("Doom Acolyte", "DOOM-Akolyth"),
    "forge_keeper": ("Forge Keeper", "Schmiedewart"),
    "anvil_mimic": ("Anvil Mimic", "Amboss-Mimic"),
    "gear_spider": ("Gear Spider", "Zahnradspinne"),
    "piston_hopper": ("Piston Hopper", "Kolbenhoppler"),
    "redstone_shade": ("Redstone Shade", "Redstone-Schemen"),
    "wire_wraith": ("Wire Wraith", "Drahtgespenst"),
    "boiler_blaze": ("Boiler Blaze", "Kessellohe"),
    "furnace_golem": ("Furnace Golem", "Ofengolem"),
    "scrap_vulture": ("Scrap Vulture", "Schrottgeier"),
    "clockwork_bee": ("Clockwork Bee", "Uhrwerkbiene"),
    "steam_ghast": ("Steam Ghast", "Dampfghast"),
    "pipe_serpent": ("Pipe Serpent", "Rohrschlange"),
    "grinder_zoglin": ("Grinder Zoglin", "Mahlwerk-Zoglin"),
    "plated_husk": ("Plated Husk", "Plattierter Wüstenzombie"),
    "riveted_stray": ("Riveted Stray", "Vernieteter Eiswanderer"),
    "tesla_creeper": ("Tesla Creeper", "Tesla-Creeper"),
    "magnet_mite": ("Magnet Mite", "Magnetmilbe"),
    "crucible_witch": ("Crucible Witch", "Tiegelhexe"),
    "bellows_bat": ("Bellows Bat", "Blasebalg-Fledermaus"),
    "ingot_golem": ("Ingot Golem", "Barrengolem"),
    "doom_marauder": ("Doom Marauder", "DOOM-Marodeur"),
}

DROP_NAMES = {
    "sentinel_plating": ("Sentinel Plating", "Wächterplattierung"),
    "slag_grit": ("Slag Grit", "Schlackensplitt"),
    "doom_emblem": ("Doom Emblem", "DOOM-Emblem"),
    "forge_bellows": ("Forge Bellows", "Schmiedeblasebalg"),
    "anvil_shard": ("Anvil Shard", "Amboss-Splitter"),
    "copper_gearwheel": ("Copper Gearwheel", "Kupfer-Getrieberad"),
    "piston_spring": ("Piston Spring", "Kolbenfeder"),
    "redstone_filament": ("Redstone Filament", "Redstone-Glühfaden"),
    "boiler_plate": ("Boiler Plate", "Kesselplatte"),
    "rivet_bolt": ("Rivet Bolt", "Nietbolzen"),
    "magnetite_shard": ("Magnetite Shard", "Magnetitscherbe"),
    "crucible_dross": ("Crucible Dross", "Tiegelkrätze"),
}


def lang(index: int) -> dict:
    out = {}
    for mob, names in NAMES.items():
        out[f"entity.{NS}.{mob}"] = names[index]
        suffix = " Spawn Egg" if index == 0 else "-Spawn-Ei"
        out[f"item.{NS}.{mob}_spawn_egg"] = names[index] + suffix
    for drop, names in DROP_NAMES.items():
        out[f"item.{NS}.{drop}"] = names[index]
    return out


# ---------------------------------------------------------------------------
# Textures (all extract_vanilla + recolor; deterministic)
# ---------------------------------------------------------------------------

def emit_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    entity_dir = ASSETS / "textures" / "entity" / "constructs"
    item_dir.mkdir(parents=True, exist_ok=True)
    entity_dir.mkdir(parents=True, exist_ok=True)
    for mob, (egg_base, entity_tex, palette) in MOBS.items():
        egg = extract_vanilla(f"assets/minecraft/textures/item/{egg_base}_spawn_egg.png")
        recolor(egg, palette).save(item_dir / f"{mob}_spawn_egg.png")
        body = extract_vanilla(f"assets/minecraft/textures/{entity_tex}")
        recolor(body, palette).save(entity_dir / f"{mob}.png")
    for drop, (src, palette) in DROPS.items():
        img = extract_vanilla(f"assets/minecraft/textures/item/{src}.png")
        recolor(img, palette).save(item_dir / f"{drop}.png")


# ---------------------------------------------------------------------------
# Item model-definitions + models (vanilla 1.21.9 formats via lib_gen.item_def)
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
# Recipes (vanilla 1.21.9 crafting_shapeless format; every input set contains a
# construct-only drop item -> globally unique inputs, checked by
# devtools/check_recipe_collisions.py)
# ---------------------------------------------------------------------------

def emit_recipes() -> None:
    m = lambda p: f"{NS}:{p}"
    rdir = DATA / "recipe" / "constructs"
    recipes = {
        # one recipe per construct drop item
        "copper_ingots_from_sentinel_plating": ([m("sentinel_plating")], "minecraft:copper_ingot", 2),
        "flint_from_slag_grit": ([m("slag_grit"), m("slag_grit")], "minecraft:flint", 1),
        "wither_rose_from_doom_emblem": ([m("doom_emblem")], "minecraft:wither_rose", 1),
        "charcoal_from_forge_bellows": ([m("forge_bellows")], "minecraft:charcoal", 3),
        "iron_ingot_from_anvil_shards": ([m("anvil_shard")] * 3, "minecraft:iron_ingot", 1),
        "copper_ingots_from_copper_gearwheel": ([m("copper_gearwheel")], "minecraft:copper_ingot", 3),
        "slime_ball_from_piston_spring": ([m("piston_spring")], "minecraft:slime_ball", 1),
        "redstone_from_redstone_filament": ([m("redstone_filament")], "minecraft:redstone", 2),
        "bucket_from_boiler_plates": ([m("boiler_plate")] * 3, "minecraft:bucket", 1),
        # NOTE: 1.21.9 renamed the chain item to minecraft:iron_chain (Copper Age copper
        # chains took the plain name pattern) - verified against the client jar's recipes.
        "chain_from_rivet_bolts": ([m("rivet_bolt")] * 4, "minecraft:iron_chain", 1),
        "compass_from_magnetite_shard": ([m("magnetite_shard"), "minecraft:iron_ingot"], "minecraft:compass", 1),
        "glowstone_dust_from_crucible_dross": ([m("crucible_dross")], "minecraft:glowstone_dust", 2),
    }
    for name, (ingredients, result, count) in recipes.items():
        write_json(rdir / f"{name}.json", {
            "type": "minecraft:crafting_shapeless",
            "category": "misc",
            "ingredients": ingredients,
            "result": {"count": count, "id": result},
        })


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_textures()
    emit_item_assets()
    emit_loot_tables()
    emit_recipes()
    write_json(ASSETS / "lang" / "fragments" / "constructs.json", lang(0))
    write_json(ASSETS / "lang" / "fragments_de" / "constructs.json", lang(1))
    print(f"constructs_gen: assets for {len(MOBS)} mobs / {len(ITEM_IDS)} items generated "
          f"({len(LOOT)} loot tables, 12 recipes, lang EN+DE).")


if __name__ == "__main__":
    main()
