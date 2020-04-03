package com.example.test0.activity


import com.example.test0.R
import com.example.test0.base.BaseActivity


class StreetSceneryActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_street_scenery
    }

    override fun initView() {
        dialog()
        dialog!!.show()

    }

    override fun initListener() {
    }


}