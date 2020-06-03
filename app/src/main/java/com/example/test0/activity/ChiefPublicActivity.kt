package com.example.test0.activity

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.fastjson.JSON
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.test0.R
import com.example.test0.adapter.ChiefPubliceMessageAdapter
import com.example.test0.adapter.ChiefPubliceTitleAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.CheifPubMessageBean
import com.example.test0.bean.CheifPubTitleBean
import com.example.test0.bean.GovinfoListBean
import com.example.test0.utlis.ListUtils
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_chief_publice.*
import kotlinx.android.synthetic.main.inclue_web_title.*
import org.json.JSONObject
import java.net.URLEncoder

/**
 * 在线办事
 */
class ChiefPublicActivity : BaseActivity() {
    private val mesList: MutableList<CheifPubMessageBean> = mutableListOf()

    var titleList: MutableList<CheifPubTitleBean> = mutableListOf()
    var titleAdapter: ChiefPubliceTitleAdapter? = null
    var messageAdapter: ChiefPubliceMessageAdapter? = null
    var requestQueue: RequestQueue? = null
    var stringExtra: String = ""

    override fun setLayoutId(): Int {
        return R.layout.activity_chief_publice
    }

    override fun initView() {
        dialog()
        dialog!!.show()
        requestQueue = Volley.newRequestQueue(this)
        stringExtra = intent.getStringExtra("path")
        tvPath.text = stringExtra
        getMessage("", "all")
    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }
    }

    /**
     * 获取在线办事列表
     */

    var categoryList: MutableList<String>? = arrayListOf()
    var getCategoryList: MutableList<String> = arrayListOf()
    fun getMessage(category: String, type: String) {
        var chiefMap = mapOf("category" to URLEncoder.encode(category, "UTF-8"))
        var url: String =
            NetConstants.GovinfoListUrl + "?category=${URLEncoder.encode(category, "UTF-8")}"
//        var url: String = NetConstants.GovinfoListUrl
        Log.e("fhxx", category)
        var jsonObject = JSONObject(chiefMap)
//        jsonObject.put("category",URLEncoder.encode(category,"UTF-8"))
        var jsonObjectRequest =
            JsonObjectRequest(Request.Method.GET, url, Response.Listener { response ->
                //            JsonObjectRequest(Request.Method.POST, url,jsonObject, Response.Listener { response ->
                Log.e("fhxx", response.toString())
                dialog!!.dismiss()
                var listBean = JSON.parseObject(response.toString(), GovinfoListBean::class.java)
                if (listBean.isStatus && listBean.data.size > 0) {
                    var data = listBean.data
                    if (type == "all") {
                        for (i in data.indices) {
                            categoryList!!.add(data[i].category)
                        }
                        getCategoryList = ListUtils.removeDuplicate(categoryList)

                        for (i in getCategoryList.indices) {
                            if (getCategoryList[i] != null) {
                                titleList.add(CheifPubTitleBean(getCategoryList[i], false))
                            }
                        }
                        setTitle()
                        getMessage(getCategoryList[0], "category")
                    } else {
                        mesList.clear()
                        for (i in data.indices) {
                            mesList.add(
                                CheifPubMessageBean(
                                    data[i].title.toString() + "(" + data[i].subject + ")",
                                    false,
                                    data[i].id
                                )
                            )
                        }
                        setMessage()
                    }
                }
            }, Response.ErrorListener {

            })
        requestQueue!!.add(jsonObjectRequest)
    }

    /**
     * 设置title
     */
    fun setTitle() {
        titleList[0].choose = true
        recycle_title.layoutManager = LinearLayoutManager(this)
        titleAdapter = ChiefPubliceTitleAdapter(this, titleList)
        recycle_title.adapter = titleAdapter
        titleAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.ll_item -> {
                        for (i in titleList.indices) {
                            titleList[i].choose = false
                        }
                        titleList[position].choose = true
                        titleAdapter!!.notifyDataSetChanged()
                        getMessage(titleList[position].title, "category")
                    }
                }
            }
    }

    /**
     * 设置二级列表
     */
    fun setMessage() {
        recycle_item.layoutManager = LinearLayoutManager(this)
        messageAdapter = ChiefPubliceMessageAdapter(this, mesList)
        recycle_item.adapter = messageAdapter

        messageAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.ll_item -> {
                        for (i in mesList.indices) {
                            mesList[i].choose = false
                        }
                        mesList[position].choose = true
                        messageAdapter!!.notifyDataSetChanged()
                    }
                    R.id.tv_guide -> {
                        Log.e("fhxx  当前", NetConstants.OnlineWorkBase + mesList[position].id)
                        var intent = Intent(this, WebActivity::class.java)
                        intent.putExtra(
                            "url",
                            NetConstants.OnlineWorkBase + mesList[position].id
                        )
                        intent.putExtra("path", "${stringExtra}/办事指南")
                        startActivity(intent)
                    }
                    R.id.tv_online -> {
                        ToastUtils.show("暂未开发")
                    }
                }
            }
    }

}