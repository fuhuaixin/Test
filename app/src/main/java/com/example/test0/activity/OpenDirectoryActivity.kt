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
import com.example.test0.adapter.OpenDireMesAdapter
import com.example.test0.adapter.OpenDireTitAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.OpenDireBean
import com.example.test0.bean.OpenDireMesBean
import com.example.test0.bean.OpenDireTitleBean
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_open_derectory.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 公开目录
 */
class OpenDirectoryActivity  :BaseActivity(){
    var requestQueue: RequestQueue? = null
    var openTitleAdapter : OpenDireTitAdapter ?=null
    var openMesAdapter :OpenDireMesAdapter ?=null

    var openDireBean : OpenDireBean ?=null
    var stringExtra =""
    var mListTitle :MutableList<OpenDireTitleBean> = mutableListOf()
    var mListMes :MutableList<OpenDireMesBean> = mutableListOf()
    var listTitle = mutableListOf("组织结构","综合政务","国民经济管理、国有资产监督","财政、金融、审计","国土资源、能源","农业、畜牧业、渔业、林业、水利、粮食",
        "工业、交通","商贸、海关、旅游","市场监管、安全生产监管","城乡建设、环境保护","科技、教育","文化、广电、新闻出版","卫生、体育","人口与计划生育、妇女儿童工作",
        "劳动、人事、监察","民政、扶贫、救灾","对外事务","港澳台侨工作","公安、司法")
    override fun setLayoutId(): Int {
        return R.layout.activity_open_derectory
    }

    override fun initView() {
        stringExtra = intent.getStringExtra("path")
        tvPath.text = stringExtra
        requestQueue = Volley.newRequestQueue(this)
        getMessage()
        for (i in 0 until listTitle.size){
            mListTitle.add(OpenDireTitleBean(listTitle[i],false))
        }
        mListTitle[0].choose=true
        openTitleAdapter =OpenDireTitAdapter(mListTitle)
        recycle_title.layoutManager =LinearLayoutManager(this)
        recycle_title.adapter =openTitleAdapter


        openMesAdapter = OpenDireMesAdapter(mListMes)
        recycle_mes.layoutManager =LinearLayoutManager(this)
        recycle_mes.adapter =openMesAdapter

    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }

        openTitleAdapter!!.onItemChildClickListener =BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            when(view.id){
                R.id.ll_item ->{
                    for (i in 0 until mListTitle.size){
                        mListTitle[i].choose =false
                    }
                    mListTitle[position].choose =true
                    openTitleAdapter!!.notifyDataSetChanged()
                    mListMes.clear()
                    when(position){
                        0 ->{
                            tv_mes_title.text ="组织结构"
                            for (i in 0 until openDireBean!!.zzjg1.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.zzjg1[i].publish,openDireBean!!.zzjg1[i].sendDate,openDireBean!!.zzjg1[i].title,openDireBean!!.zzjg1[i].url))
                            }

                        }
                        1 ->{
                            tv_mes_title.text ="综合政务"
                            for (i in 0 until openDireBean!!.zhzw2.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.zhzw2[i].publish,openDireBean!!.zhzw2[i].sendDate,openDireBean!!.zhzw2[i].title,openDireBean!!.zhzw2[i].url))
                            }
                        }
                        2 ->{
                            tv_mes_title.text ="国民经济管理、国有资产监督"
                            for (i in 0 until openDireBean!!.gmjj3.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.gmjj3[i].publish,openDireBean!!.gmjj3[i].sendDate,openDireBean!!.gmjj3[i].title,openDireBean!!.gmjj3[i].url))
                            }
                        }
                        3 ->{
                            tv_mes_title.text ="财政、金融、审计"
                            for (i in 0 until openDireBean!!.czjr4.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.czjr4[i].publish,openDireBean!!.czjr4[i].sendDate,openDireBean!!.czjr4[i].title,openDireBean!!.czjr4[i].url))
                            }
                        }
                        4 ->{
                            tv_mes_title.text ="国土资源、能源"
                            for (i in 0 until openDireBean!!.gtzy5.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.gtzy5[i].publish,openDireBean!!.gtzy5[i].sendDate,openDireBean!!.gtzy5[i].title,openDireBean!!.gtzy5[i].url))
                            }
                        }
                        5 ->{
                            tv_mes_title.text ="农业、畜牧业、渔业、林业、水利、粮食"
                            for (i in 0 until openDireBean!!.nxyl6.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.nxyl6[i].publish,openDireBean!!.nxyl6[i].sendDate,openDireBean!!.nxyl6[i].title,openDireBean!!.nxyl6[i].url))
                            }
                        }
                        6 ->{
                            tv_mes_title.text ="工业、交通"
                            for (i in 0 until openDireBean!!.gyjt7.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.gyjt7[i].publish,openDireBean!!.gyjt7[i].sendDate,openDireBean!!.gyjt7[i].title,openDireBean!!.gyjt7[i].url))
                            }
                        }
                        7 ->{
                            tv_mes_title.text ="商贸、海关、旅游"
                            for (i in 0 until openDireBean!!.smhg8.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.smhg8[i].publish,openDireBean!!.smhg8[i].sendDate,openDireBean!!.smhg8[i].title,openDireBean!!.smhg8[i].url))
                            }
                        }
                        8 ->{
                            tv_mes_title.text ="市场监管、安全生产监管"
                            for (i in 0 until openDireBean!!.scjg9.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.scjg9[i].publish,openDireBean!!.scjg9[i].sendDate,openDireBean!!.scjg9[i].title,openDireBean!!.scjg9[i].url))
                            }
                        }
                        9 ->{
                            tv_mes_title.text ="城乡建设、环境保护"
                            for (i in 0 until openDireBean!!.cxjs10.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.cxjs10[i].publish,openDireBean!!.cxjs10[i].sendDate,openDireBean!!.cxjs10[i].title,openDireBean!!.cxjs10[i].url))
                            }
                        }
                        10 ->{
                            tv_mes_title.text ="科技、教育"
                            for (i in 0 until openDireBean!!.kjjy11.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.kjjy11[i].publish,openDireBean!!.kjjy11[i].sendDate,openDireBean!!.kjjy11[i].title,openDireBean!!.kjjy11[i].url))
                            }
                        }
                        11 ->{
                            tv_mes_title.text ="文化、广电、新闻出版"
                            for (i in 0 until openDireBean!!.whgd12.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.whgd12[i].publish,openDireBean!!.whgd12[i].sendDate,openDireBean!!.whgd12[i].title,openDireBean!!.whgd12[i].url))
                            }
                        }
                        12 ->{
                            tv_mes_title.text ="卫生、体育"
                            for (i in 0 until openDireBean!!.wsty13.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.wsty13[i].publish,openDireBean!!.wsty13[i].sendDate,
                                    openDireBean!!.wsty13[i].title,openDireBean!!.wsty13[i].url))
                            }
                        }
                        13 ->{
                            tv_mes_title.text ="人口与计划生育、妇女儿童工作"
                            for (i in 0 until openDireBean!!.jhsy14.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.jhsy14[i].publish,openDireBean!!.jhsy14[i].sendDate,
                                    openDireBean!!.jhsy14[i].title,openDireBean!!.jhsy14[i].url))
                            }
                        }
                        14 ->{
                            tv_mes_title.text ="劳动、人事、监察"
                            for (i in 0 until openDireBean!!.ldjc15.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.ldjc15[i].publish,openDireBean!!.ldjc15[i].sendDate,
                                    openDireBean!!.ldjc15[i].title,openDireBean!!.ldjc15[i].url))
                            }
                        }
                        15 ->{
                            tv_mes_title.text ="民政、扶贫、救灾"
                            for (i in 0 until openDireBean!!.fpjz16.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.fpjz16[i].publish,openDireBean!!.fpjz16[i].sendDate,
                                    openDireBean!!.fpjz16[i].title,openDireBean!!.fpjz16[i].url))
                            }
                        }
                        16 ->{
                            tv_mes_title.text ="对外事务"
                            for (i in 0 until openDireBean!!.dwsw17.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.dwsw17[i].publish,openDireBean!!.dwsw17[i].sendDate,
                                    openDireBean!!.dwsw17[i].title,openDireBean!!.dwsw17[i].url))
                            }
                        }
                        17 ->{
                            tv_mes_title.text ="港澳台侨工作"
                            for (i in 0 until openDireBean!!.gat18.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.gat18[i].publish,openDireBean!!.gat18[i].sendDate,
                                    openDireBean!!.gat18[i].title,openDireBean!!.gat18[i].url))
                            }
                        }
                        18 ->{
                            tv_mes_title.text ="公安、司法"
                            for (i in 0 until openDireBean!!.gasf19.size) {
                                mListMes .add(OpenDireMesBean(openDireBean!!.gasf19[i].publish,openDireBean!!.gasf19[i].sendDate,
                                    openDireBean!!.gasf19[i].title,openDireBean!!.gasf19[i].url))
                            }
                        }
                    }
                    openMesAdapter!!.notifyDataSetChanged()

                }
            }
        }
        openMesAdapter!!.onItemChildClickListener =BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            when(view.id){
                R.id.ll_item ->{
                    var intent = Intent(this, WebActivity::class.java)
                    intent.putExtra(
                        "url",
                        NetConstants.OpenDireBase+mListMes[position].url
                    )
                    intent.putExtra("path", "$stringExtra/详情")
                    startActivity(intent)
                }
            }
        }

    }

    fun getMessage(){
        var url =NetConstants.OpenDirectoryUrl
        var jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,Response.Listener {

            openDireBean = JSON.parseObject(it.toString(), OpenDireBean::class.java)
            var zzjg1 = openDireBean!!.zzjg1
            mListMes.clear()
            for (i in 0 until zzjg1.size) {
                mListMes .add(OpenDireMesBean(zzjg1[i].publish,zzjg1[i].sendDate,zzjg1[i].title,zzjg1[i].url))
            }
            openMesAdapter!!.notifyDataSetChanged()

        },Response.ErrorListener {
            ToastUtils.show("error")
        })
        requestQueue!!.add(jsonObjectRequest)
    }
}