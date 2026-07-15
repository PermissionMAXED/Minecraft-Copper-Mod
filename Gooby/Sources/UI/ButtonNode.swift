import SpriteKit
import UIKit

final class ButtonNode: SKNode {
    var isEnabled = true

    private let action: () -> Void
    private let hitSize: CGSize
    private var label: SKLabelNode?

    init(text: String, emoji: String?, texture: SKTexture?, size: CGSize, action: @escaping () -> Void) {
        self.action = action
        self.hitSize = size
        super.init()
        isUserInteractionEnabled = true

        if let texture = texture {
            let background = SKSpriteNode(texture: texture, size: size)
            addChild(background)
        } else {
            let background = SKShapeNode(rectOf: size, cornerRadius: Theme.cornerRadius)
            background.fillColor = Theme.buttonColor
            background.strokeColor = .clear
            addChild(background)
        }

        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.fontSize = 20
        label.fontColor = .white
        label.verticalAlignmentMode = .center
        label.horizontalAlignmentMode = .center
        if let emoji = emoji, !emoji.isEmpty {
            label.text = "\(emoji) \(text)"
        } else {
            label.text = text
        }
        addChild(label)
        self.label = label
    }

    convenience init(text: String, size: CGSize, action: @escaping () -> Void) {
        self.init(text: text, emoji: nil, texture: nil, size: size, action: action)
    }

    convenience init(iconTexture: SKTexture, diameter: CGFloat, action: @escaping () -> Void) {
        self.init(text: "", emoji: nil, texture: nil,
                  size: CGSize(width: diameter, height: diameter), action: action)
        removeAllChildren()
        label = nil

        let circle = SKShapeNode(circleOfRadius: diameter / 2)
        circle.fillColor = UIColor(white: 1, alpha: 0.9)
        circle.strokeColor = .clear
        addChild(circle)

        let iconSide = diameter * 0.55
        let icon = SKSpriteNode(texture: iconTexture, size: CGSize(width: iconSide, height: iconSide))
        addChild(icon)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setText(_ text: String) {
        label?.text = text
    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        setScale(0.92)
        AudioManager.shared.playSFX(.click)
    }

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        setScale(1.0)
        guard isEnabled, let touch = touches.first else { return }
        let location = touch.location(in: self)
        if abs(location.x) <= hitSize.width / 2 && abs(location.y) <= hitSize.height / 2 {
            action()
        }
    }

    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent?) {
        setScale(1.0)
    }
}
