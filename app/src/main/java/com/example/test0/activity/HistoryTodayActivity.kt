package com.example.test0.activity

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.fastjson.JSON
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.test0.R
import com.example.test0.adapter.HistroyTodayAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.HistoryTodayBean
import com.example.test0.utlis.DateUtil
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_history_today.*
import kotlinx.android.synthetic.main.inclue_web_title.*
import java.net.URLEncoder

/**
 * 党史今天
 */
class HistoryTodayActivity : BaseActivity() {

    var requestQueue: RequestQueue? = null

    var histroyAdapter: HistroyTodayAdapter? = null

    var mList: MutableList<HistoryTodayBean.DataBean> = mutableListOf()

    override fun setLayoutId(): Int {
        return R.layout.activity_history_today
    }

    override fun initView() {

        requestQueue = Volley.newRequestQueue(this)

        getMessage()

        tvPath.text = intent.getStringExtra("path")

    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }
    }

    var bean: HistoryTodayBean? = null
    fun getMessage() {
        var mouth = DateUtil.getCurDate("M")
        var day = DateUtil.getCurDate("d")
        var s = "${mouth}月${day}日"

        var url = NetConstants.DangShiUrl + "${URLEncoder.encode(s, "UTF-8")}"

        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, Response.Listener {
            bean = JSON.parseObject(it.toString(), HistoryTodayBean::class.java)
            Log.e("fhxx", bean!!.data[0].content)
            mList = bean!!.data

            recycle_history.layoutManager = LinearLayoutManager(this)
            histroyAdapter = HistroyTodayAdapter(mList)
            recycle_history.adapter = histroyAdapter

        }, Response.ErrorListener {
            ToastUtils.show("error")
        })
        requestQueue!!.add(jsonObjectRequest)


    }
}