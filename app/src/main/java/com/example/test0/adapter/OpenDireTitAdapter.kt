package com.example.test0.adapter


import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.OpenDireTitleBean

class OpenDireTitAdapter(data: MutableList<OpenDireTitleBean>) :
    BaseQuickAdapter<OpenDireTitleBean, BaseViewHolder>(
        R.layout.adapter_open_title, data
    ) {
    override fun convert(helper: BaseViewHolder?, item: OpenDireTitleBean?) {


        helper!!.setText(R.id.tv_title, item!!.title)

        var ll_item = helper.getView<LinearLayout>(R.id.ll_item)
        if (item!!.choose){
            ll_item.setBackgroundResource(R.drawable.shape_open_title_sel)
        }else{
            ll_item.setBackgroundResource(R.drawable.shape_open_title_unsel)
        }

        helper.addOnClickListener(R.id.ll_item)
    }

}