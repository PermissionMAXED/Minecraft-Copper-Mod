import SpriteKit

class BaseScene: SKScene {
    let room: Room

    private var didBuild = false

    init(room: Room) {
        self.room = room
        super.init(size: SceneRouter.designSize)
        scaleMode = .aspectFit
        anchorPoint = CGPoint(x: 0.5, y: 0.5)
        backgroundColor = Theme.backgroundColor(for: room)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func didMove(to view: SKView) {
        super.didMove(to: view)
        guard !didBuild else { return }
        didBuild = true
        buildScene()
    }

    func buildScene() {}

    var topY: CGFloat {
        return SceneRouter.designSize.height / 2 - 60
    }

    var bottomY: CGFloat {
        return -SceneRouter.designSize.height / 2 + 40
    }

    func addBackButton(to room: Room) {
        let button = ButtonNode(iconTexture: AssetProvider.texture(.iconBack), diameter: 52) {
            AudioManager.shared.playSFX(.back)
            SceneRouter.shared.returnToRoom(room)
        }
        button.position = CGPoint(x: -SceneRouter.designSize.width / 2 + 44, y: topY)
        button.zPosition = 900
        addChild(button)
    }

    func addTitle(_ text: String) {
        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.text = text
        label.fontSize = 30
        label.fontColor = Theme.textColor
        label.verticalAlignmentMode = .center
        label.position = CGPoint(x: 0, y: topY)
        label.zPosition = 900
        addChild(label)
    }
}
