import SpriteKit
import UIKit

final class StatBarNode: SKNode {
    private static let maxValue: Double = 100
    private static let lowThreshold: Double = 25
    private static let fillInset: CGFloat = 3

    private let barSize: CGSize
    private let normalFillColor: UIColor
    private let normalBackgroundColor = UIColor(white: 0, alpha: 0.25)
    private let background: SKShapeNode
    private let fill: SKSpriteNode
    private var isFlashingLow = false

    init(kind: StatKind, size: CGSize) {
        let fillColor = Theme.statColor(kind)
        barSize = size
        normalFillColor = fillColor
        background = SKShapeNode(rectOf: size, cornerRadius: size.height / 2)
        fill = SKSpriteNode(color: fillColor,
                            size: CGSize(width: 0, height: size.height - StatBarNode.fillInset * 2))

        super.init()

        background.fillColor = normalBackgroundColor
        background.strokeColor = .clear
        addChild(background)

        fill.anchorPoint = CGPoint(x: 0, y: 0.5)
        fill.position = CGPoint(x: -size.width / 2 + StatBarNode.fillInset, y: 0)
        addChild(fill)

        let emojiLabel = SKLabelNode(fontNamed: Theme.fontName)
        emojiLabel.text = StatBarNode.emoji(for: kind)
        emojiLabel.fontSize = 16
        emojiLabel.verticalAlignmentMode = .center
        emojiLabel.horizontalAlignmentMode = .right
        emojiLabel.position = CGPoint(x: -size.width / 2 - 6, y: 0)
        addChild(emojiLabel)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setValue(_ value: Double, animated: Bool) {
        let clamped = min(max(value, 0), StatBarNode.maxValue)
        let fraction = CGFloat(clamped / StatBarNode.maxValue)
        let targetWidth = fraction * (barSize.width - StatBarNode.fillInset * 2)

        fill.removeAction(forKey: "resize")
        if animated {
            let resize = SKAction.resize(toWidth: targetWidth, duration: 0.25)
            resize.timingMode = .easeOut
            fill.run(resize, withKey: "resize")
        } else {
            fill.size.width = targetWidth
        }

        updateLowFlash(isLow: clamped < StatBarNode.lowThreshold)
    }

    private func updateLowFlash(isLow: Bool) {
        guard isLow != isFlashingLow else { return }
        isFlashingLow = isLow
        removeAction(forKey: "lowFlash")

        if isLow {
            let flashOn = SKAction.run { [weak self] in
                self?.fill.color = .systemRed
                self?.background.fillColor = UIColor.systemRed.withAlphaComponent(0.45)
            }
            let flashOff = SKAction.run { [weak self] in
                guard let self = self else { return }
                self.fill.color = self.normalFillColor
                self.background.fillColor = self.normalBackgroundColor
            }
            let sequence = SKAction.sequence([flashOn,
                                              SKAction.wait(forDuration: 0.3),
                                              flashOff,
                                              SKAction.wait(forDuration: 0.3)])
            run(SKAction.repeatForever(sequence), withKey: "lowFlash")
        } else {
            fill.color = normalFillColor
            background.fillColor = normalBackgroundColor
        }
    }

    private static func emoji(for kind: StatKind) -> String {
        switch kind {
        case .hunger: return "🍗"
        case .happiness: return "😊"
        case .energy: return "⚡"
        case .hygiene: return "🧼"
        }
    }
}
