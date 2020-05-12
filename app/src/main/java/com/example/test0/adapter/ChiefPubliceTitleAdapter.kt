package com.example.test0.adapter

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.CheifPubTitleBean

class ChiefPubliceTitleAdapter(context: Context, data:MutableList<CheifPubTitleBean>):
    BaseQuickAdapter<CheifPubTitleBean, BaseViewHolder>(
        R.layout.item_chief_public,data
    )  {
    override fun convert(helper: BaseViewHolder?, item: CheifPubTitleBean?) {
        helper!!.setText(R.id.tv_title, item!!.title)
        val ll_item = helper!!.getView<LinearLayout>(R.id.ll_item)
        val line = helper.getView<View>(R.id.view_line)

        if (item.choose) {
            ll_item.setBackgroundResource(R.color.white)
            line.visibility = View.VISIBLE
        } else {
            ll_item.setBackgroundResource(R.color.crF7)
            line.visibility = View.GONE
        }

        helper.addOnClickListener(R.id.ll_item)
    }
}