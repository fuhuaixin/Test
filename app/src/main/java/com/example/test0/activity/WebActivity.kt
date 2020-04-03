package com.example.test0.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test0.R
import com.example.test0.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webh5.*

class WebActivity : BaseActivity() {

    var stringExtraUrl: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webh5)
//        stringExtraUrl =

        var mWebSettings: WebSettings = webView.getSettings();
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        mWebSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false。设置true时，会提醒可能造成XSS漏洞
        mWebSettings.setSupportZoom(true);//是否可以缩放，默认true
        mWebSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        mWebSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        mWebSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        mWebSettings.setAppCacheEnabled(true);//是否使用缓存
        mWebSettings.setDomStorageEnabled(true);//开启本地DOM存储
        mWebSettings.setLoadsImagesAutomatically(true); // 加载图片
//        mWebSettings.setMediaPlaybackRequiresUserGesture(false);//播放音频，多媒体需要用户手动？设置为false为可自动播放

//        webView.loadUrl("https://news.sina.cn/zt_d/yiqing0121");
//        webView.loadUrl("https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_3");
        Toast.makeText(this,intent.getStringExtra("url"),Toast.LENGTH_SHORT).show()
        webView.loadUrl(intent.getStringExtra("url"));


        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
//                dialog!!.show()
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                url: String?
            ): Boolean {
                view!!.loadUrl(url);
//                dialog!!.dismiss()
                return true;
            }
        }
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_webh5
    }

    override fun initView() {
//        dialog()




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