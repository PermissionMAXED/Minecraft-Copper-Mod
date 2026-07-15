import SpriteKit

enum Room: String, CaseIterable {
    case home, kitchen, bathroom, bedroom, gameRoom, shop
}

final class SceneRouter {
    static let shared = SceneRouter()

    static let designSize = CGSize(width: 390, height: 844)

    private(set) var currentRoom: Room = .home

    private weak var view: SKView?

    func attach(to view: SKView) {
        self.view = view
    }

    func go(to room: Room, transition: SKTransition?) {
        guard let view = view else { return }
        let scene: BaseScene
        switch room {
        case .home: scene = HomeScene()
        case .kitchen: scene = KitchenScene()
        case .bathroom: scene = BathroomScene()
        case .bedroom: scene = BedroomScene()
        case .gameRoom: scene = GameRoomScene()
        case .shop: scene = ShopScene()
        }
        currentRoom = room
        let transition = transition ?? SKTransition.push(with: .left, duration: 0.35)
        view.presentScene(scene, transition: transition)
    }

    func presentMiniGame(_ scene: SKScene) {
        guard let view = view else { return }
        scene.scaleMode = .aspectFit
        view.presentScene(scene, transition: SKTransition.doorsOpenVertical(withDuration: 0.4))
    }

    func returnToRoom(_ room: Room) {
        go(to: room, transition: SKTransition.push(with: .right, duration: 0.35))
    }
}
