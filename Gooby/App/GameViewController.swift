import UIKit
import SpriteKit

final class GameViewController: UIViewController {
    override func loadView() {
        view = SKView(frame: UIScreen.main.bounds)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        let skView = view as! SKView
        skView.ignoresSiblingOrder = true
        SceneRouter.shared.attach(to: skView)
        SceneRouter.shared.go(to: .home, transition: nil)
    }

    override var prefersStatusBarHidden: Bool {
        return true
    }

    override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
        return .portrait
    }

    override var prefersHomeIndicatorAutoHidden: Bool {
        return true
    }
}
