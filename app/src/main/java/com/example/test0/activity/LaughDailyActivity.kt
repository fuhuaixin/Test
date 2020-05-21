package com.example.test0.activity

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.fastjson.JSON
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.test0.R
import com.example.test0.adapter.JokeAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.base.NetConstants
import com.example.test0.bean.JokeBean
import com.example.test0.utlis.DateUtil
import com.example.test0.utlis.SPUtils
import com.example.test0.utlis.ToastUtils
import kotlinx.android.synthetic.main.activity_laugh_daily.*
import kotlinx.android.synthetic.main.inclue_web_title.*
import kotlin.random.Random

/**
 * 每日一笑
 */
class LaughDailyActivity :BaseActivity() {

    var requestQueue: RequestQueue? = null
    var jokeAdapter :JokeAdapter ?=null
    override fun setLayoutId(): Int {
        return R.layout.activity_laugh_daily
    }

    override fun initView() {
        tvPath.text =intent.getStringExtra("path")
        llBack.setOnClickListener {
            finish()
        }

        requestQueue = Volley.newRequestQueue(this)

        recycle_joke.layoutManager = LinearLayoutManager(this)


        getJoke()
    }

    override fun initListener() {
        var stringToDate = DateUtil.getStringToDate("05-20", "MM-dd")
        Log.e("fhxx  date","$stringToDate")
    }

    private fun getJoke(){
        var nextInt = Random.nextInt(0, 21)
        Log.e("fhxx","$nextInt---");

        var url  =NetConstants.getJokeUrl+nextInt
        var jsonObjectRequest =
            JsonObjectRequest(Request.Method.GET, url, Response.Listener { response ->

                var parseObject = JSON.parseObject(response.toString(), JokeBean::class.java)
                if (parseObject.error_code==0){
                    jokeAdapter = JokeAdapter(parseObject.result.data)
                    recycle_joke.adapter = jokeAdapter
                    jokeAdapter!!.notifyDataSetChanged()
                    SPUtils.putString(this,"JokeBean",response.toString())
                }else{
                    var string = SPUtils.getString(this, "JokeBean")
                    var parseObject1 = JSON.parseObject(string, JokeBean::class.java)
                    jokeAdapter = JokeAdapter(parseObject1.result.data)
                    recycle_joke.adapter = jokeAdapter
                    jokeAdapter!!.notifyDataSetChanged()
                }

            }, Response.ErrorListener {
                Log.e("fhxx",it.message)
            })
        requestQueue!!.add(jsonObjectRequest)
    }
}