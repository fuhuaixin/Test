package com.example.test0.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.example.test0.R
import com.example.test0.utlis.SizeUtils


class EmptyView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var iv: ImageView
    private var tv: TextView
    private var bt: Button

    init {
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.layout_empty, this)

        // 获取控件
        iv = findViewById(R.id.layout_empty_img)
        tv = findViewById(R.id.layout_empty_text)
        bt = findViewById(R.id.fancyBt)
    }

    fun setViewType(type: EmptyViewType) {
        if (context != null && !(context as Activity).isFinishing && !(context as Activity).isDestroyed) {
            when (type) {
                EmptyViewType.NODATA -> {//暂无数据用的默认的，或者自定义的logo
//                    iv.setImageResource(R.drawable.ic_empty1)
//                    tv.text = "暂无数据~"
                    bt.visibility = View.GONE
                }
                EmptyViewType.NONETWORK -> {
                    iv.setImageResource(R.drawable.ic_no_net)
                    tv.text = "网络开小差了，请联网后重试~"
                    bt.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * 设置图片资源id
     */
    fun setImageResId(@DrawableRes resId: Int) {
        iv.setImageResource(resId)
    }

    /**
     * 设置显示字段
     */
    fun setTextStr(str: String) {
        tv.text = str
    }

    /**
     * 自定义图文间距
     */
    fun setPadding(size_dp: Float) {
        tv.setPadding(0, SizeUtils.dp2px(size_dp), 0, 0)
    }

    enum class EmptyViewType {
        NODATA, NONETWORK
    }
}
