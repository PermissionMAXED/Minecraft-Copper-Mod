import SpriteKit
import UIKit

final class GameRoomScene: BaseScene {
    private var coinLabel: SKLabelNode?

    init() {
        super.init(room: .gameRoom)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func buildScene() {
        addTitle("Game Room")
        addBackButton(to: .home)
        addCoinCounter()
        addCabinet(nodeName: "cabinet_carrot",
                   emoji: "🥕",
                   title: "Carrot Catch",
                   subtitle: "Catch carrots, dodge rocks!",
                   y: 130)
        addCabinet(nodeName: "cabinet_memory",
                   emoji: "🃏",
                   title: "Memory Match",
                   subtitle: "Find all the pairs!",
                   y: -110)
    }

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else { return }
        let location = touch.location(in: self)
        if let cabinet = childNode(withName: "cabinet_carrot"),
           cabinet.calculateAccumulatedFrame().contains(location) {
            AudioManager.shared.playSFX(.click)
            SceneRouter.shared.presentMiniGame(CarrotCatchScene())
        } else if let cabinet = childNode(withName: "cabinet_memory"),
                  cabinet.calculateAccumulatedFrame().contains(location) {
            AudioManager.shared.playSFX(.click)
            SceneRouter.shared.presentMiniGame(MemoryMatchScene())
        }
    }

    private func addCoinCounter() {
        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.fontSize = 20
        label.fontColor = Theme.textColor
        label.horizontalAlignmentMode = .right
        label.verticalAlignmentMode = .center
        label.position = CGPoint(x: SceneRouter.designSize.width / 2 - 16, y: topY)
        label.zPosition = 900
        addChild(label)
        coinLabel = label
        refreshCoinLabel()
        run(SKAction.repeatForever(SKAction.sequence([
            SKAction.wait(forDuration: 0.5),
            SKAction.run { [weak self] in self?.refreshCoinLabel() }
        ])))
    }

    private func refreshCoinLabel() {
        coinLabel?.text = "🪙 \(GameState.shared.coins)"
    }

    private func addCabinet(nodeName: String, emoji: String, title: String, subtitle: String, y: CGFloat) {
        let cabinet = SKNode()
        cabinet.name = nodeName
        cabinet.position = CGPoint(x: 0, y: y)

        let panel = SKShapeNode(rectOf: CGSize(width: 300, height: 190), cornerRadius: Theme.cornerRadius)
        panel.fillColor = Theme.panelColor
        panel.strokeColor = .clear
        cabinet.addChild(panel)

        let screen = SKShapeNode(rectOf: CGSize(width: 260, height: 96), cornerRadius: Theme.cornerRadius)
        screen.fillColor = UIColor(red: 0.16, green: 0.17, blue: 0.30, alpha: 1)
        screen.strokeColor = .clear
        screen.position = CGPoint(x: 0, y: 32)
        cabinet.addChild(screen)

        let emojiLabel = SKLabelNode(fontNamed: Theme.fontName)
        emojiLabel.text = emoji
        emojiLabel.fontSize = 54
        emojiLabel.verticalAlignmentMode = .center
        emojiLabel.position = CGPoint(x: 0, y: 32)
        cabinet.addChild(emojiLabel)

        let nameLabel = SKLabelNode(fontNamed: Theme.fontName)
        nameLabel.text = title
        nameLabel.fontSize = 22
        nameLabel.fontColor = Theme.textColor
        nameLabel.verticalAlignmentMode = .center
        nameLabel.position = CGPoint(x: 0, y: -44)
        cabinet.addChild(nameLabel)

        let subtitleLabel = SKLabelNode(fontNamed: Theme.fontName)
        subtitleLabel.text = subtitle
        subtitleLabel.fontSize = 14
        subtitleLabel.fontColor = UIColor(white: 0.35, alpha: 1)
        subtitleLabel.verticalAlignmentMode = .center
        subtitleLabel.position = CGPoint(x: 0, y: -72)
        cabinet.addChild(subtitleLabel)

        addChild(cabinet)
    }
}
