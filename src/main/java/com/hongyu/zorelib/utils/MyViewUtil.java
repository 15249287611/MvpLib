package com.hongyu.zorelib.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MyViewUtil {
    /**
     * 根据传递的宽度设置View的比例
     *
     * @param widthRatio  宽占比
     * @param heightRatio 高占比
     */
    public static void setViewRatio(View view, int width, int widthRatio, int heightRatio) {
        if (width <= 0) width = view.getWidth();
        int height = width * heightRatio / widthRatio;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = height;
            layoutParams.width = width;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 根据传递的宽度设置View的比例
     *
     * @param widthRatio  宽占比
     * @param heightRatio 高占比
     */
    public static void setViewRatio(View view, int width, int widthRatio, int heightRatio, int margin) {
        if (width <= 0) width = view.getWidth();
        int height = (width - margin) * heightRatio / widthRatio;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = height;
            layoutParams.width = width;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 根据屏幕宽度设置View比例
     *
     * @param widthRatio  宽占比
     * @param heightRatio 高占比
     */
    public static void setViewRatio(Context context, View view, int widthRatio, int heightRatio) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (wm != null)
            wm.getDefaultDisplay().getMetrics(dm);
        setViewRatio(view, dm.widthPixels, widthRatio, heightRatio);
    }

    /**
     * 设置某个View的margin
     * <p>
     * MarginLayoutParams是继承自ViewGroup.LayoutParmas
     */
    public static ViewGroup.LayoutParams setViewMargin(View view, int left, int top, int right, int bottom) {
        if (view == null) {
            return null;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }
        //设置margin
        marginParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(marginParams);
        return marginParams;
    }

    /**
     * 判断当前view是否在屏幕可见
     */
    public static boolean getLocalVisibleRect(Context context, View view, int offsetY) {
        Point p = new Point();
        ((Activity) context).getWindowManager().getDefaultDisplay().getSize(p);
        int screenWidth = p.x;
        int screenHeight = p.y;
        Rect rect = new Rect(0, 0, screenWidth, screenHeight);
        int[] location = new int[2];
        location[1] = location[1] + DensityTools.dp2px(context, offsetY);
        view.getLocationInWindow(location);
        view.setTag(location[1]);//存储y方向的位置
        return view.getLocalVisibleRect(rect);
    }

}
