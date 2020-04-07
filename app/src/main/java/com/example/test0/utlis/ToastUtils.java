package com.example.test0.utlis;

import android.content.Context;
import android.widget.Toast;

import com.example.test0.base.BaseApplication;

public class ToastUtils {

    public static void show(CharSequence text) {
        Toast.makeText(BaseApplication.myApplication, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLong( CharSequence text) {
        Toast.makeText(BaseApplication.myApplication, text, Toast.LENGTH_LONG).show();
    }

}
