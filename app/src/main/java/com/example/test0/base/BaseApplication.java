package com.example.test0.base;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class BaseApplication extends Application {

    public static Context myApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = getApplicationContext();
        SpeechUtility.createUtility(myApplication, SpeechConstant.APPID +"=580d7027");
    }
}
