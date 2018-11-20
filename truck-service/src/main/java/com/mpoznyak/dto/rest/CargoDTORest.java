package com.mpoznyak.dto.rest;

import com.mpoznyak.dto.CargoDTO;
import java.util.Objects;

/**
 * Created by Max Poznyak
 * on 11/19/18  at 18:39
 */
public class CargoDTORest {

    private Long id;
    private String name;
    private Long weight;
    private String status;
    private Long routePointDropOffId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRoutePointDropOff() {
        return routePointDropOffId;
    }

    public void setRoutePointDropOff(Long routePointDropOff) {
        this.routePointDropOffId = routePointDropOff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoDTORest cargo = (CargoDTORest) o;
        return Objects.equals(id, cargo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
