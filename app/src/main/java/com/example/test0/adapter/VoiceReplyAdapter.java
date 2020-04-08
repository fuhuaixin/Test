package com.example.test0.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.test0.R;
import com.example.test0.bean.VoiceReplyBean;

import java.util.List;

public class VoiceReplyAdapter extends BaseMultiItemQuickAdapter<VoiceReplyBean, BaseViewHolder> {

    public VoiceReplyAdapter(List<VoiceReplyBean> data) {
        super(data);
        addItemType(1, R.layout.adapter_item_voice_ask);
        addItemType(2, R.layout.adapter_item_voice_reply);
    }

    @Override
    protected void convert(BaseViewHolder helper, VoiceReplyBean item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tvAsk, item.getMessage());
                break;
            case 2:
                helper.setText(R.id.tvReply, item.getMessage());
                helper.addOnClickListener(R.id.rlItem);
                break;
        }
    }
}
