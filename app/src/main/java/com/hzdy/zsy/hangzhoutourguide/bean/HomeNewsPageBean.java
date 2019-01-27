package com.hzdy.zsy.hangzhoutourguide.bean;

import java.util.List;

public class HomeNewsPageBean {
    private String allPages;
    private List<HomeNewsContentList> contentlist;
    private String currentPage;
    private String allNum;
    private String maxResult;

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    public String getAllPages() {
        return allPages;
    }

    public void setAllPages(String allPages) {
        this.allPages = allPages;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(String maxResult) {
        this.maxResult = maxResult;
    }

    public List<HomeNewsContentList> getContentlist() {
        return contentlist;
    }

    public void setContentlist(List<HomeNewsContentList> contentlist) {
        this.contentlist = contentlist;
    }

}
