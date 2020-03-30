package com.example.test0.base

class NetConstants {
    companion object {

        /**
         * 统一url
         */
        //主域名
//        const val BASE_URL = "https://www.chengshizhichuang.com/"//正式环境
//        const val BASE_URL_PAY = "https://www.chengshizhichuang.com:9701/"//正式环境-支付

        //开发环境
        const val BASE_URL = "http://192.168.10.133:8089/"//开发环境
        const val BASE_URL_PAY = "http://192.168.130.131:9701/"//开发环境-支付


        //测试环境
//        const val BASE_URL = "http://192.168.130.39/"//测试环境
//        const val BASE_URL_PAY = "http://192.168.130.39:9702/"//测试环境-支付

        //外网测试环境
//        const val BASE_URL = "http://test.chengshizhichuang.com/"//外网测试环境
//        const val BASE_URL_PAY = "http://test.chengshizhichuang.com:9701/"//外网测试环境-支付

//      外网测试环境2
//        const val BASE_URL = "http://111.6.79.28/"//外网测试环境
//        const val BASE_URL_PAY = "http://111.6.79.28:9701/"//外网测试环境-支付
        /**
         * 单独模块url头
         */
        //我的
        const val API_URL = "dygabase-app/"

        const val IM_CIRCLE = ""

        //内容管理（协议，新闻，广告图。例如首页banner，公益头条等）
        const val API_URL_MENT = "dygacontent-management-app/"

        //医疗
        const val API_MEDICAL = "dygamedical-app/"
        //智慧医疗（不修改）
        const val BASE_URL_MED = BASE_URL + API_MEDICAL

        //信用通
        const val API_CREDIT_URL = "dygacredit-app/"

        //充值中心
        const val API_URL_VOUCHERCENTER = "dygarecharge-app/"

        //公益一帮一月捐
        const val API_URL_PUBW = "dygawelfare-app/"

        //卡包
        const val API_URL_CARD_PACKAGE = "dygacard-app/"

        //年卡
        const val API_URL_CARD_YEAR = "dygacard-app/"

        //基础模块，包含登录注册相关
        const val API_URL_USER = "dygabase-app/"

        //生活服务
        const val API_URL_SL = "dygaliving-payment-app/"

        //红星
        const val API_URL_REDSTAR = "dygaredstar-app/dygaRedStar/"

        //智慧运动
        const val API_URL_WISESPORT = "dygasport-app/"

        //银行卡
        const val API_URL_BK = "dygacard-app/"

        //我的客服
        const val API_URL_SERVICE = "dygacustomerservice-app/CSApi/"

        //支付
        const val API_URL_PAYMENT = "dygapayment-app/"

        //推送相关
        const val API_URL_PUSH = "dygapush-app/"

        /**
         * 以下是.net接口
         */
        //医疗基础接口
        const val API_URL_MED_COMMON = "api/Common/"

        //科普
        const val API_URL_MED_KP = "api/KPInfo/"

        //远程诊断
        const val API_URL_MED_LD = "api/LongrangeDiagnose/"

        //寻找医生
        const val API_URL_MED_DOC = "api/Doctor/"

        //健康卡
        const val API_URL_HLCARD = "api/HealthCard/"

        //医疗百科
        const val API_URL_ENCY = "healthencyclopedia-app/"


        const val API_AUTH_VERSION = "dyauth/api/v1/"

    }
}