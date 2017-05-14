package com.nevaryyy.numberscrollview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nevaryyy on 2017/5/14.
 */

public class NumberScrollView extends LinearLayout {

    private int maxDigit;

    private NumberView[] numberViews;

    public NumberScrollView(Context context) {
        this(context, null);
    }

    public NumberScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        maxDigit = 10;
        numberViews = new NumberView[maxDigit];

        for (int i = maxDigit - 1; i >= 0; i --) {
//            if (i % 3 == 0) {
//                TextView textView = new TextView(getContext());
//                textView.setText(",");
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
//                textView.setTextColor(getResources().getColor(R.color.black));
//                addView(textView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//            }

            numberViews[i] = new NumberView(getContext());
            addView(numberViews[i], new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
    }

    private void setNum(List<Integer> digitList) {
        List<Animator> animatorList = new ArrayList<>();

        for (int i = 0; i < digitList.size(); i ++) {
            int toY = (digitList.get(i) + 1) * 125;
            int fromY = numberViews[i].getScrollY();

            ValueAnimator valueAnimator = ValueAnimator.ofInt(fromY, toY);
            valueAnimator.addUpdateListener(new MyAnimationUpdateListener(numberViews[i]));

            animatorList.add(valueAnimator);
        }

        for (int i = digitList.size(); i < numberViews.length; i ++) {
            int toY = 0;
            int fromY = numberViews[i].getScrollY();

            ValueAnimator valueAnimator = ValueAnimator.ofInt(fromY, toY);
            valueAnimator.addUpdateListener(new MyAnimationUpdateListener(numberViews[i]));

            animatorList.add(valueAnimator);
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(800);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(animatorList);

        animatorSet.start();
    }

    public void setNum(int num) {
        if (num < 0) {
            return;
        }

        List<Integer> digitList = new ArrayList<>();

        for (; num > 0; num /= 10) {
            digitList.add(num % 10);
        }

        if (digitList.size() == 0 || digitList.size() > maxDigit) {
            return;
        }

        setNum(digitList);
    }

    private class MyAnimationUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        private View view;

        public MyAnimationUpdateListener(View view) {
            this.view = view;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            view.scrollTo(0, (int) animation.getAnimatedValue());
        }
    }
}
