package com.example.test0.adapter

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.CheifPubMessageBean

class ChiefPubliceMessageAdapter (context: Context, data:MutableList<CheifPubMessageBean>):
    BaseQuickAdapter<CheifPubMessageBean, BaseViewHolder>(
        R.layout.item_chief_public_message,data
    )   {
    override fun convert(helper: BaseViewHolder?, item: CheifPubMessageBean?) {

        helper!!.setText(R.id.tv_title, item!!.message)
        val tv_gone = helper.getView<LinearLayout>(R.id.ll_gone)
        if (item.choose) {
            tv_gone.visibility = View.VISIBLE
        } else {
            tv_gone.visibility = View.GONE
        }
        helper.addOnClickListener(R.id.ll_item, R.id.tv_guide, R.id.tv_online)

    }
}