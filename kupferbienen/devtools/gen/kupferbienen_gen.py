#!/usr/bin/env python3
"""kupferbienen_gen — deterministic asset generator for the KUPFERBIENEN mod.

Self-contained: write_json + rng_for + the texture conventions are COPIED from the
Copper Inferno genlib (devtools/gen/genlib.py in the reference repo) with NS switched
to "kupferbienen". Do NOT import from the reference repo's devtools.

Emits (always):
    src/main/resources/assets/kupferbienen/textures/item/kupferwabe.png
        16x16 copper honeycomb: hex-cell pattern in copper tones
        #B87333 / #8C5A28 / #E0955B.
    src/main/resources/assets/kupferbienen/icon.png
        128x128 mod icon: copper bee motif on a dark background.

Emits (only with --write-json; the JSON in src/main/resources stays authoritative):
    assets/kupferbienen/items/kupferwabe.json        (1.21.9 item model-definition)
    assets/kupferbienen/models/item/kupferwabe.json  (item/generated sprite model)

Determinism: all texture noise is seeded per texture name via rng_for("<name>")
(== Random(f"kupferbienen:<name>")) so re-runs produce byte-identical PNGs.
"""

import json
import math
import sys
from pathlib import Path
from random import Random

from PIL import Image, ImageChops, ImageDraw

NS = "kupferbienen"
ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS

# Copper palette (fixed by the mod spec).
COPPER = (0xB8, 0x73, 0x33)
COPPER_DARK = (0x8C, 0x5A, 0x28)
COPPER_LIGHT = (0xE0, 0x95, 0x5B)
ICON_BG = (0x14, 0x0D, 0x09)
ICON_BG_HEX = (0x26, 0x19, 0x0F)


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
# main
# ---------------------------------------------------------------------------

def main(argv: list) -> None:
    write_json_files = "--write-json" in argv

    save_png(kupferwabe_texture(), ASSETS / "textures" / "item" / "kupferwabe.png")
    save_png(icon_image(), ASSETS / "icon.png")
    print(f"wrote {ASSETS / 'textures' / 'item' / 'kupferwabe.png'}")
    print(f"wrote {ASSETS / 'icon.png'}")

    if write_json_files:
        emit_item_def(ASSETS, "kupferwabe")
        emit_item_model(ASSETS, "kupferwabe")
        print("wrote item def + model JSON for kupferwabe")


if __name__ == "__main__":
    main(sys.argv[1:])
