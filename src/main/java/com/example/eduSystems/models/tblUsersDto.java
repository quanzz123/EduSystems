package com.example.eduSystems.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class tblUsersDto {

    @NotNull(message = "Role is required")
    private Integer roleid;
    @NotEmpty(message = "username is required")
    private String username;
    @NotEmpty(message = "pass is required")
    private String passwordhash;
    @NotEmpty(message = "fullname is required")
    private String fullname;
    @Email(message = "Invalid email")
    private String email;
    private String phone;
    private String address;
    private Date created;
    private boolean active;

    public @NotNull(message = "Role is required") Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(@NotNull(message = "Role is required") Integer roleid) {
        this.roleid = roleid;
    }

    public @NotEmpty(message = "username is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "username is required") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "pass is required") String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(@NotEmpty(message = "pass is required") String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public @NotEmpty(message = "fullname is required") String getFullname() {
        return fullname;
    }

    public void setFullname(@NotEmpty(message = "fullname is required") String fullname) {
        this.fullname = fullname;
    }

    public @Email(message = "Invalid email") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email") String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
