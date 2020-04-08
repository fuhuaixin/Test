package com.example.test0.activity


import android.os.Handler
import com.example.test0.R
import com.example.test0.base.BaseActivity
import kotlinx.android.synthetic.main.activity_street_scenery.*


/**
 * 街道实景界面
 */
class StreetSceneryActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_street_scenery
    }

    override fun initView() {
        dialog()
        dialog!!.show()
        tvBack.setOnClickListener {
            finish()
        }
    }

    override fun initListener() {
        Handler().postDelayed({
            dialog!!.dismiss()
        },2000)
    }


}