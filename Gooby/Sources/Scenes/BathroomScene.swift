import SpriteKit
import UIKit

final class BathroomScene: BaseScene {
    private var gooby: GoobyNode!
    private var sponge: SKShapeNode!
    private var dirtContainer: SKNode!

    private let spongeHomePosition = CGPoint(x: 130, y: -290)

    private var isDraggingSponge = false
    private var isBusy = false
    private var didCelebrateClean = false
    private var lastScrubTime: TimeInterval = 0
    private var lastSplashTime: TimeInterval = 0
    private var stateObserver: NSObjectProtocol?

    init() {
        super.init(room: .bathroom)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    deinit {
        if let observer = stateObserver {
            NotificationCenter.default.removeObserver(observer)
        }
    }

    override func buildScene() {
        addTitle("Bathroom")
        addBackButton(to: .home)

        let gooby = GoobyNode(color: GameState.shared.goobyColor,
                              outfitID: GameState.shared.equippedOutfitID)
        gooby.position = CGPoint(x: 0, y: -40)
        gooby.zPosition = 20
        addChild(gooby)
        gooby.startIdleAnimations()
        gooby.mood = GameState.shared.stats.mood
        self.gooby = gooby

        buildDirtOverlay(on: gooby)
        buildSponge()
        buildButtons()
        refreshDirt()

        stateObserver = NotificationCenter.default.addObserver(forName: .gameStateDidChange,
                                                               object: nil,
                                                               queue: .main) { [weak self] _ in
            guard let self = self else { return }
            self.refreshDirt()
            self.gooby.mood = GameState.shared.stats.mood
            if GameState.shared.stats.hygiene >= 100 && !self.didCelebrateClean {
                self.didCelebrateClean = true
                self.gooby.playHappyJump()
                self.showToast("Squeaky clean!")
            }
        }
    }

    override func willMove(from view: SKView) {
        if let observer = stateObserver {
            NotificationCenter.default.removeObserver(observer)
            stateObserver = nil
        }
        super.willMove(from: view)
    }

    // MARK: - Setup

    private func buildDirtOverlay(on gooby: GoobyNode) {
        let container = SKNode()
        container.zPosition = 5
        for _ in 0..<Int.random(in: 8...12) {
            let spot = SKShapeNode(circleOfRadius: CGFloat.random(in: 9...17))
            spot.fillColor = UIColor.brown.withAlphaComponent(0.4)
            spot.strokeColor = .clear
            spot.position = CGPoint(x: CGFloat.random(in: -75...75),
                                    y: CGFloat.random(in: -100...80))
            container.addChild(spot)
        }
        gooby.addChild(container)
        dirtContainer = container
    }

    private func buildSponge() {
        let sponge = SKShapeNode(rectOf: CGSize(width: 70, height: 50), cornerRadius: 12)
        sponge.fillColor = .yellow
        sponge.strokeColor = UIColor(red: 0.85, green: 0.75, blue: 0.1, alpha: 1)
        sponge.lineWidth = 3
        sponge.position = spongeHomePosition
        sponge.zPosition = 200
        addChild(sponge)
        self.sponge = sponge
    }

    private func buildButtons() {
        let shower = ButtonNode(text: "🚿", size: CGSize(width: 56, height: 56)) { [weak self] in
            self?.startShower()
        }
        shower.position = CGPoint(x: 145, y: topY)
        shower.zPosition = 900
        addChild(shower)

        let toilet = ButtonNode(text: "🚽", size: CGSize(width: 56, height: 56)) { [weak self] in
            self?.runToiletGag()
        }
        toilet.position = CGPoint(x: 145, y: topY - 70)
        toilet.zPosition = 900
        addChild(toilet)
    }

    private func refreshDirt() {
        let hygiene = GameState.shared.stats.hygiene
        dirtContainer?.alpha = CGFloat(1 - hygiene / 100)
    }

    // MARK: - Sponge dragging

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first, let sponge = sponge else { return }
        let location = touch.location(in: self)
        if sponge.frame.insetBy(dx: -12, dy: -12).contains(location) {
            isDraggingSponge = true
        }
    }

    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard isDraggingSponge, let touch = touches.first else { return }
        sponge?.position = touch.location(in: self)
    }

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        endSpongeDrag()
    }

    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent?) {
        endSpongeDrag()
    }

    private func endSpongeDrag() {
        guard isDraggingSponge else { return }
        isDraggingSponge = false
        let goHome = SKAction.move(to: spongeHomePosition, duration: 0.25)
        goHome.timingMode = .easeOut
        sponge?.run(goHome)
    }

    override func update(_ currentTime: TimeInterval) {
        super.update(currentTime)
        guard isDraggingSponge, let sponge = sponge, let gooby = gooby else { return }
        guard sponge.frame.intersects(gooby.calculateAccumulatedFrame()) else { return }

        if currentTime - lastScrubTime >= 0.25 {
            lastScrubTime = currentTime
            GameState.shared.clean(amount: 4)
            spawnBubbles(at: sponge.position)
            if currentTime - lastSplashTime >= 0.5 {
                lastSplashTime = currentTime
                AudioManager.shared.playSFX(.splash)
            }
        }
    }

    private func spawnBubbles(at point: CGPoint) {
        for _ in 0..<3 {
            let bubble = SKSpriteNode(texture: AssetProvider.texture(.particleCircle),
                                      size: CGSize(width: 22, height: 22))
            bubble.position = CGPoint(x: point.x + CGFloat.random(in: -24...24),
                                      y: point.y + CGFloat.random(in: -18...18))
            bubble.zPosition = 300
            bubble.alpha = 0.9
            addChild(bubble)

            let float = SKAction.moveBy(x: CGFloat.random(in: -10...10),
                                        y: CGFloat.random(in: 40...70),
                                        duration: 0.8)
            let fade = SKAction.fadeOut(withDuration: 0.8)
            bubble.run(SKAction.sequence([SKAction.group([float, fade]),
                                          SKAction.removeFromParent()]))
        }
    }

    // MARK: - Shower

    private func startShower() {
        guard !isBusy else {
            AudioManager.shared.playSFX(.error)
            return
        }
        isBusy = true
        gooby.playShowerReaction()

        let spawn = SKAction.run { [weak self] in
            self?.spawnRainDrop()
        }
        let interval = SKAction.wait(forDuration: 0.15)
        let rain = SKAction.repeat(SKAction.sequence([spawn, interval]), count: 20)
        let settle = SKAction.wait(forDuration: 0.8)
        run(SKAction.sequence([rain, settle, SKAction.run { [weak self] in
            self?.isBusy = false
        }]))
    }

    private func spawnRainDrop() {
        guard let gooby = gooby else { return }
        let drop = SKSpriteNode(texture: AssetProvider.texture(.particleCircle),
                                size: CGSize(width: 12, height: 18))
        drop.color = UIColor(red: 0.45, green: 0.7, blue: 1, alpha: 1)
        drop.colorBlendFactor = 1
        drop.position = CGPoint(x: gooby.position.x + CGFloat.random(in: -90...90),
                                y: gooby.position.y + 340)
        drop.zPosition = 300
        addChild(drop)

        let bodyLevelY = gooby.position.y + 70
        let fall = SKAction.moveTo(y: bodyLevelY, duration: 0.5)
        fall.timingMode = .easeIn
        let land = SKAction.group([
            SKAction.fadeOut(withDuration: 0.2),
            SKAction.run { GameState.shared.clean(amount: 1) }
        ])
        drop.run(SKAction.sequence([fall, land, SKAction.removeFromParent()]))
    }

    // MARK: - Toilet

    private func runToiletGag() {
        guard !isBusy else {
            AudioManager.shared.playSFX(.error)
            return
        }
        isBusy = true

        let hopOut = SKAction.moveBy(x: 300, y: 0, duration: 0.4)
        hopOut.timingMode = .easeIn
        let hopBack = SKAction.moveBy(x: -300, y: 0, duration: 0.4)
        hopBack.timingMode = .easeOut
        let sequence = SKAction.sequence([
            hopOut,
            SKAction.wait(forDuration: 0.8),
            SKAction.run { AudioManager.shared.playSFX(.splash) },
            hopBack
        ])
        gooby.run(sequence) { [weak self] in
            guard let self = self else { return }
            GameState.shared.addHappiness(5)
            self.showDropsEmote()
            self.isBusy = false
        }
    }

    private func showDropsEmote() {
        guard let gooby = gooby else { return }
        let emote = SKSpriteNode(texture: AssetProvider.texture(.emoteDrops),
                                 size: CGSize(width: 48, height: 48))
        emote.position = CGPoint(x: gooby.position.x + 70, y: gooby.position.y + 160)
        emote.zPosition = 400
        emote.alpha = 0
        addChild(emote)
        emote.run(SKAction.sequence([
            SKAction.fadeIn(withDuration: 0.15),
            SKAction.wait(forDuration: 0.9),
            SKAction.fadeOut(withDuration: 0.25),
            SKAction.removeFromParent()
        ]))
    }

    // MARK: - Toast

    private func showToast(_ text: String) {
        childNode(withName: "toast")?.removeFromParent()

        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.text = text
        label.fontSize = 18
        label.fontColor = Theme.textColor
        label.verticalAlignmentMode = .center
        label.horizontalAlignmentMode = .center

        let panel = SKShapeNode(rectOf: CGSize(width: label.frame.width + 40, height: 44),
                                cornerRadius: Theme.cornerRadius)
        panel.fillColor = Theme.panelColor
        panel.strokeColor = .clear

        let container = SKNode()
        container.name = "toast"
        container.zPosition = 950
        container.position = CGPoint(x: 0, y: -230)
        container.alpha = 0
        container.addChild(panel)
        container.addChild(label)
        addChild(container)

        container.run(SKAction.sequence([
            SKAction.fadeIn(withDuration: 0.2),
            SKAction.wait(forDuration: 1.4),
            SKAction.fadeOut(withDuration: 0.3),
            SKAction.removeFromParent()
        ]))
    }
}
