package com.example.eduSystems.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblLessons")
public class tblLessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int lessonid;
    private String title;
    private String description;
    private int orderidx;
    private Date createdate;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classid")
    private tblClasses clas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private tblUsers createby;

    @OneToMany(mappedBy = "lesson")
    private List<tblLessonContent> lessoncontent;

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

    public tblClasses getClas() {
        return clas;
    }

    public void setClas(tblClasses clas) {
        this.clas = clas;
    }

    public tblUsers getCreateby() {
        return createby;
    }

    public void setCreateby(tblUsers createby) {
        this.createby = createby;
    }

    public List<tblLessonContent> getLessoncontent() {
        return lessoncontent;
    }

    public void setLessoncontent(List<tblLessonContent> lessoncontent) {
        this.lessoncontent = lessoncontent;
    }

    
    
}
