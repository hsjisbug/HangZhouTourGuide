package com.hzdy.zsy.hangzhoutourguide.model;

import android.graphics.Point;

public class Attractions {

    private int id;
    private String at_name;
    private int belong_ss;
    private String at_message;
    private Point position;
    private String at_voice_introduce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAt_name() {
        return at_name;
    }

    public void setAt_name(String at_name) {
        this.at_name = at_name;
    }

    public int getBelong_ss() {
        return belong_ss;
    }

    public void setBelong_ss(int belong_ss) {
        this.belong_ss = belong_ss;
    }

    public String getAt_message() {
        return at_message;
    }

    public void setAt_message(String at_message) {
        this.at_message = at_message;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public String getAt_voice_introduce() {
        return at_voice_introduce;
    }

    public void setAt_voice_introduce(String at_voice_introduce) {
        this.at_voice_introduce = at_voice_introduce;
    }
}
