package com.example.test0.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.fastjson.JSONObject
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.test0.R
import com.example.test0.adapter.WebInfoAdapter
import com.example.test0.base.BaseActivity
import com.example.test0.bean.WebInfoBean
import kotlinx.android.synthetic.main.activity_web_info.*
import kotlinx.android.synthetic.main.inclue_web_title.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.collections.HashMap


/**
 * 带列表的web页
 */
class WebInfoActivity : BaseActivity() {

    var webInfoAdapter: WebInfoAdapter? = null
    var mData: MutableList<WebInfoBean> = mutableListOf()
    var strPath: String = ""
    var strType: String = ""

    override fun setLayoutId(): Int {
        return R.layout.activity_web_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)

    }


    override fun initView() {
        dialog()

        strPath = intent.getStringExtra("path")
        strType = intent.getStringExtra("type")
        tvPath.text = strPath
        when (strType) {
            "通知公告" -> {
                mData.add(
                    WebInfoBean(
                        "郑州市规范性文件数据库使用说明",
                        "2017/04/12",
                        "file:////android_asset/tongzhi1.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "关于暂停新冠肺炎健康申报证明办理服务工作的通知",
                        "2020-05-09",
                        "file:////android_asset/tongzhi2.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "河南省人民政府关于调整新冠肺炎疫情防控应急响应级别的通告",
                        "2020-05-05",
                        "file:////android_asset/tongzhi3.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市2020年春季学期非高三初三年级开学时间公告",
                        "2020-04-23",
                        "file:////android_asset/tongzhi4.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "关于《郑州市非机动车管理办法（征求意见稿）》向社会公开征求意见的通告",
                        "2020-04-14",
                        "file:////android_asset/tongzhi5.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "2019-2020年秋冬季郑州的空气质量如何？",
                        "2020-04-22",
                        "file:////android_asset/tongzhi6.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "公布《郑州市关于促消费增活力稳增长的若干举措》",
                        "2020-04-04",
                        "file:////android_asset/tongzhi7.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "河南省新冠肺炎疫情防控专题第四十八场新闻发布会",
                        "2020-04-08",
                        "file:////android_asset/tongzhi8.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市规范性文件备案材料格式和内容要求",
                        "2017/03/15",
                        "file:////android_asset/tongzhi9.html",
                        1
                    )
                )
            }
            "新闻中心" -> {
                mData.add(
                    WebInfoBean(
                        "第一报道 | 习近平：只有构建人类命运共同体才是人间正道",
                        "2020-05-08",
                        "http://www.xinhuanet.com/world/2020-05/08/c_1210609072.htm",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "中共中央政治局常务委员会召开会议 习近平主持",
                        "2020-05-06",
                        "http://www.xinhuanet.com/politics/leaders/2020-05/06/c_1125949374.htm",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平寄语新时代青年并向全国各族青年致以节日的祝贺和诚挚的问候",
                        "2020-05-03",
                        "http://www.xinhuanet.com/politics/leaders/2020-05/03/c_1125938927.htm",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平同葡萄牙总统德索萨通电话",
                        "2020-05-07",
                        "http://www.xinhuanet.com/politics/leaders/2020-05/07/c_1125954499.htm",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平同乌兹别克斯坦总统米尔济约耶夫通电话",
                        "2020-05-07",
                        "http://www.xinhuanet.com/politics/leaders/2020-05/07/c_1125954497.htm",
                        1
                    )
                )

            }
            "问卷调查" ->{
                mData.add(
                    WebInfoBean(
                        "英协路街道各社区便民服务满意度调查",
                        "",
                        "https://www.wjx.cn/jq/76503635.aspx",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "英协路智慧街道云平台使用满意度调查",
                        "",
                        "https://www.wjx.cn/jq/76489931.aspx",
                        1
                    )
                )
            }
            "投票管理"->{
                mData.add(
                    WebInfoBean(
                        "我最满意的社区",
                        "",
                        "https://tp.wjx.top/jq/76510652.aspx",
                        1
                    )
                )
            }
            "政策法规"->{
                mData.add(
                    WebInfoBean(
                        "郑州市贾鲁河保护条例",
                        "2020/01/13",
                        "file:////android_asset/news1.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市停车场建设管理条例",
                        "2019/08/30",
                        "file:////android_asset/news3.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市人民代表大会常务委员会关于修改部分地方性法规的决定",
                        "2019/01/24",
                        "file:////android_asset/news4.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市政府投资项目管理条例",
                        "2018/11/29",
                        "file:////android_asset/news5.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市人民代表大会常务委员会关于修改部分地方性法规的决定",
                        "2019/01/24",
                        "file:////android_asset/news6.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市文明行为促进条例",
                        "2018/07/06",
                        "file:////android_asset/news7.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市户外广告和招牌设置管理条例",
                        "2018/04/11",
                        "file:////android_asset/news8.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市湿地保护条例",
                        "2018/04/11",
                        "file:////android_asset/news9.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市建筑市场管理条例",
                        "2016/08/23",
                        "file:////android_asset/news10.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "郑州市轨道交通条例",
                        "2015/09/27",
                        "file:////android_asset/news11.html",
                        1
                    )
                )
            }
            "学习中心"->{
                mData.add(
                    WebInfoBean(
                        "四论学习贯彻习近平总书记参加河南代表团审议时的重要讲话精神",
                        "2020/05/06",
                        "file:////android_asset/study1.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "三论学习贯彻习近平总书记参加河南代表团审议时的重要讲话精神",
                        "2020/05/09",
                        "file:////android_asset/study4.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "全面提高依法防控依法治理能力 健全国家公共卫生应急管理体系",
                        "2020/04/15",
                        "file:////android_asset/study3.html",
                        1
                    )
                )

                mData.add(
                    WebInfoBean(
                        "二论学习贯彻习近平总书记参加河南代表团审议时的重要讲话精神",
                        "2020/03/12",
                        "file:////android_asset/study2.html",
                        1
                    )
                )

                mData.add(
                    WebInfoBean(
                        "习近平：增强推进党的政治建设的自觉性和坚定性",
                        "2020/05/07",
                        "file:////android_asset/study5.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平回信勉励北京大学援鄂医疗队全体“90后”党员",
                        "2020/04/07",
                        "file:////android_asset/study6.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平总书记在“不忘初心、牢记使命”主题教育总结大会上的讲话",
                        "2020/04/10",
                        "file:////android_asset/study7.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平总书记在决战决胜脱贫攻坚座谈会上的讲话",
                        "2020/03/21",
                        "file:////android_asset/study8.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "习近平总书记在统筹推进新冠肺炎疫情防控和经济社会发展工作部署会议上的讲话",
                        "2020/03/18",
                        "file:////android_asset/study9.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "一论学习贯彻习近平总书记参加河南代表团审议时的重要讲话精神",
                        "2020/02/18",
                        "file:////android_asset/study10.html",
                        1
                    )
                )
            }
            "党务中心"->{
                mData.add(
                    WebInfoBean(
                        "金水区直机关扎实开展“党章党规学习月” 活动",
                        "2020/05/12",
                        "file:////android_asset/party1.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "你“跑腿儿”服务大家 我为你带娃解后顾之忧",
                        "2020/05/12",
                        "file:////android_asset/party2.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "树牢绿色发展理念 ——三论学习贯彻习近平总书记参加河南代表团审议时的重要讲话精神",
                        "2020/05/12",
                        "file:////android_asset/party3.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "未来路办事处：探索“互联网+智慧党建”新模式",
                        "2020/05/12",
                        "file:////android_asset/party4.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "未来路街道：党建新地标 服务大平台——未来路街道积极探索安置区党群服务新模式",
                        "2020/05/12",
                        "file:////android_asset/party5.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "未来路街道：为党庆生，两支部组团到红色教育基地参观学习",
                        "2020/05/12",
                        "file:////android_asset/party6.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "未来路街道燕庄村：村官进党校 充电强素能",
                        "2020/05/12",
                        "file:////android_asset/party7.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "我党建中心同志参加“黄河沿岸健步走、步伐诠释工作情”健身活动",
                        "2020/05/12",
                        "file:////android_asset/party8.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "携手行动，践行绿色文明生活方式",
                        "2020/05/12",
                        "file:////android_asset/party9.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "英协路上的“红马甲”",
                        "2020/05/12",
                        "file:////android_asset/party10.html",
                        1
                    )
                )
            }
            "活动中心"->{
                mData.add(
                    WebInfoBean(
                        "妇女干部学校创新开展“五四精神 传承有我”线上主题党日活动",
                        "2020/05/12",
                        "file:////android_asset/activity1.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "河南煤监局豫北监察分局党建引领 推进“学法规、抓落实、强管理”深入开展",
                        "2020/05/12",
                        "file:////android_asset/activity2.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "河南省总工会机关开展“春天一起种下希望”网上云植树活动",
                        "2020/05/12",
                        "file:////android_asset/activity3.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "热血逆行 让爱流动 ——省政府办公厅组织开展无偿献血活动",
                        "2020/05/12",
                        "file:////android_asset/activity4.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "树牢绿色发展理念 ——三论学习贯彻习近平总书记参加河南代表团审议时的重要讲话精神",
                        "2020/05/12",
                        "file:////android_asset/activity5.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "省广播电视局举办“传递书香 分享阅读” 读书分享交流会",
                        "2020/05/12",
                        "file:////android_asset/activity6.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "省政府国资委组织开展“红色歌曲大家唱”活动",
                        "2020/05/12",
                        "file:////android_asset/activity7.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "省自然资源厅“童心向党”关爱儿童成长夏令营圆满结营",
                        "2020/05/12",
                        "file:////android_asset/activity8.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "省总机关举行读书交流活动暨青年理论学习小组第四期主题活动",
                        "2020/05/12",
                        "file:////android_asset/activity9.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "我们的节日，我们一起过",
                        "2020/05/12",
                        "file:////android_asset/activity10.html",
                        1
                    )
                )
                mData.add(
                    WebInfoBean(
                        "我省举行“世界艾滋病日”主题宣传活动",
                        "2020/05/12",
                        "file:////android_asset/activity11.html",
                        1
                    )
                )
            }
        }



        mData[0].isChoose = 2
        web_recycle.layoutManager = LinearLayoutManager(this)
        webInfoAdapter = WebInfoAdapter(mData)
        webInfoAdapter!!.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        webInfoAdapter!!.isFirstOnly(false)
        web_recycle.adapter = webInfoAdapter
        webInfoAdapter!!.onItemChildClickListener =
            BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
                when (view?.id) {
                    R.id.rlItem -> {
                        EventBus.getDefault().post(mData[position])
                        for (i in 0 until mData.size) {
                            mData[i].isChoose = 1
                        }
                        mData[position].isChoose = 2

                        webInfoAdapter!!.notifyDataSetChanged()
                    }
                }
            }


        var webSettings = webViewInfo.settings
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
        webSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放

        webViewInfo.loadUrl(mData[0].h5Url)
        webViewInfo.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                dialog!!.show()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                dialog!!.dismiss()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun WebInfo(webInfoBean: WebInfoBean) {
        webViewInfo.loadUrl(webInfoBean.h5Url)
    }


    override fun initListener() {

        llBack.setOnClickListener {
            finish();
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)

    }


}