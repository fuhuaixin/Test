package com.example.test0.utlis

/**
 * @Description: 权限申请回调接口
 * @Author: CTS
 * @Date: 2019/11/19 20:34
 */
interface PermissionsInterface {
    fun resultCode(): Int {
        return 901//默认值，如果一个页面有多次权限申请，可以设置此值
    }

    fun onGranted(requestCode: Int, list: List<String>) {}
    fun onGrantedAll(requestCode: Int, list: List<String>)
    fun onDenied(requestCode: Int, list: List<String>)
}
