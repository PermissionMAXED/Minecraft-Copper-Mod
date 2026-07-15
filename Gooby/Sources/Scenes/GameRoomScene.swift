import SpriteKit

final class GameRoomScene: BaseScene {
    init() {
        super.init(room: .gameRoom)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func buildScene() {
        addTitle("Game Room")
        addBackButton(to: .home)
    }
}
