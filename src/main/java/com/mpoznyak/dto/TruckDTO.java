package com.mpoznyak.dto;

import com.mpoznyak.model.City;
import com.mpoznyak.model.type.TruckStatus;

import javax.persistence.*;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 23:02
 */

public class TruckDTO {

    private String licensePlate;

    private Integer workingSession;

    private Integer capacity;

    private String status;

    private String brand;

    private String model;

    private String city;

    private Integer maxDrivers;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getWorkingSession() {
        return workingSession;
    }

    public void setWorkingSession(Integer workingSession) {
        this.workingSession = workingSession;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getMaxDrivers() {
        return maxDrivers;
    }

    public void setMaxDrivers(Integer maxDrivers) {
        this.maxDrivers = maxDrivers;
    }
}
