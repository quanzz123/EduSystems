package com.example.eduSystems.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tblRoles")
public class tblRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleid;

    private String rolename;
    private String description;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
