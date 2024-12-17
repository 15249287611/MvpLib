package com.hongyu.zorelib.utils.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hongyu.zorelib.R;

@SuppressLint("ViewConstructor")
public class XCAutoRecyclerView extends RecyclerView {
    private static final String TAG = "XCAutoRecyclerView";
    /**
     * 不需要处理自适应，默认情况
     */
    private static final int XC_AUTO_TYPE_NO = 0;
    /**
     * 宽高全部自适应
     */
    private static final int XC_AUTO_TYPE_ALL = 1;
    /**
     * 宽度自适应
     */
    private static final int XC_AUTO_TYPE_WIDTH = 2;
    /**
     * 高度自适应
     */
    private static final int XC_AUTO_TYPE_HEIGHT = 3;
    /**
     * item的自适应模式
     */
    private int xcAutoItemFlag = 0;
    /**
     * item自适应的宽度
     */
    private int xcAutoItemWidth = 0;
    /**
     * item自适应的最小宽度
     */
    private int xcAutoItemMinWidth = 0;
    /**
     * item自适应的最大宽度
     */
    private float xcAutoItemMaxWidth = 0;
    private Context xcContext;

    public XCAutoRecyclerView(@NonNull Context context) {
        super(context);
        xcContext = context;
    }

    public XCAutoRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);
    }

    public XCAutoRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
    }

    /**
     * 获取xml中属性值
     */
    private void initParams(Context context, AttributeSet attrs) {
        xcContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XCAutoRecyclerView);
        if (typedArray != null) {
            xcAutoItemFlag = typedArray.getInt(R.styleable.XCAutoRecyclerView_xc_auto_item_flag, XC_AUTO_TYPE_NO);
            xcAutoItemWidth = px2dp(typedArray.getDimension(R.styleable.XCAutoRecyclerView_xc_auto_item_width, 0));
            xcAutoItemMinWidth = px2dp(typedArray.getDimension(R.styleable.XCAutoRecyclerView_xc_auto_item_min_width, 0));
            xcAutoItemMaxWidth = px2dp(typedArray.getDimension(R.styleable.XCAutoRecyclerView_xc_auto_item_max_width, 0));
            Log.i("jamie", "xcAutoItemFlag:" + xcAutoItemFlag + "---xcAutoItemWidth:" + xcAutoItemWidth + "---xcAutoItemMinWidth:" + xcAutoItemMinWidth + "---xcAutoItemMaxWidth:" + xcAutoItemMaxWidth);
            typedArray.recycle();
        }
    }
    /**
     * 在测量前，更改对应配置
     */
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        LayoutManager xcLayout = getLayoutManager();
        if (xcLayout instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager xcStaggeredGridLayoutManager = (StaggeredGridLayoutManager) xcLayout;
            setLayoutManager(doAotoItemShow(xcStaggeredGridLayoutManager, widthSpec, heightSpec));
        }
        super.onMeasure(widthSpec, heightSpec);
    }
    /**
     * 计算出显示数量，并进行更改
     */
    private LayoutManager doAotoItemShow(StaggeredGridLayoutManager xcLayout, int widthSpec, int heightSpec) {
        int width = LayoutManager.chooseSize(widthSpec, getPaddingLeft() + getPaddingRight(), ViewCompat.getMinimumWidth(this));
        Log.i("jamie", "width:" + width);
        if (xcAutoItemFlag == XC_AUTO_TYPE_ALL || xcAutoItemFlag == XC_AUTO_TYPE_WIDTH) {
            int itemNumber = width / xcAutoItemWidth;
            xcLayout.setSpanCount(itemNumber == 0 ? 1 : itemNumber);
        }
        return xcLayout;
    }
    /**
     * 将px值转换为dp值
     */
    public int px2dp(float pxValue) {
        final float scale = xcContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}