package com.xx.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-27
 */
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 概述
     */
    private String survery;
    /**
     * 演示图片
     */
    private String pictureshow;
    /**
     * 发布时间
     */
    private String issuetime;
    /**
     * 招募时间
     */
    private String startime;
    /**
     * 结束时间
     */
    private String endtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSurvery() {
        return survery;
    }

    public void setSurvery(String survery) {
        this.survery = survery;
    }

    public String getPictureshow() {
        return pictureshow;
    }

    public void setPictureshow(String pictureshow) {
        this.pictureshow = pictureshow;
    }

    public String getIssuetime() {
        return issuetime;
    }

    public void setIssuetime(String issuetime) {
        this.issuetime = issuetime;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Event{" +
        "id=" + id +
        ", title=" + title +
        ", survery=" + survery +
        ", pictureshow=" + pictureshow +
        ", issuetime=" + issuetime +
        ", startime=" + startime +
        ", endtime=" + endtime +
        "}";
    }
}
