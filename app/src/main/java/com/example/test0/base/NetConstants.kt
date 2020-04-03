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

        //查询天气接口
        const val WeatherUrl ="https://tianqiapi.com/api?version=v6&appid=52796525&appsecret=2cBrl3hs&city=郑州"
        //实时疫情接口
        const val NowEpidemicUrl ="http://api.tianapi.com/txapi/ncov/index?key=8a14a867077fe537998673034bde9774"

    }
}