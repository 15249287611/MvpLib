package com.hongyu.zorelib.utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

import com.hongyu.zorelib.utils.DensityTools;

/**
 * SpannableStringBuilder 添加图片,让其居中
 */
public class CenteredImageSpan extends ImageSpan {
    private final Context context;

    public CenteredImageSpan(Context context, Drawable drawable) {
        super(drawable);
        this.context = context;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text,
                     int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        Drawable b = getDrawable();
        canvas.save();
        int transY = bottom - b.getBounds().bottom - DensityTools.dp2px(context, 2); // 默认情况下是基线对齐
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }

}
