#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "archfiends" feature (10 summoned-only
ultra bosses, 50 items).

Ten new archfiend bosses, each subclassing a vanilla mob (2 each of GhastEntity,
HoglinEntity, EvokerEntity, PiglinBruteEntity, WitherSkeletonEntity), each with a
core.boss.BossBarHolder boss bar, SpawnGroup.MISC registration (summoned-only, no natural
spawns), high MAX_HEALTH + SCALE + FOLLOW_RANGE attributes and one distinct verified
mechanic (aura pulse, phase change, summon adds, regen pulse) mirroring the proven
TheOxidizerEntity / InfernoTitanEntity tick/phase/boss-bar patterns.

Per boss: 1 summon sigil (ArchfiendSummonItem, ring recipe), 2 drops, 1 EPIC trophy and
1 spawn egg = 5 items x 10 bosses = 50 items.

Idempotent: running it any number of times produces byte-identical output. Emits by
DEFAULT (no flags), mirroring devtools/gen/titanforge_gen.py:
  - items/<id>.json model-definitions + models/item/<id>.json (genlib emitters)
  - 16x16 item textures (Pillow, deterministic pixel art, distinct palette per boss)
  - entity loot tables data/copper_inferno/loot_table/entities/<boss>.json
    (infernoboss_gen schema incl. "random_sequence"; 3 pools: drop1 2-4, drop2 1-2,
    trophy 1)
  - ring recipes data/copper_inferno/recipe/archfiends/*.json (vanilla ingredients only;
    every recipe's result is this feature's own summon item id)
  - lang fragments EN + DE (assets/copper_inferno/lang/fragments{,_de}/archfiends.json)
  - generated Java (src/main + src/client, feature package "archfiends"):
    ArchfiendsFeature.java (literal registrations, MAIN_KEY creative callback,
    ArchfiendsHandbook.register()), ArchfiendSummonItem.java, the 10 boss entity
    subclasses, ArchfiendsFeatureClient.java (vanilla renderers) and
    ArchfiendsHandbook.java (genlib.java_handbook_class; "bosses" category EN+DE)
  - devtools/hooks/archfiends.txt (integration hook file)

1.21.9 API notes (every signature verified with javap against the loom minecraft
common/clientonly jars, per AGENTS.md):
  GhastEntity/HoglinEntity/EvokerEntity/PiglinBruteEntity/WitherSkeletonEntity ctors all
  take (EntityType<? extends X>, World); attribute creators createGhastAttributes /
  createHoglinAttributes / createEvokerAttributes / createPiglinBruteAttributes /
  AbstractSkeletonEntity.createAbstractSkeletonAttributes all exist and are public.
  Vanilla EntityType dims copied from the EntityType bytecode: ghast =
  makeFireImmune().dimensions(4.0f, 4.0f).eyeHeight(2.6f); hoglin =
  dimensions(1.3964844f, 1.4f); evoker = dimensions(0.6f, 1.95f); piglin_brute =
  dimensions(0.6f, 1.95f).eyeHeight(1.79f); wither_skeleton =
  makeFireImmune().dimensions(0.7f, 2.4f).eyeHeight(2.1f).
  Renderers: GhastEntityRenderer/HoglinEntityRenderer/EvokerEntityRenderer/
  WitherSkeletonEntityRenderer are Context-only; the piglin brute reuses
  PiglinEntityRenderer(Context, EntityModelLayer, EntityModelLayer, EquipmentModelData,
  EquipmentModelData) with the EntityModelLayers.PIGLIN_BRUTE(_EQUIPMENT) layers exactly
  as the vanilla EntityRendererFactories bytecode does.
  HoglinEntity.setImmuneToZombification(boolean) and
  AbstractPiglinEntity.setImmuneToZombification(boolean) are public;
  HoglinEntity.interactMob is PUBLIC (unlike IronGolemEntity's protected).
  EntityType.create(World, SpawnReason) + SpawnReason.MOB_SUMMONED spawn the vanilla
  phase-two minions; Entity.setOnFireForTicks(int), LivingEntity.heal(float),
  DustParticleEffect(int, float) and ServerWorld.spawnParticles(...) drive the auras.
"""

import sys
from pathlib import Path
from random import Random

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, write_json
from PIL import Image

import math

RECIPES = DATA / "recipe" / "archfiends"
FEATURE_DIR = (ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno"
               / "feature" / "archfiends")
CLIENT_DIR = (ROOT / "src" / "client" / "java" / "net" / "sonic0810" / "copperinferno"
              / "feature" / "archfiends" / "client")
PKG = "net.sonic0810.copperinferno.feature.archfiends"

# ---------------------------------------------------------------------------
# Boss specs. base: vanilla superclass (Yarn name, all in net.minecraft.entity.mob).
# attr_creator: the verified public static attribute builder. dims: builder chain copied
# from the vanilla EntityType registration bytecode (tracking range extended to 10 like
# the infernoboss template). Mechanics (all mirroring TheOxidizer/InfernoTitan patterns):
#   aura   = dict(interval, range, ticks, effects=[(EFFECT, amp)...], fire=None|ticks,
#                 self_speed=bool, dust=0xRRGGBB|None, particle="FLAME"|None)
#   heal   = dict(interval, amount, dust)
#   enrage = flat MOVEMENT_SPEED add below 50% health (TheOxidizer.enrage pattern)
#   melee  = flat ATTACK_DAMAGE add below 50% health (health-gated titan boost)
#   phase  = dict(minion=(TYPE_FIELD, EntityClass)|None, count, immune=bool, equip=ITEM,
#                 resistance=ticks, halve_aura=bool, speed_boost=None|f, melee_boost=None|f)
#            one-shot at 50% health, persisted via write/readCustomData (titan pattern).
# ---------------------------------------------------------------------------

BOSSES = [
    dict(
        bid="dread_ghast_sovereign", cls="DreadGhastSovereignEntity",
        base="GhastEntity", attr_creator="GhastEntity.createGhastAttributes()",
        attrs=[("MAX_HEALTH", "320.0"), ("SCALE", "1.6"), ("FOLLOW_RANGE", "100.0")],
        dims=[".makeFireImmune()", ".dimensions(4.0f, 4.0f)", ".eyeHeight(2.6f)",
              ".maxTrackingRange(10)"],
        bar=("PURPLE", "NOTCHED_10"),
        aura=dict(interval=120, range=10.0, ticks=100,
                  effects=[("DARKNESS", 0), ("SLOWNESS", 0)], dust=0x7B5CC7),
        phase=dict(resistance=200, halve_aura=True),
        summon="dread_sovereign_sigil", drop1="dread_ghast_tear",
        drop2="sovereign_veil_shard", trophy="dread_sovereign_crown",
        ring=("minecraft:ghast_tear", "minecraft:soul_sand", "minecraft:crying_obsidian"),
        en="Dread Ghast Sovereign", de="Schreckens-Ghast-F\u00fcrst",
        de_acc="den Schreckens-Ghast-F\u00fcrsten",
        pal={"dark": (0x2E, 0x2A, 0x3C), "base": (0x8C, 0x86, 0xB0),
             "bright": (0xB9, 0xB4, 0xD8), "hot": (0xE8, 0xE4, 0xFF)},
        doc=["Archfiend 1 \"Dread Ghast Sovereign\": a colossal spectral ghast (MAX_HEALTH 320,",
             "SCALE 1.6). Keeps the inherited vanilla ghast fireball AI; every 120 ticks its dread",
             "veil applies Darkness + Slowness to survival players it can see within 10 blocks.",
             "Below 50% health it enters a one-shot persisted frenzy phase: 200 ticks of",
             "Resistance and the veil pulses twice as often."],
    ),
    dict(
        bid="cinder_ghast_matriarch", cls="CinderGhastMatriarchEntity",
        base="GhastEntity", attr_creator="GhastEntity.createGhastAttributes()",
        attrs=[("MAX_HEALTH", "300.0"), ("SCALE", "1.8"), ("FOLLOW_RANGE", "100.0")],
        dims=[".makeFireImmune()", ".dimensions(4.0f, 4.0f)", ".eyeHeight(2.6f)",
              ".maxTrackingRange(10)"],
        bar=("RED", "PROGRESS"),
        aura=dict(interval=140, range=8.0, ticks=60, effects=[], fire=60,
                  particle="FLAME"),
        phase=dict(minion=("BLAZE", "BlazeEntity"), count=2, resistance=100),
        summon="cinder_matriarch_sigil", drop1="cinder_tearstone",
        drop2="matriarch_ember_sac", trophy="matriarch_cinder_diadem",
        ring=("minecraft:ghast_tear", "minecraft:magma_cream", "minecraft:magma_block"),
        en="Cinder Ghast Matriarch", de="Zinder-Ghast-Matriarchin",
        de_acc="die Zinder-Ghast-Matriarchin",
        pal={"dark": (0x4A, 0x20, 0x18), "base": (0xC8, 0x54, 0x3A),
             "bright": (0xE8, 0x87, 0x5A), "hot": (0xFF, 0xD8, 0xA0)},
        doc=["Archfiend 2 \"Cinder Ghast Matriarch\": a burning ghast (MAX_HEALTH 300, SCALE 1.8).",
             "Keeps the inherited vanilla ghast fireball AI; every 140 ticks her cinder rain",
             "ignites survival players she can see within 8 blocks (60 fire ticks). Below 50%",
             "health she enters a one-shot persisted phase: 2 vanilla blaze minions",
             "(EntityType.BLAZE.create + MOB_SUMMONED, verified) and 100 ticks of Resistance."],
    ),
    dict(
        bid="molten_hoglin_tyrant", cls="MoltenHoglinTyrantEntity",
        base="HoglinEntity", attr_creator="HoglinEntity.createHoglinAttributes()",
        attrs=[("MAX_HEALTH", "320.0"), ("ATTACK_DAMAGE", "16.0"), ("SCALE", "2.2"),
               ("KNOCKBACK_RESISTANCE", "1.0"), ("FOLLOW_RANGE", "48.0")],
        dims=[".dimensions(1.3964844f, 1.4f)", ".maxTrackingRange(10)"],
        bar=("RED", "NOTCHED_6"),
        aura=dict(interval=100, range=5.0, ticks=60, effects=[("SLOWNESS", 0)],
                  fire=60, dust=0xE25822),
        enrage=0.05,
        immune_zombification=True, block_interact=True,
        summon="molten_tyrant_sigil", drop1="molten_tusk",
        drop2="tyrant_seared_hide", trophy="molten_tyrant_idol",
        ring=("minecraft:crimson_fungus", "minecraft:magma_cream", "minecraft:shroomlight"),
        en="Molten Hoglin Tyrant", de="Schmelz-Hoglin-Tyrann",
        de_acc="den Schmelz-Hoglin-Tyrannen",
        pal={"dark": (0x3A, 0x1A, 0x10), "base": (0xC8, 0x4E, 0x1E),
             "bright": (0xFF, 0x7A, 0x2F), "hot": (0xFF, 0xD8, 0x66)},
        doc=["Archfiend 3 \"Molten Hoglin Tyrant\": a magma-clad hoglin (MAX_HEALTH 320,",
             "ATTACK_DAMAGE 16, SCALE 2.2), zombification-proof via the public",
             "HoglinEntity.setImmuneToZombification (verified via javap). Brain AI is inherited",
             "untouched; every 100 ticks its molten stomp ignites (60 fire ticks) and slows",
             "survival players it can see within 5 blocks; below 50% health it enrages with the",
             "TheOxidizer speed-modifier pattern."],
    ),
    dict(
        bid="ashen_hoglin_gorefiend", cls="AshenHoglinGorefiendEntity",
        base="HoglinEntity", attr_creator="HoglinEntity.createHoglinAttributes()",
        attrs=[("MAX_HEALTH", "340.0"), ("ATTACK_DAMAGE", "14.0"), ("SCALE", "2.4"),
               ("KNOCKBACK_RESISTANCE", "1.0"), ("FOLLOW_RANGE", "48.0")],
        dims=[".dimensions(1.3964844f, 1.4f)", ".maxTrackingRange(10)"],
        bar=("WHITE", "NOTCHED_10"),
        heal=dict(interval=160, amount="8.0f", dust=0x9A9A94),
        phase=dict(minion=("HOGLIN", "HoglinEntity"), count=2, immune=True,
                   resistance=100),
        immune_zombification=True, block_interact=True,
        summon="ashen_gorefiend_sigil", drop1="gorefiend_bristle",
        drop2="ashen_gore_heart", trophy="gorefiend_ash_totem",
        ring=("minecraft:bone", "minecraft:crimson_fungus", "minecraft:bone_block"),
        en="Ashen Hoglin Gorefiend", de="Aschen-Hoglin-Schl\u00e4chter",
        de_acc="den Aschen-Hoglin-Schl\u00e4chter",
        pal={"dark": (0x3C, 0x38, 0x34), "base": (0x8A, 0x80, 0x78),
             "bright": (0xB4, 0xAC, 0xA2), "hot": (0xD8, 0x44, 0x2E)},
        doc=["Archfiend 4 \"Ashen Hoglin Gorefiend\": an ash-crusted hoglin (MAX_HEALTH 340,",
             "SCALE 2.4), zombification-proof. Brain AI is inherited untouched; every 160 ticks",
             "it knits wounds shut (heal 8) in an ash burst. Below 50% health it enters a",
             "one-shot persisted phase: 2 vanilla hoglin minions (each made",
             "zombification-immune) and 100 ticks of Resistance."],
    ),
    dict(
        bid="ash_evoker_archon", cls="AshEvokerArchonEntity",
        base="EvokerEntity", attr_creator="EvokerEntity.createEvokerAttributes()",
        attrs=[("MAX_HEALTH", "240.0"), ("SCALE", "1.8"), ("FOLLOW_RANGE", "48.0")],
        dims=[".dimensions(0.6f, 1.95f)", ".maxTrackingRange(10)"],
        bar=("WHITE", "PROGRESS"),
        aura=dict(interval=140, range=10.0, ticks=100,
                  effects=[("MINING_FATIGUE", 0), ("WEAKNESS", 0)], dust=0xB9B6AC),
        enrage=0.03,
        summon="ash_archon_sigil", drop1="archon_ash_tome",
        drop2="ash_rune_shard", trophy="archon_cinder_mitre",
        ring=("minecraft:coal", "minecraft:emerald", "minecraft:book"),
        en="Ash Evoker Archon", de="Asche-Magier-Archon",
        de_acc="den Asche-Magier-Archon",
        pal={"dark": (0x4A, 0x46, 0x3E), "base": (0x9C, 0x96, 0x8A),
             "bright": (0xC6, 0xC0, 0xB4), "hot": (0xF0, 0xED, 0xE4)},
        doc=["Archfiend 5 \"Ash Evoker Archon\": an illager archfiend (MAX_HEALTH 240, SCALE 1.8)",
             "keeping the full inherited evoker spell AI (fangs + vex summons). Every 140 ticks",
             "its ash pall saps survival players it can see within 10 blocks with Mining Fatigue",
             "+ Weakness; below 50% health it enrages with the TheOxidizer speed-modifier",
             "pattern."],
    ),
    dict(
        bid="soot_evoker_highlord", cls="SootEvokerHighlordEntity",
        base="EvokerEntity", attr_creator="EvokerEntity.createEvokerAttributes()",
        attrs=[("MAX_HEALTH", "260.0"), ("SCALE", "2.0"), ("FOLLOW_RANGE", "48.0")],
        dims=[".dimensions(0.6f, 1.95f)", ".maxTrackingRange(10)"],
        bar=("PURPLE", "NOTCHED_12"),
        aura=dict(interval=160, range=8.0, ticks=60,
                  effects=[("BLINDNESS", 0), ("SLOWNESS", 0)], dust=0x2E2431),
        phase=dict(resistance=200, speed_boost="0.04"),
        summon="soot_highlord_sigil", drop1="highlord_soot_pearl",
        drop2="soot_grimoire_page", trophy="highlord_soot_sceptre",
        ring=("minecraft:coal_block", "minecraft:emerald", "minecraft:lapis_block"),
        en="Soot Evoker Highlord", de="Ru\u00df-Magier-Hochf\u00fcrst",
        de_acc="den Ru\u00df-Magier-Hochf\u00fcrsten",
        pal={"dark": (0x1E, 0x18, 0x22), "base": (0x4A, 0x3C, 0x56),
             "bright": (0x7A, 0x5C, 0x96), "hot": (0xB5, 0x8A, 0xE0)},
        doc=["Archfiend 6 \"Soot Evoker Highlord\": a soot-wreathed illager (MAX_HEALTH 260,",
             "SCALE 2.0) keeping the full inherited evoker spell AI. Every 160 ticks its soot",
             "veil blinds + slows survival players it can see within 8 blocks. Below 50% health",
             "it enters a one-shot persisted phase: 200 ticks of Resistance plus a permanent",
             "speed modifier (re-applied after reload with the hasModifier guard)."],
    ),
    dict(
        bid="cinder_brute_warlord", cls="CinderBruteWarlordEntity",
        base="PiglinBruteEntity",
        attr_creator="PiglinBruteEntity.createPiglinBruteAttributes()",
        attrs=[("MAX_HEALTH", "300.0"), ("ATTACK_DAMAGE", "18.0"), ("SCALE", "2.0"),
               ("KNOCKBACK_RESISTANCE", "0.8"), ("FOLLOW_RANGE", "48.0")],
        dims=[".dimensions(0.6f, 1.95f)", ".eyeHeight(1.79f)", ".maxTrackingRange(10)"],
        bar=("YELLOW", "NOTCHED_6"),
        aura=dict(interval=100, range=6.0, ticks=80, effects=[("WEAKNESS", 0)],
                  self_speed=True, dust=0xF6C12B),
        melee=4.0,
        immune_zombification=True, equip="GOLDEN_AXE",
        summon="cinder_warlord_sigil", drop1="warlord_cinder_axehead",
        drop2="warlord_gilded_tusk", trophy="warlord_war_crown",
        ring=("minecraft:gold_ingot", "minecraft:blaze_powder",
              "minecraft:gilded_blackstone"),
        en="Cinder Brute Warlord", de="Zinder-Barbar-Kriegsherr",
        de_acc="den Zinder-Barbar-Kriegsherrn",
        pal={"dark": (0x5C, 0x3A, 0x10), "base": (0xC6, 0x8A, 0x2C),
             "bright": (0xE8, 0xB8, 0x4A), "hot": (0xFF, 0xE8, 0x96)},
        doc=["Archfiend 7 \"Cinder Brute Warlord\": a piglin brute archfiend (MAX_HEALTH 300,",
             "ATTACK_DAMAGE 18, SCALE 2.0), zombification-proof via the public",
             "AbstractPiglinEntity.setImmuneToZombification and armed with a golden axe (both",
             "verified via javap). Brain AI is inherited untouched; every 100 ticks its war cry",
             "grants itself Speed and applies Weakness to survival players it can see within 6",
             "blocks; below 50% health its axe arm gains a one-shot +4 attack-damage modifier."],
    ),
    dict(
        bid="gilded_brute_executioner", cls="GildedBruteExecutionerEntity",
        base="PiglinBruteEntity",
        attr_creator="PiglinBruteEntity.createPiglinBruteAttributes()",
        attrs=[("MAX_HEALTH", "320.0"), ("ATTACK_DAMAGE", "16.0"), ("SCALE", "2.2"),
               ("KNOCKBACK_RESISTANCE", "0.8"), ("FOLLOW_RANGE", "48.0")],
        dims=[".dimensions(0.6f, 1.95f)", ".eyeHeight(1.79f)", ".maxTrackingRange(10)"],
        bar=("YELLOW", "PROGRESS"),
        phase=dict(minion=("PIGLIN_BRUTE", "PiglinBruteEntity"), count=2, immune=True,
                   equip="GOLDEN_AXE", resistance=100, speed_boost="0.05"),
        immune_zombification=True, equip="GOLDEN_AXE",
        summon="gilded_executioner_sigil", drop1="executioner_gilded_plate",
        drop2="executioner_chain_link", trophy="executioner_gold_visage",
        ring=("minecraft:gold_block", "minecraft:gold_ingot", "minecraft:golden_axe"),
        en="Gilded Brute Executioner", de="Vergoldeter Barbar-Scharfrichter",
        de_acc="den Vergoldeten Barbar-Scharfrichter",
        pal={"dark": (0x2B, 0x22, 0x16), "base": (0xA0, 0x78, 0x18),
             "bright": (0xD4, 0xA6, 0x2C), "hot": (0xFF, 0xE0, 0x66)},
        doc=["Archfiend 8 \"Gilded Brute Executioner\": a gold-plated piglin brute (MAX_HEALTH",
             "320, SCALE 2.2), zombification-proof and armed with a golden axe. Brain AI is",
             "inherited untouched. Below 50% health it enters a one-shot persisted phase:",
             "2 vanilla piglin brute minions (each zombification-immune and axe-armed),",
             "100 ticks of Resistance and a permanent speed modifier (hasModifier guard)."],
    ),
    dict(
        bid="slag_wither_monarch", cls="SlagWitherMonarchEntity",
        base="WitherSkeletonEntity",
        attr_creator="AbstractSkeletonEntity.createAbstractSkeletonAttributes()",
        attrs=[("MAX_HEALTH", "280.0"), ("ATTACK_DAMAGE", "12.0"), ("SCALE", "2.2"),
               ("KNOCKBACK_RESISTANCE", "0.6"), ("FOLLOW_RANGE", "48.0")],
        dims=[".makeFireImmune()", ".dimensions(0.7f, 2.4f)", ".eyeHeight(2.1f)",
              ".maxTrackingRange(10)"],
        bar=("GREEN", "NOTCHED_10"),
        aura=dict(interval=120, range=6.0, ticks=60,
                  effects=[("WITHER", 0), ("SLOWNESS", 0)], dust=0x39443A),
        enrage=0.05,
        equip="STONE_SWORD",
        summon="slag_monarch_sigil", drop1="monarch_slag_rib",
        drop2="withered_slag_chunk", trophy="monarch_wither_crown",
        ring=("minecraft:bone", "minecraft:coal", "minecraft:wither_skeleton_skull"),
        en="Slag Wither Monarch", de="Schlacken-Wither-Monarch",
        de_acc="den Schlacken-Wither-Monarchen",
        pal={"dark": (0x23, 0x2A, 0x24), "base": (0x4A, 0x5A, 0x4C),
             "bright": (0x71, 0x8C, 0x74), "hot": (0xA8, 0xC8, 0xA8)},
        doc=["Archfiend 9 \"Slag Wither Monarch\": a slag-armored wither skeleton (MAX_HEALTH",
             "280, SCALE 2.2) armed with a stone sword (equipStack + zero drop chance, both",
             "verified via javap; AbstractSkeletonEntity.onEquipStack picks the melee goal).",
             "Every 120 ticks its withering aura applies Wither + Slowness to survival players",
             "it can see within 6 blocks; below 50% health it enrages with the TheOxidizer",
             "speed-modifier pattern."],
    ),
    dict(
        bid="blight_wither_emperor", cls="BlightWitherEmperorEntity",
        base="WitherSkeletonEntity",
        attr_creator="AbstractSkeletonEntity.createAbstractSkeletonAttributes()",
        attrs=[("MAX_HEALTH", "300.0"), ("ATTACK_DAMAGE", "14.0"), ("SCALE", "2.4"),
               ("KNOCKBACK_RESISTANCE", "0.6"), ("FOLLOW_RANGE", "48.0")],
        dims=[".makeFireImmune()", ".dimensions(0.7f, 2.4f)", ".eyeHeight(2.1f)",
              ".maxTrackingRange(10)"],
        bar=("PURPLE", "PROGRESS"),
        phase=dict(minion=("WITHER_SKELETON", "WitherSkeletonEntity"), count=2,
                   equip="STONE_SWORD", resistance=100, melee_boost="4.0"),
        equip="IRON_SWORD",
        summon="blight_emperor_sigil", drop1="blight_bone_shard",
        drop2="emperor_blight_marrow", trophy="emperor_blight_diadem",
        ring=("minecraft:coal_block", "minecraft:bone", "minecraft:wither_skeleton_skull"),
        en="Blight Wither Emperor", de="Seuchen-Wither-Imperator",
        de_acc="den Seuchen-Wither-Imperator",
        pal={"dark": (0x1A, 0x20, 0x14), "base": (0x3E, 0x52, 0x30),
             "bright": (0x6A, 0x8A, 0x4A), "hot": (0xA8, 0xD8, 0x70)},
        doc=["Archfiend 10 \"Blight Wither Emperor\": an iron-sworded wither skeleton",
             "(MAX_HEALTH 300, SCALE 2.4). Below 50% health it enters a one-shot persisted",
             "phase (InfernoTitan pattern): 2 vanilla wither skeleton minions (each armed with",
             "a stone sword), 100 ticks of Resistance and a permanent +4 attack-damage modifier",
             "(re-applied after reload with the hasModifier guard)."],
    ),
]

assert len(BOSSES) == 10


def item_ids_of(b) -> list:
    return [b["summon"], b["drop1"], b["drop2"], b["trophy"], f"{b['bid']}_spawn_egg"]


ALL_ITEM_IDS = [i for b in BOSSES for i in item_ids_of(b)]

# ---------------------------------------------------------------------------
# Display names (EN + real German).
# ---------------------------------------------------------------------------

ITEM_EN = {
    "dread_sovereign_sigil": "Dread Sovereign Sigil",
    "dread_ghast_tear": "Dread Ghast Tear",
    "sovereign_veil_shard": "Sovereign Veil Shard",
    "dread_sovereign_crown": "Dread Sovereign Crown",
    "cinder_matriarch_sigil": "Cinder Matriarch Sigil",
    "cinder_tearstone": "Cinder Tearstone",
    "matriarch_ember_sac": "Matriarch Ember Sac",
    "matriarch_cinder_diadem": "Matriarch Cinder Diadem",
    "molten_tyrant_sigil": "Molten Tyrant Sigil",
    "molten_tusk": "Molten Tusk",
    "tyrant_seared_hide": "Seared Tyrant Hide",
    "molten_tyrant_idol": "Molten Tyrant Idol",
    "ashen_gorefiend_sigil": "Ashen Gorefiend Sigil",
    "gorefiend_bristle": "Gorefiend Bristle",
    "ashen_gore_heart": "Ashen Gore Heart",
    "gorefiend_ash_totem": "Gorefiend Ash Totem",
    "ash_archon_sigil": "Ash Archon Sigil",
    "archon_ash_tome": "Archon Ash Tome",
    "ash_rune_shard": "Ash Rune Shard",
    "archon_cinder_mitre": "Archon Cinder Mitre",
    "soot_highlord_sigil": "Soot Highlord Sigil",
    "highlord_soot_pearl": "Highlord Soot Pearl",
    "soot_grimoire_page": "Soot Grimoire Page",
    "highlord_soot_sceptre": "Highlord Soot Sceptre",
    "cinder_warlord_sigil": "Cinder Warlord Sigil",
    "warlord_cinder_axehead": "Warlord Cinder Axehead",
    "warlord_gilded_tusk": "Gilded Warlord Tusk",
    "warlord_war_crown": "Warlord War Crown",
    "gilded_executioner_sigil": "Gilded Executioner Sigil",
    "executioner_gilded_plate": "Gilded Executioner Plate",
    "executioner_chain_link": "Executioner Chain Link",
    "executioner_gold_visage": "Executioner Gold Visage",
    "slag_monarch_sigil": "Slag Monarch Sigil",
    "monarch_slag_rib": "Monarch Slag Rib",
    "withered_slag_chunk": "Withered Slag Chunk",
    "monarch_wither_crown": "Monarch Wither Crown",
    "blight_emperor_sigil": "Blight Emperor Sigil",
    "blight_bone_shard": "Blight Bone Shard",
    "emperor_blight_marrow": "Emperor Blight Marrow",
    "emperor_blight_diadem": "Emperor Blight Diadem",
}

ITEM_DE = {
    "dread_sovereign_sigil": "Schreckensf\u00fcrsten-Siegel",
    "dread_ghast_tear": "Schreckens-Ghast-Tr\u00e4ne",
    "sovereign_veil_shard": "Schleierscherbe des F\u00fcrsten",
    "dread_sovereign_crown": "Krone des Schreckensf\u00fcrsten",
    "cinder_matriarch_sigil": "Zinder-Matriarchin-Siegel",
    "cinder_tearstone": "Zinder-Tr\u00e4nenstein",
    "matriarch_ember_sac": "Glutbeutel der Matriarchin",
    "matriarch_cinder_diadem": "Zinderdiadem der Matriarchin",
    "molten_tyrant_sigil": "Schmelztyrannen-Siegel",
    "molten_tusk": "Schmelzhauer",
    "tyrant_seared_hide": "Versengte Tyrannenhaut",
    "molten_tyrant_idol": "Schmelztyrannen-G\u00f6tze",
    "ashen_gorefiend_sigil": "Aschenschl\u00e4chter-Siegel",
    "gorefiend_bristle": "Schl\u00e4chterborste",
    "ashen_gore_heart": "Aschenblutherz",
    "gorefiend_ash_totem": "Aschentotem des Schl\u00e4chters",
    "ash_archon_sigil": "Aschenarchon-Siegel",
    "archon_ash_tome": "Aschenfoliant des Archons",
    "ash_rune_shard": "Aschenrunenscherbe",
    "archon_cinder_mitre": "Zindermitra des Archons",
    "soot_highlord_sigil": "Ru\u00dfhochf\u00fcrsten-Siegel",
    "highlord_soot_pearl": "Ru\u00dfperle des Hochf\u00fcrsten",
    "soot_grimoire_page": "Ru\u00dfgrimoire-Seite",
    "highlord_soot_sceptre": "Ru\u00dfzepter des Hochf\u00fcrsten",
    "cinder_warlord_sigil": "Zinderkriegsherren-Siegel",
    "warlord_cinder_axehead": "Zinderaxtklinge des Kriegsherrn",
    "warlord_gilded_tusk": "Vergoldeter Kriegsherrenhauer",
    "warlord_war_crown": "Kriegskrone des Kriegsherrn",
    "gilded_executioner_sigil": "Scharfrichter-Siegel",
    "executioner_gilded_plate": "Vergoldete Scharfrichterplatte",
    "executioner_chain_link": "Scharfrichter-Kettenglied",
    "executioner_gold_visage": "Goldantlitz des Scharfrichters",
    "slag_monarch_sigil": "Schlackenmonarchen-Siegel",
    "monarch_slag_rib": "Schlackenrippe des Monarchen",
    "withered_slag_chunk": "Verdorrter Schlackenbrocken",
    "monarch_wither_crown": "Witherkrone des Monarchen",
    "blight_emperor_sigil": "Seuchenimperator-Siegel",
    "blight_bone_shard": "Seuchenknochensplitter",
    "emperor_blight_marrow": "Seuchenmark des Imperators",
    "emperor_blight_diadem": "Seuchendiadem des Imperators",
}

for b in BOSSES:
    ITEM_EN[f"{b['bid']}_spawn_egg"] = f"{b['en']} Spawn Egg"
    ITEM_DE[f"{b['bid']}_spawn_egg"] = f"{b['de']}-Spawn-Ei"

# EN/DE display names of the vanilla ring-recipe ingredients (handbook texts).
ING_EN = {
    "minecraft:ghast_tear": "Ghast Tears", "minecraft:soul_sand": "Soul Sand",
    "minecraft:crying_obsidian": "Crying Obsidian", "minecraft:magma_cream": "Magma Cream",
    "minecraft:magma_block": "Magma Block", "minecraft:crimson_fungus": "Crimson Fungus",
    "minecraft:shroomlight": "Shroomlight", "minecraft:bone": "Bones",
    "minecraft:bone_block": "Bone Block", "minecraft:coal": "Coal",
    "minecraft:emerald": "Emeralds", "minecraft:book": "Book",
    "minecraft:coal_block": "Coal Blocks", "minecraft:lapis_block": "Block of Lapis Lazuli",
    "minecraft:gold_ingot": "Gold Ingots", "minecraft:blaze_powder": "Blaze Powder",
    "minecraft:gilded_blackstone": "Gilded Blackstone", "minecraft:gold_block": "Gold Blocks",
    "minecraft:golden_axe": "Golden Axe",
    "minecraft:wither_skeleton_skull": "Wither Skeleton Skull",
}
ING_DE = {
    "minecraft:ghast_tear": "Ghast-Tr\u00e4nen", "minecraft:soul_sand": "Seelensand",
    "minecraft:crying_obsidian": "Weinender Obsidian",
    "minecraft:magma_cream": "Magmacreme", "minecraft:magma_block": "Magmablock",
    "minecraft:crimson_fungus": "Karmesinpilze", "minecraft:shroomlight": "Pilzlicht",
    "minecraft:bone": "Knochen", "minecraft:bone_block": "Knochenblock",
    "minecraft:coal": "Kohle", "minecraft:emerald": "Smaragde",
    "minecraft:book": "Buch", "minecraft:coal_block": "Kohlebl\u00f6cke",
    "minecraft:lapis_block": "Lapislazuliblock", "minecraft:gold_ingot": "Goldbarren",
    "minecraft:blaze_powder": "Lohenstaub",
    "minecraft:gilded_blackstone": "Vergoldeter Schwarzstein",
    "minecraft:gold_block": "Goldbl\u00f6cke", "minecraft:golden_axe": "Goldaxt",
    "minecraft:wither_skeleton_skull": "Witherskelettsch\u00e4del",
}

# Boss handbook body texts (EN, DE) keyed by boss id.
HB_TEXT = {
    "dread_ghast_sovereign": (
        "Dread Ghast Sovereign - a colossal spectral ghast archfiend. Summon: use a Dread "
        "Sovereign Sigil (never on peaceful). Its dread veil shrouds every player it can "
        "see within 10 blocks in Darkness and Slowness; below half health it enters a "
        "frenzy, venting the veil twice as often and hardening with Resistance. Drops 2-4 "
        "Dread Ghast Tears, 1-2 Sovereign Veil Shards and the Dread Sovereign Crown.",
        "Schreckens-Ghast-F\u00fcrst - ein kolossaler Geister-Ghast-Erzd\u00e4mon. "
        "Beschw\u00f6rung: ein Schreckensf\u00fcrsten-Siegel benutzen (nie auf Friedlich). "
        "Sein Schreckensschleier h\u00fcllt jeden sichtbaren Spieler im Umkreis von 10 "
        "Bl\u00f6cken in Dunkelheit und Langsamkeit; unter halber Gesundheit verf\u00e4llt "
        "er in Raserei, st\u00f6\u00dft den Schleier doppelt so oft aus und h\u00e4rtet "
        "sich mit Resistenz. L\u00e4sst 2-4 Schreckens-Ghast-Tr\u00e4nen, 1-2 "
        "Schleierscherben des F\u00fcrsten und die Krone des Schreckensf\u00fcrsten fallen."),
    "cinder_ghast_matriarch": (
        "Cinder Ghast Matriarch - a burning ghast archfiend. Summon: use a Cinder "
        "Matriarch Sigil. Every few seconds she rains cinders, igniting every player she "
        "can see within 8 blocks; below half health she calls 2 blaze minions and hardens "
        "with Resistance. Drops 2-4 Cinder Tearstones, 1-2 Matriarch Ember Sacs and the "
        "Matriarch Cinder Diadem.",
        "Zinder-Ghast-Matriarchin - ein brennender Ghast-Erzd\u00e4mon. Beschw\u00f6rung: "
        "ein Zinder-Matriarchin-Siegel benutzen. Alle paar Sekunden regnet sie Zinder und "
        "entz\u00fcndet jeden sichtbaren Spieler im Umkreis von 8 Bl\u00f6cken; unter "
        "halber Gesundheit ruft sie 2 Lohen-Diener und h\u00e4rtet sich mit Resistenz. "
        "L\u00e4sst 2-4 Zinder-Tr\u00e4nensteine, 1-2 Glutbeutel der Matriarchin und das "
        "Zinderdiadem der Matriarchin fallen."),
    "molten_hoglin_tyrant": (
        "Molten Hoglin Tyrant - a magma-clad hoglin archfiend that never zombifies. "
        "Summon: use a Molten Tyrant Sigil. Its molten stomp ignites and slows every "
        "player it can see within 5 blocks; below half health it enrages and speeds up. "
        "Drops 2-4 Molten Tusks, 1-2 Seared Tyrant Hides and the Molten Tyrant Idol.",
        "Schmelz-Hoglin-Tyrann - ein magmagepanzerter Hoglin-Erzd\u00e4mon, der nie "
        "zombifiziert. Beschw\u00f6rung: ein Schmelztyrannen-Siegel benutzen. Sein "
        "Schmelzstampfer entz\u00fcndet und verlangsamt jeden sichtbaren Spieler im "
        "Umkreis von 5 Bl\u00f6cken; unter halber Gesundheit wird er rasend und schneller. "
        "L\u00e4sst 2-4 Schmelzhauer, 1-2 Versengte Tyrannenh\u00e4ute und den "
        "Schmelztyrannen-G\u00f6tzen fallen."),
    "ashen_hoglin_gorefiend": (
        "Ashen Hoglin Gorefiend - an ash-crusted hoglin archfiend. Summon: use an Ashen "
        "Gorefiend Sigil. Every few seconds it knits its wounds shut in a burst of ash; "
        "below half health it calls 2 hoglin minions and hardens with Resistance. Drops "
        "2-4 Gorefiend Bristles, 1-2 Ashen Gore Hearts and the Gorefiend Ash Totem.",
        "Aschen-Hoglin-Schl\u00e4chter - ein aschverkrusteter Hoglin-Erzd\u00e4mon. "
        "Beschw\u00f6rung: ein Aschenschl\u00e4chter-Siegel benutzen. Alle paar Sekunden "
        "schlie\u00dft er seine Wunden in einem Aschesto\u00df; unter halber Gesundheit "
        "ruft er 2 Hoglin-Diener und h\u00e4rtet sich mit Resistenz. L\u00e4sst 2-4 "
        "Schl\u00e4chterborsten, 1-2 Aschenblutherzen und das Aschentotem des "
        "Schl\u00e4chters fallen."),
    "ash_evoker_archon": (
        "Ash Evoker Archon - a fang-conjuring illager archfiend. Summon: use an Ash "
        "Archon Sigil. On top of its inherited evoker fangs and vexes, its ash pall saps "
        "every player it can see within 10 blocks with Mining Fatigue and Weakness; below "
        "half health it enrages and speeds up. Drops 2-4 Archon Ash Tomes, 1-2 Ash Rune "
        "Shards and the Archon Cinder Mitre.",
        "Asche-Magier-Archon - ein z\u00e4hnebeschw\u00f6render Illager-Erzd\u00e4mon. "
        "Beschw\u00f6rung: ein Aschenarchon-Siegel benutzen. Zus\u00e4tzlich zu seinen "
        "ererbten Magier-Z\u00e4hnen und Plagegeistern laugt sein Ascheschleier jeden "
        "sichtbaren Spieler im Umkreis von 10 Bl\u00f6cken mit Abbaul\u00e4hmung und "
        "Schw\u00e4che aus; unter halber Gesundheit wird er rasend und schneller. "
        "L\u00e4sst 2-4 Aschenfolianten des Archons, 1-2 Aschenrunenscherben und die "
        "Zindermitra des Archons fallen."),
    "soot_evoker_highlord": (
        "Soot Evoker Highlord - a soot-wreathed illager archfiend. Summon: use a Soot "
        "Highlord Sigil. Its soot veil blinds and slows every player it can see within 8 "
        "blocks; below half health it hardens with Resistance and quickens permanently. "
        "Drops 2-4 Highlord Soot Pearls, 1-2 Soot Grimoire Pages and the Highlord Soot "
        "Sceptre.",
        "Ru\u00df-Magier-Hochf\u00fcrst - ein ru\u00dfumwobener Illager-Erzd\u00e4mon. "
        "Beschw\u00f6rung: ein Ru\u00dfhochf\u00fcrsten-Siegel benutzen. Sein "
        "Ru\u00dfschleier blendet und verlangsamt jeden sichtbaren Spieler im Umkreis von "
        "8 Bl\u00f6cken; unter halber Gesundheit h\u00e4rtet er sich mit Resistenz und "
        "wird dauerhaft schneller. L\u00e4sst 2-4 Ru\u00dfperlen des Hochf\u00fcrsten, "
        "1-2 Ru\u00dfgrimoire-Seiten und das Ru\u00dfzepter des Hochf\u00fcrsten fallen."),
    "cinder_brute_warlord": (
        "Cinder Brute Warlord - a piglin brute archfiend that never zombifies. Summon: "
        "use a Cinder Warlord Sigil. Its war cry hastens itself and weakens every player "
        "it can see within 6 blocks; below half health its axe arm gains +4 attack "
        "damage. Drops 2-4 Warlord Cinder Axeheads, 1-2 Gilded Warlord Tusks and the "
        "Warlord War Crown.",
        "Zinder-Barbar-Kriegsherr - ein Piglin-Barbar-Erzd\u00e4mon, der nie "
        "zombifiziert. Beschw\u00f6rung: ein Zinderkriegsherren-Siegel benutzen. Sein "
        "Kriegsschrei beschleunigt ihn selbst und schw\u00e4cht jeden sichtbaren Spieler "
        "im Umkreis von 6 Bl\u00f6cken; unter halber Gesundheit erh\u00e4lt sein Axtarm "
        "+4 Angriffsschaden. L\u00e4sst 2-4 Zinderaxtklingen des Kriegsherrn, 1-2 "
        "Vergoldete Kriegsherrenhauer und die Kriegskrone des Kriegsherrn fallen."),
    "gilded_brute_executioner": (
        "Gilded Brute Executioner - a gold-plated piglin brute archfiend that never "
        "zombifies. Summon: use a Gilded Executioner Sigil. Below half health it calls 2 "
        "axe-wielding piglin brute minions, hardens with Resistance and quickens "
        "permanently. Drops 2-4 Gilded Executioner Plates, 1-2 Executioner Chain Links "
        "and the Executioner Gold Visage.",
        "Vergoldeter Barbar-Scharfrichter - ein goldgepanzerter Piglin-Barbar-"
        "Erzd\u00e4mon, der nie zombifiziert. Beschw\u00f6rung: ein Scharfrichter-Siegel "
        "benutzen. Unter halber Gesundheit ruft er 2 axtschwingende Piglin-Barbar-Diener, "
        "h\u00e4rtet sich mit Resistenz und wird dauerhaft schneller. L\u00e4sst 2-4 "
        "Vergoldete Scharfrichterplatten, 1-2 Scharfrichter-Kettenglieder und das "
        "Goldantlitz des Scharfrichters fallen."),
    "slag_wither_monarch": (
        "Slag Wither Monarch - a slag-armored wither skeleton archfiend wielding a stone "
        "sword. Summon: use a Slag Monarch Sigil. Its withering aura decays and slows "
        "every player it can see within 6 blocks; below half health it enrages and "
        "speeds up. Drops 2-4 Monarch Slag Ribs, 1-2 Withered Slag Chunks and the "
        "Monarch Wither Crown.",
        "Schlacken-Wither-Monarch - ein schlackengepanzerter Witherskelett-Erzd\u00e4mon "
        "mit Steinschwert. Beschw\u00f6rung: ein Schlackenmonarchen-Siegel benutzen. "
        "Seine Wither-Aura zersetzt und verlangsamt jeden sichtbaren Spieler im Umkreis "
        "von 6 Bl\u00f6cken; unter halber Gesundheit wird er rasend und schneller. "
        "L\u00e4sst 2-4 Schlackenrippen des Monarchen, 1-2 Verdorrte Schlackenbrocken "
        "und die Witherkrone des Monarchen fallen."),
    "blight_wither_emperor": (
        "Blight Wither Emperor - an iron-sworded wither skeleton archfiend. Summon: use "
        "a Blight Emperor Sigil. Below half health it calls 2 sword-bearing wither "
        "skeleton minions, hardens with Resistance and its blade gains +4 attack damage. "
        "Drops 2-4 Blight Bone Shards, 1-2 Emperor Blight Marrows and the Emperor Blight "
        "Diadem.",
        "Seuchen-Wither-Imperator - ein Witherskelett-Erzd\u00e4mon mit Eisenschwert. "
        "Beschw\u00f6rung: ein Seuchenimperator-Siegel benutzen. Unter halber Gesundheit "
        "ruft er 2 schwerttragende Witherskelett-Diener, h\u00e4rtet sich mit Resistenz "
        "und seine Klinge erh\u00e4lt +4 Angriffsschaden. L\u00e4sst 2-4 "
        "Seuchenknochensplitter, 1-2 Seuchenmark des Imperators und das Seuchendiadem "
        "des Imperators fallen."),
}


# ---------------------------------------------------------------------------
# Lang fragments (merged into en_us.json / de_de.json by devtools/merge_lang.py).
# ---------------------------------------------------------------------------

def build_lang():
    en = {
        "message.copper_inferno.archfiend_sigil.peaceful":
            "The archfiends will not answer on peaceful difficulty.",
        "message.copper_inferno.archfiend_sigil.blocked":
            "There is no room for the archfiend to rise here.",
    }
    de = {
        "message.copper_inferno.archfiend_sigil.peaceful":
            "Die Erzd\u00e4monen antworten nicht auf friedlichem Schwierigkeitsgrad.",
        "message.copper_inferno.archfiend_sigil.blocked":
            "Hier ist kein Platz, damit sich der Erzd\u00e4mon erheben kann.",
    }
    for b in BOSSES:
        en[f"entity.{NS}.{b['bid']}"] = b["en"]
        de[f"entity.{NS}.{b['bid']}"] = b["de"]
    for iid in ALL_ITEM_IDS:
        en[f"item.{NS}.{iid}"] = ITEM_EN[iid]
        de[f"item.{NS}.{iid}"] = ITEM_DE[iid]
    return en, de


# ---------------------------------------------------------------------------
# Entity loot tables (infernoboss_gen schema incl. "random_sequence"; 3 pools).
# ---------------------------------------------------------------------------

def emit_loot_tables() -> None:
    for b in BOSSES:
        drops = [(f"{NS}:{b['drop1']}", 2.0, 4.0),
                 (f"{NS}:{b['drop2']}", 1.0, 2.0),
                 (f"{NS}:{b['trophy']}", 1.0, 1.0)]
        write_json(DATA / "loot_table" / "entities" / f"{b['bid']}.json", {
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
            "random_sequence": f"{NS}:entities/{b['bid']}",
        })


# ---------------------------------------------------------------------------
# Ring recipes (vanilla crafting_shaped format via genlib; corners A, edges B,
# center C -> "ABA"/"BCB"/"ABA"; every result is this feature's own summon item).
# ---------------------------------------------------------------------------

def emit_recipes() -> int:
    count = 0
    for b in BOSSES:
        corner, edge, center = b["ring"]
        genlib.emit_shaped(RECIPES, b["summon"],
                           {"A": corner, "B": edge, "C": center},
                           ["ABA", "BCB", "ABA"], f"{NS}:{b['summon']}", 1,
                           category="misc")
        count += 1
    return count


# ---------------------------------------------------------------------------
# Item defs + models (1.21.9 two-file contract; genlib emitters).
# ---------------------------------------------------------------------------

def emit_item_assets() -> None:
    for iid in ALL_ITEM_IDS:
        genlib.emit_item_def(ASSETS, iid)
        genlib.emit_item_model(ASSETS, iid)


# ---------------------------------------------------------------------------
# Textures (16x16 Pillow pixel art; per-texture fixed seed via genlib.rng_for so
# output is byte-identical across runs). Painters are parameterized by the boss
# palette; shapes adapted from the proven infernoboss_gen painters.
# ---------------------------------------------------------------------------

CHARCOAL_LIGHT = (0x4A, 0x36, 0x3A)
CHARCOAL = (0x3D, 0x2C, 0x2E)
CHARCOAL_DARK = (0x2B, 0x22, 0x26)
GLINT = (0xF2, 0xF5, 0xEE)


def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def tex_sigil(rng: Random, pal) -> Image.Image:
    """Charred stone tablet carved with a glowing archfiend rune (titan_sigil shape)."""
    img = blank()
    for y in range(2, 14):
        for x in range(3, 13):
            if x in (3, 12) or y in (2, 13):
                color = CHARCOAL_DARK
            elif (x * 7 + y * 3) % 11 == 0:
                color = CHARCOAL_LIGHT
            else:
                color = CHARCOAL
            px(img, x, y, color)
    for y in range(4, 12):
        px(img, 7, y, pal["bright"] if y % 2 else pal["base"])
    arms = [(5, 5), (6, 4), (9, 5), (8, 4), (5, 9), (6, 10), (9, 9), (8, 10),
            (5, 7), (10, 7)]
    for x, y in rng.sample(arms, 6):
        px(img, x, y, pal["base"])
    px(img, 7, 3, pal["hot"])
    px(img, 7, 12, pal["bright"])
    return img


def tex_shard(rng: Random, pal) -> Image.Image:
    """Teardrop shard/scale banded in the boss palette (verdigris_scale shape)."""
    img = blank()
    half = {2: 0.6, 3: 1.4, 4: 2.1, 5: 2.7, 6: 3.3, 7: 3.8, 8: 4.2, 9: 4.5,
            10: 4.7, 11: 4.7, 12: 4.2, 13: 3.2}
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (2, 13):
                color = pal["dark"]
            elif y in (5, 8, 11):
                color = pal["dark"]
            elif x <= 6 and y <= 9:
                color = pal["bright"]
            else:
                color = pal["base"]
            px(img, x, y, color)
    for x in range(6, 10):
        px(img, x, 14, pal["dark"])
    interior = [(x, y) for y, h in half.items()
                for x in range(int(math.ceil(7.5 - h)) + 1, int(math.floor(7.5 + h)))]
    for x, y in rng.sample(interior, 4):
        px(img, x, y, pal["hot"])
    px(img, 6, 4, GLINT)
    return img


def tex_orb(rng: Random, pal) -> Image.Image:
    """Round relic orb with a glowing heart (oxidizer_core inner-disc shape)."""
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - cx, y - cy)
            if d <= 5.6:
                if d > 4.6:
                    color = pal["dark"]
                elif d > 2.4:
                    color = pal["base"] if (x * 3 + y * 5) % 7 else pal["dark"]
                else:
                    color = pal["bright"]
                px(img, x, y, color)
    ring = [(3, 4), (12, 4), (2, 8), (13, 8), (5, 12), (10, 12)]
    for x, y in rng.sample(ring, 4):
        px(img, x, y, pal["hot"])
    px(img, 7, 7, pal["hot"])
    px(img, 8, 6, GLINT)
    return img


def tex_trophy(rng: Random, pal) -> Image.Image:
    """The archfiend's trophy crown: palette band, glowing points, hot jewels
    (inferno_crown shape)."""
    img = blank()
    for y in (10, 11, 12):
        for x in range(2, 14):
            if y == 12 or x in (2, 13):
                color = pal["dark"]
            elif y == 10:
                color = pal["bright"]
            else:
                color = pal["base"]
            px(img, x, y, color)
    for cx in (3, 7, 12):
        px(img, cx, 9, pal["base"])
        px(img, cx, 8, pal["base"])
        px(img, cx, 7, pal["bright"])
        px(img, cx, 6, pal["hot"])
        px(img, cx, 5, GLINT if rng.random() < 0.5 else pal["hot"])
    for cx in (5, 9):
        px(img, cx, 9, pal["dark"])
        px(img, cx, 8, pal["base"])
    px(img, 5, 11, pal["hot"])
    px(img, 8, 11, GLINT)
    px(img, 11, 11, pal["hot"])
    return img


def spawn_egg(rng: Random, base, base_dark, base_light, spots, outline) -> Image.Image:
    """Classic spawn-egg silhouette with seeded speckles (infernoboss_gen shape)."""
    img = blank()
    half = {2: 1.6, 3: 2.4, 4: 3.0, 5: 3.5, 6: 4.0, 7: 4.4, 8: 4.7, 9: 4.9,
            10: 5.0, 11: 5.0, 12: 4.7, 13: 4.0, 14: 2.8}
    cells = []
    for y, h in half.items():
        x0 = int(math.ceil(7.5 - h))
        x1 = int(math.floor(7.5 + h))
        for x in range(x0, x1 + 1):
            cells.append((x, y, x == x0 or x == x1 or y in (2, 14)))
    for x, y, edge in cells:
        if edge:
            color = outline
        elif x <= 5 and y <= 9:
            color = base_light
        elif x >= 10 or y >= 12:
            color = base_dark
        else:
            color = base
        px(img, x, y, color)
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, spots)
    return img


def emit_textures() -> None:
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for b in BOSSES:
        pal = b["pal"]
        jobs = [
            (b["summon"], lambda rng, p=pal: tex_sigil(rng, p)),
            (b["drop1"], lambda rng, p=pal: tex_shard(rng, p)),
            (b["drop2"], lambda rng, p=pal: tex_orb(rng, p)),
            (b["trophy"], lambda rng, p=pal: tex_trophy(rng, p)),
            (f"{b['bid']}_spawn_egg",
             lambda rng, p=pal: spawn_egg(rng, p["base"], p["dark"], p["bright"],
                                          p["hot"], p["dark"])),
        ]
        for name, fn in jobs:
            img = fn(genlib.rng_for(f"archfiends:{name}"))
            img.save(tex_dir / f"{name}.png")


# ---------------------------------------------------------------------------
# Java codegen: the 10 boss entity subclasses. Each mirrors the hand-written
# TheOxidizerEntity / InfernoTitanEntity structure (BossBarHolder forwarding,
# mobTick mechanics, persisted phase flag, updatePostDeath sync).
# ---------------------------------------------------------------------------

def _javadoc(lines) -> str:
    body = "\n".join(f" * {line}".rstrip() for line in lines)
    return f"/**\n{body}\n */"


def entity_source(b) -> str:
    base = b["base"]
    aura = b.get("aura")
    heal = b.get("heal")
    phase = b.get("phase")
    enrage = b.get("enrage")
    melee = b.get("melee")

    imports = {
        "net.minecraft.entity.EntityType",
        "net.minecraft.entity.boss.BossBar",
        f"net.minecraft.entity.mob.{base}",
        "net.minecraft.server.network.ServerPlayerEntity",
        "net.minecraft.server.world.ServerWorld",
        "net.minecraft.text.Text",
        "net.minecraft.world.World",
        "net.sonic0810.copperinferno.core.boss.BossBarHolder",
    }
    needs_effects = bool((aura and (aura["effects"] or aura.get("self_speed")))
                         or (phase and phase.get("resistance")))
    if needs_effects:
        imports.add("net.minecraft.entity.effect.StatusEffectInstance")
        imports.add("net.minecraft.entity.effect.StatusEffects")
    if aura:
        imports.add("net.minecraft.entity.player.PlayerEntity")
        if aura.get("dust") is not None:
            imports.add("net.minecraft.particle.DustParticleEffect")
        if aura.get("particle"):
            imports.add("net.minecraft.particle.ParticleTypes")
    if heal:
        imports.add("net.minecraft.particle.DustParticleEffect")
    if enrage or melee or (phase and (phase.get("speed_boost") or phase.get("melee_boost"))):
        imports.add("net.minecraft.entity.attribute.EntityAttributeInstance")
        imports.add("net.minecraft.entity.attribute.EntityAttributeModifier")
        imports.add("net.minecraft.entity.attribute.EntityAttributes")
        imports.add("net.minecraft.util.Identifier")
        imports.add("net.sonic0810.copperinferno.CopperInferno")
    if phase:
        imports.add("net.minecraft.storage.ReadView")
        imports.add("net.minecraft.storage.WriteView")
        if phase.get("minion"):
            imports.add("net.minecraft.entity.SpawnReason")
            imports.add(f"net.minecraft.entity.mob.{phase['minion'][1]}")
        if phase.get("equip"):
            imports.add("net.minecraft.entity.EquipmentSlot")
            imports.add("net.minecraft.item.ItemStack")
            imports.add("net.minecraft.item.Items")
    if b.get("equip"):
        imports.add("net.minecraft.entity.EquipmentSlot")
        imports.add("net.minecraft.item.ItemStack")
        imports.add("net.minecraft.item.Items")
    if b.get("block_interact"):
        imports.add("net.minecraft.entity.player.PlayerEntity")
        imports.add("net.minecraft.util.ActionResult")
        imports.add("net.minecraft.util.Hand")

    out = [f"package {PKG};", ""]
    out += [f"import {imp};" for imp in sorted(imports)]
    out += ["", _javadoc(b["doc"] + [
        f"Attributes are registered in {{@link ArchfiendsFeature}}; drops come from",
        f"{{@code loot_table/entities/{b['bid']}.json}}."]),
        f"public class {b['cls']} extends {base} {{"]

    # --- constants
    if phase:
        out.append('\tprivate static final String PHASE_TWO_KEY = "PhaseTwo";')
    if enrage:
        out.append("\tprivate static final Identifier ENRAGE_MODIFIER_ID = "
                   f"CopperInferno.id(\"{b['bid']}_enrage\");")
    if melee:
        out.append("\tprivate static final Identifier MELEE_BOOST_MODIFIER_ID = "
                   f"CopperInferno.id(\"{b['bid']}_melee\");")
    if phase and phase.get("speed_boost"):
        out.append("\tprivate static final Identifier PHASE_BOOST_MODIFIER_ID = "
                   f"CopperInferno.id(\"{b['bid']}_phase_two\");")
    if phase and phase.get("melee_boost"):
        out.append("\tprivate static final Identifier PHASE_BOOST_MODIFIER_ID = "
                   f"CopperInferno.id(\"{b['bid']}_phase_two\");")
    if aura:
        out.append(f"\tprivate static final int ABILITY_INTERVAL_TICKS = {aura['interval']};")
        out.append(f"\tprivate static final double ABILITY_RANGE = {aura['range']};")
        if aura["effects"] or aura.get("self_speed"):
            out.append(f"\tprivate static final int ABILITY_EFFECT_TICKS = {aura['ticks']};")
        if aura.get("fire"):
            out.append(f"\tprivate static final int ABILITY_FIRE_TICKS = {aura['fire']};")
        if aura.get("dust") is not None:
            out.append("\tprivate static final DustParticleEffect AURA_BURST = "
                       f"new DustParticleEffect(0x{aura['dust']:06X}, 1.0f);")
    if heal:
        out.append(f"\tprivate static final int HEAL_INTERVAL_TICKS = {heal['interval']};")
        out.append("\tprivate static final DustParticleEffect HEAL_BURST = "
                   f"new DustParticleEffect(0x{heal['dust']:06X}, 1.0f);")

    color, style = b["bar"]
    out += ["",
            "\tprivate final BossBarHolder bossBar = new BossBarHolder(",
            f"\t\t\tText.translatable(\"entity.copper_inferno.{b['bid']}\"),",
            f"\t\t\tBossBar.Color.{color}, BossBar.Style.{style});"]

    if phase:
        out += ["",
                "\t/** One-shot phase flag, persisted so a reloaded phase-2 archfiend never",
                "\t * re-runs its phase burst (InfernoTitan pattern). */",
                "\tprivate boolean phaseTwo;"]

    # --- constructor
    out += ["",
            f"\tpublic {b['cls']}(EntityType<? extends {base}> type, World world) {{",
            "\t\tsuper(type, world);",
            "\t\t// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).",
            "\t\tif (!world.isClient()) {",
            "\t\t\tthis.setPersistent();"]
    if b.get("immune_zombification"):
        out.append("\t\t\t// Public in 1.21.9 (verified via javap); an archfiend never "
                   "zombifies in the Overworld.")
        out.append("\t\t\tthis.setImmuneToZombification(true);")
    if b.get("equip"):
        out.append("\t\t\t// Summon items / EntityType.create skip initialize(), so the boss "
                   "arms itself here;")
        out.append("\t\t\t// saved equipment simply re-applies over this on load. Never "
                   "dropped on death.")
        out.append("\t\t\tthis.equipStack(EquipmentSlot.MAINHAND, new "
                   f"ItemStack(Items.{b['equip']}));")
        out.append("\t\t\tthis.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.0f);")
    out += ["\t\t}", "\t}"]

    if b.get("block_interact"):
        out += ["",
                "\t/**",
                "\t * Blocks the inherited hoglin feeding/breeding interaction: without this,",
                "\t * players could pacify or breed the boss mid-fight (TheOxidizer interactMob",
                "\t * pattern; HoglinEntity.interactMob is public, verified via javap).",
                "\t */",
                "\t@Override",
                "\tpublic ActionResult interactMob(PlayerEntity player, Hand hand) {",
                "\t\treturn ActionResult.PASS;",
                "\t}"]

    out += ["",
            "\t@Override",
            "\tpublic void onStartedTrackingBy(ServerPlayerEntity player) {",
            "\t\tsuper.onStartedTrackingBy(player);",
            "\t\tthis.bossBar.onStartedTrackingBy(player);",
            "\t}",
            "",
            "\t@Override",
            "\tpublic void onStoppedTrackingBy(ServerPlayerEntity player) {",
            "\t\tsuper.onStoppedTrackingBy(player);",
            "\t\tthis.bossBar.onStoppedTrackingBy(player);",
            "\t}"]

    # --- mobTick
    out += ["",
            "\t@Override",
            "\tprotected void mobTick(ServerWorld world) {",
            "\t\tsuper.mobTick(world);",
            "\t\tthis.bossBar.update(this);"]
    if phase:
        out += ["\t\tif (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {",
                "\t\t\tthis.phaseTwo = true;",
                "\t\t\tenterPhaseTwo(world);",
                "\t\t}"]
        if phase.get("speed_boost") or phase.get("melee_boost"):
            out += ["\t\t// Temporary modifiers are not persisted; re-apply after reload",
                    "\t\t// (hasModifier guard keeps it from stacking).",
                    "\t\tif (this.phaseTwo) {",
                    "\t\t\tapplyPhaseBoost();",
                    "\t\t}"]
    if aura:
        if phase and phase.get("halve_aura"):
            out += ["\t\t// The frenzy phase vents the veil twice as often.",
                    "\t\tint interval = this.phaseTwo ? ABILITY_INTERVAL_TICKS / 2 "
                    ": ABILITY_INTERVAL_TICKS;",
                    "\t\tif (this.age % interval == 0) {",
                    "\t\t\tpulseAura(world);",
                    "\t\t}"]
        else:
            out += ["\t\tif (this.age % ABILITY_INTERVAL_TICKS == 0) {",
                    "\t\t\tpulseAura(world);",
                    "\t\t}"]
    if heal:
        out += ["\t\tif (this.age % HEAL_INTERVAL_TICKS == 0 && this.getHealth() "
                "< this.getMaxHealth()) {",
                "\t\t\tregenerate(world);",
                "\t\t}"]
    if enrage:
        out += ["\t\tif (this.getHealth() < this.getMaxHealth() * 0.5f) {",
                "\t\t\tenrage();",
                "\t\t}"]
    if melee:
        out += ["\t\tif (this.getHealth() < this.getMaxHealth() * 0.5f) {",
                "\t\t\tapplyMeleeBoost();",
                "\t\t}"]
    out.append("\t}")

    # --- aura helper
    if aura:
        doc = ["\t/**",
               "\t * Effects hit every survival/adventure player the boss has line of sight to",
               f"\t * within {aura['range']:g} blocks (TheOxidizer ventCorrosiveCloud pattern); "
               "creative and",
               "\t * spectator players and players behind walls are unaffected.",
               "\t */"]
        out += [""] + doc
        out.append("\tprivate void pulseAura(ServerWorld world) {")
        if aura.get("self_speed"):
            out.append("\t\tthis.addStatusEffect(new StatusEffectInstance("
                       "StatusEffects.SPEED, ABILITY_EFFECT_TICKS, 0));")
        out += ["\t\tfor (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,",
                "\t\t\t\tthis.getBoundingBox().expand(ABILITY_RANGE),",
                "\t\t\t\tplayer -> !player.isSpectator() && !player.isCreative() "
                "&& this.canSee(player))) {"]
        for effect, amp in aura["effects"]:
            out.append("\t\t\tplayer.addStatusEffect(new StatusEffectInstance("
                       f"StatusEffects.{effect}, ABILITY_EFFECT_TICKS, {amp}));")
        if aura.get("fire"):
            out.append("\t\t\tplayer.setOnFireForTicks(ABILITY_FIRE_TICKS);")
        out.append("\t\t}")
        if aura.get("dust") is not None:
            out += ["\t\tworld.spawnParticles(AURA_BURST,",
                    "\t\t\t\tthis.getX(), this.getBodyY(0.5), this.getZ(), "
                    "30, 0.35, 0.45, 0.35, 0.05);"]
        elif aura.get("particle"):
            out += [f"\t\tworld.spawnParticles(ParticleTypes.{aura['particle']},",
                    "\t\t\t\tthis.getX(), this.getBodyY(0.5), this.getZ(), "
                    "30, 0.35, 0.45, 0.35, 0.05);"]
        out.append("\t}")

    # --- heal helper
    if heal:
        out += ["",
                "\t/** Knits wounds shut in a burst of ash (same spawnParticles shape as the",
                "\t * TheOxidizer verdigris burst). */",
                "\tprivate void regenerate(ServerWorld world) {",
                f"\t\tthis.heal({heal['amount']});",
                "\t\tworld.spawnParticles(HEAL_BURST,",
                "\t\t\t\tthis.getX(), this.getBodyY(0.5), this.getZ(), "
                "30, 0.35, 0.45, 0.35, 0.05);",
                "\t}"]

    # --- enrage helper
    if enrage:
        out += ["",
                "\t/**",
                f"\t * One-shot +{enrage} flat movement speed below 50% health (TheOxidizer",
                "\t * enrage pattern). Temporary modifiers are not persisted, so after a reload",
                "\t * this simply re-applies on the next tick; the {@code hasModifier} guard",
                "\t * keeps it from stacking.",
                "\t */",
                "\tprivate void enrage() {",
                "\t\tEntityAttributeInstance speed = this.getAttributeInstance("
                "EntityAttributes.MOVEMENT_SPEED);",
                "\t\tif (speed != null && !speed.hasModifier(ENRAGE_MODIFIER_ID)) {",
                "\t\t\tspeed.addTemporaryModifier(new EntityAttributeModifier(",
                f"\t\t\t\t\tENRAGE_MODIFIER_ID, {enrage}, "
                "EntityAttributeModifier.Operation.ADD_VALUE));",
                "\t\t}",
                "\t}"]

    # --- health-gated melee boost helper
    if melee:
        out += ["",
                "\t/**",
                f"\t * One-shot +{melee:g} flat attack damage below 50% health (InfernoTitan",
                "\t * applyMeleeBoost pattern); the {@code hasModifier} guard keeps it from",
                "\t * stacking across reloads.",
                "\t */",
                "\tprivate void applyMeleeBoost() {",
                "\t\tEntityAttributeInstance damage = this.getAttributeInstance("
                "EntityAttributes.ATTACK_DAMAGE);",
                "\t\tif (damage != null && !damage.hasModifier(MELEE_BOOST_MODIFIER_ID)) {",
                "\t\t\tdamage.addTemporaryModifier(new EntityAttributeModifier(",
                f"\t\t\t\t\tMELEE_BOOST_MODIFIER_ID, {melee}, "
                "EntityAttributeModifier.Operation.ADD_VALUE));",
                "\t\t}",
                "\t}"]

    # --- phase helpers
    if phase:
        burst_doc = []
        if phase.get("minion"):
            burst_doc.append(f"{phase['count']} vanilla {phase['minion'][0].lower()} minions")
        if phase.get("resistance"):
            burst_doc.append(f"{phase['resistance']} ticks of Resistance")
        if phase.get("halve_aura"):
            burst_doc.append("a doubled aura rate (see mobTick)")
        out += ["",
                f"\t/** Phase 2 burst: {' + '.join(burst_doc)}. */",
                "\tprivate void enterPhaseTwo(ServerWorld world) {"]
        if phase.get("minion"):
            mtype, mcls = phase["minion"]
            out += [f"\t\tfor (int i = 0; i < {phase['count']}; i++) {{",
                    f"\t\t\t{mcls} minion = EntityType.{mtype}.create(world, "
                    "SpawnReason.MOB_SUMMONED);",
                    "\t\t\tif (minion == null) {",
                    "\t\t\t\tcontinue;",
                    "\t\t\t}",
                    "\t\t\tminion.refreshPositionAndAngles(",
                    "\t\t\t\t\tthis.getX() + (this.random.nextDouble() - 0.5) * 3.0,",
                    "\t\t\t\t\tthis.getY(),",
                    "\t\t\t\t\tthis.getZ() + (this.random.nextDouble() - 0.5) * 3.0,",
                    "\t\t\t\t\tthis.getYaw(), 0.0f);"]
            if phase.get("immune"):
                out.append("\t\t\tminion.setImmuneToZombification(true);")
            if phase.get("equip"):
                out.append("\t\t\tminion.equipStack(EquipmentSlot.MAINHAND, new "
                           f"ItemStack(Items.{phase['equip']}));")
            out += ["\t\t\tworld.spawnEntity(minion);",
                    "\t\t}"]
        if phase.get("resistance"):
            out.append("\t\tthis.addStatusEffect(new StatusEffectInstance("
                       f"StatusEffects.RESISTANCE, {phase['resistance']}, 0));")
        out.append("\t}")

        if phase.get("speed_boost"):
            out += ["",
                    f"\t/** One-shot +{phase['speed_boost']} flat movement speed for phase 2",
                    "\t * (InfernoTitan applyMeleeBoost pattern; hasModifier guard). */",
                    "\tprivate void applyPhaseBoost() {",
                    "\t\tEntityAttributeInstance speed = this.getAttributeInstance("
                    "EntityAttributes.MOVEMENT_SPEED);",
                    "\t\tif (speed != null && !speed.hasModifier(PHASE_BOOST_MODIFIER_ID)) {",
                    "\t\t\tspeed.addTemporaryModifier(new EntityAttributeModifier(",
                    f"\t\t\t\t\tPHASE_BOOST_MODIFIER_ID, {phase['speed_boost']}, "
                    "EntityAttributeModifier.Operation.ADD_VALUE));",
                    "\t\t}",
                    "\t}"]
        if phase.get("melee_boost"):
            out += ["",
                    f"\t/** One-shot +{phase['melee_boost']} flat attack damage for phase 2",
                    "\t * (InfernoTitan applyMeleeBoost pattern; hasModifier guard). */",
                    "\tprivate void applyPhaseBoost() {",
                    "\t\tEntityAttributeInstance damage = this.getAttributeInstance("
                    "EntityAttributes.ATTACK_DAMAGE);",
                    "\t\tif (damage != null && !damage.hasModifier(PHASE_BOOST_MODIFIER_ID)) {",
                    "\t\t\tdamage.addTemporaryModifier(new EntityAttributeModifier(",
                    f"\t\t\t\t\tPHASE_BOOST_MODIFIER_ID, {phase['melee_boost']}, "
                    "EntityAttributeModifier.Operation.ADD_VALUE));",
                    "\t\t}",
                    "\t}"]

        out += ["",
                "\t@Override",
                "\tprotected void writeCustomData(WriteView view) {",
                "\t\tsuper.writeCustomData(view);",
                "\t\tview.putBoolean(PHASE_TWO_KEY, this.phaseTwo);",
                "\t}",
                "",
                "\t@Override",
                "\tprotected void readCustomData(ReadView view) {",
                "\t\tsuper.readCustomData(view);",
                "\t\tthis.phaseTwo = view.getBoolean(PHASE_TWO_KEY, false);",
                "\t}"]

    out += ["",
            "\t/** mobTick stops during the death animation; keep the boss bar synced "
            "(empty) as it dies. */",
            "\t@Override",
            "\tprotected void updatePostDeath() {",
            "\t\tsuper.updatePostDeath();",
            "\t\tthis.bossBar.update(this);",
            "\t}",
            "}"]
    return "\n".join(out) + "\n"


# ---------------------------------------------------------------------------
# Java codegen: ArchfiendSummonItem (shared summon item, TitanSigilItem pattern
# minus the dimension gate; refuses peaceful, nudges out of blocks, plays thunder).
# ---------------------------------------------------------------------------

SUMMON_ITEM_SOURCE = f'''package {PKG};

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

/**
 * Shared summon sigil for the ten archfiend bosses (TitanSigilItem pattern, minus the
 * dimension gate). Never works on peaceful difficulty (the hostile boss would instantly
 * despawn, wasting the sigil); the failure shows a translatable actionbar message
 * without consuming the item. Works both on a block (boss rises on the clicked face)
 * and in the air (boss appears a few blocks ahead of the player). If the boss's
 * collision box is obstructed it is nudged up to 8 blocks upward; with no clear spot
 * the summon aborts (actionbar message) and the sigil is NOT consumed. The boss is
 * created via {{@code EntityType.create(World, SpawnReason.MOB_SUMMONED)}} (verified via
 * javap), so each subclass constructor runs its own setPersistent/equip logic.
 */
public class ArchfiendSummonItem extends Item {{
	private static final int MAX_UPWARD_NUDGE = 8;

	private final EntityType<? extends MobEntity> bossType;

	public ArchfiendSummonItem(EntityType<? extends MobEntity> bossType, Settings settings) {{
		super(settings);
		this.bossType = bossType;
	}}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {{
		Vec3d spawnPos = Vec3d.ofBottomCenter(context.getBlockPos().offset(context.getSide()));
		return trySummon(context.getWorld(), context.getPlayer(), spawnPos, context.getStack());
	}}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {{
		Vec3d look = user.getRotationVector();
		Vec3d spawnPos = user.getEntityPos().add(look.x * 4.0, 0.0, look.z * 4.0);
		return trySummon(world, user, spawnPos, user.getStackInHand(hand));
	}}

	private ActionResult trySummon(World world, PlayerEntity player, Vec3d spawnPos, ItemStack stack) {{
		if (world.getDifficulty() == Difficulty.PEACEFUL) {{
			if (!world.isClient() && player != null) {{
				player.sendMessage(Text.translatable("message.copper_inferno.archfiend_sigil.peaceful"), true);
			}}
			return ActionResult.FAIL;
		}}
		if (world instanceof ServerWorld serverWorld) {{
			MobEntity boss = this.bossType.create(serverWorld, SpawnReason.MOB_SUMMONED);
			if (boss == null) {{
				return ActionResult.FAIL;
			}}
			float yaw = player != null ? player.getYaw() + 180.0f : 0.0f;
			if (!nudgeToEmptySpace(serverWorld, boss, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), yaw)) {{
				if (player != null) {{
					player.sendMessage(Text.translatable("message.copper_inferno.archfiend_sigil.blocked"), true);
				}}
				return ActionResult.FAIL;
			}}
			serverWorld.spawnEntity(boss);
			serverWorld.playSound(null, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(),
					SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.HOSTILE, 1.0f, 1.0f);
			if (player != null) {{
				stack.decrementUnlessCreative(1, player);
			}} else {{
				stack.decrement(1);
			}}
		}}
		return ActionResult.SUCCESS;
	}}

	/**
	 * Positions the boss at (x, y, z), nudging upward one block at a time (max 8) until
	 * its collision box is unobstructed ({{@code CollisionView.isSpaceEmpty(Entity)}}, so
	 * the boss never suffocates inside blocks). Returns false when no clear spot exists.
	 */
	private static boolean nudgeToEmptySpace(ServerWorld world, MobEntity boss,
			double x, double y, double z, float yaw) {{
		for (int dy = 0; dy <= MAX_UPWARD_NUDGE; dy++) {{
			boss.refreshPositionAndAngles(x, y + dy, z, yaw, 0.0f);
			if (world.isSpaceEmpty(boss)) {{
				return true;
			}}
		}}
		return false;
	}}
}}
'''


# ---------------------------------------------------------------------------
# Java codegen: ArchfiendsFeature (literal registrations only, so
# devtools/audit_assets.py check (f) can parse the ids symbolically).
# ---------------------------------------------------------------------------

def field_of(s: str) -> str:
    return s.upper()


def feature_source() -> str:
    attr_bases = sorted({b["attr_creator"].split(".")[0] for b in BOSSES})
    out = [f"package {PKG};", "",
           "import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;",
           "import net.fabricmc.fabric.api.object.builder.v1.entity."
           "FabricDefaultAttributeRegistry;",
           "import net.minecraft.entity.EntityType;",
           "import net.minecraft.entity.SpawnGroup;",
           "import net.minecraft.entity.attribute.EntityAttributes;"]
    out += [f"import net.minecraft.entity.mob.{cls};" for cls in attr_bases]
    out += ["import net.minecraft.item.Item;",
            "import net.minecraft.item.SpawnEggItem;",
            "import net.minecraft.util.Rarity;",
            "import net.sonic0810.copperinferno.core.ModCreativeTab;",
            "import net.sonic0810.copperinferno.core.ModEntities;",
            "import net.sonic0810.copperinferno.core.ModItems;",
            "", _javadoc([
                "The ten archfiend bosses (boss bars via core.boss.BossBarHolder), all",
                "summoned-only: each is called with its own sigil (shared",
                "{@link ArchfiendSummonItem}, ring recipes in",
                "{@code data/copper_inferno/recipe/archfiends/}) and never spawns naturally",
                "(SpawnGroup.MISC, no spawn restrictions registered). All ten reuse vanilla",
                "renderers (see {@code ArchfiendsFeatureClient}); EntityType dimensions are",
                "copied from the vanilla registrations (verified via bytecode) and inflated",
                "via the SCALE attribute. Summon sigils, boss drops, EPIC trophies, spawn",
                "eggs and handbook entries are all registered here. Generated by",
                "{@code devtools/gen/archfiends_gen.py}; keep ids as plain literals so the",
                "audit scripts can parse them."]),
            "public final class ArchfiendsFeature {",
            "\tprivate ArchfiendsFeature() {",
            "\t}", ""]

    for b in BOSSES:
        out.append(f"\tpublic static EntityType<{b['cls']}> {field_of(b['bid'])};")
    out.append("")
    for iid in ALL_ITEM_IDS:
        out.append(f"\tpublic static Item {field_of(iid)};")

    out += ["",
            "\tpublic static void init() {",
            "\t\tregisterEntityTypes();",
            "\t\tregisterAttributes();",
            "\t\tregisterItems();",
            "\t\tArchfiendsHandbook.register();",
            "\t}"]

    # --- entity types
    out += ["",
            "\t/**",
            "\t * Base dimensions copied from the vanilla EntityType registrations (bytecode);",
            "\t * the bosses' bulk comes from the SCALE attribute, and all get an extended",
            "\t * tracking range so the boss bar/model appear well before the fight. Summoned",
            "\t * bosses call setPersistent, so MISC (no natural despawn logic) fits them all.",
            "\t */",
            "\tprivate static void registerEntityTypes() {"]
    for b in BOSSES:
        out.append(f"\t\t{field_of(b['bid'])} = ModEntities.register(\"{b['bid']}\",")
        out.append(f"\t\t\t\tEntityType.Builder.create({b['cls']}::new, SpawnGroup.MISC)")
        for i, dim in enumerate(b["dims"]):
            suffix = ");" if i == len(b["dims"]) - 1 else ""
            out.append(f"\t\t\t\t\t\t{dim}{suffix}")
    out.append("\t}")

    # --- attributes
    out += ["",
            "\t/**",
            "\t * EntityAttributes fields have no GENERIC_ prefix in 1.21.9 (verified via",
            "\t * javap); DefaultAttributeContainer.Builder.add(...) overrides the base values",
            "\t * from each vanilla create*Attributes() builder.",
            "\t */",
            "\tprivate static void registerAttributes() {"]
    for b in BOSSES:
        out.append(f"\t\tFabricDefaultAttributeRegistry.register({field_of(b['bid'])}, "
                   f"{b['attr_creator']}")
        for i, (attr, value) in enumerate(b["attrs"]):
            suffix = ");" if i == len(b["attrs"]) - 1 else ""
            out.append(f"\t\t\t\t.add(EntityAttributes.{attr}, {value}){suffix}")
    out.append("\t}")

    # --- items
    out += ["",
            "\tprivate static void registerItems() {",
            "\t\t// Summon sigils (shared ArchfiendSummonItem; boss type resolved from the",
            "\t\t// already-registered EntityType fields).",]
    for b in BOSSES:
        out.append(f"\t\t{field_of(b['summon'])} = ModItems.register(\"{b['summon']}\",")
        out.append(f"\t\t\t\ts -> new ArchfiendSummonItem({field_of(b['bid'])}, s), "
                   "new Item.Settings());")
    out.append("")
    out.append("\t\t// Boss drops and EPIC trophies (Item.Settings.rarity verified via javap).")
    for b in BOSSES:
        out.append(f"\t\t{field_of(b['drop1'])} = ModItems.register(\"{b['drop1']}\", "
                   "Item::new, new Item.Settings());")
        out.append(f"\t\t{field_of(b['drop2'])} = ModItems.register(\"{b['drop2']}\", "
                   "Item::new, new Item.Settings());")
        out.append(f"\t\t{field_of(b['trophy'])} = ModItems.register(\"{b['trophy']}\", "
                   "Item::new,")
        out.append("\t\t\t\tnew Item.Settings().rarity(Rarity.EPIC));")
    out.append("")
    out.append("\t\t// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the")
    out.append("\t\t// vanilla SpawnEggItem reads its entity type from in 1.21.9")
    out.append("\t\t// (infernoboss pattern).")
    for b in BOSSES:
        egg = f"{b['bid']}_spawn_egg"
        out.append(f"\t\t{field_of(egg)} = ModItems.register(\"{egg}\", SpawnEggItem::new,")
        out.append(f"\t\t\t\tnew Item.Settings().spawnEgg({field_of(b['bid'])}));")

    out += ["",
            "\t\tItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY)"
            ".register(entries -> {",
            "\t\t\t// Summon sigils, then per-boss drops/trophies, then the spawn eggs."]
    for b in BOSSES:
        out.append(f"\t\t\tentries.add({field_of(b['summon'])});")
    for b in BOSSES:
        out.append(f"\t\t\tentries.add({field_of(b['drop1'])});")
        out.append(f"\t\t\tentries.add({field_of(b['drop2'])});")
        out.append(f"\t\t\tentries.add({field_of(b['trophy'])});")
    for b in BOSSES:
        out.append(f"\t\t\tentries.add({field_of(b['bid'] + '_spawn_egg')});")
    out += ["\t\t});", "\t}", "}"]
    return "\n".join(out) + "\n"


# ---------------------------------------------------------------------------
# Java codegen: ArchfiendsFeatureClient (vanilla renderers; the register method is
# access-widened by Fabric's transitive access wideners, same proven pattern as
# InfernoBossFeatureClient).
# ---------------------------------------------------------------------------

RENDERERS = {
    "GhastEntity": "GhastEntityRenderer::new",
    "HoglinEntity": "HoglinEntityRenderer::new",
    "EvokerEntity": "EvokerEntityRenderer::new",
    "WitherSkeletonEntity": "WitherSkeletonEntityRenderer::new",
}


def client_source() -> str:
    out = [f"package {PKG}.client;", "",
           "import net.minecraft.client.render.entity.EntityRendererFactories;",
           "import net.minecraft.client.render.entity.EvokerEntityRenderer;",
           "import net.minecraft.client.render.entity.GhastEntityRenderer;",
           "import net.minecraft.client.render.entity.HoglinEntityRenderer;",
           "import net.minecraft.client.render.entity.PiglinEntityRenderer;",
           "import net.minecraft.client.render.entity.WitherSkeletonEntityRenderer;",
           "import net.minecraft.client.render.entity.model.EntityModelLayers;",
           f"import {PKG}.ArchfiendsFeature;",
           "", _javadoc([
               "Client-side setup for the archfiend bosses: all ten reuse their vanilla",
               "renderers - the SCALE attribute makes them loom. The ghast/hoglin/evoker/",
               "wither-skeleton renderer ctors are Context-only (verified via javap); the",
               "piglin brutes reuse PiglinEntityRenderer with the PIGLIN_BRUTE model and",
               "equipment layers, exactly matching the vanilla EntityRendererFactories",
               "bytecode. The bosses extend the matching vanilla entities, so the factories",
               "fit the register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;)",
               "bound; the vanilla register method is access-widened by Fabric's transitive",
               "access wideners (same proven pattern as InfernoBossFeatureClient)."]),
           "public final class ArchfiendsFeatureClient {",
           "\tprivate ArchfiendsFeatureClient() {",
           "\t}", "",
           "\tpublic static void initClient() {"]
    for b in BOSSES:
        field = f"ArchfiendsFeature.{field_of(b['bid'])}"
        if b["base"] == "PiglinBruteEntity":
            out += [f"\t\tEntityRendererFactories.register({field},",
                    "\t\t\t\tcontext -> new PiglinEntityRenderer(context, "
                    "EntityModelLayers.PIGLIN_BRUTE,",
                    "\t\t\t\t\t\tEntityModelLayers.PIGLIN_BRUTE, "
                    "EntityModelLayers.PIGLIN_BRUTE_EQUIPMENT,",
                    "\t\t\t\t\t\tEntityModelLayers.PIGLIN_BRUTE_EQUIPMENT));"]
        else:
            out.append(f"\t\tEntityRendererFactories.register({field}, "
                       f"{RENDERERS[b['base']]});")
    out += ["\t}", "}"]
    return "\n".join(out) + "\n"


# ---------------------------------------------------------------------------
# Handbook (genlib.java_handbook_class; "bosses" category, EN + DE, one boss entry
# plus one ring-recipe grid entry per boss = 20 entries).
# ---------------------------------------------------------------------------

def handbook_entries() -> list:
    entries = []
    for b in BOSSES:
        text_en, text_de = HB_TEXT[b["bid"]]
        entries.append(("bosses", b["bid"], f"{NS}:{b['bid']}_spawn_egg",
                        None, None, None, 0, text_en, text_de))
        corner, edge, center = b["ring"]
        grid = [corner, edge, corner, edge, center, edge, corner, edge, corner]
        rec_en = (f"{ITEM_EN[b['summon']]} - ring a {ING_EN[center]} with 4 "
                  f"{ING_EN[corner]} and 4 {ING_EN[edge]}. Use it to summon the "
                  f"{b['en']} (never on peaceful).")
        rec_de = (f"{ITEM_DE[b['summon']]} - {ING_DE[center]} mit 4 "
                  f"{ING_DE[corner]} und 4 {ING_DE[edge]} umringen. Benutzen, um "
                  f"{b['de_acc']} zu beschw\u00f6ren (nie auf Friedlich).")
        entries.append(("bosses", f"{b['summon']}_recipe", f"{NS}:{b['summon']}",
                        f"archfiends/{b['summon']}", grid, f"{NS}:{b['summon']}", 1,
                        rec_en, rec_de))
    return entries


def emit_java() -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)
    CLIENT_DIR.mkdir(parents=True, exist_ok=True)

    (FEATURE_DIR / "ArchfiendsFeature.java").write_text(feature_source(),
                                                        encoding="utf-8")
    (FEATURE_DIR / "ArchfiendSummonItem.java").write_text(SUMMON_ITEM_SOURCE,
                                                          encoding="utf-8")
    for b in BOSSES:
        (FEATURE_DIR / f"{b['cls']}.java").write_text(entity_source(b),
                                                      encoding="utf-8")
    (CLIENT_DIR / "ArchfiendsFeatureClient.java").write_text(client_source(),
                                                             encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the ten archfiend bosses: one \"bosses\" entry per boss",
        "(summon, mechanics, drops) plus one grid entry for every ring recipe under",
        "{@code data/copper_inferno/recipe/archfiends/}. Entry texts and grids mirror",
        "the recipe JSONs emitted by {@code devtools/gen/archfiends_gen.py};",
        "{@code devtools/check_handbook.py} parses the inline",
        "{@code new HandbookEntry(...)} literals positionally, so keep them inline.",
    ]
    handbook_src = genlib.java_handbook_class("archfiends", "ArchfiendsHandbook",
                                              handbook_doc, handbook_entries())
    (FEATURE_DIR / "ArchfiendsHandbook.java").write_text(handbook_src,
                                                         encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/archfiends.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks(recipe_count: int, handbook_count: int) -> None:
    lines = ["# archfiends feature hooks (format: devtools/hooks/README.md)", "",
             "[init]",
             "# No init-order constraint: the ring recipes use only vanilla ingredients",
             "# and this feature's own item ids.",
             f"import {PKG}.ArchfiendsFeature;",
             "\t\tArchfiendsFeature.init();", "",
             "[client-init]",
             f"import {PKG}.client.ArchfiendsFeatureClient;",
             "\t\tArchfiendsFeatureClient.initClient();", "",
             "[recipe-dir]", "archfiends", "",
             "[counts]",
             f"bosses: {len(BOSSES)}",
             f"items: {len(ALL_ITEM_IDS)}",
             f"recipes: {recipe_count}",
             f"handbook-entries: {handbook_count}", ""]
    path = ROOT / "devtools" / "hooks" / "archfiends.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Output manifest (for determinism checks / the integrator).
# ---------------------------------------------------------------------------

def output_files() -> list:
    out = []
    for iid in ALL_ITEM_IDS:
        out += [ASSETS / "items" / f"{iid}.json",
                ASSETS / "models" / "item" / f"{iid}.json",
                ASSETS / "textures" / "item" / f"{iid}.png"]
    for b in BOSSES:
        out.append(DATA / "loot_table" / "entities" / f"{b['bid']}.json")
        out.append(RECIPES / f"{b['summon']}.json")
    out += [ASSETS / "lang" / "fragments" / "archfiends.json",
            ASSETS / "lang" / "fragments_de" / "archfiends.json",
            FEATURE_DIR / "ArchfiendsFeature.java",
            FEATURE_DIR / "ArchfiendSummonItem.java",
            FEATURE_DIR / "ArchfiendsHandbook.java",
            CLIENT_DIR / "ArchfiendsFeatureClient.java",
            ROOT / "devtools" / "hooks" / "archfiends.txt"]
    out += [FEATURE_DIR / f"{b['cls']}.java" for b in BOSSES]
    return [str(p) for p in out]


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    assert len({b["bid"] for b in BOSSES}) == 10, "duplicate boss ids"
    assert len(ALL_ITEM_IDS) == 50, f"expected 50 item ids, got {len(ALL_ITEM_IDS)}"
    assert len(set(ALL_ITEM_IDS)) == 50, "duplicate item ids emitted"
    base_counts = {}
    for b in BOSSES:
        base_counts[b["base"]] = base_counts.get(b["base"], 0) + 1
    assert base_counts == {"GhastEntity": 2, "HoglinEntity": 2, "EvokerEntity": 2,
                           "PiglinBruteEntity": 2, "WitherSkeletonEntity": 2}, base_counts

    emit_item_assets()
    emit_textures()
    emit_loot_tables()
    recipe_count = emit_recipes()

    lang_en, lang_de = build_lang()
    expected_keys = ({f"entity.{NS}.{b['bid']}" for b in BOSSES}
                     | {f"item.{NS}.{iid}" for iid in ALL_ITEM_IDS}
                     | {"message.copper_inferno.archfiend_sigil.peaceful",
                        "message.copper_inferno.archfiend_sigil.blocked"})
    assert set(lang_en) == set(lang_de) == expected_keys, "EN/DE lang key sets differ"
    genlib.lang_fragments(ASSETS, "archfiends", lang_en, lang_de)

    emit_java()
    hb_count = len(handbook_entries())
    emit_hooks(recipe_count, hb_count)

    assert recipe_count == 10, f"expected 10 recipes, got {recipe_count}"
    assert hb_count == 20, f"expected 20 handbook entries, got {hb_count}"
    print(f"archfiends_gen: assets generated for {len(BOSSES)} bosses / "
          f"{len(ALL_ITEM_IDS)} items ({recipe_count} recipes, {hb_count} handbook "
          "entries).")


if __name__ == "__main__":
    main()
