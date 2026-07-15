import Foundation

final class SaveManager {
    static let shared = SaveManager()

    private let queue = DispatchQueue(label: "com.gooby.save")
    private var pendingSave: DispatchWorkItem?

    private var saveURL: URL? {
        guard let dir = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask).first else {
            return nil
        }
        return dir.appendingPathComponent("gooby_save.json")
    }

    static func bootstrap() {
        guard let url = SaveManager.shared.saveURL else { return }
        do {
            let data = try Data(contentsOf: url)
            let decoder = JSONDecoder()
            decoder.dateDecodingStrategy = .iso8601
            let state = try decoder.decode(GameState.self, from: data)
            GameState.shared = state
            GameState.shared.tickDecay(now: Date())
        } catch {
            // No save (or corrupted save): keep the fresh default GameState.
        }
    }

    func scheduleSave() {
        queue.async { [weak self] in
            guard let self = self else { return }
            self.pendingSave?.cancel()
            let item = DispatchWorkItem { [weak self] in
                self?.saveNow()
            }
            self.pendingSave = item
            self.queue.asyncAfter(deadline: .now() + 0.5, execute: item)
        }
    }

    func saveNow() {
        guard let url = saveURL else { return }
        do {
            try FileManager.default.createDirectory(at: url.deletingLastPathComponent(),
                                                    withIntermediateDirectories: true)
            let encoder = JSONEncoder()
            encoder.dateEncodingStrategy = .iso8601
            let data = try encoder.encode(GameState.shared)
            try data.write(to: url, options: .atomic)
        } catch {
            // Saving is best-effort; errors are ignored.
        }
    }
}
