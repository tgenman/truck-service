package com.mpoznyak.dto;

import com.mpoznyak.model.type.RoutePointType;

/**
 * Created by Max Poznyak
 * on 29/10/2018  at 15:08
 */

public class RoutePointDTO {

    private Long id;

    private String customerName;

    private RoutePointType type = RoutePointType.PICK_UP;

    private Long orderId;

    private Integer routeSequenceIndex;

    private Long cityId;

    private CargoDTO cargoDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoutePointType getType() {
        return type;
    }

    public void setType(RoutePointType type) {
        this.type = type;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getRouteSequenceIndex() {
        return routeSequenceIndex;
    }

    public void setRouteSequenceIndex(Integer routeSequenceIndex) {
        this.routeSequenceIndex = routeSequenceIndex;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public CargoDTO getCargoDTO() {
        return cargoDTO;
    }

    public void setCargoDTO(CargoDTO cargoDTO) {
        this.cargoDTO = cargoDTO;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
