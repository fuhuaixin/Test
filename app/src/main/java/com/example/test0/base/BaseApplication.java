package com.example.test0.base;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    public static Context myApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = getApplicationContext();
    }
}
