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
@Table(name = "tblAttendanceSessions")
public class tblAttendanceSessions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionid;
    private Date sessiondate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classid")
    private tblClasses clas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private tblUsers createby;

     @OneToMany(mappedBy = "session")
    private List<tblAttendaceRecords> records;

     public int getSessionid() {
         return sessionid;
     }

     public void setSessionid(int sessionid) {
         this.sessionid = sessionid;
     }

     public Date getSessiondate() {
         return sessiondate;
     }

     public void setSessiondate(Date sessiondate) {
         this.sessiondate = sessiondate;
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

     public List<tblAttendaceRecords> getRecords() {
         return records;
     }

     public void setRecords(List<tblAttendaceRecords> records) {
         this.records = records;
     }

    

}
