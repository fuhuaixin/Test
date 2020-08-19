package com.example.test0.utlis;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class CustomViewGroup extends ViewGroup {
    public CustomViewGroup(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //直接消费，不向下传递
        return true;
    }
}
