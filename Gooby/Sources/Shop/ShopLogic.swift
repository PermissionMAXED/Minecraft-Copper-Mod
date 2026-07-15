import Foundation

enum ShopTab: CaseIterable {
    case food, outfits, colors
}

struct ShopEntry {
    enum State {
        case buyable, unaffordable, owned, equipped
    }

    let id: String
    let name: String
    let emoji: String
    let price: Int
    let state: State
}

enum ShopLogic {
    static func entries(for tab: ShopTab) -> [ShopEntry] {
        let gameState = GameState.shared
        switch tab {
        case .food:
            return Catalog.foods.map { item in
                ShopEntry(id: item.id,
                          name: item.name,
                          emoji: item.emoji,
                          price: item.price,
                          state: gameState.coins >= item.price ? .buyable : .unaffordable)
            }
        case .outfits:
            return Catalog.outfits.map { item in
                let state: ShopEntry.State
                if gameState.equippedOutfitID == item.id {
                    state = .equipped
                } else if gameState.ownedOutfitIDs.contains(item.id) {
                    state = .owned
                } else if gameState.coins >= item.price {
                    state = .buyable
                } else {
                    state = .unaffordable
                }
                return ShopEntry(id: item.id,
                                 name: item.name,
                                 emoji: item.emoji,
                                 price: item.price,
                                 state: state)
            }
        case .colors:
            return GoobyColor.allCases.map { color in
                ShopEntry(id: color.rawValue,
                          name: color.rawValue.capitalized,
                          emoji: "🎨",
                          price: 0,
                          state: gameState.goobyColor == color ? .equipped : .owned)
            }
        }
    }

    @discardableResult
    static func performAction(entryID: String, tab: ShopTab) -> Bool {
        let gameState = GameState.shared
        switch tab {
        case .food:
            guard let item = Catalog.food(id: entryID) else { return false }
            return gameState.buyFood(item)
        case .outfits:
            guard let item = Catalog.outfit(id: entryID) else { return false }
            guard gameState.ownedOutfitIDs.contains(item.id) else {
                return gameState.buyOutfit(item)
            }
            if gameState.equippedOutfitID == item.id {
                gameState.equipOutfit(id: nil)
            } else {
                gameState.equipOutfit(id: item.id)
            }
            return true
        case .colors:
            guard let color = GoobyColor(rawValue: entryID) else { return false }
            gameState.setColor(color)
            return true
        }
    }
}
