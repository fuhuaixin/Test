package com.example.test0.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.fastjson.JSONObject
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.test0.R
import com.example.test0.adapter.WebInfoAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.bean.MoveiBean
import com.example.test0.bean.WebInfoBean
import kotlinx.android.synthetic.main.activity_web_info.*
import kotlinx.android.synthetic.main.inclue_web_title.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.collections.HashMap


/**
 * 带列表的web页
 */
class WebInfoActivity : BaseActivity() {

    var webInfoAdapter: WebInfoAdapter? = null
    var mData: MutableList<WebInfoBean> = mutableListOf()
    var strPath: String = ""

    override fun setLayoutId(): Int {
        return R.layout.activity_web_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)

        httpPost()
    }

    fun httpPost() {
        // 1 创建一个请求队列
        val requestQueue = Volley.newRequestQueue(this)

        // 2 创建一个post请求
        val url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api"
        var hashMap = HashMap<String, String>()
        hashMap.put("key","value1")
//        map["name"] = "chaychan"
//        map["age"] = "22 years old"
//        map["hobby"] = "programming";
//        var jsonObject = JSONObject(hashMap as Map<String, Any>?) //把map转换为json

   /*     val stringRequest: StringRequest =
            object : StringRequest(
                Method.POST, url,
                Response.Listener<String?> { s -> Log.e("fhxx", s.toString()) },
                Response.ErrorListener { volleyError -> Log.e("fhxx", volleyError.message) }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
//                      map.put("value1","param1");
                    return hashMap
                }
            }*/


        // 3 将post请求添加到队列中
//        requestQueue.add(jsonObjectRequest)
    }

    override fun initView() {
        dialog()

        strPath = intent.getStringExtra("path")
        tvPath.text = strPath

        for (i in 0 until 20) {
            if (i === 0) {
                mData.add(
                    WebInfoBean(
                        "title$i",
                        "time$i",
                        "https://www.toutiao.com/a6812749554228658696/",
                        1
                    )
                )
            } else if (i === 1) {
                mData.add(
                    WebInfoBean(
                        "title$i",
                        "time$i",
                        "https://www.toutiao.com/a6812788999149584904/",
                        1
                    )
                )
            } else {
                mData.add(
                    WebInfoBean(
                        "title$i",
                        "time$i",
                        "https://www.toutiao.com/a6812788999149584904/",
                        1
                    )
                )
            }

        }

        mData[0].isChoose = 2
        web_recycle.layoutManager = LinearLayoutManager(this)
        webInfoAdapter = WebInfoAdapter(mData)
        webInfoAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        webInfoAdapter!!.isFirstOnly(false)
        web_recycle.adapter = webInfoAdapter
        webInfoAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view?.id) {
                    R.id.rlItem -> {
                        EventBus.getDefault().post(mData[position])
                        for (i in 0 until mData.size) {
                            mData[i].isChoose = 1
                        }
                        mData[position].isChoose = 2

                        webInfoAdapter!!.notifyDataSetChanged()
                    }
                }
            }


        var webSettings = webViewInfo.settings
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        webSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放

        webViewInfo.loadUrl(mData[0].h5Url)
        webViewInfo.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                dialog!!.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                dialog!!.dismiss()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun WebInfo(webInfoBean: WebInfoBean) {
        webViewInfo.loadUrl(webInfoBean.h5Url)
    }


    override fun initListener() {

        llBack.setOnClickListener {
            finish();
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }


}