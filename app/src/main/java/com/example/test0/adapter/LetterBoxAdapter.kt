package com.example.test0.adapter


import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.*

class LetterBoxAdapter(data: MutableList<LetterBoxBean.DateBean>?) :
    BaseQuickAdapter< LetterBoxBean.DateBean, BaseViewHolder>(
        R.layout.adapter_item_letter, data
    ) {
    override fun convert(helper: BaseViewHolder?, item:  LetterBoxBean.DateBean?) {


        helper!!.setText(R.id.tv_mes_title, item!!.title)
            .setText(R.id.tv_time,item.time)
            .setText(R.id.tv_type,item.type)
            .setText(R.id.tv_mes_pro,item.progress)
        var ll_item = helper.getView<LinearLayout>(R.id.ll_item)


        helper.addOnClickListener(R.id.ll_item)
    }

}