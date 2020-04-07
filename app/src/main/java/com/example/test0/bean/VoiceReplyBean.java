package com.example.test0.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class VoiceReplyBean implements MultiItemEntity {

    private String message="";
    private int itemType =0;

    public VoiceReplyBean(String message, int itemType) {
        this.message = message;
        this.itemType = itemType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
