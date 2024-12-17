package com.hongyu.zorelib.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout


/**
 * 骨架屏管理
 */
class SkeletonUtils(private val lifecycleOwner: LifecycleOwner,private var targetView: View?, defaultLayout: Int) : DefaultLifecycleObserver {

    private var shimmerLayout: ShimmerFrameLayout? = null
    private var parentLayout: ViewGroup? = null
    private var isShowingSkeleton = false // 骨架屏状态标识

    init {
        targetView?.let {
            parentLayout = it.parent as ViewGroup
            shimmerLayout = ShimmerFrameLayout(it.context).apply {
                layoutParams = it.layoutParams
            }


            val inflatedView =
                LayoutInflater.from(it.context).inflate(defaultLayout, shimmerLayout, false)
            shimmerLayout?.addView(inflatedView)


            val alphaShimmer = Shimmer.AlphaHighlightBuilder() // 创建Shimmer对象
                .setBaseAlpha(0.9f) // 设置基础透明度为 0.9
                .setHighlightAlpha(0.7f)
                .setIntensity(0.5f) // 设置闪烁强度
                .setDropoff(0.1f)
                .build()
            shimmerLayout?.setShimmer(alphaShimmer)
            show()
            lifecycleOwner.lifecycle.addObserver(this)
        }
    }


    fun show() {
        if (!isShowingSkeleton && shimmerLayout != null && targetView != null) {
            val index = parentLayout?.indexOfChild(targetView)
            parentLayout?.removeView(targetView)
            index?.let { parentLayout?.addView(shimmerLayout, it) }
            shimmerLayout?.startShimmer()
            isShowingSkeleton = true
        }
    }

    fun hide() {
        if (isShowingSkeleton && shimmerLayout != null && targetView != null) {
            shimmerLayout?.stopShimmer()
            val index = parentLayout?.indexOfChild(shimmerLayout)
            parentLayout?.removeView(shimmerLayout)
            index?.let { parentLayout?.addView(targetView, it) }
            isShowingSkeleton = false
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        hide()
        shimmerLayout = null
        targetView = null
        parentLayout = null
    }
}
