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
import com.example.test0.adapter.LetterBoxAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.LetterBoxBean
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_letter_box.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 区长信件
 */
class LetterBoxActivity : BaseActivity() {
    var requestQueue: RequestQueue? = null
    var letterAdapter: LetterBoxAdapter? = null
    var letterBoxBean: LetterBoxBean? = null
    var mList: MutableList<LetterBoxBean.DateBean> = mutableListOf()
    var stringExtra = ""
    override fun setLayoutId(): Int {
        return R.layout.activity_letter_box
    }

    override fun initView() {
        requestQueue = Volley.newRequestQueue(this)

        stringExtra = intent.getStringExtra("path")
        tvPath.text = stringExtra

        getMessage()
    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }
    }


    fun getMessage() {
        var url = NetConstants.LetterBoxUrl
        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, Response.Listener {
            letterBoxBean = JSON.parseObject(it.toString(), LetterBoxBean::class.java)
            mList = letterBoxBean!!.date
            tv_total.text = "共有信件${mList.size}份"
            recycle_let.layoutManager = LinearLayoutManager(this)
            letterAdapter = LetterBoxAdapter(mList)
            recycle_let.adapter = letterAdapter

            letterAdapter!!.onItemChildClickListener =
                BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                    when (view.id) {
                        R.id.ll_item -> {
                            var intent = Intent(this, LetterBoxMesActivity::class.java)
                            intent.putExtra(
                                "letterBean",
                                mList[position]
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