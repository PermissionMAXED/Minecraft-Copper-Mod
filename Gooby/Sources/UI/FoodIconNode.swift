import SpriteKit

final class FoodIconNode: SKNode {
    let item: FoodItem

    init(item: FoodItem, diameter: CGFloat) {
        self.item = item
        super.init()

        let plate = SKShapeNode(circleOfRadius: diameter / 2)
        plate.fillColor = .white
        plate.strokeColor = .clear
        addChild(plate)

        let label = SKLabelNode(text: item.emoji)
        label.fontSize = diameter * 0.55
        label.verticalAlignmentMode = .center
        label.horizontalAlignmentMode = .center
        addChild(label)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
