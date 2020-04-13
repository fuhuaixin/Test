package com.example.test0.activity

import android.content.Intent
import android.view.View
import com.example.test0.R
import com.example.test0.base.BaseActivity
import kotlinx.android.synthetic.main.activity_in_ours.*
import kotlinx.android.synthetic.main.inclue_web_title.*

/**
 * 走进我们页面
 */
class InOursActivity :BaseActivity() ,View.OnClickListener{

    private var strPath ="";
    var sendPath :String =""
    var sendUrl :String =""
    //街道简介url
    var introUrl :String ="http://www.tcmap.com.cn/henan/jinshuiqu_fenghuangtaijiedao_yingxieshequ.html"
    //地理位置url
    var locationUrl :String =""
    //行政区划url
    var areaUrl :String ="http://www.tcmap.com.cn/henan/jinshuiqu_fenghuangtaijiedao_yingxieshequ.html"
    //资源设施url
    var energyUrl :String =""

    override fun setLayoutId(): Int {
        return R.layout.activity_in_ours

    }

    override fun initView() {
        strPath= intent.getStringExtra("path")
        tvPath.text = strPath
        tvIntro.setOnClickListener(this)
        tvLocation.setOnClickListener(this)
        tvArea.setOnClickListener(this)
        tvEnergy.setOnClickListener(this)
    }

    override fun initListener() {

        llBack.setOnClickListener {
            finish()
        }

    }

    override fun onClick(v: View?) {
        var intent = Intent(this, WebActivity::class.java)
        when(v?.id){

            R.id.tvIntro ->{
                sendPath ="$strPath/街道简介"
                sendUrl =introUrl
            }
            R.id.tvLocation ->{
                sendPath ="$strPath/地理位置"
                sendUrl =locationUrl
            }
            R.id.tvArea ->{
                sendPath ="$strPath/行政区划"
                sendUrl =areaUrl
            }
            R.id.tvEnergy ->{
                sendPath ="$strPath/资源设施"
                sendUrl =energyUrl
            }

        }

        intent.putExtra("path",sendPath)
        intent.putExtra("url",sendUrl)
        startActivity(intent)
    }
}