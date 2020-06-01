package com.example.test0.activity

import com.example.test0.R
import com.example.test0.base.BaseActivity
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 公用详情模板
 */
class StreetIntroActivity :BaseActivity() {
    var layoutId :Int =0
    override fun setLayoutId(): Int {
        var type = intent.getStringExtra("type")

        when(type){
            "街道简介"->{
                return  R.layout.activity_street_intor
            }
            "公开指南" ->{
                return  R.layout.activity_public_guide
            }
            "依申请公开" ->{
                return  R.layout.activity_apply_for_public
            }
        }

        return layoutId
    }

    override fun initView() {
        tvPath.text =intent.getStringExtra("path")
    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }
    }
}