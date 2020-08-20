package com.lishuaihua.pictureviewer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class HackyViewPager extends ViewPager {
    private static final String TAG = "HackyViewPager";

    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException var3) {
            Log.e("HackyViewPager", "hacky viewpager error1");
            return false;
        } catch (ArrayIndexOutOfBoundsException var4) {
            Log.e("HackyViewPager", "hacky viewpager error2");
            return false;
        }
    }
}

