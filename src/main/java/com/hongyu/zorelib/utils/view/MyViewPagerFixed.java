package com.hongyu.zorelib.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * <pre>
 *     author : 宇
 *     time   : 2020/10/26
 *     desc   : 图片缩放的 viewpage  //在做多点触控放大缩小，操作自己所绘制的图形时发生这个异常
 * </pre>
 */

public class MyViewPagerFixed extends ViewPager {
    public MyViewPagerFixed(Context context) {
        super(context);
    }

    public MyViewPagerFixed(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
