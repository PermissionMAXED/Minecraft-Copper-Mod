#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO "infernogardens" feature (Inferno gardens).

25 NEW worldgen features (flora patches, giant fungi, crystal clusters, ceiling/wall
growth, vegetation mixes) for the six garden Inferno biomes (GARDEN_BIOMES; the quiet
crystal_hollows only receives the CRYSTAL_FEATURES trio) plus the 20 NEW plant/deco
blocks they place. Idempotent: re-runs produce byte-identical files (all texture noise
is seeded per texture name via genlib.rng_for).

Emits (JSON gated behind --write-json, following the repo convention that the JSON in
src/main/resources is authoritative once committed; PNGs, Java sources and the hook file
are always written):
  - 25 configured_feature + 25 placed_feature JSONs
    (data/copper_inferno/worldgen/{configured_feature,placed_feature}/) — the biome JSONs
    under worldgen/biome/ are NOT touched: wiring happens in code via Fabric's
    BiomeModifications.addFeature (signature verified via javap, see
    InfernoGardensFeature).
  - blockstates / block models / item model-definitions / item models for the 20 blocks
    (assets/copper_inferno/...), loot tables (data/copper_inferno/loot_table/blocks/),
    EN+DE lang fragments (assets/copper_inferno/lang/fragments{,_de}/infernogardens.json)
  - 16x16 PNG textures (Pillow, deterministic)
  - devtools/tagfrag/infernogardens.json (mineable tag fragments)
  - src/main/java/.../feature/infernogardens/{InfernoGardensFeature,InfernoGardensHandbook}.java
    (genlib.java_feature_class / java_handbook_class; literal ids only) and
    src/client/java/.../feature/infernogardens/client/InfernoGardensFeatureClient.java
    (CUTOUT render layers, same pattern as InfernoFloraFeatureClient)
  - devtools/hooks/infernogardens.txt

The worldgen JSON is NOT invented: every configured/placed feature is derived from the
real vanilla 1.21.9 schemas extracted straight out of fabric-loom's minecraft-client.jar
with zipfile (configured: patch_fire for every random_patch clone, crimson_forest_vegetation
for the weighted_state_provider shape, crimson_fungus for huge_fungus, glow_lichen for
multiface_growth, basalt_blobs for netherrack_replace_blobs; placed:
crimson_forest_vegetation for count_on_every_layer, brown_mushroom_nether for the
rarity_filter chain, glowstone/basalt_blobs for the full-height count chain, ore_magma
for the absolute height band, crimson_fungi for the fungus placement, glow_lichen for the
lichen placement), with only the documented substitutions applied (block ids/lists,
counts, radii; the glow_lichen placement drops its overworld-only
surface_relative_threshold_filter and clamps count/height to the ceilinged 128-high
dimension). Blockstate/model/item/loot JSON bodies are extracted from the same jar and
re-targeted by id replacement (crimson_fungus templates for the 15 cross plants,
shroomlight templates for the 4 cubes, glow_lichen templates for the multiface lichen),
exactly like devtools/gen/infernoflora_gen.py.
"""

import json
import os
import sys
import zipfile
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
import genlib
from genlib import NS, rng_for
from PIL import Image

WRITE_JSON = "--write-json" in sys.argv  # default False -> PNGs + Java + hook file only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
DATA = RES / "data" / NS
CLIENT_JAR = Path(os.path.expanduser("~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"))

FEATURE_DIR = ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "infernogardens"
CLIENT_DIR = ROOT / "src" / "client" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "infernogardens" / "client"

CF = "data/minecraft/worldgen/configured_feature"
PF = "data/minecraft/worldgen/placed_feature"

_jar = None


def jar_read(entry: str) -> str:
    global _jar
    if _jar is None:
        _jar = zipfile.ZipFile(CLIENT_JAR)
    return _jar.read(entry).decode("utf-8")


def jar_json(entry: str, repl: dict | None = None):
    """Load a vanilla JSON file straight out of the 1.21.9 minecraft-client.jar,
    optionally re-targeting full id references (longest key first)."""
    text = jar_read(entry)
    if repl:
        for old in sorted(repl, key=len, reverse=True):
            text = text.replace(old, repl[old])
    return json.loads(text)


def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    genlib.write_json(path, obj)


# ---------------------------------------------------------------------------
# Block roster (20 NEW blocks; collision-checked against assets/copper_inferno/blockstates)
# ---------------------------------------------------------------------------

# 15 cross-model plants (plain no-collision Blocks, CUTOUT client-side, like infernoflora).
CROSS_PLANTS = [
    "ember_lily", "cinder_fern", "sear_sprigs", "molten_bud", "ash_bramble",
    "glow_tendrils", "soot_puff", "slag_thistle", "pyre_reed", "copper_rose",
    "inferno_orchid", "charred_shrub", "gilded_clover", "hanging_ember_roots", "ash_veil",
]
# 4 full cubes: two giant-fungus caps + two crystal blocks.
CUBES = ["embercap_block", "gloomcap_block", "cinder_crystal_block", "verdigris_crystal_block"]
# 1 multiface growth block (GlowLichenBlock, ctor verified via javap).
LICHEN = "verdigris_lichen"

ALL_IDS = CROSS_PLANTS + CUBES + [LICHEN]

DE_NAMES = {
    "ember_lily": "Glutlilie",
    "cinder_fern": "Schlackenfarn",
    "sear_sprigs": "Sengzweiglein",
    "molten_bud": "Schmelzknospe",
    "ash_bramble": "Aschendornbusch",
    "glow_tendrils": "Leuchtranken",
    "soot_puff": "Ru\u00dfbovist",
    "slag_thistle": "Schlackendistel",
    "pyre_reed": "Feuerschilf",
    "copper_rose": "Kupferrose",
    "inferno_orchid": "Inferno-Orchidee",
    "charred_shrub": "Verkohlter Strauch",
    "gilded_clover": "Vergoldeter Klee",
    "hanging_ember_roots": "H\u00e4ngende Glutwurzeln",
    "ash_veil": "Aschenschleier",
    "embercap_block": "Glutkappenblock",
    "gloomcap_block": "D\u00fcsterkappenblock",
    "cinder_crystal_block": "Schlackenkristallblock",
    "verdigris_crystal_block": "Gr\u00fcnspankristallblock",
    "verdigris_lichen": "Gr\u00fcnspanflechte",
}


def display_name(block_id: str) -> str:
    return " ".join(w.capitalize() for w in block_id.split("_"))


# ---------------------------------------------------------------------------
# Ground / ceiling / wall block lists (mirroring infernodim_gen's surface analysis:
# cinder_wastes floors = cinderstone/ember_soil/ash_block, ember_grove floors add
# ember_moss_block/ember_wart_block, slag_sea floors = slagstone/cobbled_cinderstone;
# all features are added to ALL THREE biomes with one selector, so these predicates are
# the only per-biome flavor filter — patches keyed to grove/slag floors are cheap no-ops
# elsewhere, exactly like the existing infernodim vegetation).
# ---------------------------------------------------------------------------

GROUND_WASTES = [f"{NS}:cinderstone", f"{NS}:ember_soil", f"{NS}:ash_block"]
GROUND_GROVE = GROUND_WASTES + [f"{NS}:ember_moss_block", f"{NS}:ember_wart_block"]
GROUND_SLAG = [f"{NS}:cinderstone", f"{NS}:ash_block", f"{NS}:slagstone",
               f"{NS}:cobbled_cinderstone"]
GROUND_MOSS = [f"{NS}:ember_moss_block", f"{NS}:ember_soil"]
GROUND_SHORE = [f"{NS}:scorched_sand", f"{NS}:cinder_gravel", f"{NS}:ash_block"]
CEILING_BLOCKS = [f"{NS}:cinderstone", f"{NS}:slagstone", f"{NS}:ash_block",
                  f"{NS}:ember_soil"]
LICHEN_HOSTS = [f"{NS}:cinderstone", f"{NS}:slagstone", f"{NS}:cobbled_cinderstone"]

# Blocks the giant fungi may grow through (substituted into the vanilla crimson_fungus
# replaceable_blocks list): the six infernoflora plants plus this feature's cross plants.
FUNGUS_REPLACEABLE = [f"{NS}:{p}" for p in
                      ["ember_fungus", "ash_sprouts", "cinder_roots", "smolder_bloom",
                       "ashen_grass", "spore_cluster"] + CROSS_PLANTS]


# ---------------------------------------------------------------------------
# Worldgen schema derivation (every template extracted from the vanilla jar)
# ---------------------------------------------------------------------------

def simple_state(name: str):
    """simple_state_provider schema from vanilla patch_soul_fire's to_place."""
    return {"type": "minecraft:simple_state_provider", "state": {"Name": name}}


def weighted_states(entries):
    """weighted_state_provider: shape extracted from vanilla crimson_forest_vegetation
    (entries list rebuilt with our block ids/weights)."""
    template = jar_json(f"{CF}/crimson_forest_vegetation.json")
    provider = template["config"]["state_provider"]
    provider["entries"] = [{"data": {"Name": name}, "weight": weight}
                           for name, weight in entries]
    return provider


def on_ground(ground_blocks):
    """air at pos + ground block below; all_of/matching_blocks schema from vanilla
    patch_fire's predicate (block list substituted)."""
    pred = jar_json(f"{CF}/patch_fire.json")["config"]["feature"]["placement"][0]["predicate"]
    pred["predicates"][1]["blocks"] = ground_blocks
    return pred


def on_ceiling(ceiling_blocks):
    """air at pos + ceiling block above: the patch_fire predicate with the offset
    flipped to [0, 1, 0] (same documented substitution as infernodim's
    ceiling_glow_spores)."""
    pred = on_ground(ceiling_blocks)
    pred["predicates"][1]["offset"] = [0, 1, 0]
    return pred


def random_patch(to_place, predicate, tries=None, xz_spread=None, y_spread=None):
    """random_patch schema from vanilla patch_fire (tries 96, xz_spread 7, y_spread 3);
    only the inner to_place provider, the filter predicate and (for sparse clusters) the
    spread numbers are substituted."""
    feature = jar_json(f"{CF}/patch_fire.json")
    cfg = feature["config"]
    cfg["feature"]["feature"]["config"]["to_place"] = to_place
    cfg["feature"]["placement"][0]["predicate"] = predicate
    if tries is not None:
        cfg["tries"] = tries
    if xz_spread is not None:
        cfg["xz_spread"] = xz_spread
    if y_spread is not None:
        cfg["y_spread"] = y_spread
    return feature


def huge_fungus(stem: str, hat: str, decor: str, base_block: str):
    """huge_fungus schema from vanilla crimson_fungus; stem/hat/decor/valid_base states
    and the replaceable block list substituted (stem keeps the axis=y Properties dict —
    scorched_stem is a PillarBlock exactly like crimson_stem)."""
    feature = jar_json(f"{CF}/crimson_fungus.json")
    cfg = feature["config"]
    cfg["stem_state"] = {"Name": stem, "Properties": {"axis": "y"}}
    cfg["hat_state"] = {"Name": hat}
    cfg["decor_state"] = {"Name": decor}
    cfg["valid_base_block"] = {"Name": base_block}
    cfg["replaceable_blocks"]["blocks"] = FUNGUS_REPLACEABLE
    return feature


def multiface_growth(block: str, hosts):
    """multiface_growth schema from vanilla glow_lichen; block + can_be_placed_on
    substituted (floor/ceiling/wall flags, spread chance and search range kept)."""
    feature = jar_json(f"{CF}/glow_lichen.json")
    feature["config"]["block"] = block
    feature["config"]["can_be_placed_on"] = hosts
    return feature


def replace_blobs(state: str, target: str, radius_min: int, radius_max: int):
    """netherrack_replace_blobs schema from vanilla basalt_blobs; state (plain block, so
    the vanilla axis Properties dict is dropped), target and radius substituted."""
    feature = jar_json(f"{CF}/basalt_blobs.json")
    cfg = feature["config"]
    cfg["state"] = {"Name": state}
    cfg["target"] = {"Name": target}
    cfg["radius"]["min_inclusive"] = radius_min
    cfg["radius"]["max_inclusive"] = radius_max
    return feature


# --- placements (each chain extracted from the named vanilla placed feature) ---

def place_every_layer(count: int):
    """count_on_every_layer chain from vanilla placed crimson_forest_vegetation."""
    placement = jar_json(f"{PF}/crimson_forest_vegetation.json")["placement"]
    placement[0]["count"] = count
    return placement


def place_rarity(chance: int):
    """rarity_filter chain from vanilla placed brown_mushroom_nether
    (rarity + in_square + full height_range + biome)."""
    placement = jar_json(f"{PF}/brown_mushroom_nether.json")["placement"]
    placement[0]["chance"] = chance
    return placement


def place_count_full(count: int):
    """count chain from vanilla placed glowstone / basalt_blobs
    (count + in_square + full height_range + biome)."""
    placement = jar_json(f"{PF}/glowstone.json")["placement"]
    placement[0]["count"] = count
    return placement


def place_band(count: int, y_min: int, y_max: int):
    """count chain with an absolute height band from vanilla placed ore_magma
    (count + in_square + height_range uniform absolute + biome) — the same lava-sea-level
    band pattern infernodim uses for disk_scorched_sand."""
    placement = jar_json(f"{PF}/ore_magma.json")["placement"]
    placement[0]["count"] = count
    placement[2]["height"]["min_inclusive"] = {"absolute": y_min}
    placement[2]["height"]["max_inclusive"] = {"absolute": y_max}
    return placement


def place_fungi(count: int):
    """count_on_every_layer chain from vanilla placed crimson_fungi."""
    placement = jar_json(f"{PF}/crimson_fungi.json")["placement"]
    placement[0]["count"] = count
    return placement


def place_lichen(count_min: int, count_max: int):
    """chain from vanilla placed glow_lichen with the overworld-only
    surface_relative_threshold_filter dropped (this dimension has a ceiling, no
    OCEAN_FLOOR_WG heightmap) and count/height clamped to the 128-high dimension
    (full above_bottom..below_top range, exactly like the other infernodim clones)."""
    placement = jar_json(f"{PF}/glow_lichen.json")["placement"]
    placement = [mod for mod in placement
                 if mod["type"] != "minecraft:surface_relative_threshold_filter"]
    placement[0]["count"]["min_inclusive"] = count_min
    placement[0]["count"]["max_inclusive"] = count_max
    placement[1]["height"]["min_inclusive"] = {"above_bottom": 0}
    placement[1]["height"]["max_inclusive"] = {"below_top": 0}
    return placement


# ---------------------------------------------------------------------------
# The 25 features: name -> (configured feature JSON, placement chain, GenerationStep
# enum constant used by the Java wiring). Names collision-checked against
# data/copper_inferno/worldgen/{configured_feature,placed_feature}/.
# ---------------------------------------------------------------------------

VEGETAL = "VEGETAL_DECORATION"
UNDERGROUND = "UNDERGROUND_DECORATION"

# Biomes receiving ALL 25 garden features with one shared selector: the three original
# Inferno biomes plus the vegetated/open infernodim2 biomes. The canonical 7-biome list
# of the Inferno dimension lives in devtools/gen/infernodim2_gen.py
# (BIOMES/CLIMATE_POINTS); crystal_hollows is curated separately (CRYSTAL_FEATURES).
GARDEN_BIOMES = ["cinder_wastes", "ember_grove", "slag_sea",
                 "verdigris_jungle", "molten_delta", "soot_dunes"]

# crystal_hollows only receives the three crystal features (its sparse curated theme).
# CRITICAL: this list MUST stay in the master feature order (build_features() insertion
# order) so every biome's appended placed features form a subsequence of ONE identical
# master order and the FeatureSorter ordering stays cycle-free (verified in verify()).
CRYSTAL_HOLLOWS_BIOMES = ["crystal_hollows"]
CRYSTAL_FEATURES = ["cinder_crystal_cluster", "verdigris_crystal_cluster",
                    "buried_verdigris_crystal"]


def build_features() -> dict:
    features = {}

    def add(name, configured, placement, step):
        features[name] = (configured, placement, step)

    # --- 13 single-species flora patches ---
    add("patch_ember_lily",
        random_patch(simple_state(f"{NS}:ember_lily"), on_ground(GROUND_MOSS)),
        place_every_layer(3), VEGETAL)
    add("patch_cinder_fern",
        random_patch(simple_state(f"{NS}:cinder_fern"), on_ground(GROUND_WASTES)),
        place_every_layer(4), VEGETAL)
    add("patch_sear_sprigs",
        random_patch(simple_state(f"{NS}:sear_sprigs"),
                     on_ground([f"{NS}:cinderstone", f"{NS}:ash_block"])),
        place_every_layer(3), VEGETAL)
    add("patch_molten_bud",
        random_patch(simple_state(f"{NS}:molten_bud"),
                     on_ground([f"{NS}:ember_soil", f"{NS}:cinderstone"])),
        place_rarity(2), VEGETAL)
    add("patch_ash_bramble",
        random_patch(simple_state(f"{NS}:ash_bramble"),
                     on_ground([f"{NS}:ash_block", f"{NS}:cinder_gravel"])),
        place_every_layer(2), VEGETAL)
    add("patch_glow_tendrils",
        random_patch(simple_state(f"{NS}:glow_tendrils"),
                     on_ground([f"{NS}:ember_moss_block", f"{NS}:ember_wart_block"])),
        place_every_layer(2), VEGETAL)
    add("patch_soot_puff",
        random_patch(simple_state(f"{NS}:soot_puff"),
                     on_ground([f"{NS}:slagstone", f"{NS}:cobbled_cinderstone",
                                f"{NS}:ash_block"])),
        place_every_layer(2), VEGETAL)
    add("patch_slag_thistle",
        random_patch(simple_state(f"{NS}:slag_thistle"), on_ground(GROUND_SLAG)),
        place_rarity(2), VEGETAL)
    add("patch_pyre_reed",
        random_patch(simple_state(f"{NS}:pyre_reed"), on_ground(GROUND_SHORE)),
        place_band(4, 30, 36), VEGETAL)
    add("patch_copper_rose",
        random_patch(simple_state(f"{NS}:copper_rose"), on_ground(GROUND_GROVE)),
        place_rarity(3), VEGETAL)
    add("patch_inferno_orchid",
        random_patch(simple_state(f"{NS}:inferno_orchid"),
                     on_ground([f"{NS}:ember_moss_block"])),
        place_rarity(4), VEGETAL)
    add("patch_charred_shrub",
        random_patch(simple_state(f"{NS}:charred_shrub"), on_ground(GROUND_WASTES)),
        place_every_layer(2), VEGETAL)
    add("patch_gilded_clover",
        random_patch(simple_state(f"{NS}:gilded_clover"), on_ground(GROUND_MOSS)),
        place_rarity(3), VEGETAL)

    # --- 3 weighted garden mixes (one per biome's floor set) ---
    add("garden_wastes_mix",
        random_patch(weighted_states([(f"{NS}:sear_sprigs", 4), (f"{NS}:ash_bramble", 3),
                                      (f"{NS}:cinder_fern", 3), (f"{NS}:charred_shrub", 2)]),
                     on_ground(GROUND_WASTES)),
        place_every_layer(4), VEGETAL)
    add("garden_grove_mix",
        random_patch(weighted_states([(f"{NS}:ember_lily", 3), (f"{NS}:glow_tendrils", 3),
                                      (f"{NS}:molten_bud", 2), (f"{NS}:gilded_clover", 2),
                                      (f"{NS}:copper_rose", 1)]),
                     on_ground(GROUND_GROVE)),
        place_every_layer(5), VEGETAL)
    add("garden_slag_mix",
        random_patch(weighted_states([(f"{NS}:slag_thistle", 3), (f"{NS}:soot_puff", 3),
                                      (f"{NS}:cinder_fern", 2)]),
                     on_ground(GROUND_SLAG)),
        place_every_layer(3), VEGETAL)

    # --- 2 ceiling growths (hanging cross plants under the cavern roof) ---
    add("ceiling_hanging_ember_roots",
        random_patch(simple_state(f"{NS}:hanging_ember_roots"), on_ceiling(CEILING_BLOCKS)),
        place_count_full(8), VEGETAL)
    add("ceiling_ash_veils",
        random_patch(simple_state(f"{NS}:ash_veil"), on_ceiling(CEILING_BLOCKS)),
        place_count_full(6), VEGETAL)

    # --- 1 wall/ceiling multiface growth ---
    add("wall_verdigris_lichen",
        multiface_growth(f"{NS}:verdigris_lichen", LICHEN_HOSTS),
        place_lichen(20, 40), UNDERGROUND)

    # --- 3 crystal features ---
    add("cinder_crystal_cluster",
        random_patch(simple_state(f"{NS}:cinder_crystal_block"), on_ground(GROUND_WASTES),
                     tries=4, xz_spread=4, y_spread=3),
        place_rarity(3), VEGETAL)
    add("verdigris_crystal_cluster",
        random_patch(simple_state(f"{NS}:verdigris_crystal_block"), on_ground(GROUND_SLAG),
                     tries=4, xz_spread=4, y_spread=3),
        place_rarity(3), VEGETAL)
    add("buried_verdigris_crystal",
        replace_blobs(f"{NS}:verdigris_crystal_block", f"{NS}:cinderstone", 1, 3),
        place_count_full(6), UNDERGROUND)

    # --- 2 giant fungi (huge_fungus; valid_base_block gates them to grove floors) ---
    add("giant_embercap",
        huge_fungus(f"{NS}:scorched_stem", f"{NS}:embercap_block",
                    f"{NS}:glowing_spore_block", f"{NS}:ember_moss_block"),
        place_fungi(2), VEGETAL)
    add("giant_gloomcap",
        huge_fungus(f"{NS}:scorched_stem", f"{NS}:gloomcap_block",
                    f"{NS}:fungal_light", f"{NS}:ember_wart_block"),
        place_fungi(2), VEGETAL)

    # --- 1 moss-carpet shelf patch (places the existing infernoflora carpet) ---
    add("patch_ember_moss_carpet",
        random_patch(simple_state(f"{NS}:ember_moss_carpet"), on_ground(GROUND_MOSS)),
        place_every_layer(2), VEGETAL)

    return features


def emit_worldgen(features: dict) -> None:
    for name, (configured, placement, _step) in features.items():
        write_json(DATA / "worldgen" / "configured_feature" / f"{name}.json", configured)
        write_json(DATA / "worldgen" / "placed_feature" / f"{name}.json",
                   {"feature": f"{NS}:{name}", "placement": placement})


# ---------------------------------------------------------------------------
# Blockstates / models / item defs / loot (vanilla jar templates, id-retargeted —
# same extraction approach as infernoflora_gen.py)
# ---------------------------------------------------------------------------

def emit_block_assets() -> None:
    # 15 cross plants: crimson_fungus templates (block/cross + item/generated).
    for pid in CROSS_PLANTS:
        block_repl = {"minecraft:block/crimson_fungus": f"{NS}:block/{pid}"}
        write_json(ASSETS / "blockstates" / f"{pid}.json",
                   jar_json("assets/minecraft/blockstates/crimson_fungus.json", block_repl))
        write_json(ASSETS / "models" / "block" / f"{pid}.json",
                   jar_json("assets/minecraft/models/block/crimson_fungus.json", block_repl))
        write_json(ASSETS / "models" / "item" / f"{pid}.json",
                   jar_json("assets/minecraft/models/item/crimson_fungus.json", block_repl))
        write_json(ASSETS / "items" / f"{pid}.json",
                   jar_json("assets/minecraft/items/crimson_fungus.json",
                            {"minecraft:item/crimson_fungus": f"{NS}:item/{pid}"}))

    # 4 cubes: shroomlight templates (cube_all + block-model item def).
    for cid in CUBES:
        repl = {"minecraft:block/shroomlight": f"{NS}:block/{cid}"}
        write_json(ASSETS / "blockstates" / f"{cid}.json",
                   jar_json("assets/minecraft/blockstates/shroomlight.json", repl))
        write_json(ASSETS / "models" / "block" / f"{cid}.json",
                   jar_json("assets/minecraft/models/block/shroomlight.json", repl))
        write_json(ASSETS / "items" / f"{cid}.json",
                   jar_json("assets/minecraft/items/shroomlight.json", repl))

    # Multiface lichen: glow_lichen templates. The model file references its texture
    # WITHOUT a namespace prefix ("block/glow_lichen"), so both spellings are mapped
    # (longest-first replace in jar_json keeps them from double-firing).
    lichen_repl = {
        "minecraft:block/glow_lichen": f"{NS}:block/{LICHEN}",
        "block/glow_lichen": f"{NS}:block/{LICHEN}",
    }
    write_json(ASSETS / "blockstates" / f"{LICHEN}.json",
               jar_json("assets/minecraft/blockstates/glow_lichen.json", lichen_repl))
    write_json(ASSETS / "models" / "block" / f"{LICHEN}.json",
               jar_json("assets/minecraft/models/block/glow_lichen.json", lichen_repl))
    write_json(ASSETS / "models" / "item" / f"{LICHEN}.json",
               jar_json("assets/minecraft/models/item/glow_lichen.json", lichen_repl))
    write_json(ASSETS / "items" / f"{LICHEN}.json",
               jar_json("assets/minecraft/items/glow_lichen.json",
                        {"minecraft:item/glow_lichen": f"{NS}:item/{LICHEN}"}))


def emit_loot() -> None:
    # Plants + cubes drop themselves (vanilla crimson_fungus drop-self template).
    for bid in CROSS_PLANTS + CUBES:
        write_json(DATA / "loot_table" / "blocks" / f"{bid}.json",
                   jar_json("data/minecraft/loot_table/blocks/crimson_fungus.json", {
                       "minecraft:blocks/crimson_fungus": f"{NS}:blocks/{bid}",
                       "minecraft:crimson_fungus": f"{NS}:{bid}",
                   }))
    # Lichen: the exact vanilla glow_lichen shears table (per-face set_count functions
    # carry over 1:1 — the block has identical state properties).
    write_json(DATA / "loot_table" / "blocks" / f"{LICHEN}.json",
               jar_json("data/minecraft/loot_table/blocks/glow_lichen.json", {
                   "minecraft:blocks/glow_lichen": f"{NS}:blocks/{LICHEN}",
                   "minecraft:glow_lichen": f"{NS}:{LICHEN}",
               }))


# ---------------------------------------------------------------------------
# Textures (16x16, deterministic; seeded via genlib.rng_for)
# ---------------------------------------------------------------------------

EMBER = (0xE2, 0x58, 0x22)
EMBER_BRIGHT = (0xFF, 0x7A, 0x2F)
EMBER_CORE = (0xFF, 0xD8, 0x7A)
COPPER = (0xE0, 0x73, 0x4D)
COPPER_DARK = (0xC1, 0x5A, 0x3B)
TEAL_LIGHT = (0x63, 0xD1, 0xBC)
TEAL_MID = (0x2E, 0x9C, 0x8C)
TEAL_DARK = (0x1D, 0x6B, 0x60)
ASH_LIGHT = (0xC9, 0xC4, 0xBE)
ASH = (0xB1, 0xAB, 0xA4)
ASH_DARK = (0x8A, 0x84, 0x7D)
CHAR = (0x2E, 0x24, 0x22)
CHAR_LIGHT = (0x4A, 0x39, 0x34)


def clamp(v: int) -> int:
    return max(0, min(255, v))


def jitter(rnd, rgb, amount=3):
    d = rnd.randint(-amount, amount)
    return (clamp(rgb[0] + d), clamp(rgb[1] + d), clamp(rgb[2] + d))


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def put(px, x, y, rgb):
    if 0 <= x < 16 and 0 <= y < 16:
        px[x, y] = (rgb[0], rgb[1], rgb[2], 255)


def stem_up(px, rnd, x, y_bottom, y_top, color):
    for y in range(y_bottom, y_top - 1, -1):
        put(px, x, y, jitter(rnd, color))


def tex_ember_lily(name):
    """Orange lily: dark stem, two leaves, drooping ember bloom with a hot core."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    stem_up(px, rnd, 8, 15, 6, (0x5A, 0x4A, 0x3A))
    put(px, 7, 12, (0x6E, 0x5A, 0x42))
    put(px, 9, 11, (0x6E, 0x5A, 0x42))
    for y, (x0, x1) in {3: (6, 9), 4: (5, 10), 5: (6, 9), 6: (7, 8)}.items():
        for x in range(x0, x1 + 1):
            edge = x in (x0, x1) or y == 3
            put(px, x, y, jitter(rnd, EMBER if edge else EMBER_BRIGHT))
    put(px, 7, 4, EMBER_CORE)
    put(px, 8, 4, EMBER_CORE)
    return img


def tex_cinder_fern(name):
    """Gray-green fern: central stalk with paired arching fronds."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    stalk = (0x5C, 0x66, 0x58)
    frond = (0x78, 0x86, 0x72)
    tip = (0x97, 0xA4, 0x8F)
    stem_up(px, rnd, 8, 15, 3, stalk)
    for i, y in enumerate(range(13, 3, -2)):
        reach = max(1, 4 - i)
        for dx in range(1, reach + 1):
            c = tip if dx == reach else frond
            put(px, 8 - dx, y - (dx // 2), jitter(rnd, c))
            put(px, 8 + dx, y - (dx // 2), jitter(rnd, c))
    return img


def tex_sear_sprigs(name):
    """Short scorched sprigs with smoldering ember tips."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    twig = (0x4E, 0x3E, 0x33)
    for sx in (2, 5, 8, 11, 14):
        h = rnd.randint(3, 5)
        for i in range(h):
            put(px, sx, 15 - i, jitter(rnd, twig))
        put(px, sx, 15 - h, EMBER if rnd.random() < 0.7 else EMBER_BRIGHT)
    return img


def tex_molten_bud(name):
    """Two squat dark buds with glowing molten seams."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    for bx, by, r in ((5, 9, 3), (11, 11, 2)):
        for dy in range(r * 2):
            for dx in range(r * 2):
                x, y = bx - r + dx, by + dy
                if 0 <= by + dy <= 15 and (dx in (0, r * 2 - 1) or dy in (0, r * 2 - 1)):
                    put(px, x, y, jitter(rnd, CHAR))
                else:
                    put(px, x, y, jitter(rnd, CHAR_LIGHT))
        for i in range(r * 2):
            put(px, bx - r + i, by + r, jitter(rnd, EMBER))
        put(px, bx, by + r, EMBER_BRIGHT)
        for y in range(by + r * 2, 16):
            put(px, bx, y, jitter(rnd, (0x4E, 0x3E, 0x33)))
    return img


def tex_ash_bramble(name):
    """Tangle of pale gray thorn arcs."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    for sx, lean in ((2, 1), (6, -1), (9, 1), (13, -1)):
        x = sx
        h = rnd.randint(6, 9)
        for i in range(h):
            y = 15 - i
            if i % 3 == 2:
                x = min(15, max(0, x + lean))
            c = ASH_LIGHT if i >= h - 2 else (ASH_DARK if i == 0 else ASH)
            put(px, x, y, jitter(rnd, c))
            if i % 4 == 1:  # thorn stub
                put(px, x + lean, y, jitter(rnd, ASH_DARK))
    return img


def tex_glow_tendrils(name):
    """Wavy teal tendrils studded with bright glow nodes."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    for sx in (3, 7, 11, 14):
        x = sx
        h = rnd.randint(8, 12)
        for i in range(h):
            y = 15 - i
            if rnd.random() < 0.35:
                x = min(15, max(0, x + rnd.choice((-1, 1))))
            put(px, x, y, jitter(rnd, TEAL_MID if i % 3 else TEAL_DARK))
            if i % 4 == 3:
                put(px, x, y, TEAL_LIGHT)
        put(px, x, 15 - h, (0x9A, 0xF2, 0xDB, 255))
    return img


def tex_soot_puff(name):
    """Dark round puffballs dusted with soot speckles."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    for bx, by, r in ((4, 10, 3), (10, 8, 4), (13, 12, 2)):
        for dy in range(-r, r + 1):
            for dx in range(-r, r + 1):
                if dx * dx + dy * dy <= r * r:
                    edge = dx * dx + dy * dy >= (r - 1) * (r - 1)
                    put(px, bx + dx, by + dy, jitter(rnd, CHAR if edge else (0x3B, 0x33, 0x31)))
        put(px, bx - 1, by - 1, jitter(rnd, (0x57, 0x50, 0x4C)))
        for y in range(by + r, 16):
            put(px, bx, y, jitter(rnd, (0x4A, 0x40, 0x3A)))
    for _ in range(6):
        put(px, rnd.randrange(16), rnd.randrange(6, 16), (0x6A, 0x62, 0x5C))
    return img


def tex_slag_thistle(name):
    """Spiky gray thistle with a pale bristle crown."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    stem_up(px, rnd, 8, 15, 7, (0x54, 0x59, 0x50))
    for y, (x0, x1) in {5: (7, 8), 6: (6, 9), 7: (6, 9)}.items():
        for x in range(x0, x1 + 1):
            put(px, x, y, jitter(rnd, (0x7E, 0x84, 0x78)))
    for x, y in ((6, 4), (8, 3), (10, 4), (7, 4), (9, 4)):
        put(px, x, y, jitter(rnd, ASH_LIGHT))
    for y, dx in ((10, 2), (12, 3)):  # side spikes
        put(px, 8 - dx, y, jitter(rnd, (0x6A, 0x70, 0x66)))
        put(px, 8 + dx, y, jitter(rnd, (0x6A, 0x70, 0x66)))
    return img


def tex_pyre_reed(name):
    """Tall warm-tan reeds with darker node bands."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    reed = (0xB0, 0x8A, 0x52)
    node = (0x7E, 0x5E, 0x36)
    for sx in (3, 6, 9, 12):
        h = rnd.randint(10, 14)
        for i in range(h):
            y = 15 - i
            put(px, sx, y, jitter(rnd, node if i % 4 == 3 else reed))
        put(px, sx, 15 - h, jitter(rnd, EMBER_BRIGHT if rnd.random() < 0.4 else (0xC9, 0xA5, 0x6B)))
    return img


def tex_copper_rose(name):
    """Copper-petaled rose on a dark leafy stem."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    stem_up(px, rnd, 8, 15, 7, (0x4A, 0x52, 0x42))
    put(px, 7, 12, (0x5E, 0x68, 0x52))
    put(px, 9, 10, (0x5E, 0x68, 0x52))
    for y, (x0, x1) in {4: (6, 9), 5: (5, 10), 6: (6, 9)}.items():
        for x in range(x0, x1 + 1):
            edge = x in (x0, x1) or y == 4
            put(px, x, y, jitter(rnd, COPPER_DARK if edge else COPPER))
    put(px, 7, 5, jitter(rnd, (0xF0, 0x9A, 0x6A)))
    put(px, 8, 5, jitter(rnd, (0xF0, 0x9A, 0x6A)))
    return img


def tex_inferno_orchid(name):
    """Arcing stem carrying magenta-ember orchid blooms."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    stem = (0x4E, 0x5E, 0x54)
    x = 5
    for i in range(12):
        y = 15 - i
        if i in (4, 8):
            x += 1
        put(px, x, y, jitter(rnd, stem))
    for bx, by in ((x + 1, 3), (x - 2, 6), (x + 2, 8)):
        put(px, bx, by, jitter(rnd, (0xC2, 0x3E, 0x8E)))
        put(px, bx - 1, by, jitter(rnd, (0x8E, 0x2A, 0x66)))
        put(px, bx + 1, by, jitter(rnd, (0x8E, 0x2A, 0x66)))
        put(px, bx, by - 1, jitter(rnd, (0xE8, 0x7A, 0xB8)))
        put(px, bx, by + 1, EMBER_BRIGHT)
    return img


def tex_charred_shrub(name):
    """Dead branching shrub, charcoal black with rare ember flecks."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    def branch(x, y, dx, depth):
        while y > 3 and depth > 0:
            put(px, x, y, jitter(rnd, CHAR if rnd.random() < 0.6 else CHAR_LIGHT))
            y -= 1
            if rnd.random() < 0.4:
                x = min(15, max(0, x + dx))
            if rnd.random() < 0.25 and depth > 1:
                branch(x, y, -dx, depth - 1)
                depth -= 1
    branch(8, 15, 1, 3)
    branch(7, 15, -1, 2)
    for _ in range(3):
        put(px, rnd.randrange(3, 13), rnd.randrange(5, 13), EMBER)
    return img


def tex_gilded_clover(name):
    """Low golden-green clover heads on short stems."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    gold = (0xD8, 0xB4, 0x3A)
    leaf = (0x8C, 0x92, 0x4A)
    for cx, cy in ((3, 12), (8, 10), (12, 12), (6, 13)):
        put(px, cx, cy + 1, jitter(rnd, (0x5E, 0x68, 0x42)))
        put(px, cx, cy + 2, jitter(rnd, (0x5E, 0x68, 0x42)))
        put(px, cx - 1, cy, jitter(rnd, leaf))
        put(px, cx + 1, cy, jitter(rnd, leaf))
        put(px, cx, cy - 1, jitter(rnd, gold))
        put(px, cx, cy, jitter(rnd, gold))
    return img


def tex_hanging_ember_roots(name):
    """Dark-red root strands anchored at the TOP, ember-tipped at the bottom."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    dark = (0x8C, 0x2A, 0x1A)
    bright = (0xC2, 0x45, 0x20)
    for sx in (2, 5, 8, 11, 14):
        x = sx
        length = rnd.randint(8, 13)
        for i in range(length):
            y = i
            put(px, x, y, jitter(rnd, bright if i % 3 == 0 else dark))
            if rnd.random() < 0.25:
                x = min(15, max(0, x + rnd.choice((-1, 1))))
        put(px, x, length - 1, EMBER_BRIGHT)
    return img


def tex_ash_veil(name):
    """Pale ash curtain: dense gray strands hanging from the top edge."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    for sx in range(1, 15, 2):
        length = rnd.randint(9, 14)
        for i in range(length):
            c = ASH_LIGHT if i < 3 else (ASH if i < length - 2 else ASH_DARK)
            put(px, sx, i, jitter(rnd, c))
            if rnd.random() < 0.2:
                put(px, sx + 1, i, jitter(rnd, ASH_DARK))
    return img


def tex_embercap_block(name):
    """Embercap: clumpy orange wart nodules with glowing grout (cube)."""
    rnd = rng_for(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            px[x, y] = jitter(rnd, (0x6E, 0x28, 0x10))
    for cy in range(0, 16, 3):
        for cx in range(0, 16, 3):
            ox, oy = rnd.randrange(2), rnd.randrange(2)
            for dy in range(2):
                for dx in range(2):
                    x, y = (cx + ox + dx) % 16, (cy + oy + dy) % 16
                    c = EMBER_BRIGHT if (dx == 0 and dy == 0) else (EMBER if dx != dy else (0xB5, 0x4A, 0x1E))
                    px[x, y] = jitter(rnd, c, 4)
    for _ in range(4):
        px[rnd.randrange(16), rnd.randrange(16)] = EMBER_CORE
    return img


def tex_gloomcap_block(name):
    """Gloomcap: dusky teal wart nodules with pale spore dots (cube)."""
    rnd = rng_for(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            px[x, y] = jitter(rnd, (0x14, 0x38, 0x34))
    for cy in range(0, 16, 3):
        for cx in range(0, 16, 3):
            ox, oy = rnd.randrange(2), rnd.randrange(2)
            for dy in range(2):
                for dx in range(2):
                    x, y = (cx + ox + dx) % 16, (cy + oy + dy) % 16
                    c = TEAL_LIGHT if (dx == 0 and dy == 0) else (TEAL_MID if dx != dy else TEAL_DARK)
                    px[x, y] = jitter(rnd, c, 4)
    for _ in range(4):
        px[rnd.randrange(16), rnd.randrange(16)] = (0x9A, 0xF2, 0xDB)
    return img


def _crystal_cube(rnd, base, facet_dark, facet_mid, facet_light, spark):
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            px[x, y] = jitter(rnd, base)
    for _ in range(7):  # diagonal shard facets
        sx, sy = rnd.randrange(0, 12), rnd.randrange(0, 12)
        length = rnd.randint(3, 5)
        for i in range(length):
            x, y = sx + i, sy + i
            if x < 16 and y < 16:
                px[x, y] = jitter(rnd, facet_mid)
                if x + 1 < 16:
                    px[x + 1, y] = jitter(rnd, facet_dark)
                if y + 1 < 16:
                    px[x, y + 1] = jitter(rnd, facet_light)
        if sx + length < 16 and sy + length - 1 < 16:
            px[sx + length - 1, sy + length - 1] = spark
    return img


def tex_cinder_crystal_block(name):
    """Ember-orange crystal: glowing shard facets in a dark cinder matrix."""
    return _crystal_cube(rng_for(name), (0x33, 0x1E, 0x16),
                         COPPER_DARK, EMBER, EMBER_BRIGHT, EMBER_CORE)


def tex_verdigris_crystal_block(name):
    """Verdigris crystal: teal shard facets in a dark green matrix."""
    return _crystal_cube(rng_for(name), (0x12, 0x2E, 0x28),
                         TEAL_DARK, TEAL_MID, TEAL_LIGHT, (0x9A, 0xF2, 0xDB))


def tex_verdigris_lichen(name):
    """Multiface lichen overlay: patchy teal growth, transparent background."""
    rnd = rng_for(name)
    img = blank()
    px = img.load()
    for _ in range(9):  # irregular lichen patches
        cx, cy = rnd.randrange(2, 14), rnd.randrange(2, 14)
        r = rnd.randint(2, 3)
        for dy in range(-r, r + 1):
            for dx in range(-r, r + 1):
                if abs(dx) + abs(dy) <= r and rnd.random() < 0.85:
                    edge = abs(dx) + abs(dy) == r
                    put(px, cx + dx, cy + dy,
                        jitter(rnd, TEAL_DARK if edge else TEAL_MID, 4))
    for _ in range(8):
        put(px, rnd.randrange(16), rnd.randrange(16), TEAL_LIGHT)
    return img


TEXTURES = {
    "ember_lily": tex_ember_lily,
    "cinder_fern": tex_cinder_fern,
    "sear_sprigs": tex_sear_sprigs,
    "molten_bud": tex_molten_bud,
    "ash_bramble": tex_ash_bramble,
    "glow_tendrils": tex_glow_tendrils,
    "soot_puff": tex_soot_puff,
    "slag_thistle": tex_slag_thistle,
    "pyre_reed": tex_pyre_reed,
    "copper_rose": tex_copper_rose,
    "inferno_orchid": tex_inferno_orchid,
    "charred_shrub": tex_charred_shrub,
    "gilded_clover": tex_gilded_clover,
    "hanging_ember_roots": tex_hanging_ember_roots,
    "ash_veil": tex_ash_veil,
    "embercap_block": tex_embercap_block,
    "gloomcap_block": tex_gloomcap_block,
    "cinder_crystal_block": tex_cinder_crystal_block,
    "verdigris_crystal_block": tex_verdigris_crystal_block,
    "verdigris_lichen": tex_verdigris_lichen,
}


def emit_textures() -> None:
    block_dir = ASSETS / "textures" / "block"
    block_dir.mkdir(parents=True, exist_ok=True)
    for name, fn in TEXTURES.items():
        fn(name).save(block_dir / f"{name}.png")


# ---------------------------------------------------------------------------
# Java codegen (genlib.java_feature_class / java_handbook_class; literal ids only).
# BiomeModifications.addFeature / BiomeSelectors.includeByKey signatures verified via
# javap against the yarn-remapped fabric-biome-api-v1 jar:
#   addFeature(Predicate<BiomeSelectionContext>, GenerationStep.Feature,
#              RegistryKey<PlacedFeature>)
#   includeByKey(RegistryKey<Biome>...)
# ---------------------------------------------------------------------------

# (FIELD, id, factory, settings expression) — fresh settings per registration.
BLOCK_DEFS = [
    ("EMBER_LILY", "ember_lily", "Block::new",
     "plantSettings(BlockSoundGroup.SPORE_BLOSSOM, MapColor.ORANGE).luminance(state -> 5)"),
    ("CINDER_FERN", "cinder_fern", "Block::new",
     "plantSettings(BlockSoundGroup.ROOTS, MapColor.GRAY)"),
    ("SEAR_SPRIGS", "sear_sprigs", "Block::new",
     "plantSettings(BlockSoundGroup.ROOTS, MapColor.SPRUCE_BROWN)"),
    ("MOLTEN_BUD", "molten_bud", "Block::new",
     "plantSettings(BlockSoundGroup.NETHER_WART, MapColor.ORANGE).luminance(state -> 6)"),
    ("ASH_BRAMBLE", "ash_bramble", "Block::new",
     "plantSettings(BlockSoundGroup.ROOTS, MapColor.LIGHT_GRAY)"),
    ("GLOW_TENDRILS", "glow_tendrils", "Block::new",
     "plantSettings(BlockSoundGroup.CAVE_VINES, MapColor.TEAL).luminance(state -> 7)"),
    ("SOOT_PUFF", "soot_puff", "Block::new",
     "plantSettings(BlockSoundGroup.FUNGUS, MapColor.GRAY)"),
    ("SLAG_THISTLE", "slag_thistle", "Block::new",
     "plantSettings(BlockSoundGroup.ROOTS, MapColor.GRAY)"),
    ("PYRE_REED", "pyre_reed", "Block::new",
     "plantSettings(BlockSoundGroup.ROOTS, MapColor.ORANGE)"),
    ("COPPER_ROSE", "copper_rose", "Block::new",
     "plantSettings(BlockSoundGroup.SPORE_BLOSSOM, MapColor.ORANGE)"),
    ("INFERNO_ORCHID", "inferno_orchid", "Block::new",
     "plantSettings(BlockSoundGroup.SPORE_BLOSSOM, MapColor.MAGENTA).luminance(state -> 4)"),
    ("CHARRED_SHRUB", "charred_shrub", "Block::new",
     "plantSettings(BlockSoundGroup.ROOTS, MapColor.SPRUCE_BROWN)"),
    ("GILDED_CLOVER", "gilded_clover", "Block::new",
     "plantSettings(BlockSoundGroup.ROOTS, MapColor.YELLOW)"),
    ("HANGING_EMBER_ROOTS", "hanging_ember_roots", "Block::new",
     "plantSettings(BlockSoundGroup.HANGING_ROOTS, MapColor.DARK_RED).luminance(state -> 3)"),
    ("ASH_VEIL", "ash_veil", "Block::new",
     "plantSettings(BlockSoundGroup.HANGING_ROOTS, MapColor.LIGHT_GRAY)"),
    ("EMBERCAP_BLOCK", "embercap_block", "Block::new",
     "capSettings(MapColor.ORANGE, 4)"),
    ("GLOOMCAP_BLOCK", "gloomcap_block", "Block::new",
     "capSettings(MapColor.TEAL, 6)"),
    ("CINDER_CRYSTAL_BLOCK", "cinder_crystal_block", "Block::new",
     "crystalSettings(MapColor.ORANGE, 9)"),
    ("VERDIGRIS_CRYSTAL_BLOCK", "verdigris_crystal_block", "Block::new",
     "crystalSettings(MapColor.EMERALD_GREEN, 7)"),
    ("VERDIGRIS_LICHEN", "verdigris_lichen", "GlowLichenBlock::new",
     "AbstractBlock.Settings.create().mapColor(MapColor.TEAL).replaceable().noCollision()"
     ".strength(0.2F).sounds(BlockSoundGroup.GLOW_LICHEN)"
     ".luminance(GlowLichenBlock.getLuminanceSupplier(7))"),
]

SETTINGS_METHODS = "\n".join([
    "\t/**",
    "\t * Fresh settings per registration ({@link ModBlocks#register} writes a registry key",
    "\t * into the instance). Cross-model garden plants: no collision, instant break — same",
    "\t * documented simplification as the infernoflora plants (placeable on any surface, no",
    "\t * floor check; the worldgen patch predicates are the only ground filter).",
    "\t */",
    "\tprivate static AbstractBlock.Settings plantSettings(BlockSoundGroup sounds, MapColor mapColor) {",
    "\t\treturn AbstractBlock.Settings.create()",
    "\t\t\t\t.mapColor(mapColor)",
    "\t\t\t\t.noCollision()",
    "\t\t\t\t.breakInstantly()",
    "\t\t\t\t.sounds(sounds);",
    "\t}",
    "",
    "\t/** Giant-fungus cap cubes (vanilla wart-block profile: strength 1.0, wart sounds). */",
    "\tprivate static AbstractBlock.Settings capSettings(MapColor mapColor, int light) {",
    "\t\treturn AbstractBlock.Settings.create()",
    "\t\t\t\t.mapColor(mapColor)",
    "\t\t\t\t.strength(1.0F)",
    "\t\t\t\t.sounds(BlockSoundGroup.NETHER_WART)",
    "\t\t\t\t.luminance(state -> light);",
    "\t}",
    "",
    "\t/** Glowing crystal cubes (amethyst-block sounds; hand-breakable, no requiresTool). */",
    "\tprivate static AbstractBlock.Settings crystalSettings(MapColor mapColor, int light) {",
    "\t\treturn AbstractBlock.Settings.create()",
    "\t\t\t\t.mapColor(mapColor)",
    "\t\t\t\t.strength(1.5F)",
    "\t\t\t\t.sounds(BlockSoundGroup.AMETHYST_BLOCK)",
    "\t\t\t\t.luminance(state -> light);",
    "\t}",
])


def wiring_lines(features: dict) -> list:
    lines = [
        "",
        "\t\t// Worldgen wiring: the 25 garden placed features are appended to the six",
        "\t\t// garden Inferno biomes (GARDEN_BIOMES in the generator) in ONE fixed order",
        "\t\t// with ONE shared selector, so every biome sees the identical appended",
        "\t\t// subsequence and the FeatureSorter ordering stays cycle-free (per-biome",
        "\t\t// flavor comes from the ground-block predicates inside the features, which",
        "\t\t// are cheap no-ops on foreign terrain). Signatures verified via javap:",
        "\t\t// addFeature(Predicate<BiomeSelectionContext>,",
        "\t\t// GenerationStep.Feature, RegistryKey<PlacedFeature>).",
        "\t\tPredicate<BiomeSelectionContext> infernoBiomes = BiomeSelectors.includeByKey(",
    ]
    for i, biome in enumerate(GARDEN_BIOMES):
        suffix = ");" if i == len(GARDEN_BIOMES) - 1 else ","
        lines.append(f"\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"{biome}\")){suffix}")
    for name, (_configured, _placement, step) in features.items():
        lines.append(f"\t\tBiomeModifications.addFeature(infernoBiomes, GenerationStep.Feature.{step},")
        lines.append(f"\t\t\t\tRegistryKey.of(RegistryKeys.PLACED_FEATURE, CopperInferno.id(\"{name}\")));")
    lines += [
        "",
        "\t\t// crystal_hollows only gets the crystal trio (curated sparse theme). The",
        "\t\t// three features are appended in the SAME master order as above, so the",
        "\t\t// hollows' appended list is a subsequence of the shared master order and the",
        "\t\t// FeatureSorter stays cycle-free.",
        "\t\tPredicate<BiomeSelectionContext> crystalHollows = BiomeSelectors.includeByKey(",
    ]
    for i, biome in enumerate(CRYSTAL_HOLLOWS_BIOMES):
        suffix = ");" if i == len(CRYSTAL_HOLLOWS_BIOMES) - 1 else ","
        lines.append(f"\t\t\t\tRegistryKey.of(RegistryKeys.BIOME, CopperInferno.id(\"{biome}\")){suffix}")
    for name in CRYSTAL_FEATURES:
        step = features[name][2]
        lines.append(f"\t\tBiomeModifications.addFeature(crystalHollows, GenerationStep.Feature.{step},")
        lines.append(f"\t\t\t\tRegistryKey.of(RegistryKeys.PLACED_FEATURE, CopperInferno.id(\"{name}\")));")
    return lines


# Handbook entry texts: (feature name, icon block id, EN, DE).
HANDBOOK_TEXTS = [
    ("patch_ember_lily", "ember_lily",
     "Ember Lilies (light 5) bloom on the ember moss and glowing soil of the groves.",
     "Glutlilien (Licht 5) bl\u00fchen auf dem Glutmoos und dem gl\u00fchenden Boden der Haine."),
    ("patch_cinder_fern", "cinder_fern",
     "Cinder Ferns unfurl across cinderstone, ember soil and ash all over the Inferno.",
     "Schlackenfarne entrollen sich auf Zunderstein, Glutboden und Asche im ganzen Inferno."),
    ("patch_sear_sprigs", "sear_sprigs",
     "Sear Sprigs: stubby scorched twigs with smoldering tips, rooted in bare cinderstone and ash.",
     "Sengzweiglein: kurze versengte Zweige mit schwelenden Spitzen, verwurzelt in nacktem Zunderstein und Asche."),
    ("patch_molten_bud", "molten_bud",
     "Molten Buds (light 6) swell out of ember soil, their seams glowing with trapped heat.",
     "Schmelzknospen (Licht 6) quellen aus dem Glutboden, ihre N\u00e4hte gl\u00fchen vor gespeicherter Hitze."),
    ("patch_ash_bramble", "ash_bramble",
     "Ash Brambles tangle over ash drifts and cinder gravel in the wastes.",
     "Aschendornb\u00fcsche wuchern \u00fcber Aschenverwehungen und Zinderkies der \u00d6de."),
    ("patch_glow_tendrils", "glow_tendrils",
     "Glow Tendrils (light 7) sway over the moss and wart floors of the Ember Grove.",
     "Leuchtranken (Licht 7) wiegen sich \u00fcber den Moos- und Warzenb\u00f6den des Gluthains."),
    ("patch_soot_puff", "soot_puff",
     "Soot Puffs: dark puffball fungi dotting the slagstone flats of the Slag Sea.",
     "Ru\u00dfboviste: dunkle Bovistpilze auf den Schlackenstein-Ebenen des Schlackenmeers."),
    ("patch_slag_thistle", "slag_thistle",
     "Slag Thistles bristle from slagstone and cobbled cinderstone, hardy against the ash winds.",
     "Schlackendisteln spr\u00fce\u00dfen aus Schlackenstein und Bruchzunderstein, unbeeindruckt von den Aschenwinden."),
    ("patch_pyre_reed", "pyre_reed",
     "Pyre Reeds line the scorched-sand banks near the lava-sea level (y 30-36).",
     "Feuerschilf s\u00e4umt die B\u00e4nke aus verbranntem Sand nahe dem Lavameer-Niveau (y 30-36)."),
    ("patch_copper_rose", "copper_rose",
     "Copper Roses: rare metal-petaled blooms scattered across the grove floors.",
     "Kupferrosen: seltene Bl\u00fcten mit Metallbl\u00e4ttern, verstreut auf den B\u00f6den des Hains."),
    ("patch_inferno_orchid", "inferno_orchid",
     "The Inferno Orchid (light 4) is the rarest garden bloom — it only takes root in ember moss.",
     "Die Inferno-Orchidee (Licht 4) ist die seltenste Gartenbl\u00fcte — sie wurzelt nur in Glutmoos."),
    ("patch_charred_shrub", "charred_shrub",
     "Charred Shrubs: dead, ember-flecked brushwood clawing out of the cinder wastes.",
     "Verkohlte Str\u00e4ucher: totes, glutgesprenkeltes Buschwerk, das sich aus der Aschen\u00f6de krallt."),
    ("patch_gilded_clover", "gilded_clover",
     "Gilded Clover carpets lucky corners of the grove floors in gold-green.",
     "Vergoldeter Klee \u00fcberzieht gl\u00fcckliche Ecken der Hainb\u00f6den in Goldgr\u00fcn."),
    ("garden_wastes_mix", "sear_sprigs",
     "Wastes gardens: mixed stands of sear sprigs, ash brambles, cinder ferns and charred shrubs.",
     "W\u00fcsteng\u00e4rten: gemischte Best\u00e4nde aus Sengzweiglein, Aschendornb\u00fcschen, Schlackenfarnen und verkohlten Str\u00e4uchern."),
    ("garden_grove_mix", "ember_lily",
     "Grove gardens: lush mixed beds of ember lilies, glow tendrils, molten buds, gilded clover and copper roses.",
     "Haing\u00e4rten: \u00fcppige Mischbeete aus Glutlilien, Leuchtranken, Schmelzknospen, vergoldetem Klee und Kupferrosen."),
    ("garden_slag_mix", "slag_thistle",
     "Slag gardens: sparse beds of slag thistles, soot puffs and cinder ferns on the delta flats.",
     "Schlackeng\u00e4rten: karge Beete aus Schlackendisteln, Ru\u00dfbovisten und Schlackenfarnen auf den Delta-Ebenen."),
    ("ceiling_hanging_ember_roots", "hanging_ember_roots",
     "Hanging Ember Roots (light 3) dangle from the cavern ceilings, tips still glowing.",
     "H\u00e4ngende Glutwurzeln (Licht 3) baumeln von den H\u00f6hlendecken, die Spitzen gl\u00fchen noch."),
    ("ceiling_ash_veils", "ash_veil",
     "Ash Veils: pale curtains of compacted ash drifting from the Inferno's roof.",
     "Aschenschleier: fahle Vorh\u00e4nge aus verdichteter Asche, die vom Dach des Infernos wehen."),
    ("wall_verdigris_lichen", "verdigris_lichen",
     "Verdigris Lichen (light 7) creeps across cinderstone and slagstone walls and ceilings; shear it to collect it.",
     "Gr\u00fcnspanflechte (Licht 7) kriecht \u00fcber W\u00e4nde und Decken aus Zunderstein und Schlackenstein; mit der Schere ernten."),
    ("cinder_crystal_cluster", "cinder_crystal_block",
     "Cinder Crystal clusters (light 9) crop out of the wastes floor in tight sparkling clumps.",
     "Schlackenkristall-Ansammlungen (Licht 9) ragen in dichten, funkelnden Klumpen aus dem Boden der \u00d6de."),
    ("verdigris_crystal_cluster", "verdigris_crystal_block",
     "Verdigris Crystal clusters (light 7) grow along the Slag Sea's stone flats.",
     "Gr\u00fcnspankristall-Ansammlungen (Licht 7) wachsen auf den Steinebenen des Schlackenmeers."),
    ("buried_verdigris_crystal", "verdigris_crystal_block",
     "Small verdigris crystal pockets lie buried inside cinderstone — mine for the glow.",
     "Kleine Gr\u00fcnspankristall-Taschen liegen im Zunderstein vergraben — dem Leuchten nach graben."),
    ("giant_embercap", "embercap_block",
     "Giant Embercaps: huge scorched-stem fungi with glowing orange caps (light 4), rooted in ember moss.",
     "Riesen-Glutkappen: gewaltige Pilze mit versengtem Stiel und gl\u00fchend orangen Kappen (Licht 4), verwurzelt im Glutmoos."),
    ("giant_gloomcap", "gloomcap_block",
     "Giant Gloomcaps: towering teal-capped fungi (light 6) hung with fungal lights, rooted in ember wart.",
     "Riesen-D\u00fcsterkappen: t\u00fcrmende Pilze mit t\u00fcrkisen Kappen (Licht 6) und Pilzlichtern, verwurzelt in Glutwarzen."),
    ("patch_ember_moss_carpet", "ember_moss_carpet",
     "Moss shelves: ember moss carpets creep over the moss blocks and glowing soil of the groves.",
     "Moosb\u00e4nke: Glutmoosteppiche kriechen \u00fcber die Moosbl\u00f6cke und den gl\u00fchenden Boden der Haine."),
]


def emit_java(features: dict) -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)

    feature_doc = [
        "Inferno gardens: 25 data-driven worldgen features (flora patches, weighted garden",
        "mixes, ceiling/wall growth, crystal clusters, giant fungi, moss shelves) for the",
        "six garden Inferno biomes (crystal_hollows only receives the crystal trio), plus",
        "the 20 new plant/deco blocks they place. The",
        "configured/placed feature JSONs live under",
        "{@code data/copper_inferno/worldgen/} and are generated by",
        "{@code devtools/gen/infernogardens_gen.py} from extracted vanilla 1.21.9 schemas;",
        "the biome JSONs are NOT touched — placement is wired here via Fabric's",
        "{@code BiomeModifications.addFeature} (signature verified via javap).",
        "",
        "<p>DOCUMENTED SIMPLIFICATIONS (mirroring infernoflora):",
        "<ul>",
        "  <li>The 15 cross-model plants are plain {@link Block}s with",
        "      {@code noCollision().breakInstantly()} — no floor/support check; the worldgen",
        "      patch predicates are the only ground filter.</li>",
        "  <li>All blocks except verdigris_lichen drop themselves unconditionally;",
        "      verdigris_lichen uses the vanilla glow-lichen shears table.</li>",
        "</ul>",
        "",
        "<p>CUTOUT render layers are registered client-side by",
        "{@code InfernoGardensFeatureClient}; handbook pages by",
        "{@link InfernoGardensHandbook}.",
    ]
    feature_src = genlib.java_feature_class(
        "infernogardens", "InfernoGardensFeature", feature_doc,
        blocks=BLOCK_DEFS,
        settings_methods=SETTINGS_METHODS,
        tabs=[("BLOCKS_KEY", [field for field, *_ in BLOCK_DEFS])],
        extra_imports=(
            "java.util.function.Predicate",
            "net.fabricmc.fabric.api.biome.v1.BiomeModifications",
            "net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext",
            "net.fabricmc.fabric.api.biome.v1.BiomeSelectors",
            "net.minecraft.block.GlowLichenBlock",
            "net.minecraft.block.MapColor",
            "net.minecraft.registry.RegistryKey",
            "net.minecraft.registry.RegistryKeys",
            "net.minecraft.sound.BlockSoundGroup",
            "net.minecraft.world.gen.GenerationStep",
            "net.sonic0810.copperinferno.CopperInferno",
        ),
        extra_init_lines=wiring_lines(features),
        handbook_class="InfernoGardensHandbook",
    )
    (FEATURE_DIR / "InfernoGardensFeature.java").write_text(feature_src, encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the Inferno gardens: one \"dimension\" lore entry per worldgen",
        "feature added by {@code InfernoGardensFeature} (no recipes — the gardens are",
        "found, not crafted). Entry ids mirror the placed-feature ids emitted by",
        "{@code devtools/gen/infernogardens_gen.py}; {@code devtools/check_handbook.py}",
        "parses the inline {@code new HandbookEntry(...)} literals positionally, so keep",
        "them inline.",
    ]
    entries = [("dimension", f"infernogardens_{name}", f"{NS}:{icon}", None, None, None, 0,
                text_en, text_de)
               for name, icon, text_en, text_de in HANDBOOK_TEXTS]
    handbook_src = genlib.java_handbook_class("infernogardens", "InfernoGardensHandbook",
                                              handbook_doc, entries)
    (FEATURE_DIR / "InfernoGardensHandbook.java").write_text(handbook_src, encoding="utf-8")

    # Client class: CUTOUT render layers for the cross plants + the multiface lichen
    # (transparent-background textures), same pattern as InfernoFloraFeatureClient.
    CLIENT_DIR.mkdir(parents=True, exist_ok=True)
    cutout_fields = [field for field, bid, *_ in BLOCK_DEFS if bid in CROSS_PLANTS] + ["VERDIGRIS_LICHEN"]
    client_lines = [
        "package net.sonic0810.copperinferno.feature.infernogardens.client;",
        "",
        "import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;",
        "import net.minecraft.client.render.BlockRenderLayer;",
        "import net.sonic0810.copperinferno.feature.infernogardens.InfernoGardensFeature;",
        "",
        "/**",
        " * Client-side setup for the Inferno gardens: CUTOUT render layers for the 15 cross-model",
        " * garden plants and the multiface verdigris lichen (their textures use full alpha",
        " * transparency around the growth shape). Same {@code BlockRenderLayerMap.putBlocks}",
        " * pattern as InfernoFloraFeatureClient.",
        " */",
        "public final class InfernoGardensFeatureClient {",
        "\tprivate InfernoGardensFeatureClient() {",
        "\t}",
        "",
        "\tpublic static void initClient() {",
        "\t\tBlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,",
    ]
    client_lines += [f"\t\t\t\tInfernoGardensFeature.{field},"
                     for field in cutout_fields[:-1]]
    client_lines += [f"\t\t\t\tInfernoGardensFeature.{cutout_fields[-1]});",
                     "\t}", "}"]
    (CLIENT_DIR / "InfernoGardensFeatureClient.java").write_text(
        "\n".join(client_lines) + "\n", encoding="utf-8")


def emit_hooks(features: dict) -> None:
    lines = [
        "# infernogardens feature hooks (format: devtools/hooks/README.md)",
        "",
        "[init]",
        "# After InfernoDimensionFeature + InfernoFloraFeature: the worldgen JSON places",
        "# their terrain/flora blocks (cinderstone grounds, scorched_stem fungus stems,",
        "# glowing_spore_block/fungal_light decor, ember_moss_carpet shelves).",
        "import net.sonic0810.copperinferno.feature.infernogardens.InfernoGardensFeature;",
        "\t\tInfernoGardensFeature.init();",
        "",
        "[client-init]",
        "import net.sonic0810.copperinferno.feature.infernogardens.client.InfernoGardensFeatureClient;",
        "\t\tInfernoGardensFeatureClient.initClient();",
        "",
        "[counts]",
        f"worldgen-features: {len(features)}",
        f"blocks: {len(ALL_IDS)}",
        f"handbook-entries: {len(HANDBOOK_TEXTS)}",
        "",
    ]
    path = ROOT / "devtools" / "hooks" / "infernogardens.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Verification: every block a feature places must exist (either registered by this
# feature or already shipped by another feature — checked against the on-disk
# blockstates dir, which every registered block has per the 1.21.9 asset contract).
# ---------------------------------------------------------------------------

def collect_block_refs(node, refs: set) -> None:
    if isinstance(node, dict):
        name = node.get("Name")
        if isinstance(name, str) and name.startswith(f"{NS}:"):
            refs.add(name)
        for key in ("blocks", "can_be_placed_on", "block", "valid_blocks"):
            value = node.get(key)
            if isinstance(value, str) and value.startswith(f"{NS}:"):
                refs.add(value)
            elif isinstance(value, list):
                refs.update(v for v in value if isinstance(v, str) and v.startswith(f"{NS}:"))
        for value in node.values():
            collect_block_refs(value, refs)
    elif isinstance(node, list):
        for value in node:
            collect_block_refs(value, refs)


def verify(features: dict) -> None:
    assert len(features) == 25, f"expected 25 features, got {len(features)}"
    assert len(set(features)) == 25, "duplicate feature names"
    assert len(ALL_IDS) == 20 == len(set(ALL_IDS)), "expected 20 unique block ids"

    refs: set = set()
    for name, (configured, placement, step) in features.items():
        assert step in (VEGETAL, UNDERGROUND), f"{name}: bad step {step}"
        collect_block_refs(configured, refs)
        collect_block_refs(placement, refs)
        # round-trip parse (deterministic dump must be valid JSON)
        json.loads(json.dumps(configured))
        json.loads(json.dumps(placement))

    new_ids = {f"{NS}:{bid}" for bid in ALL_IDS}
    for ref in sorted(refs):
        if ref in new_ids:
            continue
        bid = ref.split(":", 1)[1]
        assert (ASSETS / "blockstates" / f"{bid}.json").exists(), \
            f"feature places unregistered block {ref} (no blockstate on disk)"

    # every NEW block must actually be placed by at least one feature
    unplaced = new_ids - refs
    assert not unplaced, f"registered blocks never placed by any feature: {sorted(unplaced)}"

    # feature ids in the handbook must match the feature dict exactly, in order
    assert [name for name, *_ in HANDBOOK_TEXTS] == list(features), \
        "handbook entries out of sync with the feature list"

    # crystal_hollows curation must be a subsequence of the master feature order
    # (FeatureSorter cycle-free rule: every biome's appended list follows ONE order)
    assert [name for name in features if name in set(CRYSTAL_FEATURES)] == CRYSTAL_FEATURES, \
        "CRYSTAL_FEATURES out of master feature order (FeatureSorter subsequence rule)"


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    features = build_features()
    verify(features)

    emit_worldgen(features)
    emit_block_assets()
    emit_loot()
    emit_textures()

    lang_en = {f"block.{NS}.{bid}": display_name(bid) for bid in ALL_IDS}
    lang_de = {f"block.{NS}.{bid}": DE_NAMES[bid] for bid in ALL_IDS}
    assert sorted(lang_de) == sorted(lang_en)
    if WRITE_JSON:
        genlib.lang_fragments(ASSETS, "infernogardens",
                              dict(sorted(lang_en.items())), dict(sorted(lang_de.items())))

    write_json(ROOT / "devtools" / "tagfrag" / "infernogardens.json", {
        "block/mineable/hoe": sorted(f"{NS}:{i}" for i in
                                     ("embercap_block", "gloomcap_block")),
        "block/mineable/pickaxe": sorted(f"{NS}:{i}" for i in
                                         ("cinder_crystal_block", "verdigris_crystal_block")),
    })

    emit_java(features)
    emit_hooks(features)

    mode = "PNG+JSON+Java" if WRITE_JSON else "PNG+Java only; JSON skipped (pass --write-json)"
    print(f"infernogardens_gen: {len(features)} worldgen features "
          f"(25 configured + 25 placed), {len(ALL_IDS)} blocks, "
          f"{len(HANDBOOK_TEXTS)} handbook entries ({mode}).")


if __name__ == "__main__":
    main()
