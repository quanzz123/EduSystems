package com.example.eduSystems.dto;

import java.util.Date;

public class tblLessonContentDto {
    private int contentid;
    private String title;
    private String contenttype;
    private String contenturl;
    private int duration;
    private int orderidx;
    private Date createdate;

    private Integer lessonid;

    public tblLessonContentDto() {
    }

    public tblLessonContentDto(int contentid, String title, String contenttype, String contenturl, int duration,
            int orderidx, Date createdate, Integer lessonid) {
        this.contentid = contentid;
        this.title = title;
        this.contenttype = contenttype;
        this.contenturl = contenturl;
        this.duration = duration;
        this.orderidx = orderidx;
        this.createdate = createdate;
        this.lessonid = lessonid;
    }

    public int getContentid() {
        return contentid;
    }

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getOrderidx() {
        return orderidx;
    }

    public void setOrderidx(int orderidx) {
        this.orderidx = orderidx;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getLessonid() {
        return lessonid;
    }

    public void setLessonid(Integer lessonid) {
        this.lessonid = lessonid;
    }

}
