import SpriteKit
import UIKit

final class BedroomScene: BaseScene {
    private var gooby: GoobyNode!
    private var darkOverlay: SKSpriteNode!
    private var windowSkyEmoji: SKLabelNode!
    private var windowPane: SKShapeNode!
    private var energyLabel: SKLabelNode!
    private var toggleButton: ButtonNode?

    private var isLampOn = false
    private var showsSleepingVisuals = false
    private var stateObserver: NSObjectProtocol?

    init() {
        super.init(room: .bedroom)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    deinit {
        if let observer = stateObserver {
            NotificationCenter.default.removeObserver(observer)
        }
    }

    override func buildScene() {
        addTitle("Bedroom")
        addBackButton(to: .home)

        buildWindow()
        buildBed()

        let gooby = GoobyNode(color: GameState.shared.goobyColor,
                              outfitID: GameState.shared.equippedOutfitID)
        gooby.position = CGPoint(x: 0, y: -60)
        gooby.zPosition = 20
        addChild(gooby)
        gooby.startIdleAnimations()
        gooby.mood = GameState.shared.stats.mood
        self.gooby = gooby

        buildOverlay()
        buildEnergyLabel()
        buildLampButton()

        if GameState.shared.isSleeping {
            applySleepVisuals(animated: false)
        } else {
            applyAwakeVisuals(animated: false)
        }

        stateObserver = NotificationCenter.default.addObserver(forName: .gameStateDidChange,
                                                               object: nil,
                                                               queue: .main) { [weak self] _ in
            guard let self = self else { return }
            self.refreshEnergyLabel()
            self.gooby.mood = GameState.shared.stats.mood
            // Model auto-wakes at full energy; sync visuals when that happens.
            if !GameState.shared.isSleeping && self.showsSleepingVisuals {
                self.applyAwakeVisuals(animated: true)
            }
        }
    }

    override func willMove(from view: SKView) {
        if let observer = stateObserver {
            NotificationCenter.default.removeObserver(observer)
            stateObserver = nil
        }
        super.willMove(from: view)
    }

    // MARK: - Setup

    private func buildWindow() {
        let pane = SKShapeNode(rectOf: CGSize(width: 120, height: 140), cornerRadius: Theme.cornerRadius)
        pane.fillColor = UIColor(red: 0.55, green: 0.75, blue: 0.95, alpha: 1)
        pane.strokeColor = UIColor(white: 0.9, alpha: 1)
        pane.lineWidth = 5
        pane.position = CGPoint(x: -110, y: 200)
        pane.zPosition = 10
        addChild(pane)
        windowPane = pane

        let sky = SKLabelNode(text: "☀️")
        sky.fontSize = 44
        sky.verticalAlignmentMode = .center
        sky.horizontalAlignmentMode = .center
        sky.zPosition = 1
        pane.addChild(sky)
        windowSkyEmoji = sky
    }

    private func buildBed() {
        let bed = SKShapeNode(rectOf: CGSize(width: 300, height: 130), cornerRadius: Theme.cornerRadius)
        bed.fillColor = UIColor(red: 0.55, green: 0.35, blue: 0.65, alpha: 1)
        bed.strokeColor = UIColor(red: 0.4, green: 0.24, blue: 0.5, alpha: 1)
        bed.lineWidth = 4
        bed.position = CGPoint(x: 0, y: -160)
        bed.zPosition = 12
        addChild(bed)

        let pillow = SKShapeNode(rectOf: CGSize(width: 100, height: 56), cornerRadius: 18)
        pillow.fillColor = UIColor(white: 0.95, alpha: 1)
        pillow.strokeColor = .clear
        pillow.position = CGPoint(x: -85, y: 20)
        pillow.zPosition = 1
        bed.addChild(pillow)
    }

    private func buildOverlay() {
        let overlay = SKSpriteNode(color: .black, size: CGSize(width: 1000, height: 1400))
        overlay.position = .zero
        overlay.zPosition = 500
        overlay.alpha = 0
        addChild(overlay)
        darkOverlay = overlay
    }

    private func buildEnergyLabel() {
        let label = SKLabelNode(fontNamed: Theme.fontName)
        label.fontSize = 20
        label.fontColor = Theme.statColor(.energy)
        label.verticalAlignmentMode = .center
        label.horizontalAlignmentMode = .center
        label.position = CGPoint(x: 0, y: topY - 60)
        label.zPosition = 900
        addChild(label)
        energyLabel = label
        refreshEnergyLabel()
    }

    private func buildLampButton() {
        let lamp = ButtonNode(text: "💡", size: CGSize(width: 56, height: 56)) { [weak self] in
            guard let self = self else { return }
            self.isLampOn.toggle()
            self.updateOverlay(animated: true)
        }
        lamp.position = CGPoint(x: 145, y: topY)
        lamp.zPosition = 900
        addChild(lamp)
    }

    private func rebuildToggleButton() {
        toggleButton?.removeFromParent()
        let sleeping = showsSleepingVisuals
        let title = sleeping ? "Wake up ☀️" : "Sleep 😴"
        let button = ButtonNode(text: title, size: CGSize(width: 220, height: 64)) { [weak self] in
            self?.handleToggle()
        }
        button.position = CGPoint(x: 0, y: bottomY + 50)
        button.zPosition = 900
        addChild(button)
        toggleButton = button
    }

    // MARK: - Sleep / wake

    private func handleToggle() {
        if showsSleepingVisuals {
            GameState.shared.setSleeping(false)
            applyAwakeVisuals(animated: true)
        } else {
            GameState.shared.setSleeping(true)
            applySleepVisuals(animated: true)
        }
    }

    private func applySleepVisuals(animated: Bool) {
        showsSleepingVisuals = true
        gooby.playSleep(true)
        windowSkyEmoji.text = "🌙"
        windowPane.fillColor = UIColor(red: 0.1, green: 0.12, blue: 0.3, alpha: 1)
        updateOverlay(animated: animated)
        rebuildToggleButton()
    }

    private func applyAwakeVisuals(animated: Bool) {
        showsSleepingVisuals = false
        gooby.playSleep(false)
        windowSkyEmoji.text = "☀️"
        windowPane.fillColor = UIColor(red: 0.55, green: 0.75, blue: 0.95, alpha: 1)
        updateOverlay(animated: animated)
        rebuildToggleButton()
    }

    private func updateOverlay(animated: Bool) {
        let target: CGFloat
        if showsSleepingVisuals {
            target = isLampOn ? 0.25 : 0.55
        } else {
            target = 0
        }
        darkOverlay.removeAllActions()
        if animated {
            darkOverlay.run(SKAction.fadeAlpha(to: target, duration: 0.6))
        } else {
            darkOverlay.alpha = target
        }
    }

    private func refreshEnergyLabel() {
        energyLabel?.text = "Energy: \(Int(GameState.shared.stats.energy))%"
    }
}
