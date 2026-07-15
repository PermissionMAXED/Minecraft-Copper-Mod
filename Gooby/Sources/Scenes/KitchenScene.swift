import SpriteKit

final class KitchenScene: BaseScene {
    init() {
        super.init(room: .kitchen)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func buildScene() {
        addTitle("Kitchen")
        addBackButton(to: .home)
    }
}
