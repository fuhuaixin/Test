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
import com.example.test0.adapter.GoverMesAdapter
import com.example.test0.adapter.LetterBoxAdapter
import com.example.test0.adapter.PubNewsTitAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.GovernmentBean
import com.example.test0.bean.OpenDireMesBean
import com.example.test0.bean.OpenDireTitleBean
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_government.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 *  政务要闻    通知公告    政策法规  政务文件  通用界面
 */
class GovernmentActivity : BaseActivity() {
    var requestQueue: RequestQueue? = null
    var titleAdapter: PubNewsTitAdapter? = null
    var mesAdapter: GoverMesAdapter? = null
    var titleList: MutableList<OpenDireTitleBean> = mutableListOf(
        OpenDireTitleBean("政务要闻", false, R.mipmap.gov_menu_icon_01),
        OpenDireTitleBean("通知公告", false, R.mipmap.gov_menu_icon_02),
        OpenDireTitleBean("政策法规", false, R.mipmap.gov_menu_icon_03),
        OpenDireTitleBean("政务文件", false, R.mipmap.gov_menu_icon_05)
    )

    var mListMes: MutableList<OpenDireMesBean> = mutableListOf()

    var type = ""
    var path = ""
    override fun setLayoutId(): Int {
        return R.layout.activity_government
    }

    override fun initView() {
        requestQueue = Volley.newRequestQueue(this)

        path = intent.getStringExtra("path")
        type = intent.getStringExtra("type")

        Log.e("fhxx", "${path.indexOf("政务服务") != -1}")
        if (path.indexOf("政务服务") != -1) {
            ll_gov_bg.setBackgroundResource(R.drawable.gov_page_bg)
        }
        getMessage()
        tvPath.text = path


        when (type) {
            "政务要闻" -> {
                titleList[0].choose = true
            }
            "通知公告" -> {
                titleList[1].choose = true
            }
            "政策法规" -> {
                titleList[2].choose = true
            }
            "政务文件" -> {
                titleList[3].choose = true
            }
        }

        recycle_title.layoutManager = LinearLayoutManager(this)
        titleAdapter = PubNewsTitAdapter(titleList)
        recycle_title.adapter = titleAdapter


        recycle_mes.layoutManager = LinearLayoutManager(this)
        mesAdapter = GoverMesAdapter(mListMes)
        recycle_mes.adapter = mesAdapter

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
                                tv_mes_title.text = "政务要闻"
                            }
                            1 -> {
                                for (i in 0 until inform.size) {
                                    mListMes.add(
                                        OpenDireMesBean(
                                            inform[i].time,
                                            inform[i].title,
                                            inform[i].url
                                        )
                                    )

                                }
                                tv_mes_title.text = "通知公告"
                            }
                            2 -> {
                                for (i in 0 until policies.size) {
                                    mListMes.add(
                                        OpenDireMesBean(
                                            policies[i].time,
                                            policies[i].title,
                                            policies[i].url
                                        )
                                    )

                                }
                                tv_mes_title.text = "政策法规"
                            }
                            3 -> {
                                for (i in 0 until document.size) {
                                    mListMes.add(
                                        OpenDireMesBean(
                                            document[i].time,
                                            document[i].title,
                                            document[i].url
                                        )
                                    )

                                }
                                tv_mes_title.text = "政务文件"
                            }
                        }
                        mesAdapter!!.notifyDataSetChanged()
                    }
                }
            }

        mesAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.ll_item -> {
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

    var parseObject: GovernmentBean? = null
    var news: MutableList<GovernmentBean.NewsBean> = mutableListOf()
    var document: MutableList<GovernmentBean.DocumentBean> = mutableListOf()
    var inform: MutableList<GovernmentBean.InformBean> = mutableListOf()
    var policies: MutableList<GovernmentBean.PoliciesBean> = mutableListOf()
    fun getMessage() {
        var url = NetConstants.GovernmentUrl
        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, Response.Listener {
            parseObject = JSON.parseObject(it.toString(), GovernmentBean::class.java)
            news = parseObject!!.news //政务要闻
            document = parseObject!!.document //政务文件
            inform = parseObject!!.inform //通知公告
            policies = parseObject!!.policies //政策法规
            mListMes.clear()
            when (type) {
                "政务要闻" -> {
                    for (i in 0 until news.size) {
                        mListMes.add(OpenDireMesBean(news[i].time, news[i].title, news[i].url))
                    }
                    tv_mes_title.text = "政务要闻"
                }
                "通知公告" -> {
                    for (i in 0 until inform.size) {
                        mListMes.add(
                            OpenDireMesBean(
                                inform[i].time,
                                inform[i].title,
                                inform[i].url
                            )
                        )
                    }
                    tv_mes_title.text = "通知公告"
                }
                "政策法规" -> {
                    for (i in 0 until policies.size) {
                        mListMes.add(
                            OpenDireMesBean(
                                policies[i].time,
                                policies[i].title,
                                policies[i].url
                            )
                        )
                    }
                    tv_mes_title.text = "政策法规"
                }
                "政务文件" -> {
                    for (i in 0 until document.size) {
                        mListMes.add(
                            OpenDireMesBean(
                                document[i].time,
                                document[i].title,
                                document[i].url
                            )
                        )
                    }
                    tv_mes_title.text = "政务文件"
                }

            }
            mesAdapter!!.notifyDataSetChanged()
        }, Response.ErrorListener {
            ToastUtils.show("error")
        })
        requestQueue!!.add(jsonObjectRequest)


    }

}