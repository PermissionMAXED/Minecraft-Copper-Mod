#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "infernodim2" feature (Inferno dimension
expansion: 4 new biomes + portal-frame corner block).

Idempotent: running it any number of times produces byte-identical files. Emits into
src/main/resources:
  - the 16x16 infernium_portal_corner block texture (Pillow, seeded noise) - DEFAULT
  - behind --write-json (repo convention; the JSON in src/main/resources is authoritative
    once emitted):
      blockstate/model/item def + loot table + stonecutting recipe for
      infernium_portal_corner (assets/ + data/copper_inferno/...)
      lang fragments (assets/copper_inferno/lang/fragments{,_de}/infernodim2.json)
      4 new biomes (data/copper_inferno/worldgen/biome/{verdigris_jungle,molten_delta,
      soot_dunes,crystal_hollows}.json)
      placed_feature/smolder_crystal_extra.json (crystal_hollows crystal density)
      dimension/inferno.json rewritten with a minecraft:multi_noise biome source
      (7 biomes, non-overlapping climate points)
      a biome_is patch of worldgen/noise_settings/inferno.json (new biomes appended to
      the existing surface-rule branches; crystal_hollows gets a dedicated floor branch
      placing the existing infernogeology geyserite block)
      devtools/tagfrag/infernodim2.json (mineable/pickaxe for the corner block)

Like infernodim_gen.py, NO dimension/biome JSON is invented:
  - Biome files are the real vanilla 1.21.9 biome JSONs (warped_forest, basalt_deltas,
    soul_sand_valley, nether_wastes) extracted straight out of fabric-loom's
    minecraft-client.jar with zipfile, with only the documented substitutions applied
    (effect colors, ambient particle, curated feature lists reusing the existing
    copper_inferno placed features, crystal_hollows spawner curation).
  - The multi_noise biome_source entries copy the vanilla nether preset's climate
    points. The nether preset is CODE in 1.21.9 (the jar's
    worldgen/multi_noise_biome_source_parameter_list/nether.json is just
    {"preset":"minecraft:nether"}), so the five points were read out of the yarn-mapped
    MultiNoiseBiomeSourceParameterList$Preset$1 bytecode with javap -c
    (createNoiseHypercube(temperature, humidity, continentalness, erosion, depth,
    weirdness, offset)):
      nether_wastes    ( 0.0,  0.0, 0, 0, 0, 0, 0.0  )
      soul_sand_valley ( 0.0, -0.5, 0, 0, 0, 0, 0.0  )
      crimson_forest   ( 0.4,  0.0, 0, 0, 0, 0, 0.0  )
      warped_forest    ( 0.0,  0.5, 0, 0, 0, 0, 0.375)
      basalt_deltas    (-0.5,  0.0, 0, 0, 0, 0, 0.175)
    The 7 copper_inferno biomes get 7 DISTINCT (temperature, humidity) points on the
    same lattice, so no two biomes overlap. JSON keys ("biomes", "biome", "parameters",
    "temperature".."offset") verified against the MultiNoiseBiomeSource /
    MultiNoiseUtil$NoiseHypercube codecs with javap.
  - Feature-order rule: every biome's per-step placed-feature list stays a subsequence
    of one master order (FeatureSorter cycle-free rule; same master order as
    infernodim_gen.py, with smolder_crystal_extra inserted after smolder_crystal_ore).
"""

import json
import os
import sys
import zipfile
from pathlib import Path
from random import Random

from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> texture PNG only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
NS = "copper_inferno"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
TAGFRAG = ROOT / "devtools" / "tagfrag"
CLIENT_JAR = Path(os.path.expanduser("~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"))

# ---------------------------------------------------------------------------
# Palette (house palette, matching infernodim_gen.py)
# ---------------------------------------------------------------------------
EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_HOT = (0xFF, 0xC2, 0x6B)
COPPER = (0xE0, 0x73, 0x4D)
COPPER_DARK = (0xC1, 0x5A, 0x3B)
CINDER_DARK = (0x2E, 0x24, 0x22)
CINDER = (0x3B, 0x2E, 0x2B)
CINDER_MORTAR = (0x1E, 0x15, 0x13)

CORNER = "infernium_portal_corner"

LANG_EN = {
    f"block.{NS}.{CORNER}": "Infernium Portal Corner",
    f"biome.{NS}.verdigris_jungle": "Verdigris Jungle",
    f"biome.{NS}.molten_delta": "Molten Delta",
    f"biome.{NS}.soot_dunes": "Soot Dunes",
    f"biome.{NS}.crystal_hollows": "Crystal Hollows",
}

LANG_DE = {
    f"block.{NS}.{CORNER}": "Infernium-Portalecke",
    f"biome.{NS}.verdigris_jungle": "Grünspandschungel",
    f"biome.{NS}.molten_delta": "Schmelzdelta",
    f"biome.{NS}.soot_dunes": "Rußdünen",
    f"biome.{NS}.crystal_hollows": "Kristallhöhlen",
}


def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False) + "\n",
                    encoding="utf-8")


def block_ref(name: str) -> str:
    return f"{NS}:block/{name}"


def jar_json(entry: str):
    """Load a vanilla JSON file straight out of the 1.21.9 minecraft-client.jar."""
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        return json.loads(jar.read(entry).decode("utf-8"))


# ---------------------------------------------------------------------------
# infernium_portal_corner: blockstate / model / item def / loot / recipe / tagfrag
# ---------------------------------------------------------------------------

def emit_corner_block() -> None:
    write_json(ASSETS / "blockstates" / f"{CORNER}.json",
               {"variants": {"": {"model": block_ref(CORNER)}}})
    write_json(ASSETS / "models" / "block" / f"{CORNER}.json",
               {"parent": "minecraft:block/cube_all", "textures": {"all": block_ref(CORNER)}})
    write_json(ASSETS / "items" / f"{CORNER}.json",
               {"model": {"type": "minecraft:model", "model": block_ref(CORNER)}})
    write_json(DATA / "loot_table" / "blocks" / f"{CORNER}.json", {
        "type": "minecraft:block",
        "pools": [{
            "bonus_rolls": 0.0,
            "conditions": [{"condition": "minecraft:survives_explosion"}],
            "entries": [{"type": "minecraft:item", "name": f"{NS}:{CORNER}"}],
            "rolls": 1.0,
        }],
        "random_sequence": f"{NS}:blocks/{CORNER}",
    })
    # Stonecutting format copied from vanilla stone_brick_stairs_from_stone_bricks_stonecutting
    # (same pattern as recipe/infernodim/cobbled_cinderstone_from_cinderstone_stonecutting).
    write_json(DATA / "recipe" / "infernodim2" / f"{CORNER}_from_frame_stonecutting.json", {
        "type": "minecraft:stonecutting",
        "ingredient": f"{NS}:infernium_portal_frame",
        "result": {"count": 1, "id": f"{NS}:{CORNER}"},
    })
    # The corner requiresTool like the frame -> mineable/pickaxe via devtools/merge_tags.py.
    write_json(TAGFRAG / "infernodim2.json", {
        "block/mineable/pickaxe": [f"{NS}:{CORNER}"],
    })


# ---------------------------------------------------------------------------
# Biomes (vanilla nether biome JSONs + documented substitutions)
# ---------------------------------------------------------------------------

ORE_PLACED_FEATURES = [
    f"{NS}:infernium_ore",
    f"{NS}:smolder_crystal_ore",
    f"{NS}:molten_slag",
]

# (biome id, vanilla source, effect overrides). foliage/grass colors are the optional
# vanilla BiomeEffects fields (schema cf. vanilla badlands.json).
BIOMES = [
    ("verdigris_jungle", "warped_forest", {
        "fog_color": 0x0E2A1F, "sky_color": 0x6FB08E,
        "water_color": 0x3FA080, "water_fog_color": 0x0B2E20,
        "foliage_color": 0x4E9E7A, "grass_color": 0x57A07B,
        "particle": {"options": {"type": "minecraft:warped_spore"}, "probability": 0.01428},
    }),
    ("molten_delta", "basalt_deltas", {
        "fog_color": 0x521605, "sky_color": 0xFF8A3C,
        "water_color": 0xC96C33, "water_fog_color": 0x2E1207,
        "foliage_color": 0xE25822, "grass_color": 0xC15A3B,
        # basalt_deltas' white_ash already belongs to slag_sea; molten_delta gets sparse
        # lava embers instead (minecraft:lava is a vanilla SimpleParticleType).
        "particle": {"options": {"type": "minecraft:lava"}, "probability": 0.0075},
    }),
    ("soot_dunes", "soul_sand_valley", {
        "fog_color": 0x241F1D, "sky_color": 0x8A8078,
        "water_color": 0x5D5A55, "water_fog_color": 0x15120F,
        "foliage_color": 0x968F88, "grass_color": 0xB1ABA4,
        "particle": {"options": {"type": "minecraft:ash"}, "probability": 0.00625},
    }),
    ("crystal_hollows", "nether_wastes", {
        "fog_color": 0x1E3D3A, "sky_color": 0xA5D8C0,
        "water_color": 0x63C7B4, "water_fog_color": 0x0F2C29,
        "foliage_color": 0x9ADBC8, "grass_color": 0x7FC7B0,
        # nether_wastes has no ambient particle; the hollows get drifting motes.
        "particle": {"options": {"type": "minecraft:end_rod"}, "probability": 0.008},
    }),
]

# Curated feature lists reusing the EXISTING copper_inferno configured/placed features
# (all of them target cinderstone-family terrain, see infernodim_gen.py). Per step index
# every biome's list is a subsequence of the master order shared with the 3 existing
# biomes, keeping the placed-feature ordering cycle-free (FeatureSorter rule).
# Step 7 = UNDERGROUND_DECORATION, step 9 = VEGETAL_DECORATION.
BIOME_FEATURES = {
    "verdigris_jungle": {
        7: [f"{NS}:spring_open", f"{NS}:ceiling_glow_spores", f"{NS}:wall_fungal_lights",
            f"{NS}:spring_closed", *ORE_PLACED_FEATURES],
        9: [f"{NS}:spring_lava", f"{NS}:patch_ember_flora", f"{NS}:patch_ember_moss"],
    },
    "molten_delta": {
        7: [f"{NS}:slagstone_blobs", f"{NS}:cobbled_cinderstone_blobs", f"{NS}:spring_delta",
            f"{NS}:patch_fire", f"{NS}:ceiling_glow_spores", f"{NS}:wall_fungal_lights",
            f"{NS}:spring_closed_double", *ORE_PLACED_FEATURES],
        9: [f"{NS}:spring_lava"],
    },
    "soot_dunes": {
        7: [f"{NS}:spring_open", f"{NS}:ceiling_glow_spores", f"{NS}:wall_fungal_lights",
            f"{NS}:disk_scorched_sand", f"{NS}:spring_closed", *ORE_PLACED_FEATURES],
        9: [f"{NS}:patch_ashen_vegetation"],
    },
    "crystal_hollows": {
        7: [f"{NS}:ceiling_glow_spores", f"{NS}:wall_fungal_lights", f"{NS}:spring_closed",
            f"{NS}:infernium_ore", f"{NS}:smolder_crystal_ore", f"{NS}:smolder_crystal_extra",
            f"{NS}:molten_slag"],
        9: [f"{NS}:patch_ashen_grass"],
    },
}

# crystal_hollows spawner curation (documented): the vanilla nether_wastes monster list
# (ghast/zombified_piglin/magma_cube/enderman/piglin) is reduced to endermen only —
# weight raised 1 -> 6, pack size 4 -> 1..2 — for the quiet crystal-cavern theme.
# The strider creature entry is kept as-is. Other biomes keep their vanilla spawners.
CRYSTAL_HOLLOWS_MONSTERS = [
    {"type": "minecraft:enderman", "maxCount": 2, "minCount": 1, "weight": 6},
]


def emit_biomes() -> None:
    for biome_id, source, effects in BIOMES:
        biome = jar_json(f"data/minecraft/worldgen/biome/{source}.json")
        biome["effects"].update(effects)
        if biome_id == "crystal_hollows":
            biome["spawners"]["monster"] = CRYSTAL_HOLLOWS_MONSTERS
        features = [[] for _ in range(10)]
        for step, placed in BIOME_FEATURES[biome_id].items():
            features[step] = list(placed)
        biome["features"] = features
        write_json(DATA / "worldgen" / "biome" / f"{biome_id}.json", biome)


def emit_crystal_extra_placed() -> None:
    """Extra smolder-crystal density in crystal_hollows: reuses the EXISTING configured
    feature copper_inferno:smolder_crystal_ore with a denser placement (schema identical
    to the existing placed_feature/smolder_crystal_ore.json, count 6 -> 12, band 10..60
    -> 10..90)."""
    write_json(DATA / "worldgen" / "placed_feature" / "smolder_crystal_extra.json", {
        "feature": f"{NS}:smolder_crystal_ore",
        "placement": [
            {"type": "minecraft:count", "count": 12},
            {"type": "minecraft:in_square"},
            {"type": "minecraft:height_range",
             "height": {"type": "minecraft:uniform",
                        "min_inclusive": {"absolute": 10},
                        "max_inclusive": {"absolute": 90}}},
            {"type": "minecraft:biome"},
        ],
    })


# ---------------------------------------------------------------------------
# Dimension: checkerboard -> multi_noise (nether-preset climate lattice, 7 biomes)
# ---------------------------------------------------------------------------

# (biome id, temperature, humidity, offset) — continentalness/erosion/depth/weirdness
# are 0 for every vanilla nether point and stay 0 here. The 3 existing biomes keep the
# points of the vanilla biomes they were derived from; the 4 new biomes take the two
# remaining vanilla points plus two new lattice points, so all 7 (t, h) pairs differ.
CLIMATE_POINTS = [
    ("cinder_wastes", 0.0, 0.0, 0.0),        # nether_wastes point
    ("ember_grove", 0.4, 0.0, 0.0),          # crimson_forest point
    ("slag_sea", -0.5, 0.0, 0.175),          # basalt_deltas point
    ("verdigris_jungle", 0.0, 0.5, 0.375),   # warped_forest point
    ("soot_dunes", 0.0, -0.5, 0.0),          # soul_sand_valley point
    ("molten_delta", 0.4, -0.5, 0.175),      # new: crimson t x soul h, deltas offset
    ("crystal_hollows", -0.5, 0.5, 0.375),   # new: deltas t x warped h, warped offset
]


def emit_dimension() -> None:
    write_json(DATA / "dimension" / "inferno.json", {
        "type": f"{NS}:inferno",
        "generator": {
            "type": "minecraft:noise",
            "settings": f"{NS}:inferno",
            "biome_source": {
                "type": "minecraft:multi_noise",
                "biomes": [
                    {
                        "biome": f"{NS}:{biome_id}",
                        "parameters": {
                            "temperature": t,
                            "humidity": h,
                            "continentalness": 0.0,
                            "erosion": 0.0,
                            "depth": 0.0,
                            "weirdness": 0.0,
                            "offset": offset,
                        },
                    }
                    for biome_id, t, h, offset in CLIMATE_POINTS
                ],
            },
        },
    })


# New biomes joining the EXISTING noise_settings surface-rule branches (they share the
# vanilla-derived surfaces of their sibling biome). Keyed by the anchor biome already
# present in the branch's biome_is list. crystal_hollows gets its OWN dedicated branch
# instead (crystal_hollows_surface_branch below).
SURFACE_BIOME_ADDITIONS = {
    f"{NS}:cinder_wastes": [f"{NS}:soot_dunes"],       # ash/ember_soil + gravel branches
    f"{NS}:ember_grove": [f"{NS}:verdigris_jungle"],   # ember moss/wart floor branch
    f"{NS}:slag_sea": [f"{NS}:molten_delta"],          # slagstone/cobbled delta branch
}

# crystal_hollows floor block: geyserite is an EXISTING block registered by the
# infernogeology feature (blockstate presence on disk is asserted before patching;
# no new block is registered here). Pale geyserite floors fit the crystal-cavern theme.
CRYSTAL_HOLLOWS_FLOOR = f"{NS}:geyserite"


def crystal_hollows_surface_branch() -> dict:
    """The dedicated crystal_hollows surface branch: JSON shape copied from the existing
    per-biome branches in noise_settings/inferno.json (biome_is condition -> stone_depth
    floor condition -> block), placing CRYSTAL_HOLLOWS_FLOOR as the biome's floor."""
    return {
        "if_true": {
            "biome_is": [f"{NS}:crystal_hollows"],
            "type": "minecraft:biome",
        },
        "then_run": {
            "if_true": {
                "add_surface_depth": True,
                "offset": 0,
                "secondary_depth_range": 0,
                "surface_type": "floor",
                "type": "minecraft:stone_depth",
            },
            "then_run": {
                "result_state": {"Name": CRYSTAL_HOLLOWS_FLOOR},
                "type": "minecraft:block",
            },
            "type": "minecraft:condition",
        },
        "type": "minecraft:condition",
    }


def patch_noise_settings() -> None:
    """Append the new biome ids to every existing biome_is list that contains the anchor
    biome, and insert the dedicated crystal_hollows floor branch once (both idempotent;
    running twice produces byte-identical output — the branch is only inserted if no
    top-level branch already matches crystal_hollows)."""
    path = DATA / "worldgen" / "noise_settings" / "inferno.json"
    noise = json.loads(path.read_text(encoding="utf-8"))

    def walk(node) -> None:
        if isinstance(node, dict):
            biome_is = node.get("biome_is")
            if isinstance(biome_is, list):
                for anchor, additions in SURFACE_BIOME_ADDITIONS.items():
                    if anchor in biome_is:
                        for extra in additions:
                            if extra not in biome_is:
                                biome_is.append(extra)
            for value in node.values():
                walk(value)
        elif isinstance(node, list):
            for value in node:
                walk(value)

    walk(noise["surface_rule"])

    def top_level_biome_is(entry) -> list:
        if isinstance(entry, dict) and isinstance(entry.get("if_true"), dict):
            biome_is = entry["if_true"].get("biome_is")
            if isinstance(biome_is, list):
                return biome_is
        return []

    # Insert the crystal_hollows branch right after the first cinder_wastes biome branch
    # (the ceiling/floor one), alongside its sibling per-biome branches and BEFORE the
    # un-gated lava-hole/moss floor rules — exactly where the other biome branches sit.
    sequence = noise["surface_rule"]["sequence"]
    if not any(f"{NS}:crystal_hollows" in top_level_biome_is(entry) for entry in sequence):
        floor_block = CRYSTAL_HOLLOWS_FLOOR.split(":", 1)[1]
        assert (ASSETS / "blockstates" / f"{floor_block}.json").exists(), \
            f"crystal_hollows floor block {CRYSTAL_HOLLOWS_FLOOR} has no blockstate on disk"
        anchor = next(i for i, entry in enumerate(sequence)
                      if f"{NS}:cinder_wastes" in top_level_biome_is(entry))
        sequence.insert(anchor + 1, crystal_hollows_surface_branch())

    write_json(path, noise)


# ---------------------------------------------------------------------------
# Texture (16x16, deterministic)
# ---------------------------------------------------------------------------

def new_canvas(rng: Random, shades) -> Image.Image:
    """Clustered 2x2 mottling plus sparse single-pixel accents (house style)."""
    img = Image.new("RGB", (16, 16))
    cells = {(cx, cy): rng.choice(shades) for cy in range(8) for cx in range(8)}
    for y in range(16):
        for x in range(16):
            img.putpixel((x, y), cells[(x // 2, y // 2)])
    for y in range(16):
        for x in range(16):
            if rng.random() < 0.06:
                img.putpixel((x, y), rng.choice(shades))
    return img


def tex_corner(rng: Random) -> Image.Image:
    """Chiseled dark block with thick copper L-brackets hugging all four corners and a
    glowing ember stud in each bracket's elbow (block emits light 7)."""
    img = new_canvas(rng, [CINDER_DARK, CINDER])
    for i in range(16):
        for x, y in ((i, 0), (i, 15), (0, i), (15, i)):
            img.putpixel((x, y), CINDER_MORTAR)
    # Copper L-brackets: 6px arms, 2px thick, one per corner.
    for cx, cy, dx, dy in ((1, 1, 1, 1), (14, 1, -1, 1), (1, 14, 1, -1), (14, 14, -1, -1)):
        for i in range(6):
            for t in range(2):
                img.putpixel((cx + i * dx, cy + t * dy), rng.choice([COPPER, COPPER_DARK]))
                img.putpixel((cx + t * dx, cy + i * dy), rng.choice([COPPER, COPPER_DARK]))
        img.putpixel((cx + dx, cy + dy), EMBER_BRIGHT)
        img.putpixel((cx, cy), EMBER_HOT)
    for x, y in [(7, 7), (8, 7), (7, 8), (8, 8)]:
        img.putpixel((x, y), EMBER)
    img.putpixel((7, 7), EMBER_HOT)
    return img


def emit_texture() -> None:
    block_dir = ASSETS / "textures" / "block"
    block_dir.mkdir(parents=True, exist_ok=True)
    # Per-texture fixed seed keeps output byte-identical across runs.
    tex_corner(Random(f"{NS}:{CORNER}")).save(block_dir / f"{CORNER}.png")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_corner_block()
    emit_biomes()
    emit_crystal_extra_placed()
    emit_dimension()
    patch_noise_settings()
    write_json(ASSETS / "lang" / "fragments" / "infernodim2.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "infernodim2.json", LANG_DE)
    emit_texture()
    mode = "texture + JSON" if WRITE_JSON else "texture only (pass --write-json for JSON)"
    print(f"infernodim2_gen: assets generated ({mode}).")


if __name__ == "__main__":
    main()
