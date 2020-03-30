package com.example.test0.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.base.BaseViewModel
import com.example.test0.bean.StreetMainBean
import com.example.test0.utlis.SingleLiveEvent
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.CallBack
import com.zhouyou.http.exception.ApiException

class StreetMainViewModel (context: Application) :BaseViewModel(context) {

//http://www.tianqiapi.com/api?%E6%B2%B3%E5%8C%97  ?version=v6&appid=52796525&appsecret=2cBrl3hs&city=${city}


    var getWeatherUrl = "/api"
    val weatherModel = SingleLiveEvent<StreetMainBean>()

    fun getWeather(city :String){
        EasyHttp.get(getWeatherUrl)
            .baseUrl("http://www.tianqiapi.com")
            .params("version","v6")
            .params("appid","52796525")
            .params("appsecret","2cBrl3hs")
            .params("city",city)
            .execute(object :CallBack<StreetMainBean>(){
                override fun onSuccess(t: StreetMainBean?) {
                    weatherModel.value =t
                }

                override fun onError(e: ApiException?) {

                }

                override fun onStart() {

                }

                override fun onCompleted() {

                }

            })
    }

}