import SpriteKit
import UIKit

final class ShopScene: BaseScene {
    private var selectedTab: ShopTab = .food

    private let coinLabel = SKLabelNode(fontNamed: Theme.fontName)
    private let tabBarNode = SKNode()
    private let gridNode = SKNode()
    private var previewGooby: GoobyNode?

    private let cardSize = CGSize(width: 170, height: 120)

    init() {
        super.init(room: .shop)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    deinit {
        NotificationCenter.default.removeObserver(self)
    }

    override func buildScene() {
        addTitle("Shop")
        addBackButton(to: .home)

        setupCoinLabel()
        setupPreview()
        addChild(tabBarNode)
        addChild(gridNode)
        renderTabBar()
        renderGrid()
        addHint()

        NotificationCenter.default.addObserver(self,
                                               selector: #selector(handleGameStateDidChange),
                                               name: .gameStateDidChange,
                                               object: nil)
    }

    @objc private func handleGameStateDidChange() {
        updateCoinLabel()
    }

    // MARK: - Coin label

    private func setupCoinLabel() {
        coinLabel.fontSize = 18
        coinLabel.fontColor = Theme.textColor
        coinLabel.horizontalAlignmentMode = .right
        coinLabel.verticalAlignmentMode = .center
        coinLabel.position = CGPoint(x: size.width / 2 - 16, y: topY)
        coinLabel.zPosition = 900
        addChild(coinLabel)
        updateCoinLabel()
    }

    private func updateCoinLabel() {
        coinLabel.text = "🪙 \(GameState.shared.coins)"
    }

    private func flashCoinLabel() {
        coinLabel.removeAction(forKey: "flash")
        let flash = SKAction.sequence([
            SKAction.run { [weak self] in self?.coinLabel.fontColor = .systemRed },
            SKAction.wait(forDuration: 0.25),
            SKAction.run { [weak self] in self?.coinLabel.fontColor = Theme.textColor }
        ])
        coinLabel.run(flash, withKey: "flash")
    }

    // MARK: - Gooby preview

    private func setupPreview() {
        let gooby = GoobyNode(color: GameState.shared.goobyColor,
                              outfitID: GameState.shared.equippedOutfitID)
        gooby.setScale(0.45)
        gooby.position = CGPoint(x: 0, y: topY - 105)
        gooby.startIdleAnimations()
        addChild(gooby)
        previewGooby = gooby
    }

    private func refreshPreview() {
        previewGooby?.setColor(GameState.shared.goobyColor)
        previewGooby?.setOutfit(GameState.shared.equippedOutfitID)
    }

    // MARK: - Tab bar

    private func renderTabBar() {
        tabBarNode.removeAllChildren()
        let tabs: [(tab: ShopTab, title: String)] = [
            (.food, "Food 🍽"),
            (.outfits, "Hats 🎩"),
            (.colors, "Colors 🎨")
        ]
        let buttonSize = CGSize(width: 118, height: 44)
        let xPositions: [CGFloat] = [-124, 0, 124]
        for (index, entry) in tabs.enumerated() {
            let texture = AssetProvider.texture(entry.tab == selectedTab ? .btnGreen : .btnGrey)
            let button = ButtonNode(text: entry.title,
                                    emoji: nil,
                                    texture: texture,
                                    size: buttonSize) { [weak self] in
                self?.selectTab(entry.tab)
            }
            button.position = CGPoint(x: xPositions[index], y: 168)
            tabBarNode.addChild(button)
        }
    }

    private func selectTab(_ tab: ShopTab) {
        guard tab != selectedTab else { return }
        selectedTab = tab
        renderTabBar()
        renderGrid()
    }

    // MARK: - Grid

    private func renderGrid() {
        gridNode.removeAllChildren()
        let entries = ShopLogic.entries(for: selectedTab)
        let startY: CGFloat = 74
        let rowSpacing: CGFloat = 132
        let columnX: CGFloat = 90
        for (index, entry) in entries.enumerated() {
            let card = makeCard(for: entry)
            let column = index % 2
            let row = index / 2
            card.position = CGPoint(x: column == 0 ? -columnX : columnX,
                                    y: startY - CGFloat(row) * rowSpacing)
            gridNode.addChild(card)
        }
    }

    private func makeCard(for entry: ShopEntry) -> ButtonNode {
        let backgroundID: TextureID
        switch entry.state {
        case .unaffordable, .equipped:
            backgroundID = .btnGrey
        case .buyable, .owned:
            backgroundID = .btnGreen
        }

        weak var weakCard: ButtonNode?
        let card = ButtonNode(text: "",
                              emoji: nil,
                              texture: AssetProvider.texture(backgroundID),
                              size: cardSize) { [weak self] in
            self?.handleCardTap(entry: entry, card: weakCard)
        }
        weakCard = card

        let icon = makeIcon(for: entry)
        icon.position = CGPoint(x: 0, y: 28)
        card.addChild(icon)

        let nameLabel = SKLabelNode(fontNamed: Theme.fontName)
        nameLabel.text = entry.name
        nameLabel.fontSize = 14
        nameLabel.fontColor = .white
        nameLabel.verticalAlignmentMode = .center
        nameLabel.horizontalAlignmentMode = .center
        nameLabel.position = CGPoint(x: 0, y: -12)
        card.addChild(nameLabel)

        let priceLabel = SKLabelNode(fontNamed: Theme.fontName)
        priceLabel.text = priceText(for: entry)
        priceLabel.fontSize = 13
        priceLabel.fontColor = entry.state == .unaffordable ? .systemRed : .white
        priceLabel.verticalAlignmentMode = .center
        priceLabel.horizontalAlignmentMode = .center
        priceLabel.position = CGPoint(x: 0, y: -40)
        card.addChild(priceLabel)

        if selectedTab == .food {
            let count = GameState.shared.foodInventory[entry.id] ?? 0
            if count > 0 {
                card.addChild(makeCountBadge(count: count))
            }
        }

        return card
    }

    private func makeIcon(for entry: ShopEntry) -> SKNode {
        switch selectedTab {
        case .food:
            if let item = Catalog.food(id: entry.id) {
                return FoodIconNode(item: item, diameter: 44)
            }
            return SKNode()
        case .outfits:
            let label = SKLabelNode(text: entry.emoji)
            label.fontSize = 34
            label.verticalAlignmentMode = .center
            label.horizontalAlignmentMode = .center
            return label
        case .colors:
            let circle = SKShapeNode(circleOfRadius: 22)
            if let color = GoobyColor(rawValue: entry.id) {
                circle.fillColor = Theme.bodyColor(color)
            } else {
                circle.fillColor = .white
            }
            circle.strokeColor = .white
            circle.lineWidth = 2
            return circle
        }
    }

    private func priceText(for entry: ShopEntry) -> String {
        switch entry.state {
        case .equipped:
            return "Equipped"
        case .owned:
            return entry.price == 0 ? "Free" : "Owned"
        case .buyable, .unaffordable:
            return entry.price == 0 ? "Free" : "🪙 \(entry.price)"
        }
    }

    private func makeCountBadge(count: Int) -> SKNode {
        let badge = SKShapeNode(circleOfRadius: 14)
        badge.fillColor = UIColor(white: 0, alpha: 0.55)
        badge.strokeColor = .clear
        badge.position = CGPoint(x: cardSize.width / 2 - 18,
                                 y: cardSize.height / 2 - 18)
        let countLabel = SKLabelNode(fontNamed: Theme.fontName)
        countLabel.text = "x\(count)"
        countLabel.fontSize = 12
        countLabel.fontColor = .white
        countLabel.verticalAlignmentMode = .center
        countLabel.horizontalAlignmentMode = .center
        badge.addChild(countLabel)
        return badge
    }

    // MARK: - Actions

    private func handleCardTap(entry: ShopEntry, card: ButtonNode?) {
        let tab = selectedTab
        let wasOwnedOutfit = tab == .outfits && GameState.shared.ownedOutfitIDs.contains(entry.id)
        let success = ShopLogic.performAction(entryID: entry.id, tab: tab)
        refreshPreview()
        if success {
            let isPurchase = tab == .food || (tab == .outfits && !wasOwnedOutfit)
            AudioManager.shared.playSFX(isPurchase ? .coin : .confirm)
            previewGooby?.playHappyJump()
            renderGrid()
        } else {
            AudioManager.shared.playSFX(.error)
            if let card = card {
                shake(card)
            }
            flashCoinLabel()
        }
    }

    private func shake(_ node: SKNode) {
        node.removeAction(forKey: "shake")
        let shake = SKAction.sequence([
            SKAction.moveBy(x: -8, y: 0, duration: 0.05),
            SKAction.moveBy(x: 16, y: 0, duration: 0.1),
            SKAction.moveBy(x: -16, y: 0, duration: 0.1),
            SKAction.moveBy(x: 8, y: 0, duration: 0.05)
        ])
        node.run(shake, withKey: "shake")
    }

    // MARK: - Hint

    private func addHint() {
        let hint = SKLabelNode(fontNamed: Theme.fontName)
        hint.text = "Earn coins in the Game Room!"
        hint.fontSize = 14
        hint.fontColor = Theme.textColor
        hint.alpha = 0.8
        hint.verticalAlignmentMode = .center
        hint.horizontalAlignmentMode = .center
        hint.position = CGPoint(x: 0, y: bottomY)
        addChild(hint)
    }
}
