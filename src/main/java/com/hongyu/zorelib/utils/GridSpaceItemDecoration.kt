package com.hongyu.zorelib.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * 给九宫格设置间隔并填充颜色
 *
 */
class GridSpaceItemDecoration(
    context: Context?,
    private val spanCount: Int,  //分组数量
    sizeDp: Float,          // 分割线宽度
    colorResId: Int = -1         // 分割线颜色   -1表示透明
) : RecyclerView.ItemDecoration() {
    private var paint: Paint? = null
    private var spacing = 1 // 间隔大小，单位: px

    init {
        context?.let {
            this.spacing = DensityTools.dp2px(it, sizeDp)
            if (colorResId > 0) {
                paint = Paint()
                paint?.color = ContextCompat.getColor(it, colorResId) // 间隔颜色
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val layoutManager = parent.layoutManager as? GridLayoutManager
            ?: throw IllegalArgumentException("LayoutManager must be GridLayoutManager")

        val spanSize = layoutManager.spanSizeLookup.getSpanSize(position) // 当前项目占用的列数
        val spanIndex = layoutManager.spanSizeLookup.getSpanIndex(position, layoutManager.spanCount) // 当前项目的起始列索引
        val totalSpanCount = layoutManager.spanCount // 总列数

        // 左间距：起始列索引 * 单位间距 / 总列数
        outRect.left = spacing * spanIndex / totalSpanCount
        // 右间距：单位间距 - （当前项目的结束列索引 * 单位间距 / 总列数）
        outRect.right = spacing * (totalSpanCount - spanIndex - spanSize) / totalSpanCount

        if (position >= totalSpanCount ||(position > 0 && spanSize >= totalSpanCount)) {
            outRect.top = spacing
        }
    }


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        paint?.let { paint ->
            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val position = parent.getChildAdapterPosition(child)
                val column = position % spanCount

                // 绘制垂直间隔
                if (column < spanCount - 1) { // 不是最后一列
                    val left = child.right + params.rightMargin
                    val right = left + spacing
                    c.drawRect(
                        left.toFloat(),
                        child.top.toFloat(),
                        right.toFloat(),
                        child.bottom.toFloat(),
                        paint
                    )
                }

                // 绘制水平间隔
                val totalItemCount = state.itemCount
                val currentRow = position / spanCount + 1
                val totalRow = (totalItemCount + spanCount - 1) / spanCount
                if (currentRow < totalRow) { // 不是最后一行
                    val top = child.bottom + params.bottomMargin
                    val bottom = top + spacing
                    c.drawRect(
                        child.left.toFloat(),
                        top.toFloat(),
                        child.right.toFloat(),
                        bottom.toFloat(),
                        paint
                    )
                }
            }
        }
    }
}
