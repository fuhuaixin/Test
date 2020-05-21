package com.example.test0.bean;

public class CommunityBean {

    private String  name;
    private String address;
    private String busLine;
    private String phone;

    public CommunityBean(String name, String address, String busLine, String phone) {
        this.name = name;
        this.address = address;
        this.busLine = busLine;
        this.phone = phone;
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
