package com.example.test0.base

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.annotation.Size
import androidx.appcompat.app.AppCompatActivity
import com.example.test0.utlis.PermissionsInterface
import com.example.test0.view.EmptyView
import com.zytdsj.citywindow.lib_project.view.LoadingViewInterface
import org.greenrobot.eventbus.EventBus
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest

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
abstract class BaseActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    private var TAG0 = "BaseActivity"
    private var isEventBus = false
    var emptyView: EmptyView? = null//无数据页面


    //权限申请回调接口
    private var permissionsInterface: PermissionsInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())

        initParams()
        initCommonView()
        initView()
        initListener()
        initViewModelListener()
        getData(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isEventBus) {
            EventBus.getDefault().unregister(this)//反注册EventBus
        }

    }

    //以下是通用方法
    //不可重写
    fun setEventBus() {
        isEventBus = true
        //初始化EventBus
        EventBus.getDefault().register(this)
    }

    //不可重写
    fun setEmptyView() {
        emptyView = EmptyView(this, null)
    }


//    /**
//     * 取ViewModel
//     * 不可重写
//     */
//    fun <T : ViewModel> getViewModel(viewModelClass: Class<T>): T {
//
//        return  getViewModel(viewModelClass)
//    }


    //以下是私有方法
    /**
     * 修改字体大小比例
     */
    private fun initFontScale(fontScale: Float) {
        var configuration = resources.configuration
        configuration.fontScale = fontScale
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        var metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        resources.updateConfiguration(configuration, metrics)
    }

    /**
     * 权限相关-开始
     * 请求权限
     * [requestCode] 此次请求标识，回调时用此区分（暂时不传，暂时在接口中传递）
     * [perms] 权限列表
     * [permissionsInterface] 回调函数
     */
    private var mapPer: HashMap<Int, List<String>> = HashMap()//这个暂时没意义，一个activity同一时间只调一次请求权限
    //    private var listPers = arrayListOf<String>()//缓存权限列表
    private var permissionBean: PermissionBean = PermissionBean()//提示语bean

    fun requestPermissions0(
        permissionsInterface: PermissionsInterface,
        note: PermissionBean = PermissionBean(),
        @Size(min = 1) vararg perms: String
    ) {
        this.permissionsInterface = permissionsInterface
        this.permissionBean = note
//        this.mapPer[permissionsInterface.resultCode()] = perms.toMutableList()

        permissionsInterface?.let {

            if (EasyPermissions.hasPermissions(this, *perms)) {
                it.onGrantedAll(it.resultCode(), perms.toList())
                it.onGranted(it.resultCode(), perms.toList())
            } else {
                //过滤掉已经同意的权限
                var ls = arrayListOf<String>()
                perms.filter { !EasyPermissions.hasPermissions(this, it) }
                    .forEach { ls.add(it) }
//                listPers.clear()
//                listPers.addAll(ls)
                this.mapPer[permissionsInterface.resultCode()] = ls
                var persTemp = ls.toTypedArray()

                EasyPermissions.requestPermissions(
                    PermissionRequest.Builder(this, permissionsInterface.resultCode(), *persTemp)
                        .setRationale(permissionBean.requestContent)
                        .setPositiveButtonText(permissionBean.requestSure)
                        .setNegativeButtonText(permissionBean.requestCancel)
                        //.setTheme(R.style.my_fancy_style)//弹窗样式
                        .build()
                )
            }
        }
    }

    //EasyPermissions必须重写的方法
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    //同意权限
    override fun onPermissionsGranted(requestCode: Int, list: List<String>) {
        // Some permissions have been granted
        permissionsInterface?.let {
            //请求code匹配时，才回调
            if (it.resultCode() == requestCode) {
                if (list.size == mapPer[requestCode]?.size ?: 0) {
                    var isAll = true
                    list.forEach { it0 ->
                        if (mapPer[requestCode]?.contains(it0) == false) {
                            isAll = false
                            return@forEach
                        }
                    }
                    if (isAll) {
                        it.onGrantedAll(requestCode, list)
                    }
                    it.onGranted(requestCode, list)
                } else {
                    it.onGranted(requestCode, list)
                }
            }
        }
    }

    //拒绝权限
    override fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        // Some permissions have been denied
        //判断用户是否点击了不在提醒
        if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
            AppSettingsDialog.Builder(this)
                .setTitle(permissionBean.deniedTitle)
                .setRationale(permissionBean.deniedContent)
                .setPositiveButton(permissionBean.deniedSure)
                .setNegativeButton(permissionBean.deniedCancel)
                .setRequestCode(AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE)//用于onActivityResult回调做其它对应相关的操作
                .build()
                .show()
        } else {
//            //这里需要注意的是如果用户把所有的权限都拒绝了，就给出提示框
//            if (list.size >= 6) {
            AppSettingsDialog.Builder(this)
                .setTitle(permissionBean.deniedTitle)
                .setRationale(permissionBean.deniedContent)
                .setPositiveButton(permissionBean.deniedSure)
                .setNegativeButton(permissionBean.deniedCancel)
                .setRequestCode(AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE)//用于onActivityResult回调做其它对应相关的操作
                .build()
                .show()
//            } else {
////                //如果用户没有全部拒绝那么就再次请求权限
////                EasyPermissions.requestPermissions(
////                    PermissionRequest.Builder(this, requestCode, list)
////                        .setRationale("测试")
////                        .setPositiveButtonText("确认")
////                        .setNegativeButtonText("取消")
//////                .setTheme(R.style.my_fancy_style)
////                        .build()
////                )
//            }
        }
        permissionsInterface?.let {
            //请求code匹配时，才回调
            if (it.resultCode() == requestCode) {
                it.onDenied(requestCode, list)
            }
        }
    }

    //回调，用于在设置中设置权限后返回
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
            for ((key, value) in mapPer) {
                if (EasyPermissions.hasPermissions(this, * value.toTypedArray())) {
                    //如果从设置返回后，所有权限都已申请。则回调
                    permissionsInterface?.let {
                        it.onGrantedAll(
                            key,
                            value
                        )
                        it.onGranted(
                            key,
                            value
                        )
                    }
                }
            }

        }
    }
    //权限相关-结束


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

    /**
     * 初始化viewModel的监听器
     */
    abstract fun initViewModelListener()

    /**
     * 取数据（当前页面所有数据获取，如果刷新数据直接调用此方法）
     */
    abstract fun getData(isRefresh: Boolean)

}