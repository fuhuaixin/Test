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
            mList.add(CommunityBean("文化路街道办事处","63936965","未来路街道办事处","65965194"))
            mList.add(CommunityBean("经八路街道办事处","56805800","凤凰台街道办事处","66758705"))
            mList.add(CommunityBean("大石桥街道办事处","63871900","杜岭街道办事处","66226811"))
            mList.add(CommunityBean("兴达路街道办事处","68100001","南阳路街道办事处","63922822"))
            mList.add(CommunityBean("丰产路街道办事处","69125111","北林路街道办事处","65744009"))
            mList.add(CommunityBean("国基路街道办事处","65692201","丰庆路街道办事处","63560001"))
            mList.add(CommunityBean("杨金路街道办事处","61992211","花园路街道办事处","65953245"))
            mList.add(CommunityBean("人民路街道办事处","66226465","东风路街道办事处","63752058"))
            mList.add(CommunityBean("南阳新村街道办事处","63731056","",""))
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