package com.example.test0.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test0.R
import com.example.test0.adapter.CommunityAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.bean.CommunityBean
import kotlinx.android.synthetic.main.activity_hot_line.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 社区电话
 */
class HotLineActivity :BaseActivity() {
    var communityAdapter:CommunityAdapter ?=null
    var mList :MutableList<CommunityBean> = mutableListOf()

    var type:String =""

    override fun setLayoutId(): Int {
        return  R.layout.activity_hot_line
    }

    override fun initView() {

        tvPath.text=(intent.getStringExtra("path"))
        type =intent.getStringExtra("type")

        if (type == "community"){
            tv_title.text ="社区热线"
            mList.add(CommunityBean("街道办事处","电话","街道办事处","电话"))
            mList.add(CommunityBean("燕庄社区居委会","暂无","沈庄社区居委会","暂无"))
            mList.add(CommunityBean("聂庄社区居委会","65996979","黑庄社区居委会","65675233"))
            mList.add(CommunityBean("司家庄社区居委会","暂无","东明路社区居委会","63350633"))
            mList.add(CommunityBean("康复社区居委会","65588292","东风路街道办事处","63752058"))
            mList.add(CommunityBean("金水花园社区居委会","65615465","新鑫花园社区居委会","68261655"))
            mList.add(CommunityBean("民航路社区居委会","63380286","吉祥花园社区居委会","66717072"))
            mList.add(CommunityBean("广发花园社区居委会","63381866","锦江花园社区居委会","6561371"))
            mList.add(CommunityBean("人民路街道办事处","66226465","",""))
        }else if (type=="department"){
            tv_title.text ="区直电话"
            mList.add(CommunityBean("办事处","电话","办事处","电话"))
            mList.add(CommunityBean("政府办","63526200","事管局","63526186"))
            mList.add(CommunityBean("创建办","63856140","司法局","63526147"))
            mList.add(CommunityBean("人防办","66253288","工信局","63526244"))
            mList.add(CommunityBean("农 委","63526417","自然资源局","66205666"))
            mList.add(CommunityBean("审批中心","60130900","保障办","63931409"))
            mList.add(CommunityBean("财政局","63526167","教体局","60116618"))
            mList.add(CommunityBean("粮管中心","65957279","楼宇办","63605877"))
            mList.add(CommunityBean("市场监督管理局","63935382","发改统计局","63526132"))
            mList.add(CommunityBean("住建局","63526234","民政局","58579000"))
            mList.add(CommunityBean("科技园区","86000600","行政执法局","63928110"))
            mList.add(CommunityBean("文化旅游局","86086868","审计局","63526121"))
            mList.add(CommunityBean("商务局","63526126","应急管理局","86000588"))
            mList.add(CommunityBean("科技局","63526271","交通局","63733886"))
            mList.add(CommunityBean("环保局","86000668","人社局","63526286"))
            mList.add(CommunityBean("退役军人事务管理局","56539001","卫健委","58527939"))
        }

        recycle_line.layoutManager =LinearLayoutManager(this)
        communityAdapter = CommunityAdapter(mList)
        recycle_line.adapter =communityAdapter
    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }
    }

}