import SpriteKit
import UIKit

/// Mini-game: drag a basket to catch falling carrots while dodging rocks.
/// Movement and collisions are driven by SKActions + manual AABB checks
/// in update(_:) — no physics engine involved.
final class CarrotCatchScene: SKScene {
    private enum FlowState {
        case ready
        case playing
        case gameOver
    }

    private enum ItemKind: String {
        case carrot
        case rock
    }

    // Difficulty ramps from the start values to the end values over rampDuration.
    private let startSpawnInterval: TimeInterval = 0.9
    private let endSpawnInterval: TimeInterval = 0.45
    private let startFallDuration: TimeInterval = 2.8
    private let endFallDuration: TimeInterval = 1.6
    private let rampDuration: TimeInterval = 60
    private let carrotChance: Double = 0.8

    private let itemLayer = SKNode()

    private let basket: SKShapeNode = {
        let node = SKShapeNode(rectOf: CGSize(width: 90, height: 50), cornerRadius: 10)
        node.fillColor = UIColor(red: 0.55, green: 0.36, blue: 0.20, alpha: 1)
        node.strokeColor = .clear
        node.zPosition = 20
        return node
    }()

    private let scoreLabel: SKLabelNode = {
        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.fontSize = 20
        label.fontColor = Theme.textColor
        label.horizontalAlignmentMode = .left
        label.verticalAlignmentMode = .center
        label.zPosition = 900
        return label
    }()

    private let livesLabel: SKLabelNode = {
        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.fontSize = 20
        label.horizontalAlignmentMode = .right
        label.verticalAlignmentMode = .center
        label.zPosition = 900
        return label
    }()

    private var flowState: FlowState = .ready
    private var score = 0
    private var lives = 3
    private var roundStartTime: TimeInterval?
    private var lastSpawnTime: TimeInterval = 0
    private var startOverlay: SKNode?
    private var gameOverOverlay: SKNode?
    private var didBuild = false

    override init() {
        super.init(size: SceneRouter.designSize)
        scaleMode = .aspectFit
        anchorPoint = CGPoint(x: 0.5, y: 0.5)
        backgroundColor = UIColor(red: 0.67, green: 0.86, blue: 0.98, alpha: 1)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func didMove(to view: SKView) {
        super.didMove(to: view)
        guard !didBuild else { return }
        didBuild = true

        let grass = SKSpriteNode(color: UIColor(red: 0.55, green: 0.78, blue: 0.45, alpha: 1),
                                 size: CGSize(width: size.width + 40, height: 80))
        grass.position = CGPoint(x: 0, y: -size.height / 2 + 40)
        grass.zPosition = 5
        addChild(grass)

        addChild(itemLayer)
        basket.position = CGPoint(x: 0, y: -size.height / 2 + 115)
        addChild(basket)

        buildHUD()
        showStartOverlay()
    }

    // MARK: - HUD

    private var hudTopY: CGFloat {
        return size.height / 2 - 60
    }

    private func buildHUD() {
        let close = ButtonNode(iconTexture: AssetProvider.texture(.iconCross), diameter: 48) {
            SceneRouter.shared.returnToRoom(.gameRoom)
        }
        close.position = CGPoint(x: -size.width / 2 + 44, y: hudTopY)
        close.zPosition = 900
        addChild(close)

        scoreLabel.position = CGPoint(x: -size.width / 2 + 20, y: hudTopY - 48)
        addChild(scoreLabel)

        livesLabel.position = CGPoint(x: size.width / 2 - 20, y: hudTopY)
        addChild(livesLabel)

        refreshHUD()
    }

    private func refreshHUD() {
        scoreLabel.text = "Score: \(score)"
        livesLabel.text = lives > 0 ? String(repeating: "❤️", count: lives) : "💔"
    }

    // MARK: - Flow

    private func beginRound() {
        startOverlay?.removeFromParent()
        startOverlay = nil
        flowState = .playing
        roundStartTime = nil
    }

    private func endGame() {
        guard flowState == .playing else { return }
        flowState = .gameOver
        itemLayer.removeAllChildren()

        let coinsEarned = score
        AudioManager.shared.playSFX(score > 0 ? .win : .lose)
        GameState.shared.addCoins(coinsEarned)
        GameState.shared.addHappiness(4)
        showGameOverOverlay(coinsEarned: coinsEarned)
    }

    private func restartRound() {
        gameOverOverlay?.removeFromParent()
        gameOverOverlay = nil
        itemLayer.removeAllChildren()
        score = 0
        lives = 3
        roundStartTime = nil
        basket.position.x = 0
        refreshHUD()
        flowState = .ready
        showStartOverlay()
    }

    // MARK: - Update loop

    override func update(_ currentTime: TimeInterval) {
        guard flowState == .playing else { return }

        if roundStartTime == nil {
            roundStartTime = currentTime
            lastSpawnTime = currentTime
        }
        let elapsed = currentTime - (roundStartTime ?? currentTime)
        let ramp = min(max(elapsed / rampDuration, 0), 1)

        let spawnInterval = startSpawnInterval + (endSpawnInterval - startSpawnInterval) * ramp
        if currentTime - lastSpawnTime >= spawnInterval {
            lastSpawnTime = currentTime
            let fallDuration = startFallDuration + (endFallDuration - startFallDuration) * ramp
            spawnItem(fallDuration: fallDuration)
        }

        resolveItems()
    }

    private func resolveItems() {
        let basketFrame = basket.frame
        let missY = -size.height / 2 - 40
        for item in itemLayer.children {
            guard let kindName = item.name, let kind = ItemKind(rawValue: kindName) else { continue }
            if item.calculateAccumulatedFrame().intersects(basketFrame) {
                catchItem(item, kind: kind)
            } else if item.position.y < missY {
                missItem(item, kind: kind)
            }
            if flowState != .playing {
                break
            }
        }
    }

    // MARK: - Items

    private func spawnItem(fallDuration: TimeInterval) {
        let isCarrot = Double.random(in: 0..<1) < carrotChance
        let item = isCarrot ? makeCarrot() : makeRock()
        let halfWidth = size.width / 2
        item.position = CGPoint(x: CGFloat.random(in: (-halfWidth + 40)...(halfWidth - 40)),
                                y: size.height / 2 + 50)
        item.zPosition = 10
        itemLayer.addChild(item)
        item.run(SKAction.moveTo(y: -size.height / 2 - 80, duration: fallDuration))
    }

    private func makeCarrot() -> SKNode {
        let node = SKNode()
        node.name = ItemKind.carrot.rawValue

        let path = CGMutablePath()
        path.move(to: CGPoint(x: 0, y: -25))
        path.addLine(to: CGPoint(x: -15, y: 25))
        path.addLine(to: CGPoint(x: 15, y: 25))
        path.closeSubpath()
        let body = SKShapeNode(path: path)
        body.fillColor = UIColor(red: 0.95, green: 0.52, blue: 0.13, alpha: 1)
        body.strokeColor = .clear
        node.addChild(body)

        for offset in [CGFloat(-6), CGFloat(6)] {
            let leaf = SKShapeNode(ellipseOf: CGSize(width: 8, height: 18))
            leaf.fillColor = UIColor(red: 0.28, green: 0.72, blue: 0.32, alpha: 1)
            leaf.strokeColor = .clear
            leaf.position = CGPoint(x: offset, y: 30)
            leaf.zRotation = offset < 0 ? 0.35 : -0.35
            node.addChild(leaf)
        }
        return node
    }

    private func makeRock() -> SKNode {
        let rock = SKShapeNode(circleOfRadius: 14)
        rock.name = ItemKind.rock.rawValue
        rock.fillColor = UIColor(white: 0.55, alpha: 1)
        rock.strokeColor = .clear
        return rock
    }

    private func catchItem(_ item: SKNode, kind: ItemKind) {
        item.name = nil
        item.removeAllActions()
        switch kind {
        case .carrot:
            score += 1
            refreshHUD()
            AudioManager.shared.playSFX(.pop)
            let pop = SKAction.group([
                SKAction.scale(to: 1.4, duration: 0.15),
                SKAction.fadeOut(withDuration: 0.15)
            ])
            item.run(SKAction.sequence([pop, SKAction.removeFromParent()]))
        case .rock:
            AudioManager.shared.playSFX(.error)
            flashRed()
            item.run(SKAction.sequence([
                SKAction.fadeOut(withDuration: 0.12),
                SKAction.removeFromParent()
            ]))
            loseLife()
        }
    }

    private func missItem(_ item: SKNode, kind: ItemKind) {
        item.name = nil
        item.removeAllActions()
        item.removeFromParent()
        if kind == .carrot {
            AudioManager.shared.playSFX(.drop)
            loseLife()
        }
    }

    private func loseLife() {
        lives -= 1
        refreshHUD()
        if lives <= 0 {
            endGame()
        }
    }

    private func flashRed() {
        let flash = SKSpriteNode(color: .red, size: size)
        flash.alpha = 0
        flash.zPosition = 500
        addChild(flash)
        flash.run(SKAction.sequence([
            SKAction.fadeAlpha(to: 0.3, duration: 0.08),
            SKAction.fadeOut(withDuration: 0.25),
            SKAction.removeFromParent()
        ]))
    }

    // MARK: - Touch input

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else { return }
        switch flowState {
        case .ready:
            beginRound()
        case .playing:
            moveBasket(toward: touch.location(in: self).x)
        case .gameOver:
            break
        }
    }

    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard flowState == .playing, let touch = touches.first else { return }
        moveBasket(toward: touch.location(in: self).x)
    }

    private func moveBasket(toward x: CGFloat) {
        let limit = size.width / 2 - 45
        basket.position.x = min(max(x, -limit), limit)
    }

    // MARK: - Overlays

    private func showStartOverlay() {
        let overlay = SKNode()
        overlay.zPosition = 1000

        let dim = SKSpriteNode(color: UIColor(white: 0, alpha: 0.35), size: size)
        overlay.addChild(dim)

        let panel = SKShapeNode(rectOf: CGSize(width: 320, height: 300), cornerRadius: Theme.cornerRadius)
        panel.fillColor = Theme.panelColor
        panel.strokeColor = .clear
        overlay.addChild(panel)

        let title = SKLabelNode(fontNamed: Theme.fontName)
        title.text = "🥕 Carrot Catch"
        title.fontSize = 28
        title.fontColor = Theme.textColor
        title.verticalAlignmentMode = .center
        title.position = CGPoint(x: 0, y: 105)
        overlay.addChild(title)

        let rules = SKLabelNode(fontNamed: Theme.fontName)
        rules.text = "Drag the basket to catch carrots.\nRocks cost a heart — dodge them!\nMissed carrots cost a heart too."
        rules.fontSize = 16
        rules.fontColor = Theme.textColor
        rules.numberOfLines = 0
        rules.preferredMaxLayoutWidth = 290
        rules.verticalAlignmentMode = .center
        rules.horizontalAlignmentMode = .center
        rules.position = CGPoint(x: 0, y: 20)
        overlay.addChild(rules)

        let tap = SKLabelNode(fontNamed: Theme.fontName)
        tap.text = "Tap to start"
        tap.fontSize = 22
        tap.fontColor = Theme.textColor
        tap.verticalAlignmentMode = .center
        tap.position = CGPoint(x: 0, y: -95)
        tap.run(SKAction.repeatForever(SKAction.sequence([
            SKAction.fadeAlpha(to: 0.35, duration: 0.55),
            SKAction.fadeAlpha(to: 1, duration: 0.55)
        ])))
        overlay.addChild(tap)

        addChild(overlay)
        startOverlay = overlay
    }

    private func showGameOverOverlay(coinsEarned: Int) {
        let overlay = SKNode()
        overlay.zPosition = 1000

        let dim = SKSpriteNode(color: UIColor(white: 0, alpha: 0.45), size: size)
        overlay.addChild(dim)

        let panel = SKShapeNode(rectOf: CGSize(width: 320, height: 340), cornerRadius: Theme.cornerRadius)
        panel.fillColor = Theme.panelColor
        panel.strokeColor = .clear
        overlay.addChild(panel)

        let title = SKLabelNode(fontNamed: Theme.fontName)
        title.text = "Game Over"
        title.fontSize = 30
        title.fontColor = Theme.textColor
        title.verticalAlignmentMode = .center
        title.position = CGPoint(x: 0, y: 120)
        overlay.addChild(title)

        let scoreLine = SKLabelNode(fontNamed: Theme.fontName)
        scoreLine.text = "Score: \(score)"
        scoreLine.fontSize = 22
        scoreLine.fontColor = Theme.textColor
        scoreLine.verticalAlignmentMode = .center
        scoreLine.position = CGPoint(x: 0, y: 65)
        overlay.addChild(scoreLine)

        let coinsLine = SKLabelNode(fontNamed: Theme.fontName)
        coinsLine.text = "🪙 +\(coinsEarned) coins"
        coinsLine.fontSize = 20
        coinsLine.fontColor = Theme.textColor
        coinsLine.verticalAlignmentMode = .center
        coinsLine.position = CGPoint(x: 0, y: 25)
        overlay.addChild(coinsLine)

        let playAgain = ButtonNode(text: "Play again", size: CGSize(width: 220, height: 52)) { [weak self] in
            self?.restartRound()
        }
        playAgain.position = CGPoint(x: 0, y: -45)
        overlay.addChild(playAgain)

        let exit = ButtonNode(text: "Exit", size: CGSize(width: 220, height: 52)) {
            SceneRouter.shared.returnToRoom(.gameRoom)
        }
        exit.position = CGPoint(x: 0, y: -115)
        overlay.addChild(exit)

        addChild(overlay)
        gameOverOverlay = overlay
    }
}
