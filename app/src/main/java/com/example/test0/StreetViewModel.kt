//package com.example.test0
//
//import android.app.Application
//import com.example.test0.base.NetConstants
//import com.zhouyou.http.EasyHttp
//import com.zhouyou.http.body.UIProgressResponseCallBack
//import com.zhouyou.http.exception.ApiException
//import com.zytdsj.citywindow.lib_project.base.BaseViewModel
//import com.zytdsj.citywindow.lib_project.bean.HlCardDetailModel
//import com.zytdsj.citywindow.lib_project.bean.UploadPathResultModel
//import com.zytdsj.citywindow.lib_project.bean.base.ErrorMsg
//import com.zytdsj.citywindow.lib_project.constants.NetConstants
//import com.zytdsj.citywindow.lib_project.net.callback.CustomCallBack
//import com.zytdsj.citywindow.lib_project.net.utils.ParamUtils
//import com.zytdsj.citywindow.lib_project.view.LoadingViewInterface
//import com.zytdsj.citywindow.module_card.ui.cardfriends.bean.*
//import com.zytdsj.citywindow.mvvm.RefreshTagModel
//import com.zytdsj.citywindow.mvvm.SingleLiveEvent
//import io.reactivex.disposables.Disposable
//import java.io.File
//
//class StreetViewModel(context: Application) : BaseViewModel(context) {
//
//    //当前页码
//    private var pagerIndex: Int = 1
//    //每页条数
//    private  var pageSize =10
//
//    //上传通讯录
//    var uploadUrl = "dycardbagfriends/api/v1/addressBookInfo/upload"
//    val upLoadModel = SingleLiveEvent<Any>()
//
//    //获取好友会话列表
//    var selectListUrl = "dycardbagfriends/api/v1/session/selectList"
//    val selectListModel = SingleLiveEvent<MutableList<CardFriendsBean>>()
//
//
//
//    /**
//     * 上传通讯录
//     */
//    fun getCardLoad(contact: List<Contact>) {
//        EasyHttp.post(uploadUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "contact" to contact
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<Any>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: Any) {
//                    upLoadModel.value = t
//                }
//            })
//    }
//
//    /**
//     * 获取好友会话列表
//     */
//    fun selectList() {
//        EasyHttp.post(selectListUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<MutableList<CardFriendsBean>>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: MutableList<CardFriendsBean>) {
//                    selectListModel.value = t
//                }
//            })
//    }
//
//    /**
//     * 获取好友列表
//     */
//    fun bookSelectList(
//        isRefresh: Boolean
//        ) {
//        if (isRefresh) pagerIndex = 1 else pagerIndex += 1//计算页数
//        EasyHttp.post(bookSelectListUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "index" to pagerIndex,
//                        "pageSize" to pageSize
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<CardBookInfoBean>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t:CardBookInfoBean) {
//                    if (isRefresh) {
//                        //下拉刷新
//                        bookSelectModel.value = RefreshTagModel(0, t)
//                    } else {
//                        //上拉加载
//                        bookSelectModel.value = RefreshTagModel(1, t)
//                    }
//                }
//            })
//    }
//
//    /**
//     * 添加通讯录好友
//     */
//    fun friendsUpData(id: Int) {
//        EasyHttp.post(friendsUpUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "id" to id
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<Any>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: Any) {
//                    friendsUpModel.value = t
//                }
//            })
//    }
//
//    /**
//     * 查询所有卡
//     */
//    fun queryCard(friendId :String) {
//        EasyHttp.post(queryCardUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "friendId" to friendId
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<MutableList<SendCardBean>>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: MutableList<SendCardBean>) {
//                    queryCardModel.value = t
//                }
//            })
//    }
//
//    /**
//     * 用户卡赠送好友
//     */
//    fun donateFriend(
//        friendId: String,
//        cardId: String,
//        imageUrl: String,
//        logoUrl: String,
//        title: String,
//        cardType: Int,
//        totalNum: String,
//        sendedNum: String
//    ) {
//        EasyHttp.post(donateFriendUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "friendId" to friendId,
//                        "cardId" to cardId,
//                        "imageUrl" to imageUrl,
//                        "logoUrl" to logoUrl,
//                        "title" to title,
//                        "cardType" to cardType,
//                        "totalNum" to totalNum,
//                        "sendedNum" to sendedNum
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<SendCardModel>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: SendCardModel) {
//                    donateFriendModel.value = t
//                }
//            })
//    }
//
//
//    /**
//     * 删除和置顶
//     */
//    fun deleteOrStick(operateType: Int, sessionId: String) {
//        EasyHttp.post(deleteOrStickUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "operateType" to operateType,
//                        "sessionId" to sessionId
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<Any>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: Any) {
//                    deleteOrStickModel.value = t
//                }
//            })
//    }
//
//    /**
//     * 获取聊天详情
//     */
//    fun selectChatInfo(friendId: String, sessionId: String) {
//        EasyHttp.post(selectChatInfoUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "friendId" to friendId,
//                        "sessionId" to sessionId
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<MutableList<CardChatInfoBean>>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: MutableList<CardChatInfoBean>) {
//                    selectChatInfoModel.value = t
//                }
//            })
//    }
//
//    /**
//     * 根据通讯录昵称搜索会话信息
//     */
//    fun selectByName(search: String) {
//        EasyHttp.post(selectByNameUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "search" to search
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<MutableList<CardFriendsBean>>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: MutableList<CardFriendsBean>) {
//                    selectByNameModel.value = t
//                }
//            })
//    }
//    /**
//     * 根据通讯录好友昵称搜索卡包好友
//     */
//    fun addressBookInfo(search: String,searchType :Int) {
//        EasyHttp.post(addressBookInfoUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                        "search" to search
//                    ,"searchType" to searchType
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<CardSearchFridBean>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: CardSearchFridBean) {
//                    addressBookInfoModel.value = t
//                }
//            })
//    }
//
//
//    /**
//     * 获取红点状态，状态为0没有新增好友 1有新增好友
//     */
//    fun findRedPointStatus() {
//        EasyHttp.post(findRedPointStatusUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<RedPoint>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: RedPoint) {
//                    findRedPointStatusModel.value = t
//                }
//            })
//    }
//
//
//  /**
//     * 删除红点状态，状态为0没有新增好友 1有新增好友
//     */
//    fun deleteRedPointStatus() {
//        EasyHttp.post(deleteRedPointStatusUrl)
//            .baseUrl(NetConstants.BASE_URL)
//            .upJson(
//                ParamUtils.instance.generateJsonParam(
//                    mutableMapOf(
//                    ), true
//                )
//            )
//            .execute(object :
//                CustomCallBack<RedPoint>() {
//                override fun onError0(e: ApiException) {
//                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: RedPoint) {
//                    deleteRedPointStatusModel.value = t
//                }
//            })
//    }
//
//
//
//
//
//    /**
//     * 上传文件
//     */
//
//    var onUpLoadStrSuccess = SingleLiveEvent<ArrayList<String>>()
//
//    var onUpLoadUrlError = SingleLiveEvent<Any>()
//    fun uploadingFile(
//        loadingDialog: LoadingViewInterface?,
//        pathList: ArrayList<String>
//    ): Disposable {
//        var ls_file = ArrayList<File>()
//        for (item in pathList) {
//            ls_file.add(File(item))
//        }
//        return EasyHttp.post(NetConstants.API_URL + "file/upload")
//            .addFileParams("avatarfile", ls_file,null)
//            .accessToken(true)
//            .execute(object : CustomCallBack<ArrayList<UploadPathResultModel>>(loadingDialog, true, null) {
//                override fun onError0(e: ApiException) {
//                    onUpLoadUrlError.value = e.message
////                    onErr.value = ErrorMsg(e.code, e.message ?: "服务异常")
//                }
//
//                override fun onSuccess0(t: ArrayList<UploadPathResultModel>) {
////                    onUploadSuccess.value = t
//                    if (t != null && t.size > 0) {
//                        var strList: ArrayList<String> = arrayListOf()
//                        for (element in t) {
//                            strList.add(element.fileUrl)
//                        }
//                        onUpLoadStrSuccess.value = strList
//                    }
//                }
//            })
//    }
//
//
//}