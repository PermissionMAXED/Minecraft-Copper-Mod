#!/usr/bin/env python3
"""kupferbienen_gen — deterministic asset generator for the KUPFERBIENEN mod.

Self-contained: write_json + rng_for + the texture conventions are COPIED from the
Copper Inferno genlib (devtools/gen/genlib.py in the reference repo) with NS switched
to "kupferbienen". Do NOT import from the reference repo's devtools.

Emits (always):
    src/main/resources/assets/kupferbienen/textures/item/kupferwabe.png
        16x16 copper honeycomb: hex-cell pattern in copper tones
        #B87333 / #8C5A28 / #E0955B.
    src/main/resources/assets/kupferbienen/textures/item/gruenspanpollen.png
        16x16 verdigris pollen puff.
    src/main/resources/assets/kupferbienen/textures/item/kupferbiene_spawn_egg.png
    src/main/resources/assets/kupferbienen/textures/item/gruenspanbiene_spawn_egg.png
        16x16 classic spawn-egg silhouettes (copper / verdigris).
    src/main/resources/assets/kupferbienen/textures/block/kupferbluete.png
        16x16 cross-model copper flower: green stem, copper petals, verdigris tips.
    src/main/resources/assets/kupferbienen/textures/block/kupferstock_side.png
    src/main/resources/assets/kupferbienen/textures/block/kupferstock_top.png
        16x16 copper-plank apiary faces (side has the entrance hole).
    src/client/resources/assets/kupferbienen/textures/entity/kupferbiene.png
    src/client/resources/assets/kupferbienen/textures/entity/gruenspanbiene.png
        The vanilla bee texture (assets/minecraft/textures/entity/bee/bee.png inside
        the loom minecraft-client.jar, path verified via unzip -l) luminance-remapped
        onto the copper / verdigris palettes. Pure function of the vanilla bytes +
        palette (alpha copied through unchanged, pixels never move), so byte-identical
        across runs.
    src/main/resources/assets/kupferbienen/icon.png
        128x128 mod icon: copper bee motif on a dark background.
    src/main/resources/assets/kupferbienen/textures/mob_effect/patina_haut.png
        18x18 status-effect icon: copper lump overgrown by a verdigris crust.
    src/main/resources/assets/kupferbienen/textures/mob_effect/blitzblank.png
        18x18 status-effect icon: polished copper ingot with white sparkle glints.

Emits (only with --write-json; the JSON in src/main/resources stays authoritative):
    assets/kupferbienen/items/<id>.json        (1.21.9 item model-definitions)
    assets/kupferbienen/models/item/<id>.json  (item/generated sprite models)

Determinism: all texture noise is seeded per texture name via rng_for("<name>")
(== Random(f"kupferbienen:<name>")) so re-runs produce byte-identical PNGs.
"""

import io
import json
import math
import sys
import zipfile
from pathlib import Path
from random import Random

from PIL import Image, ImageChops, ImageDraw

NS = "kupferbienen"
ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS
CLIENT_ASSETS = ROOT / "src" / "client" / "resources" / "assets" / NS
CLIENT_JAR = Path.home() / ".gradle/caches/fabric-loom/1.21.9/minecraft-client.jar"
BEE_TEXTURE_IN_JAR = "assets/minecraft/textures/entity/bee/bee.png"

# Copper palette (fixed by the mod spec).
COPPER = (0xB8, 0x73, 0x33)
COPPER_DARK = (0x8C, 0x5A, 0x28)
COPPER_LIGHT = (0xE0, 0x95, 0x5B)
ICON_BG = (0x14, 0x0D, 0x09)
ICON_BG_HEX = (0x26, 0x19, 0x0F)

# Entity-remap palettes (fixed by the mod spec).
BEE_COPPER_DARK = (0x5A, 0x32, 0x14)
BEE_COPPER = (0xB8, 0x73, 0x33)
BEE_COPPER_LIGHT = (0xE0, 0x95, 0x5B)
VERDIGRIS_DARK = (0x1E, 0x4D, 0x33)
VERDIGRIS = (0x43, 0xA0, 0x47)
VERDIGRIS_LIGHT = (0x7F, 0xD8, 0xA0)

# Flora accents.
STEM_GREEN = (0x4A, 0x8F, 0x3C)
STEM_DARK = (0x2F, 0x66, 0x28)
OUTLINE_DARK = (0x3B, 0x25, 0x10)

# Potion glassware (bottles + splash flasks).
GLASS = (0xBF, 0xD6, 0xE4)
GLASS_DARK = (0x5C, 0x74, 0x86)
GLASS_SHINE = (0xE8, 0xF4, 0xFA)
CORK = (0x9C, 0x6B, 0x38)
CORK_DARK = (0x6E, 0x49, 0x24)

# Liquid palettes (dark, base, light) — fixed by the mod spec.
SUD_LIQUID = ((0x5A, 0x3A, 0x18), (0x8C, 0x5A, 0x28), (0xB8, 0x73, 0x33))
OXIDATION_LIQUID = ((0x2E, 0x70, 0x32), (0x43, 0xA0, 0x47), (0x7F, 0xD8, 0xA0))
ENTOXIDATION_LIQUID = ((0xC9, 0x98, 0x50), (0xFF, 0xD9, 0xA0), (0xFF, 0xEF, 0xD0))


# ---------------------------------------------------------------------------
# Core helpers (copied from genlib.py, NS switched)
# ---------------------------------------------------------------------------

def write_json(path: Path, obj) -> None:
    """Deterministic JSON write: 2-space indent, sorted keys, raw unicode, trailing \\n."""
    path = Path(path)
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=True, ensure_ascii=False) + "\n",
                    encoding="utf-8")


def item_def(model_ref: str) -> dict:
    """items/<id>.json body: the 1.21.9 item model-definition."""
    return {"model": {"type": "minecraft:model", "model": model_ref}}


def rng_for(name: str) -> Random:
    """Per-texture seeded Random; the ONLY approved seeding scheme for textures."""
    return Random(f"{NS}:{name}")


def emit_item_def(assets: Path, item_id: str, model_ref: str | None = None) -> None:
    """assets/items/<id>.json model-definition (default model: <ns>:item/<id>)."""
    write_json(assets / "items" / f"{item_id}.json",
               item_def(model_ref or f"{NS}:item/{item_id}"))


def emit_item_model(assets: Path, item_id: str, texture_ref: str | None = None) -> None:
    """assets/models/item/<id>.json: item/generated with layer0 (default <ns>:item/<id>)."""
    write_json(assets / "models" / "item" / f"{item_id}.json", {
        "parent": "minecraft:item/generated",
        "textures": {"layer0": texture_ref or f"{NS}:item/{item_id}"},
    })


def save_png(img: Image.Image, path: Path) -> None:
    path = Path(path)
    path.parent.mkdir(parents=True, exist_ok=True)
    img.save(path, format="PNG")


# ---------------------------------------------------------------------------
# kupferwabe (copper comb) — 16x16 item sprite
# ---------------------------------------------------------------------------

def _comb_centers() -> list:
    """Offset grid of hex-cell centres covering the 16x16 canvas (with margin)."""
    centers = []
    for row in range(-1, 7):
        for col in range(-1, 6):
            cx = col * 5.0 + (row % 2) * 2.5
            cy = row * 3.5
            centers.append((cx, cy))
    return centers


def kupferwabe_texture() -> Image.Image:
    """Comb blob (rounded square, clipped corners) filled with a Voronoi hex-cell
    honeycomb: dark cell walls, mid copper cell fill, light glints near cell centres."""
    rng = rng_for("kupferwabe")
    centers = _comb_centers()

    # Blob mask: rounded square with the corners cut off.
    mask = [[False] * 16 for _ in range(16)]
    for y in range(16):
        for x in range(16):
            dx, dy = abs(x - 7.5), abs(y - 7.5)
            mask[y][x] = max(dx, dy) <= 7.5 and (dx + dy) <= 11.0

    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    for y in range(16):
        for x in range(16):
            mottle = rng.random()  # consumed unconditionally: keeps the rng stream fixed
            if not mask[y][x]:
                continue
            # Rim: opaque pixels touching transparency get the dark outline tone.
            rim = any(not (0 <= nx < 16 and 0 <= ny < 16 and mask[ny][nx])
                      for nx, ny in ((x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)))
            if rim:
                img.putpixel((x, y), COPPER_DARK + (255,))
                continue
            dists = sorted(math.dist((x, y), c) for c in centers)
            d1, d2 = dists[0], dists[1]
            if d2 - d1 < 1.15:
                color = COPPER_DARK          # cell wall
            elif d1 < 1.0:
                color = COPPER_LIGHT         # honey glint at the cell centre
            elif mottle < 0.12:
                color = COPPER_DARK          # sparse mottling
            elif mottle > 0.94:
                color = COPPER_LIGHT
            else:
                color = COPPER               # cell fill
            img.putpixel((x, y), color + (255,))
    return img


# ---------------------------------------------------------------------------
# icon.png — 128x128 copper bee on a dark background
# ---------------------------------------------------------------------------

def _hexagon(cx: float, cy: float, r: float) -> list:
    return [(cx + r * math.cos(math.radians(60 * i - 30)),
             cy + r * math.sin(math.radians(60 * i - 30))) for i in range(6)]


def icon_image() -> Image.Image:
    rng = rng_for("icon")
    img = Image.new("RGBA", (128, 128), ICON_BG + (255,))
    d = ImageDraw.Draw(img)

    # Faint honeycomb lattice in the background.
    for row in range(-1, 6):
        for col in range(-1, 6):
            cx = col * 26 + (row % 2) * 13
            cy = row * 23
            d.polygon(_hexagon(cx, cy, 12), outline=ICON_BG_HEX + (255,))

    # Wings (light copper, behind the body).
    d.ellipse([38, 14, 72, 56], fill=COPPER_LIGHT + (255,), outline=COPPER_DARK + (255,), width=2)
    d.ellipse([64, 10, 100, 52], fill=COPPER_LIGHT + (255,), outline=COPPER_DARK + (255,), width=2)

    # Body.
    body_box = [28, 52, 110, 106]
    d.ellipse(body_box, fill=COPPER + (255,))

    # Dark stripes, clipped to the body ellipse.
    body_mask = Image.new("L", (128, 128), 0)
    ImageDraw.Draw(body_mask).ellipse(body_box, fill=255)
    stripes = Image.new("RGBA", (128, 128), (0, 0, 0, 0))
    sd = ImageDraw.Draw(stripes)
    for x0 in (48, 68, 88):
        sd.rectangle([x0, 40, x0 + 9, 118], fill=COPPER_DARK + (255,))
    stripes.putalpha(ImageChops.multiply(stripes.split()[3], body_mask))
    img.alpha_composite(stripes)
    d.ellipse(body_box, outline=COPPER_DARK + (255,), width=2)

    # Stinger.
    d.polygon([(108, 74), (124, 79), (108, 88)], fill=COPPER_LIGHT + (255,))

    # Head + eye + antennae.
    d.ellipse([12, 58, 46, 92], fill=COPPER_DARK + (255,))
    d.ellipse([20, 66, 30, 76], fill=COPPER_LIGHT + (255,))
    d.line([(26, 60), (16, 42)], fill=COPPER_LIGHT + (255,), width=2)
    d.line([(34, 58), (32, 38)], fill=COPPER_LIGHT + (255,), width=2)
    d.ellipse([13, 39, 19, 45], fill=COPPER_LIGHT + (255,))
    d.ellipse([29, 35, 35, 41], fill=COPPER_LIGHT + (255,))

    # Sparse copper sparkles (seeded => deterministic).
    for _ in range(26):
        x, y = rng.randrange(2, 126), rng.randrange(2, 126)
        d.point([(x, y), (x + 1, y), (x, y + 1)], fill=COPPER_LIGHT + (140,))
    return img


# ---------------------------------------------------------------------------
# Bees: 16x16 helpers (px/blank/spawn_egg copied from the Copper Inferno
# infernomobs_gen.py painter conventions, NS switched)
# ---------------------------------------------------------------------------

def blank() -> Image.Image:
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))


def px(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    if 0 <= x < 16 and 0 <= y < 16:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def spawn_egg(rng: Random, base, base_dark, base_light, spots, outline) -> Image.Image:
    """Classic spawn-egg silhouette (narrow top, wide bottom) with seeded speckles."""
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
            color = base_light  # top-left sheen
        elif x >= 10 or y >= 12:
            color = base_dark
        else:
            color = base
        px(img, x, y, color)
    interior = [(x, y) for x, y, edge in cells if not edge]
    for x, y in rng.sample(interior, 12):
        px(img, x, y, spots)
    return img


def tex_kupferbiene_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, BEE_COPPER, BEE_COPPER_DARK, BEE_COPPER_LIGHT,
                     OUTLINE_DARK, OUTLINE_DARK)


def tex_gruenspanbiene_spawn_egg(rng: Random) -> Image.Image:
    return spawn_egg(rng, VERDIGRIS, VERDIGRIS_DARK, VERDIGRIS_LIGHT,
                     BEE_COPPER, VERDIGRIS_DARK)


def tex_gruenspanpollen(rng: Random) -> Image.Image:
    """Verdigris pollen puff: soft round clump with light sparkles and loose motes."""
    img = blank()
    cx, cy = 7.5, 8.0
    for y in range(16):
        for x in range(16):
            jitter = rng.random()  # consumed unconditionally: fixed rng stream
            d = math.hypot(x - cx, y - cy)
            if d > 4.6:
                continue
            if d > 3.8:
                if jitter < 0.55:  # ragged rim
                    px(img, x, y, VERDIGRIS_DARK)
            elif d > 2.4:
                px(img, x, y, VERDIGRIS_DARK if jitter < 0.25 else VERDIGRIS)
            else:
                px(img, x, y, VERDIGRIS_LIGHT if jitter < 0.35 else VERDIGRIS)
    # Loose motes drifting off the puff (seeded, kept off the clump).
    for mx, my in ((2, 3), (13, 2), (14, 12), (2, 13), (12, 14), (3, 8)):
        px(img, mx, my, VERDIGRIS_LIGHT if (mx + my) % 2 else VERDIGRIS)
    return img


def tex_kupferbluete(rng: Random) -> Image.Image:
    """Cross-model copper flower: green stem + leaves, 8 copper petals around a light
    copper heart, verdigris petal tips."""
    img = blank()
    fx, fy = 7.5, 4.0  # flower-head centre
    for y in range(16):
        for x in range(16):
            spark = rng.random()  # consumed unconditionally: fixed rng stream
            d = math.hypot(x - fx, y - fy)
            if d <= 1.2:
                px(img, x, y, BEE_COPPER_LIGHT)     # glowing heart
            elif d <= 2.3:
                px(img, x, y, BEE_COPPER_DARK if spark < 0.2 else BEE_COPPER)
            elif d <= 3.6:
                # 8 petals: keep pixels near the 45-degree spoke directions.
                ang = math.degrees(math.atan2(y - fy, x - fx)) % 45.0
                if min(ang, 45.0 - ang) <= 11.0:
                    px(img, x, y, VERDIGRIS if d >= 2.9 else BEE_COPPER)
    # Stem with two leaves.
    for y in range(7, 16):
        px(img, 7, y, STEM_DARK if y % 3 == 0 else STEM_GREEN)
    for lx, ly in ((6, 10), (5, 10), (5, 9)):
        px(img, lx, ly, STEM_GREEN)
    for lx, ly in ((8, 12), (9, 12), (9, 11)):
        px(img, lx, ly, STEM_DARK)
    return img


# ---------------------------------------------------------------------------
# Potion bottles + splash flasks (oxidation brewing chain)
# ---------------------------------------------------------------------------

def _bottle(rng: Random, liquid, flask: bool) -> Image.Image:
    """16x16 glassware sprite. Drink bottle (flask=False): glass lip + tall neck over a
    round body. Wurfphiole (flask=True): rounder/wider bulb with a cork stopper. The
    liquid tri-tone fills the lower body; rng is consumed once per canvas pixel so the
    stream (and thus the sprite) is a pure function of the seed."""
    dark, base, light = liquid
    img = blank()
    if flask:
        cx, cy, r = 7.5, 9.5, 4.8
        neck_rows = (3, 4)
        liquid_top = 8
    else:
        cx, cy, r = 7.5, 10.0, 4.2
        neck_rows = (3, 4, 5)
        liquid_top = 9

    body = [[math.hypot(x - cx, y - cy) <= r for x in range(16)] for y in range(16)]
    for y in neck_rows:
        for x in (6, 7, 8, 9):
            body[y][x] = True

    for y in range(16):
        for x in range(16):
            shimmer = rng.random()  # consumed unconditionally: fixed rng stream
            if not body[y][x]:
                continue
            rim = any(not (0 <= nx < 16 and 0 <= ny < 16 and body[ny][nx])
                      for nx, ny in ((x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)))
            if rim:
                px(img, x, y, GLASS_DARK)
            elif y >= liquid_top:
                if shimmer < 0.15:
                    px(img, x, y, dark)
                elif shimmer > 0.88:
                    px(img, x, y, light)
                else:
                    px(img, x, y, base)
            else:
                px(img, x, y, GLASS)

    # Fixed shine glints on the upper-left glass, above the liquid line.
    for sx, sy in ((5, liquid_top - 2), (5, liquid_top - 1)):
        if 0 <= sy < 16 and body[sy][sx]:
            px(img, sx, sy, GLASS_SHINE)

    if flask:
        # Cork stopper plugging the short neck.
        for y in (1, 2):
            for x in (6, 7, 8, 9):
                px(img, x, y, CORK_DARK if (y == 1 or x in (6, 9)) else CORK)
    else:
        # Vanilla-style glass lip above the neck.
        for x in range(5, 11):
            px(img, x, 2, GLASS_DARK)
        px(img, 6, 1, GLASS)
        px(img, 7, 1, GLASS)
        px(img, 8, 1, GLASS)
        px(img, 9, 1, GLASS)
    return img


def tex_kupfersud(rng: Random) -> Image.Image:
    return _bottle(rng, SUD_LIQUID, flask=False)


def tex_trank_der_oxidation(rng: Random) -> Image.Image:
    return _bottle(rng, OXIDATION_LIQUID, flask=False)


def tex_trank_der_entoxidation(rng: Random) -> Image.Image:
    return _bottle(rng, ENTOXIDATION_LIQUID, flask=False)


def tex_wurfphiole_oxidation(rng: Random) -> Image.Image:
    return _bottle(rng, OXIDATION_LIQUID, flask=True)


def tex_wurfphiole_entoxidation(rng: Random) -> Image.Image:
    return _bottle(rng, ENTOXIDATION_LIQUID, flask=True)


# ---------------------------------------------------------------------------
# Kupferstock (copper apiary) block faces: copper planks, side with entrance
# ---------------------------------------------------------------------------

def _plank_base(rng: Random) -> Image.Image:
    """16x16 copper planks: four 4px horizontal boards with dark seams + mottle."""
    img = blank()
    for y in range(16):
        for x in range(16):
            mottle = rng.random()
            if y % 4 == 3:
                color = COPPER_DARK          # horizontal seam
            elif (y // 4 * 5 + x) % 8 == 0:
                color = COPPER_DARK          # staggered board-end notches
            elif mottle < 0.08:
                color = COPPER_DARK
            elif mottle > 0.93:
                color = COPPER_LIGHT
            else:
                color = COPPER
            px(img, x, y, color)
    return img


def tex_kupferstock_side(rng: Random) -> Image.Image:
    img = _plank_base(rng)
    # Entrance: dark notch low-centre, with a light lip above (beehive-style).
    for y in range(10, 14):
        for x in range(6, 10):
            px(img, x, y, OUTLINE_DARK)
    for x in range(6, 10):
        px(img, x, 9, COPPER_LIGHT)
    return img


def tex_kupferstock_top(rng: Random) -> Image.Image:
    img = _plank_base(rng)
    # Border ring so the top face reads as a lid.
    for i in range(16):
        for (bx, by) in ((i, 0), (i, 15), (0, i), (15, i)):
            px(img, bx, by, COPPER_DARK)
    return img


# ---------------------------------------------------------------------------
# Mob-effect icons: 18x18 (the vanilla mob_effect sprite size). px()/blank()
# hardcode 16x16, so these use the size-aware variants below.
# ---------------------------------------------------------------------------

def blank_sized(size: int) -> Image.Image:
    return Image.new("RGBA", (size, size), (0, 0, 0, 0))


def px_sized(img: Image.Image, x: int, y: int, color, alpha: int = 255) -> None:
    w, h = img.size
    if 0 <= x < w and 0 <= y < h:
        img.putpixel((x, y), (color[0], color[1], color[2], alpha))


def tex_patina_haut(rng: Random) -> Image.Image:
    """18x18 effect icon: a copper lump overgrown by a verdigris crust that has taken
    hold from the top-left; ragged dark rim. rng consumed once per canvas pixel."""
    img = blank_sized(18)
    cx, cy = 8.5, 9.0
    for y in range(18):
        for x in range(18):
            noise = rng.random()  # consumed unconditionally: fixed rng stream
            d = math.hypot(x - cx, (y - cy) * 1.1)
            if d > 6.9:
                continue
            rim = d > 5.9
            # Crust coverage fades from the top-left (overgrown) to the bottom-right
            # (bare copper still showing).
            crusted = noise > (x + y) / 34.0 * 0.9 + 0.18
            if rim:
                px_sized(img, x, y, VERDIGRIS_DARK if crusted else COPPER_DARK)
            elif crusted:
                px_sized(img, x, y, VERDIGRIS_LIGHT if noise > 0.93 else VERDIGRIS)
            else:
                px_sized(img, x, y, COPPER_LIGHT if noise < 0.08 else COPPER)
    return img


def tex_blitzblank(rng: Random) -> Image.Image:
    """18x18 effect icon: a polished copper ingot (trapezoid, lit top face) with white
    four-point sparkle glints at fixed positions."""
    img = blank_sized(18)
    for y in range(6, 14):
        half = 4.0 + 3.0 * (y - 6) / 7.0  # widens toward the base
        x0 = int(round(8.5 - half))
        x1 = int(round(8.5 + half))
        for x in range(x0, x1 + 1):
            sheen = rng.random()  # consumed unconditionally: fixed rng stream
            if x == x0 or x == x1 or y in (6, 13):
                color = COPPER_DARK
            elif y <= 8:
                color = COPPER_LIGHT          # lit top face
            elif sheen < 0.10:
                color = COPPER_LIGHT          # polished speckle
            else:
                color = COPPER
            px_sized(img, x, y, color)
    white = (0xFF, 0xFF, 0xFF)
    for sx, sy in ((4, 4), (13, 3), (15, 12), (2, 14)):
        px_sized(img, sx, sy, white)
        for ox, oy in ((-1, 0), (1, 0), (0, -1), (0, 1)):
            px_sized(img, sx + ox, sy + oy, white, 200)
    return img


# ---------------------------------------------------------------------------
# Entity textures: vanilla bee texture luminance-remapped (calamities_gen
# recolor_entity_texture pattern: dark -> base -> light ramp, alpha unchanged)
# ---------------------------------------------------------------------------

def lerp(a, b, t):
    return tuple(int(round(a[i] + (b[i] - a[i]) * t)) for i in range(3))


def recolor_entity_texture(png_bytes: bytes, dark, base, light) -> Image.Image:
    src = Image.open(io.BytesIO(png_bytes)).convert("RGBA")
    out = Image.new("RGBA", src.size, (0, 0, 0, 0))
    for y in range(src.size[1]):
        for x in range(src.size[0]):
            r, g, b, a = src.getpixel((x, y))
            if a == 0:
                continue
            lum = (r + g + b) / (3 * 255)
            if lum < 0.5:
                color = lerp(dark, base, lum / 0.5)
            else:
                color = lerp(base, light, (lum - 0.5) / 0.5)
            out.putpixel((x, y), (*color, a))
    return out


def emit_entity_textures() -> None:
    if not CLIENT_JAR.is_file():
        print(f"kupferbienen_gen: WARNING client jar not found at {CLIENT_JAR}; "
              "skipping bee entity textures", file=sys.stderr)
        return
    tex_dir = CLIENT_ASSETS / "textures" / "entity"
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        bee_bytes = jar.read(BEE_TEXTURE_IN_JAR)
    save_png(recolor_entity_texture(bee_bytes, BEE_COPPER_DARK, BEE_COPPER, BEE_COPPER_LIGHT),
             tex_dir / "kupferbiene.png")
    save_png(recolor_entity_texture(bee_bytes, VERDIGRIS_DARK, VERDIGRIS, VERDIGRIS_LIGHT),
             tex_dir / "gruenspanbiene.png")
    print(f"wrote {tex_dir / 'kupferbiene.png'}")
    print(f"wrote {tex_dir / 'gruenspanbiene.png'}")


# ---------------------------------------------------------------------------
# main
# ---------------------------------------------------------------------------

ITEM_TEXTURES = {
    "gruenspanpollen": tex_gruenspanpollen,
    "kupferbiene_spawn_egg": tex_kupferbiene_spawn_egg,
    "gruenspanbiene_spawn_egg": tex_gruenspanbiene_spawn_egg,
    "kupfersud": tex_kupfersud,
    "trank_der_oxidation": tex_trank_der_oxidation,
    "trank_der_entoxidation": tex_trank_der_entoxidation,
    "wurfphiole_oxidation": tex_wurfphiole_oxidation,
    "wurfphiole_entoxidation": tex_wurfphiole_entoxidation,
}

BLOCK_TEXTURES = {
    "kupferbluete": tex_kupferbluete,
    "kupferstock_side": tex_kupferstock_side,
    "kupferstock_top": tex_kupferstock_top,
}

MOB_EFFECT_TEXTURES = {
    "patina_haut": tex_patina_haut,
    "blitzblank": tex_blitzblank,
}


def main(argv: list) -> None:
    write_json_files = "--write-json" in argv

    save_png(kupferwabe_texture(), ASSETS / "textures" / "item" / "kupferwabe.png")
    save_png(icon_image(), ASSETS / "icon.png")
    print(f"wrote {ASSETS / 'textures' / 'item' / 'kupferwabe.png'}")
    print(f"wrote {ASSETS / 'icon.png'}")

    for name, painter in ITEM_TEXTURES.items():
        path = ASSETS / "textures" / "item" / f"{name}.png"
        save_png(painter(rng_for(name)), path)
        print(f"wrote {path}")
    for name, painter in BLOCK_TEXTURES.items():
        path = ASSETS / "textures" / "block" / f"{name}.png"
        save_png(painter(rng_for(name)), path)
        print(f"wrote {path}")
    for name, painter in MOB_EFFECT_TEXTURES.items():
        path = ASSETS / "textures" / "mob_effect" / f"{name}.png"
        save_png(painter(rng_for(name)), path)
        print(f"wrote {path}")

    emit_entity_textures()

    if write_json_files:
        for item_id in ("kupferwabe", "gruenspanpollen",
                        "kupferbiene_spawn_egg", "gruenspanbiene_spawn_egg",
                        "kupfersud", "trank_der_oxidation", "trank_der_entoxidation",
                        "wurfphiole_oxidation", "wurfphiole_entoxidation"):
            emit_item_def(ASSETS, item_id)
            emit_item_model(ASSETS, item_id)
        print("wrote item def + model JSON")


if __name__ == "__main__":
    main(sys.argv[1:])
