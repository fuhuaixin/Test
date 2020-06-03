package com.example.test0.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.*

class HistroyTodayAdapter(data: MutableList<HistoryTodayBean.DataBean>?) :
    BaseQuickAdapter<HistoryTodayBean.DataBean, BaseViewHolder>(
        R.layout.adapter_history_today, data
    ) {
    override fun convert(helper: BaseViewHolder?, item: HistoryTodayBean.DataBean?) {

        helper!!.setText(R.id.tv_title, item!!.title)
            .setText(R.id.tv_mes, item.content)
    }

}