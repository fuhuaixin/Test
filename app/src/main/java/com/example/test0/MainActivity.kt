package com.example.test0

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.fastjson.JSON
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.test0.adapter.StreetMainAdapter
import com.example.test0.bean.Bean2
import com.example.test0.utlis.JsonParser
import com.iflytek.cloud.*
import com.iflytek.cloud.ui.RecognizerDialog
import com.iflytek.cloud.ui.RecognizerDialogListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var streetMainAdapter: StreetMainAdapter? = null
    var strListGoverment: MutableList<String> =
        mutableListOf("街道简介", "问卷调查", "留言建议", "投票管理", "在线办事") //政务服务数据
    var strListParty: MutableList<String> = mutableListOf("党务中心", "活动中心", "学习中心") //党建平台数据
    var strListPublicity: MutableList<String> = mutableListOf("党务中心", "活动中心", "学习中心") //宣传平台数据
    var strListForPeople: MutableList<String> = mutableListOf("交通状况", "通知公告", "疫情信息") //便民服务数据
    var strListGis: MutableList<String> = mutableListOf("街道实景", "建筑物信息") //便民服务数据
    var requestQueue: RequestQueue? = null
    //讯飞语音识别相关参数
    var recognizerDialog: RecognizerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GetPermission()

        requestQueue = Volley.newRequestQueue(this)

        initView()
        initHttpListener()

    }

    var map: Map<String, String>? = HashMap()
    fun initView() {
        rl_listen.setOnClickListener(this)
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

    fun GetPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            val REQUEST_CODE_CONTACT = 101
            val permissions = arrayOf<String>(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
            )
            //验证是否许可权限
            for (str in permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT)
                }
            }
        }
    }


    var url = "https://tianqiapi.com/api?version=v6&appid=52796525&appsecret=2cBrl3hs&city=郑州"
    var bean: Bean2? = null
    fun initHttpListener() {
        val stringRequest =
            JsonObjectRequest(Request.Method.GET, url,
                Response.Listener { response ->
                    bean = JSON.parseObject<Bean2>(response.toString(), Bean2::class.java)

                    Log.e("fhxx", bean!!.wea + " -----" + bean!!.tem)

                    tv_tem.text = bean!!.tem
                    tv_wea.text = "°C  ${bean!!.wea}"
                    when (bean!!.wea_img) {
                        "qing" -> {
                            image_wea.setImageResource(R.drawable.ic_qing)
                            image_little_wea.setImageResource(R.drawable.icon_qing)
                        }
                        "yu" -> {
                            image_wea.setImageResource(R.drawable.ic_yu)
                            image_little_wea.setImageResource(R.drawable.icon_yu)
                        }
                        "yin" -> {
                            image_wea.setImageResource(R.drawable.ic_yin)
                            image_little_wea.setImageResource(R.drawable.icon_yin)
                        }
                        "yun" -> {
                            image_wea.setImageResource(R.drawable.ic_yun)
                            image_little_wea.setImageResource(R.drawable.icon_yun)
                        }
                        "xue" -> {
                            image_wea.setImageResource(R.drawable.ic_xue)
                            image_little_wea.setImageResource(R.drawable.icon_bingbao)
                        }
                        "lei" -> {
                            image_wea.setImageResource(R.drawable.ic_lei)
                            image_little_wea.setImageResource(R.drawable.icon_lei)
                        }
                        "shachen" -> {
                            image_wea.setImageResource(R.drawable.ic_shachen)
                            image_little_wea.setImageResource(R.drawable.icon_shachen)
                        }
                        "wu" -> {
                            image_wea.setImageResource(R.drawable.ic_wu)
                            image_little_wea.setImageResource(R.drawable.icon_wu)
                        }
                        "bingbao" -> {
                            image_wea.setImageResource(R.drawable.ic_bingbao)
                            image_little_wea.setImageResource(R.drawable.icon_bingbao)
                        }
                    }


                }, Response.ErrorListener {

                })

        requestQueue!!.add(stringRequest)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rl_listen -> { //点击开始语音识别
//                getWindow().getDecorView().findViewWithTag(“textlink”);
                recognizerDialog = RecognizerDialog(this, mInitListener)
                recognizerDialog!!.setParameter(SpeechConstant.ASR_PTT, "0")
                recognizerDialog!!.setParameter(SpeechConstant.RESULT_TYPE, "json")
                recognizerDialog!!.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD)
                recognizerDialog!!.setParameter(SpeechConstant.ACCENT, "mandarin")
                recognizerDialog!!.setListener(object : RecognizerDialogListener {
                    override fun onResult(recognizerResult: RecognizerResult?, p1: Boolean) {
                        if (!p1) {
                            var parseIatResult =
                                JsonParser.parseIatResult(recognizerResult!!.resultString)
                            etMessage.setText(parseIatResult)
                            //获取焦点
                            etMessage.requestFocus();
                            //将光标定位到文字最后，以便修改
                            etMessage.setSelection(parseIatResult.length);
                        }
                      /*  var parseIatResult =
                            JsonParser.parseIatResult(recognizerResult!!.resultString)
                        etMessage.setText(parseIatResult)
                        //获取焦点
                        etMessage.requestFocus();
                        //将光标定位到文字最后，以便修改
                        etMessage.setSelection(parseIatResult.length);*/
                    }

                    override fun onError(p0: SpeechError?) {
                        Toast.makeText(this@MainActivity, "$p0", Toast.LENGTH_SHORT).show()
                    }

                })
                recognizerDialog!!.show()
                var findViewWithTag = recognizerDialog!!.getWindow()!!.getDecorView()
                    .findViewWithTag<TextView>("textlink")
                findViewWithTag.text=("");
                findViewWithTag.isEnabled =false
                findViewWithTag.paint.flags=Paint.SUBPIXEL_TEXT_FLAG
            }
        }
    }


    /**
     * 初始化监听器。
     */
    private val mInitListener = InitListener { code ->
        Log.d("TAG", "SpeechRecognizer init() code = $code")
        if (code != ErrorCode.SUCCESS) {
            Toast.makeText(
                this,
                "初始化失败，错误码：$code,请点击网址https://www.xfyun.cn/document/error-code查询解决方案",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /* fun PostTest(){
         var map = TreeMap<String,String>()
         map["name"] = "chaychan"
         map["age"] = "22 years old"
         map["hobby"] = "programming";
         HttpUtil.getInstance().request(this,url,map,object :HttpCallBack<>)
     }*/


}
