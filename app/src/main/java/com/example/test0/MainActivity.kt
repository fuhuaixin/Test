package com.example.test0

import android.Manifest
import android.app.StatusBarManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.test0.activity.*
import com.example.test0.adapter.StreetMainAdapter
import com.example.test0.base.NetConstants
import com.example.test0.bean.VoiceReplyBean
import com.example.test0.bean.WeatherNowBean
import com.example.test0.utlis.JsonParser
import com.example.test0.utlis.NoScrollerGridLayoutManager
import com.example.test0.utlis.ToastUtils
import com.example.test0.view.VoiceDialog
import com.example.test0.view.VolumeWaveView
import com.iflytek.cloud.*
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.json.JSONObject


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var streetMainAdapter: StreetMainAdapter? = null
    var strListGoverment: MutableList<String> =
        mutableListOf("街道简介", "问卷调查", "留言建议", "投票管理", "在线办事", "政策法规") //政务服务数据
    var strListParty: MutableList<String> = mutableListOf("党务中心", "活动中心", "学习中心") //党建平台数据
    var strListPublicity: MutableList<String> = mutableListOf("走进我们", "新闻中心") //宣传平台数据
    var strListForPeople: MutableList<String> = mutableListOf("便民服务", "通知公告", "疫情信息") //便民服务数据
    var strListGis: MutableList<String> = mutableListOf("街道实景", "建筑物信息") //便民服务数据
    var requestQueue: RequestQueue? = null
    var voiceDialog: VoiceDialog? = null
    var weatherNowBean: WeatherNowBean? = null
    var basePath: String = "英协路智慧街道云平台/"
    var streetMainPath: String = "政务服务/"
    var forPeoplePath: String = "便民服务/"
    var PublicityPath: String = "宣传平台/"
    var partyPath: String = "党建平台/"
    //讯飞语音识别相关参数
    var mIat: SpeechRecognizer? = null
    //语音dialog中的控件 记得在dialog后去findviewbyId
    var volumeWaveView: VolumeWaveView? = null
    var image_start: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        GetPermission()
        mIat = SpeechRecognizer.createRecognizer(this, mInitListener)

        initView()
        initHttpListener()

    }

    fun initView() {

        requestQueue = Volley.newRequestQueue(this)
        rl_listen.setOnClickListener(this)
        rel_weather.setOnClickListener(this)
        rl_big_data.setOnClickListener(this)
        rl_street_gird.setOnClickListener(this)
        recycle_government.layoutManager = NoScrollerGridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListGoverment, 1)
        recycle_government.isNestedScrollingEnabled = false
        recycle_government.adapter = streetMainAdapter
        streetMainAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view?.id) {
                    R.id.rl_bg -> {
                        when (strListGoverment[position]) {
                            "街道简介" -> {
                                var intent = Intent(this, WebActivity::class.java)
                                intent.putExtra(
                                    "url",
//                                    "https://news.ifeng.com/c/special/7uLj4F83Cqm"
                                    "http://zzjswll.hnzwfw.gov.cn/art/2019/8/27/art_54262_6347.html"
                                )
                                intent.putExtra("path", "$basePath${streetMainPath}街道简介")
                                startActivity(intent)
                            }
                            "问卷调查" -> {
                                var intent = Intent(this, WebInfoActivity::class.java)
                                intent.putExtra("path", "$basePath${streetMainPath}问卷调查")
                                intent.putExtra("type", "问卷调查")
                                startActivity(intent)
                            }
                            "投票管理" -> {
                                var intent = Intent(this, WebInfoActivity::class.java)
                                intent.putExtra("path", "$basePath${streetMainPath}投票管理")
                                intent.putExtra("type", "投票管理")
                                startActivity(intent)
                            }
                            "政策法规" -> {
                                var intent = Intent(this, WebInfoActivity::class.java)
                                intent.putExtra("path", "$basePath${streetMainPath}政策法规")
                                intent.putExtra("type", "政策法规")
                                startActivity(intent)
                            }
                            "留言建议"->{
                                var intent = Intent(this, FeedBackActivity::class.java)
                                intent.putExtra("path", "$basePath${streetMainPath}留言建议")
                                startActivity(intent)
                            }
                            "在线办事"->{
                                var intent = Intent(this, ChiefPublicActivity::class.java)
                                intent.putExtra("path", "$basePath${streetMainPath}在线办事")
                                startActivity(intent)
                            }
                        }
                    }
                }
            }

        recycle_party.layoutManager = NoScrollerGridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListParty, 2)
        recycle_party.isNestedScrollingEnabled = false
        recycle_party.adapter = streetMainAdapter
        streetMainAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.rl_bg -> {
                        when (strListParty[position]) {
                            "学习中心" -> {
                                var intent = Intent(this, WebInfoActivity::class.java)
                                intent.putExtra("path", "$basePath${partyPath}学习中心")
                                intent.putExtra("type", "学习中心")
                                startActivity(intent)
                            }
                            "党务中心" -> {
                                var intent = Intent(this, WebInfoActivity::class.java)
                                intent.putExtra("path", "$basePath${partyPath}党务中心")
                                intent.putExtra("type", "党务中心")
                                startActivity(intent)
                            }
                            "活动中心" -> {
                                var intent = Intent(this, WebInfoActivity::class.java)
                                intent.putExtra("path", "$basePath${partyPath}活动中心")
                                intent.putExtra("type", "活动中心")
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        recycle_publicity.layoutManager = NoScrollerGridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListPublicity, 2)
        recycle_publicity.adapter = streetMainAdapter
        streetMainAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.rl_bg -> {
                        when (strListPublicity[position]) {
                            "走进我们" -> {
                                var intent = Intent(this, InOursActivity::class.java)
                                intent.putExtra("path", "$basePath${PublicityPath}走进我们")
                                startActivity(intent)
                            }
                            "新闻中心" -> {
                                var intent = Intent(this, WebInfoActivity::class.java)
                                intent.putExtra("path", "$basePath${PublicityPath}新闻中心")
                                intent.putExtra("type", "新闻中心")
                                startActivity(intent)
                            }
                        }
                    }
                }
            }

        recycle_for_people.layoutManager = NoScrollerGridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListForPeople, 2)
        recycle_for_people.adapter = streetMainAdapter


        streetMainAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->

                when (view.id) {
                    R.id.rl_bg -> {
                        when (strListForPeople[position]) {
                            "便民服务" -> {
                                var intent = Intent(this, WebActivity::class.java)
                                intent.putExtra(
                                    "url",
                                    "file:////android_asset/services.html"
                                )
                                intent.putExtra("path", "$basePath${forPeoplePath}便民服务")
                                startActivity(intent)
                            }
                            "疫情信息" -> {
                                var intent = Intent(this, WebActivity::class.java)
                                intent.putExtra(
                                    "url",
//                                    "https://news.ifeng.com/c/special/7uLj4F83Cqm"
                                    "https://alihealth.taobao.com/medicalhealth/influenzamap"
                                )
                                intent.putExtra("path", "$basePath${forPeoplePath}疫情信息")
                                startActivity(intent)
                            }
                            "通知公告" -> {
                                var intent = Intent(this, WebInfoActivity::class.java)
                                intent.putExtra("path", "$basePath${forPeoplePath}通知公告")
                                intent.putExtra("type", "通知公告")
                                startActivity(intent)
                            }
                        }

                    }
                }

            }
        recycle_gis.layoutManager = NoScrollerGridLayoutManager(this, 3)
        streetMainAdapter = StreetMainAdapter(this, strListGis, 2)
        recycle_gis.adapter = streetMainAdapter
        streetMainAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->

                when (view.id) {
                    R.id.rl_bg -> {
                        when (strListGis[position]) {
                            "街道实景" -> {
                                var intent = Intent(this, StreetSceneryActivity::class.java)
                                startActivity(intent)
                            }
                            "建筑物信息"->{
                                var intent = Intent(this, WebActivity::class.java)
                                intent.putExtra(
                                    "url",
                                    "${NetConstants.BASE_URL}zhjd/earthstreet.html"
                                )
                                intent.putExtra("path", "${basePath}GIS地图/建筑物信息")
                                startActivity(intent)
                            }

                        }
                    }
                }

            }

        voiceDialog = VoiceDialog(
            this,
            R.style.CustomDialog,
            myDialogListener
        )

    }

    fun GetPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            val REQUEST_CODE_CONTACT = 101
            val permissions = arrayOf<String>(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION
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


    fun initHttpListener() {
        val stringRequest =
            JsonObjectRequest(Request.Method.GET, NetConstants.WeatherUrl,
                Response.Listener { response ->
                    Log.e("fhxx11", response.toString())
                    weatherNowBean = JSON.parseObject<WeatherNowBean>(
                        response.toString(),
                        WeatherNowBean::class.java
                    )
                    tv_tem.text = weatherNowBean!!.tem
                    tv_wea.text = "°C  ${weatherNowBean!!.wea}"
                    when (weatherNowBean!!.wea_img) {
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

        var jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            NetConstants.NowEpidemicUrl,
            Response.Listener { response ->
                var parseObject = JSON.parseObject(response.toString())
                var get = parseObject.get("data")

            },
            Response.ErrorListener {
            })

        requestQueue!!.add(stringRequest)
        requestQueue!!.add(jsonObjectRequest)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rl_listen -> { //点击开始语音弹窗
                voiceDialog!!.show()
                var win = voiceDialog!!.window
                var attributes = win!!.attributes
                attributes.width = WindowManager.LayoutParams.MATCH_PARENT
                attributes.height = WindowManager.LayoutParams.MATCH_PARENT
                win.attributes = attributes

                volumeWaveView = voiceDialog!!.findViewById(R.id.volumeWaveView)
                image_start = voiceDialog!!.findViewById(R.id.image_start)

            }
            R.id.rel_weather -> {
                var intent = Intent(this, WeatherActivity::class.java)
                intent.putExtra("path", "${basePath}天气详情")
                startActivity(intent)
            }
            R.id.rl_big_data->{
                ToastUtils.show("暂未开放")
            }
            R.id.rl_street_gird ->{
                ToastUtils.show("暂未开放")
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
                "初始化失败，错误码：$code",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val myDialogListener: VoiceDialog.MyDialogListener =
        VoiceDialog.MyDialogListener { view ->
            //            view.findViewById<>(R.id.).visibility =View.VISIBLE
            when (view!!.id) {
                R.id.image_start -> {
                    Log.e("fhxx", "点击了start")
                    mIat!!.setParameter(SpeechConstant.RESULT_TYPE, "json")
                    mIat!!.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD)
                    mIat!!.setParameter(SpeechConstant.ACCENT, "mandarin")
                    mIat!!.setParameter(SpeechConstant.ASR_PTT, "0")
                    mIat!!.startListening(mRecognizerListener)
                    volumeWaveView!!.visibility = View.VISIBLE
                    image_start!!.visibility = View.GONE
                }
                R.id.viewRight,
                R.id.viewTop,
                R.id.viewLeft,
                R.id.viewBottom -> {
                    voiceDialog!!.dismiss();
                }

            }
        }

    // 用HashMap存储听写结果
    private var mIatResults: HashMap<String, String> = LinkedHashMap<String, String>()
    private val mRecognizerListener: RecognizerListener = object : RecognizerListener {
        override fun onVolumeChanged(i: Int, bytes: ByteArray?) {
            Log.d("fhxx", "返回音频数据：" + i + "-----------" + bytes!!.size)
        }

        override fun onResult(results: RecognizerResult?, isLast: Boolean) {
            var result = results!!.getResultString(); //没有解析的

            var text = JsonParser.parseIatResult(result);//解析过后的
            var sn: String? = null
            // 读取json结果中的 sn字段
            var jsonObject = JSONObject(results.getResultString())
            sn = jsonObject.optString("sn");
            mIatResults.put(sn, text);
            //没有得到一句，添加到
            var stringBuffer = StringBuffer();
            for (key: String in mIatResults.keys) {
                stringBuffer.append(mIatResults[key])
            }
            etMessage.setText(stringBuffer.toString())
            //获取焦点
            etMessage.requestFocus();
            //将光标定位到文字最后，以便修改
            etMessage.setSelection(stringBuffer.length);
            if (isLast) {
                EventBus.getDefault().post(VoiceReplyBean(stringBuffer.toString(), 1))
            }

        }

        override fun onBeginOfSpeech() {
            Log.d("fhxx", "开始说话")
//            ToastUtils.show("开始说话")
        }

        override fun onEvent(eventType: Int, arg1: Int, arg2: Int, obj: Bundle?) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //
        }

        override fun onEndOfSpeech() {
            Log.d("fhxx", "结束说话")
//            ToastUtils.show("结束说话")
            volumeWaveView!!.visibility = View.GONE
            image_start!!.visibility = View.VISIBLE
        }

        override fun onError(speechError: SpeechError?) {
            volumeWaveView!!.visibility = View.GONE
            image_start!!.visibility = View.VISIBLE
            ToastUtils.show("录音失败")
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            Log.d("fhxx", speechError!!.getPlainDescription(true))
        }

    }


}
