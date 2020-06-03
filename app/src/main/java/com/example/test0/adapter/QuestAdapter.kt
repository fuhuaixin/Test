package com.example.test0.adapter


import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.OpenDireMesBean

class QuestAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(
        R.layout.adapter_item_question, data
    ) {
    override fun convert(helper: BaseViewHolder?, item: String?) {


        helper!!.setText(R.id.tv_title, item)

        helper.addOnClickListener(R.id.ll_item)
    }

}