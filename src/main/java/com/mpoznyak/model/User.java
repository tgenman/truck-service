package com.mpoznyak.model;


import com.mpoznyak.model.type.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Max Poznyak
 * on 18/10/2018  at 20:15
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    
    @Column(name = "companyId")
    protected Long companyId;

    @Column(name = "password")
    protected String password;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companySpecId) {
        this.companyId = companySpecId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
