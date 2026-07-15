import SpriteKit
import UIKit

/// Static geometry constants, CGPath factories and SKShapeNode builders for
/// the fat-rabbit Gooby character. Everything is vector-drawn so the pet
/// needs no bitmap assets. Origin is the body centre; the assembled
/// character has an intrinsic footprint of roughly 260x300 points.
enum GoobyParts {

    // MARK: - Palette

    static let outlineColor = UIColor(red: 0.23, green: 0.19, blue: 0.21, alpha: 1)
    static let outlineWidth: CGFloat = 3
    static let pupilColor = UIColor(red: 0.12, green: 0.10, blue: 0.12, alpha: 1)
    static let mouthFillColor = UIColor(red: 0.38, green: 0.18, blue: 0.20, alpha: 1)
    static let innerEarColor = UIColor(red: 0.97, green: 0.69, blue: 0.77, alpha: 1)
    static let noseColor = UIColor(red: 0.94, green: 0.47, blue: 0.60, alpha: 1)

    // MARK: - Layout (right-hand side; x is mirrored for left parts)

    static let bodySize = CGSize(width: 220, height: 230)
    static let bellySize = CGSize(width: 140, height: 150)
    static let bellyCenter = CGPoint(x: 0, y: -50)
    static let earSize = CGSize(width: 50, height: 120)
    static let earBase = CGPoint(x: 50, y: 80)
    /// Slight outward lean of the ears at rest (radians).
    static let normalEarAngle: CGFloat = 0.14
    /// Strong outward droop used for sad/sleepy moods (radians).
    static let droopEarAngle: CGFloat = 0.55
    static let eyeSize = CGSize(width: 34, height: 42)
    static let eyeCenter = CGPoint(x: 36, y: 62)
    static let noseCenter = CGPoint(x: 0, y: 38)
    static let mouthCenter = CGPoint(x: 0, y: 18)
    /// Full size of the open (talking) mouth ellipse at yScale 1.
    static let openMouthSize = CGSize(width: 26, height: 40)
    static let footCenter = CGPoint(x: 46, y: -110)
    static let footSize = CGSize(width: 56, height: 26)
    static let tailCenter = CGPoint(x: -106, y: -46)
    static let tailRadius: CGFloat = 22
    static let cheekCenter = CGPoint(x: 92, y: 22)
    static let cheekRadius: CGFloat = 20
    static let hatAnchor = CGPoint(x: 0, y: 112)

    // MARK: - Body part builders

    private static func shapeNode(path: CGPath,
                                  fill: UIColor,
                                  stroke: UIColor = outlineColor,
                                  lineWidth: CGFloat = outlineWidth) -> SKShapeNode {
        let node = SKShapeNode(path: path)
        node.fillColor = fill
        node.strokeColor = stroke
        node.lineWidth = lineWidth
        node.isAntialiased = true
        return node
    }

    private static func centeredEllipsePath(size: CGSize) -> CGPath {
        let rect = CGRect(x: -size.width / 2,
                          y: -size.height / 2,
                          width: size.width,
                          height: size.height)
        return CGPath(ellipseIn: rect, transform: nil)
    }

    static func bodyNode(color: GoobyColor) -> SKShapeNode {
        shapeNode(path: centeredEllipsePath(size: bodySize), fill: Theme.bodyColor(color))
    }

    static func bellyNode(color: GoobyColor) -> SKShapeNode {
        shapeNode(path: centeredEllipsePath(size: bellySize),
                  fill: Theme.bellyColor(color),
                  stroke: .clear,
                  lineWidth: 0)
    }

    static func cheekNode(color: GoobyColor) -> SKShapeNode {
        let path = centeredEllipsePath(size: CGSize(width: cheekRadius * 2, height: cheekRadius * 2))
        return shapeNode(path: path, fill: Theme.bodyColor(color), lineWidth: 2)
    }

    static func noseNode() -> SKShapeNode {
        let path = centeredEllipsePath(size: CGSize(width: 14, height: 14))
        return shapeNode(path: path, fill: noseColor, lineWidth: 2)
    }

    /// Tall rounded ear drawn with its base at the node origin so rotation
    /// pivots naturally. Contains a pink inner-ear inset as a child.
    static func earNode(color: GoobyColor) -> SKShapeNode {
        let outerRect = CGRect(x: -earSize.width / 2, y: 0,
                               width: earSize.width, height: earSize.height)
        let ear = shapeNode(path: UIBezierPath(roundedRect: outerRect,
                                               cornerRadius: earSize.width / 2).cgPath,
                            fill: Theme.bodyColor(color))
        let innerRect = CGRect(x: -13, y: 22, width: 26, height: 76)
        let inner = shapeNode(path: UIBezierPath(roundedRect: innerRect, cornerRadius: 13).cgPath,
                              fill: innerEarColor,
                              stroke: .clear,
                              lineWidth: 0)
        inner.zPosition = 1
        ear.addChild(inner)
        return ear
    }

    /// White eyeball with a black pupil child.
    static func eyeNode() -> SKShapeNode {
        let eye = shapeNode(path: centeredEllipsePath(size: eyeSize), fill: .white, lineWidth: 2)
        let pupil = shapeNode(path: centeredEllipsePath(size: CGSize(width: 16, height: 16)),
                              fill: pupilColor,
                              stroke: .clear,
                              lineWidth: 0)
        pupil.position = CGPoint(x: 0, y: -3)
        pupil.zPosition = 1
        eye.addChild(pupil)
        return eye
    }

    /// Body-coloured lid that hangs below its origin; position it at the top
    /// of the eye and animate yScale (0.05 open ... 1.0 closed) to blink.
    static func eyelidNode(color: GoobyColor) -> SKShapeNode {
        let rect = CGRect(x: -(eyeSize.width / 2 + 2),
                          y: -(eyeSize.height + 4),
                          width: eyeSize.width + 4,
                          height: eyeSize.height + 4)
        return shapeNode(path: CGPath(ellipseIn: rect, transform: nil),
                         fill: Theme.bodyColor(color),
                         stroke: .clear,
                         lineWidth: 0)
    }

    /// Empty mouth shape; GoobyNode swaps its path between the mouth path
    /// factories below depending on mood/talking state.
    static func mouthNode() -> SKShapeNode {
        let mouth = SKShapeNode()
        mouth.lineWidth = outlineWidth
        mouth.lineCap = .round
        mouth.lineJoin = .round
        mouth.strokeColor = outlineColor
        mouth.fillColor = .clear
        mouth.isAntialiased = true
        return mouth
    }

    static func footNode(color: GoobyColor) -> SKShapeNode {
        shapeNode(path: centeredEllipsePath(size: footSize), fill: Theme.bodyColor(color))
    }

    static func tailNode(color: GoobyColor) -> SKShapeNode {
        let path = centeredEllipsePath(size: CGSize(width: tailRadius * 2, height: tailRadius * 2))
        return shapeNode(path: path, fill: Theme.bellyColor(color))
    }

    // MARK: - Mouth paths (relative to the mouth node at mouthCenter)

    static func smileMouthPath() -> CGPath {
        let path = CGMutablePath()
        path.move(to: CGPoint(x: -16, y: 4))
        path.addQuadCurve(to: CGPoint(x: 16, y: 4), control: CGPoint(x: 0, y: -12))
        return path
    }

    static func flatMouthPath() -> CGPath {
        let path = CGMutablePath()
        path.move(to: CGPoint(x: -13, y: 0))
        path.addLine(to: CGPoint(x: 13, y: 0))
        return path
    }

    static func surprisedMouthPath() -> CGPath {
        CGPath(ellipseIn: CGRect(x: -8, y: -9, width: 16, height: 18), transform: nil)
    }

    /// Open-mouth ellipse anchored so its top edge stays near the node
    /// origin: scaling yScale down makes the jaw close upward.
    static func openMouthPath() -> CGPath {
        let rect = CGRect(x: -openMouthSize.width / 2,
                          y: -(openMouthSize.height - 4),
                          width: openMouthSize.width,
                          height: openMouthSize.height)
        return CGPath(ellipseIn: rect, transform: nil)
    }

    // MARK: - Hats (base of the hat sits at the node origin)

    static func hatNode(outfitID: String) -> SKNode? {
        switch outfitID {
        case "party_hat": return partyHat()
        case "top_hat": return topHat()
        case "crown": return crown()
        case "bow": return bow()
        default: return nil
        }
    }

    private static func partyHat() -> SKNode {
        let hat = SKNode()
        let cone = CGMutablePath()
        cone.move(to: CGPoint(x: -30, y: 0))
        cone.addLine(to: CGPoint(x: 30, y: 0))
        cone.addLine(to: CGPoint(x: 0, y: 64))
        cone.closeSubpath()
        hat.addChild(shapeNode(path: cone,
                               fill: UIColor(red: 0.55, green: 0.44, blue: 0.94, alpha: 1)))
        let pompom = shapeNode(path: centeredEllipsePath(size: CGSize(width: 18, height: 18)),
                               fill: UIColor(red: 1.00, green: 0.82, blue: 0.25, alpha: 1),
                               lineWidth: 2)
        pompom.position = CGPoint(x: 0, y: 66)
        pompom.zPosition = 1
        hat.addChild(pompom)
        return hat
    }

    private static func topHat() -> SKNode {
        let hat = SKNode()
        let felt = UIColor(white: 0.16, alpha: 1)
        let cylinder = shapeNode(path: UIBezierPath(roundedRect: CGRect(x: -24, y: 6, width: 48, height: 54),
                                                    cornerRadius: 6).cgPath,
                                 fill: felt,
                                 lineWidth: 2)
        hat.addChild(cylinder)
        let band = SKShapeNode(rect: CGRect(x: -24, y: 12, width: 48, height: 10))
        band.fillColor = UIColor(red: 0.86, green: 0.24, blue: 0.30, alpha: 1)
        band.strokeColor = .clear
        band.zPosition = 1
        hat.addChild(band)
        let brim = shapeNode(path: UIBezierPath(roundedRect: CGRect(x: -38, y: -2, width: 76, height: 12),
                                                cornerRadius: 5).cgPath,
                             fill: felt,
                             lineWidth: 2)
        brim.zPosition = 2
        hat.addChild(brim)
        return hat
    }

    private static func crown() -> SKNode {
        let path = CGMutablePath()
        path.move(to: CGPoint(x: -32, y: 0))
        path.addLine(to: CGPoint(x: -32, y: 16))
        path.addLine(to: CGPoint(x: -21, y: 36))
        path.addLine(to: CGPoint(x: -11, y: 16))
        path.addLine(to: CGPoint(x: 0, y: 36))
        path.addLine(to: CGPoint(x: 11, y: 16))
        path.addLine(to: CGPoint(x: 21, y: 36))
        path.addLine(to: CGPoint(x: 32, y: 16))
        path.addLine(to: CGPoint(x: 32, y: 0))
        path.closeSubpath()
        return shapeNode(path: path,
                         fill: UIColor(red: 1.00, green: 0.80, blue: 0.20, alpha: 1),
                         lineWidth: 2)
    }

    private static func bow() -> SKNode {
        let bow = SKNode()
        let pink = UIColor(red: 0.96, green: 0.55, blue: 0.70, alpha: 1)
        let leftLoop = CGMutablePath()
        leftLoop.move(to: CGPoint(x: -4, y: 8))
        leftLoop.addLine(to: CGPoint(x: -30, y: 23))
        leftLoop.addLine(to: CGPoint(x: -30, y: -7))
        leftLoop.closeSubpath()
        bow.addChild(shapeNode(path: leftLoop, fill: pink, lineWidth: 2))
        let rightLoop = CGMutablePath()
        rightLoop.move(to: CGPoint(x: 4, y: 8))
        rightLoop.addLine(to: CGPoint(x: 30, y: 23))
        rightLoop.addLine(to: CGPoint(x: 30, y: -7))
        rightLoop.closeSubpath()
        bow.addChild(shapeNode(path: rightLoop, fill: pink, lineWidth: 2))
        let knot = shapeNode(path: centeredEllipsePath(size: CGSize(width: 16, height: 16)),
                             fill: pink,
                             lineWidth: 2)
        knot.position = CGPoint(x: 0, y: 8)
        knot.zPosition = 1
        bow.addChild(knot)
        return bow
    }
}
