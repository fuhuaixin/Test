package com.example.test0.activity

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.test0.R
import com.example.test0.adapter.WebInfoAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.bean.WebInfoBean
import kotlinx.android.synthetic.main.activity_web_info.*
import kotlinx.android.synthetic.main.inclue_web_title.*

class WebInfoActivity : BaseActivity() {

    var webInfoAdapter :WebInfoAdapter ?=null
    var mData :MutableList<WebInfoBean> = mutableListOf()
    override fun setLayoutId(): Int {
       return R.layout.activity_web_info
    }

    override fun initView() {
        dialog()


        for (i in 0 until 20){
            if (i===0){
                mData.add(WebInfoBean("title$i","time$i","https://www.toutiao.com/a6812749554228658696/",1))
            }else if (i===1){
                mData.add(WebInfoBean("title$i","time$i","https://www.toutiao.com/a6812788999149584904/",1))
            }else{
                mData.add(WebInfoBean("title$i","time$i","https://www.toutiao.com/a6812788999149584904/",1))
            }

        }

        mData[0].isChoose=2
        web_recycle.layoutManager = LinearLayoutManager(this)
        webInfoAdapter = WebInfoAdapter(mData)
        web_recycle.adapter =webInfoAdapter
        webInfoAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when(view?.id){
                    R.id.rlItem->{
                        for (i in 0 until mData.size){
                            mData[i].isChoose =1
                        }
                        mData[position].isChoose=2

                        webInfoAdapter!!.notifyDataSetChanged()
                    }
                }
            }


        var settings = webViewInfo.settings
        settings.javaScriptEnabled =true
        webViewInfo.loadUrl(mData[0].h5Url)
        webViewInfo.webViewClient =object :WebViewClient(){
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

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }
    }
}