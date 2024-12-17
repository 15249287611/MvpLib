package com.hongyu.zorelib.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class RoundedProgressBar extends View {
    private Paint backgroundPaint;
    private Paint progressPaint;
    private int max = 100;
    private int progress = 0;

    public RoundedProgressBar(Context context) {
        super(context);
        init();
    }

    public RoundedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xFFCCCCCC); // 背景颜色

        progressPaint = new Paint();
        progressPaint.setColor(0xFF00FF00); // 进度颜色
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate(); // 通知视图重绘
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int progressBarWidth = (int) (width * (progress / (float) max));

        // 绘制背景
        canvas.drawRoundRect(0, 0, width, height, height / 2, height / 2, backgroundPaint);

        // 绘制进度
        canvas.drawRoundRect(0, 0, progressBarWidth, height, height / 2, height / 2, progressPaint);
    }
}
