import SpriteKit
import UIKit

/// Top-of-screen HUD: four stat bars in a 2x2 grid inside a rounded panel,
/// plus a coin display. Keeps itself in sync with `GameState.shared`.
final class HUDNode: SKNode {
    static let panelSize = CGSize(width: 372, height: 104)

    private var statBars: [StatKind: StatBarNode] = [:]
    private let coinLabel: SKLabelNode
    private var observerToken: NSObjectProtocol?

    override init() {
        coinLabel = SKLabelNode(fontNamed: Theme.fontName)
        super.init()

        let panel = SKShapeNode(rectOf: HUDNode.panelSize, cornerRadius: Theme.cornerRadius)
        panel.fillColor = Theme.panelColor
        panel.strokeColor = .clear
        addChild(panel)

        // Columns leave room for the emoji labels hanging left of each bar.
        let columnsX: [CGFloat] = [-78, 104]
        let rowsY: [CGFloat] = [26, -4]
        for (index, kind) in StatKind.allCases.enumerated() {
            let bar = StatBarNode(kind: kind, size: Theme.statBarSize)
            bar.position = CGPoint(x: columnsX[index % 2], y: rowsY[index / 2])
            addChild(bar)
            statBars[kind] = bar
        }

        coinLabel.fontSize = 16
        coinLabel.fontColor = Theme.textColor
        coinLabel.verticalAlignmentMode = .center
        coinLabel.horizontalAlignmentMode = .center
        coinLabel.position = CGPoint(x: 0, y: -36)
        addChild(coinLabel)

        refresh(animated: false)

        observerToken = NotificationCenter.default.addObserver(
            forName: .gameStateDidChange,
            object: nil,
            queue: .main
        ) { [weak self] _ in
            self?.refresh(animated: true)
        }
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    deinit {
        if let token = observerToken {
            NotificationCenter.default.removeObserver(token)
        }
    }

    func refresh(animated: Bool) {
        let state = GameState.shared
        for kind in StatKind.allCases {
            statBars[kind]?.setValue(state.stats[kind], animated: animated)
        }
        coinLabel.text = "🪙 \(state.coins)"
    }
}
