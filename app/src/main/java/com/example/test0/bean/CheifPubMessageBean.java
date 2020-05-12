package com.example.test0.bean;

public class CheifPubMessageBean {
    String message="";
    String id ="";
    Boolean isChoose =false;

    public CheifPubMessageBean(String message, Boolean isChoose,String id) {
        this.message = message;
        this.isChoose = isChoose;
        this.id =id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String title) {
        this.message = title;
    }

    public Boolean getChoose() {
        return isChoose;
    }

    public void setChoose(Boolean choose) {
        isChoose = choose;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
