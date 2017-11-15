package com.example.xn069392.safehome.activity.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import static android.text.TextUtils.TruncateAt.MARQUEE;

/**
 * Created by z on 2017/11/15.
 */

public class MarqueeText extends android.support.v7.widget.AppCompatTextView {
    public MarqueeText(Context context) {
        this(context, null);
    }

    public MarqueeText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setSingleLine();
        setEllipsize(MARQUEE);
        setMarqueeRepeatLimit(-1);
        setFocusable(true);
        setFocusableInTouchMode(true);

    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus)
            super.onWindowFocusChanged(hasWindowFocus);
    }
}
