#!/usr/bin/env python3
"""Asset generator for the MUSIC & LORE feature (COPPER INFERNO 1 v2).

Emits, directly into src/main/resources:
  - items/<id>.json + models/item/<id>.json + textures/item/<id>.png for the
    7 music/lore items (3 music discs, 2 smithing templates, pocket watch,
    medallion),
  - data/copper_inferno/jukebox_song/*.json (3 songs),
  - data/copper_inferno/recipe/music/*.json (9 copper smithing_transform
    recipes + template crafting/duplication recipes),
  - assets/copper_inferno/lang/fragments/music.json,
  - a merge-append into assets/copper_inferno/sounds.json (existing entries
    are preserved EXACTLY; only missing music_disc.* keys are added),
  - ORIGINAL synthesized music: numpy -> WAV -> ffmpeg -> mono .ogg at
    assets/copper_inferno/sounds/music/*.ogg (skipped when the ogg already
    exists, pass --force-audio to re-render).

JSON formats are copied from EXACT vanilla 1.21.9 templates extracted from
~/.gradle/caches/fabric-loom/1.21.9/minecraft-client.jar:
  - data/minecraft/jukebox_song/13.json (jukebox song schema),
  - data/minecraft/recipe/netherite_sword_smithing.json (smithing_transform),
  - data/minecraft/recipe/netherite_upgrade_smithing_template.json (shaped
    duplication recipe),
  - assets/minecraft/items/music_disc_13.json + models/item/music_disc_13.json
    (disc item definition indirection; parent item/template_music_disc).

Idempotent: JSON/PNG output is deterministic (no RNG); audio synthesis is
deterministic too but ffmpeg encoding is not byte-stable, so existing oggs
are left untouched by default.

Usage: python3 devtools/gen/music_gen.py [--force-audio]
"""

import json
import math
import shutil
import subprocess
import sys
import tempfile
import wave
from pathlib import Path

import numpy as np
from PIL import Image

ROOT = Path(__file__).resolve().parents[2]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / "copper_inferno"
DATA = RES / "data" / "copper_inferno"

MOD = "copper_inferno"

# ---------------------------------------------------------------------------
# Content tables
# ---------------------------------------------------------------------------

# song id -> (duration seconds, comparator output 1-15)
SONGS = {
    "copper_inferno": (45.0, 6),
    "soda_pop": (40.0, 3),
    "oxidation": (50.0, 10),
}

DISC_ITEMS = {  # item id -> song id
    "music_disc_copper_inferno": "copper_inferno",
    "music_disc_soda_pop": "soda_pop",
    "music_disc_oxidation": "oxidation",
}

PLAIN_ITEMS = [
    "copper_upgrade_smithing_template",
    "inferno_upgrade_smithing_template",
    "copper_pocket_watch",
    "sonic0810_medallion",
]

# iron gear -> vanilla copper gear (all exist in 1.21.9 "Copper Age")
COPPER_SMITHING = [
    "sword", "pickaxe", "axe", "shovel", "hoe",
    "helmet", "chestplate", "leggings", "boots",
]

LANG = {
    "item.copper_inferno.music_disc_copper_inferno": "Music Disc",
    "item.copper_inferno.music_disc_soda_pop": "Music Disc",
    "item.copper_inferno.music_disc_oxidation": "Music Disc",
    "jukebox_song.copper_inferno.copper_inferno": "Sonic0810 - Copper Inferno",
    "jukebox_song.copper_inferno.soda_pop": "Sonic0810 - Soda Pop",
    "jukebox_song.copper_inferno.oxidation": "Sonic0810 - Oxidation",
    "item.copper_inferno.copper_upgrade_smithing_template": "Copper Upgrade Smithing Template",
    "item.copper_inferno.inferno_upgrade_smithing_template": "Inferno Upgrade Smithing Template",
    "item.copper_inferno.copper_pocket_watch": "Copper Pocket Watch",
    "item.copper_inferno.sonic0810_medallion": "Sonic0810 Medallion",
}

# ---------------------------------------------------------------------------
# Palette
# ---------------------------------------------------------------------------
T = (0, 0, 0, 0)

# vinyl grays sampled from vanilla music_disc_13.png
VINYL_RIM = (17, 17, 17, 255)
VINYL_DARK = (33, 33, 33, 255)
VINYL_MID = (38, 38, 38, 255)
VINYL_BODY = (64, 64, 64, 255)
VINYL_SHINE = (86, 86, 86, 255)
HOLE = (10, 10, 10, 255)

COPPER = (224, 115, 77, 255)        # #E0734D
COPPER_DARK = (193, 90, 59, 255)    # #C15A3B
COPPER_LIGHT = (240, 147, 107, 255)
MAROON = (122, 27, 34, 255)         # #7A1B22
MAROON_DARK = (90, 14, 20, 255)     # #5A0E14
GREEN = (111, 176, 142, 255)        # #6FB08E
GREEN_DARK = (87, 160, 123, 255)    # #57A07B


def write_png(path: Path, px):
    img = Image.new("RGBA", (16, 16))
    img.putdata([px[y][x] for y in range(16) for x in range(16)])
    path.parent.mkdir(parents=True, exist_ok=True)
    img.save(path)


def blank():
    return [[T for _ in range(16)] for _ in range(16)]


# ---------------------------------------------------------------------------
# Textures
# ---------------------------------------------------------------------------

def disc_texture(center_light, center_dark):
    """Vinyl record: dark grooved circle with a colored label + center hole."""
    px = blank()
    for y in range(16):
        for x in range(16):
            r = math.hypot(x - 7.5, y - 7.5)
            if r > 7.0:
                continue
            if r > 6.1:
                px[y][x] = VINYL_RIM
            elif r <= 0.8:
                px[y][x] = HOLE
            elif r <= 2.7:
                px[y][x] = center_light if (x + y) % 2 == 0 else center_dark
            else:
                # concentric grooves + one specular streak (upper-left arc)
                ring = int(r * 1.6) % 2
                px[y][x] = VINYL_BODY if ring == 0 else VINYL_MID
                if x - y in (-1, 0) and 3.2 < r < 5.9 and x < 8:
                    px[y][x] = VINYL_SHINE
    return px


# silhouette of vanilla netherite_upgrade_smithing_template.png (16x16)
def template_rows():
    rows = {}
    rows[1] = range(4, 13)
    for y in range(2, 13):
        rows[y] = range(3, 14)
    rows[13] = range(4, 13)
    rows[14] = range(5, 10)
    return rows


def template_texture(border, body, body_dark, emblem_px, emblem_colors):
    """Mimics the vanilla smithing template sprite silhouette."""
    px = blank()
    rows = template_rows()
    sil = {(x, y) for y, xs in rows.items() for x in xs}
    for (x, y) in sil:
        neighbors = [(x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)]
        if any(n not in sil for n in neighbors):
            px[y][x] = border
        else:
            px[y][x] = body_dark if (x * 3 + y * 5) % 7 < 2 else body
    for (x, y), c in emblem_px.items():
        px[y][x] = emblem_colors[c]
    return px


def copper_template_texture():
    # upward "upgrade" arrow emblem in copper tones
    emblem = {}
    for x in range(6, 11):
        emblem[(x, 6)] = 0
    for x in range(7, 10):
        emblem[(x, 5)] = 0
    emblem[(8, 4)] = 1
    for y in range(7, 10):
        emblem[(8, y)] = 0
    for x in range(7, 10):
        emblem[(x, 10)] = 2
    return template_texture(
        border=(52, 14, 14, 255),
        body=(114, 50, 50, 255),
        body_dark=(101, 40, 40, 255),
        emblem_px=emblem,
        emblem_colors={0: COPPER, 1: COPPER_LIGHT, 2: COPPER_DARK},
    )


def inferno_template_texture():
    # flame emblem on a blackstone-dark body
    emblem = {
        (8, 4): 1, (7, 5): 0, (8, 5): 1, (7, 6): 0, (8, 6): 1, (9, 6): 0,
        (6, 7): 0, (7, 7): 1, (8, 7): 2, (9, 7): 0,
        (6, 8): 0, (7, 8): 2, (8, 8): 2, (9, 8): 1, (10, 8): 0,
        (7, 9): 0, (8, 9): 2, (9, 9): 0,
        (8, 10): 0,
    }
    return template_texture(
        border=(20, 17, 20, 255),
        body=(45, 40, 45, 255),
        body_dark=(30, 26, 30, 255),
        emblem_px=emblem,
        emblem_colors={0: (216, 69, 31, 255), 1: (255, 122, 42, 255), 2: (255, 194, 88, 255)},
    )


def pocket_watch_texture():
    px = blank()
    rim = (138, 74, 43, 255)
    body = COPPER
    body_dark = COPPER_DARK
    face = (242, 232, 201, 255)
    face_shade = (222, 209, 172, 255)
    hand = (61, 33, 18, 255)
    cx, cy = 7.5, 8.5
    for y in range(16):
        for x in range(16):
            r = math.hypot(x - cx, y - cy)
            if r > 5.6:
                continue
            if r > 4.7:
                px[y][x] = rim if (x + y) % 3 else body_dark
            elif r > 3.4:
                px[y][x] = body if (x - y) % 4 else body_dark
            else:
                px[y][x] = face if (x + y) % 2 == 0 else face_shade
    # 12/3/6/9 o'clock ticks
    for (x, y) in [(7, 6), (8, 6), (10, 8), (10, 9), (7, 11), (8, 11), (5, 8), (5, 9)]:
        px[y][x] = hand
    # hands (pointing up and right)
    px[8][7] = hand
    px[7][7] = hand
    px[8][8] = hand
    px[8][9] = hand
    # winding crown + bow loop
    px[2][7] = body_dark
    px[2][8] = body_dark
    px[1][7] = COPPER_LIGHT
    px[1][8] = COPPER_LIGHT
    px[0][7] = rim
    px[0][8] = rim
    # short chain link off to the side
    px[2][11] = COPPER_LIGHT
    px[1][12] = rim
    return px


def medallion_texture():
    px = blank()
    gold = (245, 194, 66, 255)
    gold_dark = (200, 149, 48, 255)
    gold_light = (255, 224, 138, 255)
    glyph = (92, 58, 12, 255)
    # ribbon straps (maroon) meeting in the middle
    for y in range(0, 6):
        lx = 4 + y // 2
        rx = 11 - y // 2
        for x in (lx, lx + 1):
            px[y][x] = MAROON if (x + y) % 2 == 0 else MAROON_DARK
        for x in (rx - 1, rx):
            px[y][x] = MAROON if (x + y) % 2 == 0 else MAROON_DARK
    # medal disc
    cx, cy = 7.5, 10.0
    for y in range(16):
        for x in range(16):
            r = math.hypot(x - cx, y - cy)
            if r > 4.6:
                continue
            if r > 3.7:
                px[y][x] = gold_dark
            else:
                px[y][x] = gold_light if (x - y) in (-1, 0) else gold
    # blocky "S" (Sonic0810)
    for x in range(6, 10):
        px[8][x] = glyph
    px[9][6] = glyph
    for x in range(6, 10):
        px[10][x] = glyph
    px[11][9] = glyph
    for x in range(6, 10):
        px[12][x] = glyph
    return px


# ---------------------------------------------------------------------------
# JSON emitters
# ---------------------------------------------------------------------------

def write_json(path: Path, obj):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(obj, indent=2, sort_keys=False) + "\n")


def emit_item_assets():
    for item_id in list(DISC_ITEMS) + PLAIN_ITEMS:
        # 1.21.9 item definition (assets/<ns>/items/<id>.json)
        write_json(ASSETS / "items" / f"{item_id}.json", {
            "model": {
                "type": "minecraft:model",
                "model": f"{MOD}:item/{item_id}",
            }
        })
        # model (discs use the vanilla disc template, itself item/generated)
        parent = "minecraft:item/template_music_disc" if item_id in DISC_ITEMS else "minecraft:item/generated"
        write_json(ASSETS / "models" / "item" / f"{item_id}.json", {
            "parent": parent,
            "textures": {
                "layer0": f"{MOD}:item/{item_id}",
            }
        })

    tex = ASSETS / "textures" / "item"
    write_png(tex / "music_disc_copper_inferno.png", disc_texture(COPPER, COPPER_DARK))
    write_png(tex / "music_disc_soda_pop.png", disc_texture(MAROON, MAROON_DARK))
    write_png(tex / "music_disc_oxidation.png", disc_texture(GREEN, GREEN_DARK))
    write_png(tex / "copper_upgrade_smithing_template.png", copper_template_texture())
    write_png(tex / "inferno_upgrade_smithing_template.png", inferno_template_texture())
    write_png(tex / "copper_pocket_watch.png", pocket_watch_texture())
    write_png(tex / "sonic0810_medallion.png", medallion_texture())


def emit_jukebox_songs():
    # schema copied from vanilla data/minecraft/jukebox_song/13.json
    for song, (seconds, comparator) in SONGS.items():
        write_json(DATA / "jukebox_song" / f"{song}.json", {
            "comparator_output": comparator,
            "description": {
                "translate": f"jukebox_song.{MOD}.{song}",
            },
            "length_in_seconds": seconds,
            "sound_event": f"{MOD}:music_disc.{song}",
        })


def emit_recipes():
    rec = DATA / "recipe" / "music"
    # 9x smithing_transform, schema copied from netherite_sword_smithing.json
    for gear in COPPER_SMITHING:
        write_json(rec / f"copper_{gear}_smithing.json", {
            "type": "minecraft:smithing_transform",
            "addition": "minecraft:copper_ingot",
            "base": f"minecraft:iron_{gear}",
            "result": {
                "id": f"minecraft:copper_{gear}",
            },
            "template": f"{MOD}:copper_upgrade_smithing_template",
        })
    # copper template: 7 copper ingots + 1 diamond + 1 blackstone -> 1
    write_json(rec / "copper_upgrade_smithing_template.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {
            "#": "minecraft:copper_ingot",
            "D": "minecraft:diamond",
            "B": "minecraft:blackstone",
        },
        "pattern": [
            "#D#",
            "#B#",
            "###",
        ],
        "result": {
            "count": 1,
            "id": f"{MOD}:copper_upgrade_smithing_template",
        },
    })
    # standard vanilla-style duplication (schema copied from
    # netherite_upgrade_smithing_template.json, themed on copper+blackstone)
    write_json(rec / "copper_upgrade_smithing_template_duplication.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {
            "#": "minecraft:copper_ingot",
            "C": "minecraft:blackstone",
            "S": f"{MOD}:copper_upgrade_smithing_template",
        },
        "pattern": [
            "#S#",
            "#C#",
            "###",
        ],
        "result": {
            "count": 2,
            "id": f"{MOD}:copper_upgrade_smithing_template",
        },
    })
    # inferno template: display/collectible, crafting recipe ONLY (no smithing use)
    write_json(rec / "inferno_upgrade_smithing_template.json", {
        "type": "minecraft:crafting_shaped",
        "category": "misc",
        "key": {
            "B": "minecraft:blackstone",
            "D": "minecraft:diamond",
            "R": "minecraft:blaze_rod",
        },
        "pattern": [
            "BDB",
            "BRB",
            "BBB",
        ],
        "result": {
            "count": 1,
            "id": f"{MOD}:inferno_upgrade_smithing_template",
        },
    })


def emit_lang():
    write_json(ASSETS / "lang" / "fragments" / "music.json", LANG)


def merge_sounds_json():
    """Appends the 3 music_disc entries; existing entries are kept EXACTLY."""
    path = ASSETS / "sounds.json"
    sounds = json.loads(path.read_text()) if path.exists() else {}
    changed = False
    for song in SONGS:
        key = f"music_disc.{song}"
        if key not in sounds:
            sounds[key] = {
                "sounds": [
                    {
                        "name": f"{MOD}:music/{song}",
                        "stream": True,
                    }
                ]
            }
            changed = True
    if changed:
        # existing file uses tab indentation
        path.write_text(json.dumps(sounds, indent="\t") + "\n")
    return changed


# ---------------------------------------------------------------------------
# Audio synthesis (all ORIGINAL, deterministic, no RNG seeds left implicit)
# ---------------------------------------------------------------------------

SR = 32000


def note_hz(semitones_from_a4: float) -> float:
    return 440.0 * (2.0 ** (semitones_from_a4 / 12.0))


def saw(freq, t):
    ph = (freq * t) % 1.0
    return 2.0 * ph - 1.0


def square(freq, t):
    return np.sign(np.sin(2 * np.pi * freq * t))


def env_decay(n, decay):
    return np.exp(-np.arange(n) / (SR * decay))


def env_adsr(n, attack, release):
    e = np.ones(n)
    a = min(int(SR * attack), n)
    r = min(int(SR * release), n)
    if a > 0:
        e[:a] = np.linspace(0.0, 1.0, a)
    if r > 0:
        e[-r:] = np.minimum(e[-r:], np.linspace(1.0, 0.0, r))
    return e


def place(buf, start_s, sig):
    i = int(start_s * SR)
    j = min(i + len(sig), len(buf))
    if i < len(buf):
        buf[i:j] += sig[: j - i]


def noise(n, seed):
    return np.random.default_rng(seed).uniform(-1.0, 1.0, n)


def synth_copper_inferno():
    """Driving sawtooth doom riff, E minor, 140 BPM, 45.0 s."""
    dur = SONGS["copper_inferno"][0]
    total = int(dur * SR)
    buf = np.zeros(total)
    sixteenth = 60.0 / 140.0 / 4.0
    e2 = note_hz(-29)  # E2

    # original 1-bar riff in semitone offsets (None = rest); gallop feel
    riff = [0, 0, 12, 0, 0, 10, 0, 0, 13, 0, 0, 7, 0, 6, 7, 3]
    bars = int(dur / (sixteenth * 16))  # 26 bars ~ 44.57 s
    for bar in range(bars):
        transpose = [0, 0, 3, -2][bar % 4]
        for step, semi in enumerate(riff):
            if semi is None:
                continue
            t0 = (bar * 16 + step) * sixteenth
            f = e2 * 2.0 ** ((semi + transpose) / 12.0)
            n = int(sixteenth * SR * 0.95)
            t = np.arange(n) / SR
            sig = 0.5 * saw(f, t) + 0.5 * saw(f * 1.006, t) + 0.35 * np.sin(2 * np.pi * f * 0.5 * t)
            sig = np.tanh(2.2 * sig) * env_decay(n, 0.09)
            place(buf, t0, 0.55 * sig)

    # kick on beats 1 and 3 + a syncopated hit, hats on 8ths
    beat = sixteenth * 4
    n_kick = int(0.12 * SR)
    t = np.arange(n_kick) / SR
    kick = np.sin(2 * np.pi * (120.0 * np.exp(-t * 18.0) + 40.0) * t) * env_decay(n_kick, 0.05)
    n_hat = int(0.03 * SR)
    hat = np.diff(noise(n_hat + 1, 810)) * env_decay(n_hat, 0.008)
    for bar in range(bars):
        base = bar * 16 * sixteenth
        for b in (0.0, 2.0, 3.5):
            place(buf, base + b * beat, 0.9 * kick)
        for h in range(8):
            place(buf, base + h * 2 * sixteenth, 0.16 * hat)

    fade = int(1.5 * SR)
    buf[-fade:] *= np.linspace(1.0, 0.0, fade)
    return buf


def synth_soda_pop():
    """Bouncy square-wave arpeggio, C major, 128 BPM, 40.0 s."""
    dur = SONGS["soda_pop"][0]
    total = int(dur * SR)
    buf = np.zeros(total)
    eighth = 60.0 / 128.0 / 2.0
    c4 = note_hz(3)  # C5? no: A4+3 = C5. use C5 for sparkle, bass below.

    chords = [0, -3, 5, 7]  # C, Am, F, G roots relative to C
    arp = [0, 4, 7, 12, 7, 4, 0, 7]  # bouncy triad + octave bounce
    bar = eighth * 8
    bars = int(dur / bar)  # 21 bars ~ 39.4 s
    for b in range(bars):
        root = chords[b % 4]
        minor = (b % 4) == 1
        for step, off in enumerate(arp):
            semi = off - 1 if (minor and off in (4,)) else off
            t0 = b * bar + step * eighth
            f = c4 * 2.0 ** ((root + semi) / 12.0)
            n = int(eighth * SR * 0.9)
            t = np.arange(n) / SR
            sig = square(f, t) * env_decay(n, 0.07)
            place(buf, t0, 0.22 * sig)
        # bass square on beats
        for q in range(4):
            f = c4 * 2.0 ** (root / 12.0) / 4.0
            n = int(eighth * 2 * SR * 0.8)
            t = np.arange(n) / SR
            place(buf, b * bar + q * eighth * 2, 0.3 * square(f, t) * env_decay(n, 0.16))
        # offbeat fizz hats
        n_hat = int(0.025 * SR)
        hat = np.diff(noise(n_hat + 1, 42)) * env_decay(n_hat, 0.007)
        for q in range(4):
            place(buf, b * bar + (q * 2 + 1) * eighth, 0.14 * hat)
    # soda "pssht" fizz sweep every 4 bars
    n_fizz = int(0.5 * SR)
    fizz = noise(n_fizz, 77) * env_decay(n_fizz, 0.18) * 0.2
    for b in range(0, bars, 4):
        place(buf, b * bar, fizz)

    fade = int(1.0 * SR)
    buf[-fade:] *= np.linspace(1.0, 0.0, fade)
    return buf


def synth_oxidation():
    """Slow ambient detuned-sine pad, A minor, 50.0 s."""
    dur = SONGS["oxidation"][0]
    total = int(dur * SR)
    buf = np.zeros(total)
    # Am9 - Fmaj7 - Cmaj7 - G6, 12.5 s each (semitones from A4, low voicings)
    prog = [
        [-24, -12, -9, -5, 2],    # A2 A3 C4 E4 B4
        [-16, -4, 0, 3, 7],       # F3 F4 A4 C5 E5 -> keep low: F3 A3? use as-is minus 12
        [-21, -9, -5, -2, 2],     # C3 C4 E4 G4 B4
        [-14, -2, 2, 5, 9],       # G3 G4 B4 D5 E5
    ]
    seg = dur / len(prog)
    n_seg = int(seg * SR)
    for i, chord in enumerate(prog):
        t = np.arange(n_seg) / SR
        pad = np.zeros(n_seg)
        for semi in chord:
            f = note_hz(semi - 12)  # drop an octave for warmth
            vib = 1.0 + 0.002 * np.sin(2 * np.pi * 0.13 * t + semi)
            pad += np.sin(2 * np.pi * f * vib * t)
            pad += 0.5 * np.sin(2 * np.pi * f * 1.003 * t)
            pad += 0.25 * np.sin(2 * np.pi * f * 2.0 * t)
        pad /= len(chord) * 1.75
        pad *= env_adsr(n_seg, attack=3.0, release=3.5)
        place(buf, i * seg, 0.8 * pad)
        # sparse soft bells (deterministic positions)
        for k, bell_off in enumerate((2.5, 6.0, 9.5)):
            f = note_hz(chord[(i + k) % len(chord)])
            n_bell = int(2.0 * SR)
            tb = np.arange(n_bell) / SR
            bell = (np.sin(2 * np.pi * f * tb) + 0.4 * np.sin(2 * np.pi * f * 2.01 * tb)) * env_decay(n_bell, 0.6)
            place(buf, i * seg + bell_off, 0.08 * bell)
    # gentle wind: heavily smoothed noise
    wind = noise(total, 2024)
    kernel = np.ones(256) / 256.0
    wind = np.convolve(wind, kernel, mode="same")
    buf += 0.10 * wind

    fade = int(3.0 * SR)
    buf[-fade:] *= np.linspace(1.0, 0.0, fade)
    buf[: int(0.5 * SR)] *= np.linspace(0.0, 1.0, int(0.5 * SR))
    return buf


SYNTHS = {
    "copper_inferno": synth_copper_inferno,
    "soda_pop": synth_soda_pop,
    "oxidation": synth_oxidation,
}


def write_wav(path: Path, buf):
    peak = np.max(np.abs(buf))
    if peak > 0:
        buf = buf / peak * 0.85
    pcm = (buf * 32767.0).astype(np.int16)
    with wave.open(str(path), "wb") as w:
        w.setnchannels(1)
        w.setsampwidth(2)
        w.setframerate(SR)
        w.writeframes(pcm.tobytes())


def emit_audio(force=False):
    out_dir = ASSETS / "sounds" / "music"
    out_dir.mkdir(parents=True, exist_ok=True)
    ffmpeg = shutil.which("ffmpeg")
    for song, synth in SYNTHS.items():
        ogg = out_dir / f"{song}.ogg"
        if ogg.exists() and not force:
            print(f"  [skip] {ogg.relative_to(ROOT)} exists")
            continue
        if ffmpeg is None:
            sys.exit("ffmpeg not found; install with: apt-get install -y ffmpeg")
        buf = synth()
        with tempfile.TemporaryDirectory() as td:
            wav = Path(td) / f"{song}.wav"
            write_wav(wav, buf)
            subprocess.run(
                [ffmpeg, "-y", "-loglevel", "error", "-i", str(wav),
                 "-ac", "1", "-c:a", "libvorbis", "-q:a", "3", str(ogg)],
                check=True,
            )
        size = ogg.stat().st_size
        print(f"  [ogg]  {ogg.relative_to(ROOT)} ({len(buf)/SR:.1f}s, {size/1024:.0f} KiB)")
        assert size < 1_500_000, f"{ogg} exceeds 1.5 MB"


def main():
    force = "--force-audio" in sys.argv
    print("music_gen: item assets ...")
    emit_item_assets()
    print("music_gen: jukebox songs ...")
    emit_jukebox_songs()
    print("music_gen: recipes ...")
    emit_recipes()
    print("music_gen: lang fragment ...")
    emit_lang()
    print("music_gen: sounds.json merge ...")
    if merge_sounds_json():
        print("  appended music_disc.* entries")
    else:
        print("  already up to date")
    print("music_gen: audio ...")
    emit_audio(force)
    print("music_gen: done")


if __name__ == "__main__":
    main()
