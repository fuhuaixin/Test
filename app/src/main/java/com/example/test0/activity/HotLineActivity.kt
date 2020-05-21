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

    override fun setLayoutId(): Int {
        return  R.layout.activity_hot_line
    }

    override fun initView() {

        tvPath.text=(intent.getStringExtra("path"))


        mList.add(CommunityBean("机构名称","地址","乘车路线","电话"))
        mList.add(CommunityBean("未来路街道办事处","未来路顺河路交叉口西南角","","65965194"))
        mList.add(CommunityBean("燕庄社区居委会","建业路6号西北90米","64,285,52",""))
        mList.add(CommunityBean("沈庄社区居委会","","",""))
        mList.add(CommunityBean("聂庄社区居委会","纬五路与未来路交叉口","23，27","65996979"))
        mList.add(CommunityBean("黑庄社区居委会","黑庄路口往东","","65675233"))
        mList.add(CommunityBean("司家庄社区居委会","","",""))
        mList.add(CommunityBean("东明路社区居委会","东明路北5号院","64，2，65，58，206，105","63350633"))
        mList.add(CommunityBean("康复社区居委会","纬四路25号院或肿瘤医学院家属院13号1楼","40，58，2，205，105","65588292"))
        mList.add(CommunityBean("金水花园社区居委会","老107国道和纬四路交叉口向西300米路北广发大厦对面","37，23，723","65615465"))
        mList.add(CommunityBean("新鑫花园社区居委会","金水区与商贸路交叉口向南100米新鑫花园","26，77，209","68261655"))
        mList.add(CommunityBean("民航路社区居委会","民航路26号楼","23，37，77","63380286"))
        mList.add(CommunityBean("吉祥花园社区居委会","顺河路与燕沈路交叉口向南200米路西","23，58，65，105","66717072"))
        mList.add(CommunityBean("广发花园社区居委会","燕黑路黑庄菜市场北门","23，37，206，916，26","63381866"))
        mList.add(CommunityBean("锦江花园社区居委会","未来路北段73号院","23，206",""))
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