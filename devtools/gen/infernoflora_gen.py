#!/usr/bin/env python3
"""Inferno flora asset generator for COPPER INFERNO 1 (mod id: copper_inferno).

Generates ALL JSON assets/data + 16x16 PNG textures for the 22 infernoflora blocks
(scorched wood set, wart blocks, six cross-model plants, ember moss, fungal utility
blocks), directly into src/main/resources so they ship with the mod.

Idempotent: writes a fixed set of files it owns (never deletes/globs), and all texture
noise is seeded per texture name, so re-runs produce identical bytes.

NOTE: JSON emission is opt-in via --write-json; the JSON in src/main/resources is
authoritative — by default this script writes ONLY PNGs.

JSON formats are NOT invented: every blockstate/model/item/loot/recipe template is read
at runtime with zipfile straight out of the vanilla 1.21.9 client jar
(~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar) and re-targeted by replacing
the full vanilla id references:
  - wood set   <- crimson_stem / stripped_crimson_stem / crimson_hyphae /
                  stripped_crimson_hyphae / crimson_planks / crimson_slab /
                  crimson_stairs / crimson_fence / crimson_fence_gate
  - wart blocks <- nether_wart_block (cube_all)
  - plants      <- crimson_fungus (block/cross parent, item/generated item model)
  - moss        <- moss_block + moss_carpet (block/carpet parent; the carpet model
                   reuses the moss BLOCK texture exactly like vanilla)
  - lights/nest <- shroomlight (cube_all)
  - loot        <- crimson_fungus (drop-self) and crimson_slab (double-slab count-2)

RECIPE CHOICES (>=8, collision-checked with devtools/check_recipe_collisions.py):
  - scorched_planks: vanilla crimson_planks.json with the #minecraft:crimson_stems tag
    ingredient replaced by an explicit alternatives LIST of the four scorched
    stem/hyphae ids (no mod tag needed) -> 4.
  - scorched_slab ("###" -> 6), scorched_stairs (stair pattern -> 4), scorched_fence
    (W#W/W#W with sticks -> 3), scorched_fence_gate (#W#/#W# -> 1), scorched_hyphae
    (2x2 stems -> 3): exact vanilla crimson_* schemas with ids swapped.
  - stripped_scorched_stem / stripped_scorched_hyphae: shapeless 1:1 from the
    unstripped block. DOCUMENTED SIMPLIFICATION: stripping is a crafting recipe, NOT an
    axe right-click interaction (single-mod-id inputs, collision-free).
  - ember_wart_block: vanilla nether_wart_block.json schema, 9x ember_fungus -> 1.
  - ember_moss_carpet: vanilla moss_carpet.json schema, "##" ember_moss_block -> 3.
"""

import json
import random
import sys
import zipfile
from pathlib import Path

from PIL import Image

# Non-PNG output (blockstates/models/items/loot/recipes/lang) is opt-in; the JSON
# already in src/main/resources is authoritative.
WRITE_JSON = "--write-json" in sys.argv  # default False -> textures/*.png only

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / "copper_inferno"
DATA = RES / "data" / "copper_inferno"
JAR = Path("~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar").expanduser()

MODID = "copper_inferno"

ALL_IDS = [
    "scorched_stem", "stripped_scorched_stem", "scorched_hyphae", "stripped_scorched_hyphae",
    "scorched_planks", "scorched_slab", "scorched_stairs", "scorched_fence", "scorched_fence_gate",
    "scorched_wart_block", "ember_wart_block",
    "ember_fungus", "ash_sprouts", "cinder_roots", "smolder_bloom", "ashen_grass", "spore_cluster",
    "ember_moss_block", "ember_moss_carpet", "glowing_spore_block", "fungal_light", "cinder_nest",
]

PLANTS = ["ember_fungus", "ash_sprouts", "cinder_roots", "smolder_bloom", "ashen_grass", "spore_cluster"]

# German display names (EN names are auto-derived from the id).
DE_NAMES = {
    "scorched_stem": "Versengter Stiel",
    "stripped_scorched_stem": "Entrindeter versengter Stiel",
    "scorched_hyphae": "Versengte Hyphen",
    "stripped_scorched_hyphae": "Entrindete versengte Hyphen",
    "scorched_planks": "Versengte Bretter",
    "scorched_slab": "Versengte Stufe",
    "scorched_stairs": "Versengte Treppe",
    "scorched_fence": "Versengter Zaun",
    "scorched_fence_gate": "Versengtes Zauntor",
    "scorched_wart_block": "Versengter Warzenblock",
    "ember_wart_block": "Glutwarzenblock",
    "ember_fungus": "Glutpilz",
    "ash_sprouts": "Aschensprossen",
    "cinder_roots": "Schlackenwurzeln",
    "smolder_bloom": "Schwelbl\u00fcte",
    "ashen_grass": "Aschengras",
    "ember_moss_block": "Glutmoosblock",
    "ember_moss_carpet": "Glutmoosteppich",
    "glowing_spore_block": "Leuchtsporenblock",
    "fungal_light": "Pilzlicht",
    "spore_cluster": "Sporenhaufen",
    "cinder_nest": "Schlackennest",
}


def write_json(path: Path, obj) -> None:
    if not WRITE_JSON:
        return
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False) + "\n",
                    encoding="utf-8")


_jar = None


def jar_json(path: str, repl: dict[str, str]):
    """Read a vanilla JSON template from the client jar and re-target the full id
    references (longest key first so e.g. stripped_crimson wins over crimson)."""
    global _jar
    if _jar is None:
        _jar = zipfile.ZipFile(JAR)
    text = _jar.read(path).decode("utf-8")
    for old in sorted(repl, key=len, reverse=True):
        text = text.replace(old, repl[old])
    return json.loads(text)


# The wood-set replacement: vanilla crimson_* names map 1:1 onto scorched_* names, so a
# prefix replace on the full "minecraft:block/..." refs retargets stems, planks, slabs,
# stairs (incl. _inner/_outer), fences and gates while leaving vanilla parents
# (minecraft:block/cube_column, slab, stairs, fence_*, template_fence_gate*) untouched.
WOOD_BLOCK_REPL = {
    "minecraft:block/stripped_crimson": f"{MODID}:block/stripped_scorched",
    "minecraft:block/crimson": f"{MODID}:block/scorched",
}


def display_name(block_id: str) -> str:
    return " ".join(w.capitalize() for w in block_id.split("_"))


# ---------------------------------------------------------------------------
# Texture helpers (16x16, deterministic per name)
# ---------------------------------------------------------------------------

def clamp(v: int) -> int:
    return max(0, min(255, v))


def jitter(rnd: random.Random, rgb, amount=3):
    d = rnd.randint(-amount, amount)
    return (clamp(rgb[0] + d), clamp(rgb[1] + d), clamp(rgb[2] + d))


def shade(rgb, amount):
    return (clamp(rgb[0] + amount), clamp(rgb[1] + amount), clamp(rgb[2] + amount))


def rgba(rgb):
    return (rgb[0], rgb[1], rgb[2], 255)


# Scorched bark palette: dark gray-brown.
BARK_BASE = (0x3E, 0x34, 0x2E)
BARK_DARK = (0x2A, 0x22, 0x1E)
BARK_LIGHT = (0x54, 0x47, 0x3E)
# Stripped wood: lighter, ashier gray-brown.
STRIP_BASE = (0x6B, 0x59, 0x49)
STRIP_DARK = (0x57, 0x47, 0x3A)
STRIP_LIGHT = (0x7C, 0x69, 0x57)
# Ember glow (cracks, flecks, blooms).
EMBER = (0xD9, 0x5B, 0x23)
EMBER_BRIGHT = (0xF5, 0x8A, 0x2E)
EMBER_CORE = (0xFF, 0xD8, 0x7A)
# Teal fungus palette (caps, spores, wart).
TEAL_LIGHT = (0x63, 0xD1, 0xBC)
TEAL_MID = (0x2E, 0x9C, 0x8C)
TEAL_DARK = (0x1D, 0x6B, 0x60)


def make_bark(name: str) -> Image.Image:
    """Scorched stem side: vertical dark gray-brown bark ridges with glowing ember
    cracks running through the crevices."""
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    # per-column ridge profile (bark striations)
    col = [rnd.choice((-1, 0, 0, 1)) for _ in range(16)]
    for x in range(16):
        breaks = {rnd.randrange(16) for _ in range(3)}  # horizontal bark breaks
        for y in range(16):
            if col[x] < 0:
                base = BARK_DARK
            elif col[x] > 0:
                base = BARK_LIGHT
            else:
                base = BARK_BASE
            if y in breaks:
                base = shade(base, -14)
            px[x, y] = jitter(rnd, base, 3)
    # ember cracks: 3 short vertical glowing seams in dark crevices
    for _ in range(3):
        cx = rnd.randrange(16)
        cy = rnd.randrange(2, 9)
        length = rnd.randint(3, 6)
        for i in range(length):
            y = (cy + i) % 16
            px[cx, y] = EMBER_BRIGHT if i == length // 2 else EMBER
    return img


def make_stripped(name: str) -> Image.Image:
    """Stripped scorched wood: smooth ashy gray-brown with vertical grain lines."""
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    grain = {rnd.randrange(16) for _ in range(4)}
    for x in range(16):
        for y in range(16):
            base = STRIP_DARK if x in grain else (STRIP_LIGHT if x % 5 == 2 else STRIP_BASE)
            if rnd.random() < 0.04:
                base = shade(base, -10)
            px[x, y] = jitter(rnd, base, 3)
    return img


def make_stem_top(name: str, ring_light, ring_dark, bark_dark) -> Image.Image:
    """Stem end: 2px bark rim, concentric growth rings, glowing ember heart."""
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            e = min(x, y, 15 - x, 15 - y)
            if e <= 1:
                base = bark_dark if (x + y) % 3 else shade(bark_dark, 10)
            else:
                d = max(abs(x - 7.5), abs(y - 7.5))  # square rings
                base = ring_light if int(d) % 2 == 0 else ring_dark
            px[x, y] = jitter(rnd, base, 3)
    for x, y in ((7, 7), (8, 7), (7, 8), (8, 8)):
        px[x, y] = EMBER_BRIGHT
    px[8, 7] = EMBER_CORE
    return img


def make_planks(name: str) -> Image.Image:
    """Scorched planks: 4px courses with deep seams, staggered vertical joints and the
    occasional ember fleck."""
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    base = (0x50, 0x43, 0x39)
    light = (0x5F, 0x51, 0x45)
    seam = (0x2C, 0x24, 0x1F)
    for y in range(16):
        course, ly = divmod(y, 4)
        for x in range(16):
            if ly == 3:
                c = seam
            elif ly == 0:
                c = light
            else:
                c = base
                if (x + 6 * course) % 8 == 3:  # staggered vertical joints
                    c = seam
            px[x, y] = jitter(rnd, c, 3)
    for _ in range(2):
        px[rnd.randrange(16), rnd.randrange(16)] = EMBER
    return img


def make_wart(name: str, light, mid, dark) -> Image.Image:
    """Wart block: clumpy 2x2 wart nodules with dark grout between them."""
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            px[x, y] = jitter(rnd, shade(dark, -8), 3)
    for cy in range(0, 16, 3):
        for cx in range(0, 16, 3):
            ox, oy = rnd.randrange(2), rnd.randrange(2)
            for dy in range(2):
                for dx in range(2):
                    x, y = (cx + ox + dx) % 16, (cy + oy + dy) % 16
                    c = light if (dx == 0 and dy == 0) else (mid if dx != dy else dark)
                    px[x, y] = jitter(rnd, c, 4)
    return img


def make_moss(name: str) -> Image.Image:
    """Ember moss: mottled teal-green tufts with rare ember specks."""
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    light = (0x4E, 0x9E, 0x7A)
    mid = (0x3B, 0x7F, 0x63)
    dark = (0x2A, 0x5C, 0x49)
    for y in range(16):
        for x in range(16):
            r = rnd.random()
            base = light if r < 0.25 else (dark if r > 0.8 else mid)
            px[x, y] = jitter(rnd, base, 4)
    for _ in range(3):
        px[rnd.randrange(16), rnd.randrange(16)] = EMBER
    return img


def make_glowing_spore(name: str) -> Image.Image:
    """Glowing spore block: dark teal base studded with bright spore dots + halos."""
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    base = (0x1E, 0x4F, 0x4A)
    for y in range(16):
        for x in range(16):
            px[x, y] = jitter(rnd, shade(base, -4 if (x + y) % 2 else 0), 3)
    for _ in range(9):
        x, y = rnd.randrange(1, 15), rnd.randrange(1, 15)
        for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)):
            px[x + dx, y + dy] = jitter(rnd, TEAL_MID, 4)
        px[x, y] = (0x9A, 0xF2, 0xDB)
    return img


def make_fungal_light(name: str) -> Image.Image:
    """Fungal light: shroomlight-style amber honeycomb cells with teal cell walls and a
    few fully transparent vent holes (hence the CUTOUT render layer + nonOpaque)."""
    rnd = random.Random(name)
    img = Image.new("RGBA", (16, 16))
    px = img.load()
    for y in range(16):
        for x in range(16):
            if x % 4 == 0 or y % 4 == 0:  # cell walls
                px[x, y] = rgba(jitter(rnd, TEAL_DARK, 4))
            else:
                cx, cy = x % 4, y % 4
                c = EMBER_CORE if (cx == 2 and cy == 2) else (EMBER_BRIGHT if cx != 1 or cy != 1 else EMBER)
                px[x, y] = rgba(jitter(rnd, c, 4))
    # transparent vent holes at deterministic cell centers
    for hx, hy in ((2, 6), (10, 2), (6, 10), (14, 14)):
        px[hx, hy] = (0, 0, 0, 0)
    return img


def make_cinder_nest(name: str) -> Image.Image:
    """Cinder nest: woven dark twig courses with smoldering ember bits caught inside."""
    rnd = random.Random(name)
    img = Image.new("RGB", (16, 16))
    px = img.load()
    browns = [(0x4A, 0x38, 0x28), (0x5C, 0x46, 0x30), (0x38, 0x2A, 0x1E)]
    for y in range(16):
        phase = (y // 2) % 2
        for x in range(16):
            seg = ((x + (4 if phase else 0)) // 4 + y // 2) % 3
            base = browns[seg]
            if y % 2 == 1:
                base = shade(base, -12)  # twig underside shadow
            px[x, y] = jitter(rnd, base, 4)
    for _ in range(5):
        px[rnd.randrange(16), rnd.randrange(16)] = EMBER if rnd.random() < 0.6 else EMBER_BRIGHT
    return img


# --- cross-plant textures (RGBA, transparent background) ---

def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def put(px, x, y, rgb):
    if 0 <= x < 16 and 0 <= y < 16:
        px[x, y] = rgba(rgb)


def make_ember_fungus(name: str) -> Image.Image:
    """Small fungus: teal cap with bright spots on a gray-brown stipe (crimson_fungus
    silhouette)."""
    rnd = random.Random(name)
    img = blank()
    px = img.load()
    stipe = (0x8A, 0x74, 0x60)
    for y in range(9, 15):
        put(px, 7, y, jitter(rnd, stipe, 4))
        put(px, 8, y, jitter(rnd, shade(stipe, -12), 4))
    cap_rows = {4: (5, 10), 5: (4, 11), 6: (4, 11), 7: (5, 10), 8: (6, 9)}
    for y, (x0, x1) in cap_rows.items():
        for x in range(x0, x1 + 1):
            edge = x in (x0, x1) or y == 4
            put(px, x, y, jitter(rnd, TEAL_DARK if edge else TEAL_MID, 4))
    for x, y in ((6, 5), (9, 6), (7, 6)):
        put(px, x, y, TEAL_LIGHT)
    put(px, 8, 5, EMBER_BRIGHT)  # single smoldering spot on the cap
    return img


def make_ash_sprouts(name: str) -> Image.Image:
    """Stubby ground-hugging sprout nubs with pale bud caps (nether_sprouts
    silhouette): SHORT (2-4 px) so they read clearly against the tall arcing
    blades of ashen_grass."""
    rnd = random.Random(name)
    img = blank()
    px = img.load()
    base = (0x8E, 0x8E, 0x88)
    bud = (0xD2, 0xD2, 0xC6)
    dark = (0x63, 0x63, 0x5C)
    for sx in (1, 4, 7, 10, 13):
        h = rnd.randint(2, 4)
        for i in range(h):  # 2px-wide stem nub
            y = 15 - i
            c = dark if i == 0 else base
            put(px, sx, y, jitter(rnd, c, 4))
            put(px, sx + 1, y, jitter(rnd, shade(c, -10), 4))
        top = 15 - h
        put(px, sx, top, jitter(rnd, bud, 4))  # rounded bud cap on top
        put(px, sx + 1, top, jitter(rnd, bud, 4))
        if h >= 3:  # taller nubs get a wider bud
            put(px, sx - 1, top + 1, jitter(rnd, bud, 6))
    return img


def make_cinder_roots(name: str) -> Image.Image:
    """Tall wavy dark-red root strands (crimson_roots silhouette)."""
    rnd = random.Random(name)
    img = blank()
    px = img.load()
    dark = (0x8C, 0x2A, 0x1A)
    bright = (0xC2, 0x45, 0x20)
    for sx in (2, 5, 8, 11, 13):
        x = sx
        top = rnd.randint(3, 6)
        for y in range(15, top - 1, -1):
            c = bright if y % 3 == 0 else dark
            put(px, x, y, jitter(rnd, c, 4))
            if rnd.random() < 0.3:
                x = min(15, max(0, x + rnd.choice((-1, 1))))
    return img


def make_smolder_bloom(name: str) -> Image.Image:
    """Glowing flower: dark stem + leaves under an ember bloom with a hot core."""
    rnd = random.Random(name)
    img = blank()
    px = img.load()
    stem = (0x5A, 0x4A, 0x3A)
    leaf = (0x6E, 0x5A, 0x42)
    for y in range(8, 16):
        put(px, 8, y, jitter(rnd, stem, 3))
    put(px, 7, 11, jitter(rnd, leaf, 3))
    put(px, 6, 12, jitter(rnd, leaf, 3))
    put(px, 9, 10, jitter(rnd, leaf, 3))
    put(px, 10, 11, jitter(rnd, leaf, 3))
    bloom_rows = {3: (7, 9), 4: (6, 10), 5: (6, 10), 6: (7, 9)}
    for y, (x0, x1) in bloom_rows.items():
        for x in range(x0, x1 + 1):
            edge = x in (x0, x1) or y in (3, 6)
            put(px, x, y, jitter(rnd, EMBER if edge else EMBER_BRIGHT, 4))
    put(px, 8, 4, EMBER_CORE)
    put(px, 8, 5, EMBER_CORE)
    return img


def make_ashen_grass(name: str) -> Image.Image:
    """Gray grass tuft: TALL (7-12 px) thin blades that arc sideways towards
    pale drooping tips (short_grass silhouette) — clearly distinct from the
    stubby budded nubs of ash_sprouts."""
    rnd = random.Random(name)
    img = blank()
    px = img.load()
    base = (0x7E, 0x84, 0x78)
    tip = (0xA8, 0xAC, 0xA0)
    dark = (0x54, 0x59, 0x50)
    for sx, lean in ((3, -1), (5, 1), (7, -1), (9, 1), (11, -1), (13, 1)):
        h = rnd.randint(7, 12)
        x = sx
        for i in range(h):
            y = 15 - i
            if i in (h // 3, (2 * h) // 3, h - 1):  # arcing blade: bends thrice
                x = min(15, max(0, x + lean))
            c = tip if i >= h - 3 else (dark if i == 0 else base)
            put(px, x, y, jitter(rnd, c, 4))
    return img


def make_spore_cluster(name: str) -> Image.Image:
    """Cluster of teal spore bulbs on short stalks at staggered heights."""
    rnd = random.Random(name)
    img = blank()
    px = img.load()
    stalk = (0x4E, 0x5E, 0x54)
    for bx, by, r in ((4, 9, 2), (9, 6, 3), (12, 11, 2)):
        for y in range(by + r, 16):
            put(px, bx, y, jitter(rnd, stalk, 3))
        for dy in range(r):
            for dx in range(r):
                x, y = bx - r // 2 + dx, by + dy
                edge = dx in (0, r - 1) or dy in (0, r - 1)
                put(px, x, y, jitter(rnd, TEAL_DARK if edge else TEAL_MID, 4))
        put(px, bx - r // 2, by, TEAL_LIGHT)  # top-left glint
        if r == 3:
            put(px, bx, by + 1, (0x9A, 0xF2, 0xDB))
    return img


# ---------------------------------------------------------------------------
# main
# ---------------------------------------------------------------------------

def main() -> None:
    files = 0

    # ------------------------------------------------------------------
    # textures
    # ------------------------------------------------------------------
    textures: dict[str, Image.Image] = {
        "scorched_stem": make_bark("scorched_stem"),
        "scorched_stem_top": make_stem_top("scorched_stem_top",
                                           (0x5A, 0x4A, 0x40), (0x46, 0x39, 0x32), BARK_DARK),
        "stripped_scorched_stem": make_stripped("stripped_scorched_stem"),
        "stripped_scorched_stem_top": make_stem_top("stripped_scorched_stem_top",
                                                    STRIP_LIGHT, STRIP_DARK, (0x4A, 0x3C, 0x31)),
        "scorched_planks": make_planks("scorched_planks"),
        "scorched_wart_block": make_wart("scorched_wart_block", TEAL_LIGHT, TEAL_MID, TEAL_DARK),
        "ember_wart_block": make_wart("ember_wart_block",
                                      (0xE0, 0x6A, 0x2C), (0xB5, 0x4A, 0x1E), (0x7A, 0x2E, 0x12)),
        "ember_moss_block": make_moss("ember_moss_block"),
        "glowing_spore_block": make_glowing_spore("glowing_spore_block"),
        "fungal_light": make_fungal_light("fungal_light"),
        "cinder_nest": make_cinder_nest("cinder_nest"),
        "ember_fungus": make_ember_fungus("ember_fungus"),
        "ash_sprouts": make_ash_sprouts("ash_sprouts"),
        "cinder_roots": make_cinder_roots("cinder_roots"),
        "smolder_bloom": make_smolder_bloom("smolder_bloom"),
        "ashen_grass": make_ashen_grass("ashen_grass"),
        "spore_cluster": make_spore_cluster("spore_cluster"),
    }
    for name, img in textures.items():
        path = ASSETS / "textures" / "block" / f"{name}.png"
        path.parent.mkdir(parents=True, exist_ok=True)
        img.save(path)
        files += 1

    # ------------------------------------------------------------------
    # blockstates + block models + item defs, all from vanilla jar templates
    # ------------------------------------------------------------------
    blockstates: dict[str, dict] = {}
    models: dict[str, dict] = {}        # models/block/<name>.json
    item_models: dict[str, dict] = {}   # models/item/<name>.json (plants only)
    items: dict[str, dict] = {}         # items/<id>.json

    # --- wood set: 1:1 crimson_* -> scorched_* prefix retarget ---
    wood_pairs = [
        ("scorched_stem", "crimson_stem"),
        ("stripped_scorched_stem", "stripped_crimson_stem"),
        ("scorched_hyphae", "crimson_hyphae"),
        ("stripped_scorched_hyphae", "stripped_crimson_hyphae"),
        ("scorched_planks", "crimson_planks"),
        ("scorched_slab", "crimson_slab"),
        ("scorched_stairs", "crimson_stairs"),
        ("scorched_fence", "crimson_fence"),
        ("scorched_fence_gate", "crimson_fence_gate"),
    ]
    for ours, vanilla in wood_pairs:
        blockstates[ours] = jar_json(f"assets/minecraft/blockstates/{vanilla}.json", WOOD_BLOCK_REPL)
        items[ours] = jar_json(f"assets/minecraft/items/{vanilla}.json", WOOD_BLOCK_REPL)
    wood_model_pairs = [
        ("scorched_stem", "crimson_stem"),
        ("stripped_scorched_stem", "stripped_crimson_stem"),
        ("scorched_hyphae", "crimson_hyphae"),
        ("stripped_scorched_hyphae", "stripped_crimson_hyphae"),
        ("scorched_planks", "crimson_planks"),
        ("scorched_slab", "crimson_slab"),
        ("scorched_slab_top", "crimson_slab_top"),
        ("scorched_stairs", "crimson_stairs"),
        ("scorched_stairs_inner", "crimson_stairs_inner"),
        ("scorched_stairs_outer", "crimson_stairs_outer"),
        ("scorched_fence_post", "crimson_fence_post"),
        ("scorched_fence_side", "crimson_fence_side"),
        ("scorched_fence_inventory", "crimson_fence_inventory"),
        ("scorched_fence_gate", "crimson_fence_gate"),
        ("scorched_fence_gate_open", "crimson_fence_gate_open"),
        ("scorched_fence_gate_wall", "crimson_fence_gate_wall"),
        ("scorched_fence_gate_wall_open", "crimson_fence_gate_wall_open"),
    ]
    for ours, vanilla in wood_model_pairs:
        models[ours] = jar_json(f"assets/minecraft/models/block/{vanilla}.json", WOOD_BLOCK_REPL)

    # --- plain cubes from nether_wart_block / moss_block / shroomlight templates ---
    cube_pairs = [
        ("scorched_wart_block", "nether_wart_block"),
        ("ember_wart_block", "nether_wart_block"),
        ("ember_moss_block", "moss_block"),
        ("glowing_spore_block", "shroomlight"),
        ("fungal_light", "shroomlight"),
        ("cinder_nest", "shroomlight"),
    ]
    for ours, vanilla in cube_pairs:
        repl = {f"minecraft:block/{vanilla}": f"{MODID}:block/{ours}"}
        blockstates[ours] = jar_json(f"assets/minecraft/blockstates/{vanilla}.json", repl)
        models[ours] = jar_json(f"assets/minecraft/models/block/{vanilla}.json", repl)
        items[ours] = jar_json(f"assets/minecraft/items/{vanilla}.json", repl)

    # --- ember moss carpet: vanilla moss_carpet templates (carpet model reuses the
    #     moss BLOCK texture, exactly like vanilla) ---
    carpet_repl = {
        "minecraft:block/moss_carpet": f"{MODID}:block/ember_moss_carpet",
        "minecraft:block/moss_block": f"{MODID}:block/ember_moss_block",
    }
    blockstates["ember_moss_carpet"] = jar_json("assets/minecraft/blockstates/moss_carpet.json", carpet_repl)
    models["ember_moss_carpet"] = jar_json("assets/minecraft/models/block/moss_carpet.json", carpet_repl)
    items["ember_moss_carpet"] = jar_json("assets/minecraft/items/moss_carpet.json", carpet_repl)

    # --- six plants: crimson_fungus templates (block/cross + item/generated) ---
    for pid in PLANTS:
        block_repl = {"minecraft:block/crimson_fungus": f"{MODID}:block/{pid}"}
        blockstates[pid] = jar_json("assets/minecraft/blockstates/crimson_fungus.json", block_repl)
        models[pid] = jar_json("assets/minecraft/models/block/crimson_fungus.json", block_repl)
        item_models[pid] = jar_json("assets/minecraft/models/item/crimson_fungus.json", block_repl)
        items[pid] = jar_json("assets/minecraft/items/crimson_fungus.json",
                              {"minecraft:item/crimson_fungus": f"{MODID}:item/{pid}"})

    # ------------------------------------------------------------------
    # loot tables: drop-self (crimson_fungus template); double-count slab template
    # ------------------------------------------------------------------
    loot: dict[str, dict] = {}
    for bid in ALL_IDS:
        if bid == "scorched_slab":
            loot[bid] = jar_json("data/minecraft/loot_table/blocks/crimson_slab.json", {
                "minecraft:blocks/crimson_slab": f"{MODID}:blocks/scorched_slab",
                "minecraft:crimson_slab": f"{MODID}:scorched_slab",
            })
        else:
            loot[bid] = jar_json("data/minecraft/loot_table/blocks/crimson_fungus.json", {
                "minecraft:blocks/crimson_fungus": f"{MODID}:blocks/{bid}",
                "minecraft:crimson_fungus": f"{MODID}:{bid}",
            })

    # ------------------------------------------------------------------
    # recipes (see module docstring for choices/simplifications)
    # ------------------------------------------------------------------
    recipes: dict[str, dict] = {}

    planks = jar_json("data/minecraft/recipe/crimson_planks.json",
                      {"minecraft:crimson_planks": f"{MODID}:scorched_planks"})
    # the vanilla #minecraft:crimson_stems TAG becomes an explicit alternatives list
    planks["ingredients"] = [[f"{MODID}:scorched_stem", f"{MODID}:stripped_scorched_stem",
                              f"{MODID}:scorched_hyphae", f"{MODID}:stripped_scorched_hyphae"]]
    recipes["scorched_planks"] = planks

    wood_recipe_repl = {
        "minecraft:crimson_planks": f"{MODID}:scorched_planks",
        "minecraft:crimson_slab": f"{MODID}:scorched_slab",
        "minecraft:crimson_stairs": f"{MODID}:scorched_stairs",
        "minecraft:crimson_fence_gate": f"{MODID}:scorched_fence_gate",
        "minecraft:crimson_fence": f"{MODID}:scorched_fence",
        "minecraft:crimson_stem": f"{MODID}:scorched_stem",
        "minecraft:crimson_hyphae": f"{MODID}:scorched_hyphae",
    }
    for rid, vanilla in [("scorched_slab", "crimson_slab"),
                         ("scorched_stairs", "crimson_stairs"),
                         ("scorched_fence", "crimson_fence"),
                         ("scorched_fence_gate", "crimson_fence_gate"),
                         ("scorched_hyphae", "crimson_hyphae")]:
        recipes[rid] = jar_json(f"data/minecraft/recipe/{vanilla}.json", wood_recipe_repl)

    # stripping via recipe — DOCUMENTED SIMPLIFICATION (no axe-strip interaction)
    for src, dst in [("scorched_stem", "stripped_scorched_stem"),
                     ("scorched_hyphae", "stripped_scorched_hyphae")]:
        recipes[dst] = {
            "type": "minecraft:crafting_shapeless",
            "category": "building",
            "ingredients": [f"{MODID}:{src}"],
            "result": {"count": 1, "id": f"{MODID}:{dst}"},
        }

    recipes["ember_wart_block"] = jar_json("data/minecraft/recipe/nether_wart_block.json", {
        "minecraft:nether_wart_block": f"{MODID}:ember_wart_block",
        "minecraft:nether_wart": f"{MODID}:ember_fungus",
    })
    recipes["ember_moss_carpet"] = jar_json("data/minecraft/recipe/moss_carpet.json", {
        "minecraft:moss_carpet": f"{MODID}:ember_moss_carpet",
        "minecraft:moss_block": f"{MODID}:ember_moss_block",
    })

    # ------------------------------------------------------------------
    # lang fragments (EN + DE)
    # ------------------------------------------------------------------
    lang_en = {f"block.{MODID}.{bid}": display_name(bid) for bid in ALL_IDS}
    lang_de = {f"block.{MODID}.{bid}": DE_NAMES[bid] for bid in ALL_IDS}

    # ------------------------------------------------------------------
    # write everything
    # ------------------------------------------------------------------
    for name, obj in blockstates.items():
        write_json(ASSETS / "blockstates" / f"{name}.json", obj)
        files += 1
    for name, obj in models.items():
        write_json(ASSETS / "models" / "block" / f"{name}.json", obj)
        files += 1
    for name, obj in item_models.items():
        write_json(ASSETS / "models" / "item" / f"{name}.json", obj)
        files += 1
    for name, obj in items.items():
        write_json(ASSETS / "items" / f"{name}.json", obj)
        files += 1
    for bid, obj in loot.items():
        write_json(DATA / "loot_table" / "blocks" / f"{bid}.json", obj)
        files += 1
    for name, obj in recipes.items():
        write_json(DATA / "recipe" / "infernoflora" / f"{name}.json", obj)
        files += 1
    write_json(ASSETS / "lang" / "fragments" / "infernoflora.json", dict(sorted(lang_en.items())))
    write_json(ASSETS / "lang" / "fragments_de" / "infernoflora.json", dict(sorted(lang_de.items())))
    files += 2

    assert len(blockstates) == 22 == len(items) == len(loot), \
        f"expected 22 ids, got {len(blockstates)} blockstates / {len(items)} items / {len(loot)} loot"
    assert len(recipes) == 10, f"expected 10 recipes, got {len(recipes)}"
    mode = "PNG+JSON" if WRITE_JSON else "PNG only; JSON skipped (pass --write-json)"
    print(f"infernoflora_gen: processed {files} files ({mode}) for {len(ALL_IDS)} block ids")


if __name__ == "__main__":
    main()
