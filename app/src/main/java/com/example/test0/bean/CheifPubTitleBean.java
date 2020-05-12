package com.example.test0.bean;

public class CheifPubTitleBean {
    String title="";
    Boolean isChoose =false;

    public CheifPubTitleBean(String title, Boolean isChoose) {
        this.title = title;
        this.isChoose = isChoose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getChoose() {
        return isChoose;
    }

    public void setChoose(Boolean choose) {
        isChoose = choose;
    }
}
