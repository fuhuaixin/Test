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
    var strType: String = ""

    override fun setLayoutId(): Int {
        return R.layout.activity_web_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)

    }


    override fun initView() {
        dialog()

        strPath = intent.getStringExtra("path")
        strType = intent.getStringExtra("type")
        tvPath.text = strPath
        when (strType) {
            "通知公告" -> {
                for (i in 0 until 20) {
                    when (i) {
                        0 -> {
                            mData.add(
                                WebInfoBean(
                                    "title$i",
                                    "time$i",
                                    "https://www.toutiao.com/a6812749554228658696/",
                                    1
                                )
                            )
                        }
                        1 -> {
                            mData.add(
                                WebInfoBean(
                                    "title$i",
                                    "time$i",
                                    "https://www.toutiao.com/a6812788999149584904/",
                                    1
                                )
                            )
                        }
                        else -> {
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
                }
            }
            "新闻中心" -> {
                mData.add(
                    WebInfoBean(
                        "第一报道 | 习近平：只有构建人类命运共同体才是人间正道",
                        "2020-05-08",
                        "http://www.xinhuanet.com/world/2020-05/08/c_1210609072.htm",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "中共中央政治局常务委员会召开会议 习近平主持",
                        "2020-05-06",
                        "http://www.xinhuanet.com/politics/leaders/2020-05/06/c_1125949374.htm",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平寄语新时代青年并向全国各族青年致以节日的祝贺和诚挚的问候",
                        "2020-05-03",
                        "http://www.xinhuanet.com/politics/leaders/2020-05/03/c_1125938927.htm",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平同葡萄牙总统德索萨通电话",
                        "2020-05-07",
                        "http://www.xinhuanet.com/politics/leaders/2020-05/07/c_1125954499.htm",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平同乌兹别克斯坦总统米尔济约耶夫通电话",
                        "2020-05-07",
                        "http://www.xinhuanet.com/politics/leaders/2020-05/07/c_1125954497.htm",
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