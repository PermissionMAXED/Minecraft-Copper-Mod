import SpriteKit
import UIKit

final class KitchenScene: BaseScene {
    private var gooby: GoobyNode!
    private var shelfIcons: [FoodIconNode] = []
    private var badgeLabels: [String: SKLabelNode] = [:]
    private var isFeedingLocked = false
    private var stateObserver: NSObjectProtocol?

    init() {
        super.init(room: .kitchen)
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
        addTitle("Kitchen")
        addBackButton(to: .home)

        let gooby = GoobyNode(color: GameState.shared.goobyColor,
                              outfitID: GameState.shared.equippedOutfitID)
        gooby.position = CGPoint(x: 0, y: -40)
        gooby.zPosition = 20
        addChild(gooby)
        gooby.startIdleAnimations()
        gooby.mood = GameState.shared.stats.mood
        self.gooby = gooby

        buildFoodShelf()
        refreshBadges()

        stateObserver = NotificationCenter.default.addObserver(forName: .gameStateDidChange,
                                                               object: nil,
                                                               queue: .main) { [weak self] _ in
            guard let self = self else { return }
            self.refreshBadges()
            self.gooby.mood = GameState.shared.stats.mood
        }
    }

    override func willMove(from view: SKView) {
        if let observer = stateObserver {
            NotificationCenter.default.removeObserver(observer)
            stateObserver = nil
        }
        super.willMove(from: view)
    }

    // MARK: - Shelf

    private func buildFoodShelf() {
        let shelfY: CGFloat = 180

        let panel = SKShapeNode(rectOf: CGSize(width: 376, height: 92), cornerRadius: Theme.cornerRadius)
        panel.fillColor = Theme.panelColor
        panel.strokeColor = .clear
        panel.position = CGPoint(x: 0, y: shelfY)
        panel.zPosition = 5
        addChild(panel)

        let foods = Catalog.foods
        let spacing: CGFloat = 62
        let startX = -spacing * CGFloat(foods.count - 1) / 2
        for (index, item) in foods.enumerated() {
            let icon = FoodIconNode(item: item, diameter: 64)
            icon.position = CGPoint(x: startX + CGFloat(index) * spacing, y: shelfY)
            icon.zPosition = 10
            addChild(icon)
            shelfIcons.append(icon)

            let badge = SKShapeNode(circleOfRadius: 13)
            badge.fillColor = Theme.statColor(.hunger)
            badge.strokeColor = .white
            badge.lineWidth = 2
            badge.position = CGPoint(x: 22, y: -22)
            badge.zPosition = 2
            icon.addChild(badge)

            let count = SKLabelNode(fontNamed: Theme.fontName)
            count.fontSize = 12
            count.fontColor = .white
            count.verticalAlignmentMode = .center
            count.horizontalAlignmentMode = .center
            count.zPosition = 3
            badge.addChild(count)
            badgeLabels[item.id] = count
        }
    }

    private func refreshBadges() {
        for item in Catalog.foods {
            let count = GameState.shared.foodInventory[item.id] ?? 0
            badgeLabels[item.id]?.text = "x\(count)"
        }
    }

    // MARK: - Touch handling

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else { return }
        let location = touch.location(in: self)
        guard let icon = shelfIcons.first(where: {
            $0.calculateAccumulatedFrame().insetBy(dx: -6, dy: -6).contains(location)
        }) else { return }
        handleTap(on: icon)
    }

    private func handleTap(on icon: FoodIconNode) {
        guard !isFeedingLocked else { return }

        if GameState.shared.stats.hunger > 95 {
            AudioManager.shared.playSFX(.error)
            showToast("Gooby is full!")
            return
        }

        let item = icon.item
        let count = GameState.shared.foodInventory[item.id] ?? 0
        guard count > 0 else {
            AudioManager.shared.playSFX(.error)
            shake(icon)
            showToast("Buy more in the Shop!")
            return
        }

        feed(item, from: icon)
    }

    // MARK: - Feeding

    private func feed(_ item: FoodItem, from icon: FoodIconNode) {
        isFeedingLocked = true

        let flying = FoodIconNode(item: item, diameter: 64)
        flying.position = icon.position
        flying.zPosition = 100
        addChild(flying)

        let mouthPoint = convert(gooby.mouthLocalPoint, from: gooby)
        let move = SKAction.move(to: mouthPoint, duration: 0.5)
        move.timingMode = .easeIn
        let scale = SKAction.scale(to: 0.2, duration: 0.5)
        let flight = SKAction.group([move, scale])
        let fade = SKAction.fadeOut(withDuration: 0.15)

        flying.run(SKAction.sequence([flight, fade])) { [weak self] in
            flying.removeFromParent()
            guard let self = self else { return }
            AudioManager.shared.playSFX(.eat)
            GameState.shared.feed(item)
            self.gooby.playEat { [weak self] in
                guard let self = self else { return }
                self.isFeedingLocked = false
                self.refreshBadges()
            }
        }
    }

    // MARK: - Effects

    private func shake(_ node: SKNode) {
        let shake = SKAction.sequence([
            SKAction.moveBy(x: 6, y: 0, duration: 0.05),
            SKAction.moveBy(x: -12, y: 0, duration: 0.08),
            SKAction.moveBy(x: 12, y: 0, duration: 0.08),
            SKAction.moveBy(x: -6, y: 0, duration: 0.05)
        ])
        node.run(shake)
    }

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
