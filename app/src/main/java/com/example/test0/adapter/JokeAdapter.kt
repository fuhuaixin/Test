package com.example.test0.adapter


import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.JokeBean

class JokeAdapter(data: MutableList<JokeBean.ResultBean.DataBean>) :
    BaseQuickAdapter<JokeBean.ResultBean.DataBean, BaseViewHolder>(
        R.layout.adapter_item_joke, data
    ) {
    override fun convert(helper: BaseViewHolder?, item: JokeBean.ResultBean.DataBean?) {


        helper!!
            .setText(R.id.tv_content, item!!.content)
            .setText(R.id.tv_time, item!!.updatetime)

    }

}