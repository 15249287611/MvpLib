package com.hongyu.zorelib.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarUtil {

    /**
     * 修改状态栏颜色
     */
    public static void setStatusBarColor(Activity activity, int colorId) {
        activity.getWindow().setStatusBarColor(activity.getColor(colorId));

    }

    /**
     * 获取状态栏高度
     */
    @SuppressLint({"InternalInsetResource", "DiscouragedApi"})
    public static int getStatusBarHeight(Context context) {
        int result = 20;
        if (context != null) {
            result = DensityTools.dp2px(context, 20);
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier(
                    "status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = resources.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 沉浸式状态栏
     */
    public static void showImmersiveBar(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

        // 设置状态栏沉浸，不隐藏底部导航栏
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}
