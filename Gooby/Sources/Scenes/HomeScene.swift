import SpriteKit

final class HomeScene: BaseScene {
    init() {
        super.init(room: .home)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func buildScene() {
        addTitle("GOOBY")

        let gooby = GoobyNode(color: GameState.shared.goobyColor,
                              outfitID: GameState.shared.equippedOutfitID)
        gooby.position = CGPoint(x: 0, y: -80)
        addChild(gooby)
        gooby.startIdleAnimations()

        let buttonSize = CGSize(width: 110, height: 56)
        let rooms: [(String, Room)] = [
            ("Kitchen", .kitchen),
            ("Bath", .bathroom),
            ("Sleep", .bedroom),
            ("Games", .gameRoom),
            ("Shop", .shop)
        ]
        for (index, entry) in rooms.enumerated() {
            let button = ButtonNode(text: entry.0, size: buttonSize) {
                SceneRouter.shared.go(to: entry.1, transition: nil)
            }
            let row = index / 3
            let column = index % 3
            let columnsInRow = row == 0 ? 3 : 2
            let x = (CGFloat(column) - CGFloat(columnsInRow - 1) / 2) * 124
            let y = bottomY + 100 - CGFloat(row) * 68
            button.position = CGPoint(x: x, y: y)
            addChild(button)
        }
    }
}
