package com.example.test0.base

import android.graphics.Color
import android.os.Bundle
import com.zyao89.view.zloading.ZLoadingDialog
import com.zyao89.view.zloading.Z_TYPE
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.app.SwipeBackActivity


/**
 * @Description: 基类
 * @Author: CTS
 * @Date:  2018/6/21
 *
 * @Note 添加EventBus可选初始化
 *       添加了ViewModel初始化
 *       添加初始化空页面
 *       添加loading dialog
 *       添加修改字体大小比例
 *       添加动态权限申请
 */
abstract class BaseActivity : SwipeBackActivity() {

    var dialog: ZLoadingDialog? = null
    private var mSwipeBackLayout: SwipeBackLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        // 可以调用该方法，设置是否允许滑动退出
        setSwipeBackEnable(true);
        mSwipeBackLayout = swipeBackLayout;
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout!!.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        //mSwipeBackLayout.setEdgeSize(200);
        initParams()
        initCommonView()
        initView()
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }

    /**
     * dialog相关
     */
    fun dialog() {
        dialog = ZLoadingDialog(this)
        dialog!!.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)
            .setLoadingColor(Color.parseColor("#eeeeee"))
            .setHintText("加载中...")
            .setHintTextSize(14F)
            .setHintTextColor(Color.parseColor("#eeeeee"))
            .setDialogBackgroundColor(Color.parseColor("#CC111111"))
            .setDurationTime(1.3)

    }


    //以下是模板方法
    /**
     * 设置布局文件id
     */
    abstract fun setLayoutId(): Int

    /**
     * 可选
     * 初始化参数，一般用于获取intent中携带的参数，在initView()之前运行
     */
    open fun initParams() {}

    /**
     * 可选
     * 初始化一些通用view
     */
    open fun initCommonView() {}

    /**
     * 初始化View（仅初始化view，在onCreate中初始化，涉及到数据的不放在这里）
     */
    abstract fun initView()

    /**
     * 初始化Listener
     */
    abstract fun initListener()


}