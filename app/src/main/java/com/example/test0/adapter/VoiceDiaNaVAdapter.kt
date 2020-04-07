package com.example.test0.adapter

import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.test0.R
import com.example.test0.bean.VoiceNavBean

class VoiceDiaNaVAdapter (context: Context, data:List<VoiceNavBean>):
    BaseQuickAdapter<VoiceNavBean, BaseViewHolder>(
        R.layout.adapter_item_voice_nav,data
    ) {
    override fun convert(helper: BaseViewHolder?, item: VoiceNavBean?) {
        helper!!.setText(R.id.tvTitle,item!!.title)
            .setText(R.id.tvMessage,item!!.message)

        var viewLine = helper.getView<View>(R.id.viewLine)
        if (helper.position===data.size-1){
            viewLine.visibility =View.GONE
        }

        helper.addOnClickListener(R.id.rlItem)
    }
}