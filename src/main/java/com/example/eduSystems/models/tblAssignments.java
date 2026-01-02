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
@Table(name = "tblAssignments")
public class tblAssignments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentid;
    private String title;
    private String description;
    private String fileurl;
    private Date deadline;
    private Date createdate;
    private Date modifieddate;
    private boolean active;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classid")
    private tblClasses clas;

    @OneToMany(mappedBy = "subm")
    private List<tblSubmissions> submissions;

    public int getAssignmentid() {
        return assignmentid;
    }

    public void setAssignmentid(int assignmentid) {
        this.assignmentid = assignmentid;
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

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
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

    public List<tblSubmissions> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<tblSubmissions> submissions) {
        this.submissions = submissions;
    }

    

    

}
