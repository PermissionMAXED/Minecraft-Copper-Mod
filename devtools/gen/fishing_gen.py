#!/usr/bin/env python3
"""Asset generator for the v4.1 "Fishing" feature.

Four fishing-loot items (verdigris_pearl, rusted_copper_ring, ancient_copper_coin,
fizzy_kelp) registered by feature.fishing.FishingFeature and injected into the vanilla
fishing junk/treasure loot tables via LootTableEvents.MODIFY (no data JSON needed).

Emits (all deterministic, idempotent — run any number of times, same bytes):
  - assets/copper_inferno/textures/item/<id>.png   16x16 sprites: pearl + kelp are
    vanilla sprites recolored onto themed ramps via lib_gen.recolor/extract_vanilla
    (exactly like devtools/gen/gemalloy_gen.py recolors the iron sprites); ring + coin
    are seeded hand-painted sprites (like gemalloy_gen's lantern item sprite)
  - assets/copper_inferno/models/item/<id>.json    (item/generated + layer0)
  - assets/copper_inferno/items/<id>.json          (1.21.9 item model definition)
  - assets/copper_inferno/lang/fragments/fishing.json + fragments_de/fishing.json
    (EN + real German)
"""

import sys
from pathlib import Path

from PIL import Image

sys.path.insert(0, str(Path(__file__).resolve().parent))

from lib_gen import (  # noqa: E402
    NS, _im, _it, _shade, extract_vanilla, item_def, merge, recolor, seeded,
    write_files, write_json,
)

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / NS

ITEMS = ["verdigris_pearl", "rusted_copper_ring", "ancient_copper_coin", "fizzy_kelp"]

# ---------------------------------------------------------------------------
# Palettes (verdigris = oxidized-copper teal, copper/rust for the ring & coin)
# ---------------------------------------------------------------------------

VERDIGRIS = (0x52, 0xA2, 0x84)   # vanilla oxidized-copper teal
FIZZ_GREEN = (0x6E, 0xB8, 0x4A)  # brighter effervescent kelp green
COPPER = (0xC1, 0x6C, 0x44)      # copper metal
RUST = (0x7A, 0x46, 0x2E)        # rusty brown
PATINA = (0x6E, 0xC2, 0x9E)      # bright verdigris speck


def ramp(base):
    """Dark -> light 5-step recolor ramp (same construction as gemalloy_gen item_ramp)."""
    return [_shade(base, -72), _shade(base, -32), base, _shade(base, 40), _shade(base, 84)]


# ---------------------------------------------------------------------------
# Texture painters (16x16, deterministic)
# ---------------------------------------------------------------------------


def tex_verdigris_pearl() -> Image.Image:
    """Vanilla ender pearl recolored onto the verdigris teal ramp."""
    return recolor(extract_vanilla("assets/minecraft/textures/item/ender_pearl.png"),
                   ramp(VERDIGRIS))


def tex_fizzy_kelp() -> Image.Image:
    """Vanilla kelp recolored onto a bright fizz-green ramp, plus seeded fizz bubbles."""
    img = recolor(extract_vanilla("assets/minecraft/textures/item/kelp.png"),
                  ramp(FIZZ_GREEN))
    rng = seeded("item/fizzy_kelp")
    px = img.load()
    bubble = (*_shade(FIZZ_GREEN, 110), 255)
    opaque = [(x, y) for y in range(16) for x in range(16) if px[x, y][3] > 0]
    for x, y in opaque:
        if rng.random() < 0.06:
            px[x, y] = bubble
    return img


def tex_rusted_copper_ring() -> Image.Image:
    """Round copper band eaten by rust: 2px ring with seeded rust/patina speckles."""
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    rng = seeded("item/rusted_copper_ring")
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d = ((x - cx) ** 2 + (y - cy) ** 2) ** 0.5
            if 4.2 <= d <= 6.4:
                r = rng.random()
                if r < 0.30:
                    color = RUST
                elif r < 0.38:
                    color = PATINA
                elif r < 0.60:
                    color = _shade(COPPER, -28)
                else:
                    color = COPPER
                px[x, y] = (*color, 255)
    # sheen on the upper-left arc, deep rust shadow on the lower-right arc
    for x, y in ((4, 3), (3, 4), (5, 3), (3, 5)):
        px[x, y] = (*_shade(COPPER, 64), 255)
    for x, y in ((11, 12), (12, 11), (10, 12), (12, 10)):
        px[x, y] = (*_shade(RUST, -24), 255)
    return img


def tex_ancient_copper_coin() -> Image.Image:
    """Ancient round coin with a square center hole, worn copper face + patina specks."""
    img = Image.new("RGBA", (16, 16), (0, 0, 0, 0))
    px = img.load()
    rng = seeded("item/ancient_copper_coin")
    cx = cy = 7.5
    for y in range(16):
        for x in range(16):
            d = ((x - cx) ** 2 + (y - cy) ** 2) ** 0.5
            if d > 6.6:
                continue
            if 6 <= x <= 9 and 6 <= y <= 9:
                continue  # square center hole
            if d > 5.6:
                color = _shade(COPPER, -44)  # rim
            elif (5 <= x <= 10 and y in (5, 10)) or (5 <= y <= 10 and x in (5, 10)):
                color = _shade(COPPER, -28)  # embossed square around the hole
            else:
                r = rng.random()
                if r < 0.14:
                    color = PATINA
                elif r < 0.32:
                    color = _shade(COPPER, -20)
                else:
                    color = COPPER
            px[x, y] = (*color, 255)
    for x, y in ((5, 3), (4, 4), (3, 5)):
        px[x, y] = (*_shade(COPPER, 56), 255)  # worn sheen
    return img


TEXTURES = {
    "verdigris_pearl": tex_verdigris_pearl,
    "rusted_copper_ring": tex_rusted_copper_ring,
    "ancient_copper_coin": tex_ancient_copper_coin,
    "fizzy_kelp": tex_fizzy_kelp,
}

# ---------------------------------------------------------------------------
# Lang (EN + real German)
# ---------------------------------------------------------------------------

LANG_EN = {
    "verdigris_pearl": "Verdigris Pearl",
    "rusted_copper_ring": "Rusted Copper Ring",
    "ancient_copper_coin": "Ancient Copper Coin",
    "fizzy_kelp": "Fizzy Kelp",
}

LANG_DE = {
    "verdigris_pearl": "Grünspanperle",
    "rusted_copper_ring": "Rostiger Kupferring",
    "ancient_copper_coin": "Alte Kupfermünze",
    "fizzy_kelp": "Sprudel-Seetang",
}

# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------


def main() -> None:
    assert set(ITEMS) == set(TEXTURES) == set(LANG_EN) == set(LANG_DE), "item tables out of sync"

    files = {}
    for item_id in ITEMS:
        files = merge(files, {
            _im(item_id): {"parent": "minecraft:item/generated",
                           "textures": {"layer0": f"{NS}:item/{item_id}"}},
            _it(item_id): item_def(f"{NS}:item/{item_id}"),
        })
    json_count = write_files(files, RES)

    tex_dir = ASSETS / "textures" / "item"
    tex_dir.mkdir(parents=True, exist_ok=True)
    for item_id in ITEMS:
        TEXTURES[item_id]().save(tex_dir / f"{item_id}.png")

    write_json(ASSETS / "lang" / "fragments" / "fishing.json",
               {f"item.{NS}.{i}": LANG_EN[i] for i in ITEMS})
    write_json(ASSETS / "lang" / "fragments_de" / "fishing.json",
               {f"item.{NS}.{i}": LANG_DE[i] for i in ITEMS})

    print(f"fishing_gen: {len(ITEMS)} items -> {json_count} asset JSON files, "
          f"{len(ITEMS)} textures, 2 lang fragments.")


if __name__ == "__main__":
    main()
