#!/usr/bin/env python3
"""Asset + code generator for the COPPER INFERNO "calamities" feature (10 bosses, 50 items).

10 ultra-powerful summon-only bosses mirroring the proven infernoboss template
(SpawnGroup.MISC + setPersistent + core.boss.BossBarHolder server boss bar + high
MAX_HEALTH + SCALE bulk + extended FOLLOW_RANGE; vanilla models scaled up, but each boss
gets its OWN recolored entity texture via a per-boss renderer subclass). Bases:
RavagerEntity x2, IronGolemEntity x2, WitherSkeletonEntity x2, BlazeEntity x2,
VindicatorEntity x2. Each boss ships a summon item (ring craft), 2 drops (entity loot
table), an EPIC trophy (shapeless craft from the drops) and a spawn egg.

Idempotent: running it any number of times produces byte-identical output (all texture
noise is seeded per texture name via genlib.rng_for; the entity recolor is a pure
function of the vanilla texture bytes + the boss palette). Emits by DEFAULT (no flags):
  - 16x16 RGBA item textures     assets/copper_inferno/textures/item/<id>.png
  - recolored entity textures    src/client/resources/assets/copper_inferno/textures/
                                 entity/<boss_id>.png (vanilla base texture from the loom
                                 minecraft-client.jar, luminance-mapped onto the boss
                                 palette; skipped with a warning if the jar is absent)
  - item model-definitions       assets/copper_inferno/items/<id>.json
  - item models                  assets/copper_inferno/models/item/<id>.json
  - entity loot tables           data/copper_inferno/loot_table/entities/<boss>.json
  - recipes                      data/copper_inferno/recipe/calamities/*.json
                                 (every recipe references at least one copper_inferno id)
  - lang fragments               assets/copper_inferno/lang/fragments/calamities.json (EN)
                                 assets/copper_inferno/lang/fragments_de/calamities.json (DE)
  - Java sources                 src/main/java/.../feature/calamities/CalamitiesFeature.java
                                 (literal registration ids only), CalamitySigilItem.java,
                                 the 10 boss entity classes (hand-written logic below),
                                 CalamitiesHandbook.java (genlib.java_handbook_class),
                                 src/client/java/.../feature/calamities/client/
                                 CalamitiesFeatureClient.java + one <Boss>Renderer per
                                 boss (vanilla renderer subclass overriding the
                                 render-state getTexture overload)
  - hook file                    devtools/hooks/calamities.txt

Every vanilla API used by the emitted Java was verified with javap against the loom
1.21.9 yarn-mapped jars (see the per-class comments): RavagerEntity /
WitherSkeletonEntity / VindicatorEntity ctors + attribute factories
(createRavagerAttributes / createAbstractSkeletonAttributes / createVindicatorAttributes),
EntityType dims from the EntityType static-initializer bytecode (ravager 1.95x2.2,
wither_skeleton fireImmune 0.7x2.4, vindicator 0.6x1.95; iron_golem 1.4x2.7 and blaze
fireImmune 0.6x1.8 from the infernoboss template), MeleeAttackGoal(PathAwareEntity,
double, boolean), ActiveTargetGoal(MobEntity, Class, boolean),
SmallFireballEntity(World, LivingEntity, Vec3d), Entity.setOnFireFor(float),
LivingEntity.equipStack + AbstractSkeletonEntity.updateAttackType(),
MobEntity.tryAttack(ServerWorld, Entity), ServerWorld.spawnParticles,
BossBar.Color/Style constants and the StatusEffects registry entries.

Renderer subclasses (all javap-verified against the loom clientonly jar): every base
renderer is a NON-final public class with a Context-only public ctor and a NON-final
public getTexture overload taking its own render state (RavagerEntityRenderer ->
RavagerEntityRenderState, IronGolemEntityRenderer -> IronGolemEntityRenderState,
WitherSkeletonEntityRenderer -> SkeletonEntityRenderState, BlazeEntityRenderer ->
LivingEntityRenderState, VindicatorEntityRenderer -> IllagerEntityRenderState), so a
tiny subclass overriding that overload swaps the texture while keeping the vanilla
model + animations.
"""

import io
import math
import sys
import zipfile
from collections import namedtuple
from pathlib import Path
from random import Random

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

import genlib
from genlib import ASSETS, DATA, NS, ROOT, rng_for, write_json

RECIPES = DATA / "recipe" / "calamities"
FEATURE_DIR = (ROOT / "src" / "main" / "java" / "net" / "sonic0810" / "copperinferno"
               / "feature" / "calamities")
CLIENT_DIR = (ROOT / "src" / "client" / "java" / "net" / "sonic0810" / "copperinferno"
              / "feature" / "calamities" / "client")
CLIENT_ASSETS = ROOT / "src" / "client" / "resources" / "assets" / NS
CLIENT_JAR = Path.home() / ".gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"

Pal = namedtuple("Pal", "base dark light accent outline")

# Vanilla + existing-mod ingredient display names (EN, DE) used by handbook texts.
ING = {
    "minecraft:copper_ingot": ("Copper Ingot", "Kupferbarren"),
    "minecraft:iron_ingot": ("Iron Ingot", "Eisenbarren"),
    "minecraft:emerald": ("Emerald", "Smaragd"),
    "minecraft:bone": ("Bone", "Knochen"),
    "minecraft:charcoal": ("Charcoal", "Holzkohle"),
    "minecraft:blaze_powder": ("Blaze Powder", "Lohenstaub"),
    "minecraft:blaze_rod": ("Blaze Rod", "Lohenrute"),
    "minecraft:gold_nugget": ("Gold Nugget", "Goldklumpen"),
    "minecraft:goat_horn": ("Goat Horn", "Ziegenhorn"),
    "minecraft:iron_block": ("Iron Block", "Eisenblock"),
    "minecraft:copper_block": ("Copper Block", "Kupferblock"),
    "minecraft:oxidized_copper": ("Oxidized Copper", "Oxidiertes Kupfer"),
    "minecraft:wither_skeleton_skull": ("Wither Skeleton Skull", "Witherskelettsch\u00e4del"),
    "minecraft:bell": ("Bell", "Glocke"),
    "minecraft:lantern": ("Lantern", "Laterne"),
    "minecraft:campfire": ("Campfire", "Lagerfeuer"),
    "minecraft:coal_block": ("Coal Block", "Kohleblock"),
    "minecraft:ominous_bottle": ("Ominous Bottle", "Unheilvolle Flasche"),
    "minecraft:gold_block": ("Gold Block", "Goldblock"),
    f"{NS}:inferno_powder": ("Inferno Powder", "Infernopulver"),
    f"{NS}:oxidized_copper_dust": ("Oxidized Copper Dust", "Oxidierter Kupferstaub"),
    f"{NS}:copper_dust": ("Copper Dust", "Kupferstaub"),
}

# ---------------------------------------------------------------------------
# The 10 calamity bosses. Every field feeds literal ids into the generated Java
# (audit check (f) parses registration ids symbolically, so literals only).
#   base      vanilla class the entity subclasses (renderer + attribute factory)
#   attrs     DefaultAttributeContainer.Builder .add overrides (EntityAttributes names)
#   summon    (item id, EN, DE); ring recipe = 4x corner + 4x edge around center
#   drop1     rare drop (1-2), drop2 common drop (2-4); trophy = EPIC, crafted
#             shapeless from 1x drop1 + 2x drop2 + 1x gold block
# ---------------------------------------------------------------------------
BOSSES = [
    dict(
        bid="emberlord_ravager", cls="EmberlordRavagerEntity", base="ravager",
        en="Emberlord Ravager", de="Glutf\u00fcrst-Verw\u00fcster",
        attrs=[("MAX_HEALTH", "400.0"), ("ATTACK_DAMAGE", "16.0"), ("SCALE", "2.0"),
               ("KNOCKBACK_RESISTANCE", "1.0"), ("FOLLOW_RANGE", "48.0")],
        summon=("emberlord_warhorn", "Emberlord Warhorn", "Glutf\u00fcrst-Kriegshorn"),
        drop1=("emberlord_tusk", "Emberlord Tusk", "Glutf\u00fcrst-Sto\u00dfzahn"),
        drop2=("charred_hide", "Charred Hide", "Verkohlte Haut"),
        trophy=("emberlord_crest", "Emberlord Crest", "Glutf\u00fcrst-Wappen"),
        corner="minecraft:copper_ingot", edge=f"{NS}:inferno_powder",
        center="minecraft:goat_horn",
        lore_en="Emberlord Ravager - a colossal war beast wreathed in embers. Summon: use an Emberlord Warhorn (consumed; refused on peaceful). Every 4 seconds it ignites every player it can see within 5 blocks, and below half health it enrages and speeds up. Drops 1-2 Emberlord Tusks and 2-4 Charred Hides.",
        lore_de="Glutf\u00fcrst-Verw\u00fcster - eine kolossale, in Glut geh\u00fcllte Kriegsbestie. Beschw\u00f6rung: ein Glutf\u00fcrst-Kriegshorn benutzen (wird verbraucht; auf Friedlich verweigert). Alle 4 Sekunden entz\u00fcndet er jeden Spieler, den er im Umkreis von 5 Bl\u00f6cken sehen kann; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 1-2 Glutf\u00fcrst-Sto\u00dfz\u00e4hne und 2-4 Verkohlte H\u00e4ute fallen.",
        pal=Pal((0xE2, 0x58, 0x22), (0x8F, 0x33, 0x16), (0xFF, 0xB1, 0x6B),
                (0xFF, 0xD8, 0x66), (0x2B, 0x22, 0x26)),
    ),
    dict(
        bid="slag_warlord", cls="SlagWarlordEntity", base="ravager",
        en="Slag Warlord", de="Schlacken-Kriegsherr",
        attrs=[("MAX_HEALTH", "500.0"), ("ATTACK_DAMAGE", "18.0"), ("SCALE", "2.2"),
               ("KNOCKBACK_RESISTANCE", "1.0"), ("FOLLOW_RANGE", "48.0")],
        summon=("slag_war_banner", "Slag War Banner", "Schlacken-Kriegsbanner"),
        drop1=("broken_war_axe", "Broken War Axe", "Zerbrochene Kriegsaxt"),
        drop2=("warlord_slag_chunk", "Warlord Slag Chunk", "Kriegsherren-Schlackenbrocken"),
        trophy=("warlord_totem", "Warlord Totem", "Kriegsherren-Totem"),
        corner="minecraft:iron_ingot", edge=f"{NS}:oxidized_copper_dust",
        center="minecraft:iron_block",
        lore_en="Slag Warlord - a slag-armored ravager warchief. Summon: use a Slag War Banner. Below half health it enters phase two exactly once - it calls 2 vindicator lieutenants, hardens with Resistance and its blows gain +6 damage. Drops 1-2 Broken War Axes and 2-4 Warlord Slag Chunks.",
        lore_de="Schlacken-Kriegsherr - ein schlackengepanzerter Verw\u00fcster-Kriegsh\u00e4uptling. Beschw\u00f6rung: ein Schlacken-Kriegsbanner benutzen. Unter halber Gesundheit beginnt genau einmal Phase zwei - er ruft 2 Diener als Verst\u00e4rkung, h\u00e4rtet sich mit Resistenz und seine Hiebe erhalten +6 Schaden. L\u00e4sst 1-2 Zerbrochene Kriegs\u00e4xte und 2-4 Kriegsherren-Schlackenbrocken fallen.",
        pal=Pal((0x74, 0x4A, 0x2C), (0x4A, 0x2E, 0x1C), (0xA8, 0x66, 0x30),
                (0xE2, 0x58, 0x22), (0x26, 0x18, 0x10)),
    ),
    dict(
        bid="molten_colossus", cls="MoltenColossusEntity", base="iron_golem",
        en="Molten Colossus", de="Schmelzkoloss",
        attrs=[("MAX_HEALTH", "600.0"), ("ATTACK_DAMAGE", "22.0"), ("SCALE", "2.5"),
               ("KNOCKBACK_RESISTANCE", "1.0"), ("FOLLOW_RANGE", "48.0")],
        summon=("colossus_effigy", "Colossus Effigy", "Koloss-Bildnis"),
        drop1=("colossus_plating", "Colossus Plating", "Koloss-Panzerplatte"),
        drop2=("molten_core_shard", "Molten Core Shard", "Schmelzkern-Scherbe"),
        trophy=("colossus_medallion", "Colossus Medallion", "Koloss-Medaillon"),
        corner=f"{NS}:copper_dust", edge="minecraft:iron_ingot",
        center="minecraft:copper_block",
        lore_en="Molten Colossus - a magma-veined iron colossus. Summon: use a Colossus Effigy. It hunts players on sight; every 5 seconds it vents a molten wave that ignites and slows everyone it can see within 6 blocks, and below half health its fists gain +8 damage. Drops 1-2 Colossus Platings and 2-4 Molten Core Shards.",
        lore_de="Schmelzkoloss - ein von Magmaadern durchzogener Eisenkoloss. Beschw\u00f6rung: ein Koloss-Bildnis benutzen. Er jagt Spieler auf Sicht; alle 5 Sekunden st\u00f6\u00dft er eine Schmelzwelle aus, die jeden entz\u00fcndet und verlangsamt, den er im Umkreis von 6 Bl\u00f6cken sehen kann; unter halber Gesundheit erhalten seine F\u00e4uste +8 Schaden. L\u00e4sst 1-2 Koloss-Panzerplatten und 2-4 Schmelzkern-Scherben fallen.",
        pal=Pal((0xE0, 0x73, 0x4D), (0x8F, 0x40, 0x2A), (0xFF, 0xA0, 0x6E),
                (0xFF, 0x7A, 0x2F), (0x3A, 0x1C, 0x12)),
    ),
    dict(
        bid="verdigris_monarch", cls="VerdigrisMonarchEntity", base="iron_golem",
        en="Verdigris Monarch", de="Gr\u00fcnspan-Monarch",
        attrs=[("MAX_HEALTH", "550.0"), ("ATTACK_DAMAGE", "18.0"), ("SCALE", "2.2"),
               ("KNOCKBACK_RESISTANCE", "1.0"), ("FOLLOW_RANGE", "48.0")],
        summon=("verdigris_regalia", "Verdigris Regalia", "Gr\u00fcnspan-Insignien"),
        drop1=("monarch_plate", "Monarch Plate", "Monarchenplatte"),
        drop2=("verdant_patina", "Verdant Patina", "Gr\u00fcne Patina"),
        trophy=("monarch_signet", "Monarch Signet", "Monarchen-Siegelring"),
        corner="minecraft:emerald", edge=f"{NS}:oxidized_copper_dust",
        center="minecraft:oxidized_copper",
        lore_en="Verdigris Monarch - a regal, patina-crowned golem. Summon: use the Verdigris Regalia. Every 5 seconds it saps everyone it can see within 6 blocks with Weakness and Mining Fatigue, and below half health it slowly regenerates. Drops 1-2 Monarch Plates and 2-4 Verdant Patinas.",
        lore_de="Gr\u00fcnspan-Monarch - ein k\u00f6niglicher, patinagekr\u00f6nter Golem. Beschw\u00f6rung: die Gr\u00fcnspan-Insignien benutzen. Alle 5 Sekunden schw\u00e4cht er jeden, den er im Umkreis von 6 Bl\u00f6cken sehen kann, mit Schw\u00e4che und Abbaul\u00e4hmung; unter halber Gesundheit regeneriert er sich langsam. L\u00e4sst 1-2 Monarchenplatten und 2-4 Gr\u00fcne Patinas fallen.",
        pal=Pal((0x57, 0xA0, 0x7B), (0x37, 0x6E, 0x55), (0x6F, 0xB0, 0x8E),
                (0xD8, 0xF5, 0xE6), (0x27, 0x4E, 0x3D)),
    ),
    dict(
        bid="ashking_wither", cls="AshkingWitherEntity", base="wither_skeleton",
        en="Ashking Wither", de="Aschek\u00f6nig-Wither",
        attrs=[("MAX_HEALTH", "350.0"), ("ATTACK_DAMAGE", "12.0"), ("SCALE", "2.0"),
               ("KNOCKBACK_RESISTANCE", "0.6"), ("FOLLOW_RANGE", "48.0")],
        summon=("ashking_skull_idol", "Ashking Skull Idol", "Aschek\u00f6nig-Sch\u00e4delidol"),
        drop1=("cursed_ash_clump", "Cursed Ash Clump", "Verfluchter Ascheklumpen"),
        drop2=("ashking_rib", "Ashking Rib", "Aschek\u00f6nig-Rippe"),
        trophy=("ashking_diadem", "Ashking Diadem", "Aschek\u00f6nig-Diadem"),
        corner="minecraft:bone", edge=f"{NS}:inferno_powder",
        center="minecraft:wither_skeleton_skull",
        lore_en="Ashking Wither - the skeletal king of the ash. Summon: use an Ashking Skull Idol. Every 5 seconds it withers everyone it can see within 6 blocks; below half health it hardens with Resistance once and gains speed. Drops 1-2 Cursed Ash Clumps and 2-4 Ashking Ribs.",
        lore_de="Aschek\u00f6nig-Wither - der Skelettk\u00f6nig der Asche. Beschw\u00f6rung: ein Aschek\u00f6nig-Sch\u00e4delidol benutzen. Alle 5 Sekunden belegt er jeden, den er im Umkreis von 6 Bl\u00f6cken sehen kann, mit Verdorren; unter halber Gesundheit h\u00e4rtet er sich einmal mit Resistenz und wird schneller. L\u00e4sst 1-2 Verfluchte Ascheklumpen und 2-4 Aschek\u00f6nig-Rippen fallen.",
        pal=Pal((0x8C, 0x86, 0x7E), (0x55, 0x50, 0x4A), (0xC4, 0xBC, 0xB0),
                (0x8A, 0x5C, 0xC8), (0x2E, 0x2A, 0x26)),
    ),
    dict(
        bid="soot_reaper", cls="SootReaperEntity", base="wither_skeleton",
        en="Soot Reaper", de="Ru\u00dfschnitter",
        attrs=[("MAX_HEALTH", "300.0"), ("ATTACK_DAMAGE", "10.0"), ("SCALE", "1.8"),
               ("KNOCKBACK_RESISTANCE", "0.4"), ("FOLLOW_RANGE", "48.0")],
        summon=("reaper_knell", "Reaper Knell", "Schnitter-Totenglocke"),
        drop1=("soot_scythe_blade", "Soot Scythe Blade", "Ru\u00dfsensenklinge"),
        drop2=("reaper_soot", "Reaper Soot", "Schnitterru\u00df"),
        trophy=("reaper_hourglass", "Reaper Hourglass", "Schnitter-Stundenglas"),
        corner="minecraft:charcoal", edge=f"{NS}:inferno_powder",
        center="minecraft:bell",
        lore_en="Soot Reaper - a spectral wither harvester. Summon: use a Reaper Knell. Its scythe strikes blind and wither their victim, and below half health it enrages and speeds up. Drops 1-2 Soot Scythe Blades and 2-4 Reaper Soot.",
        lore_de="Ru\u00dfschnitter - ein spektraler Wither-Schnitter. Beschw\u00f6rung: eine Schnitter-Totenglocke benutzen. Seine Sensenhiebe blenden ihr Opfer und lassen es verdorren; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 1-2 Ru\u00dfsensenklingen und 2-4 Schnitterru\u00df fallen.",
        pal=Pal((0x3D, 0x38, 0x3C), (0x25, 0x21, 0x25), (0x6A, 0x62, 0x68),
                (0xE8, 0xE4, 0xDE), (0x14, 0x10, 0x14)),
    ),
    dict(
        bid="cinder_sovereign", cls="CinderSovereignEntity", base="blaze",
        en="Cinder Sovereign", de="Zunderherrscher",
        attrs=[("MAX_HEALTH", "450.0"), ("SCALE", "2.5"), ("FOLLOW_RANGE", "64.0")],
        summon=("cinder_beacon", "Cinder Beacon", "Zunder-Leuchtfeuer"),
        drop1=("cinder_plume", "Cinder Plume", "Zunderfeder"),
        drop2=("sovereign_ember_shard", "Sovereign Ember Shard", "Herrscher-Glutscherbe"),
        trophy=("sovereign_scepter", "Sovereign Scepter", "Herrscherzepter"),
        corner="minecraft:blaze_powder", edge=f"{NS}:inferno_powder",
        center="minecraft:lantern",
        lore_en="Cinder Sovereign - a blazing tyrant of cinders. Summon: use a Cinder Beacon. While it has a target it periodically erupts in a ring of eight small fireballs on top of its regular volleys. Drops 1-2 Cinder Plumes and 2-4 Sovereign Ember Shards.",
        lore_de="Zunderherrscher - ein lodernder Herrscher der Zunder. Beschw\u00f6rung: ein Zunder-Leuchtfeuer benutzen. Solange er ein Ziel hat, bricht er regelm\u00e4\u00dfig in einen Ring aus acht kleinen Feuerb\u00e4llen aus, zus\u00e4tzlich zu seinen \u00fcblichen Salven. L\u00e4sst 1-2 Zunderfedern und 2-4 Herrscher-Glutscherben fallen.",
        pal=Pal((0xF6, 0xC1, 0x2B), (0xC6, 0x8A, 0x00), (0xFF, 0xE0, 0x82),
                (0xFF, 0x7A, 0x2F), (0x5A, 0x3E, 0x08)),
    ),
    dict(
        bid="pyre_tyrant", cls="PyreTyrantEntity", base="blaze",
        en="Pyre Tyrant", de="Scheiterhaufen-Tyrann",
        attrs=[("MAX_HEALTH", "500.0"), ("ATTACK_DAMAGE", "8.0"), ("SCALE", "2.8"),
               ("FOLLOW_RANGE", "64.0")],
        summon=("tyrant_pyre_brand", "Tyrant Pyre Brand", "Tyrannen-Feuerbrand"),
        drop1=("pyre_fang", "Pyre Fang", "Scheiterhaufen-Fangzahn"),
        drop2=("tyrant_ash", "Tyrant Ash", "Tyrannenasche"),
        trophy=("pyre_tyrant_crown", "Pyre Tyrant Crown", "Scheiterhaufen-Tyrannenkrone"),
        corner="minecraft:blaze_rod", edge=f"{NS}:inferno_powder",
        center="minecraft:campfire",
        lore_en="Pyre Tyrant - a colossal blaze warlord. Summon: use a Tyrant Pyre Brand. Below half health it enters phase two exactly once - it calls 3 blaze minions, hardens with Resistance and charges into melee with +4 damage. Drops 1-2 Pyre Fangs and 2-4 Tyrant Ash.",
        lore_de="Scheiterhaufen-Tyrann - ein kolossaler Lohen-Kriegsherr. Beschw\u00f6rung: einen Tyrannen-Feuerbrand benutzen. Unter halber Gesundheit beginnt genau einmal Phase zwei - er ruft 3 Lohen-Diener, h\u00e4rtet sich mit Resistenz und st\u00fcrmt mit +4 Schaden in den Nahkampf. L\u00e4sst 1-2 Scheiterhaufen-Fangz\u00e4hne und 2-4 Tyrannenasche fallen.",
        pal=Pal((0xB3, 0x2D, 0x1E), (0x6E, 0x1A, 0x12), (0xE8, 0x4A, 0x2A),
                (0xFF, 0xB1, 0x6B), (0x30, 0x0C, 0x08)),
    ),
    dict(
        bid="furnace_fiend", cls="FurnaceFiendEntity", base="vindicator",
        en="Furnace Fiend", de="Ofenunhold",
        attrs=[("MAX_HEALTH", "320.0"), ("SCALE", "1.8"),
               ("KNOCKBACK_RESISTANCE", "0.4"), ("FOLLOW_RANGE", "48.0")],
        summon=("fiend_ember_key", "Fiend Ember Key", "Unhold-Glutschl\u00fcssel"),
        drop1=("fiend_talon", "Fiend Talon", "Unhold-Klaue"),
        drop2=("fiend_cinder", "Fiend Cinder", "Unhold-Zunder"),
        trophy=("fiend_furnace_mask", "Fiend Furnace Mask", "Unhold-Ofenmaske"),
        corner="minecraft:gold_nugget", edge=f"{NS}:copper_dust",
        center="minecraft:coal_block",
        lore_en="Furnace Fiend - an axe-swinging fiend from the furnace depths. Summon: use a Fiend Ember Key. Its axe blows set the victim on fire, and below half health it enrages and speeds up. Drops 1-2 Fiend Talons and 2-4 Fiend Cinders.",
        lore_de="Ofenunhold - ein axtschwingender Unhold aus den Ofentiefen. Beschw\u00f6rung: einen Unhold-Glutschl\u00fcssel benutzen. Seine Axthiebe setzen das Opfer in Brand; unter halber Gesundheit wird er rasend und schneller. L\u00e4sst 1-2 Unhold-Klauen und 2-4 Unhold-Zunder fallen.",
        pal=Pal((0x5E, 0x56, 0x56), (0x38, 0x34, 0x34), (0x8C, 0x84, 0x90),
                (0xFF, 0x7A, 0x2F), (0x1E, 0x1B, 0x1B)),
    ),
    dict(
        bid="calamity_herald", cls="CalamityHeraldEntity", base="vindicator",
        en="Calamity Herald", de="Herold des Unheils",
        attrs=[("MAX_HEALTH", "380.0"), ("ATTACK_DAMAGE", "14.0"), ("SCALE", "2.0"),
               ("KNOCKBACK_RESISTANCE", "0.6"), ("FOLLOW_RANGE", "48.0")],
        summon=("herald_omen_sigil", "Herald Omen Sigil", "Herold-Omensiegel"),
        drop1=("herald_emberglass", "Herald Emberglass", "Herold-Glutglas"),
        drop2=("omen_fragment", "Omen Fragment", "Omenfragment"),
        trophy=("herald_war_banner", "Herald War Banner", "Herold-Kriegsbanner"),
        corner="minecraft:copper_ingot", edge=f"{NS}:oxidized_copper_dust",
        center="minecraft:ominous_bottle",
        lore_en="Calamity Herald - the doom-crier of the calamities. Summon: use a Herald Omen Sigil. Every 6 seconds it marks everyone it can see within 8 blocks with Glowing and Slowness; below half health it calls 2 vindicator adds exactly once. Drops 1-2 Herald Emberglass and 2-4 Omen Fragments.",
        lore_de="Herold des Unheils - der Unheilsrufer der Katastrophen. Beschw\u00f6rung: ein Herold-Omensiegel benutzen. Alle 6 Sekunden zeichnet er jeden, den er im Umkreis von 8 Bl\u00f6cken sehen kann, mit Leuchten und Langsamkeit; unter halber Gesundheit ruft er genau einmal 2 Diener herbei. L\u00e4sst 1-2 Herold-Glutglas und 2-4 Omenfragmente fallen.",
        pal=Pal((0x6A, 0x4E, 0x8E), (0x46, 0x32, 0x60), (0x93, 0x74, 0xBE),
                (0x57, 0xA0, 0x7B), (0x24, 0x1A, 0x34)),
    ),
]

assert len(BOSSES) == 10
assert len({b["bid"] for b in BOSSES}) == 10

# base -> (vanilla class simple name, attribute-factory call, builder chain after create()).
# Dims verified against the EntityType static-initializer bytecode (see module docstring);
# all bosses get maxTrackingRange(10) so the bar/model appear well before the fight.
BASE_INFO = {
    "ravager": ("RavagerEntity", "RavagerEntity.createRavagerAttributes()",
                "\t\t\t\t\t\t.dimensions(1.95f, 2.2f)\n\t\t\t\t\t\t.maxTrackingRange(10)"),
    "iron_golem": ("IronGolemEntity", "IronGolemEntity.createIronGolemAttributes()",
                   "\t\t\t\t\t\t.dimensions(1.4f, 2.7f)\n\t\t\t\t\t\t.maxTrackingRange(10)"),
    "wither_skeleton": ("WitherSkeletonEntity",
                        "AbstractSkeletonEntity.createAbstractSkeletonAttributes()",
                        "\t\t\t\t\t\t.makeFireImmune()\n\t\t\t\t\t\t.dimensions(0.7f, 2.4f)"
                        "\n\t\t\t\t\t\t.maxTrackingRange(10)"),
    "blaze": ("BlazeEntity", "BlazeEntity.createBlazeAttributes()",
              "\t\t\t\t\t\t.makeFireImmune()\n\t\t\t\t\t\t.dimensions(0.6f, 1.8f)"
              "\n\t\t\t\t\t\t.maxTrackingRange(10)"),
    "vindicator": ("VindicatorEntity", "VindicatorEntity.createVindicatorAttributes()",
                   "\t\t\t\t\t\t.dimensions(0.6f, 1.95f)\n\t\t\t\t\t\t.maxTrackingRange(10)"),
}

RENDERERS = {
    "ravager": "RavagerEntityRenderer",
    "iron_golem": "IronGolemEntityRenderer",
    "wither_skeleton": "WitherSkeletonEntityRenderer",
    "blaze": "BlazeEntityRenderer",
    "vindicator": "VindicatorEntityRenderer",
}

# base -> render-state type of the vanilla renderer's own getTexture overload (javap;
# see module docstring). The generated subclasses override exactly this overload.
RENDER_STATES = {
    "ravager": "RavagerEntityRenderState",
    "iron_golem": "IronGolemEntityRenderState",
    "wither_skeleton": "SkeletonEntityRenderState",
    "blaze": "LivingEntityRenderState",
    "vindicator": "IllagerEntityRenderState",
}

# base -> vanilla entity texture inside the loom minecraft-client.jar (paths verified
# by listing the jar; the ravager/vindicator live under illager/, blaze at top level).
VANILLA_ENTITY_TEXTURES = {
    "ravager": "assets/minecraft/textures/entity/illager/ravager.png",
    "iron_golem": "assets/minecraft/textures/entity/iron_golem/iron_golem.png",
    "wither_skeleton": "assets/minecraft/textures/entity/skeleton/wither_skeleton.png",
    "blaze": "assets/minecraft/textures/entity/blaze.png",
    "vindicator": "assets/minecraft/textures/entity/illager/vindicator.png",
}


def field_of(item_id: str) -> str:
    return item_id.upper()


def renderer_class_of(b: dict) -> str:
    """EmberlordRavagerEntity -> EmberlordRavagerRenderer."""
    assert b["cls"].endswith("Entity")
    return b["cls"][:-len("Entity")] + "Renderer"


def boss_items(b: dict) -> list:
    """(id, EN, DE) of the boss's 4 crafted/dropped items + its spawn egg, tab order."""
    egg = (f"{b['bid']}_spawn_egg", f"{b['en']} Spawn Egg", egg_de(b))
    return [b["summon"], b["drop1"], b["drop2"], b["trophy"], egg]


def egg_de(b: dict) -> str:
    # Template style ("Oxidierer-Spawn-Ei"); explicit per-boss German compounds.
    return {
            "emberlord_ravager": "Glutf\u00fcrst-Verw\u00fcster-Spawn-Ei",
            "slag_warlord": "Schlacken-Kriegsherr-Spawn-Ei",
            "molten_colossus": "Schmelzkoloss-Spawn-Ei",
            "verdigris_monarch": "Gr\u00fcnspan-Monarch-Spawn-Ei",
            "ashking_wither": "Aschek\u00f6nig-Wither-Spawn-Ei",
            "soot_reaper": "Ru\u00dfschnitter-Spawn-Ei",
            "cinder_sovereign": "Zunderherrscher-Spawn-Ei",
            "pyre_tyrant": "Scheiterhaufen-Tyrann-Spawn-Ei",
            "furnace_fiend": "Ofenunhold-Spawn-Ei",
            "calamity_herald": "Unheilsherold-Spawn-Ei",
        }[b["bid"]]


# ---------------------------------------------------------------------------
# Textures (16x16, Pillow; deterministic via rng_for). Shapes per item role:
# summon = carved sigil tablet, drop1 = angular shard, drop2 = round clump,
# trophy = jeweled crown, spawn egg = the classic egg silhouette.
# ---------------------------------------------------------------------------

def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def tex_sigil(rng: Random, pal: Pal) -> Image.Image:
    """Charred tablet carved with a glowing calamity rune (titan_sigil pattern)."""
    img = blank()
    for y in range(2, 14):
        for x in range(3, 13):
            if x in (3, 12) or y in (2, 13):
                color = pal.outline
            elif (x * 7 + y * 3) % 11 == 0:
                color = pal.light
            else:
                color = pal.dark
            px(img, x, y, color)
    for y in range(4, 12):
        px(img, 7, y, pal.accent if y % 2 else pal.base)
    for x, y in [(5, 5), (6, 4), (9, 5), (8, 4), (5, 9), (6, 10), (9, 9), (8, 10)]:
        px(img, x, y, pal.base)
    px(img, 7, 3, pal.accent)
    px(img, 7, 12, pal.light)
    cells = [(x, y) for x in range(4, 12) for y in range(3, 13) if x != 7]
    for x, y in rng.sample(cells, 6):
        px(img, x, y, pal.dark)
    return img


def tex_shard(rng: Random, pal: Pal) -> Image.Image:
    """Angular shard torn from the boss: bright top-left facet, accent core."""
    img = blank()
    rows = {2: (7, 8), 3: (6, 9), 4: (6, 10), 5: (5, 10), 6: (5, 11), 7: (4, 11),
            8: (4, 11), 9: (5, 10), 10: (5, 10), 11: (6, 9), 12: (6, 9), 13: (7, 8)}
    for y, (x0, x1) in rows.items():
        for x in range(x0, x1 + 1):
            if x in (x0, x1) or y in (2, 13):
                color = pal.outline
            elif x <= 7 and y <= 8:
                color = pal.light
            elif (x * 5 + y * 7) % 9 == 0:
                color = pal.accent
            else:
                color = pal.base
            px(img, x, y, color)
    interior = [(x, y) for y, (x0, x1) in rows.items() for x in range(x0 + 1, x1)]
    for x, y in rng.sample(interior, 5):
        px(img, x, y, pal.dark)
    px(img, 7, 7, pal.accent)
    px(img, 6, 5, (0xFF, 0xFF, 0xF0))
    return img


def tex_clump(rng: Random, pal: Pal) -> Image.Image:
    """Round clump/orb of boss residue with a bright heart and seeded mottling."""
    img = blank()
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d = math.hypot(x - cx, y - cy)
            if d <= 5.4:
                if d > 4.5:
                    color = pal.outline
                elif d > 2.6:
                    color = pal.base if (x * 3 + y * 5) % 7 else pal.dark
                else:
                    color = pal.light
                px(img, x, y, color)
    interior = [(x, y) for x in range(4, 12) for y in range(4, 12)
                if math.hypot(x - cx, y - cy) <= 4.5]
    for x, y in rng.sample(interior, 8):
        px(img, x, y, pal.accent)
    px(img, 7, 7, (0xFF, 0xFF, 0xF0))
    return img


def tex_trophy(rng: Random, pal: Pal) -> Image.Image:
    """EPIC trophy crown: gold band + flame-tipped points, jewels in the boss palette
    (inferno_crown pattern with per-boss jewel/tip colors)."""
    gold = (0xF6, 0xC1, 0x2B)
    gold_dark = (0xC6, 0x8A, 0x00)
    gold_light = (0xFF, 0xE0, 0x82)
    img = blank()
    for y in (10, 11, 12):
        for x in range(2, 14):
            if y == 12 or x in (2, 13):
                color = gold_dark
            elif y == 10:
                color = gold_light
            else:
                color = gold
            px(img, x, y, color)
    for cx in (3, 7, 12):
        px(img, cx, 9, gold)
        px(img, cx, 8, gold)
        px(img, cx, 7, gold_light)
        px(img, cx, 6, pal.base)
        px(img, cx, 5, pal.accent)
    for cx in (5, 9):
        px(img, cx, 9, gold_dark)
        px(img, cx, 8, gold)
    px(img, 5, 11, pal.base)
    px(img, 8, 11, pal.accent)
    px(img, 11, 11, pal.base)
    band = [(x, 11) for x in range(3, 13) if x not in (5, 8, 11)]
    for x, y in rng.sample(band, 3):
        px(img, x, y, gold_dark)
    return img


def tex_spawn_egg(rng: Random, pal: Pal) -> Image.Image:
    """Classic spawn-egg silhouette with seeded speckles (infernoboss_gen pattern)."""
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
            color = pal.outline
        elif x <= 5 and y <= 9:
            color = pal.light
        elif x >= 10 or y >= 12:
            color = pal.dark
        else:
            color = pal.base
        px(img, x, y, color)
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, pal.accent)
    return img


ROLE_PAINTERS = [tex_sigil, tex_shard, tex_clump, tex_trophy, tex_spawn_egg]


def emit_textures() -> int:
    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    count = 0
    for b in BOSSES:
        for (item_id, _en, _de), painter in zip(boss_items(b), ROLE_PAINTERS):
            img = painter(rng_for(f"calamities:{item_id}"), b["pal"])
            img.save(tex_dir / f"{item_id}.png")
            count += 1
    return count


# ---------------------------------------------------------------------------
# Recolored ENTITY textures: the base mob's vanilla texture (extracted from the loom
# minecraft-client.jar) luminance-mapped onto the boss palette. Pure function of the
# vanilla bytes + the palette (no rng), so output is byte-identical across runs.
# titanforge_gen.recolor_iron_layer generalized: full 0..1 luminance band mapped across
# a dark -> base -> light ramp; alpha is copied through UNCHANGED and pixels never move
# (entity textures are UV-mapped).
# ---------------------------------------------------------------------------

def lerp(a, b, t):
    return tuple(int(round(a[i] + (b[i] - a[i]) * t)) for i in range(3))


def recolor_entity_texture(png_bytes: bytes, pal: Pal) -> Image.Image:
    src = Image.open(io.BytesIO(png_bytes)).convert("RGBA")
    out = Image.new("RGBA", src.size, (0, 0, 0, 0))
    for y in range(src.size[1]):
        for x in range(src.size[0]):
            r, g, b, a = src.getpixel((x, y))
            if a == 0:
                continue
            lum = (r + g + b) / (3 * 255)
            if lum < 0.5:
                color = lerp(pal.dark, pal.base, lum / 0.5)
            else:
                color = lerp(pal.base, pal.light, (lum - 0.5) / 0.5)
            out.putpixel((x, y), (*color, a))
    return out


def emit_entity_textures() -> int:
    if not CLIENT_JAR.is_file():
        print(f"calamities_gen: WARNING client jar not found at {CLIENT_JAR}; "
              "skipping boss entity textures", file=sys.stderr)
        return 0
    tex_dir = CLIENT_ASSETS / "textures" / "entity"
    tex_dir.mkdir(parents=True, exist_ok=True)
    count = 0
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        for b in BOSSES:
            data = jar.read(VANILLA_ENTITY_TEXTURES[b["base"]])
            recolor_entity_texture(data, b["pal"]).save(tex_dir / f"{b['bid']}.png")
            count += 1
    return count


# ---------------------------------------------------------------------------
# Item model-definitions + models (1.21.9 two-file contract, via genlib).
# ---------------------------------------------------------------------------

def emit_item_assets() -> list:
    ids = []
    for b in BOSSES:
        for item_id, _en, _de in boss_items(b):
            genlib.emit_item_def(ASSETS, item_id)
            genlib.emit_item_model(ASSETS, item_id)
            ids.append(item_id)
    return ids


# ---------------------------------------------------------------------------
# Entity loot tables (infernoboss_gen schema incl. "random_sequence").
# drop1 = 1-2 (rare), drop2 = 2-4 (common); the trophy is CRAFTED from the drops.
# ---------------------------------------------------------------------------

def emit_loot_tables() -> None:
    for b in BOSSES:
        drops = [(f"{NS}:{b['drop1'][0]}", 1.0, 2.0), (f"{NS}:{b['drop2'][0]}", 2.0, 4.0)]
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
# Recipes. Summon item: shaped ring 4x corner + 4x edge around center (every ring
# includes at least one copper_inferno id, so no vanilla collision is possible).
# Trophy: shapeless 1x drop1 + 2x drop2 + 1x gold block (own calamities ids).
# ---------------------------------------------------------------------------

def summon_grid(b: dict) -> list:
    c, e = b["corner"], b["edge"]
    return [c, e, c, e, b["center"], e, c, e, c]


def emit_recipes() -> int:
    count = 0
    for b in BOSSES:
        genlib.emit_shaped(RECIPES, b["summon"][0],
                           {"A": b["corner"], "B": b["edge"], "C": b["center"]},
                           ["ABA", "BCB", "ABA"], f"{NS}:{b['summon'][0]}", 1,
                           category="misc")
        count += 1
        genlib.emit_shapeless(RECIPES, b["trophy"][0],
                              [f"{NS}:{b['drop1'][0]}", f"{NS}:{b['drop2'][0]}",
                               f"{NS}:{b['drop2'][0]}", "minecraft:gold_block"],
                              f"{NS}:{b['trophy'][0]}", 1, category="misc")
        count += 1
    return count


# ---------------------------------------------------------------------------
# Lang fragments (EN + real German; merged by devtools/merge_lang.py).
# ---------------------------------------------------------------------------

def lang_dicts() -> tuple:
    en = {}
    de = {}
    for b in BOSSES:
        en[f"entity.{NS}.{b['bid']}"] = b["en"]
        de[f"entity.{NS}.{b['bid']}"] = b["de"]
        for item_id, item_en, item_de in boss_items(b):
            en[f"item.{NS}.{item_id}"] = item_en
            de[f"item.{NS}.{item_id}"] = item_de
    en[f"message.{NS}.calamity_sigil.peaceful"] = \
        "Calamities refuse to rise on peaceful difficulty."
    de[f"message.{NS}.calamity_sigil.peaceful"] = \
        "Katastrophen erheben sich nicht auf friedlicher Schwierigkeit."
    en[f"message.{NS}.calamity_sigil.blocked"] = \
        "There is no room for the calamity to rise here."
    de[f"message.{NS}.calamity_sigil.blocked"] = \
        "Hier ist kein Platz, damit sich die Katastrophe erheben kann."
    return en, de


# ---------------------------------------------------------------------------
# Java codegen: CalamitiesFeature.java (literal registrations, infernoboss template).
# ---------------------------------------------------------------------------

def feature_source() -> str:
    pkg = f"{genlib.PKG_ROOT}.feature.calamities"
    imports = sorted([
        "net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents",
        "net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry",
        "net.minecraft.entity.EntityType",
        "net.minecraft.entity.SpawnGroup",
        "net.minecraft.entity.attribute.EntityAttributes",
        "net.minecraft.entity.mob.AbstractSkeletonEntity",
        "net.minecraft.entity.mob.BlazeEntity",
        "net.minecraft.entity.mob.RavagerEntity",
        "net.minecraft.entity.mob.VindicatorEntity",
        "net.minecraft.entity.passive.IronGolemEntity",
        "net.minecraft.item.Item",
        "net.minecraft.item.SpawnEggItem",
        "net.minecraft.util.Rarity",
        f"{genlib.PKG_ROOT}.core.ModCreativeTab",
        f"{genlib.PKG_ROOT}.core.ModEntities",
        f"{genlib.PKG_ROOT}.core.ModItems",
    ])
    L = [f"package {pkg};", ""]
    L += [f"import {imp};" for imp in imports]
    L += ["", "/**",
          " * The Calamities: 10 ultra-powerful summon-only bosses (boss bars via",
          " * core.boss.BossBarHolder), mirroring the proven infernoboss template. Each boss",
          " * subclasses a vanilla mob (ravager / iron golem / wither skeleton / blaze /",
          " * vindicator), keeps its vanilla model but wears its own recolored texture via a",
          " * renderer subclass (see {@code CalamitiesFeatureClient})",
          " * and gets its bulk from the SCALE attribute. EntityType dimensions are copied",
          " * from the vanilla registrations (verified via EntityType bytecode). There is NO",
          " * natural spawn: every boss is summoned with its {@link CalamitySigilItem}, a",
          " * spawn egg or /summon. Summon items, drops, EPIC trophies, spawn eggs and",
          " * handbook entries are all registered here.",
          " */",
          "public final class CalamitiesFeature {",
          "\tprivate CalamitiesFeature() {",
          "\t}", ""]
    for b in BOSSES:
        L.append(f"\tpublic static EntityType<{b['cls']}> {field_of(b['bid'])};")
    L.append("")
    for b in BOSSES:
        for item_id, _en, _de in boss_items(b):
            L.append(f"\tpublic static Item {field_of(item_id)};")
    L += ["",
          "\tpublic static void init() {",
          "\t\tregisterEntityTypes();",
          "\t\tregisterAttributes();",
          "\t\tregisterItems();",
          "\t\tCalamitiesHandbook.register();",
          "\t}", ""]

    L.append("\tprivate static void registerEntityTypes() {")
    L.append("\t\t// Dims from the vanilla EntityType registrations (bytecode-verified);")
    L.append("\t\t// the bosses' bulk comes from the SCALE attribute and all get an extended")
    L.append("\t\t// tracking range so bar/model appear well before the fight. Summoned")
    L.append("\t\t// bosses call setPersistent, so MISC (no natural despawn logic) fits.")
    for b in BOSSES:
        L.append(f"\t\t{field_of(b['bid'])} = ModEntities.register(\"{b['bid']}\",")
        L.append(f"\t\t\t\tEntityType.Builder.create({b['cls']}::new, SpawnGroup.MISC)")
        L.append(BASE_INFO[b["base"]][2] + ");")
    L += ["\t}", ""]

    L.append("\tprivate static void registerAttributes() {")
    L.append("\t\t// EntityAttributes fields have no GENERIC_ prefix in 1.21.9 (javap);")
    L.append("\t\t// DefaultAttributeContainer.Builder.add(...) overrides the base values.")
    for b in BOSSES:
        L.append(f"\t\tFabricDefaultAttributeRegistry.register({field_of(b['bid'])}, "
                 f"{BASE_INFO[b['base']][1]}")
        for i, (attr, value) in enumerate(b["attrs"]):
            eol = ");" if i == len(b["attrs"]) - 1 else ""
            L.append(f"\t\t\t\t.add(EntityAttributes.{attr}, {value}){eol}")
    L += ["\t}", ""]

    L.append("\tprivate static void registerItems() {")
    L.append("\t\t// Summon items share CalamitySigilItem (TitanSigilItem pattern); the")
    L.append("\t\t// factory lambda runs AFTER registerEntityTypes(), so the type is set.")
    for b in BOSSES:
        summon_id = b["summon"][0]
        L.append(f"\t\t{field_of(summon_id)} = ModItems.register(\"{summon_id}\",")
        L.append(f"\t\t\t\tsettings -> new CalamitySigilItem(settings,")
        L.append(f"\t\t\t\t\t\tworld -> new {b['cls']}({field_of(b['bid'])}, world)),")
        L.append("\t\t\t\tnew Item.Settings());")
    L.append("")
    for b in BOSSES:
        for item_id, _en, _de in (b["drop1"], b["drop2"]):
            L.append(f"\t\t{field_of(item_id)} = ModItems.register(\"{item_id}\", "
                     "Item::new, new Item.Settings());")
        trophy_id = b["trophy"][0]
        L.append(f"\t\t{field_of(trophy_id)} = ModItems.register(\"{trophy_id}\", Item::new,")
        L.append("\t\t\t\tnew Item.Settings().rarity(Rarity.EPIC));")
    L.append("")
    L.append("\t\t// Item.Settings.spawnEgg(type) stores the ENTITY_DATA component the")
    L.append("\t\t// vanilla SpawnEggItem reads its entity type from (infernoboss pattern).")
    for b in BOSSES:
        egg_id = f"{b['bid']}_spawn_egg"
        L.append(f"\t\t{field_of(egg_id)} = ModItems.register(\"{egg_id}\", SpawnEggItem::new,")
        L.append(f"\t\t\t\tnew Item.Settings().spawnEgg({field_of(b['bid'])}));")
    L.append("")
    L.append("\t\tItemGroupEvents.modifyEntriesEvent(ModCreativeTab.MAIN_KEY)"
             ".register(entries -> {")
    L.append("\t\t\t// Summon items, then per-boss drops + trophy, then the spawn eggs.")
    for b in BOSSES:
        L.append(f"\t\t\tentries.add({field_of(b['summon'][0])});")
    for b in BOSSES:
        for item_id, _en, _de in (b["drop1"], b["drop2"], b["trophy"]):
            L.append(f"\t\t\tentries.add({field_of(item_id)});")
    for b in BOSSES:
        L.append(f"\t\t\tentries.add({field_of(b['bid'] + '_spawn_egg')});")
    L.append("\t\t});")
    L += ["\t}", "}"]
    return "\n".join(L) + "\n"


# ---------------------------------------------------------------------------
# Java codegen: CalamitySigilItem.java (TitanSigilItem pattern, parameterized by a
# boss factory; no dimension gate, but the peaceful gate stays: 8 of the 10 bosses
# are hostile mobs that would instantly despawn on peaceful, wasting the item).
# ---------------------------------------------------------------------------

SIGIL_SOURCE = '''package net.sonic0810.copperinferno.feature.calamities;

import java.util.function.Function;

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
 * Shared summon item for the calamity bosses (TitanSigilItem pattern, parameterized by
 * the boss factory). Works on a block (the boss rises on the clicked face) and in the
 * air (the boss appears a few blocks ahead of the player), but never on peaceful
 * difficulty (most calamities are hostile mobs that would instantly despawn, wasting
 * the item). If the boss's collision box is obstructed it is nudged up to 8 blocks
 * upward; with no clear spot the summon aborts (actionbar message) and the item is NOT
 * consumed.
 */
public class CalamitySigilItem extends Item {
	private static final int MAX_UPWARD_NUDGE = 8;

	private final Function<ServerWorld, MobEntity> bossFactory;

	public CalamitySigilItem(Settings settings, Function<ServerWorld, MobEntity> bossFactory) {
		super(settings);
		this.bossFactory = bossFactory;
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		Vec3d spawnPos = Vec3d.ofBottomCenter(context.getBlockPos().offset(context.getSide()));
		return trySummon(context.getWorld(), context.getPlayer(), spawnPos, context.getStack());
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		Vec3d look = user.getRotationVector();
		Vec3d spawnPos = user.getEntityPos().add(look.x * 3.0, 0.0, look.z * 3.0);
		return trySummon(world, user, spawnPos, user.getStackInHand(hand));
	}

	private ActionResult trySummon(World world, PlayerEntity player, Vec3d spawnPos, ItemStack stack) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			if (!world.isClient() && player != null) {
				player.sendMessage(Text.translatable("message.copper_inferno.calamity_sigil.peaceful"), true);
			}
			return ActionResult.FAIL;
		}
		if (world instanceof ServerWorld serverWorld) {
			MobEntity boss = this.bossFactory.apply(serverWorld);
			float yaw = player != null ? player.getYaw() + 180.0f : 0.0f;
			if (!nudgeToEmptySpace(serverWorld, boss, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), yaw)) {
				if (player != null) {
					player.sendMessage(Text.translatable("message.copper_inferno.calamity_sigil.blocked"), true);
				}
				return ActionResult.FAIL;
			}
			serverWorld.spawnEntity(boss);
			serverWorld.playSound(null, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(),
					SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.HOSTILE, 1.0f, 1.0f);
			if (player != null) {
				stack.decrementUnlessCreative(1, player);
			} else {
				stack.decrement(1);
			}
		}
		return ActionResult.SUCCESS;
	}

	/**
	 * Positions the boss at (x, y, z), nudging upward one block at a time (max 8) until its
	 * collision box is unobstructed ({@code CollisionView.isSpaceEmpty(Entity)}, so the boss
	 * never suffocates inside blocks). Returns false when no clear spot exists.
	 */
	private static boolean nudgeToEmptySpace(ServerWorld world, MobEntity boss,
			double x, double y, double z, float yaw) {
		for (int dy = 0; dy <= MAX_UPWARD_NUDGE; dy++) {
			boss.refreshPositionAndAngles(x, y + dy, z, yaw, 0.0f);
			if (world.isSpaceEmpty(boss)) {
				return true;
			}
		}
		return false;
	}
}
'''


# ---------------------------------------------------------------------------
# Java codegen: the 10 boss entity classes. HAND-WRITTEN (real logic), mirroring
# TheOxidizerEntity (aura + enrage) and InfernoTitanEntity (persisted phase two).
# Every API call verified via javap (see module docstring).
# ---------------------------------------------------------------------------

ENTITY_SOURCES = {}

ENTITY_SOURCES["EmberlordRavagerEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 1 "Emberlord Ravager": a colossal war beast wreathed in embers (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 400, ATTACK_DAMAGE 16, SCALE 2.0). Summoned with an
 * Emberlord Warhorn. Every 80 ticks it ignites every survival player it can see within 5
 * blocks; below 50% health it enrages with bonus movement speed (TheOxidizerEntity
 * pattern). Drops per {@code loot_table/entities/emberlord_ravager.json}.
 */
public class EmberlordRavagerEntity extends RavagerEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("emberlord_enrage");
	private static final int AURA_INTERVAL_TICKS = 80;
	private static final double AURA_RANGE = 5.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.emberlord_ravager"),
			BossBar.Color.RED, BossBar.Style.NOTCHED_10);

	public EmberlordRavagerEntity(EntityType<? extends RavagerEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			ventEmberAura(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/**
	 * Ignites every survival/adventure player the boss has line of sight to within 5 blocks
	 * (TheOxidizerEntity.ventCorrosiveCloud pattern); creative and spectator players and
	 * players behind walls are unaffected.
	 */
	private void ventEmberAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.setOnFireFor(4.0f);
		}
		world.spawnParticles(ParticleTypes.FLAME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.4, 0.5, 0.4, 0.02);
	}

	/**
	 * One-shot +0.05 flat movement speed below 50% health. Temporary modifiers are not
	 * persisted, so after a reload this simply re-applies on the next tick; the
	 * {@code hasModifier} guard keeps it from stacking.
	 */
	private void enrage() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_MODIFIER_ID, 0.05, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["SlagWarlordEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 2 "Slag Warlord": a slag-armored ravager warchief (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 500, ATTACK_DAMAGE 18, SCALE 2.2). Summoned with a
 * Slag War Banner. Below 50% health it enters phase two exactly once (flag persisted via
 * write/readCustomData, InfernoTitanEntity pattern): 2 vanilla vindicator lieutenants,
 * 100 ticks of Resistance and +6 attack damage. Drops per
 * {@code loot_table/entities/slag_warlord.json}.
 */
public class SlagWarlordEntity extends RavagerEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier WAR_FURY_MODIFIER_ID = CopperInferno.id("warlord_phase_two_fury");

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.slag_warlord"),
			BossBar.Color.YELLOW, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 warlord never re-spawns adds. */
	private boolean phaseTwo;

	public SlagWarlordEntity(EntityType<? extends RavagerEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			enterPhaseTwo(world);
		}
		if (this.phaseTwo) {
			// Temporary modifiers are not persisted; re-applies after a reload (guarded).
			applyWarFury();
		}
	}

	/** Phase 2 burst (InfernoTitanEntity pattern): 2 vindicator lieutenants + Resistance. */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			VindicatorEntity lieutenant = new VindicatorEntity(EntityType.VINDICATOR, world);
			lieutenant.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(lieutenant);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
	}

	/** One-shot +6 flat attack damage in phase two; the hasModifier guard prevents stacking. */
	private void applyWarFury() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(WAR_FURY_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					WAR_FURY_MODIFIER_ID, 6.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putBoolean(PHASE_TWO_KEY, this.phaseTwo);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.phaseTwo = view.getBoolean(PHASE_TWO_KEY, false);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["MoltenColossusEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 3 "Molten Colossus": a magma-veined iron colossus (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 600, ATTACK_DAMAGE 22, SCALE 2.5). Summoned with a
 * Colossus Effigy. Hunts players on sight (unlike the vanilla golem); every 100 ticks it
 * vents a molten wave that ignites and slows every survival player it can see within 6
 * blocks; below 50% health its fists gain +8 damage (TheOxidizerEntity pattern). Drops per
 * {@code loot_table/entities/molten_colossus.json}.
 */
public class MoltenColossusEntity extends IronGolemEntity {
	private static final Identifier MOLTEN_FURY_MODIFIER_ID = CopperInferno.id("colossus_molten_fury");
	private static final int AURA_INTERVAL_TICKS = 100;
	private static final double AURA_RANGE = 6.0;
	private static final int AURA_EFFECT_TICKS = 60;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.molten_colossus"),
			BossBar.Color.RED, BossBar.Style.NOTCHED_10);

	public MoltenColossusEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// Unlike the vanilla golem (only angered on attack), the boss hunts players on sight.
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	/**
	 * Blocks the inherited iron-golem right-click repair: without this, players could heal
	 * the boss mid-fight with iron ingots (TheOxidizerEntity pattern).
	 */
	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		return ActionResult.PASS;
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			ventMoltenWave(world);
		}
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/**
	 * Ignites + slows every survival/adventure player the boss has line of sight to within
	 * 6 blocks; creative and spectator players and players behind walls are unaffected.
	 */
	private void ventMoltenWave(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.setOnFireFor(3.0f);
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, AURA_EFFECT_TICKS, 0));
		}
		world.spawnParticles(ParticleTypes.LAVA,
				this.getX(), this.getBodyY(0.5), this.getZ(), 12, 0.5, 0.6, 0.5, 0.0);
	}

	/** One-shot +8 flat attack damage below 50% health; hasModifier guard prevents stacking. */
	private void enrage() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(MOLTEN_FURY_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					MOLTEN_FURY_MODIFIER_ID, 8.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["VerdigrisMonarchEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 4 "Verdigris Monarch": a regal, patina-crowned golem (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 550, ATTACK_DAMAGE 18, SCALE 2.2). Summoned with
 * the Verdigris Regalia. Hunts players on sight; every 100 ticks it saps every survival
 * player it can see within 6 blocks with Weakness + Mining Fatigue; below 50% health it
 * periodically grants itself Regeneration. Drops per
 * {@code loot_table/entities/verdigris_monarch.json}.
 */
public class VerdigrisMonarchEntity extends IronGolemEntity {
	/** Verdigris green, matching the oxidized copper palette. */
	private static final DustParticleEffect PATINA_BURST = new DustParticleEffect(0x57A07B, 1.0f);
	private static final int AURA_INTERVAL_TICKS = 100;
	private static final double AURA_RANGE = 6.0;
	private static final int AURA_EFFECT_TICKS = 80;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.verdigris_monarch"),
			BossBar.Color.GREEN, BossBar.Style.PROGRESS);

	public VerdigrisMonarchEntity(EntityType<? extends IronGolemEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		// Unlike the vanilla golem (only angered on attack), the boss hunts players on sight.
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
	}

	/**
	 * Blocks the inherited iron-golem right-click repair: without this, players could heal
	 * the boss mid-fight with iron ingots (TheOxidizerEntity pattern).
	 */
	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		return ActionResult.PASS;
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			ventSappingAura(world);
			if (this.getHealth() < this.getMaxHealth() * 0.5f) {
				// Royal mending: periodic self-Regeneration once wounded below half.
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1));
			}
		}
	}

	/**
	 * Weakness + Mining Fatigue to every survival/adventure player the boss has line of
	 * sight to within 6 blocks, plus a verdigris dust burst; creative and spectator players
	 * and players behind walls are unaffected.
	 */
	private void ventSappingAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, AURA_EFFECT_TICKS, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, AURA_EFFECT_TICKS, 0));
		}
		world.spawnParticles(PATINA_BURST,
				this.getX(), this.getBodyY(0.5), this.getZ(), 30, 0.35, 0.45, 0.35, 0.05);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["AshkingWitherEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 5 "Ashking Wither": the skeletal king of the ash (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 350, ATTACK_DAMAGE 12, SCALE 2.0). Summoned with an
 * Ashking Skull Idol. Every 100 ticks it withers every survival player it can see within 6
 * blocks; below 50% health it enters phase two exactly once (persisted flag,
 * InfernoTitanEntity pattern) hardening with Resistance, and keeps a guarded speed boost
 * while in phase two. Drops per {@code loot_table/entities/ashking_wither.json}.
 */
public class AshkingWitherEntity extends WitherSkeletonEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier ASH_HASTE_MODIFIER_ID = CopperInferno.id("ashking_phase_two_haste");
	private static final int AURA_INTERVAL_TICKS = 100;
	private static final double AURA_RANGE = 6.0;
	private static final int AURA_EFFECT_TICKS = 60;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.ashking_wither"),
			BossBar.Color.PURPLE, BossBar.Style.NOTCHED_10);

	/** One-shot phase flag, persisted so a reloaded phase-2 king never re-buffs Resistance. */
	private boolean phaseTwo;

	public AshkingWitherEntity(EntityType<? extends WitherSkeletonEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Summoned bosses skip initialize(); hand over the vanilla stone sword so the
			// skeleton's melee goal engages (updateAttackType reads the held stack).
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			this.updateAttackType();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			ventWitherAura(world);
		}
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
		}
		if (this.phaseTwo) {
			// Temporary modifiers are not persisted; re-applies after a reload (guarded).
			applyAshHaste();
		}
	}

	/**
	 * Wither I to every survival/adventure player the boss has line of sight to within 6
	 * blocks, plus an ash smoke burst; creative and spectator players and players behind
	 * walls are unaffected.
	 */
	private void ventWitherAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, AURA_EFFECT_TICKS, 0));
		}
		world.spawnParticles(ParticleTypes.LARGE_SMOKE,
				this.getX(), this.getBodyY(0.5), this.getZ(), 24, 0.4, 0.6, 0.4, 0.01);
	}

	/** One-shot +0.06 flat movement speed in phase two; hasModifier guard prevents stacking. */
	private void applyAshHaste() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ASH_HASTE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ASH_HASTE_MODIFIER_ID, 0.06, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putBoolean(PHASE_TWO_KEY, this.phaseTwo);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.phaseTwo = view.getBoolean(PHASE_TWO_KEY, false);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["SootReaperEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 6 "Soot Reaper": a spectral wither harvester (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 300, ATTACK_DAMAGE 10, SCALE 1.8). Summoned with a
 * Reaper Knell. Its scythe strikes blind and wither the victim (tryAttack hook on top of
 * the vanilla wither-skeleton wither); below 50% health it enrages with bonus movement
 * speed. Drops per {@code loot_table/entities/soot_reaper.json}.
 */
public class SootReaperEntity extends WitherSkeletonEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("reaper_enrage");
	private static final int ON_HIT_EFFECT_TICKS = 60;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.soot_reaper"),
			BossBar.Color.WHITE, BossBar.Style.PROGRESS);

	public SootReaperEntity(EntityType<? extends WitherSkeletonEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
			// Summoned bosses skip initialize(); hand over the vanilla stone sword so the
			// skeleton's melee goal engages (updateAttackType reads the held stack).
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
			this.updateAttackType();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	/** Scythe strike: Blindness + Wither on every landed hit. */
	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		if (!super.tryAttack(world, target)) {
			return false;
		}
		if (target instanceof LivingEntity living) {
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ON_HIT_EFFECT_TICKS, 0));
			living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, ON_HIT_EFFECT_TICKS, 0));
		}
		return true;
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/**
	 * One-shot +0.06 flat movement speed below 50% health. Temporary modifiers are not
	 * persisted, so after a reload this simply re-applies on the next tick; the
	 * {@code hasModifier} guard keeps it from stacking.
	 */
	private void enrage() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_MODIFIER_ID, 0.06, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["CinderSovereignEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 7 "Cinder Sovereign": a blazing tyrant of cinders (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 450, SCALE 2.5). Summoned with a Cinder Beacon.
 * On top of the vanilla blaze volleys, every 70 ticks with a target it erupts in a ring
 * of 8 small fireballs (SmallFireballEntity(World, LivingEntity, Vec3d), the vanilla
 * blaze projectile). Drops per {@code loot_table/entities/cinder_sovereign.json}.
 */
public class CinderSovereignEntity extends BlazeEntity {
	private static final int BARRAGE_INTERVAL_TICKS = 70;
	private static final int BARRAGE_COUNT = 8;
	private static final double BARRAGE_SPEED = 0.4;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.cinder_sovereign"),
			BossBar.Color.YELLOW, BossBar.Style.NOTCHED_10);

	public CinderSovereignEntity(EntityType<? extends BlazeEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.getTarget() != null && this.age % BARRAGE_INTERVAL_TICKS == 0) {
			fireballRing(world);
		}
	}

	/** Ring of 8 small fireballs radiating horizontally from the boss, plus a flame burst. */
	private void fireballRing(ServerWorld world) {
		for (int i = 0; i < BARRAGE_COUNT; i++) {
			double angle = Math.PI * 2.0 * i / BARRAGE_COUNT;
			Vec3d velocity = new Vec3d(Math.cos(angle) * BARRAGE_SPEED, 0.05, Math.sin(angle) * BARRAGE_SPEED);
			world.spawnEntity(new SmallFireballEntity(world, this, velocity));
		}
		world.spawnParticles(ParticleTypes.FLAME,
				this.getX(), this.getBodyY(0.5), this.getZ(), 20, 0.3, 0.4, 0.3, 0.02);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["PyreTyrantEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 8 "Pyre Tyrant": a colossal blaze warlord (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 500, SCALE 2.8). Summoned with a Tyrant Pyre
 * Brand. Phase 1 is pure vanilla blaze AI (ranged fireballs); below 50% health it enters
 * phase two exactly once (flag persisted via write/readCustomData, InfernoTitanEntity
 * pattern): 3 vanilla blaze minions, 100 ticks of Resistance, a melee charge goal and +4
 * attack damage. Drops per {@code loot_table/entities/pyre_tyrant.json}.
 */
public class PyreTyrantEntity extends BlazeEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final Identifier MELEE_BOOST_MODIFIER_ID = CopperInferno.id("tyrant_phase_two_melee");

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.pyre_tyrant"),
			BossBar.Color.RED, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 tyrant never re-spawns minions. */
	private boolean phaseTwo;
	/** Goals are not persisted; tracked separately so a reloaded tyrant re-adds the charge. */
	private boolean meleeGoalAdded;

	public PyreTyrantEntity(EntityType<? extends BlazeEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			enterPhaseTwo(world);
		}
		if (this.phaseTwo && !this.meleeGoalAdded) {
			this.meleeGoalAdded = true;
			this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, true));
			applyMeleeBoost();
		}
	}

	/** One-shot +4 flat attack damage for the phase-2 charge; hasModifier guard prevents stacking. */
	private void applyMeleeBoost() {
		EntityAttributeInstance damage = this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);
		if (damage != null && !damage.hasModifier(MELEE_BOOST_MODIFIER_ID)) {
			damage.addTemporaryModifier(new EntityAttributeModifier(
					MELEE_BOOST_MODIFIER_ID, 4.0, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** Phase 2 burst: 3 vanilla blaze minions + 100 ticks of Resistance (melee goal in mobTick). */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 3; i++) {
			BlazeEntity minion = new BlazeEntity(EntityType.BLAZE, world);
			minion.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(minion);
		}
		this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putBoolean(PHASE_TWO_KEY, this.phaseTwo);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.phaseTwo = view.getBoolean(PHASE_TWO_KEY, false);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["FurnaceFiendEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.CopperInferno;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 9 "Furnace Fiend": an axe-swinging fiend from the furnace depths (attributes
 * in {@link CalamitiesFeature}: MAX_HEALTH 320, SCALE 1.8; vanilla vindicator attack
 * damage). Summoned with a Fiend Ember Key. Every landed axe blow sets the victim on fire
 * (tryAttack hook); below 50% health it enrages with bonus movement speed. Drops per
 * {@code loot_table/entities/furnace_fiend.json}.
 */
public class FurnaceFiendEntity extends VindicatorEntity {
	private static final Identifier ENRAGE_MODIFIER_ID = CopperInferno.id("fiend_enrage");

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.furnace_fiend"),
			BossBar.Color.YELLOW, BossBar.Style.NOTCHED_6);

	public FurnaceFiendEntity(EntityType<? extends VindicatorEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	/** Furnace-hot axe: every landed hit sets the victim on fire for 4 seconds. */
	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		if (!super.tryAttack(world, target)) {
			return false;
		}
		target.setOnFireFor(4.0f);
		return true;
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.getHealth() < this.getMaxHealth() * 0.5f) {
			enrage();
		}
	}

	/**
	 * One-shot +0.06 flat movement speed below 50% health. Temporary modifiers are not
	 * persisted, so after a reload this simply re-applies on the next tick; the
	 * {@code hasModifier} guard keeps it from stacking.
	 */
	private void enrage() {
		EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
		if (speed != null && !speed.hasModifier(ENRAGE_MODIFIER_ID)) {
			speed.addTemporaryModifier(new EntityAttributeModifier(
					ENRAGE_MODIFIER_ID, 0.06, EntityAttributeModifier.Operation.ADD_VALUE));
		}
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

ENTITY_SOURCES["CalamityHeraldEntity"] = '''package net.sonic0810.copperinferno.feature.calamities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.sonic0810.copperinferno.core.boss.BossBarHolder;

/**
 * Calamity 10 "Calamity Herald": the doom-crier of the calamities (attributes in
 * {@link CalamitiesFeature}: MAX_HEALTH 380, ATTACK_DAMAGE 14, SCALE 2.0). Summoned with a
 * Herald Omen Sigil. Every 120 ticks it marks every survival player it can see within 8
 * blocks with Glowing + Slowness; below 50% health it enters phase two exactly once
 * (persisted flag, InfernoTitanEntity pattern), calling 2 vanilla vindicator adds. Drops
 * per {@code loot_table/entities/calamity_herald.json}.
 */
public class CalamityHeraldEntity extends VindicatorEntity {
	private static final String PHASE_TWO_KEY = "PhaseTwo";
	private static final int AURA_INTERVAL_TICKS = 120;
	private static final double AURA_RANGE = 8.0;

	private final BossBarHolder bossBar = new BossBarHolder(
			Text.translatable("entity.copper_inferno.calamity_herald"),
			BossBar.Color.PURPLE, BossBar.Style.PROGRESS);

	/** One-shot phase flag, persisted so a reloaded phase-2 herald never re-spawns adds. */
	private boolean phaseTwo;

	public CalamityHeraldEntity(EntityType<? extends VindicatorEntity> type, World world) {
		super(type, world);
		// A boss must never despawn, whatever summoned it (item, spawn egg, /summon).
		if (!world.isClient()) {
			this.setPersistent();
		}
	}

	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.onStartedTrackingBy(player);
	}

	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.onStoppedTrackingBy(player);
	}

	@Override
	protected void mobTick(ServerWorld world) {
		super.mobTick(world);
		this.bossBar.update(this);
		if (this.age % AURA_INTERVAL_TICKS == 0) {
			ventOmenAura(world);
		}
		if (!this.phaseTwo && this.getHealth() < this.getMaxHealth() * 0.5f) {
			this.phaseTwo = true;
			enterPhaseTwo(world);
		}
	}

	/**
	 * Glowing + Slowness to every survival/adventure player the boss has line of sight to
	 * within 8 blocks, plus an omen burst; creative and spectator players and players
	 * behind walls are unaffected.
	 */
	private void ventOmenAura(ServerWorld world) {
		for (PlayerEntity player : world.getEntitiesByClass(PlayerEntity.class,
				this.getBoundingBox().expand(AURA_RANGE),
				player -> !player.isSpectator() && !player.isCreative() && this.canSee(player))) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0));
		}
		world.spawnParticles(ParticleTypes.WITCH,
				this.getX(), this.getBodyY(0.5), this.getZ(), 24, 0.5, 0.6, 0.5, 0.0);
	}

	/** Phase 2 burst (InfernoTitanEntity pattern): 2 vanilla vindicator adds. */
	private void enterPhaseTwo(ServerWorld world) {
		for (int i = 0; i < 2; i++) {
			VindicatorEntity add = new VindicatorEntity(EntityType.VINDICATOR, world);
			add.refreshPositionAndAngles(
					this.getX() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getY(),
					this.getZ() + (this.random.nextDouble() - 0.5) * 3.0,
					this.getYaw(), 0.0f);
			world.spawnEntity(add);
		}
	}

	@Override
	protected void writeCustomData(WriteView view) {
		super.writeCustomData(view);
		view.putBoolean(PHASE_TWO_KEY, this.phaseTwo);
	}

	@Override
	protected void readCustomData(ReadView view) {
		super.readCustomData(view);
		this.phaseTwo = view.getBoolean(PHASE_TWO_KEY, false);
	}

	/** mobTick stops during the death animation; keep the boss bar synced (empty) as it dies. */
	@Override
	protected void updatePostDeath() {
		super.updatePostDeath();
		this.bossBar.update(this);
	}
}
'''

assert set(ENTITY_SOURCES) == {b["cls"] for b in BOSSES}


# ---------------------------------------------------------------------------
# Java codegen: per-boss renderer subclasses + CalamitiesFeatureClient.java. Every
# boss keeps its vanilla MODEL/animations but swaps in its own recolored entity
# texture: the subclass overrides the render-state getTexture overload (class,
# Context-only ctor and overload all javap-verified non-final; see module docstring).
# ---------------------------------------------------------------------------

def renderer_source(b: dict) -> str:
    base = b["base"]
    vanilla = RENDERERS[base]
    state = RENDER_STATES[base]
    cls = renderer_class_of(b)
    imports = sorted([
        "net.minecraft.client.render.entity.EntityRendererFactory",
        f"net.minecraft.client.render.entity.{vanilla}",
        f"net.minecraft.client.render.entity.state.{state}",
        "net.minecraft.util.Identifier",
    ])
    L = [f"package {genlib.PKG_ROOT}.feature.calamities.client;", ""]
    L += [f"import {imp};" for imp in imports]
    L += ["", "/**",
          f" * Renderer for the \"{b['en']}\" calamity boss: the vanilla {vanilla}",
          f" * (public non-final, Context-only ctor, verified via javap) with only the",
          f" * getTexture({state}) overload swapped to the boss's recolored",
          f" * texture (emitted by devtools/gen/calamities_gen.py into",
          f" * src/client/resources/assets/copper_inferno/textures/entity/{b['bid']}.png).",
          " * Model and animations stay vanilla; the texture is a pure luminance->palette",
          " * remap of the base mob's texture, so all UV mapping is preserved.",
          " */",
          f"public class {cls} extends {vanilla} {{",
          "\tprivate static final Identifier TEXTURE =",
          f"\t\t\tIdentifier.of(\"copper_inferno\", \"textures/entity/{b['bid']}.png\");",
          "",
          f"\tpublic {cls}(EntityRendererFactory.Context context) {{",
          "\t\tsuper(context);",
          "\t}",
          "",
          "\t@Override",
          f"\tpublic Identifier getTexture({state} state) {{",
          "\t\treturn TEXTURE;",
          "\t}",
          "}"]
    return "\n".join(L) + "\n"


def client_source() -> str:
    L = [f"package {genlib.PKG_ROOT}.feature.calamities.client;", "",
         "import net.minecraft.client.render.entity.EntityRendererFactories;",
         f"import {genlib.PKG_ROOT}.feature.calamities.CalamitiesFeature;", "",
         "/**",
         " * Client-side setup for the calamity bosses: each registers its own tiny renderer",
         " * subclass (vanilla renderer + the boss's recolored entity texture; every ctor is",
         " * Context-only, verified via javap) - the SCALE attribute makes them loom. The",
         " * bosses extend the matching vanilla entities, so the factories fit the",
         " * register(EntityType&lt;? extends T&gt;, EntityRendererFactory&lt;T&gt;)",
         " * bound; the vanilla register method is access-widened by Fabric's transitive",
         " * access wideners (same proven pattern as the infernoboss feature).",
         " */",
         "public final class CalamitiesFeatureClient {",
         "\tprivate CalamitiesFeatureClient() {",
         "\t}", "",
         "\tpublic static void initClient() {"]
    for b in BOSSES:
        L.append(f"\t\tEntityRendererFactories.register(CalamitiesFeature.{field_of(b['bid'])}, "
                 f"{renderer_class_of(b)}::new);")
    L += ["\t}", "}"]
    return "\n".join(L) + "\n"


# ---------------------------------------------------------------------------
# Handbook ("bosses" category): 1 lore page per boss + 1 page per recipe.
# ---------------------------------------------------------------------------

def handbook_entries() -> list:
    entries = []
    for b in BOSSES:
        entries.append(("bosses", b["bid"], f"{NS}:{b['bid']}_spawn_egg",
                        None, None, None, 0, b["lore_en"], b["lore_de"]))
    for b in BOSSES:
        s_id, s_en, s_de = b["summon"]
        corner_en, corner_de = ING[b["corner"]]
        edge_en, edge_de = ING[b["edge"]]
        center_en, center_de = ING[b["center"]]
        entries.append((
            "bosses", f"{s_id}_recipe", f"{NS}:{s_id}", f"calamities/{s_id}",
            summon_grid(b), f"{NS}:{s_id}", 1,
            f"{s_en} - ring {center_en} with 4x {corner_en} and 4x {edge_en}. "
            f"Use it to summon the {b['en']}; consumed on success, refused on peaceful.",
            f"{s_de} - {center_de} mit 4x {corner_de} und 4x {edge_de} umringen. "
            f"Benutzen, um den {b['de']} zu beschw\u00f6ren; wird bei Erfolg verbraucht, "
            f"auf Friedlich verweigert."))
        t_id, t_en, t_de = b["trophy"]
        d1_id, d1_en, d1_de = b["drop1"]
        d2_id, d2_en, d2_de = b["drop2"]
        entries.append((
            "bosses", f"{t_id}_recipe", f"{NS}:{t_id}", f"calamities/{t_id}",
            [f"{NS}:{d1_id}", f"{NS}:{d2_id}", f"{NS}:{d2_id}",
             "minecraft:gold_block", "", "", "", "", ""],
            f"{NS}:{t_id}", 1,
            f"{t_en} - shapeless: 1x {d1_en} + 2x {d2_en} + 1x Gold Block. "
            f"The epic trophy of the {b['en']}.",
            f"{t_de} - formlos: 1x {d1_de} + 2x {d2_de} + 1x Goldblock. "
            f"Die epische Troph\u00e4e zum Boss {b['de']}."))
    return entries


def emit_java() -> None:
    FEATURE_DIR.mkdir(parents=True, exist_ok=True)
    CLIENT_DIR.mkdir(parents=True, exist_ok=True)

    (FEATURE_DIR / "CalamitiesFeature.java").write_text(feature_source(), encoding="utf-8")
    (FEATURE_DIR / "CalamitySigilItem.java").write_text(SIGIL_SOURCE, encoding="utf-8")
    for cls, source in ENTITY_SOURCES.items():
        (FEATURE_DIR / f"{cls}.java").write_text(source, encoding="utf-8")

    handbook_doc = [
        "Handbook pages for the calamity bosses: one \"bosses\" lore page per boss",
        "(summon ritual, phases/mechanic, drops) plus one page for every recipe JSON under",
        "{@code data/copper_inferno/recipe/calamities/} (summon ring crafts + shapeless",
        "trophy crafts). Texts and grids mirror the JSONs emitted by",
        "{@code devtools/gen/calamities_gen.py}; {@code devtools/check_handbook.py} parses",
        "the inline {@code new HandbookEntry(...)} literals positionally, so keep them",
        "inline.",
    ]
    handbook_src = genlib.java_handbook_class("calamities", "CalamitiesHandbook",
                                              handbook_doc, handbook_entries())
    (FEATURE_DIR / "CalamitiesHandbook.java").write_text(handbook_src, encoding="utf-8")

    (CLIENT_DIR / "CalamitiesFeatureClient.java").write_text(client_source(), encoding="utf-8")
    for b in BOSSES:
        (CLIENT_DIR / f"{renderer_class_of(b)}.java").write_text(renderer_source(b),
                                                                 encoding="utf-8")


# ---------------------------------------------------------------------------
# Hook file (devtools/hooks/calamities.txt; format per devtools/hooks/README.md).
# ---------------------------------------------------------------------------

def emit_hooks(item_count: int, recipe_count: int, handbook_count: int) -> None:
    lines = [
        "# calamities feature hooks (format: devtools/hooks/README.md)", "",
        "[init]",
        "import net.sonic0810.copperinferno.feature.calamities.CalamitiesFeature;",
        "\t\tCalamitiesFeature.init();", "",
        "[client-init]",
        "import net.sonic0810.copperinferno.feature.calamities.client.CalamitiesFeatureClient;",
        "\t\tCalamitiesFeatureClient.initClient();", "",
        "[recipe-dir]",
        "calamities", "",
        "[counts]",
        f"bosses: {len(BOSSES)}",
        f"items: {item_count}",
        f"recipes: {recipe_count}",
        f"handbook-entries: {handbook_count}", "",
    ]
    path = ROOT / "devtools" / "hooks" / "calamities.txt"
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text("\n".join(lines), encoding="utf-8")


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    ids = emit_item_assets()
    tex_count = emit_textures()
    entity_tex_count = emit_entity_textures()
    emit_loot_tables()
    recipe_count = emit_recipes()

    lang_en, lang_de = lang_dicts()
    assert set(lang_en) == set(lang_de), "EN and DE lang key sets must match"
    genlib.lang_fragments(ASSETS, "calamities", lang_en, lang_de)

    emit_java()
    hb_count = len(handbook_entries())
    emit_hooks(len(ids), recipe_count, hb_count)

    summons = [b["summon"][0] for b in BOSSES]
    trophies = [b["trophy"][0] for b in BOSSES]
    assert len(BOSSES) == 10 and len(summons) == 10 and len(trophies) == 10
    assert len(ids) == 50 and len(set(ids)) == 50
    assert tex_count == 50
    assert entity_tex_count in (0, 10)  # 0 only when the client jar is absent
    assert recipe_count == 20
    assert hb_count == 30
    print(f"calamities_gen: {len(BOSSES)} bosses / {len(ids)} items / "
          f"{entity_tex_count} entity textures / {recipe_count} recipes / "
          f"{hb_count} handbook entries / {len(lang_en)} lang keys (EN==DE) generated.")


if __name__ == "__main__":
    main()
