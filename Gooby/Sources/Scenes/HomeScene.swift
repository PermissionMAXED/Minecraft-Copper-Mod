import SpriteKit
import UIKit

final class HomeScene: BaseScene {
    private enum ZLayer {
        static let sleepOverlay: CGFloat = 500
        static let controls: CGFloat = 600
        static let toast: CGFloat = 950
    }

    private var gooby: GoobyNode?
    private var hud: HUDNode?
    private var musicButton: ButtonNode?
    private var micTint: SKShapeNode?
    private var stateObserverToken: NSObjectProtocol?
    private var didShowMicDeniedToast = false
    private var sleepOverlay: SKSpriteNode?
    private var sleepHint: SKLabelNode?
    private var lastKnownIsSleeping = false

    // Poke / tickle touch tracking.
    private var isTrackingGoobyTouch = false
    private var totalDragDistance: CGFloat = 0
    private var lastTouchLocation: CGPoint = .zero
    private var lastDragDirection = 0
    private var directionChanges = 0

    init() {
        super.init(room: .home)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    deinit {
        if let token = stateObserverToken {
            NotificationCenter.default.removeObserver(token)
        }
    }

    override func buildScene() {
        addTitle("GOOBY")

        let hud = HUDNode()
        hud.position = CGPoint(x: 0, y: topY - 90)
        hud.zPosition = ZLayer.controls
        addChild(hud)
        self.hud = hud

        let gooby = GoobyNode(color: GameState.shared.goobyColor,
                              outfitID: GameState.shared.equippedOutfitID)
        gooby.position = CGPoint(x: 0, y: -60)
        addChild(gooby)
        gooby.startIdleAnimations()
        gooby.mood = GameState.shared.stats.mood
        self.gooby = gooby

        installBottomNav()
        installMicButton()
        installMusicButton()
        installSleepOverlayIfNeeded()

        stateObserverToken = NotificationCenter.default.addObserver(
            forName: .gameStateDidChange,
            object: nil,
            queue: .main
        ) { [weak self] _ in
            guard let self = self else { return }
            self.gooby?.mood = GameState.shared.stats.mood
            let sleeping = GameState.shared.isSleeping
            if sleeping != self.lastKnownIsSleeping {
                self.lastKnownIsSleeping = sleeping
                self.applySleepVisuals(sleeping)
            }
        }
    }

    override func willMove(from view: SKView) {
        let mic = VoiceMimicController.shared
        mic.stopListening()
        mic.onStateChange = nil
        mic.onPlaybackLevel = nil
        super.willMove(from: view)
    }

    // MARK: - Bottom navigation

    private func installBottomNav() {
        let entries: [(label: String, emoji: String?, icon: TextureID?, room: Room)] = [
            ("Kitchen", "🍽", nil, .kitchen),
            ("Bath", "🛁", nil, .bathroom),
            ("Sleep", "🛏", nil, .bedroom),
            ("Games", nil, .iconGamepad, .gameRoom),
            ("Shop", nil, .iconCart, .shop)
        ]

        for (index, entry) in entries.enumerated() {
            let texture: SKTexture
            if let icon = entry.icon {
                texture = AssetProvider.texture(icon)
            } else {
                texture = HomeScene.emojiTexture(entry.emoji ?? "?", pointSize: 34)
            }

            let room = entry.room
            let button = ButtonNode(iconTexture: texture, diameter: 56) {
                SceneRouter.shared.go(to: room, transition: nil)
            }
            let x = (CGFloat(index) - 2) * 74
            button.position = CGPoint(x: x, y: bottomY + 24)
            button.zPosition = ZLayer.controls
            addChild(button)

            let label = SKLabelNode(fontNamed: Theme.fontName)
            label.text = entry.label
            label.fontSize = 11
            label.fontColor = Theme.textColor
            label.verticalAlignmentMode = .center
            label.position = CGPoint(x: x, y: bottomY - 18)
            label.zPosition = ZLayer.controls
            addChild(label)
        }
    }

    // MARK: - Mic button

    private func installMicButton() {
        let button = ButtonNode(iconTexture: HomeScene.emojiTexture("🎤", pointSize: 34),
                                diameter: 56) {
            let mic = VoiceMimicController.shared
            if mic.state == .idle {
                mic.startListening()
            } else {
                mic.stopListening()
            }
        }
        button.position = CGPoint(x: SceneRouter.designSize.width / 2 - 44, y: 20)
        button.zPosition = ZLayer.controls
        addChild(button)

        let tint = SKShapeNode(circleOfRadius: 28)
        tint.strokeColor = .clear
        tint.zPosition = 10
        button.addChild(tint)
        micTint = tint

        let mic = VoiceMimicController.shared
        mic.onStateChange = { [weak self] state in
            DispatchQueue.main.async {
                self?.applyMicTint(for: state)
                // onPlaybackLevel stops firing outside .playing, so close
                // the mouth here or it can stay stuck open after playback.
                if state != .playing {
                    self?.gooby?.setTalkingLevel(0)
                }
            }
        }
        mic.onPlaybackLevel = { [weak self] level in
            DispatchQueue.main.async {
                self?.gooby?.setTalkingLevel(level)
            }
        }
        applyMicTint(for: mic.state)
    }

    private func applyMicTint(for state: VoiceMimicController.TalkState) {
        guard let tint = micTint else { return }
        tint.removeAction(forKey: "pulse")
        tint.setScale(1)

        switch state {
        case .idle, .requestingPermission:
            tint.fillColor = UIColor.gray.withAlphaComponent(0.35)
        case .listening:
            tint.fillColor = UIColor.systemGreen.withAlphaComponent(0.45)
            let pulse = SKAction.sequence([SKAction.scale(to: 1.15, duration: 0.4),
                                           SKAction.scale(to: 0.95, duration: 0.4)])
            tint.run(SKAction.repeatForever(pulse), withKey: "pulse")
        case .recording:
            tint.fillColor = UIColor.systemRed.withAlphaComponent(0.45)
        case .playing:
            tint.fillColor = UIColor.systemBlue.withAlphaComponent(0.45)
        case .denied:
            tint.fillColor = UIColor(white: 0.1, alpha: 0.6)
            showMicDeniedToastIfNeeded()
        }
    }

    private func showMicDeniedToastIfNeeded() {
        guard !didShowMicDeniedToast else { return }
        didShowMicDeniedToast = true

        let panel = SKShapeNode(rectOf: CGSize(width: 230, height: 44),
                                cornerRadius: Theme.cornerRadius)
        panel.fillColor = UIColor(white: 0.1, alpha: 0.85)
        panel.strokeColor = .clear
        panel.position = CGPoint(x: 0, y: 130)
        panel.zPosition = ZLayer.toast
        panel.alpha = 0

        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.text = "Mic access needed"
        label.fontSize = 16
        label.fontColor = .white
        label.verticalAlignmentMode = .center
        panel.addChild(label)
        addChild(panel)

        panel.run(SKAction.sequence([SKAction.fadeIn(withDuration: 0.2),
                                     SKAction.wait(forDuration: 1.6),
                                     SKAction.fadeOut(withDuration: 0.3),
                                     SKAction.removeFromParent()]))
    }

    // MARK: - Music toggle

    private func installMusicButton() {
        musicButton?.removeFromParent()

        let textureID: TextureID = AudioManager.shared.isMusicEnabled ? .iconMusicOn : .iconMusicOff
        let button = ButtonNode(iconTexture: AssetProvider.texture(textureID), diameter: 44) { [weak self] in
            AudioManager.shared.isMusicEnabled = !AudioManager.shared.isMusicEnabled
            self?.installMusicButton()
        }
        button.position = CGPoint(x: SceneRouter.designSize.width / 2 - 36, y: topY)
        button.zPosition = ZLayer.controls
        addChild(button)
        musicButton = button
    }

    // MARK: - Sleeping state

    private func installSleepOverlayIfNeeded() {
        lastKnownIsSleeping = GameState.shared.isSleeping
        guard lastKnownIsSleeping else { return }
        applySleepVisuals(true)
    }

    private func applySleepVisuals(_ sleeping: Bool) {
        gooby?.playSleep(sleeping)

        sleepOverlay?.removeFromParent()
        sleepOverlay = nil
        sleepHint?.removeFromParent()
        sleepHint = nil

        guard sleeping else { return }

        let overlay = SKSpriteNode(color: UIColor(white: 0, alpha: 0.45),
                                   size: SceneRouter.designSize)
        overlay.zPosition = ZLayer.sleepOverlay
        addChild(overlay)
        sleepOverlay = overlay

        let hint = SKLabelNode(fontNamed: Theme.fontName)
        hint.text = "Gooby is sleeping… go to the Bedroom"
        hint.fontSize = 16
        hint.fontColor = .white
        hint.verticalAlignmentMode = .center
        hint.position = CGPoint(x: 0, y: 150)
        hint.zPosition = ZLayer.sleepOverlay + 1
        addChild(hint)
        sleepHint = hint
    }

    // MARK: - Poke & tickle touch handling

    private func isOverGooby(_ location: CGPoint) -> Bool {
        guard let gooby = gooby else { return false }
        return nodes(at: location).contains { $0 === gooby || $0.inParentHierarchy(gooby) }
    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard let touch = touches.first else { return }
        let location = touch.location(in: self)
        guard !GameState.shared.isSleeping, isOverGooby(location) else { return }

        isTrackingGoobyTouch = true
        totalDragDistance = 0
        lastTouchLocation = location
        lastDragDirection = 0
        directionChanges = 0
    }

    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard isTrackingGoobyTouch, let touch = touches.first else { return }
        let location = touch.location(in: self)
        let dx = location.x - lastTouchLocation.x
        let dy = location.y - lastTouchLocation.y
        totalDragDistance += (dx * dx + dy * dy).squareRoot()

        if abs(dx) >= 4, isOverGooby(location) {
            let direction = dx > 0 ? 1 : -1
            if lastDragDirection != 0 && direction != lastDragDirection {
                directionChanges += 1
            }
            lastDragDirection = direction
        }
        lastTouchLocation = location
    }

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        guard isTrackingGoobyTouch else { return }
        isTrackingGoobyTouch = false

        if directionChanges >= 3 {
            gooby?.playTickleReaction()
            GameState.shared.addHappiness(3)
        } else if totalDragDistance < 12 {
            gooby?.playPokeReaction()
            GameState.shared.addHappiness(1)
        }
    }

    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent?) {
        isTrackingGoobyTouch = false
    }

    // MARK: - Helpers

    private static func emojiTexture(_ emoji: String, pointSize: CGFloat) -> SKTexture {
        let attributes: [NSAttributedString.Key: Any] = [
            .font: UIFont.systemFont(ofSize: pointSize)
        ]
        let string = emoji as NSString
        let size = string.size(withAttributes: attributes)
        let renderSize = CGSize(width: max(size.width, 1), height: max(size.height, 1))
        let renderer = UIGraphicsImageRenderer(size: renderSize)
        let image = renderer.image { _ in
            string.draw(at: .zero, withAttributes: attributes)
        }
        return SKTexture(image: image)
    }
}
