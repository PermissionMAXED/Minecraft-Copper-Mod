import SpriteKit
import UIKit

enum TextureID: String, CaseIterable {
    case iconBack = "tex_icon_back"
    case iconHome = "tex_icon_home"
    case iconGear = "tex_icon_gear"
    case iconCart = "tex_icon_cart"
    case iconMusicOn = "tex_icon_music_on"
    case iconMusicOff = "tex_icon_music_off"
    case iconAudioOn = "tex_icon_audio_on"
    case iconAudioOff = "tex_icon_audio_off"
    case iconPause = "tex_icon_pause"
    case iconStar = "tex_icon_star"
    case iconTrophy = "tex_icon_trophy"
    case iconGamepad = "tex_icon_gamepad"
    case iconQuestion = "tex_icon_question"
    case iconCross = "tex_icon_cross"
    case iconCheckmark = "tex_icon_checkmark"
    case btnBlue = "tex_btn_blue"
    case btnGreen = "tex_btn_green"
    case btnRed = "tex_btn_red"
    case btnYellow = "tex_btn_yellow"
    case btnGrey = "tex_btn_grey"
    case btnRoundBlue = "tex_btn_round_blue"
    case barBg = "tex_bar_bg"
    case barFill = "tex_bar_fill"
    case emoteHeart = "tex_emote_heart"
    case emoteHearts = "tex_emote_hearts"
    case emoteSleeps = "tex_emote_sleeps"
    case emoteMusic = "tex_emote_music"
    case emoteStar = "tex_emote_star"
    case emoteAnger = "tex_emote_anger"
    case emoteExclamation = "tex_emote_exclamation"
    case emoteQuestion = "tex_emote_question"
    case emoteDrops = "tex_emote_drops"
    case emoteCloud = "tex_emote_cloud"
    case particleStar = "tex_particle_star"
    case particleCircle = "tex_particle_circle"
    case particleSmoke = "tex_particle_smoke"
    case particleLight = "tex_particle_light"
    case particleMagic = "tex_particle_magic"
}

enum AssetProvider {
    private static var cache: [TextureID: SKTexture] = [:]

    static func texture(_ id: TextureID) -> SKTexture {
        if let cached = cache[id] {
            return cached
        }
        let texture: SKTexture
        if hasRealAsset(id) {
            texture = SKTexture(imageNamed: id.rawValue)
        } else {
            texture = SKTexture(image: placeholderImage(for: id))
        }
        cache[id] = texture
        return texture
    }

    static func hasRealAsset(_ id: TextureID) -> Bool {
        return Bundle.main.url(forResource: id.rawValue, withExtension: "png") != nil
    }

    private static func placeholderImage(for id: TextureID) -> UIImage {
        let size = CGSize(width: 64, height: 64)
        let renderer = UIGraphicsImageRenderer(size: size)
        return renderer.image { _ in
            // Deterministic hue derived from the raw value.
            var hash: UInt32 = 0
            for scalar in id.rawValue.unicodeScalars {
                hash = hash &* 31 &+ scalar.value
            }
            let hue = CGFloat(hash % 360) / 360
            let color = UIColor(hue: hue, saturation: 0.55, brightness: 0.75, alpha: 1)
            color.setFill()
            let rect = CGRect(origin: .zero, size: size)
            UIBezierPath(roundedRect: rect, cornerRadius: 12).fill()

            let suffix = String(id.rawValue.dropFirst("tex_".count))
            let letter = String(suffix.prefix(1)).uppercased()
            let attributes: [NSAttributedString.Key: Any] = [
                .font: UIFont.boldSystemFont(ofSize: 32),
                .foregroundColor: UIColor.white
            ]
            let textSize = (letter as NSString).size(withAttributes: attributes)
            let point = CGPoint(x: (size.width - textSize.width) / 2,
                                y: (size.height - textSize.height) / 2)
            (letter as NSString).draw(at: point, withAttributes: attributes)
        }
    }
}
