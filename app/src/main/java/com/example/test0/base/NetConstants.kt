package com.example.test0.base

class NetConstants {
    companion object {

        /**
         * 统一url
         */

        //开发环境
//        const val BASE_URL = "http://192.168.10.50:8055/"//开发环境
        const val BASE_URL = "http://117.160.157.90:8055/"//正式环境
//        const val BASE_URL2 = "http://192.168.10.50:8080/"//开发环境

        //办事指南前缀
        const val OnlineWorkBase = BASE_URL + "zhjd/onlinework.html?id="

        const val OpenDireBase = BASE_URL + "Data/yxlzhjd/voiceScreen/"

        //今日党史
        const val DangShiUrl = BASE_URL + "zhjd/server/govinfo/dangshi?datestr="
        //查询天气接口
        const val WeatherUrl =
            "https://tianqiapi.com/api?version=v6&appid=52796525&appsecret=2cBrl3hs&city=金水"
        //七日天气接口
        const val WeatherDaysUrl =
            "https://tianqiapi.com/api?version=v1&appid=52796525&appsecret=2cBrl3hs&city=金水"
        //实时疫情接口
        const val NowEpidemicUrl =
            "http://api.tianapi.com/txapi/ncov/index?key=8a14a867077fe537998673034bde9774"
        //获取在线办事列表
        const val GovinfoListUrl = BASE_URL + "zhjd/server/govinfo/list"
        //获取笑话
        const val getJokeUrl =
            "http://v.juhe.cn/joke/content/text.php?pagesize=20&key=f9beadc20ba2f1be943862a9650de422&page="
        //公开指南
        const val OpenDirectoryUrl = OpenDireBase + "OpenDirectory.json"
        //规范性文件
        const val NormativeUrl = OpenDireBase + "Normative.json"
        //区长信箱
        const val LetterBoxUrl = OpenDireBase + "LetterBox.json"
        //政务接口
        const val GovernmentUrl = OpenDireBase + "Government.json"
        //宣传平台
        const val PublicityUrl = OpenDireBase + "Publicity.json"


    }
}