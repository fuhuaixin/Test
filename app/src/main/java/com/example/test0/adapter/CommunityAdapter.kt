package com.example.test0.adapter


import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
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
            .setText(R.id.tv_number, item!!.phone)
            .setText(R.id.tv_name2,item!!.name2)
            .setText(R.id.tv_number2,item!!.phone2)

        var ll_item = helper.getView<LinearLayout>(R.id.ll_item)
        var view1 = helper.getView<View>(R.id.view1)
        var view2 = helper.getView<View>(R.id.view2)
        if (helper.position==0){
            view1.visibility =View.GONE
            view2.visibility =View.GONE
        }else{
            view1.visibility =View.VISIBLE
            view2.visibility =View.VISIBLE
        }

        if (helper.position %2==0){
            ll_item.setBackgroundColor(mContext.resources.getColor(R.color.clF2))
        }else{
            ll_item.setBackgroundColor(mContext.resources.getColor(R.color.white))
        }

    }

}