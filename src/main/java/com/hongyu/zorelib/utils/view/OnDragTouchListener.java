package com.hongyu.zorelib.utils.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * View拖动+点击事件
 */
public class OnDragTouchListener implements View.OnTouchListener {
    private int mParentWidth, mParentHeight; // 父视图宽高
//    private int mScreenWidth, mScreenHeight;//屏幕宽高
    private float mOriginalX, mOriginalY;//手指按下时的初始位置
    private float mDistanceX, mDistanceY;//记录手指与view的左上角的距离
    private int left, top, right, bottom;
    private final OnDraggableClickListener mListener;
    private final boolean hasAutoPullToBorder;//标记是否开启自动拉到边缘功能
    private boolean isHasAutoRight;//标记是否自动靠右

    public OnDragTouchListener(boolean isAutoPullToBorder, OnDraggableClickListener listener) {
        this.hasAutoPullToBorder = isAutoPullToBorder;
        this.mListener = listener;
    }

    public OnDragTouchListener(boolean isAutoPullToBorder, boolean isHasAutoRight, OnDraggableClickListener listener) {
        this.hasAutoPullToBorder = isAutoPullToBorder;
        this.isHasAutoRight = isHasAutoRight;
        this.mListener = listener;
    }

    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            mParentWidth = parent.getWidth();
            mParentHeight = parent.getHeight();
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                mScreenWidth = v.getResources().getDisplayMetrics().widthPixels;
//                mScreenHeight = v.getResources().getDisplayMetrics().heightPixels;
                mOriginalX = event.getRawX();
                mOriginalY = event.getRawY();
                mDistanceX = event.getRawX() - v.getLeft();
                mDistanceY = event.getRawY() - v.getTop();
                break;
            case MotionEvent.ACTION_MOVE:
                left = (int) (event.getRawX() - mDistanceX);
                top = (int) (event.getRawY() - mDistanceY);
                right = left + v.getWidth();
                bottom = top + v.getHeight();
                if (left < 0) {
                    left = 0;
                    right = left + v.getWidth();
                }
                if (top < 0) {
                    top = 0;
                    bottom = top + v.getHeight();
                }
                if (right > mParentWidth) {
                    right = mParentWidth;
                    left = right - v.getWidth();
                }
                if (bottom > mParentHeight) {
                    bottom = mParentHeight;
                    top = bottom - v.getHeight();
                }
                v.layout(left, top, right, bottom);
                break;
            case MotionEvent.ACTION_UP:
                //如果移动距离过小，则判定为点击
                if (mListener != null
                        && Math.abs(event.getRawX() - mOriginalX) < 10
                        && Math.abs(event.getRawY() - mOriginalY) < 10) {
                    mListener.onClick(v);
                } else {
                    //在拖动过按钮后，如果其他view刷新导致重绘，会让按钮重回原点，所以需要更改布局参数
                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                    startAutoPull(v, lp);
                }
                //消除警告
                v.callOnClick();
                break;
        }
        return true;
    }

    /**
     * 开启自动拖拽
     *
     * @param v  拉动控件
     * @param lp 控件布局参数
     */
    private void startAutoPull(final View v, final ViewGroup.MarginLayoutParams lp) {
        if (!hasAutoPullToBorder) {
            v.layout(left, top, right, bottom);
            lp.setMargins(left, top, 0, 0);
            v.setLayoutParams(lp);
            /*if (mListener != null) {
                mListener.onDragged(v, left, top);
            }*/
            return;
        }
        //当用户拖拽完后，让控件根据远近距离回到最近的边缘
        float end = 0;
        if (isHasAutoRight) {//判断是否只靠右
            end = mParentWidth - v.getWidth();
        } else {
            if ((left + v.getWidth() / 2) >= mParentWidth / 2) {
                end = mParentWidth - v.getWidth();
            }
        }
        ValueAnimator animator = ValueAnimator.ofFloat(left, end);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(animation -> {
            int leftMargin = (int) ((float) animation.getAnimatedValue());
            v.layout(leftMargin, top, right, bottom);
            lp.setMargins(leftMargin, top, 0, 0);
            v.setLayoutParams(lp);
        });
        final float finalEnd = end;
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
              /*  if (mListener != null) {
                    mListener.onDragged(v, (int) finalEnd, top);
                }*/
            }
        });
        animator.setDuration(400);
        animator.start();
    }

    /**
     * 控件拖拽监听器
     */

    public interface OnDraggableClickListener {
        /**
         * 当控件拖拽完后回调
         *
         * @param v    拖拽控件
         * @param left 控件左边距
         * @param top  控件右边距
        void onDragged(View v, int left, int top){};
         */

        /**
         * 当可拖拽控件被点击时回调
         *
         * @param v 拖拽控件
         */
        void onClick(View v);
    }
}
