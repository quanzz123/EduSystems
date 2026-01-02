package com.example.eduSystems.models;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tblClasses")
public class tblClasses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classid;
    private String classname;
    private String description;
    private String subject;
    private Date startdate;
    private Date enddate;
    private String schedule;
    private String imagename;
    private Date createdate;
    private Date modifieddate;
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private tblUsers teacher;

    @OneToMany(mappedBy = "clas")
    private List<tblClassMembers> members;

    @OneToMany(mappedBy = "clas")
    private List<tblAssignments> assignments;
    
    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public tblUsers getTeacher() {
        return teacher;
    }

    public void setTeacher(tblUsers teacher) {
        this.teacher = teacher;
    }

    public List<tblClassMembers> getMembers() {
        return members;
    }

    public void setMembers(List<tblClassMembers> members) {
        this.members = members;
    }

    public List<tblAssignments> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<tblAssignments> assignments) {
        this.assignments = assignments;
    }

    
}
