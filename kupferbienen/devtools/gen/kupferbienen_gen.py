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
    plus, per declarative-table row: emit_flower_block / emit_machine_block /
    emit_crop JSON sets (blockstates, block models, item defs/models, block loot).

Mega-expansion framework: painters are parametric (comb/pollen/flower/machine_face/
crop_stage/effect_icon take palettes) and the declarative tables BEE_PALETTES,
SIMPLE_FLOWERS, MACHINE_BLOCKS and CROPS drive emission — later workers append rows
instead of writing new painter code.

Determinism: all texture noise is seeded per texture name via rng_for("<name>")
(== Random(f"kupferbienen:<name>")), and every painter consumes the rng exactly once
per canvas pixel, so re-runs produce byte-identical PNGs.
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
# comb (honeycomb blob) — 16x16 item sprite, parametric on a (dark, base, light)
# palette
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


def comb(rng: Random, palette) -> Image.Image:
    """Comb blob (rounded square, clipped corners) filled with a Voronoi hex-cell
    honeycomb: dark cell walls, mid cell fill, light glints near cell centres.
    palette = (dark, base, light); rng consumed once per canvas pixel."""
    dark, base, light = palette
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
                img.putpixel((x, y), dark + (255,))
                continue
            dists = sorted(math.dist((x, y), c) for c in centers)
            d1, d2 = dists[0], dists[1]
            if d2 - d1 < 1.15:
                color = dark                 # cell wall
            elif d1 < 1.0:
                color = light                # honey glint at the cell centre
            elif mottle < 0.12:
                color = dark                 # sparse mottling
            elif mottle > 0.94:
                color = light
            else:
                color = base                 # cell fill
            img.putpixel((x, y), color + (255,))
    return img


def tex_kupferwabe(rng: Random) -> Image.Image:
    return comb(rng, (COPPER_DARK, COPPER, COPPER_LIGHT))


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


def pollen(rng: Random, palette) -> Image.Image:
    """Pollen puff: soft round clump with light sparkles and loose motes.
    palette = (dark, base, light); rng consumed once per canvas pixel."""
    dark, base, light = palette
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
                    px(img, x, y, dark)
            elif d > 2.4:
                px(img, x, y, dark if jitter < 0.25 else base)
            else:
                px(img, x, y, light if jitter < 0.35 else base)
    # Loose motes drifting off the puff (seeded, kept off the clump).
    for mx, my in ((2, 3), (13, 2), (14, 12), (2, 13), (12, 14), (3, 8)):
        px(img, mx, my, light if (mx + my) % 2 else base)
    return img


def tex_gruenspanpollen(rng: Random) -> Image.Image:
    return pollen(rng, (VERDIGRIS_DARK, VERDIGRIS, VERDIGRIS_LIGHT))


def flower(rng: Random, petal, tip, heart, stem) -> Image.Image:
    """Cross-model flower: stem + two leaves, 8 petals around a glowing heart, tinted
    petal tips. petal = (petal_dark, petal_base); stem = (stem_green, stem_dark);
    tip/heart are single colors. rng consumed once per canvas pixel."""
    petal_dark, petal_base = petal
    stem_green, stem_dark = stem
    img = blank()
    fx, fy = 7.5, 4.0  # flower-head centre
    for y in range(16):
        for x in range(16):
            spark = rng.random()  # consumed unconditionally: fixed rng stream
            d = math.hypot(x - fx, y - fy)
            if d <= 1.2:
                px(img, x, y, heart)                # glowing heart
            elif d <= 2.3:
                px(img, x, y, petal_dark if spark < 0.2 else petal_base)
            elif d <= 3.6:
                # 8 petals: keep pixels near the 45-degree spoke directions.
                ang = math.degrees(math.atan2(y - fy, x - fx)) % 45.0
                if min(ang, 45.0 - ang) <= 11.0:
                    px(img, x, y, tip if d >= 2.9 else petal_base)
    # Stem with two leaves.
    for y in range(7, 16):
        px(img, 7, y, stem_dark if y % 3 == 0 else stem_green)
    for lx, ly in ((6, 10), (5, 10), (5, 9)):
        px(img, lx, ly, stem_green)
    for lx, ly in ((8, 12), (9, 12), (9, 11)):
        px(img, lx, ly, stem_dark)
    return img


def tex_kupferbluete(rng: Random) -> Image.Image:
    return flower(rng, (BEE_COPPER_DARK, BEE_COPPER), VERDIGRIS, BEE_COPPER_LIGHT,
                  (STEM_GREEN, STEM_DARK))


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
# Machine block faces: plank base parametric on a (dark, base, light) palette,
# plus an accent-colored motif drawn on top (entrance hole, lid border, dials, ...)
# ---------------------------------------------------------------------------

def _plank_base(rng: Random, palette) -> Image.Image:
    """16x16 planks: four 4px horizontal boards with dark seams + mottle.
    palette = (dark, base, light); rng consumed once per canvas pixel."""
    dark, base, light = palette
    img = blank()
    for y in range(16):
        for x in range(16):
            mottle = rng.random()
            if y % 4 == 3:
                color = dark                 # horizontal seam
            elif (y // 4 * 5 + x) % 8 == 0:
                color = dark                 # staggered board-end notches
            elif mottle < 0.08:
                color = dark
            elif mottle > 0.93:
                color = light
            else:
                color = base
            px(img, x, y, color)
    return img


def machine_face(rng: Random, base, accent, motif) -> Image.Image:
    """One 16x16 machine-block face: parametric plank base + a motif drawn on top.
    base = (dark, mid, light) plank palette; accent is the motif's accent color;
    motif(img, base, accent) draws AFTER the plank base and must NOT consume rng
    (the plank base already consumed exactly one rng.random() per pixel)."""
    img = _plank_base(rng, base)
    if motif is not None:
        motif(img, base, accent)
    return img


def motif_entrance(img: Image.Image, base, accent) -> None:
    """Beehive-style entrance: accent-dark notch low-centre with a light lip above."""
    for y in range(10, 14):
        for x in range(6, 10):
            px(img, x, y, accent)
    for x in range(6, 10):
        px(img, x, 9, base[2])


def motif_lid_border(img: Image.Image, base, accent) -> None:
    """Accent border ring so the face reads as a lid."""
    for i in range(16):
        for (bx, by) in ((i, 0), (i, 15), (0, i), (15, i)):
            px(img, bx, by, accent)


def tex_kupferstock_side(rng: Random) -> Image.Image:
    return machine_face(rng, (COPPER_DARK, COPPER, COPPER_LIGHT), OUTLINE_DARK,
                        motif_entrance)


def tex_kupferstock_top(rng: Random) -> Image.Image:
    return machine_face(rng, (COPPER_DARK, COPPER, COPPER_LIGHT), COPPER_DARK,
                        motif_lid_border)


# ---------------------------------------------------------------------------
# Crop stage textures: 16x16 sprites for the vanilla block/crop model (stub —
# later flora workers refine the look; the determinism contract still holds)
# ---------------------------------------------------------------------------

def crop_stage(rng: Random, stage: int, stages: int, palette) -> Image.Image:
    """Stub crop-stage painter for {id}_stage{N}.png: four stalks that grow taller
    with the stage, light heads on the final stage. palette = (dark, base, light);
    rng consumed once per canvas pixel (fixed stream regardless of stage)."""
    dark, base, light = palette
    img = blank()
    height = 3 + round(11 * stage / max(stages - 1, 1))
    ripe = stage == stages - 1
    for y in range(16):
        for x in range(16):
            jitter = rng.random()  # consumed unconditionally: fixed rng stream
            if x not in (2, 6, 9, 13) or y < 16 - height:
                continue
            if ripe and y <= 16 - height + 1:
                px(img, x, y, light)         # ripe head
            elif jitter < 0.2:
                px(img, x, y, dark)
            else:
                px(img, x, y, base)
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


def effect_icon(rng: Random, palette, glyph=None) -> Image.Image:
    """Generic 18x18 effect icon for NEW palettes (patina_haut/blitzblank keep their
    bespoke painters): tri-tone noisy disc with a dark rim; optional
    glyph(img, palette) overlay drawn AFTER the disc, must NOT consume rng.
    palette = (dark, base, light); rng consumed once per canvas pixel."""
    dark, base, light = palette
    img = blank_sized(18)
    cx, cy = 8.5, 8.5
    for y in range(18):
        for x in range(18):
            noise = rng.random()  # consumed unconditionally: fixed rng stream
            d = math.hypot(x - cx, y - cy)
            if d > 7.4:
                continue
            if d > 6.4 or noise < 0.12:
                px_sized(img, x, y, dark)
            elif noise > 0.90:
                px_sized(img, x, y, light)
            else:
                px_sized(img, x, y, base)
    if glyph is not None:
        glyph(img, palette)
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


# Bee entity textures: <entity id> -> (dark, base, light) remap palette applied to the
# vanilla bee texture. Later bee workers append here (renderer + lang keys still needed).
BEE_PALETTES = {
    "kupferbiene": (BEE_COPPER_DARK, BEE_COPPER, BEE_COPPER_LIGHT),
    "gruenspanbiene": (VERDIGRIS_DARK, VERDIGRIS, VERDIGRIS_LIGHT),
}


def emit_entity_textures() -> None:
    if not CLIENT_JAR.is_file():
        print(f"kupferbienen_gen: WARNING client jar not found at {CLIENT_JAR}; "
              "skipping bee entity textures", file=sys.stderr)
        return
    tex_dir = CLIENT_ASSETS / "textures" / "entity"
    with zipfile.ZipFile(CLIENT_JAR) as jar:
        bee_bytes = jar.read(BEE_TEXTURE_IN_JAR)
    for name, (dark, base, light) in BEE_PALETTES.items():
        path = tex_dir / f"{name}.png"
        save_png(recolor_entity_texture(bee_bytes, dark, base, light), path)
        print(f"wrote {path}")


# ---------------------------------------------------------------------------
# Block JSON emitters (all --write-json only; shapes mirror the committed JSON
# under src/main/resources: blockstates/kupferbluete.json, models/block/*.json,
# loot_table/blocks/*.json)
# ---------------------------------------------------------------------------

def emit_block_loot(block_id: str) -> None:
    """data/loot_table/blocks/<id>.json: drop-self with survives_explosion
    (kupferbluete/kupferstock shape)."""
    write_json(RES / "data" / NS / "loot_table" / "blocks" / f"{block_id}.json", {
        "pools": [
            {
                "bonus_rolls": 0.0,
                "conditions": [{"condition": "minecraft:survives_explosion"}],
                "entries": [{"name": f"{NS}:{block_id}", "type": "minecraft:item"}],
                "rolls": 1.0,
            }
        ],
        "random_sequence": f"{NS}:blocks/{block_id}",
        "type": "minecraft:block",
    })


def emit_flower_block(block_id: str) -> None:
    """Full JSON set for a cross-model flower block (kupferbluete shape): blockstate,
    cross block model, item def + generated item model (layer0 = block texture),
    survives-explosion loot."""
    write_json(ASSETS / "blockstates" / f"{block_id}.json",
               {"variants": {"": {"model": f"{NS}:block/{block_id}"}}})
    write_json(ASSETS / "models" / "block" / f"{block_id}.json", {
        "parent": "minecraft:block/cross",
        "textures": {"cross": f"{NS}:block/{block_id}"},
    })
    emit_item_def(ASSETS, block_id)
    emit_item_model(ASSETS, block_id, f"{NS}:block/{block_id}")
    emit_block_loot(block_id)


def emit_machine_block(block_id: str) -> None:
    """Full JSON set for a cube_bottom_top machine block (kupferstock shape):
    blockstate, block model over <id>_side/<id>_top textures, item def + block-parent
    item model, survives-explosion loot."""
    write_json(ASSETS / "blockstates" / f"{block_id}.json",
               {"variants": {"": {"model": f"{NS}:block/{block_id}"}}})
    write_json(ASSETS / "models" / "block" / f"{block_id}.json", {
        "parent": "minecraft:block/cube_bottom_top",
        "textures": {
            "bottom": f"{NS}:block/{block_id}_top",
            "side": f"{NS}:block/{block_id}_side",
            "top": f"{NS}:block/{block_id}_top",
        },
    })
    emit_item_def(ASSETS, block_id)
    write_json(ASSETS / "models" / "item" / f"{block_id}.json",
               {"parent": f"{NS}:block/{block_id}"})
    emit_block_loot(block_id)


def emit_crop(block_id: str, stages: int) -> None:
    """age=N blockstate + one block/crop model per stage over <id>_stage<N> textures.
    Crop loot (seed/produce splits) is block-specific — later workers add it by hand."""
    write_json(ASSETS / "blockstates" / f"{block_id}.json", {
        "variants": {f"age={i}": {"model": f"{NS}:block/{block_id}_stage{i}"}
                     for i in range(stages)},
    })
    for i in range(stages):
        write_json(ASSETS / "models" / "block" / f"{block_id}_stage{i}.json", {
            "parent": "minecraft:block/crop",
            "textures": {"crop": f"{NS}:block/{block_id}_stage{i}"},
        })


# ---------------------------------------------------------------------------
# Declarative content tables — later workers APPEND rows here; main() emits the
# matching textures (always) and JSON (--write-json only) from them.
# ---------------------------------------------------------------------------

# <flower block id> -> flower() kwargs:
# {"petal": (dark, base), "tip": color, "heart": color, "stem": (green, dark)}
SIMPLE_FLOWERS = {}

# <machine block id> -> {"base": (dark, mid, light), "accent": color,
#                        "side_motif": motif_fn, "top_motif": motif_fn}
# Textures emitted: <id>_side.png / <id>_top.png (machine_face).
MACHINE_BLOCKS = {}

# <crop block id> -> {"stages": N, "palette": (dark, base, light)}
# Textures emitted: <id>_stage0..N-1.png (crop_stage).
CROPS = {}


# ---------------------------------------------------------------------------
# main
# ---------------------------------------------------------------------------

ITEM_TEXTURES = {
    "kupferwabe": tex_kupferwabe,
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

    save_png(icon_image(), ASSETS / "icon.png")
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

    # Table-driven textures (empty tables today; later workers append rows).
    for name, spec in SIMPLE_FLOWERS.items():
        path = ASSETS / "textures" / "block" / f"{name}.png"
        save_png(flower(rng_for(name), spec["petal"], spec["tip"], spec["heart"],
                        spec["stem"]), path)
        print(f"wrote {path}")
    for name, spec in MACHINE_BLOCKS.items():
        for face in ("side", "top"):
            tex = f"{name}_{face}"
            path = ASSETS / "textures" / "block" / f"{tex}.png"
            save_png(machine_face(rng_for(tex), spec["base"], spec["accent"],
                                  spec[f"{face}_motif"]), path)
            print(f"wrote {path}")
    for name, spec in CROPS.items():
        for i in range(spec["stages"]):
            tex = f"{name}_stage{i}"
            path = ASSETS / "textures" / "block" / f"{tex}.png"
            save_png(crop_stage(rng_for(tex), i, spec["stages"], spec["palette"]), path)
            print(f"wrote {path}")

    emit_entity_textures()

    if write_json_files:
        for item_id in ("kupferwabe", "gruenspanpollen",
                        "kupferbiene_spawn_egg", "gruenspanbiene_spawn_egg",
                        "kupfersud", "trank_der_oxidation", "trank_der_entoxidation",
                        "wurfphiole_oxidation", "wurfphiole_entoxidation"):
            emit_item_def(ASSETS, item_id)
            emit_item_model(ASSETS, item_id)
        for flower_id in SIMPLE_FLOWERS:
            emit_flower_block(flower_id)
        for machine_id in MACHINE_BLOCKS:
            emit_machine_block(machine_id)
        for crop_id, spec in CROPS.items():
            emit_crop(crop_id, spec["stages"])
        print("wrote item def + model JSON")


if __name__ == "__main__":
    main(sys.argv[1:])
