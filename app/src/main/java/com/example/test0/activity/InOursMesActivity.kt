package com.example.test0.activity

import com.example.test0.R
import com.example.test0.base.BaseActivity
import kotlinx.android.synthetic.main.activity_in_ours_message.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 走进我们详情
 */
class InOursMesActivity :BaseActivity(){
    override fun setLayoutId(): Int {
        return R.layout.activity_in_ours_message
    }

    override fun initView() {
        tvPath.text =intent.getStringExtra("path")
        tvTitle.text=intent.getStringExtra("title")
        tvContent.text =intent.getStringExtra("content")
    }

    override fun initListener() {
        llBack.setOnClickListener {
            finish()
        }

    }

}