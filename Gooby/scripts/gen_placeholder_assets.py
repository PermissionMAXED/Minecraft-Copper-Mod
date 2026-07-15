#!/usr/bin/env python3
"""Generate placeholder textures for Gooby (64x64 rounded rects, distinct hue
per name, white initial letter). Never overwrites existing files."""

import colorsys
import hashlib
from pathlib import Path

from PIL import Image, ImageDraw, ImageFont

TEX_DIR = Path(__file__).resolve().parent.parent / "Resources" / "Textures"

TEXTURE_NAMES = [
    "tex_icon_back", "tex_icon_home", "tex_icon_gear", "tex_icon_cart",
    "tex_icon_music_on", "tex_icon_music_off", "tex_icon_audio_on",
    "tex_icon_audio_off", "tex_icon_pause", "tex_icon_star",
    "tex_icon_trophy", "tex_icon_gamepad", "tex_icon_question",
    "tex_icon_cross", "tex_icon_checkmark",
    "tex_btn_blue", "tex_btn_green", "tex_btn_red", "tex_btn_yellow",
    "tex_btn_grey", "tex_btn_round_blue", "tex_bar_bg", "tex_bar_fill",
    "tex_emote_heart", "tex_emote_hearts", "tex_emote_sleeps",
    "tex_emote_music", "tex_emote_star", "tex_emote_anger",
    "tex_emote_exclamation", "tex_emote_question", "tex_emote_drops",
    "tex_emote_cloud",
    "tex_particle_star", "tex_particle_circle", "tex_particle_smoke",
    "tex_particle_light", "tex_particle_magic",
]

SIZE = 64
RADIUS = 12


def hue_for(name: str) -> tuple[int, int, int]:
    digest = hashlib.md5(name.encode()).hexdigest()
    hue = int(digest[:4], 16) / 0xFFFF
    r, g, b = colorsys.hsv_to_rgb(hue, 0.65, 0.85)
    return int(r * 255), int(g * 255), int(b * 255)


def initial_for(name: str) -> str:
    # Use the first letter after the "tex_<category>_" prefix, e.g.
    # tex_icon_back -> "B", tex_btn_blue -> "B", tex_emote_heart -> "H".
    parts = name.split("_")
    word = parts[2] if len(parts) > 2 else parts[-1]
    return word[0].upper()


def load_font() -> ImageFont.ImageFont:
    for path in (
        "/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf",
        "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf",
    ):
        try:
            return ImageFont.truetype(path, 36)
        except OSError:
            continue
    return ImageFont.load_default()


def generate(name: str, font: ImageFont.ImageFont) -> None:
    dst = TEX_DIR / f"{name}.png"
    if dst.exists():
        print(f"[skip] {dst.name} exists")
        return
    img = Image.new("RGBA", (SIZE, SIZE), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    draw.rounded_rectangle(
        [2, 2, SIZE - 3, SIZE - 3], radius=RADIUS, fill=hue_for(name)
    )
    letter = initial_for(name)
    left, top, right, bottom = draw.textbbox((0, 0), letter, font=font)
    x = (SIZE - (right - left)) / 2 - left
    y = (SIZE - (bottom - top)) / 2 - top
    draw.text((x, y), letter, fill=(255, 255, 255, 255), font=font)
    img.save(dst, "PNG")
    print(f"[gen ] {dst.name}")


def main() -> None:
    TEX_DIR.mkdir(parents=True, exist_ok=True)
    font = load_font()
    for name in TEXTURE_NAMES:
        generate(name, font)


if __name__ == "__main__":
    main()
