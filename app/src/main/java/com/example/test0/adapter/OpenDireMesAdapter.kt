package com.example.test0.adapter


import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.JokeBean
import com.example.test0.bean.OpenDireBean
import com.example.test0.bean.OpenDireMesBean
import com.example.test0.bean.OpenDireTitleBean

class OpenDireMesAdapter(data: MutableList<OpenDireMesBean>) :
    BaseQuickAdapter<OpenDireMesBean, BaseViewHolder>(
        R.layout.adapter_open_mes, data
    ) {
    override fun convert(helper: BaseViewHolder?, item: OpenDireMesBean?) {


        helper!!.setText(R.id.tv_mes_title, item!!.title)
            .setText(R.id.tv_mes_jg, item.publish)
            .setText(R.id.tv_mes_time, item.sendDate)

        helper.addOnClickListener(R.id.ll_item)
    }

}