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
        // Debounce state lives on the main thread so cancel/re-arm is
        // race-free, and the fired work item encodes on main as well.
        if Thread.isMainThread {
            scheduleSaveOnMain()
        } else {
            DispatchQueue.main.async { [weak self] in
                self?.scheduleSaveOnMain()
            }
        }
    }

    private func scheduleSaveOnMain() {
        pendingSave?.cancel()
        let item = DispatchWorkItem { [weak self] in
            self?.saveNow()
        }
        pendingSave = item
        DispatchQueue.main.asyncAfter(deadline: .now() + 3.0, execute: item)
    }

    func saveNow() {
        // GameState is only mutated on the main thread, so the snapshot
        // must be encoded there; only the file write goes off-main.
        guard let data = encodeStateOnMain() else { return }
        writeToDisk(data)
    }

    private func encodeStateOnMain() -> Data? {
        let encode: () -> Data? = {
            let encoder = JSONEncoder()
            encoder.dateEncodingStrategy = .iso8601
            return try? encoder.encode(GameState.shared)
        }
        if Thread.isMainThread {
            return encode()
        }
        return DispatchQueue.main.sync(execute: encode)
    }

    private func writeToDisk(_ data: Data) {
        guard let url = saveURL else { return }
        queue.async {
            do {
                try FileManager.default.createDirectory(at: url.deletingLastPathComponent(),
                                                        withIntermediateDirectories: true)
                try data.write(to: url, options: .atomic)
            } catch {
                // Saving is best-effort; errors are ignored.
            }
        }
    }
}
