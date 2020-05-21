package com.example.test0.adapter


import android.content.Context
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.CommunityBean
import com.example.test0.bean.JokeBean

class CommunityAdapter(data: MutableList<CommunityBean>) :
    BaseQuickAdapter<CommunityBean, BaseViewHolder>(
        R.layout.adapter_item_community, data
    ) {
    override fun convert(helper: BaseViewHolder?, item: CommunityBean?) {


        helper!!.setText(R.id.tv_name, item!!.name)
            .setText(R.id.tv_address, item!!.address)
            .setText(R.id.tv_busline, item!!.busLine)
            .setText(R.id.tv_number, item!!.phone)

        var ll_item = helper.getView<LinearLayout>(R.id.ll_item)
        if (helper.position==0){
            ll_item.setBackgroundColor(mContext.resources.getColor(R.color.lineColor))
        }else{
            ll_item.setBackgroundColor(mContext.resources.getColor(R.color.white))
        }

    }

}