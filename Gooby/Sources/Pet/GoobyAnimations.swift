import SpriteKit
import UIKit

/// SKAction factories for Gooby's canned animations. Every action returns
/// its node to the rest pose when it completes, unless documented otherwise.
enum GoobyAnimations {

    static let breatheKey = "gooby.breathe"
    static let blinkKey = "gooby.blink"
    static let zzzKey = "gooby.zzz"

    private static func rad(_ degrees: CGFloat) -> CGFloat {
        degrees * .pi / 180
    }

    /// Forever body breathing: yScale 1.0 -> 1.035 -> 1.0 over 2.4 s.
    static func breathe() -> SKAction {
        let inhale = SKAction.scaleY(to: 1.035, duration: 1.2)
        inhale.timingMode = .easeInEaseOut
        let exhale = SKAction.scaleY(to: 1.0, duration: 1.2)
        exhale.timingMode = .easeInEaseOut
        return .repeatForever(.sequence([inhale, exhale]))
    }

    /// Eyelid blink: close fully, hold briefly, reopen to the mood's
    /// resting lid scale.
    static func blink(reopenTo restingScale: CGFloat) -> SKAction {
        .sequence([
            .scaleY(to: 1.0, duration: 0.05),
            .wait(forDuration: 0.12),
            .scaleY(to: restingScale, duration: 0.08)
        ])
    }

    /// Classic squash-and-stretch bounce.
    static func bounce() -> SKAction {
        .sequence([
            .scaleX(to: 1.12, y: 0.88, duration: 0.09),
            .scaleX(to: 0.94, y: 1.08, duration: 0.12),
            .scaleX(to: 1.0, y: 1.0, duration: 0.10)
        ])
    }

    /// Sharper, quicker squash used when Gooby is poked.
    static func pokeSquash() -> SKAction {
        .sequence([
            .scaleX(to: 1.16, y: 0.84, duration: 0.06),
            .scaleX(to: 1.0, y: 1.0, duration: 0.14)
        ])
    }

    /// Net-zero relative ear wobble, safe to layer on any base rotation.
    static func earWobble() -> SKAction {
        .sequence([
            .rotate(byAngle: rad(10), duration: 0.06),
            .rotate(byAngle: rad(-20), duration: 0.12),
            .rotate(byAngle: rad(16), duration: 0.10),
            .rotate(byAngle: rad(-6), duration: 0.08)
        ])
    }

    /// Tickle wiggle: rotate -4deg / +4deg / 0, repeated three times.
    static func tickleWiggle() -> SKAction {
        let once = SKAction.sequence([
            .rotate(toAngle: rad(-4), duration: 0.07),
            .rotate(toAngle: rad(4), duration: 0.07),
            .rotate(toAngle: 0, duration: 0.07)
        ])
        return SKAction.repeat(once, count: 3)
    }

    /// Shower shiver: fast +/-2deg rotation, four times, then settle at 0.
    static func shiver() -> SKAction {
        let once = SKAction.sequence([
            .rotate(toAngle: rad(2), duration: 0.04),
            .rotate(toAngle: rad(-2), duration: 0.04)
        ])
        return .sequence([SKAction.repeat(once, count: 4),
                          .rotate(toAngle: 0, duration: 0.04)])
    }

    /// One 0.3 s body pulse matching a single chew cycle.
    static func chewPulse() -> SKAction {
        .sequence([
            .scale(to: 1.05, duration: 0.15),
            .scale(to: 1.0, duration: 0.15)
        ])
    }

    /// Small vertical gulp used when Gooby swallows.
    static func swallowPulse() -> SKAction {
        .sequence([
            .scaleX(to: 0.96, y: 1.06, duration: 0.10),
            .scaleX(to: 1.0, y: 1.0, duration: 0.10)
        ])
    }

    /// Jump 40 pt up and back down with squash & stretch. Uses absolute
    /// values so it is interruption-safe (the node always settles at y == 0).
    static func happyJump() -> SKAction {
        let rise = SKAction.moveTo(y: 40, duration: 0.20)
        rise.timingMode = .easeOut
        let fall = SKAction.moveTo(y: 0, duration: 0.18)
        fall.timingMode = .easeIn
        return .sequence([
            .scaleX(to: 1.10, y: 0.90, duration: 0.08),
            .group([rise, .scaleX(to: 0.92, y: 1.10, duration: 0.20)]),
            .group([fall, .scaleX(to: 1.0, y: 1.0, duration: 0.18)]),
            .scaleX(to: 1.08, y: 0.92, duration: 0.06),
            .scaleX(to: 1.0, y: 1.0, duration: 0.10)
        ])
    }

    /// Drift up by `dy` while fading out, then remove the node from its
    /// parent. Used for emotes and bubble particles.
    static func floatUpAndVanish(dy: CGFloat, duration: TimeInterval) -> SKAction {
        let drift = SKAction.moveBy(x: 0, y: dy, duration: duration)
        drift.timingMode = .easeOut
        return .sequence([
            .group([drift, .fadeOut(withDuration: duration)]),
            .removeFromParent()
        ])
    }
}
