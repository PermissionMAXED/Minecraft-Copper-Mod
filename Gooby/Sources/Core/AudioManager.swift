import Foundation
import AVFoundation

enum SFX: String, CaseIterable {
    case click = "sfx_click"
    case back = "sfx_back"
    case confirm = "sfx_confirm"
    case error = "sfx_error"
    case coin = "sfx_coin"
    case eat = "sfx_eat"
    case drop = "sfx_drop"
    case splash = "sfx_splash"
    case pop = "sfx_pop"
    case win = "sfx_win"
    case lose = "sfx_lose"
}

final class AudioManager {
    static let shared = AudioManager()

    private static let musicKey = "gooby.musicEnabled"
    private static let sfxKey = "gooby.sfxEnabled"

    private var sfxPlayers: [SFX: AVAudioPlayer] = [:]
    private var musicPlayer: AVAudioPlayer?

    var isMusicEnabled: Bool {
        get {
            if UserDefaults.standard.object(forKey: AudioManager.musicKey) == nil { return true }
            return UserDefaults.standard.bool(forKey: AudioManager.musicKey)
        }
        set {
            UserDefaults.standard.set(newValue, forKey: AudioManager.musicKey)
            if newValue {
                startMusic()
            } else {
                stopMusic()
            }
        }
    }

    var isSfxEnabled: Bool {
        get {
            if UserDefaults.standard.object(forKey: AudioManager.sfxKey) == nil { return true }
            return UserDefaults.standard.bool(forKey: AudioManager.sfxKey)
        }
        set {
            UserDefaults.standard.set(newValue, forKey: AudioManager.sfxKey)
        }
    }

    func playSFX(_ sfx: SFX) {
        guard isSfxEnabled else { return }
        let player: AVAudioPlayer
        if let cached = sfxPlayers[sfx] {
            player = cached
        } else {
            guard let url = Bundle.main.url(forResource: sfx.rawValue, withExtension: "m4a"),
                  let created = try? AVAudioPlayer(contentsOf: url) else {
                return
            }
            created.prepareToPlay()
            sfxPlayers[sfx] = created
            player = created
        }
        player.currentTime = 0
        player.play()
    }

    func startMusic() {
        guard isMusicEnabled else { return }
        if let player = musicPlayer {
            if !player.isPlaying {
                player.play()
            }
            return
        }
        guard let url = Bundle.main.url(forResource: "music_main", withExtension: "m4a"),
              let player = try? AVAudioPlayer(contentsOf: url) else {
            return
        }
        player.numberOfLoops = -1
        player.volume = 0.35
        player.play()
        musicPlayer = player
    }

    func stopMusic() {
        musicPlayer?.stop()
        musicPlayer = nil
    }

    func duckMusic(_ ducked: Bool) {
        musicPlayer?.volume = ducked ? 0.1 : 0.35
    }

    func configureSessionForPlayback() {
        try? AVAudioSession.sharedInstance().setCategory(.ambient)
        try? AVAudioSession.sharedInstance().setActive(true)
    }

    func configureSessionForRecording() {
        try? AVAudioSession.sharedInstance().setCategory(.playAndRecord,
                                                         mode: .default,
                                                         options: [.defaultToSpeaker, .allowBluetooth])
    }
}
