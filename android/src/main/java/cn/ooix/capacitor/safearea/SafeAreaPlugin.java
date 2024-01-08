package cn.ooix.capacitor.safearea;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "SafeArea")
public class SafeAreaPlugin extends Plugin {

    private SafeArea implementation;

    @Override
    public void load() {
        implementation = new SafeArea(getActivity());
    }

    @PluginMethod
    public void getSafeArea(PluginCall call) {
        call.resolve(implementation.getSafeArea());
    }

    @PluginMethod
    public void getStatusBarHeight(PluginCall call) {
        call.resolve(implementation.getStatusBarHeight());
    }
}
