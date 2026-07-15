import Foundation

final class VoiceMimicController {
    static let shared = VoiceMimicController()

    enum TalkState: Equatable {
        case idle, requestingPermission, listening, recording, playing, denied
    }

    private(set) var state: TalkState = .idle

    var onStateChange: ((TalkState) -> Void)?
    var onPlaybackLevel: ((Float) -> Void)?

    func startListening() {
        state = .listening
        onStateChange?(state)
    }

    func stopListening() {
        state = .idle
        onStateChange?(state)
    }
}
