package com.example.test0.utlis

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.io.File

/**
 * @Description: Activity拓展函数
 * @Author: CTS
 * @Date: 2019/6/18 14:39
 */

/**
 * 隐藏软键盘
 */
inline fun Activity.hintKeyBoard() {
    //拿到InputMethodManager
    var imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //如果window上view获取焦点 && view不为空
    if (imm.isActive && currentFocus != null) {
        //拿到view的token 不为空
        if (currentFocus!!.windowToken != null) {
            //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
            imm.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            ); }
    }
}

/**
 * 使edittext获得焦点并弹出软键盘
 */
inline fun showSoftInputForEt(editText: EditText) {
    editText.isEnabled = true
    editText.isFocusable = true
    editText.isFocusableInTouchMode = true
    editText.requestFocus()
    var inputManager =
        editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.showSoftInput(editText, 0)
}

/**
 * 取ViewModel
 */
inline fun <T : ViewModel> FragmentActivity.getViewModel(viewModelClass: Class<T>): T {
//    return ViewModelProviders.of(this, ViewModelFactory.getINSTANCE(application)).get(viewModelClass)
    return ViewModelProviders.of(this).get(viewModelClass)
}

/**
 * 通过id取颜色
 */
inline fun Activity.getColorById(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

/**
 * 通知相册刷新（一般用于保存照片之后）
 */
inline fun Activity.noticeAlbumRefresh(filePath: String): MediaScannerConnection? {
    try {
        // 通知图库更新
        val file0 = File(filePath)
        val mediaScannerConnection = MediaScannerConnection(this, object :
            MediaScannerConnection.MediaScannerConnectionClient {
            override fun onMediaScannerConnected() {
            }

            override fun onScanCompleted(p0: String?, uri: Uri?) {
                val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                mediaScanIntent.data = uri
                sendBroadcast(mediaScanIntent)
            }

        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mediaScannerConnection.scanFile(filePath, null)
        } else {
            val relationDir = file0.parent
            if (relationDir != null && relationDir != "") {
                sendBroadcast(
                    Intent(
                        Intent.ACTION_MEDIA_MOUNTED,
                        Uri.fromFile(File(relationDir).absoluteFile)
                    )
                )
            }
        }

        return mediaScannerConnection

    } catch (e: Exception) {
        Log.i("Activity扩展函数", "通知相册刷新出现异常：${e.message}")
    } finally {
    }
    return null

}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    /**
     *  commit方法是在Activity的onSaveInstanceState()之后调用的，这样会出错，因为onSaveInstanceState
     *  方法是在该Activity即将被销毁前调用，来保存Activity数据的，如果在保存玩状态后再给它添加Fragment就会出错。解决办法就
     *  是把commit（）方法替换成 commitAllowingStateLoss()就行了，其效果是一样的。
     */
    beginTransaction().apply {
        action()
    }.commitAllowingStateLoss()
}

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        add(frameId, fragment)
    }
}


fun AppCompatActivity.showFragmentToActivity(fragment: Fragment) {
    supportFragmentManager.transact {
        show(fragment)
    }
}

fun AppCompatActivity.hindFragment(fragment: Fragment) {
    supportFragmentManager.transact {
        hide(fragment)
    }
}