package com.mpoznyak.dto.rest;

import java.util.Objects;

/**
 * Created by Max Poznyak
 * on 11/19/18  at 19:19
 */

public class TruckDTORest {

    private Long id;
    private String licensePlate;
    private Long workingSession;
    private Long capacity;
    private String status;
    private String brand;
    private String model;
    private String cityName;
    private Long maxDrivers;
    private Boolean free;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getWorkingSession() {
        return workingSession;
    }

    public void setWorkingSession(Long workingSession) {
        this.workingSession = workingSession;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getMaxDrivers() {
        return maxDrivers;
    }

    public void setMaxDrivers(Long maxDrivers) {
        this.maxDrivers = maxDrivers;
    }

    public Boolean isFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckDTORest truck = (TruckDTORest) o;
        return Objects.equals(id, truck.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

