package com.example.test0.adapter

import android.content.Context
import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import java.security.AccessControlContext

class StreetMainAdapter(context: Context,data:MutableList<String>,var choose:Int):BaseQuickAdapter<String,BaseViewHolder>(
    R.layout.adapter_item_street_mian,data
) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        var rl_bg = helper!!.getView<RelativeLayout>(R.id.rl_bg)
        when(choose){
            1 ->{
                rl_bg.setBackgroundResource(R.drawable.icon_btn_bg_red)
            }
            2 ->{
                rl_bg.setBackgroundResource(R.drawable.icon_btn_bg)
            }
        }

        helper!!.setText(R.id.tv_name,item)

        helper.addOnClickListener(R.id.rl_bg)
    }

}