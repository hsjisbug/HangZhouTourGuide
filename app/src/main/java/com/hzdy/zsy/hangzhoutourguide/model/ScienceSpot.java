package com.hzdy.zsy.hangzhoutourguide.model;

import java.util.ArrayList;

public class ScienceSpot {
    private String cityName;
    private String areaName;
    private String summary;
    private String address;
    private String name;
    private double lat;
    private double lon;
    private double price;
    private String star;
    private String content;
    private ArrayList<String> picUrlList;
    private ArrayList<String> picSmallList;
    private String openTime;
    private String coupon;
    private String attention;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(ArrayList<String> picUrlList) {
        this.picUrlList = picUrlList;
    }

    public ArrayList<String> getPicSmallList() {
        return picSmallList;
    }

    public void setPicSmallList(ArrayList<String> picSmallList) {
        this.picSmallList = picSmallList;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

}
