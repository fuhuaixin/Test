package com.example.test0.adapter


import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.*

class NormaAdapter(data: MutableList<NormativeBean.DateBean>?) :
    BaseQuickAdapter< NormativeBean.DateBean, BaseViewHolder>(
        R.layout.adapter_item_normative, data
    ) {
    override fun convert(helper: BaseViewHolder?, item:  NormativeBean.DateBean?) {


        helper!!.setText(R.id.tv_mes_title, item!!.title)
            .setText(R.id.tv_mes_jg,item.publish)
            .setText(R.id.tv_mes_time,item.time)
        var ll_item = helper.getView<LinearLayout>(R.id.ll_item)


        helper.addOnClickListener(R.id.ll_item)
    }

}