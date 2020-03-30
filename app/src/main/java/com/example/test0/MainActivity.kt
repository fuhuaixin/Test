package com.example.test0

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test0.adapter.StreetMainAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.utlis.UniCode
import com.example.test0.utlis.getViewModel
import com.example.test0.viewmodel.StreetMainViewModel
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.CallBack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    //    val url :String ="http://192.168.10.133:8089/VideoManager/dev/detailInfo?devId=7657856"
    private val viewModel: StreetMainViewModel by lazy { getViewModel(StreetMainViewModel::class.java) }


    var streetMainAdapter: StreetMainAdapter? = null
    var strListGoverment: MutableList<String> =
        mutableListOf("街道简介", "问卷调查", "留言建议", "投票管理", "在线办事") //政务服务数据
    var strListParty: MutableList<String> = mutableListOf("党务中心", "活动中心", "活动中心") //党建平台数据
    var strListPublicity: MutableList<String> = mutableListOf("党务中心", "活动中心", "活动中心") //宣传平台数据
    var strListForPeople: MutableList<String> = mutableListOf("交通状况", "通知公告", "疫情信息") //便民服务数据
    var strListGis: MutableList<String> = mutableListOf("街道实景", "建筑物信息") //便民服务数据

    var cityName :String ="郑州"

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

//        EasyHttp.get(url).execute(object :CallBack<>)
        recycle_government.layoutManager = GridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListGoverment, 1)
        recycle_government.adapter = streetMainAdapter

        recycle_party.layoutManager = GridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListParty, 2)
        recycle_party.adapter = streetMainAdapter

        recycle_publicity.layoutManager = GridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListPublicity, 2)
        recycle_publicity.adapter = streetMainAdapter


        recycle_for_people.layoutManager = GridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListForPeople, 2)
        recycle_for_people.adapter = streetMainAdapter


        recycle_gis.layoutManager = GridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListGis, 2)
        recycle_gis.adapter = streetMainAdapter


    }

    override fun initListener() {
    }

    override fun initViewModelListener() {
        viewModel.getWeather(cityName)
        viewModel.weatherModel.observe(this, Observer {
            it.apply {
//                Log.e("fhxx",it.toString())
//                Log.e("fhxx",UniCode.decode2(it.wea))

            }
        })
    }

    override fun getData(isRefresh: Boolean) {

    }



}
