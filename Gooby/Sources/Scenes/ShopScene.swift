import SpriteKit

final class ShopScene: BaseScene {
    init() {
        super.init(room: .shop)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func buildScene() {
        addTitle("Shop")
        addBackButton(to: .home)
    }
}
