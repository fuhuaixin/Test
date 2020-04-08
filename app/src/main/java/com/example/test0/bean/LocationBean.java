package com.example.test0.bean;

public class LocationBean {

    private Double lat;
    private Double lng;

    public LocationBean(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "LocationBean{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
