package com.example.test0.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.VoiceNavBean
import com.example.test0.bean.WebInfoBean

class WebInfoAdapter (data:MutableList<WebInfoBean>):
    BaseQuickAdapter<WebInfoBean, BaseViewHolder> (
        R.layout.adapter_item_web_info,data
    ) {
    override fun convert(helper: BaseViewHolder?, item: WebInfoBean?) {
        helper!!.setText(R.id.tvTime,item!!.date)
            .setText(R.id.tvTitle,item!!.title)
        var tvTime = helper.getView<TextView>(R.id.tvTime)
        var tvTitle = helper.getView<TextView>(R.id.tvTitle)
        var rlItem = helper.getView<RelativeLayout>(R.id.rlItem)
        var imageInto = helper.getView<ImageView>(R.id.imageInto)

        if (item.date == ""){
            tvTime.visibility =View.GONE
        }else{
            tvTime.visibility =View.VISIBLE
        }

        if (item.isChoose==2){
            tvTime.setTextColor(Color.parseColor("#ffffff"))
            tvTitle.setTextColor(Color.parseColor("#ffffff"))
            rlItem.setBackgroundColor(Color.parseColor("#1C87F4"))
            imageInto.visibility =View.VISIBLE
        }else {
            tvTime.setTextColor(Color.parseColor("#282828"))
            tvTitle.setTextColor(Color.parseColor("#282828"))
            rlItem.setBackgroundColor(Color.parseColor("#E7E7E7"))
            imageInto.visibility =View.GONE
        }
        helper.addOnClickListener(R.id.rlItem)
    }
}