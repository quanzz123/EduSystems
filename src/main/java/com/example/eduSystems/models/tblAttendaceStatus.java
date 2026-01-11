package com.example.eduSystems.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblAttendanceStatus")
public class tblAttendaceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int statusid;
    private String title;
    private String description;

    @OneToMany(mappedBy = "status")
    private List<tblAttendaceRecords> records;

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
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

    public List<tblAttendaceRecords> getRecords() {
        return records;
    }

    public void setRecords(List<tblAttendaceRecords> records) {
        this.records = records;
    }

    

}
