package com.example.test0.activity

import com.example.test0.R
import com.example.test0.base.BaseActivity
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 街道简介
 */
class StreetIntroActivity :BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_street_intor
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