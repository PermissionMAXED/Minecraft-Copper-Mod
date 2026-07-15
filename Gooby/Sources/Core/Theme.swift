import UIKit

enum Theme {
    static let fontName = "AvenirNext-Bold"

    static func backgroundColor(for room: Room) -> UIColor {
        switch room {
        case .home: return UIColor(hex: 0xA8D8EA)
        case .kitchen: return UIColor(hex: 0xFFE9C7)
        case .bathroom: return UIColor(hex: 0xCFF2F0)
        case .bedroom: return UIColor(hex: 0x2E3358)
        case .gameRoom: return UIColor(hex: 0xE8D5F7)
        case .shop: return UIColor(hex: 0xFFF3B8)
        }
    }

    static func statColor(_ kind: StatKind) -> UIColor {
        switch kind {
        case .hunger: return UIColor(hex: 0xFF9F45)
        case .happiness: return UIColor(hex: 0xFF6FA5)
        case .energy: return UIColor(hex: 0xFFD93D)
        case .hygiene: return UIColor(hex: 0x4ECDC4)
        }
    }

    static func bodyColor(_ color: GoobyColor) -> UIColor {
        switch color {
        case .grey: return UIColor(hex: 0xB9BDC4)
        case .brown: return UIColor(hex: 0xC49A6C)
        case .white: return UIColor(hex: 0xF2F2F0)
        case .pink: return UIColor(hex: 0xF2A7C3)
        }
    }

    static func bellyColor(_ color: GoobyColor) -> UIColor {
        var red: CGFloat = 0, green: CGFloat = 0, blue: CGFloat = 0, alpha: CGFloat = 1
        bodyColor(color).getRed(&red, green: &green, blue: &blue, alpha: &alpha)
        let lighten: (CGFloat) -> CGFloat = { $0 + (1 - $0) * 0.45 }
        return UIColor(red: lighten(red), green: lighten(green), blue: lighten(blue), alpha: alpha)
    }

    static let buttonColor = UIColor(red: 0.30, green: 0.62, blue: 0.90, alpha: 1)
    static let panelColor = UIColor(white: 1, alpha: 0.85)
    static let textColor = UIColor(white: 0.15, alpha: 1)
    static let cornerRadius: CGFloat = 14
    static let buttonHeight: CGFloat = 56
    static let statBarSize = CGSize(width: 150, height: 22)
}

fileprivate extension UIColor {
    convenience init(hex: UInt32) {
        self.init(red: CGFloat((hex >> 16) & 0xFF) / 255,
                  green: CGFloat((hex >> 8) & 0xFF) / 255,
                  blue: CGFloat(hex & 0xFF) / 255,
                  alpha: 1)
    }
}
