import SpriteKit

final class GoobyNode: SKNode {
    var mood: Mood = .neutral

    init(color: GoobyColor, outfitID: String?) {
        super.init()

        let body = SKShapeNode(ellipseOf: CGSize(width: 220, height: 260))
        body.fillColor = Theme.bodyColor(color)
        body.strokeColor = .clear
        addChild(body)

        for x in [CGFloat(-40), CGFloat(40)] {
            let eye = SKShapeNode(circleOfRadius: 12)
            eye.fillColor = .black
            eye.strokeColor = .clear
            eye.position = CGPoint(x: x, y: 60)
            addChild(eye)
        }
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func startIdleAnimations() {}

    func stopIdleAnimations() {}

    func setTalkingLevel(_ level: Float) {}

    func playBounce() {}

    func playPokeReaction() {}

    func playTickleReaction() {}

    func playEat(completion: (() -> Void)?) {
        run(SKAction.wait(forDuration: 1.0)) {
            completion?()
        }
    }

    func playSleep(_ sleeping: Bool) {}

    func playShowerReaction() {}

    func playHappyJump() {}

    func setColor(_ color: GoobyColor) {}

    func setOutfit(_ outfitID: String?) {}

    var mouthLocalPoint: CGPoint { CGPoint(x: 0, y: 40) }
}
