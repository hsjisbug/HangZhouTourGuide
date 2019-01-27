package com.hzdy.zsy.hangzhoutourguide.bean;

import java.util.List;

public class HomeNewsPriceList {
    private String type;
    private List<HomeNewsEntityList> entityList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<HomeNewsEntityList> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<HomeNewsEntityList> entityList) {
        this.entityList = entityList;
    }

}
