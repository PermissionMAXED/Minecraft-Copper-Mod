import AVFoundation
import Foundation

/// Talking-Tom style voice mimic: listens on the microphone, records when it
/// detects speech, then replays the clip pitched-up and sped-up, looping back
/// to listening until `stopListening()` is called.
final class VoiceMimicController {
    static let shared = VoiceMimicController()

    enum TalkState: Equatable { case idle, requestingPermission, listening, recording, playing, denied }

    private(set) var state: TalkState = .idle

    var onStateChange: ((TalkState) -> Void)?
    var onPlaybackLevel: ((Float) -> Void)?

    // MARK: - Tuning

    private enum Tuning {
        static let voiceStartRMS: Float = 0.02
        static let voiceStopRMS: Float = 0.012
        static let voiceStartBufferCount = 2
        static let silenceStopSeconds = 0.7
        static let maxRecordSeconds = 8.0
        static let pitchCents: Float = 600
        static let playbackRate: Float = 1.15
        static let levelGain: Float = 8
        static let deniedResetSeconds = 2.0
        static let tapBufferSize: AVAudioFrameCount = 1024
    }

    // MARK: - Capture bookkeeping

    /// Capture progress as seen by the input tap. Read/written from both the
    /// audio tap thread and the main thread, so all access goes through `lock`.
    private enum CapturePhase { case inactive, waitingForVoice, capturing, finished }

    /// Outcome of processing one tap buffer, acted upon outside the lock.
    private enum CaptureEvent { case none, voiceStarted, captureEnded }

    private let lock = NSLock()
    private var capturePhase: CapturePhase = .inactive
    private var loudBufferStreak = 0
    private var silenceSeconds = 0.0
    private var recordedSeconds = 0.0
    private var audioFile: AVAudioFile?

    private var recordEngine: AVAudioEngine?
    private var playbackEngine: AVAudioEngine?
    private var playerNode: AVAudioPlayerNode?
    private var timePitchNode: AVAudioUnitTimePitch?

    /// Incremented on every start/stop so stale async callbacks (permission
    /// response, tap dispatches, playback completion, timers) become no-ops.
    private var generation = 0

    private var tempFileURL: URL {
        FileManager.default.temporaryDirectory.appendingPathComponent("gooby_talk.caf")
    }

    // MARK: - Public API

    func startListening() {
        if !Thread.isMainThread {
            DispatchQueue.main.async { self.startListening() }
            return
        }
        guard state == .idle else { return }
        generation += 1
        let gen = generation
        setState(.requestingPermission)
        AVAudioSession.sharedInstance().requestRecordPermission { [weak self] granted in
            DispatchQueue.main.async {
                guard let self = self, self.generation == gen, self.state == .requestingPermission else { return }
                if granted {
                    self.beginRecordLoop(generation: gen)
                } else {
                    self.setState(.denied)
                    DispatchQueue.main.asyncAfter(deadline: .now() + Tuning.deniedResetSeconds) { [weak self] in
                        guard let self = self, self.generation == gen, self.state == .denied else { return }
                        self.setState(.idle)
                    }
                }
            }
        }
    }

    func stopListening() {
        if !Thread.isMainThread {
            DispatchQueue.main.async { self.stopListening() }
            return
        }
        generation += 1
        stopPlaybackEngine()
        stopRecordEngine()
        try? FileManager.default.removeItem(at: tempFileURL)
        AudioManager.shared.duckMusic(false)
        AudioManager.shared.configureSessionForPlayback()
        if state != .idle {
            setState(.idle)
        }
    }

    // MARK: - Record loop

    /// Builds a fresh input engine + tap and enters `.listening`. Used both for
    /// the initial (post-permission) start and when looping after playback.
    private func beginRecordLoop(generation gen: Int) {
        guard generation == gen else { return }

        AudioManager.shared.configureSessionForRecording()
        AudioManager.shared.duckMusic(true)

        locked { () -> Void in
            capturePhase = .waitingForVoice
            loudBufferStreak = 0
            silenceSeconds = 0
            recordedSeconds = 0
            audioFile = nil
        }

        let engine = AVAudioEngine()
        let input = engine.inputNode
        let format = input.outputFormat(forBus: 0)
        guard format.sampleRate > 0, format.channelCount > 0 else {
            stopListening()
            return
        }

        input.removeTap(onBus: 0)
        input.installTap(onBus: 0, bufferSize: Tuning.tapBufferSize, format: format) { [weak self] buffer, _ in
            self?.processCaptureBuffer(buffer, generation: gen)
        }

        engine.prepare()
        do {
            try engine.start()
        } catch {
            input.removeTap(onBus: 0)
            stopListening()
            return
        }

        recordEngine = engine
        setState(.listening)
    }

    /// Runs on the audio tap thread. Only lightweight math + file writes here;
    /// all state transitions are dispatched to the main thread.
    private func processCaptureBuffer(_ buffer: AVAudioPCMBuffer, generation gen: Int) {
        let level = VoiceMimicController.rootMeanSquare(of: buffer)
        let sampleRate = buffer.format.sampleRate
        let bufferSeconds = sampleRate > 0 ? Double(buffer.frameLength) / sampleRate : 0

        let event = locked { () -> CaptureEvent in
            switch capturePhase {
            case .inactive, .finished:
                return .none
            case .waitingForVoice:
                loudBufferStreak = level > Tuning.voiceStartRMS ? loudBufferStreak + 1 : 0
                guard loudBufferStreak >= Tuning.voiceStartBufferCount else { return .none }
                let url = tempFileURL
                try? FileManager.default.removeItem(at: url)
                guard let file = try? AVAudioFile(forWriting: url, settings: buffer.format.settings) else {
                    loudBufferStreak = 0
                    return .none
                }
                try? file.write(from: buffer)
                audioFile = file
                capturePhase = .capturing
                silenceSeconds = 0
                recordedSeconds = bufferSeconds
                return .voiceStarted
            case .capturing:
                if let file = audioFile {
                    try? file.write(from: buffer)
                }
                recordedSeconds += bufferSeconds
                silenceSeconds = level < Tuning.voiceStopRMS ? silenceSeconds + bufferSeconds : 0
                guard silenceSeconds >= Tuning.silenceStopSeconds || recordedSeconds > Tuning.maxRecordSeconds else {
                    return .none
                }
                capturePhase = .finished
                // Releasing the writer closes/flushes the file before playback opens it.
                audioFile = nil
                return .captureEnded
            }
        }

        switch event {
        case .none:
            break
        case .voiceStarted:
            DispatchQueue.main.async { [weak self] in
                guard let self = self, self.generation == gen, self.state == .listening else { return }
                self.setState(.recording)
            }
        case .captureEnded:
            DispatchQueue.main.async { [weak self] in
                self?.finishCapture(generation: gen)
            }
        }
    }

    private func finishCapture(generation gen: Int) {
        guard generation == gen, state == .recording else { return }
        stopRecordEngine()
        startPlayback(generation: gen)
    }

    private func stopRecordEngine() {
        locked { () -> Void in
            capturePhase = .inactive
            audioFile = nil
            loudBufferStreak = 0
            silenceSeconds = 0
            recordedSeconds = 0
        }
        if let engine = recordEngine {
            engine.inputNode.removeTap(onBus: 0)
            engine.stop()
        }
        recordEngine = nil
    }

    // MARK: - Playback

    private func startPlayback(generation gen: Int) {
        guard let file = try? AVAudioFile(forReading: tempFileURL) else {
            // Nothing usable was captured; go straight back to listening.
            beginRecordLoop(generation: gen)
            return
        }

        let engine = AVAudioEngine()
        let player = AVAudioPlayerNode()
        let timePitch = AVAudioUnitTimePitch()
        timePitch.pitch = Tuning.pitchCents
        timePitch.rate = Tuning.playbackRate

        engine.attach(player)
        engine.attach(timePitch)
        engine.connect(player, to: timePitch, format: file.processingFormat)
        engine.connect(timePitch, to: engine.mainMixerNode, format: file.processingFormat)

        timePitch.removeTap(onBus: 0)
        timePitch.installTap(onBus: 0, bufferSize: Tuning.tapBufferSize, format: nil) { [weak self] buffer, _ in
            guard let self = self else { return }
            let level = min(1, VoiceMimicController.rootMeanSquare(of: buffer) * Tuning.levelGain)
            DispatchQueue.main.async {
                guard self.generation == gen, self.state == .playing else { return }
                self.onPlaybackLevel?(level)
            }
        }

        engine.prepare()
        do {
            try engine.start()
        } catch {
            timePitch.removeTap(onBus: 0)
            stopListening()
            return
        }

        playbackEngine = engine
        playerNode = player
        timePitchNode = timePitch

        player.scheduleFile(file, at: nil) { [weak self] in
            DispatchQueue.main.async {
                self?.finishPlayback(generation: gen)
            }
        }
        player.play()
        setState(.playing)
    }

    private func finishPlayback(generation gen: Int) {
        guard generation == gen, state == .playing else { return }
        stopPlaybackEngine()
        beginRecordLoop(generation: gen)
    }

    private func stopPlaybackEngine() {
        timePitchNode?.removeTap(onBus: 0)
        playerNode?.stop()
        playbackEngine?.stop()
        playerNode = nil
        timePitchNode = nil
        playbackEngine = nil
    }

    // MARK: - Helpers

    /// Must only be called on the main thread.
    private func setState(_ newState: TalkState) {
        state = newState
        onStateChange?(newState)
    }

    private func locked<T>(_ body: () -> T) -> T {
        lock.lock()
        defer { lock.unlock() }
        return body()
    }

    private static func rootMeanSquare(of buffer: AVAudioPCMBuffer) -> Float {
        guard let channelData = buffer.floatChannelData, buffer.frameLength > 0 else { return 0 }
        let samples = channelData[0]
        let frameCount = Int(buffer.frameLength)
        var sumOfSquares: Float = 0
        for frame in 0..<frameCount {
            let sample = samples[frame]
            sumOfSquares += sample * sample
        }
        return (sumOfSquares / Float(frameCount)).squareRoot()
    }
}
