import Foundation

extension Notification.Name {
    static let gameStateDidChange = Notification.Name("gameStateDidChange")
}

enum GoobyColor: String, Codable, CaseIterable {
    case grey, brown, white, pink
}

final class GameState: Codable {
    static var shared: GameState = GameState()

    var stats: PetStats
    var coins: Int
    var foodInventory: [String: Int]
    var ownedOutfitIDs: Set<String>
    var equippedOutfitID: String?
    var goobyColor: GoobyColor
    var isSleeping: Bool
    var lastUpdated: Date

    init() {
        stats = .initial
        coins = 100
        foodInventory = ["carrot": 3]
        ownedOutfitIDs = []
        equippedOutfitID = nil
        goobyColor = .grey
        isSleeping = false
        lastUpdated = Date()
    }

    func addCoins(_ amount: Int) {
        coins = max(0, coins + amount)
        didMutate()
    }

    @discardableResult
    func spendCoins(_ amount: Int) -> Bool {
        guard coins >= amount else { return false }
        coins -= amount
        didMutate()
        return true
    }

    func feed(_ item: FoodItem) {
        stats[.hunger] += item.hungerRestore
        stats[.happiness] += item.happinessBonus
        if let count = foodInventory[item.id], count > 0 {
            foodInventory[item.id] = count - 1
        }
        didMutate()
    }

    @discardableResult
    func buyFood(_ item: FoodItem) -> Bool {
        guard coins >= item.price else { return false }
        coins -= item.price
        foodInventory[item.id, default: 0] += 1
        didMutate()
        return true
    }

    @discardableResult
    func buyOutfit(_ item: OutfitItem) -> Bool {
        guard !ownedOutfitIDs.contains(item.id), coins >= item.price else { return false }
        coins -= item.price
        ownedOutfitIDs.insert(item.id)
        didMutate()
        return true
    }

    func equipOutfit(id: String?) {
        equippedOutfitID = id
        didMutate()
    }

    func setColor(_ color: GoobyColor) {
        goobyColor = color
        didMutate()
    }

    func clean(amount: Double) {
        stats[.hygiene] += amount
        didMutate()
    }

    func addHappiness(_ amount: Double) {
        stats[.happiness] += amount
        didMutate()
    }

    func addEnergy(_ amount: Double) {
        stats[.energy] += amount
        didMutate()
    }

    func setSleeping(_ sleeping: Bool) {
        isSleeping = sleeping
        didMutate()
    }

    func tickDecay(now: Date) {
        let elapsed = min(now.timeIntervalSince(lastUpdated), 72 * 3600)
        let hours = elapsed / 3600
        if hours <= 0 { return }
        stats.applyDecay(hours: hours, isSleeping: isSleeping)
        if isSleeping && stats.energy >= PetStats.maxValue {
            isSleeping = false
        }
        didMutate()
    }

    private func didMutate() {
        lastUpdated = Date()
        SaveManager.shared.scheduleSave()
        DispatchQueue.main.async {
            NotificationCenter.default.post(name: .gameStateDidChange, object: nil)
        }
    }
}
