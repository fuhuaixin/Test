package com.example.test0.bean;

public class CommunityBean {

    private String  name;
    private String address;
    private String busLine;
    private String phone;
    private String name2;
    private String phone2;


    public CommunityBean(String name, String phone, String name2, String phone2) {
        this.name = name;
        this.phone = phone;
        this.name2 = name2;
        this.phone2 = phone2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusLine() {
        return busLine;
    }

    public void setBusLine(String busLine) {
        this.busLine = busLine;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
