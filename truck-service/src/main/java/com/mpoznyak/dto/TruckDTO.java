package com.mpoznyak.dto;

/**
 * Created by Max Poznyak
 * on 23/10/2018  at 23:02
 */

public class TruckDTO {

    private Long id;

    private String licensePlate;

    private Long workingSession;

    private Long capacity;

    private String status;

    private String brand;

    private String model;

    private Long city;

    private String cityName;

    private Long maxDrivers;

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

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getMaxDrivers() {
        return maxDrivers;
    }

    public void setMaxDrivers(Long maxDrivers) {
        this.maxDrivers = maxDrivers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
