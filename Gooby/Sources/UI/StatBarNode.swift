import SpriteKit
import UIKit

final class StatBarNode: SKNode {
    init(kind: StatKind, size: CGSize) {
        super.init()
        let background = SKShapeNode(rectOf: size, cornerRadius: size.height / 2)
        background.fillColor = UIColor(white: 0.85, alpha: 1)
        background.strokeColor = .clear
        addChild(background)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setValue(_ value: Double, animated: Bool) {
        // Stub: full fill/animation behaviour ships in a later work package.
    }
}
