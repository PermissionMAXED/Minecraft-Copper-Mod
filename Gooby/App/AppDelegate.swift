import UIKit

@main
final class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        SaveManager.bootstrap()
        GameClock.shared.start()
        AudioManager.shared.configureSessionForPlayback()
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = GameViewController()
        window?.makeKeyAndVisible()
        AudioManager.shared.startMusic()
        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {
        SaveManager.shared.saveNow()
    }

    func applicationDidBecomeActive(_ application: UIApplication) {
        GameState.shared.tickDecay(now: Date())
    }
}
