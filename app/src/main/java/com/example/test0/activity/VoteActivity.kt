package com.example.test0.activity

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.test0.R
import com.example.test0.adapter.QuestAdapter
import com.example.test0.base.BaseActivity
import kotlinx.android.synthetic.main.activity_vote.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 投票管理
 */
class VoteActivity :BaseActivity() {
    var questAdapter: QuestAdapter?=null
    var mList :MutableList<String> = mutableListOf("我最满意得社区")

    var path =""
    override fun setLayoutId(): Int {
        return R.layout.activity_vote
    }

    override fun initView() {
        path =intent.getStringExtra("path")
        tvPath.text =path
        recycle_hot_ques.layoutManager = GridLayoutManager(this,4)
        questAdapter = QuestAdapter(mList)
        recycle_hot_ques.adapter =questAdapter
        questAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.ll_item ->{
                        when(position){
                            0 ->{
                                toWeb("https://tp.wjx.top/jq/82257582.aspx")
                            }
                        }
                    }
                }
            }

        recycle_all_ques.layoutManager = GridLayoutManager(this,4)
        questAdapter = QuestAdapter(mList)
        recycle_all_ques.adapter =questAdapter

        questAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.ll_item ->{
                        when(position){
                            0 ->{
                                toWeb("https://tp.wjx.top/jq/82257582.aspx")
                            }
                        }
                    }
                }
            }
    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }
    }

    fun toWeb(url :String ){
        var intent = Intent(this, WebActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("path", "$path/详情")
        startActivity(intent)
    }
}