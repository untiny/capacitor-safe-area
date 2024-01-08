package cn.ooix.capacitor.safearea;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Insets;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.JSObject;

public class SafeArea {

    private final AppCompatActivity activity;
    private final float density;

    public SafeArea(AppCompatActivity activity) {
        this.activity = activity;
        this.density = activity.getApplicationContext().getResources().getDisplayMetrics().density;
    }

    public JSObject getSafeArea() {
        float left = 0, right = 0, top = 0, bottom = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            FrameLayout content = this.activity.getWindow().getDecorView().findViewById(android.R.id.content);
            Insets windowInsets = content.getRootView().getRootWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            left = windowInsets.left;
            right = windowInsets.right;
            top = windowInsets.top;
            bottom = windowInsets.bottom;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowInsets windowInsets = this.activity.getWindow().getDecorView().getRootWindowInsets();
            final DisplayCutout displayCutout = windowInsets.getDisplayCutout();
            if (displayCutout != null) {
                left = displayCutout.getSafeInsetLeft();
                right = displayCutout.getSafeInsetRight();
                top = displayCutout.getSafeInsetTop();
                bottom = displayCutout.getSafeInsetBottom();
            }
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                Insets insets = windowInsets.getSystemWindowInsets();
                left = Math.max(left, insets.left) / this.density;
                right = Math.max(right, insets.right) / this.density;
                top = Math.max(top, insets.top) / this.density;
                bottom = Math.max(bottom, insets.bottom) / this.density;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            WindowInsets windowInsets = this.activity.getWindow().getDecorView().getRootWindowInsets();
            left = windowInsets.getStableInsetLeft() / this.density;
            right = windowInsets.getStableInsetRight() / this.density;
            top = windowInsets.getStableInsetTop() / this.density;
            bottom = windowInsets.getStableInsetBottom() / this.density;
        }
        JSObject ret = new JSObject();
        ret.put("top", top);
        ret.put("bottom", bottom);
        ret.put("left", left);
        ret.put("right", right);

        Log.i("get safe area", ret.toString());
        return ret;
    }

    public JSObject getStatusBarHeight() {
        JSObject ret = new JSObject();
        ret.put("height", 0);
        Resources resources = this.activity.getResources();
        @SuppressLint({ "InternalInsetResource", "DiscouragedApi" })
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) ret.put("height", resources.getDimensionPixelSize(resourceId) / this.density);
        Log.i("get status bar height", ret.toString());
        return ret;
    }
}
