package com.example.test0.permission

/**
 * @Description: 权限回调bean
 * @Author: CTS
 * @Date: 2019/11/20 14:49
 */
class PermissionBean(
    var requestTitle: String = "提示",//申请前提示标题
    var requestContent: String = "请授予权限，否则将导致部分功能无法使用",//申请前提示内容
    var requestSure: String = "确认",//确认按钮内容
    var requestCancel: String = "取消",//取消按钮内容
    var deniedTitle: String = "权限已经被您拒绝",//拒绝后再次申请提示标题
    var deniedContent: String = "可能会导致您的某些功能不能正常使用,前往设置打开权限",//拒绝后再次申请提示内容
    var deniedSure: String = "前往设置",//拒绝后再次申请提示前往设置按钮
    var deniedCancel: String = "取消"//拒绝后再次申请提示取消按钮
)