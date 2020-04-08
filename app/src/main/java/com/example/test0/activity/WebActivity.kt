package com.example.test0.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.test0.R
import com.example.test0.base.BaseActivity
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_webh5.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * web页展示
 */
class WebActivity : BaseActivity() {

    var stringExtraUrl: String = ""
    var strPath: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webh5)
//        stringExtraUrl =
        strPath = intent.getStringExtra("path")
        tvPath.text = strPath
        dialog()
        var mWebSettings: WebSettings = webView.getSettings();
        mWebSettings.javaScriptCanOpenWindowsAutomatically =
            true;//设置js可以直接打开窗口，如window.open()，默认为false
        mWebSettings.javaScriptEnabled = true;//是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
        mWebSettings.setSupportZoom(true);//是否可以缩放，默认true
        mWebSettings.builtInZoomControls = true;//是否显示缩放按钮，默认false
        mWebSettings.useWideViewPort = true;//设置此属性，可任意比例缩放。大视图模式
        mWebSettings.loadWithOverviewMode = true;//和setUseWideViewPort(true)一起解决网页自适应问题
        mWebSettings.setAppCacheEnabled(true);//是否使用缓存
        mWebSettings.domStorageEnabled = true;//开启本地DOM存储
        mWebSettings.loadsImagesAutomatically = true; // 加载图片
//        mWebSettings.setMediaPlaybackRequiresUserGesture(false);//播放音频，多媒体需要用户手动？设置为false为可自动播放

//        Toast.makeText(this,intent.getStringExtra("url"),Toast.LENGTH_SHORT).show()
        webView.loadUrl(intent.getStringExtra("url"));

        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                dialog!!.show()
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                view!!.loadUrl(url);
                dialog!!.dismiss()
                return true;
            }
        }

        llBack.setOnClickListener {
            ToastUtils.show("点击了返回")
            finish();
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_webh5
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event)

    }

    /*返回页
    ll_friend.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if (webView.canGoBack()) {
                  webView.goBack();//返回上个页面
                  return;
              } else {
                  finish();
              }
          }
      });*/
}