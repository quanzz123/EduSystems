package com.example.eduSystems.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tblClassMembers")
public class tblClassMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberid;
    private Date joindate;
    private String status;
    private double progress;
    private double finalscore;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classid")
    private tblClasses clas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private tblUsers user;


    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getFinalscore() {
        return finalscore;
    }

    public void setFinalscore(double finalscore) {
        this.finalscore = finalscore;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public tblClasses getClas() {
        return clas;
    }

    public void setClas(tblClasses clas) {
        this.clas = clas;
    }

    public tblUsers getUser() {
        return user;
    }

    public void setUser(tblUsers user) {
        this.user = user;
    }
}
