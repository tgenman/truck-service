package com.mpoznyak.validator.form;

import com.mpoznyak.model.City;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;

/**
 * Created by Max Poznyak
 * on 11/16/18  at 23:12
 */
public class DriverForm {



    @NotNull
    @Size(min = 2, max = 30)
    String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    String lastName;

    @NotNull
    Long city;

    @NotNull
    @Size(min = 6, max = 10)
    private String username;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DriverForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city=" + city +
                '}';
    }
}
