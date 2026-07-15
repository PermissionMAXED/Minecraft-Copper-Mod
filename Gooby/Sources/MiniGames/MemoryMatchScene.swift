import SpriteKit
import UIKit

/// Mini-game: classic pairs. Flip cards two at a time and match all
/// eight emoji pairs in as few moves as possible.
final class MemoryMatchScene: SKScene {
    private enum FlowState {
        case ready
        case playing
        case gameOver
    }

    private final class CardNode: SKNode {
        private(set) var emoji: String
        private(set) var isFaceUp = false
        private(set) var isMatched = false
        private var isAnimating = false

        private let back: SKShapeNode
        private let face: SKNode
        private let faceLabel: SKLabelNode

        init(emoji: String, cardSize: CGSize) {
            self.emoji = emoji

            let back = SKShapeNode(rectOf: cardSize, cornerRadius: 10)
            back.fillColor = UIColor(red: 0.27, green: 0.47, blue: 0.85, alpha: 1)
            back.strokeColor = UIColor(red: 0.18, green: 0.32, blue: 0.62, alpha: 1)
            back.lineWidth = 2

            let backMark = SKLabelNode(fontNamed: Theme.fontName)
            backMark.text = "🐾"
            backMark.fontSize = cardSize.height * 0.4
            backMark.verticalAlignmentMode = .center
            backMark.horizontalAlignmentMode = .center
            back.addChild(backMark)

            let facePlate = SKShapeNode(rectOf: cardSize, cornerRadius: 10)
            facePlate.fillColor = .white
            facePlate.strokeColor = UIColor(white: 0.8, alpha: 1)
            facePlate.lineWidth = 2

            let faceLabel = SKLabelNode(fontNamed: Theme.fontName)
            faceLabel.text = emoji
            faceLabel.fontSize = cardSize.height * 0.55
            faceLabel.verticalAlignmentMode = .center
            faceLabel.horizontalAlignmentMode = .center

            let face = SKNode()
            face.addChild(facePlate)
            face.addChild(faceLabel)
            face.isHidden = true

            self.back = back
            self.faceLabel = faceLabel
            self.face = face

            super.init()

            addChild(back)
            addChild(face)
        }

        required init?(coder aDecoder: NSCoder) {
            fatalError("init(coder:) has not been implemented")
        }

        var canFlip: Bool {
            return !isFaceUp && !isMatched && !isAnimating
        }

        func flip(faceUp: Bool, completion: (() -> Void)? = nil) {
            guard faceUp != isFaceUp else {
                completion?()
                return
            }
            isFaceUp = faceUp
            isAnimating = true
            let halfFlip = SKAction.scaleX(to: 0, duration: 0.12)
            let swap = SKAction.run { [weak self] in
                self?.back.isHidden = faceUp
                self?.face.isHidden = !faceUp
            }
            let unflip = SKAction.scaleX(to: 1, duration: 0.12)
            let done = SKAction.run { [weak self] in
                self?.isAnimating = false
                completion?()
            }
            run(SKAction.sequence([halfFlip, swap, unflip, done]))
        }

        func markMatched() {
            isMatched = true
            run(SKAction.sequence([
                SKAction.scale(to: 1.12, duration: 0.12),
                SKAction.scale(to: 1, duration: 0.12)
            ]))
        }

        func reset(emoji: String) {
            self.emoji = emoji
            faceLabel.text = emoji
            isFaceUp = false
            isMatched = false
            isAnimating = false
            removeAllActions()
            setScale(1)
            back.isHidden = false
            face.isHidden = true
        }
    }

    private let emojiSet = ["🥕", "🍰", "🐰", "⭐️", "🎈", "🍪", "🧼", "⚡️"]
    private let columns = 4
    private let rows = 4
    private let cardSize = CGSize(width: 76, height: 76)
    private let cardSpacing: CGFloat = 8

    private var flowState: FlowState = .ready
    private var cards: [CardNode] = []
    private var flippedCards: [CardNode] = []
    private var isBoardLocked = false
    private var moves = 0
    private var matchedPairs = 0
    private var startOverlay: SKNode?
    private var gameOverOverlay: SKNode?
    private var didBuild = false

    private let movesLabel: SKLabelNode = {
        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.fontSize = 20
        label.fontColor = Theme.textColor
        label.horizontalAlignmentMode = .right
        label.verticalAlignmentMode = .center
        label.zPosition = 900
        return label
    }()

    override init() {
        super.init(size: SceneRouter.designSize)
        scaleMode = .aspectFill
        anchorPoint = CGPoint(x: 0.5, y: 0.5)
        backgroundColor = UIColor(red: 0.87, green: 0.80, blue: 0.96, alpha: 1)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func didMove(to view: SKView) {
        super.didMove(to: view)
        guard !didBuild else { return }
        didBuild = true
        buildHUD()
        buildBoard()
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

        let title = SKLabelNode(fontNamed: Theme.fontName)
        title.text = "Memory Match"
        title.fontSize = 26
        title.fontColor = Theme.textColor
        title.verticalAlignmentMode = .center
        title.position = CGPoint(x: 0, y: hudTopY)
        title.zPosition = 900
        addChild(title)

        movesLabel.position = CGPoint(x: size.width / 2 - 20, y: hudTopY - 48)
        addChild(movesLabel)
        refreshMovesLabel()
    }

    private func refreshMovesLabel() {
        movesLabel.text = "Moves: \(moves)"
    }

    // MARK: - Board

    private func buildBoard() {
        let deck = shuffledDeck()
        let gridWidth = CGFloat(columns) * cardSize.width + CGFloat(columns - 1) * cardSpacing
        let gridHeight = CGFloat(rows) * cardSize.height + CGFloat(rows - 1) * cardSpacing
        let originX = -gridWidth / 2 + cardSize.width / 2
        let originY = -gridHeight / 2 + cardSize.height / 2 - 30

        for index in 0..<(columns * rows) {
            let column = index % columns
            let row = index / columns
            let card = CardNode(emoji: deck[index], cardSize: cardSize)
            card.position = CGPoint(x: originX + CGFloat(column) * (cardSize.width + cardSpacing),
                                    y: originY + CGFloat(rows - 1 - row) * (cardSize.height + cardSpacing))
            card.zPosition = 10
            addChild(card)
            cards.append(card)
        }
    }

    /// Fisher–Yates shuffle over the doubled emoji deck.
    private func shuffledDeck() -> [String] {
        var deck = emojiSet + emojiSet
        var i = deck.count - 1
        while i > 0 {
            let j = Int.random(in: 0...i)
            if i != j {
                deck.swapAt(i, j)
            }
            i -= 1
        }
        return deck
    }

    // MARK: - Flow

    private func beginRound() {
        startOverlay?.removeFromParent()
        startOverlay = nil
        flowState = .playing
    }

    private func endGame() {
        guard flowState == .playing else { return }
        flowState = .gameOver

        let coinsEarned = max(6, 30 - moves)
        AudioManager.shared.playSFX(coinsEarned > 0 ? .win : .lose)
        GameState.shared.addCoins(coinsEarned)
        GameState.shared.addHappiness(4)
        showGameOverOverlay(coinsEarned: coinsEarned)
    }

    private func restartRound() {
        gameOverOverlay?.removeFromParent()
        gameOverOverlay = nil
        let deck = shuffledDeck()
        for (index, card) in cards.enumerated() {
            card.reset(emoji: deck[index])
        }
        flippedCards = []
        isBoardLocked = false
        moves = 0
        matchedPairs = 0
        refreshMovesLabel()
        flowState = .ready
        showStartOverlay()
    }

    // MARK: - Touch input

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else { return }
        switch flowState {
        case .ready:
            beginRound()
        case .playing:
            handleBoardTap(at: touch.location(in: self))
        case .gameOver:
            break
        }
    }

    private func handleBoardTap(at location: CGPoint) {
        guard !isBoardLocked, flippedCards.count < 2 else { return }
        guard let card = cards.first(where: { card in
            card.canFlip && card.calculateAccumulatedFrame().contains(location)
        }) else { return }

        AudioManager.shared.playSFX(.click)
        flippedCards.append(card)
        let isSecond = flippedCards.count == 2
        if isSecond {
            isBoardLocked = true
        }
        card.flip(faceUp: true) { [weak self] in
            guard isSecond else { return }
            self?.resolveFlippedPair()
        }
    }

    private func resolveFlippedPair() {
        guard flippedCards.count == 2 else { return }
        moves += 1
        refreshMovesLabel()

        let first = flippedCards[0]
        let second = flippedCards[1]
        if first.emoji == second.emoji {
            AudioManager.shared.playSFX(.coin)
            first.markMatched()
            second.markMatched()
            emitStarBurst(at: first.position)
            emitStarBurst(at: second.position)
            flippedCards = []
            isBoardLocked = false
            matchedPairs += 1
            if matchedPairs == emojiSet.count {
                endGame()
            }
        } else {
            run(SKAction.sequence([
                SKAction.wait(forDuration: 0.7),
                SKAction.run { [weak self] in
                    guard let self = self else { return }
                    first.flip(faceUp: false)
                    second.flip(faceUp: false)
                    self.flippedCards = []
                    self.isBoardLocked = false
                }
            ]))
        }
    }

    private func emitStarBurst(at position: CGPoint) {
        let texture = AssetProvider.texture(.particleStar)
        for angleStep in 0..<6 {
            let star = SKSpriteNode(texture: texture, size: CGSize(width: 18, height: 18))
            star.position = position
            star.zPosition = 50
            addChild(star)
            let angle = CGFloat(angleStep) / 6 * .pi * 2
            let distance = CGFloat.random(in: 34...58)
            let target = CGPoint(x: position.x + cos(angle) * distance,
                                 y: position.y + sin(angle) * distance)
            star.run(SKAction.sequence([
                SKAction.group([
                    SKAction.move(to: target, duration: 0.4),
                    SKAction.fadeOut(withDuration: 0.4),
                    SKAction.rotate(byAngle: .pi, duration: 0.4)
                ]),
                SKAction.removeFromParent()
            ]))
        }
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
        title.text = "🃏 Memory Match"
        title.fontSize = 28
        title.fontColor = Theme.textColor
        title.verticalAlignmentMode = .center
        title.position = CGPoint(x: 0, y: 105)
        overlay.addChild(title)

        let rules = SKLabelNode(fontNamed: Theme.fontName)
        rules.text = "Flip two cards at a time.\nMatch all 8 pairs to win.\nFewer moves = more coins!"
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
        title.text = "You matched them all! 🎉"
        title.fontSize = 22
        title.fontColor = Theme.textColor
        title.verticalAlignmentMode = .center
        title.position = CGPoint(x: 0, y: 120)
        overlay.addChild(title)

        let movesLine = SKLabelNode(fontNamed: Theme.fontName)
        movesLine.text = "Moves: \(moves)"
        movesLine.fontSize = 22
        movesLine.fontColor = Theme.textColor
        movesLine.verticalAlignmentMode = .center
        movesLine.position = CGPoint(x: 0, y: 65)
        overlay.addChild(movesLine)

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
