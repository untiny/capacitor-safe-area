import Foundation

@objc public class SafeArea: NSObject {
    @objc public func getSafeArea() -> [String:Float] {
        let window: UIWindow?
        if #available(iOS 13.0, *) {
            window = UIApplication.shared.windows.first
        } else {
            window = UIApplication.shared.keyWindow
        }
        let safeAreaInsets = window?.safeAreaInsets
        return [
          "top": safeAreaInsets.top ?? 0,
          "right": safeAreaInsets.right ?? 0,
          "bottom": safeAreaInsets.bottom ?? 0,
          "left": safeAreaInsets.left ?? 0
        ]
    }

    @objc public func getStatusBarHeight() -> [String:Float] {
        if #available(iOS 13.0, *) {
            let scenes = UIApplication.shared.connectedScenes
            let windowScene = scenes.first as? UIWindowScene
            let window = windowScene?.windows.first
            return [
              "height": window?.windowScene?.statusBarManager?.statusBarFrame.height ?? 0
            ]
        }
        return [
            "height": UIApplication.shared.statusBarFrame.height
        ]
    }
}