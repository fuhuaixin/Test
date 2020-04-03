package com.example.test0.permission

import com.example.test0.permission.PermissionBean


/**
 * @Description: 权限相关
 * @Author: CTS
 * @Date: 2019/11/20 20:11
 */
class PermissionConstants {
    companion object {

        /**
         * 权限申请提示语常量
         */
        //拍照和存储
        open val PER_NOTE_CAMERA = PermissionBean(
            requestContent = "请授予拍照和存储权限，否则拍照将无法使用",
            deniedTitle = "拍照或存储权限已经被您拒绝",
            deniedContent = "可能会导致拍照功能无法正常使用,请前往设置开启拍照和存储权限"
        )

        open val PER_NOTE_STORAGE2LOCATION =
            PermissionBean(
                requestContent = "请授予存储和定位权限，否则部分功能将无法使用",
                deniedTitle = "存储或定位权限已经被您拒绝",
                deniedContent = "可能会导致部分功能无法正常使用,请前往设置开启存储和定位权限"
            )

        //存储
        open val PER_NOTE_STORAGE = PermissionBean(
            requestContent = "请授予存储权限，否则部分功能将无法使用",
            deniedTitle = "存储权限已经被您拒绝",
            deniedContent = "可能会导致部分功能无法正常使用,请前往设置开启存储权限"
        )

        //定位
        open val PER_NOTE_LOCATION = PermissionBean(
            requestContent = "请授予定位权限，否则定位将无法使用",
            deniedTitle = "定位权限已经被您拒绝",
            deniedContent = "可能会导致定位功能无法正常使用,请前往设置开启定位权限"
        )

        //通讯录读权限
        open val PER_NOTE_CONTACTS_READ =
            PermissionBean(
                requestContent = "请授予通讯录权限，否则该功能将无法使用",
                deniedTitle = "通讯录权限已经被您拒绝",
                deniedContent = "可能会导致该功能无法正常使用,请前往设置开启通讯录权限"
            )

        //通讯录、电话权限
        open val PER_NOTE_CONTACTS2 =
            PermissionBean(
                requestContent = "请授予通讯录和电话权限，否则该功能将无法使用",
                deniedTitle = "通讯录或电话权限已经被您拒绝",
                deniedContent = "可能会导致该功能无法正常使用,请前往设置开启通讯录和电话权限"
            )

        //短信权限
        open val PER_NOTE_SMS = PermissionBean(
            requestContent = "请授予发送短信权限，否则该功能将无法使用",
            deniedTitle = "发送短信权限已经被您拒绝",
            deniedContent = "可能会导致该功能无法正常使用,请前往设置开启短信权限"
        )

        //创建桌面快捷方式权限
        open val PER_NOTE_SHORTCUT = PermissionBean(
            requestContent = "请授予桌面快捷方式权限，否则该功能将无法使用",
            deniedTitle = "桌面快捷方式权限已经被您拒绝",
            deniedContent = "可能会导致该功能无法正常使用,请前往设置开启桌面快捷方式权限"
        )
    }

}