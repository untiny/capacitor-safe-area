package cn.ooix.capacitor.safearea;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Insets;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.getcapacitor.JSObject;

public class SafeArea {

    private final AppCompatActivity activity;

    public SafeArea(AppCompatActivity activity) {
        this.activity = activity;
    }

    public JSObject getSafeArea() {
        int top = 0, right = 0, bottom = 0, left = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            FrameLayout content = this.activity.getWindow().getDecorView().findViewById(android.R.id.content);
            Insets windowInsets = content.getRootView().getRootWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
            top = windowInsets.top;
            right = windowInsets.right;
            bottom = windowInsets.bottom;
            left = windowInsets.left;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            WindowInsets windowInsets = this.activity.getWindow().getDecorView().getRootWindowInsets();
            top = windowInsets.getStableInsetTop();
            right = windowInsets.getStableInsetRight();
            bottom = windowInsets.getStableInsetBottom();
            left = windowInsets.getStableInsetLeft();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                DisplayCutout displayCutout = windowInsets.getDisplayCutout();
                if (displayCutout != null) {
                    top = Math.max(displayCutout.getSafeInsetTop(), windowInsets.getStableInsetTop());
                    right = Math.max(displayCutout.getSafeInsetRight(), windowInsets.getStableInsetRight());
                    bottom = Math.max(displayCutout.getSafeInsetBottom(), windowInsets.getStableInsetBottom());
                    left = Math.max(displayCutout.getSafeInsetLeft(), windowInsets.getStableInsetLeft());
                }
            }
        }
        JSObject ret = new JSObject();
        ret.put("top", this.dpToPixels(top));
        ret.put("right", this.dpToPixels(right));
        ret.put("bottom", this.dpToPixels(bottom));
        ret.put("left", this.dpToPixels(left));
        return ret;
    }

    public JSObject getStatusBarHeight() {
        JSObject ret = new JSObject();
        ret.put("height", 0);
        Resources resources = this.activity.getResources();
        @SuppressLint({ "InternalInsetResource", "DiscouragedApi" })
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) ret.put("height", this.dpToPixels(resources.getDimensionPixelSize(resourceId)));
        return ret;
    }

    private int dpToPixels(int dp) {
        float density = this.activity.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) Math.round(dp / density);
    }
}
