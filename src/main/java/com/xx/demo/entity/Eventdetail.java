package com.xx.demo.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoxiao
 * @since 2019-03-27
 */
public class Eventdetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 关联支教信息id
     */
    private String eventid;
    private String event1;
    private String event2;
    private String event3;
    private String event4;
    private String event5;
    private String pic1;
    private String pic2;
    private String pic3;
    private String pic4;
    private String pic5;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getEvent1() {
        return event1;
    }

    public void setEvent1(String event1) {
        this.event1 = event1;
    }

    public String getEvent2() {
        return event2;
    }

    public void setEvent2(String event2) {
        this.event2 = event2;
    }

    public String getEvent3() {
        return event3;
    }

    public void setEvent3(String event3) {
        this.event3 = event3;
    }

    public String getEvent4() {
        return event4;
    }

    public void setEvent4(String event4) {
        this.event4 = event4;
    }

    public String getEvent5() {
        return event5;
    }

    public void setEvent5(String event5) {
        this.event5 = event5;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4;
    }

    public String getPic5() {
        return pic5;
    }

    public void setPic5(String pic5) {
        this.pic5 = pic5;
    }

    @Override
    public String toString() {
        return "Eventdetail{" +
        "id=" + id +
        ", eventid=" + eventid +
        ", event1=" + event1 +
        ", event2=" + event2 +
        ", event3=" + event3 +
        ", event4=" + event4 +
        ", event5=" + event5 +
        ", pic1=" + pic1 +
        ", pic2=" + pic2 +
        ", pic3=" + pic3 +
        ", pic4=" + pic4 +
        ", pic5=" + pic5 +
        "}";
    }
}
