package com.mpoznyak.model;

import com.mpoznyak.model.type.RoleType;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private RoleType type;
    
    @Column(name = "company_spec_id")
    private Long companySpecId;

    @Column(name = "password")
    private String password;

    public Long getCompanySpecId() {
        return companySpecId;
    }

    public void setCompanySpecId(Long companySpecId) {
        this.companySpecId = companySpecId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
