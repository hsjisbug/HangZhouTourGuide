package com.hzdy.zsy.hangzhoutourguide.model;

public class Weather {
    private String weather;
    private String temperature;
    private String position;

    public Weather(String weather,String temperature,String position){
        this.weather=weather;
        this.temperature=temperature;
        this.position=position;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
