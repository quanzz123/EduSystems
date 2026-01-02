package com.example.eduSystems.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class tblClassesDto {

    private int classid;
    @NotEmpty(message = "class name is requied!")

    private String classname;

    private String description;

    @NotEmpty(message = "Subject is requied")
    private String subject;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Start Date is requied")
    private Date startdate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "End Date is requied")
    private Date enddate;

    @NotEmpty(message = "Schedule Date is requied")
    private String schedule;

    private String imagename;

    private Date createdate;

    private Date modifieddate;

    private Boolean active;

    private Integer teacherid;

    private String teachername;

    private List<tblClassMembersDto> members;

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public @NotEmpty(message = "class name is requied!") String getClassname() {
        return classname;
    }

    public void setClassname(@NotEmpty(message = "class name is requied!") String classname) {
        this.classname = classname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotEmpty(message = "Subject is requied") String getSubject() {
        return subject;
    }

    public void setSubject(@NotEmpty(message = "Subject is requied") String subject) {
        this.subject = subject;
    }

    public @NotNull(message = "Start Date is requied") Date getStartdate() {
        return startdate;
    }

    public void setStartdate(@NotNull(message = "Start Date is requied") Date startdate) {
        this.startdate = startdate;
    }

    public @NotNull(message = "End Date is requied") Date getEnddate() {
        return enddate;
    }

    public void setEnddate(@NotNull(message = "End Date is requied") Date enddate) {
        this.enddate = enddate;
    }

    public @NotEmpty(message = "Schedule Date is requied") String getSchedule() {
        return schedule;
    }

    public void setSchedule(@NotEmpty(message = "Schedule Date is requied") String schedule) {
        this.schedule = schedule;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
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

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public List<tblClassMembersDto> getMembers() {
        return members;
    }

    public void setMembers(List<tblClassMembersDto> members) {
        this.members = members;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
