package com.example.dotanewszxc;

public class ParseItem {
    private String imgUrl;
    private String title;
    private String detailUrl;
    private String dataTime;
    private String star;


    public ParseItem(String imgUrl, String title, String detailUrl, String dataTime, String star) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.detailUrl = detailUrl;
        this.dataTime = dataTime;
        this.star = star;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
