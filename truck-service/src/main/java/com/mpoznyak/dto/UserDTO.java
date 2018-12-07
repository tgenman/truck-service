package com.mpoznyak.dto;

/**
 * Created by Max Poznyak
 * on 11/14/18  at 13:09
 */

public class UserDTO {

    private Long id;

    private String companyId;

    private String password;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
