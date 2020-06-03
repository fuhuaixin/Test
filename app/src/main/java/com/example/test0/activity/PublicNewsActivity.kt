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
import com.example.test0.adapter.GoverMesAdapter
import com.example.test0.adapter.PubNewsTitAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.GovernmentBean
import com.example.test0.bean.OpenDireMesBean
import com.example.test0.bean.OpenDireTitleBean
import com.example.test0.bean.PublicNewsBean
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_government.*
import kotlinx.android.synthetic.main.activity_public_news.*
import kotlinx.android.synthetic.main.activity_public_news.recycle_title
import kotlinx.android.synthetic.main.activity_public_news.tv_mes_title
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 新闻中心和政务动态
 */
class PublicNewsActivity : BaseActivity() {

    var requestQueue: RequestQueue? = null
    var titleAdapter: PubNewsTitAdapter? = null
    var mesAdapter: GoverMesAdapter? = null
    var titleList: MutableList<OpenDireTitleBean> = mutableListOf(
        OpenDireTitleBean("新闻中心", false, R.mipmap.publicity_menu_icon_01),
        OpenDireTitleBean("政务动态", false, R.mipmap.publicity_menu_icon_02)
    )
    var mListMes: MutableList<OpenDireMesBean> = mutableListOf()
    var type = 0
    var path = ""
    override fun setLayoutId(): Int {
        return R.layout.activity_public_news
    }

    override fun initView() {

        requestQueue = Volley.newRequestQueue(this)

        path = intent.getStringExtra("path")
        type = intent.getIntExtra("type", 0)
        getMessage()

        tvPath.text = path

        if (type == 1) {
            titleList[0].choose = true
        } else if (type == 2) {
            titleList[1].choose = true
        }

        recycle_title.layoutManager = LinearLayoutManager(this)
        titleAdapter = PubNewsTitAdapter(titleList)
        recycle_title.adapter = titleAdapter

        recycle_pub_mes.layoutManager = LinearLayoutManager(this)
        mesAdapter = GoverMesAdapter(mListMes)
        recycle_pub_mes.adapter = mesAdapter

    }

    override fun initListener() {

        llBack.setOnClickListener {
            finish()
        }

        titleAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.ll_item -> {
                        mListMes.clear()

                        for (i in 0 until titleList.size) {
                            titleList[i].choose = false
                        }

                        titleList[position].choose = true
                        titleAdapter!!.notifyDataSetChanged()

                        when (position) {
                            0 -> {
                                for (i in 0 until news.size) {
                                    mListMes.add(
                                        OpenDireMesBean(
                                            news[i].time,
                                            news[i].title,
                                            news[i].url
                                        )
                                    )

                                }
                                tv_mes_title.text = "新闻中心"
                            }
                            1 -> {
                                for (i in 0 until document.size) {
                                    mListMes.add(
                                        OpenDireMesBean(
                                            document[i].time,
                                            document[i].title,
                                            document[i].url
                                        )
                                    )

                                }
                                tv_mes_title.text = "政务动态"
                            }
                        }
                        mesAdapter!!.notifyDataSetChanged()
                    }
                }
            }

        mesAdapter!!.onItemChildClickListener =BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            when(view.id){
                R.id.ll_item ->{
                    var intent = Intent(this, WebActivity::class.java)
                    intent.putExtra(
                        "url",
                        NetConstants.OpenDireBase + mListMes[position].url
                    )
                    intent.putExtra("path", "$path/详情")
                    startActivity(intent)
                }
            }
        }

    }

    var parseObject: PublicNewsBean? = null
    var news: MutableList<PublicNewsBean.NewsBean> = mutableListOf()
    var document: MutableList<PublicNewsBean.DynamicBean> = mutableListOf()
    fun getMessage() {
        var url = NetConstants.PublicityUrl
        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, Response.Listener {
            parseObject = JSON.parseObject(it.toString(), PublicNewsBean::class.java)
            news = parseObject!!.news //新闻
            document = parseObject!!.dynamic //政务动态
            mListMes.clear()
            when (type) {
                1 -> {
                    for (i in 0 until news.size) {
                        mListMes.add(OpenDireMesBean(news[i].time, news[i].title, news[i].url))
                    }
                    tv_mes_title.text = "新闻中心"
                }
                2 -> {
                    for (i in 0 until document.size) {
                        mListMes.add(
                            OpenDireMesBean(
                                document[i].time,
                                document[i].title,
                                document[i].url
                            )
                        )
                    }
                    tv_mes_title.text = "政务动态"
                }

            }
            mesAdapter!!.notifyDataSetChanged()
        }, Response.ErrorListener {
            ToastUtils.show("error")
        })
        requestQueue!!.add(jsonObjectRequest)


    }
}