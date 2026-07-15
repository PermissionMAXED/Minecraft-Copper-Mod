#!/usr/bin/env bash
# Fetch Kenney CC0 asset packs and install Gooby textures/audio.
# Idempotent: skips downloads/conversions whose outputs already exist.
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
GOOBY_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"
TEX_DIR="$GOOBY_DIR/Resources/Textures"
AUD_DIR="$GOOBY_DIR/Resources/Audio"
WORK_DIR="/tmp/kenney_assets"

mkdir -p "$TEX_DIR" "$AUD_DIR" "$WORK_DIR"

declare -A FALLBACK_URLS=(
  [ui-pack]="https://kenney.nl/media/pages/assets/ui-pack/f651646eab-1718203990/kenney_ui-pack.zip"
  [interface-sounds]="https://kenney.nl/media/pages/assets/interface-sounds/fa43c1dd4d-1677589452/kenney_interface-sounds.zip"
  [emotes-pack]="https://kenney.nl/media/pages/assets/emotes-pack/d00a3dcb06-1677578798/kenney_emotes-pack.zip"
  [game-icons]="https://kenney.nl/media/pages/assets/game-icons/1ebf9c14af-1677661579/kenney_game-icons.zip"
  [particle-pack]="https://kenney.nl/media/pages/assets/particle-pack/f8fe0f8cb8-1677578741/kenney_particle-pack.zip"
  [music-jingles]="https://kenney.nl/media/pages/assets/music-jingles/f37e530b9e-1677590399/kenney_music-jingles.zip"
)

fetch_pack() {
  local slug="$1"
  local zip="$WORK_DIR/$slug.zip"
  local dir="$WORK_DIR/$slug"
  if [ -d "$dir" ] && [ -n "$(ls -A "$dir" 2>/dev/null)" ]; then
    echo "[skip] $slug already extracted"
    return 0
  fi
  local url
  # Scrape the asset page for the current zip URL; "|| true" guards pipefail
  # (grep exits 1 on no match, and head -1 can SIGPIPE upstream commands).
  url=$(curl -s "https://kenney.nl/assets/$slug" | grep -o "href='[^']*\.zip'" | grep -o "https[^']*" | head -1 || true)
  if [ -z "$url" ]; then
    url="${FALLBACK_URLS[$slug]}"
    echo "[note] $slug: scrape failed, using pinned fallback URL"
  fi
  echo "[get ] $slug <- $url"
  curl -fsSL --retry 3 --max-time 300 -o "$zip" "$url"
  mkdir -p "$dir"
  unzip -oq "$zip" -d "$dir"
}

# Locate a file inside an extracted pack by relative sub-path; if the exact
# path is absent, fall back to the closest basename match and note it.
find_src() {
  local dir="$1" relpath="$2"
  local hit
  hit=$(find "$dir" -type f -path "*/$relpath" | head -1 || true)
  if [ -z "$hit" ]; then
    local base
    base="$(basename "$relpath")"
    hit=$(find "$dir" -type f -iname "$base" | head -1 || true)
    [ -n "$hit" ] && echo "[note] closest match for '$relpath': ${hit#"$dir"/}" >&2
  fi
  printf '%s' "$hit"
}

# Copy a PNG, downscaling to max width 512 (aspect kept) via Pillow.
install_tex() {
  local slug="$1" relpath="$2" dst="$TEX_DIR/$3"
  if [ -f "$dst" ]; then echo "[skip] $(basename "$dst") exists"; return 0; fi
  local src
  src=$(find_src "$WORK_DIR/$slug" "$relpath")
  if [ -z "$src" ]; then
    echo "[warn] $slug: no source found for '$relpath' -> $(basename "$dst")" >&2
    return 0
  fi
  python3 - "$src" "$dst" <<'PY'
import sys
from PIL import Image
src, dst = sys.argv[1], sys.argv[2]
img = Image.open(src)
if img.width > 512:
    h = max(1, round(img.height * 512 / img.width))
    img = img.resize((512, h), Image.LANCZOS)
img.save(dst, "PNG")
PY
  echo "[tex ] $(basename "$dst")"
}

# Convert an OGG to AAC .m4a for iOS playback.
install_audio() {
  local slug="$1" relpath="$2" dst="$AUD_DIR/$3"
  if [ -f "$dst" ]; then echo "[skip] $(basename "$dst") exists"; return 0; fi
  local src
  src=$(find_src "$WORK_DIR/$slug" "$relpath")
  if [ -z "$src" ]; then
    echo "[warn] $slug: no source found for '$relpath' -> $(basename "$dst")" >&2
    return 0
  fi
  ffmpeg -y -loglevel error -i "$src" -c:a aac -b:a 128k "$dst"
  echo "[snd ] $(basename "$dst")"
}

for slug in ui-pack interface-sounds emotes-pack game-icons particle-pack music-jingles; do
  fetch_pack "$slug"
done

# --- game-icons: PNG/White/2x/ ---
install_tex game-icons "PNG/White/2x/arrowLeft.png"  tex_icon_back.png
install_tex game-icons "PNG/White/2x/home.png"       tex_icon_home.png
install_tex game-icons "PNG/White/2x/gear.png"       tex_icon_gear.png
install_tex game-icons "PNG/White/2x/shoppingCart.png" tex_icon_cart.png
install_tex game-icons "PNG/White/2x/musicOn.png"    tex_icon_music_on.png
install_tex game-icons "PNG/White/2x/musicOff.png"   tex_icon_music_off.png
install_tex game-icons "PNG/White/2x/audioOn.png"    tex_icon_audio_on.png
install_tex game-icons "PNG/White/2x/audioOff.png"   tex_icon_audio_off.png
install_tex game-icons "PNG/White/2x/pause.png"      tex_icon_pause.png
install_tex game-icons "PNG/White/2x/star.png"       tex_icon_star.png
install_tex game-icons "PNG/White/2x/trophy.png"     tex_icon_trophy.png
install_tex game-icons "PNG/White/2x/gamepad.png"    tex_icon_gamepad.png
install_tex game-icons "PNG/White/2x/question.png"   tex_icon_question.png
install_tex game-icons "PNG/White/2x/cross.png"      tex_icon_cross.png
install_tex game-icons "PNG/White/2x/checkmark.png"  tex_icon_checkmark.png

# --- ui-pack: PNG/<Color>/Default/ ---
install_tex ui-pack "PNG/Blue/Default/button_rectangle_gradient.png"   tex_btn_blue.png
install_tex ui-pack "PNG/Green/Default/button_rectangle_gradient.png"  tex_btn_green.png
install_tex ui-pack "PNG/Red/Default/button_rectangle_gradient.png"    tex_btn_red.png
install_tex ui-pack "PNG/Yellow/Default/button_rectangle_gradient.png" tex_btn_yellow.png
install_tex ui-pack "PNG/Grey/Default/button_rectangle_gradient.png"   tex_btn_grey.png
install_tex ui-pack "PNG/Blue/Default/button_round_gradient.png"       tex_btn_round_blue.png
install_tex ui-pack "PNG/Grey/Default/slide_horizontal_grey.png"       tex_bar_bg.png
install_tex ui-pack "PNG/Blue/Default/slide_horizontal_color.png"      tex_bar_fill.png

# --- emotes-pack: PNG/Vector/Style 1/ ---
install_tex emotes-pack "PNG/Vector/Style 1/emote_heart.png"        tex_emote_heart.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_hearts.png"       tex_emote_hearts.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_sleeps.png"       tex_emote_sleeps.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_music.png"        tex_emote_music.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_star.png"         tex_emote_star.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_anger.png"        tex_emote_anger.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_exclamation.png"  tex_emote_exclamation.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_question.png"     tex_emote_question.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_drops.png"        tex_emote_drops.png
install_tex emotes-pack "PNG/Vector/Style 1/emote_cloud.png"        tex_emote_cloud.png

# --- particle-pack: PNG (Transparent)/ ---
install_tex particle-pack "PNG (Transparent)/star_04.png"   tex_particle_star.png
install_tex particle-pack "PNG (Transparent)/circle_05.png" tex_particle_circle.png
install_tex particle-pack "PNG (Transparent)/smoke_04.png"  tex_particle_smoke.png
install_tex particle-pack "PNG (Transparent)/light_01.png"  tex_particle_light.png
install_tex particle-pack "PNG (Transparent)/magic_01.png"  tex_particle_magic.png

# --- interface-sounds: Audio/ ---
install_audio interface-sounds "Audio/click_001.ogg"        sfx_click.m4a
install_audio interface-sounds "Audio/back_002.ogg"         sfx_back.m4a
install_audio interface-sounds "Audio/confirmation_001.ogg" sfx_confirm.m4a
install_audio interface-sounds "Audio/error_004.ogg"        sfx_error.m4a
install_audio interface-sounds "Audio/glass_002.ogg"        sfx_coin.m4a
install_audio interface-sounds "Audio/drop_003.ogg"         sfx_eat.m4a
install_audio interface-sounds "Audio/drop_002.ogg"         sfx_drop.m4a
install_audio interface-sounds "Audio/drop_001.ogg"         sfx_splash.m4a
install_audio interface-sounds "Audio/bong_001.ogg"         sfx_pop.m4a

# --- music-jingles ---
install_audio music-jingles "Audio/Pizzicato jingles/jingles_PIZZI00.ogg" sfx_win.m4a
install_audio music-jingles "Audio/8-Bit jingles/jingles_NES09.ogg"       sfx_lose.m4a
install_audio music-jingles "Audio/Steel jingles/jingles_STEEL00.ogg"     music_main.m4a

echo "Done. Textures: $(ls "$TEX_DIR" | wc -l), Audio: $(ls "$AUD_DIR" | wc -l)"
