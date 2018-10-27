package com.mpoznyak.dto;

/**
 * Created by Max Poznyak
 * on 27/10/2018  at 15:34
 */

public class CompoundRoutePointDTO {

    private Long orderId;

    private String cityPickUp;

    private String cityDropOff;

    private String cargoName;

    private String cargoWeight;

    public String getCityPickUp() {
        return cityPickUp;
    }

    public void setCityPickUp(String cityPickUp) {
        this.cityPickUp = cityPickUp;
    }

    public String getCityDropOff() {
        return cityDropOff;
    }

    public void setCityDropOff(String cityDropOff) {
        this.cityDropOff = cityDropOff;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(String cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
