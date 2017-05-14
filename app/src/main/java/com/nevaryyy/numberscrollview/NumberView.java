package com.nevaryyy.numberscrollview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by nevaryyy on 2017/5/14.
 */

public class NumberView extends AppCompatTextView {

    public NumberView(Context context) {
        this(context, null);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setEms(1);
        setText("   0 1 2 3 4 5 6 7 8 9");
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        setTextColor(getResources().getColor(R.color.black));
        setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
