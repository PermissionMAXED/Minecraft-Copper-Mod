import Foundation

struct FoodItem: Codable, Equatable, Identifiable {
    let id, name, emoji: String
    let price: Int
    let hungerRestore, happinessBonus: Double
}

struct OutfitItem: Codable, Equatable, Identifiable {
    let id, name, emoji: String
    let price: Int
}

enum Catalog {
    static let foods: [FoodItem] = [
        FoodItem(id: "carrot", name: "Carrot", emoji: "🥕", price: 5, hungerRestore: 15, happinessBonus: 2),
        FoodItem(id: "cabbage", name: "Cabbage", emoji: "🥬", price: 8, hungerRestore: 20, happinessBonus: 3),
        FoodItem(id: "cookie", name: "Cookie", emoji: "🍪", price: 12, hungerRestore: 18, happinessBonus: 8),
        FoodItem(id: "milkshake", name: "Milkshake", emoji: "🥤", price: 18, hungerRestore: 22, happinessBonus: 10),
        FoodItem(id: "cake", name: "Cake", emoji: "🍰", price: 25, hungerRestore: 35, happinessBonus: 15),
        FoodItem(id: "burger", name: "Burger", emoji: "🍔", price: 30, hungerRestore: 45, happinessBonus: 8)
    ]

    static let outfits: [OutfitItem] = [
        OutfitItem(id: "party_hat", name: "Party Hat", emoji: "🎉", price: 50),
        OutfitItem(id: "bow", name: "Bow", emoji: "🎀", price: 75),
        OutfitItem(id: "top_hat", name: "Top Hat", emoji: "🎩", price: 120),
        OutfitItem(id: "crown", name: "Crown", emoji: "👑", price: 300)
    ]

    static func food(id: String) -> FoodItem? {
        return foods.first { $0.id == id }
    }

    static func outfit(id: String) -> OutfitItem? {
        return outfits.first { $0.id == id }
    }
}
