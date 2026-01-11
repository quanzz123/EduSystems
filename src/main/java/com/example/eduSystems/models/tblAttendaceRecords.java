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
@Table(name = "tblAttendanceRecords")
public class tblAttendaceRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int recordid;
    private String note;
    private Date recorddate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private tblUsers createby;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessionid")
    private tblAttendanceSessions session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statusid")
    private tblAttendaceStatus status;

    public int getRecordid() {
        return recordid;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public tblUsers getCreateby() {
        return createby;
    }

    public void setCreateby(tblUsers createby) {
        this.createby = createby;
    }

    public tblAttendanceSessions getSession() {
        return session;
    }

    public void setSession(tblAttendanceSessions session) {
        this.session = session;
    }

    public tblAttendaceStatus getStatus() {
        return status;
    }

    public void setStatus(tblAttendaceStatus status) {
        this.status = status;
    }

    


}
