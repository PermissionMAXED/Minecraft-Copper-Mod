import Foundation

final class GameClock {
    static let shared = GameClock()

    private var timer: Timer?

    func start() {
        stop()
        timer = Timer.scheduledTimer(withTimeInterval: 1.0, repeats: true) { _ in
            GameState.shared.tickDecay(now: Date())
        }
    }

    func stop() {
        timer?.invalidate()
        timer = nil
    }
}
