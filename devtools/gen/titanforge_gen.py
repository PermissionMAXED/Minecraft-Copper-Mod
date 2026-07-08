#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "titanforge" feature (96 gear items).

Six new tool & armor material tiers forged in the TitanForge progression —
ember_steel -> pyrite -> slagsteel -> cinderforge -> molten_titan -> infernal_alloy —
each shipping sword/pickaxe/axe/shovel/hoe + helmet/chestplate/leggings/boots (54 gear
items), plus one alloy ingot and six charm/trinket items per tier (42 plain items).
54 + 42 = 96 items total.

Idempotent: running it any number of times produces byte-identical output (all pixel art
is deterministic; the armor-layer recolor is a pure function of the vanilla iron layer
PNGs). Emits by DEFAULT (no flags), mirroring devtools/gen/pyrestone_gen.py:
  - items/<id>.json model-definitions + models/item/<id>.json (genlib emitters; tools use
    parent minecraft:item/handheld exactly like devtools/gen/infernium_gen.py)
  - 16x16 item textures (Pillow, deterministic pixel art, distinct palette per tier)
  - equipment assets assets/copper_inferno/equipment/<tier>.json (schema copied from
    infernium_gen.py == vanilla assets/minecraft/equipment/iron.json minus horse_body)
  - worn-armor layer textures textures/entity/equipment/{humanoid,humanoid_leggings}/
    <tier>.png (vanilla iron layers recolored per tier, exactly like infernium_gen.py)
  - repair item tags data/copper_inferno/tags/item/<tier>_repair.json
  - recipes data/copper_inferno/recipe/titanforge/*.json (141: vanilla iron tool/armor
    shapes with sticks, alloy-ingot shapeless chains, charm shapes, and smithing_transform
    upgrade chains between consecutive tiers using the shipped
    copper_inferno:infernium_upgrade_smithing_template — formats copied from
    infernium_gen.py). EVERY recipe contains at least one titanforge id.
  - lang fragments EN + DE (assets/copper_inferno/lang/fragments{,_de}/titanforge.json)
  - devtools/tagfrag/titanforge.json (item/swords..foot_armor for merge_tags.py)
  - src/main/java/.../feature/titanforge/TitanForgeFeature.java + TitanForgeHandbook.java
    (genlib.java_feature_class / genlib.java_handbook_class; literal ids only)
  - devtools/hooks/titanforge.txt (integration hook file)

1.21.9 API notes (verified with javap against the loom minecraft-common jar, mirroring
feature/infernium/InferniumFeature.java):
  ToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float speed,
               float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems)
  ArmorMaterial(int durability, Map<EquipmentType,Integer> defense, int enchantmentValue,
                RegistryEntry<SoundEvent> equipSound, float toughness,
                float knockbackResistance, TagKey<Item> repairIngredient,
                RegistryKey<EquipmentAsset> assetId)
  Item.Settings.sword/pickaxe(ToolMaterial, float, float), Item.Settings.armor(
  ArmorMaterial, EquipmentType); AxeItem/ShovelItem/HoeItem(ToolMaterial, float, float,
  Item.Settings).
"""

import sys
import zipfile
from collections import namedtuple
from pathlib import Path

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, write_json

RECIPES = DATA / "recipe" / "titanforge"
FEATURE_DIR = ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno" / "feature" / "titanforge"
CLIENT_JAR = Path.home() / ".gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"

# The shipped infernium upgrade template (feature/infernium) doubles as the TitanForge
# upgrade template: smithing recipes reference it by id only (data-side dependency).
TPL = f"{NS}:infernium_upgrade_smithing_template"

# ---------------------------------------------------------------------------
# Tiers, in PROGRESSION ORDER (each tier's ingot alloys from the previous tier's ingot;
# smithing upgrades transform the previous tier's gear piece into this tier's).
# Tool stats: (incorrect-for tag, durability, speed, attackDamageBonus, enchantmentValue).
# Armor stats: (durability multiplier, defense (boots, leggings, chestplate, helmet,
# body), enchantmentValue, equip sound, toughness, knockbackResistance) — the defense
# order matches InferniumFeature.defenseMap.
# pal = the tier's 4-color texture ramp (dark, base, bright, hot).
# ---------------------------------------------------------------------------
Tier = namedtuple("Tier", "tid en de incorrect dur speed dmg ench "
                          "adur defense aench sound tough kb fireproof pal "
                          "ingot_ings ingot_count")

TIERS = [
    Tier("ember_steel", "Ember Steel", "Glutstahl",
         "INCORRECT_FOR_IRON_TOOL", 320, "6.5F", "2.0F", 16,
         16, (2, 5, 6, 2, 5), 16, "ITEM_ARMOR_EQUIP_IRON", "0.5F", "0.0F", False,
         {"dark": (0x3A, 0x22, 0x1E), "base": (0xB4, 0x4A, 0x28),
          "bright": (0xE0, 0x70, 0x38), "hot": (0xFF, 0xB2, 0x6B)},
         ["minecraft:iron_ingot", "minecraft:blaze_powder", "minecraft:coal"], 2),
    Tier("pyrite", "Pyrite", "Pyrit",
         "INCORRECT_FOR_IRON_TOOL", 500, "7.0F", "2.0F", 22,
         12, (2, 4, 6, 2, 4), 22, "ITEM_ARMOR_EQUIP_GOLD", "0.0F", "0.0F", False,
         {"dark": (0x5C, 0x48, 0x14), "base": (0xC6, 0x9E, 0x2C),
          "bright": (0xE8, 0xC4, 0x48), "hot": (0xFF, 0xEC, 0x96)},
         [f"{NS}:ember_steel_ingot", "minecraft:gold_ingot", "minecraft:glowstone_dust"], 2),
    Tier("slagsteel", "Slagsteel", "Schlackenstahl",
         "INCORRECT_FOR_IRON_TOOL", 900, "6.0F", "3.0F", 10,
         24, (3, 5, 7, 3, 6), 10, "ITEM_ARMOR_EQUIP_IRON", "1.0F", "0.05F", False,
         {"dark": (0x2A, 0x2A, 0x30), "base": (0x6C, 0x70, 0x7A),
          "bright": (0x96, 0x9C, 0xA8), "hot": (0xD0, 0xD6, 0xE0)},
         [f"{NS}:pyrite_ingot", "minecraft:iron_ingot", "minecraft:charcoal"], 2),
    Tier("cinderforge", "Cinderforge", "Zinderschmiede",
         "INCORRECT_FOR_DIAMOND_TOOL", 1400, "8.0F", "3.0F", 12,
         33, (3, 6, 8, 3, 7), 12, "ITEM_ARMOR_EQUIP_DIAMOND", "2.0F", "0.0F", False,
         {"dark": (0x46, 0x12, 0x0E), "base": (0xA8, 0x28, 0x1C),
          "bright": (0xD8, 0x50, 0x2C), "hot": (0xFF, 0x8C, 0x50)},
         [f"{NS}:slagsteel_ingot", "minecraft:diamond", "minecraft:blaze_rod"], 2),
    Tier("molten_titan", "Molten Titan", "Schmelztitan",
         "INCORRECT_FOR_DIAMOND_TOOL", 1800, "8.5F", "3.5F", 14,
         36, (3, 6, 8, 3, 8), 14, "ITEM_ARMOR_EQUIP_NETHERITE", "2.5F", "0.05F", True,
         {"dark": (0x50, 0x28, 0x10), "base": (0xE2, 0x6E, 0x22),
          "bright": (0xFF, 0xA0, 0x40), "hot": (0xFF, 0xDC, 0x96)},
         [f"{NS}:cinderforge_ingot", "minecraft:magma_block", "minecraft:ghast_tear"], 2),
    # DE stem carries the Fugen-s for compounds: H\u00f6llenlegierungs-Schwert etc.
    Tier("infernal_alloy", "Infernal Alloy", "H\u00f6llenlegierungs",
         "INCORRECT_FOR_NETHERITE_TOOL", 2400, "9.5F", "4.5F", 18,
         40, (4, 7, 9, 4, 9), 18, "ITEM_ARMOR_EQUIP_NETHERITE", "3.5F", "0.1F", True,
         {"dark": (0x28, 0x14, 0x30), "base": (0x6E, 0x34, 0x8C),
          "bright": (0xA0, 0x54, 0xC8), "hot": (0xE6, 0xAA, 0xFF)},
         [f"{NS}:molten_titan_ingot", "minecraft:netherite_ingot", "minecraft:blaze_rod"], 1),
]

assert len(TIERS) == 6
assert len({t.tid for t in TIERS}) == 6

TOOL_PIECES = [("sword", "Sword", "Schwert"),
               ("pickaxe", "Pickaxe", "Spitzhacke"),
               ("axe", "Axe", "Axt"),
               ("shovel", "Shovel", "Schaufel"),
               ("hoe", "Hoe", "Hacke")]
ARMOR_PIECES = [("helmet", "Helmet", "Helm", "HELMET"),
                ("chestplate", "Chestplate", "Brustpanzer", "CHESTPLATE"),
                ("leggings", "Leggings", "Beinschutz", "LEGGINGS"),
                ("boots", "Boots", "Stiefel", "BOOTS")]
# (suffix, EN noun, DE noun, vanilla flavor center of the charm recipe,
#  EN flavor template, DE flavor template) — {en}/{de} = tier display names.
CHARM_PIECES = [
    ("charm", "Charm", "Gl\u00fccksbringer", "minecraft:string",
     "A pocket {en} charm for luck at the forge.",
     "Ein {de}-Gl\u00fccksbringer f\u00fcr Gl\u00fcck an der Esse."),
    ("totem", "Totem", "Totem", "minecraft:emerald",
     "A carved {en} totem watching over the smithy.",
     "Ein geschnitztes {de}-Totem wacht \u00fcber die Schmiede."),
    ("ring", "Ring", "Ring", "minecraft:gold_nugget",
     "A polished {en} ring, warm to the touch.",
     "Ein polierter {de}-Ring, warm bei Ber\u00fchrung."),
    ("amulet", "Amulet", "Amulett", "minecraft:amethyst_shard",
     "The {en} amulet swings on a silver chain.",
     "Das {de}-Amulett schwingt an silberner Kette."),
    ("talisman", "Talisman", "Talisman", "minecraft:blaze_powder",
     "This {en} talisman is etched with forge runes.",
     "Dieser {de}-Talisman ist mit Schmiederunen graviert."),
    ("medallion", "Medallion", "Medaillon", "minecraft:copper_ingot",
     "The {en} medallion is awarded to master smiths.",
     "Das {de}-Medaillon wird an Meisterschmiede verliehen."),
]

TOOL_PATTERNS = {"sword": ["X", "X", "#"],
                 "pickaxe": ["XXX", " # ", " # "],
                 "axe": ["XX", "X#", " #"],
                 "shovel": ["X", "#", "#"],
                 "hoe": ["XX", " #", " #"]}
ARMOR_PATTERNS = {"helmet": ["XXX", "X X"],
                  "chestplate": ["X X", "XXX", "XXX"],
                  "leggings": ["XXX", "X X", "X X"],
                  "boots": ["X X", "X X"]}

STICK = "minecraft:stick"
# 3x3 handbook grids, row-major ("X" = tier ingot, "#" = stick) — layouts mirror the
# hand-written InferniumFeature handbook entries.
TOOL_GRIDS = {"sword": ["", "X", "", "", "X", "", "", "#", ""],
              "pickaxe": ["X", "X", "X", "", "#", "", "", "#", ""],
              "axe": ["X", "X", "", "X", "#", "", "", "#", ""],
              "shovel": ["", "X", "", "", "#", "", "", "#", ""],
              "hoe": ["X", "X", "", "", "#", "", "", "#", ""]}
ARMOR_GRIDS = {"helmet": ["X", "X", "X", "X", "", "X", "", "", ""],
               "chestplate": ["X", "", "X", "X", "X", "X", "X", "X", "X"],
               "leggings": ["X", "X", "X", "X", "", "X", "X", "", "X"],
               "boots": ["X", "", "X", "X", "", "X", "", "", ""]}


def ids_of(tier: Tier) -> list:
    """All 16 item ids of one tier, in creative-tab order."""
    out = [f"{tier.tid}_ingot"]
    out += [f"{tier.tid}_{p}" for p, _, _ in TOOL_PIECES]
    out += [f"{tier.tid}_{p}" for p, _, _, _ in ARMOR_PIECES]
    out += [f"{tier.tid}_{p}" for p, _, _, _, _, _ in CHARM_PIECES]
    return out


ALL_IDS = [i for t in TIERS for i in ids_of(t)]
HANDHELD = {f"{t.tid}_{p}" for t in TIERS for p, _, _ in TOOL_PIECES}


# ---------------------------------------------------------------------------
# Display names (EN + real German; German compounds hyphenate like Infernium-Schwert).
# ---------------------------------------------------------------------------

def build_names():
    en, de = {}, {}
    for t in TIERS:
        en[f"{t.tid}_ingot"] = f"{t.en} Ingot"
        de[f"{t.tid}_ingot"] = f"{t.de}-Barren"
        for p, pen, pde in TOOL_PIECES:
            en[f"{t.tid}_{p}"] = f"{t.en} {pen}"
            de[f"{t.tid}_{p}"] = f"{t.de}-{pde}"
        for p, pen, pde, _et in ARMOR_PIECES:
            en[f"{t.tid}_{p}"] = f"{t.en} {pen}"
            de[f"{t.tid}_{p}"] = f"{t.de}-{pde}"
        for p, pen, pde, _c, _fe, _fd in CHARM_PIECES:
            en[f"{t.tid}_{p}"] = f"{t.en} {pen}"
            de[f"{t.tid}_{p}"] = f"{t.de}-{pde}"
    return en, de


EN, DE = build_names()


# ---------------------------------------------------------------------------
# Handbook entries (collected while emitting recipes; rendered by java_handbook_class).
# ---------------------------------------------------------------------------
HANDBOOK = []


def hb(category, name, icon, grid, result, count, text_en, text_de):
    HANDBOOK.append((category, f"titanforge/{name}", icon, f"titanforge/{name}",
                     grid, result, count, text_en, text_de))


# ---------------------------------------------------------------------------
# Recipes (141). Formats are byte-identical to genlib / infernium_gen output.
# ---------------------------------------------------------------------------

def emit_smithing(name: str, base: str, addition: str, result: str) -> None:
    """smithing_transform, exact field set of infernium_gen's *_smithing recipes."""
    write_json(RECIPES / f"{name}.json", {
        "type": "minecraft:smithing_transform",
        "addition": addition,
        "base": base,
        "result": {"id": result},
        "template": TPL,
    })


def emit_recipes() -> int:
    count = 0
    for idx, t in enumerate(TIERS):
        ing = f"{NS}:{t.tid}_ingot"

        # --- alloy ingot (shapeless; tiers >= 2 consume the previous tier's ingot)
        genlib.emit_shapeless(RECIPES, f"{t.tid}_ingot", list(t.ingot_ings), ing,
                              t.ingot_count, category="misc")
        hb("items", f"{t.tid}_ingot", ing,
           list(t.ingot_ings) + [""] * 6, ing, t.ingot_count,
           f"Alloy the shown ingredients into {t.ingot_count}x {t.en} Ingot.",
           f"Die gezeigten Zutaten zu {t.ingot_count}x {t.de}-Barren legieren.")
        count += 1

        # --- tools (vanilla iron tool shapes: tier ingots + sticks)
        for p, pen, pde in TOOL_PIECES:
            rid = f"{t.tid}_{p}"
            genlib.emit_shaped(RECIPES, rid, {"#": STICK, "X": ing},
                               TOOL_PATTERNS[p], f"{NS}:{rid}", 1, category="equipment")
            grid = [ing if g == "X" else (STICK if g == "#" else "") for g in TOOL_GRIDS[p]]
            hb("gear", rid, f"{NS}:{rid}", grid, f"{NS}:{rid}", 1,
               f"Craft the {t.en} {pen} from {t.en} Ingots and sticks.",
               f"{t.de}-{pde} aus {t.de}-Barren und St\u00f6cken herstellen.")
            count += 1

        # --- armor (vanilla iron armor shapes)
        for p, pen, pde, _et in ARMOR_PIECES:
            rid = f"{t.tid}_{p}"
            genlib.emit_shaped(RECIPES, rid, {"X": ing},
                               ARMOR_PATTERNS[p], f"{NS}:{rid}", 1, category="equipment")
            grid = [ing if g == "X" else "" for g in ARMOR_GRIDS[p]]
            hb("gear", rid, f"{NS}:{rid}", grid, f"{NS}:{rid}", 1,
               f"Craft the {t.en} {pen} from {t.en} Ingots.",
               f"{t.de}-{pde} aus {t.de}-Barren herstellen.")
            count += 1

        # --- charms (badge shape from gear_gen: ingot ring around a vanilla flavor item)
        for p, pen, pde, center, fen, fde in CHARM_PIECES:
            rid = f"{t.tid}_{p}"
            genlib.emit_shaped(RECIPES, rid, {"I": ing, "X": center},
                               [" I ", "IXI", " I "], f"{NS}:{rid}", 1, category="misc")
            hb("gear", rid, f"{NS}:{rid}",
               ["", ing, "", ing, center, ing, "", ing, ""], f"{NS}:{rid}", 1,
               fen.format(en=t.en), fde.format(de=t.de))
            count += 1

        # --- smithing upgrade chain (previous tier's piece + this tier's ingot;
        #     template = the shipped infernium upgrade smithing template)
        if idx > 0:
            prev = TIERS[idx - 1]
            for p, pen, pde in TOOL_PIECES:
                pieces = (p, pen, pde)
                _emit_upgrade(t, prev, ing, pieces)
                count += 1
            for p, pen, pde, _et in ARMOR_PIECES:
                _emit_upgrade(t, prev, ing, (p, pen, pde))
                count += 1
    return count


def _emit_upgrade(t: Tier, prev: Tier, ing: str, piece) -> None:
    p, pen, pde = piece
    rid = f"{t.tid}_{p}_smithing"
    base = f"{NS}:{prev.tid}_{p}"
    emit_smithing(rid, base, ing, f"{NS}:{t.tid}_{p}")
    hb("gear", rid, f"{NS}:{t.tid}_{p}",
       ["", "", "", TPL, base, ing, "", "", ""], f"{NS}:{t.tid}_{p}", 1,
       f"Smithing table: upgrade the {prev.en} {pen} with an Infernium Upgrade "
       f"Smithing Template and a {t.en} Ingot.",
       f"Schmiedetisch: {prev.de}-{pde} mit Infernium-Aufwertungs-Schmiedevorlage "
       f"und {t.de}-Barren aufwerten.")


# ---------------------------------------------------------------------------
# Item defs / models / equipment assets / repair tags / tagfrag
# ---------------------------------------------------------------------------

def emit_item_assets() -> None:
    for item_id in ALL_IDS:
        # 1.21.9 two-file contract; tools use the handheld parent (infernium_gen).
        parent = "minecraft:item/handheld" if item_id in HANDHELD else "minecraft:item/generated"
        write_json(ASSETS / "models" / "item" / f"{item_id}.json",
                   {"parent": parent, "textures": {"layer0": f"{NS}:item/{item_id}"}})
        genlib.emit_item_def(ASSETS, item_id)


def emit_equipment_assets() -> None:
    # Schema copied from infernium_gen.emit_equipment_asset (== vanilla iron.json minus
    # the horse_body layer).
    for t in TIERS:
        write_json(ASSETS / "equipment" / f"{t.tid}.json", {
            "layers": {
                "humanoid": [{"texture": f"{NS}:{t.tid}"}],
                "humanoid_leggings": [{"texture": f"{NS}:{t.tid}"}],
            }
        })


def emit_repair_tags() -> None:
    for t in TIERS:
        write_json(DATA / "tags" / "item" / f"{t.tid}_repair.json",
                   {"values": [f"{NS}:{t.tid}_ingot"]})


def emit_tagfrag() -> None:
    write_json(ROOT / "devtools" / "tagfrag" / "titanforge.json", {
        "item/swords": [f"{NS}:{t.tid}_sword" for t in TIERS],
        "item/pickaxes": [f"{NS}:{t.tid}_pickaxe" for t in TIERS],
        "item/axes": [f"{NS}:{t.tid}_axe" for t in TIERS],
        "item/shovels": [f"{NS}:{t.tid}_shovel" for t in TIERS],
        "item/hoes": [f"{NS}:{t.tid}_hoe" for t in TIERS],
        "item/head_armor": [f"{NS}:{t.tid}_helmet" for t in TIERS],
        "item/chest_armor": [f"{NS}:{t.tid}_chestplate" for t in TIERS],
        "item/leg_armor": [f"{NS}:{t.tid}_leggings" for t in TIERS],
        "item/foot_armor": [f"{NS}:{t.tid}_boots" for t in TIERS],
    })


# ---------------------------------------------------------------------------
# Item textures (16x16, deterministic pixel art; helpers copied from infernium_gen /
# gear_gen with the ember palette replaced by per-tier ramps).
# ---------------------------------------------------------------------------

K = (0x1C, 0x12, 0x16)        # outline / near-black
D = (0x2B, 0x22, 0x26)        # handle charcoal dark
C = (0x3D, 0x2C, 0x2E)        # handle charcoal
WHITE = (242, 239, 234)
SILVER = (154, 160, 166)
WHOT = (0xFF, 0xE0, 0xC0)     # near-white hot spark


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def put(img, x, y, color):
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (*color, 255))


def fill_rect(img, x0, y0, x1, y1, color):
    for y in range(y0, y1 + 1):
        for x in range(x0, x1 + 1):
            put(img, x, y, color)


def outline(img, color=K):
    """MC-style sprite outline drawn OUTSIDE the shape (copied from infernium_gen)."""
    src = img.copy()
    for y in range(16):
        for x in range(16):
            if src.getpixel((x, y))[3] != 0:
                continue
            for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)):
                nx, ny = x + dx, y + dy
                if 0 <= nx < 16 and 0 <= ny < 16 and src.getpixel((nx, ny))[3] != 0:
                    put(img, x, y, color)
                    break
    return img


def diag_handle(img, x0, y0, steps, tip):
    """2-wide charcoal handle running up-right from (x0, y0), tier-colored base tip."""
    x = x0
    for i in range(steps):
        x, y = x0 + i, y0 - i
        put(img, x, y, D)
        put(img, x + 1, y, C)
    put(img, x0, y0, tip)


def paint(rows, palette):
    """Renders a 16x16 char map: '.' = transparent (copied from gear_gen)."""
    assert len(rows) == 16, f"need 16 rows, got {len(rows)}"
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    for y, row in enumerate(rows):
        assert len(row) == 16, f"row {y} is {len(row)} wide: {row!r}"
        for x, ch in enumerate(row):
            if ch != ".":
                px[x, y] = palette[ch] + (255,)
    return img


def tex_ingot(pal) -> Image.Image:
    img = blank()
    for i in range(3):  # top face rows y=4..6, shifting left
        y = 4 + i
        for x in range(6 - i, 12 - i + 1):
            put(img, x, y, pal["hot"] if i == 0 else pal["bright"])
    fill_rect(img, 3, 7, 10, 10, pal["base"])   # front face
    for i in range(3):                          # right side face, sloped
        y = 7 + i
        put(img, 11 + (2 - i) // 2, y, pal["dark"])
    fill_rect(img, 11, 7, 11, 10, pal["dark"])
    put(img, 4, 8, pal["bright"])
    put(img, 5, 8, pal["bright"])
    put(img, 7, 5, WHOT)
    return outline(img)


def tex_sword(pal) -> Image.Image:
    img = blank()
    for i in range(9):
        x, y = 13 - i, 1 + i
        put(img, x, y, pal["hot"] if i < 2 else pal["bright"])
        put(img, x - 1, y + 1, pal["base"])
        put(img, x, y + 1, pal["bright"])
    for x, y in [(3, 10), (4, 11), (4, 9), (5, 10), (2, 11), (5, 12)]:
        put(img, x, y, pal["base"])
    put(img, 3, 12, D)
    put(img, 2, 13, D)
    put(img, 1, 14, C)
    return outline(img)


def tex_pickaxe(pal) -> Image.Image:
    img = blank()
    diag_handle(img, 2, 13, 9, pal["base"])
    head = [(4, 3), (5, 2), (6, 1), (7, 1), (8, 1), (9, 1), (10, 2), (11, 3),
            (12, 4), (13, 5), (13, 6), (14, 7), (14, 8)]
    for x, y in head:
        put(img, x, y, pal["base"])
        put(img, x, y + 1, pal["bright"])
    for x, y in [(7, 1), (8, 1), (7, 2), (8, 2)]:
        put(img, x, y, pal["hot"])
    return outline(img)


def tex_axe(pal) -> Image.Image:
    img = blank()
    diag_handle(img, 2, 13, 9, pal["base"])
    fill_rect(img, 9, 2, 12, 2, pal["base"])
    fill_rect(img, 8, 3, 13, 3, pal["base"])
    fill_rect(img, 7, 4, 13, 4, pal["base"])
    fill_rect(img, 7, 5, 9, 5, pal["base"])
    fill_rect(img, 12, 5, 13, 5, pal["base"])
    fill_rect(img, 7, 6, 8, 6, pal["base"])
    put(img, 7, 7, pal["base"])
    for x, y in [(9, 2), (10, 2), (8, 3)]:
        put(img, x, y, pal["hot"])
    for x, y in [(7, 5), (7, 6), (7, 7), (8, 6)]:
        put(img, x, y, pal["bright"])
    return outline(img)


def tex_shovel(pal) -> Image.Image:
    img = blank()
    diag_handle(img, 2, 13, 8, pal["base"])
    fill_rect(img, 11, 1, 12, 1, pal["base"])
    fill_rect(img, 10, 2, 13, 2, pal["base"])
    fill_rect(img, 9, 3, 14, 3, pal["base"])
    fill_rect(img, 9, 4, 14, 4, pal["base"])
    fill_rect(img, 10, 5, 13, 5, pal["base"])
    fill_rect(img, 11, 6, 12, 6, pal["base"])
    for x, y in [(11, 1), (12, 1), (10, 2), (11, 2)]:
        put(img, x, y, pal["hot"])
    for x, y in [(9, 4), (10, 5), (11, 6)]:
        put(img, x, y, pal["bright"])
    return outline(img)


def tex_hoe(pal) -> Image.Image:
    img = blank()
    diag_handle(img, 2, 13, 9, pal["base"])
    for x, y in [(8, 1), (9, 1), (10, 1), (11, 1), (12, 2), (13, 3),
                 (8, 2), (9, 2), (10, 2), (11, 2), (12, 3)]:
        put(img, x, y, pal["base"])
    for x, y in [(8, 1), (9, 1)]:
        put(img, x, y, pal["hot"])
    put(img, 8, 2, pal["bright"])
    return outline(img)


def tex_helmet(pal) -> Image.Image:
    img = blank()
    fill_rect(img, 4, 4, 11, 5, pal["base"])
    fill_rect(img, 3, 6, 12, 8, pal["base"])
    put(img, 5, 3, pal["base"])
    fill_rect(img, 6, 3, 9, 3, pal["base"])
    put(img, 10, 3, pal["base"])
    fill_rect(img, 3, 9, 5, 11, pal["base"])
    fill_rect(img, 10, 9, 12, 11, pal["base"])
    fill_rect(img, 6, 9, 9, 9, pal["bright"])
    fill_rect(img, 5, 4, 6, 5, pal["hot"])
    put(img, 7, 3, pal["hot"])
    put(img, 4, 7, pal["bright"])
    return outline(img)


def tex_chestplate(pal) -> Image.Image:
    img = blank()
    fill_rect(img, 2, 3, 5, 5, pal["base"])
    fill_rect(img, 10, 3, 13, 5, pal["base"])
    fill_rect(img, 3, 6, 12, 12, pal["base"])
    fill_rect(img, 6, 4, 9, 5, pal["bright"])
    for x in range(6, 10):
        img.putpixel((x, 3), (0, 0, 0, 0))
    fill_rect(img, 4, 7, 5, 9, pal["hot"])
    fill_rect(img, 7, 8, 8, 12, pal["bright"])
    put(img, 3, 3, pal["hot"])
    return outline(img)


def tex_leggings(pal) -> Image.Image:
    img = blank()
    fill_rect(img, 3, 3, 12, 5, pal["base"])
    fill_rect(img, 3, 3, 12, 3, pal["bright"])
    fill_rect(img, 3, 6, 6, 13, pal["base"])
    fill_rect(img, 9, 6, 12, 13, pal["base"])
    fill_rect(img, 4, 6, 4, 12, pal["hot"])
    put(img, 10, 6, pal["bright"])
    put(img, 10, 7, pal["bright"])
    return outline(img)


def tex_boots(pal) -> Image.Image:
    img = blank()
    for x0 in (2, 9):
        fill_rect(img, x0, 5, x0 + 3, 8, pal["base"])
        fill_rect(img, x0, 9, x0 + 4, 11, pal["base"])
        put(img, x0 + 1, 5, pal["hot"])
        fill_rect(img, x0, 11, x0 + 4, 11, pal["bright"])
    return outline(img)


# Charm char maps. Roles: O outline/dark, B base, L bright, H hot, S silver, W white.
CHARM_ROWS = [
    "................",
    "................",
    "..OOOOOOOOOOOO..",
    "..OBBBBBBBBBBO..",
    "..OBLBBBBBBBBO..",
    "..OBBBBHHBBBBO..",
    "..OBBBHHHHBBBO..",
    "..OBBBHHHHBBBO..",
    "...OBBBHHBBBO...",
    "...OBBBBBBBBO...",
    "....OBBBBBBO....",
    ".....OBBBBO.....",
    "......OBBO......",
    ".......OO.......",
    "................",
    "................",
]
TOTEM_ROWS = [
    "................",
    "....OOOOOOOO....",
    "....OBBLLBBO....",
    "....OBHOOHBO....",
    "....OBBBBBBO....",
    "....OOBHHBOO....",
    "....OBBBBBBO....",
    "....OLBBBBLO....",
    "....OBHBBHBO....",
    "....OBBLLBBO....",
    "....OOBBBBOO....",
    "....OBBBBBBO....",
    "....OBLBBLBO....",
    "....OOOOOOOO....",
    "................",
    "................",
]
RING_ROWS = [
    "................",
    "................",
    ".....SHHS.......",
    ".....SHHS.......",
    "....OOBBOO......",
    "...OBBOOBBO.....",
    "...OBO..OBO.....",
    "..OBO....OBO....",
    "..OBO....OBO....",
    "...OBO..OBO.....",
    "...OBBOOBBO.....",
    "....OOBBOO......",
    "................",
    "................",
    "................",
    "................",
]
AMULET_ROWS = [
    "................",
    ".S............S.",
    "..S..........S..",
    "...S........S...",
    "....S......S....",
    ".....S....S.....",
    "......S..S......",
    ".......OO.......",
    "......OBBO......",
    ".....OBHHBO.....",
    ".....OBHHBO.....",
    "......OBBO......",
    ".......OO.......",
    "................",
    "................",
    "................",
]
TALISMAN_ROWS = [
    "................",
    ".....OOOOO......",
    "....OBBBBBO.....",
    "...OBBLLLBBO....",
    "..OBBLBBBLBBO...",
    "..OBLBHHHBLBO...",
    "..OBLBHBHBLBO...",
    "..OBLBHHHBLBO...",
    "..OBBLBBBLBBO...",
    "...OBBLLLBBO....",
    "....OBBBBBO.....",
    ".....OOOOO......",
    "................",
    "................",
    "................",
    "................",
]
MEDALLION_ROWS = [
    "................",
    "....SSS.SSS.....",
    "....SSS.SSS.....",
    "....SSSSSSS.....",
    ".....SSSSS......",
    "......OOO.......",
    ".....OBBBO......",
    "....OBLLLBO.....",
    "...OBLHHHLBO....",
    "...OBLHBHLBO....",
    "...OBLHHHLBO....",
    "....OBLLLBO.....",
    ".....OBBBO......",
    "......OOO.......",
    "................",
    "................",
]
CHARM_MAPS = {"charm": CHARM_ROWS, "totem": TOTEM_ROWS, "ring": RING_ROWS,
              "amulet": AMULET_ROWS, "talisman": TALISMAN_ROWS,
              "medallion": MEDALLION_ROWS}

GEAR_TEXTURES = {"ingot": tex_ingot, "sword": tex_sword, "pickaxe": tex_pickaxe,
                 "axe": tex_axe, "shovel": tex_shovel, "hoe": tex_hoe,
                 "helmet": tex_helmet, "chestplate": tex_chestplate,
                 "leggings": tex_leggings, "boots": tex_boots}


def charm_palette(pal) -> dict:
    return {"O": pal["dark"], "B": pal["base"], "L": pal["bright"], "H": pal["hot"],
            "S": SILVER, "W": WHITE}


def emit_item_textures() -> None:
    item_dir = ASSETS / "textures" / "item"
    item_dir.mkdir(parents=True, exist_ok=True)
    for t in TIERS:
        for suffix, fn in GEAR_TEXTURES.items():
            fn(t.pal).save(item_dir / f"{t.tid}_{suffix}.png")
        for suffix, rows in CHARM_MAPS.items():
            paint(rows, charm_palette(t.pal)).save(item_dir / f"{t.tid}_{suffix}.png")


# ---------------------------------------------------------------------------
# Worn-armor layer textures: vanilla iron layers recolored per tier (infernium_gen).
# ---------------------------------------------------------------------------

def lerp(a, b, t):
    return tuple(int(round(a[i] + (b[i] - a[i]) * t)) for i in range(3))


def recolor_iron_layer(png_bytes: bytes, pal) -> Image.Image:
    src = Image.open(__import__("io").BytesIO(png_bytes)).convert("RGBA")
    out = Image.new("RGBA", src.size, (0, 0, 0, 0))
    for y in range(src.size[1]):
        for x in range(src.size[0]):
            r, g, b, a = src.getpixel((x, y))
            if a == 0:
                continue
            # Vanilla iron layer's opaque luminance spans ~0.70..1.0; stretch that band
            # across the tier's dark -> base -> hot ramp (same math as infernium_gen).
            lum = (r + g + b) / (3 * 255)
            t = min(1.0, max(0.0, (lum - 0.70) / 0.30))
            if t < 0.4:
                color = lerp(pal["dark"], pal["base"], t / 0.4)
            else:
                color = lerp(pal["base"], pal["hot"], (t - 0.4) / 0.6)
            out.putpixel((x, y), (*color, a))
    return out


def emit_armor_layers() -> None:
    if not CLIENT_JAR.is_file():
        print(f"titanforge_gen: WARNING client jar not found at {CLIENT_JAR}; "
              "skipping worn-armor layer textures", file=sys.stderr)
        return
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        for layer in ("humanoid", "humanoid_leggings"):
            data = jar.read(f"assets/minecraft/textures/entity/equipment/{layer}/iron.png")
            for t in TIERS:
                dest = ASSETS / "textures" / "entity" / "equipment" / layer / f"{t.tid}.png"
                dest.parent.mkdir(parents=True, exist_ok=True)
                recolor_iron_layer(data, t.pal).save(dest)


# ---------------------------------------------------------------------------
# Java codegen (genlib.java_feature_class / java_handbook_class; literal ids only so
# devtools/audit_assets.py check (f) and devtools/check_handbook.py can parse them).
# ---------------------------------------------------------------------------

def field_of(item_id: str) -> str:
    return item_id.upper()


def materials_src() -> str:
    """The static-final materials block inserted verbatim before init()."""
    lines = []
    for t in TIERS:
        tid = t.tid.upper()
        lines += [
            f"\t/** Repair tag, backed by data/copper_inferno/tags/item/{t.tid}_repair.json. */",
            f"\tpublic static final TagKey<Item> REPAIRS_{tid} =",
            f"\t\t\tTagKey.of(RegistryKeys.ITEM, CopperInferno.id(\"{t.tid}_repair\"));",
            "",
            f"\t/** Custom worn-armor asset: assets/copper_inferno/equipment/{t.tid}.json. */",
            f"\tpublic static final RegistryKey<EquipmentAsset> {tid}_EQUIPMENT_ASSET_ID =",
            f"\t\t\tRegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, CopperInferno.id(\"{t.tid}\"));",
            "",
            "\t/**",
            "\t * ToolMaterial is a record: (incorrectBlocksForDrops, durability, speed,",
            "\t * attackDamageBonus, enchantmentValue, repairItems) \u2014 verified via javap,",
            "\t * mirroring InferniumFeature.",
            "\t */",
            f"\tpublic static final ToolMaterial {tid}_TOOL_MATERIAL = new ToolMaterial(",
            f"\t\t\tBlockTags.{t.incorrect}, {t.dur}, {t.speed}, {t.dmg}, {t.ench}, REPAIRS_{tid});",
            "",
            "\t/**",
            "\t * ArmorMaterial is a record: (durability, defense map, enchantmentValue, equipSound,",
            "\t * toughness, knockbackResistance, repairIngredient, assetId) \u2014 verified via javap,",
            "\t * mirroring InferniumFeature (defenseMap order: boots, leggings, chestplate,",
            "\t * helmet, body).",
            "\t */",
            f"\tpublic static final ArmorMaterial {tid}_ARMOR_MATERIAL = new ArmorMaterial(",
            f"\t\t\t{t.adur}, defenseMap({', '.join(str(d) for d in t.defense)}), {t.aench},",
            f"\t\t\tSoundEvents.{t.sound}, {t.tough}, {t.kb}, REPAIRS_{tid}, {tid}_EQUIPMENT_ASSET_ID);",
            "",
        ]
    lines += [
        "\tprivate static Map<EquipmentType, Integer> defenseMap(int boots, int leggings, int chestplate, int helmet, int body) {",
        "\t\tMap<EquipmentType, Integer> map = new EnumMap<>(EquipmentType.class);",
        "\t\tmap.put(EquipmentType.BOOTS, boots);",
        "\t\tmap.put(EquipmentType.LEGGINGS, leggings);",
        "\t\tmap.put(EquipmentType.CHESTPLATE, chestplate);",
        "\t\tmap.put(EquipmentType.HELMET, helmet);",
        "\t\tmap.put(EquipmentType.BODY, body);",
        "\t\treturn map;",
        "\t}",
    ]
    return "\n".join(lines)


def item_tuples() -> list:
    """(FIELD, id, factory_expr, settings_expr) for all 96 registrations, in tab order.
    Every settings_expr constructs a FRESH Item.Settings (never shared)."""
    out = []
    for t in TIERS:
        tid = t.tid.upper()
        fp = ".fireproof()" if t.fireproof else ""
        tool_settings = {
            "sword": ("Item::new", f"new Item.Settings().sword({tid}_TOOL_MATERIAL, 3.0F, -2.4F){fp}"),
            "pickaxe": ("Item::new", f"new Item.Settings().pickaxe({tid}_TOOL_MATERIAL, 1.0F, -2.8F){fp}"),
            "axe": (f"s -> new AxeItem({tid}_TOOL_MATERIAL, 5.0F, -3.0F, s)", f"new Item.Settings(){fp}"),
            "shovel": (f"s -> new ShovelItem({tid}_TOOL_MATERIAL, 1.5F, -3.0F, s)", f"new Item.Settings(){fp}"),
            "hoe": (f"s -> new HoeItem({tid}_TOOL_MATERIAL, -3.0F, 0.0F, s)", f"new Item.Settings(){fp}"),
        }
        ingot = f"{t.tid}_ingot"
        out.append((field_of(ingot), ingot, "Item::new", f"new Item.Settings().maxCount(64){fp}"))
        for p, _pen, _pde in TOOL_PIECES:
            factory, settings = tool_settings[p]
            out.append((field_of(f"{t.tid}_{p}"), f"{t.tid}_{p}", factory, settings))
        for p, _pen, _pde, et in ARMOR_PIECES:
            out.append((field_of(f"{t.tid}_{p}"), f"{t.tid}_{p}", "Item::new",
                        f"new Item.Settings().armor({tid}_ARMOR_MATERIAL, EquipmentType.{et}){fp}"))
        for p, _pen, _pde, _c, _fe, _fd in CHARM_PIECES:
            out.append((field_of(f"{t.tid}_{p}"), f"{t.tid}_{p}", "Item::new",
                        f"new Item.Settings().maxCount(16){fp}"))
    return out


def emit_java() -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)

    items = item_tuples()
    feature_doc = [
        "TitanForge gear: 96 items across six new tool &amp; armor tiers in progression order",
        "(ember_steel, pyrite, slagsteel, cinderforge, molten_titan, infernal_alloy). Each tier",
        "ships sword/pickaxe/axe/shovel/hoe + helmet/chestplate/leggings/boots, one alloy ingot",
        "and six charm trinkets (charm, totem, ring, amulet, talisman, medallion).",
        "",
        "<p>Tool and armor materials mirror {@code InferniumFeature}: ToolMaterial /",
        "ArmorMaterial record constructors verified via javap; each tier repairs from its own",
        "{@code copper_inferno:&lt;tier&gt;_repair} item tag and renders worn armor through its",
        "own equipment asset {@code assets/copper_inferno/equipment/&lt;tier&gt;.json}. Molten",
        "Titan and Infernal Alloy gear is fireproof.",
        "",
        "<p>Recipes live in {@code data/copper_inferno/recipe/titanforge/}: vanilla tool/armor",
        "shapes from tier ingots + sticks, shapeless alloy chains (each tier's ingot consumes",
        "the previous tier's), charm shapes, and smithing_transform upgrade chains between",
        "consecutive tiers using the shipped infernium upgrade smithing template (data-side",
        "dependency only). Assets are generated by {@code devtools/gen/titanforge_gen.py};",
        "handbook pages are registered by {@link TitanForgeHandbook}.",
    ]
    feature_src = genlib.java_feature_class(
        "titanforge", "TitanForgeFeature", feature_doc,
        items=items,
        settings_methods=materials_src(),
        tabs=[("MAIN_KEY", [f for f, *_ in items])],
        extra_imports=(
            "java.util.EnumMap",
            "java.util.Map",
            "net.minecraft.item.AxeItem",
            "net.minecraft.item.HoeItem",
            "net.minecraft.item.ShovelItem",
            "net.minecraft.item.ToolMaterial",
            "net.minecraft.item.equipment.ArmorMaterial",
            "net.minecraft.item.equipment.EquipmentAsset",
            "net.minecraft.item.equipment.EquipmentAssetKeys",
            "net.minecraft.item.equipment.EquipmentType",
            "net.minecraft.registry.RegistryKey",
            "net.minecraft.registry.RegistryKeys",
            "net.minecraft.registry.tag.BlockTags",
            "net.minecraft.registry.tag.TagKey",
            "net.minecraft.sound.SoundEvents",
            "net.sonic0810.copperinferno.CopperInferno",
        ),
        handbook_class="TitanForgeHandbook",
    )
    # genlib adds an AbstractBlock import whenever settings_methods is non-empty; our
    # verbatim block is pure item material code, so strip the unused import.
    feature_src = feature_src.replace("import net.minecraft.block.AbstractBlock;\n", "")
    (FEATURE_DIR / "TitanForgeFeature.java").write_text(feature_src, encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the TitanForge gear tiers: one \"gear\" overview plus one grid",
        "entry for every recipe JSON under {@code data/copper_inferno/recipe/titanforge/}",
        "(alloy ingots, tools, armor, charms and smithing upgrades). Entry texts and grids",
        "mirror the recipe JSONs emitted by {@code devtools/gen/titanforge_gen.py};",
        "{@code devtools/check_handbook.py} parses the inline {@code new HandbookEntry(...)}",
        "literals positionally, so keep them inline.",
    ]
    handbook_src = genlib.java_handbook_class("titanforge", "TitanForgeHandbook",
                                              handbook_doc, HANDBOOK)
    (FEATURE_DIR / "TitanForgeHandbook.java").write_text(handbook_src, encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/titanforge.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks(recipe_count: int) -> None:
    lines = ["# titanforge feature hooks (format: devtools/hooks/README.md)", "",
             "[init]",
             "# No Java init-order constraint: the smithing recipes reference",
             "# copper_inferno:infernium_upgrade_smithing_template by id only (data-side).",
             "import net.sonic0810.copperinferno.feature.titanforge.TitanForgeFeature;",
             "\t\tTitanForgeFeature.init();", "",
             "[recipe-dir]", "titanforge", "",
             "[counts]",
             f"items: {len(ALL_IDS)}",
             f"recipes: {recipe_count}",
             f"handbook-entries: {len(HANDBOOK)}", ""]
    path = ROOT / "devtools" / "hooks" / "titanforge.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Output manifest (for determinism checks / the integrator).
# ---------------------------------------------------------------------------

def output_files() -> list:
    out = []
    for i in ALL_IDS:
        out += [ASSETS / "items" / f"{i}.json",
                ASSETS / "models" / "item" / f"{i}.json",
                ASSETS / "textures" / "item" / f"{i}.png"]
    for t in TIERS:
        out.append(ASSETS / "equipment" / f"{t.tid}.json")
        out.append(DATA / "tags" / "item" / f"{t.tid}_repair.json")
        for layer in ("humanoid", "humanoid_leggings"):
            out.append(ASSETS / "textures" / "entity" / "equipment" / layer / f"{t.tid}.png")
    out += sorted(RECIPES.glob("*.json"))
    out += [ASSETS / "lang" / "fragments" / "titanforge.json",
            ASSETS / "lang" / "fragments_de" / "titanforge.json",
            ROOT / "devtools" / "tagfrag" / "titanforge.json",
            FEATURE_DIR / "TitanForgeFeature.java",
            FEATURE_DIR / "TitanForgeHandbook.java",
            ROOT / "devtools" / "hooks" / "titanforge.txt"]
    return [str(p) for p in out]


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    emit_item_assets()
    emit_equipment_assets()
    emit_repair_tags()
    emit_tagfrag()

    # Overview page first, then one entry per recipe (collected by emit_recipes).
    HANDBOOK.append((
        "gear", "titanforge_overview", f"{NS}:infernal_alloy_sword", None, None, None, 0,
        "TitanForge gear spans six forged tiers \u2014 Ember Steel, Pyrite, Slagsteel, "
        "Cinderforge, Molten Titan and Infernal Alloy \u2014 each with a full "
        "sword/pickaxe/axe/shovel/hoe tool set, helmet/chestplate/leggings/boots armor and "
        "six charm trinkets. Higher tiers are reached by alloying the previous tier's ingot "
        "or by smithing-table upgrades with the Infernium Upgrade Smithing Template; Molten "
        "Titan and Infernal Alloy gear never burns.",
        "TitanForge-Ausr\u00fcstung umfasst sechs geschmiedete Stufen \u2014 Glutstahl, "
        "Pyrit, Schlackenstahl, Zinderschmiede, Schmelztitan und H\u00f6llenlegierung \u2014 "
        "jede mit komplettem Werkzeugsatz (Schwert/Spitzhacke/Axt/Schaufel/Hacke), "
        "R\u00fcstung (Helm/Brustpanzer/Beinschutz/Stiefel) und sechs Anh\u00e4ngern. "
        "H\u00f6here Stufen entstehen durch Legieren des vorherigen Barrens oder per "
        "Schmiedetisch-Aufwertung mit der Infernium-Aufwertungs-Schmiedevorlage; "
        "Schmelztitan- und H\u00f6llenlegierungs-Ausr\u00fcstung verbrennt nie."))
    recipe_count = emit_recipes()

    lang_en = {f"item.{NS}.{i}": EN[i] for i in ALL_IDS}
    lang_de = {f"item.{NS}.{i}": DE[i] for i in ALL_IDS}
    genlib.lang_fragments(ASSETS, "titanforge", lang_en, lang_de)

    emit_item_textures()
    emit_armor_layers()
    emit_java()
    emit_hooks(recipe_count)

    assert len(ALL_IDS) == 96, f"expected 96 item ids, got {len(ALL_IDS)}"
    assert len(set(ALL_IDS)) == 96, "duplicate item ids emitted"
    expected_keys = {f"item.{NS}.{i}" for i in ALL_IDS}
    assert set(lang_en) == set(lang_de) == expected_keys, "EN/DE/id lang key sets differ"
    assert recipe_count == 141, f"expected 141 recipes, got {recipe_count}"
    assert len(HANDBOOK) == 142, f"expected 142 handbook entries, got {len(HANDBOOK)}"
    print(f"titanforge_gen: assets generated for {len(ALL_IDS)} items "
          f"({recipe_count} recipes, {len(HANDBOOK)} handbook entries).")


if __name__ == "__main__":
    main()
