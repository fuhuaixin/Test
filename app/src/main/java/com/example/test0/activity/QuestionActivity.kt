package com.example.test0.activity

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.test0.R
import com.example.test0.adapter.QuestAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 问卷调查
 */
class QuestionActivity :BaseActivity() {

    var questAdapter:QuestAdapter ?=null
    var mList :MutableList<String> = mutableListOf("英协路街道各社区便民服务度调查","英协路街道各云平台使用满意度调查")

    var path =""
    override fun setLayoutId(): Int {
        return R.layout.activity_question
    }

    override fun initView() {
        path =intent.getStringExtra("path")
        tvPath.text =path
        recycle_hot_ques.layoutManager =GridLayoutManager(this,4)
        questAdapter = QuestAdapter(mList)
        recycle_hot_ques.adapter =questAdapter
        questAdapter!!.onItemChildClickListener =BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            when(view.id){
                R.id.ll_item ->{
                    when(position){
                        0 ->{
                            toWeb("https://www.wjx.cn/jq/76503635.aspx")
                        }
                        1 ->{
                            toWeb("https://www.wjx.cn/jq/76489931.aspx")
                        }
                    }
                }
            }
        }

        recycle_all_ques.layoutManager =GridLayoutManager(this,4)
        questAdapter = QuestAdapter(mList)
        recycle_all_ques.adapter =questAdapter

        questAdapter!!.onItemChildClickListener =BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            when(view.id){
                R.id.ll_item ->{
                    when(position){
                        0 ->{
                            toWeb("https://www.wjx.cn/jq/76503635.aspx")
                        }
                        1 ->{
                            toWeb("https://www.wjx.cn/jq/76489931.aspx")
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