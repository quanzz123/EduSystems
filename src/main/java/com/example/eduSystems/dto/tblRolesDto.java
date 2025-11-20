package com.example.eduSystems.dto;

import jakarta.validation.constraints.NotEmpty;

public class tblRolesDto {

    @NotEmpty(message = "rolename is required")
    private String rolename;
    private String description;

    public @NotEmpty(message = "rolename is required") String getRolename() {
        return rolename;
    }

    public void setRolename(@NotEmpty(message = "rolename is required") String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
