import SpriteKit

final class BedroomScene: BaseScene {
    init() {
        super.init(room: .bedroom)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func buildScene() {
        addTitle("Bedroom")
        addBackButton(to: .home)
    }
}
