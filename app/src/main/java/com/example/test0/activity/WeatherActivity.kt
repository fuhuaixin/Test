package com.example.test0.activity

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.alibaba.fastjson.JSON
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.test0.R
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.WeatherDaysBean
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.inclue_wea_index.*
import kotlinx.android.synthetic.main.inclue_wea_week.*
import kotlinx.android.synthetic.main.inclue_web_title.*

class WeatherActivity : BaseActivity(), View.OnClickListener {
    var requestQueue: RequestQueue? = null
    var stringExtra = ""

    var weatherDaysBean: WeatherDaysBean? = null
    override fun setLayoutId(): Int {
        return R.layout.activity_weather
    }

    override fun initView() {
        dialog()
        dialog!!.show()
        stringExtra = intent.getStringExtra("path")
        requestQueue = Volley.newRequestQueue(this)
        tvPath.text = stringExtra;
        val jsonObjectRequest = JsonObjectRequest(NetConstants.WeatherDaysUrl, Response.Listener {
            Log.e("fhxx", it.toString())
            weatherDaysBean = JSON.parseObject(it.toString(), WeatherDaysBean::class.java)
            var data = weatherDaysBean!!.data

            imageWeaTodey.setImageResource(getWeather(data[0].wea_img))
            tvTemToday.text ="${data[0].tem}"
            tvDHTemToday.text ="${data[0].tem2} ~ ${data[0].tem1} ${data[3].win[0]} ${data[3].win_speed}"
            tvAirToday.text ="空气指数：${data[0].air}  等级：${data[0].air_level}"
            tvWeaToday.text="${data[0].win}"
            tvTipsToday.text ="生活小贴士:${data[0].air_tips}"

            tvTem1.text = "${data[1].tem2}/${data[1].tem1}"
            tvWea1.text = "${data[1].date} ${data[1].week} ${data[1].wea}"
            tvWind1.text = "${data[1].win[0]}  ${data[1].win_speed}"
            imageWea1.setImageResource(getWeather(data[1].wea_img))

            tvTem2.text = "${data[2].tem2}/${data[2].tem1}"
            tvWea2.text = "${data[2].date} ${data[2].week} ${data[2].wea}"
            tvWind2.text = "${data[2].win[0]}  ${data[2].win_speed}"
            imageWea2.setImageResource(getWeather(data[2].wea_img))

            tvTem3.text = "${data[3].tem2}/${data[3].tem1}"
            tvWea3.text = "${data[3].date} ${data[3].week} ${data[3].wea}"
            tvWind3.text = "${data[3].win[0]}  ${data[3].win_speed}"
            imageWea3.setImageResource(getWeather(data[3].wea_img))

            tvTem4.text = "${data[4].tem2}/${data[4].tem1}"
            tvWea4.text = "${data[4].date} ${data[4].week} ${data[4].wea}"
            tvWind4.text = "${data[4].win[0]}  ${data[4].win_speed}"
            imageWea4.setImageResource(getWeather(data[4].wea_img))

            tvTem5.text = "${data[5].tem2}/${data[5].tem1}"
            tvWea5.text = "${data[5].date} ${data[5].week} ${data[5].wea}"
            tvWind5.text = "${data[5].win[0]}  ${data[5].win_speed}"
            imageWea5.setImageResource(getWeather(data[5].wea_img))

            tvTem6.text = "${data[6].tem2}/${data[6].tem1}"
            tvWea6.text = "${data[6].date} ${data[6].week} ${data[6].wea}"
            tvWind6.text = "${data[5].win[0]} ${data[6].win_speed}"
            imageWea6.setImageResource(getWeather(data[6].wea_img))

            tvIndex1.text = "${data[0].index[0].title}:"
            tvLevel1.text = "${data[0].index[0].level}"
            tvDesc1.text = "${data[0].index[0].desc}"

            tvIndex2.text = "${data[0].index[2].title}:"
            tvLeve2.text = "${data[0].index[2].level}"
            tvDesc2.text = "${data[0].index[2].desc}"

            tvIndex3.text = "${data[0].index[3].title}:"
            tvLeve3.text = "${data[0].index[3].level}"
            tvDesc3.text = "${data[0].index[3].desc}"

            tvIndex4.text = "${data[0].index[4].title}:"
            tvLeve4.text = "${data[0].index[4].level}"
            tvDesc4.text = "${data[0].index[4].desc}"

            tvIndex5.text = "${data[0].index[5].title}:"
            tvLeve5.text = "${data[0].index[5].level}"
            tvDesc5.text = "${data[0].index[5].desc}"

            dialog!!.dismiss()

        }, Response.ErrorListener {
            Log.e("fhxx", it.message)

            dialog!!.dismiss()

        })
        requestQueue!!.add(jsonObjectRequest)


    }

    override fun initListener() {
        llBack.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.llBack -> {
                finish()
            }
        }
    }

    fun getWeather(weaImage: String): Int {
        var str: Int = 0
        when (weaImage) {
            "qing" -> {
                str = R.drawable.icon_qing
            }
            "yu" -> {
                str = R.drawable.icon_yu
            }
            "yin" -> {
                str = R.drawable.icon_yin

            }
            "yun" -> {
                str = R.drawable.icon_yun
            }
            "xue" -> {
                str = R.drawable.icon_bingbao

            }
            "lei" -> {
                str = R.drawable.icon_lei
            }
            "shachen" -> {
                str = R.drawable.icon_shachen
            }
            "wu" -> {
                str = R.drawable.icon_wu
            }
            "bingbao" -> {
                str = R.drawable.icon_bingbao
            }
        }
        return str
    }
}