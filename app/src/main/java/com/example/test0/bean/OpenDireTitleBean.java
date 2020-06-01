package com.example.test0.bean;

public class OpenDireTitleBean {

    private String title;
    private Boolean isChoose;
    private int image;

    public OpenDireTitleBean(String title, Boolean isChoose) {
        this.title = title;
        this.isChoose = isChoose;
    }

    public OpenDireTitleBean(String title, Boolean isChoose,int image) {
        this.title = title;
        this.isChoose = isChoose;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
