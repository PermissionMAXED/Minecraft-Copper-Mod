#!/usr/bin/env python3
"""Asset generator for the COPPER INFERNO v4.1 "Enchants" feature (WP enchants).

Emits, directly into src/main/resources (plus the tag fragment under devtools/tagfrag):
  - 6 DATA-DRIVEN enchantments (data/copper_inferno/enchantment/<id>.json, the 1.21+
    datapack format — no code registration). Every JSON is a structural copy of a vanilla
    1.21.9 enchantment (extract with e.g.
    `unzip -p ~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar
     data/minecraft/enchantment/fire_aspect.json`), using ONLY vanilla effect components:
      ember_edge       swords   post_attack ignite               (fire_aspect, weaker)
      verdigris_guard  armor    damage_protection vs fire        (fire_protection, weaker)
      fizz_burst       boots    post_attack explode knockback    (wind_burst, tuned down)
      coppersoul       tools    item_damage remove_binomial      (unbreaking, flatter curve)
      slag_breaker     pickaxes attributes mining_efficiency     (efficiency, linear curve)
      doomtouch        swords   post_attack apply_mob_effect     (bane_of_arthropods style,
                                weakness)
  - tag fragment devtools/tagfrag/enchants.json: the 6 ids joining the vanilla acquisition
    tags enchantment/in_enchanting_table + enchantment/non_treasure + enchantment/tradeable
    (merged into data/minecraft/tags/enchantment/*.json by devtools/merge_tags.py — same
    layout vanilla uses)
  - lang fragments assets/copper_inferno/lang/fragments/enchants.json (EN) and
    fragments_de/enchants.json (real German), keys enchantment.copper_inferno.<id>

Idempotent: pure JSON with fixed content; re-running writes identical bytes.
"""

import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from lib_gen import NS, write_json  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
ENCHANT_DIR = RES / "data" / NS / "enchantment"
TAGFRAG = ROOT / "devtools" / "tagfrag" / "enchants.json"


def desc(ident: str) -> dict:
    return {"translate": f"enchantment.{NS}.{ident}"}


# ---------------------------------------------------------------------------
# The 6 enchantments. Structures are exact copies of the named vanilla 1.21.9
# enchantment JSONs with tuned numbers (modest balance: max_level 2-3, costs
# copied from the comparable vanilla enchant).
# ---------------------------------------------------------------------------

# fire_aspect structure, one level weaker (3s + 3s/level vs vanilla 4s + 4s/level).
EMBER_EDGE = {
    "anvil_cost": 4,
    "description": desc("ember_edge"),
    "effects": {
        "minecraft:post_attack": [
            {
                "affected": "victim",
                "effect": {
                    "type": "minecraft:ignite",
                    "duration": {
                        "type": "minecraft:linear",
                        "base": 3.0,
                        "per_level_above_first": 3.0,
                    },
                },
                "enchanted": "attacker",
                "requirements": {
                    "condition": "minecraft:damage_source_properties",
                    "predicate": {"is_direct": True},
                },
            }
        ]
    },
    "max_cost": {"base": 60, "per_level_above_first": 20},
    "max_level": 2,
    "min_cost": {"base": 10, "per_level_above_first": 20},
    "primary_items": "#minecraft:enchantable/sword",
    "slots": ["mainhand"],
    "supported_items": "#minecraft:enchantable/sharp_weapon",
    "weight": 2,
}

# fire_protection structure, weaker (1 point + 10% shorter burns per level vs
# vanilla's 2 points + 15%); same exclusive set + costs, max_level 3 (vanilla 4).
VERDIGRIS_GUARD = {
    "anvil_cost": 2,
    "description": desc("verdigris_guard"),
    "effects": {
        "minecraft:attributes": [
            {
                "amount": {
                    "type": "minecraft:linear",
                    "base": -0.1,
                    "per_level_above_first": -0.1,
                },
                "attribute": "minecraft:burning_time",
                "id": f"{NS}:enchantment.verdigris_guard",
                "operation": "add_multiplied_base",
            }
        ],
        "minecraft:damage_protection": [
            {
                "effect": {
                    "type": "minecraft:add",
                    "value": {
                        "type": "minecraft:linear",
                        "base": 1.0,
                        "per_level_above_first": 1.0,
                    },
                },
                "requirements": {
                    "condition": "minecraft:all_of",
                    "terms": [
                        {
                            "condition": "minecraft:damage_source_properties",
                            "predicate": {
                                "tags": [
                                    {"expected": True, "id": "minecraft:is_fire"},
                                    {"expected": False, "id": "minecraft:bypasses_invulnerability"},
                                ]
                            },
                        }
                    ],
                },
            }
        ],
    },
    "exclusive_set": "#minecraft:exclusive_set/armor",
    "max_cost": {"base": 18, "per_level_above_first": 8},
    "max_level": 3,
    "min_cost": {"base": 10, "per_level_above_first": 8},
    "slots": ["armor"],
    "supported_items": "#minecraft:enchantable/armor",
    "weight": 5,
}

# wind_burst explode structure on boots (small fizzy pop instead of a mace slam:
# radius 2.5, gentle knockback); is_direct requirement from fire_aspect; costs
# copied from knockback (the comparable enchant).
FIZZ_BURST = {
    "anvil_cost": 2,
    "description": desc("fizz_burst"),
    "effects": {
        "minecraft:post_attack": [
            {
                "affected": "attacker",
                "effect": {
                    "type": "minecraft:explode",
                    "block_interaction": "trigger",
                    "immune_blocks": "#minecraft:blocks_wind_charge_explosions",
                    "knockback_multiplier": {
                        "type": "minecraft:linear",
                        "base": 0.8,
                        "per_level_above_first": 0.4,
                    },
                    "large_particle": {"type": "minecraft:gust_emitter_large"},
                    "radius": 2.5,
                    "small_particle": {"type": "minecraft:gust_emitter_small"},
                    "sound": "minecraft:entity.wind_charge.wind_burst",
                },
                "enchanted": "attacker",
                "requirements": {
                    "condition": "minecraft:damage_source_properties",
                    "predicate": {"is_direct": True},
                },
            }
        ]
    },
    "max_cost": {"base": 55, "per_level_above_first": 20},
    "max_level": 2,
    "min_cost": {"base": 5, "per_level_above_first": 20},
    "slots": ["feet"],
    "supported_items": "#minecraft:enchantable/foot_armor",
    "weight": 5,
}

# unbreaking structure (remove_binomial fraction) with a flatter, weaker curve:
# 1/(4+2L-2) ignore chance = 25% / 33% / 37.5% (unbreaking: 50% / 66% / 75%).
COPPERSOUL = {
    "anvil_cost": 2,
    "description": desc("coppersoul"),
    "effects": {
        "minecraft:item_damage": [
            {
                "effect": {
                    "type": "minecraft:remove_binomial",
                    "chance": {
                        "type": "minecraft:fraction",
                        "denominator": {
                            "type": "minecraft:linear",
                            "base": 4.0,
                            "per_level_above_first": 2.0,
                        },
                        "numerator": {
                            "type": "minecraft:linear",
                            "base": 1.0,
                            "per_level_above_first": 1.0,
                        },
                    },
                }
            }
        ]
    },
    "max_cost": {"base": 55, "per_level_above_first": 8},
    "max_level": 3,
    "min_cost": {"base": 5, "per_level_above_first": 8},
    "slots": ["mainhand"],
    "supported_items": "#minecraft:enchantable/mining",
    "weight": 5,
}

# efficiency structure with a linear (not levels_squared) curve: +2 mining
# efficiency per level (efficiency V reaches 26; slag_breaker III caps at 6).
SLAG_BREAKER = {
    "anvil_cost": 1,
    "description": desc("slag_breaker"),
    "effects": {
        "minecraft:attributes": [
            {
                "amount": {
                    "type": "minecraft:linear",
                    "base": 2.0,
                    "per_level_above_first": 2.0,
                },
                "attribute": "minecraft:mining_efficiency",
                "id": f"{NS}:enchantment.slag_breaker",
                "operation": "add_value",
            }
        ]
    },
    "max_cost": {"base": 51, "per_level_above_first": 10},
    "max_level": 3,
    "min_cost": {"base": 1, "per_level_above_first": 10},
    "slots": ["mainhand"],
    "supported_items": "#minecraft:enchantable/mining",
    "weight": 10,
}

# bane_of_arthropods' post_attack apply_mob_effect structure, applying Weakness I
# to ANY victim (no entity-type gate); fire_aspect costs (comparable rarity).
DOOMTOUCH = {
    "anvil_cost": 4,
    "description": desc("doomtouch"),
    "effects": {
        "minecraft:post_attack": [
            {
                "affected": "victim",
                "effect": {
                    "type": "minecraft:apply_mob_effect",
                    "max_amplifier": 0.0,
                    "max_duration": {
                        "type": "minecraft:linear",
                        "base": 3.0,
                        "per_level_above_first": 2.0,
                    },
                    "min_amplifier": 0.0,
                    "min_duration": 1.5,
                    "to_apply": "minecraft:weakness",
                },
                "enchanted": "attacker",
                "requirements": {
                    "condition": "minecraft:damage_source_properties",
                    "predicate": {"is_direct": True},
                },
            }
        ]
    },
    "max_cost": {"base": 60, "per_level_above_first": 20},
    "max_level": 2,
    "min_cost": {"base": 10, "per_level_above_first": 20},
    "primary_items": "#minecraft:enchantable/sword",
    "slots": ["mainhand"],
    "supported_items": "#minecraft:enchantable/sharp_weapon",
    "weight": 2,
}

ENCHANTS = {
    "ember_edge": EMBER_EDGE,
    "verdigris_guard": VERDIGRIS_GUARD,
    "fizz_burst": FIZZ_BURST,
    "coppersoul": COPPERSOUL,
    "slag_breaker": SLAG_BREAKER,
    "doomtouch": DOOMTOUCH,
}

# ---------------------------------------------------------------------------
# Acquisition tags (merged into data/minecraft/tags/enchantment/*.json by
# devtools/merge_tags.py — the layout vanilla itself uses for these tags).
# ---------------------------------------------------------------------------

ALL_IDS = [f"{NS}:{ident}" for ident in ENCHANTS]

TAGS = {
    "enchantment/in_enchanting_table": ALL_IDS,
    "enchantment/non_treasure": ALL_IDS,
    "enchantment/tradeable": ALL_IDS,
}

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

LANG_EN = {
    "enchantment.copper_inferno.ember_edge": "Ember Edge",
    "enchantment.copper_inferno.verdigris_guard": "Verdigris Guard",
    "enchantment.copper_inferno.fizz_burst": "Fizz Burst",
    "enchantment.copper_inferno.coppersoul": "Coppersoul",
    "enchantment.copper_inferno.slag_breaker": "Slag Breaker",
    "enchantment.copper_inferno.doomtouch": "Doomtouch",
}

LANG_DE = {
    "enchantment.copper_inferno.ember_edge": "Glutklinge",
    "enchantment.copper_inferno.verdigris_guard": "Gr\u00fcnspanschutz",
    "enchantment.copper_inferno.fizz_burst": "Sprudelsto\u00df",
    "enchantment.copper_inferno.coppersoul": "Kupferseele",
    "enchantment.copper_inferno.slag_breaker": "Schlackenbrecher",
    "enchantment.copper_inferno.doomtouch": "Verh\u00e4ngnisgriff",
}


def main():
    for ident, obj in ENCHANTS.items():
        write_json(ENCHANT_DIR / f"{ident}.json", obj)

    write_json(ASSETS / "lang" / "fragments" / "enchants.json", LANG_EN)
    write_json(ASSETS / "lang" / "fragments_de" / "enchants.json", LANG_DE)
    write_json(TAGFRAG, TAGS)

    print(f"enchants_gen: wrote {len(ENCHANTS)} enchantment JSONs, "
          "2 lang fragments, 1 tag fragment")


if __name__ == "__main__":
    main()
