package com.example.eduSystems.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.eduSystems.models.tblAssignments;
import com.example.eduSystems.models.tblUsers;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class tblSubmissionDto {
    private int submissionid;
    private String fileurl;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date submitdate;
    private Double score;
    private String feedback;
    private String status;

    private Integer assignmentid;
    private Integer userid;

    //them cac truong lưu ten nguoi nộp, tên bài tập nếu cần
    private String fullname;
    
    public tblSubmissionDto() {}
    public tblSubmissionDto(int submissionid, String fileurl, Date submitdate, Double score, String feedback,
            String status, Integer assignmentid, Integer userid) {
        this.submissionid = submissionid;
        this.fileurl = fileurl;
        this.submitdate = submitdate;
        this.score = score;
        this.feedback = feedback;
        this.status = status;
        this.assignmentid = assignmentid;
        this.userid = userid;
    }
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
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
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
    public Integer getAssignmentid() {
        return assignmentid;
    }
    public void setAssignmentid(Integer assignmentid) {
        this.assignmentid = assignmentid;
    }
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    
}
