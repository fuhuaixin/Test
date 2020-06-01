package com.example.test0.activity

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.fastjson.JSON
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.test0.R
import com.example.test0.adapter.NormaAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.NormativeBean
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_normative.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 规范性文件
 */
class NormativeActivity :BaseActivity() {

    var requestQueue: RequestQueue? = null
    var normaAdapter :NormaAdapter ?=null
    var mList:MutableList<NormativeBean.DateBean> = mutableListOf()
    var parseObject :NormativeBean ?=null
    var stringExtra =""
    override fun setLayoutId(): Int {
        return R.layout.activity_normative
    }

    override fun initView() {
        stringExtra = intent.getStringExtra("path")
        tvPath.text = stringExtra
        requestQueue = Volley.newRequestQueue(this)

        getMessage()

    }

    override fun initListener() {

        llBack.setOnClickListener {
            finish()
        }

    }
    fun getMessage(){
        var url = NetConstants.NormativeUrl
        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url, Response.Listener {
            parseObject = JSON.parseObject(it.toString(), NormativeBean::class.java)
            mList =parseObject!!.date

            recycle_nor.layoutManager =LinearLayoutManager(this)
            normaAdapter = NormaAdapter(mList)
            recycle_nor.adapter =normaAdapter

            normaAdapter!!.notifyDataSetChanged()


            normaAdapter!!.onItemChildClickListener =BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.ll_item ->{
                        var intent = Intent(this, WebActivity::class.java)
                        intent.putExtra(
                            "url",
                            NetConstants.OpenDireBase+mList[position].url
                        )
                        intent.putExtra("path", "$stringExtra/详情")
                        startActivity(intent)
                    }
                }
            }

        }, Response.ErrorListener {
            ToastUtils.show("error")
        })
        requestQueue!!.add(jsonObjectRequest)


    }
}