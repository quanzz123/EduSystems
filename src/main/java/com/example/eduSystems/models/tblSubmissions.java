package com.example.eduSystems.models;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblSubmissions")
public class tblSubmissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int submissionid;
    private String fileurl;
    private Date submitdate;
    private double score;
    private String feedback;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignmentid")
    private tblAssignments subm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private tblUsers user;

    public int getSubmissionid() {
        return submissionid;
    }

    public void setSubmissionid(int submissionid) {
        this.submissionid = submissionid;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public tblAssignments getSubm() {
        return subm;
    }

    public void setSubm(tblAssignments subm) {
        this.subm = subm;
    }

    public tblUsers getUser() {
        return user;
    }

    public void setUser(tblUsers user) {
        this.user = user;
    }

    
}
