import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(SafeAreaPlugin)
public class SafeAreaPlugin: CAPPlugin {
    private let implementation = SafeArea()

    @objc func getSafeArea(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            call.resolve(self.implementation.getSafeArea())
        }
    }

    @objc func getStatusBarHeight(_ call: CAPPluginCall) {
        DispatchQueue.main.async {
            call.resolve(self.implementation.getStatusBarHeight())
        }
    }
}
