package com.hzdy.zsy.hangzhoutourguide.model;

import android.graphics.Point;

import java.util.List;

public class Sciencespot {
    private String ss_name;
    private String address;
    private Point point;
    private String ss_message;
    private String ss_voice_introduce;
    private List<Point> wc;
    private String food;
    private List<Point> in_and_out;
    private List<Point> hotel;
    private List<Point> parking_lot;
    private List<Point> bus_stop;
    private List<Point> visitor_center;

    public String getSs_name() {
        return ss_name;
    }

    public void setSs_name(String ss_name) {
        this.ss_name = ss_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getSs_message() {
        return ss_message;
    }

    public void setSs_message(String ss_message) {
        this.ss_message = ss_message;
    }

    public String getSs_voice_introduce() {
        return ss_voice_introduce;
    }

    public void setSs_voice_introduce(String ss_voice_introduce) {
        this.ss_voice_introduce = ss_voice_introduce;
    }

    public List<Point> getWc() {
        return wc;
    }

    public void setWc(List<Point> wc) {
        this.wc = wc;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public List<Point> getIn_and_out() {
        return in_and_out;
    }

    public void setIn_and_out(List<Point> in_and_out) {
        this.in_and_out = in_and_out;
    }

    public List<Point> getHotel() {
        return hotel;
    }

    public void setHotel(List<Point> hotel) {
        this.hotel = hotel;
    }

    public List<Point> getParking_lot() {
        return parking_lot;
    }

    public void setParking_lot(List<Point> parking_lot) {
        this.parking_lot = parking_lot;
    }

    public List<Point> getBus_stop() {
        return bus_stop;
    }

    public void setBus_stop(List<Point> bus_stop) {
        this.bus_stop = bus_stop;
    }

    public List<Point> getVisitor_center() {
        return visitor_center;
    }

    public void setVisitor_center(List<Point> visitor_center) {
        this.visitor_center = visitor_center;
    }
}
