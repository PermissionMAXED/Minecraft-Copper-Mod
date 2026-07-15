import SpriteKit
import UIKit

/// The star of the show: a fat vector-drawn rabbit assembled from
/// GoobyParts builders and animated with GoobyAnimations factories.
/// Origin is the body centre; intrinsic footprint is roughly 260x300 pt.
///
/// Node graph: self -> squashNode (squash/jump/wiggle) -> breatheNode
/// (idle breathing) -> body parts. Emotes/particles attach directly to
/// self so they are unaffected by squash & stretch.
final class GoobyNode: SKNode {

    // MARK: - Parts

    private let squashNode = SKNode()
    private let breatheNode = SKNode()

    private let body: SKShapeNode
    private let belly: SKShapeNode
    private let leftEar: SKShapeNode
    private let rightEar: SKShapeNode
    private let leftCheek: SKShapeNode
    private let rightCheek: SKShapeNode
    private let leftEye: SKShapeNode
    private let rightEye: SKShapeNode
    private let leftEyelid: SKShapeNode
    private let rightEyelid: SKShapeNode
    private let nose: SKShapeNode
    private let mouth: SKShapeNode
    private let leftFoot: SKShapeNode
    private let rightFoot: SKShapeNode
    private let tail: SKShapeNode
    private let hatAnchor = SKNode()

    // MARK: - State

    private var isSleeping = false
    /// True while the mouth node is showing the open-ellipse path
    /// (talking/chewing) instead of the mood mouth.
    private var isMouthOpenPath = false

    private static let squashKey = "gooby.squash"
    private static let jumpKey = "gooby.jump"
    private static let wiggleKey = "gooby.wiggle"
    private static let earWobbleKey = "gooby.earwobble"
    private static let faceKey = "gooby.face"
    private static let faceRestoreKey = "gooby.facerestore"

    var mood: Mood = .neutral {
        didSet { applyMoodFace() }
    }

    // MARK: - Init

    init(color: GoobyColor, outfitID: String?) {
        body = GoobyParts.bodyNode(color: color)
        belly = GoobyParts.bellyNode(color: color)
        leftEar = GoobyParts.earNode(color: color)
        rightEar = GoobyParts.earNode(color: color)
        leftCheek = GoobyParts.cheekNode(color: color)
        rightCheek = GoobyParts.cheekNode(color: color)
        leftEye = GoobyParts.eyeNode()
        rightEye = GoobyParts.eyeNode()
        leftEyelid = GoobyParts.eyelidNode(color: color)
        rightEyelid = GoobyParts.eyelidNode(color: color)
        nose = GoobyParts.noseNode()
        mouth = GoobyParts.mouthNode()
        leftFoot = GoobyParts.footNode(color: color)
        rightFoot = GoobyParts.footNode(color: color)
        tail = GoobyParts.tailNode(color: color)
        super.init()
        assemble()
        applyMoodFace(animated: false)
        setOutfit(outfitID)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func assemble() {
        addChild(squashNode)
        squashNode.addChild(breatheNode)

        tail.position = GoobyParts.tailCenter
        tail.zPosition = -3

        let earX = GoobyParts.earBase.x
        let earY = GoobyParts.earBase.y
        leftEar.position = CGPoint(x: -earX, y: earY)
        rightEar.position = CGPoint(x: earX, y: earY)
        leftEar.zPosition = -2
        rightEar.zPosition = -2

        body.zPosition = 0

        belly.position = GoobyParts.bellyCenter
        belly.zPosition = 1

        let footX = GoobyParts.footCenter.x
        leftFoot.position = CGPoint(x: -footX, y: GoobyParts.footCenter.y)
        rightFoot.position = CGPoint(x: footX, y: GoobyParts.footCenter.y)
        leftFoot.zPosition = 2
        rightFoot.zPosition = 2

        let cheekX = GoobyParts.cheekCenter.x
        leftCheek.position = CGPoint(x: -cheekX, y: GoobyParts.cheekCenter.y)
        rightCheek.position = CGPoint(x: cheekX, y: GoobyParts.cheekCenter.y)
        leftCheek.zPosition = 3
        rightCheek.zPosition = 3

        let eyeX = GoobyParts.eyeCenter.x
        let eyeY = GoobyParts.eyeCenter.y
        leftEye.position = CGPoint(x: -eyeX, y: eyeY)
        rightEye.position = CGPoint(x: eyeX, y: eyeY)
        leftEye.zPosition = 4
        rightEye.zPosition = 4

        // Lid origin sits at the top of the eye so yScale grows downward.
        let lidY = eyeY + GoobyParts.eyeSize.height / 2
        leftEyelid.position = CGPoint(x: -eyeX, y: lidY)
        rightEyelid.position = CGPoint(x: eyeX, y: lidY)
        leftEyelid.zPosition = 5
        rightEyelid.zPosition = 5

        nose.position = GoobyParts.noseCenter
        nose.zPosition = 4

        mouth.position = GoobyParts.mouthCenter
        mouth.zPosition = 4

        hatAnchor.position = GoobyParts.hatAnchor
        hatAnchor.zPosition = 6

        for part in [tail, leftEar, rightEar, body, belly,
                     leftFoot, rightFoot, leftCheek, rightCheek,
                     leftEye, rightEye, leftEyelid, rightEyelid,
                     nose, mouth] {
            breatheNode.addChild(part)
        }
        breatheNode.addChild(hatAnchor)
    }

    // MARK: - Mood face

    private var restingLidScale: CGFloat {
        if isSleeping { return 1.0 }
        switch mood {
        case .sleepy: return 0.7
        case .sad: return 0.4
        default: return 0.05
        }
    }

    private var restingEarAngle: CGFloat {
        switch mood {
        case .sad, .sleepy: return GoobyParts.droopEarAngle
        default: return GoobyParts.normalEarAngle
        }
    }

    private func applyMoodFace(animated: Bool = true) {
        if !isMouthOpenPath {
            applyMoodMouth()
        }
        let lidScale = restingLidScale
        let earAngle = restingEarAngle
        if animated {
            let lids = SKAction.scaleY(to: lidScale, duration: 0.15)
            leftEyelid.run(lids, withKey: GoobyNode.faceKey)
            rightEyelid.run(lids, withKey: GoobyNode.faceKey)
            leftEar.run(.rotate(toAngle: earAngle, duration: 0.18),
                        withKey: GoobyNode.faceKey)
            rightEar.run(.rotate(toAngle: -earAngle, duration: 0.18),
                         withKey: GoobyNode.faceKey)
        } else {
            leftEyelid.yScale = lidScale
            rightEyelid.yScale = lidScale
            leftEar.zRotation = earAngle
            rightEar.zRotation = -earAngle
        }
    }

    private func applyMoodMouth() {
        isMouthOpenPath = false
        mouth.yScale = 1
        mouth.fillColor = .clear
        switch mood {
        case .happy, .neutral:
            mouth.path = GoobyParts.smileMouthPath()
        case .sad, .hungry, .sleepy, .dirty:
            mouth.path = GoobyParts.flatMouthPath()
        }
    }

    private func showSurprisedMouth(for duration: TimeInterval) {
        isMouthOpenPath = false
        mouth.yScale = 1
        mouth.path = GoobyParts.surprisedMouthPath()
        mouth.fillColor = GoobyParts.mouthFillColor
        removeAction(forKey: GoobyNode.faceRestoreKey)
        run(.sequence([
            .wait(forDuration: duration),
            .run { [weak self] in self?.applyMoodMouth() }
        ]), withKey: GoobyNode.faceRestoreKey)
    }

    /// Swaps in the open-ellipse mouth (on first call) and scales its
    /// height to 6 + openness * 34 points. Cheap enough for per-frame use.
    private func setMouthOpen(_ openness: CGFloat) {
        let clamped = max(0, min(1, openness))
        if !isMouthOpenPath {
            isMouthOpenPath = true
            mouth.path = GoobyParts.openMouthPath()
            mouth.fillColor = GoobyParts.mouthFillColor
        }
        mouth.yScale = (6 + 34 * clamped) / GoobyParts.openMouthSize.height
    }

    // MARK: - Idle animations

    func startIdleAnimations() {
        breatheNode.removeAction(forKey: GoobyAnimations.breatheKey)
        breatheNode.run(GoobyAnimations.breathe(),
                        withKey: GoobyAnimations.breatheKey)

        removeAction(forKey: GoobyAnimations.blinkKey)
        let blinkLoop = SKAction.repeatForever(.sequence([
            .wait(forDuration: 3.0, withRange: 3.0),
            .run { [weak self] in self?.performBlink() }
        ]))
        run(blinkLoop, withKey: GoobyAnimations.blinkKey)
    }

    func stopIdleAnimations() {
        breatheNode.removeAction(forKey: GoobyAnimations.breatheKey)
        breatheNode.yScale = 1
        removeAction(forKey: GoobyAnimations.blinkKey)
    }

    private func performBlink() {
        guard !isSleeping else { return }
        let blink = GoobyAnimations.blink(reopenTo: restingLidScale)
        leftEyelid.run(blink, withKey: GoobyNode.faceKey)
        rightEyelid.run(blink, withKey: GoobyNode.faceKey)
    }

    // MARK: - Talking

    func setTalkingLevel(_ level: Float) {
        let clamped = max(0, min(1, CGFloat(level)))
        if clamped < 0.05 {
            if isMouthOpenPath {
                applyMoodMouth()
            }
            return
        }
        setMouthOpen(clamped)
    }

    // MARK: - Reactions

    func playBounce() {
        squashNode.run(GoobyAnimations.bounce(), withKey: GoobyNode.squashKey)
    }

    func playPokeReaction() {
        AudioManager.shared.playSFX(.pop)
        squashNode.run(GoobyAnimations.pokeSquash(), withKey: GoobyNode.squashKey)
        showSurprisedMouth(for: 0.4)
        leftEar.run(GoobyAnimations.earWobble(), withKey: GoobyNode.earWobbleKey)
        rightEar.run(GoobyAnimations.earWobble(), withKey: GoobyNode.earWobbleKey)
    }

    func playTickleReaction() {
        squashNode.run(GoobyAnimations.tickleWiggle(), withKey: GoobyNode.wiggleKey)
        let hearts = SKSpriteNode(texture: AssetProvider.texture(.emoteHearts))
        hearts.size = CGSize(width: 44, height: 44)
        hearts.position = CGPoint(x: CGFloat.random(in: -30...30), y: 130)
        hearts.zPosition = 20
        addChild(hearts)
        hearts.run(GoobyAnimations.floatUpAndVanish(dy: 90, duration: 0.9))
    }

    func playEat(completion: (() -> Void)?) {
        AudioManager.shared.playSFX(.eat)
        var steps: [SKAction] = [
            .run { [weak self] in self?.setMouthOpen(0.9) },
            .wait(forDuration: 0.15)
        ]
        for _ in 0..<3 {
            steps.append(.run { [weak self] in
                guard let self = self else { return }
                self.setMouthOpen(0.1)
                self.squashNode.run(GoobyAnimations.chewPulse(),
                                    withKey: GoobyNode.squashKey)
            })
            steps.append(.wait(forDuration: 0.15))
            steps.append(.run { [weak self] in self?.setMouthOpen(0.9) })
            steps.append(.wait(forDuration: 0.15))
        }
        steps.append(.run { [weak self] in
            guard let self = self else { return }
            self.setMouthOpen(0)
            self.squashNode.run(GoobyAnimations.swallowPulse(),
                                withKey: GoobyNode.squashKey)
        })
        steps.append(.wait(forDuration: 0.2))
        steps.append(.run { [weak self] in self?.applyMoodMouth() })
        // Single terminal step: completion fires exactly once per call.
        steps.append(.run { completion?() })
        run(.sequence(steps))
    }

    func playSleep(_ sleeping: Bool) {
        isSleeping = sleeping
        if sleeping {
            let close = SKAction.scaleY(to: 1.0, duration: 0.25)
            leftEyelid.run(close, withKey: GoobyNode.faceKey)
            rightEyelid.run(close, withKey: GoobyNode.faceKey)

            removeAction(forKey: GoobyAnimations.zzzKey)
            let spawn = SKAction.run { [weak self] in
                guard let self = self else { return }
                let zzz = SKSpriteNode(texture: AssetProvider.texture(.emoteSleeps))
                zzz.size = CGSize(width: 44, height: 44)
                zzz.position = CGPoint(x: 58, y: 140)
                zzz.zPosition = 20
                self.addChild(zzz)
                zzz.run(GoobyAnimations.floatUpAndVanish(dy: 70, duration: 1.6))
            }
            run(.repeatForever(.sequence([spawn, .wait(forDuration: 1.8)])),
                withKey: GoobyAnimations.zzzKey)
        } else {
            removeAction(forKey: GoobyAnimations.zzzKey)
            applyMoodFace()
        }
    }

    func playShowerReaction() {
        if !isMouthOpenPath {
            mouth.yScale = 1
            mouth.fillColor = .clear
            mouth.path = GoobyParts.smileMouthPath()
        }
        squashNode.run(GoobyAnimations.shiver(), withKey: GoobyNode.wiggleKey)
        for index in 0..<5 {
            let bubble = SKSpriteNode(texture: AssetProvider.texture(.particleCircle))
            let side = CGFloat.random(in: 10...18)
            bubble.size = CGSize(width: side, height: side)
            bubble.alpha = 0.85
            bubble.position = CGPoint(x: CGFloat.random(in: -80...80),
                                      y: CGFloat.random(in: -20...80))
            bubble.zPosition = 20
            addChild(bubble)
            bubble.run(.sequence([
                .wait(forDuration: 0.06 * Double(index)),
                GoobyAnimations.floatUpAndVanish(dy: CGFloat.random(in: 60...110),
                                                 duration: 0.8)
            ]))
        }
    }

    func playHappyJump() {
        squashNode.run(GoobyAnimations.happyJump(), withKey: GoobyNode.jumpKey)
        let starCount = 6
        for index in 0..<starCount {
            let star = SKSpriteNode(texture: AssetProvider.texture(.emoteStar))
            star.size = CGSize(width: 28, height: 28)
            star.position = CGPoint(x: 0, y: 60)
            star.zPosition = 20
            addChild(star)
            let angle = CGFloat(index) / CGFloat(starCount) * 2 * .pi
            let move = SKAction.moveBy(x: cos(angle) * 90,
                                       y: sin(angle) * 90 + 30,
                                       duration: 0.5)
            move.timingMode = .easeOut
            star.run(.sequence([
                .group([move, .fadeOut(withDuration: 0.5)]),
                .removeFromParent()
            ]))
        }
    }

    // MARK: - Appearance

    func setColor(_ color: GoobyColor) {
        let bodyFill = Theme.bodyColor(color)
        let bellyFill = Theme.bellyColor(color)
        body.fillColor = bodyFill
        belly.fillColor = bellyFill
        leftEar.fillColor = bodyFill
        rightEar.fillColor = bodyFill
        leftCheek.fillColor = bodyFill
        rightCheek.fillColor = bodyFill
        leftFoot.fillColor = bodyFill
        rightFoot.fillColor = bodyFill
        leftEyelid.fillColor = bodyFill
        rightEyelid.fillColor = bodyFill
        tail.fillColor = bellyFill
    }

    func setOutfit(_ outfitID: String?) {
        hatAnchor.removeAllChildren()
        guard let outfitID = outfitID,
              let hat = GoobyParts.hatNode(outfitID: outfitID) else { return }
        hatAnchor.addChild(hat)
    }

    /// Actual mouth centre in GoobyNode-local coordinates, accounting for
    /// the current mouth path and any squash/jump transforms.
    var mouthLocalPoint: CGPoint {
        let frame = mouth.calculateAccumulatedFrame()
        guard frame.width > 0 || frame.height > 0 else {
            return convert(mouth.position, from: breatheNode)
        }
        return convert(CGPoint(x: frame.midX, y: frame.midY), from: breatheNode)
    }
}
