package com.example.test0.activity

import com.example.test0.R
import com.example.test0.base.BaseActivity
import com.example.test0.bean.LetterBoxBean
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_letter_box_mes.*
import kotlinx.android.synthetic.main.inclue_web_title.*

class LetterBoxMesActivity :BaseActivity() {

    var mData :LetterBoxBean.DateBean ?=null
    var path:String =""
    override fun setLayoutId(): Int {
        return R.layout.activity_letter_box_mes
    }

    override fun initView() {
        mData = intent.getSerializableExtra("letterBean") as LetterBoxBean.DateBean?
        path =intent.getStringExtra("path")
        tvPath.text =path
        tv_letter_title.text =mData!!.askMessage
        tv_deal_mes.text =mData!!.dealMessage
        tv_address.text =mData!!.address
        tv_tel.text =mData!!.telephone
        tv_deal_time.text =mData!!.dealTime
        tv_send_time.text =mData!!.sendTime
        tv_type.text =mData!!.type
        tv_status.text =mData!!.progress
        tv_deal_gov.text =mData!!.gov

    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }
    }
}