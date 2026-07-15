import SpriteKit

final class BathroomScene: BaseScene {
    init() {
        super.init(room: .bathroom)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func buildScene() {
        addTitle("Bathroom")
        addBackButton(to: .home)
    }
}
