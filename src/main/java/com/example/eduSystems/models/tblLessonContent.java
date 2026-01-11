package com.example.eduSystems.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblLessonContent")
public class tblLessonContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int contentid;
    private String title;
    private String contenttype;
    private String contenturl;
    private int duration;
    private int orderidx;
    private Date createdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessonid")
    private tblLessons lesson;


    
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

    public tblLessons getLesson() {
        return lesson;
    }

    public void setLesson(tblLessons lesson) {
        this.lesson = lesson;
    }

    

}
