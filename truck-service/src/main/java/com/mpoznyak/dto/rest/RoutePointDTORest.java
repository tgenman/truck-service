package com.mpoznyak.dto.rest;

import java.util.List;
import java.util.Objects;

/**
 * Created by Max Poznyak
 * on 11/19/18  at 19:12
 */
public class RoutePointDTORest {

    private Long id;
    private String type;
    private Boolean completed;
    private Long orderId;
    private String cityName;
    private Long cargoId;
    private List<Long> cargoesForDropId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    public List<Long> getCargoesForDropId() {
        return cargoesForDropId;
    }

    public void setCargoesForDropId(List<Long> cargoesForDropId) {
        this.cargoesForDropId = cargoesForDropId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutePointDTORest that = (RoutePointDTORest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
