package com.hongyu.zorelib.utils.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextViewBold extends AppCompatTextView {

    public TextViewBold(Context context) {
        super(context);
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        setTypeface(null,selected ? Typeface.BOLD : Typeface.NORMAL);
    }
}
