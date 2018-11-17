package com.mpoznyak.validator.form;

import javax.validation.constraints.*;

/**
 * Created by Max Poznyak
 * on 11/17/18  at 22:53
 */
public class TruckForm {

    private Long id;

    @NotNull
    @Size(min = 7, max = 7)
    @Pattern(regexp = "^[A-Za-z]{2}[0-9]{5}$")
    private String licensePlate;

    @NotNull
    private Long workingSession;

    @NotNull
    @Min(1)
    private Long capacity;

    @NotNull
    private String status;

    @NotNull
    @Size(min = 1, max = 30)
    private String brand;

    @NotNull
    @Size(min = 1, max = 30)
    private String model;

    @NotNull
    private Long city;

    @NotNull
    @Min(1)
    private Long maxDrivers;

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

    @Override
    public String toString() {
        return "TruckForm{" +
                "id=" + id +"\n" +
                ", licensePlate='" + licensePlate + '\'' + "\n" +
                ", workingSession=" + workingSession +"\n" +
                ", capacity=" + capacity +"\n" +
                ", status='" + status + '\'' +"\n" +
                ", brand='" + brand + '\'' +"\n" +
                ", model='" + model + '\'' +"\n" +
                ", city=" + city +"\n" +
                ", maxDrivers=" + maxDrivers +"\n" +
                '}';
    }
}
