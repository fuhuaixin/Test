package com.example.test0.bean;

public class WebInfoBean {

    private  String title ="";
    private String date ="";
    private String h5Url="";
    private int IsChoose=0;

    public WebInfoBean(String title, String date, String h5Url,int isChoose) {
        this.title = title;
        this.date = date;
        IsChoose = isChoose;
        this.h5Url =h5Url;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsChoose() {
        return IsChoose;
    }

    public void setIsChoose(int isChoose) {
        IsChoose = isChoose;
    }
}
