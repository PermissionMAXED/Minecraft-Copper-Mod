import Foundation

enum StatKind: String, Codable, CaseIterable {
    case hunger, happiness, energy, hygiene
}

enum Mood: String {
    case happy, neutral, sad, hungry, sleepy, dirty
}

struct PetStats: Codable, Equatable {
    var hunger, happiness, energy, hygiene: Double

    static let maxValue: Double = 100
    static let initial = PetStats(hunger: 80, happiness: 80, energy: 80, hygiene: 80)

    subscript(kind: StatKind) -> Double {
        get {
            switch kind {
            case .hunger: return hunger
            case .happiness: return happiness
            case .energy: return energy
            case .hygiene: return hygiene
            }
        }
        set {
            let clamped = min(max(newValue, 0), PetStats.maxValue)
            switch kind {
            case .hunger: hunger = clamped
            case .happiness: happiness = clamped
            case .energy: energy = clamped
            case .hygiene: hygiene = clamped
            }
        }
    }

    mutating func applyDecay(hours: Double, isSleeping: Bool) {
        guard hours > 0 else { return }
        self[.hunger] -= 4.2 * hours
        self[.hygiene] -= 2.1 * hours
        if isSleeping {
            self[.energy] += 12.5 * hours
        } else {
            self[.energy] -= 2.8 * hours
        }
        var happinessRate = 2.8
        if hunger < 30 || hygiene < 30 {
            happinessRate *= 2
        }
        self[.happiness] -= happinessRate * hours
    }

    var mood: Mood {
        if hunger < 30 { return .hungry }
        if hygiene < 30 { return .dirty }
        if happiness < 30 { return .sad }
        if hunger > 70 && happiness > 70 && energy > 70 && hygiene > 70 { return .happy }
        return .neutral
    }
}
