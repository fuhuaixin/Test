package com.example.test0.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.baidu.lbsapi.BMapManager;
import com.baidu.lbsapi.MKGeneralListener;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 *AppName
 */
public class BaseApplication extends Application {

    public static Context myApplication;
    public BMapManager mBMapManager = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = getApplicationContext();
        SDKInitializer.initialize(myApplication);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        SpeechUtility.createUtility(myApplication, SpeechConstant.APPID +"=580d7027");
        initEngineManager(this);
    }


    public void initEngineManager(Context context) {
        if (mBMapManager == null) {
            mBMapManager = new BMapManager(context);
        }

        if (!mBMapManager.init(new MyGeneralListener())) {
            Toast.makeText(
                    myApplication,
                    "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
        }
    }



    // 常用事件监听，用来处理通常的网络错误，授权验证错误等
    static class MyGeneralListener implements MKGeneralListener {

        @Override
        public void onGetPermissionState(int iError) {
            // 非零值表示key验证未通过
            if (iError != 0) {
                // 授权Key错误：
//                Toast.makeText(
//                        myApplication,
//                        "请在AndoridManifest.xml中输入正确的授权Key,并检查您的网络连接是否正常！error: "
//                                + iError, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(
                        myApplication, "key认证成功",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
