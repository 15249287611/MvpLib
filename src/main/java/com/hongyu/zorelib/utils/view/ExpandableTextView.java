package com.hongyu.zorelib.utils.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;

import com.hongyu.zorelib.R;
import com.hongyu.zorelib.utils.DensityTools;

/**
 * <pre>
 *     author : 宇
 *     time   : 2020/06/15
 *     desc   :
 * </pre>
 */
public class ExpandableTextView extends androidx.appcompat.widget.AppCompatTextView {
    private static final int DEFAULT_TRIM_LENGTH = 40;
    private static final String ELLIPSIS = "...  [更多]";
    private static final String ELLIPSIS_All = "  [收起]";

    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
        setOnClickListener(v -> {
            trim = !trim;
            setText();
        });
        typedArray.recycle();

    }

    private void setText() {
        super.setText(getDisplayableText(), bufferType);
    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = getTrimmedTextAll(text);
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {
        if (text != null && text.length() > trimLength+1) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text, 0, trimLength + 1).append(ELLIPSIS);
            ColorStateList redColors = ColorStateList.valueOf(getResources().getColor(R.color.color_blue));
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, 0, DensityTools.sp2px(getContext(), 11), redColors, null);
            spannableStringBuilder.setSpan(textAppearanceSpan, spannableStringBuilder.length() - 4, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            return spannableStringBuilder;
        } else {
            return text;
        }
    }

    private CharSequence getTrimmedTextAll(CharSequence text) {
        if (text != null && text.length() > trimLength+1) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text).append(ELLIPSIS_All);
            ColorStateList redColors = ColorStateList.valueOf(getResources().getColor(R.color.color_blue));
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, 0, DensityTools.sp2px(getContext(), 11), redColors, null);
            spannableStringBuilder.setSpan(textAppearanceSpan, spannableStringBuilder.length() - 4, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            return spannableStringBuilder;
        } else {
            return text;
        }
    }

    public CharSequence getOriginalText() {
        return originalText;
    }

    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }

    public int getTrimLength() {
        return trimLength;
    }
}
