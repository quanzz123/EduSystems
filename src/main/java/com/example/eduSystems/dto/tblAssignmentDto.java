package com.example.eduSystems.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.eduSystems.models.tblSubmissions;

import jakarta.validation.constraints.NotEmpty;

public class tblAssignmentDto {
    private int assignmentid;
    @NotEmpty(message = "title is requied")
    private String title;
    private String description;
    private String fileurl;
    @DateTimeFormat(pattern = "dd/MM/yyy")
    private Date deadline;
    @DateTimeFormat(pattern = "dd/MM/yyy")
    private Date createdate;
    @DateTimeFormat(pattern = "dd/MM/yyy")
    private Date modifieddate;
    private Boolean active;
    private List<tblSubmissionDto> submissions;
    private Integer classid;

    public tblAssignmentDto() {}
    
    public tblAssignmentDto(int assignmentid, @NotEmpty(message = "title is requied") String title, String description,
            String fileurl, Date deadline, Date createdate, Date modifieddate, Boolean active,
            List<tblSubmissionDto> submissions, Integer classid) {
        this.assignmentid = assignmentid;
        this.title = title;
        this.description = description;
        this.fileurl = fileurl;
        this.deadline = deadline;
        this.createdate = createdate;
        this.modifieddate = modifieddate;
        this.active = active;
        this.submissions = submissions;
        this.classid = classid;
    }

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
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public List<tblSubmissionDto> getSubmissions() {
        return submissions;
    }
    public void setSubmissions(List<tblSubmissionDto> submissions) {
        this.submissions = submissions;
    }
    public Integer getClassid() {
        return classid;
    }
    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    
}
