package com.example.eduSystems.dto;

import java.util.Date;
import java.util.List;

public class tblLessonDto {
    private int lessonid;
    private String title;
    private String description;
    private int orderidx;
    private Date createdate;
    private boolean active;

    private Integer classid;
    private Integer createbyid;
    private List<tblLessonContentDto> lessoncontent;

    public tblLessonDto() {}

    public tblLessonDto(int lessonid, String title, String description, int orderidx, Date createdate, boolean active,
            Integer classid, Integer createbyid, List<tblLessonContentDto> lessoncontent) {
        this.lessonid = lessonid;
        this.title = title;
        this.description = description;
        this.orderidx = orderidx;
        this.createdate = createdate;
        this.active = active;
        this.classid = classid;
        this.createbyid = createbyid;
        this.lessoncontent = lessoncontent;
    }

    public int getLessonid() {
        return lessonid;
    }

    public void setLessonid(int lessonid) {
        this.lessonid = lessonid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getCreatebyid() {
        return createbyid;
    }

    public void setCreatebyid(Integer createbyid) {
        this.createbyid = createbyid;
    }

    public List<tblLessonContentDto> getLessoncontent() {
        return lessoncontent;
    }

    public void setLessoncontent(List<tblLessonContentDto> lessoncontent) {
        this.lessoncontent = lessoncontent;
    }
    
}
